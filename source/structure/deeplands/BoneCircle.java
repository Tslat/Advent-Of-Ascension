package net.tslat.aoa3.structure.deeplands;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class BoneCircle extends AoAStructure { //StructureSize: 7x4x7
	private static final IBlockState skeletalBlock = BlockRegister.skeletalBlock.getDefaultState();

	public BoneCircle() {
		super("BoneCircle");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 2, skeletalBlock);
		addBlock(world, basePos, 0, 0, 4, skeletalBlock);
		addBlock(world, basePos, 1, 0, 1, skeletalBlock);
		addBlock(world, basePos, 1, 0, 2, skeletalBlock);
		addBlock(world, basePos, 1, 0, 3, skeletalBlock);
		addBlock(world, basePos, 1, 0, 4, skeletalBlock);
		addBlock(world, basePos, 1, 0, 5, skeletalBlock);
		addBlock(world, basePos, 2, 0, 0, skeletalBlock);
		addBlock(world, basePos, 2, 0, 1, skeletalBlock);
		addBlock(world, basePos, 2, 0, 2, skeletalBlock);
		addBlock(world, basePos, 2, 0, 3, skeletalBlock);
		addBlock(world, basePos, 2, 0, 4, skeletalBlock);
		addBlock(world, basePos, 2, 0, 5, skeletalBlock);
		addBlock(world, basePos, 2, 0, 6, skeletalBlock);
		addBlock(world, basePos, 3, 0, 1, skeletalBlock);
		addBlock(world, basePos, 3, 0, 2, skeletalBlock);
		addBlock(world, basePos, 3, 0, 3, skeletalBlock);
		addBlock(world, basePos, 3, 0, 4, skeletalBlock);
		addBlock(world, basePos, 3, 0, 5, skeletalBlock);
		addBlock(world, basePos, 4, 0, 0, skeletalBlock);
		addBlock(world, basePos, 4, 0, 1, skeletalBlock);
		addBlock(world, basePos, 4, 0, 2, skeletalBlock);
		addBlock(world, basePos, 4, 0, 3, skeletalBlock);
		addBlock(world, basePos, 4, 0, 4, skeletalBlock);
		addBlock(world, basePos, 4, 0, 5, skeletalBlock);
		addBlock(world, basePos, 4, 0, 6, skeletalBlock);
		addBlock(world, basePos, 5, 0, 1, skeletalBlock);
		addBlock(world, basePos, 5, 0, 2, skeletalBlock);
		addBlock(world, basePos, 5, 0, 3, skeletalBlock);
		addBlock(world, basePos, 5, 0, 4, skeletalBlock);
		addBlock(world, basePos, 5, 0, 5, skeletalBlock);
		addBlock(world, basePos, 6, 0, 2, skeletalBlock);
		addBlock(world, basePos, 6, 0, 4, skeletalBlock);
		addBlock(world, basePos, 0, 1, 2, skeletalBlock);
		addBlock(world, basePos, 0, 1, 4, skeletalBlock);
		addBlock(world, basePos, 2, 1, 0, skeletalBlock);
		addBlock(world, basePos, 2, 1, 6, skeletalBlock);
		addBlock(world, basePos, 4, 1, 0, skeletalBlock);
		addBlock(world, basePos, 4, 1, 6, skeletalBlock);
		addBlock(world, basePos, 6, 1, 2, skeletalBlock);
		addBlock(world, basePos, 6, 1, 4, skeletalBlock);
		addBlock(world, basePos, 0, 2, 2, skeletalBlock);
		addBlock(world, basePos, 0, 2, 4, skeletalBlock);
		addBlock(world, basePos, 2, 2, 0, skeletalBlock);
		addBlock(world, basePos, 2, 2, 6, skeletalBlock);
		addBlock(world, basePos, 4, 2, 0, skeletalBlock);
		addBlock(world, basePos, 4, 2, 6, skeletalBlock);
		addBlock(world, basePos, 6, 2, 2, skeletalBlock);
		addBlock(world, basePos, 6, 2, 4, skeletalBlock);
		addBlock(world, basePos, 0, 3, 2, skeletalBlock);
		addBlock(world, basePos, 0, 3, 4, skeletalBlock);
		addBlock(world, basePos, 1, 3, 2, skeletalBlock);
		addBlock(world, basePos, 1, 3, 4, skeletalBlock);
		addBlock(world, basePos, 2, 3, 0, skeletalBlock);
		addBlock(world, basePos, 2, 3, 1, skeletalBlock);
		addBlock(world, basePos, 2, 3, 5, skeletalBlock);
		addBlock(world, basePos, 2, 3, 6, skeletalBlock);
		addBlock(world, basePos, 4, 3, 0, skeletalBlock);
		addBlock(world, basePos, 4, 3, 1, skeletalBlock);
		addBlock(world, basePos, 4, 3, 5, skeletalBlock);
		addBlock(world, basePos, 4, 3, 6, skeletalBlock);
		addBlock(world, basePos, 5, 3, 2, skeletalBlock);
		addBlock(world, basePos, 5, 3, 4, skeletalBlock);
		addBlock(world, basePos, 6, 3, 2, skeletalBlock);
		addBlock(world, basePos, 6, 3, 4, skeletalBlock);
	}
}
