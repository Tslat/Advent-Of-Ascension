package net.tslat.aoa3.structure.iromine;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class SilvroFloater2 extends AoAStructure { //StructureSize: 5x5x1
	private static final IBlockState irodustLeaves = BlockRegister.leavesIrodust.getDefaultState();
	private static final IBlockState silvroLeaves = BlockRegister.leavesSilvro.getDefaultState();
	private static final IBlockState silvroBox = BlockRegister.silvroBox.getDefaultState();

	public SilvroFloater2() {
		super("SilvroFloater2");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 0, irodustLeaves);
		addBlock(world, basePos, 1, 0, 0, irodustLeaves);
		addBlock(world, basePos, 2, 0, 0, irodustLeaves);
		addBlock(world, basePos, 3, 0, 0, irodustLeaves);
		addBlock(world, basePos, 4, 0, 0, irodustLeaves);
		addBlock(world, basePos, 0, 1, 0, irodustLeaves);
		addBlock(world, basePos, 2, 1, 0, silvroBox);
		addBlock(world, basePos, 4, 1, 0, irodustLeaves);
		addBlock(world, basePos, 0, 2, 0, irodustLeaves);
		addBlock(world, basePos, 1, 2, 0, silvroBox);
		addBlock(world, basePos, 2, 2, 0, silvroLeaves);
		addBlock(world, basePos, 3, 2, 0, silvroBox);
		addBlock(world, basePos, 4, 2, 0, irodustLeaves);
		addBlock(world, basePos, 0, 3, 0, irodustLeaves);
		addBlock(world, basePos, 2, 3, 0, silvroBox);
		addBlock(world, basePos, 4, 3, 0, irodustLeaves);
		addBlock(world, basePos, 0, 4, 0, irodustLeaves);
		addBlock(world, basePos, 1, 4, 0, irodustLeaves);
		addBlock(world, basePos, 2, 4, 0, irodustLeaves);
		addBlock(world, basePos, 3, 4, 0, irodustLeaves);
		addBlock(world, basePos, 4, 4, 0, irodustLeaves);
	}
}
