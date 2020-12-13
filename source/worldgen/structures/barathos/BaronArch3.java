package net.tslat.aoa3.worldgen.structures.barathos;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class BaronArch3 extends AoAStructure { //StructureSize: 5x4x1
	private static final BlockState baronStone = AoABlocks.BARON_STONE.get().getDefaultState();

	public BaronArch3() {
		super("BaronArch3");
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 0, baronStone);
		addBlock(world, basePos, 4, 0, 0, baronStone);
		addBlock(world, basePos, 0, 1, 0, baronStone);
		addBlock(world, basePos, 4, 1, 0, baronStone);
		addBlock(world, basePos, 0, 2, 0, baronStone);
		addBlock(world, basePos, 4, 2, 0, baronStone);
		addBlock(world, basePos, 0, 3, 0, baronStone);
		addBlock(world, basePos, 1, 3, 0, baronStone);
		addBlock(world, basePos, 2, 3, 0, baronStone);
		addBlock(world, basePos, 3, 3, 0, baronStone);
		addBlock(world, basePos, 4, 3, 0, baronStone);
	}
}
