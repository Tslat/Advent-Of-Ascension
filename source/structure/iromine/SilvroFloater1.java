package net.tslat.aoa3.structure.iromine;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class SilvroFloater1 extends AoAStructure { //StructureSize: 5x1x5
	private static final IBlockState irodustLeaves = BlockRegister.leavesIrodust.getDefaultState();
	private static final IBlockState silvroLeaves = BlockRegister.leavesSilvro.getDefaultState();
	private static final IBlockState silvroBox = BlockRegister.silvroBox.getDefaultState();

	public SilvroFloater1() {
		super("SilvroFloater1");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 0, irodustLeaves);
		addBlock(world, basePos, 0, 0, 1, irodustLeaves);
		addBlock(world, basePos, 0, 0, 2, irodustLeaves);
		addBlock(world, basePos, 0, 0, 3, irodustLeaves);
		addBlock(world, basePos, 0, 0, 4, irodustLeaves);
		addBlock(world, basePos, 1, 0, 0, irodustLeaves);
		addBlock(world, basePos, 1, 0, 2, silvroBox);
		addBlock(world, basePos, 1, 0, 4, irodustLeaves);
		addBlock(world, basePos, 2, 0, 0, irodustLeaves);
		addBlock(world, basePos, 2, 0, 1, silvroBox);
		addBlock(world, basePos, 2, 0, 2, silvroLeaves);
		addBlock(world, basePos, 2, 0, 3, silvroBox);
		addBlock(world, basePos, 2, 0, 4, irodustLeaves);
		addBlock(world, basePos, 3, 0, 0, irodustLeaves);
		addBlock(world, basePos, 3, 0, 2, silvroBox);
		addBlock(world, basePos, 3, 0, 4, irodustLeaves);
		addBlock(world, basePos, 4, 0, 0, irodustLeaves);
		addBlock(world, basePos, 4, 0, 1, irodustLeaves);
		addBlock(world, basePos, 4, 0, 2, irodustLeaves);
		addBlock(world, basePos, 4, 0, 3, irodustLeaves);
		addBlock(world, basePos, 4, 0, 4, irodustLeaves);
	}
}
