package net.nevermine.dimension.labricon;

import net.minecraft.block.Block;
import net.nevermine.structures.labricon.DoorLong;
import net.nevermine.structures.labricon.DoorWide;
import net.nevermine.structures.labricon.shell.darkin.*;
import net.nevermine.structures.labricon.shell.eternal.*;
import net.nevermine.structures.labricon.shell.leveled.*;
import net.nevermine.structures.voxponds.IVoxpondsStructure;

import java.util.Random;

public class LabriconChunkBuilder {
	private static Random rand;
	private static IVoxpondsStructure Floor1;
	private static IVoxpondsStructure Floor2;
	private static IVoxpondsStructure Floor3;
	private static IVoxpondsStructure Floor4;
	private static IVoxpondsStructure Floor5;
	private static IVoxpondsStructure Floor6;
	private static IVoxpondsStructure Floor7;
	private static IVoxpondsStructure Shell1;
	private static IVoxpondsStructure Shell2;
	private static IVoxpondsStructure Shell3;
	private static IVoxpondsStructure Shell4;
	private static IVoxpondsStructure Shell5;
	private static IVoxpondsStructure Shell6;
	private static IVoxpondsStructure Shell7;
	private static IVoxpondsStructure Shell8;
	private static IVoxpondsStructure Floor1L;
	private static IVoxpondsStructure Floor2L;
	private static IVoxpondsStructure Floor3L;
	private static IVoxpondsStructure Floor4L;
	private static IVoxpondsStructure Floor5L;
	private static IVoxpondsStructure Floor6L;
	private static IVoxpondsStructure Floor7L;
	private static IVoxpondsStructure Shell1L;
	private static IVoxpondsStructure Shell2L;
	private static IVoxpondsStructure Shell3L;
	private static IVoxpondsStructure Shell4L;
	private static IVoxpondsStructure Shell5L;
	private static IVoxpondsStructure Shell6L;
	private static IVoxpondsStructure Shell7L;
	private static IVoxpondsStructure Shell8L;
	private static IVoxpondsStructure Floor1D;
	private static IVoxpondsStructure Floor2D;
	private static IVoxpondsStructure Floor3D;
	private static IVoxpondsStructure Floor4D;
	private static IVoxpondsStructure Floor5D;
	private static IVoxpondsStructure Floor6D;
	private static IVoxpondsStructure Floor7D;
	private static IVoxpondsStructure Shell1D;
	private static IVoxpondsStructure Shell2D;
	private static IVoxpondsStructure Shell3D;
	private static IVoxpondsStructure Shell4D;
	private static IVoxpondsStructure Shell5D;
	private static IVoxpondsStructure Shell6D;
	private static IVoxpondsStructure Shell7D;
	private static IVoxpondsStructure Shell8D;
	private static IVoxpondsStructure DoorWide;
	private static IVoxpondsStructure DoorLong;

	public Block[][][] buildChunk() {
		final Block[][][] chunk = new Block[16][256][16];
		for (int x = 0; x < 16; ++x) {
			for (int y = 0; y < 64; ++y) {
				for (int z = 0; z < 16; ++z) {
					if (y == 5 && x == 0 && z == 0) {
						final int floor = LabriconChunkBuilder.rand.nextInt(7);
						if (floor == 1) {
							LabriconChunkBuilder.Floor1D.generate(chunk, x, y, z);
						}
						else if (floor == 2) {
							LabriconChunkBuilder.Floor3D.generate(chunk, x, y, z);
						}
						else if (floor == 2) {
							LabriconChunkBuilder.Floor4D.generate(chunk, x, y, z);
						}
						else if (floor == 2) {
							LabriconChunkBuilder.Floor5D.generate(chunk, x, y, z);
						}
						else if (floor == 2) {
							LabriconChunkBuilder.Floor6D.generate(chunk, x, y, z);
						}
						else if (floor == 2) {
							LabriconChunkBuilder.Floor7D.generate(chunk, x, y, z);
						}
						else {
							LabriconChunkBuilder.Floor2D.generate(chunk, x, y, z);
						}
					}
					if (y == 6) {
						if (x == 0 && z == 0) {
							final int floor = LabriconChunkBuilder.rand.nextInt(8);
							if (floor == 1) {
								LabriconChunkBuilder.Shell1D.generate(chunk, x, y, z);
							}
							else if (floor == 2) {
								LabriconChunkBuilder.Shell3D.generate(chunk, x, y, z);
							}
							else if (floor == 2) {
								LabriconChunkBuilder.Shell4D.generate(chunk, x, y, z);
							}
							else if (floor == 2) {
								LabriconChunkBuilder.Shell5D.generate(chunk, x, y, z);
							}
							else if (floor == 2) {
								LabriconChunkBuilder.Shell6D.generate(chunk, x, y, z);
							}
							else if (floor == 2) {
								LabriconChunkBuilder.Shell7D.generate(chunk, x, y, z);
							}
							else if (floor == 2) {
								LabriconChunkBuilder.Shell8D.generate(chunk, x, y, z);
							}
							else {
								LabriconChunkBuilder.Shell2D.generate(chunk, x, y, z);
							}
						}
						if (x == 7 && z == 0 && LabriconChunkBuilder.rand.nextInt(3) > 0) {
							LabriconChunkBuilder.DoorLong.generate(chunk, x, y, z);
						}
						if (x == 0 && z == 7 && LabriconChunkBuilder.rand.nextInt(3) > 0) {
							LabriconChunkBuilder.DoorWide.generate(chunk, x, y, z);
						}
						if (x == 15 && z == 7 && LabriconChunkBuilder.rand.nextInt(3) > 0) {
							LabriconChunkBuilder.DoorWide.generate(chunk, x, y, z);
						}
						if (x == 7 && z == 15 && LabriconChunkBuilder.rand.nextInt(3) > 0) {
							LabriconChunkBuilder.DoorLong.generate(chunk, x, y, z);
						}
					}
					if (y == 14 && x == 0 && z == 0) {
						final int floor = LabriconChunkBuilder.rand.nextInt(7);
						if (floor == 1) {
							LabriconChunkBuilder.Floor1D.generate(chunk, x, y, z);
						}
						else if (floor == 2) {
							LabriconChunkBuilder.Floor3D.generate(chunk, x, y, z);
						}
						else if (floor == 3) {
							LabriconChunkBuilder.Floor4D.generate(chunk, x, y, z);
						}
						else if (floor == 4) {
							LabriconChunkBuilder.Floor5D.generate(chunk, x, y, z);
						}
						else if (floor == 5) {
							LabriconChunkBuilder.Floor6D.generate(chunk, x, y, z);
						}
						else if (floor == 6) {
							LabriconChunkBuilder.Floor7D.generate(chunk, x, y, z);
						}
						else {
							LabriconChunkBuilder.Floor2D.generate(chunk, x, y, z);
						}
					}
					if (y == 15) {
						if (x == 0 && z == 0) {
							final int floor = LabriconChunkBuilder.rand.nextInt(8);
							if (floor == 1) {
								LabriconChunkBuilder.Shell1D.generate(chunk, x, y, z);
							}
							else if (floor == 2) {
								LabriconChunkBuilder.Shell3D.generate(chunk, x, y, z);
							}
							else if (floor == 3) {
								LabriconChunkBuilder.Shell4D.generate(chunk, x, y, z);
							}
							else if (floor == 4) {
								LabriconChunkBuilder.Shell5D.generate(chunk, x, y, z);
							}
							else if (floor == 5) {
								LabriconChunkBuilder.Shell6D.generate(chunk, x, y, z);
							}
							else if (floor == 6) {
								LabriconChunkBuilder.Shell7D.generate(chunk, x, y, z);
							}
							else if (floor == 7) {
								LabriconChunkBuilder.Shell8D.generate(chunk, x, y, z);
							}
							else {
								LabriconChunkBuilder.Shell2D.generate(chunk, x, y, z);
							}
						}
						if (x == 7 && z == 0 && LabriconChunkBuilder.rand.nextInt(3) > 0) {
							LabriconChunkBuilder.DoorLong.generate(chunk, x, y, z);
						}
						if (x == 0 && z == 7 && LabriconChunkBuilder.rand.nextInt(3) > 0) {
							LabriconChunkBuilder.DoorWide.generate(chunk, x, y, z);
						}
						if (x == 15 && z == 7 && LabriconChunkBuilder.rand.nextInt(3) > 0) {
							LabriconChunkBuilder.DoorWide.generate(chunk, x, y, z);
						}
						if (x == 7 && z == 15 && LabriconChunkBuilder.rand.nextInt(3) > 0) {
							LabriconChunkBuilder.DoorLong.generate(chunk, x, y, z);
						}
					}
					if (y == 23 && x == 0 && z == 0) {
						final int floor = LabriconChunkBuilder.rand.nextInt(7);
						if (floor == 1) {
							LabriconChunkBuilder.Floor1L.generate(chunk, x, y, z);
						}
						else if (floor == 2) {
							LabriconChunkBuilder.Floor3L.generate(chunk, x, y, z);
						}
						else if (floor == 3) {
							LabriconChunkBuilder.Floor4L.generate(chunk, x, y, z);
						}
						else if (floor == 4) {
							LabriconChunkBuilder.Floor5L.generate(chunk, x, y, z);
						}
						else if (floor == 5) {
							LabriconChunkBuilder.Floor6L.generate(chunk, x, y, z);
						}
						else if (floor == 6) {
							LabriconChunkBuilder.Floor7L.generate(chunk, x, y, z);
						}
						else {
							LabriconChunkBuilder.Floor2L.generate(chunk, x, y, z);
						}
					}
					if (y == 24) {
						if (x == 0 && z == 0) {
							final int floor = LabriconChunkBuilder.rand.nextInt(8);
							if (floor == 1) {
								LabriconChunkBuilder.Shell1L.generate(chunk, x, y, z);
							}
							else if (floor == 2) {
								LabriconChunkBuilder.Shell3L.generate(chunk, x, y, z);
							}
							else if (floor == 3) {
								LabriconChunkBuilder.Shell4L.generate(chunk, x, y, z);
							}
							else if (floor == 4) {
								LabriconChunkBuilder.Shell5L.generate(chunk, x, y, z);
							}
							else if (floor == 5) {
								LabriconChunkBuilder.Shell6L.generate(chunk, x, y, z);
							}
							else if (floor == 6) {
								LabriconChunkBuilder.Shell7L.generate(chunk, x, y, z);
							}
							else if (floor == 7) {
								LabriconChunkBuilder.Shell8L.generate(chunk, x, y, z);
							}
							else {
								LabriconChunkBuilder.Shell2L.generate(chunk, x, y, z);
							}
						}
						if (x == 7 && z == 0 && LabriconChunkBuilder.rand.nextInt(3) > 0) {
							LabriconChunkBuilder.DoorLong.generate(chunk, x, y, z);
						}
						if (x == 0 && z == 7 && LabriconChunkBuilder.rand.nextInt(3) > 0) {
							LabriconChunkBuilder.DoorWide.generate(chunk, x, y, z);
						}
						if (x == 15 && z == 7 && LabriconChunkBuilder.rand.nextInt(3) > 0) {
							LabriconChunkBuilder.DoorWide.generate(chunk, x, y, z);
						}
						if (x == 7 && z == 15 && LabriconChunkBuilder.rand.nextInt(3) > 0) {
							LabriconChunkBuilder.DoorLong.generate(chunk, x, y, z);
						}
					}
					if (y == 32 && x == 0 && z == 0) {
						final int floor = LabriconChunkBuilder.rand.nextInt(7);
						if (floor == 1) {
							LabriconChunkBuilder.Floor1L.generate(chunk, x, y, z);
						}
						else if (floor == 2) {
							LabriconChunkBuilder.Floor3L.generate(chunk, x, y, z);
						}
						else if (floor == 3) {
							LabriconChunkBuilder.Floor4L.generate(chunk, x, y, z);
						}
						else if (floor == 4) {
							LabriconChunkBuilder.Floor5L.generate(chunk, x, y, z);
						}
						else if (floor == 5) {
							LabriconChunkBuilder.Floor6L.generate(chunk, x, y, z);
						}
						else if (floor == 6) {
							LabriconChunkBuilder.Floor7L.generate(chunk, x, y, z);
						}
						else {
							LabriconChunkBuilder.Floor2L.generate(chunk, x, y, z);
						}
					}
					if (y == 33) {
						if (x == 0 && z == 0) {
							final int floor = LabriconChunkBuilder.rand.nextInt(8);
							if (floor == 1) {
								LabriconChunkBuilder.Shell1L.generate(chunk, x, y, z);
							}
							else if (floor == 2) {
								LabriconChunkBuilder.Shell3L.generate(chunk, x, y, z);
							}
							else if (floor == 3) {
								LabriconChunkBuilder.Shell4L.generate(chunk, x, y, z);
							}
							else if (floor == 4) {
								LabriconChunkBuilder.Shell5L.generate(chunk, x, y, z);
							}
							else if (floor == 5) {
								LabriconChunkBuilder.Shell6L.generate(chunk, x, y, z);
							}
							else if (floor == 6) {
								LabriconChunkBuilder.Shell7L.generate(chunk, x, y, z);
							}
							else if (floor == 7) {
								LabriconChunkBuilder.Shell8L.generate(chunk, x, y, z);
							}
							else {
								LabriconChunkBuilder.Shell2L.generate(chunk, x, y, z);
							}
						}
						if (x == 7 && z == 0 && LabriconChunkBuilder.rand.nextInt(3) > 0) {
							LabriconChunkBuilder.DoorLong.generate(chunk, x, y, z);
						}
						if (x == 0 && z == 7 && LabriconChunkBuilder.rand.nextInt(3) > 0) {
							LabriconChunkBuilder.DoorWide.generate(chunk, x, y, z);
						}
						if (x == 15 && z == 7 && LabriconChunkBuilder.rand.nextInt(3) > 0) {
							LabriconChunkBuilder.DoorWide.generate(chunk, x, y, z);
						}
						if (x == 7 && z == 15 && LabriconChunkBuilder.rand.nextInt(3) > 0) {
							LabriconChunkBuilder.DoorLong.generate(chunk, x, y, z);
						}
					}
					if (y == 41 && x == 0 && z == 0) {
						final int floor = LabriconChunkBuilder.rand.nextInt(7);
						if (floor == 1) {
							LabriconChunkBuilder.Floor1.generate(chunk, x, y, z);
						}
						else if (floor == 2) {
							LabriconChunkBuilder.Floor3.generate(chunk, x, y, z);
						}
						else if (floor == 3) {
							LabriconChunkBuilder.Floor4.generate(chunk, x, y, z);
						}
						else if (floor == 4) {
							LabriconChunkBuilder.Floor5.generate(chunk, x, y, z);
						}
						else if (floor == 5) {
							LabriconChunkBuilder.Floor6.generate(chunk, x, y, z);
						}
						else if (floor == 6) {
							LabriconChunkBuilder.Floor7.generate(chunk, x, y, z);
						}
						else {
							LabriconChunkBuilder.Floor2.generate(chunk, x, y, z);
						}
					}
					if (y == 42) {
						if (x == 0 && z == 0) {
							final int floor = LabriconChunkBuilder.rand.nextInt(8);
							if (floor == 1) {
								LabriconChunkBuilder.Shell1.generate(chunk, x, y, z);
							}
							else if (floor == 2) {
								LabriconChunkBuilder.Shell3.generate(chunk, x, y, z);
							}
							else if (floor == 3) {
								LabriconChunkBuilder.Shell4.generate(chunk, x, y, z);
							}
							else if (floor == 4) {
								LabriconChunkBuilder.Shell5.generate(chunk, x, y, z);
							}
							else if (floor == 5) {
								LabriconChunkBuilder.Shell6.generate(chunk, x, y, z);
							}
							else if (floor == 6) {
								LabriconChunkBuilder.Shell7.generate(chunk, x, y, z);
							}
							else if (floor == 7) {
								LabriconChunkBuilder.Shell8.generate(chunk, x, y, z);
							}
							else {
								LabriconChunkBuilder.Shell2.generate(chunk, x, y, z);
							}
						}
						if (x == 7 && z == 0 && LabriconChunkBuilder.rand.nextInt(3) > 0) {
							LabriconChunkBuilder.DoorLong.generate(chunk, x, y, z);
						}
						if (x == 0 && z == 7 && LabriconChunkBuilder.rand.nextInt(3) > 0) {
							LabriconChunkBuilder.DoorWide.generate(chunk, x, y, z);
						}
						if (x == 15 && z == 7 && LabriconChunkBuilder.rand.nextInt(3) > 0) {
							LabriconChunkBuilder.DoorWide.generate(chunk, x, y, z);
						}
						if (x == 7 && z == 15 && LabriconChunkBuilder.rand.nextInt(3) > 0) {
							LabriconChunkBuilder.DoorLong.generate(chunk, x, y, z);
						}
					}
					if (y == 50 && x == 0 && z == 0) {
						final int floor = LabriconChunkBuilder.rand.nextInt(7);
						if (floor == 1) {
							LabriconChunkBuilder.Floor1.generate(chunk, x, y, z);
						}
						else if (floor == 2) {
							LabriconChunkBuilder.Floor3.generate(chunk, x, y, z);
						}
						else if (floor == 3) {
							LabriconChunkBuilder.Floor4.generate(chunk, x, y, z);
						}
						else if (floor == 4) {
							LabriconChunkBuilder.Floor5.generate(chunk, x, y, z);
						}
						else if (floor == 5) {
							LabriconChunkBuilder.Floor6.generate(chunk, x, y, z);
						}
						else if (floor == 6) {
							LabriconChunkBuilder.Floor7.generate(chunk, x, y, z);
						}
						else {
							LabriconChunkBuilder.Floor2.generate(chunk, x, y, z);
						}
					}
					if (y == 59 && x == 0 && z == 0) {
						final int floor = LabriconChunkBuilder.rand.nextInt(7);
						if (floor == 1) {
							LabriconChunkBuilder.Floor1.generate(chunk, x, y, z);
						}
						else if (floor == 2) {
							LabriconChunkBuilder.Floor3.generate(chunk, x, y, z);
						}
						else if (floor == 3) {
							LabriconChunkBuilder.Floor4.generate(chunk, x, y, z);
						}
						else if (floor == 4) {
							LabriconChunkBuilder.Floor5.generate(chunk, x, y, z);
						}
						else if (floor == 5) {
							LabriconChunkBuilder.Floor6.generate(chunk, x, y, z);
						}
						else if (floor == 6) {
							LabriconChunkBuilder.Floor7.generate(chunk, x, y, z);
						}
						else {
							LabriconChunkBuilder.Floor2.generate(chunk, x, y, z);
						}
					}
					if (y == 51) {
						if (x == 0 && z == 0) {
							final int floor = LabriconChunkBuilder.rand.nextInt(8);
							if (floor == 1) {
								LabriconChunkBuilder.Shell1.generate(chunk, x, y, z);
							}
							else if (floor == 2) {
								LabriconChunkBuilder.Shell3.generate(chunk, x, y, z);
							}
							else if (floor == 3) {
								LabriconChunkBuilder.Shell4.generate(chunk, x, y, z);
							}
							else if (floor == 4) {
								LabriconChunkBuilder.Shell5.generate(chunk, x, y, z);
							}
							else if (floor == 5) {
								LabriconChunkBuilder.Shell6.generate(chunk, x, y, z);
							}
							else if (floor == 6) {
								LabriconChunkBuilder.Shell7.generate(chunk, x, y, z);
							}
							else if (floor == 7) {
								LabriconChunkBuilder.Shell8.generate(chunk, x, y, z);
							}
							else {
								LabriconChunkBuilder.Shell2.generate(chunk, x, y, z);
							}
						}
						if (x == 7 && z == 0 && LabriconChunkBuilder.rand.nextInt(3) > 0) {
							LabriconChunkBuilder.DoorLong.generate(chunk, x, y, z);
						}
						if (x == 0 && z == 7 && LabriconChunkBuilder.rand.nextInt(3) > 0) {
							LabriconChunkBuilder.DoorWide.generate(chunk, x, y, z);
						}
						if (x == 15 && z == 7 && LabriconChunkBuilder.rand.nextInt(3) > 0) {
							LabriconChunkBuilder.DoorWide.generate(chunk, x, y, z);
						}
						if (x == 7 && z == 15 && LabriconChunkBuilder.rand.nextInt(3) > 0) {
							LabriconChunkBuilder.DoorLong.generate(chunk, x, y, z);
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
		LabriconChunkBuilder.rand = new Random();
		LabriconChunkBuilder.Floor1 = new Floor1();
		LabriconChunkBuilder.Floor2 = new Floor2();
		LabriconChunkBuilder.Floor3 = new Floor3();
		LabriconChunkBuilder.Floor4 = new Floor4();
		LabriconChunkBuilder.Floor5 = new Floor5();
		LabriconChunkBuilder.Floor6 = new Floor6();
		LabriconChunkBuilder.Floor7 = new Floor7();
		LabriconChunkBuilder.Shell1 = new Shell1();
		LabriconChunkBuilder.Shell2 = new Shell2();
		LabriconChunkBuilder.Shell3 = new Shell3();
		LabriconChunkBuilder.Shell4 = new Shell4();
		LabriconChunkBuilder.Shell5 = new Shell5();
		LabriconChunkBuilder.Shell6 = new Shell6();
		LabriconChunkBuilder.Shell7 = new Shell7();
		LabriconChunkBuilder.Shell8 = new Shell8();
		LabriconChunkBuilder.Floor1L = new Floor1L();
		LabriconChunkBuilder.Floor2L = new Floor2L();
		LabriconChunkBuilder.Floor3L = new Floor3L();
		LabriconChunkBuilder.Floor4L = new Floor4L();
		LabriconChunkBuilder.Floor5L = new Floor5L();
		LabriconChunkBuilder.Floor6L = new Floor6L();
		LabriconChunkBuilder.Floor7L = new Floor7L();
		LabriconChunkBuilder.Shell1L = new Shell1L();
		LabriconChunkBuilder.Shell2L = new Shell2L();
		LabriconChunkBuilder.Shell3L = new Shell3L();
		LabriconChunkBuilder.Shell4L = new Shell4L();
		LabriconChunkBuilder.Shell5L = new Shell5L();
		LabriconChunkBuilder.Shell6L = new Shell6L();
		LabriconChunkBuilder.Shell7L = new Shell7L();
		LabriconChunkBuilder.Shell8L = new Shell8L();
		LabriconChunkBuilder.Floor1D = new Floor1D();
		LabriconChunkBuilder.Floor2D = new Floor2D();
		LabriconChunkBuilder.Floor3D = new Floor3D();
		LabriconChunkBuilder.Floor4D = new Floor4D();
		LabriconChunkBuilder.Floor5D = new Floor5D();
		LabriconChunkBuilder.Floor6D = new Floor6D();
		LabriconChunkBuilder.Floor7D = new Floor7D();
		LabriconChunkBuilder.Shell1D = new Shell1D();
		LabriconChunkBuilder.Shell2D = new Shell2D();
		LabriconChunkBuilder.Shell3D = new Shell3D();
		LabriconChunkBuilder.Shell4D = new Shell4D();
		LabriconChunkBuilder.Shell5D = new Shell5D();
		LabriconChunkBuilder.Shell6D = new Shell6D();
		LabriconChunkBuilder.Shell7D = new Shell7D();
		LabriconChunkBuilder.Shell8D = new Shell8D();
		LabriconChunkBuilder.DoorWide = new DoorWide();
		LabriconChunkBuilder.DoorLong = new DoorLong();
	}
}
