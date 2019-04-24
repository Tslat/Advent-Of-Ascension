package net.nevermine.item.functional;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.izer.Itemizer;

import java.util.List;
import java.util.Random;

public class ShinyBox extends Item {
	Random rand;

	public ShinyBox() {
		rand = new Random();
		setCreativeTab(Itemizer.TotemsTab);
	}

	public boolean onItemUse(final ItemStack par1ItemStack, final EntityPlayer player, final World par3World, final int par4, final int par5, final int par6, final int par7, final float par8, final float par9, final float par10) {
		for (int i = 0; i < 3; ++i) {
			if (!par3World.isRemote) {
				final int randnum = rand.nextInt(4);
				if (randnum == 1) {
					player.dropItem(Itemizer.AmethystIngot, 1);
				}
				else if (randnum == 2) {
					player.dropItem(Itemizer.IngotJade, 1);
				}
				else if (randnum == 3) {
					player.dropItem(Itemizer.IngotSapphire, 1);
				}
				else {
					player.dropItem(Itemizer.IngotRosite, 1);
				}
			}
		}
		--par1ItemStack.stackSize;
		return true;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean bool) {
		list.add(StringUtil.getColourLocaleString("item.ShinyBox.desc.1", EnumChatFormatting.GOLD));
	}
}
