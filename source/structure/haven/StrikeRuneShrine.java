package net.tslat.aoa3.structure.haven;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class StrikeRuneShrine extends AoAStructure { //StructureSize: 7x4x7
	private static final IBlockState darkBricks = BlockRegister.bricksDark.getDefaultState();
	private static final IBlockState darkBrickSlab = BlockRegister.slabDarkBricks.getHalfBlock().getDefaultState().withProperty(BlockSlab.HALF, BlockSlab.EnumBlockHalf.BOTTOM);
	private static final IBlockState post = BlockRegister.runePostStrike.getDefaultState();

	public StrikeRuneShrine() {
		super("StrikeRuneShrine");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 0, darkBricks);
		addBlock(world, basePos, 0, 0, 1, darkBricks);
		addBlock(world, basePos, 0, 0, 2, darkBricks);
		addBlock(world, basePos, 0, 0, 3, darkBricks);
		addBlock(world, basePos, 0, 0, 4, darkBricks);
		addBlock(world, basePos, 0, 0, 5, darkBricks);
		addBlock(world, basePos, 0, 0, 6, darkBricks);
		addBlock(world, basePos, 1, 0, 0, darkBricks);
		addBlock(world, basePos, 1, 0, 1, darkBricks);
		addBlock(world, basePos, 1, 0, 2, darkBricks);
		addBlock(world, basePos, 1, 0, 3, darkBricks);
		addBlock(world, basePos, 1, 0, 4, darkBricks);
		addBlock(world, basePos, 1, 0, 5, darkBricks);
		addBlock(world, basePos, 1, 0, 6, darkBricks);
		addBlock(world, basePos, 2, 0, 0, darkBricks);
		addBlock(world, basePos, 2, 0, 1, darkBricks);
		addBlock(world, basePos, 2, 0, 2, darkBricks);
		addBlock(world, basePos, 2, 0, 3, darkBricks);
		addBlock(world, basePos, 2, 0, 4, darkBricks);
		addBlock(world, basePos, 2, 0, 5, darkBricks);
		addBlock(world, basePos, 2, 0, 6, darkBricks);
		addBlock(world, basePos, 3, 0, 0, darkBricks);
		addBlock(world, basePos, 3, 0, 1, darkBricks);
		addBlock(world, basePos, 3, 0, 2, darkBricks);
		addBlock(world, basePos, 3, 0, 3, darkBrickSlab);
		addBlock(world, basePos, 3, 0, 4, darkBricks);
		addBlock(world, basePos, 3, 0, 5, darkBricks);
		addBlock(world, basePos, 3, 0, 6, darkBricks);
		addBlock(world, basePos, 4, 0, 0, darkBricks);
		addBlock(world, basePos, 4, 0, 1, darkBricks);
		addBlock(world, basePos, 4, 0, 2, darkBricks);
		addBlock(world, basePos, 4, 0, 3, darkBricks);
		addBlock(world, basePos, 4, 0, 4, darkBricks);
		addBlock(world, basePos, 4, 0, 5, darkBricks);
		addBlock(world, basePos, 4, 0, 6, darkBricks);
		addBlock(world, basePos, 5, 0, 0, darkBricks);
		addBlock(world, basePos, 5, 0, 1, darkBricks);
		addBlock(world, basePos, 5, 0, 2, darkBricks);
		addBlock(world, basePos, 5, 0, 3, darkBricks);
		addBlock(world, basePos, 5, 0, 4, darkBricks);
		addBlock(world, basePos, 5, 0, 5, darkBricks);
		addBlock(world, basePos, 5, 0, 6, darkBricks);
		addBlock(world, basePos, 6, 0, 0, darkBricks);
		addBlock(world, basePos, 6, 0, 1, darkBricks);
		addBlock(world, basePos, 6, 0, 2, darkBricks);
		addBlock(world, basePos, 6, 0, 3, darkBricks);
		addBlock(world, basePos, 6, 0, 4, darkBricks);
		addBlock(world, basePos, 6, 0, 5, darkBricks);
		addBlock(world, basePos, 6, 0, 6, darkBricks);
		addBlock(world, basePos, 0, 1, 0, darkBricks);
		addBlock(world, basePos, 0, 1, 6, darkBricks);
		addBlock(world, basePos, 6, 1, 0, darkBricks);
		addBlock(world, basePos, 6, 1, 6, darkBricks);
		addBlock(world, basePos, 0, 2, 0, darkBricks);
		addBlock(world, basePos, 0, 2, 6, darkBricks);
		addBlock(world, basePos, 6, 2, 0, darkBricks);
		addBlock(world, basePos, 6, 2, 6, darkBricks);
		addBlock(world, basePos, 0, 3, 0, post);
		addBlock(world, basePos, 0, 3, 6, post);
		addBlock(world, basePos, 6, 3, 0, post);
		addBlock(world, basePos, 6, 3, 6, post);
	}
}
