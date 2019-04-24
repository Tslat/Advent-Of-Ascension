package net.nevermine.dimension.deeplands;

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
import net.minecraft.world.gen.NoiseGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.nevermine.dimension.DimensionOrganizer;
import net.nevermine.izer.Blockizer;
import net.nevermine.structures.deeplands.*;

import java.util.List;
import java.util.Random;

public class ChunkProviderDeeplands implements IChunkProvider {
	private Block grass = Blockizer.DeepRock, dirt = Blockizer.DeepRock;
	private Random rand;
	private NoiseGeneratorOctaves noiseGen1, noiseGen2, noiseGen3, noiseGen5, noiseGen6;
	private NoiseGeneratorPerlin noiseGen4;
	private World worldObj;
	private WorldType type;
	private final double[] da;
	private final float[] parabolicField;
	private double[] stoneNoise = new double[256];
	private BiomeGenBase[] biomesForGeneration;
	private double[] gen1, gen2, gen3, gen4;
	private int[][] ia = new int[32][32];
	private boolean canSpawn = true;
	private WorldGenerator ribs1 = new Ribs1();
	private WorldGenerator ribs2 = new Ribs2();
	private WorldGenerator bonecircle = new BoneCircle();
	private WorldGenerator bm1 = new BlackMushroom1();
	private WorldGenerator bm2 = new BlackMushroom2();

	public ChunkProviderDeeplands(World par1World, long par2) {
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
				float f = 10.0F / MathHelper.sqrt_float((float)(j * j + k * k) + 0.2F);
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
		byte b0 = 4;
		biomesForGeneration = worldObj.getWorldChunkManager().getBiomesForGeneration(biomesForGeneration, i * 4 - 2, j * 4 - 2, 10, 10);
		generate(i * 4, 0, j * 4);
		for (int k = 0; k < 4; ++k) {
			int l = k * 5;
			int i1 = (k + 1) * 5;
			for (int j1 = 0; j1 < 4; ++j1) {
				int k1 = (l + j1) * 33;
				int l1 = (l + j1 + 1) * 33;
				int i2 = (i1 + j1) * 33;
				int j2 = (i1 + j1 + 1) * 33;
				for (int k2 = 0; k2 < 32; ++k2) {
					double d0 = 0.125D;
					double d1 = da[k1 + k2];
					double d2 = da[l1 + k2];
					double d3 = da[i2 + k2];
					double d4 = da[j2 + k2];
					double d5 = (da[k1 + k2 + 1] - d1) * d0;
					double d6 = (da[l1 + k2 + 1] - d2) * d0;
					double d7 = (da[i2 + k2 + 1] - d3) * d0;
					double d8 = (da[j2 + k2 + 1] - d4) * d0;
					for (int l2 = 0; l2 < 8; ++l2) {
						double d9 = 0.25D;
						double d10 = d1;
						double d11 = d2;
						double d12 = (d3 - d1) * d9;
						double d13 = (d4 - d2) * d9;
						for (int i3 = 0; i3 < 4; ++i3) {
							int j3 = i3 + k * 4 << 12 | 0 + j1 * 4 << 8 | k2 * 8 + l2;
							short short1 = 256;
							j3 -= short1;
							double d14 = 0.25D;
							double d16 = (d11 - d10) * d14;
							double d15 = d10 - d16;
							for (int k3 = 0; k3 < 4; ++k3) {
								if ((d15 += d16) > 0.0D) {
									if (k2 * 8 + l2 < 130)
										b[j3 += short1] = dirt;
								}
								else if (k2 * 8 + l2 < 20) {
									if (k2 * 8 + l2 < 130)
										b[j3 += short1] = grass;
								}
								else if (k2 * 8 + l2 < b0) {
									if (k2 * 8 + l2 < 130)
										b[j3 += short1] = grass;
								}
								else {
									if (k2 * 8 + l2 < 130)
										b[j3 += short1] = null;
								}
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

	public void replaceBlocksForBiome(int i, int j, Block[] ba, byte[] by, BiomeGenBase[] b) {
		double d0 = 0.03125D;
		stoneNoise = noiseGen4.func_151599_a(stoneNoise, (double)(i * 16), (double)(j * 16), 16, 16, d0 * 2.0D, d0 * 2.0D, 1.0D);
		for (int k = 0; k < 16; ++k) {
			for (int l = 0; l < 16; ++l) {
				BiomeGenBase biomegenbase = b[l + k * 16];
				genBiomeTerrain(worldObj, rand, ba, by, i * 16 + k, j * 16 + l, stoneNoise[l + k * 16], biomegenbase);
			}
		}
	}

	public final void genBiomeTerrain(World w, Random rand, Block[] blocks, byte[] bytes, int i, int j, double d, BiomeGenBase b) {
		boolean flag = true;
		Block block = b.topBlock;
		byte b0 = (byte)(b.field_150604_aj & 255);
		Block block1 = b.fillerBlock;
		int k = -1;
		int l = (int)(d / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
		int i1 = i & 15;
		int j1 = j & 15;
		int k1 = blocks.length / 256;
		for (int l1 = 255; l1 >= 0; --l1) {
			int i2 = (j1 * 16 + i1) * k1 + l1;
			if (l1 <= 0 + rand.nextInt(5)) {
				blocks[i2] = Blocks.bedrock;
			}
			else {
				Block block2 = blocks[i2];
				if (block2 != null && block2.getMaterial() != Material.air) {
					if (block2 == dirt) {
						if (k == -1) {
							if (l <= 0) {
								block = null;
								b0 = 0;
								block1 = dirt;
							}
							else if (l1 >= 59 && l1 <= 64) {
								block = b.topBlock;
								b0 = (byte)(b.field_150604_aj & 255);
								block1 = b.fillerBlock;
							}
							if (l1 < 4 && (block == null || block.getMaterial() == Material.air)) {
								if (b.getFloatTemperature(i, l1, j) < 0.15F) {
									block = Blockizer.DeepRock;
									b0 = 0;
								}
								else {
									block = dirt;
									b0 = 0;
								}
							}
							k = l;
							if (l1 >= 20) {
								blocks[i2] = block;
								bytes[i2] = b0;
							}
							else if (l1 < 56 - l) {
								block = null;
								block1 = dirt;
								blocks[i2] = dirt;
							}
							else {
								blocks[i2] = block1;
							}
						}
						else if (k > 0) {
							--k;
							blocks[i2] = block1;
							if (k == 0 && block1 == dirt) {
								k = rand.nextInt(4) + Math.max(0, l1 - 4);
								block1 = dirt;
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

	@Override
	public Chunk loadChunk(int par1, int par2) {
		return provideChunk(par1, par2);
	}

	@Override
	public Chunk provideChunk(int par1, int par2) {
		rand.setSeed((long)par1 * 341873128712L + (long)par2 * 132897987541L);
		Block[] ablock = new Block[65536];
		byte[] abyte = new byte[65536];
		generate(par1, par2, ablock);
		biomesForGeneration = worldObj.getWorldChunkManager().loadBlockGeneratorData(biomesForGeneration, par1 * 16, par2 * 16, 16, 16);
		replaceBlocksForBiome(par1, par2, ablock, abyte, biomesForGeneration);
		for (int i = 120; i < 65536; i += 256) {
			ablock[i] = Blocks.bedrock;
		}
		Chunk chunk = new Chunk(worldObj, ablock, abyte, par1, par2);
		byte[] abyte1 = chunk.getBiomeArray();
		for (int k = 0; k < abyte1.length; ++k)
			abyte1[k] = (byte)biomesForGeneration[k].biomeID;
		chunk.generateSkylightMap();
		return chunk;
	}

	private void generate(int x, int y, int z) {
		gen4 = noiseGen6.generateNoiseOctaves(gen4, x, z, 5, 5, 200.0D, 200.0D, 0.5D);
		gen1 = noiseGen3.generateNoiseOctaves(gen1, x, y, z, 5, 33, 5, 8.555150000000001D, 4.277575000000001D, 8.555150000000001D);
		gen2 = noiseGen1.generateNoiseOctaves(gen2, x, y, z, 5, 33, 5, 684.412D, 684.412D, 684.412D);
		gen3 = noiseGen2.generateNoiseOctaves(gen3, x, y, z, 5, 33, 5, 684.412D, 684.412D, 684.412D);
		boolean flag1 = false;
		boolean flag = false;
		int l = 0;
		int i1 = 0;
		double d4 = 8.5D;
		for (int j1 = 0; j1 < 5; ++j1) {
			for (int k1 = 0; k1 < 5; ++k1) {
				float f = 0.0F;
				float f1 = 0.0F;
				float f2 = 0.0F;
				byte b0 = 2;
				BiomeGenBase biomegenbase = biomesForGeneration[j1 + 2 + (k1 + 2) * 10];
				for (int l1 = -b0; l1 <= b0; ++l1) {
					for (int i2 = -b0; i2 <= b0; ++i2) {
						BiomeGenBase biomegenbase1 = biomesForGeneration[j1 + l1 + 2 + (k1 + i2 + 2) * 10];
						float f3 = biomegenbase1.rootHeight;
						float f4 = biomegenbase1.heightVariation;
						f3 = 0.0F;
						f4 = -1.0F;
						float f5 = parabolicField[l1 + 2 + (i2 + 2) * 5] / (f3 + 2.0F);
						if (biomegenbase1.rootHeight > biomegenbase.rootHeight)
							f5 /= 2.0F;
						f += f4 * f5;
						f1 += f3 * f5;
						f2 += f5;
					}
				}
				f /= f2;
				f1 /= f2;
				f = f * 0.9F + 0.1F;
				f1 = (f1 * 4.0F - 1.0F) / 8.0F;
				double d12 = gen4[i1] / 8000.0D;
				if (d12 < 0.0D) {
					d12 = -d12 * 0.3D;
				}
				d12 = d12 * 3.0D - 2.0D;
				if (d12 < 0.0D) {
					d12 /= 2.0D;
					if (d12 < -1.0D)
						d12 = -1.0D;
					d12 /= 1.4D;
					d12 /= 2.0D;
				}
				else {
					if (d12 > 1.0D)
						d12 = 1.0D;
					d12 /= 8.0D;
				}
				++i1;
				double d13 = (double)f1;
				double d14 = (double)f;
				d13 += d12 * 0.2D;
				d13 = d13 * 8.5D / 8.0D;
				double d5 = 8.5D + d13 * 4.0D;
				for (int j2 = 0; j2 < 33; ++j2) {
					double d6 = ((double)j2 - d5) * 12.0D * 128.0D / 256.0D / d14;
					if (d6 < 0.0D)
						d6 *= 4.0D;
					double d7 = gen2[l] / 512.0D;
					double d8 = gen3[l] / 512.0D;
					double d9 = (gen1[l] / 10.0D + 1.0D) / 2.0D;
					double d10 = MathHelper.denormalizeClamp(d7, d8, d9) - d6;
					if (j2 > 29) {
						double d11 = (double)((float)(j2 - 29) / 3.0F);
						d10 = d10 * (1.0D - d11) + -10.0D * d11;
					}
					da[l] = d10;
					++l;
				}
			}
		}
	}

	@Override
	public boolean chunkExists(int par1, int par2) {
		return true;
	}

	@Override
	public void populate(IChunkProvider par1IChunkProvider, int chunkX, int chunkZ) {
		int var4 = chunkX * 16;
		int var5 = chunkZ * 16;
		BiomeGenBase biomegenbase = worldObj.getBiomeGenForCoords(var4 + 16, var5 + 16);
		int x, y, z;
		int i;
		for (i = 0; i < 15; i++) {
			x = var4 + rand.nextInt(16);
			z = var5 + rand.nextInt(16);
			y = 20;
			worldObj.setBlock(x, y, z, Blockizer.DeepGrass);
		}
		for (i = 0; i < 15; i++) {
			x = var4 + rand.nextInt(16);
			z = var5 + rand.nextInt(16);
			y = 20;
			worldObj.setBlock(x, y, z, Blockizer.DeepBlooms);
		}

		if (biomegenbase == DimensionOrganizer.Deeplands) {
			if (rand.nextInt(25) == 7) {
				x = var4 + rand.nextInt(16);
				z = var5 + rand.nextInt(16);
				y = 20 - rand.nextInt(2);
				Random rand = new Random();
				int randomNum = rand.nextInt(2);
				if (randomNum == 1) {
					(new Spire1()).generate(worldObj, rand, x, y, z);
				}
				else {
					(new Spire2()).generate(worldObj, rand, x, y, z);
				}
			}
			if (rand.nextInt(6) == 2) {
				x = var4 + rand.nextInt(16);
				z = var5 + rand.nextInt(16);
				y = 20;
				int randomNum = rand.nextInt(3);
				if (randomNum == 1) {
					ribs1.generate(worldObj, rand, x, y, z);
				}
				else if (randomNum == 2) {
					bonecircle.generate(worldObj, rand, x, y, z);
				}
				else {
					ribs2.generate(worldObj, rand, x, y, z);
				}
			}

			for (i = 0; i < 1; i++) {
				if (rand.nextInt(3) == 2) {
					x = var4 + rand.nextInt(16);
					z = var5 + rand.nextInt(16);
					y = 20;
					if (worldObj.getBlock(x, y - 1, z) == Blockizer.DeepRock) {
						int randomNum = rand.nextInt(2);
						if (randomNum == 1) {
							bm1.generate(worldObj, rand, x, y, z);
						}
						else {
							bm2.generate(worldObj, rand, x, y, z);
						}
					}
				}
			}
		}
		else if (biomegenbase == DimensionOrganizer.DeeplandsFungal) {
			for (i = 0; i < 25; i++) {
				x = var4 + rand.nextInt(16);
				z = var5 + rand.nextInt(16);
				y = 20;
				worldObj.setBlock(x, y, z, Blockizer.DeepGrass);
			}
			for (i = 0; i < 3; i++) {
				x = var4 + rand.nextInt(16);
				z = var5 + rand.nextInt(16);
				y = 20;
				int randomNum = rand.nextInt(3);
				if (randomNum == 1) {
					new DeepFungus1().generate(worldObj, rand, x, y, z);
				}
				else if (randomNum == 2) {
					new DeepFungus2().generate(worldObj, rand, x, y, z);
				}
				else {
					new DeepFungus3().generate(worldObj, rand, x, y, z);
				}
			}
		}
		else {
			x = var4 + rand.nextInt(16);
			z = var5 + rand.nextInt(16);
			y = 20;
			int randomNum = rand.nextInt(3);
			if (randomNum == 1) {
				new DeepShine1().generate(worldObj, rand, x, y, z);
			}
			else if (randomNum == 2) {
				new DeepShine2().generate(worldObj, rand, x, y, z);
			}
			else {
				new DeepShine3().generate(worldObj, rand, x, y, z);
			}
			x = var4 + rand.nextInt(16);
			z = var5 + rand.nextInt(16);
			if (rand.nextInt(2) == 1)
				new DeepShine4().generate(worldObj, rand, x, y, z);
			else
				new DeepShine5().generate(worldObj, rand, x, y, z);

			for (i = 0; i < 3; i++) {
				x = var4 + rand.nextInt(16);
				z = var5 + rand.nextInt(16);
				y = 20;
				new DeepVines().generate(worldObj, rand, x, y, z);
			}
		}
		if (rand.nextInt(635) == 333) {
			x = var4 + rand.nextInt(16);
			z = var5 + rand.nextInt(16);
			y = 20;
			(new ChargingStation()).generate(worldObj, rand, x, y, z);
		}
		if (rand.nextInt(700) == 221) {
			x = var4 + rand.nextInt(16);
			z = var5 + rand.nextInt(16);
			y = 20;
			(new ArocknidCave()).generate(worldObj, rand, x, y, z);
		}
		if (rand.nextInt(635) == 447) {
			x = var4 + rand.nextInt(16);
			z = var5 + rand.nextInt(16);
			y = 20;
			(new KrorPillars()).generate(worldObj, rand, x, y, z);
		}
		if (rand.nextInt(635) == 123) {
			x = var4 + rand.nextInt(16);
			z = var5 + rand.nextInt(16);
			y = 20;
			(new LottoDeepStructure()).generate(worldObj, rand, x, y, z);
		}
		x = var4 + rand.nextInt(16);
		z = var5 + rand.nextInt(16);
		y = 19;
		worldObj.setBlock(x, y, z, Blockizer.DeepCrystal);
		// Ore and Traps//
		for (i = 0; i < 4; i++) {
			x = var4 + rand.nextInt(16);
			y = rand.nextInt(45) + 70;
			z = var5 + rand.nextInt(16);
			new WorldGenMinable(Blockizer.Deepcase, 5, Blockizer.DeepRock).generate(worldObj, rand, x, y, z);
		}
		// Ore and Traps//
		for (i = 0; i < 5; i++) {
			x = var4 + rand.nextInt(16);
			y = rand.nextInt(45) + 70;
			z = var5 + rand.nextInt(16);
			new WorldGenMinable(Blockizer.oreRuniumCharged, 16, Blockizer.DeepRock).generate(worldObj, rand, x, y, z);
		}
		for (i = 0; i < 25; i++) {
			x = var4 + rand.nextInt(16);
			y = rand.nextInt(45) + 70;
			z = var5 + rand.nextInt(16);
			int pick = rand.nextInt(3);
			if (pick == 1)
				new WorldGenMinable(Blockizer.DeeplandsExplosionTrap, 20, Blockizer.DeepRock).generate(worldObj, rand, x, y, z);
			else if (pick == 2)
				new WorldGenMinable(Blockizer.DeeplandsLavaTrap, 20, Blockizer.DeepRock).generate(worldObj, rand, x, y, z);
			else
				new WorldGenMinable(Blockizer.DeeplandsNipperTrap, 20, Blockizer.DeepRock).generate(worldObj, rand, x, y, z);
		}
	}

	@Override
	public boolean saveChunks(boolean par1, IProgressUpdate par2IProgressUpdate) {
		return true;
	}

	@Override
	public void saveExtraData() {
	}

	@Override
	public boolean unloadQueuedChunks() {
		return false;
	}

	@Override
	public boolean canSave() {
		return true;
	}

	@Override
	public String makeString() {
		return "Deeplands";
	}

	@Override
	public List getPossibleCreatures(EnumCreatureType c, int x, int y, int z) {
		BiomeGenBase biomegenbase = worldObj.getBiomeGenForCoords(x, z);
		return biomegenbase.getSpawnableList(c);
	}

	@Override
	public ChunkPosition func_147416_a(World w, String s, int x, int y, int z) {
		return null;
	}

	@Override
	public int getLoadedChunkCount() {
		return 0;
	}

	@Override
	public void recreateStructures(int par1, int par2) {
	}
}