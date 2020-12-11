package net.tslat.aoa3.worldgen.structures.abyss;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class TentacleTree1 extends AoAStructure { //StructureSize: 2x10x2
	private static final BlockState tentacleBlock = AoABlocks.TENTACLE.get().getDefaultState();
	private static final BlockState tentacleEye = AoABlocks.TENTACLE_EYE_RED.get().getDefaultState();

	public TentacleTree1() {
		super("TentacleTree1");
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 0, tentacleBlock);
		addBlock(world, basePos, 0, 1, 0, tentacleBlock);
		addBlock(world, basePos, 0, 2, 0, tentacleBlock);
		addBlock(world, basePos, 0, 3, 0, tentacleBlock);
		addBlock(world, basePos, 0, 3, 1, tentacleBlock);
		addBlock(world, basePos, 0, 4, 1, tentacleBlock);
		addBlock(world, basePos, 0, 5, 1, tentacleEye);
		addBlock(world, basePos, 0, 6, 1, tentacleBlock);
		addBlock(world, basePos, 1, 6, 1, tentacleBlock);
		addBlock(world, basePos, 1, 7, 1, tentacleBlock);
		addBlock(world, basePos, 1, 8, 1, tentacleEye);
		addBlock(world, basePos, 1, 9, 1, tentacleBlock);
	}
}
