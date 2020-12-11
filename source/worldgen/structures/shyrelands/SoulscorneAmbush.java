package net.tslat.aoa3.worldgen.structures.shyrelands;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class SoulscorneAmbush extends AoAStructure { //StructureSize: 14x6x14
	private static final BlockState whiteShyreBricks = AoABlocks.WHITE_SHYRE_BRICKS.get().getDefaultState();
	private static final BlockState yellowShyreBricks = AoABlocks.YELLOW_SHYRE_BRICKS.get().getDefaultState();
	private static final BlockState shyreLeaves = AoABlocks.SHYRE_LEAVES.get().getDefaultState();
	private static final BlockState soulscorneSpawner = Blocks.SPAWNER.getDefaultState();

	public SoulscorneAmbush() {
		super("SoulscorneAmbush");
	}

	@Override
	protected boolean replacesBlocks() {
		return true;
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 0, yellowShyreBricks);
		addBlock(world, basePos, 0, 0, 1, whiteShyreBricks);
		addBlock(world, basePos, 0, 0, 2, whiteShyreBricks);
		addBlock(world, basePos, 0, 0, 3, whiteShyreBricks);
		addBlock(world, basePos, 0, 0, 4, yellowShyreBricks);
		addBlock(world, basePos, 0, 0, 9, yellowShyreBricks);
		addBlock(world, basePos, 0, 0, 10, whiteShyreBricks);
		addBlock(world, basePos, 0, 0, 11, whiteShyreBricks);
		addBlock(world, basePos, 0, 0, 12, whiteShyreBricks);
		addBlock(world, basePos, 0, 0, 13, yellowShyreBricks);
		addBlock(world, basePos, 1, 0, 0, whiteShyreBricks);
		addBlock(world, basePos, 1, 0, 13, whiteShyreBricks);
		addBlock(world, basePos, 2, 0, 0, whiteShyreBricks);
		addBlock(world, basePos, 2, 0, 13, whiteShyreBricks);
		addBlock(world, basePos, 3, 0, 0, whiteShyreBricks);
		addBlock(world, basePos, 3, 0, 13, whiteShyreBricks);
		addBlock(world, basePos, 4, 0, 0, yellowShyreBricks);
		addBlock(world, basePos, 4, 0, 13, yellowShyreBricks);
		addBlock(world, basePos, 6, 0, 6, soulscorneSpawner);
		addBlock(world, basePos, 6, 0, 7, soulscorneSpawner);
		addBlock(world, basePos, 7, 0, 6, soulscorneSpawner);
		addBlock(world, basePos, 7, 0, 7, soulscorneSpawner);
		addBlock(world, basePos, 9, 0, 0, yellowShyreBricks);
		addBlock(world, basePos, 9, 0, 13, yellowShyreBricks);
		addBlock(world, basePos, 10, 0, 0, whiteShyreBricks);
		addBlock(world, basePos, 10, 0, 13, whiteShyreBricks);
		addBlock(world, basePos, 11, 0, 0, whiteShyreBricks);
		addBlock(world, basePos, 11, 0, 13, whiteShyreBricks);
		addBlock(world, basePos, 12, 0, 0, whiteShyreBricks);
		addBlock(world, basePos, 12, 0, 13, whiteShyreBricks);
		addBlock(world, basePos, 13, 0, 0, yellowShyreBricks);
		addBlock(world, basePos, 13, 0, 1, whiteShyreBricks);
		addBlock(world, basePos, 13, 0, 2, whiteShyreBricks);
		addBlock(world, basePos, 13, 0, 3, whiteShyreBricks);
		addBlock(world, basePos, 13, 0, 4, yellowShyreBricks);
		addBlock(world, basePos, 13, 0, 9, yellowShyreBricks);
		addBlock(world, basePos, 13, 0, 10, whiteShyreBricks);
		addBlock(world, basePos, 13, 0, 11, whiteShyreBricks);
		addBlock(world, basePos, 13, 0, 12, whiteShyreBricks);
		addBlock(world, basePos, 13, 0, 13, yellowShyreBricks);
		addBlock(world, basePos, 0, 1, 0, yellowShyreBricks);
		addBlock(world, basePos, 0, 1, 1, shyreLeaves);
		addBlock(world, basePos, 0, 1, 2, shyreLeaves);
		addBlock(world, basePos, 0, 1, 3, shyreLeaves);
		addBlock(world, basePos, 0, 1, 4, yellowShyreBricks);
		addBlock(world, basePos, 0, 1, 9, yellowShyreBricks);
		addBlock(world, basePos, 0, 1, 10, shyreLeaves);
		addBlock(world, basePos, 0, 1, 11, shyreLeaves);
		addBlock(world, basePos, 0, 1, 12, shyreLeaves);
		addBlock(world, basePos, 0, 1, 13, yellowShyreBricks);
		addBlock(world, basePos, 1, 1, 0, shyreLeaves);
		addBlock(world, basePos, 1, 1, 13, shyreLeaves);
		addBlock(world, basePos, 2, 1, 0, shyreLeaves);
		addBlock(world, basePos, 2, 1, 13, shyreLeaves);
		addBlock(world, basePos, 3, 1, 0, shyreLeaves);
		addBlock(world, basePos, 3, 1, 13, shyreLeaves);
		addBlock(world, basePos, 4, 1, 0, yellowShyreBricks);
		addBlock(world, basePos, 4, 1, 13, yellowShyreBricks);
		addBlock(world, basePos, 9, 1, 0, yellowShyreBricks);
		addBlock(world, basePos, 9, 1, 13, yellowShyreBricks);
		addBlock(world, basePos, 10, 1, 0, shyreLeaves);
		addBlock(world, basePos, 10, 1, 13, shyreLeaves);
		addBlock(world, basePos, 11, 1, 0, shyreLeaves);
		addBlock(world, basePos, 11, 1, 13, shyreLeaves);
		addBlock(world, basePos, 12, 1, 0, shyreLeaves);
		addBlock(world, basePos, 12, 1, 13, shyreLeaves);
		addBlock(world, basePos, 13, 1, 0, yellowShyreBricks);
		addBlock(world, basePos, 13, 1, 1, shyreLeaves);
		addBlock(world, basePos, 13, 1, 2, shyreLeaves);
		addBlock(world, basePos, 13, 1, 3, shyreLeaves);
		addBlock(world, basePos, 13, 1, 4, yellowShyreBricks);
		addBlock(world, basePos, 13, 1, 9, yellowShyreBricks);
		addBlock(world, basePos, 13, 1, 10, shyreLeaves);
		addBlock(world, basePos, 13, 1, 11, shyreLeaves);
		addBlock(world, basePos, 13, 1, 12, shyreLeaves);
		addBlock(world, basePos, 13, 1, 13, yellowShyreBricks);
		addBlock(world, basePos, 0, 2, 0, yellowShyreBricks);
		addBlock(world, basePos, 0, 2, 1, shyreLeaves);
		addBlock(world, basePos, 0, 2, 2, shyreLeaves);
		addBlock(world, basePos, 0, 2, 3, shyreLeaves);
		addBlock(world, basePos, 0, 2, 4, yellowShyreBricks);
		addBlock(world, basePos, 0, 2, 9, yellowShyreBricks);
		addBlock(world, basePos, 0, 2, 10, shyreLeaves);
		addBlock(world, basePos, 0, 2, 11, shyreLeaves);
		addBlock(world, basePos, 0, 2, 12, shyreLeaves);
		addBlock(world, basePos, 0, 2, 13, yellowShyreBricks);
		addBlock(world, basePos, 1, 2, 0, shyreLeaves);
		addBlock(world, basePos, 1, 2, 13, shyreLeaves);
		addBlock(world, basePos, 2, 2, 0, shyreLeaves);
		addBlock(world, basePos, 2, 2, 13, shyreLeaves);
		addBlock(world, basePos, 3, 2, 0, shyreLeaves);
		addBlock(world, basePos, 3, 2, 13, shyreLeaves);
		addBlock(world, basePos, 4, 2, 0, yellowShyreBricks);
		addBlock(world, basePos, 4, 2, 13, yellowShyreBricks);
		addBlock(world, basePos, 9, 2, 0, yellowShyreBricks);
		addBlock(world, basePos, 9, 2, 13, yellowShyreBricks);
		addBlock(world, basePos, 10, 2, 0, shyreLeaves);
		addBlock(world, basePos, 10, 2, 13, shyreLeaves);
		addBlock(world, basePos, 11, 2, 0, shyreLeaves);
		addBlock(world, basePos, 11, 2, 13, shyreLeaves);
		addBlock(world, basePos, 12, 2, 0, shyreLeaves);
		addBlock(world, basePos, 12, 2, 13, shyreLeaves);
		addBlock(world, basePos, 13, 2, 0, yellowShyreBricks);
		addBlock(world, basePos, 13, 2, 1, shyreLeaves);
		addBlock(world, basePos, 13, 2, 2, shyreLeaves);
		addBlock(world, basePos, 13, 2, 3, shyreLeaves);
		addBlock(world, basePos, 13, 2, 4, yellowShyreBricks);
		addBlock(world, basePos, 13, 2, 9, yellowShyreBricks);
		addBlock(world, basePos, 13, 2, 10, shyreLeaves);
		addBlock(world, basePos, 13, 2, 11, shyreLeaves);
		addBlock(world, basePos, 13, 2, 12, shyreLeaves);
		addBlock(world, basePos, 13, 2, 13, yellowShyreBricks);
		addBlock(world, basePos, 0, 3, 2, yellowShyreBricks);
		addBlock(world, basePos, 0, 3, 11, yellowShyreBricks);
		addBlock(world, basePos, 2, 3, 0, yellowShyreBricks);
		addBlock(world, basePos, 2, 3, 13, yellowShyreBricks);
		addBlock(world, basePos, 11, 3, 0, yellowShyreBricks);
		addBlock(world, basePos, 11, 3, 13, yellowShyreBricks);
		addBlock(world, basePos, 13, 3, 2, yellowShyreBricks);
		addBlock(world, basePos, 13, 3, 11, yellowShyreBricks);
		addBlock(world, basePos, 0, 4, 2, yellowShyreBricks);
		addBlock(world, basePos, 0, 4, 11, yellowShyreBricks);
		addBlock(world, basePos, 2, 4, 0, yellowShyreBricks);
		addBlock(world, basePos, 2, 4, 13, yellowShyreBricks);
		addBlock(world, basePos, 11, 4, 0, yellowShyreBricks);
		addBlock(world, basePos, 11, 4, 13, yellowShyreBricks);
		addBlock(world, basePos, 13, 4, 2, yellowShyreBricks);
		addBlock(world, basePos, 13, 4, 11, yellowShyreBricks);
		addBlock(world, basePos, 0, 5, 2, yellowShyreBricks);
		addBlock(world, basePos, 0, 5, 11, yellowShyreBricks);
		addBlock(world, basePos, 1, 5, 2, yellowShyreBricks);
		addBlock(world, basePos, 1, 5, 11, yellowShyreBricks);
		addBlock(world, basePos, 2, 5, 0, yellowShyreBricks);
		addBlock(world, basePos, 2, 5, 1, yellowShyreBricks);
		addBlock(world, basePos, 2, 5, 2, yellowShyreBricks);
		addBlock(world, basePos, 2, 5, 11, yellowShyreBricks);
		addBlock(world, basePos, 2, 5, 12, yellowShyreBricks);
		addBlock(world, basePos, 2, 5, 13, yellowShyreBricks);
		addBlock(world, basePos, 11, 5, 0, yellowShyreBricks);
		addBlock(world, basePos, 11, 5, 1, yellowShyreBricks);
		addBlock(world, basePos, 11, 5, 2, yellowShyreBricks);
		addBlock(world, basePos, 11, 5, 11, yellowShyreBricks);
		addBlock(world, basePos, 11, 5, 12, yellowShyreBricks);
		addBlock(world, basePos, 11, 5, 13, yellowShyreBricks);
		addBlock(world, basePos, 12, 5, 2, yellowShyreBricks);
		addBlock(world, basePos, 12, 5, 11, yellowShyreBricks);
		addBlock(world, basePos, 13, 5, 2, yellowShyreBricks);
		addBlock(world, basePos, 13, 5, 11, yellowShyreBricks);
	}

	@Override
	protected void doPostBuildOps(IWorld world, Random rand, BlockPos basePos) {
		initSpawner(world, basePos.add(6, 0, 6), AoAEntities.Mobs.SOULSCORNE.get());
		initSpawner(world, basePos.add(6, 0, 7), AoAEntities.Mobs.SOULSCORNE.get());
		initSpawner(world, basePos.add(7, 0, 6), AoAEntities.Mobs.SOULSCORNE.get());
		initSpawner(world, basePos.add(7, 0, 7), AoAEntities.Mobs.SOULSCORNE.get());
	}
}
