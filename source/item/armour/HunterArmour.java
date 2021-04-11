package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
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
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.tslat.aoa3.item.SkillItem;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.constant.Skills;
import net.tslat.aoa3.util.player.PlayerDataManager;
import net.tslat.aoa3.util.skill.HunterUtil;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public class HunterArmour extends AdventArmour implements SkillItem {
	private static final AttributeModifier HUNTER_ARMOUR_KNOCKBACK = new AttributeModifier(UUID.fromString("a794717e-8b9b-4d20-b224-0a7571ddd012"), "AoAHunterArmourBuff", 0.5, AttributeModifier.Operation.ADDITION);

	public HunterArmour(EquipmentSlotType slot) {
		super(ItemUtil.customArmourMaterial("aoa3:hunter", 65, new int[] {6, 7, 9, 4}, 10, SoundEvents.ARMOR_EQUIP_GENERIC, 7), slot, Rarity.RARE);
	}

	@Override
	public AdventArmour.Type setType() {
		return AdventArmour.Type.HUNTER;
	}

	@Override
	public Skills getSkill() {
		return Skills.HUNTER;
	}

	@Override
	public int getLevelReq() {
		return 100;
	}

	@Override
	public void addBuffs(PlayerDataManager.PlayerBuffs plBuffs, @Nullable EquipmentSlotType slot) {
		if (slot == null)
			plBuffs.addXpModifier(Skills.HUNTER, 0.3f);
	}

	@Override
	public void removeBuffs(PlayerDataManager.PlayerBuffs plBuffs, @Nullable EquipmentSlotType slot) {
		if (slot == null)
			plBuffs.removeXpModifier(Skills.HUNTER, 0.3f);
	}

	@Override
	public void onEquip(PlayerDataManager plData, @Nullable EquipmentSlotType slot) {
		EntityUtil.applyAttributeModifierSafely(plData.player(), Attributes.KNOCKBACK_RESISTANCE, HUNTER_ARMOUR_KNOCKBACK);
	}

	@Override
	public void onUnequip(PlayerDataManager plData, @Nullable EquipmentSlotType slot) {
		EntityUtil.removeAttributeModifier(plData.player(), Attributes.KNOCKBACK_RESISTANCE, HUNTER_ARMOUR_KNOCKBACK);
	}

	@Override
	public void onDamageDealt(PlayerDataManager plData, @Nullable HashSet<EquipmentSlotType> slots, LivingHurtEvent event) {
		if (slots == null && HunterUtil.isHunterCreature(event.getEntityLiving()))
			event.setAmount(event.getAmount() * 1.15f);
	}

	@Override
	public void onAttackReceived(PlayerDataManager plData, @Nullable HashSet<EquipmentSlotType> slots, LivingHurtEvent event) {
		if (slots == null && event.getSource().getEntity() instanceof LivingEntity && HunterUtil.isHunterCreature((LivingEntity)event.getSource().getEntity()))
			event.setAmount(event.getAmount() * 0.85f);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(setEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.hunter_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.hunter_armour.desc.2", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.hunter_armour.desc.3", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Constants.XP_BONUS, LocaleUtil.ItemDescriptionType.BENEFICIAL, new StringTextComponent("30"), new TranslationTextComponent(LocaleUtil.Constants.HUNTER)));
		tooltip.add(LocaleUtil.getFormattedLevelRestrictedDescriptionText(Skills.HUNTER, 100));
	}
}
