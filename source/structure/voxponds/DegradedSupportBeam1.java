package net.tslat.aoa3.structure.voxponds;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class DegradedSupportBeam1 extends AoAStructure { //StructureSize: 4x16x4
	private static final IBlockState degradedSteel = BlockRegister.DEGRADED_STEEL.getDefaultState();

	public DegradedSupportBeam1() {
		super("DegradedSupportBeam1");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 0, degradedSteel);
		addBlock(world, basePos, 0, 0, 3, degradedSteel);
		addBlock(world, basePos, 3, 0, 0, degradedSteel);
		addBlock(world, basePos, 3, 0, 3, degradedSteel);
		addBlock(world, basePos, 0, 1, 0, degradedSteel);
		addBlock(world, basePos, 0, 1, 3, degradedSteel);
		addBlock(world, basePos, 3, 1, 0, degradedSteel);
		addBlock(world, basePos, 3, 1, 3, degradedSteel);
		addBlock(world, basePos, 0, 2, 0, degradedSteel);
		addBlock(world, basePos, 0, 2, 1, degradedSteel);
		addBlock(world, basePos, 0, 2, 2, degradedSteel);
		addBlock(world, basePos, 0, 2, 3, degradedSteel);
		addBlock(world, basePos, 1, 2, 0, degradedSteel);
		addBlock(world, basePos, 1, 2, 3, degradedSteel);
		addBlock(world, basePos, 2, 2, 0, degradedSteel);
		addBlock(world, basePos, 2, 2, 3, degradedSteel);
		addBlock(world, basePos, 3, 2, 0, degradedSteel);
		addBlock(world, basePos, 3, 2, 1, degradedSteel);
		addBlock(world, basePos, 3, 2, 2, degradedSteel);
		addBlock(world, basePos, 3, 2, 3, degradedSteel);
		addBlock(world, basePos, 0, 3, 0, degradedSteel);
		addBlock(world, basePos, 0, 3, 3, degradedSteel);
		addBlock(world, basePos, 3, 3, 0, degradedSteel);
		addBlock(world, basePos, 3, 3, 3, degradedSteel);
		addBlock(world, basePos, 0, 4, 0, degradedSteel);
		addBlock(world, basePos, 0, 4, 3, degradedSteel);
		addBlock(world, basePos, 3, 4, 0, degradedSteel);
		addBlock(world, basePos, 3, 4, 3, degradedSteel);
		addBlock(world, basePos, 0, 5, 0, degradedSteel);
		addBlock(world, basePos, 0, 5, 1, degradedSteel);
		addBlock(world, basePos, 0, 5, 2, degradedSteel);
		addBlock(world, basePos, 0, 5, 3, degradedSteel);
		addBlock(world, basePos, 1, 5, 0, degradedSteel);
		addBlock(world, basePos, 1, 5, 3, degradedSteel);
		addBlock(world, basePos, 2, 5, 0, degradedSteel);
		addBlock(world, basePos, 2, 5, 3, degradedSteel);
		addBlock(world, basePos, 3, 5, 0, degradedSteel);
		addBlock(world, basePos, 3, 5, 1, degradedSteel);
		addBlock(world, basePos, 3, 5, 2, degradedSteel);
		addBlock(world, basePos, 3, 5, 3, degradedSteel);
		addBlock(world, basePos, 0, 6, 0, degradedSteel);
		addBlock(world, basePos, 0, 6, 3, degradedSteel);
		addBlock(world, basePos, 3, 6, 0, degradedSteel);
		addBlock(world, basePos, 3, 6, 3, degradedSteel);
		addBlock(world, basePos, 0, 7, 0, degradedSteel);
		addBlock(world, basePos, 0, 7, 3, degradedSteel);
		addBlock(world, basePos, 3, 7, 0, degradedSteel);
		addBlock(world, basePos, 3, 7, 3, degradedSteel);
		addBlock(world, basePos, 0, 8, 0, degradedSteel);
		addBlock(world, basePos, 0, 8, 1, degradedSteel);
		addBlock(world, basePos, 0, 8, 2, degradedSteel);
		addBlock(world, basePos, 0, 8, 3, degradedSteel);
		addBlock(world, basePos, 1, 8, 0, degradedSteel);
		addBlock(world, basePos, 1, 8, 3, degradedSteel);
		addBlock(world, basePos, 2, 8, 0, degradedSteel);
		addBlock(world, basePos, 2, 8, 3, degradedSteel);
		addBlock(world, basePos, 3, 8, 0, degradedSteel);
		addBlock(world, basePos, 3, 8, 1, degradedSteel);
		addBlock(world, basePos, 3, 8, 2, degradedSteel);
		addBlock(world, basePos, 3, 8, 3, degradedSteel);
		addBlock(world, basePos, 0, 9, 0, degradedSteel);
		addBlock(world, basePos, 0, 9, 3, degradedSteel);
		addBlock(world, basePos, 3, 9, 0, degradedSteel);
		addBlock(world, basePos, 3, 9, 3, degradedSteel);
		addBlock(world, basePos, 0, 10, 0, degradedSteel);
		addBlock(world, basePos, 0, 10, 3, degradedSteel);
		addBlock(world, basePos, 3, 10, 0, degradedSteel);
		addBlock(world, basePos, 3, 10, 3, degradedSteel);
		addBlock(world, basePos, 0, 11, 0, degradedSteel);
		addBlock(world, basePos, 0, 11, 1, degradedSteel);
		addBlock(world, basePos, 0, 11, 2, degradedSteel);
		addBlock(world, basePos, 0, 11, 3, degradedSteel);
		addBlock(world, basePos, 1, 11, 0, degradedSteel);
		addBlock(world, basePos, 1, 11, 3, degradedSteel);
		addBlock(world, basePos, 2, 11, 0, degradedSteel);
		addBlock(world, basePos, 2, 11, 3, degradedSteel);
		addBlock(world, basePos, 3, 11, 0, degradedSteel);
		addBlock(world, basePos, 3, 11, 1, degradedSteel);
		addBlock(world, basePos, 3, 11, 2, degradedSteel);
		addBlock(world, basePos, 3, 11, 3, degradedSteel);
		addBlock(world, basePos, 0, 12, 0, degradedSteel);
		addBlock(world, basePos, 0, 12, 3, degradedSteel);
		addBlock(world, basePos, 3, 12, 0, degradedSteel);
		addBlock(world, basePos, 3, 12, 3, degradedSteel);
		addBlock(world, basePos, 0, 13, 0, degradedSteel);
		addBlock(world, basePos, 0, 13, 3, degradedSteel);
		addBlock(world, basePos, 3, 13, 0, degradedSteel);
		addBlock(world, basePos, 3, 13, 3, degradedSteel);
		addBlock(world, basePos, 0, 14, 0, degradedSteel);
		addBlock(world, basePos, 0, 14, 1, degradedSteel);
		addBlock(world, basePos, 0, 14, 2, degradedSteel);
		addBlock(world, basePos, 0, 14, 3, degradedSteel);
		addBlock(world, basePos, 1, 14, 0, degradedSteel);
		addBlock(world, basePos, 1, 14, 3, degradedSteel);
		addBlock(world, basePos, 2, 14, 0, degradedSteel);
		addBlock(world, basePos, 2, 14, 3, degradedSteel);
		addBlock(world, basePos, 3, 14, 0, degradedSteel);
		addBlock(world, basePos, 3, 14, 1, degradedSteel);
		addBlock(world, basePos, 3, 14, 2, degradedSteel);
		addBlock(world, basePos, 3, 14, 3, degradedSteel);
		addBlock(world, basePos, 0, 15, 0, degradedSteel);
		addBlock(world, basePos, 0, 15, 3, degradedSteel);
		addBlock(world, basePos, 3, 15, 0, degradedSteel);
		addBlock(world, basePos, 3, 15, 3, degradedSteel);
	}
}
