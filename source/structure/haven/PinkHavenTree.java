package net.tslat.aoa3.structure.haven;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class PinkHavenTree extends AoAStructure { //StructureSize: 7x10x7
	private static final IBlockState pinkLeaves = BlockRegister.PINK_HAVEN_LEAVES.getDefaultState();
	private static final IBlockState log = Blocks.LOG.getDefaultState();

	public PinkHavenTree() {
		super("PinkHavenTree");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 3, 0, 3, log);
		addBlock(world, basePos, 3, 1, 3, log);
		addBlock(world, basePos, 3, 2, 3, log);
		addBlock(world, basePos, 3, 3, 3, log);
		addBlock(world, basePos, 0, 4, 3, pinkLeaves);
		addBlock(world, basePos, 2, 4, 3, pinkLeaves);
		addBlock(world, basePos, 3, 4, 0, pinkLeaves);
		addBlock(world, basePos, 3, 4, 2, pinkLeaves);
		addBlock(world, basePos, 3, 4, 3, log);
		addBlock(world, basePos, 3, 4, 4, pinkLeaves);
		addBlock(world, basePos, 3, 4, 6, pinkLeaves);
		addBlock(world, basePos, 4, 4, 3, pinkLeaves);
		addBlock(world, basePos, 6, 4, 3, pinkLeaves);
		addBlock(world, basePos, 0, 5, 3, pinkLeaves);
		addBlock(world, basePos, 1, 5, 3, pinkLeaves);
		addBlock(world, basePos, 2, 5, 2, pinkLeaves);
		addBlock(world, basePos, 2, 5, 3, pinkLeaves);
		addBlock(world, basePos, 2, 5, 4, pinkLeaves);
		addBlock(world, basePos, 3, 5, 0, pinkLeaves);
		addBlock(world, basePos, 3, 5, 1, pinkLeaves);
		addBlock(world, basePos, 3, 5, 2, pinkLeaves);
		addBlock(world, basePos, 3, 5, 3, log);
		addBlock(world, basePos, 3, 5, 4, pinkLeaves);
		addBlock(world, basePos, 3, 5, 5, pinkLeaves);
		addBlock(world, basePos, 3, 5, 6, pinkLeaves);
		addBlock(world, basePos, 4, 5, 2, pinkLeaves);
		addBlock(world, basePos, 4, 5, 3, pinkLeaves);
		addBlock(world, basePos, 4, 5, 4, pinkLeaves);
		addBlock(world, basePos, 5, 5, 3, pinkLeaves);
		addBlock(world, basePos, 6, 5, 3, pinkLeaves);
		addBlock(world, basePos, 0, 6, 3, pinkLeaves);
		addBlock(world, basePos, 1, 6, 2, pinkLeaves);
		addBlock(world, basePos, 1, 6, 3, pinkLeaves);
		addBlock(world, basePos, 1, 6, 4, pinkLeaves);
		addBlock(world, basePos, 2, 6, 1, pinkLeaves);
		addBlock(world, basePos, 2, 6, 2, pinkLeaves);
		addBlock(world, basePos, 2, 6, 3, pinkLeaves);
		addBlock(world, basePos, 2, 6, 4, pinkLeaves);
		addBlock(world, basePos, 2, 6, 5, pinkLeaves);
		addBlock(world, basePos, 3, 6, 0, pinkLeaves);
		addBlock(world, basePos, 3, 6, 1, pinkLeaves);
		addBlock(world, basePos, 3, 6, 2, pinkLeaves);
		addBlock(world, basePos, 3, 6, 3, log);
		addBlock(world, basePos, 3, 6, 4, pinkLeaves);
		addBlock(world, basePos, 3, 6, 5, pinkLeaves);
		addBlock(world, basePos, 3, 6, 6, pinkLeaves);
		addBlock(world, basePos, 4, 6, 1, pinkLeaves);
		addBlock(world, basePos, 4, 6, 2, pinkLeaves);
		addBlock(world, basePos, 4, 6, 3, pinkLeaves);
		addBlock(world, basePos, 4, 6, 4, pinkLeaves);
		addBlock(world, basePos, 4, 6, 5, pinkLeaves);
		addBlock(world, basePos, 5, 6, 2, pinkLeaves);
		addBlock(world, basePos, 5, 6, 3, pinkLeaves);
		addBlock(world, basePos, 5, 6, 4, pinkLeaves);
		addBlock(world, basePos, 6, 6, 3, pinkLeaves);
		addBlock(world, basePos, 1, 7, 3, pinkLeaves);
		addBlock(world, basePos, 2, 7, 2, pinkLeaves);
		addBlock(world, basePos, 2, 7, 3, pinkLeaves);
		addBlock(world, basePos, 2, 7, 4, pinkLeaves);
		addBlock(world, basePos, 3, 7, 1, pinkLeaves);
		addBlock(world, basePos, 3, 7, 2, pinkLeaves);
		addBlock(world, basePos, 3, 7, 3, pinkLeaves);
		addBlock(world, basePos, 3, 7, 4, pinkLeaves);
		addBlock(world, basePos, 3, 7, 5, pinkLeaves);
		addBlock(world, basePos, 4, 7, 2, pinkLeaves);
		addBlock(world, basePos, 4, 7, 3, pinkLeaves);
		addBlock(world, basePos, 4, 7, 4, pinkLeaves);
		addBlock(world, basePos, 5, 7, 3, pinkLeaves);
		addBlock(world, basePos, 2, 8, 3, pinkLeaves);
		addBlock(world, basePos, 3, 8, 2, pinkLeaves);
		addBlock(world, basePos, 4, 8, 3, pinkLeaves);
		addBlock(world, basePos, 3, 8, 4, pinkLeaves);
		addBlock(world, basePos, 3, 9, 3, pinkLeaves);
	}
}
