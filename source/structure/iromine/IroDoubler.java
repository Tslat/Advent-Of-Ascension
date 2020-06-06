package net.tslat.aoa3.structure.iromine;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class IroDoubler extends AoAStructure { //StructureSize: 5x8x5
	private static final IBlockState leaves = BlockRegister.IROGOLD_LEAVES.getDefaultState();
	private static final IBlockState log = BlockRegister.IRO_LOG.getDefaultState();

	public IroDoubler() {
		super("IroDoubler");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 1, 0, 1, leaves); 
		addBlock(world, basePos, 1, 0, 2, leaves);
		addBlock(world, basePos, 1, 0, 3, leaves);
		addBlock(world, basePos, 2, 0, 1, leaves);
		addBlock(world, basePos, 2, 0, 2, leaves);
		addBlock(world, basePos, 2, 0, 3, leaves);
		addBlock(world, basePos, 3, 0, 1, leaves);
		addBlock(world, basePos, 3, 0, 2, leaves);
		addBlock(world, basePos, 3, 0, 3, leaves);
		addBlock(world, basePos, 0, 1, 0, leaves);
		addBlock(world, basePos, 0, 1, 1, leaves); 
		addBlock(world, basePos, 0, 1, 2, leaves);
		addBlock(world, basePos, 0, 1, 3, leaves);
		addBlock(world, basePos, 0, 1, 4, leaves);
		addBlock(world, basePos, 1, 1, 0, leaves); 
		addBlock(world, basePos, 1, 1, 4, leaves);
		addBlock(world, basePos, 2, 1, 0, leaves);
		addBlock(world, basePos, 2, 1, 2, log);
		addBlock(world, basePos, 2, 1, 4, leaves);
		addBlock(world, basePos, 3, 1, 0, leaves);
		addBlock(world, basePos, 3, 1, 4, leaves);
		addBlock(world, basePos, 4, 1, 0, leaves);
		addBlock(world, basePos, 4, 1, 1, leaves);
		addBlock(world, basePos, 4, 1, 2, leaves);
		addBlock(world, basePos, 4, 1, 3, leaves);
		addBlock(world, basePos, 4, 1, 4, leaves);
		addBlock(world, basePos, 2, 2, 2, log);
		addBlock(world, basePos, 2, 3, 2, log);
		addBlock(world, basePos, 2, 4, 2, log);
		addBlock(world, basePos, 2, 5, 2, log);
		addBlock(world, basePos, 0, 6, 0, leaves);
		addBlock(world, basePos, 0, 6, 1, leaves);
		addBlock(world, basePos, 0, 6, 2, leaves);
		addBlock(world, basePos, 0, 6, 3, leaves);
		addBlock(world, basePos, 0, 6, 4, leaves);
		addBlock(world, basePos, 1, 6, 0, leaves);
		addBlock(world, basePos, 1, 6, 4, leaves);
		addBlock(world, basePos, 2, 6, 0, leaves);
		addBlock(world, basePos, 2, 6, 2, log);
		addBlock(world, basePos, 2, 6, 4, leaves);
		addBlock(world, basePos, 3, 6, 0, leaves);
		addBlock(world, basePos, 3, 6, 4, leaves);
		addBlock(world, basePos, 4, 6, 0, leaves);
		addBlock(world, basePos, 4, 6, 1, leaves);
		addBlock(world, basePos, 4, 6, 2, leaves);
		addBlock(world, basePos, 4, 6, 3, leaves);
		addBlock(world, basePos, 4, 6, 4, leaves);
		addBlock(world, basePos, 1, 7, 1, leaves);
		addBlock(world, basePos, 1, 7, 2, leaves);
		addBlock(world, basePos, 1, 7, 3, leaves);
		addBlock(world, basePos, 2, 7, 1, leaves);
		addBlock(world, basePos, 2, 7, 2, leaves);
		addBlock(world, basePos, 2, 7, 3, leaves);
		addBlock(world, basePos, 3, 7, 1, leaves);
		addBlock(world, basePos, 3, 7, 2, leaves);
		addBlock(world, basePos, 3, 7, 3, leaves);
	}
}
