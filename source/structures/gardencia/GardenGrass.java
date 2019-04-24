package net.nevermine.structures.gardencia;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.nevermine.izer.Blockizer;

import java.util.Random;

public class GardenGrass extends WorldGenerator {
	public boolean generate(final World world, final Random rand, final int i, final int j, final int k) {
		for (int y = j; y < j + 9; ++y) {
			world.setBlock(i, y, k, Blockizer.GardenGrass);
			if (rand.nextInt(2) == 1) {
				break;
			}
			if (y == j + 4) {
				break;
			}
		}
		return true;
	}
}
