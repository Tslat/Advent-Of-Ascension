package net.nevermine.item.weapon.vulcane;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;

import java.util.List;

public class Vulcane extends BaseVulcane {
	private float damage;

	public Vulcane(final String effect, final int uses, final float dmg, final int need) {
		super(effect, uses, dmg, need);
		damage = dmg;
	}

	@Override
	public void fireGun(final World world, final ItemStack stack, final EntityPlayer player, final float multi, final EntityMob mob) {
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean bool) {
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.damage.true", EnumChatFormatting.DARK_RED, Integer.toString((int)damage)));
		list.add(StringUtil.getColourLocaleString("items.description.vulcane.activate", EnumChatFormatting.AQUA));
	}
}
