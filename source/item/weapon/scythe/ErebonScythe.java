package net.nevermine.item.weapon.scythe;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.izer.Itemizer;
import net.nevermine.projectiles.auxillary.EntityScythe;

import java.util.List;

public class ErebonScythe extends BaseScythe {
	private float dmg;

	public ErebonScythe(final int uses, final float dealDamage) {
		super(uses, dealDamage);
		dmg = dealDamage;
	}

	@Override
	public void fireScythe(final World world, final ItemStack stack, final EntityPlayer player) {
		player.worldObj.spawnEntityInWorld(new EntityScythe(player.worldObj, player, dmg, 2));
	}

	@Override
	public boolean getIsRepairable(final ItemStack par1ItemStack, final ItemStack par2ItemStack) {
		return Itemizer.IngotRosite == par2ItemStack.getItem() || super.getIsRepairable(par1ItemStack, par2ItemStack);
	}

	@Override
	public void addInformation(final ItemStack item, final EntityPlayer player, final List list, final boolean bool) {
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.damage.ranged", EnumChatFormatting.DARK_RED, Integer.toString((int)dmg)));
		list.add(StringUtil.getColourLocaleString("items.description.damage.fire", EnumChatFormatting.DARK_GREEN));
		list.add(StringUtil.getColourLocaleString("items.description.scythe.desc.1", EnumChatFormatting.AQUA));
		list.add(StringUtil.getColourLocaleString("items.description.scythe.desc.2", EnumChatFormatting.AQUA));
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.energy.cost", EnumChatFormatting.LIGHT_PURPLE, "15"));
	}
}