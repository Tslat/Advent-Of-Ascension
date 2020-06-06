package net.tslat.aoa3.structure.abyss;

import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

import static net.minecraft.block.BlockLog.LOG_AXIS;

public class EyeHanger1 extends AoAStructure { //StructureSize: 3x7x3
	private static final IBlockState eyeBlock = BlockRegister.EYE_BLOCK.getDefaultState();
	private static final IBlockState bloodLog = BlockRegister.BLOOD_LOG.getDefaultState();
	private static final IBlockState bloodLogBark = BlockRegister.BLOOD_LOG.getDefaultState().withProperty(LOG_AXIS, BlockLog.EnumAxis.NONE);
	private static final IBlockState bloodStrands = BlockRegister.BLOOD_STRANDS.getDefaultState();

	public EyeHanger1() {
		super("EyeHanger1");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
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
