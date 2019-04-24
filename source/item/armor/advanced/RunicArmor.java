package net.nevermine.item.armor.advanced;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.nevermine.assist.StringUtil;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Armorizer;

import java.util.List;

public class RunicArmor extends ItemArmor {
	public RunicArmor(final ItemArmor.ArmorMaterial p_i45325_1_, final int p_i45325_2_, final int p_i45325_3_) {
		super(p_i45325_1_, p_i45325_2_, p_i45325_3_);
	}

	public String getArmorTexture(final ItemStack stack, final Entity entity, final int slot, final String type) {
		if (stack.getItem() == Armorizer.RunicHelmet || stack.getItem() == Armorizer.RunicChestplate || stack.getItem() == Armorizer.RunicBoots) {
			return "nevermine:textures/models/armor/runicarmor_1.png";
		}
		if (stack.getItem() == Armorizer.RunicLeggings) {
			return "nevermine:textures/models/armor/runicarmor_2.png";
		}
		return null;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean bool) {
		list.add(StringUtil.getColourLocaleString("items.description.fullSetBonus", EnumChatFormatting.GOLD));
		list.add(StringUtil.getColourLocaleString("item.RunicArmor.desc.1", EnumChatFormatting.DARK_GREEN));
		list.add(StringUtil.getColourLocaleString("item.RunicArmor.desc.2", EnumChatFormatting.DARK_GREEN));
		list.add(StringUtil.getColourLocaleString("item.RunicArmor.desc.3", EnumChatFormatting.DARK_GREEN));
	}

	public boolean getIsRepairable(final ItemStack par1ItemStack, final ItemStack par2ItemStack) {
		return Itemizer.IngotRosite == par2ItemStack.getItem() || super.getIsRepairable(par1ItemStack, par2ItemStack);
	}
}
