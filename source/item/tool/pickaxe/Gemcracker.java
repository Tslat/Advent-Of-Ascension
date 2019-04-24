package net.nevermine.item.tool.pickaxe;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;
import net.nevermine.assist.StringUtil;
import net.nevermine.block.generation.OreBlock;
import net.nevermine.item.tool.ExtractionTool;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Toolizer;

import java.util.List;

public class Gemcracker extends ItemPickaxe implements ExtractionTool {
	public Gemcracker(Item.ToolMaterial p_i45347_1_) {
		super(p_i45347_1_);

		setCreativeTab(Toolizer.ToolsTab);
	}

	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
		return Itemizer.IngotRosite == par2ItemStack.getItem() ? true : super.getIsRepairable(par1ItemStack, par2ItemStack);
	}

	public boolean onBlockDestroyed(ItemStack item, World world, Block block, int x, int y, int z, EntityLivingBase harvester) {
		super.onBlockDestroyed(item, world, block, x, y, z, harvester);

		if (harvester != null && harvester instanceof EntityPlayer && !(harvester instanceof FakePlayer) && (block instanceof OreBlock || block instanceof BlockOre)) {
			item.setItemDamage(item.getItemDamage() - 30);
		}

		return true;
	}

	public int getLevelReq() {
		return 45;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool) {
		list.add(StringUtil.getColourLocaleString("item.Gemcracker.desc.1", EnumChatFormatting.DARK_GREEN));
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.skillRequirement", EnumChatFormatting.RED, Integer.toString(45), StringUtil.getLocaleString("skills.extraction.name")));
	}
}
