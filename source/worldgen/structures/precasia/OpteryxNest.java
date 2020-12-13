package net.tslat.aoa3.worldgen.structures.precasia;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class OpteryxNest extends AoAStructure { //StructureSize: 9x2x9
	private static final BlockState stranglewoodLog = AoABlocks.STRANGLEWOOD_LOG.get().getDefaultState();
	private static final BlockState cycadeLog = AoABlocks.STRANGLEWOOD_LOG.get().getDefaultState();
	private static final BlockState lucalusLog = AoABlocks.LUCALUS_LOG.get().getDefaultState();
	private static final BlockState opteryxSpawner = Blocks.SPAWNER.getDefaultState();

	public OpteryxNest() {
		super("OpteryxNest");
	}

	@Override
	protected boolean replacesBlocks() {
		return true;
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 1, 0, 1, cycadeLog);
		addBlock(world, basePos, 1, 0, 2, cycadeLog);
		addBlock(world, basePos, 1, 0, 3, cycadeLog);
		addBlock(world, basePos, 1, 0, 4, cycadeLog);
		addBlock(world, basePos, 1, 0, 5, cycadeLog);
		addBlock(world, basePos, 1, 0, 6, cycadeLog);
		addBlock(world, basePos, 1, 0, 7, cycadeLog);
		addBlock(world, basePos, 2, 0, 1, cycadeLog);
		addBlock(world, basePos, 2, 0, 2, lucalusLog);
		addBlock(world, basePos, 2, 0, 3, cycadeLog);
		addBlock(world, basePos, 2, 0, 4, cycadeLog);
		addBlock(world, basePos, 2, 0, 5, stranglewoodLog);
		addBlock(world, basePos, 2, 0, 6, cycadeLog);
		addBlock(world, basePos, 2, 0, 7, cycadeLog);
		addBlock(world, basePos, 3, 0, 1, cycadeLog);
		addBlock(world, basePos, 3, 0, 2, cycadeLog);
		addBlock(world, basePos, 3, 0, 3, stranglewoodLog);
		addBlock(world, basePos, 3, 0, 4, lucalusLog);
		addBlock(world, basePos, 3, 0, 5, cycadeLog);
		addBlock(world, basePos, 3, 0, 6, cycadeLog);
		addBlock(world, basePos, 3, 0, 7, cycadeLog);
		addBlock(world, basePos, 4, 0, 1, cycadeLog);
		addBlock(world, basePos, 4, 0, 2, cycadeLog);
		addBlock(world, basePos, 4, 0, 3, cycadeLog);
		addBlock(world, basePos, 4, 0, 4, cycadeLog);
		addBlock(world, basePos, 4, 0, 5, cycadeLog);
		addBlock(world, basePos, 4, 0, 6, lucalusLog);
		addBlock(world, basePos, 4, 0, 7, cycadeLog);
		addBlock(world, basePos, 5, 0, 1, cycadeLog);
		addBlock(world, basePos, 5, 0, 2, cycadeLog);
		addBlock(world, basePos, 5, 0, 3, lucalusLog);
		addBlock(world, basePos, 5, 0, 4, stranglewoodLog);
		addBlock(world, basePos, 5, 0, 5, cycadeLog);
		addBlock(world, basePos, 5, 0, 6, cycadeLog);
		addBlock(world, basePos, 5, 0, 7, cycadeLog);
		addBlock(world, basePos, 6, 0, 1, cycadeLog);
		addBlock(world, basePos, 6, 0, 2, cycadeLog);
		addBlock(world, basePos, 6, 0, 3, cycadeLog);
		addBlock(world, basePos, 6, 0, 4, cycadeLog);
		addBlock(world, basePos, 6, 0, 5, stranglewoodLog);
		addBlock(world, basePos, 6, 0, 6, cycadeLog);
		addBlock(world, basePos, 6, 0, 7, cycadeLog);
		addBlock(world, basePos, 7, 0, 1, cycadeLog);
		addBlock(world, basePos, 7, 0, 2, cycadeLog);
		addBlock(world, basePos, 7, 0, 3, cycadeLog);
		addBlock(world, basePos, 7, 0, 4, cycadeLog);
		addBlock(world, basePos, 7, 0, 5, cycadeLog);
		addBlock(world, basePos, 7, 0, 6, cycadeLog);
		addBlock(world, basePos, 7, 0, 7, cycadeLog);
		addBlock(world, basePos, 0, 1, 0, stranglewoodLog);
		addBlock(world, basePos, 0, 1, 1, cycadeLog);
		addBlock(world, basePos, 0, 1, 2, lucalusLog);
		addBlock(world, basePos, 0, 1, 3, stranglewoodLog);
		addBlock(world, basePos, 0, 1, 4, stranglewoodLog);
		addBlock(world, basePos, 0, 1, 5, cycadeLog);
		addBlock(world, basePos, 0, 1, 6, lucalusLog);
		addBlock(world, basePos, 0, 1, 7, stranglewoodLog);
		addBlock(world, basePos, 0, 1, 8, stranglewoodLog);
		addBlock(world, basePos, 1, 1, 0, stranglewoodLog);
		addBlock(world, basePos, 1, 1, 8, cycadeLog);
		addBlock(world, basePos, 2, 1, 0, lucalusLog);
		addBlock(world, basePos, 2, 1, 8, lucalusLog);
		addBlock(world, basePos, 3, 1, 0, cycadeLog);
		addBlock(world, basePos, 3, 1, 8, stranglewoodLog);
		addBlock(world, basePos, 4, 1, 0, stranglewoodLog);
		addBlock(world, basePos, 4, 1, 4, opteryxSpawner);
		addBlock(world, basePos, 4, 1, 8, stranglewoodLog);
		addBlock(world, basePos, 5, 1, 0, stranglewoodLog);
		addBlock(world, basePos, 5, 1, 8, cycadeLog);
		addBlock(world, basePos, 6, 1, 0, lucalusLog);
		addBlock(world, basePos, 6, 1, 8, lucalusLog);
		addBlock(world, basePos, 7, 1, 0, cycadeLog);
		addBlock(world, basePos, 7, 1, 8, stranglewoodLog);
		addBlock(world, basePos, 8, 1, 0, stranglewoodLog);
		addBlock(world, basePos, 8, 1, 1, stranglewoodLog);
		addBlock(world, basePos, 8, 1, 2, lucalusLog);
		addBlock(world, basePos, 8, 1, 3, cycadeLog);
		addBlock(world, basePos, 8, 1, 4, stranglewoodLog);
		addBlock(world, basePos, 8, 1, 5, stranglewoodLog);
		addBlock(world, basePos, 8, 1, 6, lucalusLog);
		addBlock(world, basePos, 8, 1, 7, cycadeLog);
		addBlock(world, basePos, 8, 1, 8, stranglewoodLog);
	}

	@Override
	protected void doPostBuildOps(IWorld world, Random rand, BlockPos basePos) {
		initSpawner(world, basePos.add(4, 1, 4), AoAEntities.Mobs.OPTERYX.get());
	}
}
