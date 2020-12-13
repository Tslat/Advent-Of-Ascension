package net.tslat.aoa3.worldgen.structures.abyss;


import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class EyeCane3 extends AoAStructure { //StructureSize: 1x8x3
	private static final BlockState bloodLog = AoABlocks.BLOOD_LOG.get().getDefaultState();
	private static final BlockState bloodLogBark = AoABlocks.BLOOD_WOOD.get().getDefaultState();
	private static final BlockState bloodStrands = AoABlocks.BLOOD_STRANDS.get().getDefaultState();
	private static final BlockState eyeBlock = AoABlocks.EYE_BLOCK.get().getDefaultState();

	public EyeCane3() {
		super("EyeCane3");
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 0, bloodLog);
		addBlock(world, basePos, 0, 1, 0, bloodLog);
		addBlock(world, basePos, 0, 2, 0, bloodLog);
		addBlock(world, basePos, 0, 3, 0, bloodLog);
		addBlock(world, basePos, 0, 3, 2, eyeBlock);
		addBlock(world, basePos, 0, 4, 0, bloodLog);
		addBlock(world, basePos, 0, 4, 2, bloodStrands);
		addBlock(world, basePos, 0, 5, 0, bloodLog);
		addBlock(world, basePos, 0, 5, 2, bloodStrands);
		addBlock(world, basePos, 0, 6, 0, bloodLog);
		addBlock(world, basePos, 0, 6, 2, bloodStrands);
		addBlock(world, basePos, 0, 7, 0, bloodLogBark);
		addBlock(world, basePos, 0, 7, 1, bloodLogBark);
		addBlock(world, basePos, 0, 7, 2, bloodLogBark);
	}
}
