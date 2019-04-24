package net.tslat.aoa3.structure.voxponds;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class ToxicTentacle3 extends AoAStructure { //StructureSize: 4x8x1
	private static final IBlockState greenTentacle = BlockRegister.tentaclesGreen.getDefaultState();
	private static final IBlockState leftDotsTentacle = BlockRegister.tentaclesDotsLeft.getDefaultState();
	private static final IBlockState rightDotsTentacle = BlockRegister.tentaclesDotsRight.getDefaultState();

	public ToxicTentacle3() {
		super("ToxicTentacle3");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 0, greenTentacle);
		addBlock(world, basePos, 0, 1, 0, greenTentacle);
		addBlock(world, basePos, 0, 2, 0, greenTentacle);
		addBlock(world, basePos, 0, 4, 0, greenTentacle);
		addBlock(world, basePos, 0, 5, 0, greenTentacle);
		addBlock(world, basePos, 1, 5, 0, greenTentacle);
		addBlock(world, basePos, 2, 5, 0, greenTentacle);
		addBlock(world, basePos, 3, 5, 0, greenTentacle);
		addBlock(world, basePos, 3, 7, 0, greenTentacle);

		if (rand.nextBoolean()) {
			addBlock(world, basePos, 0, 3, 0, leftDotsTentacle);
		}
		else {
			addBlock(world, basePos, 0, 3, 0, rightDotsTentacle);
		}

		if (rand.nextBoolean()) {
			addBlock(world, basePos, 3, 6, 0, leftDotsTentacle);
		}
		else {
			addBlock(world, basePos, 3, 6, 0, rightDotsTentacle);
		}
	}
}
