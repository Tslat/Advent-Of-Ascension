package net.nevermine.structures.voxponds;

import net.minecraft.block.Block;
import net.nevermine.izer.SpecialBlockizer;

public class VoxBranch1 implements IVoxpondsStructure {
	@Override
	public void generate(final Block[][][] chunk, final int i, final int j, final int k) {
		chunk[i + 0][j + 0][k + 0] = SpecialBlockizer.VoxLog2;
		chunk[i + 0][j + 0][k + 1] = SpecialBlockizer.VoxLog2;
		chunk[i + 0][j + 0][k + 2] = SpecialBlockizer.VoxLog2;
		chunk[i + 0][j + 0][k + 3] = SpecialBlockizer.VoxLog2;
	}
}
