package net.nevermine.item.armor.artistry;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.nevermine.assist.StringUtil;
import net.nevermine.container.PlayerContainer;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Armorizer;

import java.util.List;

import static net.nevermine.container.PlayerContainer.Skills.Hauling;

public class HaulingArmor extends ItemArmor implements ArtistryArmor {

	@Override
	public PlayerContainer.Skills getAssociatedSkill() {
		return Hauling;
	}

	public HaulingArmor(final ItemArmor.ArmorMaterial p_i45325_1_, final int p_i45325_2_, final int p_i45325_3_) {
		super(p_i45325_1_, p_i45325_2_, p_i45325_3_);
	}

	public String getArmorTexture(final ItemStack stack, final Entity entity, final int slot, final String type) {
		if (stack.getItem() == Armorizer.HaulingHelmet || stack.getItem() == Armorizer.HaulingChestplate || stack.getItem() == Armorizer.HaulingBoots) {
			return "nevermine:textures/models/armor/haulingarmor_1.png";
		}
		if (stack.getItem() == Armorizer.HaulingLeggings) {
			return "nevermine:textures/models/armor/haulingarmor_2.png";
		}
		return null;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean bool) {
		list.add(StringUtil.getColourLocaleString("items.description.fullSetBonus", EnumChatFormatting.GOLD));
		list.add(StringUtil.getColourLocaleString("item.HaulingArmor.desc.1", EnumChatFormatting.DARK_GREEN));
		list.add(StringUtil.getColourLocaleString("item.HaulingArmor.desc.2", EnumChatFormatting.DARK_GREEN));
		list.add(StringUtil.getColourLocaleString("item.HaulingArmor.desc.3", EnumChatFormatting.DARK_GREEN));
		list.add(StringUtil.getColourLocaleString("item.HaulingArmor.desc.4", EnumChatFormatting.DARK_GREEN));
		list.add(StringUtil.getColourLocaleString("item.HaulingArmor.desc.5", EnumChatFormatting.DARK_GREEN));
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.skillRequirement", EnumChatFormatting.RED, Integer.toString(100), StringUtil.getLocaleString("skills.hauling.name")));
	}

	public boolean getIsRepairable(final ItemStack par1ItemStack, final ItemStack par2ItemStack) {
		return Itemizer.IngotRosite == par2ItemStack.getItem() || super.getIsRepairable(par1ItemStack, par2ItemStack);
	}
}
