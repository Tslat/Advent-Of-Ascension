package net.tslat.aoa3.structure.candyland;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class Lollypop1 extends AoAStructure { //StructureSize: 5x7x5
	private static final IBlockState redCandy = BlockRegister.candyRed.getDefaultState();
	private static final IBlockState plasticPole = BlockRegister.plastic.getDefaultState();

	public Lollypop1() {
		super("Lollypop1");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 2, 0, 2, plasticPole);
		addBlock(world, basePos, 2, 1, 2, plasticPole);
		addBlock(world, basePos, 1, 2, 1, redCandy);
		addBlock(world, basePos, 1, 2, 2, redCandy);
		addBlock(world, basePos, 1, 2, 3, redCandy);
		addBlock(world, basePos, 2, 2, 1, redCandy);
		addBlock(world, basePos, 2, 2, 2, redCandy);
		addBlock(world, basePos, 2, 2, 3, redCandy);
		addBlock(world, basePos, 3, 2, 1, redCandy);
		addBlock(world, basePos, 3, 2, 2, redCandy);
		addBlock(world, basePos, 3, 2, 3, redCandy);
		addBlock(world, basePos, 0, 3, 1, redCandy);
		addBlock(world, basePos, 0, 3, 2, redCandy);
		addBlock(world, basePos, 0, 3, 3, redCandy);
		addBlock(world, basePos, 1, 3, 0, redCandy);
		addBlock(world, basePos, 1, 3, 1, redCandy);
		addBlock(world, basePos, 1, 3, 2, redCandy);
		addBlock(world, basePos, 1, 3, 3, redCandy);
		addBlock(world, basePos, 1, 3, 4, redCandy);
		addBlock(world, basePos, 2, 3, 0, redCandy);
		addBlock(world, basePos, 2, 3, 1, redCandy);
		addBlock(world, basePos, 2, 3, 2, redCandy);
		addBlock(world, basePos, 2, 3, 3, redCandy);
		addBlock(world, basePos, 2, 3, 4, redCandy);
		addBlock(world, basePos, 3, 3, 0, redCandy);
		addBlock(world, basePos, 3, 3, 1, redCandy);
		addBlock(world, basePos, 3, 3, 2, redCandy);
		addBlock(world, basePos, 3, 3, 3, redCandy);
		addBlock(world, basePos, 3, 3, 4, redCandy);
		addBlock(world, basePos, 4, 3, 1, redCandy);
		addBlock(world, basePos, 4, 3, 2, redCandy);
		addBlock(world, basePos, 4, 3, 3, redCandy);
		addBlock(world, basePos, 0, 4, 1, redCandy);
		addBlock(world, basePos, 0, 4, 2, redCandy);
		addBlock(world, basePos, 0, 4, 3, redCandy);
		addBlock(world, basePos, 1, 4, 0, redCandy);
		addBlock(world, basePos, 1, 4, 1, redCandy);
		addBlock(world, basePos, 1, 4, 2, redCandy);
		addBlock(world, basePos, 1, 4, 3, redCandy);
		addBlock(world, basePos, 1, 4, 4, redCandy);
		addBlock(world, basePos, 2, 4, 0, redCandy);
		addBlock(world, basePos, 2, 4, 1, redCandy);
		addBlock(world, basePos, 2, 4, 2, redCandy);
		addBlock(world, basePos, 2, 4, 3, redCandy);
		addBlock(world, basePos, 2, 4, 4, redCandy);
		addBlock(world, basePos, 3, 4, 0, redCandy);
		addBlock(world, basePos, 3, 4, 1, redCandy);
		addBlock(world, basePos, 3, 4, 2, redCandy);
		addBlock(world, basePos, 3, 4, 3, redCandy);
		addBlock(world, basePos, 3, 4, 4, redCandy);
		addBlock(world, basePos, 4, 4, 1, redCandy);
		addBlock(world, basePos, 4, 4, 2, redCandy);
		addBlock(world, basePos, 4, 4, 3, redCandy);
		addBlock(world, basePos, 0, 5, 1, redCandy);
		addBlock(world, basePos, 0, 5, 2, redCandy);
		addBlock(world, basePos, 0, 5, 3, redCandy);
		addBlock(world, basePos, 1, 5, 0, redCandy);
		addBlock(world, basePos, 1, 5, 1, redCandy);
		addBlock(world, basePos, 1, 5, 2, redCandy);
		addBlock(world, basePos, 1, 5, 3, redCandy);
		addBlock(world, basePos, 1, 5, 4, redCandy);
		addBlock(world, basePos, 2, 5, 0, redCandy);
		addBlock(world, basePos, 2, 5, 1, redCandy);
		addBlock(world, basePos, 2, 5, 2, redCandy);
		addBlock(world, basePos, 2, 5, 3, redCandy);
		addBlock(world, basePos, 2, 5, 4, redCandy);
		addBlock(world, basePos, 3, 5, 0, redCandy);
		addBlock(world, basePos, 3, 5, 1, redCandy);
		addBlock(world, basePos, 3, 5, 2, redCandy);
		addBlock(world, basePos, 3, 5, 3, redCandy);
		addBlock(world, basePos, 3, 5, 4, redCandy);
		addBlock(world, basePos, 4, 5, 1, redCandy);
		addBlock(world, basePos, 4, 5, 2, redCandy);
		addBlock(world, basePos, 4, 5, 3, redCandy);
		addBlock(world, basePos, 1, 6, 1, redCandy);
		addBlock(world, basePos, 1, 6, 2, redCandy);
		addBlock(world, basePos, 1, 6, 3, redCandy);
		addBlock(world, basePos, 2, 6, 1, redCandy);
		addBlock(world, basePos, 2, 6, 2, redCandy);
		addBlock(world, basePos, 2, 6, 3, redCandy);
		addBlock(world, basePos, 3, 6, 1, redCandy);
		addBlock(world, basePos, 3, 6, 2, redCandy);
		addBlock(world, basePos, 3, 6, 3, redCandy);
	}
}
