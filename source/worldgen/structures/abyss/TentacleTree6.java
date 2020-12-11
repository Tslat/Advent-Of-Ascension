package net.tslat.aoa3.worldgen.structures.abyss;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class TentacleTree6 extends AoAStructure { //StructureSize: 1x14x3
	private static final BlockState tentacleBlock = AoABlocks.TENTACLE.get().getDefaultState();
	private static final BlockState tentacleEye = AoABlocks.TENTACLE_EYE_ORANGE.get().getDefaultState();

	public TentacleTree6() {
		super("TentacleTree6");
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 1, tentacleBlock);
		addBlock(world, basePos, 0, 1, 1, tentacleBlock);
		addBlock(world, basePos, 0, 2, 1, tentacleBlock);
		addBlock(world, basePos, 0, 3, 1, tentacleEye);
		addBlock(world, basePos, 0, 4, 1, tentacleBlock);
		addBlock(world, basePos, 0, 5, 1, tentacleEye);
		addBlock(world, basePos, 0, 6, 1, tentacleBlock);
		addBlock(world, basePos, 0, 7, 1, tentacleBlock);
		addBlock(world, basePos, 0, 8, 1, tentacleBlock);
		addBlock(world, basePos, 0, 9, 1, tentacleBlock);
		addBlock(world, basePos, 0, 9, 2, tentacleBlock);
		addBlock(world, basePos, 0, 10, 2, tentacleBlock);
		addBlock(world, basePos, 0, 11, 0, tentacleBlock);
		addBlock(world, basePos, 0, 11, 1, tentacleBlock);
		addBlock(world, basePos, 0, 11, 2, tentacleBlock);
		addBlock(world, basePos, 0, 12, 0, tentacleBlock);
		addBlock(world, basePos, 0, 13, 0, tentacleBlock);
	}
}
