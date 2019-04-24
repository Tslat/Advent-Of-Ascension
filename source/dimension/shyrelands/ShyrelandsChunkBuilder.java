package net.nevermine.dimension.shyrelands;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.nevermine.izer.Blockizer;
import net.nevermine.structures.shyrelands.*;
import net.nevermine.structures.voxponds.IVoxpondsStructure;

import java.util.Random;

public class ShyrelandsChunkBuilder {
	private Random rand = new Random();
	private int pick;
	private static IVoxpondsStructure shyrePlantU = new ShyrePlantUP();
	private static IVoxpondsStructure shyrePlantD = new ShyrePlantDOWN();
	private static IVoxpondsStructure sTree1 = new ShyreTree1();
	private static IVoxpondsStructure sTree2 = new ShyreTree2();
	private static IVoxpondsStructure sTree3 = new ShyreTree3();
	private static IVoxpondsStructure sTree4 = new ShyreTree4();
	private static IVoxpondsStructure sd1 = new ShyreDoor1();
	private static IVoxpondsStructure sd2 = new ShyreDoor2();
	private static IVoxpondsStructure sd3 = new ShyreDoor3();
	private static IVoxpondsStructure sd4 = new ShyreDoor4();
	private static IVoxpondsStructure sd5 = new ShyreDoor5();
	private static IVoxpondsStructure sd6 = new ShyreDoor6();
	private static IVoxpondsStructure sd7 = new ShyreDoor7();
	private static IVoxpondsStructure sd8 = new ShyreDoor8();
	private static IVoxpondsStructure sd9 = new ShyreDoor9();
	private static IVoxpondsStructure sd10 = new ShyreDoor10();
	private static IVoxpondsStructure st1 = new ShyreStructure1();
	private static IVoxpondsStructure st2 = new ShyreStructure2();
	private static IVoxpondsStructure st3 = new ShyreStructure3();
	private static IVoxpondsStructure st4 = new ShyreStructure4();
	private static IVoxpondsStructure st5 = new ShyreStructure5();
	private static IVoxpondsStructure st6 = new ShyreStructure6();
	private static IVoxpondsStructure st7 = new ShyreStructure7();
	private boolean isRoom = false;

	public Block[][][] buildChunk() {
		if (this.rand.nextInt(25) == 3) {
			this.isRoom = true;
		}
		Block[][][] chunk = new Block[16][256][16];
		for (int x = 0; x < 16; ++x) {
			for (int z = 0; z < 16; ++z) {
				for (int y = 0; y < 65; ++y) {
					if (y > 30) {
						if (y == 64 && this.rand.nextInt(3) == 2) {
							chunk[x][y][z] = Blockizer.ShyreCloud;
						}
						if (x == 0 || z == 0 || x == 15 || z == 15) {
							if ((x == 0 || x == 15) && y == 31 && z == 4) {
								this.pick = this.rand.nextInt(5);
								if (this.pick == 1) {
									sd1.generate(chunk, x, y - 1, z);
								}
								else if (this.pick == 2) {
									sd2.generate(chunk, x, y - 1, z);
								}
								else if (this.pick == 3) {
									sd3.generate(chunk, x, y - 1, z);
								}
								else if (this.pick == 4) {
									sd4.generate(chunk, x, y - 1, z);
								}
								else {
									sd5.generate(chunk, x, y - 1, z);
								}
							}
							else if ((z == 0 || z == 15) && y == 31 && x == 4) {
								this.pick = this.rand.nextInt(5);
								if (this.pick == 1) {
									sd6.generate(chunk, x, y - 1, z);
								}
								else if (this.pick == 2) {
									sd7.generate(chunk, x, y - 1, z);
								}
								else if (this.pick == 3) {
									sd8.generate(chunk, x, y - 1, z);
								}
								else if (this.pick == 4) {
									sd9.generate(chunk, x, y - 1, z);
								}
								else {
									sd10.generate(chunk, x, y - 1, z);
								}
							}
							if (y <= 45) {
								if ((x >= 4 && x <= 11 || z != 0 && z != 15) && (z >= 4 && z <= 11 || x != 0 && x != 15) && y != 45)
									continue;
								if (this.rand.nextInt(12) == 3) {
									chunk[x][y][z] = Blockizer.ShyreGlass;
									continue;
								}
								chunk[x][y][z] = Blockizer.ShyreWall;
								continue;
							}
							if ((x == 0 && z == 0 || x == 15 && z == 0 || x == 0 && z == 15 || x == 15 && z == 15) && y < 56) {
								chunk[x][y][z] = Blockizer.ShyreWallWhite;
								continue;
							}
							if (y == 55) {
								chunk[x][y][z] = Blockizer.ShyreWallWhite;
								continue;
							}
							if (y >= 55)
								continue;
							chunk[x][y][z] = Blockizer.ShyreGlass;
							continue;
						}
						if (y == 54 && this.rand.nextInt(25) == 3) {
							shyrePlantD.generate(chunk, x, y, z);
							chunk[x][y + 1][z] = Blockizer.ShyreGlass;
						}
						if (this.isRoom) {
							if (y != 31 || x != 1 || z != 1)
								continue;
							this.pick = this.rand.nextInt(7);
							if (this.pick == 1) {
								st1.generate(chunk, x, y, z);
								continue;
							}
							if (this.pick == 2) {
								st2.generate(chunk, x, y, z);
								continue;
							}
							if (this.pick == 3) {
								st3.generate(chunk, x, y, z);
								continue;
							}
							if (this.pick == 4) {
								st4.generate(chunk, x, y, z);
								continue;
							}
							if (this.pick == 5) {
								st5.generate(chunk, x, y, z);
								continue;
							}
							if (this.pick == 6) {
								st6.generate(chunk, x, y, z);
								continue;
							}
							st7.generate(chunk, x, y, z);
							continue;
						}
						if (this.rand.nextInt(4) == 1 && y == 31 && chunk[x][y][z] == null) {
							this.pick = this.rand.nextInt(5);
							if (this.pick == 1) {
								chunk[x][y][z] = Blockizer.ShyreFlowers1;
							}
							else if (this.pick == 2) {
								chunk[x][y][z] = Blockizer.ShyreFlowers2;
							}
							else if (this.pick == 3) {
								chunk[x][y][z] = Blockizer.ShyreFlowers3;
							}
							else if (this.pick == 4) {
								if (this.rand.nextInt(3) == 1) {
									shyrePlantU.generate(chunk, x, y, z);
								}
							}
							else {
								chunk[x][y][z] = Blockizer.ShyreFlowers4;
							}
						}
						if (this.rand.nextInt(45) != 13 || y != 31 || x <= 1 || z <= 1 || x >= 13 || z >= 13)
							continue;
						this.pick = this.rand.nextInt(4);
						if (this.pick == 1) {
							sTree1.generate(chunk, x, y, z);
							continue;
						}
						if (this.pick == 2) {
							sTree2.generate(chunk, x, y, z);
							continue;
						}
						if (this.pick == 3) {
							sTree3.generate(chunk, x, y, z);
							continue;
						}
						sTree4.generate(chunk, x, y, z);
						continue;
					}
					if (y == 30) {
						if (x == 0 || z == 0 || x == 15 || z == 15) {
							chunk[x][y][z] = Blockizer.ShyreWallWhite;
							continue;
						}
						chunk[x][y][z] = Blockizer.ShyreGrass;
						continue;
					}
					if (y == 1) {
						chunk[x][y][z] = Blocks.bedrock;
						continue;
					}
					if (this.rand.nextInt(400) == 35) {
						if (this.rand.nextInt(8) == 1) {
							chunk[x][y][z] = Blockizer.ShyreGems;
							continue;
						}
						chunk[x][y][z] = Blockizer.ShyreStones;
						continue;
					}
					chunk[x][y][z] = Blockizer.ShyreRock;
				}
			}
		}
		return chunk;
	}

	public static void toTerrainArray(Block[][][] chunk, Block[] output) {
		for (int x = 0; x < 16; ++x) {
			for (int y = 0; y < 256; ++y) {
				for (int z = 0; z < 16; ++z) {
					output[z << 12 | x << 8 | y] = chunk[x][y][z];
				}
			}
		}
	}
}

