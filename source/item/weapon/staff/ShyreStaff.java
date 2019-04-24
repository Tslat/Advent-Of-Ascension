package net.nevermine.item.weapon.staff;

import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.item.ItemRune;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Weaponizer;
import net.nevermine.projectiles.energy.EntityShyreShot;

import java.util.HashMap;
import java.util.List;

public class ShyreStaff extends BaseStaff {
	private static HashMap<ItemRune, Integer> runes = new HashMap<ItemRune, Integer>();

	public ShyreStaff() {
		super(runes);
		setCreativeTab(Weaponizer.StaffTab);
		setMaxDamage(600);
	}

	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
		return Itemizer.IngotRosite == par2ItemStack.getItem() ? true : super.getIsRepairable(par1ItemStack, par2ItemStack);
	}

	public void fireStaff(World world, ItemStack stack, EntityPlayer player) {
		player.worldObj.playSoundAtEntity(player, "nevermine:ShyreStaff", 1.0F, 1.0F);
		if (!player.worldObj.isRemote) {
			player.worldObj.spawnEntityInWorld(new EntityShyreShot(player.worldObj, player, 0.0F));
			stack.damageItem(1, player);
		}
	}

	@SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool) {
		list.add(StringUtil.getColourLocaleString("item.ShyreStaff.desc.1", EnumChatFormatting.DARK_GREEN));
		list.add(StringUtil.getColourLocaleString("items.description.runes.required", EnumChatFormatting.LIGHT_PURPLE));
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.runes.specific", EnumChatFormatting.WHITE, Integer.toString(3), StringUtil.getLocaleString("item.WindRune.name")));
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.runes.specific", EnumChatFormatting.WHITE, Integer.toString(1), StringUtil.getLocaleString("item.DistortionRune.name")));
	}

	static {
		runes.put((ItemRune)Itemizer.WindRune, 3);
		runes.put((ItemRune)Itemizer.DistortionRune, 1);
	}
}
