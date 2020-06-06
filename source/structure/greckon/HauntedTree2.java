package net.tslat.aoa3.structure.greckon;

import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

import static net.minecraft.block.BlockLog.LOG_AXIS;

public class HauntedTree2 extends AoAStructure { //StructureSize: 9x13x9
	private static final IBlockState hauntedLeaves = BlockRegister.HAUNTED_LEAVES.getDefaultState();
	private static final IBlockState hauntedLog = BlockRegister.HAUNTED_LOG.getDefaultState();
	private static final IBlockState hauntedLogBark = BlockRegister.HAUNTED_LOG.getDefaultState().withProperty(LOG_AXIS, BlockLog.EnumAxis.NONE);
	private static final IBlockState hauntedEyesLeaves = BlockRegister.HAUNTED_EYES_LEAVES.getDefaultState();
	private static final IBlockState hauntedEyesLog = BlockRegister.HAUNTED_EYES_LOG.getDefaultState();
	private static final IBlockState hauntedLargeEyeLog = BlockRegister.HAUNTED_EYE_LOG.getDefaultState();

	public HauntedTree2() {
		super("HauntedTree2");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 4, 0, 4, hauntedLog);
		addBlock(world, basePos, 4, 1, 4, hauntedLog);
		addBlock(world, basePos, 4, 2, 4, hauntedLog);
		addBlock(world, basePos, 4, 3, 4, hauntedLog);
		addBlock(world, basePos, 4, 5, 4, hauntedLog);
		addBlock(world, basePos, 4, 6, 4, hauntedLog);
		addBlock(world, basePos, 4, 8, 4, hauntedLog);
		addBlock(world, basePos, 0, 9, 3, hauntedLeaves);
		addBlock(world, basePos, 0, 9, 5, hauntedLeaves);
		addBlock(world, basePos, 1, 9, 7, hauntedLeaves);
		addBlock(world, basePos, 3, 9, 0, hauntedLeaves);
		addBlock(world, basePos, 3, 9, 8, hauntedLeaves);
		addBlock(world, basePos, 4, 9, 4, hauntedLog);
		addBlock(world, basePos, 5, 9, 0, hauntedLeaves);
		addBlock(world, basePos, 5, 9, 8, hauntedLeaves);
		addBlock(world, basePos, 7, 9, 1, hauntedLeaves);
		addBlock(world, basePos, 7, 9, 7, hauntedLeaves);
		addBlock(world, basePos, 8, 9, 3, hauntedLeaves);
		addBlock(world, basePos, 8, 9, 5, hauntedLeaves);
		addBlock(world, basePos, 0, 10, 3, hauntedLeaves);
		addBlock(world, basePos, 0, 10, 5, hauntedLeaves);
		addBlock(world, basePos, 1, 10, 1, hauntedLeaves);
		addBlock(world, basePos, 1, 10, 7, hauntedLeaves);
		addBlock(world, basePos, 2, 10, 4, hauntedLogBark);
		addBlock(world, basePos, 3, 10, 0, hauntedLeaves);
		addBlock(world, basePos, 3, 10, 4, hauntedLogBark);
		addBlock(world, basePos, 3, 10, 8, hauntedLeaves);
		addBlock(world, basePos, 4, 10, 2, hauntedLogBark);
		addBlock(world, basePos, 4, 10, 3, hauntedLogBark);
		addBlock(world, basePos, 4, 10, 4, hauntedLogBark);
		addBlock(world, basePos, 4, 10, 5, hauntedLogBark);
		addBlock(world, basePos, 4, 10, 6, hauntedLogBark);
		addBlock(world, basePos, 5, 10, 0, hauntedLeaves);
		addBlock(world, basePos, 5, 10, 4, hauntedLogBark);
		addBlock(world, basePos, 5, 10, 8, hauntedLeaves);
		addBlock(world, basePos, 6, 10, 4, hauntedLogBark);
		addBlock(world, basePos, 7, 10, 1, hauntedLeaves);
		addBlock(world, basePos, 7, 10, 7, hauntedLeaves);
		addBlock(world, basePos, 8, 10, 3, hauntedLeaves);
		addBlock(world, basePos, 8, 10, 5, hauntedLeaves);
		addBlock(world, basePos, 0, 11, 3, hauntedLeaves);
		addBlock(world, basePos, 0, 11, 5, hauntedLeaves);
		addBlock(world, basePos, 1, 11, 1, hauntedLeaves);
		addBlock(world, basePos, 1, 11, 7, hauntedLeaves);
		addBlock(world, basePos, 2, 11, 4, hauntedLog);
		addBlock(world, basePos, 3, 11, 0, hauntedLeaves);
		addBlock(world, basePos, 3, 11, 8, hauntedLeaves);
		addBlock(world, basePos, 4, 11, 2, hauntedLog);
		addBlock(world, basePos, 4, 11, 6, hauntedLog);
		addBlock(world, basePos, 5, 11, 0, hauntedLeaves);
		addBlock(world, basePos, 5, 11, 8, hauntedLeaves);
		addBlock(world, basePos, 6, 11, 4, hauntedLog);
		addBlock(world, basePos, 7, 11, 1, hauntedLeaves);
		addBlock(world, basePos, 7, 11, 7, hauntedLeaves);
		addBlock(world, basePos, 8, 11, 3, hauntedLeaves);
		addBlock(world, basePos, 8, 11, 5, hauntedLeaves);
		addBlock(world, basePos, 0, 12, 3, hauntedLeaves);
		addBlock(world, basePos, 0, 12, 4, hauntedLeaves);
		addBlock(world, basePos, 0, 12, 5, hauntedLeaves);
		addBlock(world, basePos, 1, 12, 1, hauntedLeaves);
		addBlock(world, basePos, 1, 12, 2, hauntedLeaves);
		addBlock(world, basePos, 1, 12, 3, hauntedLeaves);
		addBlock(world, basePos, 1, 12, 4, hauntedLeaves);
		addBlock(world, basePos, 1, 12, 5, hauntedLeaves);
		addBlock(world, basePos, 1, 12, 6, hauntedLeaves);
		addBlock(world, basePos, 1, 12, 7, hauntedLeaves);
		addBlock(world, basePos, 2, 12, 1, hauntedLeaves);
		addBlock(world, basePos, 2, 12, 2, hauntedLeaves);
		addBlock(world, basePos, 2, 12, 3, hauntedLeaves);
		addBlock(world, basePos, 2, 12, 4, hauntedLeaves);
		addBlock(world, basePos, 2, 12, 5, hauntedLeaves);
		addBlock(world, basePos, 2, 12, 6, hauntedLeaves);
		addBlock(world, basePos, 2, 12, 7, hauntedLeaves);
		addBlock(world, basePos, 3, 12, 0, hauntedLeaves);
		addBlock(world, basePos, 3, 12, 1, hauntedLeaves);
		addBlock(world, basePos, 3, 12, 2, hauntedLeaves);
		addBlock(world, basePos, 3, 12, 3, hauntedLeaves);
		addBlock(world, basePos, 3, 12, 4, hauntedLeaves);
		addBlock(world, basePos, 3, 12, 5, hauntedLeaves);
		addBlock(world, basePos, 3, 12, 6, hauntedLeaves);
		addBlock(world, basePos, 3, 12, 7, hauntedLeaves);
		addBlock(world, basePos, 3, 12, 8, hauntedLeaves);
		addBlock(world, basePos, 4, 12, 0, hauntedLeaves);
		addBlock(world, basePos, 4, 12, 1, hauntedLeaves);
		addBlock(world, basePos, 4, 12, 2, hauntedLeaves);
		addBlock(world, basePos, 4, 12, 3, hauntedLeaves);
		addBlock(world, basePos, 4, 12, 5, hauntedLeaves);
		addBlock(world, basePos, 4, 12, 6, hauntedLeaves);
		addBlock(world, basePos, 4, 12, 7, hauntedLeaves);
		addBlock(world, basePos, 4, 12, 8, hauntedLeaves);
		addBlock(world, basePos, 5, 12, 0, hauntedLeaves);
		addBlock(world, basePos, 5, 12, 1, hauntedLeaves);
		addBlock(world, basePos, 5, 12, 2, hauntedLeaves);
		addBlock(world, basePos, 5, 12, 3, hauntedLeaves);
		addBlock(world, basePos, 5, 12, 4, hauntedLeaves);
		addBlock(world, basePos, 5, 12, 5, hauntedLeaves);
		addBlock(world, basePos, 5, 12, 6, hauntedLeaves);
		addBlock(world, basePos, 5, 12, 7, hauntedLeaves);
		addBlock(world, basePos, 5, 12, 8, hauntedLeaves);
		addBlock(world, basePos, 6, 12, 1, hauntedLeaves);
		addBlock(world, basePos, 6, 12, 2, hauntedLeaves);
		addBlock(world, basePos, 6, 12, 3, hauntedLeaves);
		addBlock(world, basePos, 6, 12, 4, hauntedLeaves);
		addBlock(world, basePos, 6, 12, 5, hauntedLeaves);
		addBlock(world, basePos, 6, 12, 6, hauntedLeaves);
		addBlock(world, basePos, 6, 12, 7, hauntedLeaves);
		addBlock(world, basePos, 7, 12, 1, hauntedLeaves);
		addBlock(world, basePos, 7, 12, 2, hauntedLeaves);
		addBlock(world, basePos, 7, 12, 3, hauntedLeaves);
		addBlock(world, basePos, 7, 12, 4, hauntedLeaves);
		addBlock(world, basePos, 7, 12, 5, hauntedLeaves);
		addBlock(world, basePos, 7, 12, 6, hauntedLeaves);
		addBlock(world, basePos, 7, 12, 7, hauntedLeaves);
		addBlock(world, basePos, 8, 12, 3, hauntedLeaves);
		addBlock(world, basePos, 8, 12, 4, hauntedLeaves);
		addBlock(world, basePos, 8, 12, 5, hauntedLeaves);

		if (rand.nextInt(3) == 0) {
			addBlock(world, basePos, 1, 9, 1, hauntedLeaves);
		}
		else {
			addBlock(world, basePos, 1, 9, 1, hauntedEyesLeaves);
		}

		if (rand.nextInt(3) == 0) {
			addBlock(world, basePos, 4, 4, 4, hauntedLog);
		}
		else {
			addBlock(world, basePos, 4, 4, 4, hauntedLargeEyeLog);
		}

		if (rand.nextInt(3) == 0) {
			addBlock(world, basePos, 4, 7, 4, hauntedLog);
		}
		else {
			addBlock(world, basePos, 4, 7, 4, hauntedEyesLog);
		}

		if (rand.nextInt(3) == 0) {
			addBlock(world, basePos, 4, 12, 4, hauntedLeaves);
		}
		else {
			addBlock(world, basePos, 4, 12, 4, hauntedEyesLeaves);
		}
	}
}
