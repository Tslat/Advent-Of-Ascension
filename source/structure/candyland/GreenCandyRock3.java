package net.tslat.aoa3.structure.candyland;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class GreenCandyRock3 extends AoAStructure { //StructureSize: 4x8x4
	private static final IBlockState greenRockCandy = BlockRegister.rockCandyGreen.getDefaultState();

	public GreenCandyRock3() {
		super("GreenCandyRock3");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 1, greenRockCandy);
		addBlock(world, basePos, 1, 0, 1, greenRockCandy);
		addBlock(world, basePos, 1, 0, 2, greenRockCandy);
		addBlock(world, basePos, 1, 0, 3, greenRockCandy);
		addBlock(world, basePos, 2, 0, 1, greenRockCandy);
		addBlock(world, basePos, 2, 0, 2, greenRockCandy);
		addBlock(world, basePos, 3, 0, 2, greenRockCandy);
		addBlock(world, basePos, 1, 1, 0, greenRockCandy);
		addBlock(world, basePos, 1, 1, 1, greenRockCandy);
		addBlock(world, basePos, 1, 1, 2, greenRockCandy);
		addBlock(world, basePos, 2, 1, 1, greenRockCandy);
		addBlock(world, basePos, 2, 1, 2, greenRockCandy);
		addBlock(world, basePos, 3, 1, 1, greenRockCandy);
		addBlock(world, basePos, 0, 2, 2, greenRockCandy);
		addBlock(world, basePos, 1, 2, 1, greenRockCandy);
		addBlock(world, basePos, 1, 2, 2, greenRockCandy);
		addBlock(world, basePos, 2, 2, 1, greenRockCandy);
		addBlock(world, basePos, 2, 2, 2, greenRockCandy);
		addBlock(world, basePos, 2, 2, 3, greenRockCandy);
		addBlock(world, basePos, 1, 3, 1, greenRockCandy);
		addBlock(world, basePos, 1, 3, 2, greenRockCandy);
		addBlock(world, basePos, 2, 3, 0, greenRockCandy);
		addBlock(world, basePos, 2, 3, 1, greenRockCandy);
		addBlock(world, basePos, 2, 3, 2, greenRockCandy);
		addBlock(world, basePos, 3, 3, 2, greenRockCandy);
		addBlock(world, basePos, 1, 4, 0, greenRockCandy);
		addBlock(world, basePos, 1, 4, 1, greenRockCandy);
		addBlock(world, basePos, 1, 4, 2, greenRockCandy);
		addBlock(world, basePos, 1, 4, 3, greenRockCandy);
		addBlock(world, basePos, 2, 4, 1, greenRockCandy);
		addBlock(world, basePos, 2, 4, 2, greenRockCandy);
		addBlock(world, basePos, 0, 5, 1, greenRockCandy);
		addBlock(world, basePos, 1, 5, 1, greenRockCandy);
		addBlock(world, basePos, 1, 5, 2, greenRockCandy);
		addBlock(world, basePos, 2, 5, 1, greenRockCandy);
		addBlock(world, basePos, 2, 5, 2, greenRockCandy);
		addBlock(world, basePos, 3, 5, 1, greenRockCandy);
		addBlock(world, basePos, 1, 6, 1, greenRockCandy);
		addBlock(world, basePos, 1, 6, 2, greenRockCandy);
		addBlock(world, basePos, 2, 6, 0, greenRockCandy);
		addBlock(world, basePos, 2, 6, 1, greenRockCandy);
		addBlock(world, basePos, 2, 6, 2, greenRockCandy);
		addBlock(world, basePos, 2, 6, 3, greenRockCandy);
		addBlock(world, basePos, 1, 7, 1, greenRockCandy);
	}
}
