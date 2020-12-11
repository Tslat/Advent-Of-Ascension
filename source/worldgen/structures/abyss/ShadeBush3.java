package net.tslat.aoa3.worldgen.structures.abyss;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class ShadeBush3 extends AoAStructure { //StructureSize: 1x3x4
	private static final BlockState shadowLeaves = AoABlocks.SHADOW_LEAVES.get().getDefaultState();

	public ShadeBush3() {
		super("ShadeBush3");
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 0, shadowLeaves);
		addBlock(world, basePos, 0, 0, 3, shadowLeaves);
		addBlock(world, basePos, 0, 1, 0, shadowLeaves);
		addBlock(world, basePos, 0, 1, 3, shadowLeaves);
		addBlock(world, basePos, 0, 2, 0, shadowLeaves);
		addBlock(world, basePos, 0, 2, 1, shadowLeaves);
		addBlock(world, basePos, 0, 2, 2, shadowLeaves);
		addBlock(world, basePos, 0, 2, 3, shadowLeaves);
	}
}
