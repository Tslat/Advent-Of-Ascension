package net.tslat.aoa3.worldgen.structures.abyss;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class AbyssalTree3 extends AoAStructure { //StructureSize: 7x6x7
	private static final BlockState veinLeaves = AoABlocks.VEIN_LEAVES.get().getDefaultState();
	private static final BlockState eyeballLog = AoABlocks.EYEBALL_LOG.get().getDefaultState();

	public AbyssalTree3() {
		super("AbyssalTree3");
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 3, 0, 3, eyeballLog);
		addBlock(world, basePos, 1, 1, 2, veinLeaves);
		addBlock(world, basePos, 1, 1, 3, veinLeaves);
		addBlock(world, basePos, 1, 1, 4, veinLeaves);
		addBlock(world, basePos, 2, 1, 1, veinLeaves);
		addBlock(world, basePos, 2, 1, 2, veinLeaves);
		addBlock(world, basePos, 2, 1, 3, eyeballLog);
		addBlock(world, basePos, 2, 1, 4, veinLeaves);
		addBlock(world, basePos, 2, 1, 5, veinLeaves);
		addBlock(world, basePos, 3, 1, 1, veinLeaves);
		addBlock(world, basePos, 3, 1, 2, eyeballLog);
		addBlock(world, basePos, 3, 1, 3, eyeballLog);
		addBlock(world, basePos, 3, 1, 4, eyeballLog);
		addBlock(world, basePos, 3, 1, 5, veinLeaves);
		addBlock(world, basePos, 4, 1, 1, veinLeaves);
		addBlock(world, basePos, 4, 1, 2, veinLeaves);
		addBlock(world, basePos, 4, 1, 3, eyeballLog);
		addBlock(world, basePos, 4, 1, 4, veinLeaves);
		addBlock(world, basePos, 4, 1, 5, veinLeaves);
		addBlock(world, basePos, 5, 1, 2, veinLeaves);
		addBlock(world, basePos, 5, 1, 3, veinLeaves);
		addBlock(world, basePos, 5, 1, 4, veinLeaves);
		addBlock(world, basePos, 0, 2, 1, veinLeaves);
		addBlock(world, basePos, 0, 2, 2, veinLeaves);
		addBlock(world, basePos, 0, 2, 3, veinLeaves);
		addBlock(world, basePos, 0, 2, 4, veinLeaves);
		addBlock(world, basePos, 0, 2, 5, veinLeaves);
		addBlock(world, basePos, 1, 2, 0, veinLeaves);
		addBlock(world, basePos, 1, 2, 1, veinLeaves);
		addBlock(world, basePos, 1, 2, 2, veinLeaves);
		addBlock(world, basePos, 1, 2, 3, veinLeaves);
		addBlock(world, basePos, 1, 2, 4, veinLeaves);
		addBlock(world, basePos, 1, 2, 5, veinLeaves);
		addBlock(world, basePos, 1, 2, 6, veinLeaves);
		addBlock(world, basePos, 2, 2, 0, veinLeaves);
		addBlock(world, basePos, 2, 2, 1, veinLeaves);
		addBlock(world, basePos, 2, 2, 2, veinLeaves);
		addBlock(world, basePos, 2, 2, 3, veinLeaves);
		addBlock(world, basePos, 2, 2, 4, veinLeaves);
		addBlock(world, basePos, 2, 2, 5, veinLeaves);
		addBlock(world, basePos, 2, 2, 6, veinLeaves);
		addBlock(world, basePos, 3, 2, 0, veinLeaves);
		addBlock(world, basePos, 3, 2, 1, veinLeaves);
		addBlock(world, basePos, 3, 2, 2, veinLeaves);
		addBlock(world, basePos, 3, 2, 3, veinLeaves);
		addBlock(world, basePos, 3, 2, 4, veinLeaves);
		addBlock(world, basePos, 3, 2, 5, veinLeaves);
		addBlock(world, basePos, 3, 2, 6, veinLeaves);
		addBlock(world, basePos, 4, 2, 0, veinLeaves);
		addBlock(world, basePos, 4, 2, 1, veinLeaves);
		addBlock(world, basePos, 4, 2, 2, veinLeaves);
		addBlock(world, basePos, 4, 2, 3, veinLeaves);
		addBlock(world, basePos, 4, 2, 4, veinLeaves);
		addBlock(world, basePos, 4, 2, 5, veinLeaves);
		addBlock(world, basePos, 4, 2, 6, veinLeaves);
		addBlock(world, basePos, 5, 2, 0, veinLeaves);
		addBlock(world, basePos, 5, 2, 1, veinLeaves);
		addBlock(world, basePos, 5, 2, 2, veinLeaves);
		addBlock(world, basePos, 5, 2, 3, veinLeaves);
		addBlock(world, basePos, 5, 2, 4, veinLeaves);
		addBlock(world, basePos, 5, 2, 5, veinLeaves);
		addBlock(world, basePos, 5, 2, 6, veinLeaves);
		addBlock(world, basePos, 6, 2, 1, veinLeaves);
		addBlock(world, basePos, 6, 2, 2, veinLeaves);
		addBlock(world, basePos, 6, 2, 3, veinLeaves);
		addBlock(world, basePos, 6, 2, 4, veinLeaves);
		addBlock(world, basePos, 6, 2, 5, veinLeaves);
		addBlock(world, basePos, 1, 3, 2, veinLeaves);
		addBlock(world, basePos, 1, 3, 3, veinLeaves);
		addBlock(world, basePos, 1, 3, 4, veinLeaves);
		addBlock(world, basePos, 2, 3, 1, veinLeaves);
		addBlock(world, basePos, 2, 3, 2, veinLeaves);
		addBlock(world, basePos, 2, 3, 3, veinLeaves);
		addBlock(world, basePos, 2, 3, 4, veinLeaves);
		addBlock(world, basePos, 2, 3, 5, veinLeaves);
		addBlock(world, basePos, 3, 3, 1, veinLeaves);
		addBlock(world, basePos, 3, 3, 2, veinLeaves);
		addBlock(world, basePos, 3, 3, 3, veinLeaves);
		addBlock(world, basePos, 3, 3, 4, veinLeaves);
		addBlock(world, basePos, 3, 3, 5, veinLeaves);
		addBlock(world, basePos, 4, 3, 1, veinLeaves);
		addBlock(world, basePos, 4, 3, 2, veinLeaves);
		addBlock(world, basePos, 4, 3, 3, veinLeaves);
		addBlock(world, basePos, 4, 3, 4, veinLeaves);
		addBlock(world, basePos, 4, 3, 5, veinLeaves);
		addBlock(world, basePos, 5, 3, 2, veinLeaves);
		addBlock(world, basePos, 5, 3, 3, veinLeaves);
		addBlock(world, basePos, 5, 3, 4, veinLeaves);
		addBlock(world, basePos, 2, 4, 3, veinLeaves);
		addBlock(world, basePos, 3, 4, 2, veinLeaves);
		addBlock(world, basePos, 3, 4, 3, veinLeaves);
		addBlock(world, basePos, 3, 4, 4, veinLeaves);
		addBlock(world, basePos, 4, 4, 3, veinLeaves);
		addBlock(world, basePos, 3, 5, 3, veinLeaves);
	}
}
