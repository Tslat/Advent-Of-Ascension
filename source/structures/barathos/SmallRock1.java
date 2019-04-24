package net.nevermine.structures.barathos;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.nevermine.izer.Blockizer;

import java.util.Random;

public class SmallRock1 extends WorldGenerator {
	public boolean generate(final World world, final Random rand, final int i, final int j, final int k) {
		if (world.getBlock(i + 3, j - 1, k) != Blockizer.BaronGround) {
			return false;
		}
		world.setBlock(i + 0, j + 0, k + 0, Blockizer.BaronStone);
		world.setBlock(i + 0, j + 0, k + 1, Blockizer.BaronStone);
		world.setBlock(i + 0, j + 1, k + 0, Blockizer.BaronStone);
		world.setBlock(i + 0, j + 1, k + 1, Blockizer.BaronStone);
		world.setBlock(i + 1, j + 0, k + 0, Blockizer.BaronStone);
		world.setBlock(i + 1, j + 0, k + 1, Blockizer.BaronStone);
		world.setBlock(i + 1, j + 1, k + 0, Blockizer.BaronStone);
		world.setBlock(i + 1, j + 1, k + 1, Blockizer.BaronStone);
		world.setBlock(i + 2, j + 0, k + 0, Blockizer.BaronStone);
		world.setBlock(i + 2, j + 0, k + 1, Blockizer.BaronStone);
		world.setBlock(i + 2, j + 1, k + 0, Blockizer.BaronStone);
		world.setBlock(i + 2, j + 1, k + 1, Blockizer.BaronStone);
		world.setBlock(i + 3, j + 0, k + 0, Blockizer.BaronStone);
		world.setBlock(i + 3, j + 0, k + 1, Blockizer.BaronStone);
		world.setBlock(i + 3, j + 1, k + 0, Blockizer.BaronStone);
		world.setBlock(i + 3, j + 1, k + 1, Blockizer.BaronStone);
		world.setBlock(i + 4, j + 0, k + 0, Blockizer.BaronStone);
		world.setBlock(i + 4, j + 0, k + 1, Blockizer.BaronStone);
		world.setBlock(i + 4, j + 1, k + 0, Blockizer.BaronStone);
		world.setBlock(i + 4, j + 1, k + 1, Blockizer.BaronStone);
		world.setBlock(i + 5, j + 0, k + 0, Blockizer.BaronStone);
		world.setBlock(i + 5, j + 0, k + 1, Blockizer.BaronStone);
		world.setBlock(i + 5, j + 1, k + 0, Blockizer.BaronStone);
		world.setBlock(i + 5, j + 1, k + 1, Blockizer.BaronStone);
		world.setBlock(i + 6, j + 0, k + 0, Blockizer.BaronStone);
		world.setBlock(i + 6, j + 0, k + 1, Blockizer.BaronStone);
		world.setBlock(i + 6, j + 1, k + 0, Blockizer.BaronStone);
		world.setBlock(i + 6, j + 1, k + 1, Blockizer.BaronStone);
		return true;
	}
}
