package net.tslat.aoa3.structure.candyland;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class MallowTree3 extends AoAStructure { //StructureSize: 3x17x3
	private static final IBlockState marshmallow = BlockRegister.marshmallow.getDefaultState();
	private static final IBlockState licorice = BlockRegister.licorice.getDefaultState();

	public MallowTree3() {
		super("MallowTree3");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 1, 0, 1, licorice);
		addBlock(world, basePos, 1, 1, 1, licorice);
		addBlock(world, basePos, 1, 2, 1, licorice);
		addBlock(world, basePos, 1, 3, 1, licorice);
		addBlock(world, basePos, 1, 4, 1, licorice);
		addBlock(world, basePos, 0, 5, 0, marshmallow);
		addBlock(world, basePos, 0, 5, 1, marshmallow);
		addBlock(world, basePos, 0, 5, 2, marshmallow);
		addBlock(world, basePos, 1, 5, 0, marshmallow);
		addBlock(world, basePos, 1, 5, 1, licorice);
		addBlock(world, basePos, 1, 5, 2, marshmallow);
		addBlock(world, basePos, 2, 5, 0, marshmallow);
		addBlock(world, basePos, 2, 5, 1, marshmallow);
		addBlock(world, basePos, 2, 5, 2, marshmallow);
		addBlock(world, basePos, 0, 6, 0, marshmallow);
		addBlock(world, basePos, 0, 6, 1, marshmallow);
		addBlock(world, basePos, 0, 6, 2, marshmallow);
		addBlock(world, basePos, 1, 6, 0, marshmallow);
		addBlock(world, basePos, 1, 6, 1, licorice);
		addBlock(world, basePos, 1, 6, 2, marshmallow);
		addBlock(world, basePos, 2, 6, 0, marshmallow);
		addBlock(world, basePos, 2, 6, 1, marshmallow);
		addBlock(world, basePos, 2, 6, 2, marshmallow);
		addBlock(world, basePos, 0, 7, 0, marshmallow);
		addBlock(world, basePos, 0, 7, 1, marshmallow);
		addBlock(world, basePos, 0, 7, 2, marshmallow);
		addBlock(world, basePos, 1, 7, 0, marshmallow);
		addBlock(world, basePos, 1, 7, 1, licorice);
		addBlock(world, basePos, 1, 7, 2, marshmallow);
		addBlock(world, basePos, 2, 7, 0, marshmallow);
		addBlock(world, basePos, 2, 7, 1, marshmallow);
		addBlock(world, basePos, 2, 7, 2, marshmallow);
		addBlock(world, basePos, 0, 8, 0, marshmallow);
		addBlock(world, basePos, 0, 8, 1, marshmallow);
		addBlock(world, basePos, 0, 8, 2, marshmallow);
		addBlock(world, basePos, 1, 8, 0, marshmallow);
		addBlock(world, basePos, 1, 8, 1, licorice);
		addBlock(world, basePos, 1, 8, 2, marshmallow);
		addBlock(world, basePos, 2, 8, 0, marshmallow);
		addBlock(world, basePos, 2, 8, 1, marshmallow);
		addBlock(world, basePos, 2, 8, 2, marshmallow);
		addBlock(world, basePos, 1, 9, 1, licorice);
		addBlock(world, basePos, 1, 10, 1, licorice);
		addBlock(world, basePos, 1, 11, 1, licorice);
		addBlock(world, basePos, 1, 12, 1, licorice);
		addBlock(world, basePos, 0, 13, 0, marshmallow);
		addBlock(world, basePos, 0, 13, 1, marshmallow);
		addBlock(world, basePos, 0, 13, 2, marshmallow);
		addBlock(world, basePos, 1, 13, 0, marshmallow);
		addBlock(world, basePos, 1, 13, 1, licorice);
		addBlock(world, basePos, 1, 13, 2, marshmallow);
		addBlock(world, basePos, 2, 13, 0, marshmallow);
		addBlock(world, basePos, 2, 13, 1, marshmallow);
		addBlock(world, basePos, 2, 13, 2, marshmallow);
		addBlock(world, basePos, 0, 14, 0, marshmallow);
		addBlock(world, basePos, 0, 14, 1, marshmallow);
		addBlock(world, basePos, 0, 14, 2, marshmallow);
		addBlock(world, basePos, 1, 14, 0, marshmallow);
		addBlock(world, basePos, 1, 14, 1, marshmallow);
		addBlock(world, basePos, 1, 14, 2, marshmallow);
		addBlock(world, basePos, 2, 14, 0, marshmallow);
		addBlock(world, basePos, 2, 14, 1, marshmallow);
		addBlock(world, basePos, 2, 14, 2, marshmallow);
		addBlock(world, basePos, 0, 15, 0, marshmallow);
		addBlock(world, basePos, 0, 15, 1, marshmallow);
		addBlock(world, basePos, 0, 15, 2, marshmallow);
		addBlock(world, basePos, 1, 15, 0, marshmallow);
		addBlock(world, basePos, 1, 15, 1, marshmallow);
		addBlock(world, basePos, 1, 15, 2, marshmallow);
		addBlock(world, basePos, 2, 15, 0, marshmallow);
		addBlock(world, basePos, 2, 15, 1, marshmallow);
		addBlock(world, basePos, 2, 15, 2, marshmallow);
		addBlock(world, basePos, 0, 16, 0, marshmallow);
		addBlock(world, basePos, 0, 16, 1, marshmallow);
		addBlock(world, basePos, 0, 16, 2, marshmallow);
		addBlock(world, basePos, 1, 16, 0, marshmallow);
		addBlock(world, basePos, 1, 16, 1, marshmallow);
		addBlock(world, basePos, 1, 16, 2, marshmallow);
		addBlock(world, basePos, 2, 16, 0, marshmallow);
		addBlock(world, basePos, 2, 16, 1, marshmallow);
		addBlock(world, basePos, 2, 16, 2, marshmallow);
	}
}
