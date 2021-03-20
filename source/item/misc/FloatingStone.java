package net.tslat.aoa3.item.misc;

import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.common.registration.AoAItems;

public class FloatingStone extends Item {
	public FloatingStone() {
		super(new Item.Properties().tab(AoAItemGroups.MISC_ITEMS));
	}

	@Override
	public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
		if (entity.getY() < -10) {
			entity.teleportTo(entity.getX(), 257, entity.getZ());
			entity.setItem(new ItemStack(AoAItems.HEAVY_BOULDER.get(), 1));

			return true;
		}

		return false;
	}
}
