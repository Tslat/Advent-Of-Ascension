package net.nevermine.item.food;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.izer.Itemizer;

import java.util.List;

public class FungalTea extends ItemFood {
	public FungalTea() {
		super(0, 0.5f, true);
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
			player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 75, 3));
			player.addPotionEffect(new PotionEffect(Potion.resistance.id, 75));

			if (!player.inventory.addItemStackToInventory(new ItemStack(Itemizer.Cup)))
				player.dropItem(Itemizer.Cup, 1);
		}

		return item;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean bool) {
		list.add(StringUtil.getLocaleString("item.FungalTea.desc.1"));
	}
}
