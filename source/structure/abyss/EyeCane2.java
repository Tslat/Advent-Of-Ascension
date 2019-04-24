package net.tslat.aoa3.structure.abyss;

import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

import static net.minecraft.block.BlockLog.LOG_AXIS;

public class EyeCane2 extends AoAStructure { //StructureSize: 3x8x1
	private static final IBlockState bloodLog = BlockRegister.logBlood.getDefaultState();
	private static final IBlockState bloodLogBark = BlockRegister.logBlood.getDefaultState().withProperty(LOG_AXIS, BlockLog.EnumAxis.NONE);
	private static final IBlockState bloodStrands = BlockRegister.plantBloodStrands.getDefaultState();
	private static final IBlockState eyeBlock = BlockRegister.eyeBlock.getDefaultState();

	public EyeCane2() {
		super("EyeCane2");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
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
