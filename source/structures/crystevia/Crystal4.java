package net.nevermine.structures.crystevia;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.nevermine.izer.Blockizer;

import java.util.Random;

public class Crystal4 extends WorldGenerator {
	public boolean generate(final World world, final Random rand, final int i, final int j, final int k) {
		final int pick = rand.nextInt(6);
		Block cBlock;
		if (pick == 1) {
			cBlock = Blockizer.CrystalBlockBlue;
		}
		else if (pick == 2) {
			cBlock = Blockizer.CrystalBlockGreen;
		}
		else if (pick == 3) {
			cBlock = Blockizer.CrystalBlockYellow;
		}
		else if (pick == 4) {
			cBlock = Blockizer.CrystalBlockRed;
		}
		else if (pick == 5) {
			cBlock = Blockizer.CrystalBlockWhite;
		}
		else {
			cBlock = Blockizer.CrystalBlockPurple;
		}
		world.setBlock(i + 0, j + 0, k + 0, cBlock);
		world.setBlock(i + 0, j + 0, k + 1, cBlock);
		world.setBlock(i + 0, j + 1, k + 0, cBlock);
		world.setBlock(i + 0, j + 1, k + 1, cBlock);
		world.setBlock(i + 0, j + 2, k + 0, cBlock);
		world.setBlock(i + 0, j + 2, k + 1, cBlock);
		world.setBlock(i + 0, j + 3, k + 0, cBlock);
		world.setBlock(i + 0, j + 3, k + 1, cBlock);
		world.setBlock(i + 0, j + 4, k + 0, cBlock);
		world.setBlock(i + 0, j + 4, k + 1, cBlock);
		world.setBlock(i + 0, j + 5, k + 0, cBlock);
		world.setBlock(i + 0, j + 5, k + 1, cBlock);
		world.setBlock(i + 0, j + 6, k + 0, cBlock);
		world.setBlock(i + 0, j + 6, k + 1, cBlock);
		world.setBlock(i + 1, j + 0, k + 0, cBlock);
		world.setBlock(i + 1, j + 0, k + 1, cBlock);
		world.setBlock(i + 1, j + 1, k + 0, cBlock);
		world.setBlock(i + 1, j + 1, k + 1, cBlock);
		world.setBlock(i + 1, j + 2, k + 0, cBlock);
		world.setBlock(i + 1, j + 2, k + 1, cBlock);
		world.setBlock(i + 1, j + 3, k + 0, cBlock);
		world.setBlock(i + 1, j + 3, k + 1, cBlock);
		world.setBlock(i + 1, j + 4, k + 0, cBlock);
		world.setBlock(i + 1, j + 4, k + 1, cBlock);
		world.setBlock(i + 1, j + 5, k + 0, cBlock);
		world.setBlock(i + 1, j + 5, k + 1, cBlock);
		world.setBlock(i + 1, j + 6, k + 0, cBlock);
		world.setBlock(i + 1, j + 6, k + 1, cBlock);
		return true;
	}
}
