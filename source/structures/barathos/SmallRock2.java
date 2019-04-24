package net.nevermine.structures.barathos;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.nevermine.izer.Blockizer;

import java.util.Random;

public class SmallRock2 extends WorldGenerator {
	public boolean generate(final World world, final Random rand, final int i, final int j, final int k) {
		if (world.getBlock(i + 1, j - 1, k + 3) != Blockizer.BaronGround) {
			return false;
		}
		world.setBlock(i + 0, j + 0, k + 0, Blockizer.BaronStone);
		world.setBlock(i + 0, j + 0, k + 1, Blockizer.BaronStone);
		world.setBlock(i + 0, j + 0, k + 2, Blockizer.BaronStone);
		world.setBlock(i + 0, j + 0, k + 3, Blockizer.BaronStone);
		world.setBlock(i + 0, j + 0, k + 4, Blockizer.BaronStone);
		world.setBlock(i + 0, j + 0, k + 5, Blockizer.BaronStone);
		world.setBlock(i + 0, j + 1, k + 0, Blockizer.BaronStone);
		world.setBlock(i + 0, j + 1, k + 1, Blockizer.BaronStone);
		world.setBlock(i + 0, j + 1, k + 2, Blockizer.BaronStone);
		world.setBlock(i + 0, j + 1, k + 3, Blockizer.BaronStone);
		world.setBlock(i + 0, j + 1, k + 4, Blockizer.BaronStone);
		world.setBlock(i + 0, j + 1, k + 5, Blockizer.BaronStone);
		world.setBlock(i + 1, j + 0, k + 0, Blockizer.BaronStone);
		world.setBlock(i + 1, j + 0, k + 1, Blockizer.BaronStone);
		world.setBlock(i + 1, j + 0, k + 2, Blockizer.BaronStone);
		world.setBlock(i + 1, j + 0, k + 3, Blockizer.BaronStone);
		world.setBlock(i + 1, j + 0, k + 4, Blockizer.BaronStone);
		world.setBlock(i + 1, j + 0, k + 5, Blockizer.BaronStone);
		world.setBlock(i + 1, j + 1, k + 0, Blockizer.BaronStone);
		world.setBlock(i + 1, j + 1, k + 1, Blockizer.BaronStone);
		world.setBlock(i + 1, j + 1, k + 2, Blockizer.BaronStone);
		world.setBlock(i + 1, j + 1, k + 3, Blockizer.BaronStone);
		world.setBlock(i + 1, j + 1, k + 4, Blockizer.BaronStone);
		world.setBlock(i + 1, j + 1, k + 5, Blockizer.BaronStone);
		return true;
	}
}
