package net.tslat.aoa3.worldgen.structures.abyss;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class TentacleTree3 extends AoAStructure { //StructureSize: 4x8x1
	private static final BlockState tentacleBlock = AoABlocks.TENTACLE.get().getDefaultState();
	private static final BlockState tentacleEye = AoABlocks.TENTACLE_EYE_RED.get().getDefaultState();

	public TentacleTree3() {
		super("TentacleTree3");
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 0, tentacleBlock);
		addBlock(world, basePos, 0, 1, 0, tentacleBlock);
		addBlock(world, basePos, 0, 2, 0, tentacleBlock);
		addBlock(world, basePos, 0, 3, 0, tentacleEye);
		addBlock(world, basePos, 0, 4, 0, tentacleBlock);
		addBlock(world, basePos, 0, 5, 0, tentacleBlock);
		addBlock(world, basePos, 1, 5, 0, tentacleBlock);
		addBlock(world, basePos, 2, 5, 0, tentacleBlock);
		addBlock(world, basePos, 3, 5, 0, tentacleBlock);
		addBlock(world, basePos, 3, 6, 0, tentacleEye);
		addBlock(world, basePos, 3, 7, 0, tentacleBlock);
	}
}
