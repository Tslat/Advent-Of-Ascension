package net.tslat.aoa3.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import javax.annotation.Nullable;

public class BasicNonCubeBlock extends BasicBlock {
	private AxisAlignedBB boundingBox = FULL_BLOCK_AABB;

	public BasicNonCubeBlock(String name, String registryName, Material material, float hardness, float resistance) {
		super(name, registryName, material, hardness, resistance);
	}

	public BasicNonCubeBlock(String name, String registryName, Material material) {
		super(name, registryName, material);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return boundingBox;
	}

	@Nullable
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return boundingBox;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public boolean getUseNeighborBrightness(IBlockState state) {
		return true;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	public BasicNonCubeBlock setBoundingBox(AxisAlignedBB boundingBox) {
		this.boundingBox = boundingBox;

		return this;
	}

	public static final AxisAlignedBB shroomStemAABB = new AxisAlignedBB(0.3125, 0, 0.3125, 0.6875, 1, 0.6875);
}
