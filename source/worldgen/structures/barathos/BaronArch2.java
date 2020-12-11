package net.tslat.aoa3.worldgen.structures.barathos;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class BaronArch2 extends AoAStructure { //StructureSize: 5x3x1
	private static final BlockState baronStone = AoABlocks.BARON_STONE.get().getDefaultState();

	public BaronArch2() {
		super("BaronArch2");
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 0, baronStone);
		addBlock(world, basePos, 4, 0, 0, baronStone);
		addBlock(world, basePos, 0, 1, 0, baronStone);
		addBlock(world, basePos, 1, 1, 0, baronStone);
		addBlock(world, basePos, 3, 1, 0, baronStone);
		addBlock(world, basePos, 4, 1, 0, baronStone);
		addBlock(world, basePos, 1, 2, 0, baronStone);
		addBlock(world, basePos, 2, 2, 0, baronStone);
		addBlock(world, basePos, 3, 2, 0, baronStone);
	}
}
