package net.tslat.aoa3.worldgen.structures.lborean;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class GreenCoral1 extends AoAStructure { //StructureSize: 7x18x7
	private static final BlockState greenCoral = AoABlocks.GREEN_CORAL.get().getDefaultState();

	public GreenCoral1() {
		super("GreenCoral1");
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 3, 0, 3, greenCoral);
		addBlock(world, basePos, 3, 1, 3, greenCoral);
		addBlock(world, basePos, 3, 2, 3, greenCoral);
		addBlock(world, basePos, 3, 3, 3, greenCoral);
		addBlock(world, basePos, 3, 4, 3, greenCoral);
		addBlock(world, basePos, 3, 4, 6, greenCoral);
		addBlock(world, basePos, 0, 5, 3, greenCoral);
		addBlock(world, basePos, 3, 5, 0, greenCoral);
		addBlock(world, basePos, 3, 5, 1, greenCoral);
		addBlock(world, basePos, 3, 5, 2, greenCoral);
		addBlock(world, basePos, 3, 5, 3, greenCoral);
		addBlock(world, basePos, 3, 5, 4, greenCoral);
		addBlock(world, basePos, 3, 5, 5, greenCoral);
		addBlock(world, basePos, 3, 5, 6, greenCoral);
		addBlock(world, basePos, 0, 6, 3, greenCoral);
		addBlock(world, basePos, 1, 6, 3, greenCoral);
		addBlock(world, basePos, 2, 6, 3, greenCoral);
		addBlock(world, basePos, 3, 6, 1, greenCoral);
		addBlock(world, basePos, 3, 6, 3, greenCoral);
		addBlock(world, basePos, 3, 6, 5, greenCoral);
		addBlock(world, basePos, 5, 6, 3, greenCoral);
		addBlock(world, basePos, 1, 7, 3, greenCoral);
		addBlock(world, basePos, 3, 7, 3, greenCoral);
		addBlock(world, basePos, 4, 7, 3, greenCoral);
		addBlock(world, basePos, 5, 7, 3, greenCoral);
		addBlock(world, basePos, 6, 7, 3, greenCoral);
		addBlock(world, basePos, 3, 8, 1, greenCoral);
		addBlock(world, basePos, 3, 8, 3, greenCoral);
		addBlock(world, basePos, 3, 8, 5, greenCoral);
		addBlock(world, basePos, 0, 9, 3, greenCoral);
		addBlock(world, basePos, 1, 9, 3, greenCoral);
		addBlock(world, basePos, 2, 9, 3, greenCoral);
		addBlock(world, basePos, 3, 9, 0, greenCoral);
		addBlock(world, basePos, 3, 9, 1, greenCoral);
		addBlock(world, basePos, 3, 9, 2, greenCoral);
		addBlock(world, basePos, 3, 9, 3, greenCoral);
		addBlock(world, basePos, 3, 9, 4, greenCoral);
		addBlock(world, basePos, 3, 9, 5, greenCoral);
		addBlock(world, basePos, 3, 9, 6, greenCoral);
		addBlock(world, basePos, 4, 9, 3, greenCoral);
		addBlock(world, basePos, 0, 10, 3, greenCoral);
		addBlock(world, basePos, 3, 10, 0, greenCoral);
		addBlock(world, basePos, 3, 10, 3, greenCoral);
		addBlock(world, basePos, 3, 10, 6, greenCoral);
		addBlock(world, basePos, 1, 11, 3, greenCoral);
		addBlock(world, basePos, 3, 11, 1, greenCoral);
		addBlock(world, basePos, 3, 11, 3, greenCoral);
		addBlock(world, basePos, 5, 11, 3, greenCoral);
		addBlock(world, basePos, 0, 12, 3, greenCoral);
		addBlock(world, basePos, 1, 12, 3, greenCoral);
		addBlock(world, basePos, 2, 12, 3, greenCoral);
		addBlock(world, basePos, 3, 12, 0, greenCoral);
		addBlock(world, basePos, 3, 12, 1, greenCoral);
		addBlock(world, basePos, 3, 12, 2, greenCoral);
		addBlock(world, basePos, 3, 12, 3, greenCoral);
		addBlock(world, basePos, 3, 12, 5, greenCoral);
		addBlock(world, basePos, 4, 12, 3, greenCoral);
		addBlock(world, basePos, 5, 12, 3, greenCoral);
		addBlock(world, basePos, 6, 12, 3, greenCoral);
		addBlock(world, basePos, 3, 13, 3, greenCoral);
		addBlock(world, basePos, 3, 13, 4, greenCoral);
		addBlock(world, basePos, 3, 13, 5, greenCoral);
		addBlock(world, basePos, 3, 13, 6, greenCoral);
		addBlock(world, basePos, 3, 14, 1, greenCoral);
		addBlock(world, basePos, 3, 14, 3, greenCoral);
		addBlock(world, basePos, 3, 14, 6, greenCoral);
		addBlock(world, basePos, 0, 15, 3, greenCoral);
		addBlock(world, basePos, 1, 15, 3, greenCoral);
		addBlock(world, basePos, 2, 15, 3, greenCoral);
		addBlock(world, basePos, 3, 15, 1, greenCoral);
		addBlock(world, basePos, 3, 15, 2, greenCoral);
		addBlock(world, basePos, 3, 15, 3, greenCoral);
		addBlock(world, basePos, 3, 15, 5, greenCoral);
		addBlock(world, basePos, 5, 15, 3, greenCoral);
		addBlock(world, basePos, 1, 16, 3, greenCoral);
		addBlock(world, basePos, 3, 16, 3, greenCoral);
		addBlock(world, basePos, 3, 16, 4, greenCoral);
		addBlock(world, basePos, 3, 16, 5, greenCoral);
		addBlock(world, basePos, 4, 16, 3, greenCoral);
		addBlock(world, basePos, 5, 16, 3, greenCoral);
		addBlock(world, basePos, 6, 16, 3, greenCoral);
		addBlock(world, basePos, 3, 17, 3, greenCoral);
		addBlock(world, basePos, 6, 17, 3, greenCoral);
	}
}
