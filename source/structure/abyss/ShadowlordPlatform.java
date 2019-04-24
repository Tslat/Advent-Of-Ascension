package net.tslat.aoa3.structure.abyss;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class ShadowlordPlatform extends AoAStructure { //StructureSize: 7x8x6
	private static final IBlockState bloodstoneBricks = BlockRegister.bricksBloodstone.getDefaultState();
	private static final IBlockState shadowAltar = BlockRegister.shadowAltar.getDefaultState();

	public ShadowlordPlatform() {
		super("ShadowlordPlatform");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 1, 0, 1, bloodstoneBricks);
		addBlock(world, basePos, 1, 0, 2, bloodstoneBricks);
		addBlock(world, basePos, 2, 0, 1, bloodstoneBricks);
		addBlock(world, basePos, 2, 0, 2, bloodstoneBricks);
		addBlock(world, basePos, 6, 0, 5, bloodstoneBricks);
		addBlock(world, basePos, 1, 1, 0, bloodstoneBricks);
		addBlock(world, basePos, 1, 1, 1, bloodstoneBricks);
		addBlock(world, basePos, 2, 1, 0, bloodstoneBricks);
		addBlock(world, basePos, 2, 1, 1, bloodstoneBricks);
		addBlock(world, basePos, 6, 1, 5, bloodstoneBricks);
		addBlock(world, basePos, 2, 2, 1, bloodstoneBricks);
		addBlock(world, basePos, 2, 2, 2, bloodstoneBricks);
		addBlock(world, basePos, 3, 2, 1, bloodstoneBricks);
		addBlock(world, basePos, 3, 2, 2, bloodstoneBricks);
		addBlock(world, basePos, 6, 2, 5, bloodstoneBricks);
		addBlock(world, basePos, 1, 3, 2, bloodstoneBricks);
		addBlock(world, basePos, 1, 3, 3, bloodstoneBricks);
		addBlock(world, basePos, 2, 3, 2, bloodstoneBricks);
		addBlock(world, basePos, 2, 3, 3, bloodstoneBricks);
		addBlock(world, basePos, 6, 3, 5, bloodstoneBricks);
		addBlock(world, basePos, 0, 4, 1, bloodstoneBricks);
		addBlock(world, basePos, 0, 4, 2, bloodstoneBricks);
		addBlock(world, basePos, 1, 4, 1, bloodstoneBricks);
		addBlock(world, basePos, 1, 4, 2, bloodstoneBricks);
		addBlock(world, basePos, 6, 4, 5, bloodstoneBricks);
		addBlock(world, basePos, 1, 5, 0, bloodstoneBricks);
		addBlock(world, basePos, 1, 5, 1, bloodstoneBricks);
		addBlock(world, basePos, 2, 5, 0, bloodstoneBricks);
		addBlock(world, basePos, 2, 5, 1, bloodstoneBricks);
		addBlock(world, basePos, 6, 5, 5, bloodstoneBricks);
		addBlock(world, basePos, 2, 6, 1, bloodstoneBricks);
		addBlock(world, basePos, 2, 6, 2, bloodstoneBricks);
		addBlock(world, basePos, 2, 6, 3, bloodstoneBricks);
		addBlock(world, basePos, 2, 6, 4, bloodstoneBricks);
		addBlock(world, basePos, 2, 6, 5, bloodstoneBricks);
		addBlock(world, basePos, 3, 6, 1, bloodstoneBricks);
		addBlock(world, basePos, 3, 6, 2, bloodstoneBricks);
		addBlock(world, basePos, 3, 6, 3, bloodstoneBricks);
		addBlock(world, basePos, 3, 6, 4, bloodstoneBricks);
		addBlock(world, basePos, 3, 6, 5, bloodstoneBricks);
		addBlock(world, basePos, 4, 6, 1, bloodstoneBricks);
		addBlock(world, basePos, 4, 6, 2, bloodstoneBricks);
		addBlock(world, basePos, 4, 6, 3, bloodstoneBricks);
		addBlock(world, basePos, 4, 6, 4, bloodstoneBricks);
		addBlock(world, basePos, 4, 6, 5, bloodstoneBricks);
		addBlock(world, basePos, 5, 6, 1, bloodstoneBricks);
		addBlock(world, basePos, 5, 6, 2, bloodstoneBricks);
		addBlock(world, basePos, 5, 6, 3, bloodstoneBricks);
		addBlock(world, basePos, 5, 6, 4, bloodstoneBricks);
		addBlock(world, basePos, 5, 6, 5, bloodstoneBricks);
		addBlock(world, basePos, 6, 6, 1, bloodstoneBricks);
		addBlock(world, basePos, 6, 6, 2, bloodstoneBricks);
		addBlock(world, basePos, 6, 6, 3, bloodstoneBricks);
		addBlock(world, basePos, 6, 6, 4, bloodstoneBricks);
		addBlock(world, basePos, 6, 6, 5, bloodstoneBricks);
		addBlock(world, basePos, 5, 7, 4, shadowAltar);
	}
}
