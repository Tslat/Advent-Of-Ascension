package net.nevermine.structures.gardencia;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.nevermine.izer.Blockizer;

import java.util.Random;

public class Flowertree extends WorldGenerator {
	public boolean generate(final World world, final Random rand, final int i, final int j, final int k) {
		if (world.getBlock(i + 4, j - 1, k + 3) != Blockizer.GrassGardencia && world.getBlock(i + 4, j - 1, k + 3) != Blockizer.DirtGardencia) {
			return false;
		}
		world.setBlock(i + 1, j + 4, k + 3, Blockizer.PedalsRose);
		world.setBlock(i + 1, j + 5, k + 2, Blockizer.PedalsRose);
		world.setBlock(i + 1, j + 5, k + 3, Blockizer.PedalsLightRose);
		world.setBlock(i + 1, j + 5, k + 4, Blockizer.PedalsRose);
		world.setBlock(i + 1, j + 6, k + 3, Blockizer.PedalsRose);
		world.setBlock(i + 1, j + 7, k + 3, Blockizer.PedalsRose);
		world.setBlock(i + 1, j + 8, k + 2, Blockizer.PedalsRose);
		world.setBlock(i + 1, j + 8, k + 3, Blockizer.PedalsLightRose);
		world.setBlock(i + 1, j + 8, k + 4, Blockizer.PedalsRose);
		world.setBlock(i + 1, j + 9, k + 3, Blockizer.PedalsRose);
		world.setBlock(i + 2, j + 5, k + 3, Blockizer.PlantStem);
		world.setBlock(i + 2, j + 8, k + 3, Blockizer.PlantStem);
		world.setBlock(i + 3, j + 3, k + 6, Blockizer.PedalsRose);
		world.setBlock(i + 3, j + 4, k + 0, Blockizer.PedalsRose);
		world.setBlock(i + 3, j + 5, k + 3, Blockizer.PlantStem);
		world.setBlock(i + 3, j + 8, k + 0, Blockizer.PedalsRose);
		world.setBlock(i + 3, j + 8, k + 3, Blockizer.PlantStem);
		world.setBlock(i + 3, j + 8, k + 6, Blockizer.PedalsRose);
		world.setBlock(i + 3, j + 11, k + 3, Blockizer.PedalsRose);
		world.setBlock(i + 4, j + 0, k + 3, Blockizer.PlantStem);
		world.setBlock(i + 4, j + 1, k + 3, Blockizer.PlantStem);
		world.setBlock(i + 4, j + 2, k + 3, Blockizer.PlantStem);
		world.setBlock(i + 4, j + 2, k + 6, Blockizer.PedalsRose);
		world.setBlock(i + 4, j + 3, k + 0, Blockizer.PedalsRose);
		world.setBlock(i + 4, j + 3, k + 3, Blockizer.PlantStem);
		world.setBlock(i + 4, j + 3, k + 4, Blockizer.PlantStem);
		world.setBlock(i + 4, j + 3, k + 5, Blockizer.PlantStem);
		world.setBlock(i + 4, j + 3, k + 6, Blockizer.PedalsLightRose);
		world.setBlock(i + 4, j + 4, k + 0, Blockizer.PedalsLightRose);
		world.setBlock(i + 4, j + 4, k + 1, Blockizer.PlantStem);
		world.setBlock(i + 4, j + 4, k + 2, Blockizer.PlantStem);
		world.setBlock(i + 4, j + 4, k + 3, Blockizer.PlantStem);
		world.setBlock(i + 4, j + 4, k + 6, Blockizer.PedalsRose);
		world.setBlock(i + 4, j + 5, k + 0, Blockizer.PedalsRose);
		world.setBlock(i + 4, j + 5, k + 3, Blockizer.PlantStem);
		world.setBlock(i + 4, j + 6, k + 3, Blockizer.PlantStem);
		world.setBlock(i + 4, j + 7, k + 0, Blockizer.PedalsRose);
		world.setBlock(i + 4, j + 7, k + 3, Blockizer.PlantStem);
		world.setBlock(i + 4, j + 7, k + 6, Blockizer.PedalsRose);
		world.setBlock(i + 4, j + 8, k + 0, Blockizer.PedalsLightRose);
		world.setBlock(i + 4, j + 8, k + 1, Blockizer.PlantStem);
		world.setBlock(i + 4, j + 8, k + 2, Blockizer.PlantStem);
		world.setBlock(i + 4, j + 8, k + 3, Blockizer.PlantStem);
		world.setBlock(i + 4, j + 8, k + 4, Blockizer.PlantStem);
		world.setBlock(i + 4, j + 8, k + 5, Blockizer.PlantStem);
		world.setBlock(i + 4, j + 8, k + 6, Blockizer.PedalsLightRose);
		world.setBlock(i + 4, j + 9, k + 0, Blockizer.PedalsRose);
		world.setBlock(i + 4, j + 9, k + 3, Blockizer.PlantStem);
		world.setBlock(i + 4, j + 9, k + 6, Blockizer.PedalsRose);
		world.setBlock(i + 4, j + 10, k + 3, Blockizer.PlantStem);
		world.setBlock(i + 4, j + 11, k + 2, Blockizer.PedalsRose);
		world.setBlock(i + 4, j + 11, k + 3, Blockizer.PedalsLightRose);
		world.setBlock(i + 4, j + 11, k + 4, Blockizer.PedalsRose);
		world.setBlock(i + 5, j + 2, k + 3, Blockizer.PlantStem);
		world.setBlock(i + 5, j + 3, k + 6, Blockizer.PedalsRose);
		world.setBlock(i + 5, j + 4, k + 0, Blockizer.PedalsRose);
		world.setBlock(i + 5, j + 6, k + 3, Blockizer.PlantStem);
		world.setBlock(i + 5, j + 8, k + 0, Blockizer.PedalsRose);
		world.setBlock(i + 5, j + 8, k + 6, Blockizer.PedalsRose);
		world.setBlock(i + 5, j + 11, k + 3, Blockizer.PedalsRose);
		world.setBlock(i + 6, j + 2, k + 3, Blockizer.PlantStem);
		world.setBlock(i + 6, j + 6, k + 3, Blockizer.PlantStem);
		world.setBlock(i + 7, j + 1, k + 3, Blockizer.PedalsRose);
		world.setBlock(i + 7, j + 2, k + 2, Blockizer.PedalsRose);
		world.setBlock(i + 7, j + 2, k + 3, Blockizer.PedalsLightRose);
		world.setBlock(i + 7, j + 2, k + 4, Blockizer.PedalsRose);
		world.setBlock(i + 7, j + 3, k + 3, Blockizer.PedalsRose);
		world.setBlock(i + 7, j + 5, k + 3, Blockizer.PedalsRose);
		world.setBlock(i + 7, j + 6, k + 2, Blockizer.PedalsRose);
		world.setBlock(i + 7, j + 6, k + 3, Blockizer.PedalsLightRose);
		world.setBlock(i + 7, j + 6, k + 4, Blockizer.PedalsRose);
		world.setBlock(i + 7, j + 7, k + 3, Blockizer.PedalsRose);
		return true;
	}
}
