package net.tslat.aoa3.worldgen.structures.abyss;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class TentacleTree4 extends AoAStructure { //StructureSize: 3x10x2
	private static final BlockState tentacleBlock = AoABlocks.TENTACLE.get().getDefaultState();
	private static final BlockState tentacleEye = AoABlocks.TENTACLE_EYE_ORANGE.get().getDefaultState();

	public TentacleTree4() {
		super("TentacleTree4");
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 2, 0, 1, tentacleBlock);
		addBlock(world, basePos, 2, 1, 1, tentacleBlock);
		addBlock(world, basePos, 2, 2, 1, tentacleBlock);
		addBlock(world, basePos, 2, 3, 1, tentacleBlock);
		addBlock(world, basePos, 2, 4, 1, tentacleEye);
		addBlock(world, basePos, 2, 5, 1, tentacleBlock);
		addBlock(world, basePos, 0, 6, 0, tentacleBlock);
		addBlock(world, basePos, 0, 6, 1, tentacleBlock);
		addBlock(world, basePos, 1, 6, 1, tentacleBlock);
		addBlock(world, basePos, 2, 6, 1, tentacleBlock);
		addBlock(world, basePos, 0, 7, 0, tentacleBlock);
		addBlock(world, basePos, 0, 8, 0, tentacleEye);
		addBlock(world, basePos, 0, 9, 0, tentacleBlock);
	}
}
