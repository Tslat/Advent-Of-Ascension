package net.nevermine.structures.candyland;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.nevermine.izer.Blockizer;

import java.util.Random;

public class CandyTube extends WorldGenerator {

	public CandyTube() {
	}

	public boolean generate(World world, Random rand, int i, int j, int k) {
		for (int y = j; y < j + 5; y++) {
			world.setBlock(i, y, k, Blockizer.PlasticTube);
			if ((rand.nextInt(2) == 1) || y == j + 2) {
				world.setBlock(i, y + 1, k, Blockizer.CandyTube);
				break;
			}
		}
		return true;
	}
}