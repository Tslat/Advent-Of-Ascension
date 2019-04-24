package net.nevermine.structures.abyss;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.nevermine.izer.Blockizer;

import java.util.Random;

public class BulbStock extends WorldGenerator {

	public BulbStock() {
	}

	public boolean generate(World world, Random rand, int i, int j, int k) {
		for (int y = j; y < j + 3; y++) {
			world.setBlock(i, y, k, Blockizer.BulbStock);
			if ((rand.nextInt(3) == 1) || y == j + 2) {
				world.setBlock(i, y + 1, k, Blockizer.BulbStockTop);
				break;
			}
		}
		return true;
	}
}