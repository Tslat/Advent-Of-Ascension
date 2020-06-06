package net.tslat.aoa3.structure.shyrelands;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class BrightShyreTree2 extends AoAStructure { //StructureSize: 3x10x3
	private static final IBlockState brightShyreLeaves = BlockRegister.BRIGHT_SHYRE_LEAVES.getDefaultState();
	private static final IBlockState shyreLog = BlockRegister.SHYRE_LOG.getDefaultState();

	public BrightShyreTree2() {
		super("BrightShyreTree2");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 1, 0, 1, shyreLog);
		addBlock(world, basePos, 1, 1, 1, shyreLog);
		addBlock(world, basePos, 1, 2, 1, shyreLog);
		addBlock(world, basePos, 1, 3, 1, shyreLog);
		addBlock(world, basePos, 1, 4, 1, shyreLog);
		addBlock(world, basePos, 1, 5, 1, shyreLog);
		addBlock(world, basePos, 1, 6, 1, shyreLog);
		addBlock(world, basePos, 0, 7, 0, brightShyreLeaves);
		addBlock(world, basePos, 0, 7, 1, brightShyreLeaves);
		addBlock(world, basePos, 0, 7, 2, brightShyreLeaves);
		addBlock(world, basePos, 1, 7, 0, brightShyreLeaves);
		addBlock(world, basePos, 1, 7, 1, brightShyreLeaves);
		addBlock(world, basePos, 1, 7, 2, brightShyreLeaves);
		addBlock(world, basePos, 2, 7, 0, brightShyreLeaves);
		addBlock(world, basePos, 2, 7, 1, brightShyreLeaves);
		addBlock(world, basePos, 2, 7, 2, brightShyreLeaves);
		addBlock(world, basePos, 0, 8, 0, brightShyreLeaves);
		addBlock(world, basePos, 0, 8, 2, brightShyreLeaves);
		addBlock(world, basePos, 2, 8, 0, brightShyreLeaves);
		addBlock(world, basePos, 2, 8, 2, brightShyreLeaves);
		addBlock(world, basePos, 0, 9, 0, brightShyreLeaves);
		addBlock(world, basePos, 0, 9, 1, brightShyreLeaves);
		addBlock(world, basePos, 0, 9, 2, brightShyreLeaves);
		addBlock(world, basePos, 1, 9, 0, brightShyreLeaves);
		addBlock(world, basePos, 1, 9, 1, brightShyreLeaves);
		addBlock(world, basePos, 1, 9, 2, brightShyreLeaves);
		addBlock(world, basePos, 2, 9, 0, brightShyreLeaves);
		addBlock(world, basePos, 2, 9, 1, brightShyreLeaves);
		addBlock(world, basePos, 2, 9, 2, brightShyreLeaves);
	}
}
