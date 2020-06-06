package net.tslat.aoa3.structure.voxponds;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class HangingDegradedLampPost extends AoAStructure { //StructureSize: 5x5x5
	private static final IBlockState degradedSteel = BlockRegister.DEGRADED_STEEL.getDefaultState();
	private static final IBlockState voxLight = BlockRegister.VOX_LIGHT.getDefaultState();

	public HangingDegradedLampPost() {
		super("HangingDegradedLampPost");
	}

	@Override
	protected boolean replacesBlocks() {
		return true;
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
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
