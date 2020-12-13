package net.tslat.aoa3.worldgen.structures.shyrelands;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class ShyreTree1 extends AoAStructure { //StructureSize: 3x8x3
	private static final BlockState shyreLeaves = AoABlocks.SHYRE_LEAVES.get().getDefaultState();
	private static final BlockState shyreLog = AoABlocks.SHYRE_LOG.get().getDefaultState();

	public ShyreTree1() {
		super("ShyreTree1");
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
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
