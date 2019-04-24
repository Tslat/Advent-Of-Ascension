package net.tslat.aoa3.structure.abyss;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class ShadeBush1 extends AoAStructure { //StructureSize: 1x2x3
	private static final IBlockState shadowLeaves = BlockRegister.leavesShadow.getDefaultState();

	public ShadeBush1() {
		super("ShadeBush1");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 0, shadowLeaves);
		addBlock(world, basePos, 0, 0, 2, shadowLeaves);
		addBlock(world, basePos, 0, 1, 0, shadowLeaves);
		addBlock(world, basePos, 0, 1, 1, shadowLeaves);
		addBlock(world, basePos, 0, 1, 2, shadowLeaves);
	}
}
