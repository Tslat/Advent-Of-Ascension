package net.tslat.aoa3.worldgen.structures.candyland;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class DarkChocolateBar2 extends AoAStructure { //StructureSize: 1x7x3
	private static final BlockState darkChocolate = AoABlocks.DARK_CHOCOLATE_BLOCK.get().getDefaultState();

	public DarkChocolateBar2() {
		super("DarkChocolateBar2");
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 0, darkChocolate);
		addBlock(world, basePos, 0, 0, 1, darkChocolate);
		addBlock(world, basePos, 0, 0, 2, darkChocolate);
		addBlock(world, basePos, 0, 1, 0, darkChocolate);
		addBlock(world, basePos, 0, 1, 1, darkChocolate);
		addBlock(world, basePos, 0, 1, 2, darkChocolate);
		addBlock(world, basePos, 0, 2, 0, darkChocolate);
		addBlock(world, basePos, 0, 2, 1, darkChocolate);
		addBlock(world, basePos, 0, 2, 2, darkChocolate);
		addBlock(world, basePos, 0, 3, 0, darkChocolate);
		addBlock(world, basePos, 0, 3, 1, darkChocolate);
		addBlock(world, basePos, 0, 3, 2, darkChocolate);
		addBlock(world, basePos, 0, 4, 0, darkChocolate);
		addBlock(world, basePos, 0, 4, 1, darkChocolate);
		addBlock(world, basePos, 0, 4, 2, darkChocolate);
		addBlock(world, basePos, 0, 5, 0, darkChocolate);
		addBlock(world, basePos, 0, 5, 1, darkChocolate);
		addBlock(world, basePos, 0, 5, 2, darkChocolate);
		addBlock(world, basePos, 0, 6, 0, darkChocolate);
		addBlock(world, basePos, 0, 6, 1, darkChocolate);
		addBlock(world, basePos, 0, 6, 2, darkChocolate);
	}
}
