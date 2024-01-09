package net.tslat.aoa3.content.item.armour;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.model.armor.AoAMiscModels;
import net.tslat.aoa3.player.ClientPlayerDataManager;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.RegistryUtil;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class SkillHelmet extends AdventArmour {
	private static final ArmorMaterial MATERIAL = ItemUtil.customArmourMaterial("skill_helmet", 50, new int[] {5, 8, 9, 5}, 20, SoundEvents.ARMOR_EQUIP_LEATHER, 7);
	private final Supplier<AoASkill> skill;

	public SkillHelmet(Supplier<AoASkill> skill) {
		super(MATERIAL, ArmorItem.Type.HELMET, Rarity.RARE);

		this.skill = skill;
	}

	public AoASkill getSkill() {
		return this.skill.get();
	}

	@Override
	public int getMaxDamage(ItemStack stack) {
		return 0;
	}

	@Override
	public boolean canBeDepleted() {
		return false;
	}

	@Override
	public Type getSetType() {
		return Type.ALL;
	}

	@Override
	public boolean isEnchantable(ItemStack pStack) {
		return true;
	}

	@Override
	public void onEquip(ServerPlayerDataManager plData, @Nullable EquipmentSlot slot) {
		plData.getSkill(getSkill()).applyXpModifier(0.5f);
	}

	@Override
	public void onUnequip(ServerPlayerDataManager plData, @Nullable EquipmentSlot slot) {
		plData.getSkill(getSkill()).removeXpModifier(0.5f);
	}

	@Override
	public void initializeClient(Consumer<IClientItemExtensions> consumer) {
		consumer.accept(new IClientItemExtensions() {
			@Nonnull
			@Override
			public Model getGenericArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> defaultModel) {
				return AoAMiscModels.getSkillHelmetModel(getSkill(), entityLiving instanceof Player && ClientPlayerDataManager.get().getSkill(getSkill()).hasLevel(1000), defaultModel);
			}
		});
	}

	@Nullable
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
		return AdventOfAscension.id("textures/models/armor/custom/" + RegistryUtil.getId(stack.getItem()).getPath() + (ClientPlayerDataManager.get().getSkill(getSkill()).getLevel(true) == 1000 ? "_trim" : "") + ".png").toString();
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level pLevel, List<Component> tooltip, TooltipFlag pFlag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.UNBREAKABLE, LocaleUtil.ItemDescriptionType.UNIQUE));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.XP_BONUS, LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO, LocaleUtil.numToComponent(50), getSkill().getName()));
		tooltip.add(anySetEffectHeader());
	}
}
