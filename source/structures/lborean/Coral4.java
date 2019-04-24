package net.nevermine.structures.lborean;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.nevermine.izer.Blockizer;

import java.util.Random;

public class Coral4 extends WorldGenerator {
	public boolean generate(final World world, final Random rand, final int i, final int j, final int k) {
		if (world.getBlock(i + 1, j - 1, k + 1) != Blockizer.GrassBorean || world.getBlock(i + 10, j - 1, k + 1) != Blockizer.GrassBorean) {
			return false;
		}
		world.setBlock(i + 0, j + 2, k + 1, Blockizer.CoralOrange);
		world.setBlock(i + 1, j + 0, k + 1, Blockizer.CoralOrange);
		world.setBlock(i + 1, j - 1, k + 1, Blockizer.CoralOrange);
		world.setBlock(i + 1, j - 2, k + 1, Blockizer.CoralOrange);
		world.setBlock(i + 1, j + 1, k + 0, Blockizer.CoralOrange);
		world.setBlock(i + 1, j + 1, k + 1, Blockizer.CoralOrange);
		world.setBlock(i + 1, j + 2, k + 1, Blockizer.CoralOrange);
		world.setBlock(i + 1, j + 2, k + 2, Blockizer.CoralOrange);
		world.setBlock(i + 1, j + 3, k + 0, Blockizer.CoralOrange);
		world.setBlock(i + 1, j + 3, k + 1, Blockizer.CoralOrange);
		world.setBlock(i + 2, j + 1, k + 1, Blockizer.CoralOrange);
		world.setBlock(i + 2, j + 3, k + 1, Blockizer.CoralOrange);
		world.setBlock(i + 2, j + 3, k + 2, Blockizer.CoralOrange);
		world.setBlock(i + 2, j + 5, k + 1, Blockizer.CoralOrange);
		world.setBlock(i + 2, j + 6, k + 1, Blockizer.CoralOrange);
		world.setBlock(i + 3, j + 3, k + 0, Blockizer.CoralOrange);
		world.setBlock(i + 3, j + 3, k + 1, Blockizer.CoralOrange);
		world.setBlock(i + 3, j + 4, k + 1, Blockizer.CoralOrange);
		world.setBlock(i + 3, j + 4, k + 2, Blockizer.CoralOrange);
		world.setBlock(i + 3, j + 5, k + 0, Blockizer.CoralOrange);
		world.setBlock(i + 3, j + 5, k + 1, Blockizer.CoralOrange);
		world.setBlock(i + 4, j + 3, k + 1, Blockizer.CoralOrange);
		world.setBlock(i + 4, j + 5, k + 1, Blockizer.CoralOrange);
		world.setBlock(i + 4, j + 5, k + 2, Blockizer.CoralOrange);
		world.setBlock(i + 5, j + 5, k + 0, Blockizer.CoralOrange);
		world.setBlock(i + 5, j + 5, k + 1, Blockizer.CoralOrange);
		world.setBlock(i + 5, j + 6, k + 1, Blockizer.CoralOrange);
		world.setBlock(i + 6, j + 5, k + 1, Blockizer.CoralOrange);
		world.setBlock(i + 6, j + 5, k + 2, Blockizer.CoralOrange);
		world.setBlock(i + 7, j + 3, k + 1, Blockizer.CoralOrange);
		world.setBlock(i + 7, j + 5, k + 1, Blockizer.CoralOrange);
		world.setBlock(i + 7, j + 6, k + 1, Blockizer.CoralOrange);
		world.setBlock(i + 8, j + 2, k + 1, Blockizer.CoralOrange);
		world.setBlock(i + 8, j + 3, k + 1, Blockizer.CoralOrange);
		world.setBlock(i + 8, j + 4, k + 0, Blockizer.CoralOrange);
		world.setBlock(i + 8, j + 4, k + 1, Blockizer.CoralOrange);
		world.setBlock(i + 8, j + 4, k + 2, Blockizer.CoralOrange);
		world.setBlock(i + 8, j + 5, k + 1, Blockizer.CoralOrange);
		world.setBlock(i + 9, j + 3, k + 1, Blockizer.CoralOrange);
		world.setBlock(i + 10, j + 0, k + 1, Blockizer.CoralOrange);
		world.setBlock(i + 10, j - 1, k + 1, Blockizer.CoralOrange);
		world.setBlock(i + 10, j - 2, k + 1, Blockizer.CoralOrange);
		world.setBlock(i + 10, j + 1, k + 0, Blockizer.CoralOrange);
		world.setBlock(i + 10, j + 1, k + 1, Blockizer.CoralOrange);
		world.setBlock(i + 10, j + 1, k + 2, Blockizer.CoralOrange);
		world.setBlock(i + 10, j + 2, k + 1, Blockizer.CoralOrange);
		world.setBlock(i + 10, j + 3, k + 0, Blockizer.CoralOrange);
		world.setBlock(i + 10, j + 3, k + 1, Blockizer.CoralOrange);
		world.setBlock(i + 10, j + 3, k + 2, Blockizer.CoralOrange);
		world.setBlock(i + 10, j + 4, k + 1, Blockizer.CoralOrange);
		world.setBlock(i + 11, j + 1, k + 1, Blockizer.CoralOrange);
		world.setBlock(i + 11, j + 3, k + 1, Blockizer.CoralOrange);
		return true;
	}
}
