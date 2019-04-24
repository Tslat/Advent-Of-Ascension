package net.nevermine.structures.celeve;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.nevermine.izer.Blockizer;

import java.util.Random;

public class CelebulbPlant extends WorldGenerator {
	public boolean generate(final World world, final Random rand, final int i, final int j, final int k) {
		int y = j;
		while (y < j + 9) {
			world.setBlock(i, y, k, Blockizer.CelebulbsStem);
			if (rand.nextInt(3) == 2 || y == j + 5) {
				if (rand.nextInt(2) == 1) {
					world.setBlock(i, y + 1, k, Blockizer.CelebulbsTopGreen);
					break;
				}
				world.setBlock(i, y + 1, k, Blockizer.CelebulbsTopYellow);
				break;
			}
			else {
				++y;
			}
		}
		return true;
	}
}
