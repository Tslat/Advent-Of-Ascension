package net.nevermine.item.tool.pickaxe;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.world.BlockEvent;
import net.nevermine.assist.StringUtil;
import net.nevermine.item.tool.ExtractionTool;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Toolizer;

import java.util.List;
import java.util.Random;

public class Pickmax extends ItemPickaxe implements ExtractionTool {
	private int count;
	private Random rand = new Random();

	public Pickmax(Item.ToolMaterial p_i45347_1_) {
		super(p_i45347_1_);

		setCreativeTab(Toolizer.ToolsTab);
	}

	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
		return Itemizer.IngotRosite == par2ItemStack.getItem() ? true : super.getIsRepairable(par1ItemStack, par2ItemStack);
	}

	public int getLevelReq() {
		return 90;
	}

	public boolean onBlockDestroyed(ItemStack item, World world, Block block, int x, int y, int z, EntityLivingBase harvester) {
		super.onBlockDestroyed(item, world, block, x, y, z, harvester);

		if (harvester != null && harvester instanceof EntityPlayer && !(harvester instanceof FakePlayer) && block.getMaterial() == Material.rock && block.isOpaqueCube()) {
			for (int i = x - 1; i < x + 2; i++) {
				for (int j = y - 1; j < y + 2; j++) {
					for (int k = z - 1; k < z + 2; k++) {
						Block bl = world.getBlock(i, j, k);
						float hardness = bl.getBlockHardness(world, i, j, k);

						if (hardness == -1 || hardness > 1.5 * block.getBlockHardness(world, x, y, z)) {
							continue;
						}

						if (!MinecraftForge.EVENT_BUS.post(new BlockEvent.BreakEvent(i, j, k, world, bl, world.getBlockMetadata(i, j, k), (EntityPlayer)harvester))) {
							world.setBlockToAir(i, j, k);
						}
					}
				}
			}
		}

		return true;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool) {
		list.add(StringUtil.getColourLocaleString("item.Pickmax.desc.1", EnumChatFormatting.DARK_GREEN));
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.skillRequirement", EnumChatFormatting.RED, Integer.toString(90), StringUtil.getLocaleString("skills.extraction.name")));
	}
}
