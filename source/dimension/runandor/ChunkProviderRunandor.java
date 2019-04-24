package net.nevermine.dimension.runandor;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.NoiseGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.nevermine.izer.Blockizer;

import java.util.List;
import java.util.Random;

public class ChunkProviderRunandor implements IChunkProvider {
	private int count;
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

	public ChunkProviderRunandor(final World par1World, final long par2) {
		count = 0;
		stoneNoise = new double[256];
		ia = new int[32][32];
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

	public void generate(final int i, final int j, final Block[] b) {
		++count;
		if (count > 25) {
			count = 0;
		}
		final RunandorChunkBuilder builder = new RunandorChunkBuilder();
		RunandorChunkBuilder.toTerrainArray(builder.buildChunk(count), b);
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
					if (block3 == Blockizer.StoneToxic) {
						if (k == -1) {
							if (l <= 0) {
								block = null;
								b2 = 0;
								block2 = Blockizer.StoneToxic;
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
									block = Blockizer.StoneToxic;
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
								block2 = Blockizer.StoneToxic;
								p_150560_3_[i2] = Blockizer.StoneToxic;
							}
							else {
								p_150560_3_[i2] = block2;
							}
						}
						else if (k > 0) {
							--k;
							p_150560_3_[i2] = block2;
							if (k == 0 && block2 == Blockizer.StoneToxic) {
								k = p_150560_2_.nextInt(4) + Math.max(0, l2 - 63);
								block2 = Blockizer.StoneToxic;
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
		for (int x = 0; x < 16; ++x) {
			for (int z = 0; z < 16; ++z) {
				if (worldObj.getBlock(x + var4, 20, z + var5) == Blockizer.LampVox) {
					worldObj.func_147451_t(x + var4, 20, z + var5);
					worldObj.markBlockRangeForRenderUpdate(x + var4, 20, z + var5, x + var4 + 12, 20, z + var5 + 12);
				}
				if (worldObj.getBlock(x + var4, 27, z + var5) == Blockizer.LampVox) {
					worldObj.func_147451_t(x + var4, 27, z + var5);
					worldObj.markBlockRangeForRenderUpdate(x + var4, 27, z + var5, x + var4 + 12, 27, z + var5 + 12);
				}
				if (worldObj.getBlock(x + var4, 19, z + var5) == Blockizer.SpawnerNightwing) {
					final Block b = worldObj.getBlock(x + var4, 19, z + var5);
					final TileEntity te = b.createTileEntity(worldObj, 0);
					worldObj.setTileEntity(var4 + x, 19, var5 + z, te);
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
		return "Runandor";
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
