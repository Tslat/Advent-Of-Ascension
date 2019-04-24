package net.nevermine.structures.dustopia;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.nevermine.izer.Blockizer;

import java.util.Random;

public class DawnBulb extends WorldGenerator {

	public DawnBulb() {
	}

	public boolean generate(World world, Random rand, int i, int j, int k) {
		for (int y = j; y < j + 10; y++) {
			world.setBlock(i, y, k, Blockizer.DawnStocks);
			if (((rand.nextInt(4) == 2) && y >= j + 6) || y == j + 9) {
				world.setBlock(i, y + 1, k, Blockizer.DawnBulb);
				break;
			}
		}

		return true;
	}
}