package net.tslat.aoa3.worldgen.structures.abyss;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class AbyssalTree2 extends AoAStructure { //StructureSize: 11x11x11
	private volatile BlockState shadowLeaves = AoABlocks.SHADOW_LEAVES.get().getDefaultState();
	private final BlockState shadowLog = AoABlocks.SHADOW_LOG.get().getDefaultState();
	private final BlockState shadowLogBark = AoABlocks.SHADOW_WOOD.get().getDefaultState();

	public AbyssalTree2() {
		super("AbyssalTree2");
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 5, 0, 5, shadowLog);
		addBlock(world, basePos, 5, 1, 5, shadowLog);
		addBlock(world, basePos, 5, 2, 5, shadowLog);
		addBlock(world, basePos, 5, 3, 5, shadowLog);
		addBlock(world, basePos, 5, 4, 5, shadowLog);
		addBlock(world, basePos, 5, 5, 5, shadowLog);
		addBlock(world, basePos, 0, 6, 4, shadowLeaves);
		addBlock(world, basePos, 0, 6, 6, shadowLeaves);
		addBlock(world, basePos, 2, 6, 4, shadowLeaves);
		addBlock(world, basePos, 2, 6, 6, shadowLeaves);
		addBlock(world, basePos, 4, 6, 0, shadowLeaves);
		addBlock(world, basePos, 4, 6, 2, shadowLeaves);
		addBlock(world, basePos, 4, 6, 8, shadowLeaves);
		addBlock(world, basePos, 4, 6, 10, shadowLeaves);
		addBlock(world, basePos, 5, 6, 5, shadowLog);
		addBlock(world, basePos, 6, 6, 0, shadowLeaves);
		addBlock(world, basePos, 6, 6, 2, shadowLeaves);
		addBlock(world, basePos, 6, 6, 8, shadowLeaves);
		addBlock(world, basePos, 6, 6, 10, shadowLeaves);
		addBlock(world, basePos, 8, 6, 4, shadowLeaves);
		addBlock(world, basePos, 8, 6, 6, shadowLeaves);
		addBlock(world, basePos, 10, 6, 4, shadowLeaves);
		addBlock(world, basePos, 10, 6, 6, shadowLeaves);
		addBlock(world, basePos, 0, 7, 4, shadowLeaves);
		addBlock(world, basePos, 0, 7, 6, shadowLeaves);
		addBlock(world, basePos, 2, 7, 4, shadowLeaves);
		addBlock(world, basePos, 2, 7, 6, shadowLeaves);
		addBlock(world, basePos, 4, 7, 0, shadowLeaves);
		addBlock(world, basePos, 4, 7, 2, shadowLeaves);
		addBlock(world, basePos, 4, 7, 8, shadowLeaves);
		addBlock(world, basePos, 4, 7, 10, shadowLeaves);
		addBlock(world, basePos, 5, 7, 5, shadowLog);
		addBlock(world, basePos, 6, 7, 0, shadowLeaves);
		addBlock(world, basePos, 6, 7, 2, shadowLeaves);
		addBlock(world, basePos, 6, 7, 8, shadowLeaves);
		addBlock(world, basePos, 6, 7, 10, shadowLeaves);
		addBlock(world, basePos, 8, 7, 4, shadowLeaves);
		addBlock(world, basePos, 8, 7, 6, shadowLeaves);
		addBlock(world, basePos, 10, 7, 4, shadowLeaves);
		addBlock(world, basePos, 10, 7, 6, shadowLeaves);
		addBlock(world, basePos, 0, 8, 4, shadowLeaves);
		addBlock(world, basePos, 0, 8, 5, shadowLeaves);
		addBlock(world, basePos, 0, 8, 6, shadowLeaves);
		addBlock(world, basePos, 1, 8, 4, shadowLeaves);
		addBlock(world, basePos, 1, 8, 6, shadowLeaves);
		addBlock(world, basePos, 2, 8, 4, shadowLeaves);
		addBlock(world, basePos, 2, 8, 6, shadowLeaves);
		addBlock(world, basePos, 4, 8, 0, shadowLeaves);
		addBlock(world, basePos, 4, 8, 1, shadowLeaves);
		addBlock(world, basePos, 4, 8, 2, shadowLeaves);
		addBlock(world, basePos, 4, 8, 8, shadowLeaves);
		addBlock(world, basePos, 4, 8, 9, shadowLeaves);
		addBlock(world, basePos, 4, 8, 10, shadowLeaves);
		addBlock(world, basePos, 5, 8, 0, shadowLeaves);
		addBlock(world, basePos, 5, 8, 5, shadowLog);
		addBlock(world, basePos, 5, 8, 10, shadowLeaves);
		addBlock(world, basePos, 6, 8, 0, shadowLeaves);
		addBlock(world, basePos, 6, 8, 1, shadowLeaves);
		addBlock(world, basePos, 6, 8, 2, shadowLeaves);
		addBlock(world, basePos, 6, 8, 8, shadowLeaves);
		addBlock(world, basePos, 6, 8, 9, shadowLeaves);
		addBlock(world, basePos, 6, 8, 10, shadowLeaves);
		addBlock(world, basePos, 8, 8, 4, shadowLeaves);
		addBlock(world, basePos, 8, 8, 6, shadowLeaves);
		addBlock(world, basePos, 9, 8, 4, shadowLeaves);
		addBlock(world, basePos, 9, 8, 6, shadowLeaves);
		addBlock(world, basePos, 10, 8, 4, shadowLeaves);
		addBlock(world, basePos, 10, 8, 5, shadowLeaves);
		addBlock(world, basePos, 10, 8, 6, shadowLeaves);
		addBlock(world, basePos, 0, 9, 5, shadowLeaves);
		addBlock(world, basePos, 1, 9, 5, shadowLog);
		addBlock(world, basePos, 2, 9, 5, shadowLog);
		addBlock(world, basePos, 5, 9, 0, shadowLeaves);
		addBlock(world, basePos, 5, 9, 1, shadowLog);
		addBlock(world, basePos, 5, 9, 2, shadowLog);
		addBlock(world, basePos, 5, 9, 5, shadowLog);
		addBlock(world, basePos, 5, 9, 8, shadowLog);
		addBlock(world, basePos, 5, 9, 9, shadowLog);
		addBlock(world, basePos, 5, 9, 10, shadowLeaves);
		addBlock(world, basePos, 8, 9, 5, shadowLog);
		addBlock(world, basePos, 9, 9, 5, shadowLog);
		addBlock(world, basePos, 10, 9, 5, shadowLeaves);
		addBlock(world, basePos, 0, 10, 5, shadowLeaves);
		addBlock(world, basePos, 1, 10, 5, shadowLeaves);
		addBlock(world, basePos, 2, 10, 5, shadowLeaves);
		addBlock(world, basePos, 3, 10, 5, shadowLogBark);
		addBlock(world, basePos, 4, 10, 5, shadowLogBark);
		addBlock(world, basePos, 5, 10, 0, shadowLeaves);
		addBlock(world, basePos, 5, 10, 1, shadowLeaves);
		addBlock(world, basePos, 5, 10, 2, shadowLeaves);
		addBlock(world, basePos, 5, 10, 3, shadowLogBark);
		addBlock(world, basePos, 5, 10, 4, shadowLogBark);
		addBlock(world, basePos, 5, 10, 5, shadowLogBark);
		addBlock(world, basePos, 5, 10, 6, shadowLogBark);
		addBlock(world, basePos, 5, 10, 7, shadowLogBark);
		addBlock(world, basePos, 5, 10, 8, shadowLeaves);
		addBlock(world, basePos, 5, 10, 9, shadowLeaves);
		addBlock(world, basePos, 5, 10, 10, shadowLeaves);
		addBlock(world, basePos, 6, 10, 5, shadowLogBark);
		addBlock(world, basePos, 7, 10, 5, shadowLogBark);
		addBlock(world, basePos, 8, 10, 5, shadowLeaves);
		addBlock(world, basePos, 9, 10, 5, shadowLeaves);
		addBlock(world, basePos, 10, 10, 5, shadowLeaves);
	}
}
