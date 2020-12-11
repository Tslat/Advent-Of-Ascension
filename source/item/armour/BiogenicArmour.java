package net.tslat.aoa3.item.armour;

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
import net.tslat.aoa3.util.player.PlayerDataManager;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

public class BiogenicArmour extends AdventArmour {
	public BiogenicArmour(EquipmentSlotType slot) {
		super(ItemUtil.customArmourMaterial("aoa3:biogenic", 38, new int[] {3, 6, 8, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 3), slot);
	}

	@Override
	public AdventArmour.Type setType() {
		return AdventArmour.Type.BIOGENIC;
	}

	public void onEffectTick(PlayerDataManager plData, @Nullable HashSet<EquipmentSlotType> slots) {
		if (slots == null) {
			if (plData.player().isInWater())
				plData.player().setAir(-10);

			if (plData.player().areEyesInFluid(FluidTags.WATER)) {
				plData.player().addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 300, 0, true, false));
			}
			else {
				EffectInstance nightVision = plData.player().getActivePotionEffect(Effects.NIGHT_VISION);

				if (nightVision != null && nightVision.getDuration() <= 300)
					plData.player().removePotionEffect(Effects.NIGHT_VISION);
			}
		}
	}

	@Override
	public void onPostAttackReceived(PlayerDataManager plData, @Nullable HashSet<EquipmentSlotType> slots, LivingDamageEvent event) {
		if (slots != null && DamageUtil.isMeleeDamage(event.getSource()) && event.getSource().getTrueSource() instanceof LivingEntity)
			((LivingEntity)event.getSource().getTrueSource()).addPotionEffect(new EffectInstance(Effects.SLOWNESS, (int)event.getAmount() * 3 * slots.size(), slots.size() >= 2 ? 1 : 0, false, true));
	}

	@Override
	public void onUnequip(PlayerDataManager plData, @Nullable EquipmentSlotType slot) {
		if (slot == null) {
			EffectInstance nightVision = plData.player().getActivePotionEffect(Effects.NIGHT_VISION);

			if (nightVision != null && nightVision.getDuration() <= 300)
				plData.player().removePotionEffect(Effects.NIGHT_VISION);
		}
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.biogenic_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(pieceEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.biogenic_armour.desc.2", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(setEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.biogenic_armour.desc.3", LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
