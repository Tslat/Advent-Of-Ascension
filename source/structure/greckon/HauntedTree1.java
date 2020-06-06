package net.tslat.aoa3.structure.greckon;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class HauntedTree1 extends AoAStructure { //StructureSize: 7x16x7
	private static final IBlockState hauntedLeaves = BlockRegister.HAUNTED_LEAVES.getDefaultState();
	private static final IBlockState hauntedLog = BlockRegister.HAUNTED_LOG.getDefaultState();
	private static final IBlockState hauntedEyesLeaves = BlockRegister.HAUNTED_EYES_LEAVES.getDefaultState();
	private static final IBlockState hauntedFlashingFaceLog = BlockRegister.HAUNTED_FLASHING_LOG.getDefaultState();
	private static final IBlockState hauntedEyesLog = BlockRegister.HAUNTED_EYES_LOG.getDefaultState();

	public HauntedTree1() {
		super("HauntedTree1");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 3, 0, 3, hauntedLog);
		addBlock(world, basePos, 3, 1, 3, hauntedLog);
		addBlock(world, basePos, 3, 3, 3, hauntedLog);
		addBlock(world, basePos, 3, 4, 3, hauntedLog);
		addBlock(world, basePos, 1, 5, 1, hauntedLeaves);
		addBlock(world, basePos, 1, 5, 2, hauntedLeaves);
		addBlock(world, basePos, 1, 5, 3, hauntedLeaves);
		addBlock(world, basePos, 1, 5, 4, hauntedLeaves);
		addBlock(world, basePos, 1, 5, 5, hauntedLeaves);
		addBlock(world, basePos, 2, 5, 1, hauntedLeaves);
		addBlock(world, basePos, 2, 5, 3, hauntedLeaves);
		addBlock(world, basePos, 2, 5, 4, hauntedLeaves);
		addBlock(world, basePos, 2, 5, 5, hauntedLeaves);
		addBlock(world, basePos, 3, 5, 2, hauntedLeaves);
		addBlock(world, basePos, 3, 5, 3, hauntedLog);
		addBlock(world, basePos, 3, 5, 4, hauntedLeaves);
		addBlock(world, basePos, 3, 5, 5, hauntedLeaves);
		addBlock(world, basePos, 4, 5, 1, hauntedLeaves);
		addBlock(world, basePos, 4, 5, 2, hauntedLeaves);
		addBlock(world, basePos, 4, 5, 3, hauntedLeaves);
		addBlock(world, basePos, 4, 5, 4, hauntedLeaves);
		addBlock(world, basePos, 4, 5, 5, hauntedLeaves);
		addBlock(world, basePos, 5, 5, 1, hauntedLeaves);
		addBlock(world, basePos, 5, 5, 2, hauntedLeaves);
		addBlock(world, basePos, 5, 5, 3, hauntedLeaves);
		addBlock(world, basePos, 5, 5, 5, hauntedLeaves);
		addBlock(world, basePos, 1, 6, 1, hauntedLeaves);
		addBlock(world, basePos, 1, 6, 2, hauntedLeaves);
		addBlock(world, basePos, 1, 6, 3, hauntedLeaves);
		addBlock(world, basePos, 1, 6, 4, hauntedLeaves);
		addBlock(world, basePos, 2, 6, 1, hauntedLeaves);
		addBlock(world, basePos, 2, 6, 5, hauntedLeaves);
		addBlock(world, basePos, 3, 6, 1, hauntedLeaves);
		addBlock(world, basePos, 3, 6, 3, hauntedLog);
		addBlock(world, basePos, 3, 6, 5, hauntedLeaves);
		addBlock(world, basePos, 4, 6, 1, hauntedLeaves);
		addBlock(world, basePos, 4, 6, 5, hauntedLeaves);
		addBlock(world, basePos, 5, 6, 1, hauntedLeaves);
		addBlock(world, basePos, 5, 6, 2, hauntedLeaves);
		addBlock(world, basePos, 5, 6, 3, hauntedLeaves);
		addBlock(world, basePos, 5, 6, 4, hauntedLeaves);
		addBlock(world, basePos, 5, 6, 5, hauntedLeaves);
		addBlock(world, basePos, 1, 7, 1, hauntedLeaves);
		addBlock(world, basePos, 1, 7, 2, hauntedLeaves);
		addBlock(world, basePos, 1, 7, 3, hauntedLeaves);
		addBlock(world, basePos, 1, 7, 4, hauntedLeaves);
		addBlock(world, basePos, 1, 7, 5, hauntedLeaves);
		addBlock(world, basePos, 2, 7, 1, hauntedLeaves);
		addBlock(world, basePos, 2, 7, 5, hauntedLeaves);
		addBlock(world, basePos, 3, 7, 1, hauntedLeaves);
		addBlock(world, basePos, 3, 7, 5, hauntedLeaves);
		addBlock(world, basePos, 4, 7, 1, hauntedLeaves);
		addBlock(world, basePos, 4, 7, 5, hauntedLeaves);
		addBlock(world, basePos, 5, 7, 1, hauntedLeaves);
		addBlock(world, basePos, 5, 7, 2, hauntedLeaves);
		addBlock(world, basePos, 5, 7, 3, hauntedLeaves);
		addBlock(world, basePos, 5, 7, 4, hauntedLeaves);
		addBlock(world, basePos, 5, 7, 5, hauntedLeaves);
		addBlock(world, basePos, 0, 8, 2, hauntedLeaves);
		addBlock(world, basePos, 0, 8, 4, hauntedLeaves);
		addBlock(world, basePos, 1, 8, 1, hauntedLeaves);
		addBlock(world, basePos, 1, 8, 2, hauntedLeaves);
		addBlock(world, basePos, 1, 8, 3, hauntedLeaves);
		addBlock(world, basePos, 1, 8, 4, hauntedLeaves);
		addBlock(world, basePos, 1, 8, 5, hauntedLeaves);
		addBlock(world, basePos, 2, 8, 0, hauntedLeaves);
		addBlock(world, basePos, 2, 8, 1, hauntedLeaves);
		addBlock(world, basePos, 2, 8, 5, hauntedLeaves);
		addBlock(world, basePos, 2, 8, 6, hauntedLeaves);
		addBlock(world, basePos, 3, 8, 1, hauntedLeaves);
		addBlock(world, basePos, 3, 8, 5, hauntedLeaves);
		addBlock(world, basePos, 4, 8, 0, hauntedLeaves);
		addBlock(world, basePos, 4, 8, 1, hauntedLeaves);
		addBlock(world, basePos, 4, 8, 5, hauntedLeaves);
		addBlock(world, basePos, 4, 8, 6, hauntedLeaves);
		addBlock(world, basePos, 5, 8, 1, hauntedLeaves);
		addBlock(world, basePos, 5, 8, 2, hauntedLeaves);
		addBlock(world, basePos, 5, 8, 3, hauntedLeaves);
		addBlock(world, basePos, 5, 8, 4, hauntedLeaves);
		addBlock(world, basePos, 5, 8, 5, hauntedLeaves);
		addBlock(world, basePos, 6, 8, 2, hauntedLeaves);
		addBlock(world, basePos, 6, 8, 4, hauntedLeaves);
		addBlock(world, basePos, 0, 9, 2, hauntedLeaves);
		addBlock(world, basePos, 0, 9, 4, hauntedLeaves);
		addBlock(world, basePos, 2, 9, 0, hauntedLeaves);
		addBlock(world, basePos, 2, 9, 2, hauntedLeaves);
		addBlock(world, basePos, 2, 9, 3, hauntedLeaves);
		addBlock(world, basePos, 2, 9, 4, hauntedLeaves);
		addBlock(world, basePos, 2, 9, 6, hauntedLeaves);
		addBlock(world, basePos, 3, 9, 2, hauntedLeaves);
		addBlock(world, basePos, 3, 9, 4, hauntedLeaves);
		addBlock(world, basePos, 4, 9, 0, hauntedLeaves);
		addBlock(world, basePos, 4, 9, 2, hauntedLeaves);
		addBlock(world, basePos, 4, 9, 3, hauntedLeaves);
		addBlock(world, basePos, 4, 9, 4, hauntedLeaves);
		addBlock(world, basePos, 4, 9, 6, hauntedLeaves);
		addBlock(world, basePos, 6, 9, 2, hauntedLeaves);
		addBlock(world, basePos, 6, 9, 4, hauntedLeaves);
		addBlock(world, basePos, 0, 10, 2, hauntedLeaves);
		addBlock(world, basePos, 0, 10, 4, hauntedLeaves);
		addBlock(world, basePos, 2, 10, 0, hauntedLeaves);
		addBlock(world, basePos, 2, 10, 2, hauntedLeaves);
		addBlock(world, basePos, 2, 10, 3, hauntedLeaves);
		addBlock(world, basePos, 2, 10, 4, hauntedLeaves);
		addBlock(world, basePos, 2, 10, 6, hauntedLeaves);
		addBlock(world, basePos, 3, 10, 2, hauntedLeaves);
		addBlock(world, basePos, 3, 10, 4, hauntedLeaves);
		addBlock(world, basePos, 4, 10, 0, hauntedLeaves);
		addBlock(world, basePos, 4, 10, 2, hauntedLeaves);
		addBlock(world, basePos, 4, 10, 3, hauntedLeaves);
		addBlock(world, basePos, 4, 10, 6, hauntedLeaves);
		addBlock(world, basePos, 6, 10, 2, hauntedLeaves);
		addBlock(world, basePos, 6, 10, 4, hauntedLeaves);
		addBlock(world, basePos, 0, 11, 2, hauntedLeaves);
		addBlock(world, basePos, 0, 11, 4, hauntedLeaves);
		addBlock(world, basePos, 2, 11, 0, hauntedLeaves);
		addBlock(world, basePos, 2, 11, 2, hauntedLeaves);
		addBlock(world, basePos, 2, 11, 3, hauntedLeaves);
		addBlock(world, basePos, 2, 11, 4, hauntedLeaves);
		addBlock(world, basePos, 2, 11, 6, hauntedLeaves);
		addBlock(world, basePos, 3, 11, 2, hauntedLeaves);
		addBlock(world, basePos, 3, 11, 4, hauntedLeaves);
		addBlock(world, basePos, 4, 11, 0, hauntedLeaves);
		addBlock(world, basePos, 4, 11, 2, hauntedLeaves);
		addBlock(world, basePos, 4, 11, 3, hauntedLeaves);
		addBlock(world, basePos, 4, 11, 4, hauntedLeaves);
		addBlock(world, basePos, 4, 11, 6, hauntedLeaves);
		addBlock(world, basePos, 6, 11, 2, hauntedLeaves);
		addBlock(world, basePos, 6, 11, 4, hauntedLeaves);
		addBlock(world, basePos, 2, 12, 2, hauntedLeaves);
		addBlock(world, basePos, 2, 12, 3, hauntedLeaves);
		addBlock(world, basePos, 2, 12, 4, hauntedLeaves);
		addBlock(world, basePos, 3, 12, 2, hauntedLeaves);
		addBlock(world, basePos, 3, 12, 4, hauntedLeaves);
		addBlock(world, basePos, 4, 12, 2, hauntedLeaves);
		addBlock(world, basePos, 4, 12, 3, hauntedLeaves);
		addBlock(world, basePos, 4, 12, 4, hauntedLeaves);
		addBlock(world, basePos, 3, 13, 3, hauntedLeaves);
		addBlock(world, basePos, 3, 14, 3, hauntedLeaves);
		addBlock(world, basePos, 3, 15, 3, hauntedLeaves);

		if (rand.nextInt(3) == 0) {
			addBlock(world, basePos, 1, 6, 5, hauntedLeaves);
		}
		else {
			addBlock(world, basePos, 1, 6, 5, hauntedEyesLeaves);
		}

		if (rand.nextInt(3) == 0) {
			addBlock(world, basePos, 2, 5, 2, hauntedLeaves);
		}
		else {
			addBlock(world, basePos, 2, 5, 2, hauntedEyesLeaves);
		}

		if (rand.nextInt(3) == 0) {
			addBlock(world, basePos, 3, 2, 3, hauntedLog);
		}
		else if (rand.nextInt(5) == 0) {
			addBlock(world, basePos, 3, 2, 3, hauntedFlashingFaceLog);
		}
		else {
			addBlock(world, basePos, 3, 2, 3, hauntedEyesLog);
		}

		if (rand.nextInt(3) == 0) {
			addBlock(world, basePos, 3, 5, 1, hauntedLeaves);
		}
		else {
			addBlock(world, basePos, 3, 5, 1, hauntedEyesLeaves);
		}

		if (rand.nextInt(3) == 0) {
			addBlock(world, basePos, 3, 7, 3, hauntedLog);
		}
		else {
			addBlock(world, basePos, 3, 7, 3, hauntedEyesLog);
		}

		if (rand.nextInt(3) == 0) {
			addBlock(world, basePos, 4, 10, 4, hauntedLeaves);
		}
		else {
			addBlock(world, basePos, 4, 10, 4, hauntedEyesLeaves);
		}

		if (rand.nextInt(3) == 0) {
			addBlock(world, basePos, 5, 5, 4, hauntedLeaves);
		}
		else {
			addBlock(world, basePos, 5, 5, 4, hauntedEyesLeaves);
		}
	}
}
