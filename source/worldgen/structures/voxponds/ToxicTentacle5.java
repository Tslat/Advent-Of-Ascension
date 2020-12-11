package net.tslat.aoa3.worldgen.structures.voxponds;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class ToxicTentacle5 extends AoAStructure { //StructureSize: 3x6x2
	private static final BlockState greenTentacle = AoABlocks.TENTACLE_GREEN.get().getDefaultState();
	private static final BlockState leftDotsTentacle = AoABlocks.TENTACLE_DOTS_LEFT.get().getDefaultState();
	private static final BlockState rightDotsTentacle = AoABlocks.TENTACLE_DOTS_RIGHT.get().getDefaultState();

	public ToxicTentacle5() {
		super("ToxicTentacle5");
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 1, greenTentacle);
		addBlock(world, basePos, 0, 1, 1, greenTentacle);
		addBlock(world, basePos, 1, 1, 1, greenTentacle);
		addBlock(world, basePos, 2, 1, 1, greenTentacle);
		addBlock(world, basePos, 0, 2, 1, greenTentacle);
		addBlock(world, basePos, 2, 2, 1, greenTentacle);
		addBlock(world, basePos, 0, 3, 0, greenTentacle);
		addBlock(world, basePos, 0, 3, 1, greenTentacle);
		addBlock(world, basePos, 2, 4, 1, greenTentacle);
		addBlock(world, basePos, 0, 5, 0, greenTentacle);

		if (rand.nextBoolean()) {
			addBlock(world, basePos, 0, 4, 0, leftDotsTentacle);
		}
		else {
			addBlock(world, basePos, 0, 4, 0, rightDotsTentacle);
		}

		if (rand.nextBoolean()) {
			addBlock(world, basePos, 2, 3, 1, leftDotsTentacle);
		}
		else {
			addBlock(world, basePos, 2, 3, 1, rightDotsTentacle);
		}
	}
}
