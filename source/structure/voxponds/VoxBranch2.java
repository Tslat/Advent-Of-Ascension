package net.tslat.aoa3.structure.voxponds;

import net.minecraft.block.BlockDirectional;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class VoxBranch2 extends AoAStructure { //StructureSize: 4x1x1
	private static final IBlockState voxLog = BlockRegister.voxLog1.getDefaultState().withProperty(BlockDirectional.FACING, EnumFacing.EAST);

	public VoxBranch2() {
		super("VoxBranch2");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 0, voxLog);
		addBlock(world, basePos, 1, 0, 0, voxLog);
		addBlock(world, basePos, 2, 0, 0, voxLog);
		addBlock(world, basePos, 3, 0, 0, voxLog);
	}
}
