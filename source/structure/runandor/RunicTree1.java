package net.tslat.aoa3.structure.runandor;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class RunicTree1 extends AoAStructure { //StructureSize: 3x8x3
	private static final IBlockState runicLeaves = BlockRegister.leavesRunic.getDefaultState();
	private static final IBlockState runicLog = BlockRegister.logRunic.getDefaultState();

	public RunicTree1() {
		super("RunicTree1");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 1, 0, 1, runicLog);
		addBlock(world, basePos, 1, 1, 1, runicLog);
		addBlock(world, basePos, 1, 2, 1, runicLog);
		addBlock(world, basePos, 1, 3, 1, runicLog);
		addBlock(world, basePos, 0, 4, 1, runicLog);
		addBlock(world, basePos, 1, 4, 0, runicLog);
		addBlock(world, basePos, 1, 4, 1, runicLog);
		addBlock(world, basePos, 1, 4, 2, runicLog);
		addBlock(world, basePos, 2, 4, 1, runicLog);
		addBlock(world, basePos, 0, 5, 1, runicLog);
		addBlock(world, basePos, 1, 5, 0, runicLog);
		addBlock(world, basePos, 1, 5, 1, runicLeaves);
		addBlock(world, basePos, 1, 5, 2, runicLog);
		addBlock(world, basePos, 2, 5, 1, runicLog);
		addBlock(world, basePos, 1, 6, 1, runicLeaves);
		addBlock(world, basePos, 1, 7, 1, runicLeaves);
	}
}
