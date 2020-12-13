package net.tslat.aoa3.worldgen.structures.candyland;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class DarkChocolateBar1 extends AoAStructure { //StructureSize: 3x7x1
	private static final BlockState darkChocolate = AoABlocks.DARK_CHOCOLATE_BLOCK.get().getDefaultState();

	public DarkChocolateBar1() {
		super("DarkChocolateBar1");
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 0, darkChocolate);
		addBlock(world, basePos, 1, 0, 0, darkChocolate);
		addBlock(world, basePos, 2, 0, 0, darkChocolate);
		addBlock(world, basePos, 0, 1, 0, darkChocolate);
		addBlock(world, basePos, 1, 1, 0, darkChocolate);
		addBlock(world, basePos, 2, 1, 0, darkChocolate);
		addBlock(world, basePos, 0, 2, 0, darkChocolate);
		addBlock(world, basePos, 1, 2, 0, darkChocolate);
		addBlock(world, basePos, 2, 2, 0, darkChocolate);
		addBlock(world, basePos, 0, 3, 0, darkChocolate);
		addBlock(world, basePos, 1, 3, 0, darkChocolate);
		addBlock(world, basePos, 2, 3, 0, darkChocolate);
		addBlock(world, basePos, 0, 4, 0, darkChocolate);
		addBlock(world, basePos, 1, 4, 0, darkChocolate);
		addBlock(world, basePos, 2, 4, 0, darkChocolate);
		addBlock(world, basePos, 0, 5, 0, darkChocolate);
		addBlock(world, basePos, 1, 5, 0, darkChocolate);
		addBlock(world, basePos, 2, 5, 0, darkChocolate);
		addBlock(world, basePos, 0, 6, 0, darkChocolate);
		addBlock(world, basePos, 1, 6, 0, darkChocolate);
		addBlock(world, basePos, 2, 6, 0, darkChocolate);
	}
}
