package net.nevermine.dimension.creeponia;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.NoiseGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.nevermine.izer.Blockizer;
import net.nevermine.structures.creeponia.*;

import java.util.List;
import java.util.Random;

public class ChunkProviderCreeponia implements IChunkProvider {
	private Random rand;
	private NoiseGeneratorOctaves noiseGen1;
	private NoiseGeneratorOctaves noiseGen2;
	private NoiseGeneratorOctaves noiseGen3;
	private NoiseGeneratorOctaves noiseGen5;
	private NoiseGeneratorOctaves noiseGen6;
	private NoiseGeneratorPerlin noiseGen4;
	private World worldObj;
	private WorldType type;
	private final double[] da;
	private final float[] parabolicField;
	private double[] stoneNoise;
	private BiomeGenBase[] biomesForGeneration;
	private double[] gen1;
	private double[] gen2;
	private double[] gen3;
	private double[] gen4;
	private int[][] ia;
	private MapGenBase caveGenerator;
	private WorldGenerator ct1;
	private WorldGenerator ct2;
	private WorldGenerator ct3;
	private WorldGenerator ct4;
	private WorldGenerator ct5;
	private WorldGenerator ct6;

	public ChunkProviderCreeponia(final World par1World, final long par2) {
		stoneNoise = new double[256];
		ia = new int[32][32];
		caveGenerator = new MapGenCreeponiaCaves();
		ct1 = new CreepTree1();
		ct2 = new CreepTree2();
		ct3 = new CreepTree3();
		ct4 = new CreepTree4();
		ct5 = new CreepTree5();
		ct6 = new CreepTree6();
		worldObj = par1World;
		type = par1World.getWorldInfo().getTerrainType();
		rand = new Random(par2);
		noiseGen1 = new NoiseGeneratorOctaves(rand, 16);
		noiseGen2 = new NoiseGeneratorOctaves(rand, 16);
		noiseGen3 = new NoiseGeneratorOctaves(rand, 8);
		noiseGen4 = new NoiseGeneratorPerlin(rand, 4);
		noiseGen5 = new NoiseGeneratorOctaves(rand, 10);
		noiseGen6 = new NoiseGeneratorOctaves(rand, 16);
		da = new double[825];
		parabolicField = new float[25];
		for (int j = -2; j <= 2; ++j) {
			for (int k = -2; k <= 2; ++k) {
				final float f = 10.0f / MathHelper.sqrt_float(j * j + k * k + 0.2f);
				parabolicField[j + 2 + (k + 2) * 5] = f;
			}
		}
		NoiseGenerator[] noiseGens = {noiseGen1, noiseGen2, noiseGen3, noiseGen4, noiseGen5, noiseGen6};
		noiseGens = TerrainGen.getModdedNoiseGenerators(par1World, rand, noiseGens);
		noiseGen1 = (NoiseGeneratorOctaves)noiseGens[0];
		noiseGen2 = (NoiseGeneratorOctaves)noiseGens[1];
		noiseGen3 = (NoiseGeneratorOctaves)noiseGens[2];
		noiseGen4 = (NoiseGeneratorPerlin)noiseGens[3];
		noiseGen5 = (NoiseGeneratorOctaves)noiseGens[4];
		noiseGen6 = (NoiseGeneratorOctaves)noiseGens[5];
	}

	public void generate(int i, int j, Block[] b) {
		byte b0 = 63;
		this.biomesForGeneration = worldObj.getWorldChunkManager().getBiomesForGeneration(biomesForGeneration, i * 4 - 2, j * 4 - 2, 10, 10);
		this.generate(i * 4, 0, j * 4);
		for (int k = 0; k < 4; ++k) {
			int l = k * 5;
			int i2 = (k + 1) * 5;
			for (int j2 = 0; j2 < 4; ++j2) {
				int k2 = (l + j2) * 33;
				int l2 = (l + j2 + 1) * 33;
				int i3 = (i2 + j2) * 33;
				int j3 = (i2 + j2 + 1) * 33;
				for (int k3 = 0; k3 < 32; ++k3) {
					double d0 = 0.125;
					double d2 = da[k2 + k3];
					double d3 = da[l2 + k3];
					double d4 = da[i3 + k3];
					double d5 = da[j3 + k3];
					double d6 = (da[k2 + k3 + 1] - d2) * d0;
					double d7 = (da[l2 + k3 + 1] - d3) * d0;
					double d8 = (da[i3 + k3 + 1] - d4) * d0;
					double d9 = (da[j3 + k3 + 1] - d5) * d0;
					for (int l3 = 0; l3 < 8; ++l3) {
						double d10 = 0.25;
						double d11 = d2;
						double d12 = d3;
						double d13 = (d4 - d2) * d10;
						double d14 = (d5 - d3) * d10;
						for (int i4 = 0; i4 < 4; ++i4) {
							int j4 = i4 + k * 4 << 12 | 0 + j2 * 4 << 8 | k3 * 8 + l3;
							short short1 = 256;
							j4 -= short1;
							double d15 = 0.25;
							double d16 = (d12 - d11) * d15;
							double d17 = d11 - d16;
							for (int k4 = 0; k4 < 4; ++k4) {
								if ((d17 += d16) > 0.0) {
									b[j4 += short1] = Blockizer.CreepStone;
								}
								else if (k3 * 8 + l3 < b0) {
									b[j4 += short1] = Blockizer.GrassCreeponia;
								}
								else {
									b[j4 += short1] = null;
								}
							}
							d11 += d13;
							d12 += d14;
						}
						d2 += d6;
						d3 += d7;
						d4 += d8;
						d5 += d9;
					}
				}
			}
		}
	}

	public void replaceBlocksForBiome(final int i, final int j, final Block[] ba, final byte[] by, final BiomeGenBase[] b) {
		final double d0 = 0.03125;
		stoneNoise = noiseGen4.func_151599_a(stoneNoise, (double)(i * 16), (double)(j * 16), 16, 16, d0 * 2.0, d0 * 2.0, 1.0);
		for (int k = 0; k < 16; ++k) {
			for (int l = 0; l < 16; ++l) {
				final BiomeGenBase biomegenbase = b[l + k * 16];
				genBiomeTerrain(worldObj, rand, ba, by, i * 16 + k, j * 16 + l, stoneNoise[l + k * 16], biomegenbase);
			}
		}
	}

	public final void genBiomeTerrain(final World p_150560_1_, final Random p_150560_2_, final Block[] p_150560_3_, final byte[] p_150560_4_, final int p_150560_5_, final int p_150560_6_, final double p_150560_7_, final BiomeGenBase b) {
		final boolean flag = true;
		Block block = b.topBlock;
		byte b2 = (byte)(b.field_150604_aj & 0xFF);
		Block block2 = b.fillerBlock;
		int k = -1;
		final int l = (int)(p_150560_7_ / 3.0 + 3.0 + p_150560_2_.nextDouble() * 0.25);
		final int i1 = p_150560_5_ & 0xF;
		final int j1 = p_150560_6_ & 0xF;
		final int k2 = p_150560_3_.length / 256;
		for (int l2 = 255; l2 >= 0; --l2) {
			final int i2 = (j1 * 16 + i1) * k2 + l2;
			if (l2 <= 0 + p_150560_2_.nextInt(5)) {
				p_150560_3_[i2] = Blocks.bedrock;
			}
			else {
				final Block block3 = p_150560_3_[i2];
				if (block3 != null && block3.getMaterial() != Material.air) {
					if (block3 == Blockizer.CreepStone) {
						if (k == -1) {
							if (l <= 0) {
								block = null;
								b2 = 0;
								block2 = Blockizer.CreepStone;
							}
							else if (l2 >= 59 && l2 <= 64) {
								block = b.topBlock;
								b2 = (byte)(b.field_150604_aj & 0xFF);
								block2 = b.fillerBlock;
							}
							if (l2 < 63 && (block == null || block.getMaterial() == Material.air)) {
								if (b.getFloatTemperature(p_150560_5_, l2, p_150560_6_) < 0.15f) {
									block = Blocks.water;
									b2 = 0;
								}
								else {
									block = Blockizer.CreepStone;
									b2 = 0;
								}
							}
							k = l;
							if (l2 >= 62) {
								p_150560_3_[i2] = block;
								p_150560_4_[i2] = b2;
							}
							else if (l2 < 56 - l) {
								block = null;
								block2 = Blockizer.CreepStone;
								p_150560_3_[i2] = Blockizer.CreepStone;
							}
							else {
								p_150560_3_[i2] = block2;
							}
						}
						else if (k > 0) {
							--k;
							p_150560_3_[i2] = block2;
							if (k == 0 && block2 == Blockizer.CreepStone) {
								k = p_150560_2_.nextInt(4) + Math.max(0, l2 - 63);
								block2 = Blockizer.CreepStone;
							}
						}
					}
				}
				else {
					k = -1;
				}
			}
		}
	}

	public Chunk loadChunk(final int par1, final int par2) {
		return provideChunk(par1, par2);
	}

	public Chunk provideChunk(final int par1, final int par2) {
		rand.setSeed(par1 * 341873128712L + par2 * 132897987541L);
		final Block[] ablock = new Block[65536];
		final byte[] abyte = new byte[65536];
		generate(par1, par2, ablock);
		replaceBlocksForBiome(par1, par2, ablock, abyte, biomesForGeneration = worldObj.getWorldChunkManager().loadBlockGeneratorData(biomesForGeneration, par1 * 16, par2 * 16, 16, 16));
		caveGenerator.func_151539_a(this, worldObj, par1, par2, ablock);
		for (int i = 0; i < 65536; ++i) {
			if (i % 256 < 50 && ablock[i] == Blockizer.CreepStone && rand.nextInt(100) == 12) {
				ablock[i] = Blockizer.CreepCrystal;
			}
			if (i % 256 < 20 + rand.nextInt(2) && ablock[i] == Blockizer.CreepStone) {
				ablock[i] = Blockizer.PrimedStone;

			}
			if (i % 256 > 20 && i % 256 < 40 + rand.nextInt(2) && ablock[i] == Blockizer.CreepStone) {
				ablock[i] = Blockizer.UnstableStone;
			}
		}
		final Chunk chunk = new Chunk(worldObj, ablock, abyte, par1, par2);
		final byte[] abyte2 = chunk.getBiomeArray();
		for (int k = 0; k < abyte2.length; ++k) {
			abyte2[k] = (byte)biomesForGeneration[k].biomeID;
		}
		chunk.generateSkylightMap();
		return chunk;
	}

	private void generate(final int x, final int y, final int z) {
		final double d0 = 684.412;
		final double d2 = 684.412;
		final double d3 = 512.0;
		final double d4 = 512.0;
		gen4 = noiseGen6.generateNoiseOctaves(gen4, x, z, 5, 5, 200.0, 200.0, 0.5);
		gen1 = noiseGen3.generateNoiseOctaves(gen1, x, y, z, 5, 33, 5, 8.555150000000001, 4.277575000000001, 8.555150000000001);
		gen2 = noiseGen1.generateNoiseOctaves(gen2, x, y, z, 5, 33, 5, 684.412, 684.412, 684.412);
		gen3 = noiseGen2.generateNoiseOctaves(gen3, x, y, z, 5, 33, 5, 684.412, 684.412, 684.412);
		final boolean flag1 = false;
		final boolean flag2 = false;
		int l = 0;
		int i1 = 0;
		final double d5 = 8.5;
		for (int j1 = 0; j1 < 5; ++j1) {
			for (int k1 = 0; k1 < 5; ++k1) {
				float f = 0.0f;
				float f2 = 0.0f;
				float f3 = 0.0f;
				final byte b0 = 2;
				final BiomeGenBase biomegenbase = biomesForGeneration[j1 + 2 + (k1 + 2) * 10];
				for (int l2 = -b0; l2 <= b0; ++l2) {
					for (int i2 = -b0; i2 <= b0; ++i2) {
						final BiomeGenBase biomegenbase2 = biomesForGeneration[j1 + l2 + 2 + (k1 + i2 + 2) * 10];
						float f4 = biomegenbase2.rootHeight;
						float f5 = biomegenbase2.heightVariation;
						if (type == WorldType.AMPLIFIED && f4 > 0.0f) {
							f4 = 1.0f + f4 * 2.0f;
							f5 = 1.0f + f5 * 4.0f;
						}
						float f6 = parabolicField[l2 + 2 + (i2 + 2) * 5] / (f4 + 2.0f);
						if (biomegenbase2.rootHeight > biomegenbase.rootHeight) {
							f6 /= 2.0f;
						}
						f += f5 * f6;
						f2 += f4 * f6;
						f3 += f6;
					}
				}
				f /= f3;
				f2 /= f3;
				f = f * 0.9f + 0.1f;
				f2 = (f2 * 4.0f - 1.0f) / 8.0f;
				double d6 = gen4[i1] / 8000.0;
				if (d6 < 0.0) {
					d6 = -d6 * 0.3;
				}
				d6 = d6 * 3.0 - 2.0;
				if (d6 < 0.0) {
					d6 /= 2.0;
					if (d6 < -1.0) {
						d6 = -1.0;
					}
					d6 /= 1.4;
					d6 /= 2.0;
				}
				else {
					if (d6 > 1.0) {
						d6 = 1.0;
					}
					d6 /= 8.0;
				}
				++i1;
				double d7 = f2;
				final double d8 = f;
				d7 += d6 * 0.2;
				d7 = d7 * 8.5 / 8.0;
				final double d9 = 8.5 + d7 * 4.0;
				for (int j2 = 0; j2 < 33; ++j2) {
					double d10 = (j2 - d9) * 12.0 * 128.0 / 256.0 / d8;
					if (d10 < 0.0) {
						d10 *= 4.0;
					}
					final double d11 = gen2[l] / 512.0;
					final double d12 = gen3[l] / 512.0;
					final double d13 = (gen1[l] / 10.0 + 1.0) / 2.0;
					double d14 = MathHelper.denormalizeClamp(d11, d12, d13) - d10;
					if (j2 > 29) {
						final double d15 = (j2 - 29) / 3.0f;
						d14 = d14 * (1.0 - d15) + -10.0 * d15;
					}
					da[l] = d14;
					++l;
				}
			}
		}
	}

	public boolean chunkExists(final int par1, final int par2) {
		return true;
	}

	public void populate(final IChunkProvider par1IChunkProvider, final int par2, final int par3) {
		final int var4 = par2 * 16;
		final int var5 = par3 * 16;
		final BiomeGenBase biomegenbase = worldObj.getBiomeGenForCoords(var4 + 16, var5 + 16);
		final long p1 = rand.nextLong() / 2L * 2L + 1L;
		final long j1 = rand.nextLong() / 2L * 2L + 1L;
		rand.setSeed(worldObj.getSeed());
		rand.setSeed(par2 * p1 + par3 * j1 ^ worldObj.getSeed());
		for (int i = 0; i < 7; ++i) {
			final int x = var4 + rand.nextInt(16);
			final int z = var5 + rand.nextInt(16);
			final int y = worldObj.getHeightValue(x + 3, z + 3);
			if (rand.nextInt(5) > 1) {
				if (worldObj.getBlock(x + 3, y - 1, z + 3) == Blockizer.GrassCreeponia) {
					final int pick = rand.nextInt(5);
					if (pick == 1) {
						ct2.generate(worldObj, rand, x, y, z);
					}
					else if (pick == 2) {
						ct3.generate(worldObj, rand, x, y, z);
					}
					else if (pick == 3) {
						ct4.generate(worldObj, rand, x, y, z);
					}
					else if (pick == 4) {
						ct5.generate(worldObj, rand, x, y, z);
					}
					else {
						ct6.generate(worldObj, rand, x, y, z);
					}
				}
			}
			else if (worldObj.getBlock(x + 2, y - 1, z + 2) == Blockizer.GrassCreeponia) {
				ct1.generate(worldObj, rand, x, y, z);
			}
		}
		if (rand.nextInt(300) == 161) {
			final int x = var4 + rand.nextInt(16);
			final int z = var5 + rand.nextInt(16);
			final int y = worldObj.getHeightValue(x + 3, z + 3);
			if (worldObj.getBlock(x, y - 1, z) == Blockizer.GrassCreeponia) {
				new CreeponiaLottoStructure().generate(worldObj, rand, x, y - 1, z);
			}
		}
		if (rand.nextInt(700) == 365) {
			final int x = var4 + rand.nextInt(16);
			final int z = var5 + rand.nextInt(16);
			final int y = worldObj.getHeightValue(x + 9, z + 9);
			if (worldObj.getBlock(x, y - 1, z) == Blockizer.GrassCreeponia) {
				new CreeperHQ().generate(worldObj, rand, x, y - 1, z);
			}
		}
		if (rand.nextInt(300) == 185) {
			final int x = var4 + rand.nextInt(16);
			final int z = var5 + rand.nextInt(16);
			final int y = worldObj.getHeightValue(x + 3, z + 3);
			if (worldObj.getBlock(x, y - 1, z) == Blockizer.GrassCreeponia) {
				new ExplosivesTower().generate(worldObj, rand, x, y - 1, z);
			}
		}
		if (rand.nextInt(300) == 207) {
			final int x = var4 + rand.nextInt(16);
			final int z = var5 + rand.nextInt(16);
			final int y = worldObj.getHeightValue(x + 2, z + 5);
			if (worldObj.getBlock(x, y - 1, z) == Blockizer.GrassCreeponia) {
				new CreeponiaBanker().generate(worldObj, rand, x, y - 1, z);
			}
		}
		for (int i = 0; i < 60; ++i) {
			final int x = var4 + rand.nextInt(16);
			final int z = var5 + rand.nextInt(16);
			final int y = worldObj.getHeightValue(x, z);
			if (worldObj.getBlock(x, y - 1, z) == Blockizer.GrassCreeponia && worldObj.getBlock(x, y, z) == Blocks.air) {
				if (rand.nextInt(2) == 1) {
					worldObj.setBlock(x, y, z, Blockizer.CreepGrass);
				}
				else {
					worldObj.setBlock(x, y, z, Blockizer.CreepFlowers);
				}
			}
		}
	}

	public boolean saveChunks(final boolean par1, final IProgressUpdate par2IProgressUpdate) {
		return true;
	}

	public void saveExtraData() {
	}

	public boolean unloadQueuedChunks() {
		return false;
	}

	public boolean canSave() {
		return true;
	}

	public String makeString() {
		return "Creeponia";
	}

	public List getPossibleCreatures(final EnumCreatureType par1EnumCreatureType, final int par2, final int par3, final int par4) {
		final BiomeGenBase biomegenbase = worldObj.getBiomeGenForCoords(par2, par4);
		return biomegenbase.getSpawnableList(par1EnumCreatureType);
	}

	public ChunkPosition func_147416_a(final World p_147416_1_, final String p_147416_2_, final int p_147416_3_, final int p_147416_4_, final int p_147416_5_) {
		return null;
	}

	public int getLoadedChunkCount() {
		return 0;
	}

	public void recreateStructures(final int par1, final int par2) {
	}
}
