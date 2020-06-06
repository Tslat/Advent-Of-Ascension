package net.tslat.aoa3.structure.lborean;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class GreenCoral2 extends AoAStructure { //StructureSize: 3x10x12
	private static final IBlockState greenCoral = BlockRegister.GREEN_CORAL.getDefaultState();

	public GreenCoral2() {
		super("GreenCoral2");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 1, 0, 1, greenCoral);
		addBlock(world, basePos, 1, 0, 10, greenCoral);
		addBlock(world, basePos, 1, 1, 1, greenCoral);
		addBlock(world, basePos, 1, 1, 10, greenCoral);
		addBlock(world, basePos, 1, 2, 1, greenCoral);
		addBlock(world, basePos, 1, 2, 10, greenCoral);
		addBlock(world, basePos, 1, 3, 0, greenCoral);
		addBlock(world, basePos, 1, 3, 1, greenCoral);
		addBlock(world, basePos, 1, 3, 10, greenCoral);
		addBlock(world, basePos, 2, 3, 10, greenCoral);
		addBlock(world, basePos, 0, 4, 1, greenCoral);
		addBlock(world, basePos, 1, 4, 1, greenCoral);
		addBlock(world, basePos, 1, 4, 3, greenCoral);
		addBlock(world, basePos, 1, 4, 10, greenCoral);
		addBlock(world, basePos, 1, 4, 11, greenCoral);
		addBlock(world, basePos, 0, 5, 10, greenCoral);
		addBlock(world, basePos, 1, 5, 0, greenCoral);
		addBlock(world, basePos, 1, 5, 1, greenCoral);
		addBlock(world, basePos, 1, 5, 2, greenCoral);
		addBlock(world, basePos, 1, 5, 3, greenCoral);
		addBlock(world, basePos, 1, 5, 4, greenCoral);
		addBlock(world, basePos, 1, 5, 8, greenCoral);
		addBlock(world, basePos, 1, 5, 9, greenCoral);
		addBlock(world, basePos, 1, 5, 10, greenCoral);
		addBlock(world, basePos, 2, 5, 1, greenCoral);
		addBlock(world, basePos, 2, 5, 3, greenCoral);
		addBlock(world, basePos, 2, 5, 8, greenCoral);
		addBlock(world, basePos, 2, 5, 10, greenCoral);
		addBlock(world, basePos, 0, 6, 4, greenCoral);
		addBlock(world, basePos, 0, 6, 8, greenCoral);
		addBlock(world, basePos, 1, 6, 2, greenCoral);
		addBlock(world, basePos, 1, 6, 4, greenCoral);
		addBlock(world, basePos, 1, 6, 5, greenCoral);
		addBlock(world, basePos, 1, 6, 7, greenCoral);
		addBlock(world, basePos, 1, 6, 8, greenCoral);
		addBlock(world, basePos, 1, 6, 10, greenCoral);
		addBlock(world, basePos, 2, 6, 4, greenCoral);
		addBlock(world, basePos, 1, 7, 3, greenCoral);
		addBlock(world, basePos, 1, 7, 4, greenCoral);
		addBlock(world, basePos, 1, 7, 8, greenCoral);
		addBlock(world, basePos, 2, 7, 8, greenCoral);
		addBlock(world, basePos, 0, 8, 5, greenCoral);
		addBlock(world, basePos, 1, 8, 4, greenCoral);
		addBlock(world, basePos, 1, 8, 5, greenCoral);
		addBlock(world, basePos, 1, 8, 6, greenCoral);
		addBlock(world, basePos, 1, 8, 7, greenCoral);
		addBlock(world, basePos, 1, 8, 8, greenCoral);
		addBlock(world, basePos, 1, 8, 9, greenCoral);
		addBlock(world, basePos, 2, 8, 4, greenCoral);
		addBlock(world, basePos, 2, 8, 6, greenCoral);
		addBlock(world, basePos, 1, 9, 6, greenCoral);
		addBlock(world, basePos, 1, 9, 8, greenCoral);
	}
}
