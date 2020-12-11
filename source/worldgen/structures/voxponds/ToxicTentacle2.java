package net.tslat.aoa3.worldgen.structures.voxponds;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class ToxicTentacle2 extends AoAStructure { //StructureSize: 4x9x3
	private static final BlockState greenTentacle = AoABlocks.TENTACLE_GREEN.get().getDefaultState();
	private static final BlockState leftDotsTentacle = AoABlocks.TENTACLE_DOTS_LEFT.get().getDefaultState();
	private static final BlockState rightDotsTentacle = AoABlocks.TENTACLE_DOTS_RIGHT.get().getDefaultState();

	public ToxicTentacle2() {
		super("ToxicTentacle2");
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
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
