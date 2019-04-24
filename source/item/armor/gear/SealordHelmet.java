package net.nevermine.item.armor.gear;

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

public class SealordHelmet extends ItemArmor {
	public SealordHelmet(final ItemArmor.ArmorMaterial material, final int renderIndex, final int armorPiece) {
		super(material, renderIndex, armorPiece);
	}

	public String getArmorTexture(final ItemStack stack, final Entity entity, final int slot, final String type) {
		if (stack.getItem() == Armorizer.SealordHelmet) {
			return "nevermine:textures/models/armor/sealord_helmet.png";
		}

		return null;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean bool) {
		list.add(StringUtil.getColourLocaleString("item.SealordHelmet.desc.1", EnumChatFormatting.DARK_GREEN));
		list.add(StringUtil.getColourLocaleString("item.SealordHelmet.desc.2", EnumChatFormatting.DARK_GREEN));
		list.add(StringUtil.getColourLocaleString("item.SealordHelmet.desc.3", EnumChatFormatting.DARK_GREEN));
		list.add(StringUtil.getColourLocaleString("item.SealordHelmet.desc.4", EnumChatFormatting.DARK_GREEN));
	}

	public boolean getIsRepairable(final ItemStack itemStackArmor, final ItemStack repairItem) {
		return Itemizer.IngotRosite == repairItem.getItem() || super.getIsRepairable(itemStackArmor, repairItem);
	}
}
