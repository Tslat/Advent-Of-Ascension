package net.tslat.aoa3.structure.lunalus;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class LunossoTree1 extends AoAStructure { //StructureSize: 3x9x3
	private static final IBlockState lunossoLeaves = BlockRegister.LUNOSSO_LEAVES.getDefaultState();
	private static final IBlockState lunideLog = BlockRegister.LUNIDE_LOG.getDefaultState();

	public LunossoTree1() {
		super("LunossoTree1");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 1, 0, 1, lunideLog);
		addBlock(world, basePos, 1, 1, 1, lunideLog);
		addBlock(world, basePos, 1, 2, 1, lunideLog);
		addBlock(world, basePos, 1, 3, 1, lunideLog);
		addBlock(world, basePos, 1, 4, 1, lunideLog);
		addBlock(world, basePos, 1, 5, 1, lunideLog);
		addBlock(world, basePos, 0, 6, 1, lunideLog);
		addBlock(world, basePos, 1, 6, 0, lunideLog);
		addBlock(world, basePos, 1, 6, 1, lunideLog);
		addBlock(world, basePos, 1, 6, 2, lunideLog);
		addBlock(world, basePos, 2, 6, 1, lunideLog);
		addBlock(world, basePos, 0, 8, 1, lunossoLeaves);
		addBlock(world, basePos, 1, 8, 0, lunossoLeaves);
		addBlock(world, basePos, 1, 8, 2, lunossoLeaves);
		addBlock(world, basePos, 2, 8, 1, lunossoLeaves);
	}
}
