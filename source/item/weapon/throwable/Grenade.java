package net.nevermine.item.weapon.throwable;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.izer.equipment.Weaponizer;
import net.nevermine.projectiles.cannon.EntityGrenadeShot;

import java.util.List;

public class Grenade extends BaseThrowable {
	public Grenade() {
		setCreativeTab(Weaponizer.ThrowablesTab);
	}

	@Override
	public void fireGun(final World world, final ItemStack stack, final EntityPlayer player) {
		player.worldObj.spawnEntityInWorld(new EntityGrenadeShot(player.worldObj, player, 0.0f, 0));
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean bool) {
		list.add(StringUtil.getColourLocaleString("item.Grenade.desc.1", EnumChatFormatting.DARK_GREEN));
		list.add(StringUtil.getColourLocaleString("items.description.thrownWeapon", EnumChatFormatting.AQUA));
		list.add(StringUtil.getLocaleString("items.description.speed.slow"));
	}
}
