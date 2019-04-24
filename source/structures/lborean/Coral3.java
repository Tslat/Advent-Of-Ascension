package net.nevermine.structures.lborean;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.nevermine.izer.Blockizer;

import java.util.Random;

public class Coral3 extends WorldGenerator {
	public boolean generate(final World world, final Random rand, final int i, final int j, final int k) {
		if (world.getBlock(i + 1, j - 1, k + 10) != Blockizer.GrassBorean || world.getBlock(i + 1, j - 1, k + 1) != Blockizer.GrassBorean) {
			return false;
		}
		world.setBlock(i + 0, j + 2, k + 1, Blockizer.CoralGreen);
		world.setBlock(i + 0, j + 3, k + 10, Blockizer.CoralGreen);
		world.setBlock(i + 0, j + 4, k + 4, Blockizer.CoralGreen);
		world.setBlock(i + 0, j + 4, k + 8, Blockizer.CoralGreen);
		world.setBlock(i + 0, j + 6, k + 5, Blockizer.CoralGreen);
		world.setBlock(i + 1, j + 0, k + 1, Blockizer.CoralGreen);
		world.setBlock(i + 1, j - 1, k + 1, Blockizer.CoralGreen);
		world.setBlock(i + 1, j - 2, k + 1, Blockizer.CoralGreen);
		world.setBlock(i + 1, j + 0, k + 10, Blockizer.CoralGreen);
		world.setBlock(i + 1, j - 1, k + 10, Blockizer.CoralGreen);
		world.setBlock(i + 1, j - 2, k + 10, Blockizer.CoralGreen);
		world.setBlock(i + 1, j + 1, k + 0, Blockizer.CoralGreen);
		world.setBlock(i + 1, j + 1, k + 1, Blockizer.CoralGreen);
		world.setBlock(i + 1, j + 1, k + 10, Blockizer.CoralGreen);
		world.setBlock(i + 1, j + 2, k + 1, Blockizer.CoralGreen);
		world.setBlock(i + 1, j + 2, k + 3, Blockizer.CoralGreen);
		world.setBlock(i + 1, j + 2, k + 10, Blockizer.CoralGreen);
		world.setBlock(i + 1, j + 2, k + 11, Blockizer.CoralGreen);
		world.setBlock(i + 1, j + 3, k + 0, Blockizer.CoralGreen);
		world.setBlock(i + 1, j + 3, k + 1, Blockizer.CoralGreen);
		world.setBlock(i + 1, j + 3, k + 2, Blockizer.CoralGreen);
		world.setBlock(i + 1, j + 3, k + 3, Blockizer.CoralGreen);
		world.setBlock(i + 1, j + 3, k + 4, Blockizer.CoralGreen);
		world.setBlock(i + 1, j + 3, k + 8, Blockizer.CoralGreen);
		world.setBlock(i + 1, j + 3, k + 9, Blockizer.CoralGreen);
		world.setBlock(i + 1, j + 3, k + 10, Blockizer.CoralGreen);
		world.setBlock(i + 1, j + 4, k + 2, Blockizer.CoralGreen);
		world.setBlock(i + 1, j + 4, k + 4, Blockizer.CoralGreen);
		world.setBlock(i + 1, j + 4, k + 5, Blockizer.CoralGreen);
		world.setBlock(i + 1, j + 4, k + 7, Blockizer.CoralGreen);
		world.setBlock(i + 1, j + 4, k + 8, Blockizer.CoralGreen);
		world.setBlock(i + 1, j + 4, k + 10, Blockizer.CoralGreen);
		world.setBlock(i + 1, j + 5, k + 3, Blockizer.CoralGreen);
		world.setBlock(i + 1, j + 5, k + 4, Blockizer.CoralGreen);
		world.setBlock(i + 1, j + 5, k + 8, Blockizer.CoralGreen);
		world.setBlock(i + 1, j + 6, k + 4, Blockizer.CoralGreen);
		world.setBlock(i + 1, j + 6, k + 5, Blockizer.CoralGreen);
		world.setBlock(i + 1, j + 6, k + 6, Blockizer.CoralGreen);
		world.setBlock(i + 1, j + 6, k + 7, Blockizer.CoralGreen);
		world.setBlock(i + 1, j + 6, k + 8, Blockizer.CoralGreen);
		world.setBlock(i + 1, j + 6, k + 9, Blockizer.CoralGreen);
		world.setBlock(i + 1, j + 7, k + 6, Blockizer.CoralGreen);
		world.setBlock(i + 1, j + 7, k + 8, Blockizer.CoralGreen);
		world.setBlock(i + 2, j + 1, k + 10, Blockizer.CoralGreen);
		world.setBlock(i + 2, j + 3, k + 1, Blockizer.CoralGreen);
		world.setBlock(i + 2, j + 3, k + 3, Blockizer.CoralGreen);
		world.setBlock(i + 2, j + 3, k + 8, Blockizer.CoralGreen);
		world.setBlock(i + 2, j + 3, k + 10, Blockizer.CoralGreen);
		world.setBlock(i + 2, j + 4, k + 4, Blockizer.CoralGreen);
		world.setBlock(i + 2, j + 5, k + 8, Blockizer.CoralGreen);
		world.setBlock(i + 2, j + 6, k + 4, Blockizer.CoralGreen);
		world.setBlock(i + 2, j + 6, k + 6, Blockizer.CoralGreen);
		return true;
	}
}
