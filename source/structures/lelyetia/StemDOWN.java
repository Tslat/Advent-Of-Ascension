package net.nevermine.structures.lelyetia;

import net.minecraft.block.Block;
import net.nevermine.izer.Blockizer;
import net.nevermine.structures.voxponds.IVoxpondsStructure;

import java.util.Random;

public class StemDOWN implements IVoxpondsStructure {
	private Random rand;

	public StemDOWN() {
		rand = new Random();
	}

	@Override
	public void generate(final Block[][][] chunk, final int i, final int j, final int k) {
		for (int y = j; y > j - 15; --y) {
			chunk[i][y][k] = Blockizer.LelyetianStem;
			if (rand.nextInt(6) == 3 || y == j - 14) {
				chunk[i][y - 1][k] = Blockizer.LelyetianStemCapDown;
				break;
			}
		}
	}
}
