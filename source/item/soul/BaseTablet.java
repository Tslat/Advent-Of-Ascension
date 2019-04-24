package net.nevermine.item.soul;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.container.PlayerContainer;
import net.nevermine.izer.Itemizer;
import net.nevermine.resource.soulpower.soulPowerHelper;
import net.nevermine.skill.anima.animaHelper;

import java.util.List;
import java.util.Random;

public abstract class BaseTablet extends Item {
	private int cost;
	private int level;
	Random rand;

	public BaseTablet(final int price, final int req) {
		rand = new Random();
		cost = price;
		level = req;
		setMaxStackSize(1);
		setCreativeTab(Itemizer.SoulTab);
	}

	public ItemStack onItemRightClick(final ItemStack stack, final World world, final EntityPlayer player) {
		double newcost = cost * animaHelper.getCostModifier(player);

		if (PlayerContainer.getProperties(player).getLevel(PlayerContainer.Skills.Anima) >= level && !world.isRemote && (player.capabilities.isCreativeMode || soulPowerHelper.getProperties(player).useBar((float)newcost))) {
			useTablet(world, stack, player);
			player.worldObj.playSoundAtEntity(player, "nevermine:Tablet", 1.0f, 1.0f);
		}
		return stack;
	}

	public abstract void useTablet(final World p0, final ItemStack p1, final EntityPlayer p2);

	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean bool) {
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.tablet.cost", EnumChatFormatting.LIGHT_PURPLE, Integer.toString(cost / 2000)));
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.tablet.level", EnumChatFormatting.RED, Integer.toString(level)));
	}
}
