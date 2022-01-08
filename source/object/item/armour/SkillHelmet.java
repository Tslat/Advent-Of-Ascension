package net.tslat.aoa3.object.item.armour;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.tslat.aoa3.client.model.armor.AoAArmourModels;
import net.tslat.aoa3.player.ClientPlayerDataManager;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Supplier;

public class SkillHelmet extends AdventArmour {
	private final Supplier<AoASkill> skill;

	public SkillHelmet(Supplier<AoASkill> skill) {
		super(ArmorMaterial.DIAMOND, EquipmentSlotType.HEAD);

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
	public Type setType() {
		return Type.ALL;
	}

	@Override
	public boolean isEnchantable(ItemStack pStack) {
		return true;
	}

	@Override
	public void onEquip(ServerPlayerDataManager plData, @Nullable EquipmentSlotType slot) {
		plData.getSkill(getSkill()).applyXpModifier(0.5f);
	}

	@Override
	public void onUnequip(ServerPlayerDataManager plData, @Nullable EquipmentSlotType slot) {
		plData.getSkill(getSkill()).removeXpModifier(0.5f);
	}

	@OnlyIn(Dist.CLIENT)
	@Nullable
	@Override
	public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
		return (A)AoAArmourModels.getSkillHelmetModel(getSkill(), entityLiving instanceof PlayerEntity && ClientPlayerDataManager.get().getSkill(getSkill()).hasLevel(1000));
	}

	@OnlyIn(Dist.CLIENT)
	@Nullable
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
		ResourceLocation texturePath = AoAArmourModels.getSkillHelmetTexture(getSkill(), entity instanceof PlayerEntity && ClientPlayerDataManager.get().getSkill(getSkill()).hasLevel(1000));

		return texturePath == null ? null : texturePath.toString();
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World pLevel, List<ITextComponent> tooltip, ITooltipFlag pFlag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.unbreakable", LocaleUtil.ItemDescriptionType.UNIQUE));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Constants.XP_BONUS, LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO, LocaleUtil.numToComponent(50), getSkill().getName()));
		tooltip.add(anySetEffectHeader());
	}
}
