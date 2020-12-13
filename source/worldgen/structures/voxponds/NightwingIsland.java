package net.tslat.aoa3.worldgen.structures.voxponds;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class NightwingIsland extends AoAStructure { //StructureSize: 5x2x5
	private static final BlockState degradedSteel = AoABlocks.DEGRADED_STEEL.get().getDefaultState();
	private static final BlockState nightwingSpawner = Blocks.SPAWNER.getDefaultState();

	public NightwingIsland() {
		super("NightwingIsland");
	}

	@Override
	protected boolean replacesBlocks() {
		return true;
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 0, degradedSteel);
		addBlock(world, basePos, 0, 0, 1, degradedSteel);
		addBlock(world, basePos, 0, 0, 2, degradedSteel);
		addBlock(world, basePos, 0, 0, 3, degradedSteel);
		addBlock(world, basePos, 0, 0, 4, degradedSteel);
		addBlock(world, basePos, 1, 0, 0, degradedSteel);
		addBlock(world, basePos, 1, 0, 1, degradedSteel);
		addBlock(world, basePos, 1, 0, 2, degradedSteel);
		addBlock(world, basePos, 1, 0, 3, degradedSteel);
		addBlock(world, basePos, 1, 0, 4, degradedSteel);
		addBlock(world, basePos, 2, 0, 0, degradedSteel);
		addBlock(world, basePos, 2, 0, 1, degradedSteel);
		addBlock(world, basePos, 2, 0, 2, degradedSteel);
		addBlock(world, basePos, 2, 0, 3, degradedSteel);
		addBlock(world, basePos, 2, 0, 4, degradedSteel);
		addBlock(world, basePos, 3, 0, 0, degradedSteel);
		addBlock(world, basePos, 3, 0, 1, degradedSteel);
		addBlock(world, basePos, 3, 0, 2, degradedSteel);
		addBlock(world, basePos, 3, 0, 3, degradedSteel);
		addBlock(world, basePos, 3, 0, 4, degradedSteel);
		addBlock(world, basePos, 4, 0, 0, degradedSteel);
		addBlock(world, basePos, 4, 0, 1, degradedSteel);
		addBlock(world, basePos, 4, 0, 2, degradedSteel);
		addBlock(world, basePos, 4, 0, 3, degradedSteel);
		addBlock(world, basePos, 4, 0, 4, degradedSteel);
		addBlock(world, basePos, 2, 1, 2, nightwingSpawner);
	}

	@Override
	protected void doPostBuildOps(IWorld world, Random rand, BlockPos basePos) {
		initSpawner(world, basePos.add(2, 1, 2), AoAEntities.Mobs.NIGHTWING.get());
	}
}
