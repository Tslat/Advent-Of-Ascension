package net.tslat.aoa3.content.item.misc;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.tslat.aoa3.common.registration.AoACreativeModeTabs;
import net.tslat.aoa3.util.ItemUtil;

public class OldBoot extends ArmorItem {
	public OldBoot() {
		super(ItemUtil.customArmourMaterial("aoa3:old_boot", 30, new int[] {2, 2, 2, 2}, 0, SoundEvents.ARMOR_EQUIP_LEATHER, 0), EquipmentSlot.FEET, new Item.Properties().tab(AoACreativeModeTabs.MISC_ITEMS).rarity(Rarity.RARE));
	}
}
