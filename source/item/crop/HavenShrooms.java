package net.nevermine.item.crop;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.izer.Itemizer;

import java.util.List;

public class HavenShrooms extends ItemFood {
	public HavenShrooms() {
		super(2, true);
		setCreativeTab(Itemizer.MiscTab);
	}

	public ItemStack onEaten(final ItemStack item, final World world, final EntityPlayer player) {
		super.onEaten(item, world, player);
		return item;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean bool) {
		list.add(StringUtil.getLocaleString("item.HavenShrooms.desc.1"));
	}
}
