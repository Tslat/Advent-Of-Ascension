package net.nevermine.structures.labricon;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.nevermine.structures.voxponds.IVoxpondsStructure;

public class DoorWide implements IVoxpondsStructure {
	@Override
	public void generate(final Block[][][] chunk, final int i, final int j, final int k) {
		chunk[i + 0][j + 0][k + 0] = Blocks.air;
		chunk[i + 0][j + 0][k + 1] = Blocks.air;
		chunk[i + 0][j + 1][k + 0] = Blocks.air;
		chunk[i + 0][j + 1][k + 1] = Blocks.air;
		chunk[i + 0][j + 2][k + 0] = Blocks.air;
		chunk[i + 0][j + 2][k + 1] = Blocks.air;
	}
}
