package net.nevermine.item.functional;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.nevermine.assist.ConfigurationHelper;
import net.nevermine.assist.StringUtil;
import net.nevermine.container.PlayerContainer;
import net.nevermine.izer.Itemizer;

import java.util.List;

public class ReturnCrystal extends ItemFood {
	public ReturnCrystal() {
		super(0, true);
		setCreativeTab(Itemizer.MiscTab);
		setAlwaysEdible();
	}

	public ItemStack onEaten(final ItemStack item, final World world, final EntityPlayer player) {
		if (world.provider.dimensionId == ConfigurationHelper.immortallis) {
			super.onEaten(item, world, player);

			if (!world.isRemote) {
				player.setPositionAndUpdate(0.0, 20.0, 0.0);
				PlayerContainer.getProperties(player).resetAllTribute();
			}

			player.inventory.consumeInventoryItem(Itemizer.ProgressCoin1);
			player.inventory.consumeInventoryItem(Itemizer.ProgressCoin2);
			player.inventory.consumeInventoryItem(Itemizer.ProgressCoin3);
			player.inventory.consumeInventoryItem(Itemizer.ProgressCoin4);
		}
		else {
			if (!world.isRemote)
				player.addChatMessage(StringUtil.getLocale("message.feedback.item.returnCrystal.fail"));
		}

		return item;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean bool) {
		list.add(StringUtil.getLocaleString("item.ReturnCrystal.desc.1"));
	}
}
