package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.List;

public class HazmatArmour extends AdventArmour {
	public HazmatArmour(EquipmentSlotType slot) {
		super(ItemUtil.customArmourMaterial("aoa3:hazmat", 30, new int[] {2, 5, 6, 2}, 10, SoundEvents.ARMOR_EQUIP_LEATHER, 0), slot);
	}

	@Override
	public AdventArmour.Type setType() {
		return AdventArmour.Type.HAZMAT;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(setEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.hazmat_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
