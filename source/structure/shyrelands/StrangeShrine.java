package net.tslat.aoa3.structure.shyrelands;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class StrangeShrine extends AoAStructure { //StructureSize: 14x5x14
	private static final IBlockState whiteShyreBricks = BlockRegister.WHITE_SHYRE_BRICKS.getDefaultState();
	private static final IBlockState yellowShyreBricks = BlockRegister.YELLOW_SHYRE_BRICKS.getDefaultState();
	private static final IBlockState shyreLeaves = BlockRegister.SHYRE_LEAVES.getDefaultState();
	private static final IBlockState strangeBlock = BlockRegister.STRANGE_BLOCK.getDefaultState();
	private static final IBlockState shyreLog = BlockRegister.SHYRE_LOG.getDefaultState();

	public StrangeShrine() {
		super("StrangeShrine");
	}

	@Override
	protected boolean replacesBlocks() {
		return true;
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 0, yellowShyreBricks);
		addBlock(world, basePos, 0, 0, 1, whiteShyreBricks);
		addBlock(world, basePos, 0, 0, 2, whiteShyreBricks);
		addBlock(world, basePos, 0, 0, 3, whiteShyreBricks);
		addBlock(world, basePos, 0, 0, 4, yellowShyreBricks);
		addBlock(world, basePos, 0, 0, 9, yellowShyreBricks);
		addBlock(world, basePos, 0, 0, 10, whiteShyreBricks);
		addBlock(world, basePos, 0, 0, 11, whiteShyreBricks);
		addBlock(world, basePos, 0, 0, 12, whiteShyreBricks);
		addBlock(world, basePos, 0, 0, 13, yellowShyreBricks);
		addBlock(world, basePos, 1, 0, 0, whiteShyreBricks);
		addBlock(world, basePos, 1, 0, 13, whiteShyreBricks);
		addBlock(world, basePos, 2, 0, 0, whiteShyreBricks);
		addBlock(world, basePos, 2, 0, 2, shyreLog);
		addBlock(world, basePos, 2, 0, 11, shyreLog);
		addBlock(world, basePos, 2, 0, 13, whiteShyreBricks);
		addBlock(world, basePos, 3, 0, 0, whiteShyreBricks);
		addBlock(world, basePos, 3, 0, 13, whiteShyreBricks);
		addBlock(world, basePos, 4, 0, 0, yellowShyreBricks);
		addBlock(world, basePos, 4, 0, 4, shyreLog);
		addBlock(world, basePos, 4, 0, 9, shyreLog);
		addBlock(world, basePos, 4, 0, 13, yellowShyreBricks);
		addBlock(world, basePos, 6, 0, 6, strangeBlock);
		addBlock(world, basePos, 6, 0, 7, strangeBlock);
		addBlock(world, basePos, 7, 0, 6, strangeBlock);
		addBlock(world, basePos, 7, 0, 7, strangeBlock);
		addBlock(world, basePos, 9, 0, 0, yellowShyreBricks);
		addBlock(world, basePos, 9, 0, 4, shyreLog);
		addBlock(world, basePos, 9, 0, 9, shyreLog);
		addBlock(world, basePos, 9, 0, 13, yellowShyreBricks);
		addBlock(world, basePos, 10, 0, 0, whiteShyreBricks);
		addBlock(world, basePos, 10, 0, 13, whiteShyreBricks);
		addBlock(world, basePos, 11, 0, 0, whiteShyreBricks);
		addBlock(world, basePos, 11, 0, 2, shyreLog);
		addBlock(world, basePos, 11, 0, 11, shyreLog);
		addBlock(world, basePos, 11, 0, 13, whiteShyreBricks);
		addBlock(world, basePos, 12, 0, 0, whiteShyreBricks);
		addBlock(world, basePos, 12, 0, 13, whiteShyreBricks);
		addBlock(world, basePos, 13, 0, 0, yellowShyreBricks);
		addBlock(world, basePos, 13, 0, 1, whiteShyreBricks);
		addBlock(world, basePos, 13, 0, 2, whiteShyreBricks);
		addBlock(world, basePos, 13, 0, 3, whiteShyreBricks);
		addBlock(world, basePos, 13, 0, 4, yellowShyreBricks);
		addBlock(world, basePos, 13, 0, 9, yellowShyreBricks);
		addBlock(world, basePos, 13, 0, 10, whiteShyreBricks);
		addBlock(world, basePos, 13, 0, 11, whiteShyreBricks);
		addBlock(world, basePos, 13, 0, 12, whiteShyreBricks);
		addBlock(world, basePos, 13, 0, 13, yellowShyreBricks);
		addBlock(world, basePos, 2, 1, 2, shyreLog);
		addBlock(world, basePos, 2, 1, 11, shyreLog);
		addBlock(world, basePos, 4, 1, 4, shyreLog);
		addBlock(world, basePos, 4, 1, 9, shyreLog);
		addBlock(world, basePos, 9, 1, 4, shyreLog);
		addBlock(world, basePos, 9, 1, 9, shyreLog);
		addBlock(world, basePos, 11, 1, 2, shyreLog);
		addBlock(world, basePos, 11, 1, 11, shyreLog);
		addBlock(world, basePos, 2, 2, 2, shyreLog);
		addBlock(world, basePos, 2, 2, 11, shyreLog);
		addBlock(world, basePos, 4, 2, 4, shyreLog);
		addBlock(world, basePos, 4, 2, 9, shyreLog);
		addBlock(world, basePos, 9, 2, 4, shyreLog);
		addBlock(world, basePos, 9, 2, 9, shyreLog);
		addBlock(world, basePos, 11, 2, 2, shyreLog);
		addBlock(world, basePos, 11, 2, 11, shyreLog);
		addBlock(world, basePos, 2, 3, 2, shyreLog);
		addBlock(world, basePos, 2, 3, 11, shyreLog);
		addBlock(world, basePos, 4, 3, 4, shyreLeaves);
		addBlock(world, basePos, 4, 3, 9, shyreLeaves);
		addBlock(world, basePos, 9, 3, 4, shyreLeaves);
		addBlock(world, basePos, 9, 3, 9, shyreLeaves);
		addBlock(world, basePos, 11, 3, 2, shyreLog);
		addBlock(world, basePos, 11, 3, 11, shyreLog);
		addBlock(world, basePos, 2, 4, 2, shyreLeaves);
		addBlock(world, basePos, 2, 4, 11, shyreLeaves);
		addBlock(world, basePos, 11, 4, 2, shyreLeaves);
		addBlock(world, basePos, 11, 4, 11, shyreLeaves);
	}
}
