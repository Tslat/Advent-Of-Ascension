package net.tslat.aoa3.worldgen.structures.barathos;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class BaronArch5 extends AoAStructure { //StructureSize: 1x4x5
	private static final BlockState baronStone = AoABlocks.BARON_STONE.get().getDefaultState();

	public BaronArch5() {
		super("BaronArch5");
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 0, baronStone);
		addBlock(world, basePos, 0, 0, 4, baronStone);
		addBlock(world, basePos, 0, 1, 0, baronStone);
		addBlock(world, basePos, 0, 1, 4, baronStone);
		addBlock(world, basePos, 0, 2, 0, baronStone);
		addBlock(world, basePos, 0, 2, 4, baronStone);
		addBlock(world, basePos, 0, 3, 0, baronStone);
		addBlock(world, basePos, 0, 3, 1, baronStone);
		addBlock(world, basePos, 0, 3, 2, baronStone);
		addBlock(world, basePos, 0, 3, 3, baronStone);
		addBlock(world, basePos, 0, 3, 4, baronStone);
	}
}
