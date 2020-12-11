package net.tslat.aoa3.worldgen.structures.voxponds;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class DegradedLampPost extends AoAStructure { //StructureSize: 5x5x5
	private static final BlockState degradedSteel = AoABlocks.DEGRADED_STEEL.get().getDefaultState();
	private static final BlockState voxLight = AoABlocks.VOX_LIGHT.get().getDefaultState();

	public DegradedLampPost() {
		super("DegradedLampPost");
	}

	@Override
	protected boolean replacesBlocks() {
		return true;
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 2, degradedSteel);
		addBlock(world, basePos, 2, 0, 0, degradedSteel);
		addBlock(world, basePos, 2, 0, 4, degradedSteel);
		addBlock(world, basePos, 4, 0, 2, degradedSteel);
		addBlock(world, basePos, 0, 1, 2, degradedSteel);
		addBlock(world, basePos, 2, 1, 0, degradedSteel);
		addBlock(world, basePos, 2, 1, 4, degradedSteel);
		addBlock(world, basePos, 4, 1, 2, degradedSteel);
		addBlock(world, basePos, 0, 2, 2, degradedSteel);
		addBlock(world, basePos, 1, 2, 2, degradedSteel);
		addBlock(world, basePos, 2, 2, 0, degradedSteel);
		addBlock(world, basePos, 2, 2, 1, degradedSteel);
		addBlock(world, basePos, 2, 2, 2, degradedSteel);
		addBlock(world, basePos, 2, 2, 3, degradedSteel);
		addBlock(world, basePos, 2, 2, 4, degradedSteel);
		addBlock(world, basePos, 3, 2, 2, degradedSteel);
		addBlock(world, basePos, 4, 2, 2, degradedSteel);
		addBlock(world, basePos, 2, 3, 2, degradedSteel);
		addBlock(world, basePos, 2, 4, 2, voxLight);
	}
}
