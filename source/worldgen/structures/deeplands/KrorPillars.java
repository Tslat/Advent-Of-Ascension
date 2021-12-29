package net.tslat.aoa3.worldgen.structures.deeplands;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class KrorPillars extends AoAStructure { //StructureSize: 15x41x6
	private static final BlockState stone = AoABlocks.DENSE_STONE.get().getDefaultState();
	private static final BlockState decayedGlass = AoABlocks.DECAYED_GLASS.get().getDefaultState();
	private static final BlockState krorAltar = AoABlocks.KROR_ALTAR.get().getDefaultState();

	public KrorPillars() {
		super("KrorPillars");
	}

	@Override
	protected boolean replacesBlocks() {
		return true;
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 0, stone);
		addBlock(world, basePos, 0, 0, 1, stone);
		addBlock(world, basePos, 1, 0, 0, stone);
		addBlock(world, basePos, 1, 0, 1, stone);
		addBlock(world, basePos, 4, 0, 4, stone);
		addBlock(world, basePos, 4, 0, 5, stone);
		addBlock(world, basePos, 5, 0, 4, stone);
		addBlock(world, basePos, 5, 0, 5, stone);
		addBlock(world, basePos, 7, 0, 0, krorAltar);
		addBlock(world, basePos, 9, 0, 4, stone);
		addBlock(world, basePos, 9, 0, 5, stone);
		addBlock(world, basePos, 10, 0, 4, stone);
		addBlock(world, basePos, 10, 0, 5, stone);
		addBlock(world, basePos, 13, 0, 0, stone);
		addBlock(world, basePos, 13, 0, 1, stone);
		addBlock(world, basePos, 14, 0, 0, stone);
		addBlock(world, basePos, 14, 0, 1, stone);
		addBlock(world, basePos, 0, 1, 0, decayedGlass);
		addBlock(world, basePos, 0, 1, 1, decayedGlass);
		addBlock(world, basePos, 1, 1, 0, decayedGlass);
		addBlock(world, basePos, 1, 1, 1, decayedGlass);
		addBlock(world, basePos, 4, 1, 4, decayedGlass);
		addBlock(world, basePos, 4, 1, 5, decayedGlass);
		addBlock(world, basePos, 5, 1, 4, decayedGlass);
		addBlock(world, basePos, 5, 1, 5, decayedGlass);
		addBlock(world, basePos, 9, 1, 4, decayedGlass);
		addBlock(world, basePos, 9, 1, 5, decayedGlass);
		addBlock(world, basePos, 10, 1, 4, decayedGlass);
		addBlock(world, basePos, 10, 1, 5, decayedGlass);
		addBlock(world, basePos, 13, 1, 0, decayedGlass);
		addBlock(world, basePos, 13, 1, 1, decayedGlass);
		addBlock(world, basePos, 14, 1, 0, decayedGlass);
		addBlock(world, basePos, 14, 1, 1, decayedGlass);
		addBlock(world, basePos, 0, 2, 0, stone);
		addBlock(world, basePos, 0, 2, 1, stone);
		addBlock(world, basePos, 1, 2, 0, stone);
		addBlock(world, basePos, 1, 2, 1, stone);
		addBlock(world, basePos, 4, 2, 4, stone);
		addBlock(world, basePos, 4, 2, 5, stone);
		addBlock(world, basePos, 5, 2, 4, stone);
		addBlock(world, basePos, 5, 2, 5, stone);
		addBlock(world, basePos, 9, 2, 4, stone);
		addBlock(world, basePos, 9, 2, 5, stone);
		addBlock(world, basePos, 10, 2, 4, stone);
		addBlock(world, basePos, 10, 2, 5, stone);
		addBlock(world, basePos, 13, 2, 0, stone);
		addBlock(world, basePos, 13, 2, 1, stone);
		addBlock(world, basePos, 14, 2, 0, stone);
		addBlock(world, basePos, 14, 2, 1, stone);
		addBlock(world, basePos, 0, 3, 0, decayedGlass);
		addBlock(world, basePos, 0, 3, 1, decayedGlass);
		addBlock(world, basePos, 1, 3, 0, decayedGlass);
		addBlock(world, basePos, 1, 3, 1, decayedGlass);
		addBlock(world, basePos, 4, 3, 4, decayedGlass);
		addBlock(world, basePos, 4, 3, 5, decayedGlass);
		addBlock(world, basePos, 5, 3, 4, decayedGlass);
		addBlock(world, basePos, 5, 3, 5, decayedGlass);
		addBlock(world, basePos, 9, 3, 4, decayedGlass);
		addBlock(world, basePos, 9, 3, 5, decayedGlass);
		addBlock(world, basePos, 10, 3, 4, decayedGlass);
		addBlock(world, basePos, 10, 3, 5, decayedGlass);
		addBlock(world, basePos, 13, 3, 0, decayedGlass);
		addBlock(world, basePos, 13, 3, 1, decayedGlass);
		addBlock(world, basePos, 14, 3, 0, decayedGlass);
		addBlock(world, basePos, 14, 3, 1, decayedGlass);
		addBlock(world, basePos, 0, 4, 0, stone);
		addBlock(world, basePos, 0, 4, 1, stone);
		addBlock(world, basePos, 1, 4, 0, stone);
		addBlock(world, basePos, 1, 4, 1, stone);
		addBlock(world, basePos, 4, 4, 4, stone);
		addBlock(world, basePos, 4, 4, 5, stone);
		addBlock(world, basePos, 5, 4, 4, stone);
		addBlock(world, basePos, 5, 4, 5, stone);
		addBlock(world, basePos, 9, 4, 4, stone);
		addBlock(world, basePos, 9, 4, 5, stone);
		addBlock(world, basePos, 10, 4, 4, stone);
		addBlock(world, basePos, 10, 4, 5, stone);
		addBlock(world, basePos, 13, 4, 0, stone);
		addBlock(world, basePos, 13, 4, 1, stone);
		addBlock(world, basePos, 14, 4, 0, stone);
		addBlock(world, basePos, 14, 4, 1, stone);
		addBlock(world, basePos, 0, 5, 0, decayedGlass);
		addBlock(world, basePos, 0, 5, 1, decayedGlass);
		addBlock(world, basePos, 1, 5, 0, decayedGlass);
		addBlock(world, basePos, 1, 5, 1, decayedGlass);
		addBlock(world, basePos, 4, 5, 4, decayedGlass);
		addBlock(world, basePos, 4, 5, 5, decayedGlass);
		addBlock(world, basePos, 5, 5, 4, decayedGlass);
		addBlock(world, basePos, 5, 5, 5, decayedGlass);
		addBlock(world, basePos, 9, 5, 4, decayedGlass);
		addBlock(world, basePos, 9, 5, 5, decayedGlass);
		addBlock(world, basePos, 10, 5, 4, decayedGlass);
		addBlock(world, basePos, 10, 5, 5, decayedGlass);
		addBlock(world, basePos, 13, 5, 0, decayedGlass);
		addBlock(world, basePos, 13, 5, 1, decayedGlass);
		addBlock(world, basePos, 14, 5, 0, decayedGlass);
		addBlock(world, basePos, 14, 5, 1, decayedGlass);
		addBlock(world, basePos, 0, 6, 0, stone);
		addBlock(world, basePos, 0, 6, 1, stone);
		addBlock(world, basePos, 1, 6, 0, stone);
		addBlock(world, basePos, 1, 6, 1, stone);
		addBlock(world, basePos, 4, 6, 4, stone);
		addBlock(world, basePos, 4, 6, 5, stone);
		addBlock(world, basePos, 5, 6, 4, stone);
		addBlock(world, basePos, 5, 6, 5, stone);
		addBlock(world, basePos, 6, 6, 4, stone);
		addBlock(world, basePos, 6, 6, 5, stone);
		addBlock(world, basePos, 7, 6, 4, stone);
		addBlock(world, basePos, 7, 6, 5, stone);
		addBlock(world, basePos, 8, 6, 4, stone);
		addBlock(world, basePos, 8, 6, 5, stone);
		addBlock(world, basePos, 9, 6, 4, stone);
		addBlock(world, basePos, 9, 6, 5, stone);
		addBlock(world, basePos, 10, 6, 4, stone);
		addBlock(world, basePos, 10, 6, 5, stone);
		addBlock(world, basePos, 13, 6, 0, stone);
		addBlock(world, basePos, 13, 6, 1, stone);
		addBlock(world, basePos, 14, 6, 0, stone);
		addBlock(world, basePos, 14, 6, 1, stone);
		addBlock(world, basePos, 0, 7, 0, decayedGlass);
		addBlock(world, basePos, 0, 7, 1, decayedGlass);
		addBlock(world, basePos, 1, 7, 0, decayedGlass);
		addBlock(world, basePos, 1, 7, 1, decayedGlass);
		addBlock(world, basePos, 4, 7, 4, decayedGlass);
		addBlock(world, basePos, 4, 7, 5, decayedGlass);
		addBlock(world, basePos, 5, 7, 4, decayedGlass);
		addBlock(world, basePos, 5, 7, 5, decayedGlass);
		addBlock(world, basePos, 6, 7, 4, decayedGlass);
		addBlock(world, basePos, 6, 7, 5, decayedGlass);
		addBlock(world, basePos, 7, 7, 4, decayedGlass);
		addBlock(world, basePos, 7, 7, 5, decayedGlass);
		addBlock(world, basePos, 8, 7, 4, decayedGlass);
		addBlock(world, basePos, 8, 7, 5, decayedGlass);
		addBlock(world, basePos, 9, 7, 4, decayedGlass);
		addBlock(world, basePos, 9, 7, 5, decayedGlass);
		addBlock(world, basePos, 10, 7, 4, decayedGlass);
		addBlock(world, basePos, 10, 7, 5, decayedGlass);
		addBlock(world, basePos, 13, 7, 0, decayedGlass);
		addBlock(world, basePos, 13, 7, 1, decayedGlass);
		addBlock(world, basePos, 14, 7, 0, decayedGlass);
		addBlock(world, basePos, 14, 7, 1, decayedGlass);
		addBlock(world, basePos, 0, 8, 0, stone);
		addBlock(world, basePos, 0, 8, 1, stone);
		addBlock(world, basePos, 1, 8, 0, stone);
		addBlock(world, basePos, 1, 8, 1, stone);
		addBlock(world, basePos, 4, 8, 4, stone);
		addBlock(world, basePos, 4, 8, 5, stone);
		addBlock(world, basePos, 5, 8, 4, stone);
		addBlock(world, basePos, 5, 8, 5, stone);
		addBlock(world, basePos, 6, 8, 4, stone);
		addBlock(world, basePos, 6, 8, 5, stone);
		addBlock(world, basePos, 7, 8, 4, stone);
		addBlock(world, basePos, 7, 8, 5, stone);
		addBlock(world, basePos, 8, 8, 4, stone);
		addBlock(world, basePos, 8, 8, 5, stone);
		addBlock(world, basePos, 9, 8, 4, stone);
		addBlock(world, basePos, 9, 8, 5, stone);
		addBlock(world, basePos, 10, 8, 4, stone);
		addBlock(world, basePos, 10, 8, 5, stone);
		addBlock(world, basePos, 13, 8, 0, stone);
		addBlock(world, basePos, 13, 8, 1, stone);
		addBlock(world, basePos, 14, 8, 0, stone);
		addBlock(world, basePos, 14, 8, 1, stone);
		addBlock(world, basePos, 0, 9, 0, decayedGlass);
		addBlock(world, basePos, 0, 9, 1, decayedGlass);
		addBlock(world, basePos, 1, 9, 0, decayedGlass);
		addBlock(world, basePos, 1, 9, 1, decayedGlass);
		addBlock(world, basePos, 4, 9, 4, decayedGlass);
		addBlock(world, basePos, 4, 9, 5, decayedGlass);
		addBlock(world, basePos, 5, 9, 4, decayedGlass);
		addBlock(world, basePos, 5, 9, 5, decayedGlass);
		addBlock(world, basePos, 7, 9, 4, stone);
		addBlock(world, basePos, 7, 9, 5, stone);
		addBlock(world, basePos, 9, 9, 4, decayedGlass);
		addBlock(world, basePos, 9, 9, 5, decayedGlass);
		addBlock(world, basePos, 10, 9, 4, decayedGlass);
		addBlock(world, basePos, 10, 9, 5, decayedGlass);
		addBlock(world, basePos, 13, 9, 0, decayedGlass);
		addBlock(world, basePos, 13, 9, 1, decayedGlass);
		addBlock(world, basePos, 14, 9, 0, decayedGlass);
		addBlock(world, basePos, 14, 9, 1, decayedGlass);
		addBlock(world, basePos, 0, 10, 0, stone);
		addBlock(world, basePos, 0, 10, 1, stone);
		addBlock(world, basePos, 1, 10, 0, stone);
		addBlock(world, basePos, 1, 10, 1, stone);
		addBlock(world, basePos, 4, 10, 4, stone);
		addBlock(world, basePos, 4, 10, 5, stone);
		addBlock(world, basePos, 5, 10, 4, stone);
		addBlock(world, basePos, 5, 10, 5, stone);
		addBlock(world, basePos, 7, 10, 4, decayedGlass);
		addBlock(world, basePos, 7, 10, 5, decayedGlass);
		addBlock(world, basePos, 9, 10, 4, stone);
		addBlock(world, basePos, 9, 10, 5, stone);
		addBlock(world, basePos, 10, 10, 4, stone);
		addBlock(world, basePos, 10, 10, 5, stone);
		addBlock(world, basePos, 13, 10, 0, stone);
		addBlock(world, basePos, 13, 10, 1, stone);
		addBlock(world, basePos, 14, 10, 0, stone);
		addBlock(world, basePos, 14, 10, 1, stone);
		addBlock(world, basePos, 0, 11, 0, decayedGlass);
		addBlock(world, basePos, 0, 11, 1, decayedGlass);
		addBlock(world, basePos, 1, 11, 0, decayedGlass);
		addBlock(world, basePos, 1, 11, 1, decayedGlass);
		addBlock(world, basePos, 4, 11, 4, decayedGlass);
		addBlock(world, basePos, 4, 11, 5, decayedGlass);
		addBlock(world, basePos, 5, 11, 4, decayedGlass);
		addBlock(world, basePos, 5, 11, 5, decayedGlass);
		addBlock(world, basePos, 7, 11, 4, stone);
		addBlock(world, basePos, 7, 11, 5, stone);
		addBlock(world, basePos, 9, 11, 4, decayedGlass);
		addBlock(world, basePos, 9, 11, 5, decayedGlass);
		addBlock(world, basePos, 10, 11, 4, decayedGlass);
		addBlock(world, basePos, 10, 11, 5, decayedGlass);
		addBlock(world, basePos, 13, 11, 0, decayedGlass);
		addBlock(world, basePos, 13, 11, 1, decayedGlass);
		addBlock(world, basePos, 14, 11, 0, decayedGlass);
		addBlock(world, basePos, 14, 11, 1, decayedGlass);
		addBlock(world, basePos, 0, 12, 0, stone);
		addBlock(world, basePos, 0, 12, 1, stone);
		addBlock(world, basePos, 1, 12, 0, stone);
		addBlock(world, basePos, 1, 12, 1, stone);
		addBlock(world, basePos, 4, 12, 4, stone);
		addBlock(world, basePos, 4, 12, 5, stone);
		addBlock(world, basePos, 5, 12, 4, stone);
		addBlock(world, basePos, 5, 12, 5, stone);
		addBlock(world, basePos, 7, 12, 4, decayedGlass);
		addBlock(world, basePos, 7, 12, 5, decayedGlass);
		addBlock(world, basePos, 9, 12, 4, stone);
		addBlock(world, basePos, 9, 12, 5, stone);
		addBlock(world, basePos, 10, 12, 4, stone);
		addBlock(world, basePos, 10, 12, 5, stone);
		addBlock(world, basePos, 13, 12, 0, stone);
		addBlock(world, basePos, 13, 12, 1, stone);
		addBlock(world, basePos, 14, 12, 0, stone);
		addBlock(world, basePos, 14, 12, 1, stone);
		addBlock(world, basePos, 0, 13, 0, decayedGlass);
		addBlock(world, basePos, 0, 13, 1, decayedGlass);
		addBlock(world, basePos, 1, 13, 0, decayedGlass);
		addBlock(world, basePos, 1, 13, 1, decayedGlass);
		addBlock(world, basePos, 4, 13, 4, decayedGlass);
		addBlock(world, basePos, 4, 13, 5, decayedGlass);
		addBlock(world, basePos, 5, 13, 4, decayedGlass);
		addBlock(world, basePos, 5, 13, 5, decayedGlass);
		addBlock(world, basePos, 6, 13, 4, stone);
		addBlock(world, basePos, 6, 13, 5, stone);
		addBlock(world, basePos, 7, 13, 4, stone);
		addBlock(world, basePos, 7, 13, 5, stone);
		addBlock(world, basePos, 8, 13, 4, stone);
		addBlock(world, basePos, 8, 13, 5, stone);
		addBlock(world, basePos, 9, 13, 4, decayedGlass);
		addBlock(world, basePos, 9, 13, 5, decayedGlass);
		addBlock(world, basePos, 10, 13, 4, decayedGlass);
		addBlock(world, basePos, 10, 13, 5, decayedGlass);
		addBlock(world, basePos, 13, 13, 0, decayedGlass);
		addBlock(world, basePos, 13, 13, 1, decayedGlass);
		addBlock(world, basePos, 14, 13, 0, decayedGlass);
		addBlock(world, basePos, 14, 13, 1, decayedGlass);
		addBlock(world, basePos, 0, 14, 0, stone);
		addBlock(world, basePos, 0, 14, 1, stone);
		addBlock(world, basePos, 1, 14, 0, stone);
		addBlock(world, basePos, 1, 14, 1, stone);
		addBlock(world, basePos, 4, 14, 4, stone);
		addBlock(world, basePos, 4, 14, 5, stone);
		addBlock(world, basePos, 5, 14, 4, stone);
		addBlock(world, basePos, 5, 14, 5, stone);
		addBlock(world, basePos, 9, 14, 4, stone);
		addBlock(world, basePos, 9, 14, 5, stone);
		addBlock(world, basePos, 10, 14, 4, stone);
		addBlock(world, basePos, 10, 14, 5, stone);
		addBlock(world, basePos, 13, 14, 0, stone);
		addBlock(world, basePos, 13, 14, 1, stone);
		addBlock(world, basePos, 14, 14, 0, stone);
		addBlock(world, basePos, 14, 14, 1, stone);
		addBlock(world, basePos, 0, 15, 0, decayedGlass);
		addBlock(world, basePos, 0, 15, 1, decayedGlass);
		addBlock(world, basePos, 1, 15, 0, decayedGlass);
		addBlock(world, basePos, 1, 15, 1, decayedGlass);
		addBlock(world, basePos, 4, 15, 4, decayedGlass);
		addBlock(world, basePos, 4, 15, 5, decayedGlass);
		addBlock(world, basePos, 5, 15, 4, decayedGlass);
		addBlock(world, basePos, 5, 15, 5, decayedGlass);
		addBlock(world, basePos, 9, 15, 4, decayedGlass);
		addBlock(world, basePos, 9, 15, 5, decayedGlass);
		addBlock(world, basePos, 10, 15, 4, decayedGlass);
		addBlock(world, basePos, 10, 15, 5, decayedGlass);
		addBlock(world, basePos, 13, 15, 0, decayedGlass);
		addBlock(world, basePos, 13, 15, 1, decayedGlass);
		addBlock(world, basePos, 14, 15, 0, decayedGlass);
		addBlock(world, basePos, 14, 15, 1, decayedGlass);
		addBlock(world, basePos, 0, 16, 0, stone);
		addBlock(world, basePos, 0, 16, 1, stone);
		addBlock(world, basePos, 1, 16, 0, stone);
		addBlock(world, basePos, 1, 16, 1, stone);
		addBlock(world, basePos, 4, 16, 4, stone);
		addBlock(world, basePos, 4, 16, 5, stone);
		addBlock(world, basePos, 5, 16, 4, stone);
		addBlock(world, basePos, 5, 16, 5, stone);
		addBlock(world, basePos, 9, 16, 4, stone);
		addBlock(world, basePos, 9, 16, 5, stone);
		addBlock(world, basePos, 10, 16, 4, stone);
		addBlock(world, basePos, 10, 16, 5, stone);
		addBlock(world, basePos, 13, 16, 0, stone);
		addBlock(world, basePos, 13, 16, 1, stone);
		addBlock(world, basePos, 14, 16, 0, stone);
		addBlock(world, basePos, 14, 16, 1, stone);
		addBlock(world, basePos, 0, 17, 0, decayedGlass);
		addBlock(world, basePos, 0, 17, 1, decayedGlass);
		addBlock(world, basePos, 1, 17, 0, decayedGlass);
		addBlock(world, basePos, 1, 17, 1, decayedGlass);
		addBlock(world, basePos, 4, 17, 4, decayedGlass);
		addBlock(world, basePos, 4, 17, 5, decayedGlass);
		addBlock(world, basePos, 5, 17, 4, decayedGlass);
		addBlock(world, basePos, 5, 17, 5, decayedGlass);
		addBlock(world, basePos, 9, 17, 4, decayedGlass);
		addBlock(world, basePos, 9, 17, 5, decayedGlass);
		addBlock(world, basePos, 10, 17, 4, decayedGlass);
		addBlock(world, basePos, 10, 17, 5, decayedGlass);
		addBlock(world, basePos, 13, 17, 0, decayedGlass);
		addBlock(world, basePos, 13, 17, 1, decayedGlass);
		addBlock(world, basePos, 14, 17, 0, decayedGlass);
		addBlock(world, basePos, 14, 17, 1, decayedGlass);
		addBlock(world, basePos, 0, 18, 0, stone);
		addBlock(world, basePos, 0, 18, 1, stone);
		addBlock(world, basePos, 1, 18, 0, stone);
		addBlock(world, basePos, 1, 18, 1, stone);
		addBlock(world, basePos, 4, 18, 4, stone);
		addBlock(world, basePos, 4, 18, 5, stone);
		addBlock(world, basePos, 5, 18, 4, stone);
		addBlock(world, basePos, 5, 18, 5, stone);
		addBlock(world, basePos, 9, 18, 4, stone);
		addBlock(world, basePos, 9, 18, 5, stone);
		addBlock(world, basePos, 10, 18, 4, stone);
		addBlock(world, basePos, 10, 18, 5, stone);
		addBlock(world, basePos, 13, 18, 0, stone);
		addBlock(world, basePos, 13, 18, 1, stone);
		addBlock(world, basePos, 14, 18, 0, stone);
		addBlock(world, basePos, 14, 18, 1, stone);
		addBlock(world, basePos, 0, 19, 0, decayedGlass);
		addBlock(world, basePos, 0, 19, 1, decayedGlass);
		addBlock(world, basePos, 1, 19, 0, decayedGlass);
		addBlock(world, basePos, 1, 19, 1, decayedGlass);
		addBlock(world, basePos, 4, 19, 4, decayedGlass);
		addBlock(world, basePos, 4, 19, 5, decayedGlass);
		addBlock(world, basePos, 5, 19, 4, decayedGlass);
		addBlock(world, basePos, 5, 19, 5, decayedGlass);
		addBlock(world, basePos, 9, 19, 4, decayedGlass);
		addBlock(world, basePos, 9, 19, 5, decayedGlass);
		addBlock(world, basePos, 10, 19, 4, decayedGlass);
		addBlock(world, basePos, 10, 19, 5, decayedGlass);
		addBlock(world, basePos, 13, 19, 0, decayedGlass);
		addBlock(world, basePos, 13, 19, 1, decayedGlass);
		addBlock(world, basePos, 14, 19, 0, decayedGlass);
		addBlock(world, basePos, 14, 19, 1, decayedGlass);
		addBlock(world, basePos, 0, 20, 0, stone);
		addBlock(world, basePos, 0, 20, 1, stone);
		addBlock(world, basePos, 1, 20, 0, stone);
		addBlock(world, basePos, 1, 20, 1, stone);
		addBlock(world, basePos, 4, 20, 4, stone);
		addBlock(world, basePos, 4, 20, 5, stone);
		addBlock(world, basePos, 5, 20, 4, stone);
		addBlock(world, basePos, 5, 20, 5, stone);
		addBlock(world, basePos, 9, 20, 4, stone);
		addBlock(world, basePos, 9, 20, 5, stone);
		addBlock(world, basePos, 10, 20, 4, stone);
		addBlock(world, basePos, 10, 20, 5, stone);
		addBlock(world, basePos, 13, 20, 0, stone);
		addBlock(world, basePos, 13, 20, 1, stone);
		addBlock(world, basePos, 14, 20, 0, stone);
		addBlock(world, basePos, 14, 20, 1, stone);
		addBlock(world, basePos, 0, 21, 0, decayedGlass);
		addBlock(world, basePos, 0, 21, 1, decayedGlass);
		addBlock(world, basePos, 1, 21, 0, decayedGlass);
		addBlock(world, basePos, 1, 21, 1, decayedGlass);
		addBlock(world, basePos, 4, 21, 4, decayedGlass);
		addBlock(world, basePos, 4, 21, 5, decayedGlass);
		addBlock(world, basePos, 5, 21, 4, decayedGlass);
		addBlock(world, basePos, 5, 21, 5, decayedGlass);
		addBlock(world, basePos, 9, 21, 4, decayedGlass);
		addBlock(world, basePos, 9, 21, 5, decayedGlass);
		addBlock(world, basePos, 10, 21, 4, decayedGlass);
		addBlock(world, basePos, 10, 21, 5, decayedGlass);
		addBlock(world, basePos, 13, 21, 0, decayedGlass);
		addBlock(world, basePos, 13, 21, 1, decayedGlass);
		addBlock(world, basePos, 14, 21, 0, decayedGlass);
		addBlock(world, basePos, 14, 21, 1, decayedGlass);
		addBlock(world, basePos, 0, 22, 0, stone);
		addBlock(world, basePos, 0, 22, 1, stone);
		addBlock(world, basePos, 1, 22, 0, stone);
		addBlock(world, basePos, 1, 22, 1, stone);
		addBlock(world, basePos, 4, 22, 4, stone);
		addBlock(world, basePos, 4, 22, 5, stone);
		addBlock(world, basePos, 5, 22, 4, stone);
		addBlock(world, basePos, 5, 22, 5, stone);
		addBlock(world, basePos, 9, 22, 4, stone);
		addBlock(world, basePos, 9, 22, 5, stone);
		addBlock(world, basePos, 10, 22, 4, stone);
		addBlock(world, basePos, 10, 22, 5, stone);
		addBlock(world, basePos, 13, 22, 0, stone);
		addBlock(world, basePos, 13, 22, 1, stone);
		addBlock(world, basePos, 14, 22, 0, stone);
		addBlock(world, basePos, 14, 22, 1, stone);
		addBlock(world, basePos, 0, 23, 0, decayedGlass);
		addBlock(world, basePos, 0, 23, 1, decayedGlass);
		addBlock(world, basePos, 1, 23, 0, decayedGlass);
		addBlock(world, basePos, 1, 23, 1, decayedGlass);
		addBlock(world, basePos, 4, 23, 4, decayedGlass);
		addBlock(world, basePos, 4, 23, 5, decayedGlass);
		addBlock(world, basePos, 5, 23, 4, decayedGlass);
		addBlock(world, basePos, 5, 23, 5, decayedGlass);
		addBlock(world, basePos, 9, 23, 4, decayedGlass);
		addBlock(world, basePos, 9, 23, 5, decayedGlass);
		addBlock(world, basePos, 10, 23, 4, decayedGlass);
		addBlock(world, basePos, 10, 23, 5, decayedGlass);
		addBlock(world, basePos, 13, 23, 0, decayedGlass);
		addBlock(world, basePos, 13, 23, 1, decayedGlass);
		addBlock(world, basePos, 14, 23, 0, decayedGlass);
		addBlock(world, basePos, 14, 23, 1, decayedGlass);
		addBlock(world, basePos, 0, 24, 0, stone);
		addBlock(world, basePos, 0, 24, 1, stone);
		addBlock(world, basePos, 1, 24, 0, stone);
		addBlock(world, basePos, 1, 24, 1, stone);
		addBlock(world, basePos, 4, 24, 4, stone);
		addBlock(world, basePos, 4, 24, 5, stone);
		addBlock(world, basePos, 5, 24, 4, stone);
		addBlock(world, basePos, 5, 24, 5, stone);
		addBlock(world, basePos, 9, 24, 4, stone);
		addBlock(world, basePos, 9, 24, 5, stone);
		addBlock(world, basePos, 10, 24, 4, stone);
		addBlock(world, basePos, 10, 24, 5, stone);
		addBlock(world, basePos, 13, 24, 0, stone);
		addBlock(world, basePos, 13, 24, 1, stone);
		addBlock(world, basePos, 14, 24, 0, stone);
		addBlock(world, basePos, 14, 24, 1, stone);
		addBlock(world, basePos, 0, 25, 0, decayedGlass);
		addBlock(world, basePos, 0, 25, 1, decayedGlass);
		addBlock(world, basePos, 1, 25, 0, decayedGlass);
		addBlock(world, basePos, 1, 25, 1, decayedGlass);
		addBlock(world, basePos, 4, 25, 4, decayedGlass);
		addBlock(world, basePos, 4, 25, 5, decayedGlass);
		addBlock(world, basePos, 5, 25, 4, decayedGlass);
		addBlock(world, basePos, 5, 25, 5, decayedGlass);
		addBlock(world, basePos, 9, 25, 4, decayedGlass);
		addBlock(world, basePos, 9, 25, 5, decayedGlass);
		addBlock(world, basePos, 10, 25, 4, decayedGlass);
		addBlock(world, basePos, 10, 25, 5, decayedGlass);
		addBlock(world, basePos, 13, 25, 0, decayedGlass);
		addBlock(world, basePos, 13, 25, 1, decayedGlass);
		addBlock(world, basePos, 14, 25, 0, decayedGlass);
		addBlock(world, basePos, 14, 25, 1, decayedGlass);
		addBlock(world, basePos, 0, 26, 0, stone);
		addBlock(world, basePos, 0, 26, 1, stone);
		addBlock(world, basePos, 1, 26, 0, stone);
		addBlock(world, basePos, 1, 26, 1, stone);
		addBlock(world, basePos, 4, 26, 4, stone);
		addBlock(world, basePos, 4, 26, 5, stone);
		addBlock(world, basePos, 5, 26, 4, stone);
		addBlock(world, basePos, 5, 26, 5, stone);
		addBlock(world, basePos, 9, 26, 4, stone);
		addBlock(world, basePos, 9, 26, 5, stone);
		addBlock(world, basePos, 10, 26, 4, stone);
		addBlock(world, basePos, 10, 26, 5, stone);
		addBlock(world, basePos, 13, 26, 0, stone);
		addBlock(world, basePos, 13, 26, 1, stone);
		addBlock(world, basePos, 14, 26, 0, stone);
		addBlock(world, basePos, 14, 26, 1, stone);
		addBlock(world, basePos, 0, 27, 0, decayedGlass);
		addBlock(world, basePos, 0, 27, 1, decayedGlass);
		addBlock(world, basePos, 1, 27, 0, decayedGlass);
		addBlock(world, basePos, 1, 27, 1, decayedGlass);
		addBlock(world, basePos, 4, 27, 4, decayedGlass);
		addBlock(world, basePos, 4, 27, 5, decayedGlass);
		addBlock(world, basePos, 5, 27, 4, decayedGlass);
		addBlock(world, basePos, 5, 27, 5, decayedGlass);
		addBlock(world, basePos, 9, 27, 4, decayedGlass);
		addBlock(world, basePos, 9, 27, 5, decayedGlass);
		addBlock(world, basePos, 10, 27, 4, decayedGlass);
		addBlock(world, basePos, 10, 27, 5, decayedGlass);
		addBlock(world, basePos, 13, 27, 0, decayedGlass);
		addBlock(world, basePos, 13, 27, 1, decayedGlass);
		addBlock(world, basePos, 14, 27, 0, decayedGlass);
		addBlock(world, basePos, 14, 27, 1, decayedGlass);
		addBlock(world, basePos, 0, 28, 0, stone);
		addBlock(world, basePos, 0, 28, 1, stone);
		addBlock(world, basePos, 1, 28, 0, stone);
		addBlock(world, basePos, 1, 28, 1, stone);
		addBlock(world, basePos, 4, 28, 4, stone);
		addBlock(world, basePos, 4, 28, 5, stone);
		addBlock(world, basePos, 5, 28, 4, stone);
		addBlock(world, basePos, 5, 28, 5, stone);
		addBlock(world, basePos, 9, 28, 4, stone);
		addBlock(world, basePos, 9, 28, 5, stone);
		addBlock(world, basePos, 10, 28, 4, stone);
		addBlock(world, basePos, 10, 28, 5, stone);
		addBlock(world, basePos, 13, 28, 0, stone);
		addBlock(world, basePos, 13, 28, 1, stone);
		addBlock(world, basePos, 14, 28, 0, stone);
		addBlock(world, basePos, 14, 28, 1, stone);
		addBlock(world, basePos, 0, 29, 0, decayedGlass);
		addBlock(world, basePos, 0, 29, 1, decayedGlass);
		addBlock(world, basePos, 1, 29, 0, decayedGlass);
		addBlock(world, basePos, 1, 29, 1, decayedGlass);
		addBlock(world, basePos, 4, 29, 4, decayedGlass);
		addBlock(world, basePos, 4, 29, 5, decayedGlass);
		addBlock(world, basePos, 5, 29, 4, decayedGlass);
		addBlock(world, basePos, 5, 29, 5, decayedGlass);
		addBlock(world, basePos, 9, 29, 4, decayedGlass);
		addBlock(world, basePos, 9, 29, 5, decayedGlass);
		addBlock(world, basePos, 10, 29, 4, decayedGlass);
		addBlock(world, basePos, 10, 29, 5, decayedGlass);
		addBlock(world, basePos, 13, 29, 0, decayedGlass);
		addBlock(world, basePos, 13, 29, 1, decayedGlass);
		addBlock(world, basePos, 14, 29, 0, decayedGlass);
		addBlock(world, basePos, 14, 29, 1, decayedGlass);
		addBlock(world, basePos, 0, 30, 0, stone);
		addBlock(world, basePos, 0, 30, 1, stone);
		addBlock(world, basePos, 1, 30, 0, stone);
		addBlock(world, basePos, 1, 30, 1, stone);
		addBlock(world, basePos, 4, 30, 4, stone);
		addBlock(world, basePos, 4, 30, 5, stone);
		addBlock(world, basePos, 5, 30, 4, stone);
		addBlock(world, basePos, 5, 30, 5, stone);
		addBlock(world, basePos, 9, 30, 4, stone);
		addBlock(world, basePos, 9, 30, 5, stone);
		addBlock(world, basePos, 10, 30, 4, stone);
		addBlock(world, basePos, 10, 30, 5, stone);
		addBlock(world, basePos, 13, 30, 0, stone);
		addBlock(world, basePos, 13, 30, 1, stone);
		addBlock(world, basePos, 14, 30, 0, stone);
		addBlock(world, basePos, 14, 30, 1, stone);
		addBlock(world, basePos, 0, 31, 0, decayedGlass);
		addBlock(world, basePos, 0, 31, 1, decayedGlass);
		addBlock(world, basePos, 1, 31, 0, decayedGlass);
		addBlock(world, basePos, 1, 31, 1, decayedGlass);
		addBlock(world, basePos, 4, 31, 4, decayedGlass);
		addBlock(world, basePos, 4, 31, 5, decayedGlass);
		addBlock(world, basePos, 5, 31, 4, decayedGlass);
		addBlock(world, basePos, 5, 31, 5, decayedGlass);
		addBlock(world, basePos, 9, 31, 4, decayedGlass);
		addBlock(world, basePos, 9, 31, 5, decayedGlass);
		addBlock(world, basePos, 10, 31, 4, decayedGlass);
		addBlock(world, basePos, 10, 31, 5, decayedGlass);
		addBlock(world, basePos, 13, 31, 0, decayedGlass);
		addBlock(world, basePos, 13, 31, 1, decayedGlass);
		addBlock(world, basePos, 14, 31, 0, decayedGlass);
		addBlock(world, basePos, 14, 31, 1, decayedGlass);
		addBlock(world, basePos, 0, 32, 0, stone);
		addBlock(world, basePos, 0, 32, 1, stone);
		addBlock(world, basePos, 1, 32, 0, stone);
		addBlock(world, basePos, 1, 32, 1, stone);
		addBlock(world, basePos, 4, 32, 4, stone);
		addBlock(world, basePos, 4, 32, 5, stone);
		addBlock(world, basePos, 5, 32, 4, stone);
		addBlock(world, basePos, 5, 32, 5, stone);
		addBlock(world, basePos, 9, 32, 4, stone);
		addBlock(world, basePos, 9, 32, 5, stone);
		addBlock(world, basePos, 10, 32, 4, stone);
		addBlock(world, basePos, 10, 32, 5, stone);
		addBlock(world, basePos, 13, 32, 0, stone);
		addBlock(world, basePos, 13, 32, 1, stone);
		addBlock(world, basePos, 14, 32, 0, stone);
		addBlock(world, basePos, 14, 32, 1, stone);
		addBlock(world, basePos, 0, 33, 0, decayedGlass);
		addBlock(world, basePos, 0, 33, 1, decayedGlass);
		addBlock(world, basePos, 1, 33, 0, decayedGlass);
		addBlock(world, basePos, 1, 33, 1, decayedGlass);
		addBlock(world, basePos, 4, 33, 4, decayedGlass);
		addBlock(world, basePos, 4, 33, 5, decayedGlass);
		addBlock(world, basePos, 5, 33, 4, decayedGlass);
		addBlock(world, basePos, 5, 33, 5, decayedGlass);
		addBlock(world, basePos, 9, 33, 4, decayedGlass);
		addBlock(world, basePos, 9, 33, 5, decayedGlass);
		addBlock(world, basePos, 10, 33, 4, decayedGlass);
		addBlock(world, basePos, 10, 33, 5, decayedGlass);
		addBlock(world, basePos, 13, 33, 0, decayedGlass);
		addBlock(world, basePos, 13, 33, 1, decayedGlass);
		addBlock(world, basePos, 14, 33, 0, decayedGlass);
		addBlock(world, basePos, 14, 33, 1, decayedGlass);
		addBlock(world, basePos, 0, 34, 0, stone);
		addBlock(world, basePos, 0, 34, 1, stone);
		addBlock(world, basePos, 1, 34, 0, stone);
		addBlock(world, basePos, 1, 34, 1, stone);
		addBlock(world, basePos, 4, 34, 4, stone);
		addBlock(world, basePos, 4, 34, 5, stone);
		addBlock(world, basePos, 5, 34, 4, stone);
		addBlock(world, basePos, 5, 34, 5, stone);
		addBlock(world, basePos, 9, 34, 4, stone);
		addBlock(world, basePos, 9, 34, 5, stone);
		addBlock(world, basePos, 10, 34, 4, stone);
		addBlock(world, basePos, 10, 34, 5, stone);
		addBlock(world, basePos, 13, 34, 0, stone);
		addBlock(world, basePos, 13, 34, 1, stone);
		addBlock(world, basePos, 14, 34, 0, stone);
		addBlock(world, basePos, 14, 34, 1, stone);
		addBlock(world, basePos, 0, 35, 0, decayedGlass);
		addBlock(world, basePos, 0, 35, 1, decayedGlass);
		addBlock(world, basePos, 1, 35, 0, decayedGlass);
		addBlock(world, basePos, 1, 35, 1, decayedGlass);
		addBlock(world, basePos, 4, 35, 4, decayedGlass);
		addBlock(world, basePos, 4, 35, 5, decayedGlass);
		addBlock(world, basePos, 5, 35, 4, decayedGlass);
		addBlock(world, basePos, 5, 35, 5, decayedGlass);
		addBlock(world, basePos, 9, 35, 4, decayedGlass);
		addBlock(world, basePos, 9, 35, 5, decayedGlass);
		addBlock(world, basePos, 10, 35, 4, decayedGlass);
		addBlock(world, basePos, 10, 35, 5, decayedGlass);
		addBlock(world, basePos, 13, 35, 0, decayedGlass);
		addBlock(world, basePos, 13, 35, 1, decayedGlass);
		addBlock(world, basePos, 14, 35, 0, decayedGlass);
		addBlock(world, basePos, 14, 35, 1, decayedGlass);
		addBlock(world, basePos, 0, 36, 0, stone);
		addBlock(world, basePos, 0, 36, 1, stone);
		addBlock(world, basePos, 1, 36, 0, stone);
		addBlock(world, basePos, 1, 36, 1, stone);
		addBlock(world, basePos, 4, 36, 4, stone);
		addBlock(world, basePos, 4, 36, 5, stone);
		addBlock(world, basePos, 5, 36, 4, stone);
		addBlock(world, basePos, 5, 36, 5, stone);
		addBlock(world, basePos, 9, 36, 4, stone);
		addBlock(world, basePos, 9, 36, 5, stone);
		addBlock(world, basePos, 10, 36, 4, stone);
		addBlock(world, basePos, 10, 36, 5, stone);
		addBlock(world, basePos, 13, 36, 0, stone);
		addBlock(world, basePos, 13, 36, 1, stone);
		addBlock(world, basePos, 14, 36, 0, stone);
		addBlock(world, basePos, 14, 36, 1, stone);
		addBlock(world, basePos, 0, 37, 0, decayedGlass);
		addBlock(world, basePos, 0, 37, 1, decayedGlass);
		addBlock(world, basePos, 1, 37, 0, decayedGlass);
		addBlock(world, basePos, 1, 37, 1, decayedGlass);
		addBlock(world, basePos, 4, 37, 4, decayedGlass);
		addBlock(world, basePos, 4, 37, 5, decayedGlass);
		addBlock(world, basePos, 5, 37, 4, decayedGlass);
		addBlock(world, basePos, 5, 37, 5, decayedGlass);
		addBlock(world, basePos, 9, 37, 4, decayedGlass);
		addBlock(world, basePos, 9, 37, 5, decayedGlass);
		addBlock(world, basePos, 10, 37, 4, decayedGlass);
		addBlock(world, basePos, 10, 37, 5, decayedGlass);
		addBlock(world, basePos, 13, 37, 0, decayedGlass);
		addBlock(world, basePos, 13, 37, 1, decayedGlass);
		addBlock(world, basePos, 14, 37, 0, decayedGlass);
		addBlock(world, basePos, 14, 37, 1, decayedGlass);
		addBlock(world, basePos, 0, 38, 0, stone);
		addBlock(world, basePos, 0, 38, 1, stone);
		addBlock(world, basePos, 1, 38, 0, stone);
		addBlock(world, basePos, 1, 38, 1, stone);
		addBlock(world, basePos, 4, 38, 4, stone);
		addBlock(world, basePos, 4, 38, 5, stone);
		addBlock(world, basePos, 5, 38, 4, stone);
		addBlock(world, basePos, 5, 38, 5, stone);
		addBlock(world, basePos, 9, 38, 4, stone);
		addBlock(world, basePos, 9, 38, 5, stone);
		addBlock(world, basePos, 10, 38, 4, stone);
		addBlock(world, basePos, 10, 38, 5, stone);
		addBlock(world, basePos, 13, 38, 0, stone);
		addBlock(world, basePos, 13, 38, 1, stone);
		addBlock(world, basePos, 14, 38, 0, stone);
		addBlock(world, basePos, 14, 38, 1, stone);
		addBlock(world, basePos, 0, 39, 0, decayedGlass);
		addBlock(world, basePos, 0, 39, 1, decayedGlass);
		addBlock(world, basePos, 1, 39, 0, decayedGlass);
		addBlock(world, basePos, 1, 39, 1, decayedGlass);
		addBlock(world, basePos, 4, 39, 4, decayedGlass);
		addBlock(world, basePos, 4, 39, 5, decayedGlass);
		addBlock(world, basePos, 5, 39, 4, decayedGlass);
		addBlock(world, basePos, 5, 39, 5, decayedGlass);
		addBlock(world, basePos, 9, 39, 4, decayedGlass);
		addBlock(world, basePos, 9, 39, 5, decayedGlass);
		addBlock(world, basePos, 10, 39, 4, decayedGlass);
		addBlock(world, basePos, 10, 39, 5, decayedGlass);
		addBlock(world, basePos, 13, 39, 0, decayedGlass);
		addBlock(world, basePos, 13, 39, 1, decayedGlass);
		addBlock(world, basePos, 14, 39, 0, decayedGlass);
		addBlock(world, basePos, 14, 39, 1, decayedGlass);
		addBlock(world, basePos, 0, 40, 0, stone);
		addBlock(world, basePos, 0, 40, 1, stone);
		addBlock(world, basePos, 1, 40, 0, stone);
		addBlock(world, basePos, 1, 40, 1, stone);
		addBlock(world, basePos, 4, 40, 4, stone);
		addBlock(world, basePos, 4, 40, 5, stone);
		addBlock(world, basePos, 5, 40, 4, stone);
		addBlock(world, basePos, 5, 40, 5, stone);
		addBlock(world, basePos, 9, 40, 4, stone);
		addBlock(world, basePos, 9, 40, 5, stone);
		addBlock(world, basePos, 10, 40, 4, stone);
		addBlock(world, basePos, 10, 40, 5, stone);
		addBlock(world, basePos, 13, 40, 0, stone);
		addBlock(world, basePos, 13, 40, 1, stone);
		addBlock(world, basePos, 14, 40, 0, stone);
		addBlock(world, basePos, 14, 40, 1, stone);
	}
}