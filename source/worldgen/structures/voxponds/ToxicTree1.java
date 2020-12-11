package net.tslat.aoa3.worldgen.structures.voxponds;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class ToxicTree1 extends AoAStructure { //StructureSize: 9x14x9
	private static final BlockState toxicLog = AoABlocks.TOXIC_LOG.get().getDefaultState();

	public ToxicTree1() {
		super("ToxicTree1");
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 4, 0, 4, toxicLog);
		addBlock(world, basePos, 4, 1, 4, toxicLog);
		addBlock(world, basePos, 0, 2, 4, toxicLog);
		addBlock(world, basePos, 1, 2, 4, toxicLog);
		addBlock(world, basePos, 2, 2, 4, toxicLog);
		addBlock(world, basePos, 3, 2, 4, toxicLog);
		addBlock(world, basePos, 4, 2, 0, toxicLog);
		addBlock(world, basePos, 4, 2, 1, toxicLog);
		addBlock(world, basePos, 4, 2, 2, toxicLog);
		addBlock(world, basePos, 4, 2, 3, toxicLog);
		addBlock(world, basePos, 4, 2, 4, toxicLog);
		addBlock(world, basePos, 4, 2, 5, toxicLog);
		addBlock(world, basePos, 4, 2, 6, toxicLog);
		addBlock(world, basePos, 4, 2, 7, toxicLog);
		addBlock(world, basePos, 4, 2, 8, toxicLog);
		addBlock(world, basePos, 5, 2, 4, toxicLog);
		addBlock(world, basePos, 6, 2, 4, toxicLog);
		addBlock(world, basePos, 7, 2, 4, toxicLog);
		addBlock(world, basePos, 8, 2, 4, toxicLog);
		addBlock(world, basePos, 4, 3, 4, toxicLog);
		addBlock(world, basePos, 4, 4, 4, toxicLog);
		addBlock(world, basePos, 1, 5, 4, toxicLog);
		addBlock(world, basePos, 2, 5, 4, toxicLog);
		addBlock(world, basePos, 3, 5, 4, toxicLog);
		addBlock(world, basePos, 4, 5, 1, toxicLog);
		addBlock(world, basePos, 4, 5, 2, toxicLog);
		addBlock(world, basePos, 4, 5, 3, toxicLog);
		addBlock(world, basePos, 4, 5, 4, toxicLog);
		addBlock(world, basePos, 4, 5, 5, toxicLog);
		addBlock(world, basePos, 4, 5, 6, toxicLog);
		addBlock(world, basePos, 4, 5, 7, toxicLog);
		addBlock(world, basePos, 5, 5, 4, toxicLog);
		addBlock(world, basePos, 6, 5, 4, toxicLog);
		addBlock(world, basePos, 7, 5, 4, toxicLog);
		addBlock(world, basePos, 4, 6, 4, toxicLog);
		addBlock(world, basePos, 4, 7, 4, toxicLog);
		addBlock(world, basePos, 2, 8, 4, toxicLog);
		addBlock(world, basePos, 3, 8, 4, toxicLog);
		addBlock(world, basePos, 4, 8, 2, toxicLog);
		addBlock(world, basePos, 4, 8, 3, toxicLog);
		addBlock(world, basePos, 4, 8, 4, toxicLog);
		addBlock(world, basePos, 4, 8, 5, toxicLog);
		addBlock(world, basePos, 4, 8, 6, toxicLog);
		addBlock(world, basePos, 5, 8, 4, toxicLog);
		addBlock(world, basePos, 6, 8, 4, toxicLog);
		addBlock(world, basePos, 4, 9, 4, toxicLog);
		addBlock(world, basePos, 4, 10, 4, toxicLog);
		addBlock(world, basePos, 3, 11, 4, toxicLog);
		addBlock(world, basePos, 4, 11, 3, toxicLog);
		addBlock(world, basePos, 4, 11, 4, toxicLog);
		addBlock(world, basePos, 4, 11, 5, toxicLog);
		addBlock(world, basePos, 5, 11, 4, toxicLog);
		addBlock(world, basePos, 4, 12, 4, toxicLog);
		addBlock(world, basePos, 4, 13, 4, toxicLog);
	}
}
