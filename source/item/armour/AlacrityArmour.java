package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.player.PlayerDataManager;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

public class AlacrityArmour extends AdventArmour {
	public AlacrityArmour(EquipmentSlotType slot) {
		super(ItemUtil.customArmourMaterial("aoa3:alacrity", 55, new int[] {4, 8, 9, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 5), slot);
	}

	@Override
	public AdventArmour.Type setType() {
		return AdventArmour.Type.ALACRITY;
	}

	@Override
	public void onPlayerLandingFall(PlayerDataManager plData, @Nullable HashSet<EquipmentSlotType> slots, LivingFallEvent event) {
		if (slots == null) {
			event.setCanceled(true);
		}
		else if (plData.equipment().getCurrentFullArmourSet() != setType()) {
			event.setDamageMultiplier(1 - (slots.size() * 0.2f));
		}
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(pieceEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.alacrity_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(setEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.alacrity_armour.desc.2", LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
