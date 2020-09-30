package net.tslat.aoa3.structure.candyland;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class CandyTube extends AoAStructure {
	public CandyTube() {
		super("CandyTube");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		int y = 0;

		while (y <= 4) {
			if (world.getBlockState(basePos.add(0, y, 0)).getBlock() == Blocks.AIR) {
				addBlock(world, basePos, 0, y, 0, BlockRegister.PLASTIC_STICK.getDefaultState());
				y++;
			}
			else {
				break;
			}

			if (rand.nextBoolean())
				break;
		}

		addBlock(world, basePos, 0, y, 0, BlockRegister.CANDY_TUBE.getDefaultState());
	}
}
