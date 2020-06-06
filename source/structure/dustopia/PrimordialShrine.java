package net.tslat.aoa3.structure.dustopia;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class PrimordialShrine extends AoAStructure { //StructureSize: 5x7x11
	private static final IBlockState dustopianLamp = BlockRegister.DUSTOPIAN_LAMP_OFF.getDefaultState();
	private static final IBlockState dawnwoodPlanks = BlockRegister.DAWNWOOD_PLANKS.getDefaultState();
	private static final IBlockState dawnwoodBars = BlockRegister.DAWNWOOD_BARS.getDefaultState();
	private static final IBlockState stone = BlockRegister.DUSTOPIA_STONE.getDefaultState();
	private static final IBlockState primordialShrine = BlockRegister.PRIMORDIAL_SHRINE.getDefaultState();

	public PrimordialShrine() {
		super("PrimordialShrine");
	}

	@Override
	protected boolean replacesBlocks() {
		return true;
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 0, stone);
		addBlock(world, basePos, 0, 0, 1, stone);
		addBlock(world, basePos, 0, 0, 2, stone);
		addBlock(world, basePos, 0, 0, 3, stone);
		addBlock(world, basePos, 0, 0, 4, stone);
		addBlock(world, basePos, 0, 0, 5, stone);
		addBlock(world, basePos, 0, 0, 6, stone);
		addBlock(world, basePos, 0, 0, 7, stone);
		addBlock(world, basePos, 0, 0, 8, stone);
		addBlock(world, basePos, 0, 0, 9, stone);
		addBlock(world, basePos, 0, 0, 10, stone);
		addBlock(world, basePos, 1, 0, 0, stone);
		addBlock(world, basePos, 1, 0, 1, stone);
		addBlock(world, basePos, 1, 0, 2, stone);
		addBlock(world, basePos, 1, 0, 3, stone);
		addBlock(world, basePos, 1, 0, 4, stone);
		addBlock(world, basePos, 1, 0, 5, stone);
		addBlock(world, basePos, 1, 0, 6, stone);
		addBlock(world, basePos, 1, 0, 7, stone);
		addBlock(world, basePos, 1, 0, 8, stone);
		addBlock(world, basePos, 1, 0, 9, stone);
		addBlock(world, basePos, 1, 0, 10, stone);
		addBlock(world, basePos, 2, 0, 0, stone);
		addBlock(world, basePos, 2, 0, 1, stone);
		addBlock(world, basePos, 2, 0, 2, stone);
		addBlock(world, basePos, 2, 0, 3, stone);
		addBlock(world, basePos, 2, 0, 4, stone);
		addBlock(world, basePos, 2, 0, 5, stone);
		addBlock(world, basePos, 2, 0, 6, stone);
		addBlock(world, basePos, 2, 0, 7, stone);
		addBlock(world, basePos, 2, 0, 8, stone);
		addBlock(world, basePos, 2, 0, 9, stone);
		addBlock(world, basePos, 2, 0, 10, stone);
		addBlock(world, basePos, 3, 0, 0, stone);
		addBlock(world, basePos, 3, 0, 1, stone);
		addBlock(world, basePos, 3, 0, 2, stone);
		addBlock(world, basePos, 3, 0, 3, stone);
		addBlock(world, basePos, 3, 0, 4, stone);
		addBlock(world, basePos, 3, 0, 5, stone);
		addBlock(world, basePos, 3, 0, 6, stone);
		addBlock(world, basePos, 3, 0, 7, stone);
		addBlock(world, basePos, 3, 0, 8, stone);
		addBlock(world, basePos, 3, 0, 9, stone);
		addBlock(world, basePos, 3, 0, 10, stone);
		addBlock(world, basePos, 4, 0, 0, stone);
		addBlock(world, basePos, 4, 0, 1, stone);
		addBlock(world, basePos, 4, 0, 2, stone);
		addBlock(world, basePos, 4, 0, 3, stone);
		addBlock(world, basePos, 4, 0, 4, stone);
		addBlock(world, basePos, 4, 0, 5, stone);
		addBlock(world, basePos, 4, 0, 6, stone);
		addBlock(world, basePos, 4, 0, 7, stone);
		addBlock(world, basePos, 4, 0, 8, stone);
		addBlock(world, basePos, 4, 0, 9, stone);
		addBlock(world, basePos, 4, 0, 10, stone);
		addBlock(world, basePos, 0, 1, 0, dawnwoodBars);
		addBlock(world, basePos, 0, 1, 10, dawnwoodBars);
		addBlock(world, basePos, 2, 1, 1, dawnwoodBars);
		addBlock(world, basePos, 2, 1, 9, dawnwoodBars);
		addBlock(world, basePos, 3, 1, 3, dawnwoodPlanks);
		addBlock(world, basePos, 3, 1, 4, dawnwoodPlanks);
		addBlock(world, basePos, 3, 1, 5, primordialShrine);
		addBlock(world, basePos, 3, 1, 6, dawnwoodPlanks);
		addBlock(world, basePos, 3, 1, 7, dawnwoodPlanks);
		addBlock(world, basePos, 4, 1, 2, dawnwoodPlanks);
		addBlock(world, basePos, 4, 1, 3, dawnwoodPlanks);
		addBlock(world, basePos, 4, 1, 4, dawnwoodPlanks);
		addBlock(world, basePos, 4, 1, 5, dawnwoodPlanks);
		addBlock(world, basePos, 4, 1, 6, dawnwoodPlanks);
		addBlock(world, basePos, 4, 1, 7, dawnwoodPlanks);
		addBlock(world, basePos, 4, 1, 8, dawnwoodPlanks);
		addBlock(world, basePos, 0, 2, 0, dustopianLamp);
		addBlock(world, basePos, 0, 2, 10, dustopianLamp);
		addBlock(world, basePos, 2, 2, 1, dustopianLamp);
		addBlock(world, basePos, 2, 2, 9, dustopianLamp);
		addBlock(world, basePos, 4, 2, 2, dawnwoodBars);
		addBlock(world, basePos, 4, 2, 3, dawnwoodPlanks);
		addBlock(world, basePos, 4, 2, 4, dawnwoodPlanks);
		addBlock(world, basePos, 4, 2, 5, dawnwoodPlanks);
		addBlock(world, basePos, 4, 2, 6, dawnwoodPlanks);
		addBlock(world, basePos, 4, 2, 7, dawnwoodPlanks);
		addBlock(world, basePos, 4, 2, 8, dawnwoodBars);
		addBlock(world, basePos, 4, 3, 2, dawnwoodBars);
		addBlock(world, basePos, 4, 3, 4, dawnwoodPlanks);
		addBlock(world, basePos, 4, 3, 6, dawnwoodPlanks);
		addBlock(world, basePos, 4, 3, 8, dawnwoodBars);
		addBlock(world, basePos, 4, 4, 2, dustopianLamp);
		addBlock(world, basePos, 4, 4, 4, dawnwoodBars);
		addBlock(world, basePos, 4, 4, 6, dawnwoodBars);
		addBlock(world, basePos, 4, 4, 8, dustopianLamp);
		addBlock(world, basePos, 4, 5, 4, dawnwoodBars);
		addBlock(world, basePos, 4, 5, 6, dawnwoodBars);
		addBlock(world, basePos, 4, 6, 4, dustopianLamp);
		addBlock(world, basePos, 4, 6, 6, dustopianLamp);
	}
}
