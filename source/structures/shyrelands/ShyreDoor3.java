package net.nevermine.structures.shyrelands;

import net.minecraft.block.Block;
import net.nevermine.izer.Blockizer;
import net.nevermine.structures.voxponds.IVoxpondsStructure;

public class ShyreDoor3 implements IVoxpondsStructure {
	public void generate(Block[][][] chunk, int x, int y, int z) {
		chunk[x][y][z] = Blockizer.ShyreWallWhite;
		chunk[x][y][z + 7] = Blockizer.ShyreWallWhite;
		chunk[x][y + 1][z] = Blockizer.ShyreWallWhite;
		chunk[x][y + 1][z + 7] = Blockizer.ShyreWallWhite;
		chunk[x][y + 2][z] = Blockizer.ShyreWallWhite;
		chunk[x][y + 2][z + 7] = Blockizer.ShyreWallWhite;
		chunk[x][y + 3][z] = Blockizer.ShyreWallWhite;
		chunk[x][y + 3][z + 7] = Blockizer.ShyreWallWhite;
		chunk[x][y + 4][z] = Blockizer.ShyreWallWhite;
		chunk[x][y + 4][z + 7] = Blockizer.ShyreWallWhite;
		chunk[x][y + 5][z] = Blockizer.ShyreWallWhite;
		chunk[x][y + 5][z + 7] = Blockizer.ShyreWallWhite;
		chunk[x][y + 6][z] = Blockizer.ShyreWallWhite;
		chunk[x][y + 6][z + 7] = Blockizer.ShyreWallWhite;
		chunk[x][y + 7][z] = Blockizer.ShyreWallWhite;
		chunk[x][y + 7][z + 7] = Blockizer.ShyreWallWhite;
		chunk[x][y + 8][z] = Blockizer.ShyreWallWhite;
		chunk[x][y + 8][z + 7] = Blockizer.ShyreWallWhite;
		chunk[x][y + 9][z] = Blockizer.ShyreWallWhite;
		chunk[x][y + 9][z + 7] = Blockizer.ShyreWallWhite;
		chunk[x][y + 10][z] = Blockizer.ShyreWallWhite;
		chunk[x][y + 10][z + 7] = Blockizer.ShyreWallWhite;
		chunk[x][y + 11][z] = Blockizer.ShyreWallWhite;
		chunk[x][y + 11][z + 1] = Blockizer.ShyreWallWhite;
		chunk[x][y + 11][z + 2] = Blockizer.ShyreWallWhite;
		chunk[x][y + 11][z + 5] = Blockizer.ShyreWallWhite;
		chunk[x][y + 11][z + 6] = Blockizer.ShyreWallWhite;
		chunk[x][y + 11][z + 7] = Blockizer.ShyreWallWhite;
		chunk[x][y + 12][z] = Blockizer.ShyreWallWhite;
		chunk[x][y + 12][z + 1] = Blockizer.ShyreGlass;
		chunk[x][y + 12][z + 2] = Blockizer.ShyreWallWhite;
		chunk[x][y + 12][z + 3] = Blockizer.ShyreWallWhite;
		chunk[x][y + 12][z + 4] = Blockizer.ShyreWallWhite;
		chunk[x][y + 12][z + 5] = Blockizer.ShyreWallWhite;
		chunk[x][y + 12][z + 6] = Blockizer.ShyreGlass;
		chunk[x][y + 12][z + 7] = Blockizer.ShyreWallWhite;
		chunk[x][y + 13][z] = Blockizer.ShyreWallWhite;
		chunk[x][y + 13][z + 1] = Blockizer.ShyreGlass;
		chunk[x][y + 13][z + 2] = Blockizer.ShyreWallWhite;
		chunk[x][y + 13][z + 3] = Blockizer.ShyreGlass;
		chunk[x][y + 13][z + 4] = Blockizer.ShyreGlass;
		chunk[x][y + 13][z + 5] = Blockizer.ShyreWallWhite;
		chunk[x][y + 13][z + 6] = Blockizer.ShyreGlass;
		chunk[x][y + 13][z + 7] = Blockizer.ShyreWallWhite;
		chunk[x][y + 14][z] = Blockizer.ShyreWallWhite;
		chunk[x][y + 14][z + 1] = Blockizer.ShyreWallWhite;
		chunk[x][y + 14][z + 2] = Blockizer.ShyreWallWhite;
		chunk[x][y + 14][z + 3] = Blockizer.ShyreWallWhite;
		chunk[x][y + 14][z + 4] = Blockizer.ShyreWallWhite;
		chunk[x][y + 14][z + 5] = Blockizer.ShyreWallWhite;
		chunk[x][y + 14][z + 6] = Blockizer.ShyreWallWhite;
		chunk[x][y + 14][z + 7] = Blockizer.ShyreWallWhite;
	}
}
