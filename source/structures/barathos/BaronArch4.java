package net.nevermine.structures.barathos;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.nevermine.izer.Blockizer;

import java.util.Random;

public class BaronArch4 extends WorldGenerator {

	public BaronArch4() {
	}

	public boolean generate(World world, Random rand, int i, int j, int k) {

		world.setBlock(i + 0, j + 0, k + 0, Blockizer.BaronStone);
		world.setBlock(i + 0, j + 1, k + 0, Blockizer.BaronStone);
		world.setBlock(i + 0, j + 2, k + 0, Blockizer.BaronStone);
		world.setBlock(i + 1, j + 2, k + 0, Blockizer.BaronStone);
		world.setBlock(i + 2, j + 2, k + 0, Blockizer.BaronStone);
		world.setBlock(i + 3, j + 0, k + 0, Blockizer.BaronStone);
		world.setBlock(i + 3, j + 1, k + 0, Blockizer.BaronStone);
		world.setBlock(i + 3, j + 2, k + 0, Blockizer.BaronStone);

		return true;
	}
}