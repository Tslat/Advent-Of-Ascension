package net.nevermine.structures.shyrelands;

import net.minecraft.block.Block;
import net.nevermine.izer.Blockizer;
import net.nevermine.structures.voxponds.IVoxpondsStructure;

import java.util.Random;

public class ShyrePlantUP implements IVoxpondsStructure {
	private Random rand = new Random();

	public void generate(Block[][][] chunk, int i, int j, int k) {
		for (int y = j; y < j + 3; y++) {
			chunk[i][y][k] = Blockizer.ShyreStock;
			if ((this.rand.nextInt(3) == 2) || (y == j + 2)) {
				chunk[i][(y + 1)][k] = Blockizer.ShyreCapUp;
				break;
			}
		}
	}
}
