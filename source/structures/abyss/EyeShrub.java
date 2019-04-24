package net.nevermine.structures.abyss;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.nevermine.izer.Blockizer;

import java.util.Random;

public class EyeShrub extends WorldGenerator {
	public boolean generate(final World world, final Random rand, final int i, final int j, final int k) {
		for (int y = j; y < j + 9; ++y) {
			world.setBlock(i, y, k, Blockizer.EyeShrubStem);
			if (rand.nextInt(4) == 3 || y == j + 8) {
				world.setBlock(i, y + 1, k, Blockizer.EyeShrub);
				break;
			}
		}
		return true;
	}
}
