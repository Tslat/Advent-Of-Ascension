package net.nevermine.structures.barathos;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.nevermine.izer.Blockizer;

import java.util.Random;

public class BaronArch3 extends WorldGenerator {

	public BaronArch3() {
	}

	public boolean generate(World world, Random rand, int i, int j, int k) {

		world.setBlock(i + 0, j + 0, k + 0, Blockizer.BaronStone);
		world.setBlock(i + 0, j + 1, k + 0, Blockizer.BaronStone);
		world.setBlock(i + 0, j + 2, k + 0, Blockizer.BaronStone);
		world.setBlock(i + 0, j + 3, k + 0, Blockizer.BaronStone);
		world.setBlock(i + 1, j + 3, k + 0, Blockizer.BaronStone);
		world.setBlock(i + 2, j + 3, k + 0, Blockizer.BaronStone);
		world.setBlock(i + 3, j + 3, k + 0, Blockizer.BaronStone);
		world.setBlock(i + 4, j + 0, k + 0, Blockizer.BaronStone);
		world.setBlock(i + 4, j + 1, k + 0, Blockizer.BaronStone);
		world.setBlock(i + 4, j + 2, k + 0, Blockizer.BaronStone);
		world.setBlock(i + 4, j + 3, k + 0, Blockizer.BaronStone);

		return true;
	}
}