package net.tslat.aoa3.structure.barathos;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class BaronArch3 extends AoAStructure { //StructureSize: 5x4x1
	private static final IBlockState baronStone = BlockRegister.BARON_STONE.getDefaultState();

	public BaronArch3() {
		super("BaronArch3");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 0, baronStone);
		addBlock(world, basePos, 4, 0, 0, baronStone);
		addBlock(world, basePos, 0, 1, 0, baronStone);
		addBlock(world, basePos, 4, 1, 0, baronStone);
		addBlock(world, basePos, 0, 2, 0, baronStone);
		addBlock(world, basePos, 4, 2, 0, baronStone);
		addBlock(world, basePos, 0, 3, 0, baronStone);
		addBlock(world, basePos, 1, 3, 0, baronStone);
		addBlock(world, basePos, 2, 3, 0, baronStone);
		addBlock(world, basePos, 3, 3, 0, baronStone);
		addBlock(world, basePos, 4, 3, 0, baronStone);
	}
}
