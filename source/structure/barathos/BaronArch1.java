package net.tslat.aoa3.structure.barathos;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class BaronArch1 extends AoAStructure { //StructureSize: 1x3x5
	private static final IBlockState baronStone = BlockRegister.BARON_STONE.getDefaultState();

	public BaronArch1() {
		super("BaronArch1");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 0, baronStone);
		addBlock(world, basePos, 0, 0, 4, baronStone);
		addBlock(world, basePos, 0, 1, 0, baronStone);
		addBlock(world, basePos, 0, 1, 1, baronStone);
		addBlock(world, basePos, 0, 1, 3, baronStone);
		addBlock(world, basePos, 0, 1, 4, baronStone);
		addBlock(world, basePos, 0, 2, 1, baronStone);
		addBlock(world, basePos, 0, 2, 2, baronStone);
		addBlock(world, basePos, 0, 2, 3, baronStone);
	}
}
