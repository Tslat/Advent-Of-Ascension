package net.nevermine.structures.barathos;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.nevermine.izer.Blockizer;

import java.util.Random;

public class FlakeVine extends WorldGenerator {

	public FlakeVine() {
	}

	public boolean generate(World world, Random rand, int i, int j, int k) {
		for (int y = j; y < j + 4; y++) {
			world.setBlock(i, y, k, Blockizer.FlakeVine);
			if ((rand.nextInt(2) == 1) || y == j + 3) {
				world.setBlock(i, y + 1, k, Blockizer.FlakeVineTop);
				break;
			}
		}
		return true;
	}
}