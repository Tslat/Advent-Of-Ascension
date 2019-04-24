package net.nevermine.item.weapon.staff;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.item.ItemRune;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Weaponizer;

import java.util.HashMap;
import java.util.List;

public class SkyStaff extends BaseStaff {
	private static HashMap<ItemRune, Integer> runes = new HashMap<ItemRune, Integer>();

	public SkyStaff() {
		super(runes);
		setMaxDamage(400);
		setCreativeTab(Weaponizer.StaffTab);
	}

	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
		return Itemizer.MagicRepairDust == par2ItemStack.getItem() ? true : super.getIsRepairable(par1ItemStack, par2ItemStack);
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool) {
		list.add(StringUtil.getColourLocaleString("item.SkyStaff.desc.1", EnumChatFormatting.DARK_GREEN));
		list.add(StringUtil.getColourLocaleString("item.SkyStaff.desc.2", EnumChatFormatting.DARK_GREEN));
		list.add(StringUtil.getColourLocaleString("items.description.runes.required", EnumChatFormatting.LIGHT_PURPLE));
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.runes.specific", EnumChatFormatting.WHITE, Integer.toString(2), StringUtil.getLocaleString("item.WindRune.name")));
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.runes.specific", EnumChatFormatting.WHITE, Integer.toString(2), StringUtil.getLocaleString("item.KineticRune.name")));
	}

	public void fireStaff(World world, ItemStack stack, EntityPlayer player) {
		player.worldObj.playSoundAtEntity(player, "nevermine:SkyStaff", 1.0F, 1.0F);
		player.setSprinting(true);
		player.motionY += 0.5F;
		player.motionX = (double)(-MathHelper.sin(player.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(player.rotationPitch / 180.0F * (float)Math.PI) * 1.5f);
		player.motionZ = (double)(MathHelper.cos(player.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(player.rotationPitch / 180.0F * (float)Math.PI) * 1.5f);
		player.velocityChanged = true;

		if (!player.worldObj.isRemote) {
			stack.damageItem(1, player);
		}
	}

	static {
		runes.put((ItemRune)Itemizer.WindRune, 2);
		runes.put((ItemRune)Itemizer.KineticRune, 2);
	}
}
