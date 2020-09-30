package net.tslat.aoa3.structure.abyss;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class EyeHanger3 extends AoAStructure { //StructureSize: 5x7x5
	private static final IBlockState eyeBlock = BlockRegister.EYE_BLOCK.getDefaultState();
	private static final IBlockState bloodLog = BlockRegister.BLOOD_LOG.getDefaultState();

	public EyeHanger3() {
		super("EyeHanger3");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 2, 0, 2, bloodLog);
		addBlock(world, basePos, 2, 1, 2, bloodLog);
		addBlock(world, basePos, 2, 2, 2, bloodLog);
		addBlock(world, basePos, 2, 3, 2, bloodLog);
		addBlock(world, basePos, 2, 4, 2, bloodLog);
		addBlock(world, basePos, 0, 5, 0, bloodLog);
		addBlock(world, basePos, 0, 5, 1, bloodLog);
		addBlock(world, basePos, 0, 5, 2, bloodLog);
		addBlock(world, basePos, 0, 5, 3, bloodLog);
		addBlock(world, basePos, 0, 5, 4, bloodLog);
		addBlock(world, basePos, 1, 5, 0, bloodLog);
		addBlock(world, basePos, 1, 5, 1, eyeBlock);
		addBlock(world, basePos, 1, 5, 2, eyeBlock);
		addBlock(world, basePos, 1, 5, 3, eyeBlock);
		addBlock(world, basePos, 1, 5, 4, bloodLog);
		addBlock(world, basePos, 2, 5, 0, bloodLog);
		addBlock(world, basePos, 2, 5, 1, eyeBlock);
		addBlock(world, basePos, 2, 5, 2, bloodLog);
		addBlock(world, basePos, 2, 5, 3, eyeBlock);
		addBlock(world, basePos, 2, 5, 4, bloodLog);
		addBlock(world, basePos, 3, 5, 0, bloodLog);
		addBlock(world, basePos, 3, 5, 1, eyeBlock);
		addBlock(world, basePos, 3, 5, 2, eyeBlock);
		addBlock(world, basePos, 3, 5, 3, eyeBlock);
		addBlock(world, basePos, 3, 5, 4, bloodLog);
		addBlock(world, basePos, 4, 5, 0, bloodLog);
		addBlock(world, basePos, 4, 5, 1, bloodLog);
		addBlock(world, basePos, 4, 5, 2, bloodLog);
		addBlock(world, basePos, 4, 5, 3, bloodLog);
		addBlock(world, basePos, 4, 5, 4, bloodLog);
		addBlock(world, basePos, 1, 6, 1, bloodLog);
		addBlock(world, basePos, 1, 6, 2, bloodLog);
		addBlock(world, basePos, 1, 6, 3, bloodLog);
		addBlock(world, basePos, 2, 6, 1, bloodLog);
		addBlock(world, basePos, 2, 6, 2, bloodLog);
		addBlock(world, basePos, 2, 6, 3, bloodLog);
		addBlock(world, basePos, 3, 6, 1, bloodLog);
		addBlock(world, basePos, 3, 6, 2, bloodLog);
		addBlock(world, basePos, 3, 6, 3, bloodLog);
	}
}
