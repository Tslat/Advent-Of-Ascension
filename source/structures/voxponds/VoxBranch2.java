package net.nevermine.structures.voxponds;

import net.minecraft.block.Block;
import net.nevermine.izer.SpecialBlockizer;

public class VoxBranch2 implements IVoxpondsStructure {
	@Override
	public void generate(final Block[][][] chunk, final int i, final int j, final int k) {
		chunk[i + 0][j + 0][k + 0] = SpecialBlockizer.VoxLog1;
		chunk[i + 1][j + 0][k + 0] = SpecialBlockizer.VoxLog1;
		chunk[i + 2][j + 0][k + 0] = SpecialBlockizer.VoxLog1;
		chunk[i + 3][j + 0][k + 0] = SpecialBlockizer.VoxLog1;
	}
}
