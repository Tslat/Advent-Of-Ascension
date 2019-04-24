package net.nevermine.structures.lborean;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.nevermine.izer.Blockizer;

import java.util.Random;

public class Coral8 extends WorldGenerator {

	public Coral8() {
	}

	public boolean generate(World world, Random rand, int i, int j, int k) {
		if (world.getBlock(i + 1, j - 1, k + 1) == Blocks.air && world.getBlock(i + 10, j - 1, k + 1) == Blocks.air) {
			return false;
		}
		else {

			world.setBlock(i + 0, j + 2, k + 1, Blockizer.CoralPink);
			world.setBlock(i + 1, j + 0, k + 1, Blockizer.CoralPink);
			world.setBlock(i + 1, j - 1, k + 1, Blockizer.CoralPink);
			world.setBlock(i + 1, j - 2, k + 1, Blockizer.CoralPink);
			world.setBlock(i + 1, j + 1, k + 0, Blockizer.CoralPink);
			world.setBlock(i + 1, j + 1, k + 1, Blockizer.CoralPink);
			world.setBlock(i + 1, j + 2, k + 1, Blockizer.CoralPink);
			world.setBlock(i + 1, j + 2, k + 2, Blockizer.CoralPink);
			world.setBlock(i + 1, j + 3, k + 0, Blockizer.CoralPink);
			world.setBlock(i + 1, j + 3, k + 1, Blockizer.CoralPink);
			world.setBlock(i + 2, j + 1, k + 1, Blockizer.CoralPink);
			world.setBlock(i + 2, j + 3, k + 1, Blockizer.CoralPink);
			world.setBlock(i + 2, j + 3, k + 2, Blockizer.CoralPink);
			world.setBlock(i + 2, j + 5, k + 1, Blockizer.CoralPink);
			world.setBlock(i + 2, j + 6, k + 1, Blockizer.CoralPink);
			world.setBlock(i + 3, j + 3, k + 0, Blockizer.CoralPink);
			world.setBlock(i + 3, j + 3, k + 1, Blockizer.CoralPink);
			world.setBlock(i + 3, j + 4, k + 1, Blockizer.CoralPink);
			world.setBlock(i + 3, j + 4, k + 2, Blockizer.CoralPink);
			world.setBlock(i + 3, j + 5, k + 0, Blockizer.CoralPink);
			world.setBlock(i + 3, j + 5, k + 1, Blockizer.CoralPink);
			world.setBlock(i + 4, j + 3, k + 1, Blockizer.CoralPink);
			world.setBlock(i + 4, j + 5, k + 1, Blockizer.CoralPink);
			world.setBlock(i + 4, j + 5, k + 2, Blockizer.CoralPink);
			world.setBlock(i + 5, j + 5, k + 0, Blockizer.CoralPink);
			world.setBlock(i + 5, j + 5, k + 1, Blockizer.CoralPink);
			world.setBlock(i + 5, j + 6, k + 1, Blockizer.CoralPink);
			world.setBlock(i + 6, j + 5, k + 1, Blockizer.CoralPink);
			world.setBlock(i + 6, j + 5, k + 2, Blockizer.CoralPink);
			world.setBlock(i + 7, j + 3, k + 1, Blockizer.CoralPink);
			world.setBlock(i + 7, j + 5, k + 1, Blockizer.CoralPink);
			world.setBlock(i + 7, j + 6, k + 1, Blockizer.CoralPink);
			world.setBlock(i + 8, j + 2, k + 1, Blockizer.CoralPink);
			world.setBlock(i + 8, j + 3, k + 1, Blockizer.CoralPink);
			world.setBlock(i + 8, j + 4, k + 0, Blockizer.CoralPink);
			world.setBlock(i + 8, j + 4, k + 1, Blockizer.CoralPink);
			world.setBlock(i + 8, j + 4, k + 2, Blockizer.CoralPink);
			world.setBlock(i + 8, j + 5, k + 1, Blockizer.CoralPink);
			world.setBlock(i + 9, j + 3, k + 1, Blockizer.CoralPink);
			world.setBlock(i + 10, j + 0, k + 1, Blockizer.CoralPink);
			world.setBlock(i + 10, j - 1, k + 1, Blockizer.CoralPink);
			world.setBlock(i + 10, j - 2, k + 1, Blockizer.CoralPink);
			world.setBlock(i + 10, j + 1, k + 0, Blockizer.CoralPink);
			world.setBlock(i + 10, j + 1, k + 1, Blockizer.CoralPink);
			world.setBlock(i + 10, j + 1, k + 2, Blockizer.CoralPink);
			world.setBlock(i + 10, j + 2, k + 1, Blockizer.CoralPink);
			world.setBlock(i + 10, j + 3, k + 0, Blockizer.CoralPink);
			world.setBlock(i + 10, j + 3, k + 1, Blockizer.CoralPink);
			world.setBlock(i + 10, j + 3, k + 2, Blockizer.CoralPink);
			world.setBlock(i + 10, j + 4, k + 1, Blockizer.CoralPink);
			world.setBlock(i + 11, j + 1, k + 1, Blockizer.CoralPink);
			world.setBlock(i + 11, j + 3, k + 1, Blockizer.CoralPink);

			return true;
		}
	}
}