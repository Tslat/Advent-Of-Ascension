package net.nevermine.structures.voxponds;

import net.minecraft.block.Block;
import net.nevermine.izer.Blockizer;

public class ToxicStem4 implements IVoxpondsStructure {
	@Override
	public void generate(final Block[][][] chunk, final int i, final int j, final int k) {
		chunk[i + 0][j + 0][k + 0] = Blockizer.ToxicStem;
		chunk[i + 0][j + 1][k + 0] = Blockizer.ToxicStem;
		chunk[i + 0][j + 2][k + 0] = Blockizer.ToxicStem;
		chunk[i + 0][j + 2][k + 1] = Blockizer.ToxicStem;
		chunk[i + 0][j + 3][k + 1] = Blockizer.ToxicStem;
	}
}
