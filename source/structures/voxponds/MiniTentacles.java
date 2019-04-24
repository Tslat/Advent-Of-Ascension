package net.nevermine.structures.voxponds;

import net.minecraft.block.Block;
import net.nevermine.izer.Blockizer;

import java.util.Random;

public class MiniTentacles implements IVoxpondsStructure {
	private Random rand;

	public MiniTentacles() {
		rand = new Random();
	}

	@Override
	public void generate(final Block[][][] chunk, final int i, final int j, final int k) {
		for (int y = j; y < j + 5; ++y) {
			chunk[i][y][k] = Blockizer.MiniTentacles;
			if (rand.nextInt(3) == 1 || y == j + 4) {
				chunk[i][y + 1][k] = Blockizer.MiniTentaclesTop;
				break;
			}
		}
	}
}
