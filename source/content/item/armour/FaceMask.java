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

public class FaceMask extends AdventArmour {
	public FaceMask() {
		super(ItemUtil.customArmourMaterial("aoa3:face_mask", 36, new int[] {4, 7, 8, 5}, 10, SoundEvents.ARMOR_EQUIP_GENERIC, 5), ArmorItem.Type.HELMET);
	}

	@Override
	public Type getSetType() {
		return Type.ALL;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.ARMOUR_AIRTIGHT, LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO));
		tooltip.add(anySetEffectHeader());
	}
}
