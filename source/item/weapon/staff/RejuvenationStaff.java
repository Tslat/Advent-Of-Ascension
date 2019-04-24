package net.nevermine.item.weapon.staff;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
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

public class RejuvenationStaff extends BaseStaff {
	private static HashMap<ItemRune, Integer> runes = new HashMap<ItemRune, Integer>();

	public RejuvenationStaff() {
		super(runes);
		setMaxDamage(50);
		setCreativeTab(Weaponizer.StaffTab);
	}

	public boolean getIsRepairable(final ItemStack par1ItemStack, final ItemStack par2ItemStack) {
		return Itemizer.IngotRosite == par2ItemStack.getItem() || super.getIsRepairable(par1ItemStack, par2ItemStack);
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean bool) {
		list.add(StringUtil.getColourLocaleString("item.RejuvenationStaff.desc.1", EnumChatFormatting.DARK_GREEN));
		list.add(StringUtil.getColourLocaleString("items.description.runes.required", EnumChatFormatting.LIGHT_PURPLE));
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.runes.specific", EnumChatFormatting.WHITE, Integer.toString(10), StringUtil.getLocaleString("item.LifeRune.name")));
	}

	@Override
	public void fireStaff(final World world, final ItemStack stack, final EntityPlayer player) {
		player.worldObj.playSoundAtEntity(player, "nevermine:Rejuvination", 1.0f, 1.0f);
		player.heal(15.0f);
		stack.damageItem(1, player);
	}

	static {
		runes.put((ItemRune)Itemizer.LifeRune, 10);
	}
}
