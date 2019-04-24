package net.nevermine.dimension.lelyetia;

import net.minecraft.block.Block;
import net.nevermine.izer.Blockizer;
import net.nevermine.structures.lelyetia.*;
import net.nevermine.structures.voxponds.IVoxpondsStructure;

import java.util.Random;

public class LelyetiaChunkBuilder {
	private static Random rand;
	private static IVoxpondsStructure AchonyTree1;
	private static IVoxpondsStructure AchonyTree2;
	private static IVoxpondsStructure AchonyTreeU1;
	private static IVoxpondsStructure AchonyTreeU2;
	private static IVoxpondsStructure ChurryTree1;
	private static IVoxpondsStructure ChurryTree2;
	private static IVoxpondsStructure ChurryTreeU1;
	private static IVoxpondsStructure ChurryTreeU2;
	private static IVoxpondsStructure LelyetiaHole1;
	private static IVoxpondsStructure LelyetiaHole2;
	private static IVoxpondsStructure LelyetiaHole3;
	private static IVoxpondsStructure LelyetiaHole4;
	private static IVoxpondsStructure LelyetiaHole5;
	private static IVoxpondsStructure LelyetiaHole6;
	private static IVoxpondsStructure LelyetiaHole7;
	private static IVoxpondsStructure Pillar;
	private static IVoxpondsStructure StemUP;
	private static IVoxpondsStructure StemDown;
	private static IVoxpondsStructure WigglerUP;
	private static IVoxpondsStructure WigglerDown;
	private int achonyUP;
	private int achonyDOWN;
	private int churryUP;
	private int churryDOWN;
	private boolean genning;

	public LelyetiaChunkBuilder() {
		achonyUP = 0;
		achonyDOWN = 0;
		churryUP = 0;
		churryDOWN = 0;
	}

	public Block[][][] buildChunk() {
		final Block[][][] chunk = new Block[16][256][16];
		genning = (LelyetiaChunkBuilder.rand.nextInt(11) < 7);
		for (int x = 0; x < 16; ++x) {
			for (int y = 0; y < 65; ++y) {
				for (int z = 0; z < 16; ++z) {
					if (genning) {
						if (y > 55 && y < 59) {
							chunk[x][y][z] = Blockizer.StoneLelyetia;
						}
						if (y == 55) {
							chunk[x][y][z] = Blockizer.GrassLelyetiaDown;
						}
						if (y == 59) {
							chunk[x][y][z] = Blockizer.GrassLelyetiaUp;
						}
						if (LelyetiaChunkBuilder.rand.nextInt(24) == 7 && y == 54) {
							if (LelyetiaChunkBuilder.rand.nextInt(2) == 1) {
								LelyetiaChunkBuilder.StemDown.generate(chunk, x, y, z);
							}
							else {
								LelyetiaChunkBuilder.WigglerDown.generate(chunk, x, y, z);
							}
						}
						if (y == 3 && LelyetiaChunkBuilder.rand.nextInt(75) == 41 && churryDOWN < 2 && x < 8 && z < 8) {
							if (LelyetiaChunkBuilder.rand.nextInt(2) == 1) {
								LelyetiaChunkBuilder.ChurryTreeU1.generate(chunk, x, y + 12, z);
							}
							else {
								LelyetiaChunkBuilder.ChurryTreeU2.generate(chunk, x, y, z);
							}
							++churryDOWN;
						}
						if (y == 30 && LelyetiaChunkBuilder.rand.nextInt(75) == 41 && achonyDOWN < 2 && x < 10 && z < 10) {
							if (LelyetiaChunkBuilder.rand.nextInt(2) == 1) {
								LelyetiaChunkBuilder.AchonyTreeU1.generate(chunk, x, y, z);
							}
							else {
								LelyetiaChunkBuilder.AchonyTreeU2.generate(chunk, x, y - 7, z);
							}
							++achonyDOWN;
						}
					}
					else if (x == 0 && y == 55 && z == 0) {
						final int pick = LelyetiaChunkBuilder.rand.nextInt(7);
						if (pick == 1) {
							LelyetiaChunkBuilder.LelyetiaHole1.generate(chunk, x, y, z);
						}
						else if (pick == 2) {
							LelyetiaChunkBuilder.LelyetiaHole2.generate(chunk, x, y, z);
						}
						else if (pick == 3) {
							LelyetiaChunkBuilder.LelyetiaHole3.generate(chunk, x, y, z);
						}
						else if (pick == 4) {
							LelyetiaChunkBuilder.LelyetiaHole4.generate(chunk, x, y, z);
						}
						else if (pick == 5) {
							LelyetiaChunkBuilder.LelyetiaHole5.generate(chunk, x, y, z);
						}
						else if (pick == 6) {
							LelyetiaChunkBuilder.LelyetiaHole6.generate(chunk, x, y, z);
						}
						else {
							LelyetiaChunkBuilder.LelyetiaHole7.generate(chunk, x, y, z);
						}
					}
					if (LelyetiaChunkBuilder.rand.nextInt(5) == 3 && y > 50 && chunk[x][y + 1][z] == Blockizer.GrassLelyetiaDown && chunk[x][y][z] == null) {
						chunk[x][y][z] = Blockizer.LelyetianGrassDown;
					}
					if (LelyetiaChunkBuilder.rand.nextInt(5) == 3 && y > 50 && chunk[x][y - 1][z] == Blockizer.GrassLelyetiaUp && chunk[x][y][z] == null) {
						chunk[x][y][z] = Blockizer.LelyetianGrassUp;
					}
					if (LelyetiaChunkBuilder.rand.nextInt(24) == 7 && y == 60 && chunk[x][y - 1][z] == Blockizer.GrassLelyetiaUp) {
						if (LelyetiaChunkBuilder.rand.nextInt(2) == 1) {
							LelyetiaChunkBuilder.StemUP.generate(chunk, x, y, z);
						}
						else {
							LelyetiaChunkBuilder.WigglerUP.generate(chunk, x, y, z);
						}
					}
					if (y > 50 && LelyetiaChunkBuilder.rand.nextInt(75) == 41 && churryUP < 2 && x > 4 && z > 4 && x < 12 && z < 12 && chunk[x][y - 1][z] == Blockizer.GrassLelyetiaUp) {
						if (LelyetiaChunkBuilder.rand.nextInt(2) == 1) {
							LelyetiaChunkBuilder.ChurryTree1.generate(chunk, x - 4, y, z - 4);
						}
						else {
							LelyetiaChunkBuilder.ChurryTree2.generate(chunk, x - 4, y, z - 4);
						}
						++churryUP;
					}
					if (y == 60 && LelyetiaChunkBuilder.rand.nextInt(6000) == 760 && x > 4 && z > 4 && x < 11 && z < 11 && chunk[x][y - 1][z] == Blockizer.GrassLelyetiaUp) {
						LelyetiaChunkBuilder.Pillar.generate(chunk, x - 3, y, z - 3);
					}
					if (y > 50 && LelyetiaChunkBuilder.rand.nextInt(75) == 41 && achonyUP < 2 && x > 2 && z > 2 && x < 13 && z < 13 && chunk[x][y - 1][z] == Blockizer.GrassLelyetiaUp) {
						if (LelyetiaChunkBuilder.rand.nextInt(2) == 1) {
							LelyetiaChunkBuilder.AchonyTree1.generate(chunk, x - 3, y, z - 3);
						}
						else {
							LelyetiaChunkBuilder.AchonyTree2.generate(chunk, x - 3, y, z - 3);
						}
						++achonyUP;
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
		LelyetiaChunkBuilder.rand = new Random();
		LelyetiaChunkBuilder.AchonyTree1 = new AchonyTree1();
		LelyetiaChunkBuilder.AchonyTree2 = new AchonyTree2();
		LelyetiaChunkBuilder.AchonyTreeU1 = new AchonyTreeU1();
		LelyetiaChunkBuilder.AchonyTreeU2 = new AchonyTreeU2();
		LelyetiaChunkBuilder.ChurryTree1 = new ChurryTree1();
		LelyetiaChunkBuilder.ChurryTree2 = new ChurryTree2();
		LelyetiaChunkBuilder.ChurryTreeU1 = new ChurryTreeU1();
		LelyetiaChunkBuilder.ChurryTreeU2 = new ChurryTreeU2();
		LelyetiaChunkBuilder.LelyetiaHole1 = new LelyetiaHole1();
		LelyetiaChunkBuilder.LelyetiaHole2 = new LelyetiaHole2();
		LelyetiaChunkBuilder.LelyetiaHole3 = new LelyetiaHole3();
		LelyetiaChunkBuilder.LelyetiaHole4 = new LelyetiaHole4();
		LelyetiaChunkBuilder.LelyetiaHole5 = new LelyetiaHole5();
		LelyetiaChunkBuilder.LelyetiaHole6 = new LelyetiaHole6();
		LelyetiaChunkBuilder.LelyetiaHole7 = new LelyetiaHole7();
		LelyetiaChunkBuilder.Pillar = new GrawPillar();
		LelyetiaChunkBuilder.StemUP = new StemUP();
		LelyetiaChunkBuilder.StemDown = new StemDOWN();
		LelyetiaChunkBuilder.WigglerUP = new WigglerUP();
		LelyetiaChunkBuilder.WigglerDown = new WigglerDOWN();
	}
}
