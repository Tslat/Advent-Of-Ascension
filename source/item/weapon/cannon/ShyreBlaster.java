package net.nevermine.item.weapon.cannon;

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
import net.nevermine.projectiles.cannon.EntityCannonBall;

import java.util.List;

public class ShyreBlaster extends BaseGun {
	private final float dmg = 45.0f;

	public ShyreBlaster(int consumeChance, String effect, int uses, int fireRate, Item item) {
		super(consumeChance, effect, uses, fireRate, item);
		setCreativeTab(Weaponizer.CannonsTab);
	}

	public void fireGun(World world, ItemStack stack, EntityPlayer player, float multi, boolean bool) {
		player.worldObj.spawnEntityInWorld(new EntityCannonBall(player.worldObj, player, dmg * multi, 2, 0.095F));
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool) {
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.damage.ranged", EnumChatFormatting.DARK_RED, Integer.toString((int)dmg)));
		list.add(StringUtil.getLocaleString("items.description.knockback.medium"));
		list.add(StringUtil.getLocaleString("items.description.speed.verySlow"));
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.ammo", EnumChatFormatting.LIGHT_PURPLE, StringUtil.getLocaleString("item.CannonBall.name")));
	}
}
