package net.nevermine.item.weapon.energy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.projectiles.energy.EntityShowerShot;

import java.util.List;

public class ApocoShower extends BaseEnergy {
	private final int cost = 50;

	public ApocoShower(final int consumeChance, final String effect, final int uses, final int fireRate, final int cost) {
		super(consumeChance, effect, uses, fireRate, cost);
	}

	@Override
	public void fireAncient(final World world, final ItemStack stack, final EntityPlayer player) {
		player.worldObj.spawnEntityInWorld(new EntityShowerShot(player.worldObj, player, 1));
		player.worldObj.spawnEntityInWorld(new EntityShowerShot(player.worldObj, player, 2));
		player.worldObj.spawnEntityInWorld(new EntityShowerShot(player.worldObj, player, 0));
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean bool) {
		list.add(StringUtil.getColourLocaleString("item.ApocoShower.desc.1", EnumChatFormatting.DARK_GREEN));
		list.add(StringUtil.getLocaleString("items.description.speed.verySlow"));
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.energy.cost", EnumChatFormatting.LIGHT_PURPLE, Integer.toString(cost)));
	}
}
