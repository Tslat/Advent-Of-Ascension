package net.nevermine.item.weapon.greatblade;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.izer.equipment.Weaponizer;
import net.nevermine.projectiles.auxillary.EntityGreatblade;
import net.nevermine.projectiles.cannon.EntityGrenadeShot;

import java.util.List;

public class BaronGreatblade extends BaseGreatblade {
	private float dmg;

	public BaronGreatblade(final int uses, final float dealDamage) {
		super(uses, dealDamage);
		dmg = dealDamage;
	}

	@Override
	public void fireGreatblade(final World world, final ItemStack stack, final EntityPlayer player) {
		if (player.capabilities.isCreativeMode || player.inventory.consumeInventoryItem(Weaponizer.Grenade)) {
			if (!world.isRemote) {
				player.worldObj.spawnEntityInWorld(new EntityGreatblade(player.worldObj, player, dmg, 8));
			}
			stack.damageItem(1, player);
			if (!world.isRemote) {
				player.worldObj.spawnEntityInWorld(new EntityGrenadeShot(player.worldObj, player, 8.0f, 0));
			}
		}
		else if (!world.isRemote) {
			player.worldObj.spawnEntityInWorld(new EntityGreatblade(player.worldObj, player, dmg, 0));
		}
	}

	@Override
	public void addInformation(final ItemStack item, final EntityPlayer player, final List list, final boolean bool) {
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.damage.ranged", EnumChatFormatting.DARK_RED, Integer.toString((int)dmg)));
		list.add(StringUtil.getColourLocaleString("item.BaronGreatblade.desc.1", EnumChatFormatting.DARK_GREEN));
		list.add(StringUtil.getColourLocaleString("items.description.greatblade.desc.1", EnumChatFormatting.AQUA));
		list.add(StringUtil.getColourLocaleString("items.description.greatblade.desc.2", EnumChatFormatting.AQUA));
	}
}
