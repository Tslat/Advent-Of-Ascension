package net.nevermine.structures.deeplands;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.nevermine.izer.Blockizer;

import java.util.Random;

public class DeepFungus3 extends WorldGenerator {

	public DeepFungus3() {
	}

	public boolean generate(World world, Random rand, int i, int j, int k) {
		int y = j;
		int max = rand.nextInt(3) + 1;

		for (int m = 0; m < max; m++) {
			world.setBlock(i + 0, y + 4, k + 2, Blockizer.BlackMushroom);
			world.setBlock(i + 0, y + 4, k + 3, Blockizer.BlackMushroom);
			world.setBlock(i + 0, y + 4, k + 4, Blockizer.BlackMushroom);
			world.setBlock(i + 1, y + 4, k + 1, Blockizer.BlackMushroom);
			world.setBlock(i + 1, y + 4, k + 2, Blockizer.BlackMushroom);
			world.setBlock(i + 1, y + 4, k + 3, Blockizer.BlackMushroom);
			world.setBlock(i + 1, y + 4, k + 4, Blockizer.BlackMushroom);
			world.setBlock(i + 1, y + 4, k + 5, Blockizer.BlackMushroom);
			world.setBlock(i + 1, y + 7, k + 2, Blockizer.BlackMushroom);
			world.setBlock(i + 1, y + 7, k + 3, Blockizer.BlackMushroom);
			world.setBlock(i + 1, y + 7, k + 4, Blockizer.BlackMushroom);
			world.setBlock(i + 2, y + 4, k + 0, Blockizer.BlackMushroom);
			world.setBlock(i + 2, y + 4, k + 1, Blockizer.BlackMushroom);
			world.setBlock(i + 2, y + 4, k + 2, Blockizer.BlackMushroom);
			world.setBlock(i + 2, y + 4, k + 3, Blockizer.BlackMushroom);
			world.setBlock(i + 2, y + 4, k + 4, Blockizer.BlackMushroom);
			world.setBlock(i + 2, y + 4, k + 5, Blockizer.BlackMushroom);
			world.setBlock(i + 2, y + 4, k + 6, Blockizer.BlackMushroom);
			world.setBlock(i + 2, y + 7, k + 1, Blockizer.BlackMushroom);
			world.setBlock(i + 2, y + 7, k + 2, Blockizer.BlackMushroom);
			world.setBlock(i + 2, y + 7, k + 3, Blockizer.BlackMushroom);
			world.setBlock(i + 2, y + 7, k + 4, Blockizer.BlackMushroom);
			world.setBlock(i + 2, y + 7, k + 5, Blockizer.BlackMushroom);
			world.setBlock(i + 3, y + 0, k + 3, Blockizer.BlackMushroomStem);
			world.setBlock(i + 3, y + 1, k + 3, Blockizer.BlackMushroomStem);
			world.setBlock(i + 3, y + 2, k + 3, Blockizer.BlackMushroomStem);
			world.setBlock(i + 3, y + 3, k + 3, Blockizer.BlackMushroomStem);
			world.setBlock(i + 3, y + 4, k + 0, Blockizer.BlackMushroom);
			world.setBlock(i + 3, y + 4, k + 1, Blockizer.BlackMushroom);
			world.setBlock(i + 3, y + 4, k + 2, Blockizer.BlackMushroom);
			world.setBlock(i + 3, y + 4, k + 3, Blockizer.BlackMushroomStem);
			world.setBlock(i + 3, y + 4, k + 4, Blockizer.BlackMushroom);
			world.setBlock(i + 3, y + 4, k + 5, Blockizer.BlackMushroom);
			world.setBlock(i + 3, y + 4, k + 6, Blockizer.BlackMushroom);
			world.setBlock(i + 3, y + 5, k + 3, Blockizer.BlackMushroomStem);
			world.setBlock(i + 3, y + 6, k + 3, Blockizer.BlackMushroomStem);
			world.setBlock(i + 3, y + 7, k + 1, Blockizer.BlackMushroom);
			world.setBlock(i + 3, y + 7, k + 2, Blockizer.BlackMushroom);
			world.setBlock(i + 3, y + 7, k + 3, Blockizer.BlackMushroomStem);
			world.setBlock(i + 3, y + 7, k + 4, Blockizer.BlackMushroom);
			world.setBlock(i + 3, y + 7, k + 5, Blockizer.BlackMushroom);
			world.setBlock(i + 4, y + 4, k + 0, Blockizer.BlackMushroom);
			world.setBlock(i + 4, y + 4, k + 1, Blockizer.BlackMushroom);
			world.setBlock(i + 4, y + 4, k + 2, Blockizer.BlackMushroom);
			world.setBlock(i + 4, y + 4, k + 3, Blockizer.BlackMushroom);
			world.setBlock(i + 4, y + 4, k + 4, Blockizer.BlackMushroom);
			world.setBlock(i + 4, y + 4, k + 5, Blockizer.BlackMushroom);
			world.setBlock(i + 4, y + 4, k + 6, Blockizer.BlackMushroom);
			world.setBlock(i + 4, y + 7, k + 1, Blockizer.BlackMushroom);
			world.setBlock(i + 4, y + 7, k + 2, Blockizer.BlackMushroom);
			world.setBlock(i + 4, y + 7, k + 3, Blockizer.BlackMushroom);
			world.setBlock(i + 4, y + 7, k + 4, Blockizer.BlackMushroom);
			world.setBlock(i + 4, y + 7, k + 5, Blockizer.BlackMushroom);
			world.setBlock(i + 5, y + 4, k + 1, Blockizer.BlackMushroom);
			world.setBlock(i + 5, y + 4, k + 2, Blockizer.BlackMushroom);
			world.setBlock(i + 5, y + 4, k + 3, Blockizer.BlackMushroom);
			world.setBlock(i + 5, y + 4, k + 4, Blockizer.BlackMushroom);
			world.setBlock(i + 5, y + 4, k + 5, Blockizer.BlackMushroom);
			world.setBlock(i + 5, y + 7, k + 2, Blockizer.BlackMushroom);
			world.setBlock(i + 5, y + 7, k + 3, Blockizer.BlackMushroom);
			world.setBlock(i + 5, y + 7, k + 4, Blockizer.BlackMushroom);
			world.setBlock(i + 6, y + 4, k + 2, Blockizer.BlackMushroom);
			world.setBlock(i + 6, y + 4, k + 3, Blockizer.BlackMushroom);
			world.setBlock(i + 6, y + 4, k + 4, Blockizer.BlackMushroom);
			y += 8;
		}

		return true;
	}
}