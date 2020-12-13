package net.tslat.aoa3.worldgen.structures.abyss;


import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class EyeHanger2 extends AoAStructure { //StructureSize: 3x9x3
	private static final BlockState eyeBlock = AoABlocks.EYE_BLOCK.get().getDefaultState();
	private static final BlockState bloodLog = AoABlocks.BLOOD_LOG.get().getDefaultState();
	private static final BlockState bloodLogBark = AoABlocks.BLOOD_WOOD.get().getDefaultState();

	public EyeHanger2() {
		super("EyeHanger2");
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
		addBlock(world, basePos, 0, 4, 1, bloodLog);
		addBlock(world, basePos, 1, 4, 0, bloodLog);
		addBlock(world, basePos, 1, 4, 1, bloodLog);
		addBlock(world, basePos, 1, 4, 2, bloodLog);
		addBlock(world, basePos, 2, 4, 1, bloodLog);
		addBlock(world, basePos, 1, 5, 1, bloodLog);
		addBlock(world, basePos, 1, 6, 1, bloodLog);
		addBlock(world, basePos, 0, 7, 1, eyeBlock);
		addBlock(world, basePos, 1, 7, 0, eyeBlock);
		addBlock(world, basePos, 1, 7, 1, bloodLog);
		addBlock(world, basePos, 1, 7, 2, eyeBlock);
		addBlock(world, basePos, 2, 7, 1, eyeBlock);
		addBlock(world, basePos, 0, 8, 1, bloodLogBark);
		addBlock(world, basePos, 1, 8, 0, bloodLogBark);
		addBlock(world, basePos, 1, 8, 1, bloodLogBark);
		addBlock(world, basePos, 1, 8, 2, bloodLogBark);
		addBlock(world, basePos, 2, 8, 1, bloodLogBark);
	}
}
