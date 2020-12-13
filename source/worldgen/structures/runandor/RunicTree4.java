package net.tslat.aoa3.worldgen.structures.runandor;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class RunicTree4 extends AoAStructure { //StructureSize: 5x8x5
	private static final BlockState runicLeaves = AoABlocks.RUNIC_LEAVES.get().getDefaultState();
	private static final BlockState runicLog = AoABlocks.RUNIC_LOG.get().getDefaultState();

	public RunicTree4() {
		super("RunicTree4");
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 2, 0, 2, runicLog);
		addBlock(world, basePos, 2, 1, 2, runicLog);
		addBlock(world, basePos, 2, 2, 2, runicLog);
		addBlock(world, basePos, 2, 3, 2, runicLog);
		addBlock(world, basePos, 0, 4, 2, runicLog);
		addBlock(world, basePos, 1, 4, 2, runicLog);
		addBlock(world, basePos, 2, 4, 0, runicLog);
		addBlock(world, basePos, 2, 4, 1, runicLog);
		addBlock(world, basePos, 2, 4, 2, runicLog);
		addBlock(world, basePos, 2, 4, 3, runicLog);
		addBlock(world, basePos, 2, 4, 4, runicLog);
		addBlock(world, basePos, 3, 4, 2, runicLog);
		addBlock(world, basePos, 4, 4, 2, runicLog);
		addBlock(world, basePos, 0, 5, 2, runicLog);
		addBlock(world, basePos, 1, 5, 1, runicLeaves);
		addBlock(world, basePos, 1, 5, 2, runicLeaves);
		addBlock(world, basePos, 1, 5, 3, runicLeaves);
		addBlock(world, basePos, 2, 5, 0, runicLog);
		addBlock(world, basePos, 2, 5, 1, runicLeaves);
		addBlock(world, basePos, 2, 5, 2, runicLeaves);
		addBlock(world, basePos, 2, 5, 3, runicLeaves);
		addBlock(world, basePos, 2, 5, 4, runicLog);
		addBlock(world, basePos, 3, 5, 1, runicLeaves);
		addBlock(world, basePos, 3, 5, 2, runicLeaves);
		addBlock(world, basePos, 3, 5, 3, runicLeaves);
		addBlock(world, basePos, 4, 5, 2, runicLog);
		addBlock(world, basePos, 1, 6, 1, runicLeaves);
		addBlock(world, basePos, 1, 6, 2, runicLeaves);
		addBlock(world, basePos, 1, 6, 3, runicLeaves);
		addBlock(world, basePos, 2, 6, 1, runicLeaves);
		addBlock(world, basePos, 2, 6, 2, runicLeaves);
		addBlock(world, basePos, 2, 6, 3, runicLeaves);
		addBlock(world, basePos, 3, 6, 1, runicLeaves);
		addBlock(world, basePos, 3, 6, 2, runicLeaves);
		addBlock(world, basePos, 3, 6, 3, runicLeaves);
		addBlock(world, basePos, 1, 7, 1, runicLeaves);
		addBlock(world, basePos, 1, 7, 2, runicLeaves);
		addBlock(world, basePos, 1, 7, 3, runicLeaves);
		addBlock(world, basePos, 2, 7, 1, runicLeaves);
		addBlock(world, basePos, 2, 7, 2, runicLeaves);
		addBlock(world, basePos, 2, 7, 3, runicLeaves);
		addBlock(world, basePos, 3, 7, 1, runicLeaves);
		addBlock(world, basePos, 3, 7, 2, runicLeaves);
		addBlock(world, basePos, 3, 7, 3, runicLeaves);
	}
}
