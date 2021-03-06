package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.item.SkillItem;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.constant.Skills;
import net.tslat.aoa3.util.player.PlayerDataManager;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class InnervationArmour extends AdventArmour implements SkillItem {
	private static final AttributeModifier INNERVATION_ARMOUR_BUFF = new AttributeModifier(UUID.fromString("bc07e37c-9b4b-4bc3-8aa5-a613b8d3c257"), "AoAInnervationArmourSet", 10d, AttributeModifier.Operation.ADDITION);

	public InnervationArmour(EquipmentSlotType slot) {
		super(ItemUtil.customArmourMaterial("aoa3:innervation", 65, new int[] {6, 7, 9, 4}, 10, SoundEvents.ARMOR_EQUIP_GENERIC, 7), slot, Rarity.RARE);
	}

	@Override
	public AdventArmour.Type setType() {
		return AdventArmour.Type.INNERVATION;
	}

	@Override
	public Skills getSkill() {
		return Skills.INNERVATION;
	}

	@Override
	public int getLevelReq() {
		return 100;
	}

	@Override
	public void addBuffs(PlayerDataManager.PlayerBuffs plBuffs, @Nullable EquipmentSlotType slot) {
		if (slot == null)
			plBuffs.addXpModifier(Skills.INNERVATION, 0.3f);
	}

	@Override
	public void removeBuffs(PlayerDataManager.PlayerBuffs plBuffs, @Nullable EquipmentSlotType slot) {
		if (slot == null)
			plBuffs.removeXpModifier(Skills.INNERVATION, 0.3f);
	}

	@Override
	public void onEquip(PlayerDataManager plData, @Nullable EquipmentSlotType slot) {
		if (slot == null)
			EntityUtil.applyAttributeModifierSafely(plData.player(), Attributes.MAX_HEALTH, INNERVATION_ARMOUR_BUFF);
	}

	@Override
	public void onUnequip(PlayerDataManager plData, @Nullable EquipmentSlotType slot) {
		if (slot == null)
			EntityUtil.removeAttributeModifier(plData.player(), Attributes.MAX_HEALTH, INNERVATION_ARMOUR_BUFF);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(setEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.innervation_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Constants.XP_BONUS, LocaleUtil.ItemDescriptionType.BENEFICIAL, new StringTextComponent("30"), new TranslationTextComponent(LocaleUtil.Constants.INNERVATION)));
		tooltip.add(LocaleUtil.getFormattedLevelRestrictedDescriptionText(Skills.INNERVATION, 100));
	}
}
