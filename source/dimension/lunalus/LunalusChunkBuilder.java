package net.nevermine.dimension.lunalus;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.nevermine.izer.Blockizer;
import net.nevermine.structures.lunalus.*;
import net.nevermine.structures.voxponds.IVoxpondsStructure;

import java.util.Random;

public class LunalusChunkBuilder {
	private static Random rand;
	private boolean islandLow;
	private boolean islandHigh;
	private int treelow;
	private int treehigh;
	private int pick;
	private static IVoxpondsStructure Island1;
	private static IVoxpondsStructure Island2;
	private static IVoxpondsStructure VisualentEye;
	private static IVoxpondsStructure Tree1;
	private static IVoxpondsStructure Tree2;
	private static IVoxpondsStructure Tree3;
	private static IVoxpondsStructure Tree4;

	public LunalusChunkBuilder() {
		treelow = 0;
		treehigh = 0;
	}

	public Block[][][] buildChunk() {
		final Block[][][] chunk = new Block[16][256][16];
		for (int x = 0; x < 16; ++x) {
			for (int y = 0; y < 80; ++y) {
				for (int z = 0; z < 16; ++z) {
					if (y == 4 || y == 5) {
						chunk[x][y][z] = Blocks.water;
					}
					if (!islandLow && y > 35 && y < 45 && x < 2 && z < 2 && LunalusChunkBuilder.rand.nextInt(45) == 5) {
						if (LunalusChunkBuilder.rand.nextInt(2) == 1) {
							LunalusChunkBuilder.Island1.generate(chunk, x, y, z);
						}
						else {
							LunalusChunkBuilder.Island2.generate(chunk, x, y, z);
						}
						islandLow = true;
					}
					if (!islandHigh && y > 60 && y < 70 && x < 2 && z < 2 && LunalusChunkBuilder.rand.nextInt(45) == 5) {
						if (LunalusChunkBuilder.rand.nextInt(2) == 1) {
							LunalusChunkBuilder.Island1.generate(chunk, x, y, z);
						}
						else {
							LunalusChunkBuilder.Island2.generate(chunk, x, y, z);
						}
						islandHigh = true;
					}
					if (y == 8 && x < 2 && z < 2 && LunalusChunkBuilder.rand.nextInt(1500) == 5) {
						LunalusChunkBuilder.VisualentEye.generate(chunk, x, y, z);
					}
					if (y > 0) {
						if (treelow < 2 && y < 63 && LunalusChunkBuilder.rand.nextInt(12) == 3 && x < 11 && z < 11 && x > 0 && z > 0 && (chunk[x + 2][y - 1][z + 2] == Blockizer.GrassLunasole || chunk[x + 2][y - 1][z + 2] == Blockizer.GrassLunalyte)) {
							pick = LunalusChunkBuilder.rand.nextInt(4);
							if (pick == 1) {
								LunalusChunkBuilder.Tree1.generate(chunk, x, y, z);
							}
							else if (pick == 2) {
								LunalusChunkBuilder.Tree2.generate(chunk, x, y, z);
							}
							else if (pick == 3) {
								LunalusChunkBuilder.Tree4.generate(chunk, x, y, z);
							}
							else {
								LunalusChunkBuilder.Tree3.generate(chunk, x, y, z);
							}
							++treelow;
						}
						if (treehigh < 2 && y > 64 && LunalusChunkBuilder.rand.nextInt(12) == 3 && x < 11 && z < 11 && x > 0 && z > 0 && (chunk[x + 2][y - 1][z + 2] == Blockizer.GrassLunasole || chunk[x + 2][y - 1][z + 2] == Blockizer.GrassLunalyte)) {
							pick = LunalusChunkBuilder.rand.nextInt(4);
							if (pick == 1) {
								LunalusChunkBuilder.Tree1.generate(chunk, x, y, z);
							}
							else if (pick == 2) {
								LunalusChunkBuilder.Tree2.generate(chunk, x, y, z);
							}
							else if (pick == 3) {
								LunalusChunkBuilder.Tree4.generate(chunk, x, y, z);
							}
							else {
								LunalusChunkBuilder.Tree3.generate(chunk, x, y, z);
							}
							++treehigh;
						}
					}
					if (y > 0 && (chunk[x][y - 1][z] == Blockizer.GrassLunasole || chunk[x][y - 1][z] == Blockizer.GrassLunalyte) && LunalusChunkBuilder.rand.nextInt(10) == 4 && chunk[x][y][z] == null) {
						if (LunalusChunkBuilder.rand.nextInt(2) == 1) {
							chunk[x][y][z] = Blockizer.Luntar;
						}
						else {
							chunk[x][y][z] = Blockizer.Lunalip;
						}
					}
				}
			}
		}
		return chunk;
	}

	public static void toTerrainArray(final Block[][][] chunk, final Block[] output) {
		for (int x = 0; x < 16; ++x) {
			for (int y = 0; y < 256; ++y) {
				for (int z = 0; z < 16; ++z) {
					output[z << 12 | x << 8 | y] = chunk[x][y][z];
				}
			}
		}
	}

	static {
		LunalusChunkBuilder.rand = new Random();
		LunalusChunkBuilder.Island1 = new LunarIsland1();
		LunalusChunkBuilder.Island2 = new LunarIsland2();
		LunalusChunkBuilder.VisualentEye = new VisualentEye();
		LunalusChunkBuilder.Tree1 = new LunarTree1();
		LunalusChunkBuilder.Tree2 = new LunarTree2();
		LunalusChunkBuilder.Tree3 = new LunarTree3();
		LunalusChunkBuilder.Tree4 = new LunarTree4();
	}
}
