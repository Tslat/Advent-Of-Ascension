package net.nevermine.structures.abyss;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.nevermine.izer.Blockizer;

import java.util.Random;

public class Shadicles extends WorldGenerator {

	public Shadicles() {
	}

	public boolean generate(World world, Random rand, int i, int j, int k) {
		for (int y = j; y < j + 5; y++) {
			world.setBlock(i, y, k, Blockizer.Shadicles);
			if ((rand.nextInt(3) == 2) || y == j + 4) {
				world.setBlock(i, y + 1, k, Blockizer.ShadiclesTop);
				break;
			}
		}
		return true;
	}
}