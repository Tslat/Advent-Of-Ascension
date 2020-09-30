package net.tslat.aoa3.structure.voxponds;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class ToxicTentacle1 extends AoAStructure { //StructureSize: 2x10x2
	private static final IBlockState greenTentacle = BlockRegister.TENTACLES_GREEN.getDefaultState();
	private static final IBlockState leftDotsTentacle = BlockRegister.TENTACLES_DOTS_LEFT.getDefaultState();
	private static final IBlockState rightDotsTentacle = BlockRegister.TENTACLES_DOTS_RIGHT.getDefaultState();

	public ToxicTentacle1() {
		super("ToxicTentacle1");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 0, greenTentacle);
		addBlock(world, basePos, 0, 1, 0, greenTentacle);
		addBlock(world, basePos, 0, 2, 0, greenTentacle);
		addBlock(world, basePos, 0, 3, 0, greenTentacle);
		addBlock(world, basePos, 0, 3, 1, greenTentacle);
		addBlock(world, basePos, 0, 4, 1, greenTentacle);
		addBlock(world, basePos, 0, 6, 1, greenTentacle);
		addBlock(world, basePos, 1, 6, 1, greenTentacle);
		addBlock(world, basePos, 1, 7, 1, greenTentacle);
		addBlock(world, basePos, 1, 9, 1, greenTentacle);

		if (rand.nextBoolean()) {
			addBlock(world, basePos, 0, 5, 1, leftDotsTentacle);
		}
		else {
			addBlock(world, basePos, 0, 5, 1, rightDotsTentacle);
		}

		if (rand.nextBoolean()) {
			addBlock(world, basePos, 1, 8, 1, leftDotsTentacle);
		}
		else {
			addBlock(world, basePos, 1, 8, 1, rightDotsTentacle);
		}
	}
}
