package net.tslat.aoa3.worldgen.structures.voxponds;

import net.minecraft.block.BlockState;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class VoxBranch2 extends AoAStructure { //StructureSize: 4x1x1
	private static final BlockState voxLog = AoABlocks.VOX_LOG.get().getDefaultState().with(RotatedPillarBlock.AXIS, Direction.Axis.X);

	public VoxBranch2() {
		super("VoxBranch2");
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 0, voxLog);
		addBlock(world, basePos, 1, 0, 0, voxLog);
		addBlock(world, basePos, 2, 0, 0, voxLog);
		addBlock(world, basePos, 3, 0, 0, voxLog);
	}
}
