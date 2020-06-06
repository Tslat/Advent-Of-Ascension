package net.tslat.aoa3.structure.candyland;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class SwirlPop1 extends AoAStructure { //StructureSize: 9x13x1
	//private static final IBlockState candy = BlockRegister.candyRed.getDefaultState();
	private static final IBlockState whiteCandy = BlockRegister.WHITE_CANDY.getDefaultState();
	private static final IBlockState plasticPole = BlockRegister.PLASTIC.getDefaultState();

	public SwirlPop1() {
		super("SwirlPop1");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		IBlockState candy = rand.nextBoolean() ? BlockRegister.RED_CANDY.getDefaultState() : BlockRegister.GREEN_CANDY.getDefaultState();

		addBlock(world, basePos, 4, 0, 0, plasticPole);
		addBlock(world, basePos, 4, 1, 0, plasticPole);
		addBlock(world, basePos, 4, 2, 0, plasticPole);
		addBlock(world, basePos, 4, 3, 0, plasticPole);
		addBlock(world, basePos, 4, 4, 0, plasticPole);
		addBlock(world, basePos, 2, 5, 0, candy);
		addBlock(world, basePos, 3, 5, 0, candy);
		addBlock(world, basePos, 4, 5, 0, candy);
		addBlock(world, basePos, 5, 5, 0, candy);
		addBlock(world, basePos, 6, 5, 0, candy);
		addBlock(world, basePos, 1, 6, 0, candy);
		addBlock(world, basePos, 2, 6, 0, whiteCandy);
		addBlock(world, basePos, 3, 6, 0, whiteCandy);
		addBlock(world, basePos, 4, 6, 0, whiteCandy);
		addBlock(world, basePos, 5, 6, 0, whiteCandy);
		addBlock(world, basePos, 6, 6, 0, whiteCandy);
		addBlock(world, basePos, 7, 6, 0, candy);
		addBlock(world, basePos, 0, 7, 0, candy);
		addBlock(world, basePos, 1, 7, 0, whiteCandy);
		addBlock(world, basePos, 2, 7, 0, candy);
		addBlock(world, basePos, 3, 7, 0, candy);
		addBlock(world, basePos, 4, 7, 0, candy);
		addBlock(world, basePos, 5, 7, 0, candy);
		addBlock(world, basePos, 6, 7, 0, candy);
		addBlock(world, basePos, 7, 7, 0, candy);
		addBlock(world, basePos, 8, 7, 0, candy);
		addBlock(world, basePos, 0, 8, 0, candy);
		addBlock(world, basePos, 1, 8, 0, whiteCandy);
		addBlock(world, basePos, 2, 8, 0, candy);
		addBlock(world, basePos, 3, 8, 0, candy);
		addBlock(world, basePos, 4, 8, 0, whiteCandy);
		addBlock(world, basePos, 5, 8, 0, whiteCandy);
		addBlock(world, basePos, 6, 8, 0, whiteCandy);
		addBlock(world, basePos, 7, 8, 0, candy);
		addBlock(world, basePos, 8, 8, 0, candy);
		addBlock(world, basePos, 0, 9, 0, candy);
		addBlock(world, basePos, 1, 9, 0, whiteCandy);
		addBlock(world, basePos, 2, 9, 0, candy);
		addBlock(world, basePos, 3, 9, 0, whiteCandy);
		addBlock(world, basePos, 4, 9, 0, candy);
		addBlock(world, basePos, 5, 9, 0, candy);
		addBlock(world, basePos, 6, 9, 0, candy);
		addBlock(world, basePos, 7, 9, 0, whiteCandy);
		addBlock(world, basePos, 8, 9, 0, candy);
		addBlock(world, basePos, 0, 10, 0, candy);
		addBlock(world, basePos, 1, 10, 0, whiteCandy);
		addBlock(world, basePos, 2, 10, 0, candy);
		addBlock(world, basePos, 3, 10, 0, candy);
		addBlock(world, basePos, 4, 10, 0, candy);
		addBlock(world, basePos, 5, 10, 0, candy);
		addBlock(world, basePos, 6, 10, 0, candy);
		addBlock(world, basePos, 7, 10, 0, whiteCandy);
		addBlock(world, basePos, 8, 10, 0, candy);
		addBlock(world, basePos, 1, 11, 0, candy);
		addBlock(world, basePos, 2, 11, 0, whiteCandy);
		addBlock(world, basePos, 3, 11, 0, whiteCandy);
		addBlock(world, basePos, 4, 11, 0, whiteCandy);
		addBlock(world, basePos, 5, 11, 0, whiteCandy);
		addBlock(world, basePos, 6, 11, 0, whiteCandy);
		addBlock(world, basePos, 7, 11, 0, candy);
		addBlock(world, basePos, 2, 12, 0, candy);
		addBlock(world, basePos, 3, 12, 0, candy);
		addBlock(world, basePos, 4, 12, 0, candy);
		addBlock(world, basePos, 5, 12, 0, candy);
		addBlock(world, basePos, 6, 12, 0, candy);
	}
}
