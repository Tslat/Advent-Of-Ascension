package net.nevermine.item.weapon.staff;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.item.ItemRune;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Weaponizer;

import java.util.HashMap;
import java.util.List;

public class CrystalStaff extends BaseStaff {
	private static HashMap<ItemRune, Integer> runes = new HashMap<ItemRune, Integer>();

	public CrystalStaff() {
		super(runes);
		setCreativeTab(Weaponizer.StaffTab);
		setMaxDamage(300);
	}

	public boolean getIsRepairable(final ItemStack par1ItemStack, final ItemStack par2ItemStack) {
		return Itemizer.IngotRosite == par2ItemStack.getItem() || super.getIsRepairable(par1ItemStack, par2ItemStack);
	}

	@Override
	public void fireStaff(final World world, final ItemStack stack, final EntityPlayer player) {
		player.worldObj.playSoundAtEntity(player, "nevermine:CrysteviaStaff", 1.0f, 1.0f);
		int size = world.getEntitiesWithinAABB(EntityLiving.class, player.boundingBox.expand(10.0, 10.0, 10.0)).size();
		if (size > 7) {
			size = 7;
		}
		player.heal((float)(size * 2));
		stack.damageItem(1, player);
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean bool) {
		list.add(StringUtil.getColourLocaleString("item.CrystalStaff.desc.1", EnumChatFormatting.DARK_GREEN));
		list.add(StringUtil.getColourLocaleString("item.CrystalStaff.desc.2", EnumChatFormatting.DARK_GREEN));
		list.add(StringUtil.getColourLocaleString("items.description.runes.required", EnumChatFormatting.LIGHT_PURPLE));
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.runes.specific", EnumChatFormatting.WHITE, Integer.toString(3), StringUtil.getLocaleString("item.CompassRune.name")));
	}

	static {
		runes.put((ItemRune)Itemizer.CompassRune, 3);
	}
}
