package net.nevermine.structures.greckon;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.nevermine.izer.Blockizer;

import java.util.Random;

public class HauntedTree1 extends WorldGenerator {
	public boolean generate(final World world, final Random rand, final int i, final int j, final int k) {
		if (world.getBlock(i + 3, j - 1, k + 3) != Blockizer.GrassGreckon && world.getBlock(i + 3, j - 1, k + 3) != Blockizer.DirtGreckon) {
			return false;
		}
		world.setBlock(i + 0, j + 8, k + 2, Blockizer.LeavesHaunted);
		world.setBlock(i + 0, j + 8, k + 4, Blockizer.LeavesHaunted);
		world.setBlock(i + 0, j + 9, k + 2, Blockizer.LeavesHaunted);
		world.setBlock(i + 0, j + 9, k + 4, Blockizer.LeavesHaunted);
		world.setBlock(i + 0, j + 10, k + 2, Blockizer.LeavesHaunted);
		world.setBlock(i + 0, j + 10, k + 4, Blockizer.LeavesHaunted);
		world.setBlock(i + 0, j + 11, k + 2, Blockizer.LeavesHaunted);
		world.setBlock(i + 0, j + 11, k + 4, Blockizer.LeavesHaunted);
		world.setBlock(i + 1, j + 5, k + 1, Blockizer.LeavesHaunted);
		world.setBlock(i + 1, j + 5, k + 2, Blockizer.LeavesHaunted);
		world.setBlock(i + 1, j + 5, k + 3, Blockizer.LeavesHaunted);
		world.setBlock(i + 1, j + 5, k + 4, Blockizer.LeavesHaunted);
		world.setBlock(i + 1, j + 5, k + 5, Blockizer.LeavesHaunted);
		world.setBlock(i + 1, j + 6, k + 1, Blockizer.LeavesHaunted);
		world.setBlock(i + 1, j + 6, k + 2, Blockizer.LeavesHaunted);
		world.setBlock(i + 1, j + 6, k + 3, Blockizer.LeavesHaunted);
		world.setBlock(i + 1, j + 6, k + 4, Blockizer.LeavesHaunted);
		if (rand.nextInt(3) == 2) {
			world.setBlock(i + 1, j + 6, k + 5, Blockizer.LeavesHaunted);
		}
		else {
			world.setBlock(i + 1, j + 6, k + 5, Blockizer.HauntedLeavesGhouls);
		}
		world.setBlock(i + 1, j + 7, k + 1, Blockizer.LeavesHaunted);
		world.setBlock(i + 1, j + 7, k + 2, Blockizer.LeavesHaunted);
		world.setBlock(i + 1, j + 7, k + 3, Blockizer.LeavesHaunted);
		world.setBlock(i + 1, j + 7, k + 4, Blockizer.LeavesHaunted);
		world.setBlock(i + 1, j + 7, k + 5, Blockizer.LeavesHaunted);
		world.setBlock(i + 1, j + 8, k + 1, Blockizer.LeavesHaunted);
		world.setBlock(i + 1, j + 8, k + 2, Blockizer.LeavesHaunted);
		world.setBlock(i + 1, j + 8, k + 3, Blockizer.LeavesHaunted);
		world.setBlock(i + 1, j + 8, k + 4, Blockizer.LeavesHaunted);
		world.setBlock(i + 1, j + 8, k + 5, Blockizer.LeavesHaunted);
		world.setBlock(i + 2, j + 5, k + 1, Blockizer.LeavesHaunted);
		if (rand.nextInt(3) == 2) {
			world.setBlock(i + 2, j + 5, k + 2, Blockizer.LeavesHaunted);
		}
		else {
			world.setBlock(i + 2, j + 5, k + 2, Blockizer.HauntedLeavesGhouls);
		}
		world.setBlock(i + 2, j + 5, k + 3, Blockizer.LeavesHaunted);
		world.setBlock(i + 2, j + 5, k + 4, Blockizer.LeavesHaunted);
		world.setBlock(i + 2, j + 5, k + 5, Blockizer.LeavesHaunted);
		world.setBlock(i + 2, j + 6, k + 1, Blockizer.LeavesHaunted);
		world.setBlock(i + 2, j + 6, k + 5, Blockizer.LeavesHaunted);
		world.setBlock(i + 2, j + 7, k + 1, Blockizer.LeavesHaunted);
		world.setBlock(i + 2, j + 7, k + 5, Blockizer.LeavesHaunted);
		world.setBlock(i + 2, j + 8, k + 0, Blockizer.LeavesHaunted);
		world.setBlock(i + 2, j + 8, k + 1, Blockizer.LeavesHaunted);
		world.setBlock(i + 2, j + 8, k + 5, Blockizer.LeavesHaunted);
		world.setBlock(i + 2, j + 8, k + 6, Blockizer.LeavesHaunted);
		world.setBlock(i + 2, j + 9, k + 0, Blockizer.LeavesHaunted);
		world.setBlock(i + 2, j + 9, k + 2, Blockizer.LeavesHaunted);
		world.setBlock(i + 2, j + 9, k + 3, Blockizer.LeavesHaunted);
		world.setBlock(i + 2, j + 9, k + 4, Blockizer.LeavesHaunted);
		world.setBlock(i + 2, j + 9, k + 6, Blockizer.LeavesHaunted);
		world.setBlock(i + 2, j + 10, k + 0, Blockizer.LeavesHaunted);
		world.setBlock(i + 2, j + 10, k + 2, Blockizer.LeavesHaunted);
		world.setBlock(i + 2, j + 10, k + 3, Blockizer.LeavesHaunted);
		world.setBlock(i + 2, j + 10, k + 4, Blockizer.LeavesHaunted);
		world.setBlock(i + 2, j + 10, k + 6, Blockizer.LeavesHaunted);
		world.setBlock(i + 2, j + 11, k + 0, Blockizer.LeavesHaunted);
		world.setBlock(i + 2, j + 11, k + 2, Blockizer.LeavesHaunted);
		world.setBlock(i + 2, j + 11, k + 3, Blockizer.LeavesHaunted);
		world.setBlock(i + 2, j + 11, k + 4, Blockizer.LeavesHaunted);
		world.setBlock(i + 2, j + 11, k + 6, Blockizer.LeavesHaunted);
		world.setBlock(i + 2, j + 12, k + 2, Blockizer.LeavesHaunted);
		world.setBlock(i + 2, j + 12, k + 3, Blockizer.LeavesHaunted);
		world.setBlock(i + 2, j + 12, k + 4, Blockizer.LeavesHaunted);
		world.setBlock(i + 3, j + 0, k + 3, Blockizer.WoodHaunted);
		world.setBlock(i + 3, j + 1, k + 3, Blockizer.WoodHaunted);
		if (rand.nextInt(3) == 2) {
			world.setBlock(i + 3, j + 2, k + 3, Blockizer.WoodHaunted);
		}
		else if (rand.nextInt(5) == 1) {
			world.setBlock(i + 3, j + 2, k + 3, Blockizer.HauntedWoodFlashingFace);
		}
		else {
			world.setBlock(i + 3, j + 2, k + 3, Blockizer.HauntedWoodRedEyes);
		}
		world.setBlock(i + 3, j + 3, k + 3, Blockizer.WoodHaunted);
		world.setBlock(i + 3, j + 4, k + 3, Blockizer.WoodHaunted);
		if (rand.nextInt(3) == 2) {
			world.setBlock(i + 3, j + 5, k + 1, Blockizer.LeavesHaunted);
		}
		else {
			world.setBlock(i + 3, j + 5, k + 1, Blockizer.HauntedLeavesGhouls);
		}
		world.setBlock(i + 3, j + 5, k + 2, Blockizer.LeavesHaunted);
		world.setBlock(i + 3, j + 5, k + 3, Blockizer.WoodHaunted);
		world.setBlock(i + 3, j + 5, k + 4, Blockizer.LeavesHaunted);
		world.setBlock(i + 3, j + 5, k + 5, Blockizer.LeavesHaunted);
		world.setBlock(i + 3, j + 6, k + 1, Blockizer.LeavesHaunted);
		world.setBlock(i + 3, j + 6, k + 3, Blockizer.WoodHaunted);
		world.setBlock(i + 3, j + 6, k + 5, Blockizer.LeavesHaunted);
		world.setBlock(i + 3, j + 7, k + 1, Blockizer.LeavesHaunted);
		if (rand.nextInt(3) == 2) {
			world.setBlock(i + 3, j + 7, k + 3, Blockizer.WoodHaunted);
		}
		else {
			world.setBlock(i + 3, j + 7, k + 3, Blockizer.HauntedWoodRedEyes);
		}
		world.setBlock(i + 3, j + 7, k + 5, Blockizer.LeavesHaunted);
		world.setBlock(i + 3, j + 8, k + 1, Blockizer.LeavesHaunted);
		world.setBlock(i + 3, j + 8, k + 5, Blockizer.LeavesHaunted);
		world.setBlock(i + 3, j + 9, k + 2, Blockizer.LeavesHaunted);
		world.setBlock(i + 3, j + 9, k + 4, Blockizer.LeavesHaunted);
		world.setBlock(i + 3, j + 10, k + 2, Blockizer.LeavesHaunted);
		world.setBlock(i + 3, j + 10, k + 4, Blockizer.LeavesHaunted);
		world.setBlock(i + 3, j + 11, k + 2, Blockizer.LeavesHaunted);
		world.setBlock(i + 3, j + 11, k + 4, Blockizer.LeavesHaunted);
		world.setBlock(i + 3, j + 12, k + 2, Blockizer.LeavesHaunted);
		world.setBlock(i + 3, j + 12, k + 4, Blockizer.LeavesHaunted);
		world.setBlock(i + 3, j + 13, k + 3, Blockizer.LeavesHaunted);
		world.setBlock(i + 3, j + 14, k + 3, Blockizer.LeavesHaunted);
		world.setBlock(i + 3, j + 15, k + 3, Blockizer.LeavesHaunted);
		world.setBlock(i + 4, j + 5, k + 1, Blockizer.LeavesHaunted);
		world.setBlock(i + 4, j + 5, k + 2, Blockizer.LeavesHaunted);
		world.setBlock(i + 4, j + 5, k + 3, Blockizer.LeavesHaunted);
		world.setBlock(i + 4, j + 5, k + 4, Blockizer.LeavesHaunted);
		world.setBlock(i + 4, j + 5, k + 5, Blockizer.LeavesHaunted);
		world.setBlock(i + 4, j + 6, k + 1, Blockizer.LeavesHaunted);
		world.setBlock(i + 4, j + 6, k + 5, Blockizer.LeavesHaunted);
		world.setBlock(i + 4, j + 7, k + 1, Blockizer.LeavesHaunted);
		world.setBlock(i + 4, j + 7, k + 5, Blockizer.LeavesHaunted);
		world.setBlock(i + 4, j + 8, k + 0, Blockizer.LeavesHaunted);
		world.setBlock(i + 4, j + 8, k + 1, Blockizer.LeavesHaunted);
		world.setBlock(i + 4, j + 8, k + 5, Blockizer.LeavesHaunted);
		world.setBlock(i + 4, j + 8, k + 6, Blockizer.LeavesHaunted);
		world.setBlock(i + 4, j + 9, k + 0, Blockizer.LeavesHaunted);
		world.setBlock(i + 4, j + 9, k + 2, Blockizer.LeavesHaunted);
		world.setBlock(i + 4, j + 9, k + 3, Blockizer.LeavesHaunted);
		world.setBlock(i + 4, j + 9, k + 4, Blockizer.LeavesHaunted);
		world.setBlock(i + 4, j + 9, k + 6, Blockizer.LeavesHaunted);
		world.setBlock(i + 4, j + 10, k + 0, Blockizer.LeavesHaunted);
		world.setBlock(i + 4, j + 10, k + 2, Blockizer.LeavesHaunted);
		world.setBlock(i + 4, j + 10, k + 3, Blockizer.LeavesHaunted);
		if (rand.nextInt(3) == 2) {
			world.setBlock(i + 4, j + 10, k + 4, Blockizer.LeavesHaunted);
		}
		else {
			world.setBlock(i + 4, j + 10, k + 4, Blockizer.HauntedLeavesGhouls);
		}
		world.setBlock(i + 4, j + 10, k + 6, Blockizer.LeavesHaunted);
		world.setBlock(i + 4, j + 11, k + 0, Blockizer.LeavesHaunted);
		world.setBlock(i + 4, j + 11, k + 2, Blockizer.LeavesHaunted);
		world.setBlock(i + 4, j + 11, k + 3, Blockizer.LeavesHaunted);
		world.setBlock(i + 4, j + 11, k + 4, Blockizer.LeavesHaunted);
		world.setBlock(i + 4, j + 11, k + 6, Blockizer.LeavesHaunted);
		world.setBlock(i + 4, j + 12, k + 2, Blockizer.LeavesHaunted);
		world.setBlock(i + 4, j + 12, k + 3, Blockizer.LeavesHaunted);
		world.setBlock(i + 4, j + 12, k + 4, Blockizer.LeavesHaunted);
		world.setBlock(i + 5, j + 5, k + 1, Blockizer.LeavesHaunted);
		world.setBlock(i + 5, j + 5, k + 2, Blockizer.LeavesHaunted);
		world.setBlock(i + 5, j + 5, k + 3, Blockizer.LeavesHaunted);
		if (rand.nextInt(3) == 2) {
			world.setBlock(i + 5, j + 5, k + 4, Blockizer.LeavesHaunted);
		}
		else {
			world.setBlock(i + 5, j + 5, k + 4, Blockizer.HauntedLeavesGhouls);
		}
		world.setBlock(i + 5, j + 5, k + 5, Blockizer.LeavesHaunted);
		world.setBlock(i + 5, j + 6, k + 1, Blockizer.LeavesHaunted);
		world.setBlock(i + 5, j + 6, k + 2, Blockizer.LeavesHaunted);
		world.setBlock(i + 5, j + 6, k + 3, Blockizer.LeavesHaunted);
		world.setBlock(i + 5, j + 6, k + 4, Blockizer.LeavesHaunted);
		world.setBlock(i + 5, j + 6, k + 5, Blockizer.LeavesHaunted);
		world.setBlock(i + 5, j + 7, k + 1, Blockizer.LeavesHaunted);
		world.setBlock(i + 5, j + 7, k + 2, Blockizer.LeavesHaunted);
		world.setBlock(i + 5, j + 7, k + 3, Blockizer.LeavesHaunted);
		world.setBlock(i + 5, j + 7, k + 4, Blockizer.LeavesHaunted);
		world.setBlock(i + 5, j + 7, k + 5, Blockizer.LeavesHaunted);
		world.setBlock(i + 5, j + 8, k + 1, Blockizer.LeavesHaunted);
		world.setBlock(i + 5, j + 8, k + 2, Blockizer.LeavesHaunted);
		world.setBlock(i + 5, j + 8, k + 3, Blockizer.LeavesHaunted);
		world.setBlock(i + 5, j + 8, k + 4, Blockizer.LeavesHaunted);
		world.setBlock(i + 5, j + 8, k + 5, Blockizer.LeavesHaunted);
		world.setBlock(i + 6, j + 8, k + 2, Blockizer.LeavesHaunted);
		world.setBlock(i + 6, j + 8, k + 4, Blockizer.LeavesHaunted);
		world.setBlock(i + 6, j + 9, k + 2, Blockizer.LeavesHaunted);
		world.setBlock(i + 6, j + 9, k + 4, Blockizer.LeavesHaunted);
		world.setBlock(i + 6, j + 10, k + 2, Blockizer.LeavesHaunted);
		world.setBlock(i + 6, j + 10, k + 4, Blockizer.LeavesHaunted);
		world.setBlock(i + 6, j + 11, k + 2, Blockizer.LeavesHaunted);
		world.setBlock(i + 6, j + 11, k + 4, Blockizer.LeavesHaunted);
		return true;
	}
}
