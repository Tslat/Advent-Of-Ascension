package net.tslat.aoa3.structure.candyland;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class MallowTree1 extends AoAStructure { //StructureSize: 3x13x3
	private static final IBlockState marshmallow = BlockRegister.marshmallow.getDefaultState();
	private static final IBlockState licorice = BlockRegister.licorice.getDefaultState();

	public MallowTree1() {
		super("MallowTree1");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 1, 0, 1, licorice);
		addBlock(world, basePos, 1, 1, 1, licorice);
		addBlock(world, basePos, 1, 2, 1, licorice);
		addBlock(world, basePos, 1, 3, 1, licorice);
		addBlock(world, basePos, 1, 4, 1, licorice);
		addBlock(world, basePos, 1, 5, 1, licorice);
		addBlock(world, basePos, 1, 6, 1, licorice);
		addBlock(world, basePos, 1, 7, 1, licorice);
		addBlock(world, basePos, 1, 8, 1, licorice);
		addBlock(world, basePos, 0, 9, 0, marshmallow);
		addBlock(world, basePos, 0, 9, 1, marshmallow);
		addBlock(world, basePos, 0, 9, 2, marshmallow);
		addBlock(world, basePos, 1, 9, 0, marshmallow);
		addBlock(world, basePos, 1, 9, 1, licorice);
		addBlock(world, basePos, 1, 9, 2, marshmallow);
		addBlock(world, basePos, 2, 9, 0, marshmallow);
		addBlock(world, basePos, 2, 9, 1, marshmallow);
		addBlock(world, basePos, 2, 9, 2, marshmallow);
		addBlock(world, basePos, 0, 10, 0, marshmallow);
		addBlock(world, basePos, 0, 10, 1, marshmallow);
		addBlock(world, basePos, 0, 10, 2, marshmallow);
		addBlock(world, basePos, 1, 10, 0, marshmallow);
		addBlock(world, basePos, 1, 10, 1, marshmallow);
		addBlock(world, basePos, 1, 10, 2, marshmallow);
		addBlock(world, basePos, 2, 10, 0, marshmallow);
		addBlock(world, basePos, 2, 10, 1, marshmallow);
		addBlock(world, basePos, 2, 10, 2, marshmallow);
		addBlock(world, basePos, 0, 11, 0, marshmallow);
		addBlock(world, basePos, 0, 11, 1, marshmallow);
		addBlock(world, basePos, 0, 11, 2, marshmallow);
		addBlock(world, basePos, 1, 11, 0, marshmallow);
		addBlock(world, basePos, 1, 11, 1, marshmallow);
		addBlock(world, basePos, 1, 11, 2, marshmallow);
		addBlock(world, basePos, 2, 11, 0, marshmallow);
		addBlock(world, basePos, 2, 11, 1, marshmallow);
		addBlock(world, basePos, 2, 11, 2, marshmallow);
		addBlock(world, basePos, 0, 12, 0, marshmallow);
		addBlock(world, basePos, 0, 12, 1, marshmallow);
		addBlock(world, basePos, 0, 12, 2, marshmallow);
		addBlock(world, basePos, 1, 12, 0, marshmallow);
		addBlock(world, basePos, 1, 12, 1, marshmallow);
		addBlock(world, basePos, 1, 12, 2, marshmallow);
		addBlock(world, basePos, 2, 12, 0, marshmallow);
		addBlock(world, basePos, 2, 12, 1, marshmallow);
		addBlock(world, basePos, 2, 12, 2, marshmallow);
	}
}
