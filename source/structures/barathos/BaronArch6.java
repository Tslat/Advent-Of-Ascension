package net.nevermine.structures.barathos;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.nevermine.izer.Blockizer;

import java.util.Random;

public class BaronArch6 extends WorldGenerator {

	public BaronArch6() {
	}

	public boolean generate(World world, Random rand, int i, int j, int k) {

		world.setBlock(i + 0, j + 0, k + 0, Blockizer.BaronStone);
		world.setBlock(i + 0, j + 0, k + 3, Blockizer.BaronStone);
		world.setBlock(i + 0, j + 1, k + 0, Blockizer.BaronStone);
		world.setBlock(i + 0, j + 1, k + 3, Blockizer.BaronStone);
		world.setBlock(i + 0, j + 2, k + 0, Blockizer.BaronStone);
		world.setBlock(i + 0, j + 2, k + 1, Blockizer.BaronStone);
		world.setBlock(i + 0, j + 2, k + 2, Blockizer.BaronStone);
		world.setBlock(i + 0, j + 2, k + 3, Blockizer.BaronStone);

		return true;
	}
}