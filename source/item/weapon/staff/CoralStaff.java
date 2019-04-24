package net.nevermine.item.weapon.staff;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.item.ItemRune;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Weaponizer;

import java.util.HashMap;
import java.util.List;

public class CoralStaff extends BaseStaff {
	private static HashMap<ItemRune, Integer> runes = new HashMap<ItemRune, Integer>();

	public CoralStaff() {
		super(runes);
		setMaxDamage(150);
		setCreativeTab(Weaponizer.StaffTab);
	}

	public boolean getIsRepairable(final ItemStack par1ItemStack, final ItemStack par2ItemStack) {
		return Itemizer.IngotRosite == par2ItemStack.getItem() || super.getIsRepairable(par1ItemStack, par2ItemStack);
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean bool) {
		list.add(StringUtil.getColourLocaleString("item.CoralStaff.desc.1", EnumChatFormatting.DARK_GREEN));
		list.add(StringUtil.getColourLocaleString("items.description.runes.required", EnumChatFormatting.LIGHT_PURPLE));
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.runes.specific", EnumChatFormatting.WHITE, Integer.toString(5), StringUtil.getLocaleString("item.WaterRune.name")));
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.runes.specific", EnumChatFormatting.WHITE, Integer.toString(1), StringUtil.getLocaleString("item.LifeRune.name")));
	}

	@Override
	public void fireStaff(final World world, final ItemStack stack, final EntityPlayer player) {
		player.worldObj.playSoundAtEntity(player, "nevermine:CoralStaff", 1.0f, 1.0f);
		player.addPotionEffect(new PotionEffect(21, 1000, 2));
		stack.damageItem(1, player);
	}

	static {
		runes.put((ItemRune)Itemizer.WaterRune, 5);
		runes.put((ItemRune)Itemizer.LifeRune, 1);
	}
}
