package net.tslat.aoa3.structure.candyland;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class ChocolateBar2 extends AoAStructure { //StructureSize: 3x7x1
	private static final IBlockState chocolate = BlockRegister.CHOCOLATE_BLOCK.getDefaultState();

	public ChocolateBar2() {
		super("ChocolateBar2");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 0, chocolate);
		addBlock(world, basePos, 1, 0, 0, chocolate);
		addBlock(world, basePos, 2, 0, 0, chocolate);
		addBlock(world, basePos, 0, 1, 0, chocolate);
		addBlock(world, basePos, 1, 1, 0, chocolate);
		addBlock(world, basePos, 2, 1, 0, chocolate);
		addBlock(world, basePos, 0, 2, 0, chocolate);
		addBlock(world, basePos, 1, 2, 0, chocolate);
		addBlock(world, basePos, 2, 2, 0, chocolate);
		addBlock(world, basePos, 0, 3, 0, chocolate);
		addBlock(world, basePos, 1, 3, 0, chocolate);
		addBlock(world, basePos, 2, 3, 0, chocolate);
		addBlock(world, basePos, 0, 4, 0, chocolate);
		addBlock(world, basePos, 1, 4, 0, chocolate);
		addBlock(world, basePos, 2, 4, 0, chocolate);
		addBlock(world, basePos, 0, 5, 0, chocolate);
		addBlock(world, basePos, 1, 5, 0, chocolate);
		addBlock(world, basePos, 2, 5, 0, chocolate);
		addBlock(world, basePos, 0, 6, 0, chocolate);
		addBlock(world, basePos, 1, 6, 0, chocolate);
		addBlock(world, basePos, 2, 6, 0, chocolate);
	}
}
