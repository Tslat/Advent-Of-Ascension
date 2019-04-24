package net.tslat.aoa3.structure.lborean;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class PinkCoral1 extends AoAStructure { //StructureSize: 9x17x9
	private static final IBlockState pinkCoral = BlockRegister.coralPink.getDefaultState();

	public PinkCoral1() {
		super("PinkCoral1");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 4, 0, 4, pinkCoral);
		addBlock(world, basePos, 4, 1, 4, pinkCoral);
		addBlock(world, basePos, 4, 2, 4, pinkCoral);
		addBlock(world, basePos, 4, 3, 4, pinkCoral);
		addBlock(world, basePos, 0, 4, 4, pinkCoral);
		addBlock(world, basePos, 4, 4, 0, pinkCoral);
		addBlock(world, basePos, 4, 4, 2, pinkCoral);
		addBlock(world, basePos, 4, 4, 4, pinkCoral);
		addBlock(world, basePos, 4, 4, 7, pinkCoral);
		addBlock(world, basePos, 7, 4, 4, pinkCoral);
		addBlock(world, basePos, 0, 5, 4, pinkCoral);
		addBlock(world, basePos, 1, 5, 4, pinkCoral);
		addBlock(world, basePos, 2, 5, 4, pinkCoral);
		addBlock(world, basePos, 3, 5, 4, pinkCoral);
		addBlock(world, basePos, 4, 5, 0, pinkCoral);
		addBlock(world, basePos, 4, 5, 1, pinkCoral);
		addBlock(world, basePos, 4, 5, 2, pinkCoral);
		addBlock(world, basePos, 4, 5, 3, pinkCoral);
		addBlock(world, basePos, 4, 5, 4, pinkCoral);
		addBlock(world, basePos, 4, 5, 5, pinkCoral);
		addBlock(world, basePos, 4, 5, 6, pinkCoral);
		addBlock(world, basePos, 4, 5, 7, pinkCoral);
		addBlock(world, basePos, 4, 5, 8, pinkCoral);
		addBlock(world, basePos, 5, 5, 4, pinkCoral);
		addBlock(world, basePos, 6, 5, 4, pinkCoral);
		addBlock(world, basePos, 7, 5, 4, pinkCoral);
		addBlock(world, basePos, 8, 5, 4, pinkCoral);
		addBlock(world, basePos, 1, 6, 4, pinkCoral);
		addBlock(world, basePos, 4, 6, 1, pinkCoral);
		addBlock(world, basePos, 4, 6, 4, pinkCoral);
		addBlock(world, basePos, 4, 6, 6, pinkCoral);
		addBlock(world, basePos, 4, 6, 8, pinkCoral);
		addBlock(world, basePos, 7, 6, 4, pinkCoral);
		addBlock(world, basePos, 4, 7, 4, pinkCoral);
		addBlock(world, basePos, 1, 8, 4, pinkCoral);
		addBlock(world, basePos, 4, 8, 0, pinkCoral);
		addBlock(world, basePos, 4, 8, 2, pinkCoral);
		addBlock(world, basePos, 4, 8, 4, pinkCoral);
		addBlock(world, basePos, 4, 8, 6, pinkCoral);
		addBlock(world, basePos, 4, 8, 8, pinkCoral);
		addBlock(world, basePos, 6, 8, 4, pinkCoral);
		addBlock(world, basePos, 0, 9, 4, pinkCoral);
		addBlock(world, basePos, 1, 9, 4, pinkCoral);
		addBlock(world, basePos, 2, 9, 4, pinkCoral);
		addBlock(world, basePos, 3, 9, 4, pinkCoral);
		addBlock(world, basePos, 4, 9, 0, pinkCoral);
		addBlock(world, basePos, 4, 9, 1, pinkCoral);
		addBlock(world, basePos, 4, 9, 2, pinkCoral);
		addBlock(world, basePos, 4, 9, 3, pinkCoral);
		addBlock(world, basePos, 4, 9, 4, pinkCoral);
		addBlock(world, basePos, 4, 9, 5, pinkCoral);
		addBlock(world, basePos, 4, 9, 6, pinkCoral);
		addBlock(world, basePos, 4, 9, 7, pinkCoral);
		addBlock(world, basePos, 4, 9, 8, pinkCoral);
		addBlock(world, basePos, 5, 9, 4, pinkCoral);
		addBlock(world, basePos, 6, 9, 4, pinkCoral);
		addBlock(world, basePos, 7, 9, 4, pinkCoral);
		addBlock(world, basePos, 8, 9, 4, pinkCoral);
		addBlock(world, basePos, 0, 10, 4, pinkCoral);
		addBlock(world, basePos, 2, 10, 4, pinkCoral);
		addBlock(world, basePos, 4, 10, 1, pinkCoral);
		addBlock(world, basePos, 4, 10, 4, pinkCoral);
		addBlock(world, basePos, 4, 10, 8, pinkCoral);
		addBlock(world, basePos, 7, 10, 4, pinkCoral);
		addBlock(world, basePos, 4, 11, 4, pinkCoral);
		addBlock(world, basePos, 1, 12, 4, pinkCoral);
		addBlock(world, basePos, 4, 12, 2, pinkCoral);
		addBlock(world, basePos, 4, 12, 4, pinkCoral);
		addBlock(world, basePos, 4, 12, 6, pinkCoral);
		addBlock(world, basePos, 6, 12, 4, pinkCoral);
		addBlock(world, basePos, 1, 13, 4, pinkCoral);
		addBlock(world, basePos, 2, 13, 4, pinkCoral);
		addBlock(world, basePos, 3, 13, 4, pinkCoral);
		addBlock(world, basePos, 4, 13, 1, pinkCoral);
		addBlock(world, basePos, 4, 13, 2, pinkCoral);
		addBlock(world, basePos, 4, 13, 3, pinkCoral);
		addBlock(world, basePos, 4, 13, 4, pinkCoral);
		addBlock(world, basePos, 4, 13, 5, pinkCoral);
		addBlock(world, basePos, 4, 13, 6, pinkCoral);
		addBlock(world, basePos, 4, 13, 7, pinkCoral);
		addBlock(world, basePos, 5, 13, 4, pinkCoral);
		addBlock(world, basePos, 6, 13, 4, pinkCoral);
		addBlock(world, basePos, 7, 13, 4, pinkCoral);
		addBlock(world, basePos, 2, 14, 4, pinkCoral);
		addBlock(world, basePos, 4, 14, 1, pinkCoral);
		addBlock(world, basePos, 4, 14, 4, pinkCoral);
		addBlock(world, basePos, 4, 14, 6, pinkCoral);
		addBlock(world, basePos, 6, 14, 4, pinkCoral);
		addBlock(world, basePos, 4, 15, 3, pinkCoral);
		addBlock(world, basePos, 4, 15, 4, pinkCoral);
		addBlock(world, basePos, 4, 15, 5, pinkCoral);
		addBlock(world, basePos, 4, 16, 4, pinkCoral);
	}
}
