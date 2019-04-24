package net.nevermine.structures.gardencia;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.nevermine.izer.Blockizer;

import java.util.Random;

public class MarshFern1 extends WorldGenerator {

	public MarshFern1() {
	}

	public boolean generate(World world, Random rand, int i, int j, int k) {

		int y = j;
		int max = rand.nextInt(3) + 1;

		for (int m = 0; m < max; m++) {
			world.setBlock(i + 0, y + 6, k + 0, Blockizer.PedalsOrange);
			world.setBlock(i + 0, y + 6, k + 1, Blockizer.PedalsOrange);
			world.setBlock(i + 0, y + 6, k + 5, Blockizer.PedalsOrange);
			world.setBlock(i + 0, y + 6, k + 6, Blockizer.PedalsOrange);
			world.setBlock(i + 1, y + 5, k + 1, Blockizer.PedalsOrange);
			world.setBlock(i + 1, y + 5, k + 2, Blockizer.PedalsOrange);
			world.setBlock(i + 1, y + 5, k + 4, Blockizer.PedalsOrange);
			world.setBlock(i + 1, y + 5, k + 5, Blockizer.PedalsOrange);
			world.setBlock(i + 1, y + 6, k + 0, Blockizer.PedalsOrange);
			world.setBlock(i + 1, y + 6, k + 6, Blockizer.PedalsOrange);
			world.setBlock(i + 2, y + 4, k + 2, Blockizer.PedalsOrange);
			world.setBlock(i + 2, y + 4, k + 3, Blockizer.PedalsOrange);
			world.setBlock(i + 2, y + 4, k + 4, Blockizer.PedalsOrange);
			world.setBlock(i + 2, y + 5, k + 1, Blockizer.PedalsOrange);
			world.setBlock(i + 2, y + 5, k + 5, Blockizer.PedalsOrange);
			world.setBlock(i + 3, y + 0, k + 3, Blockizer.PlantStem);
			world.setBlock(i + 3, y + 1, k + 3, Blockizer.PlantStem);
			world.setBlock(i + 3, y + 2, k + 3, Blockizer.PlantStem);
			world.setBlock(i + 3, y + 3, k + 3, Blockizer.PlantStem);
			world.setBlock(i + 3, y + 4, k + 2, Blockizer.PedalsOrange);
			world.setBlock(i + 3, y + 4, k + 3, Blockizer.PlantStem);
			world.setBlock(i + 3, y + 4, k + 4, Blockizer.PedalsOrange);
			world.setBlock(i + 3, y + 5, k + 3, Blockizer.PlantStem);
			world.setBlock(i + 3, y + 6, k + 3, Blockizer.PlantStem);
			world.setBlock(i + 4, y + 4, k + 2, Blockizer.PedalsOrange);
			world.setBlock(i + 4, y + 4, k + 3, Blockizer.PedalsOrange);
			world.setBlock(i + 4, y + 4, k + 4, Blockizer.PedalsOrange);
			world.setBlock(i + 4, y + 5, k + 1, Blockizer.PedalsOrange);
			world.setBlock(i + 4, y + 5, k + 5, Blockizer.PedalsOrange);
			world.setBlock(i + 5, y + 5, k + 1, Blockizer.PedalsOrange);
			world.setBlock(i + 5, y + 5, k + 2, Blockizer.PedalsOrange);
			world.setBlock(i + 5, y + 5, k + 4, Blockizer.PedalsOrange);
			world.setBlock(i + 5, y + 5, k + 5, Blockizer.PedalsOrange);
			world.setBlock(i + 5, y + 6, k + 0, Blockizer.PedalsOrange);
			world.setBlock(i + 5, y + 6, k + 6, Blockizer.PedalsOrange);
			world.setBlock(i + 6, y + 6, k + 0, Blockizer.PedalsOrange);
			world.setBlock(i + 6, y + 6, k + 1, Blockizer.PedalsOrange);
			world.setBlock(i + 6, y + 6, k + 5, Blockizer.PedalsOrange);
			world.setBlock(i + 6, y + 6, k + 6, Blockizer.PedalsOrange);
			y += 7;
		}

		return true;
	}
}