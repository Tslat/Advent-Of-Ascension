package net.tslat.aoa3.structure.lunalus;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class LuniciaTree2 extends AoAStructure { //StructureSize: 5x9x5
	private static final IBlockState luniciaLeaves = BlockRegister.leavesLunicia.getDefaultState();
	private static final IBlockState lunideLog = BlockRegister.logLunide.getDefaultState();

	public LuniciaTree2() {
		super("LuniciaTree2");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 2, 0, 2, lunideLog);
		addBlock(world, basePos, 2, 1, 2, lunideLog);
		addBlock(world, basePos, 2, 2, 2, lunideLog);
		addBlock(world, basePos, 2, 3, 2, lunideLog);
		addBlock(world, basePos, 2, 4, 2, lunideLog);
		addBlock(world, basePos, 0, 6, 2, luniciaLeaves);
		addBlock(world, basePos, 1, 6, 1, luniciaLeaves);
		addBlock(world, basePos, 1, 6, 2, luniciaLeaves);
		addBlock(world, basePos, 1, 6, 3, luniciaLeaves);
		addBlock(world, basePos, 2, 6, 0, luniciaLeaves);
		addBlock(world, basePos, 2, 6, 1, luniciaLeaves);
		addBlock(world, basePos, 2, 6, 2, luniciaLeaves);
		addBlock(world, basePos, 2, 6, 3, luniciaLeaves);
		addBlock(world, basePos, 2, 6, 4, luniciaLeaves);
		addBlock(world, basePos, 3, 6, 1, luniciaLeaves);
		addBlock(world, basePos, 3, 6, 2, luniciaLeaves);
		addBlock(world, basePos, 3, 6, 3, luniciaLeaves);
		addBlock(world, basePos, 4, 6, 2, luniciaLeaves);
		addBlock(world, basePos, 1, 7, 2, luniciaLeaves);
		addBlock(world, basePos, 2, 7, 1, luniciaLeaves);
		addBlock(world, basePos, 2, 7, 2, luniciaLeaves);
		addBlock(world, basePos, 2, 7, 3, luniciaLeaves);
		addBlock(world, basePos, 3, 7, 2, luniciaLeaves);
		addBlock(world, basePos, 2, 8, 2, luniciaLeaves);
	}
}
