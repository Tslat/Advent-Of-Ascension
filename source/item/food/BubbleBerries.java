package net.nevermine.item.food;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.izer.Itemizer;

import java.util.List;

public class BubbleBerries extends ItemFood {
	public BubbleBerries() {
		super(0, true);
		setCreativeTab(Itemizer.MiscTab);
		setAlwaysEdible();
	}

	public ItemStack onEaten(final ItemStack item, final World world, final EntityPlayer player) {
		super.onEaten(item, world, player);

		if (!world.isRemote) {
			player.setAir(Math.min(300, player.getAir() + 50));
		}

		return item;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 24;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean bool) {
		list.add(StringUtil.getLocaleString("item.BubbleBerries.desc.1"));
	}
}
