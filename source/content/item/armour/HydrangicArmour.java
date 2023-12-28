package net.tslat.aoa3.content.item.armour;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HydrangicArmour extends AdventArmour {
	public HydrangicArmour(ArmorItem.Type slot) {
		super(ItemUtil.customArmourMaterial("aoa3:hydrangic", 54, new int[] {4, 7, 9, 4}, 10, SoundEvents.ARMOR_EQUIP_GENERIC, 5), slot);
	}

	@Override
	public Type getSetType() {
		return Type.HYDRANGIC;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(setEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.hydrangic_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
