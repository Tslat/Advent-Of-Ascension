package net.tslat.aoa3.structure.iromine;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class SilvroTree1 extends AoAStructure { //StructureSize: 5x12x5
	private static final IBlockState log = BlockRegister.logIro.getDefaultState();
	private static final IBlockState silvroLeaves = BlockRegister.leavesSilvro.getDefaultState();

	public SilvroTree1() {
		super("SilvroTree1");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 2, log);
		addBlock(world, basePos, 1, 0, 1, log);
		addBlock(world, basePos, 1, 0, 2, log);
		addBlock(world, basePos, 1, 0, 3, log);
		addBlock(world, basePos, 2, 0, 0, log);
		addBlock(world, basePos, 2, 0, 1, log);
		addBlock(world, basePos, 2, 0, 2, log);
		addBlock(world, basePos, 2, 0, 3, log);
		addBlock(world, basePos, 2, 0, 4, log);
		addBlock(world, basePos, 3, 0, 1, log);
		addBlock(world, basePos, 3, 0, 2, log);
		addBlock(world, basePos, 3, 0, 3, log);
		addBlock(world, basePos, 4, 0, 2, log);
		addBlock(world, basePos, 0, 1, 2, log);
		addBlock(world, basePos, 1, 1, 1, log);
		addBlock(world, basePos, 1, 1, 2, log);
		addBlock(world, basePos, 1, 1, 3, log);
		addBlock(world, basePos, 2, 1, 0, log);
		addBlock(world, basePos, 2, 1, 1, log);
		addBlock(world, basePos, 2, 1, 2, log);
		addBlock(world, basePos, 2, 1, 3, log);
		addBlock(world, basePos, 2, 1, 4, log);
		addBlock(world, basePos, 3, 1, 1, log);
		addBlock(world, basePos, 3, 1, 2, log);
		addBlock(world, basePos, 3, 1, 3, log);
		addBlock(world, basePos, 4, 1, 2, log);
		addBlock(world, basePos, 1, 2, 1, log);
		addBlock(world, basePos, 1, 2, 2, log);
		addBlock(world, basePos, 1, 2, 3, log);
		addBlock(world, basePos, 2, 2, 1, log);
		addBlock(world, basePos, 2, 2, 2, log);
		addBlock(world, basePos, 2, 2, 3, log);
		addBlock(world, basePos, 3, 2, 1, log);
		addBlock(world, basePos, 3, 2, 2, log);
		addBlock(world, basePos, 3, 2, 3, log);
		addBlock(world, basePos, 1, 3, 1, log);
		addBlock(world, basePos, 1, 3, 2, log);
		addBlock(world, basePos, 1, 3, 3, log);
		addBlock(world, basePos, 2, 3, 1, log);
		addBlock(world, basePos, 2, 3, 2, log);
		addBlock(world, basePos, 2, 3, 3, log);
		addBlock(world, basePos, 3, 3, 1, log);
		addBlock(world, basePos, 3, 3, 2, log);
		addBlock(world, basePos, 3, 3, 3, log);
		addBlock(world, basePos, 1, 4, 2, log);
		addBlock(world, basePos, 2, 4, 1, log);
		addBlock(world, basePos, 2, 4, 2, log);
		addBlock(world, basePos, 2, 4, 3, log);
		addBlock(world, basePos, 3, 4, 2, log);
		addBlock(world, basePos, 1, 5, 2, log);
		addBlock(world, basePos, 2, 5, 1, log);
		addBlock(world, basePos, 2, 5, 2, log);
		addBlock(world, basePos, 2, 5, 3, log);
		addBlock(world, basePos, 3, 5, 2, log);
		addBlock(world, basePos, 2, 6, 2, log);
		addBlock(world, basePos, 2, 7, 2, log);
		addBlock(world, basePos, 2, 8, 2, log);
		addBlock(world, basePos, 1, 9, 1, silvroLeaves);
		addBlock(world, basePos, 1, 9, 2, silvroLeaves);
		addBlock(world, basePos, 1, 9, 3, silvroLeaves);
		addBlock(world, basePos, 2, 9, 1, silvroLeaves);
		addBlock(world, basePos, 2, 9, 2, silvroLeaves);
		addBlock(world, basePos, 2, 9, 3, silvroLeaves);
		addBlock(world, basePos, 3, 9, 1, silvroLeaves);
		addBlock(world, basePos, 3, 9, 2, silvroLeaves);
		addBlock(world, basePos, 3, 9, 3, silvroLeaves);
		addBlock(world, basePos, 1, 10, 1, silvroLeaves);
		addBlock(world, basePos, 1, 10, 2, silvroLeaves);
		addBlock(world, basePos, 1, 10, 3, silvroLeaves);
		addBlock(world, basePos, 2, 10, 1, silvroLeaves);
		addBlock(world, basePos, 2, 10, 2, silvroLeaves);
		addBlock(world, basePos, 2, 10, 3, silvroLeaves);
		addBlock(world, basePos, 3, 10, 1, silvroLeaves);
		addBlock(world, basePos, 3, 10, 2, silvroLeaves);
		addBlock(world, basePos, 3, 10, 3, silvroLeaves);
		addBlock(world, basePos, 1, 11, 1, silvroLeaves);
		addBlock(world, basePos, 1, 11, 2, silvroLeaves);
		addBlock(world, basePos, 1, 11, 3, silvroLeaves);
		addBlock(world, basePos, 2, 11, 1, silvroLeaves);
		addBlock(world, basePos, 2, 11, 2, silvroLeaves);
		addBlock(world, basePos, 2, 11, 3, silvroLeaves);
		addBlock(world, basePos, 3, 11, 1, silvroLeaves);
		addBlock(world, basePos, 3, 11, 2, silvroLeaves);
		addBlock(world, basePos, 3, 11, 3, silvroLeaves);
	}
}
