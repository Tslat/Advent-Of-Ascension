package net.tslat.aoa3.worldgen.structures.candyland;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class Lollypop2 extends AoAStructure { //StructureSize: 5x10x5
	private static final BlockState redCandy = AoABlocks.RED_CANDY.get().getDefaultState();
	private static final BlockState plasticPole = AoABlocks.PLASTIC.get().getDefaultState();

	public Lollypop2() {
		super("Lollypop2");
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 2, 0, 2, plasticPole);
		addBlock(world, basePos, 2, 1, 2, plasticPole);
		addBlock(world, basePos, 2, 2, 2, plasticPole);
		addBlock(world, basePos, 2, 3, 2, plasticPole);
		addBlock(world, basePos, 2, 4, 2, plasticPole);
		addBlock(world, basePos, 1, 5, 1, redCandy);
		addBlock(world, basePos, 1, 5, 2, redCandy);
		addBlock(world, basePos, 1, 5, 3, redCandy);
		addBlock(world, basePos, 2, 5, 1, redCandy);
		addBlock(world, basePos, 2, 5, 2, redCandy);
		addBlock(world, basePos, 2, 5, 3, redCandy);
		addBlock(world, basePos, 3, 5, 1, redCandy);
		addBlock(world, basePos, 3, 5, 2, redCandy);
		addBlock(world, basePos, 3, 5, 3, redCandy);
		addBlock(world, basePos, 0, 6, 1, redCandy);
		addBlock(world, basePos, 0, 6, 2, redCandy);
		addBlock(world, basePos, 0, 6, 3, redCandy);
		addBlock(world, basePos, 1, 6, 0, redCandy);
		addBlock(world, basePos, 1, 6, 1, redCandy);
		addBlock(world, basePos, 1, 6, 2, redCandy);
		addBlock(world, basePos, 1, 6, 3, redCandy);
		addBlock(world, basePos, 1, 6, 4, redCandy);
		addBlock(world, basePos, 2, 6, 0, redCandy);
		addBlock(world, basePos, 2, 6, 1, redCandy);
		addBlock(world, basePos, 2, 6, 2, redCandy);
		addBlock(world, basePos, 2, 6, 3, redCandy);
		addBlock(world, basePos, 2, 6, 4, redCandy);
		addBlock(world, basePos, 3, 6, 0, redCandy);
		addBlock(world, basePos, 3, 6, 1, redCandy);
		addBlock(world, basePos, 3, 6, 2, redCandy);
		addBlock(world, basePos, 3, 6, 3, redCandy);
		addBlock(world, basePos, 3, 6, 4, redCandy);
		addBlock(world, basePos, 4, 6, 1, redCandy);
		addBlock(world, basePos, 4, 6, 2, redCandy);
		addBlock(world, basePos, 4, 6, 3, redCandy);
		addBlock(world, basePos, 0, 7, 1, redCandy);
		addBlock(world, basePos, 0, 7, 2, redCandy);
		addBlock(world, basePos, 0, 7, 3, redCandy);
		addBlock(world, basePos, 1, 7, 0, redCandy);
		addBlock(world, basePos, 1, 7, 1, redCandy);
		addBlock(world, basePos, 1, 7, 2, redCandy);
		addBlock(world, basePos, 1, 7, 3, redCandy);
		addBlock(world, basePos, 1, 7, 4, redCandy);
		addBlock(world, basePos, 2, 7, 0, redCandy);
		addBlock(world, basePos, 2, 7, 1, redCandy);
		addBlock(world, basePos, 2, 7, 2, redCandy);
		addBlock(world, basePos, 2, 7, 3, redCandy);
		addBlock(world, basePos, 2, 7, 4, redCandy);
		addBlock(world, basePos, 3, 7, 0, redCandy);
		addBlock(world, basePos, 3, 7, 1, redCandy);
		addBlock(world, basePos, 3, 7, 2, redCandy);
		addBlock(world, basePos, 3, 7, 3, redCandy);
		addBlock(world, basePos, 3, 7, 4, redCandy);
		addBlock(world, basePos, 4, 7, 1, redCandy);
		addBlock(world, basePos, 4, 7, 2, redCandy);
		addBlock(world, basePos, 4, 7, 3, redCandy);
		addBlock(world, basePos, 0, 8, 1, redCandy);
		addBlock(world, basePos, 0, 8, 2, redCandy);
		addBlock(world, basePos, 0, 8, 3, redCandy);
		addBlock(world, basePos, 1, 8, 0, redCandy);
		addBlock(world, basePos, 1, 8, 1, redCandy);
		addBlock(world, basePos, 1, 8, 2, redCandy);
		addBlock(world, basePos, 1, 8, 3, redCandy);
		addBlock(world, basePos, 1, 8, 4, redCandy);
		addBlock(world, basePos, 2, 8, 0, redCandy);
		addBlock(world, basePos, 2, 8, 1, redCandy);
		addBlock(world, basePos, 2, 8, 2, redCandy);
		addBlock(world, basePos, 2, 8, 3, redCandy);
		addBlock(world, basePos, 2, 8, 4, redCandy);
		addBlock(world, basePos, 3, 8, 0, redCandy);
		addBlock(world, basePos, 3, 8, 1, redCandy);
		addBlock(world, basePos, 3, 8, 2, redCandy);
		addBlock(world, basePos, 3, 8, 3, redCandy);
		addBlock(world, basePos, 3, 8, 4, redCandy);
		addBlock(world, basePos, 4, 8, 1, redCandy);
		addBlock(world, basePos, 4, 8, 2, redCandy);
		addBlock(world, basePos, 4, 8, 3, redCandy);
		addBlock(world, basePos, 1, 9, 1, redCandy);
		addBlock(world, basePos, 1, 9, 2, redCandy);
		addBlock(world, basePos, 1, 9, 3, redCandy);
		addBlock(world, basePos, 2, 9, 1, redCandy);
		addBlock(world, basePos, 2, 9, 2, redCandy);
		addBlock(world, basePos, 2, 9, 3, redCandy);
		addBlock(world, basePos, 3, 9, 1, redCandy);
		addBlock(world, basePos, 3, 9, 2, redCandy);
		addBlock(world, basePos, 3, 9, 3, redCandy);
	}
}
