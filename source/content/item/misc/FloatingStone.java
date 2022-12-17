package net.tslat.aoa3.content.item.misc;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.tslat.aoa3.common.registration.item.AoAItems;

public class FloatingStone extends Item {
	public FloatingStone() {
		super(new Item.Properties());
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
