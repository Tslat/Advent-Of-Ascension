package net.nevermine.item.weapon.greatblade;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.projectiles.auxillary.EntityGreatblade;

import java.util.List;

public class HauntedGreatblade extends BaseGreatblade {
	private float dmg;

	public HauntedGreatblade(final int uses, final float dealDamage) {
		super(uses, dealDamage);
		dmg = dealDamage;
	}

	@Override
	public void fireGreatblade(final World world, final ItemStack stack, final EntityPlayer player) {
		player.worldObj.spawnEntityInWorld(new EntityGreatblade(player.worldObj, player, dmg, 0));

		if (!player.capabilities.isCreativeMode) {
			if (player.getHealth() - 1.0f > 0.0f) {
				player.setHealth(player.getHealth() - 1.0f);
			}
			else {
				player.attackEntityFrom(DamageSource.magic, 1.0f);
			}
		}
	}

	@Override
	public void addInformation(final ItemStack item, final EntityPlayer player, final List list, final boolean bool) {
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.damage.ranged", EnumChatFormatting.DARK_RED, Integer.toString((int)dmg)));
		list.add(StringUtil.getColourLocaleString("item.HauntedGreatblade.desc.1", EnumChatFormatting.DARK_GREEN));
		list.add(StringUtil.getColourLocaleString("items.description.greatblade.desc.1", EnumChatFormatting.AQUA));
		list.add(StringUtil.getColourLocaleString("items.description.greatblade.desc.2", EnumChatFormatting.AQUA));
	}
}
