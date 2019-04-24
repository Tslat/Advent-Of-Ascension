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

public class KnightArmor extends ItemArmor {
	public KnightArmor(ArmorMaterial p_i45325_1_, int p_i45325_2_, int p_i45325_3_) {
		super(p_i45325_1_, p_i45325_2_, p_i45325_3_);
	}

	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		if ((stack.getItem() == Armorizer.KnightHelmet) || (stack.getItem() == Armorizer.KnightChestplate) || (stack.getItem() == Armorizer.KnightBoots)) {
			return "nevermine:textures/models/armor/knightarmor_1.png";
		}

		if (stack.getItem() == Armorizer.KnightLeggings) {
			return "nevermine:textures/models/armor/knightarmor_2.png";
		}

		return null;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool) {
		list.add(StringUtil.getColourLocaleString("items.description.fullSetBonus", EnumChatFormatting.GOLD));
		list.add(StringUtil.getColourLocaleString("item.KnightArmor.desc.1", EnumChatFormatting.DARK_GREEN));
		list.add(StringUtil.getColourLocaleString("item.KnightArmor.desc.2", EnumChatFormatting.DARK_GREEN));
	}

	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
		return Itemizer.IngotRosite == par2ItemStack.getItem() ? true : super.getIsRepairable(par1ItemStack, par2ItemStack);
	}
}
