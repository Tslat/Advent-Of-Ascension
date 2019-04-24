package net.tslat.aoa3.structure.voxponds;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class DegradedLampPost extends AoAStructure { //StructureSize: 5x5x5
	private static final IBlockState degradedSteel = BlockRegister.degradedSteel.getDefaultState();
	private static final IBlockState voxLight = BlockRegister.lightVox.getDefaultState();

	public DegradedLampPost() {
		super("DegradedLampPost");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
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
