package net.tslat.aoa3.worldgen.structures.abyss;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class TentacleTree2 extends AoAStructure { //StructureSize: 4x9x3
	private static final BlockState tentacleBlock = AoABlocks.TENTACLE.get().getDefaultState();
	private static final BlockState tentacleEye = AoABlocks.TENTACLE_EYE_RED.get().getDefaultState();

	public TentacleTree2() {
		super("TentacleTree2");
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 1, 0, 1, tentacleBlock);
		addBlock(world, basePos, 1, 1, 1, tentacleBlock);
		addBlock(world, basePos, 1, 2, 1, tentacleBlock);
		addBlock(world, basePos, 1, 3, 1, tentacleEye);
		addBlock(world, basePos, 1, 4, 1, tentacleBlock);
		addBlock(world, basePos, 1, 5, 1, tentacleBlock);
		addBlock(world, basePos, 0, 6, 1, tentacleBlock);
		addBlock(world, basePos, 1, 6, 1, tentacleBlock);
		addBlock(world, basePos, 2, 6, 1, tentacleBlock);
		addBlock(world, basePos, 3, 6, 1, tentacleBlock);
		addBlock(world, basePos, 0, 7, 1, tentacleBlock);
		addBlock(world, basePos, 3, 7, 1, tentacleBlock);
		addBlock(world, basePos, 3, 7, 2, tentacleBlock);
		addBlock(world, basePos, 0, 8, 0, tentacleBlock);
		addBlock(world, basePos, 0, 8, 1, tentacleBlock);
	}
}
