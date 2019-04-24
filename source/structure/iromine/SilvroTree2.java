package net.tslat.aoa3.structure.iromine;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class SilvroTree2 extends AoAStructure { //StructureSize: 3x11x3
	private static final IBlockState log = BlockRegister.logIro.getDefaultState();
	private static final IBlockState silvroLeaves = BlockRegister.leavesSilvro.getDefaultState();

	public SilvroTree2() {
		super("SilvroTree2");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 0, log);
		addBlock(world, basePos, 0, 0, 1, log);
		addBlock(world, basePos, 0, 0, 2, log);
		addBlock(world, basePos, 1, 0, 0, log);
		addBlock(world, basePos, 1, 0, 1, log);
		addBlock(world, basePos, 1, 0, 2, log);
		addBlock(world, basePos, 2, 0, 0, log);
		addBlock(world, basePos, 2, 0, 1, log);
		addBlock(world, basePos, 2, 0, 2, log);
		addBlock(world, basePos, 0, 1, 0, log);
		addBlock(world, basePos, 0, 1, 1, log);
		addBlock(world, basePos, 0, 1, 2, log);
		addBlock(world, basePos, 1, 1, 0, log);
		addBlock(world, basePos, 1, 1, 1, log);
		addBlock(world, basePos, 1, 1, 2, log);
		addBlock(world, basePos, 2, 1, 0, log);
		addBlock(world, basePos, 2, 1, 1, log);
		addBlock(world, basePos, 2, 1, 2, log);
		addBlock(world, basePos, 0, 2, 1, log);
		addBlock(world, basePos, 1, 2, 0, log);
		addBlock(world, basePos, 1, 2, 1, log);
		addBlock(world, basePos, 1, 2, 2, log);
		addBlock(world, basePos, 2, 2, 1, log);
		addBlock(world, basePos, 0, 3, 1, log);
		addBlock(world, basePos, 1, 3, 0, log);
		addBlock(world, basePos, 1, 3, 1, log);
		addBlock(world, basePos, 1, 3, 2, log);
		addBlock(world, basePos, 2, 3, 1, log);
		addBlock(world, basePos, 0, 4, 1, log);
		addBlock(world, basePos, 1, 4, 0, log);
		addBlock(world, basePos, 1, 4, 1, log);
		addBlock(world, basePos, 1, 4, 2, log);
		addBlock(world, basePos, 2, 4, 1, log);
		addBlock(world, basePos, 1, 5, 1, log);
		addBlock(world, basePos, 1, 6, 1, log);
		addBlock(world, basePos, 1, 7, 1, log);
		addBlock(world, basePos, 0, 8, 0, silvroLeaves);
		addBlock(world, basePos, 0, 8, 1, silvroLeaves);
		addBlock(world, basePos, 0, 8, 2, silvroLeaves);
		addBlock(world, basePos, 1, 8, 0, silvroLeaves);
		addBlock(world, basePos, 1, 8, 1, silvroLeaves);
		addBlock(world, basePos, 1, 8, 2, silvroLeaves);
		addBlock(world, basePos, 2, 8, 0, silvroLeaves);
		addBlock(world, basePos, 2, 8, 1, silvroLeaves);
		addBlock(world, basePos, 2, 8, 2, silvroLeaves);
		addBlock(world, basePos, 0, 9, 0, silvroLeaves);
		addBlock(world, basePos, 0, 9, 1, silvroLeaves);
		addBlock(world, basePos, 0, 9, 2, silvroLeaves);
		addBlock(world, basePos, 1, 9, 0, silvroLeaves);
		addBlock(world, basePos, 1, 9, 1, silvroLeaves);
		addBlock(world, basePos, 1, 9, 2, silvroLeaves);
		addBlock(world, basePos, 2, 9, 0, silvroLeaves);
		addBlock(world, basePos, 2, 9, 1, silvroLeaves);
		addBlock(world, basePos, 2, 9, 2, silvroLeaves);
		addBlock(world, basePos, 0, 10, 0, silvroLeaves);
		addBlock(world, basePos, 0, 10, 1, silvroLeaves);
		addBlock(world, basePos, 0, 10, 2, silvroLeaves);
		addBlock(world, basePos, 1, 10, 0, silvroLeaves);
		addBlock(world, basePos, 1, 10, 1, silvroLeaves);
		addBlock(world, basePos, 1, 10, 2, silvroLeaves);
		addBlock(world, basePos, 2, 10, 0, silvroLeaves);
		addBlock(world, basePos, 2, 10, 1, silvroLeaves);
		addBlock(world, basePos, 2, 10, 2, silvroLeaves);
	}
}
