package net.nevermine.item.food;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;

public class NatureMelonSlice extends ItemFood {
	public NatureMelonSlice() {
		super(1, true);
		setCreativeTab(Itemizer.MiscTab);
	}

	public ItemStack onEaten(final ItemStack item, final World world, final EntityPlayer player) {
		super.onEaten(item, world, player);
		return item;
	}
}
