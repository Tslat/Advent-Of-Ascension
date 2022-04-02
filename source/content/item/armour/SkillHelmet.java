package net.tslat.aoa3.content.item.armour;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Supplier;

public class SkillHelmet extends AdventArmour {
	private final Supplier<AoASkill> skill;

	public SkillHelmet(Supplier<AoASkill> skill) {
		super(ArmorMaterials.DIAMOND, EquipmentSlot.HEAD);

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
	public void onEquip(ServerPlayerDataManager plData, @Nullable EquipmentSlot slot) {
		plData.getSkill(getSkill()).applyXpModifier(0.5f);
	}

	@Override
	public void onUnequip(ServerPlayerDataManager plData, @Nullable EquipmentSlot slot) {
		plData.getSkill(getSkill()).removeXpModifier(0.5f);
	}

	/*@OnlyIn(Dist.CLIENT)
	@Nullable
	@Override
	public <A extends HumanoidModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, A _default) {
		return (A)AoAArmourModels.getSkillHelmetModel(getSkill(), entityLiving instanceof Player && ClientPlayerDataManager.get().getSkill(getSkill()).hasLevel(1000));
	}

	@OnlyIn(Dist.CLIENT)
	@Nullable
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
		ResourceLocation texturePath = AoAArmourModels.getSkillHelmetTexture(getSkill(), entity instanceof Player && ClientPlayerDataManager.get().getSkill(getSkill()).hasLevel(1000));

		return texturePath == null ? null : texturePath.toString();
	}*/

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level pLevel, List<Component> tooltip, TooltipFlag pFlag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.unbreakable", LocaleUtil.ItemDescriptionType.UNIQUE));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Constants.XP_BONUS, LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO, LocaleUtil.numToComponent(50), getSkill().getName()));
		tooltip.add(anySetEffectHeader());
	}
}
