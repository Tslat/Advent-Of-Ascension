package net.nevermine.structures.lunalus;

import net.minecraft.block.Block;
import net.nevermine.izer.Blockizer;
import net.nevermine.structures.voxponds.IVoxpondsStructure;

public class LunarTree2 implements IVoxpondsStructure {
	@Override
	public void generate(final Block[][][] chunk, final int i, final int j, final int k) {
		chunk[i + 0][j + 6][k + 2] = Blockizer.LeavesLunicia;
		chunk[i + 1][j + 6][k + 1] = Blockizer.LeavesLunicia;
		chunk[i + 1][j + 6][k + 2] = Blockizer.LeavesLunicia;
		chunk[i + 1][j + 6][k + 3] = Blockizer.LeavesLunicia;
		chunk[i + 1][j + 7][k + 2] = Blockizer.LeavesLunicia;
		chunk[i + 2][j + 0][k + 2] = Blockizer.WoodLunide;
		chunk[i + 2][j + 1][k + 2] = Blockizer.WoodLunide;
		chunk[i + 2][j + 2][k + 2] = Blockizer.WoodLunide;
		chunk[i + 2][j + 3][k + 2] = Blockizer.WoodLunide;
		chunk[i + 2][j + 4][k + 2] = Blockizer.WoodLunide;
		chunk[i + 2][j + 6][k + 0] = Blockizer.LeavesLunicia;
		chunk[i + 2][j + 6][k + 1] = Blockizer.LeavesLunicia;
		chunk[i + 2][j + 6][k + 2] = Blockizer.LeavesLunicia;
		chunk[i + 2][j + 6][k + 3] = Blockizer.LeavesLunicia;
		chunk[i + 2][j + 6][k + 4] = Blockizer.LeavesLunicia;
		chunk[i + 2][j + 7][k + 1] = Blockizer.LeavesLunicia;
		chunk[i + 2][j + 7][k + 2] = Blockizer.LeavesLunicia;
		chunk[i + 2][j + 7][k + 3] = Blockizer.LeavesLunicia;
		chunk[i + 2][j + 8][k + 2] = Blockizer.LeavesLunicia;
		chunk[i + 3][j + 6][k + 1] = Blockizer.LeavesLunicia;
		chunk[i + 3][j + 6][k + 2] = Blockizer.LeavesLunicia;
		chunk[i + 3][j + 6][k + 3] = Blockizer.LeavesLunicia;
		chunk[i + 3][j + 7][k + 2] = Blockizer.LeavesLunicia;
		chunk[i + 4][j + 6][k + 2] = Blockizer.LeavesLunicia;
	}
}
