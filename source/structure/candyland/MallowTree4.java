package net.tslat.aoa3.structure.candyland;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class MallowTree4 extends AoAStructure { //StructureSize: 3x14x3
	private static final IBlockState marshmallow = BlockRegister.marshmallow.getDefaultState();
	private static final IBlockState licorice = BlockRegister.licorice.getDefaultState();

	public MallowTree4() {
		super("MallowTree4");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 1, 0, 1, licorice);
		addBlock(world, basePos, 1, 1, 1, licorice);
		addBlock(world, basePos, 0, 2, 0, marshmallow);
		addBlock(world, basePos, 0, 2, 1, marshmallow);
		addBlock(world, basePos, 0, 2, 2, marshmallow);
		addBlock(world, basePos, 1, 2, 0, marshmallow);
		addBlock(world, basePos, 1, 2, 1, licorice);
		addBlock(world, basePos, 1, 2, 2, marshmallow);
		addBlock(world, basePos, 2, 2, 0, marshmallow);
		addBlock(world, basePos, 2, 2, 1, marshmallow);
		addBlock(world, basePos, 2, 2, 2, marshmallow);
		addBlock(world, basePos, 0, 3, 0, marshmallow);
		addBlock(world, basePos, 0, 3, 1, marshmallow);
		addBlock(world, basePos, 0, 3, 2, marshmallow);
		addBlock(world, basePos, 1, 3, 0, marshmallow);
		addBlock(world, basePos, 1, 3, 1, licorice);
		addBlock(world, basePos, 1, 3, 2, marshmallow);
		addBlock(world, basePos, 2, 3, 0, marshmallow);
		addBlock(world, basePos, 2, 3, 1, marshmallow);
		addBlock(world, basePos, 2, 3, 2, marshmallow);
		addBlock(world, basePos, 0, 4, 0, marshmallow);
		addBlock(world, basePos, 0, 4, 1, marshmallow);
		addBlock(world, basePos, 0, 4, 2, marshmallow);
		addBlock(world, basePos, 1, 4, 0, marshmallow);
		addBlock(world, basePos, 1, 4, 1, licorice);
		addBlock(world, basePos, 1, 4, 2, marshmallow);
		addBlock(world, basePos, 2, 4, 0, marshmallow);
		addBlock(world, basePos, 2, 4, 1, marshmallow);
		addBlock(world, basePos, 2, 4, 2, marshmallow);
		addBlock(world, basePos, 0, 5, 0, marshmallow);
		addBlock(world, basePos, 0, 5, 1, marshmallow);
		addBlock(world, basePos, 0, 5, 2, marshmallow);
		addBlock(world, basePos, 1, 5, 0, marshmallow);
		addBlock(world, basePos, 1, 5, 1, licorice);
		addBlock(world, basePos, 1, 5, 2, marshmallow);
		addBlock(world, basePos, 2, 5, 0, marshmallow);
		addBlock(world, basePos, 2, 5, 1, marshmallow);
		addBlock(world, basePos, 2, 5, 2, marshmallow);
		addBlock(world, basePos, 1, 6, 1, licorice);
		addBlock(world, basePos, 1, 7, 1, licorice);
		addBlock(world, basePos, 1, 8, 1, licorice);
		addBlock(world, basePos, 1, 9, 1, licorice);
		addBlock(world, basePos, 0, 10, 0, marshmallow);
		addBlock(world, basePos, 0, 10, 1, marshmallow);
		addBlock(world, basePos, 0, 10, 2, marshmallow);
		addBlock(world, basePos, 1, 10, 0, marshmallow);
		addBlock(world, basePos, 1, 10, 1, licorice);
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
		addBlock(world, basePos, 0, 13, 0, marshmallow);
		addBlock(world, basePos, 0, 13, 1, marshmallow);
		addBlock(world, basePos, 0, 13, 2, marshmallow);
		addBlock(world, basePos, 1, 13, 0, marshmallow);
		addBlock(world, basePos, 1, 13, 1, marshmallow);
		addBlock(world, basePos, 1, 13, 2, marshmallow);
		addBlock(world, basePos, 2, 13, 0, marshmallow);
		addBlock(world, basePos, 2, 13, 1, marshmallow);
		addBlock(world, basePos, 2, 13, 2, marshmallow);
	}
}
