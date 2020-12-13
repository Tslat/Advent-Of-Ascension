package net.tslat.aoa3.worldgen.structures.dustopia;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class DawnTree2 extends AoAStructure { //StructureSize: 3x20x3
	private static final BlockState dawnLog = AoABlocks.DAWN_LOG.get().getDefaultState();
	private static final BlockState dawnLeaves = AoABlocks.DAWN_LEAVES.get().getDefaultState();

	public DawnTree2() {
		super("DawnTree2");
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 1, 0, 1, dawnLog);
		addBlock(world, basePos, 1, 1, 1, dawnLog);
		addBlock(world, basePos, 0, 2, 1, dawnLeaves);
		addBlock(world, basePos, 1, 2, 1, dawnLog);
		addBlock(world, basePos, 2, 2, 1, dawnLeaves);
		addBlock(world, basePos, 1, 3, 1, dawnLog);
		addBlock(world, basePos, 1, 3, 2, dawnLeaves);
		addBlock(world, basePos, 1, 4, 0, dawnLeaves);
		addBlock(world, basePos, 1, 4, 1, dawnLog);
		addBlock(world, basePos, 1, 5, 1, dawnLog);
		addBlock(world, basePos, 2, 5, 1, dawnLeaves);
		addBlock(world, basePos, 0, 6, 1, dawnLeaves);
		addBlock(world, basePos, 1, 6, 1, dawnLog);
		addBlock(world, basePos, 1, 7, 1, dawnLog);
		addBlock(world, basePos, 1, 7, 2, dawnLeaves);
		addBlock(world, basePos, 1, 8, 1, dawnLog);
		addBlock(world, basePos, 1, 9, 0, dawnLeaves);
		addBlock(world, basePos, 1, 9, 1, dawnLog);
		addBlock(world, basePos, 2, 9, 1, dawnLeaves);
		addBlock(world, basePos, 1, 10, 1, dawnLog);
		addBlock(world, basePos, 1, 11, 0, dawnLeaves);
		addBlock(world, basePos, 1, 11, 1, dawnLog);
		addBlock(world, basePos, 1, 11, 2, dawnLeaves);
		addBlock(world, basePos, 0, 12, 1, dawnLeaves);
		addBlock(world, basePos, 1, 12, 1, dawnLog);
		addBlock(world, basePos, 2, 12, 1, dawnLeaves);
		addBlock(world, basePos, 1, 13, 1, dawnLog);
		addBlock(world, basePos, 0, 14, 1, dawnLeaves);
		addBlock(world, basePos, 1, 14, 1, dawnLog);
		addBlock(world, basePos, 1, 14, 2, dawnLeaves);
		addBlock(world, basePos, 1, 15, 0, dawnLeaves);
		addBlock(world, basePos, 1, 15, 1, dawnLog);
		addBlock(world, basePos, 1, 16, 1, dawnLog);
		addBlock(world, basePos, 2, 16, 1, dawnLeaves);
		addBlock(world, basePos, 1, 17, 1, dawnLog);
		addBlock(world, basePos, 1, 17, 2, dawnLeaves);
		addBlock(world, basePos, 0, 18, 1, dawnLeaves);
		addBlock(world, basePos, 1, 18, 1, dawnLog);
		addBlock(world, basePos, 2, 18, 1, dawnLeaves);
		addBlock(world, basePos, 1, 19, 1, dawnLeaves);
	}
}
