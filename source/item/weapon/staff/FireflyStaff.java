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
import net.nevermine.projectiles.staff.EntityFireflyStaff;

import java.util.HashMap;
import java.util.List;

public class FireflyStaff extends BaseStaff {
	private static HashMap<ItemRune, Integer> runes = new HashMap<ItemRune, Integer>();
	private final float dmg = 20.0f;

	public FireflyStaff() {
		super(runes);
		setCreativeTab(Weaponizer.StaffTab);
		setMaxDamage(700);
	}

	public boolean getIsRepairable(final ItemStack par1ItemStack, final ItemStack par2ItemStack) {
		return Itemizer.IngotRosite == par2ItemStack.getItem() || super.getIsRepairable(par1ItemStack, par2ItemStack);
	}

	@Override
	public void fireStaff(final World world, final ItemStack stack, final EntityPlayer player) {
		player.worldObj.playSoundAtEntity(player, "nevermine:FireflyStaff", 1.0f, 1.0f);
		if (!player.worldObj.isRemote) {
			player.worldObj.spawnEntityInWorld(new EntityFireflyStaff(player.worldObj, player, dmg, 0.0f, -0.1f, 0.0f, 1, 1, 1));
			player.worldObj.spawnEntityInWorld(new EntityFireflyStaff(player.worldObj, player, dmg, 0.0f, -0.1f, 0.0f, -1, 1, 1));
			player.worldObj.spawnEntityInWorld(new EntityFireflyStaff(player.worldObj, player, dmg, 0.0f, -0.1f, 0.0f, -1, 1, -1));
			player.worldObj.spawnEntityInWorld(new EntityFireflyStaff(player.worldObj, player, dmg, 0.0f, -0.1f, 0.0f, -1, 1, -1));
			player.worldObj.spawnEntityInWorld(new EntityFireflyStaff(player.worldObj, player, dmg, 0.0f, -0.05f, 0.0f, 1, 1, 1));
			player.worldObj.spawnEntityInWorld(new EntityFireflyStaff(player.worldObj, player, dmg, 0.0f, -0.05f, 0.0f, -1, 1, 1));
			player.worldObj.spawnEntityInWorld(new EntityFireflyStaff(player.worldObj, player, dmg, 0.0f, -0.05f, 0.0f, -1, 1, -1));
			player.worldObj.spawnEntityInWorld(new EntityFireflyStaff(player.worldObj, player, dmg, 0.0f, -0.05f, 0.0f, -1, 1, -1));
			stack.damageItem(1, player);
		}
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean bool) {
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.damage.ranged", EnumChatFormatting.DARK_RED, Integer.toString((int)dmg)));
		list.add(StringUtil.getColourLocaleString("item.FireflyStaff.desc.1", EnumChatFormatting.DARK_GREEN));
		list.add(StringUtil.getColourLocaleString("item.FireflyStaff.desc.2", EnumChatFormatting.DARK_GREEN));
		list.add(StringUtil.getColourLocaleString("items.description.runes.required", EnumChatFormatting.LIGHT_PURPLE));
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.runes.specific", EnumChatFormatting.WHITE, Integer.toString(1), StringUtil.getLocaleString("item.WindRune.name")));
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.runes.specific", EnumChatFormatting.WHITE, Integer.toString(5), StringUtil.getLocaleString("item.FireRune.name")));
	}

	static {
		runes.put((ItemRune)Itemizer.WindRune, 1);
		runes.put((ItemRune)Itemizer.FireRune, 5);
	}
}
