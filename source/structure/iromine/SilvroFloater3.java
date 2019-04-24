package net.tslat.aoa3.structure.iromine;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class SilvroFloater3 extends AoAStructure { //StructureSize: 1x5x5
	private static final IBlockState irodustLeaves = BlockRegister.leavesIrodust.getDefaultState();
	private static final IBlockState silvroLeaves = BlockRegister.leavesSilvro.getDefaultState();
	private static final IBlockState silvroBox = BlockRegister.silvroBox.getDefaultState();

	public SilvroFloater3() {
		super("SilvroFloater3");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 0, irodustLeaves);
		addBlock(world, basePos, 0, 0, 1, irodustLeaves);
		addBlock(world, basePos, 0, 0, 2, irodustLeaves);
		addBlock(world, basePos, 0, 0, 3, irodustLeaves);
		addBlock(world, basePos, 0, 0, 4, irodustLeaves);
		addBlock(world, basePos, 0, 1, 0, irodustLeaves);
		addBlock(world, basePos, 0, 1, 2, silvroBox);
		addBlock(world, basePos, 0, 1, 4, irodustLeaves);
		addBlock(world, basePos, 0, 2, 0, irodustLeaves);
		addBlock(world, basePos, 0, 2, 1, silvroBox);
		addBlock(world, basePos, 0, 2, 2, silvroLeaves);
		addBlock(world, basePos, 0, 2, 3, silvroBox);
		addBlock(world, basePos, 0, 2, 4, irodustLeaves);
		addBlock(world, basePos, 0, 3, 0, irodustLeaves);
		addBlock(world, basePos, 0, 3, 2, silvroBox);
		addBlock(world, basePos, 0, 3, 4, irodustLeaves);
		addBlock(world, basePos, 0, 4, 0, irodustLeaves);
		addBlock(world, basePos, 0, 4, 1, irodustLeaves);
		addBlock(world, basePos, 0, 4, 2, irodustLeaves);
		addBlock(world, basePos, 0, 4, 3, irodustLeaves);
		addBlock(world, basePos, 0, 4, 4, irodustLeaves);
	}
}
