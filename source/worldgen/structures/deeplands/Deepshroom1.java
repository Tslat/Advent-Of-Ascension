package net.tslat.aoa3.worldgen.structures.deeplands;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class Deepshroom1 extends AoAStructure { //StructureSize: 7x10x7
	private static final BlockState mushroom = AoABlocks.BLACK_MUSHROOM_BLOCK.get().getDefaultState();
	private static final BlockState mushroomStem = AoABlocks.BLACK_MUSHROOM_STEM.get().getDefaultState();

	public Deepshroom1() {
		super("Deepshroom1");
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 3, 0, 3, mushroomStem);
		addBlock(world, basePos, 3, 1, 3, mushroomStem);
		addBlock(world, basePos, 3, 2, 3, mushroomStem);
		addBlock(world, basePos, 3, 3, 3, mushroomStem);
		addBlock(world, basePos, 3, 4, 3, mushroomStem);
		addBlock(world, basePos, 2, 5, 2, mushroom);
		addBlock(world, basePos, 2, 5, 3, mushroom);
		addBlock(world, basePos, 2, 5, 4, mushroom);
		addBlock(world, basePos, 3, 5, 2, mushroom);
		addBlock(world, basePos, 3, 5, 3, mushroomStem);
		addBlock(world, basePos, 3, 5, 4, mushroom);
		addBlock(world, basePos, 4, 5, 2, mushroom);
		addBlock(world, basePos, 4, 5, 3, mushroom);
		addBlock(world, basePos, 4, 5, 4, mushroom);
		addBlock(world, basePos, 1, 6, 1, mushroom);
		addBlock(world, basePos, 1, 6, 2, mushroom);
		addBlock(world, basePos, 1, 6, 3, mushroom);
		addBlock(world, basePos, 1, 6, 4, mushroom);
		addBlock(world, basePos, 1, 6, 5, mushroom);
		addBlock(world, basePos, 2, 6, 1, mushroom);
		addBlock(world, basePos, 2, 6, 5, mushroom);
		addBlock(world, basePos, 3, 6, 1, mushroom);
		addBlock(world, basePos, 3, 6, 3, mushroomStem);
		addBlock(world, basePos, 3, 6, 5, mushroom);
		addBlock(world, basePos, 4, 6, 1, mushroom);
		addBlock(world, basePos, 4, 6, 5, mushroom);
		addBlock(world, basePos, 5, 6, 1, mushroom);
		addBlock(world, basePos, 5, 6, 2, mushroom);
		addBlock(world, basePos, 5, 6, 3, mushroom);
		addBlock(world, basePos, 5, 6, 4, mushroom);
		addBlock(world, basePos, 5, 6, 5, mushroom);
		addBlock(world, basePos, 3, 7, 3, mushroomStem);
		addBlock(world, basePos, 1, 8, 1, mushroom);
		addBlock(world, basePos, 1, 8, 2, mushroom);
		addBlock(world, basePos, 1, 8, 3, mushroom);
		addBlock(world, basePos, 1, 8, 4, mushroom);
		addBlock(world, basePos, 1, 8, 5, mushroom);
		addBlock(world, basePos, 2, 8, 1, mushroom);
		addBlock(world, basePos, 2, 8, 2, mushroom);
		addBlock(world, basePos, 2, 8, 3, mushroom);
		addBlock(world, basePos, 2, 8, 4, mushroom);
		addBlock(world, basePos, 2, 8, 5, mushroom);
		addBlock(world, basePos, 3, 8, 1, mushroom);
		addBlock(world, basePos, 3, 8, 2, mushroom);
		addBlock(world, basePos, 3, 8, 3, mushroom);
		addBlock(world, basePos, 3, 8, 4, mushroom);
		addBlock(world, basePos, 3, 8, 5, mushroom);
		addBlock(world, basePos, 4, 8, 1, mushroom);
		addBlock(world, basePos, 4, 8, 2, mushroom);
		addBlock(world, basePos, 4, 8, 3, mushroom);
		addBlock(world, basePos, 4, 8, 4, mushroom);
		addBlock(world, basePos, 4, 8, 5, mushroom);
		addBlock(world, basePos, 5, 8, 1, mushroom);
		addBlock(world, basePos, 5, 8, 2, mushroom);
		addBlock(world, basePos, 5, 8, 3, mushroom);
		addBlock(world, basePos, 5, 8, 4, mushroom);
		addBlock(world, basePos, 5, 8, 5, mushroom);
		addBlock(world, basePos, 0, 9, 0, mushroom);
		addBlock(world, basePos, 0, 9, 1, mushroom);
		addBlock(world, basePos, 0, 9, 2, mushroom);
		addBlock(world, basePos, 0, 9, 3, mushroom);
		addBlock(world, basePos, 0, 9, 4, mushroom);
		addBlock(world, basePos, 0, 9, 5, mushroom);
		addBlock(world, basePos, 0, 9, 6, mushroom);
		addBlock(world, basePos, 1, 9, 0, mushroom);
		addBlock(world, basePos, 1, 9, 6, mushroom);
		addBlock(world, basePos, 2, 9, 0, mushroom);
		addBlock(world, basePos, 2, 9, 6, mushroom);
		addBlock(world, basePos, 3, 9, 0, mushroom);
		addBlock(world, basePos, 3, 9, 6, mushroom);
		addBlock(world, basePos, 4, 9, 0, mushroom);
		addBlock(world, basePos, 4, 9, 6, mushroom);
		addBlock(world, basePos, 5, 9, 0, mushroom);
		addBlock(world, basePos, 5, 9, 6, mushroom);
		addBlock(world, basePos, 6, 9, 0, mushroom);
		addBlock(world, basePos, 6, 9, 1, mushroom);
		addBlock(world, basePos, 6, 9, 2, mushroom);
		addBlock(world, basePos, 6, 9, 3, mushroom);
		addBlock(world, basePos, 6, 9, 4, mushroom);
		addBlock(world, basePos, 6, 9, 5, mushroom);
		addBlock(world, basePos, 6, 9, 6, mushroom);
	}
}
