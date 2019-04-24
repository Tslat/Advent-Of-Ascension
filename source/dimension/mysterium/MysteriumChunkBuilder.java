package net.nevermine.dimension.mysterium;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.nevermine.izer.Blockizer;

import java.util.Random;

public class MysteriumChunkBuilder {
	private static Random rand;
	private boolean hole;
	private int pick;

	public Block[][][] buildChunk() {
		hole = MysteriumChunkBuilder.rand.nextInt(3) == 1;
		final Block[][][] chunk = new Block[16][256][16];
		for (int x = 0; x < 16; ++x) {
			for (int y = 0; y < 50; ++y) {
				for (int z = 0; z < 16; ++z) {
					if (hole) {
						if (y <= 10) {
							chunk[x][y][z] = Blockizer.StoneMysterium;
						}
						else if (y == 11 || y == 12) {
							chunk[x][y][z] = Blocks.water;
						}
					}
					else if (x == 0 || z == 0 || x == 15 || z == 15) {
						if (y <= 20) {
							chunk[x][y][z] = Blockizer.StoneMysterium;
						}
						else if (y == 21) {
							chunk[x][y][z] = Blockizer.DirtMysterium;
						}
						else if (y == 22) {
							chunk[x][y][z] = Blockizer.GrassMysterium;
						}
						else if (y == 23) {
							pick = MysteriumChunkBuilder.rand.nextInt(10);
							if (pick == 1) {
								chunk[x][y][z] = Blockizer.MysticBush;
							}
							else if (pick == 2) {
								chunk[x][y][z] = Blockizer.MysticFerns;
							}
							else if (pick > 6) {
								pick = MysteriumChunkBuilder.rand.nextInt(3);
								if (pick == 1) {
									chunk[x][y][z] = Blockizer.RainbowGrass;
								}
								else if (pick == 2) {
									chunk[x][y][z] = Blockizer.RainbowGrass2;
								}
								else {
									chunk[x][y][z] = Blockizer.RainbowGrass3;
								}
							}
						}
					}
					else if (x == 1 || z == 1 || x == 14 || z == 14) {
						if (y <= 20) {
							chunk[x][y][z] = Blockizer.StoneMysterium;
						}
						else if (y == 21 || y == 22) {
							chunk[x][y][z] = Blockizer.DirtMysterium;
						}
						else if (y == 23) {
							chunk[x][y][z] = Blockizer.GrassMysterium;
						}
						else if (y == 24) {
							pick = MysteriumChunkBuilder.rand.nextInt(10);
							if (pick == 1) {
								chunk[x][y][z] = Blockizer.MysticBush;
							}
							else if (pick == 2) {
								chunk[x][y][z] = Blockizer.MysticFerns;
							}
							else if (pick > 6) {
								pick = MysteriumChunkBuilder.rand.nextInt(3);
								if (pick == 1) {
									chunk[x][y][z] = Blockizer.RainbowGrass;
								}
								else if (pick == 2) {
									chunk[x][y][z] = Blockizer.RainbowGrass2;
								}
								else {
									chunk[x][y][z] = Blockizer.RainbowGrass3;
								}
							}
						}
					}
					else if (y <= 20) {
						chunk[x][y][z] = Blockizer.StoneMysterium;
					}
					else if (y == 21 || y == 22 || y == 23) {
						chunk[x][y][z] = Blockizer.DirtMysterium;
					}
					else if (y == 24) {
						chunk[x][y][z] = Blockizer.GrassMysterium;
					}
					else if (y == 25) {
						pick = MysteriumChunkBuilder.rand.nextInt(10);
						if (pick == 1) {
							chunk[x][y][z] = Blockizer.MysticBush;
						}
						else if (pick == 2) {
							chunk[x][y][z] = Blockizer.MysticFerns;
						}
						else if (pick > 6) {
							pick = MysteriumChunkBuilder.rand.nextInt(3);
							if (pick == 1) {
								chunk[x][y][z] = Blockizer.RainbowGrass;
							}
							else if (pick == 2) {
								chunk[x][y][z] = Blockizer.RainbowGrass2;
							}
							else {
								chunk[x][y][z] = Blockizer.RainbowGrass3;
							}
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
		MysteriumChunkBuilder.rand = new Random();
	}
}
