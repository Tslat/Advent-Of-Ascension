package net.nevermine.item.weapon.gun;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.izer.equipment.Weaponizer;
import net.nevermine.projectiles.gun.EntityShell;

import java.util.List;

public class Amplifier extends BaseGun {
	private final float dmg = 10.0f;

	public Amplifier(int consumeChance, String effect, int uses, int fireRate, Item item) {
		super(consumeChance, effect, uses, fireRate, item);
		setCreativeTab(Weaponizer.WeaponsTab);
	}

	public void fireGun(World world, ItemStack stack, EntityPlayer player, float multi, boolean bool) {
		player.worldObj.spawnEntityInWorld(new EntityShell(player.worldObj, player, dmg, 0.0F));
		player.worldObj.spawnEntityInWorld(new EntityShell(player.worldObj, player, dmg, 0.025F));
		player.worldObj.spawnEntityInWorld(new EntityShell(player.worldObj, player, dmg, -0.025F));
		player.worldObj.spawnEntityInWorld(new EntityShell(player.worldObj, player, dmg, 0.05F));
		player.worldObj.spawnEntityInWorld(new EntityShell(player.worldObj, player, dmg, -0.05F));
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool) {
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.damage.ranged", EnumChatFormatting.DARK_RED, Integer.toString((int)dmg)));
		list.add(StringUtil.getLocaleString("item.Amplifier.desc.1"));
		list.add(StringUtil.getLocaleString("items.description.speed.fast"));
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.ammo", EnumChatFormatting.LIGHT_PURPLE, StringUtil.getLocaleString("item.MetalPellet.name")));
	}
}
