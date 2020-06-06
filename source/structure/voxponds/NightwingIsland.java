package net.tslat.aoa3.structure.voxponds;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class NightwingIsland extends AoAStructure { //StructureSize: 5x2x5
	private static final IBlockState degradedSteel = BlockRegister.DEGRADED_STEEL.getDefaultState();
	private static final IBlockState nightwingSpawner = BlockRegister.NIGHTWING_SPAWNER.getDefaultState();

	public NightwingIsland() {
		super("NightwingIsland");
	}

	@Override
	protected boolean replacesBlocks() {
		return true;
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
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
}
