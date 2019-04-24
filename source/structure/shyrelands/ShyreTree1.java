package net.tslat.aoa3.structure.shyrelands;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class ShyreTree1 extends AoAStructure { //StructureSize: 3x8x3
	private static final IBlockState shyreLeaves = BlockRegister.leavesShyre.getDefaultState();
	private static final IBlockState shyreLog = BlockRegister.logShyre.getDefaultState();

	public ShyreTree1() {
		super("ShyreTree1");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 1, 0, 1, shyreLog);
		addBlock(world, basePos, 1, 1, 1, shyreLog);
		addBlock(world, basePos, 1, 2, 1, shyreLog);
		addBlock(world, basePos, 1, 3, 1, shyreLog);
		addBlock(world, basePos, 1, 4, 1, shyreLog);
		addBlock(world, basePos, 0, 5, 0, shyreLeaves);
		addBlock(world, basePos, 0, 5, 1, shyreLeaves);
		addBlock(world, basePos, 0, 5, 2, shyreLeaves);
		addBlock(world, basePos, 1, 5, 0, shyreLeaves);
		addBlock(world, basePos, 1, 5, 1, shyreLeaves);
		addBlock(world, basePos, 1, 5, 2, shyreLeaves);
		addBlock(world, basePos, 2, 5, 0, shyreLeaves);
		addBlock(world, basePos, 2, 5, 1, shyreLeaves);
		addBlock(world, basePos, 2, 5, 2, shyreLeaves);
		addBlock(world, basePos, 0, 6, 0, shyreLeaves);
		addBlock(world, basePos, 0, 6, 1, shyreLeaves);
		addBlock(world, basePos, 0, 6, 2, shyreLeaves);
		addBlock(world, basePos, 1, 6, 0, shyreLeaves);
		addBlock(world, basePos, 1, 6, 1, shyreLeaves);
		addBlock(world, basePos, 1, 6, 2, shyreLeaves);
		addBlock(world, basePos, 2, 6, 0, shyreLeaves);
		addBlock(world, basePos, 2, 6, 1, shyreLeaves);
		addBlock(world, basePos, 2, 6, 2, shyreLeaves);
		addBlock(world, basePos, 0, 7, 1, shyreLeaves);
		addBlock(world, basePos, 1, 7, 0, shyreLeaves);
		addBlock(world, basePos, 1, 7, 1, shyreLeaves);
		addBlock(world, basePos, 1, 7, 2, shyreLeaves);
		addBlock(world, basePos, 2, 7, 1, shyreLeaves);
	}
}
