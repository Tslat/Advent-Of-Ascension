package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.player.PlayerDataManager;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

public class RunicArmour extends AdventArmour {
	public RunicArmour(EquipmentSlotType slot) {
		super(ItemUtil.customArmourMaterial("aoa3:runic", 67, new int[] {5, 8, 9, 5}, 10, SoundEvents.ARMOR_EQUIP_GENERIC, 7), slot);
	}

	@Override
	public AdventArmour.Type setType() {
		return AdventArmour.Type.RUNIC;
	}

	@Override
	public void onAttackReceived(PlayerDataManager plData, @Nullable HashSet<EquipmentSlotType> slots, LivingHurtEvent event) {
		if (slots != null && DamageUtil.isMagicDamage(event.getSource(), plData.player(), event.getAmount()))
			event.setAmount(event.getAmount() * (1 - (slots.size() * 0.1f)));
	}

	@Override
	public void onPostAttackReceived(PlayerDataManager plData, @Nullable HashSet<EquipmentSlotType> slots, LivingDamageEvent event) {
		if (slots == null && plData.equipment().isCooledDown("runic_armour") && DamageUtil.isMagicDamage(event.getSource(), plData.player(), event.getAmount())) {
			plData.player().addEffect(new EffectInstance(Effects.ABSORPTION, 200, 0, false, false));
			plData.equipment().setCooldown("runic_armour", 6000);
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(pieceEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.runic_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(setEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.runic_armour.desc.2", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.runic_armour.desc.3", LocaleUtil.ItemDescriptionType.NEUTRAL));
	}
}
