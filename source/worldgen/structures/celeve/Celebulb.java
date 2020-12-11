package net.tslat.aoa3.worldgen.structures.celeve;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class Celebulb extends AoAStructure {
	public Celebulb() {
		super("Celebulb");
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		int y = 0;

		while (y <= 8) {
			if (world.getBlockState(basePos.add(0, y, 0)).getBlock() == Blocks.AIR) {
				addBlock(world, basePos, 0, y, 0, AoABlocks.CELEBULBS_STEM.get().getDefaultState());
				y++;
			}
			else {
				break;
			}

			if (rand.nextInt(3) == 0)
				break;
		}

		if (rand.nextBoolean()) {
			addBlock(world, basePos, 0, y, 0, AoABlocks.GREEN_CELEBULBS.get().getDefaultState());
		}
		else {
			addBlock(world, basePos, 0, y, 0, AoABlocks.YELLOW_CELEBULBS.get().getDefaultState());
		}
	}
}
