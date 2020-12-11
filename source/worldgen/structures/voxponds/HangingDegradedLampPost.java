package net.tslat.aoa3.worldgen.structures.voxponds;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class HangingDegradedLampPost extends AoAStructure { //StructureSize: 5x5x5
	private static final BlockState degradedSteel = AoABlocks.DEGRADED_STEEL.get().getDefaultState();
	private static final BlockState voxLight = AoABlocks.VOX_LIGHT.get().getDefaultState();

	public HangingDegradedLampPost() {
		super("HangingDegradedLampPost");
	}

	@Override
	protected boolean replacesBlocks() {
		return true;
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 2, 0, 2, voxLight);
		addBlock(world, basePos, 2, 1, 2, degradedSteel);
		addBlock(world, basePos, 0, 2, 2, degradedSteel);
		addBlock(world, basePos, 1, 2, 2, degradedSteel);
		addBlock(world, basePos, 2, 2, 0, degradedSteel);
		addBlock(world, basePos, 2, 2, 1, degradedSteel);
		addBlock(world, basePos, 2, 2, 2, degradedSteel);
		addBlock(world, basePos, 2, 2, 3, degradedSteel);
		addBlock(world, basePos, 2, 2, 4, degradedSteel);
		addBlock(world, basePos, 3, 2, 2, degradedSteel);
		addBlock(world, basePos, 4, 2, 2, degradedSteel);
		addBlock(world, basePos, 0, 3, 2, degradedSteel);
		addBlock(world, basePos, 2, 3, 0, degradedSteel);
		addBlock(world, basePos, 2, 3, 4, degradedSteel);
		addBlock(world, basePos, 4, 3, 2, degradedSteel);
		addBlock(world, basePos, 0, 4, 2, degradedSteel);
		addBlock(world, basePos, 2, 4, 0, degradedSteel);
		addBlock(world, basePos, 2, 4, 4, degradedSteel);
		addBlock(world, basePos, 4, 4, 2, degradedSteel);
	}
}
