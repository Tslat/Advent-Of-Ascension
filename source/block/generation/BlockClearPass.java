package net.nevermine.block.generation;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;

import java.util.Random;

public class BlockClearPass extends Block {
	private static SoundType BlockClear;
	private String name;

	public BlockClearPass(Material mat) {
		super(mat);
		setCreativeTab(Itemizer.GenerationTab);
		if (mat == Material.glass) {
			setHardness(-1.0F);
			setResistance(-1.0F);
			BlockClear = Block.soundTypeGlass;
		}
		else {
			setHardness(0.2F);
			setResistance(0.0F);
			BlockClear = Block.soundTypeGrass;
		}

		setSoundType(BlockClear);
	}

	public int quantityDropped(Random p_149745_1_) {
		return 0;
	}

	public void onEntityCollidedWithBlock(World p_149670_1_, int p_149670_2_, int p_149670_3_, int p_149670_4_, Entity p_149670_5_) {
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_) {
		return null;
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

	protected boolean canSilkHarvest() {
		return true;
	}

	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess blockAccess, int x, int y, int z, int side) {
		Block i1 = blockAccess.getBlock(x, y, z);
		return i1 == this ? false : super.shouldSideBeRendered(blockAccess, x, y, z, side);
	}

	public Block setTextureName(String name) {
		return setBlockTextureName("nevermine:" + name);
	}

	public Block setName(String name) {
		this.name = name;
		setBlockName(name);
		setTextureName(name);
		GameRegistry.registerBlock(this, name);
		return this;
	}

	public Block setSoundType(SoundType name) {
		return setStepSound(name);
	}
}
