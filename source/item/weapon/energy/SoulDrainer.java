package net.nevermine.item.weapon.energy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.projectiles.energy.EntityBloodDrainShot;

import java.util.List;

public class SoulDrainer extends BaseEnergy {
	private final float dmg = 12.0f;

	public SoulDrainer(int consumeChance, String effect, int uses, int fireRate, int cost) {
		super(consumeChance, effect, uses, fireRate, cost);
	}

	public void fireAncient(World world, ItemStack stack, EntityPlayer player) {
		player.worldObj.spawnEntityInWorld(new EntityBloodDrainShot(player.worldObj, player, dmg, 2.0F));
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool) {
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.damage.ranged", EnumChatFormatting.DARK_RED, Integer.toString((int)dmg)));
		list.add(StringUtil.getColourLocaleString("item.SoulDrainer.desc.1", EnumChatFormatting.DARK_GREEN));
		list.add(StringUtil.getLocaleString("items.description.speed.medium"));
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.energy.cost", EnumChatFormatting.LIGHT_PURPLE, "30"));
	}
}
