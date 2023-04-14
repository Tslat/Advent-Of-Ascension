package net.tslat.aoa3.content.item.armour;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public class KnightArmour extends AdventArmour {
	public static final AttributeModifier KNIGHT_BOOTS_BUFF = new AttributeModifier(UUID.fromString("9283b669-bc04-4055-a165-73e3a2b5ab7e"), "AoAKnightArmourBoots", 1.5d, AttributeModifier.Operation.ADDITION);
	public static final AttributeModifier KNIGHT_LEGS_BUFF = new AttributeModifier(UUID.fromString("e60b8cda-a196-4922-b867-01d2422a9e8c"), "AoAKnightArmourLegs", 1.5d, AttributeModifier.Operation.ADDITION);
	public static final AttributeModifier KNIGHT_CHESTPLATE_BUFF = new AttributeModifier(UUID.fromString("8ecbc122-563a-4de5-8f27-3f461ad2fb5c"), "AoAKnightArmourBody", 1.5d, AttributeModifier.Operation.ADDITION);
	public static final AttributeModifier KNIGHT_HELMET_BUFF = new AttributeModifier(UUID.fromString("673ef5d8-9df5-4dbb-84f0-1da677d59f05"), "AoAKnightArmourHelmet", 1.5d, AttributeModifier.Operation.ADDITION);

	public KnightArmour(ArmorItem.Type slot) {
		super(ItemUtil.customArmourMaterial("aoa3:knight", 70, new int[] {4, 8, 9, 5}, 10, SoundEvents.ARMOR_EQUIP_GENERIC, 7), slot);
	}

	@Override
	public Type setType() {
		return Type.KNIGHT;
	}

	@Override
	public void onEffectTick(ServerPlayerDataManager plData, @Nullable HashSet<EquipmentSlot> slots) {
		if (slots == null && EntityUtil.checkBelowHealthPercentThreshold(plData.player(), 0.2f))
			plData.player().addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 0, 1, false, true));
	}

	@Override
	public void onEquip(ServerPlayerDataManager plData, @Nullable EquipmentSlot slot) {
		if (slot != null) {
			switch (slot) {
				case FEET:
					EntityUtil.applyAttributeModifierSafely(plData.player(), Attributes.MAX_HEALTH, KNIGHT_BOOTS_BUFF, false);
					break;
				case LEGS:
					EntityUtil.applyAttributeModifierSafely(plData.player(), Attributes.MAX_HEALTH, KNIGHT_LEGS_BUFF, false);
					break;
				case CHEST:
					EntityUtil.applyAttributeModifierSafely(plData.player(), Attributes.MAX_HEALTH, KNIGHT_CHESTPLATE_BUFF, false);
					break;
				case HEAD:
					EntityUtil.applyAttributeModifierSafely(plData.player(), Attributes.MAX_HEALTH, KNIGHT_HELMET_BUFF, false);
					break;
			}
		}
	}

	@Override
	public void onUnequip(ServerPlayerDataManager plData, @Nullable EquipmentSlot slot) {
		if (slot != null) {
			switch (slot) {
				case FEET:
					EntityUtil.removeAttributeModifier(plData.player(), Attributes.MAX_HEALTH, KNIGHT_BOOTS_BUFF);
					break;
				case LEGS:
					EntityUtil.removeAttributeModifier(plData.player(), Attributes.MAX_HEALTH, KNIGHT_LEGS_BUFF);
					break;
				case CHEST:
					EntityUtil.removeAttributeModifier(plData.player(), Attributes.MAX_HEALTH, KNIGHT_CHESTPLATE_BUFF);
					break;
				case HEAD:
					EntityUtil.removeAttributeModifier(plData.player(), Attributes.MAX_HEALTH, KNIGHT_HELMET_BUFF);
					break;
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(pieceEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.knight_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(setEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.knight_armour.desc.2", LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
