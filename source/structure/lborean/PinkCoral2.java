package net.tslat.aoa3.structure.lborean;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class PinkCoral2 extends AoAStructure { //StructureSize: 3x10x12
	private static final IBlockState pinkCoral = BlockRegister.PINK_CORAL.getDefaultState();

	public PinkCoral2() {
		super("PinkCoral2");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 1, 0, 1, pinkCoral);
		addBlock(world, basePos, 1, 0, 10, pinkCoral);
		addBlock(world, basePos, 1, 1, 1, pinkCoral);
		addBlock(world, basePos, 1, 1, 10, pinkCoral);
		addBlock(world, basePos, 1, 2, 1, pinkCoral);
		addBlock(world, basePos, 1, 2, 10, pinkCoral);
		addBlock(world, basePos, 1, 3, 0, pinkCoral);
		addBlock(world, basePos, 1, 3, 1, pinkCoral);
		addBlock(world, basePos, 1, 3, 10, pinkCoral);
		addBlock(world, basePos, 2, 3, 10, pinkCoral);
		addBlock(world, basePos, 0, 4, 1, pinkCoral);
		addBlock(world, basePos, 1, 4, 1, pinkCoral);
		addBlock(world, basePos, 1, 4, 3, pinkCoral);
		addBlock(world, basePos, 1, 4, 10, pinkCoral);
		addBlock(world, basePos, 1, 4, 11, pinkCoral);
		addBlock(world, basePos, 0, 5, 10, pinkCoral);
		addBlock(world, basePos, 1, 5, 0, pinkCoral);
		addBlock(world, basePos, 1, 5, 1, pinkCoral);
		addBlock(world, basePos, 1, 5, 2, pinkCoral);
		addBlock(world, basePos, 1, 5, 3, pinkCoral);
		addBlock(world, basePos, 1, 5, 4, pinkCoral);
		addBlock(world, basePos, 1, 5, 8, pinkCoral);
		addBlock(world, basePos, 1, 5, 9, pinkCoral);
		addBlock(world, basePos, 1, 5, 10, pinkCoral);
		addBlock(world, basePos, 2, 5, 1, pinkCoral);
		addBlock(world, basePos, 2, 5, 3, pinkCoral);
		addBlock(world, basePos, 2, 5, 8, pinkCoral);
		addBlock(world, basePos, 2, 5, 10, pinkCoral);
		addBlock(world, basePos, 0, 6, 4, pinkCoral);
		addBlock(world, basePos, 0, 6, 8, pinkCoral);
		addBlock(world, basePos, 1, 6, 2, pinkCoral);
		addBlock(world, basePos, 1, 6, 4, pinkCoral);
		addBlock(world, basePos, 1, 6, 5, pinkCoral);
		addBlock(world, basePos, 1, 6, 7, pinkCoral);
		addBlock(world, basePos, 1, 6, 8, pinkCoral);
		addBlock(world, basePos, 1, 6, 10, pinkCoral);
		addBlock(world, basePos, 2, 6, 4, pinkCoral);
		addBlock(world, basePos, 1, 7, 3, pinkCoral);
		addBlock(world, basePos, 1, 7, 4, pinkCoral);
		addBlock(world, basePos, 1, 7, 8, pinkCoral);
		addBlock(world, basePos, 2, 7, 8, pinkCoral);
		addBlock(world, basePos, 0, 8, 5, pinkCoral);
		addBlock(world, basePos, 1, 8, 4, pinkCoral);
		addBlock(world, basePos, 1, 8, 5, pinkCoral);
		addBlock(world, basePos, 1, 8, 6, pinkCoral);
		addBlock(world, basePos, 1, 8, 7, pinkCoral);
		addBlock(world, basePos, 1, 8, 8, pinkCoral);
		addBlock(world, basePos, 1, 8, 9, pinkCoral);
		addBlock(world, basePos, 2, 8, 4, pinkCoral);
		addBlock(world, basePos, 2, 8, 6, pinkCoral);
		addBlock(world, basePos, 1, 9, 6, pinkCoral);
		addBlock(world, basePos, 1, 9, 8, pinkCoral);
	}
}
