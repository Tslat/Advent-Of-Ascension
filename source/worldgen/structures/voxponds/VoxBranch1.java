package net.tslat.aoa3.worldgen.structures.voxponds;

import net.minecraft.block.BlockState;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class VoxBranch1 extends AoAStructure { //StructureSize: 1x1x4
	private static final BlockState voxLog = AoABlocks.VOX_LOG.get().getDefaultState().with(RotatedPillarBlock.AXIS, Direction.Axis.Z);

	public VoxBranch1() {
		super("VoxBranch1");
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 0, voxLog);
		addBlock(world, basePos, 0, 0, 1, voxLog);
		addBlock(world, basePos, 0, 0, 2, voxLog);
		addBlock(world, basePos, 0, 0, 3, voxLog);
	}
}
