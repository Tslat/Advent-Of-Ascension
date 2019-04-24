package net.tslat.aoa3.item.special;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.tslat.aoa3.item.misc.SimpleItem;

public class HeartStone extends SimpleItem {
	public HeartStone() {
		super("HeartStone", "heart_stone");
		setCreativeTab(null);
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entityIn, int itemSlot, boolean isSelected) {
		stack.setCount(0);
	}
}
