package net.nevermine.structures.lborean;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.nevermine.izer.Blockizer;

import java.util.Random;

public class Coral1 extends WorldGenerator {
	public boolean generate(final World world, final Random rand, final int i, final int j, final int k) {
		if (world.getBlock(i + 3, j - 1, k + 3) != Blockizer.GrassBorean) {
			return false;
		}
		world.setBlock(i + 0, j + 3, k + 3, Blockizer.CoralGreen);
		world.setBlock(i + 0, j + 4, k + 3, Blockizer.CoralGreen);
		world.setBlock(i + 0, j + 7, k + 3, Blockizer.CoralGreen);
		world.setBlock(i + 0, j + 8, k + 3, Blockizer.CoralGreen);
		world.setBlock(i + 0, j + 10, k + 3, Blockizer.CoralGreen);
		world.setBlock(i + 0, j + 13, k + 3, Blockizer.CoralGreen);
		world.setBlock(i + 1, j + 4, k + 3, Blockizer.CoralGreen);
		world.setBlock(i + 1, j + 5, k + 3, Blockizer.CoralGreen);
		world.setBlock(i + 1, j + 7, k + 3, Blockizer.CoralGreen);
		world.setBlock(i + 1, j + 9, k + 3, Blockizer.CoralGreen);
		world.setBlock(i + 1, j + 10, k + 3, Blockizer.CoralGreen);
		world.setBlock(i + 1, j + 13, k + 3, Blockizer.CoralGreen);
		world.setBlock(i + 1, j + 14, k + 3, Blockizer.CoralGreen);
		world.setBlock(i + 2, j + 4, k + 3, Blockizer.CoralGreen);
		world.setBlock(i + 2, j + 7, k + 3, Blockizer.CoralGreen);
		world.setBlock(i + 2, j + 10, k + 3, Blockizer.CoralGreen);
		world.setBlock(i + 2, j + 13, k + 3, Blockizer.CoralGreen);
		world.setBlock(i + 3, j + 0, k + 3, Blockizer.CoralGreen);
		world.setBlock(i + 3, j - 1, k + 3, Blockizer.CoralGreen);
		world.setBlock(i + 3, j - 2, k + 3, Blockizer.CoralGreen);
		world.setBlock(i + 3, j + 1, k + 3, Blockizer.CoralGreen);
		world.setBlock(i + 3, j + 2, k + 3, Blockizer.CoralGreen);
		world.setBlock(i + 3, j + 2, k + 6, Blockizer.CoralGreen);
		world.setBlock(i + 3, j + 3, k + 0, Blockizer.CoralGreen);
		world.setBlock(i + 3, j + 3, k + 1, Blockizer.CoralGreen);
		world.setBlock(i + 3, j + 3, k + 2, Blockizer.CoralGreen);
		world.setBlock(i + 3, j + 3, k + 3, Blockizer.CoralGreen);
		world.setBlock(i + 3, j + 3, k + 4, Blockizer.CoralGreen);
		world.setBlock(i + 3, j + 3, k + 5, Blockizer.CoralGreen);
		world.setBlock(i + 3, j + 3, k + 6, Blockizer.CoralGreen);
		world.setBlock(i + 3, j + 4, k + 1, Blockizer.CoralGreen);
		world.setBlock(i + 3, j + 4, k + 3, Blockizer.CoralGreen);
		world.setBlock(i + 3, j + 4, k + 5, Blockizer.CoralGreen);
		world.setBlock(i + 3, j + 5, k + 3, Blockizer.CoralGreen);
		world.setBlock(i + 3, j + 6, k + 1, Blockizer.CoralGreen);
		world.setBlock(i + 3, j + 6, k + 3, Blockizer.CoralGreen);
		world.setBlock(i + 3, j + 6, k + 5, Blockizer.CoralGreen);
		world.setBlock(i + 3, j + 7, k + 0, Blockizer.CoralGreen);
		world.setBlock(i + 3, j + 7, k + 1, Blockizer.CoralGreen);
		world.setBlock(i + 3, j + 7, k + 2, Blockizer.CoralGreen);
		world.setBlock(i + 3, j + 7, k + 3, Blockizer.CoralGreen);
		world.setBlock(i + 3, j + 7, k + 4, Blockizer.CoralGreen);
		world.setBlock(i + 3, j + 7, k + 5, Blockizer.CoralGreen);
		world.setBlock(i + 3, j + 7, k + 6, Blockizer.CoralGreen);
		world.setBlock(i + 3, j + 8, k + 0, Blockizer.CoralGreen);
		world.setBlock(i + 3, j + 8, k + 3, Blockizer.CoralGreen);
		world.setBlock(i + 3, j + 8, k + 6, Blockizer.CoralGreen);
		world.setBlock(i + 3, j + 9, k + 1, Blockizer.CoralGreen);
		world.setBlock(i + 3, j + 9, k + 3, Blockizer.CoralGreen);
		world.setBlock(i + 3, j + 10, k + 0, Blockizer.CoralGreen);
		world.setBlock(i + 3, j + 10, k + 1, Blockizer.CoralGreen);
		world.setBlock(i + 3, j + 10, k + 2, Blockizer.CoralGreen);
		world.setBlock(i + 3, j + 10, k + 3, Blockizer.CoralGreen);
		world.setBlock(i + 3, j + 10, k + 5, Blockizer.CoralGreen);
		world.setBlock(i + 3, j + 11, k + 3, Blockizer.CoralGreen);
		world.setBlock(i + 3, j + 11, k + 4, Blockizer.CoralGreen);
		world.setBlock(i + 3, j + 11, k + 5, Blockizer.CoralGreen);
		world.setBlock(i + 3, j + 11, k + 6, Blockizer.CoralGreen);
		world.setBlock(i + 3, j + 12, k + 1, Blockizer.CoralGreen);
		world.setBlock(i + 3, j + 12, k + 3, Blockizer.CoralGreen);
		world.setBlock(i + 3, j + 12, k + 6, Blockizer.CoralGreen);
		world.setBlock(i + 3, j + 13, k + 1, Blockizer.CoralGreen);
		world.setBlock(i + 3, j + 13, k + 2, Blockizer.CoralGreen);
		world.setBlock(i + 3, j + 13, k + 3, Blockizer.CoralGreen);
		world.setBlock(i + 3, j + 13, k + 5, Blockizer.CoralGreen);
		world.setBlock(i + 3, j + 14, k + 3, Blockizer.CoralGreen);
		world.setBlock(i + 3, j + 14, k + 4, Blockizer.CoralGreen);
		world.setBlock(i + 3, j + 14, k + 5, Blockizer.CoralGreen);
		world.setBlock(i + 3, j + 15, k + 3, Blockizer.CoralGreen);
		world.setBlock(i + 4, j + 5, k + 3, Blockizer.CoralGreen);
		world.setBlock(i + 4, j + 7, k + 3, Blockizer.CoralGreen);
		world.setBlock(i + 4, j + 10, k + 3, Blockizer.CoralGreen);
		world.setBlock(i + 4, j + 14, k + 3, Blockizer.CoralGreen);
		world.setBlock(i + 5, j + 4, k + 3, Blockizer.CoralGreen);
		world.setBlock(i + 5, j + 5, k + 3, Blockizer.CoralGreen);
		world.setBlock(i + 5, j + 9, k + 3, Blockizer.CoralGreen);
		world.setBlock(i + 5, j + 10, k + 3, Blockizer.CoralGreen);
		world.setBlock(i + 5, j + 13, k + 3, Blockizer.CoralGreen);
		world.setBlock(i + 5, j + 14, k + 3, Blockizer.CoralGreen);
		world.setBlock(i + 6, j + 5, k + 3, Blockizer.CoralGreen);
		world.setBlock(i + 6, j + 10, k + 3, Blockizer.CoralGreen);
		world.setBlock(i + 6, j + 14, k + 3, Blockizer.CoralGreen);
		world.setBlock(i + 6, j + 15, k + 3, Blockizer.CoralGreen);
		return true;
	}
}
