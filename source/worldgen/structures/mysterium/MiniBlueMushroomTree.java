package net.tslat.aoa3.worldgen.structures.mysterium;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HugeMushroomBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class MiniBlueMushroomTree extends AoAStructure { //StructureSize: 5x7x5
	private static final BlockState blueMushroomOutside = AoABlocks.BLUE_MUSHROOM_BLOCK.get().getDefaultState();
	private static final BlockState blueMushroomInside = AoABlocks.BLUE_MUSHROOM_BLOCK.get().getDefaultState().with(HugeMushroomBlock.NORTH, false).with(HugeMushroomBlock.SOUTH, false).with(HugeMushroomBlock.EAST, false).with(HugeMushroomBlock.WEST, false).with(HugeMushroomBlock.UP, false).with(HugeMushroomBlock.DOWN, false);
	private static final BlockState blueMushroomStem = AoABlocks.BLUE_MUSHROOM_STEM.get().getDefaultState();
	private static final BlockState mysteriumGrass = AoABlocks.FUNGAL_GRASS.get().getDefaultState();

	public MiniBlueMushroomTree() {
		super("MiniBlueMushroomTree");
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		if (world.getBlockState(basePos.add(2, -1, 2)).getBlock() == Blocks.WATER)
			world.setBlockState(basePos.add(2, -1, 2), mysteriumGrass, 2);

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
