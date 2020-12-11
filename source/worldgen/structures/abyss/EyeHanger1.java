package net.tslat.aoa3.worldgen.structures.abyss;


import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class EyeHanger1 extends AoAStructure { //StructureSize: 3x7x3
	private static final BlockState eyeBlock = AoABlocks.EYE_BLOCK.get().getDefaultState();
	private static final BlockState bloodLog = AoABlocks.BLOOD_LOG.get().getDefaultState();
	private static final BlockState bloodLogBark = AoABlocks.BLOOD_WOOD.get().getDefaultState();
	private static final BlockState bloodStrands = AoABlocks.BLOOD_STRANDS.get().getDefaultState();

	public EyeHanger1() {
		super("EyeHanger1");
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 1, 0, 1, bloodLog);
		addBlock(world, basePos, 1, 1, 1, bloodLog);
		addBlock(world, basePos, 1, 2, 1, bloodLog);
		addBlock(world, basePos, 0, 3, 1, eyeBlock);
		addBlock(world, basePos, 1, 3, 0, eyeBlock);
		addBlock(world, basePos, 1, 3, 1, bloodLog);
		addBlock(world, basePos, 1, 3, 2, eyeBlock);
		addBlock(world, basePos, 2, 3, 1, eyeBlock);
		addBlock(world, basePos, 0, 4, 1, bloodStrands);
		addBlock(world, basePos, 1, 4, 0, bloodStrands);
		addBlock(world, basePos, 1, 4, 1, bloodLog);
		addBlock(world, basePos, 1, 4, 2, bloodStrands);
		addBlock(world, basePos, 2, 4, 1, bloodStrands);
		addBlock(world, basePos, 0, 5, 1, bloodStrands);
		addBlock(world, basePos, 1, 5, 0, bloodStrands);
		addBlock(world, basePos, 1, 5, 1, bloodLog);
		addBlock(world, basePos, 1, 5, 2, bloodStrands);
		addBlock(world, basePos, 2, 5, 1, bloodStrands);
		addBlock(world, basePos, 0, 6, 1, bloodLogBark);
		addBlock(world, basePos, 1, 6, 0, bloodLogBark);
		addBlock(world, basePos, 1, 6, 1, bloodLogBark);
		addBlock(world, basePos, 1, 6, 2, bloodLogBark);
		addBlock(world, basePos, 2, 6, 1, bloodLogBark);
	}
}
