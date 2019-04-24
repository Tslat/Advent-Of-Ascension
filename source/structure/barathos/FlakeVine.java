package net.tslat.aoa3.structure.barathos;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class FlakeVine extends AoAStructure {

	public FlakeVine() {
		super("FlakeVine");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		int y = 0;

		while (y <= 3) {
			if (world.getBlockState(basePos.add(0, y, 0)).getBlock() == Blocks.AIR) {
				addBlock(world, basePos, 0, y, 0, BlockRegister.plantFlakeVine.getDefaultState());
				y++;
			}
			else {
				break;
			}

			if (rand.nextBoolean())
				break;
		}

		addBlock(world, basePos, 0, y, 0, BlockRegister.plantFlakeVineTop.getDefaultState());
	}
}
