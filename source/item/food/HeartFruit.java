package net.nevermine.item.food;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;

public class HeartFruit extends ItemFood {
	public HeartFruit() {
		super(12, 0.95f, true);
		setCreativeTab(Itemizer.MiscTab);
	}

	public ItemStack onEaten(final ItemStack item, final World world, final EntityPlayer player) {
		super.onEaten(item, world, player);

		player.attackEntityFrom(DamageSource.generic, 7.0f);

		return item;
	}
}
