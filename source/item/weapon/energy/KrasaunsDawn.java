package net.nevermine.item.weapon.energy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.projectiles.cannon.EntityGhoulBall;
import net.nevermine.projectiles.energy.*;

import java.util.List;

public class KrasaunsDawn extends BaseEnergy {
	public KrasaunsDawn(final int consumeChance, final String effect, final int uses, final int fireRate, final int cost) {
		super(consumeChance, effect, uses, fireRate, cost);
	}

	@Override
	public void fireAncient(final World world, final ItemStack stack, final EntityPlayer player) {
		player.worldObj.spawnEntityInWorld(new EntityRevolution(player.worldObj, player, 5.0f));
		player.worldObj.spawnEntityInWorld(new EntityAtomizerShot(player.worldObj, player, 5.0f));
		player.worldObj.spawnEntityInWorld(new EntityMindShot(player.worldObj, player, 15.0f));
		player.worldObj.spawnEntityInWorld(new EntityPoisonPlungerShot(player.worldObj, player, 15.0f, 0.085f));
		player.worldObj.spawnEntityInWorld(new EntityShowerShot(player.worldObj, player, 2));
		player.worldObj.spawnEntityInWorld(new EntityDoomShot(player.worldObj, player));
		player.worldObj.spawnEntityInWorld(new EntityGhoulBall(player.worldObj, player, 10.0f, 1, 0.075f));
		player.worldObj.spawnEntityInWorld(new EntityPowerRayShot(player.worldObj, player, 10.0f));
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean bool) {
		list.add(StringUtil.getColourLocaleString("item.KrasaunsDawn.desc.1", EnumChatFormatting.DARK_GREEN));
		list.add(StringUtil.getLocaleString("items.description.speed.ultraSlow"));
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.energy.cost", EnumChatFormatting.LIGHT_PURPLE, "200"));
	}
}
