package net.tslat.aoa3.structure.abyss;

import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

import static net.minecraft.block.BlockLog.LOG_AXIS;

public class EyeHanger2 extends AoAStructure { //StructureSize: 3x9x3
	private static final IBlockState eyeBlock = BlockRegister.EYE_BLOCK.getDefaultState();
	private static final IBlockState bloodLog = BlockRegister.BLOOD_LOG.getDefaultState();
	private static final IBlockState bloodLogBark = BlockRegister.BLOOD_LOG.getDefaultState().withProperty(LOG_AXIS, BlockLog.EnumAxis.NONE);

	public EyeHanger2() {
		super("EyeHanger2");
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
