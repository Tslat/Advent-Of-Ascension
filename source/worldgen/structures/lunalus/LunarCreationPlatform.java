package net.tslat.aoa3.worldgen.structures.lunalus;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class LunarCreationPlatform extends AoAStructure { //StructureSize: 9x5x9
	private static final BlockState lunarBricks = AoABlocks.LUNAR_BRICKS.get().getDefaultState();
	private static final BlockState lunarPillar = AoABlocks.LUNAR_PILLAR.get().getDefaultState();
	private static final BlockState lunarPad = AoABlocks.LUNAR_PAD.get().getDefaultState();
	private static final BlockState lunarCreationTable = AoABlocks.LUNAR_CREATION_TABLE.get().getDefaultState();

	public LunarCreationPlatform() {
		super("LunarCreationPlatform");
	}

	@Override
	protected boolean replacesBlocks() {
		return true;
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 1, 0, 1, lunarBricks);
		addBlock(world, basePos, 1, 0, 2, lunarBricks);
		addBlock(world, basePos, 1, 0, 3, lunarBricks);
		addBlock(world, basePos, 1, 0, 4, lunarBricks);
		addBlock(world, basePos, 1, 0, 5, lunarBricks);
		addBlock(world, basePos, 1, 0, 6, lunarBricks);
		addBlock(world, basePos, 1, 0, 7, lunarBricks);
		addBlock(world, basePos, 2, 0, 1, lunarBricks);
		addBlock(world, basePos, 2, 0, 2, lunarPad);
		addBlock(world, basePos, 2, 0, 3, lunarBricks);
		addBlock(world, basePos, 2, 0, 4, lunarPad);
		addBlock(world, basePos, 2, 0, 5, lunarBricks);
		addBlock(world, basePos, 2, 0, 6, lunarPad);
		addBlock(world, basePos, 2, 0, 7, lunarBricks);
		addBlock(world, basePos, 3, 0, 1, lunarBricks);
		addBlock(world, basePos, 3, 0, 2, lunarBricks);
		addBlock(world, basePos, 3, 0, 3, lunarBricks);
		addBlock(world, basePos, 3, 0, 4, lunarBricks);
		addBlock(world, basePos, 3, 0, 5, lunarBricks);
		addBlock(world, basePos, 3, 0, 6, lunarBricks);
		addBlock(world, basePos, 3, 0, 7, lunarBricks);
		addBlock(world, basePos, 4, 0, 1, lunarBricks);
		addBlock(world, basePos, 4, 0, 2, lunarPad);
		addBlock(world, basePos, 4, 0, 3, lunarBricks);
		addBlock(world, basePos, 4, 0, 4, lunarBricks);
		addBlock(world, basePos, 4, 0, 5, lunarBricks);
		addBlock(world, basePos, 4, 0, 6, lunarPad);
		addBlock(world, basePos, 4, 0, 7, lunarBricks);
		addBlock(world, basePos, 5, 0, 1, lunarBricks);
		addBlock(world, basePos, 5, 0, 2, lunarBricks);
		addBlock(world, basePos, 5, 0, 3, lunarBricks);
		addBlock(world, basePos, 5, 0, 4, lunarBricks);
		addBlock(world, basePos, 5, 0, 5, lunarBricks);
		addBlock(world, basePos, 5, 0, 6, lunarBricks);
		addBlock(world, basePos, 5, 0, 7, lunarBricks);
		addBlock(world, basePos, 6, 0, 1, lunarBricks);
		addBlock(world, basePos, 6, 0, 2, lunarPad);
		addBlock(world, basePos, 6, 0, 3, lunarBricks);
		addBlock(world, basePos, 6, 0, 4, lunarPad);
		addBlock(world, basePos, 6, 0, 5, lunarBricks);
		addBlock(world, basePos, 6, 0, 6, lunarPad);
		addBlock(world, basePos, 6, 0, 7, lunarBricks);
		addBlock(world, basePos, 7, 0, 1, lunarBricks);
		addBlock(world, basePos, 7, 0, 2, lunarBricks);
		addBlock(world, basePos, 7, 0, 3, lunarBricks);
		addBlock(world, basePos, 7, 0, 4, lunarBricks);
		addBlock(world, basePos, 7, 0, 5, lunarBricks);
		addBlock(world, basePos, 7, 0, 6, lunarBricks);
		addBlock(world, basePos, 7, 0, 7, lunarBricks);
		addBlock(world, basePos, 1, 1, 1, lunarBricks);
		addBlock(world, basePos, 1, 1, 7, lunarBricks);
		addBlock(world, basePos, 4, 1, 4, lunarCreationTable);
		addBlock(world, basePos, 7, 1, 1, lunarBricks);
		addBlock(world, basePos, 7, 1, 7, lunarBricks);
		addBlock(world, basePos, 0, 2, 1, lunarBricks);
		addBlock(world, basePos, 0, 2, 7, lunarBricks);
		addBlock(world, basePos, 1, 2, 0, lunarBricks);
		addBlock(world, basePos, 1, 2, 1, lunarPillar);
		addBlock(world, basePos, 1, 2, 7, lunarPillar);
		addBlock(world, basePos, 1, 2, 8, lunarBricks);
		addBlock(world, basePos, 7, 2, 0, lunarBricks);
		addBlock(world, basePos, 7, 2, 1, lunarPillar);
		addBlock(world, basePos, 7, 2, 7, lunarPillar);
		addBlock(world, basePos, 7, 2, 8, lunarBricks);
		addBlock(world, basePos, 8, 2, 1, lunarBricks);
		addBlock(world, basePos, 8, 2, 7, lunarBricks);
		addBlock(world, basePos, 1, 3, 1, lunarPillar);
		addBlock(world, basePos, 1, 3, 7, lunarPillar);
		addBlock(world, basePos, 7, 3, 1, lunarPillar);
		addBlock(world, basePos, 7, 3, 7, lunarPillar);
		addBlock(world, basePos, 1, 4, 1, lunarPillar);
		addBlock(world, basePos, 1, 4, 7, lunarPillar);
		addBlock(world, basePos, 7, 4, 1, lunarPillar);
		addBlock(world, basePos, 7, 4, 7, lunarPillar);
	}
}
