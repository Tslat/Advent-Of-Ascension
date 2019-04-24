package net.nevermine.item.weapon.artillery;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.item.weapon.gun.BaseGun;
import net.nevermine.izer.equipment.Weaponizer;
import net.nevermine.projectiles.gun.EntityDischargeShell;

import java.util.List;
import java.util.Random;

public class DischargeShotgun extends BaseGun {
	private Random rand;
	private int canShootTick;

	public DischargeShotgun(final int consumeChance, final String effect, final int uses, final int fireRate, final Item item) {
		super(consumeChance, effect, uses, fireRate, item);
		rand = new Random();
		canShootTick = 0;
		setCreativeTab(Weaponizer.WeaponsTab);
	}

	@Override
	public void fireGun(final World world, final ItemStack stack, final EntityPlayer player, final float multi, boolean consume) {
		player.worldObj.spawnEntityInWorld(new EntityDischargeShell(player.worldObj, player, 20.0f, 0.0f));
		player.worldObj.spawnEntityInWorld(new EntityDischargeShell(player.worldObj, player, 20.0f, 0.025f));
		player.worldObj.spawnEntityInWorld(new EntityDischargeShell(player.worldObj, player, 20.0f, -0.025f));
		player.worldObj.spawnEntityInWorld(new EntityDischargeShell(player.worldObj, player, 20.0f, 0.05f));
		player.worldObj.spawnEntityInWorld(new EntityDischargeShell(player.worldObj, player, 20.0f, -0.05f));
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean bool) {
		list.add(StringUtil.getColourLocaleString("item.DischargeShotgun.desc.1", EnumChatFormatting.DARK_GREEN));
		list.add(StringUtil.getLocaleString("items.description.speed.verySlow"));
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.ammo", EnumChatFormatting.LIGHT_PURPLE, StringUtil.getLocaleString("item.DischargeCapsule.name")));
	}
}
