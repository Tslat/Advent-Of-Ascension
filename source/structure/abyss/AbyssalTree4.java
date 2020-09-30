package net.tslat.aoa3.structure.abyss;

import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

import static net.minecraft.block.BlockLog.LOG_AXIS;

public class AbyssalTree4 extends AoAStructure { //StructureSize: 8x10x7
	private static final IBlockState shadowbloodLeaves = BlockRegister.SHADOWBLOOD_LEAVES.getDefaultState();
	private static final IBlockState bloodStrands = BlockRegister.BLOOD_STRANDS.getDefaultState();
	private static final IBlockState shadowLog = BlockRegister.SHADOW_LOG.getDefaultState();
	private static final IBlockState shadowLogBark = BlockRegister.SHADOW_LOG.getDefaultState().withProperty(LOG_AXIS, BlockLog.EnumAxis.NONE);
	private static final IBlockState eyeBlock = BlockRegister.EYE_BLOCK.getDefaultState();

	public AbyssalTree4() {
		super("AbyssalTree4");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 3, 0, 3, shadowLog);
		addBlock(world, basePos, 3, 1, 3, shadowLog);
		addBlock(world, basePos, 3, 2, 3, shadowLog);
		addBlock(world, basePos, 0, 3, 3, eyeBlock);
		addBlock(world, basePos, 3, 3, 0, eyeBlock);
		addBlock(world, basePos, 3, 3, 3, shadowLog);
		addBlock(world, basePos, 3, 3, 6, eyeBlock);
		addBlock(world, basePos, 6, 3, 3, eyeBlock);
		addBlock(world, basePos, 0, 4, 3, bloodStrands);
		addBlock(world, basePos, 3, 4, 0, bloodStrands);
		addBlock(world, basePos, 3, 4, 3, shadowLog);
		addBlock(world, basePos, 3, 4, 6, bloodStrands);
		addBlock(world, basePos, 6, 4, 3, bloodStrands);
		addBlock(world, basePos, 0, 5, 3, bloodStrands);
		addBlock(world, basePos, 3, 5, 0, bloodStrands);
		addBlock(world, basePos, 3, 5, 3, shadowLog);
		addBlock(world, basePos, 3, 5, 6, bloodStrands);
		addBlock(world, basePos, 6, 5, 3, bloodStrands);
		addBlock(world, basePos, 0, 6, 3, bloodStrands);
		addBlock(world, basePos, 1, 6, 3, shadowLogBark);
		addBlock(world, basePos, 2, 6, 3, shadowLogBark);
		addBlock(world, basePos, 3, 6, 0, bloodStrands);
		addBlock(world, basePos, 3, 6, 1, shadowLogBark);
		addBlock(world, basePos, 3, 6, 2, shadowLogBark);
		addBlock(world, basePos, 3, 6, 3, shadowLog);
		addBlock(world, basePos, 3, 6, 4, shadowLogBark);
		addBlock(world, basePos, 3, 6, 5, shadowLogBark);
		addBlock(world, basePos, 3, 6, 6, bloodStrands);
		addBlock(world, basePos, 4, 6, 3, shadowLogBark);
		addBlock(world, basePos, 5, 6, 3, shadowLogBark);
		addBlock(world, basePos, 6, 6, 3, bloodStrands);
		addBlock(world, basePos, 0, 7, 2, shadowbloodLeaves);
		addBlock(world, basePos, 0, 7, 3, shadowbloodLeaves);
		addBlock(world, basePos, 0, 7, 4, shadowbloodLeaves);
		addBlock(world, basePos, 1, 7, 1, shadowbloodLeaves);
		addBlock(world, basePos, 1, 7, 2, shadowbloodLeaves);
		addBlock(world, basePos, 1, 7, 3, shadowLog);
		addBlock(world, basePos, 1, 7, 4, shadowbloodLeaves);
		addBlock(world, basePos, 1, 7, 5, shadowbloodLeaves);
		addBlock(world, basePos, 2, 7, 0, shadowbloodLeaves);
		addBlock(world, basePos, 2, 7, 1, shadowbloodLeaves);
		addBlock(world, basePos, 2, 7, 2, shadowbloodLeaves);
		addBlock(world, basePos, 2, 7, 3, shadowbloodLeaves);
		addBlock(world, basePos, 2, 7, 4, shadowbloodLeaves);
		addBlock(world, basePos, 2, 7, 5, shadowbloodLeaves);
		addBlock(world, basePos, 2, 7, 6, shadowbloodLeaves);
		addBlock(world, basePos, 3, 7, 0, shadowbloodLeaves);
		addBlock(world, basePos, 3, 7, 1, shadowLog);
		addBlock(world, basePos, 3, 7, 2, shadowbloodLeaves);
		addBlock(world, basePos, 3, 7, 3, shadowbloodLeaves);
		addBlock(world, basePos, 3, 7, 5, shadowLog);
		addBlock(world, basePos, 3, 7, 6, shadowbloodLeaves);
		addBlock(world, basePos, 4, 7, 0, shadowbloodLeaves);
		addBlock(world, basePos, 4, 7, 1, shadowbloodLeaves);
		addBlock(world, basePos, 4, 7, 2, shadowbloodLeaves);
		addBlock(world, basePos, 4, 7, 3, shadowbloodLeaves);
		addBlock(world, basePos, 4, 7, 5, shadowbloodLeaves);
		addBlock(world, basePos, 4, 7, 6, shadowbloodLeaves);
		addBlock(world, basePos, 5, 7, 1, shadowbloodLeaves);
		addBlock(world, basePos, 5, 7, 2, shadowbloodLeaves);
		addBlock(world, basePos, 5, 7, 3, shadowLog);
		addBlock(world, basePos, 5, 7, 4, shadowbloodLeaves);
		addBlock(world, basePos, 5, 7, 5, shadowbloodLeaves);
		addBlock(world, basePos, 6, 7, 2, shadowbloodLeaves);
		addBlock(world, basePos, 6, 7, 3, shadowbloodLeaves);
		addBlock(world, basePos, 6, 7, 4, shadowbloodLeaves);
		addBlock(world, basePos, 7, 7, 3, shadowbloodLeaves);
		addBlock(world, basePos, 1, 8, 3, shadowbloodLeaves);
		addBlock(world, basePos, 2, 8, 2, shadowbloodLeaves);
		addBlock(world, basePos, 2, 8, 3, shadowbloodLeaves);
		addBlock(world, basePos, 2, 8, 4, shadowbloodLeaves);
		addBlock(world, basePos, 3, 8, 1, shadowbloodLeaves);
		addBlock(world, basePos, 3, 8, 2, shadowbloodLeaves);
		addBlock(world, basePos, 3, 8, 3, shadowbloodLeaves);
		addBlock(world, basePos, 3, 8, 4, shadowbloodLeaves);
		addBlock(world, basePos, 3, 8, 5, shadowbloodLeaves);
		addBlock(world, basePos, 4, 8, 2, shadowbloodLeaves);
		addBlock(world, basePos, 4, 8, 3, shadowbloodLeaves);
		addBlock(world, basePos, 4, 8, 4, shadowbloodLeaves);
		addBlock(world, basePos, 5, 8, 3, shadowbloodLeaves);
		addBlock(world, basePos, 3, 9, 3, shadowbloodLeaves);
	}
}
