package net.tslat.aoa3.worldgen.structures.shyrelands;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.npc.trader.ShyreArcherEntity;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class ShyreDecoration3 extends AoAStructure { //StructureSize: 14x4x14
	private static final BlockState whiteShyreBricks = AoABlocks.WHITE_SHYRE_BRICKS.get().getDefaultState();
	private static final BlockState yellowShyreBricks = AoABlocks.YELLOW_SHYRE_BRICKS.get().getDefaultState();

	public ShyreDecoration3() {
		super("ShyreDecoration3");
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
		addBlock(world, basePos, 2, 0, 13, whiteShyreBricks);
		addBlock(world, basePos, 3, 0, 0, whiteShyreBricks);
		addBlock(world, basePos, 3, 0, 3, yellowShyreBricks);
		addBlock(world, basePos, 3, 0, 4, yellowShyreBricks);
		addBlock(world, basePos, 3, 0, 5, yellowShyreBricks);
		addBlock(world, basePos, 3, 0, 6, whiteShyreBricks);
		addBlock(world, basePos, 3, 0, 7, whiteShyreBricks);
		addBlock(world, basePos, 3, 0, 8, yellowShyreBricks);
		addBlock(world, basePos, 3, 0, 9, yellowShyreBricks);
		addBlock(world, basePos, 3, 0, 10, yellowShyreBricks);
		addBlock(world, basePos, 3, 0, 13, whiteShyreBricks);
		addBlock(world, basePos, 4, 0, 0, yellowShyreBricks);
		addBlock(world, basePos, 4, 0, 3, yellowShyreBricks);
		addBlock(world, basePos, 4, 0, 4, yellowShyreBricks);
		addBlock(world, basePos, 4, 0, 5, yellowShyreBricks);
		addBlock(world, basePos, 4, 0, 6, yellowShyreBricks);
		addBlock(world, basePos, 4, 0, 7, yellowShyreBricks);
		addBlock(world, basePos, 4, 0, 8, yellowShyreBricks);
		addBlock(world, basePos, 4, 0, 9, yellowShyreBricks);
		addBlock(world, basePos, 4, 0, 10, yellowShyreBricks);
		addBlock(world, basePos, 4, 0, 13, yellowShyreBricks);
		addBlock(world, basePos, 5, 0, 3, yellowShyreBricks);
		addBlock(world, basePos, 5, 0, 4, yellowShyreBricks);
		addBlock(world, basePos, 5, 0, 5, yellowShyreBricks);
		addBlock(world, basePos, 5, 0, 6, yellowShyreBricks);
		addBlock(world, basePos, 5, 0, 7, yellowShyreBricks);
		addBlock(world, basePos, 5, 0, 8, yellowShyreBricks);
		addBlock(world, basePos, 5, 0, 9, yellowShyreBricks);
		addBlock(world, basePos, 5, 0, 10, yellowShyreBricks);
		addBlock(world, basePos, 6, 0, 3, whiteShyreBricks);
		addBlock(world, basePos, 6, 0, 4, yellowShyreBricks);
		addBlock(world, basePos, 6, 0, 5, yellowShyreBricks);
		addBlock(world, basePos, 6, 0, 6, yellowShyreBricks);
		addBlock(world, basePos, 6, 0, 7, yellowShyreBricks);
		addBlock(world, basePos, 6, 0, 8, yellowShyreBricks);
		addBlock(world, basePos, 6, 0, 9, yellowShyreBricks);
		addBlock(world, basePos, 6, 0, 10, whiteShyreBricks);
		addBlock(world, basePos, 7, 0, 3, whiteShyreBricks);
		addBlock(world, basePos, 7, 0, 4, yellowShyreBricks);
		addBlock(world, basePos, 7, 0, 5, yellowShyreBricks);
		addBlock(world, basePos, 7, 0, 6, yellowShyreBricks);
		addBlock(world, basePos, 7, 0, 7, yellowShyreBricks);
		addBlock(world, basePos, 7, 0, 8, yellowShyreBricks);
		addBlock(world, basePos, 7, 0, 9, yellowShyreBricks);
		addBlock(world, basePos, 7, 0, 10, whiteShyreBricks);
		addBlock(world, basePos, 8, 0, 3, yellowShyreBricks);
		addBlock(world, basePos, 8, 0, 4, yellowShyreBricks);
		addBlock(world, basePos, 8, 0, 5, yellowShyreBricks);
		addBlock(world, basePos, 8, 0, 6, yellowShyreBricks);
		addBlock(world, basePos, 8, 0, 7, yellowShyreBricks);
		addBlock(world, basePos, 8, 0, 8, yellowShyreBricks);
		addBlock(world, basePos, 8, 0, 9, yellowShyreBricks);
		addBlock(world, basePos, 8, 0, 10, yellowShyreBricks);
		addBlock(world, basePos, 9, 0, 0, yellowShyreBricks);
		addBlock(world, basePos, 9, 0, 3, yellowShyreBricks);
		addBlock(world, basePos, 9, 0, 4, yellowShyreBricks);
		addBlock(world, basePos, 9, 0, 5, yellowShyreBricks);
		addBlock(world, basePos, 9, 0, 6, yellowShyreBricks);
		addBlock(world, basePos, 9, 0, 7, yellowShyreBricks);
		addBlock(world, basePos, 9, 0, 8, yellowShyreBricks);
		addBlock(world, basePos, 9, 0, 9, yellowShyreBricks);
		addBlock(world, basePos, 9, 0, 10, yellowShyreBricks);
		addBlock(world, basePos, 9, 0, 13, yellowShyreBricks);
		addBlock(world, basePos, 10, 0, 0, whiteShyreBricks);
		addBlock(world, basePos, 10, 0, 3, yellowShyreBricks);
		addBlock(world, basePos, 10, 0, 4, yellowShyreBricks);
		addBlock(world, basePos, 10, 0, 5, yellowShyreBricks);
		addBlock(world, basePos, 10, 0, 6, whiteShyreBricks);
		addBlock(world, basePos, 10, 0, 7, whiteShyreBricks);
		addBlock(world, basePos, 10, 0, 8, yellowShyreBricks);
		addBlock(world, basePos, 10, 0, 9, yellowShyreBricks);
		addBlock(world, basePos, 10, 0, 10, yellowShyreBricks);
		addBlock(world, basePos, 10, 0, 13, whiteShyreBricks);
		addBlock(world, basePos, 11, 0, 0, whiteShyreBricks);
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
		addBlock(world, basePos, 4, 1, 4, yellowShyreBricks);
		addBlock(world, basePos, 4, 1, 5, yellowShyreBricks);
		addBlock(world, basePos, 4, 1, 6, whiteShyreBricks);
		addBlock(world, basePos, 4, 1, 7, whiteShyreBricks);
		addBlock(world, basePos, 4, 1, 8, yellowShyreBricks);
		addBlock(world, basePos, 4, 1, 9, yellowShyreBricks);
		addBlock(world, basePos, 5, 1, 4, yellowShyreBricks);
		addBlock(world, basePos, 5, 1, 5, yellowShyreBricks);
		addBlock(world, basePos, 5, 1, 6, yellowShyreBricks);
		addBlock(world, basePos, 5, 1, 7, yellowShyreBricks);
		addBlock(world, basePos, 5, 1, 8, yellowShyreBricks);
		addBlock(world, basePos, 5, 1, 9, yellowShyreBricks);
		addBlock(world, basePos, 6, 1, 4, whiteShyreBricks);
		addBlock(world, basePos, 6, 1, 5, yellowShyreBricks);
		addBlock(world, basePos, 6, 1, 6, yellowShyreBricks);
		addBlock(world, basePos, 6, 1, 7, yellowShyreBricks);
		addBlock(world, basePos, 6, 1, 8, yellowShyreBricks);
		addBlock(world, basePos, 6, 1, 9, whiteShyreBricks);
		addBlock(world, basePos, 7, 1, 4, whiteShyreBricks);
		addBlock(world, basePos, 7, 1, 5, yellowShyreBricks);
		addBlock(world, basePos, 7, 1, 6, yellowShyreBricks);
		addBlock(world, basePos, 7, 1, 7, yellowShyreBricks);
		addBlock(world, basePos, 7, 1, 8, yellowShyreBricks);
		addBlock(world, basePos, 7, 1, 9, whiteShyreBricks);
		addBlock(world, basePos, 8, 1, 4, yellowShyreBricks);
		addBlock(world, basePos, 8, 1, 5, yellowShyreBricks);
		addBlock(world, basePos, 8, 1, 6, yellowShyreBricks);
		addBlock(world, basePos, 8, 1, 7, yellowShyreBricks);
		addBlock(world, basePos, 8, 1, 8, yellowShyreBricks);
		addBlock(world, basePos, 8, 1, 9, yellowShyreBricks);
		addBlock(world, basePos, 9, 1, 4, yellowShyreBricks);
		addBlock(world, basePos, 9, 1, 5, yellowShyreBricks);
		addBlock(world, basePos, 9, 1, 6, whiteShyreBricks);
		addBlock(world, basePos, 9, 1, 7, whiteShyreBricks);
		addBlock(world, basePos, 9, 1, 8, yellowShyreBricks);
		addBlock(world, basePos, 9, 1, 9, yellowShyreBricks);
		addBlock(world, basePos, 5, 2, 5, yellowShyreBricks);
		addBlock(world, basePos, 5, 2, 6, whiteShyreBricks);
		addBlock(world, basePos, 5, 2, 7, whiteShyreBricks);
		addBlock(world, basePos, 5, 2, 8, yellowShyreBricks);
		addBlock(world, basePos, 6, 2, 5, whiteShyreBricks);
		addBlock(world, basePos, 6, 2, 6, yellowShyreBricks);
		addBlock(world, basePos, 6, 2, 7, yellowShyreBricks);
		addBlock(world, basePos, 6, 2, 8, whiteShyreBricks);
		addBlock(world, basePos, 7, 2, 5, whiteShyreBricks);
		addBlock(world, basePos, 7, 2, 6, yellowShyreBricks);
		addBlock(world, basePos, 7, 2, 7, yellowShyreBricks);
		addBlock(world, basePos, 7, 2, 8, whiteShyreBricks);
		addBlock(world, basePos, 8, 2, 5, yellowShyreBricks);
		addBlock(world, basePos, 8, 2, 6, whiteShyreBricks);
		addBlock(world, basePos, 8, 2, 7, whiteShyreBricks);
		addBlock(world, basePos, 8, 2, 8, yellowShyreBricks);
		addBlock(world, basePos, 6, 3, 6, yellowShyreBricks);
		addBlock(world, basePos, 6, 3, 7, yellowShyreBricks);
		addBlock(world, basePos, 7, 3, 6, yellowShyreBricks);
		addBlock(world, basePos, 7, 3, 7, yellowShyreBricks);
	}

	@Override
	protected void spawnEntities(IWorld world, Random rand, BlockPos basePos) {
		ShyreArcherEntity archer = new ShyreArcherEntity(AoAEntities.NPCs.SHYRE_ARCHER.get(), world.getWorld());

		archer.setLocationAndAngles(basePos.getX() + 7, basePos.getY() + 4, basePos.getZ() + 7, rand.nextFloat() * 360, 0);
		world.addEntity(archer);
	}
}
