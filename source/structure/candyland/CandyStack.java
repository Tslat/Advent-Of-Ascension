package net.tslat.aoa3.structure.candyland;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class CandyStack extends AoAStructure {
	public CandyStack() {
		super("CandyStack");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		int y = 0;

		while (y <= 4) {
			if (world.getBlockState(basePos.add(0, y, 0)).getBlock() == Blocks.AIR) {
				switch (rand.nextInt(3)) {
					case 0:
						addBlock(world, basePos, 0, y, 0, BlockRegister.YELLOW_LOLLYPOP.getDefaultState());
						break;
					case 1:
						addBlock(world, basePos, 0, y, 0, BlockRegister.RED_LOLLYPOP.getDefaultState());
						break;
					case 2:
						addBlock(world, basePos, 0, y, 0, BlockRegister.BLUE_LOLLYPOP.getDefaultState());
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
