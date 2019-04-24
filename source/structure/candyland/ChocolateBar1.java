package net.tslat.aoa3.structure.candyland;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class ChocolateBar1 extends AoAStructure { //StructureSize: 1x7x3
	private static final IBlockState chocolate = BlockRegister.chocolateBlock.getDefaultState();

	public ChocolateBar1() {
		super("ChocolateBar1");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 0, chocolate);
		addBlock(world, basePos, 0, 0, 1, chocolate);
		addBlock(world, basePos, 0, 0, 2, chocolate);
		addBlock(world, basePos, 0, 1, 0, chocolate);
		addBlock(world, basePos, 0, 1, 1, chocolate);
		addBlock(world, basePos, 0, 1, 2, chocolate);
		addBlock(world, basePos, 0, 2, 0, chocolate);
		addBlock(world, basePos, 0, 2, 1, chocolate);
		addBlock(world, basePos, 0, 2, 2, chocolate);
		addBlock(world, basePos, 0, 3, 0, chocolate);
		addBlock(world, basePos, 0, 3, 1, chocolate);
		addBlock(world, basePos, 0, 3, 2, chocolate);
		addBlock(world, basePos, 0, 4, 0, chocolate);
		addBlock(world, basePos, 0, 4, 1, chocolate);
		addBlock(world, basePos, 0, 4, 2, chocolate);
		addBlock(world, basePos, 0, 5, 0, chocolate);
		addBlock(world, basePos, 0, 5, 1, chocolate);
		addBlock(world, basePos, 0, 5, 2, chocolate);
		addBlock(world, basePos, 0, 6, 0, chocolate);
		addBlock(world, basePos, 0, 6, 1, chocolate);
		addBlock(world, basePos, 0, 6, 2, chocolate);
	}
}
