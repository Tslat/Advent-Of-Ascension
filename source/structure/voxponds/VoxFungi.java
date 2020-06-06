package net.tslat.aoa3.structure.voxponds;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class VoxFungi extends AoAStructure {
	public VoxFungi() {
		super("VoxFungi");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		int y = 0;

		while (y <= 1) {
			if (world.getBlockState(basePos.add(0, y, 0)).getBlock() == Blocks.AIR) {
				addBlock(world, basePos, 0, y, 0, BlockRegister.VOX_FUNGI_STEM.getDefaultState());
				y++;
			}
			else {
				break;
			}

			if (rand.nextInt(2) == 0)
				break;
		}

		addBlock(world, basePos, 0, y, 0, BlockRegister.VOX_FUNGI.getDefaultState());
	}
}
