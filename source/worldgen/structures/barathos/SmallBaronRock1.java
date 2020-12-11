package net.tslat.aoa3.worldgen.structures.barathos;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class SmallBaronRock1 extends AoAStructure { //StructureSize: 7x2x2
	private static final BlockState baronStone = AoABlocks.BARON_STONE.get().getDefaultState();

	public SmallBaronRock1() {
		super("SmallBaronRock1");
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 0, baronStone);
		addBlock(world, basePos, 0, 0, 1, baronStone);
		addBlock(world, basePos, 1, 0, 0, baronStone);
		addBlock(world, basePos, 1, 0, 1, baronStone);
		addBlock(world, basePos, 2, 0, 0, baronStone);
		addBlock(world, basePos, 2, 0, 1, baronStone);
		addBlock(world, basePos, 3, 0, 0, baronStone);
		addBlock(world, basePos, 3, 0, 1, baronStone);
		addBlock(world, basePos, 4, 0, 0, baronStone);
		addBlock(world, basePos, 4, 0, 1, baronStone);
		addBlock(world, basePos, 5, 0, 0, baronStone);
		addBlock(world, basePos, 5, 0, 1, baronStone);
		addBlock(world, basePos, 6, 0, 0, baronStone);
		addBlock(world, basePos, 6, 0, 1, baronStone);
		addBlock(world, basePos, 0, 1, 0, baronStone);
		addBlock(world, basePos, 0, 1, 1, baronStone);
		addBlock(world, basePos, 1, 1, 0, baronStone);
		addBlock(world, basePos, 1, 1, 1, baronStone);
		addBlock(world, basePos, 2, 1, 0, baronStone);
		addBlock(world, basePos, 2, 1, 1, baronStone);
		addBlock(world, basePos, 3, 1, 0, baronStone);
		addBlock(world, basePos, 3, 1, 1, baronStone);
		addBlock(world, basePos, 4, 1, 0, baronStone);
		addBlock(world, basePos, 4, 1, 1, baronStone);
		addBlock(world, basePos, 5, 1, 0, baronStone);
		addBlock(world, basePos, 5, 1, 1, baronStone);
		addBlock(world, basePos, 6, 1, 0, baronStone);
		addBlock(world, basePos, 6, 1, 1, baronStone);
	}
}
