package net.tslat.aoa3.structure.abyss;

import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

import static net.minecraft.block.BlockLog.LOG_AXIS;

public class AbyssalTree1 extends AoAStructure { //StructureSize: 6x16x6
	private static final IBlockState bloodLeaves = BlockRegister.BLOOD_LEAVES.getDefaultState();
	private static final IBlockState bloodLog = BlockRegister.BLOOD_LOG.getDefaultState();
	private static final IBlockState eyeballLog = BlockRegister.EYEBALL_LOG.getDefaultState();
	private static final IBlockState bloodLogBark = bloodLog.withProperty(LOG_AXIS, BlockLog.EnumAxis.NONE);
	
	public AbyssalTree1() {
		super("AbyssalTree1");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 3, 0, 3, bloodLog);
		addBlock(world, basePos, 3, 1, 3, bloodLog);
		addBlock(world, basePos, 3, 2, 3, eyeballLog);
		addBlock(world, basePos, 3, 3, 3, bloodLog);
		addBlock(world, basePos, 3, 4, 3, bloodLog);
		addBlock(world, basePos, 3, 5, 3, bloodLog);
		addBlock(world, basePos, 0, 6, 2, bloodLeaves);
		addBlock(world, basePos, 0, 6, 3, bloodLeaves);
		addBlock(world, basePos, 1, 6, 3, bloodLeaves);
		addBlock(world, basePos, 2, 6, 3, bloodLogBark);
		addBlock(world, basePos, 2, 6, 6, bloodLeaves);
		addBlock(world, basePos, 3, 6, 0, bloodLeaves);
		addBlock(world, basePos, 3, 6, 1, bloodLeaves);
		addBlock(world, basePos, 3, 6, 2, bloodLogBark);
		addBlock(world, basePos, 3, 6, 3, bloodLogBark);
		addBlock(world, basePos, 3, 6, 4, bloodLogBark);
		addBlock(world, basePos, 3, 6, 5, bloodLeaves);
		addBlock(world, basePos, 3, 6, 6, bloodLeaves);
		addBlock(world, basePos, 4, 6, 0, bloodLeaves);
		addBlock(world, basePos, 4, 6, 3, bloodLogBark);
		addBlock(world, basePos, 5, 6, 3, bloodLeaves);
		addBlock(world, basePos, 6, 6, 3, bloodLeaves);
		addBlock(world, basePos, 6, 6, 4, bloodLeaves);
		addBlock(world, basePos, 0, 7, 1, bloodLeaves);
		addBlock(world, basePos, 0, 7, 2, bloodLeaves);
		addBlock(world, basePos, 1, 7, 6, bloodLeaves);
		addBlock(world, basePos, 2, 7, 6, bloodLeaves);
		addBlock(world, basePos, 4, 7, 0, bloodLeaves);
		addBlock(world, basePos, 5, 7, 0, bloodLeaves);
		addBlock(world, basePos, 6, 7, 4, bloodLeaves);
		addBlock(world, basePos, 6, 7, 5, bloodLeaves);
		addBlock(world, basePos, 0, 8, 1, bloodLeaves);
		addBlock(world, basePos, 1, 8, 1, bloodLeaves);
		addBlock(world, basePos, 1, 8, 5, bloodLeaves);
		addBlock(world, basePos, 1, 8, 6, bloodLeaves);
		addBlock(world, basePos, 5, 8, 0, bloodLeaves);
		addBlock(world, basePos, 5, 8, 1, bloodLeaves);
		addBlock(world, basePos, 5, 8, 5, bloodLeaves);
		addBlock(world, basePos, 6, 8, 5, bloodLeaves);
		addBlock(world, basePos, 1, 9, 1, bloodLeaves);
		addBlock(world, basePos, 1, 9, 4, bloodLeaves);
		addBlock(world, basePos, 1, 9, 5, bloodLeaves);
		addBlock(world, basePos, 2, 9, 1, bloodLeaves);
		addBlock(world, basePos, 2, 9, 4, bloodLeaves);
		addBlock(world, basePos, 4, 9, 5, bloodLeaves);
		addBlock(world, basePos, 5, 9, 1, bloodLeaves);
		addBlock(world, basePos, 5, 9, 2, bloodLeaves);
		addBlock(world, basePos, 5, 9, 5, bloodLeaves);
		addBlock(world, basePos, 2, 10, 1, bloodLeaves);
		addBlock(world, basePos, 2, 10, 2, bloodLeaves);
		addBlock(world, basePos, 2, 10, 4, bloodLeaves);
		addBlock(world, basePos, 3, 10, 4, bloodLeaves);
		addBlock(world, basePos, 4, 10, 2, bloodLeaves);
		addBlock(world, basePos, 4, 10, 4, bloodLeaves);
		addBlock(world, basePos, 4, 10, 5, bloodLeaves);
		addBlock(world, basePos, 5, 10, 2, bloodLeaves);
		addBlock(world, basePos, 2, 11, 2, bloodLeaves);
		addBlock(world, basePos, 2, 11, 3, bloodLeaves);
		addBlock(world, basePos, 3, 11, 2, bloodLeaves);
		addBlock(world, basePos, 3, 11, 4, bloodLeaves);
		addBlock(world, basePos, 3, 11, 5, bloodLeaves);
		addBlock(world, basePos, 4, 11, 2, bloodLeaves);
		addBlock(world, basePos, 4, 11, 3, bloodLeaves);
		addBlock(world, basePos, 4, 11, 4, bloodLeaves);
		addBlock(world, basePos, 1, 12, 3, bloodLeaves);
		addBlock(world, basePos, 2, 12, 3, bloodLeaves);
		addBlock(world, basePos, 2, 12, 6, bloodLeaves);
		addBlock(world, basePos, 3, 12, 1, bloodLeaves);
		addBlock(world, basePos, 3, 12, 2, bloodLeaves);
		addBlock(world, basePos, 3, 12, 5, bloodLeaves);
		addBlock(world, basePos, 3, 12, 6, bloodLeaves);
		addBlock(world, basePos, 4, 12, 3, bloodLeaves);
		addBlock(world, basePos, 5, 12, 3, bloodLeaves);
		addBlock(world, basePos, 0, 13, 3, bloodLeaves);
		addBlock(world, basePos, 1, 13, 3, bloodLeaves);
		addBlock(world, basePos, 1, 13, 6, bloodLeaves);
		addBlock(world, basePos, 2, 13, 6, bloodLeaves);
		addBlock(world, basePos, 3, 13, 0, bloodLeaves);
		addBlock(world, basePos, 3, 13, 1, bloodLeaves);
		addBlock(world, basePos, 5, 13, 3, bloodLeaves);
		addBlock(world, basePos, 6, 13, 3, bloodLeaves);
		addBlock(world, basePos, 0, 14, 2, bloodLeaves);
		addBlock(world, basePos, 0, 14, 3, bloodLeaves);
		addBlock(world, basePos, 1, 14, 5, bloodLeaves);
		addBlock(world, basePos, 1, 14, 6, bloodLeaves);
		addBlock(world, basePos, 3, 14, 0, bloodLeaves);
		addBlock(world, basePos, 4, 14, 0, bloodLeaves);
		addBlock(world, basePos, 6, 14, 3, bloodLeaves);
		addBlock(world, basePos, 6, 14, 4, bloodLeaves);
		addBlock(world, basePos, 0, 15, 1, bloodLeaves);
		addBlock(world, basePos, 0, 15, 2, bloodLeaves);
		addBlock(world, basePos, 1, 15, 4, bloodLeaves);
		addBlock(world, basePos, 1, 15, 5, bloodLeaves);
		addBlock(world, basePos, 4, 15, 0, bloodLeaves);
		addBlock(world, basePos, 5, 15, 0, bloodLeaves);
		addBlock(world, basePos, 6, 15, 4, bloodLeaves);
		addBlock(world, basePos, 6, 15, 5, bloodLeaves);
		addBlock(world, basePos, 0, 16, 1, bloodLeaves);
		addBlock(world, basePos, 1, 16, 4, bloodLeaves);
		addBlock(world, basePos, 5, 16, 0, bloodLeaves);
		addBlock(world, basePos, 6, 16, 5, bloodLeaves);
	}
}
