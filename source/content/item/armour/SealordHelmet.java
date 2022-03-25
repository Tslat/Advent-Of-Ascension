package net.tslat.aoa3.content.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.player.ServerPlayerDataManager;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public class SealordHelmet extends AdventArmour {
	public static final AttributeModifier SEALORD_ATTACK_BUFF = new AttributeModifier(UUID.fromString("027744fa-e85d-4d1e-946a-747739900753"), "AoASealordMovementBuff", 2, AttributeModifier.Operation.ADDITION);

	public SealordHelmet() {
		super(ItemUtil.customArmourMaterial("aoa3:sealord", 60, new int[] {5, 7, 9, 5}, 10, SoundEvents.ARMOR_EQUIP_GENERIC, 7), EquipmentSlotType.HEAD);
	}

	@Override
	public AdventArmour.Type setType() {
		return AdventArmour.Type.ALL;
	}

	@Override
	public void onEffectTick(ServerPlayerDataManager plData, @Nullable HashSet<EquipmentSlotType> slots) {
		PlayerEntity player = plData.player();

		if (player.isEyeInFluid(FluidTags.WATER)) {
			EntityUtil.applyAttributeModifierSafely(plData.player(), Attributes.ATTACK_SPEED, SEALORD_ATTACK_BUFF, false);
		}
		else {
			EntityUtil.removeAttributeModifier(plData.player(), Attributes.ATTACK_SPEED, SEALORD_ATTACK_BUFF);
		}

		if (plData.player().isInWater())
			plData.player().setAirSupply(-10);
	}

	@Override
	public void onUnequip(ServerPlayerDataManager plData, @Nullable EquipmentSlotType slot) {
		EntityUtil.removeAttributeModifier(plData.player(), Attributes.ATTACK_SPEED, SEALORD_ATTACK_BUFF);
	}

	@Override
	public boolean isHelmetAirTight(ServerPlayerEntity player) {
		return true;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.helmet.airTight", LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		tooltip.add(anySetEffectHeader());
	}
}
