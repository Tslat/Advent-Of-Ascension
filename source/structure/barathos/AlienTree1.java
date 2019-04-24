package net.tslat.aoa3.structure.barathos;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class AlienTree1 extends AoAStructure { //StructureSize: 3x13x3
	private static final IBlockState opolloLeaves = BlockRegister.leavesOpollo.getDefaultState();
	private static final IBlockState opolloLog = BlockRegister.logOpollo.getDefaultState();

	public AlienTree1() {
		super("AlienTree1");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 1, 0, 1, opolloLog);
		addBlock(world, basePos, 1, 1, 1, opolloLog);
		addBlock(world, basePos, 1, 2, 1, opolloLog);
		addBlock(world, basePos, 1, 3, 1, opolloLog);
		addBlock(world, basePos, 1, 4, 1, opolloLog);
		addBlock(world, basePos, 1, 5, 1, opolloLog);
		addBlock(world, basePos, 1, 6, 1, opolloLog);
		addBlock(world, basePos, 1, 7, 1, opolloLog);
		addBlock(world, basePos, 1, 8, 1, opolloLog);
		addBlock(world, basePos, 1, 9, 1, opolloLog);
		addBlock(world, basePos, 0, 10, 1, opolloLeaves);
		addBlock(world, basePos, 1, 10, 0, opolloLeaves);
		addBlock(world, basePos, 1, 10, 1, opolloLeaves);
		addBlock(world, basePos, 1, 10, 2, opolloLeaves);
		addBlock(world, basePos, 2, 10, 1, opolloLeaves);
		addBlock(world, basePos, 0, 11, 0, opolloLeaves);
		addBlock(world, basePos, 0, 11, 1, opolloLeaves);
		addBlock(world, basePos, 0, 11, 2, opolloLeaves);
		addBlock(world, basePos, 1, 11, 0, opolloLeaves);
		addBlock(world, basePos, 1, 11, 1, opolloLeaves);
		addBlock(world, basePos, 1, 11, 2, opolloLeaves);
		addBlock(world, basePos, 2, 11, 0, opolloLeaves);
		addBlock(world, basePos, 2, 11, 1, opolloLeaves);
		addBlock(world, basePos, 2, 11, 2, opolloLeaves);
		addBlock(world, basePos, 0, 12, 1, opolloLeaves);
		addBlock(world, basePos, 1, 12, 0, opolloLeaves);
		addBlock(world, basePos, 1, 12, 1, opolloLeaves);
		addBlock(world, basePos, 1, 12, 2, opolloLeaves);
		addBlock(world, basePos, 2, 12, 1, opolloLeaves);
	}
}
