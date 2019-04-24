package net.nevermine.item.weapon.throwable;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.izer.equipment.Weaponizer;
import net.nevermine.projectiles.throwable.EntityChakram;

import java.util.List;

public class Chakram extends BaseThrowable {
	private final float dmg = 2.0f;

	public Chakram() {
		setMaxStackSize(64);
		setCreativeTab(Weaponizer.ThrowablesTab);
	}

	@Override
	public void fireGun(final World world, final ItemStack stack, final EntityPlayer player) {
		player.worldObj.spawnEntityInWorld(new EntityChakram(player.worldObj, player, dmg));
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean bool) {
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.damage.ranged", EnumChatFormatting.DARK_RED, Integer.toString((int)dmg)));
		list.add(StringUtil.getColourLocaleString("items.description.damage.poison", EnumChatFormatting.DARK_GREEN));
		list.add(StringUtil.getColourLocaleString("items.description.thrownWeapon", EnumChatFormatting.AQUA));
	}
}
