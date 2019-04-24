package net.nevermine.item.functional;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.common.nevermine;
import net.nevermine.izer.Itemizer;

import java.util.List;

public class GemBag extends Item {
	public GemBag() {
		setCreativeTab(Itemizer.TotemsTab);
	}

	public boolean onItemUse(final ItemStack stack, final EntityPlayer player, final World world, final int par4, final int par5, final int par6, final int par7, final float par8, final float par9, final float par10) {
		if (!world.isRemote) {
			for (int i = 0; i < 5; i++) {
				switch (nevermine.rand.nextInt(21)) {
					case 0:
						player.dropItem(Itemizer.CoinsAbyss, 1);
						break;
					case 1:
						player.dropItem(Itemizer.CoinsBarathos, 1);
						break;
					case 2:
						player.dropItem(Itemizer.CoinsDeeplands, 1);
						break;
					case 3:
						player.dropItem(Itemizer.CoinsDustopia, 1);
						break;
					case 4:
						player.dropItem(Itemizer.CoinsGardencia, 1);
						break;
					case 5:
						player.dropItem(Itemizer.CoinsVoxPonds, 1);
						break;
					case 6:
						player.dropItem(Itemizer.CoinsGreckon, 1);
						break;
					case 7:
						player.dropItem(Itemizer.CoinsHaven, 1);
						break;
					case 8:
						player.dropItem(Itemizer.CoinsIromine, 1);
						break;
					case 9:
						player.dropItem(Itemizer.CoinsLborean, 1);
						break;
					case 10:
						player.dropItem(Itemizer.CoinsLunalus, 1);
						break;
					case 11:
						player.dropItem(Itemizer.CoinsNether, 1);
						break;
					case 12:
						player.dropItem(Itemizer.CoinsMysterium, 1);
						break;
					case 13:
						player.dropItem(Itemizer.CoinsPrecasian, 1);
						break;
					case 14:
						player.dropItem(Itemizer.CoinsRunandor, 1);
						break;
					case 15:
						player.dropItem(Itemizer.CoinsLelyetia, 1);
						break;
					case 16:
						player.dropItem(Itemizer.CoinsCrystevia, 1);
						break;
					case 17:
						player.dropItem(Itemizer.CoinsCeleve, 1);
						break;
					case 18:
						player.dropItem(Itemizer.CoinsCandyland, 1);
						break;
					case 19:
						player.dropItem(Itemizer.CoinsCreeponia, 1);
						break;
					case 20:
						player.dropItem(Itemizer.CoinsShyrelands, 1);
						break;
				}
			}
		}

		--stack.stackSize;
		return true;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean bool) {
		list.add(StringUtil.getColourLocaleString("item.GemBag.desc.1", EnumChatFormatting.GOLD));
	}
}
