package net.tslat.aoa3.structure.candyland;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class MallowTree2 extends AoAStructure { //StructureSize: 3x10x3
	private static final IBlockState marshmallow = BlockRegister.marshmallow.getDefaultState();
	private static final IBlockState licorice = BlockRegister.licorice.getDefaultState();

	public MallowTree2() {
		super("MallowTree2");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 1, 0, 1, licorice);
		addBlock(world, basePos, 1, 1, 1, licorice);
		addBlock(world, basePos, 1, 2, 1, licorice);
		addBlock(world, basePos, 1, 3, 1, licorice);
		addBlock(world, basePos, 1, 4, 1, licorice);
		addBlock(world, basePos, 1, 5, 1, licorice);
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
		addBlock(world, basePos, 1, 7, 1, marshmallow);
		addBlock(world, basePos, 1, 7, 2, marshmallow);
		addBlock(world, basePos, 2, 7, 0, marshmallow);
		addBlock(world, basePos, 2, 7, 1, marshmallow);
		addBlock(world, basePos, 2, 7, 2, marshmallow);
		addBlock(world, basePos, 0, 8, 0, marshmallow);
		addBlock(world, basePos, 0, 8, 1, marshmallow);
		addBlock(world, basePos, 0, 8, 2, marshmallow);
		addBlock(world, basePos, 1, 8, 0, marshmallow);
		addBlock(world, basePos, 1, 8, 1, marshmallow);
		addBlock(world, basePos, 1, 8, 2, marshmallow);
		addBlock(world, basePos, 2, 8, 0, marshmallow);
		addBlock(world, basePos, 2, 8, 1, marshmallow);
		addBlock(world, basePos, 2, 8, 2, marshmallow);
		addBlock(world, basePos, 0, 9, 0, marshmallow);
		addBlock(world, basePos, 0, 9, 1, marshmallow);
		addBlock(world, basePos, 0, 9, 2, marshmallow);
		addBlock(world, basePos, 1, 9, 0, marshmallow);
		addBlock(world, basePos, 1, 9, 1, marshmallow);
		addBlock(world, basePos, 1, 9, 2, marshmallow);
		addBlock(world, basePos, 2, 9, 0, marshmallow);
		addBlock(world, basePos, 2, 9, 1, marshmallow);
		addBlock(world, basePos, 2, 9, 2, marshmallow);
	}
}
