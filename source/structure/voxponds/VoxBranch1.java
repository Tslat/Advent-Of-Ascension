package net.tslat.aoa3.structure.voxponds;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class VoxBranch1 extends AoAStructure { //StructureSize: 1x1x4
	private static final IBlockState voxLog = BlockRegister.VOX_LOG.getDefaultState();

	public VoxBranch1() {
		super("VoxBranch1");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 0, voxLog);
		addBlock(world, basePos, 0, 0, 1, voxLog);
		addBlock(world, basePos, 0, 0, 2, voxLog);
		addBlock(world, basePos, 0, 0, 3, voxLog);
	}
}
