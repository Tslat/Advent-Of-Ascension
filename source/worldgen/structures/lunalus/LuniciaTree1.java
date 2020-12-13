package net.tslat.aoa3.worldgen.structures.lunalus;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class LuniciaTree1 extends AoAStructure { //StructureSize: 5x9x5
	private static final BlockState luniciaLeaves = AoABlocks.LUNICIA_LEAVES.get().getDefaultState();
	private static final BlockState lunideLog = AoABlocks.LUNIDE_LOG.get().getDefaultState();

	public LuniciaTree1() {
		super("LuniciaTree1");
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 2, 0, 2, lunideLog);
		addBlock(world, basePos, 2, 1, 2, lunideLog);
		addBlock(world, basePos, 2, 2, 2, lunideLog);
		addBlock(world, basePos, 2, 3, 2, lunideLog);
		addBlock(world, basePos, 0, 4, 0, luniciaLeaves);
		addBlock(world, basePos, 0, 4, 1, luniciaLeaves);
		addBlock(world, basePos, 0, 4, 2, luniciaLeaves);
		addBlock(world, basePos, 0, 4, 3, luniciaLeaves);
		addBlock(world, basePos, 0, 4, 4, luniciaLeaves);
		addBlock(world, basePos, 1, 4, 0, luniciaLeaves);
		addBlock(world, basePos, 1, 4, 4, luniciaLeaves);
		addBlock(world, basePos, 2, 4, 0, luniciaLeaves);
		addBlock(world, basePos, 2, 4, 2, lunideLog);
		addBlock(world, basePos, 2, 4, 4, luniciaLeaves);
		addBlock(world, basePos, 3, 4, 0, luniciaLeaves);
		addBlock(world, basePos, 3, 4, 4, luniciaLeaves);
		addBlock(world, basePos, 4, 4, 0, luniciaLeaves);
		addBlock(world, basePos, 4, 4, 1, luniciaLeaves);
		addBlock(world, basePos, 4, 4, 2, luniciaLeaves);
		addBlock(world, basePos, 4, 4, 3, luniciaLeaves);
		addBlock(world, basePos, 4, 4, 4, luniciaLeaves);
		addBlock(world, basePos, 2, 5, 2, lunideLog);
		addBlock(world, basePos, 2, 6, 2, lunideLog);
		addBlock(world, basePos, 0, 7, 0, luniciaLeaves);
		addBlock(world, basePos, 0, 7, 1, luniciaLeaves);
		addBlock(world, basePos, 0, 7, 2, luniciaLeaves);
		addBlock(world, basePos, 0, 7, 3, luniciaLeaves);
		addBlock(world, basePos, 0, 7, 4, luniciaLeaves);
		addBlock(world, basePos, 1, 7, 0, luniciaLeaves);
		addBlock(world, basePos, 1, 7, 4, luniciaLeaves);
		addBlock(world, basePos, 2, 7, 0, luniciaLeaves);
		addBlock(world, basePos, 2, 7, 2, lunideLog);
		addBlock(world, basePos, 2, 7, 4, luniciaLeaves);
		addBlock(world, basePos, 3, 7, 0, luniciaLeaves);
		addBlock(world, basePos, 3, 7, 4, luniciaLeaves);
		addBlock(world, basePos, 4, 7, 0, luniciaLeaves);
		addBlock(world, basePos, 4, 7, 1, luniciaLeaves);
		addBlock(world, basePos, 4, 7, 2, luniciaLeaves);
		addBlock(world, basePos, 4, 7, 3, luniciaLeaves);
		addBlock(world, basePos, 4, 7, 4, luniciaLeaves);
		addBlock(world, basePos, 2, 8, 2, lunideLog);
	}
}
