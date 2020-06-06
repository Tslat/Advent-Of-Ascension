package net.tslat.aoa3.structure.haven;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class BlueHavenTree extends AoAStructure { //StructureSize: 5x14x5
	private static final IBlockState blueLeaves = BlockRegister.BLUE_HAVEN_LEAVES.getDefaultState();
	private static final IBlockState log = Blocks.LOG.getDefaultState();

	public BlueHavenTree() {
		super("BlueHavenTree");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 2, 0, 2, log);
		addBlock(world, basePos, 2, 1, 2, log);
		addBlock(world, basePos, 2, 2, 2, log);
		addBlock(world, basePos, 2, 3, 2, log);
		addBlock(world, basePos, 1, 4, 2, blueLeaves);
		addBlock(world, basePos, 2, 4, 1, blueLeaves);
		addBlock(world, basePos, 2, 4, 2, log);
		addBlock(world, basePos, 2, 4, 3, blueLeaves);
		addBlock(world, basePos, 3, 4, 2, blueLeaves);
		addBlock(world, basePos, 0, 5, 2, blueLeaves);
		addBlock(world, basePos, 1, 5, 1, blueLeaves);
		addBlock(world, basePos, 1, 5, 2, blueLeaves);
		addBlock(world, basePos, 1, 5, 3, blueLeaves);
		addBlock(world, basePos, 2, 5, 0, blueLeaves);
		addBlock(world, basePos, 2, 5, 1, blueLeaves);
		addBlock(world, basePos, 2, 5, 2, log);
		addBlock(world, basePos, 2, 5, 3, blueLeaves);
		addBlock(world, basePos, 2, 5, 4, blueLeaves);
		addBlock(world, basePos, 3, 5, 1, blueLeaves);
		addBlock(world, basePos, 3, 5, 2, blueLeaves);
		addBlock(world, basePos, 3, 5, 3, blueLeaves);
		addBlock(world, basePos, 4, 5, 2, blueLeaves);
		addBlock(world, basePos, 0, 6, 2, blueLeaves);
		addBlock(world, basePos, 1, 6, 1, blueLeaves);
		addBlock(world, basePos, 1, 6, 2, blueLeaves);
		addBlock(world, basePos, 1, 6, 3, blueLeaves);
		addBlock(world, basePos, 2, 6, 0, blueLeaves);
		addBlock(world, basePos, 2, 6, 1, blueLeaves);
		addBlock(world, basePos, 2, 6, 2, log);
		addBlock(world, basePos, 2, 6, 3, blueLeaves);
		addBlock(world, basePos, 2, 6, 4, blueLeaves);
		addBlock(world, basePos, 3, 6, 1, blueLeaves);
		addBlock(world, basePos, 3, 6, 2, blueLeaves);
		addBlock(world, basePos, 3, 6, 3, blueLeaves);
		addBlock(world, basePos, 4, 6, 2, blueLeaves);
		addBlock(world, basePos, 1, 7, 2, blueLeaves);
		addBlock(world, basePos, 2, 7, 1, blueLeaves);
		addBlock(world, basePos, 2, 7, 2, log);
		addBlock(world, basePos, 2, 7, 3, blueLeaves);
		addBlock(world, basePos, 3, 7, 2, blueLeaves);
		addBlock(world, basePos, 2, 8, 2, log);
		addBlock(world, basePos, 1, 9, 2, blueLeaves);
		addBlock(world, basePos, 2, 9, 1, blueLeaves);
		addBlock(world, basePos, 2, 9, 2, log);
		addBlock(world, basePos, 2, 9, 3, blueLeaves);
		addBlock(world, basePos, 3, 9, 2, blueLeaves);
		addBlock(world, basePos, 1, 10, 1, blueLeaves);
		addBlock(world, basePos, 1, 10, 2, blueLeaves);
		addBlock(world, basePos, 1, 10, 3, blueLeaves);
		addBlock(world, basePos, 2, 10, 1, blueLeaves);
		addBlock(world, basePos, 2, 10, 2, blueLeaves);
		addBlock(world, basePos, 2, 10, 3, blueLeaves);
		addBlock(world, basePos, 3, 10, 1, blueLeaves);
		addBlock(world, basePos, 3, 10, 2, blueLeaves);
		addBlock(world, basePos, 3, 10, 3, blueLeaves);
		addBlock(world, basePos, 0, 11, 0, blueLeaves);
		addBlock(world, basePos, 0, 11, 1, blueLeaves);
		addBlock(world, basePos, 0, 11, 2, blueLeaves);
		addBlock(world, basePos, 0, 11, 3, blueLeaves);
		addBlock(world, basePos, 0, 11, 4, blueLeaves);
		addBlock(world, basePos, 1, 11, 0, blueLeaves);
		addBlock(world, basePos, 1, 11, 1, blueLeaves);
		addBlock(world, basePos, 1, 11, 2, blueLeaves);
		addBlock(world, basePos, 1, 11, 3, blueLeaves);
		addBlock(world, basePos, 1, 11, 4, blueLeaves);
		addBlock(world, basePos, 2, 11, 0, blueLeaves);
		addBlock(world, basePos, 2, 11, 1, blueLeaves);
		addBlock(world, basePos, 2, 11, 2, blueLeaves);
		addBlock(world, basePos, 2, 11, 3, blueLeaves);
		addBlock(world, basePos, 2, 11, 4, blueLeaves);
		addBlock(world, basePos, 3, 11, 0, blueLeaves);
		addBlock(world, basePos, 3, 11, 1, blueLeaves);
		addBlock(world, basePos, 3, 11, 2, blueLeaves);
		addBlock(world, basePos, 3, 11, 3, blueLeaves);
		addBlock(world, basePos, 3, 11, 4, blueLeaves);
		addBlock(world, basePos, 4, 11, 0, blueLeaves);
		addBlock(world, basePos, 4, 11, 1, blueLeaves);
		addBlock(world, basePos, 4, 11, 2, blueLeaves);
		addBlock(world, basePos, 4, 11, 3, blueLeaves);
		addBlock(world, basePos, 4, 11, 4, blueLeaves);
		addBlock(world, basePos, 1, 12, 1, blueLeaves);
		addBlock(world, basePos, 1, 12, 2, blueLeaves);
		addBlock(world, basePos, 1, 12, 3, blueLeaves);
		addBlock(world, basePos, 2, 12, 1, blueLeaves);
		addBlock(world, basePos, 2, 12, 2, blueLeaves);
		addBlock(world, basePos, 2, 12, 3, blueLeaves);
		addBlock(world, basePos, 3, 12, 1, blueLeaves);
		addBlock(world, basePos, 3, 12, 2, blueLeaves);
		addBlock(world, basePos, 3, 12, 3, blueLeaves);
		addBlock(world, basePos, 2, 13, 2, blueLeaves);
	}
}
