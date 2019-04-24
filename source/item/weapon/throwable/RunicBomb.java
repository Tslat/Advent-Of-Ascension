package net.nevermine.item.weapon.throwable;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.izer.equipment.Weaponizer;
import net.nevermine.projectiles.cannon.EntityRunicBomb;

import java.util.List;
import java.util.Random;

public class RunicBomb extends BaseThrowable {
	private Random rand;
	private int canShootTick;

	public RunicBomb() {
		rand = new Random();
		canShootTick = 0;
		setCreativeTab(Weaponizer.ThrowablesTab);
	}

	@Override
	public void fireGun(final World world, final ItemStack stack, final EntityPlayer player) {
		player.worldObj.spawnEntityInWorld(new EntityRunicBomb(player.worldObj, player, 1.0f, 0));
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean bool) {
		list.add(StringUtil.getLocaleString("item.RunicBomb.desc.1"));
		list.add(StringUtil.getLocaleString("item.RunicBomb.desc.2"));
		list.add(StringUtil.getColourLocaleString("items.description.thrownWeapon", EnumChatFormatting.AQUA));
	}
}
