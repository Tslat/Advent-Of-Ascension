package net.nevermine.structures.lborean;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.nevermine.izer.Blockizer;

import java.util.Random;

public class Coral7 extends WorldGenerator {

	public Coral7() {
	}

	public boolean generate(World world, Random rand, int i, int j, int k) {
		if (world.getBlock(i + 1, j - 1, k + 10) == Blocks.air || world.getBlock(i + 1, j - 1, k + 1) == Blocks.air) {
			return false;
		}
		else {
			world.setBlock(i + 0, j + 2, k + 1, Blockizer.CoralPink);
			world.setBlock(i + 0, j + 3, k + 10, Blockizer.CoralPink);
			world.setBlock(i + 0, j + 4, k + 4, Blockizer.CoralPink);
			world.setBlock(i + 0, j + 4, k + 8, Blockizer.CoralPink);
			world.setBlock(i + 0, j + 6, k + 5, Blockizer.CoralPink);
			world.setBlock(i + 1, j + 0, k + 1, Blockizer.CoralPink);
			world.setBlock(i + 1, j - 1, k + 1, Blockizer.CoralPink);
			world.setBlock(i + 1, j - 2, k + 1, Blockizer.CoralPink);
			world.setBlock(i + 1, j + 0, k + 10, Blockizer.CoralPink);
			world.setBlock(i + 1, j - 1, k + 10, Blockizer.CoralPink);
			world.setBlock(i + 1, j - 2, k + 10, Blockizer.CoralPink);
			world.setBlock(i + 1, j + 1, k + 0, Blockizer.CoralPink);
			world.setBlock(i + 1, j + 1, k + 1, Blockizer.CoralPink);
			world.setBlock(i + 1, j + 1, k + 10, Blockizer.CoralPink);
			world.setBlock(i + 1, j + 2, k + 1, Blockizer.CoralPink);
			world.setBlock(i + 1, j + 2, k + 3, Blockizer.CoralPink);
			world.setBlock(i + 1, j + 2, k + 10, Blockizer.CoralPink);
			world.setBlock(i + 1, j + 2, k + 11, Blockizer.CoralPink);
			world.setBlock(i + 1, j + 3, k + 0, Blockizer.CoralPink);
			world.setBlock(i + 1, j + 3, k + 1, Blockizer.CoralPink);
			world.setBlock(i + 1, j + 3, k + 2, Blockizer.CoralPink);
			world.setBlock(i + 1, j + 3, k + 3, Blockizer.CoralPink);
			world.setBlock(i + 1, j + 3, k + 4, Blockizer.CoralPink);
			world.setBlock(i + 1, j + 3, k + 8, Blockizer.CoralPink);
			world.setBlock(i + 1, j + 3, k + 9, Blockizer.CoralPink);
			world.setBlock(i + 1, j + 3, k + 10, Blockizer.CoralPink);
			world.setBlock(i + 1, j + 4, k + 2, Blockizer.CoralPink);
			world.setBlock(i + 1, j + 4, k + 4, Blockizer.CoralPink);
			world.setBlock(i + 1, j + 4, k + 5, Blockizer.CoralPink);
			world.setBlock(i + 1, j + 4, k + 7, Blockizer.CoralPink);
			world.setBlock(i + 1, j + 4, k + 8, Blockizer.CoralPink);
			world.setBlock(i + 1, j + 4, k + 10, Blockizer.CoralPink);
			world.setBlock(i + 1, j + 5, k + 3, Blockizer.CoralPink);
			world.setBlock(i + 1, j + 5, k + 4, Blockizer.CoralPink);
			world.setBlock(i + 1, j + 5, k + 8, Blockizer.CoralPink);
			world.setBlock(i + 1, j + 6, k + 4, Blockizer.CoralPink);
			world.setBlock(i + 1, j + 6, k + 5, Blockizer.CoralPink);
			world.setBlock(i + 1, j + 6, k + 6, Blockizer.CoralPink);
			world.setBlock(i + 1, j + 6, k + 7, Blockizer.CoralPink);
			world.setBlock(i + 1, j + 6, k + 8, Blockizer.CoralPink);
			world.setBlock(i + 1, j + 6, k + 9, Blockizer.CoralPink);
			world.setBlock(i + 1, j + 7, k + 6, Blockizer.CoralPink);
			world.setBlock(i + 1, j + 7, k + 8, Blockizer.CoralPink);
			world.setBlock(i + 2, j + 1, k + 10, Blockizer.CoralPink);
			world.setBlock(i + 2, j + 3, k + 1, Blockizer.CoralPink);
			world.setBlock(i + 2, j + 3, k + 3, Blockizer.CoralPink);
			world.setBlock(i + 2, j + 3, k + 8, Blockizer.CoralPink);
			world.setBlock(i + 2, j + 3, k + 10, Blockizer.CoralPink);
			world.setBlock(i + 2, j + 4, k + 4, Blockizer.CoralPink);
			world.setBlock(i + 2, j + 5, k + 8, Blockizer.CoralPink);
			world.setBlock(i + 2, j + 6, k + 4, Blockizer.CoralPink);
			world.setBlock(i + 2, j + 6, k + 6, Blockizer.CoralPink);

			return true;
		}
	}
}