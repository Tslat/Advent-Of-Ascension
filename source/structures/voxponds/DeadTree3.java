package net.nevermine.structures.voxponds;

import net.minecraft.block.Block;
import net.nevermine.izer.Blockizer;

public class DeadTree3 implements IVoxpondsStructure {
	@Override
	public void generate(final Block[][][] chunk, final int i, final int j, final int k) {
		chunk[i + 0][j + 6][k + 3] = Blockizer.WoodToxic;
		chunk[i + 0][j + 7][k + 3] = Blockizer.WoodToxic;
		chunk[i + 1][j + 4][k + 3] = Blockizer.WoodToxic;
		chunk[i + 1][j + 5][k + 3] = Blockizer.WoodToxic;
		chunk[i + 1][j + 6][k + 3] = Blockizer.WoodToxic;
		chunk[i + 2][j + 4][k + 3] = Blockizer.WoodToxic;
		chunk[i + 3][j + 0][k + 3] = Blockizer.WoodToxic;
		chunk[i + 3][j + 1][k + 3] = Blockizer.WoodToxic;
		chunk[i + 3][j + 2][k + 3] = Blockizer.WoodToxic;
		chunk[i + 3][j + 3][k + 3] = Blockizer.WoodToxic;
		chunk[i + 3][j + 4][k + 1] = Blockizer.WoodToxic;
		chunk[i + 3][j + 4][k + 2] = Blockizer.WoodToxic;
		chunk[i + 3][j + 4][k + 3] = Blockizer.WoodToxic;
		chunk[i + 3][j + 4][k + 4] = Blockizer.WoodToxic;
		chunk[i + 3][j + 4][k + 5] = Blockizer.WoodToxic;
		chunk[i + 3][j + 5][k + 1] = Blockizer.WoodToxic;
		chunk[i + 3][j + 5][k + 5] = Blockizer.WoodToxic;
		chunk[i + 3][j + 6][k + 0] = Blockizer.WoodToxic;
		chunk[i + 3][j + 6][k + 1] = Blockizer.WoodToxic;
		chunk[i + 3][j + 6][k + 5] = Blockizer.WoodToxic;
		chunk[i + 3][j + 6][k + 6] = Blockizer.WoodToxic;
		chunk[i + 3][j + 7][k + 0] = Blockizer.WoodToxic;
		chunk[i + 3][j + 7][k + 6] = Blockizer.WoodToxic;
		chunk[i + 4][j + 4][k + 3] = Blockizer.WoodToxic;
		chunk[i + 5][j + 4][k + 3] = Blockizer.WoodToxic;
		chunk[i + 5][j + 5][k + 3] = Blockizer.WoodToxic;
		chunk[i + 5][j + 6][k + 3] = Blockizer.WoodToxic;
		chunk[i + 6][j + 6][k + 3] = Blockizer.WoodToxic;
		chunk[i + 6][j + 7][k + 3] = Blockizer.WoodToxic;
	}
}
