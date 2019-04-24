package net.nevermine.structures.barathos;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.nevermine.izer.Blockizer;

import java.util.Random;

public class BaronArch5 extends WorldGenerator {
	public BaronArch5() {
	}

	public boolean generate(World world, Random rand, int i, int j, int k) {

		world.setBlock(i + 0, j + 0, k + 0, Blockizer.BaronStone);
		world.setBlock(i + 0, j + 0, k + 4, Blockizer.BaronStone);
		world.setBlock(i + 0, j + 1, k + 0, Blockizer.BaronStone);
		world.setBlock(i + 0, j + 1, k + 4, Blockizer.BaronStone);
		world.setBlock(i + 0, j + 2, k + 0, Blockizer.BaronStone);
		world.setBlock(i + 0, j + 2, k + 4, Blockizer.BaronStone);
		world.setBlock(i + 0, j + 3, k + 0, Blockizer.BaronStone);
		world.setBlock(i + 0, j + 3, k + 1, Blockizer.BaronStone);
		world.setBlock(i + 0, j + 3, k + 2, Blockizer.BaronStone);
		world.setBlock(i + 0, j + 3, k + 3, Blockizer.BaronStone);
		world.setBlock(i + 0, j + 3, k + 4, Blockizer.BaronStone);

		return true;
	}
}