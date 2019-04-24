package net.tslat.aoa3.structure.abyss;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class BloodPine extends AoAStructure {
	public BloodPine() {
		super("BloodPine");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		int y = 0;

		while (y <= 8) {
			if (world.getBlockState(basePos.add(0, y, 0)).getBlock() == Blocks.AIR) {
				addBlock(world, basePos, 0, y, 0, BlockRegister.plantBloodPineStem.getDefaultState());
				y++;
			}
			else {
				break;
			}

			if (rand.nextInt(4) == 0)
				break;
		}

		addBlock(world, basePos, 0, y, 0, BlockRegister.plantBloodPine.getDefaultState());
	}
}
