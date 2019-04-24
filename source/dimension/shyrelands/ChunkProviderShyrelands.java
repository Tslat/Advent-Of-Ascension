package net.nevermine.dimension.shyrelands;

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
import net.nevermine.block.generation.structure.BlockSpawner;
import net.nevermine.izer.Blockizer;
import net.nevermine.npc.entity.EntityShyreArcher;
import net.nevermine.npc.entity.EntityShyreBanker;
import net.nevermine.npc.entity.lottoman.EntityLottomanShyrelands;

import java.util.List;
import java.util.Random;

public class ChunkProviderShyrelands implements IChunkProvider {
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
	private double[] stoneNoise = new double[256];
	private BiomeGenBase[] biomesForGeneration;
	private double[] gen1;
	private double[] gen2;
	private double[] gen3;
	private double[] gen4;
	private int[][] ia = new int[32][32];

	public ChunkProviderShyrelands(World par1World, long par2) {
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
				float f;
				parabolicField[j + 2 + (k + 2) * 5] = f = 10.0f / MathHelper.sqrt_float(((float)(j * j + k * k) + 0.2f));
			}
		}
		NoiseGenerator[] noiseGens = new NoiseGenerator[] {noiseGen1, noiseGen2, noiseGen3, noiseGen4, noiseGen5, noiseGen6};
		noiseGens = TerrainGen.getModdedNoiseGenerators(par1World, rand, noiseGens);
		noiseGen1 = (NoiseGeneratorOctaves)noiseGens[0];
		noiseGen2 = (NoiseGeneratorOctaves)noiseGens[1];
		noiseGen3 = (NoiseGeneratorOctaves)noiseGens[2];
		noiseGen4 = (NoiseGeneratorPerlin)noiseGens[3];
		noiseGen5 = (NoiseGeneratorOctaves)noiseGens[4];
		noiseGen6 = (NoiseGeneratorOctaves)noiseGens[5];
	}

	public void generate(int i, int j, Block[] b) {
		ShyrelandsChunkBuilder builder = new ShyrelandsChunkBuilder();
		ShyrelandsChunkBuilder.toTerrainArray(builder.buildChunk(), b);
	}

	public void replaceBlocksForBiome(int i, int j, Block[] ba, byte[] by, BiomeGenBase[] b) {
	}

	public final void genBiomeTerrain(World p_150560_1_, Random p_150560_2_, Block[] p_150560_3_, byte[] p_150560_4_, int p_150560_5_, int p_150560_6_, double p_150560_7_, BiomeGenBase b) {
		boolean flag = true;
		Block block = b.topBlock;
		byte b0 = (byte)(b.field_150604_aj & 255);
		Block block1 = b.fillerBlock;
		int k = -1;
		int l = (int)(p_150560_7_ / 3.0 + 3.0 + p_150560_2_.nextDouble() * 0.25);
		int i1 = p_150560_5_ & 15;
		int j1 = p_150560_6_ & 15;
		int k1 = p_150560_3_.length / 256;
		for (int l1 = 255; l1 >= 0; --l1) {
			int i2 = (j1 * 16 + i1) * k1 + l1;
			if (l1 <= 0 + p_150560_2_.nextInt(5)) {
				p_150560_3_[i2] = Blocks.bedrock;
				continue;
			}
			Block block2 = p_150560_3_[i2];
			if (block2 != null && block2.getMaterial() != Material.air) {
				if (block2 != Blockizer.StoneToxic)
					continue;
				if (k == -1) {
					if (l <= 0) {
						block = null;
						b0 = 0;
						block1 = Blockizer.StoneToxic;
					}
					else if (l1 >= 59 && l1 <= 64) {
						block = b.topBlock;
						b0 = (byte)(b.field_150604_aj & 255);
						block1 = b.fillerBlock;
					}
					if (l1 < 63 && (block == null || block.getMaterial() == Material.air)) {
						if (b.getFloatTemperature(p_150560_5_, l1, p_150560_6_) < 0.15f) {
							block = Blocks.water;
							b0 = 0;
						}
						else {
							block = Blockizer.StoneToxic;
							b0 = 0;
						}
					}
					k = l;
					if (l1 >= 62) {
						p_150560_3_[i2] = block;
						p_150560_4_[i2] = b0;
						continue;
					}
					if (l1 < 56 - l) {
						block = null;
						block1 = Blockizer.StoneToxic;
						p_150560_3_[i2] = Blockizer.StoneToxic;
						continue;
					}
					p_150560_3_[i2] = block1;
					continue;
				}
				if (k <= 0)
					continue;
				p_150560_3_[i2] = block1;
				if (--k != 0 || block1 != Blockizer.StoneToxic)
					continue;
				k = p_150560_2_.nextInt(4) + Math.max(0, l1 - 63);
				block1 = Blockizer.StoneToxic;
				continue;
			}
			k = -1;
		}
	}

	public Chunk loadChunk(int par1, int par2) {
		return provideChunk(par1, par2);
	}

	public Chunk provideChunk(int par1, int par2) {
		rand.setSeed((long)par1 * 341873128712L + (long)par2 * 132897987541L);
		Block[] ablock = new Block[65536];
		byte[] abyte = new byte[65536];
		generate(par1, par2, ablock);
		biomesForGeneration = worldObj.getWorldChunkManager().loadBlockGeneratorData(biomesForGeneration, par1 * 16, par2 * 16, 16, 16);
		replaceBlocksForBiome(par1, par2, ablock, abyte, biomesForGeneration);
		Chunk chunk = new Chunk(worldObj, ablock, abyte, par1, par2);
		byte[] abyte1 = chunk.getBiomeArray();
		for (int k = 0; k < abyte1.length; ++k) {
			abyte1[k] = (byte)biomesForGeneration[k].biomeID;
		}
		chunk.generateSkylightMap();
		return chunk;
	}

	private void generate(int x, int y, int z) {
		double d0 = 684.412;
		double d1 = 684.412;
		double d2 = 512.0;
		double d3 = 512.0;
		gen4 = noiseGen6.generateNoiseOctaves(gen4, x, z, 5, 5, 200.0, 200.0, 0.5);
		gen1 = noiseGen3.generateNoiseOctaves(gen1, x, y, z, 5, 33, 5, 8.555150000000001, 4.277575000000001, 8.555150000000001);
		gen2 = noiseGen1.generateNoiseOctaves(gen2, x, y, z, 5, 33, 5, 684.412, 684.412, 684.412);
		gen3 = noiseGen2.generateNoiseOctaves(gen3, x, y, z, 5, 33, 5, 684.412, 684.412, 684.412);
		boolean flag1 = false;
		boolean flag = false;
		int l = 0;
		int i1 = 0;
		double d4 = 8.5;
		for (int j1 = 0; j1 < 5; ++j1) {
			for (int k1 = 0; k1 < 5; ++k1) {
				float f = 0.0f;
				float f1 = 0.0f;
				float f2 = 0.0f;
				int b0 = 2;
				BiomeGenBase biomegenbase = biomesForGeneration[j1 + 2 + (k1 + 2) * 10];
				for (int l1 = -b0; l1 <= b0; ++l1) {
					for (int i2 = -b0; i2 <= b0; ++i2) {
						BiomeGenBase biomegenbase1 = biomesForGeneration[j1 + l1 + 2 + (k1 + i2 + 2) * 10];
						float f3 = biomegenbase1.rootHeight;
						float f4 = biomegenbase1.heightVariation;
						if (type == WorldType.AMPLIFIED && f3 > 0.0f) {
							f3 = 1.0f + f3 * 2.0f;
							f4 = 1.0f + f4 * 4.0f;
						}
						float f5 = parabolicField[l1 + 2 + (i2 + 2) * 5] / (f3 + 2.0f);
						if (biomegenbase1.rootHeight > biomegenbase.rootHeight) {
							f5 /= 2.0f;
						}
						f += f4 * f5;
						f1 += f3 * f5;
						f2 += f5;
					}
				}
				f /= f2;
				f1 /= f2;
				f = f * 0.9f + 0.1f;
				f1 = (f1 * 4.0f - 1.0f) / 8.0f;
				double d12 = gen4[i1] / 8000.0;
				if (d12 < 0.0) {
					d12 = (-d12) * 0.3;
				}
				if ((d12 = d12 * 3.0 - 2.0) < 0.0) {
					if ((d12 /= 2.0) < -1.0) {
						d12 = -1.0;
					}
					d12 /= 1.4;
					d12 /= 2.0;
				}
				else {
					if (d12 > 1.0) {
						d12 = 1.0;
					}
					d12 /= 8.0;
				}
				++i1;
				double d13 = f1;
				double d14 = f;
				d13 += d12 * 0.2;
				d13 = d13 * 8.5 / 8.0;
				double d5 = 8.5 + d13 * 4.0;
				for (int j2 = 0; j2 < 33; ++j2) {
					double d6 = (j2 - d5) * 12.0 * 128.0 / 256.0 / d14;
					if (d6 < 0.0) {
						d6 *= 4.0;
					}
					double d7 = gen2[l] / 512.0;
					double d8 = gen3[l] / 512.0;
					double d9 = (gen1[l] / 10.0 + 1.0) / 2.0;
					double d10 = MathHelper.denormalizeClamp(d7, d8, d9) - d6;
					if (j2 > 29) {
						double d11 = (float)(j2 - 29) / 3.0f;
						d10 = d10 * (1.0 - d11) + -10.0 * d11;
					}
					da[l] = d10;
					++l;
				}
			}
		}
	}

	public boolean chunkExists(int par1, int par2) {
		return true;
	}

	public void populate(IChunkProvider par1IChunkProvider, int par2, int par3) {
		int var4 = par2 * 16;
		int var5 = par3 * 16;
		BiomeGenBase biomegenbase = worldObj.getBiomeGenForCoords(var4 + 16, var5 + 16);
		long p1 = rand.nextLong() / 2L * 2L + 1L;
		long j1 = rand.nextLong() / 2L * 2L + 1L;
		rand.setSeed(worldObj.getSeed());
		rand.setSeed((long)par2 * p1 + (long)par3 * j1 ^ worldObj.getSeed());
		for (int x = 0; x < 16; ++x) {
			for (int z = 0; z < 16; ++z) {
				if (worldObj.getBlock(x + var4, 31, z + var5) instanceof BlockSpawner) {
					Block b = worldObj.getBlock(x + var4, 31, z + var5);
					TileEntity te = b.createTileEntity(worldObj, 0);
					worldObj.setTileEntity(var4 + x, 31, var5 + z, te);
					continue;
				}
				if (worldObj.getBlock(x + var4, 32, z + var5) == Blockizer.ShyreCloud) {
					worldObj.setBlock(x + var4, 32, z + var5, Blocks.air);
					if (worldObj.isRemote)
						continue;
					EntityLottomanShyrelands var2 = new EntityLottomanShyrelands(worldObj);
					var2.setLocationAndAngles((x + var4), 32.0, (z + var5), rand.nextFloat() * 360.0f, 0.0f);
					worldObj.spawnEntityInWorld(var2);
					continue;
				}
				if (worldObj.getBlock(x + var4, 36, z + var5) != Blockizer.ShyreCloud)
					continue;
				worldObj.setBlock(x + var4, 36, z + var5, Blocks.air);
				if (worldObj.isRemote)
					continue;
				if (rand.nextInt(2) == 1) {
					EntityShyreBanker var2 = new EntityShyreBanker(worldObj);
					var2.setLocationAndAngles((x + var4), 36.0, (z + var5), rand.nextFloat() * 360.0f, 0.0f);
					worldObj.spawnEntityInWorld(var2);
					continue;
				}
				EntityShyreArcher var2 = new EntityShyreArcher(worldObj);
				var2.setLocationAndAngles((x + var4), 36.0, (z + var5), rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var2);
			}
		}
	}

	public boolean saveChunks(boolean par1, IProgressUpdate par2IProgressUpdate) {
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
		return "Shyrelands";
	}

	public List getPossibleCreatures(EnumCreatureType par1EnumCreatureType, int par2, int par3, int par4) {
		BiomeGenBase biomegenbase = worldObj.getBiomeGenForCoords(par2, par4);
		return biomegenbase.getSpawnableList(par1EnumCreatureType);
	}

	public ChunkPosition func_147416_a(World p_147416_1_, String p_147416_2_, int p_147416_3_, int p_147416_4_, int p_147416_5_) {
		return null;
	}

	public int getLoadedChunkCount() {
		return 0;
	}

	public void recreateStructures(int par1, int par2) {
	}
}

