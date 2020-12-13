package net.tslat.aoa3.item.misc;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvents;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.util.ItemUtil;

public class OldBoot extends ArmorItem {
	public OldBoot() {
		super(ItemUtil.customArmourMaterial("aoa3:old_boot", 30, new int[] {2, 2, 2, 2}, 0, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0), EquipmentSlotType.FEET, new Item.Properties().group(AoAItemGroups.MISC_ITEMS));
	}
}
