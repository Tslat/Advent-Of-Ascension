package net.tslat.aoa3.block.functional.misc;

import net.minecraft.block.BlockLadder;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;

import java.util.Random;

public class LadderBlock extends BlockLadder {
	public LadderBlock(String name, String registryName, float hardness, float resistance) {
		super();
		setTranslationKey(name);
		setRegistryName("aoa3:" + registryName);
		setHardness(hardness);
		setResistance(resistance);
		setDefaultState(blockState.getBaseState().withProperty(BlockLadder.FACING, EnumFacing.NORTH));
		setCreativeTab(CreativeTabsRegister.DECORATION_BLOCKS);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		switch (state.getValue(FACING))	{
			case NORTH:
				return LADDER_NORTH_AABB;
			case SOUTH:
				return LADDER_SOUTH_AABB;
			case WEST:
				return LADDER_WEST_AABB;
			case EAST:
			default:
				return LADDER_EAST_AABB;
		}
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumFacing facing = EnumFacing.byIndex(meta);

		if (facing.getAxis() == EnumFacing.Axis.Y)
			facing = EnumFacing.NORTH;

		return getDefaultState().withProperty(FACING, facing);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(FACING).getIndex();
	}

	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot) {
		return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
	}

	@Override
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
		return state.withRotation(mirrorIn.toRotation(state.getValue(FACING)));
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, FACING);
	}

	@Override
	public int quantityDropped(Random random) {
		return 1;
	}
}
