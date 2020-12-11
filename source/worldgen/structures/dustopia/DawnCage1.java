package net.tslat.aoa3.worldgen.structures.dustopia;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class DawnCage1 extends AoAStructure { //StructureSize: 5x9x5
	private static final BlockState dawnwoodPlanks = AoABlocks.DAWNWOOD_PLANKS.get().getDefaultState();
	private static final BlockState dawnwoodBars = AoABlocks.DAWNWOOD_BARS.get().getDefaultState();
	private static final BlockState stone = AoABlocks.DARKENED_ROCK.get().getDefaultState();

	public DawnCage1() {
		super("DawnCage1");
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 2, 0, 2, stone);
		addBlock(world, basePos, 2, 1, 2, stone);
		addBlock(world, basePos, 2, 2, 2, stone);
		addBlock(world, basePos, 2, 3, 2, stone);
		addBlock(world, basePos, 0, 4, 0, dawnwoodPlanks);
		addBlock(world, basePos, 0, 4, 1, dawnwoodPlanks);
		addBlock(world, basePos, 0, 4, 2, dawnwoodPlanks);
		addBlock(world, basePos, 0, 4, 3, dawnwoodPlanks);
		addBlock(world, basePos, 0, 4, 4, dawnwoodPlanks);
		addBlock(world, basePos, 1, 4, 0, dawnwoodPlanks);
		addBlock(world, basePos, 1, 4, 1, dawnwoodPlanks);
		addBlock(world, basePos, 1, 4, 2, dawnwoodPlanks);
		addBlock(world, basePos, 1, 4, 3, dawnwoodPlanks);
		addBlock(world, basePos, 1, 4, 4, dawnwoodPlanks);
		addBlock(world, basePos, 2, 4, 0, dawnwoodPlanks);
		addBlock(world, basePos, 2, 4, 1, dawnwoodPlanks);
		addBlock(world, basePos, 2, 4, 2, dawnwoodPlanks);
		addBlock(world, basePos, 2, 4, 3, dawnwoodPlanks);
		addBlock(world, basePos, 2, 4, 4, dawnwoodPlanks);
		addBlock(world, basePos, 3, 4, 0, dawnwoodPlanks);
		addBlock(world, basePos, 3, 4, 1, dawnwoodPlanks);
		addBlock(world, basePos, 3, 4, 2, dawnwoodPlanks);
		addBlock(world, basePos, 3, 4, 3, dawnwoodPlanks);
		addBlock(world, basePos, 3, 4, 4, dawnwoodPlanks);
		addBlock(world, basePos, 4, 4, 0, dawnwoodPlanks);
		addBlock(world, basePos, 4, 4, 1, dawnwoodPlanks);
		addBlock(world, basePos, 4, 4, 2, dawnwoodPlanks);
		addBlock(world, basePos, 4, 4, 3, dawnwoodPlanks);
		addBlock(world, basePos, 4, 4, 4, dawnwoodPlanks);
		addBlock(world, basePos, 0, 5, 0, dawnwoodBars);
		addBlock(world, basePos, 0, 5, 1, dawnwoodBars);
		addBlock(world, basePos, 0, 5, 2, dawnwoodBars);
		addBlock(world, basePos, 0, 5, 3, dawnwoodBars);
		addBlock(world, basePos, 0, 5, 4, dawnwoodBars);
		addBlock(world, basePos, 1, 5, 0, dawnwoodBars);
		addBlock(world, basePos, 1, 5, 4, dawnwoodBars);
		addBlock(world, basePos, 2, 5, 0, dawnwoodBars);
		addBlock(world, basePos, 2, 5, 4, dawnwoodBars);
		addBlock(world, basePos, 3, 5, 0, dawnwoodBars);
		addBlock(world, basePos, 3, 5, 4, dawnwoodBars);
		addBlock(world, basePos, 4, 5, 0, dawnwoodBars);
		addBlock(world, basePos, 4, 5, 1, dawnwoodBars);
		addBlock(world, basePos, 4, 5, 2, dawnwoodBars);
		addBlock(world, basePos, 4, 5, 3, dawnwoodBars);
		addBlock(world, basePos, 4, 5, 4, dawnwoodBars);
		addBlock(world, basePos, 0, 6, 0, dawnwoodBars);
		addBlock(world, basePos, 0, 6, 1, dawnwoodBars);
		addBlock(world, basePos, 0, 6, 2, dawnwoodBars);
		addBlock(world, basePos, 0, 6, 3, dawnwoodBars);
		addBlock(world, basePos, 0, 6, 4, dawnwoodBars);
		addBlock(world, basePos, 1, 6, 0, dawnwoodBars);
		addBlock(world, basePos, 1, 6, 4, dawnwoodBars);
		addBlock(world, basePos, 2, 6, 0, dawnwoodBars);
		addBlock(world, basePos, 2, 6, 4, dawnwoodBars);
		addBlock(world, basePos, 3, 6, 0, dawnwoodBars);
		addBlock(world, basePos, 3, 6, 4, dawnwoodBars);
		addBlock(world, basePos, 4, 6, 0, dawnwoodBars);
		addBlock(world, basePos, 4, 6, 1, dawnwoodBars);
		addBlock(world, basePos, 4, 6, 2, dawnwoodBars);
		addBlock(world, basePos, 4, 6, 3, dawnwoodBars);
		addBlock(world, basePos, 4, 6, 4, dawnwoodBars);
		addBlock(world, basePos, 0, 7, 0, dawnwoodBars);
		addBlock(world, basePos, 0, 7, 1, dawnwoodBars);
		addBlock(world, basePos, 0, 7, 2, dawnwoodBars);
		addBlock(world, basePos, 0, 7, 3, dawnwoodBars);
		addBlock(world, basePos, 0, 7, 4, dawnwoodBars);
		addBlock(world, basePos, 1, 7, 0, dawnwoodBars);
		addBlock(world, basePos, 1, 7, 4, dawnwoodBars);
		addBlock(world, basePos, 2, 7, 0, dawnwoodBars);
		addBlock(world, basePos, 2, 7, 4, dawnwoodBars);
		addBlock(world, basePos, 3, 7, 0, dawnwoodBars);
		addBlock(world, basePos, 3, 7, 4, dawnwoodBars);
		addBlock(world, basePos, 4, 7, 0, dawnwoodBars);
		addBlock(world, basePos, 4, 7, 1, dawnwoodBars);
		addBlock(world, basePos, 4, 7, 2, dawnwoodBars);
		addBlock(world, basePos, 4, 7, 3, dawnwoodBars);
		addBlock(world, basePos, 4, 7, 4, dawnwoodBars);
		addBlock(world, basePos, 0, 8, 0, dawnwoodPlanks);
		addBlock(world, basePos, 0, 8, 1, dawnwoodPlanks);
		addBlock(world, basePos, 0, 8, 2, dawnwoodPlanks);
		addBlock(world, basePos, 0, 8, 3, dawnwoodPlanks);
		addBlock(world, basePos, 0, 8, 4, dawnwoodPlanks);
		addBlock(world, basePos, 1, 8, 0, dawnwoodPlanks);
		addBlock(world, basePos, 1, 8, 1, dawnwoodPlanks);
		addBlock(world, basePos, 1, 8, 2, dawnwoodPlanks);
		addBlock(world, basePos, 1, 8, 3, dawnwoodPlanks);
		addBlock(world, basePos, 1, 8, 4, dawnwoodPlanks);
		addBlock(world, basePos, 2, 8, 0, dawnwoodPlanks);
		addBlock(world, basePos, 2, 8, 1, dawnwoodPlanks);
		addBlock(world, basePos, 2, 8, 2, dawnwoodPlanks);
		addBlock(world, basePos, 2, 8, 3, dawnwoodPlanks);
		addBlock(world, basePos, 2, 8, 4, dawnwoodPlanks);
		addBlock(world, basePos, 3, 8, 0, dawnwoodPlanks);
		addBlock(world, basePos, 3, 8, 1, dawnwoodPlanks);
		addBlock(world, basePos, 3, 8, 2, dawnwoodPlanks);
		addBlock(world, basePos, 3, 8, 3, dawnwoodPlanks);
		addBlock(world, basePos, 3, 8, 4, dawnwoodPlanks);
		addBlock(world, basePos, 4, 8, 0, dawnwoodPlanks);
		addBlock(world, basePos, 4, 8, 1, dawnwoodPlanks);
		addBlock(world, basePos, 4, 8, 2, dawnwoodPlanks);
		addBlock(world, basePos, 4, 8, 3, dawnwoodPlanks);
		addBlock(world, basePos, 4, 8, 4, dawnwoodPlanks);
	}
}
