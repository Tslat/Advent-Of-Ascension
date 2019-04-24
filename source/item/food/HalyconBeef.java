package net.nevermine.item.food;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.izer.Itemizer;

import java.util.List;

public class HalyconBeef extends ItemFood {
	public HalyconBeef() {
		super(7, 0.7f, true);
		setCreativeTab(Itemizer.MiscTab);
	}

	public ItemStack onEaten(final ItemStack item, final World world, final EntityPlayer player) {
		super.onEaten(item, world, player);

		if (!world.isRemote) {
			player.addPotionEffect(new PotionEffect(Potion.confusion.id, 150));
		}

		return item;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean bool) {
		list.add(StringUtil.getLocaleString("item.HalyconBeef.desc.1"));
	}
}
