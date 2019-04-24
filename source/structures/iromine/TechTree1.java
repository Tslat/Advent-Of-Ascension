package net.nevermine.structures.iromine;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.nevermine.izer.Blockizer;

import java.util.Random;

public class TechTree1 extends WorldGenerator {

	public TechTree1() {
	}

	public boolean generate(World world, Random rand, int i, int j, int k) {

		world.setBlock(i + 0, j + 6, k + 1, Blockizer.WoodIromine);
		world.setBlock(i + 0, j + 7, k + 0, Blockizer.IroGlass);
		world.setBlock(i + 0, j + 7, k + 1, Blockizer.IroGlass);
		world.setBlock(i + 0, j + 7, k + 2, Blockizer.IroGlass);
		world.setBlock(i + 0, j + 8, k + 0, Blockizer.IroGlass);
		world.setBlock(i + 0, j + 8, k + 1, Blockizer.IroGlass);
		world.setBlock(i + 0, j + 8, k + 2, Blockizer.IroGlass);
		world.setBlock(i + 1, j + 0, k + 1, Blockizer.WoodIromine);
		world.setBlock(i + 1, j + 1, k + 1, Blockizer.WoodIromine);
		world.setBlock(i + 1, j + 2, k + 1, Blockizer.IroBox);
		world.setBlock(i + 1, j + 3, k + 1, Blockizer.IroBox);
		world.setBlock(i + 1, j + 4, k + 1, Blockizer.IroBox);
		world.setBlock(i + 1, j + 5, k + 1, Blockizer.IroBox);
		world.setBlock(i + 1, j + 6, k + 0, Blockizer.WoodIromine);
		world.setBlock(i + 1, j + 6, k + 1, Blockizer.WoodIromine);
		world.setBlock(i + 1, j + 6, k + 2, Blockizer.WoodIromine);
		world.setBlock(i + 1, j + 7, k + 0, Blockizer.IroGlass);
		world.setBlock(i + 1, j + 7, k + 1, Blockizer.IroGlass);
		world.setBlock(i + 1, j + 7, k + 2, Blockizer.IroGlass);
		world.setBlock(i + 1, j + 8, k + 0, Blockizer.IroGlass);
		world.setBlock(i + 1, j + 8, k + 1, Blockizer.IroGlass);
		world.setBlock(i + 1, j + 8, k + 2, Blockizer.IroGlass);
		world.setBlock(i + 2, j + 6, k + 1, Blockizer.WoodIromine);
		world.setBlock(i + 2, j + 7, k + 0, Blockizer.IroGlass);
		world.setBlock(i + 2, j + 7, k + 1, Blockizer.IroGlass);
		world.setBlock(i + 2, j + 7, k + 2, Blockizer.IroGlass);
		world.setBlock(i + 2, j + 8, k + 0, Blockizer.IroGlass);
		world.setBlock(i + 2, j + 8, k + 1, Blockizer.IroGlass);
		world.setBlock(i + 2, j + 8, k + 2, Blockizer.IroGlass);

		return true;
	}
}