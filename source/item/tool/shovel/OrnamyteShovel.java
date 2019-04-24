package net.nevermine.item.tool.shovel;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.nevermine.assist.StringUtil;
import net.nevermine.item.tool.ExtractionTool;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Toolizer;

import java.util.List;

public class OrnamyteShovel extends ItemSpade implements ExtractionTool {

	@Override
	public int getLevelReq() {
		return 65;
	}

	public OrnamyteShovel(final Item.ToolMaterial p_i45353_1_) {
		super(p_i45353_1_);
		setCreativeTab(Toolizer.ToolsTab);
	}

	public boolean getIsRepairable(final ItemStack par1ItemStack, final ItemStack par2ItemStack) {
		return Itemizer.IngotRosite == par2ItemStack.getItem() || super.getIsRepairable(par1ItemStack, par2ItemStack);
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean bool) {
		list.add(StringUtil.getColourLocaleString("item.OrnamyteShovel.desc.1", EnumChatFormatting.DARK_GREEN));
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.skillRequirement", EnumChatFormatting.RED, Integer.toString(65), StringUtil.getLocaleString("skills.extraction.name")));
	}
}
