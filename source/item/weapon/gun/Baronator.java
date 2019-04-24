package net.nevermine.item.weapon.gun;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.common.nevermine;
import net.nevermine.izer.equipment.Weaponizer;
import net.nevermine.projectiles.cannon.EntityGrenadeShot;
import net.nevermine.projectiles.gun.EntityMetalPellet;

import java.util.List;

public class Baronator extends BaseGun {
	private final float dmg = 21.0f;

	public Baronator(final int consumeChance, final String effect, final int uses, final int fireRate, final Item item) {
		super(consumeChance, effect, uses, fireRate, item);
		setCreativeTab(Weaponizer.WeaponsTab);
	}

	@Override
	public void fireGun(final World world, final ItemStack stack, final EntityPlayer player, final float multi, boolean consume) {
		if (nevermine.rand.nextInt(3) == 2) {
			player.worldObj.spawnEntityInWorld(new EntityGrenadeShot(player.worldObj, player, 5.0f * multi, 0));
		}
		player.worldObj.spawnEntityInWorld(new EntityMetalPellet(player.worldObj, player, dmg * multi, 0));
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean bool) {
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.damage.ranged", EnumChatFormatting.DARK_RED, Integer.toString((int)dmg)));
		list.add(StringUtil.getColourLocaleString("item.Baronator.desc.1", EnumChatFormatting.DARK_GREEN));
		list.add(StringUtil.getLocaleString("items.description.speed.slow"));
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.ammo", EnumChatFormatting.LIGHT_PURPLE, StringUtil.getLocaleString("item.MetalPellet.name")));
	}
}
