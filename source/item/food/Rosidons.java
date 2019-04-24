package net.nevermine.item.food;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.nevermine.assist.ConfigurationHelper;
import net.nevermine.assist.StringUtil;
import net.nevermine.assist.WorldUtil;
import net.nevermine.izer.Itemizer;

import java.util.List;

public class Rosidons extends ItemFood {
	public Rosidons() {
		super(0, true);
		setCreativeTab(Itemizer.MiscTab);
		setAlwaysEdible();
	}

	public ItemStack onEaten(final ItemStack item, final World world, final EntityPlayer player) {
		super.onEaten(item, world, player);

		if (player.dimension == ConfigurationHelper.ancientcavern || player.dimension == ConfigurationHelper.immortallis || player.dimension == ConfigurationHelper.labricon) {
			if (!world.isRemote) {
				player.addChatMessage(StringUtil.getLocale("message.feedback.item.rosidons.fail"));
			}
		}
		else if (!world.isRemote) {
			final int var1 = MathHelper.floor_double(player.posX);
			final int var2 = MathHelper.floor_double(player.posZ);
			final double y = WorldUtil.getTrueWorldHeight(world, var1, var2);

			if (y == 0) {
				player.addChatMessage(StringUtil.getLocale("message.feedback.item.rosidons.noHeightFail"));
				return item;
			}

			player.setPositionAndUpdate((double)var1, y + 2.0, (double)var2);
		}
		return item;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean bool) {
		list.add(StringUtil.getLocaleString("item.Rosidons.desc.1"));
	}
}
