package net.nevermine.dimension.celeve;

import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.ChunkProviderEvent;
import net.nevermine.izer.Blockizer;
import net.nevermine.structures.celeve.*;
import net.nevermine.structures.vanilla.RuneShrinePlatform;

import java.util.List;
import java.util.Random;

public class ChunkProviderCeleve implements IChunkProvider {
	private Random rand;
	private NoiseGeneratorOctaves noiseGen1;
	private NoiseGeneratorOctaves noiseGen2;
	private NoiseGeneratorOctaves noiseGen3;
	private NoiseGeneratorOctaves field_909_n;
	private NoiseGeneratorOctaves noiseGen4;
	public NoiseGeneratorOctaves noiseGen5;
	public NoiseGeneratorOctaves noiseGen6;
	public NoiseGeneratorOctaves mobSpawnerNoise;
	private World worldObj;
	private double[] noiseArray;
	private double[] stoneNoise;
	private BiomeGenBase[] biomesForGeneration;
	double[] noise3;
	double[] noise1;
	double[] noise2;
	double[] noise5;
	double[] noise6;
	int[][] field_914_i;
	private double[] generatedTemperatures;
	private WorldGenerator cp;
	private WorldGenerator Celebulbs;
	private WorldGenerator ct1;
	private WorldGenerator ct2;
	private WorldGenerator ct3;
	private WorldGenerator ct4;
	private WorldGenerator lotto;
	private WorldGenerator toymerc;
	private WorldGenerator gyro;

	public ChunkProviderCeleve(final World var1, final long var2) {
		stoneNoise = new double[256];
		field_914_i = new int[32][32];
		cp = new CelevePole();
		Celebulbs = new CelebulbPlant();
		ct1 = new TreeCeleve1();
		ct2 = new TreeCeleve2();
		ct3 = new TreeCeleve3();
		ct4 = new TreeCeleve4();
		lotto = new CeleveLottoStructure();
		toymerc = new ToyTower();
		gyro = new GyroPlatform();
		worldObj = var1;
		rand = new Random(var2);
		noiseGen1 = new NoiseGeneratorOctaves(rand, 16);
		noiseGen2 = new NoiseGeneratorOctaves(rand, 16);
		noiseGen3 = new NoiseGeneratorOctaves(rand, 8);
		field_909_n = new NoiseGeneratorOctaves(rand, 4);
		noiseGen4 = new NoiseGeneratorOctaves(rand, 4);
		noiseGen5 = new NoiseGeneratorOctaves(rand, 10);
		noiseGen6 = new NoiseGeneratorOctaves(rand, 16);
		mobSpawnerNoise = new NoiseGeneratorOctaves(rand, 8);
	}

	public boolean chunkExists(final int i, final int j) {
		return true;
	}

	public void generateTerrain(final int p_147420_1_, final int p_147420_2_, final Block[] p_14720_3_, final BiomeGenBase[] p_147420_4_) {
		final byte b0 = 2;
		final int k = b0 + 1;
		final byte b1 = 33;
		final int l = b0 + 1;
		noiseArray = initializeNoiseField(noiseArray, p_147420_1_ * b0, 0, p_147420_2_ * b0, k, b1, l);

		for (int i1 = 0; i1 < b0; ++i1) {
			for (int j1 = 0; j1 < b0; ++j1) {
				for (int k1 = 0; k1 < 32; ++k1) {
					final double d0 = 0.25;
					double d1 = noiseArray[((i1 + 0) * l + j1 + 0) * b1 + k1 + 0];
					double d2 = noiseArray[((i1 + 0) * l + j1 + 1) * b1 + k1 + 0];
					double d3 = noiseArray[((i1 + 1) * l + j1 + 0) * b1 + k1 + 0];
					double d4 = noiseArray[((i1 + 1) * l + j1 + 1) * b1 + k1 + 0];
					final double d5 = (noiseArray[((i1 + 0) * l + j1 + 0) * b1 + k1 + 1] - d1) * d0;
					final double d6 = (noiseArray[((i1 + 0) * l + j1 + 1) * b1 + k1 + 1] - d2) * d0;
					final double d7 = (noiseArray[((i1 + 1) * l + j1 + 0) * b1 + k1 + 1] - d3) * d0;
					final double d8 = (noiseArray[((i1 + 1) * l + j1 + 1) * b1 + k1 + 1] - d4) * d0;

					for (int var21 = 0; var21 < 4; ++var21) {
						final double d9 = 0.0025;
						double d10 = d1;
						double d11 = d2;
						final double d12 = (d3 - d1) * d9;
						final double d13 = (d4 - d2) * d9;

						for (int i2 = 0; i2 < 8; ++i2) {
							int j2 = i2 + i1 * 8 << 11 | 0 + j1 * 8 << 7 | k1 * 4 + var21;
							final short short1 = 128;
							final double d14 = 0.125;
							double d15 = d10;
							final double d16 = (d11 - d10) * d14;

							for (int var33 = 0; var33 < 8; ++var33) {
								Block block = null;

								if (d15 > 0.0) {
									block = Blockizer.DirtCeleve;
								}

								p_14720_3_[j2] = block;
								j2 += short1;
								d15 += d16;
							}

							d10 += d12;
							d11 += d13;
						}

						d1 += d5;
						d2 += d6;
						d3 += d7;
						d4 += d8;
					}
				}
			}
		}
	}

	public Chunk provideChunk(final int par1, final int par2) {
		rand.setSeed(par1 * 391279128714L + par2 * 132894987741L);
		final Block[] ablock = new Block[32768];
		generateTerrain(par1, par2, ablock, biomesForGeneration = worldObj.getWorldChunkManager().loadBlockGeneratorData(biomesForGeneration, par1 * 16, par2 * 16, 16, 16));
		replaceBlocksForBiome(par1, par2, ablock, biomesForGeneration);
		for (int i = 0; i < 32768; ++i) {
			if (ablock[i] == Blockizer.GrassCeleve && ablock[i + 1] != null) {
				ablock[i] = Blockizer.DirtCeleve;
			}
		}
		final Chunk chunk = new Chunk(worldObj, ablock, par1, par2);
		final byte[] abyte = chunk.getBiomeArray();
		for (int k = 0; k < abyte.length; ++k) {
			abyte[k] = (byte)biomesForGeneration[k].biomeID;
		}
		chunk.generateSkylightMap();
		return chunk;
	}

	public void replaceBlocksForBiome(final int var1, final int var2, final Block[] var3, final BiomeGenBase[] var4) {
		final byte var5 = 63;
		final double var6 = 0.03125;
		stoneNoise = noiseGen4.generateNoiseOctaves(stoneNoise, var1 * 16, var2 * 16, 0, 16, 16, 1, var6 * 2.0, var6 * 2.0, var6 * 2.0);
		final ChunkProviderEvent.ReplaceBiomeBlocks event = new ChunkProviderEvent.ReplaceBiomeBlocks(this, var1, var2, var3, var4);
		MinecraftForge.EVENT_BUS.post(event);
		if (event.getResult() == Event.Result.DENY) {
			return;
		}
		for (int var7 = 0; var7 < 16; ++var7) {
			for (int var8 = 0; var8 < 16; ++var8) {
				final BiomeGenBase var9 = var4[var8 + var7 * 16];
				final float var10 = var9.getFloatTemperature(var8, var8, var8);
				final int var11 = (int)(stoneNoise[var7 + var8 * 16] / 3.0 + 3.0 + rand.nextDouble() * 0.25);
				int var12 = -1;
				Block var13 = var9.topBlock;
				Block var14 = Blockizer.GrassCeleve;
				for (int var15 = 127; var15 >= 0; --var15) {
					final int var16 = (var8 * 16 + var7) * 128 + var15;
					if (var15 <= 0 + rand.nextInt(5)) {
						var3[var16] = null;
					}
					else {
						final Block var17 = var3[var16];
						if (var17 == null) {
							var12 = -1;
						}
						else if (var17 == Blockizer.DirtCeleve) {
							if (var12 == -1) {
								if (var11 <= 0) {
									var13 = Blockizer.GrassCeleve;
									var14 = Blockizer.GrassCeleve;
								}
								else if (var15 >= var5 - 4 && var15 <= var5 + 1) {
									var13 = Blockizer.GrassCeleve;
									var14 = Blockizer.DirtCeleve;
								}
								if (var15 >= var5 - 1) {
									var3[var16] = var13;
								}
								else {
									var3[var16] = var14;
								}
							}
							else if (var12 > 0) {
								--var12;
								var3[var16] = var14;
								if (var12 == 0 && var14 == Blockizer.GrassCeleve) {
									var12 = -1;
									var14 = Blockizer.GrassCeleve;
								}
							}
						}
						if (var12 > 0) {
							--var12;
							var3[var16] = var14;
							if (var12 == 0 && var14 == Blockizer.DirtCeleve) {
								var12 = -1;
								var14 = Blockizer.DirtCeleve;
							}
						}
					}
				}
			}
		}
	}

	public Chunk loadChunk(final int i, final int j) {
		return provideChunk(i, j);
	}

	private double[] initializeNoiseField(double[] var1, final int var2, final int var3, final int var4, final int var5, final int var6, final int var7) {
		if (var1 == null) {
			var1 = new double[var5 * var6 * var7];
		}
		double var8 = 684.412;
		final double var9 = 684.412;
		noise5 = noiseGen5.generateNoiseOctaves(noise5, var2, var4, var5, var7, 1.121, 1.121, 0.5);
		noise6 = noiseGen6.generateNoiseOctaves(noise6, var2, var4, var5, var7, 200.0, 200.0, 0.5);
		var8 *= 2.0;
		noise3 = noiseGen3.generateNoiseOctaves(noise3, var2, var3, var4, var5, var6, var7, var8 / 80.0, var8 / 160.0, var8 / 80.0);
		noise1 = noiseGen1.generateNoiseOctaves(noise1, var2, var3, var4, var5, var6, var7, var8, var9, var8);
		noise2 = noiseGen2.generateNoiseOctaves(noise2, var2, var3, var4, var5, var6, var7, var8, var9, var8);
		int var10 = 0;
		int var11 = 0;
		final int var12 = 16 / var5;
		for (int var13 = 0; var13 < var5; ++var13) {
			final int var14 = var13 * var12 + var12 / 2;
			for (int var15 = 0; var15 < var7; ++var15) {
				final int var16 = var15 * var12 + var12 / 2;
				double var17 = (noise5[var11] + 256.0) / 512.0;
				double var18 = noise6[var11] / 8000.0;
				if (var18 < 0.0) {
					var18 = -var18 * 0.3;
				}
				var18 = var18 * 3.0 - 2.0;
				if (var18 > 1.0) {
					var18 = 1.0;
				}
				var18 /= 8.0;
				var18 = 0.0;
				if (var17 < 0.0) {
					var17 = 0.0;
				}
				var17 += 0.5;
				var18 = var18 * var6 / 16.0;
				++var11;
				final double var19 = var6 / 2.0;
				for (int var20 = 0; var20 < var6; ++var20) {
					double var21 = 0.0;
					double var22 = (var20 - var19) * 8.0 / var17;
					if (var22 < 0.0) {
						var22 *= -1.0;
					}
					final double var23 = noise1[var10] / 512.0;
					final double var24 = noise2[var10] / 512.0;
					final double var25 = (noise3[var10] / 10.0 + 1.0) / 2.0;
					if (var25 < 0.0) {
						var21 = var23;
					}
					else if (var25 > 1.0) {
						var21 = var24;
					}
					else {
						var21 = var23 + (var24 - var23) * var25;
					}
					var21 -= 8.0;
					byte var26 = 32;
					if (var20 > var6 - var26) {
						final double var27 = (var20 - (var6 - var26)) / (var26 - 1.0f);
						var21 = var21 * (1.0 - var27) + -30.0 * var27;
					}
					var26 = 8;
					if (var20 < var26) {
						final double var27 = (var26 - var20) / (var26 - 1.0f);
						var21 = var21 * (1.0 - var27) + -30.0 * var27;
					}
					var1[var10] = var21;
					++var10;
				}
			}
		}
		return var1;
	}

	public void populate(final IChunkProvider ichunkprovider, int i, final int j) {
		final int var4 = i * 16;
		final int var5 = j * 16;
		final BiomeGenBase var6 = worldObj.getWorldChunkManager().getBiomeGenAt(var4 + 16, var5 + 16);
		rand.setSeed(i * rand.nextInt() + j * rand.nextInt() ^ worldObj.getSeed());
		int x;
		int z;
		int y;
		int tree;
		for (i = 0; i < 2; ++i) {
			x = var4 + rand.nextInt(16);
			z = var5 + rand.nextInt(16);
			y = worldObj.getHeightValue(x + 2, z + 2);
			if (worldObj.getBlock(x + 2, y - 1, z + 2) == Blockizer.GrassCeleve) {
				tree = rand.nextInt(4);
				if (tree == 1) {
					ct1.generate(worldObj, rand, x, y, z);
				}
				else if (tree == 2) {
					ct2.generate(worldObj, rand, x, y, z);
				}
				else if (tree == 3) {
					ct3.generate(worldObj, rand, x, y, z);
				}
				else {
					ct4.generate(worldObj, rand, x, y, z);
				}
			}
		}
		x = var4 + rand.nextInt(16);
		z = var5 + rand.nextInt(16);
		y = worldObj.getHeightValue(x + 2, z + 2);
		if (worldObj.getBlock(x + 2, y - 1, z + 2) == Blockizer.GrassCeleve) {
			cp.generate(worldObj, rand, x, y, z);
		}
		int pick;
		for (i = 0; i < 60; ++i) {
			x = var4 + rand.nextInt(16);
			z = var5 + rand.nextInt(16);
			y = worldObj.getHeightValue(x, z);
			if (worldObj.getBlock(x, y - 1, z) == Blockizer.GrassCeleve && worldObj.getBlock(x, y, z) == Blocks.air) {
				pick = rand.nextInt(5);
				if (pick == 1) {
					worldObj.setBlock(x, y, z, Blockizer.CeleviansBlue);
				}
				else if (pick == 2) {
					worldObj.setBlock(x, y, z, Blockizer.CeleviansRed);
				}
				else if (pick == 3) {
					worldObj.setBlock(x, y, z, Blockizer.CeleviansWhite);
				}
				else if (pick == 4) {
					worldObj.setBlock(x, y, z, Blockizer.CeleviansPurple);
				}
				else {
					Celebulbs.generate(worldObj, rand, x, y, z);
				}
			}
		}
		if (rand.nextInt(980) == 327) {
			x = var4 + rand.nextInt(16);
			z = var5 + rand.nextInt(16);
			y = 50;
			if (worldObj.getBlock(x, y, z) == Blocks.air && worldObj.getBlock(x + 15, y + 2, z + 15) == Blocks.air) {
				gyro.generate(worldObj, rand, x, y, z);
			}
		}
		if (rand.nextInt(650) == 116) {
			x = var4 + rand.nextInt(16);
			z = var5 + rand.nextInt(16);
			y = 20;
			if (worldObj.getBlock(x + 2, y, z + 2) == Blocks.air) {
				lotto.generate(worldObj, rand, x, y, z);
			}
		}
		if (rand.nextInt(450) == 127) {
			x = var4 + rand.nextInt(16);
			z = var5 + rand.nextInt(16);
			y = worldObj.getHeightValue(x, z) + 15;
			new RuneShrinePlatform(13).generate(worldObj, rand, x, y, z);
		}
		if (rand.nextInt(600) == 333) {
			x = var4 + rand.nextInt(16);
			z = var5 + rand.nextInt(16);
			y = worldObj.getHeightValue(x + 5, z + 5);
			toymerc.generate(worldObj, rand, x, y - 1, z);
		}
	}

	public boolean saveChunks(final boolean flag, final IProgressUpdate iprogressupdate) {
		return true;
	}

	public boolean unloadQueuedChunks() {
		return false;
	}

	public boolean canSave() {
		return true;
	}

	public String makeString() {
		return "Celeve";
	}

	public List getPossibleCreatures(final EnumCreatureType enumcreaturetype, final int i, final int j, final int k) {
		final BiomeGenBase var5 = worldObj.getBiomeGenForCoords(i, k);
		return (var5 == null) ? null : var5.getSpawnableList(enumcreaturetype);
	}

	public ChunkPosition func_147416_a(final World world, final String s, final int i, final int j, final int k) {
		return null;
	}

	public int getLoadedChunkCount() {
		return 0;
	}

	public void recreateStructures(final int i, final int j) {
	}

	public void saveExtraData() {
	}
}
