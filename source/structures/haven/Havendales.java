package net.nevermine.structures.haven;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.nevermine.izer.Blockizer;

import java.util.Random;

public class Havendales extends WorldGenerator {
	private int pick;

	public Havendales(final int choice) {
		pick = choice;
	}

	public boolean generate(final World world, final Random rand, final int i, final int j, final int k) {
		if (pick == 1) {
			for (int y = j; y < j + 3; ++y) {
				world.setBlock(i, y, k, Blockizer.HavendalesBlueStem);
				if (rand.nextInt(3) == 2 || y == j + 2) {
					world.setBlock(i, y + 1, k, Blockizer.HavendalesBlue);
					break;
				}
			}
		}
		else if (pick == 2) {
			for (int y = j; y < j + 3; ++y) {
				world.setBlock(i, y, k, Blockizer.HavendalesPinkStem);
				if (rand.nextInt(3) == 2 || y == j + 2) {
					world.setBlock(i, y + 1, k, Blockizer.HavendalesPink);
					break;
				}
			}
		}
		else {
			for (int y = j; y < j + 3; ++y) {
				world.setBlock(i, y, k, Blockizer.HavendalesYellowStem);
				if (rand.nextInt(3) == 2 || y == j + 2) {
					world.setBlock(i, y + 1, k, Blockizer.HavendalesYellow);
					break;
				}
			}
		}
		return true;
	}
}
