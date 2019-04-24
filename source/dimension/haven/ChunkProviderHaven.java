package net.nevermine.dimension.haven;

import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.ChunkProviderEvent;
import net.nevermine.izer.Blockizer;
import net.nevermine.structures.haven.*;
import net.nevermine.structures.vanilla.RuneShrinePlatform;

import java.util.List;
import java.util.Random;

public class ChunkProviderHaven implements IChunkProvider {
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
	private int picker;
	private double[] noiseArray;
	private double[] stoneNoise;
	private MapGenBase caveGenerator;
	private BiomeGenBase[] biomesForGeneration;
	double[] noise3;
	double[] noise1;
	double[] noise2;
	double[] noise5;
	double[] noise6;
	int[][] field_914_i;
	private double[] generatedTemperatures;
	private WorldGenerator ht1;
	private WorldGenerator ht2;
	private WorldGenerator ht3;
	private WorldGenerator ht4;
	private WorldGenerator ht5;
	private WorldGenerator ht6;

	public ChunkProviderHaven(final World var1, final long var2) {
		stoneNoise = new double[256];
		caveGenerator = new MapGenCaves();
		field_914_i = new int[32][32];
		ht1 = new HavenTree1();
		ht2 = new HavenTree2();
		ht3 = new HavenTree3();
		ht4 = new HavenTree4();
		ht5 = new HavenTree5();
		ht6 = new HavenTree6();
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

	public void generateTerrain(final int var1, final int var2, final Block[] var3, final BiomeGenBase[] var4) {
		final byte var5 = 2;
		final int var6 = var5 + 1;
		final byte var7 = 33;
		final int var8 = var5 + 1;
		noiseArray = initializeNoiseField(noiseArray, var1 * var5, 0, var2 * var5, var6, var7, var8);
		for (int var9 = 0; var9 < var5; ++var9) {
			for (int var10 = 0; var10 < var5; ++var10) {
				for (int var11 = 0; var11 < 32; ++var11) {
					final double var12 = 0.25;
					double var13 = noiseArray[((var9 + 0) * var8 + var10 + 0) * var7 + var11 + 0];
					double var14 = noiseArray[((var9 + 0) * var8 + var10 + 1) * var7 + var11 + 0];
					double var15 = noiseArray[((var9 + 1) * var8 + var10 + 0) * var7 + var11 + 0];
					double var16 = noiseArray[((var9 + 1) * var8 + var10 + 1) * var7 + var11 + 0];
					final double var17 = (noiseArray[((var9 + 0) * var8 + var10 + 0) * var7 + var11 + 1] - var13) * var12;
					final double var18 = (noiseArray[((var9 + 0) * var8 + var10 + 1) * var7 + var11 + 1] - var14) * var12;
					final double var19 = (noiseArray[((var9 + 1) * var8 + var10 + 0) * var7 + var11 + 1] - var15) * var12;
					final double var20 = (noiseArray[((var9 + 1) * var8 + var10 + 1) * var7 + var11 + 1] - var16) * var12;
					for (int var21 = 0; var21 < 4; ++var21) {
						final double var22 = 0.125;
						double var23 = var13;
						double var24 = var14;
						final double var25 = (var15 - var13) * var22;
						final double var26 = (var16 - var14) * var22;
						for (int var27 = 0; var27 < 8; ++var27) {
							int var28 = var27 + var9 * 8 << 11 | 0 + var10 * 8 << 7 | var11 * 4 + var21;
							final short var29 = 128;
							final double var30 = 0.125;
							double var31 = var23;
							final double var32 = (var24 - var23) * var30;
							for (int var33 = 0; var33 < 8; ++var33) {
								Block var34 = null;
								if (var31 > 0.0) {
									var34 = Blockizer.StoneHaven;
								}
								var3[var28] = var34;
								var28 += var29;
								var31 += var32;
							}
							var23 += var25;
							var24 += var26;
						}
						var13 += var17;
						var14 += var18;
						var15 += var19;
						var16 += var20;
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
			if (ablock[i] == Blockizer.GrassHaven && ablock[i + 1] != null) {
				ablock[i] = Blockizer.DirtHaven;
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
				Block var14 = Blockizer.GrassHaven;
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
						else if (var17 == Blockizer.StoneHaven) {
							if (var12 == -1) {
								if (var11 <= 0) {
									var13 = Blockizer.GrassHaven;
									var14 = Blockizer.GrassHaven;
								}
								else if (var15 >= var5 - 4 && var15 <= var5 + 1) {
									var13 = Blockizer.GrassHaven;
									var14 = Blockizer.StoneHaven;
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
								if (var12 == 0 && var14 == Blockizer.GrassHaven) {
									var12 = -1;
									var14 = Blockizer.GrassHaven;
								}
							}
						}
						if (var12 > 0) {
							--var12;
							var3[var16] = var14;
							if (var12 == 0 && var14 == Blockizer.StoneHaven) {
								var12 = -1;
								var14 = Blockizer.StoneHaven;
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
		BlockFalling.fallInstantly = true;
		final int var4 = i * 16;
		final int var5 = j * 16;
		final BiomeGenBase var6 = worldObj.getWorldChunkManager().getBiomeGenAt(var4 + 16, var5 + 16);
		rand.setSeed(i * rand.nextInt() + j * rand.nextInt() ^ worldObj.getSeed());
		for (int var7 = 0; var7 < 8; ++var7) {
			final int var8 = var4 + rand.nextInt(16);
			final int var9 = rand.nextInt(40) + 30;
			final int var10 = var5 + rand.nextInt(16);
			new WorldGenMinable(Blockizer.oreCrystallite, 15, Blockizer.StoneHaven).generate(worldObj, rand, var8, var9, var10);
		}
		int x;
		int z;
		int y;
		Random rand;
		int randomNum;
		for (i = 0; i < 3; ++i) {
			x = var4 + this.rand.nextInt(16);
			z = var5 + this.rand.nextInt(16);
			y = worldObj.getHeightValue(x, z);
			rand = new Random();
			randomNum = rand.nextInt(6);
			if (randomNum == 5) {
				ht1.generate(worldObj, this.rand, x, y, z);
			}
			else if (randomNum == 4) {
				ht2.generate(worldObj, this.rand, x, y, z);
			}
			else if (randomNum == 3) {
				ht3.generate(worldObj, this.rand, x, y, z);
			}
			else if (randomNum == 2) {
				ht4.generate(worldObj, this.rand, x, y, z);
			}
			else if (randomNum == 1) {
				ht5.generate(worldObj, this.rand, x, y, z);
			}
			else {
				ht6.generate(worldObj, this.rand, x, y, z);
			}
		}
		for (i = 0; i < 50; ++i) {
			x = var4 + this.rand.nextInt(16);
			z = var5 + this.rand.nextInt(16);
			y = worldObj.getHeightValue(x, z);
			if (worldObj.getBlock(x, y - 1, z) == Blockizer.GrassHaven && worldObj.getBlock(x, y, z) == Blocks.air) {
				picker = this.rand.nextInt(7);
				if (picker == 1) {
					worldObj.setBlock(x, y, z, Blockizer.DayloomsBlue);
				}
				else if (picker == 2) {
					worldObj.setBlock(x, y, z, Blockizer.DayloomsYellow);
				}
				else if (picker == 3) {
					worldObj.setBlock(x, y, z, Blockizer.DayloomsPink);
				}
				else {
					worldObj.setBlock(x, y, z, Blockizer.HavenGrass);
				}
			}
		}
		if (this.rand.nextInt(650) == 127) {
			x = var4 + this.rand.nextInt(16);
			z = var5 + this.rand.nextInt(16);
			y = worldObj.getHeightValue(x, z) + 20;
			new RuneShrinePlatform(10).generate(worldObj, this.rand, x, y, z);
		}
		if (this.rand.nextInt(750) == 650) {
			x = var4 + this.rand.nextInt(16);
			z = var5 + this.rand.nextInt(16);
			y = worldObj.getHeightValue(x + 15, z + 15);
			if (worldObj.getBlock(x + 15, y - 1, z + 15) == Blockizer.GrassHaven) {
				new FragmentTempleHaven().generate(worldObj, this.rand, x, y - 1, z);
			}
		}
		for (i = 0; i < 5; ++i) {
			x = var4 + this.rand.nextInt(16);
			z = var5 + this.rand.nextInt(16);
			y = worldObj.getHeightValue(x, z);
			if (worldObj.getBlock(x, y - 1, z) == Blockizer.GrassHaven) {
				new Havendales(this.rand.nextInt(3)).generate(worldObj, this.rand, x, y, z);
			}
		}
		if (this.rand.nextInt(650) == 255) {
			x = var4 + this.rand.nextInt(16);
			z = var5 + this.rand.nextInt(16);
			y = 10 + this.rand.nextInt(25);
			new DawnlightDungeon().generate(worldObj, this.rand, x, y, z);
		}
		if (this.rand.nextInt(650) == 544) {
			x = var4 + this.rand.nextInt(16);
			z = var5 + this.rand.nextInt(16);
			y = 10 + this.rand.nextInt(25);
			new LottoHavenStructure().generate(worldObj, this.rand, x, y, z);
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
		return "Eden";
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
