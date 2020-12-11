package net.tslat.aoa3.worldgen.structures.candyland;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class CandyStack extends AoAStructure {
	public CandyStack() {
		super("CandyStack");
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		int y = 0;

		while (y <= 4) {
			if (world.getBlockState(basePos.add(0, y, 0)).getBlock() == Blocks.AIR) {
				switch (rand.nextInt(3)) {
					case 0:
						addBlock(world, basePos, 0, y, 0, AoABlocks.YELLOW_LOLLYPOP.get().getDefaultState());
						break;
					case 1:
						addBlock(world, basePos, 0, y, 0, AoABlocks.RED_LOLLYPOP.get().getDefaultState());
						break;
					case 2:
						addBlock(world, basePos, 0, y, 0, AoABlocks.BLUE_LOLLYPOP.get().getDefaultState());
						break;
				}

				y++;
			}
			else {
				break;
			}

			if (rand.nextBoolean())
				break;
		}
	}
}
