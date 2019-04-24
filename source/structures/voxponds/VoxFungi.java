package net.nevermine.structures.voxponds;

import net.minecraft.block.Block;
import net.nevermine.izer.Blockizer;

import java.util.Random;

public class VoxFungi implements IVoxpondsStructure {
	private Random rand;

	public VoxFungi() {
		rand = new Random();
	}

	@Override
	public void generate(final Block[][][] chunk, final int i, final int j, final int k) {
		for (int y = j; y < j + 2; ++y) {
			chunk[i][y][k] = Blockizer.VoxFungiStem;
			if (rand.nextInt(2) == 1 || y == j + 1) {
				chunk[i][y + 1][k] = Blockizer.VoxFungi;
				break;
			}
		}
	}
}
