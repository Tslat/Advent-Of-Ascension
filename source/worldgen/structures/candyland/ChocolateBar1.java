package net.tslat.aoa3.worldgen.structures.candyland;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class ChocolateBar1 extends AoAStructure { //StructureSize: 1x7x3
	private static final BlockState chocolate = AoABlocks.CHOCOLATE_BLOCK.get().getDefaultState();

	public ChocolateBar1() {
		super("ChocolateBar1");
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 0, chocolate);
		addBlock(world, basePos, 0, 0, 1, chocolate);
		addBlock(world, basePos, 0, 0, 2, chocolate);
		addBlock(world, basePos, 0, 1, 0, chocolate);
		addBlock(world, basePos, 0, 1, 1, chocolate);
		addBlock(world, basePos, 0, 1, 2, chocolate);
		addBlock(world, basePos, 0, 2, 0, chocolate);
		addBlock(world, basePos, 0, 2, 1, chocolate);
		addBlock(world, basePos, 0, 2, 2, chocolate);
		addBlock(world, basePos, 0, 3, 0, chocolate);
		addBlock(world, basePos, 0, 3, 1, chocolate);
		addBlock(world, basePos, 0, 3, 2, chocolate);
		addBlock(world, basePos, 0, 4, 0, chocolate);
		addBlock(world, basePos, 0, 4, 1, chocolate);
		addBlock(world, basePos, 0, 4, 2, chocolate);
		addBlock(world, basePos, 0, 5, 0, chocolate);
		addBlock(world, basePos, 0, 5, 1, chocolate);
		addBlock(world, basePos, 0, 5, 2, chocolate);
		addBlock(world, basePos, 0, 6, 0, chocolate);
		addBlock(world, basePos, 0, 6, 1, chocolate);
		addBlock(world, basePos, 0, 6, 2, chocolate);
	}
}
