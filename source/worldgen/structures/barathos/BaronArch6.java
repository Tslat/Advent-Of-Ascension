package net.tslat.aoa3.worldgen.structures.barathos;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class BaronArch6 extends AoAStructure { //StructureSize: 1x3x4
	private static final BlockState baronStone = AoABlocks.BARON_STONE.get().getDefaultState();

	public BaronArch6() {
		super("BaronArch6");
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 0, baronStone);
		addBlock(world, basePos, 0, 0, 3, baronStone);
		addBlock(world, basePos, 0, 1, 0, baronStone);
		addBlock(world, basePos, 0, 1, 3, baronStone);
		addBlock(world, basePos, 0, 2, 0, baronStone);
		addBlock(world, basePos, 0, 2, 1, baronStone);
		addBlock(world, basePos, 0, 2, 2, baronStone);
		addBlock(world, basePos, 0, 2, 3, baronStone);
	}
}
