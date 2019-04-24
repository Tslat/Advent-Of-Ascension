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

public class TrilliadLeaves extends ItemFood {
	public TrilliadLeaves() {
		super(2, true);
		setCreativeTab(Itemizer.MiscTab);
		setAlwaysEdible();
	}

	public ItemStack onEaten(final ItemStack item, final World world, final EntityPlayer player) {
		super.onEaten(item, world, player);

		if (!world.isRemote) {
			player.removePotionEffect(Potion.confusion.id);
			player.addPotionEffect(new PotionEffect(Potion.blindness.id, 130));
			player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 100, 10));
			player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 100, 2));
			player.addPotionEffect(new PotionEffect(Potion.poison.id, 100, 7));
			player.addPotionEffect(new PotionEffect(Potion.jump.id, 100, 128));
		}

		return item;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean bool) {
		list.add(StringUtil.getLocaleString("item.TrilliadLeaves.desc.1"));
	}
}
