package net.nevermine.block.generation;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import net.nevermine.izer.Itemizer;

import java.util.Random;

public class BlockClear extends Block {
	private static Block.SoundType BlockClear = Block.soundTypeGlass;
	private String name;
	private static Material Lvs = Material.glass;

	public BlockClear() {
		super(net.nevermine.block.generation.BlockClear.Lvs);
		setCreativeTab(Itemizer.GenerationTab);
		setHardness(0.2f);
		setResistance(0.0f);
		setSoundType(net.nevermine.block.generation.BlockClear.BlockClear);
	}

	@Override
	protected boolean canSilkHarvest() {
		return true;
	}

	public int quantityDropped(final Random p_149745_1_) {
		return 0;
	}

	public int getRenderBlockPass() {
		return 1;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(final IBlockAccess blockAccess, final int x, final int y, final int z, final int side) {
		final Block i1 = blockAccess.getBlock(x, y, z);
		return i1 != this && super.shouldSideBeRendered(blockAccess, x, y, z, side);
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

	public Block setSoundType(final Block.SoundType name) {
		return setStepSound(name);
	}
}
