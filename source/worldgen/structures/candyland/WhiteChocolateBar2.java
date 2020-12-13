package net.tslat.aoa3.worldgen.structures.candyland;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class WhiteChocolateBar2 extends AoAStructure { //StructureSize: 3x7x1
	private static final BlockState whiteChocolate = AoABlocks.WHITE_CHOCOLATE_BLOCK.get().getDefaultState();

	public WhiteChocolateBar2() {
		super("WhiteChocolateBar2");
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 0, whiteChocolate);
		addBlock(world, basePos, 1, 0, 0, whiteChocolate);
		addBlock(world, basePos, 2, 0, 0, whiteChocolate);
		addBlock(world, basePos, 0, 1, 0, whiteChocolate);
		addBlock(world, basePos, 1, 1, 0, whiteChocolate);
		addBlock(world, basePos, 2, 1, 0, whiteChocolate);
		addBlock(world, basePos, 0, 2, 0, whiteChocolate);
		addBlock(world, basePos, 1, 2, 0, whiteChocolate);
		addBlock(world, basePos, 2, 2, 0, whiteChocolate);
		addBlock(world, basePos, 0, 3, 0, whiteChocolate);
		addBlock(world, basePos, 1, 3, 0, whiteChocolate);
		addBlock(world, basePos, 2, 3, 0, whiteChocolate);
		addBlock(world, basePos, 0, 4, 0, whiteChocolate);
		addBlock(world, basePos, 1, 4, 0, whiteChocolate);
		addBlock(world, basePos, 2, 4, 0, whiteChocolate);
		addBlock(world, basePos, 0, 5, 0, whiteChocolate);
		addBlock(world, basePos, 1, 5, 0, whiteChocolate);
		addBlock(world, basePos, 2, 5, 0, whiteChocolate);
		addBlock(world, basePos, 0, 6, 0, whiteChocolate);
		addBlock(world, basePos, 1, 6, 0, whiteChocolate);
		addBlock(world, basePos, 2, 6, 0, whiteChocolate);
	}
}
