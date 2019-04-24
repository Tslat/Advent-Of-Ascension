package net.tslat.aoa3.structure.deeplands;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class DeepVines extends AoAStructure {
	public DeepVines() {
		super("DeepVines");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		int y = 0;

		while (y <= 5) {
			if (world.getBlockState(basePos.add(0, y, 0)).getBlock() == Blocks.AIR) {
				addBlock(world, basePos, 0, y, 0, BlockRegister.plantDeepVines.getDefaultState());
				y++;
			}
			else {
				break;
			}

			if (rand.nextInt(4) == 0)
				break;
		}

		addBlock(world, basePos, 0, y, 0, BlockRegister.plantDeepBulb.getDefaultState());
	}
}
