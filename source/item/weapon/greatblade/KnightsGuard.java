package net.nevermine.item.weapon.greatblade;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.projectiles.auxillary.EntityGreatblade;

import java.util.List;

public class KnightsGuard extends BaseGreatblade {
	private float dmg;

	public KnightsGuard(int uses, float dealDamage) {
		super(uses, dealDamage);
		this.dmg = dealDamage;
	}

	public void fireGreatblade(World world, ItemStack stack, EntityPlayer player) {
		player.worldObj.spawnEntityInWorld(new EntityGreatblade(player.worldObj, player, this.dmg, 15));
	}

	public void addInformation(ItemStack item, EntityPlayer player, List list, boolean bool) {
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.damage.ranged", EnumChatFormatting.DARK_RED, Integer.toString((int)dmg)));
		list.add(StringUtil.getColourLocaleString("item.KnightsGuard.desc.1", EnumChatFormatting.DARK_GREEN));
		list.add(StringUtil.getColourLocaleString("items.description.greatblade.desc.1", EnumChatFormatting.AQUA));
		list.add(StringUtil.getColourLocaleString("items.description.greatblade.desc.2", EnumChatFormatting.AQUA));
	}
}
