package net.nevermine.structures.barathos;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.nevermine.izer.Blockizer;

import java.util.Random;

public class SmallRock3 extends WorldGenerator {
	public boolean generate(final World world, final Random rand, final int i, final int j, final int k) {
		if (world.getBlock(i, j - 1, k + 1) != Blockizer.BaronGround) {
			return false;
		}
		world.setBlock(i + 0, j + 0, k + 0, Blockizer.BaronStone);
		world.setBlock(i + 0, j + 0, k + 1, Blockizer.BaronStone);
		world.setBlock(i + 1, j + 0, k + 0, Blockizer.BaronStone);
		world.setBlock(i + 1, j + 0, k + 1, Blockizer.BaronStone);
		world.setBlock(i + 2, j + 0, k + 0, Blockizer.BaronStone);
		world.setBlock(i + 2, j + 0, k + 1, Blockizer.BaronStone);
		world.setBlock(i + 3, j + 0, k + 0, Blockizer.BaronStone);
		world.setBlock(i + 3, j + 0, k + 1, Blockizer.BaronStone);
		return true;
	}
}
