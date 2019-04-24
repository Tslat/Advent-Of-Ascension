package net.nevermine.item.food;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.izer.Itemizer;

import java.util.List;

public class BucketHalyconMilk extends ItemFood {
	public BucketHalyconMilk() {
		super(0, true);
		setCreativeTab(Itemizer.MiscTab);
		setAlwaysEdible();
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.drink;
	}

	public ItemStack onEaten(final ItemStack item, final World world, final EntityPlayer player) {
		super.onEaten(item, world, player);

		if (!world.isRemote) {
			player.heal(2.0f);
			player.curePotionEffects(new ItemStack(Items.milk_bucket));
			player.addPotionEffect(new PotionEffect(Potion.confusion.id, 100));

			if (!player.inventory.addItemStackToInventory(new ItemStack(Items.bucket, 1)))
				player.dropItem(Items.bucket, 1);
		}

		return item;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean bool) {
		list.add(StringUtil.getLocaleString("item.BucketHalyconMilk.desc.1"));
	}
}
