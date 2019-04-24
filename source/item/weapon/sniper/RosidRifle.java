package net.nevermine.item.weapon.sniper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.nevermine.assist.ArmorUtil;
import net.nevermine.assist.StringUtil;
import net.nevermine.item.weapon.gun.BaseGun;
import net.nevermine.izer.equipment.Weaponizer;
import net.nevermine.projectiles.gun.EntitySpecialMetalPellet;

import java.util.List;

public class RosidRifle extends BaseGun implements SniperInstance {
	private final float dmg = 35.0f;
	private float multiplier;

	public RosidRifle(final int consumeChance, final String effect, final int uses, final int fireRate, final Item item) {
		super(consumeChance, effect, uses, fireRate, item);
		multiplier = 1.0f;
		setCreativeTab(Weaponizer.SnipersTab);
	}

	@Override
	public void fireGun(final World world, final ItemStack stack, final EntityPlayer player, final float multi, boolean consume) {
		if (ArmorUtil.isSharpshotArmor(player)) {
			multiplier = 1.25f;
		}
		else {
			multiplier = 1.0f;
		}
		player.worldObj.spawnEntityInWorld(new EntitySpecialMetalPellet(player.worldObj, player, dmg * multiplier * multi, 3));
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean bool) {
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.damage.ranged", EnumChatFormatting.DARK_RED, Integer.toString((int)dmg)));
		list.add(StringUtil.getColourLocaleString("item.RosidRifle.desc.1", EnumChatFormatting.DARK_GREEN));
		list.add(StringUtil.getLocaleString("items.description.speed.incrediblySlow"));
		list.add(StringUtil.getColourLocaleString("items.description.sniper.movement", EnumChatFormatting.AQUA));
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.ammo", EnumChatFormatting.LIGHT_PURPLE, StringUtil.getLocaleString("item.MetalPellet.name")));
	}
}
