package net.tslat.aoa3.structure.mysterium;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class MiniBlueMushroomTree extends AoAStructure { //StructureSize: 5x7x5
	private static final IBlockState blueMushroomOutside = BlockRegister.BLUE_MUSHROOM_OUTSIDE.getDefaultState();
	private static final IBlockState blueMushroomInside = BlockRegister.BLUE_MUSHROOM_INSIDE.getDefaultState();
	private static final IBlockState blueMushroomStem = BlockRegister.BLUE_MUSHROOM_STEM.getDefaultState();
	private static final IBlockState mysteriumGrass = BlockRegister.MYSTERIUM_GRASS.getDefaultState();

	public MiniBlueMushroomTree() {
		super("MiniBlueMushroomTree");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		if (world.getBlockState(basePos.add(2, -1, 2)).getBlock() == Blocks.WATER)
			world.setBlockState(basePos.add(2, -1, 2), mysteriumGrass);

		addBlock(world, basePos, 2, 0, 2, blueMushroomStem);
		addBlock(world, basePos, 2, 1, 2, blueMushroomStem);
		addBlock(world, basePos, 2, 2, 2, blueMushroomStem);
		addBlock(world, basePos, 2, 3, 2, blueMushroomStem);
		addBlock(world, basePos, 0, 4, 1, blueMushroomOutside);
		addBlock(world, basePos, 0, 4, 2, blueMushroomOutside);
		addBlock(world, basePos, 0, 4, 3, blueMushroomOutside);
		addBlock(world, basePos, 1, 4, 0, blueMushroomOutside);
		addBlock(world, basePos, 1, 4, 4, blueMushroomOutside);
		addBlock(world, basePos, 2, 4, 0, blueMushroomOutside);
		addBlock(world, basePos, 2, 4, 2, blueMushroomStem);
		addBlock(world, basePos, 2, 4, 4, blueMushroomOutside);
		addBlock(world, basePos, 3, 4, 0, blueMushroomOutside);
		addBlock(world, basePos, 3, 4, 4, blueMushroomOutside);
		addBlock(world, basePos, 4, 4, 1, blueMushroomOutside);
		addBlock(world, basePos, 4, 4, 2, blueMushroomOutside);
		addBlock(world, basePos, 4, 4, 3, blueMushroomOutside);
		addBlock(world, basePos, 0, 5, 1, blueMushroomOutside);
		addBlock(world, basePos, 0, 5, 2, blueMushroomOutside);
		addBlock(world, basePos, 0, 5, 3, blueMushroomOutside);
		addBlock(world, basePos, 1, 5, 0, blueMushroomOutside);
		addBlock(world, basePos, 1, 5, 1, blueMushroomInside);
		addBlock(world, basePos, 1, 5, 2, blueMushroomInside);
		addBlock(world, basePos, 1, 5, 3, blueMushroomInside);
		addBlock(world, basePos, 1, 5, 4, blueMushroomOutside);
		addBlock(world, basePos, 2, 5, 0, blueMushroomOutside);
		addBlock(world, basePos, 2, 5, 1, blueMushroomInside);
		addBlock(world, basePos, 2, 5, 2, blueMushroomStem);
		addBlock(world, basePos, 2, 5, 3, blueMushroomInside);
		addBlock(world, basePos, 2, 5, 4, blueMushroomOutside);
		addBlock(world, basePos, 3, 5, 0, blueMushroomOutside);
		addBlock(world, basePos, 3, 5, 1, blueMushroomInside);
		addBlock(world, basePos, 3, 5, 2, blueMushroomInside);
		addBlock(world, basePos, 3, 5, 3, blueMushroomInside);
		addBlock(world, basePos, 3, 5, 4, blueMushroomOutside);
		addBlock(world, basePos, 4, 5, 1, blueMushroomOutside);
		addBlock(world, basePos, 4, 5, 2, blueMushroomOutside);
		addBlock(world, basePos, 4, 5, 3, blueMushroomOutside);
		addBlock(world, basePos, 1, 6, 1, blueMushroomOutside);
		addBlock(world, basePos, 1, 6, 2, blueMushroomOutside);
		addBlock(world, basePos, 1, 6, 3, blueMushroomOutside);
		addBlock(world, basePos, 2, 6, 1, blueMushroomOutside);
		addBlock(world, basePos, 2, 6, 2, blueMushroomOutside);
		addBlock(world, basePos, 2, 6, 3, blueMushroomOutside);
		addBlock(world, basePos, 3, 6, 1, blueMushroomOutside);
		addBlock(world, basePos, 3, 6, 2, blueMushroomOutside);
		addBlock(world, basePos, 3, 6, 3, blueMushroomOutside);
	}
}
