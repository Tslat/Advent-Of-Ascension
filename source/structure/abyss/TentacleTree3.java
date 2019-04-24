package net.tslat.aoa3.structure.abyss;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class TentacleTree3 extends AoAStructure { //StructureSize: 4x8x1
	private static final IBlockState tentacleBlock = BlockRegister.tentacles.getDefaultState();
	private static final IBlockState tentacleEye = BlockRegister.tentaclesEyeRed.getDefaultState();

	public TentacleTree3() {
		super("TentacleTree3");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
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
