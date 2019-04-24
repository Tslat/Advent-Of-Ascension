package net.nevermine.dimension.runandor;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.nevermine.izer.Blockizer;

import java.util.Random;

public class RunandorChunkBuilder {
	private static Random rand;
	private int pick;

	public Block[][][] buildChunk(final int count) {
		final Block[][][] chunk = new Block[16][256][16];
		for (int x = 0; x < 16; ++x) {
			for (int y = 0; y < 40; ++y) {
				for (int z = 0; z < 16; ++z) {
					if (y <= 10 + count) {
						chunk[x][y][z] = Blockizer.RunicBrick;
					}
					else if (y == 11 + count) {
						chunk[x][y][z] = Blockizer.GrassRunic;
					}
					if (y < 16 && chunk[x][y][z] == null) {
						chunk[x][y][z] = Blocks.water;
					}
					if (y == 12 + count && RunandorChunkBuilder.rand.nextInt(3) == 1 && chunk[x][y][z] == null) {
						pick = RunandorChunkBuilder.rand.nextInt(3);
						if (pick == 1) {
							chunk[x][y][z] = Blockizer.RunicBush;
						}
						else if (pick == 2) {
							chunk[x][y][z] = Blockizer.RuneBulbs;
						}
						else {
							chunk[x][y][z] = Blockizer.Magias;
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
		RunandorChunkBuilder.rand = new Random();
	}
}
