package net.tslat.aoa3.worldgen.structures.runandor;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class RunicTree3 extends AoAStructure { //StructureSize: 3x10x3
	private static final BlockState runicLeaves = AoABlocks.RUNIC_LEAVES.get().getDefaultState();
	private static final BlockState runicLog = AoABlocks.RUNIC_LOG.get().getDefaultState();

	public RunicTree3() {
		super("RunicTree3");
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 1, 0, 1, runicLog);
		addBlock(world, basePos, 1, 1, 1, runicLog);
		addBlock(world, basePos, 1, 2, 1, runicLog);
		addBlock(world, basePos, 1, 3, 1, runicLog);
		addBlock(world, basePos, 1, 4, 1, runicLog);
		addBlock(world, basePos, 1, 5, 1, runicLog);
		addBlock(world, basePos, 0, 6, 1, runicLog);
		addBlock(world, basePos, 1, 6, 0, runicLog);
		addBlock(world, basePos, 1, 6, 1, runicLog);
		addBlock(world, basePos, 1, 6, 2, runicLog);
		addBlock(world, basePos, 2, 6, 1, runicLog);
		addBlock(world, basePos, 0, 7, 1, runicLog);
		addBlock(world, basePos, 1, 7, 0, runicLog);
		addBlock(world, basePos, 1, 7, 1, runicLeaves);
		addBlock(world, basePos, 1, 7, 2, runicLog);
		addBlock(world, basePos, 2, 7, 1, runicLog);
		addBlock(world, basePos, 1, 8, 1, runicLeaves);
		addBlock(world, basePos, 1, 9, 1, runicLeaves);
	}
}
