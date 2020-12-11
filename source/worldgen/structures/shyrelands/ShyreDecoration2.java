package net.tslat.aoa3.worldgen.structures.shyrelands;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.npc.banker.ShyreBankerEntity;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class ShyreDecoration2 extends AoAStructure { //StructureSize: 14x4x14
	private static final BlockState whiteShyreBricks = AoABlocks.WHITE_SHYRE_BRICKS.get().getDefaultState();
	private static final BlockState yellowShyreBricks = AoABlocks.YELLOW_SHYRE_BRICKS.get().getDefaultState();
	private static final BlockState shyreLeaves = AoABlocks.SHYRE_LEAVES.get().getDefaultState();

	public ShyreDecoration2() {
		super("ShyreDecoration2");
	}

	@Override
	protected boolean replacesBlocks() {
		return true;
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
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
		addBlock(world, basePos, 2, 0, 2, shyreLeaves);
		addBlock(world, basePos, 2, 0, 3, shyreLeaves);
		addBlock(world, basePos, 2, 0, 10, shyreLeaves);
		addBlock(world, basePos, 2, 0, 11, shyreLeaves);
		addBlock(world, basePos, 2, 0, 13, whiteShyreBricks);
		addBlock(world, basePos, 3, 0, 0, whiteShyreBricks);
		addBlock(world, basePos, 3, 0, 2, shyreLeaves);
		addBlock(world, basePos, 3, 0, 3, shyreLeaves);
		addBlock(world, basePos, 3, 0, 10, shyreLeaves);
		addBlock(world, basePos, 3, 0, 11, shyreLeaves);
		addBlock(world, basePos, 3, 0, 13, whiteShyreBricks);
		addBlock(world, basePos, 4, 0, 0, yellowShyreBricks);
		addBlock(world, basePos, 4, 0, 6, yellowShyreBricks);
		addBlock(world, basePos, 4, 0, 7, yellowShyreBricks);
		addBlock(world, basePos, 4, 0, 8, yellowShyreBricks);
		addBlock(world, basePos, 4, 0, 13, yellowShyreBricks);
		addBlock(world, basePos, 5, 0, 4, yellowShyreBricks);
		addBlock(world, basePos, 6, 0, 4, yellowShyreBricks);
		addBlock(world, basePos, 6, 0, 6, yellowShyreBricks);
		addBlock(world, basePos, 6, 0, 7, yellowShyreBricks);
		addBlock(world, basePos, 6, 0, 9, yellowShyreBricks);
		addBlock(world, basePos, 7, 0, 4, yellowShyreBricks);
		addBlock(world, basePos, 7, 0, 6, yellowShyreBricks);
		addBlock(world, basePos, 7, 0, 7, yellowShyreBricks);
		addBlock(world, basePos, 7, 0, 9, yellowShyreBricks);
		addBlock(world, basePos, 8, 0, 9, yellowShyreBricks);
		addBlock(world, basePos, 9, 0, 0, yellowShyreBricks);
		addBlock(world, basePos, 9, 0, 5, yellowShyreBricks);
		addBlock(world, basePos, 9, 0, 6, yellowShyreBricks);
		addBlock(world, basePos, 9, 0, 7, yellowShyreBricks);
		addBlock(world, basePos, 9, 0, 13, yellowShyreBricks);
		addBlock(world, basePos, 10, 0, 0, whiteShyreBricks);
		addBlock(world, basePos, 10, 0, 2, shyreLeaves);
		addBlock(world, basePos, 10, 0, 3, shyreLeaves);
		addBlock(world, basePos, 10, 0, 10, shyreLeaves);
		addBlock(world, basePos, 10, 0, 11, shyreLeaves);
		addBlock(world, basePos, 10, 0, 13, whiteShyreBricks);
		addBlock(world, basePos, 11, 0, 0, whiteShyreBricks);
		addBlock(world, basePos, 11, 0, 2, shyreLeaves);
		addBlock(world, basePos, 11, 0, 3, shyreLeaves);
		addBlock(world, basePos, 11, 0, 10, shyreLeaves);
		addBlock(world, basePos, 11, 0, 11, shyreLeaves);
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
		addBlock(world, basePos, 4, 1, 7, yellowShyreBricks);
		addBlock(world, basePos, 4, 1, 8, yellowShyreBricks);
		addBlock(world, basePos, 5, 1, 4, yellowShyreBricks);
		addBlock(world, basePos, 6, 1, 4, yellowShyreBricks);
		addBlock(world, basePos, 6, 1, 6, yellowShyreBricks);
		addBlock(world, basePos, 6, 1, 7, yellowShyreBricks);
		addBlock(world, basePos, 7, 1, 6, yellowShyreBricks);
		addBlock(world, basePos, 7, 1, 7, yellowShyreBricks);
		addBlock(world, basePos, 7, 1, 9, yellowShyreBricks);
		addBlock(world, basePos, 8, 1, 9, yellowShyreBricks);
		addBlock(world, basePos, 9, 1, 5, yellowShyreBricks);
		addBlock(world, basePos, 9, 1, 6, yellowShyreBricks);
		addBlock(world, basePos, 4, 2, 8, yellowShyreBricks);
		addBlock(world, basePos, 5, 2, 4, yellowShyreBricks);
		addBlock(world, basePos, 6, 2, 6, yellowShyreBricks);
		addBlock(world, basePos, 6, 2, 7, yellowShyreBricks);
		addBlock(world, basePos, 7, 2, 6, yellowShyreBricks);
		addBlock(world, basePos, 7, 2, 7, yellowShyreBricks);
		addBlock(world, basePos, 8, 2, 9, yellowShyreBricks);
		addBlock(world, basePos, 9, 2, 5, yellowShyreBricks);
		addBlock(world, basePos, 5, 3, 5, yellowShyreBricks);
		addBlock(world, basePos, 5, 3, 6, yellowShyreBricks);
		addBlock(world, basePos, 5, 3, 7, yellowShyreBricks);
		addBlock(world, basePos, 5, 3, 8, yellowShyreBricks);
		addBlock(world, basePos, 6, 3, 5, yellowShyreBricks);
		addBlock(world, basePos, 6, 3, 6, whiteShyreBricks);
		addBlock(world, basePos, 6, 3, 7, whiteShyreBricks);
		addBlock(world, basePos, 6, 3, 8, yellowShyreBricks);
		addBlock(world, basePos, 7, 3, 5, yellowShyreBricks);
		addBlock(world, basePos, 7, 3, 6, whiteShyreBricks);
		addBlock(world, basePos, 7, 3, 7, whiteShyreBricks);
		addBlock(world, basePos, 7, 3, 8, yellowShyreBricks);
		addBlock(world, basePos, 8, 3, 5, yellowShyreBricks);
		addBlock(world, basePos, 8, 3, 6, yellowShyreBricks);
		addBlock(world, basePos, 8, 3, 7, yellowShyreBricks);
		addBlock(world, basePos, 8, 3, 8, yellowShyreBricks);
	}

	@Override
	protected void spawnEntities(IWorld world, Random rand, BlockPos basePos) {
		ShyreBankerEntity banker = new ShyreBankerEntity(AoAEntities.NPCs.SHYRE_BANKER.get(), world.getWorld());

		banker.setLocationAndAngles(basePos.getX() + 7, basePos.getY() + 4, basePos.getZ() + 7, rand.nextFloat() * 360, 0);
		world.addEntity(banker);
	}
}
