package net.nevermine.dimension.crystevia;

import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.NoiseGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.ChunkProviderEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.nevermine.izer.Blockizer;
import net.nevermine.structures.crystevia.*;

import java.util.List;
import java.util.Random;

public class ChunkProviderCrystevia implements IChunkProvider {
	private Random hellRNG;
	private NoiseGeneratorOctaves netherNoiseGen1;
	private NoiseGeneratorOctaves netherNoiseGen2;
	private NoiseGeneratorOctaves netherNoiseGen3;
	private NoiseGeneratorOctaves netherrackExculsivityNoiseGen;
	public NoiseGeneratorOctaves netherNoiseGen6;
	public NoiseGeneratorOctaves netherNoiseGen7;
	private World worldObj;
	private double[] noiseField;
	private double[] slowsandNoise;
	private double[] gravelNoise;
	private double[] netherrackExclusivityNoise;
	double[] noiseData1;
	double[] noiseData2;
	double[] noiseData3;
	double[] noiseData4;
	double[] noiseData5;
	private WorldGenerator c1;
	private WorldGenerator c2;
	private WorldGenerator c3;
	private WorldGenerator c4;
	private WorldGenerator c5;
	private WorldGenerator c6;
	private WorldGenerator ct1;
	private WorldGenerator ct2;
	private WorldGenerator ct3;
	private WorldGenerator ct4;
	private WorldGenerator ct5;
	private WorldGenerator ct6;
	private WorldGenerator crys;
	private WorldGenerator lot;
	private WorldGenerator tad;
	private WorldGenerator ac;

	public ChunkProviderCrystevia(final World par1World, final long par2) {
		slowsandNoise = new double[256];
		gravelNoise = new double[256];
		netherrackExclusivityNoise = new double[256];
		c1 = new Crystal1();
		c2 = new Crystal2();
		c3 = new Crystal3();
		c4 = new Crystal4();
		c5 = new Crystal5();
		c6 = new Crystal6();
		ct1 = new CrystalTransferGreen();
		ct2 = new CrystalTransferYellow();
		ct3 = new CrystalTransferBlue();
		ct4 = new CrystalTransferPurple();
		ct5 = new CrystalTransferRed();
		ct6 = new CrystalTransferWhite();
		crys = new CrystocoreSpawner();
		lot = new CrysteviaLottoStructure();
		tad = new CrystalTradingPost();
		ac = new AuguryCrystal();
		worldObj = par1World;
		hellRNG = new Random(par2);
		netherNoiseGen1 = new NoiseGeneratorOctaves(hellRNG, 16);
		netherNoiseGen2 = new NoiseGeneratorOctaves(hellRNG, 16);
		netherNoiseGen3 = new NoiseGeneratorOctaves(hellRNG, 8);
		netherrackExculsivityNoiseGen = new NoiseGeneratorOctaves(hellRNG, 4);
		netherNoiseGen6 = new NoiseGeneratorOctaves(hellRNG, 10);
		netherNoiseGen7 = new NoiseGeneratorOctaves(hellRNG, 16);
		NoiseGenerator[] noiseGens = {netherNoiseGen1, netherNoiseGen2, netherNoiseGen3, netherrackExculsivityNoiseGen, netherNoiseGen6, netherNoiseGen7};
		noiseGens = TerrainGen.getModdedNoiseGenerators(par1World, hellRNG, noiseGens);
		netherNoiseGen1 = (NoiseGeneratorOctaves)noiseGens[0];
		netherNoiseGen2 = (NoiseGeneratorOctaves)noiseGens[1];
		netherNoiseGen3 = (NoiseGeneratorOctaves)noiseGens[2];
		netherrackExculsivityNoiseGen = (NoiseGeneratorOctaves)noiseGens[3];
		netherNoiseGen6 = (NoiseGeneratorOctaves)noiseGens[4];
		netherNoiseGen7 = (NoiseGeneratorOctaves)noiseGens[5];
	}

	public void generateNetherTerrain(final int par1, final int par2, final Block[] par3ArrayOfByte) {
		final byte b0 = 4;
		final byte b2 = 32;
		final int k = b0 + 1;
		final byte b3 = 17;
		final int l = b0 + 1;
		noiseField = initializeNoiseField(noiseField, par1 * b0, 0, par2 * b0, k, b3, l);
		for (int i1 = 0; i1 < b0; ++i1) {
			for (int j1 = 0; j1 < b0; ++j1) {
				for (int k2 = 0; k2 < 16; ++k2) {
					final double d0 = 0.125;
					double d2 = noiseField[((i1 + 0) * l + j1 + 0) * b3 + k2 + 0];
					double d3 = noiseField[((i1 + 0) * l + j1 + 1) * b3 + k2 + 0];
					double d4 = noiseField[((i1 + 1) * l + j1 + 0) * b3 + k2 + 0];
					double d5 = noiseField[((i1 + 1) * l + j1 + 1) * b3 + k2 + 0];
					final double d6 = (noiseField[((i1 + 0) * l + j1 + 0) * b3 + k2 + 1] - d2) * d0;
					final double d7 = (noiseField[((i1 + 0) * l + j1 + 1) * b3 + k2 + 1] - d3) * d0;
					final double d8 = (noiseField[((i1 + 1) * l + j1 + 0) * b3 + k2 + 1] - d4) * d0;
					final double d9 = (noiseField[((i1 + 1) * l + j1 + 1) * b3 + k2 + 1] - d5) * d0;
					for (int l2 = 0; l2 < 8; ++l2) {
						final double d10 = 0.25;
						double d11 = d2;
						double d12 = d3;
						final double d13 = (d4 - d2) * d10;
						final double d14 = (d5 - d3) * d10;
						for (int i2 = 0; i2 < 4; ++i2) {
							int j2 = i2 + i1 * 4 << 11 | 0 + j1 * 4 << 7 | k2 * 8 + l2;
							final short short1 = 128;
							final double d15 = 0.25;
							double d16 = d11;
							final double d17 = (d12 - d11) * d15;
							for (int k3 = 0; k3 < 4; ++k3) {
								Block l3 = null;
								if (d16 > 0.0) {
									l3 = Blockizer.CrysteviaRock;
								}
								par3ArrayOfByte[j2] = l3;
								j2 += short1;
								d16 += d17;
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

	public void replaceBlocksForBiome(final int par1, final int par2, final Block[] par3ArrayOfByte) {
		final ChunkProviderEvent.ReplaceBiomeBlocks event = new ChunkProviderEvent.ReplaceBiomeBlocks(this, par1, par2, par3ArrayOfByte, null);
		MinecraftForge.EVENT_BUS.post(event);
		if (event.getResult() == Event.Result.DENY) {
			return;
		}
		final byte b0 = 64;
		final double d0 = 0.03125;
		netherrackExclusivityNoise = netherrackExculsivityNoiseGen.generateNoiseOctaves(netherrackExclusivityNoise, par1 * 16, par2 * 16, 0, 16, 16, 1, d0 * 2.0, d0 * 2.0, d0 * 2.0);
		for (int k = 0; k < 16; ++k) {
			for (int l = 0; l < 16; ++l) {
				final boolean flag = slowsandNoise[k + l * 16] + hellRNG.nextDouble() * 0.2 > 0.0;
				final boolean flag2 = gravelNoise[k + l * 16] + hellRNG.nextDouble() * 0.2 > 0.0;
				final int i1 = (int)(netherrackExclusivityNoise[k + l * 16] / 3.0 + 3.0 + hellRNG.nextDouble() * 0.25);
				int j1 = -1;
				Block b2 = Blockizer.CrysteviaRock;
				Block b3 = Blockizer.CrysteviaRock;
				for (int k2 = 127; k2 >= 0; --k2) {
					final int l2 = (l * 16 + k) * 128 + k2;
					if (k2 < 127 - hellRNG.nextInt(5) && k2 > 0 + hellRNG.nextInt(5)) {
						final Block b4 = par3ArrayOfByte[l2];
						if (b4 == null) {
							j1 = -1;
						}
						else if (b4 == Blockizer.CrysteviaRock) {
							if (j1 == -1) {
								if (i1 <= 0) {
									b2 = null;
									b3 = Blockizer.CrysteviaRock;
								}
								else if (k2 >= b0 - 4 && k2 <= b0 + 1) {
									b2 = Blockizer.CrysteviaRock;
									b3 = Blockizer.CrysteviaRock;
									if (flag2) {
										b2 = Blockizer.CrysteviaRock;
									}
									if (flag2) {
										b3 = Blockizer.CrysteviaRock;
									}
									if (flag) {
										b2 = Blockizer.CrysteviaRock;
									}
									if (flag) {
										b3 = Blockizer.CrysteviaRock;
									}
								}
								if (k2 < b0 && b2 == null) {
									b2 = Blocks.water;
								}
								j1 = i1;
								if (k2 >= b0 - 1) {
									par3ArrayOfByte[l2] = b2;
								}
								else {
									par3ArrayOfByte[l2] = b3;
								}
							}
							else if (j1 > 0) {
								--j1;
								par3ArrayOfByte[l2] = b3;
							}
						}
					}
					else {
						par3ArrayOfByte[l2] = Blocks.bedrock;
					}
				}
			}
		}
	}

	public Chunk loadChunk(final int par1, final int par2) {
		return provideChunk(par1, par2);
	}

	public Chunk provideChunk(final int par1, final int par2) {
		hellRNG.setSeed(par1 * 341873128712L + par2 * 132897987541L);
		final Block[] blocks = new Block[32768];
		generateNetherTerrain(par1, par2, blocks);
		replaceBlocksForBiome(par1, par2, blocks);
		for (int i = 0; i < 32767; ++i) {
			if (blocks[i] == Blockizer.CrysteviaRock && blocks[i + 1] != null) {
				blocks[i] = Blockizer.CrysteviaRock;
			}
		}
		final Chunk chunk = new Chunk(worldObj, blocks, par1, par2);
		final BiomeGenBase[] abiomegenbase = worldObj.getWorldChunkManager().loadBlockGeneratorData(null, par1 * 16, par2 * 16, 16, 16);
		final byte[] abyte1 = chunk.getBiomeArray();
		for (int k = 0; k < abyte1.length; ++k) {
			abyte1[k] = (byte)abiomegenbase[k].biomeID;
		}
		chunk.resetRelightChecks();
		return chunk;
	}

	private double[] initializeNoiseField(double[] par1ArrayOfDouble, final int par2, final int par3, final int par4, final int par5, final int par6, final int par7) {
		final ChunkProviderEvent.InitNoiseField event = new ChunkProviderEvent.InitNoiseField(this, par1ArrayOfDouble, par2, par3, par4, par5, par6, par7);
		MinecraftForge.EVENT_BUS.post(event);
		if (event.getResult() == Event.Result.DENY) {
			return event.noisefield;
		}
		if (par1ArrayOfDouble == null) {
			par1ArrayOfDouble = new double[par5 * par6 * par7];
		}
		final double d0 = 684.412;
		final double d2 = 2053.236;
		noiseData4 = netherNoiseGen6.generateNoiseOctaves(noiseData4, par2, par3, par4, par5, 1, par7, 1.0, 0.0, 1.0);
		noiseData5 = netherNoiseGen7.generateNoiseOctaves(noiseData5, par2, par3, par4, par5, 1, par7, 100.0, 0.0, 100.0);
		noiseData1 = netherNoiseGen3.generateNoiseOctaves(noiseData1, par2, par3, par4, par5, par6, par7, d0 / 80.0, d2 / 60.0, d0 / 80.0);
		noiseData2 = netherNoiseGen1.generateNoiseOctaves(noiseData2, par2, par3, par4, par5, par6, par7, d0, d2, d0);
		noiseData3 = netherNoiseGen2.generateNoiseOctaves(noiseData3, par2, par3, par4, par5, par6, par7, d0, d2, d0);
		int k1 = 0;
		int l1 = 0;
		final double[] adouble1 = new double[par6];
		for (int i2 = 0; i2 < par6; ++i2) {
			adouble1[i2] = Math.cos(i2 * 3.141592653589793 * 6.0 / par6) * 2.0;
			double d3 = i2;
			if (i2 > par6 / 2) {
				d3 = par6 - 1 - i2;
			}
			if (d3 < 4.0) {
				d3 = 4.0 - d3;
				final double[] array = adouble1;
				final int n = i2;
				array[n] -= d3 * d3 * d3 * 10.0;
			}
		}
		for (int i2 = 0; i2 < par5; ++i2) {
			for (int j2 = 0; j2 < par7; ++j2) {
				double d4 = (noiseData4[l1] + 256.0) / 512.0;
				if (d4 > 1.0) {
					d4 = 1.0;
				}
				final double d5 = 0.0;
				double d6 = noiseData5[l1] / 8000.0;
				if (d6 < 0.0) {
					d6 = -d6;
				}
				d6 = d6 * 3.0 - 3.0;
				if (d6 < 0.0) {
					d6 /= 2.0;
					if (d6 < -1.0) {
						d6 = -1.0;
					}
					d6 /= 1.4;
					d6 /= 2.0;
					d4 = 0.0;
				}
				else {
					if (d6 > 1.0) {
						d6 = 1.0;
					}
					d6 /= 6.0;
				}
				d4 += 0.5;
				d6 = d6 * par6 / 16.0;
				++l1;
				for (int k2 = 0; k2 < par6; ++k2) {
					double d7 = 0.0;
					final double d8 = adouble1[k2];
					final double d9 = noiseData2[k1] / 512.0;
					final double d10 = noiseData3[k1] / 512.0;
					final double d11 = (noiseData1[k1] / 10.0 + 1.0) / 2.0;
					if (d11 < 0.0) {
						d7 = d9;
					}
					else if (d11 > 1.0) {
						d7 = d10;
					}
					else {
						d7 = d9 + (d10 - d9) * d11;
					}
					d7 -= d8;
					if (k2 > par6 - 4) {
						final double d12 = (k2 - (par6 - 4)) / 3.0f;
						d7 = d7 * (1.0 - d12) + -10.0 * d12;
					}
					if (k2 < d5) {
						double d12 = (d5 - k2) / 4.0;
						if (d12 < 0.0) {
							d12 = 0.0;
						}
						if (d12 > 1.0) {
							d12 = 1.0;
						}
						d7 = d7 * (1.0 - d12) + -10.0 * d12;
					}
					par1ArrayOfDouble[k1] = d7;
					++k1;
				}
			}
		}
		return par1ArrayOfDouble;
	}

	public boolean chunkExists(final int par1, final int par2) {
		return true;
	}

	public void populate(final IChunkProvider provider, final int x, final int y) {
		BlockSand.fallInstantly = true;
		MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Pre(provider, worldObj, hellRNG, x, y, false));
		final int var4 = x * 16;
		final int var5 = y * 16;
		for (int i = 0; i < 750; ++i) {
			final int var6 = var4 + hellRNG.nextInt(16) + 8;
			final int var7 = var5 + hellRNG.nextInt(16) + 8;
			final int var8 = hellRNG.nextInt(82) + 20;
			final int pick = hellRNG.nextInt(6);
			if (worldObj.getBlock(var6, var8, var7) == Blockizer.CrysteviaRock && worldObj.getBlock(var6, var8 - 1, var7) == Blocks.air && hellRNG.nextInt(30) == 14) {
				worldObj.setBlock(var6, var8 - 1, var7, Blocks.water);
				worldObj.setBlock(var6, var8, var7, Blocks.water);
			}
			else if (worldObj.getBlock(var6, var8 - 1, var7) == Blockizer.CrysteviaRock && worldObj.getBlock(var6, var8, var7) == Blocks.air) {
				if (hellRNG.nextInt(9) == 4) {
					if (pick == 1) {
						c1.generate(worldObj, hellRNG, var6, var8 - 1, var7);
					}
					else if (pick == 2) {
						c2.generate(worldObj, hellRNG, var6, var8 - 1, var7);
					}
					else if (pick == 3) {
						c3.generate(worldObj, hellRNG, var6, var8 - 1, var7);
					}
					else if (pick == 4) {
						c4.generate(worldObj, hellRNG, var6, var8 - 1, var7);
					}
					else if (pick == 5) {
						c5.generate(worldObj, hellRNG, var6, var8 - 1, var7);
					}
					else {
						c6.generate(worldObj, hellRNG, var6, var8 - 1, var7);
					}
				}
				else if (pick == 1) {
					worldObj.setBlock(var6, var8, var7, Blockizer.CrystalPurple);
				}
				else if (pick == 2) {
					worldObj.setBlock(var6, var8, var7, Blockizer.CrystalGreen);
				}
				else if (pick == 3) {
					worldObj.setBlock(var6, var8, var7, Blockizer.CrystalYellow);
				}
				else if (pick == 4) {
					worldObj.setBlock(var6, var8, var7, Blockizer.CrystalWhite);
				}
				else if (pick == 5) {
					worldObj.setBlock(var6, var8, var7, Blockizer.CrystalRed);
				}
				else {
					worldObj.setBlock(var6, var8, var7, Blockizer.CrystalBlue);
				}
			}
		}
		if (hellRNG.nextInt(400) == 255) {
			final int var6 = var4 + hellRNG.nextInt(16) + 8;
			final int var7 = var5 + hellRNG.nextInt(16) + 8;
			final int var8 = hellRNG.nextInt(82) + 20;
			if (worldObj.getBlock(var6 + 2, var8 + 3, var7 + 2) == Blocks.air) {
				final int pick2 = hellRNG.nextInt(4);
				if (pick2 == 1) {
					crys.generate(worldObj, hellRNG, var6, var8, var7);
				}
				else if (pick2 == 2) {
					lot.generate(worldObj, hellRNG, var6, var8, var7);
				}
				else if (pick2 == 3) {
					tad.generate(worldObj, hellRNG, var6, var8, var7);
				}
				else {
					ac.generate(worldObj, hellRNG, var6, var8, var7);
				}
			}
		}
		if (hellRNG.nextInt(30) == 25) {
			final int var6 = var4 + hellRNG.nextInt(16) + 8;
			final int var7 = var5 + hellRNG.nextInt(16) + 8;
			final int var8 = hellRNG.nextInt(82) + 20;
			if (worldObj.getBlock(var6 + 2, var8, var7 + 2) == Blocks.air && worldObj.getBlock(var6 + 4, var8 + 5, var7 + 4) == Blocks.air) {
				final int pick2 = hellRNG.nextInt(6);
				if (pick2 == 1) {
					ct1.generate(worldObj, hellRNG, var6, var8, var7);
				}
				else if (pick2 == 2) {
					ct2.generate(worldObj, hellRNG, var6, var8, var7);
				}
				else if (pick2 == 3) {
					ct3.generate(worldObj, hellRNG, var6, var8, var7);
				}
				else if (pick2 == 4) {
					ct4.generate(worldObj, hellRNG, var6, var8, var7);
				}
				else if (pick2 == 5) {
					ct5.generate(worldObj, hellRNG, var6, var8, var7);
				}
				else {
					ct6.generate(worldObj, hellRNG, var6, var8, var7);
				}
			}
		}
		MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Post(worldObj, hellRNG, var4, var5));
		MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Post(provider, worldObj, hellRNG, x, y, false));
		BlockSand.fallInstantly = false;
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
		return "Crystevia";
	}

	public List getPossibleCreatures(final EnumCreatureType enumcreaturetype, final int i, final int j, final int k) {
		final BiomeGenBase var5 = worldObj.getBiomeGenForCoords(i, k);
		return (var5 == null) ? null : var5.getSpawnableList(enumcreaturetype);
	}

	public int getLoadedChunkCount() {
		return 0;
	}

	public void recreateStructures(final int i, final int j) {
	}

	public void saveExtraData() {
	}

	public ChunkPosition func_147416_a(final World p_147416_1_, final String p_147416_2_, final int p_147416_3_, final int p_147416_4_, final int p_147416_5_) {
		return null;
	}
}
