package net.nevermine.item.tool.pickaxe;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.block.generation.OreBlock;
import net.nevermine.item.tool.ExtractionTool;
import net.nevermine.izer.Blockizer;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Toolizer;

import java.util.List;

public class OccultPickaxe extends ItemPickaxe implements ExtractionTool {

	@Override
	public int getLevelReq() {
		return 75;
	}

	public OccultPickaxe(final Item.ToolMaterial p_i45347_1_) {
		super(p_i45347_1_);
		setCreativeTab(Toolizer.ToolsTab);
	}

	public boolean onBlockDestroyed(final ItemStack stack, final World world, final Block b, final int x, final int y, final int z, final EntityLivingBase entity) {
		if (b.getBlockHardness(world, x, y, z) != 0.0) {
			if (!world.isRemote && entity instanceof EntityPlayer) {
				for (int i = (int)(entity.posX - 3.0); i < (int)(entity.posX + 3.0); ++i) {
					for (int j = (int)(entity.posY - 3.0); j < (int)(entity.posY + 3.0); ++j) {
						for (int k = (int)(entity.posZ - 3.0); k < (int)(entity.posZ + 3.0); ++k) {
							if ((b instanceof OreBlock && b != Blockizer.oreLimonite) || b == Blocks.diamond_ore) {
								((EntityPlayer)entity).addChatMessage(StringUtil.getColourLocale("message.feedback.item.occultPickaxe.activate", EnumChatFormatting.GOLD));
								return true;
							}
						}
					}
				}
			}

			stack.damageItem(1, entity);
		}

		return true;
	}

	public boolean getIsRepairable(final ItemStack par1ItemStack, final ItemStack par2ItemStack) {
		return Itemizer.IngotRosite == par2ItemStack.getItem() || super.getIsRepairable(par1ItemStack, par2ItemStack);
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean bool) {
		list.add(StringUtil.getColourLocaleString("item.OccultPickaxe.desc.1", EnumChatFormatting.DARK_GREEN));
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.skillRequirement", EnumChatFormatting.RED, Integer.toString(75), StringUtil.getLocaleString("skills.extraction.name")));
	}
}
