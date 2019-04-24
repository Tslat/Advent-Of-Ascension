package net.nevermine.structures.candyland;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.nevermine.izer.Blockizer;

import java.util.Random;

public class ChocolateBar4 extends WorldGenerator {

	public ChocolateBar4() {
	}

	public boolean generate(World world, Random rand, int i, int j, int k) {

		world.setBlock(i + 0, j + 0, k + 0, Blockizer.ChocolateBlockDark);
		world.setBlock(i + 0, j + 0, k + 1, Blockizer.ChocolateBlockDark);
		world.setBlock(i + 0, j + 0, k + 2, Blockizer.ChocolateBlockDark);
		world.setBlock(i + 0, j + 1, k + 0, Blockizer.ChocolateBlockDark);
		world.setBlock(i + 0, j + 1, k + 1, Blockizer.ChocolateBlockDark);
		world.setBlock(i + 0, j + 1, k + 2, Blockizer.ChocolateBlockDark);
		world.setBlock(i + 0, j + 2, k + 0, Blockizer.ChocolateBlockDark);
		world.setBlock(i + 0, j + 2, k + 1, Blockizer.ChocolateBlockDark);
		world.setBlock(i + 0, j + 2, k + 2, Blockizer.ChocolateBlockDark);
		world.setBlock(i + 0, j + 3, k + 0, Blockizer.ChocolateBlockDark);
		world.setBlock(i + 0, j + 3, k + 1, Blockizer.ChocolateBlockDark);
		world.setBlock(i + 0, j + 3, k + 2, Blockizer.ChocolateBlockDark);
		world.setBlock(i + 0, j + 4, k + 0, Blockizer.ChocolateBlockDark);
		world.setBlock(i + 0, j + 4, k + 1, Blockizer.ChocolateBlockDark);
		world.setBlock(i + 0, j + 4, k + 2, Blockizer.ChocolateBlockDark);
		world.setBlock(i + 0, j + 5, k + 0, Blockizer.ChocolateBlockDark);
		world.setBlock(i + 0, j + 5, k + 1, Blockizer.ChocolateBlockDark);
		world.setBlock(i + 0, j + 5, k + 2, Blockizer.ChocolateBlockDark);
		world.setBlock(i + 0, j + 6, k + 0, Blockizer.ChocolateBlockDark);
		world.setBlock(i + 0, j + 6, k + 1, Blockizer.ChocolateBlockDark);
		world.setBlock(i + 0, j + 6, k + 2, Blockizer.ChocolateBlockDark);

		return true;
	}
}