package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.player.PlayerDataManager;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

public class InfernalArmour extends AdventArmour {
	public InfernalArmour(EquipmentSlotType slot) {
		super(ItemUtil.customArmourMaterial("aoa3:infernal", 39, new int[] {4, 7, 8, 3}, 10, SoundEvents.ARMOR_EQUIP_GENERIC, 3), slot);
	}

	@Override
	public AdventArmour.Type setType() {
		return AdventArmour.Type.INFERNAL;
	}

	@Override
	public void onAttackReceived(PlayerDataManager plData, @Nullable HashSet<EquipmentSlotType> slots, LivingHurtEvent event) {
		if (event.getSource().isFire() && slots != null)
			event.setAmount(event.getAmount() *  (1 - 0.15f * slots.size()));
	}

	@Override
	public void onPreAttackReceived(PlayerDataManager plData, @Nullable HashSet<EquipmentSlotType> slots, LivingAttackEvent event) {
		if (event.getSource().isFire() && slots == null)
			event.setCanceled(true);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(pieceEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.infernal_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(setEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.infernal_armour.desc.2", LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
