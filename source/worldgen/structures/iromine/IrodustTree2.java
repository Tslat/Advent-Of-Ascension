package net.tslat.aoa3.worldgen.structures.iromine;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class IrodustTree2 extends AoAStructure { //StructureSize: 7x11x7
	private static final BlockState irodustLeaves = AoABlocks.IRODUST_LEAVES.get().getDefaultState();
	private static final BlockState log = AoABlocks.IROLOG.get().getDefaultState();

	public IrodustTree2() {
		super("IrodustTree2");
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 3, 0, 3, log);
		addBlock(world, basePos, 3, 1, 3, log);
		addBlock(world, basePos, 3, 2, 3, log);
		addBlock(world, basePos, 3, 3, 3, log);
		addBlock(world, basePos, 3, 4, 3, log);
		addBlock(world, basePos, 3, 5, 3, log);
		addBlock(world, basePos, 3, 6, 3, log);
		addBlock(world, basePos, 3, 7, 3, log);
		addBlock(world, basePos, 0, 8, 0, irodustLeaves);
		addBlock(world, basePos, 0, 8, 1, irodustLeaves);
		addBlock(world, basePos, 0, 8, 2, irodustLeaves);
		addBlock(world, basePos, 0, 8, 3, irodustLeaves);
		addBlock(world, basePos, 0, 8, 4, irodustLeaves);
		addBlock(world, basePos, 0, 8, 5, irodustLeaves);
		addBlock(world, basePos, 0, 8, 6, irodustLeaves);
		addBlock(world, basePos, 1, 8, 0, irodustLeaves);
		addBlock(world, basePos, 1, 8, 3, log);
		addBlock(world, basePos, 1, 8, 6, irodustLeaves);
		addBlock(world, basePos, 2, 8, 0, irodustLeaves);
		addBlock(world, basePos, 2, 8, 3, log);
		addBlock(world, basePos, 2, 8, 6, irodustLeaves);
		addBlock(world, basePos, 3, 8, 0, irodustLeaves);
		addBlock(world, basePos, 3, 8, 1, log);
		addBlock(world, basePos, 3, 8, 2, log);
		addBlock(world, basePos, 3, 8, 3, log);
		addBlock(world, basePos, 3, 8, 4, log);
		addBlock(world, basePos, 3, 8, 5, log);
		addBlock(world, basePos, 3, 8, 6, irodustLeaves);
		addBlock(world, basePos, 4, 8, 0, irodustLeaves);
		addBlock(world, basePos, 4, 8, 3, log);
		addBlock(world, basePos, 4, 8, 6, irodustLeaves);
		addBlock(world, basePos, 5, 8, 0, irodustLeaves);
		addBlock(world, basePos, 5, 8, 3, log);
		addBlock(world, basePos, 5, 8, 6, irodustLeaves);
		addBlock(world, basePos, 6, 8, 0, irodustLeaves);
		addBlock(world, basePos, 6, 8, 1, irodustLeaves);
		addBlock(world, basePos, 6, 8, 2, irodustLeaves);
		addBlock(world, basePos, 6, 8, 3, irodustLeaves);
		addBlock(world, basePos, 6, 8, 4, irodustLeaves);
		addBlock(world, basePos, 6, 8, 5, irodustLeaves);
		addBlock(world, basePos, 6, 8, 6, irodustLeaves);
		addBlock(world, basePos, 0, 9, 0, irodustLeaves);
		addBlock(world, basePos, 0, 9, 1, irodustLeaves);
		addBlock(world, basePos, 0, 9, 2, irodustLeaves);
		addBlock(world, basePos, 0, 9, 3, irodustLeaves);
		addBlock(world, basePos, 0, 9, 4, irodustLeaves);
		addBlock(world, basePos, 0, 9, 5, irodustLeaves);
		addBlock(world, basePos, 0, 9, 6, irodustLeaves);
		addBlock(world, basePos, 1, 9, 0, irodustLeaves);
		addBlock(world, basePos, 1, 9, 6, irodustLeaves);
		addBlock(world, basePos, 2, 9, 0, irodustLeaves);
		addBlock(world, basePos, 2, 9, 6, irodustLeaves);
		addBlock(world, basePos, 3, 9, 0, irodustLeaves);
		addBlock(world, basePos, 3, 9, 3, log);
		addBlock(world, basePos, 3, 9, 6, irodustLeaves);
		addBlock(world, basePos, 4, 9, 0, irodustLeaves);
		addBlock(world, basePos, 4, 9, 6, irodustLeaves);
		addBlock(world, basePos, 5, 9, 0, irodustLeaves);
		addBlock(world, basePos, 5, 9, 6, irodustLeaves);
		addBlock(world, basePos, 6, 9, 0, irodustLeaves);
		addBlock(world, basePos, 6, 9, 1, irodustLeaves);
		addBlock(world, basePos, 6, 9, 2, irodustLeaves);
		addBlock(world, basePos, 6, 9, 3, irodustLeaves);
		addBlock(world, basePos, 6, 9, 4, irodustLeaves);
		addBlock(world, basePos, 6, 9, 5, irodustLeaves);
		addBlock(world, basePos, 6, 9, 6, irodustLeaves);
		addBlock(world, basePos, 1, 10, 1, irodustLeaves);
		addBlock(world, basePos, 1, 10, 2, irodustLeaves);
		addBlock(world, basePos, 1, 10, 3, irodustLeaves);
		addBlock(world, basePos, 1, 10, 4, irodustLeaves);
		addBlock(world, basePos, 1, 10, 5, irodustLeaves);
		addBlock(world, basePos, 2, 10, 1, irodustLeaves);
		addBlock(world, basePos, 2, 10, 2, irodustLeaves);
		addBlock(world, basePos, 2, 10, 3, irodustLeaves);
		addBlock(world, basePos, 2, 10, 4, irodustLeaves);
		addBlock(world, basePos, 2, 10, 5, irodustLeaves);
		addBlock(world, basePos, 3, 10, 1, irodustLeaves);
		addBlock(world, basePos, 3, 10, 2, irodustLeaves);
		addBlock(world, basePos, 3, 10, 3, irodustLeaves);
		addBlock(world, basePos, 3, 10, 4, irodustLeaves);
		addBlock(world, basePos, 3, 10, 5, irodustLeaves);
		addBlock(world, basePos, 4, 10, 1, irodustLeaves);
		addBlock(world, basePos, 4, 10, 2, irodustLeaves);
		addBlock(world, basePos, 4, 10, 3, irodustLeaves);
		addBlock(world, basePos, 4, 10, 4, irodustLeaves);
		addBlock(world, basePos, 4, 10, 5, irodustLeaves);
		addBlock(world, basePos, 5, 10, 1, irodustLeaves);
		addBlock(world, basePos, 5, 10, 2, irodustLeaves);
		addBlock(world, basePos, 5, 10, 3, irodustLeaves);
		addBlock(world, basePos, 5, 10, 4, irodustLeaves);
		addBlock(world, basePos, 5, 10, 5, irodustLeaves);
	}
}
