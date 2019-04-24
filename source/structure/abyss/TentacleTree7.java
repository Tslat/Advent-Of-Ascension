package net.tslat.aoa3.structure.abyss;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class TentacleTree7 extends AoAStructure { //StructureSize: 1x11x2
	private static final IBlockState tentacleBlock = BlockRegister.tentacles.getDefaultState();
	private static final IBlockState tentacleEye = BlockRegister.tentaclesEyeOrange.getDefaultState();

	public TentacleTree7() {
		super("TentacleTree7");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
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
