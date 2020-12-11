package net.tslat.aoa3.worldgen.structures.dustopia;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class DawnCage2 extends AoAStructure { //StructureSize: 3x11x11
	private static final BlockState dawnwoodPlanks = AoABlocks.DAWNWOOD_PLANKS.get().getDefaultState();
	private static final BlockState dawnwoodBars = AoABlocks.DAWNWOOD_BARS.get().getDefaultState();
	private static final BlockState stone = AoABlocks.DARKENED_ROCK.get().getDefaultState();

	public DawnCage2() {
		super("DawnCage2");
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 1, 0, 5, stone);
		addBlock(world, basePos, 1, 1, 5, stone);
		addBlock(world, basePos, 1, 2, 5, stone);
		addBlock(world, basePos, 1, 3, 5, stone);
		addBlock(world, basePos, 1, 4, 5, stone);
		addBlock(world, basePos, 0, 5, 0, dawnwoodPlanks);
		addBlock(world, basePos, 0, 5, 1, dawnwoodPlanks);
		addBlock(world, basePos, 0, 5, 2, dawnwoodPlanks);
		addBlock(world, basePos, 0, 5, 8, dawnwoodPlanks);
		addBlock(world, basePos, 0, 5, 9, dawnwoodPlanks);
		addBlock(world, basePos, 0, 5, 10, dawnwoodPlanks);
		addBlock(world, basePos, 1, 5, 0, dawnwoodPlanks);
		addBlock(world, basePos, 1, 5, 1, dawnwoodPlanks);
		addBlock(world, basePos, 1, 5, 2, dawnwoodPlanks);
		addBlock(world, basePos, 1, 5, 5, stone);
		addBlock(world, basePos, 1, 5, 8, dawnwoodPlanks);
		addBlock(world, basePos, 1, 5, 9, dawnwoodPlanks);
		addBlock(world, basePos, 1, 5, 10, dawnwoodPlanks);
		addBlock(world, basePos, 2, 5, 0, dawnwoodPlanks);
		addBlock(world, basePos, 2, 5, 1, dawnwoodPlanks);
		addBlock(world, basePos, 2, 5, 2, dawnwoodPlanks);
		addBlock(world, basePos, 2, 5, 8, dawnwoodPlanks);
		addBlock(world, basePos, 2, 5, 9, dawnwoodPlanks);
		addBlock(world, basePos, 2, 5, 10, dawnwoodPlanks);
		addBlock(world, basePos, 0, 6, 0, dawnwoodBars);
		addBlock(world, basePos, 0, 6, 1, dawnwoodBars);
		addBlock(world, basePos, 0, 6, 2, dawnwoodBars);
		addBlock(world, basePos, 0, 6, 8, dawnwoodBars);
		addBlock(world, basePos, 0, 6, 9, dawnwoodBars);
		addBlock(world, basePos, 0, 6, 10, dawnwoodBars);
		addBlock(world, basePos, 1, 6, 0, dawnwoodBars);
		addBlock(world, basePos, 1, 6, 2, dawnwoodBars);
		addBlock(world, basePos, 1, 6, 5, stone);
		addBlock(world, basePos, 1, 6, 8, dawnwoodBars);
		addBlock(world, basePos, 1, 6, 10, dawnwoodBars);
		addBlock(world, basePos, 2, 6, 0, dawnwoodBars);
		addBlock(world, basePos, 2, 6, 1, dawnwoodBars);
		addBlock(world, basePos, 2, 6, 2, dawnwoodBars);
		addBlock(world, basePos, 2, 6, 8, dawnwoodBars);
		addBlock(world, basePos, 2, 6, 9, dawnwoodBars);
		addBlock(world, basePos, 2, 6, 10, dawnwoodBars);
		addBlock(world, basePos, 0, 7, 0, dawnwoodBars);
		addBlock(world, basePos, 0, 7, 1, dawnwoodBars);
		addBlock(world, basePos, 0, 7, 2, dawnwoodBars);
		addBlock(world, basePos, 0, 7, 8, dawnwoodBars);
		addBlock(world, basePos, 0, 7, 9, dawnwoodBars);
		addBlock(world, basePos, 0, 7, 10, dawnwoodBars);
		addBlock(world, basePos, 1, 7, 0, dawnwoodBars);
		addBlock(world, basePos, 1, 7, 2, dawnwoodBars);
		addBlock(world, basePos, 1, 7, 5, stone);
		addBlock(world, basePos, 1, 7, 8, dawnwoodBars);
		addBlock(world, basePos, 1, 7, 10, dawnwoodBars);
		addBlock(world, basePos, 2, 7, 0, dawnwoodBars);
		addBlock(world, basePos, 2, 7, 1, dawnwoodBars);
		addBlock(world, basePos, 2, 7, 2, dawnwoodBars);
		addBlock(world, basePos, 2, 7, 8, dawnwoodBars);
		addBlock(world, basePos, 2, 7, 9, dawnwoodBars);
		addBlock(world, basePos, 2, 7, 10, dawnwoodBars);
		addBlock(world, basePos, 0, 8, 0, dawnwoodPlanks);
		addBlock(world, basePos, 0, 8, 1, dawnwoodPlanks);
		addBlock(world, basePos, 0, 8, 2, dawnwoodPlanks);
		addBlock(world, basePos, 0, 8, 8, dawnwoodPlanks);
		addBlock(world, basePos, 0, 8, 9, dawnwoodPlanks);
		addBlock(world, basePos, 0, 8, 10, dawnwoodPlanks);
		addBlock(world, basePos, 1, 8, 0, dawnwoodPlanks);
		addBlock(world, basePos, 1, 8, 1, dawnwoodPlanks);
		addBlock(world, basePos, 1, 8, 2, dawnwoodPlanks);
		addBlock(world, basePos, 1, 8, 5, stone);
		addBlock(world, basePos, 1, 8, 8, dawnwoodPlanks);
		addBlock(world, basePos, 1, 8, 9, dawnwoodPlanks);
		addBlock(world, basePos, 1, 8, 10, dawnwoodPlanks);
		addBlock(world, basePos, 2, 8, 0, dawnwoodPlanks);
		addBlock(world, basePos, 2, 8, 1, dawnwoodPlanks);
		addBlock(world, basePos, 2, 8, 2, dawnwoodPlanks);
		addBlock(world, basePos, 2, 8, 8, dawnwoodPlanks);
		addBlock(world, basePos, 2, 8, 9, dawnwoodPlanks);
		addBlock(world, basePos, 2, 8, 10, dawnwoodPlanks);
		addBlock(world, basePos, 1, 9, 1, stone);
		addBlock(world, basePos, 1, 9, 5, stone);
		addBlock(world, basePos, 1, 9, 9, stone);
		addBlock(world, basePos, 1, 10, 1, stone);
		addBlock(world, basePos, 1, 10, 2, stone);
		addBlock(world, basePos, 1, 10, 3, stone);
		addBlock(world, basePos, 1, 10, 4, stone);
		addBlock(world, basePos, 1, 10, 5, stone);
		addBlock(world, basePos, 1, 10, 6, stone);
		addBlock(world, basePos, 1, 10, 7, stone);
		addBlock(world, basePos, 1, 10, 8, stone);
		addBlock(world, basePos, 1, 10, 9, stone);
	}
}
