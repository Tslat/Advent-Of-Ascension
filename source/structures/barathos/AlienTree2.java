package net.nevermine.structures.barathos;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.nevermine.izer.Blockizer;

import java.util.Random;

public class AlienTree2 extends WorldGenerator {

	public AlienTree2() {
	}

	public boolean generate(World world, Random rand, int i, int j, int k) {

		world.setBlock(i + 0, j + 9, k + 1, Blockizer.LeavesOpollo);
		world.setBlock(i + 0, j + 10, k + 0, Blockizer.LeavesOpollo);
		world.setBlock(i + 0, j + 10, k + 1, Blockizer.LeavesOpollo);
		world.setBlock(i + 0, j + 10, k + 2, Blockizer.LeavesOpollo);
		world.setBlock(i + 0, j + 11, k + 1, Blockizer.LeavesOpollo);
		world.setBlock(i + 1, j + 0, k + 1, Blockizer.WoodOpollo);
		world.setBlock(i + 1, j + 1, k + 1, Blockizer.WoodOpollo);
		world.setBlock(i + 1, j + 2, k + 1, Blockizer.WoodOpollo);
		world.setBlock(i + 1, j + 3, k + 1, Blockizer.WoodOpollo);
		world.setBlock(i + 1, j + 4, k + 1, Blockizer.WoodOpollo);
		world.setBlock(i + 1, j + 5, k + 1, Blockizer.WoodOpollo);
		world.setBlock(i + 1, j + 6, k + 1, Blockizer.WoodOpollo);
		world.setBlock(i + 1, j + 7, k + 1, Blockizer.WoodOpollo);
		world.setBlock(i + 1, j + 8, k + 1, Blockizer.WoodOpollo);
		world.setBlock(i + 1, j + 9, k + 0, Blockizer.LeavesOpollo);
		world.setBlock(i + 1, j + 9, k + 1, Blockizer.LeavesOpollo);
		world.setBlock(i + 1, j + 9, k + 2, Blockizer.LeavesOpollo);
		world.setBlock(i + 1, j + 10, k + 0, Blockizer.LeavesOpollo);
		world.setBlock(i + 1, j + 10, k + 1, Blockizer.LeavesOpollo);
		world.setBlock(i + 1, j + 10, k + 2, Blockizer.LeavesOpollo);
		world.setBlock(i + 1, j + 11, k + 0, Blockizer.LeavesOpollo);
		world.setBlock(i + 1, j + 11, k + 1, Blockizer.LeavesOpollo);
		world.setBlock(i + 1, j + 11, k + 2, Blockizer.LeavesOpollo);
		world.setBlock(i + 2, j + 9, k + 1, Blockizer.LeavesOpollo);
		world.setBlock(i + 2, j + 10, k + 0, Blockizer.LeavesOpollo);
		world.setBlock(i + 2, j + 10, k + 1, Blockizer.LeavesOpollo);
		world.setBlock(i + 2, j + 10, k + 2, Blockizer.LeavesOpollo);
		world.setBlock(i + 2, j + 11, k + 1, Blockizer.LeavesOpollo);

		return true;
	}
}