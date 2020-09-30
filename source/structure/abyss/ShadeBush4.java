package net.tslat.aoa3.structure.abyss;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class ShadeBush4 extends AoAStructure { //StructureSize: 4x3x1
	private static final IBlockState shadowLeaves = BlockRegister.SHADOW_LEAVES.getDefaultState();

	public ShadeBush4() {
		super("ShadeBush4");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 0, shadowLeaves);
		addBlock(world, basePos, 3, 0, 0, shadowLeaves);
		addBlock(world, basePos, 0, 1, 0, shadowLeaves);
		addBlock(world, basePos, 3, 1, 0, shadowLeaves);
		addBlock(world, basePos, 0, 2, 0, shadowLeaves);
		addBlock(world, basePos, 1, 2, 0, shadowLeaves);
		addBlock(world, basePos, 2, 2, 0, shadowLeaves);
		addBlock(world, basePos, 3, 2, 0, shadowLeaves);
	}
}
