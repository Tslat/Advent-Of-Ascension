package net.tslat.aoa3.structure.candyland;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class SwirlPop2 extends AoAStructure { //StructureSize: 1x13x9
	//private static final IBlockState candy = BlockRegister.candyRed.getDefaultState();
	private static final IBlockState whiteCandy = BlockRegister.candyWhite.getDefaultState();
	private static final IBlockState plasticPole = BlockRegister.plastic.getDefaultState();

	public SwirlPop2() {
		super("SwirlPop2");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		IBlockState candy = rand.nextBoolean() ? BlockRegister.candyRed.getDefaultState() : BlockRegister.candyGreen.getDefaultState();

		addBlock(world, basePos, 0, 0, 4, plasticPole);
		addBlock(world, basePos, 0, 1, 4, plasticPole);
		addBlock(world, basePos, 0, 2, 4, plasticPole);
		addBlock(world, basePos, 0, 3, 4, plasticPole);
		addBlock(world, basePos, 0, 4, 4, plasticPole);
		addBlock(world, basePos, 0, 5, 2, candy);
		addBlock(world, basePos, 0, 5, 3, candy);
		addBlock(world, basePos, 0, 5, 4, candy);
		addBlock(world, basePos, 0, 5, 5, candy);
		addBlock(world, basePos, 0, 5, 6, candy);
		addBlock(world, basePos, 0, 6, 1, candy);
		addBlock(world, basePos, 0, 6, 2, whiteCandy);
		addBlock(world, basePos, 0, 6, 3, whiteCandy);
		addBlock(world, basePos, 0, 6, 4, whiteCandy);
		addBlock(world, basePos, 0, 6, 5, whiteCandy);
		addBlock(world, basePos, 0, 6, 6, whiteCandy);
		addBlock(world, basePos, 0, 6, 7, candy);
		addBlock(world, basePos, 0, 7, 0, candy);
		addBlock(world, basePos, 0, 7, 1, candy);
		addBlock(world, basePos, 0, 7, 2, candy);
		addBlock(world, basePos, 0, 7, 3, candy);
		addBlock(world, basePos, 0, 7, 4, candy);
		addBlock(world, basePos, 0, 7, 5, candy);
		addBlock(world, basePos, 0, 7, 6, candy);
		addBlock(world, basePos, 0, 7, 7, whiteCandy);
		addBlock(world, basePos, 0, 7, 8, candy);
		addBlock(world, basePos, 0, 8, 0, candy);
		addBlock(world, basePos, 0, 8, 1, candy);
		addBlock(world, basePos, 0, 8, 2, whiteCandy);
		addBlock(world, basePos, 0, 8, 3, whiteCandy);
		addBlock(world, basePos, 0, 8, 4, whiteCandy);
		addBlock(world, basePos, 0, 8, 5, candy);
		addBlock(world, basePos, 0, 8, 6, candy);
		addBlock(world, basePos, 0, 8, 7, whiteCandy);
		addBlock(world, basePos, 0, 8, 8, candy);
		addBlock(world, basePos, 0, 9, 0, candy);
		addBlock(world, basePos, 0, 9, 1, whiteCandy);
		addBlock(world, basePos, 0, 9, 2, candy);
		addBlock(world, basePos, 0, 9, 3, candy);
		addBlock(world, basePos, 0, 9, 4, candy);
		addBlock(world, basePos, 0, 9, 5, whiteCandy);
		addBlock(world, basePos, 0, 9, 6, candy);
		addBlock(world, basePos, 0, 9, 7, whiteCandy);
		addBlock(world, basePos, 0, 9, 8, candy);
		addBlock(world, basePos, 0, 10, 0, candy);
		addBlock(world, basePos, 0, 10, 1, whiteCandy);
		addBlock(world, basePos, 0, 10, 2, candy);
		addBlock(world, basePos, 0, 10, 3, candy);
		addBlock(world, basePos, 0, 10, 4, candy);
		addBlock(world, basePos, 0, 10, 5, candy);
		addBlock(world, basePos, 0, 10, 6, candy);
		addBlock(world, basePos, 0, 10, 7, whiteCandy);
		addBlock(world, basePos, 0, 10, 8, candy);
		addBlock(world, basePos, 0, 11, 1, candy);
		addBlock(world, basePos, 0, 11, 2, whiteCandy);
		addBlock(world, basePos, 0, 11, 3, whiteCandy);
		addBlock(world, basePos, 0, 11, 4, whiteCandy);
		addBlock(world, basePos, 0, 11, 5, whiteCandy);
		addBlock(world, basePos, 0, 11, 6, whiteCandy);
		addBlock(world, basePos, 0, 11, 7, candy);
		addBlock(world, basePos, 0, 12, 2, candy);
		addBlock(world, basePos, 0, 12, 3, candy);
		addBlock(world, basePos, 0, 12, 4, candy);
		addBlock(world, basePos, 0, 12, 5, candy);
		addBlock(world, basePos, 0, 12, 6, candy);
	}
}
