package net.nevermine.block.functional;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.nevermine.izer.Blockizer;
import net.nevermine.izer.Itemizer;

public class WhitewashingTable extends Block {
	@SideOnly(Side.CLIENT)
	private IIcon top;
	@SideOnly(Side.CLIENT)
	private IIcon side;
	@SideOnly(Side.CLIENT)
	private IIcon bottom;

	public WhitewashingTable(final Material p_i45394_1_) {
		super(p_i45394_1_);
		setCreativeTab(Itemizer.FurnitureTab);
		setHardness(5.0f);
		setResistance(0.5f);
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(final int p_149691_1_, final int p_149691_2_) {
		return (p_149691_1_ == 0) ? bottom : ((p_149691_1_ == 1) ? top : blockIcon);
	}

	public Item getWhitewashBlock() {
		return Item.getItemFromBlock(Blockizer.WhiteWashBricks);
	}

	public boolean onBlockActivated(final World w, final int x, final int y, final int z, final EntityPlayer p, final int var6, final float var7, final float var8, final float var9) {
		if (!p.isSneaking() && p.inventory.hasItem(Itemizer.WhitewashingSolution) && p.inventory.hasItem(Item.getItemFromBlock(Blocks.stonebrick)) && p.inventory.consumeInventoryItem(Itemizer.WhitewashingSolution) && p.inventory.consumeInventoryItem(Item.getItemFromBlock(Blocks.stonebrick))) {
			w.playSoundAtEntity(p, "nevermine:WhiteWash", 1.85f, 1.0f);
			if (!w.isRemote) {
				p.dropItem(getWhitewashBlock(), 1);
			}
		}
		return true;
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(final IIconRegister icon) {
		top = icon.registerIcon("nevermine:whitewashTable_top");
		side = icon.registerIcon("nevermine:whitewashTable_side");
		bottom = icon.registerIcon("nevermine:whitewashTable_bottom");
		blockIcon = icon.registerIcon("nevermine:whitewashTable_side");
	}
}
