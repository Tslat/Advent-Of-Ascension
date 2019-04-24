package net.nevermine.structures.voxponds;

import net.minecraft.block.Block;
import net.nevermine.izer.Blockizer;

public class HangingVoxLight implements IVoxpondsStructure {
	@Override
	public void generate(final Block[][][] chunk, final int i, final int j, final int k) {
		chunk[i + 2][j + 1][k + 2] = Blockizer.DegradedSteel;
		chunk[i + 2][j + 2][k + 2] = Blockizer.DegradedSteel;
		chunk[i + 2][j + 2][k + 3] = Blockizer.DegradedSteel;
		chunk[i + 2][j + 2][k + 4] = Blockizer.DegradedSteel;
		chunk[i + 2][j + 2][k + 0] = Blockizer.DegradedSteel;
		chunk[i + 2][j + 0][k + 2] = Blockizer.LampVox;
		chunk[i + 2][j + 2][k + 1] = Blockizer.DegradedSteel;
		chunk[i + 0][j + 2][k + 2] = Blockizer.DegradedSteel;
		chunk[i + 1][j + 2][k + 2] = Blockizer.DegradedSteel;
		chunk[i + 3][j + 2][k + 2] = Blockizer.DegradedSteel;
		chunk[i + 4][j + 2][k + 2] = Blockizer.DegradedSteel;
		chunk[i + 2][j + 3][k + 0] = Blockizer.DegradedSteel;
		chunk[i + 2][j + 4][k + 0] = Blockizer.DegradedSteel;
		chunk[i + 2][j + 3][k + 4] = Blockizer.DegradedSteel;
		chunk[i + 2][j + 4][k + 4] = Blockizer.DegradedSteel;
		chunk[i + 0][j + 3][k + 2] = Blockizer.DegradedSteel;
		chunk[i + 0][j + 4][k + 2] = Blockizer.DegradedSteel;
		chunk[i + 4][j + 3][k + 2] = Blockizer.DegradedSteel;
		chunk[i + 4][j + 4][k + 2] = Blockizer.DegradedSteel;
	}
}
