package net.tslat.aoa3.worldgen.structures.abyss;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class TentacleTree7 extends AoAStructure { //StructureSize: 1x11x2
	private static final BlockState tentacleBlock = AoABlocks.TENTACLE.get().getDefaultState();
	private static final BlockState tentacleEye = AoABlocks.TENTACLE_EYE_ORANGE.get().getDefaultState();

	public TentacleTree7() {
		super("TentacleTree7");
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 0, tentacleBlock);
		addBlock(world, basePos, 0, 1, 0, tentacleBlock);
		addBlock(world, basePos, 0, 2, 0, tentacleBlock);
		addBlock(world, basePos, 0, 3, 0, tentacleBlock);
		addBlock(world, basePos, 0, 4, 0, tentacleBlock);
		addBlock(world, basePos, 0, 5, 0, tentacleBlock);
		addBlock(world, basePos, 0, 6, 0, tentacleEye);
		addBlock(world, basePos, 0, 7, 0, tentacleBlock);
		addBlock(world, basePos, 0, 8, 0, tentacleBlock);
		addBlock(world, basePos, 0, 8, 1, tentacleBlock);
		addBlock(world, basePos, 0, 9, 1, tentacleEye);
		addBlock(world, basePos, 0, 10, 1, tentacleBlock);
	}
}
