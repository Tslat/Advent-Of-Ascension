package net.tslat.aoa3.worldgen.structures.creeponia;

import net.minecraft.block.BlockState;
import net.minecraft.block.VineBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class CreepTree4 extends AoAStructure { //StructureSize: 7x7x7
	private static final BlockState creepLeaves = AoABlocks.CREEP_LEAVES.get().getDefaultState();
	private static final BlockState creepLog = AoABlocks.CREEP_LOG.get().getDefaultState();
	private static final BlockState creepVineNorth = AoABlocks.CREEP_VINES.get().getDefaultState().with(VineBlock.NORTH, true);
	private static final BlockState creepVineSouth = AoABlocks.CREEP_VINES.get().getDefaultState().with(VineBlock.SOUTH, true);
	private static final BlockState creepVineWest = AoABlocks.CREEP_VINES.get().getDefaultState().with(VineBlock.WEST, true);
	private static final BlockState creepVineEast = AoABlocks.CREEP_VINES.get().getDefaultState().with(VineBlock.EAST, true);

	public CreepTree4() {
		super("CreepTree4");
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 3, 0, 3, creepLog);
		addBlock(world, basePos, 3, 1, 3, creepLog);
		addBlock(world, basePos, 3, 2, 3, creepLog);
		addBlock(world, basePos, 3, 3, 3, creepLog);
		addBlock(world, basePos, 3, 4, 3, creepLog);
		addBlock(world, basePos, 1, 5, 3, creepLeaves);
		addBlock(world, basePos, 2, 5, 3, creepLeaves);
		addBlock(world, basePos, 3, 5, 1, creepLeaves);
		addBlock(world, basePos, 3, 5, 2, creepLeaves);
		addBlock(world, basePos, 3, 5, 3, creepLeaves);
		addBlock(world, basePos, 3, 5, 4, creepLeaves);
		addBlock(world, basePos, 3, 5, 5, creepLeaves);
		addBlock(world, basePos, 4, 5, 3, creepLeaves);
		addBlock(world, basePos, 5, 5, 3, creepLeaves);
		addBlock(world, basePos, 2, 6, 3, creepLeaves);
		addBlock(world, basePos, 3, 6, 2, creepLeaves);
		addBlock(world, basePos, 3, 6, 3, creepLeaves);
		addBlock(world, basePos, 3, 6, 4, creepLeaves);
		addBlock(world, basePos, 4, 6, 3, creepLeaves);

		addBlock(world, basePos, 0, 3, 3, creepVineEast);
		addBlock(world, basePos, 1, 3, 2, creepVineSouth);
		addBlock(world, basePos, 1, 3, 4, creepVineNorth);
		addBlock(world, basePos, 2, 3, 1, creepVineEast);
		addBlock(world, basePos, 2, 3, 5, creepVineEast);
		addBlock(world, basePos, 3, 3, 0, creepVineSouth);
		addBlock(world, basePos, 3, 3, 6, creepVineNorth);
		addBlock(world, basePos, 4, 3, 1, creepVineWest);
		addBlock(world, basePos, 4, 3, 5, creepVineWest);
		addBlock(world, basePos, 5, 3, 2, creepVineSouth);
		addBlock(world, basePos, 5, 3, 4, creepVineNorth);
		addBlock(world, basePos, 6, 3, 3, creepVineWest);
		addBlock(world, basePos, 0, 4, 3, creepVineEast);
		addBlock(world, basePos, 1, 4, 2, creepVineSouth);
		addBlock(world, basePos, 1, 4, 4, creepVineNorth);
		addBlock(world, basePos, 2, 4, 1, creepVineEast);
		addBlock(world, basePos, 2, 4, 5, creepVineEast);
		addBlock(world, basePos, 3, 4, 0, creepVineSouth);
		addBlock(world, basePos, 3, 4, 6, creepVineNorth);
		addBlock(world, basePos, 4, 4, 1, creepVineWest);
		addBlock(world, basePos, 4, 4, 5, creepVineWest);
		addBlock(world, basePos, 5, 4, 2, creepVineSouth);
		addBlock(world, basePos, 5, 4, 4, creepVineNorth);
		addBlock(world, basePos, 6, 4, 3, creepVineWest);
		addBlock(world, basePos, 0, 5, 3, creepVineEast);
		addBlock(world, basePos, 1, 5, 2, creepVineSouth);
		addBlock(world, basePos, 1, 5, 4, creepVineNorth);
		addBlock(world, basePos, 2, 5, 1, creepVineEast);
		addBlock(world, basePos, 2, 5, 5, creepVineEast);
		addBlock(world, basePos, 3, 5, 0, creepVineSouth);
		addBlock(world, basePos, 3, 5, 6, creepVineNorth);
		addBlock(world, basePos, 4, 5, 1, creepVineWest);
		addBlock(world, basePos, 4, 5, 5, creepVineWest);
		addBlock(world, basePos, 5, 5, 2, creepVineSouth);
		addBlock(world, basePos, 5, 5, 4, creepVineNorth);
		addBlock(world, basePos, 6, 5, 3, creepVineWest);
	}
}
