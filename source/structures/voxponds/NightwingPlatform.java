package net.nevermine.structures.voxponds;

import net.minecraft.block.Block;
import net.nevermine.izer.Blockizer;

public class NightwingPlatform implements IVoxpondsStructure {
	@Override
	public void generate(final Block[][][] chunk, final int i, final int j, final int k) {
		chunk[i + 0][j + 0][k + 0] = Blockizer.DegradedSteel;
		chunk[i + 0][j + 0][k + 1] = Blockizer.DegradedSteel;
		chunk[i + 0][j + 0][k + 2] = Blockizer.DegradedSteel;
		chunk[i + 0][j + 0][k + 3] = Blockizer.DegradedSteel;
		chunk[i + 0][j + 0][k + 4] = Blockizer.DegradedSteel;
		chunk[i + 1][j + 0][k + 0] = Blockizer.DegradedSteel;
		chunk[i + 1][j + 0][k + 1] = Blockizer.DegradedSteel;
		chunk[i + 1][j + 0][k + 2] = Blockizer.DegradedSteel;
		chunk[i + 1][j + 0][k + 3] = Blockizer.DegradedSteel;
		chunk[i + 1][j + 0][k + 4] = Blockizer.DegradedSteel;
		chunk[i + 2][j + 0][k + 0] = Blockizer.DegradedSteel;
		chunk[i + 2][j + 0][k + 1] = Blockizer.DegradedSteel;
		chunk[i + 2][j + 0][k + 2] = Blockizer.DegradedSteel;
		chunk[i + 2][j + 0][k + 3] = Blockizer.DegradedSteel;
		chunk[i + 2][j + 0][k + 4] = Blockizer.DegradedSteel;
		chunk[i + 3][j + 0][k + 0] = Blockizer.DegradedSteel;
		chunk[i + 3][j + 0][k + 1] = Blockizer.DegradedSteel;
		chunk[i + 3][j + 0][k + 2] = Blockizer.DegradedSteel;
		chunk[i + 3][j + 0][k + 3] = Blockizer.DegradedSteel;
		chunk[i + 3][j + 0][k + 4] = Blockizer.DegradedSteel;
		chunk[i + 4][j + 0][k + 0] = Blockizer.DegradedSteel;
		chunk[i + 4][j + 0][k + 1] = Blockizer.DegradedSteel;
		chunk[i + 4][j + 0][k + 2] = Blockizer.DegradedSteel;
		chunk[i + 4][j + 0][k + 3] = Blockizer.DegradedSteel;
		chunk[i + 4][j + 0][k + 4] = Blockizer.DegradedSteel;
		chunk[i + 2][j + 1][k + 2] = Blockizer.SpawnerNightwing;
	}
}
