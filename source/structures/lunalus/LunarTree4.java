package net.nevermine.structures.lunalus;

import net.minecraft.block.Block;
import net.nevermine.izer.Blockizer;
import net.nevermine.structures.voxponds.IVoxpondsStructure;

public class LunarTree4 implements IVoxpondsStructure {
	@Override
	public void generate(final Block[][][] chunk, final int i, final int j, final int k) {
		chunk[i + 0][j + 5][k + 2] = Blockizer.LeavesLunosso;
		chunk[i + 0][j + 6][k + 2] = Blockizer.LeavesLunosso;
		chunk[i + 0][j + 7][k + 2] = Blockizer.LeavesLunosso;
		chunk[i + 0][j + 8][k + 2] = Blockizer.LeavesLunosso;
		chunk[i + 1][j + 5][k + 2] = Blockizer.LeavesLunosso;
		chunk[i + 1][j + 8][k + 2] = Blockizer.LeavesLunosso;
		chunk[i + 2][j + 0][k + 2] = Blockizer.WoodLunide;
		chunk[i + 2][j + 1][k + 2] = Blockizer.WoodLunide;
		chunk[i + 2][j + 2][k + 2] = Blockizer.WoodLunide;
		chunk[i + 2][j + 3][k + 2] = Blockizer.WoodLunide;
		chunk[i + 2][j + 5][k + 0] = Blockizer.LeavesLunosso;
		chunk[i + 2][j + 5][k + 1] = Blockizer.LeavesLunosso;
		chunk[i + 2][j + 5][k + 2] = Blockizer.LeavesLunosso;
		chunk[i + 2][j + 5][k + 3] = Blockizer.LeavesLunosso;
		chunk[i + 2][j + 5][k + 4] = Blockizer.LeavesLunosso;
		chunk[i + 2][j + 6][k + 0] = Blockizer.LeavesLunosso;
		chunk[i + 2][j + 6][k + 4] = Blockizer.LeavesLunosso;
		chunk[i + 2][j + 7][k + 0] = Blockizer.LeavesLunosso;
		chunk[i + 2][j + 7][k + 4] = Blockizer.LeavesLunosso;
		chunk[i + 2][j + 8][k + 0] = Blockizer.LeavesLunosso;
		chunk[i + 2][j + 8][k + 1] = Blockizer.LeavesLunosso;
		chunk[i + 2][j + 8][k + 2] = Blockizer.LeavesLunosso;
		chunk[i + 2][j + 8][k + 3] = Blockizer.LeavesLunosso;
		chunk[i + 2][j + 8][k + 4] = Blockizer.LeavesLunosso;
		chunk[i + 3][j + 5][k + 2] = Blockizer.LeavesLunosso;
		chunk[i + 3][j + 8][k + 2] = Blockizer.LeavesLunosso;
		chunk[i + 4][j + 5][k + 2] = Blockizer.LeavesLunosso;
		chunk[i + 4][j + 6][k + 2] = Blockizer.LeavesLunosso;
		chunk[i + 4][j + 7][k + 2] = Blockizer.LeavesLunosso;
		chunk[i + 4][j + 8][k + 2] = Blockizer.LeavesLunosso;
	}
}
