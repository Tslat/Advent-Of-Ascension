package net.nevermine.block.decoration;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;

public class Carpet extends Block {
	private String name;

	public Carpet(final Material DecorBasic) {
		super(DecorBasic);
		setCreativeTab(Itemizer.DecorativeBlocksTab);
		setHardness(0.3f);
		setResistance(0.5f);
		func_150089_b(0);
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(final World p_149668_1_, final int p_149668_2_, final int p_149668_3_, final int p_149668_4_) {
		final byte b0 = 0;
		final float f = 0.0625f;
		return AxisAlignedBB.getBoundingBox(p_149668_2_ + minX, p_149668_3_ + minY, p_149668_4_ + minZ, p_149668_2_ + maxX, (double)(p_149668_3_ + b0 * f), p_149668_4_ + maxZ);
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public void setBlockBoundsForItemRender() {
		func_150089_b(0);
	}

	public void setBlockBoundsBasedOnState(final IBlockAccess p_149719_1_, final int p_149719_2_, final int p_149719_3_, final int p_149719_4_) {
		func_150089_b(p_149719_1_.getBlockMetadata(p_149719_2_, p_149719_3_, p_149719_4_));
	}

	protected void func_150089_b(final int p_150089_1_) {
		final byte b0 = 0;
		final float f = 1 * (1 + b0) / 16.0f;
		setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, f, 1.0f);
	}

	private boolean func_150090_e(final World p_150090_1_, final int p_150090_2_, final int p_150090_3_, final int p_150090_4_) {
		if (!canBlockStay(p_150090_1_, p_150090_2_, p_150090_3_, p_150090_4_)) {
			dropBlockAsItem(p_150090_1_, p_150090_2_, p_150090_3_, p_150090_4_, p_150090_1_.getBlockMetadata(p_150090_2_, p_150090_3_, p_150090_4_), 0);
			p_150090_1_.setBlockToAir(p_150090_2_, p_150090_3_, p_150090_4_);
			return false;
		}
		return true;
	}

	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(final IBlockAccess p_149646_1_, final int p_149646_2_, final int p_149646_3_, final int p_149646_4_, final int p_149646_5_) {
		return p_149646_5_ == 1 || super.shouldSideBeRendered(p_149646_1_, p_149646_2_, p_149646_3_, p_149646_4_, p_149646_5_);
	}

	public Block setTextureName(final String name) {
		return setBlockTextureName("nevermine:" + name);
	}

	public Block setName(final String name) {
		setBlockName(this.name = name);
		setTextureName(name);
		GameRegistry.registerBlock(this, name);
		return this;
	}
}
