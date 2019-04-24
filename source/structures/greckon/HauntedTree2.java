package net.nevermine.structures.greckon;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.nevermine.izer.Blockizer;

import java.util.Random;

public class HauntedTree2 extends WorldGenerator {
	public boolean generate(final World world, final Random rand, final int i, final int j, final int k) {
		if (world.getBlock(i + 4, j - 1, k + 4) != Blockizer.GrassGreckon && world.getBlock(i + 4, j - 1, k + 4) != Blockizer.DirtGreckon) {
			return false;
		}
		world.setBlock(i + 0, j + 9, k + 3, Blockizer.LeavesHaunted);
		world.setBlock(i + 0, j + 9, k + 5, Blockizer.LeavesHaunted);
		world.setBlock(i + 0, j + 10, k + 3, Blockizer.LeavesHaunted);
		world.setBlock(i + 0, j + 10, k + 5, Blockizer.LeavesHaunted);
		world.setBlock(i + 0, j + 11, k + 3, Blockizer.LeavesHaunted);
		world.setBlock(i + 0, j + 11, k + 5, Blockizer.LeavesHaunted);
		world.setBlock(i + 0, j + 12, k + 3, Blockizer.LeavesHaunted);
		world.setBlock(i + 0, j + 12, k + 4, Blockizer.LeavesHaunted);
		world.setBlock(i + 0, j + 12, k + 5, Blockizer.LeavesHaunted);
		if (rand.nextInt(3) == 2) {
			world.setBlock(i + 1, j + 9, k + 1, Blockizer.LeavesHaunted);
		}
		else {
			world.setBlock(i + 1, j + 9, k + 1, Blockizer.HauntedLeavesGhouls);
		}
		world.setBlock(i + 1, j + 9, k + 7, Blockizer.LeavesHaunted);
		world.setBlock(i + 1, j + 10, k + 1, Blockizer.LeavesHaunted);
		world.setBlock(i + 1, j + 10, k + 7, Blockizer.LeavesHaunted);
		world.setBlock(i + 1, j + 11, k + 1, Blockizer.LeavesHaunted);
		world.setBlock(i + 1, j + 11, k + 7, Blockizer.LeavesHaunted);
		world.setBlock(i + 1, j + 12, k + 1, Blockizer.LeavesHaunted);
		world.setBlock(i + 1, j + 12, k + 2, Blockizer.LeavesHaunted);
		world.setBlock(i + 1, j + 12, k + 3, Blockizer.LeavesHaunted);
		world.setBlock(i + 1, j + 12, k + 4, Blockizer.LeavesHaunted);
		world.setBlock(i + 1, j + 12, k + 5, Blockizer.LeavesHaunted);
		world.setBlock(i + 1, j + 12, k + 6, Blockizer.LeavesHaunted);
		world.setBlock(i + 1, j + 12, k + 7, Blockizer.LeavesHaunted);
		world.setBlock(i + 2, j + 10, k + 4, Blockizer.WoodHaunted);
		world.setBlock(i + 2, j + 11, k + 4, Blockizer.WoodHaunted);
		world.setBlock(i + 2, j + 12, k + 1, Blockizer.LeavesHaunted);
		world.setBlock(i + 2, j + 12, k + 2, Blockizer.LeavesHaunted);
		world.setBlock(i + 2, j + 12, k + 3, Blockizer.LeavesHaunted);
		world.setBlock(i + 2, j + 12, k + 4, Blockizer.LeavesHaunted);
		world.setBlock(i + 2, j + 12, k + 5, Blockizer.LeavesHaunted);
		world.setBlock(i + 2, j + 12, k + 6, Blockizer.LeavesHaunted);
		world.setBlock(i + 2, j + 12, k + 7, Blockizer.LeavesHaunted);
		world.setBlock(i + 3, j + 9, k + 0, Blockizer.LeavesHaunted);
		world.setBlock(i + 3, j + 9, k + 8, Blockizer.LeavesHaunted);
		world.setBlock(i + 3, j + 10, k + 0, Blockizer.LeavesHaunted);
		world.setBlock(i + 3, j + 10, k + 4, Blockizer.WoodHaunted);
		world.setBlock(i + 3, j + 10, k + 8, Blockizer.LeavesHaunted);
		world.setBlock(i + 3, j + 11, k + 0, Blockizer.LeavesHaunted);
		world.setBlock(i + 3, j + 11, k + 8, Blockizer.LeavesHaunted);
		world.setBlock(i + 3, j + 12, k + 0, Blockizer.LeavesHaunted);
		world.setBlock(i + 3, j + 12, k + 1, Blockizer.LeavesHaunted);
		world.setBlock(i + 3, j + 12, k + 2, Blockizer.LeavesHaunted);
		world.setBlock(i + 3, j + 12, k + 3, Blockizer.LeavesHaunted);
		world.setBlock(i + 3, j + 12, k + 4, Blockizer.LeavesHaunted);
		world.setBlock(i + 3, j + 12, k + 5, Blockizer.LeavesHaunted);
		world.setBlock(i + 3, j + 12, k + 6, Blockizer.LeavesHaunted);
		world.setBlock(i + 3, j + 12, k + 7, Blockizer.LeavesHaunted);
		world.setBlock(i + 3, j + 12, k + 8, Blockizer.LeavesHaunted);
		world.setBlock(i + 4, j + 0, k + 4, Blockizer.WoodHaunted);
		world.setBlock(i + 4, j + 1, k + 4, Blockizer.WoodHaunted);
		world.setBlock(i + 4, j + 2, k + 4, Blockizer.WoodHaunted);
		world.setBlock(i + 4, j + 3, k + 4, Blockizer.WoodHaunted);
		if (rand.nextInt(3) == 2) {
			world.setBlock(i + 4, j + 4, k + 4, Blockizer.WoodHaunted);
		}
		else {
			world.setBlock(i + 4, j + 4, k + 4, Blockizer.HauntedWoodLargeEye);
		}
		world.setBlock(i + 4, j + 5, k + 4, Blockizer.WoodHaunted);
		world.setBlock(i + 4, j + 6, k + 4, Blockizer.WoodHaunted);
		if (rand.nextInt(3) == 2) {
			world.setBlock(i + 4, j + 7, k + 4, Blockizer.WoodHaunted);
		}
		else {
			world.setBlock(i + 4, j + 7, k + 4, Blockizer.HauntedWoodRedEyes);
		}
		world.setBlock(i + 4, j + 8, k + 4, Blockizer.WoodHaunted);
		world.setBlock(i + 4, j + 9, k + 4, Blockizer.WoodHaunted);
		world.setBlock(i + 4, j + 10, k + 2, Blockizer.WoodHaunted);
		world.setBlock(i + 4, j + 10, k + 3, Blockizer.WoodHaunted);
		world.setBlock(i + 4, j + 10, k + 4, Blockizer.WoodHaunted);
		world.setBlock(i + 4, j + 10, k + 5, Blockizer.WoodHaunted);
		world.setBlock(i + 4, j + 10, k + 6, Blockizer.WoodHaunted);
		world.setBlock(i + 4, j + 11, k + 2, Blockizer.WoodHaunted);
		world.setBlock(i + 4, j + 11, k + 6, Blockizer.WoodHaunted);
		world.setBlock(i + 4, j + 12, k + 0, Blockizer.LeavesHaunted);
		world.setBlock(i + 4, j + 12, k + 1, Blockizer.LeavesHaunted);
		world.setBlock(i + 4, j + 12, k + 2, Blockizer.LeavesHaunted);
		world.setBlock(i + 4, j + 12, k + 3, Blockizer.LeavesHaunted);
		if (rand.nextInt(3) == 2) {
			world.setBlock(i + 4, j + 12, k + 4, Blockizer.LeavesHaunted);
		}
		else {
			world.setBlock(i + 4, j + 12, k + 4, Blockizer.HauntedLeavesGhouls);
		}
		world.setBlock(i + 4, j + 12, k + 5, Blockizer.LeavesHaunted);
		world.setBlock(i + 4, j + 12, k + 6, Blockizer.LeavesHaunted);
		world.setBlock(i + 4, j + 12, k + 7, Blockizer.LeavesHaunted);
		world.setBlock(i + 4, j + 12, k + 8, Blockizer.LeavesHaunted);
		world.setBlock(i + 5, j + 9, k + 0, Blockizer.LeavesHaunted);
		world.setBlock(i + 5, j + 9, k + 8, Blockizer.LeavesHaunted);
		world.setBlock(i + 5, j + 10, k + 0, Blockizer.LeavesHaunted);
		world.setBlock(i + 5, j + 10, k + 4, Blockizer.WoodHaunted);
		world.setBlock(i + 5, j + 10, k + 8, Blockizer.LeavesHaunted);
		world.setBlock(i + 5, j + 11, k + 0, Blockizer.LeavesHaunted);
		world.setBlock(i + 5, j + 11, k + 8, Blockizer.LeavesHaunted);
		world.setBlock(i + 5, j + 12, k + 0, Blockizer.LeavesHaunted);
		world.setBlock(i + 5, j + 12, k + 1, Blockizer.LeavesHaunted);
		world.setBlock(i + 5, j + 12, k + 2, Blockizer.LeavesHaunted);
		world.setBlock(i + 5, j + 12, k + 3, Blockizer.LeavesHaunted);
		world.setBlock(i + 5, j + 12, k + 4, Blockizer.LeavesHaunted);
		world.setBlock(i + 5, j + 12, k + 5, Blockizer.LeavesHaunted);
		world.setBlock(i + 5, j + 12, k + 6, Blockizer.LeavesHaunted);
		world.setBlock(i + 5, j + 12, k + 7, Blockizer.LeavesHaunted);
		world.setBlock(i + 5, j + 12, k + 8, Blockizer.LeavesHaunted);
		world.setBlock(i + 6, j + 10, k + 4, Blockizer.WoodHaunted);
		world.setBlock(i + 6, j + 11, k + 4, Blockizer.WoodHaunted);
		world.setBlock(i + 6, j + 12, k + 1, Blockizer.LeavesHaunted);
		world.setBlock(i + 6, j + 12, k + 2, Blockizer.LeavesHaunted);
		world.setBlock(i + 6, j + 12, k + 3, Blockizer.LeavesHaunted);
		world.setBlock(i + 6, j + 12, k + 4, Blockizer.LeavesHaunted);
		world.setBlock(i + 6, j + 12, k + 5, Blockizer.LeavesHaunted);
		world.setBlock(i + 6, j + 12, k + 6, Blockizer.LeavesHaunted);
		world.setBlock(i + 6, j + 12, k + 7, Blockizer.LeavesHaunted);
		world.setBlock(i + 7, j + 9, k + 1, Blockizer.LeavesHaunted);
		world.setBlock(i + 7, j + 9, k + 7, Blockizer.LeavesHaunted);
		world.setBlock(i + 7, j + 10, k + 1, Blockizer.LeavesHaunted);
		world.setBlock(i + 7, j + 10, k + 7, Blockizer.LeavesHaunted);
		world.setBlock(i + 7, j + 11, k + 1, Blockizer.LeavesHaunted);
		world.setBlock(i + 7, j + 11, k + 7, Blockizer.LeavesHaunted);
		world.setBlock(i + 7, j + 12, k + 1, Blockizer.LeavesHaunted);
		world.setBlock(i + 7, j + 12, k + 2, Blockizer.LeavesHaunted);
		world.setBlock(i + 7, j + 12, k + 3, Blockizer.LeavesHaunted);
		world.setBlock(i + 7, j + 12, k + 4, Blockizer.LeavesHaunted);
		world.setBlock(i + 7, j + 12, k + 5, Blockizer.LeavesHaunted);
		world.setBlock(i + 7, j + 12, k + 6, Blockizer.LeavesHaunted);
		world.setBlock(i + 7, j + 12, k + 7, Blockizer.LeavesHaunted);
		world.setBlock(i + 8, j + 9, k + 3, Blockizer.LeavesHaunted);
		world.setBlock(i + 8, j + 9, k + 5, Blockizer.LeavesHaunted);
		world.setBlock(i + 8, j + 10, k + 3, Blockizer.LeavesHaunted);
		world.setBlock(i + 8, j + 10, k + 5, Blockizer.LeavesHaunted);
		world.setBlock(i + 8, j + 11, k + 3, Blockizer.LeavesHaunted);
		world.setBlock(i + 8, j + 11, k + 5, Blockizer.LeavesHaunted);
		world.setBlock(i + 8, j + 12, k + 3, Blockizer.LeavesHaunted);
		world.setBlock(i + 8, j + 12, k + 4, Blockizer.LeavesHaunted);
		world.setBlock(i + 8, j + 12, k + 5, Blockizer.LeavesHaunted);
		return true;
	}
}
