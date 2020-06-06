package net.tslat.aoa3.structure.abyss;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class ShadeBush2 extends AoAStructure { //StructureSize: 3x2x1
	private static final IBlockState shadowLeaves = BlockRegister.SHADOW_LEAVES.getDefaultState();

	public ShadeBush2() {
		super("ShadeBush2");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 0, shadowLeaves);
		addBlock(world, basePos, 2, 0, 0, shadowLeaves);
		addBlock(world, basePos, 0, 1, 0, shadowLeaves);
		addBlock(world, basePos, 1, 1, 0, shadowLeaves);
		addBlock(world, basePos, 2, 1, 0, shadowLeaves);
	}
}
