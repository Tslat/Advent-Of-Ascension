package net.tslat.aoa3.structure.iromine;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class SilvroFloater4 extends AoAStructure { //StructureSize: 3x8x3
	private static final IBlockState irodustLeaves = BlockRegister.leavesIrodust.getDefaultState();
	private static final IBlockState silvroLeaves = BlockRegister.leavesSilvro.getDefaultState();

	public SilvroFloater4() {
		super("SilvroFloater4");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 0, silvroLeaves); 
		addBlock(world, basePos, 0, 0, 1, silvroLeaves); 
		addBlock(world, basePos, 0, 0, 2, silvroLeaves);
		addBlock(world, basePos, 1, 0, 0, silvroLeaves); 
		addBlock(world, basePos, 1, 0, 1, silvroLeaves); 
		addBlock(world, basePos, 1, 0, 2, silvroLeaves);
		addBlock(world, basePos, 2, 0, 0, silvroLeaves);
		addBlock(world, basePos, 2, 0, 1, silvroLeaves);
		addBlock(world, basePos, 2, 0, 2, silvroLeaves);
		addBlock(world, basePos, 0, 1, 0, silvroLeaves);
		addBlock(world, basePos, 0, 1, 1, silvroLeaves); 
		addBlock(world, basePos, 0, 1, 2, silvroLeaves);
		addBlock(world, basePos, 1, 1, 0, silvroLeaves); 
		addBlock(world, basePos, 1, 1, 1, silvroLeaves); 
		addBlock(world, basePos, 1, 1, 2, silvroLeaves);
		addBlock(world, basePos, 2, 1, 0, silvroLeaves);
		addBlock(world, basePos, 2, 1, 1, silvroLeaves);
		addBlock(world, basePos, 2, 1, 2, silvroLeaves);
		addBlock(world, basePos, 1, 2, 1, irodustLeaves); 
		addBlock(world, basePos, 1, 3, 1, irodustLeaves);
		addBlock(world, basePos, 1, 4, 1, irodustLeaves);
		addBlock(world, basePos, 1, 5, 1, irodustLeaves);
		addBlock(world, basePos, 0, 6, 0, silvroLeaves);
		addBlock(world, basePos, 0, 6, 1, silvroLeaves);
		addBlock(world, basePos, 0, 6, 2, silvroLeaves);
		addBlock(world, basePos, 1, 6, 0, silvroLeaves);
		addBlock(world, basePos, 1, 6, 1, silvroLeaves);
		addBlock(world, basePos, 1, 6, 2, silvroLeaves);
		addBlock(world, basePos, 2, 6, 0, silvroLeaves);
		addBlock(world, basePos, 2, 6, 1, silvroLeaves);
		addBlock(world, basePos, 2, 6, 2, silvroLeaves);
		addBlock(world, basePos, 0, 7, 0, silvroLeaves);
		addBlock(world, basePos, 0, 7, 1, silvroLeaves);
		addBlock(world, basePos, 0, 7, 2, silvroLeaves);
		addBlock(world, basePos, 1, 7, 0, silvroLeaves);
		addBlock(world, basePos, 1, 7, 1, silvroLeaves);
		addBlock(world, basePos, 1, 7, 2, silvroLeaves);
		addBlock(world, basePos, 2, 7, 0, silvroLeaves);
		addBlock(world, basePos, 2, 7, 1, silvroLeaves);
		addBlock(world, basePos, 2, 7, 2, silvroLeaves);
	}
}
