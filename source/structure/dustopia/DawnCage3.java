package net.tslat.aoa3.structure.dustopia;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class DawnCage3 extends AoAStructure { //StructureSize: 11x11x3
	private static final IBlockState dawnwoodPlanks = BlockRegister.DAWNWOOD_PLANKS.getDefaultState();
	private static final IBlockState dawnwoodBars = BlockRegister.DAWNWOOD_BARS.getDefaultState();
	private static final IBlockState stone = BlockRegister.DUSTOPIA_STONE.getDefaultState();

	public DawnCage3() {
		super("DawnCage3");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 5, 0, 1, stone);
		addBlock(world, basePos, 5, 1, 1, stone);
		addBlock(world, basePos, 5, 2, 1, stone);
		addBlock(world, basePos, 5, 3, 1, stone);
		addBlock(world, basePos, 5, 4, 1, stone);
		addBlock(world, basePos, 0, 5, 0, dawnwoodPlanks);
		addBlock(world, basePos, 0, 5, 1, dawnwoodPlanks);
		addBlock(world, basePos, 0, 5, 2, dawnwoodPlanks);
		addBlock(world, basePos, 1, 5, 0, dawnwoodPlanks);
		addBlock(world, basePos, 1, 5, 1, dawnwoodPlanks);
		addBlock(world, basePos, 1, 5, 2, dawnwoodPlanks);
		addBlock(world, basePos, 2, 5, 0, dawnwoodPlanks);
		addBlock(world, basePos, 2, 5, 1, dawnwoodPlanks);
		addBlock(world, basePos, 2, 5, 2, dawnwoodPlanks);
		addBlock(world, basePos, 5, 5, 1, stone);
		addBlock(world, basePos, 8, 5, 0, dawnwoodPlanks);
		addBlock(world, basePos, 8, 5, 1, dawnwoodPlanks);
		addBlock(world, basePos, 8, 5, 2, dawnwoodPlanks);
		addBlock(world, basePos, 9, 5, 0, dawnwoodPlanks);
		addBlock(world, basePos, 9, 5, 1, dawnwoodPlanks);
		addBlock(world, basePos, 9, 5, 2, dawnwoodPlanks);
		addBlock(world, basePos, 10, 5, 0, dawnwoodPlanks);
		addBlock(world, basePos, 10, 5, 1, dawnwoodPlanks);
		addBlock(world, basePos, 10, 5, 2, dawnwoodPlanks);
		addBlock(world, basePos, 0, 6, 0, dawnwoodBars);
		addBlock(world, basePos, 0, 6, 1, dawnwoodBars);
		addBlock(world, basePos, 0, 6, 2, dawnwoodBars);
		addBlock(world, basePos, 1, 6, 0, dawnwoodBars);
		addBlock(world, basePos, 1, 6, 2, dawnwoodBars);
		addBlock(world, basePos, 2, 6, 0, dawnwoodBars);
		addBlock(world, basePos, 2, 6, 1, dawnwoodBars);
		addBlock(world, basePos, 2, 6, 2, dawnwoodBars);
		addBlock(world, basePos, 5, 6, 1, stone);
		addBlock(world, basePos, 8, 6, 0, dawnwoodBars);
		addBlock(world, basePos, 8, 6, 1, dawnwoodBars);
		addBlock(world, basePos, 8, 6, 2, dawnwoodBars);
		addBlock(world, basePos, 9, 6, 0, dawnwoodBars);
		addBlock(world, basePos, 9, 6, 2, dawnwoodBars);
		addBlock(world, basePos, 10, 6, 0, dawnwoodBars);
		addBlock(world, basePos, 10, 6, 1, dawnwoodBars);
		addBlock(world, basePos, 10, 6, 2, dawnwoodBars);
		addBlock(world, basePos, 0, 7, 0, dawnwoodBars);
		addBlock(world, basePos, 0, 7, 1, dawnwoodBars);
		addBlock(world, basePos, 0, 7, 2, dawnwoodBars);
		addBlock(world, basePos, 1, 7, 0, dawnwoodBars);
		addBlock(world, basePos, 1, 7, 2, dawnwoodBars);
		addBlock(world, basePos, 2, 7, 0, dawnwoodBars);
		addBlock(world, basePos, 2, 7, 1, dawnwoodBars);
		addBlock(world, basePos, 2, 7, 2, dawnwoodBars);
		addBlock(world, basePos, 5, 7, 1, stone);
		addBlock(world, basePos, 8, 7, 0, dawnwoodBars);
		addBlock(world, basePos, 8, 7, 1, dawnwoodBars);
		addBlock(world, basePos, 8, 7, 2, dawnwoodBars);
		addBlock(world, basePos, 9, 7, 0, dawnwoodBars);
		addBlock(world, basePos, 9, 7, 2, dawnwoodBars);
		addBlock(world, basePos, 10, 7, 0, dawnwoodBars);
		addBlock(world, basePos, 10, 7, 1, dawnwoodBars);
		addBlock(world, basePos, 10, 7, 2, dawnwoodBars);
		addBlock(world, basePos, 0, 8, 0, dawnwoodPlanks);
		addBlock(world, basePos, 0, 8, 1, dawnwoodPlanks);
		addBlock(world, basePos, 0, 8, 2, dawnwoodPlanks);
		addBlock(world, basePos, 1, 8, 0, dawnwoodPlanks);
		addBlock(world, basePos, 1, 8, 1, dawnwoodPlanks);
		addBlock(world, basePos, 1, 8, 2, dawnwoodPlanks);
		addBlock(world, basePos, 2, 8, 0, dawnwoodPlanks);
		addBlock(world, basePos, 2, 8, 1, dawnwoodPlanks);
		addBlock(world, basePos, 2, 8, 2, dawnwoodPlanks);
		addBlock(world, basePos, 5, 8, 1, stone);
		addBlock(world, basePos, 8, 8, 0, dawnwoodPlanks);
		addBlock(world, basePos, 8, 8, 1, dawnwoodPlanks);
		addBlock(world, basePos, 8, 8, 2, dawnwoodPlanks);
		addBlock(world, basePos, 9, 8, 0, dawnwoodPlanks);
		addBlock(world, basePos, 9, 8, 1, dawnwoodPlanks);
		addBlock(world, basePos, 9, 8, 2, dawnwoodPlanks);
		addBlock(world, basePos, 10, 8, 0, dawnwoodPlanks);
		addBlock(world, basePos, 10, 8, 1, dawnwoodPlanks);
		addBlock(world, basePos, 10, 8, 2, dawnwoodPlanks);
		addBlock(world, basePos, 1, 9, 1, stone);
		addBlock(world, basePos, 5, 9, 1, stone);
		addBlock(world, basePos, 9, 9, 1, stone);
		addBlock(world, basePos, 1, 10, 1, stone);
		addBlock(world, basePos, 2, 10, 1, stone);
		addBlock(world, basePos, 3, 10, 1, stone);
		addBlock(world, basePos, 4, 10, 1, stone);
		addBlock(world, basePos, 5, 10, 1, stone);
		addBlock(world, basePos, 6, 10, 1, stone);
		addBlock(world, basePos, 7, 10, 1, stone);
		addBlock(world, basePos, 8, 10, 1, stone);
		addBlock(world, basePos, 9, 10, 1, stone);
	}
}
