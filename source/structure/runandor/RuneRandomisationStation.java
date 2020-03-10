package net.tslat.aoa3.structure.runandor;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

import static net.tslat.aoa3.block.functional.lamps.LampBlock.FIXED_LAMP;

public class RuneRandomisationStation extends AoAStructure { //StructureSize: 14x6x14
	private static final IBlockState whitewashBricks = BlockRegister.bricksWhitewash.getDefaultState();
	private static final IBlockState stone = BlockRegister.stoneRunic.getDefaultState();
	private static final IBlockState neonLapisLamp = BlockRegister.lampNeonLapis.getDefaultState().withProperty(FIXED_LAMP, true);
	private static final IBlockState runeRandomizer = BlockRegister.runeRandomizer.getDefaultState();

	public RuneRandomisationStation() {
		super("RuneRandomisationStation");
	}

	@Override
	protected boolean replacesBlocks() {
		return true;
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 6, stone);
		addBlock(world, basePos, 0, 0, 7, stone);
		addBlock(world, basePos, 1, 0, 5, stone);
		addBlock(world, basePos, 1, 0, 6, whitewashBricks);
		addBlock(world, basePos, 1, 0, 7, whitewashBricks);
		addBlock(world, basePos, 1, 0, 8, stone);
		addBlock(world, basePos, 2, 0, 4, stone);
		addBlock(world, basePos, 2, 0, 5, whitewashBricks);
		addBlock(world, basePos, 2, 0, 6, stone);
		addBlock(world, basePos, 2, 0, 7, stone);
		addBlock(world, basePos, 2, 0, 8, whitewashBricks);
		addBlock(world, basePos, 2, 0, 9, stone);
		addBlock(world, basePos, 3, 0, 3, stone);
		addBlock(world, basePos, 3, 0, 4, whitewashBricks);
		addBlock(world, basePos, 3, 0, 5, stone);
		addBlock(world, basePos, 3, 0, 6, whitewashBricks);
		addBlock(world, basePos, 3, 0, 7, whitewashBricks);
		addBlock(world, basePos, 3, 0, 8, stone);
		addBlock(world, basePos, 3, 0, 9, whitewashBricks);
		addBlock(world, basePos, 3, 0, 10, stone);
		addBlock(world, basePos, 4, 0, 2, stone);
		addBlock(world, basePos, 4, 0, 3, whitewashBricks);
		addBlock(world, basePos, 4, 0, 4, stone);
		addBlock(world, basePos, 4, 0, 5, whitewashBricks);
		addBlock(world, basePos, 4, 0, 6, stone);
		addBlock(world, basePos, 4, 0, 7, stone);
		addBlock(world, basePos, 4, 0, 8, whitewashBricks);
		addBlock(world, basePos, 4, 0, 9, stone);
		addBlock(world, basePos, 4, 0, 10, whitewashBricks);
		addBlock(world, basePos, 4, 0, 11, stone);
		addBlock(world, basePos, 5, 0, 1, stone);
		addBlock(world, basePos, 5, 0, 2, whitewashBricks);
		addBlock(world, basePos, 5, 0, 3, stone);
		addBlock(world, basePos, 5, 0, 4, whitewashBricks);
		addBlock(world, basePos, 5, 0, 5, stone);
		addBlock(world, basePos, 5, 0, 6, stone);
		addBlock(world, basePos, 5, 0, 7, stone);
		addBlock(world, basePos, 5, 0, 8, stone);
		addBlock(world, basePos, 5, 0, 9, whitewashBricks);
		addBlock(world, basePos, 5, 0, 10, stone);
		addBlock(world, basePos, 5, 0, 11, whitewashBricks);
		addBlock(world, basePos, 5, 0, 12, stone);
		addBlock(world, basePos, 6, 0, 0, stone);
		addBlock(world, basePos, 6, 0, 1, whitewashBricks);
		addBlock(world, basePos, 6, 0, 2, stone);
		addBlock(world, basePos, 6, 0, 3, whitewashBricks);
		addBlock(world, basePos, 6, 0, 4, stone);
		addBlock(world, basePos, 6, 0, 5, stone);
		addBlock(world, basePos, 6, 0, 6, stone);
		addBlock(world, basePos, 6, 0, 7, stone);
		addBlock(world, basePos, 6, 0, 8, stone);
		addBlock(world, basePos, 6, 0, 9, stone);
		addBlock(world, basePos, 6, 0, 10, whitewashBricks);
		addBlock(world, basePos, 6, 0, 11, stone);
		addBlock(world, basePos, 6, 0, 12, whitewashBricks);
		addBlock(world, basePos, 6, 0, 13, stone);
		addBlock(world, basePos, 7, 0, 0, stone);
		addBlock(world, basePos, 7, 0, 1, whitewashBricks);
		addBlock(world, basePos, 7, 0, 2, stone);
		addBlock(world, basePos, 7, 0, 3, whitewashBricks);
		addBlock(world, basePos, 7, 0, 4, stone);
		addBlock(world, basePos, 7, 0, 5, stone);
		addBlock(world, basePos, 7, 0, 6, stone);
		addBlock(world, basePos, 7, 0, 7, stone);
		addBlock(world, basePos, 7, 0, 8, stone);
		addBlock(world, basePos, 7, 0, 9, stone);
		addBlock(world, basePos, 7, 0, 10, whitewashBricks);
		addBlock(world, basePos, 7, 0, 11, stone);
		addBlock(world, basePos, 7, 0, 12, whitewashBricks);
		addBlock(world, basePos, 7, 0, 13, stone);
		addBlock(world, basePos, 8, 0, 1, stone);
		addBlock(world, basePos, 8, 0, 2, whitewashBricks);
		addBlock(world, basePos, 8, 0, 3, stone);
		addBlock(world, basePos, 8, 0, 4, whitewashBricks);
		addBlock(world, basePos, 8, 0, 5, stone);
		addBlock(world, basePos, 8, 0, 6, stone);
		addBlock(world, basePos, 8, 0, 7, stone);
		addBlock(world, basePos, 8, 0, 8, stone);
		addBlock(world, basePos, 8, 0, 9, whitewashBricks);
		addBlock(world, basePos, 8, 0, 10, stone);
		addBlock(world, basePos, 8, 0, 11, whitewashBricks);
		addBlock(world, basePos, 8, 0, 12, stone);
		addBlock(world, basePos, 9, 0, 2, stone);
		addBlock(world, basePos, 9, 0, 3, whitewashBricks);
		addBlock(world, basePos, 9, 0, 4, stone);
		addBlock(world, basePos, 9, 0, 5, whitewashBricks);
		addBlock(world, basePos, 9, 0, 6, stone);
		addBlock(world, basePos, 9, 0, 7, stone);
		addBlock(world, basePos, 9, 0, 8, whitewashBricks);
		addBlock(world, basePos, 9, 0, 9, stone);
		addBlock(world, basePos, 9, 0, 10, whitewashBricks);
		addBlock(world, basePos, 9, 0, 11, stone);
		addBlock(world, basePos, 10, 0, 3, stone);
		addBlock(world, basePos, 10, 0, 4, whitewashBricks);
		addBlock(world, basePos, 10, 0, 5, stone);
		addBlock(world, basePos, 10, 0, 6, whitewashBricks);
		addBlock(world, basePos, 10, 0, 7, whitewashBricks);
		addBlock(world, basePos, 10, 0, 8, stone);
		addBlock(world, basePos, 10, 0, 9, whitewashBricks);
		addBlock(world, basePos, 10, 0, 10, stone);
		addBlock(world, basePos, 11, 0, 4, stone);
		addBlock(world, basePos, 11, 0, 5, whitewashBricks);
		addBlock(world, basePos, 11, 0, 6, stone);
		addBlock(world, basePos, 11, 0, 7, stone);
		addBlock(world, basePos, 11, 0, 8, whitewashBricks);
		addBlock(world, basePos, 11, 0, 9, stone);
		addBlock(world, basePos, 12, 0, 5, stone);
		addBlock(world, basePos, 12, 0, 6, whitewashBricks);
		addBlock(world, basePos, 12, 0, 7, whitewashBricks);
		addBlock(world, basePos, 12, 0, 8, stone);
		addBlock(world, basePos, 13, 0, 6, stone);
		addBlock(world, basePos, 13, 0, 7, stone);
		addBlock(world, basePos, 4, 1, 4, neonLapisLamp); 
		addBlock(world, basePos, 4, 1, 9, neonLapisLamp); 
		addBlock(world, basePos, 6, 1, 6, neonLapisLamp); 
		addBlock(world, basePos, 6, 1, 7, neonLapisLamp); 
		addBlock(world, basePos, 7, 1, 6, neonLapisLamp); 
		addBlock(world, basePos, 7, 1, 7, neonLapisLamp); 
		addBlock(world, basePos, 9, 1, 4, neonLapisLamp); 
		addBlock(world, basePos, 9, 1, 9, neonLapisLamp); 
		addBlock(world, basePos, 4, 2, 4, neonLapisLamp); 
		addBlock(world, basePos, 4, 2, 9, neonLapisLamp); 
		addBlock(world, basePos, 6, 2, 6, runeRandomizer);
		addBlock(world, basePos, 6, 2, 7, runeRandomizer);
		addBlock(world, basePos, 7, 2, 6, runeRandomizer);
		addBlock(world, basePos, 7, 2, 7, runeRandomizer);
		addBlock(world, basePos, 9, 2, 4, neonLapisLamp); 
		addBlock(world, basePos, 9, 2, 9, neonLapisLamp); 
		addBlock(world, basePos, 4, 3, 4, neonLapisLamp); 
		addBlock(world, basePos, 4, 3, 9, neonLapisLamp); 
		addBlock(world, basePos, 6, 3, 6, neonLapisLamp); 
		addBlock(world, basePos, 6, 3, 7, neonLapisLamp); 
		addBlock(world, basePos, 7, 3, 6, neonLapisLamp); 
		addBlock(world, basePos, 7, 3, 7, neonLapisLamp); 
		addBlock(world, basePos, 9, 3, 4, neonLapisLamp); 
		addBlock(world, basePos, 9, 3, 9, neonLapisLamp); 
		addBlock(world, basePos, 6, 4, 6, neonLapisLamp); 
		addBlock(world, basePos, 6, 4, 7, neonLapisLamp); 
		addBlock(world, basePos, 7, 4, 6, neonLapisLamp); 
		addBlock(world, basePos, 7, 4, 7, neonLapisLamp); 
		addBlock(world, basePos, 6, 5, 6, neonLapisLamp); 
		addBlock(world, basePos, 6, 5, 7, neonLapisLamp); 
		addBlock(world, basePos, 7, 5, 6, neonLapisLamp); 
		addBlock(world, basePos, 7, 5, 7, neonLapisLamp); 
	}
}
