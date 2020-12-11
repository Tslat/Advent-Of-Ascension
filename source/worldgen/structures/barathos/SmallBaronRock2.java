package net.tslat.aoa3.worldgen.structures.barathos;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class SmallBaronRock2 extends AoAStructure { //StructureSize: 2x2x6
	private static final BlockState baronStone = AoABlocks.BARON_STONE.get().getDefaultState();

	public SmallBaronRock2() {
		super("SmallBaronRock2");
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 0, baronStone);
		addBlock(world, basePos, 0, 0, 1, baronStone);
		addBlock(world, basePos, 0, 0, 2, baronStone);
		addBlock(world, basePos, 0, 0, 3, baronStone);
		addBlock(world, basePos, 0, 0, 4, baronStone);
		addBlock(world, basePos, 0, 0, 5, baronStone);
		addBlock(world, basePos, 1, 0, 0, baronStone);
		addBlock(world, basePos, 1, 0, 1, baronStone);
		addBlock(world, basePos, 1, 0, 2, baronStone);
		addBlock(world, basePos, 1, 0, 3, baronStone);
		addBlock(world, basePos, 1, 0, 4, baronStone);
		addBlock(world, basePos, 1, 0, 5, baronStone);
		addBlock(world, basePos, 0, 1, 0, baronStone);
		addBlock(world, basePos, 0, 1, 1, baronStone);
		addBlock(world, basePos, 0, 1, 2, baronStone);
		addBlock(world, basePos, 0, 1, 3, baronStone);
		addBlock(world, basePos, 0, 1, 4, baronStone);
		addBlock(world, basePos, 0, 1, 5, baronStone);
		addBlock(world, basePos, 1, 1, 0, baronStone);
		addBlock(world, basePos, 1, 1, 1, baronStone);
		addBlock(world, basePos, 1, 1, 2, baronStone);
		addBlock(world, basePos, 1, 1, 3, baronStone);
		addBlock(world, basePos, 1, 1, 4, baronStone);
		addBlock(world, basePos, 1, 1, 5, baronStone);
	}
}
