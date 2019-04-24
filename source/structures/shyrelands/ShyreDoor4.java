package net.nevermine.structures.shyrelands;

import net.minecraft.block.Block;
import net.nevermine.izer.Blockizer;
import net.nevermine.structures.voxponds.IVoxpondsStructure;

public class ShyreDoor4 implements IVoxpondsStructure {
	public void generate(Block[][][] chunk, int i, int j, int k) {
		chunk[i][j][k] = Blockizer.ShyreWallWhite;
		chunk[i][j][k + 7] = Blockizer.ShyreWallWhite;
		chunk[i][j + 1][k] = Blockizer.ShyreWallWhite;
		chunk[i][j + 1][k + 7] = Blockizer.ShyreWallWhite;
		chunk[i][j + 2][k] = Blockizer.ShyreWallWhite;
		chunk[i][j + 2][k + 7] = Blockizer.ShyreWallWhite;
		chunk[i][j + 3][k] = Blockizer.ShyreWallWhite;
		chunk[i][j + 3][k + 7] = Blockizer.ShyreWallWhite;
		chunk[i][j + 4][k] = Blockizer.ShyreWallWhite;
		chunk[i][j + 4][k + 7] = Blockizer.ShyreWallWhite;
		chunk[i][j + 5][k] = Blockizer.ShyreWallWhite;
		chunk[i][j + 5][k + 7] = Blockizer.ShyreWallWhite;
		chunk[i][j + 6][k] = Blockizer.ShyreWallWhite;
		chunk[i][j + 6][k + 7] = Blockizer.ShyreWallWhite;
		chunk[i][j + 7][k] = Blockizer.ShyreWallWhite;
		chunk[i][j + 7][k + 7] = Blockizer.ShyreWallWhite;
		chunk[i][j + 8][k] = Blockizer.ShyreWallWhite;
		chunk[i][j + 8][k + 7] = Blockizer.ShyreWallWhite;
		chunk[i][j + 9][k] = Blockizer.ShyreWallWhite;
		chunk[i][j + 9][k + 7] = Blockizer.ShyreWallWhite;
		chunk[i][j + 10][k] = Blockizer.ShyreWallWhite;
		chunk[i][j + 10][k + 1] = Blockizer.ShyreWallWhite;
		chunk[i][j + 10][k + 2] = Blockizer.ShyreWallWhite;
		chunk[i][j + 10][k + 5] = Blockizer.ShyreWallWhite;
		chunk[i][j + 10][k + 6] = Blockizer.ShyreWallWhite;
		chunk[i][j + 10][k + 7] = Blockizer.ShyreWallWhite;
		chunk[i][j + 11][k] = Blockizer.ShyreWallWhite;
		chunk[i][j + 11][k + 1] = Blockizer.ShyreGlass;
		chunk[i][j + 11][k + 2] = Blockizer.ShyreWallWhite;
		chunk[i][j + 11][k + 5] = Blockizer.ShyreWallWhite;
		chunk[i][j + 11][k + 6] = Blockizer.ShyreGlass;
		chunk[i][j + 11][k + 7] = Blockizer.ShyreWallWhite;
		chunk[i][j + 12][k] = Blockizer.ShyreWallWhite;
		chunk[i][j + 12][k + 1] = Blockizer.ShyreGlass;
		chunk[i][j + 12][k + 2] = Blockizer.ShyreWallWhite;
		chunk[i][j + 12][k + 3] = Blockizer.ShyreWallWhite;
		chunk[i][j + 12][k + 4] = Blockizer.ShyreWallWhite;
		chunk[i][j + 12][k + 5] = Blockizer.ShyreWallWhite;
		chunk[i][j + 12][k + 6] = Blockizer.ShyreGlass;
		chunk[i][j + 12][k + 7] = Blockizer.ShyreWallWhite;
		chunk[i][j + 13][k] = Blockizer.ShyreWallWhite;
		chunk[i][j + 13][k + 1] = Blockizer.ShyreGlass;
		chunk[i][j + 13][k + 2] = Blockizer.ShyreGlass;
		chunk[i][j + 13][k + 3] = Blockizer.ShyreGlass;
		chunk[i][j + 13][k + 4] = Blockizer.ShyreGlass;
		chunk[i][j + 13][k + 5] = Blockizer.ShyreGlass;
		chunk[i][j + 13][k + 6] = Blockizer.ShyreGlass;
		chunk[i][j + 13][k + 7] = Blockizer.ShyreWallWhite;
		chunk[i][j + 14][k] = Blockizer.ShyreWallWhite;
		chunk[i][j + 14][k + 1] = Blockizer.ShyreWallWhite;
		chunk[i][j + 14][k + 2] = Blockizer.ShyreWallWhite;
		chunk[i][j + 14][k + 3] = Blockizer.ShyreWallWhite;
		chunk[i][j + 14][k + 4] = Blockizer.ShyreWallWhite;
		chunk[i][j + 14][k + 5] = Blockizer.ShyreWallWhite;
		chunk[i][j + 14][k + 6] = Blockizer.ShyreWallWhite;
		chunk[i][j + 14][k + 7] = Blockizer.ShyreWallWhite;
	}
}
