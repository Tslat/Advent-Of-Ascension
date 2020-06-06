package net.tslat.aoa3.structure.candyland;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class 	GreenPeppermintStack extends AoAStructure {
	public GreenPeppermintStack() {
		super("GreenPeppermintStack");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		int y = 0;

		while (y <= 4) {
			if (world.getBlockState(basePos.add(0, y, 0)).getBlock() == Blocks.AIR) {
				addBlock(world, basePos, 0, y, 0, BlockRegister.GREEN_PEPPERMINT.getDefaultState());
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
