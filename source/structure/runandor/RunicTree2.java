package net.tslat.aoa3.structure.runandor;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class RunicTree2 extends AoAStructure { //StructureSize: 4x10x4
	private static final IBlockState runicLeaves = BlockRegister.leavesRunic.getDefaultState();
	private static final IBlockState runicLog = BlockRegister.logRunic.getDefaultState();

	public RunicTree2() {
		super("RunicTree2");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 1, 0, 1, runicLog);
		addBlock(world, basePos, 1, 0, 2, runicLog);
		addBlock(world, basePos, 2, 0, 1, runicLog);
		addBlock(world, basePos, 2, 0, 2, runicLog);
		addBlock(world, basePos, 1, 1, 1, runicLog);
		addBlock(world, basePos, 1, 1, 2, runicLog);
		addBlock(world, basePos, 2, 1, 1, runicLog);
		addBlock(world, basePos, 2, 1, 2, runicLog);
		addBlock(world, basePos, 1, 2, 1, runicLog);
		addBlock(world, basePos, 1, 2, 2, runicLog);
		addBlock(world, basePos, 2, 2, 1, runicLog);
		addBlock(world, basePos, 2, 2, 2, runicLog);
		addBlock(world, basePos, 1, 3, 1, runicLog);
		addBlock(world, basePos, 1, 3, 2, runicLog);
		addBlock(world, basePos, 2, 3, 1, runicLog);
		addBlock(world, basePos, 2, 3, 2, runicLog);
		addBlock(world, basePos, 1, 4, 1, runicLog);
		addBlock(world, basePos, 1, 4, 2, runicLog);
		addBlock(world, basePos, 2, 4, 1, runicLog);
		addBlock(world, basePos, 2, 4, 2, runicLog);
		addBlock(world, basePos, 0, 5, 1, runicLog);
		addBlock(world, basePos, 0, 5, 2, runicLog);
		addBlock(world, basePos, 1, 5, 0, runicLog);
		addBlock(world, basePos, 1, 5, 1, runicLog);
		addBlock(world, basePos, 1, 5, 2, runicLog);
		addBlock(world, basePos, 1, 5, 3, runicLog);
		addBlock(world, basePos, 2, 5, 0, runicLog);
		addBlock(world, basePos, 2, 5, 1, runicLog);
		addBlock(world, basePos, 2, 5, 2, runicLog);
		addBlock(world, basePos, 2, 5, 3, runicLog);
		addBlock(world, basePos, 3, 5, 1, runicLog);
		addBlock(world, basePos, 3, 5, 2, runicLog);
		addBlock(world, basePos, 0, 6, 1, runicLog);
		addBlock(world, basePos, 0, 6, 2, runicLog);
		addBlock(world, basePos, 1, 6, 0, runicLog);
		addBlock(world, basePos, 1, 6, 1, runicLeaves);
		addBlock(world, basePos, 1, 6, 2, runicLeaves);
		addBlock(world, basePos, 1, 6, 3, runicLog);
		addBlock(world, basePos, 2, 6, 0, runicLog);
		addBlock(world, basePos, 2, 6, 1, runicLeaves);
		addBlock(world, basePos, 2, 6, 2, runicLeaves);
		addBlock(world, basePos, 2, 6, 3, runicLog);
		addBlock(world, basePos, 3, 6, 1, runicLog);
		addBlock(world, basePos, 3, 6, 2, runicLog);
		addBlock(world, basePos, 1, 7, 1, runicLeaves);
		addBlock(world, basePos, 1, 7, 2, runicLeaves);
		addBlock(world, basePos, 2, 7, 1, runicLeaves);
		addBlock(world, basePos, 2, 7, 2, runicLeaves);
		addBlock(world, basePos, 1, 8, 1, runicLeaves);
		addBlock(world, basePos, 1, 8, 2, runicLeaves);
		addBlock(world, basePos, 2, 8, 1, runicLeaves);
		addBlock(world, basePos, 2, 8, 2, runicLeaves);
		addBlock(world, basePos, 1, 9, 1, runicLeaves);
		addBlock(world, basePos, 1, 9, 2, runicLeaves);
		addBlock(world, basePos, 2, 9, 1, runicLeaves);
		addBlock(world, basePos, 2, 9, 2, runicLeaves);
	}
}
