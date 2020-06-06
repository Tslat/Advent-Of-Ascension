package net.tslat.aoa3.structure.abyss;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class ShadeBush3 extends AoAStructure { //StructureSize: 1x3x4
	private static final IBlockState shadowLeaves = BlockRegister.SHADOW_LEAVES.getDefaultState();

	public ShadeBush3() {
		super("ShadeBush3");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 0, shadowLeaves);
		addBlock(world, basePos, 0, 0, 3, shadowLeaves);
		addBlock(world, basePos, 0, 1, 0, shadowLeaves);
		addBlock(world, basePos, 0, 1, 3, shadowLeaves);
		addBlock(world, basePos, 0, 2, 0, shadowLeaves);
		addBlock(world, basePos, 0, 2, 1, shadowLeaves);
		addBlock(world, basePos, 0, 2, 2, shadowLeaves);
		addBlock(world, basePos, 0, 2, 3, shadowLeaves);
	}
}
