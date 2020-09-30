package net.tslat.aoa3.structure.iromine;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class IrodustTree1 extends AoAStructure { //StructureSize: 7x10x7
	private static final IBlockState irodustLeaves = BlockRegister.IRODUST_LEAVES.getDefaultState();
	private static final IBlockState log = BlockRegister.IRO_LOG.getDefaultState();

	public IrodustTree1() {
		super("IrodustTree1");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 3, 0, 3, log);
		addBlock(world, basePos, 3, 1, 3, log);
		addBlock(world, basePos, 3, 2, 3, log);
		addBlock(world, basePos, 0, 3, 1, irodustLeaves);
		addBlock(world, basePos, 0, 3, 2, irodustLeaves);
		addBlock(world, basePos, 0, 3, 3, irodustLeaves);
		addBlock(world, basePos, 0, 3, 4, irodustLeaves);
		addBlock(world, basePos, 0, 3, 5, irodustLeaves);
		addBlock(world, basePos, 1, 3, 0, irodustLeaves);
		addBlock(world, basePos, 1, 3, 1, irodustLeaves);
		addBlock(world, basePos, 1, 3, 3, log);
		addBlock(world, basePos, 1, 3, 5, irodustLeaves);
		addBlock(world, basePos, 1, 3, 6, irodustLeaves);
		addBlock(world, basePos, 2, 3, 0, irodustLeaves);
		addBlock(world, basePos, 2, 3, 3, log);
		addBlock(world, basePos, 2, 3, 6, irodustLeaves);
		addBlock(world, basePos, 3, 3, 0, irodustLeaves);
		addBlock(world, basePos, 3, 3, 1, log);
		addBlock(world, basePos, 3, 3, 2, log);
		addBlock(world, basePos, 3, 3, 3, log);
		addBlock(world, basePos, 3, 3, 4, log);
		addBlock(world, basePos, 3, 3, 5, log);
		addBlock(world, basePos, 3, 3, 6, irodustLeaves);
		addBlock(world, basePos, 4, 3, 0, irodustLeaves);
		addBlock(world, basePos, 4, 3, 3, log);
		addBlock(world, basePos, 4, 3, 6, irodustLeaves);
		addBlock(world, basePos, 5, 3, 0, irodustLeaves);
		addBlock(world, basePos, 5, 3, 1, irodustLeaves);
		addBlock(world, basePos, 5, 3, 3, log);
		addBlock(world, basePos, 5, 3, 5, irodustLeaves);
		addBlock(world, basePos, 5, 3, 6, irodustLeaves);
		addBlock(world, basePos, 6, 3, 1, irodustLeaves);
		addBlock(world, basePos, 6, 3, 2, irodustLeaves);
		addBlock(world, basePos, 6, 3, 3, irodustLeaves);
		addBlock(world, basePos, 6, 3, 4, irodustLeaves);
		addBlock(world, basePos, 6, 3, 5, irodustLeaves);
		addBlock(world, basePos, 3, 4, 3, log);
		addBlock(world, basePos, 3, 5, 3, log);
		addBlock(world, basePos, 1, 6, 1, irodustLeaves);
		addBlock(world, basePos, 1, 6, 2, irodustLeaves);
		addBlock(world, basePos, 1, 6, 3, irodustLeaves);
		addBlock(world, basePos, 1, 6, 4, irodustLeaves);
		addBlock(world, basePos, 1, 6, 5, irodustLeaves);
		addBlock(world, basePos, 2, 6, 1, irodustLeaves);
		addBlock(world, basePos, 2, 6, 3, log);
		addBlock(world, basePos, 2, 6, 5, irodustLeaves);
		addBlock(world, basePos, 3, 6, 1, irodustLeaves);
		addBlock(world, basePos, 3, 6, 2, log);
		addBlock(world, basePos, 3, 6, 3, log);
		addBlock(world, basePos, 3, 6, 4, log);
		addBlock(world, basePos, 3, 6, 5, irodustLeaves);
		addBlock(world, basePos, 4, 6, 1, irodustLeaves);
		addBlock(world, basePos, 4, 6, 3, log);
		addBlock(world, basePos, 4, 6, 5, irodustLeaves);
		addBlock(world, basePos, 5, 6, 1, irodustLeaves);
		addBlock(world, basePos, 5, 6, 2, irodustLeaves);
		addBlock(world, basePos, 5, 6, 3, irodustLeaves);
		addBlock(world, basePos, 5, 6, 4, irodustLeaves);
		addBlock(world, basePos, 5, 6, 5, irodustLeaves);
		addBlock(world, basePos, 3, 7, 3, log);
		addBlock(world, basePos, 2, 8, 2, irodustLeaves);
		addBlock(world, basePos, 2, 8, 3, irodustLeaves);
		addBlock(world, basePos, 2, 8, 4, irodustLeaves);
		addBlock(world, basePos, 3, 8, 2, irodustLeaves);
		addBlock(world, basePos, 3, 8, 3, log);
		addBlock(world, basePos, 3, 8, 4, irodustLeaves);
		addBlock(world, basePos, 4, 8, 2, irodustLeaves);
		addBlock(world, basePos, 4, 8, 3, irodustLeaves);
		addBlock(world, basePos, 4, 8, 4, irodustLeaves);
		addBlock(world, basePos, 2, 9, 2, irodustLeaves);
		addBlock(world, basePos, 2, 9, 3, irodustLeaves);
		addBlock(world, basePos, 2, 9, 4, irodustLeaves);
		addBlock(world, basePos, 3, 9, 2, irodustLeaves);
		addBlock(world, basePos, 3, 9, 3, irodustLeaves);
		addBlock(world, basePos, 3, 9, 4, irodustLeaves);
		addBlock(world, basePos, 4, 9, 2, irodustLeaves);
		addBlock(world, basePos, 4, 9, 3, irodustLeaves);
		addBlock(world, basePos, 4, 9, 4, irodustLeaves);
	}
}
