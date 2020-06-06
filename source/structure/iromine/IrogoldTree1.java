package net.tslat.aoa3.structure.iromine;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class IrogoldTree1 extends AoAStructure { //StructureSize: 8x9x8
	private static final IBlockState irogoldLeaves = BlockRegister.IROGOLD_LEAVES.getDefaultState();
	private static final IBlockState log = BlockRegister.IRO_LOG.getDefaultState();

	public IrogoldTree1() {
		super("IrogoldTree1");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 3, 0, 3, log);
		addBlock(world, basePos, 3, 0, 4, log);
		addBlock(world, basePos, 4, 0, 3, log);
		addBlock(world, basePos, 4, 0, 4, log);
		addBlock(world, basePos, 3, 1, 3, log);
		addBlock(world, basePos, 3, 1, 4, log);
		addBlock(world, basePos, 4, 1, 3, log);
		addBlock(world, basePos, 4, 1, 4, log);
		addBlock(world, basePos, 3, 2, 3, log);
		addBlock(world, basePos, 3, 2, 4, log);
		addBlock(world, basePos, 4, 2, 3, log);
		addBlock(world, basePos, 4, 2, 4, log);
		addBlock(world, basePos, 3, 3, 3, log);
		addBlock(world, basePos, 3, 3, 4, log);
		addBlock(world, basePos, 4, 3, 3, log);
		addBlock(world, basePos, 4, 3, 4, log);
		addBlock(world, basePos, 3, 4, 3, log);
		addBlock(world, basePos, 3, 4, 4, log);
		addBlock(world, basePos, 4, 4, 3, log);
		addBlock(world, basePos, 4, 4, 4, log);
		addBlock(world, basePos, 3, 5, 3, log);
		addBlock(world, basePos, 3, 5, 4, log);
		addBlock(world, basePos, 4, 5, 3, log);
		addBlock(world, basePos, 4, 5, 4, log);
		addBlock(world, basePos, 1, 6, 3, irogoldLeaves);
		addBlock(world, basePos, 1, 6, 4, irogoldLeaves);
		addBlock(world, basePos, 3, 6, 1, irogoldLeaves);
		addBlock(world, basePos, 3, 6, 3, log);
		addBlock(world, basePos, 3, 6, 4, log);
		addBlock(world, basePos, 3, 6, 6, irogoldLeaves);
		addBlock(world, basePos, 4, 6, 1, irogoldLeaves);
		addBlock(world, basePos, 4, 6, 3, log);
		addBlock(world, basePos, 4, 6, 4, log);
		addBlock(world, basePos, 4, 6, 6, irogoldLeaves);
		addBlock(world, basePos, 6, 6, 3, irogoldLeaves);
		addBlock(world, basePos, 6, 6, 4, irogoldLeaves);
		addBlock(world, basePos, 0, 7, 3, irogoldLeaves);
		addBlock(world, basePos, 0, 7, 4, irogoldLeaves);
		addBlock(world, basePos, 1, 7, 2, irogoldLeaves);
		addBlock(world, basePos, 1, 7, 3, log);
		addBlock(world, basePos, 1, 7, 4, log);
		addBlock(world, basePos, 1, 7, 5, irogoldLeaves);
		addBlock(world, basePos, 2, 7, 1, irogoldLeaves);
		addBlock(world, basePos, 2, 7, 3, log);
		addBlock(world, basePos, 2, 7, 4, log);
		addBlock(world, basePos, 2, 7, 6, irogoldLeaves);
		addBlock(world, basePos, 3, 7, 0, irogoldLeaves);
		addBlock(world, basePos, 3, 7, 1, log);
		addBlock(world, basePos, 3, 7, 2, log);
		addBlock(world, basePos, 3, 7, 3, log);
		addBlock(world, basePos, 3, 7, 4, log);
		addBlock(world, basePos, 3, 7, 5, log);
		addBlock(world, basePos, 3, 7, 6, log);
		addBlock(world, basePos, 3, 7, 7, irogoldLeaves);
		addBlock(world, basePos, 4, 7, 0, irogoldLeaves);
		addBlock(world, basePos, 4, 7, 1, log);
		addBlock(world, basePos, 4, 7, 2, log);
		addBlock(world, basePos, 4, 7, 3, log);
		addBlock(world, basePos, 4, 7, 4, log);
		addBlock(world, basePos, 4, 7, 5, log);
		addBlock(world, basePos, 4, 7, 6, log);
		addBlock(world, basePos, 4, 7, 7, irogoldLeaves);
		addBlock(world, basePos, 5, 7, 1, irogoldLeaves);
		addBlock(world, basePos, 5, 7, 3, log);
		addBlock(world, basePos, 5, 7, 4, log);
		addBlock(world, basePos, 5, 7, 6, irogoldLeaves);
		addBlock(world, basePos, 6, 7, 2, irogoldLeaves);
		addBlock(world, basePos, 6, 7, 3, log);
		addBlock(world, basePos, 6, 7, 4, log);
		addBlock(world, basePos, 6, 7, 5, irogoldLeaves);
		addBlock(world, basePos, 7, 7, 3, irogoldLeaves);
		addBlock(world, basePos, 7, 7, 4, irogoldLeaves);
		addBlock(world, basePos, 1, 8, 3, irogoldLeaves);
		addBlock(world, basePos, 1, 8, 4, irogoldLeaves);
		addBlock(world, basePos, 3, 8, 1, irogoldLeaves);
		addBlock(world, basePos, 3, 8, 6, irogoldLeaves);
		addBlock(world, basePos, 4, 8, 1, irogoldLeaves);
		addBlock(world, basePos, 4, 8, 6, irogoldLeaves);
		addBlock(world, basePos, 6, 8, 3, irogoldLeaves);
		addBlock(world, basePos, 6, 8, 4, irogoldLeaves);
	}
}
