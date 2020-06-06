package net.tslat.aoa3.structure.runandor;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class RunicTree6 extends AoAStructure { //StructureSize: 9x11x9
	private static final IBlockState runicLeaves = BlockRegister.RUNIC_LEAVES.getDefaultState();
	private static final IBlockState runicLog = BlockRegister.RUNIC_LOG.getDefaultState();

	public RunicTree6() {
		super("RunicTree6");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 4, 0, 4, runicLog);
		addBlock(world, basePos, 4, 1, 4, runicLog);
		addBlock(world, basePos, 4, 2, 4, runicLog);
		addBlock(world, basePos, 4, 3, 4, runicLog);
		addBlock(world, basePos, 4, 4, 4, runicLog);
		addBlock(world, basePos, 4, 5, 4, runicLog);
		addBlock(world, basePos, 4, 6, 4, runicLog);
		addBlock(world, basePos, 2, 7, 4, runicLeaves);
		addBlock(world, basePos, 3, 7, 3, runicLeaves);
		addBlock(world, basePos, 3, 7, 4, runicLeaves);
		addBlock(world, basePos, 3, 7, 5, runicLeaves);
		addBlock(world, basePos, 4, 7, 2, runicLeaves);
		addBlock(world, basePos, 4, 7, 3, runicLeaves);
		addBlock(world, basePos, 4, 7, 4, runicLog);
		addBlock(world, basePos, 4, 7, 5, runicLeaves);
		addBlock(world, basePos, 4, 7, 6, runicLeaves);
		addBlock(world, basePos, 5, 7, 3, runicLeaves);
		addBlock(world, basePos, 5, 7, 4, runicLeaves);
		addBlock(world, basePos, 5, 7, 5, runicLeaves);
		addBlock(world, basePos, 6, 7, 4, runicLeaves);
		addBlock(world, basePos, 0, 8, 4, runicLog);
		addBlock(world, basePos, 1, 8, 4, runicLeaves);
		addBlock(world, basePos, 2, 8, 3, runicLeaves);
		addBlock(world, basePos, 2, 8, 4, runicLeaves);
		addBlock(world, basePos, 2, 8, 5, runicLeaves);
		addBlock(world, basePos, 3, 8, 2, runicLeaves);
		addBlock(world, basePos, 3, 8, 3, runicLeaves);
		addBlock(world, basePos, 3, 8, 4, runicLeaves);
		addBlock(world, basePos, 3, 8, 5, runicLeaves);
		addBlock(world, basePos, 3, 8, 6, runicLeaves);
		addBlock(world, basePos, 4, 8, 0, runicLog);
		addBlock(world, basePos, 4, 8, 1, runicLeaves);
		addBlock(world, basePos, 4, 8, 2, runicLeaves);
		addBlock(world, basePos, 4, 8, 3, runicLeaves);
		addBlock(world, basePos, 4, 8, 4, runicLog);
		addBlock(world, basePos, 4, 8, 5, runicLeaves);
		addBlock(world, basePos, 4, 8, 6, runicLeaves);
		addBlock(world, basePos, 4, 8, 7, runicLeaves);
		addBlock(world, basePos, 4, 8, 8, runicLog);
		addBlock(world, basePos, 5, 8, 2, runicLeaves);
		addBlock(world, basePos, 5, 8, 3, runicLeaves);
		addBlock(world, basePos, 5, 8, 4, runicLeaves);
		addBlock(world, basePos, 5, 8, 5, runicLeaves);
		addBlock(world, basePos, 5, 8, 6, runicLeaves);
		addBlock(world, basePos, 6, 8, 3, runicLeaves);
		addBlock(world, basePos, 6, 8, 4, runicLeaves);
		addBlock(world, basePos, 6, 8, 5, runicLeaves);
		addBlock(world, basePos, 7, 8, 4, runicLeaves);
		addBlock(world, basePos, 8, 8, 4, runicLog);
		addBlock(world, basePos, 0, 9, 4, runicLog);
		addBlock(world, basePos, 2, 9, 4, runicLeaves);
		addBlock(world, basePos, 3, 9, 3, runicLeaves);
		addBlock(world, basePos, 3, 9, 4, runicLeaves);
		addBlock(world, basePos, 3, 9, 5, runicLeaves);
		addBlock(world, basePos, 4, 9, 0, runicLog);
		addBlock(world, basePos, 4, 9, 2, runicLeaves);
		addBlock(world, basePos, 4, 9, 3, runicLeaves);
		addBlock(world, basePos, 4, 9, 4, runicLog);
		addBlock(world, basePos, 4, 9, 5, runicLeaves);
		addBlock(world, basePos, 4, 9, 6, runicLeaves);
		addBlock(world, basePos, 4, 9, 8, runicLog);
		addBlock(world, basePos, 5, 9, 3, runicLeaves);
		addBlock(world, basePos, 5, 9, 4, runicLeaves);
		addBlock(world, basePos, 5, 9, 5, runicLeaves);
		addBlock(world, basePos, 6, 9, 4, runicLeaves);
		addBlock(world, basePos, 8, 9, 4, runicLog);
		addBlock(world, basePos, 0, 10, 4, runicLog);
		addBlock(world, basePos, 1, 10, 4, runicLog);
		addBlock(world, basePos, 2, 10, 4, runicLog);
		addBlock(world, basePos, 3, 10, 4, runicLog);
		addBlock(world, basePos, 4, 10, 0, runicLog);
		addBlock(world, basePos, 4, 10, 1, runicLog);
		addBlock(world, basePos, 4, 10, 2, runicLog);
		addBlock(world, basePos, 4, 10, 3, runicLog);
		addBlock(world, basePos, 4, 10, 4, runicLog);
		addBlock(world, basePos, 4, 10, 5, runicLog);
		addBlock(world, basePos, 4, 10, 6, runicLog);
		addBlock(world, basePos, 4, 10, 7, runicLog);
		addBlock(world, basePos, 4, 10, 8, runicLog);
		addBlock(world, basePos, 5, 10, 4, runicLog);
		addBlock(world, basePos, 6, 10, 4, runicLog);
		addBlock(world, basePos, 7, 10, 4, runicLog);
		addBlock(world, basePos, 8, 10, 4, runicLog);
	}
}
