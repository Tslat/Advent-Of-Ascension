package net.nevermine.structures.candyland;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.nevermine.izer.Blockizer;

import java.util.Random;

public class ChocolateBar6 extends WorldGenerator {

	public ChocolateBar6() {
	}

	public boolean generate(World world, Random rand, int i, int j, int k) {

		world.setBlock(i + 0, j + 0, k + 0, Blockizer.ChocolateBlockWhite);
		world.setBlock(i + 0, j + 1, k + 0, Blockizer.ChocolateBlockWhite);
		world.setBlock(i + 0, j + 2, k + 0, Blockizer.ChocolateBlockWhite);
		world.setBlock(i + 0, j + 3, k + 0, Blockizer.ChocolateBlockWhite);
		world.setBlock(i + 0, j + 4, k + 0, Blockizer.ChocolateBlockWhite);
		world.setBlock(i + 0, j + 5, k + 0, Blockizer.ChocolateBlockWhite);
		world.setBlock(i + 0, j + 6, k + 0, Blockizer.ChocolateBlockWhite);
		world.setBlock(i + 1, j + 0, k + 0, Blockizer.ChocolateBlockWhite);
		world.setBlock(i + 1, j + 1, k + 0, Blockizer.ChocolateBlockWhite);
		world.setBlock(i + 1, j + 2, k + 0, Blockizer.ChocolateBlockWhite);
		world.setBlock(i + 1, j + 3, k + 0, Blockizer.ChocolateBlockWhite);
		world.setBlock(i + 1, j + 4, k + 0, Blockizer.ChocolateBlockWhite);
		world.setBlock(i + 1, j + 5, k + 0, Blockizer.ChocolateBlockWhite);
		world.setBlock(i + 1, j + 6, k + 0, Blockizer.ChocolateBlockWhite);
		world.setBlock(i + 2, j + 0, k + 0, Blockizer.ChocolateBlockWhite);
		world.setBlock(i + 2, j + 1, k + 0, Blockizer.ChocolateBlockWhite);
		world.setBlock(i + 2, j + 2, k + 0, Blockizer.ChocolateBlockWhite);
		world.setBlock(i + 2, j + 3, k + 0, Blockizer.ChocolateBlockWhite);
		world.setBlock(i + 2, j + 4, k + 0, Blockizer.ChocolateBlockWhite);
		world.setBlock(i + 2, j + 5, k + 0, Blockizer.ChocolateBlockWhite);
		world.setBlock(i + 2, j + 6, k + 0, Blockizer.ChocolateBlockWhite);

		return true;
	}
}