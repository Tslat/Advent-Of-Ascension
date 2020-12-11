package net.tslat.aoa3.worldgen.structures.abyss;


import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class EyeCane2 extends AoAStructure { //StructureSize: 3x8x1
	private static final BlockState bloodLog = AoABlocks.BLOOD_LOG.get().getDefaultState();
	private static final BlockState bloodLogBark = AoABlocks.BLOOD_WOOD.get().getDefaultState();
	private static final BlockState bloodStrands = AoABlocks.BLOOD_STRANDS.get().getDefaultState();
	private static final BlockState eyeBlock = AoABlocks.EYE_BLOCK.get().getDefaultState();

	public EyeCane2() {
		super("EyeCane2");
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 2, 0, 0, bloodLog);
		addBlock(world, basePos, 2, 1, 0, bloodLog);
		addBlock(world, basePos, 2, 2, 0, bloodLog);
		addBlock(world, basePos, 0, 3, 0, eyeBlock);
		addBlock(world, basePos, 2, 3, 0, bloodLog);
		addBlock(world, basePos, 0, 4, 0, bloodStrands);
		addBlock(world, basePos, 2, 4, 0, bloodLog);
		addBlock(world, basePos, 0, 5, 0, bloodStrands);
		addBlock(world, basePos, 2, 5, 0, bloodLog);
		addBlock(world, basePos, 0, 6, 0, bloodStrands);
		addBlock(world, basePos, 2, 6, 0, bloodLog);
		addBlock(world, basePos, 0, 7, 0, bloodLogBark);
		addBlock(world, basePos, 1, 7, 0, bloodLogBark);
		addBlock(world, basePos, 2, 7, 0, bloodLogBark);
	}
}
