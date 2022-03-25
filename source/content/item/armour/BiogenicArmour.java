package net.tslat.aoa3.content.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.player.ServerPlayerDataManager;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

public class BiogenicArmour extends AdventArmour {
	public BiogenicArmour(EquipmentSlotType slot) {
		super(ItemUtil.customArmourMaterial("aoa3:biogenic", 38, new int[] {3, 6, 8, 3}, 10, SoundEvents.ARMOR_EQUIP_GENERIC, 3), slot);
	}

	@Override
	public AdventArmour.Type setType() {
		return AdventArmour.Type.BIOGENIC;
	}

	public void onEffectTick(ServerPlayerDataManager plData, @Nullable HashSet<EquipmentSlotType> slots) {
		if (slots == null) {
			if (plData.player().isInWater())
				plData.player().setAirSupply(-10);

			if (plData.player().isEyeInFluid(FluidTags.WATER)) {
				plData.player().addEffect(new EffectInstance(Effects.NIGHT_VISION, 300, 0, true, false));
			}
			else {
				EffectInstance nightVision = plData.player().getEffect(Effects.NIGHT_VISION);

				if (nightVision != null && nightVision.getDuration() <= 300)
					plData.player().removeEffect(Effects.NIGHT_VISION);
			}
		}
	}

	@Override
	public void onPostAttackReceived(ServerPlayerDataManager plData, @Nullable HashSet<EquipmentSlotType> slots, LivingDamageEvent event) {
		if (slots != null && DamageUtil.isMeleeDamage(event.getSource()) && event.getSource().getEntity() instanceof LivingEntity)
			((LivingEntity)event.getSource().getEntity()).addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, (int)event.getAmount() * 3 * slots.size(), slots.size() >= 2 ? 1 : 0, false, true));
	}

	@Override
	public void onUnequip(ServerPlayerDataManager plData, @Nullable EquipmentSlotType slot) {
		if (slot == null) {
			EffectInstance nightVision = plData.player().getEffect(Effects.NIGHT_VISION);

			if (nightVision != null && nightVision.getDuration() <= 300)
				plData.player().removeEffect(Effects.NIGHT_VISION);
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.biogenic_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(pieceEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.biogenic_armour.desc.2", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(setEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.biogenic_armour.desc.3", LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
