package net.tslat.aoa3.structure.voxponds;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class ToxicTentacle2 extends AoAStructure { //StructureSize: 4x9x3
	private static final IBlockState greenTentacle = BlockRegister.tentaclesGreen.getDefaultState();
	private static final IBlockState leftDotsTentacle = BlockRegister.tentaclesDotsLeft.getDefaultState();
	private static final IBlockState rightDotsTentacle = BlockRegister.tentaclesDotsRight.getDefaultState();

	public ToxicTentacle2() {
		super("ToxicTentacle2");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 1, 0, 1, greenTentacle);
		addBlock(world, basePos, 1, 1, 1, greenTentacle);
		addBlock(world, basePos, 1, 2, 1, greenTentacle);
		addBlock(world, basePos, 1, 4, 1, greenTentacle);
		addBlock(world, basePos, 1, 5, 1, greenTentacle);
		addBlock(world, basePos, 0, 6, 1, greenTentacle);
		addBlock(world, basePos, 1, 6, 1, greenTentacle);
		addBlock(world, basePos, 2, 6, 1, greenTentacle);
		addBlock(world, basePos, 3, 6, 1, greenTentacle);
		addBlock(world, basePos, 0, 7, 1, greenTentacle);
		addBlock(world, basePos, 3, 7, 1, greenTentacle);
		addBlock(world, basePos, 3, 7, 2, greenTentacle);
		addBlock(world, basePos, 0, 8, 0, greenTentacle);
		addBlock(world, basePos, 0, 8, 1, greenTentacle);

		if (rand.nextBoolean()) {
			addBlock(world, basePos, 1, 3, 1, leftDotsTentacle);
		}
		else {
			addBlock(world, basePos, 1, 3, 1, rightDotsTentacle);
		}
	}
}
