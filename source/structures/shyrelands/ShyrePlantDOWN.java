package net.nevermine.structures.shyrelands;

import net.minecraft.block.Block;
import net.nevermine.izer.Blockizer;
import net.nevermine.structures.voxponds.IVoxpondsStructure;

import java.util.Random;

public class ShyrePlantDOWN implements IVoxpondsStructure {
	private Random rand = new Random();

	public void generate(Block[][][] chunk, int i, int j, int k) {
		for (int y = j; y > j - 12; y--) {
			chunk[i][y][k] = Blockizer.ShyreStock;
			if ((this.rand.nextInt(6) == 3) || (y == j - 11)) {
				chunk[i][(y - 1)][k] = Blockizer.ShyreCapDown;
				break;
			}
		}
	}
}
