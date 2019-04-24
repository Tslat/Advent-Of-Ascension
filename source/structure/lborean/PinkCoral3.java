package net.tslat.aoa3.structure.lborean;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class PinkCoral3 extends AoAStructure { //StructureSize: 12x9x3
	private static final IBlockState pinkCoral = BlockRegister.coralPink.getDefaultState();

	public PinkCoral3() {
		super("PinkCoral3");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 1, 0, 1, pinkCoral);
		addBlock(world, basePos, 10, 0, 1, pinkCoral);
		addBlock(world, basePos, 1, 1, 1, pinkCoral);
		addBlock(world, basePos, 10, 1, 1, pinkCoral);
		addBlock(world, basePos, 1, 2, 1, pinkCoral);
		addBlock(world, basePos, 10, 2, 1, pinkCoral);
		addBlock(world, basePos, 1, 3, 0, pinkCoral);
		addBlock(world, basePos, 1, 3, 1, pinkCoral);
		addBlock(world, basePos, 2, 3, 1, pinkCoral);
		addBlock(world, basePos, 10, 3, 0, pinkCoral);
		addBlock(world, basePos, 10, 3, 1, pinkCoral);
		addBlock(world, basePos, 10, 3, 2, pinkCoral);
		addBlock(world, basePos, 11, 3, 1, pinkCoral);
		addBlock(world, basePos, 0, 4, 1, pinkCoral);
		addBlock(world, basePos, 1, 4, 1, pinkCoral);
		addBlock(world, basePos, 1, 4, 2, pinkCoral);
		addBlock(world, basePos, 8, 4, 1, pinkCoral);
		addBlock(world, basePos, 10, 4, 1, pinkCoral);
		addBlock(world, basePos, 1, 5, 0, pinkCoral);
		addBlock(world, basePos, 1, 5, 1, pinkCoral);
		addBlock(world, basePos, 2, 5, 1, pinkCoral);
		addBlock(world, basePos, 2, 5, 2, pinkCoral);
		addBlock(world, basePos, 3, 5, 0, pinkCoral);
		addBlock(world, basePos, 3, 5, 1, pinkCoral);
		addBlock(world, basePos, 4, 5, 1, pinkCoral);
		addBlock(world, basePos, 7, 5, 1, pinkCoral);
		addBlock(world, basePos, 8, 5, 1, pinkCoral);
		addBlock(world, basePos, 9, 5, 1, pinkCoral);
		addBlock(world, basePos, 10, 5, 0, pinkCoral);
		addBlock(world, basePos, 10, 5, 1, pinkCoral);
		addBlock(world, basePos, 10, 5, 2, pinkCoral);
		addBlock(world, basePos, 11, 5, 1, pinkCoral);
		addBlock(world, basePos, 3, 6, 1, pinkCoral);
		addBlock(world, basePos, 3, 6, 2, pinkCoral);
		addBlock(world, basePos, 8, 6, 0, pinkCoral);
		addBlock(world, basePos, 8, 6, 1, pinkCoral);
		addBlock(world, basePos, 8, 6, 2, pinkCoral);
		addBlock(world, basePos, 10, 6, 1, pinkCoral);
		addBlock(world, basePos, 2, 7, 1, pinkCoral);
		addBlock(world, basePos, 3, 7, 0, pinkCoral);
		addBlock(world, basePos, 3, 7, 1, pinkCoral);
		addBlock(world, basePos, 4, 7, 1, pinkCoral);
		addBlock(world, basePos, 4, 7, 2, pinkCoral);
		addBlock(world, basePos, 5, 7, 0, pinkCoral);
		addBlock(world, basePos, 5, 7, 1, pinkCoral);
		addBlock(world, basePos, 6, 7, 1, pinkCoral);
		addBlock(world, basePos, 6, 7, 2, pinkCoral);
		addBlock(world, basePos, 7, 7, 1, pinkCoral);
		addBlock(world, basePos, 8, 7, 1, pinkCoral);
		addBlock(world, basePos, 2, 8, 1, pinkCoral);
		addBlock(world, basePos, 5, 8, 1, pinkCoral);
		addBlock(world, basePos, 7, 8, 1, pinkCoral);
	}
}
