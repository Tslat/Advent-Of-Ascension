package net.tslat.aoa3.worldgen.structures.dustopia;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class DawnTree1 extends AoAStructure { //StructureSize: 3x21x3
	private static final BlockState dawnLog = AoABlocks.DAWN_LOG.get().getDefaultState();
	private static final BlockState dawnLeaves = AoABlocks.DAWN_LEAVES.get().getDefaultState();

	public DawnTree1() {
		super("DawnTree1");
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 1, 0, 1, dawnLog);
		addBlock(world, basePos, 1, 1, 1, dawnLog);
		addBlock(world, basePos, 1, 1, 2, dawnLeaves);
		addBlock(world, basePos, 1, 2, 1, dawnLog);
		addBlock(world, basePos, 0, 3, 1, dawnLeaves);
		addBlock(world, basePos, 1, 3, 0, dawnLeaves);
		addBlock(world, basePos, 1, 3, 1, dawnLog);
		addBlock(world, basePos, 1, 4, 1, dawnLog);
		addBlock(world, basePos, 1, 4, 2, dawnLeaves);
		addBlock(world, basePos, 2, 4, 1, dawnLeaves);
		addBlock(world, basePos, 1, 5, 0, dawnLeaves);
		addBlock(world, basePos, 1, 5, 1, dawnLog);
		addBlock(world, basePos, 1, 6, 1, dawnLog);
		addBlock(world, basePos, 1, 7, 1, dawnLog);
		addBlock(world, basePos, 1, 7, 2, dawnLeaves);
		addBlock(world, basePos, 2, 7, 1, dawnLeaves);
		addBlock(world, basePos, 0, 8, 1, dawnLeaves);
		addBlock(world, basePos, 1, 8, 1, dawnLog);
		addBlock(world, basePos, 1, 9, 0, dawnLeaves);
		addBlock(world, basePos, 1, 9, 1, dawnLog);
		addBlock(world, basePos, 1, 10, 1, dawnLog);
		addBlock(world, basePos, 1, 11, 0, dawnLeaves);
		addBlock(world, basePos, 1, 11, 1, dawnLog);
		addBlock(world, basePos, 1, 11, 2, dawnLeaves);
		addBlock(world, basePos, 0, 12, 1, dawnLeaves);
		addBlock(world, basePos, 1, 12, 1, dawnLog);
		addBlock(world, basePos, 1, 13, 1, dawnLog);
		addBlock(world, basePos, 2, 13, 1, dawnLeaves);
		addBlock(world, basePos, 1, 14, 1, dawnLog);
		addBlock(world, basePos, 1, 14, 2, dawnLeaves);
		addBlock(world, basePos, 1, 15, 1, dawnLog);
		addBlock(world, basePos, 0, 16, 1, dawnLeaves);
		addBlock(world, basePos, 1, 16, 0, dawnLeaves);
		addBlock(world, basePos, 1, 16, 1, dawnLog);
		addBlock(world, basePos, 1, 17, 1, dawnLog);
		addBlock(world, basePos, 2, 17, 1, dawnLeaves);
		addBlock(world, basePos, 1, 18, 1, dawnLog);
		addBlock(world, basePos, 1, 18, 2, dawnLeaves);
		addBlock(world, basePos, 1, 19, 0, dawnLeaves);
		addBlock(world, basePos, 1, 19, 1, dawnLog);
		addBlock(world, basePos, 1, 20, 1, dawnLeaves);
	}
}
