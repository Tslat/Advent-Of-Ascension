package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.player.PlayerDataManager;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public class SealordHelmet extends AdventArmour {
	public static final AttributeModifier SEALORD_ATTACK_BUFF = new AttributeModifier(UUID.fromString("027744fa-e85d-4d1e-946a-747739900753"), "AoASealordMovementBuff", 2, AttributeModifier.Operation.ADDITION);

	public SealordHelmet() {
		super(ItemUtil.customArmourMaterial("aoa3:sealord", 60, new int[] {5, 7, 9, 5}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 7), EquipmentSlotType.HEAD);
	}

	@Override
	public AdventArmour.Type setType() {
		return AdventArmour.Type.ALL;
	}

	@Override
	public void onEffectTick(PlayerDataManager plData, @Nullable HashSet<EquipmentSlotType> slots) {
		PlayerEntity player = plData.player();

		if (player.areEyesInFluid(FluidTags.WATER)) {
			EntityUtil.applyAttributeModifierSafely(plData.player(), SharedMonsterAttributes.ATTACK_SPEED, SEALORD_ATTACK_BUFF);
		}
		else {
			EntityUtil.removeAttributeModifier(plData.player(), SharedMonsterAttributes.ATTACK_SPEED, SEALORD_ATTACK_BUFF);
		}
	}

	@Override
	public void onUnequip(PlayerDataManager plData, @Nullable EquipmentSlotType slot) {
		EntityUtil.removeAttributeModifier(plData.player(), SharedMonsterAttributes.ATTACK_SPEED, SEALORD_ATTACK_BUFF);
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		tooltip.add(anySetEffectHeader());
	}
}
