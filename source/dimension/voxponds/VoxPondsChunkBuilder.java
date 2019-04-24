package net.nevermine.dimension.voxponds;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.nevermine.izer.Blockizer;
import net.nevermine.izer.SpecialBlockizer;
import net.nevermine.structures.voxponds.*;

import java.util.Random;

public class VoxPondsChunkBuilder {
	private static Random rand;
	private int DT1;
	private int DT2;
	private int DT3;
	private Boolean Cntrl;
	private Boolean Pillar;
	private Boolean Hang;
	private int StemCount;
	private int WhichStem;
	private static IVoxpondsStructure deadtree1;
	private static IVoxpondsStructure deadtree2;
	private static IVoxpondsStructure deadtree3;
	private static IVoxpondsStructure pillar1;
	private static IVoxpondsStructure pillar2;
	private static IVoxpondsStructure stem1;
	private static IVoxpondsStructure stem2;
	private static IVoxpondsStructure stem3;
	private static IVoxpondsStructure stem4;
	private static IVoxpondsStructure hanglight;
	private static IVoxpondsStructure groundlight;
	private static IVoxpondsStructure controltower;
	private static IVoxpondsStructure runeshrine;
	private static Block topper;
	private static Block under;
	private static IVoxpondsStructure EnigmaStation;
	private static IVoxpondsStructure DestroyedStore1;
	private static IVoxpondsStructure DestroyedStore2;
	private static IVoxpondsStructure IntelligenceTower;
	private static IVoxpondsStructure ObservationTower;
	private static IVoxpondsStructure VoxxulonAltar;
	private static IVoxpondsStructure ExoidPlatform;
	private static IVoxpondsStructure nightwingPlatform;
	private static IVoxpondsStructure lotto;
	private static IVoxpondsStructure branch1;
	private static IVoxpondsStructure branch2;
	private static IVoxpondsStructure fungi;
	private static IVoxpondsStructure ten1;
	private static IVoxpondsStructure ten2;
	private static IVoxpondsStructure ten3;
	private static IVoxpondsStructure ten4;
	private static IVoxpondsStructure ten5;
	private static IVoxpondsStructure miniTentacles;

	public VoxPondsChunkBuilder() {
		DT1 = 0;
		DT2 = 0;
		DT3 = 0;
		Cntrl = false;
		Pillar = false;
		Hang = false;
		StemCount = 0;
		WhichStem = 0;
	}

	public Block[][][] buildChunk() {
		final Block[][][] chunk = new Block[16][256][16];
		if (VoxPondsChunkBuilder.rand.nextInt(2) == 1) {
			VoxPondsChunkBuilder.topper = Blockizer.GrassToxic;
			VoxPondsChunkBuilder.under = Blockizer.DirtToxic;
		}
		else {
			VoxPondsChunkBuilder.topper = Blocks.air;
			VoxPondsChunkBuilder.under = Blockizer.ToxicWaste;
		}
		for (int x = 0; x < 16; ++x) {
			for (int y = 0; y < 50; ++y) {
				for (int z = 0; z < 16; ++z) {
					if ((y > 13 && y <= 15) || (y > 31 && y <= 38)) {
						chunk[x][y][z] = Blockizer.StoneToxic;
					}
					if (y == 38) {
						chunk[x][y][z] = VoxPondsChunkBuilder.topper;
						chunk[x][y - 1][z] = VoxPondsChunkBuilder.under;
						if (VoxPondsChunkBuilder.rand.nextInt(2) == 0) {
							chunk[x][y - 2][z] = VoxPondsChunkBuilder.under;
						}
					}
					if ((y == 16 || y == 17) && chunk[x][y][z] == null) {
						chunk[x][y][z] = Blocks.water;
					}
					if (y == 18 && VoxPondsChunkBuilder.rand.nextInt(6200) == 323 && x < 8 && z < 8) {
						VoxPondsChunkBuilder.nightwingPlatform.generate(chunk, x, y, z);
					}
					if (y == 18 && VoxPondsChunkBuilder.rand.nextInt(7800) == 144 && x < 3 && z < 3) {
						VoxPondsChunkBuilder.EnigmaStation.generate(chunk, x, y, z);
					}
					if (VoxPondsChunkBuilder.topper == Blockizer.GrassToxic) {
						final int full = VoxPondsChunkBuilder.rand.nextInt(130);
						if (full < 128) {
							if (DT1 < 2 && y == 39 && VoxPondsChunkBuilder.rand.nextInt(500) == 0 && x < 8 && z < 8) {
								VoxPondsChunkBuilder.deadtree1.generate(chunk, x, y, z);
								++DT1;
							}
							if (DT2 < 2 && y == 39 && VoxPondsChunkBuilder.rand.nextInt(500) == 0 && x < 9 && z < 9) {
								VoxPondsChunkBuilder.deadtree2.generate(chunk, x, y, z);
								++DT2;
							}
							if (DT3 < 2 && y == 39 && VoxPondsChunkBuilder.rand.nextInt(500) == 0 && x < 9 && z < 9) {
								VoxPondsChunkBuilder.deadtree3.generate(chunk, x, y, z);
								++DT3;
							}
							if (!Cntrl && y == 39 && VoxPondsChunkBuilder.rand.nextInt(3000) == 0 && x < 6 && z < 6) {
								VoxPondsChunkBuilder.lotto.generate(chunk, x, y, z);
								Cntrl = true;
							}
							if (!Cntrl && y == 39 && VoxPondsChunkBuilder.rand.nextInt(2700) == 0 && x < 4 && z < 4) {
								final int twr = VoxPondsChunkBuilder.rand.nextInt(3);
								if (twr == 1) {
									VoxPondsChunkBuilder.controltower.generate(chunk, x, y, z);
								}
								else if (twr == 2 && y == 0 && x == 0) {
									VoxPondsChunkBuilder.ObservationTower.generate(chunk, x, y, z);
								}
								else {
									VoxPondsChunkBuilder.IntelligenceTower.generate(chunk, x, y, z);
								}
								Cntrl = true;
							}
							if (y == 39 && x < 13 && z < 13 && chunk[x][y][z] == null && VoxPondsChunkBuilder.rand.nextInt(14) == 3) {
								final int pick = VoxPondsChunkBuilder.rand.nextInt(24);
								if (pick == 1) {
									VoxPondsChunkBuilder.ten5.generate(chunk, x, y, z);
								}
								else if (pick == 2) {
									VoxPondsChunkBuilder.ten1.generate(chunk, x, y, z);
								}
								else if (pick == 3) {
									VoxPondsChunkBuilder.ten2.generate(chunk, x, y, z);
								}
								else if (pick == 4) {
									VoxPondsChunkBuilder.ten3.generate(chunk, x, y, z);
								}
								else if (pick == 5) {
									VoxPondsChunkBuilder.ten4.generate(chunk, x, y, z);
								}
								else {
									VoxPondsChunkBuilder.fungi.generate(chunk, x, y, z);
								}
							}
							if (y == 39 && chunk[x][y][z] == null && VoxPondsChunkBuilder.rand.nextInt(2) == 1) {
								chunk[x][y][z] = Blockizer.DeadGrass;
							}
							if (y == 39 && chunk[x][y][z] == null && VoxPondsChunkBuilder.rand.nextInt(80) == 33) {
								chunk[x][y][z] = SpecialBlockizer.VoxShroom;
							}
							if (y == 39 && z < 12 && chunk[x][y][z] == null && VoxPondsChunkBuilder.rand.nextInt(45) == 32) {
								VoxPondsChunkBuilder.branch1.generate(chunk, x, y, z);
							}
							if (y == 39 && x < 12 && chunk[x][y][z] == null && VoxPondsChunkBuilder.rand.nextInt(45) == 32) {
								VoxPondsChunkBuilder.branch2.generate(chunk, x, y, z);
							}
						}
						else if (y == 39 && x == 0 && z == 0) {
							final int choose = VoxPondsChunkBuilder.rand.nextInt(4);
							if (choose == 1) {
								VoxPondsChunkBuilder.DestroyedStore1.generate(chunk, x, y, z);
							}
							else if (choose == 2) {
								VoxPondsChunkBuilder.DestroyedStore2.generate(chunk, x, y, z);
							}
							else if (choose == 3) {
								VoxPondsChunkBuilder.ExoidPlatform.generate(chunk, x, y, z);
							}
							else {
								VoxPondsChunkBuilder.VoxxulonAltar.generate(chunk, x, y, z);
							}
						}
					}
					else if (y == 38 && VoxPondsChunkBuilder.rand.nextInt(30) == 13) {
						VoxPondsChunkBuilder.miniTentacles.generate(chunk, x, y, z);
					}
					if (!Pillar && y == 16 && VoxPondsChunkBuilder.rand.nextInt(600) == 0 && x < 6 && z < 6) {
						if (VoxPondsChunkBuilder.rand.nextInt(2) == 1) {
							VoxPondsChunkBuilder.pillar1.generate(chunk, x, y, z);
						}
						else {
							VoxPondsChunkBuilder.pillar2.generate(chunk, x, y, z);
						}
						Pillar = true;
					}
					if (StemCount < 8 && y == 28 && VoxPondsChunkBuilder.rand.nextInt(40) == 0 && x < 14 && z < 14) {
						WhichStem = VoxPondsChunkBuilder.rand.nextInt(4);
						if (WhichStem == 1) {
							VoxPondsChunkBuilder.stem1.generate(chunk, x, y, z);
						}
						else if (WhichStem == 2) {
							VoxPondsChunkBuilder.stem2.generate(chunk, x, y, z);
						}
						else if (WhichStem == 3) {
							VoxPondsChunkBuilder.stem3.generate(chunk, x, y, z);
						}
						else {
							VoxPondsChunkBuilder.stem4.generate(chunk, x, y, z);
						}
						++StemCount;
					}
					if (!Hang && y == 27 && VoxPondsChunkBuilder.rand.nextInt(600) == 0 && x < 10 && z < 10) {
						if (VoxPondsChunkBuilder.rand.nextInt(2) == 1) {
							VoxPondsChunkBuilder.hanglight.generate(chunk, x, y, z);
						}
						else {
							VoxPondsChunkBuilder.groundlight.generate(chunk, x, 16, z);
						}
						Hang = true;
					}
					if (y == 47 && x < 9 && z < 9 && VoxPondsChunkBuilder.rand.nextInt(30000) == 5141) {
						VoxPondsChunkBuilder.runeshrine.generate(chunk, x, y, z);
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
		VoxPondsChunkBuilder.rand = new Random();
		VoxPondsChunkBuilder.deadtree1 = new DeadTree1();
		VoxPondsChunkBuilder.deadtree2 = new DeadTree2();
		VoxPondsChunkBuilder.deadtree3 = new DeadTree3();
		VoxPondsChunkBuilder.pillar1 = new VoxPillar1();
		VoxPondsChunkBuilder.pillar2 = new VoxPillar2();
		VoxPondsChunkBuilder.stem1 = new ToxicStem1();
		VoxPondsChunkBuilder.stem2 = new ToxicStem2();
		VoxPondsChunkBuilder.stem3 = new ToxicStem3();
		VoxPondsChunkBuilder.stem4 = new ToxicStem4();
		VoxPondsChunkBuilder.hanglight = new HangingVoxLight();
		VoxPondsChunkBuilder.groundlight = new GroundVoxLight();
		VoxPondsChunkBuilder.controltower = new ControlTower();
		VoxPondsChunkBuilder.runeshrine = new PoisonRuneShrine();
		VoxPondsChunkBuilder.EnigmaStation = new EnigmaStation();
		VoxPondsChunkBuilder.DestroyedStore1 = new DestroyedStore1();
		VoxPondsChunkBuilder.DestroyedStore2 = new DestroyedStore2();
		VoxPondsChunkBuilder.IntelligenceTower = new IntelligenceTower();
		VoxPondsChunkBuilder.ObservationTower = new ObservationTower();
		VoxPondsChunkBuilder.VoxxulonAltar = new VoxxulonAltar();
		VoxPondsChunkBuilder.ExoidPlatform = new ExoidPlatform();
		VoxPondsChunkBuilder.nightwingPlatform = new NightwingPlatform();
		VoxPondsChunkBuilder.lotto = new LottoVoxStructure();
		VoxPondsChunkBuilder.branch1 = new VoxBranch1();
		VoxPondsChunkBuilder.branch2 = new VoxBranch2();
		VoxPondsChunkBuilder.fungi = new VoxFungi();
		VoxPondsChunkBuilder.ten1 = new VoxTentacle1();
		VoxPondsChunkBuilder.ten2 = new VoxTentacle2();
		VoxPondsChunkBuilder.ten3 = new VoxTentacle3();
		VoxPondsChunkBuilder.ten4 = new VoxTentacle4();
		VoxPondsChunkBuilder.ten5 = new VoxTentacle5();
		VoxPondsChunkBuilder.miniTentacles = new MiniTentacles();
	}
}
