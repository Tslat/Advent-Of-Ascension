package net.tslat.aoa3.worldgen.structures.dustopia;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class DawnTree3 extends AoAStructure { //StructureSize: 7x23x7
	private static final BlockState dawnLog = AoABlocks.DAWN_LOG.get().getDefaultState();
	private static final BlockState dawnLeaves = AoABlocks.DAWN_LEAVES.get().getDefaultState();

	public DawnTree3() {
		super("DawnTree3");
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 3, 0, 3, dawnLog);
		addBlock(world, basePos, 3, 1, 3, dawnLog);
		addBlock(world, basePos, 3, 2, 3, dawnLog);
		addBlock(world, basePos, 3, 3, 3, dawnLog);
		addBlock(world, basePos, 3, 4, 3, dawnLog);
		addBlock(world, basePos, 3, 5, 3, dawnLog);
		addBlock(world, basePos, 3, 6, 3, dawnLog);
		addBlock(world, basePos, 3, 7, 3, dawnLog);
		addBlock(world, basePos, 3, 8, 3, dawnLog);
		addBlock(world, basePos, 3, 9, 3, dawnLog);
		addBlock(world, basePos, 3, 10, 3, dawnLog);
		addBlock(world, basePos, 3, 11, 3, dawnLog);
		addBlock(world, basePos, 3, 12, 3, dawnLog);
		addBlock(world, basePos, 3, 13, 3, dawnLog);
		addBlock(world, basePos, 3, 14, 3, dawnLog);
		addBlock(world, basePos, 3, 15, 3, dawnLog);
		addBlock(world, basePos, 3, 16, 3, dawnLog);
		addBlock(world, basePos, 3, 17, 3, dawnLog);
		addBlock(world, basePos, 2, 18, 3, dawnLeaves);
		addBlock(world, basePos, 3, 18, 2, dawnLeaves);
		addBlock(world, basePos, 3, 18, 3, dawnLog);
		addBlock(world, basePos, 3, 18, 4, dawnLeaves);
		addBlock(world, basePos, 4, 18, 3, dawnLeaves);
		addBlock(world, basePos, 2, 19, 2, dawnLeaves);
		addBlock(world, basePos, 2, 19, 3, dawnLeaves);
		addBlock(world, basePos, 2, 19, 4, dawnLeaves);
		addBlock(world, basePos, 3, 19, 2, dawnLeaves);
		addBlock(world, basePos, 3, 19, 3, dawnLog);
		addBlock(world, basePos, 3, 19, 4, dawnLeaves);
		addBlock(world, basePos, 4, 19, 2, dawnLeaves);
		addBlock(world, basePos, 4, 19, 3, dawnLeaves);
		addBlock(world, basePos, 4, 19, 4, dawnLeaves);
		addBlock(world, basePos, 0, 20, 0, dawnLeaves);
		addBlock(world, basePos, 0, 20, 1, dawnLeaves);
		addBlock(world, basePos, 0, 20, 2, dawnLeaves);
		addBlock(world, basePos, 0, 20, 3, dawnLeaves);
		addBlock(world, basePos, 0, 20, 4, dawnLeaves);
		addBlock(world, basePos, 0, 20, 5, dawnLeaves);
		addBlock(world, basePos, 0, 20, 6, dawnLeaves);
		addBlock(world, basePos, 1, 20, 0, dawnLeaves);
		addBlock(world, basePos, 1, 20, 1, dawnLeaves);
		addBlock(world, basePos, 1, 20, 2, dawnLeaves);
		addBlock(world, basePos, 1, 20, 3, dawnLeaves);
		addBlock(world, basePos, 1, 20, 4, dawnLeaves);
		addBlock(world, basePos, 1, 20, 5, dawnLeaves);
		addBlock(world, basePos, 1, 20, 6, dawnLeaves);
		addBlock(world, basePos, 2, 20, 0, dawnLeaves);
		addBlock(world, basePos, 2, 20, 1, dawnLeaves);
		addBlock(world, basePos, 2, 20, 2, dawnLeaves);
		addBlock(world, basePos, 2, 20, 3, dawnLeaves);
		addBlock(world, basePos, 2, 20, 4, dawnLeaves);
		addBlock(world, basePos, 2, 20, 5, dawnLeaves);
		addBlock(world, basePos, 2, 20, 6, dawnLeaves);
		addBlock(world, basePos, 3, 20, 0, dawnLeaves);
		addBlock(world, basePos, 3, 20, 1, dawnLeaves);
		addBlock(world, basePos, 3, 20, 2, dawnLeaves);
		addBlock(world, basePos, 3, 20, 3, dawnLog);
		addBlock(world, basePos, 3, 20, 4, dawnLeaves);
		addBlock(world, basePos, 3, 20, 5, dawnLeaves);
		addBlock(world, basePos, 3, 20, 6, dawnLeaves);
		addBlock(world, basePos, 4, 20, 0, dawnLeaves);
		addBlock(world, basePos, 4, 20, 1, dawnLeaves);
		addBlock(world, basePos, 4, 20, 2, dawnLeaves);
		addBlock(world, basePos, 4, 20, 3, dawnLeaves);
		addBlock(world, basePos, 4, 20, 4, dawnLeaves);
		addBlock(world, basePos, 4, 20, 5, dawnLeaves);
		addBlock(world, basePos, 4, 20, 6, dawnLeaves);
		addBlock(world, basePos, 5, 20, 0, dawnLeaves);
		addBlock(world, basePos, 5, 20, 1, dawnLeaves);
		addBlock(world, basePos, 5, 20, 2, dawnLeaves);
		addBlock(world, basePos, 5, 20, 3, dawnLeaves);
		addBlock(world, basePos, 5, 20, 4, dawnLeaves);
		addBlock(world, basePos, 5, 20, 5, dawnLeaves);
		addBlock(world, basePos, 5, 20, 6, dawnLeaves);
		addBlock(world, basePos, 6, 20, 0, dawnLeaves);
		addBlock(world, basePos, 6, 20, 1, dawnLeaves);
		addBlock(world, basePos, 6, 20, 2, dawnLeaves);
		addBlock(world, basePos, 6, 20, 3, dawnLeaves);
		addBlock(world, basePos, 6, 20, 4, dawnLeaves);
		addBlock(world, basePos, 6, 20, 5, dawnLeaves);
		addBlock(world, basePos, 6, 20, 6, dawnLeaves);
		addBlock(world, basePos, 2, 21, 2, dawnLeaves);
		addBlock(world, basePos, 2, 21, 3, dawnLeaves);
		addBlock(world, basePos, 2, 21, 4, dawnLeaves);
		addBlock(world, basePos, 3, 21, 2, dawnLeaves);
		addBlock(world, basePos, 3, 21, 3, dawnLeaves);
		addBlock(world, basePos, 3, 21, 4, dawnLeaves);
		addBlock(world, basePos, 4, 21, 2, dawnLeaves);
		addBlock(world, basePos, 4, 21, 3, dawnLeaves);
		addBlock(world, basePos, 4, 21, 4, dawnLeaves);
		addBlock(world, basePos, 3, 22, 3, dawnLeaves);
	}
}
