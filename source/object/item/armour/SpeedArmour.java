package net.tslat.aoa3.object.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.player.ServerPlayerDataManager;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class SpeedArmour extends AdventArmour {
	private static final AttributeModifier SET_BONUS = new AttributeModifier(UUID.fromString("748347d0-6ed4-4917-9495-0e7137fcf61a"), "AoASpeedArmourBoots", 0.1d, AttributeModifier.Operation.MULTIPLY_TOTAL);
	private static final AttributeModifier BOOTS_BONUS = new AttributeModifier(UUID.fromString("d0a8b22e-8c0e-42a0-be57-284110170f8c"), "AoASpeedArmourBoots", 0.1d, AttributeModifier.Operation.MULTIPLY_TOTAL);
	private static final AttributeModifier LEGS_BONUS = new AttributeModifier(UUID.fromString("31c9600f-b10f-48f0-8acb-2a3009fb3466"), "AoASpeedArmourLegs", 0.1d, AttributeModifier.Operation.MULTIPLY_TOTAL);
	private static final AttributeModifier CHESTPLATE_BONUS = new AttributeModifier(UUID.fromString("0f20e0f4-a909-409d-99cf-65fd80f516c7"), "AoASpeedArmourBody", 0.1d, AttributeModifier.Operation.MULTIPLY_TOTAL);
	private static final AttributeModifier HELMET_BONUS = new AttributeModifier(UUID.fromString("6d13cd91-39d8-4e68-8c25-b9b45bb729d9"), "AoASpeedArmourHelmet", 0.1d, AttributeModifier.Operation.MULTIPLY_TOTAL);

	public SpeedArmour(EquipmentSlotType slot) {
		super(ItemUtil.customArmourMaterial("aoa3:speed", 63, new int[] {4, 9, 9, 3}, 10, SoundEvents.ARMOR_EQUIP_GENERIC, 7), slot);
	}

	@Override
	public AdventArmour.Type setType() {
		return AdventArmour.Type.SPEED;
	}

	@Override
	public void onEquip(ServerPlayerDataManager plData, @Nullable EquipmentSlotType slot) {
		if (slot == null) {
			EntityUtil.applyAttributeModifierSafely(plData.player(), Attributes.MOVEMENT_SPEED, SET_BONUS, false);
		}
		else {
			switch (slot) {
				case FEET:
					EntityUtil.applyAttributeModifierSafely(plData.player(), Attributes.MOVEMENT_SPEED, BOOTS_BONUS, false);
					break;
				case LEGS:
					EntityUtil.applyAttributeModifierSafely(plData.player(), Attributes.MOVEMENT_SPEED, LEGS_BONUS, false);
					break;
				case CHEST:
					EntityUtil.applyAttributeModifierSafely(plData.player(), Attributes.MOVEMENT_SPEED, CHESTPLATE_BONUS, false);
					break;
				case HEAD:
					EntityUtil.applyAttributeModifierSafely(plData.player(), Attributes.MOVEMENT_SPEED, HELMET_BONUS, false);
					break;
				default:
					break;
			}
		}
	}

	@Override
	public void onUnequip(ServerPlayerDataManager plData, @Nullable EquipmentSlotType slot) {
		if (slot == null) {
			EntityUtil.removeAttributeModifier(plData.player(), Attributes.MOVEMENT_SPEED, SET_BONUS);
		}
		else {
			switch (slot) {
				case FEET:
					EntityUtil.removeAttributeModifier(plData.player(), Attributes.MOVEMENT_SPEED, BOOTS_BONUS);
					break;
				case LEGS:
					EntityUtil.removeAttributeModifier(plData.player(), Attributes.MOVEMENT_SPEED, LEGS_BONUS);
					break;
				case CHEST:
					EntityUtil.removeAttributeModifier(plData.player(), Attributes.MOVEMENT_SPEED, CHESTPLATE_BONUS);
					break;
				case HEAD:
					EntityUtil.removeAttributeModifier(plData.player(), Attributes.MOVEMENT_SPEED, HELMET_BONUS);
					break;
				default:
					break;
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(pieceEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.speed_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(setEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.speed_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
