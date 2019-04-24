package net.tslat.aoa3.structure.deeplands;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class Deepshine1 extends AoAStructure { //StructureSize: 4x5x4
	private static final IBlockState stone = BlockRegister.stoneDeeplands.getDefaultState();
	private static final IBlockState deepshine = BlockRegister.deepshine.getDefaultState();

	public Deepshine1() {
		super("Deepshine1");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 0, stone);
		addBlock(world, basePos, 0, 0, 1, stone);
		addBlock(world, basePos, 0, 0, 2, stone);
		addBlock(world, basePos, 0, 0, 3, stone);
		addBlock(world, basePos, 1, 0, 0, stone);
		addBlock(world, basePos, 1, 0, 1, stone);
		addBlock(world, basePos, 1, 0, 2, stone);
		addBlock(world, basePos, 1, 0, 3, stone);
		addBlock(world, basePos, 2, 0, 0, stone);
		addBlock(world, basePos, 2, 0, 1, stone);
		addBlock(world, basePos, 2, 0, 2, stone);
		addBlock(world, basePos, 2, 0, 3, stone);
		addBlock(world, basePos, 3, 0, 0, stone);
		addBlock(world, basePos, 3, 0, 1, stone);
		addBlock(world, basePos, 3, 0, 2, stone);
		addBlock(world, basePos, 3, 0, 3, stone);
		addBlock(world, basePos, 1, 1, 1, stone);
		addBlock(world, basePos, 1, 1, 2, stone);
		addBlock(world, basePos, 2, 1, 1, stone);
		addBlock(world, basePos, 2, 1, 2, stone);
		addBlock(world, basePos, 0, 2, 0, deepshine);
		addBlock(world, basePos, 0, 2, 1, deepshine);
		addBlock(world, basePos, 0, 2, 2, deepshine);
		addBlock(world, basePos, 0, 2, 3, deepshine);
		addBlock(world, basePos, 1, 2, 0, deepshine);
		addBlock(world, basePos, 1, 2, 1, stone);
		addBlock(world, basePos, 1, 2, 2, stone);
		addBlock(world, basePos, 1, 2, 3, deepshine);
		addBlock(world, basePos, 2, 2, 0, deepshine);
		addBlock(world, basePos, 2, 2, 1, stone);
		addBlock(world, basePos, 2, 2, 2, stone);
		addBlock(world, basePos, 2, 2, 3, deepshine);
		addBlock(world, basePos, 3, 2, 0, deepshine);
		addBlock(world, basePos, 3, 2, 1, deepshine);
		addBlock(world, basePos, 3, 2, 2, deepshine);
		addBlock(world, basePos, 3, 2, 3, deepshine);
		addBlock(world, basePos, 1, 3, 1, stone);
		addBlock(world, basePos, 1, 3, 2, stone);
		addBlock(world, basePos, 2, 3, 1, stone);
		addBlock(world, basePos, 2, 3, 2, stone);
		addBlock(world, basePos, 1, 4, 1, deepshine);
		addBlock(world, basePos, 1, 4, 2, deepshine);
		addBlock(world, basePos, 2, 4, 1, deepshine);
		addBlock(world, basePos, 2, 4, 2, deepshine);
	}
}
