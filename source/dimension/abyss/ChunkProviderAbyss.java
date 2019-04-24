package net.nevermine.dimension.abyss;

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
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.nevermine.dimension.DimensionOrganizer;
import net.nevermine.izer.Blockizer;
import net.nevermine.structures.abyss.*;
import net.nevermine.structures.vanilla.RuneShrinePlatform;

import java.util.List;
import java.util.Random;

public class ChunkProviderAbyss implements IChunkProvider {

	private Random rand;
	private NoiseGeneratorOctaves noiseGen1, noiseGen2, noiseGen3, noiseGen5, noiseGen6;
	private NoiseGeneratorPerlin noiseGen4;
	private World worldObj;
	private WorldType type;
	private final double[] da;
	private final float[] parabolicField;
	private double[] stoneNoise = new double[256];
	private MapGenBase caveGenerator = new MapGenAbyssCaves();
	private BiomeGenBase[] biomesForGeneration;
	private double[] gen1, gen2, gen3, gen4;
	private int[][] ia = new int[32][32];

	public ChunkProviderAbyss(World par1World, long par2) {
		this.worldObj = par1World;
		this.type = par1World.getWorldInfo().getTerrainType();
		this.rand = new Random(par2);
		this.noiseGen1 = new NoiseGeneratorOctaves(this.rand, 16);
		this.noiseGen2 = new NoiseGeneratorOctaves(this.rand, 16);
		this.noiseGen3 = new NoiseGeneratorOctaves(this.rand, 8);
		this.noiseGen4 = new NoiseGeneratorPerlin(this.rand, 4);
		this.noiseGen5 = new NoiseGeneratorOctaves(this.rand, 10);
		this.noiseGen6 = new NoiseGeneratorOctaves(this.rand, 16);
		this.da = new double[825];
		this.parabolicField = new float[25];
		for (int j = -2; j <= 2; ++j) {
			for (int k = -2; k <= 2; ++k) {
				float f = 10.0F / MathHelper.sqrt_float((float)(j * j + k * k) + 0.2F);
				this.parabolicField[j + 2 + (k + 2) * 5] = f;
			}
		}
		NoiseGenerator[] noiseGens = {noiseGen1, noiseGen2, noiseGen3, noiseGen4, noiseGen5, noiseGen6};
		noiseGens = TerrainGen.getModdedNoiseGenerators(par1World, this.rand, noiseGens);
		this.noiseGen1 = (NoiseGeneratorOctaves)noiseGens[0];
		this.noiseGen2 = (NoiseGeneratorOctaves)noiseGens[1];
		this.noiseGen3 = (NoiseGeneratorOctaves)noiseGens[2];
		this.noiseGen4 = (NoiseGeneratorPerlin)noiseGens[3];
		this.noiseGen5 = (NoiseGeneratorOctaves)noiseGens[4];
		this.noiseGen6 = (NoiseGeneratorOctaves)noiseGens[5];
	}

	public void generate(int i, int j, Block[] b) {
		byte b0 = 63;
		this.biomesForGeneration = this.worldObj.getWorldChunkManager().getBiomesForGeneration(this.biomesForGeneration, i * 4 - 2, j * 4 - 2, 10, 10);
		this.generate(i * 4, 0, j * 4);
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
					double d1 = this.da[k1 + k2];
					double d2 = this.da[l1 + k2];
					double d3 = this.da[i2 + k2];
					double d4 = this.da[j2 + k2];
					double d5 = (this.da[k1 + k2 + 1] - d1) * d0;
					double d6 = (this.da[l1 + k2 + 1] - d2) * d0;
					double d7 = (this.da[i2 + k2 + 1] - d3) * d0;
					double d8 = (this.da[j2 + k2 + 1] - d4) * d0;
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
								if ((d15 += d16) > 0.0D)
									b[j3 += short1] = Blockizer.AbyssStone;
								else if (k2 * 8 + l2 < b0)
									b[j3 += short1] = Blockizer.AbyssStone;
								else
									b[j3 += short1] = null;
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
		this.stoneNoise = this.noiseGen4.func_151599_a(this.stoneNoise, (double)(i * 16), (double)(j * 16), 16, 16, d0 * 2.0D, d0 * 2.0D, 1.0D);
		for (int k = 0; k < 16; ++k) {
			for (int l = 0; l < 16; ++l) {
				BiomeGenBase biomegenbase = b[l + k * 16];
				genBiomeTerrain(this.worldObj, this.rand, ba, by, i * 16 + k, j * 16 + l, this.stoneNoise[l + k * 16], biomegenbase);
			}
		}
	}

	public final void genBiomeTerrain(World p_150560_1_, Random p_150560_2_, Block[] p_150560_3_, byte[] p_150560_4_, int p_150560_5_, int p_150560_6_, double p_150560_7_, BiomeGenBase b) {
		boolean flag = true;
		Block block = b.topBlock;
		byte b0 = (byte)(b.field_150604_aj & 255);
		Block block1 = b.fillerBlock;
		int k = -1;
		int l = (int)(p_150560_7_ / 3.0D + 3.0D + p_150560_2_.nextDouble() * 0.25D);
		int i1 = p_150560_5_ & 15;
		int j1 = p_150560_6_ & 15;
		int k1 = p_150560_3_.length / 256;
		for (int l1 = 255; l1 >= 0; --l1) {
			int i2 = (j1 * 16 + i1) * k1 + l1;
			if (l1 <= 0 + p_150560_2_.nextInt(5)) {
				p_150560_3_[i2] = Blocks.bedrock;
			}
			else {
				Block block2 = p_150560_3_[i2];
				if (block2 != null && block2.getMaterial() != Material.air) {
					if (block2 == Blockizer.AbyssStone) {
						if (k == -1) {
							if (l <= 0) {
								block = null;
								b0 = 0;
								block1 = Blockizer.AbyssStone;
							}
							else if (l1 >= 59 && l1 <= 64) {
								block = b.topBlock;
								b0 = (byte)(b.field_150604_aj & 255);
								block1 = b.fillerBlock;
							}
							if (l1 < 63 && (block == null || block.getMaterial() == Material.air)) {
								if (b.getFloatTemperature(p_150560_5_, l1, p_150560_6_) < 0.15F) {
									block = Blocks.lava;
									b0 = 0;
								}
								else {
									block = Blockizer.AbyssStone;
									b0 = 0;
								}
							}
							k = l;
							if (l1 >= 62) {
								p_150560_3_[i2] = block;
								p_150560_4_[i2] = b0;
							}
							else if (l1 < 56 - l) {
								block = null;
								block1 = Blockizer.AbyssStone;
								p_150560_3_[i2] = Blockizer.AbyssStone;
							}
							else {
								p_150560_3_[i2] = block1;
							}
						}
						else if (k > 0) {
							--k;
							p_150560_3_[i2] = block1;
							if (k == 0 && block1 == Blockizer.AbyssStone) {
								k = p_150560_2_.nextInt(4) + Math.max(0, l1 - 63);
								block1 = Blockizer.AbyssStone;
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
		return this.provideChunk(par1, par2);
	}

	@Override
	public Chunk provideChunk(int par1, int par2) {
		this.rand.setSeed((long)par1 * 341873128712L + (long)par2 * 132897987541L);
		Block[] ablock = new Block[65536];
		byte[] abyte = new byte[65536];
		this.generate(par1, par2, ablock);
		this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, par1 * 16, par2 * 16, 16, 16);
		this.replaceBlocksForBiome(par1, par2, ablock, abyte, this.biomesForGeneration);
		this.caveGenerator.func_151539_a(this, this.worldObj, par1, par2, ablock);
		Chunk chunk = new Chunk(this.worldObj, ablock, abyte, par1, par2);
		byte[] abyte1 = chunk.getBiomeArray();
		for (int k = 0; k < abyte1.length; ++k)
			abyte1[k] = (byte)this.biomesForGeneration[k].biomeID;
		chunk.generateSkylightMap();
		return chunk;
	}

	private void generate(int x, int y, int z) {
		double d0 = 684.412D;
		double d1 = 684.412D;
		double d2 = 512.0D;
		double d3 = 512.0D;
		this.gen4 = this.noiseGen6.generateNoiseOctaves(this.gen4, x, z, 5, 5, 200.0D, 200.0D, 0.5D);
		this.gen1 = this.noiseGen3.generateNoiseOctaves(this.gen1, x, y, z, 5, 33, 5, 8.555150000000001D, 4.277575000000001D, 8.555150000000001D);
		this.gen2 = this.noiseGen1.generateNoiseOctaves(this.gen2, x, y, z, 5, 33, 5, 684.412D, 684.412D, 684.412D);
		this.gen3 = this.noiseGen2.generateNoiseOctaves(this.gen3, x, y, z, 5, 33, 5, 684.412D, 684.412D, 684.412D);
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
				BiomeGenBase biomegenbase = this.biomesForGeneration[j1 + 2 + (k1 + 2) * 10];
				for (int l1 = -b0; l1 <= b0; ++l1) {
					for (int i2 = -b0; i2 <= b0; ++i2) {
						BiomeGenBase biomegenbase1 = this.biomesForGeneration[j1 + l1 + 2 + (k1 + i2 + 2) * 10];
						float f3 = biomegenbase1.rootHeight;
						float f4 = biomegenbase1.heightVariation;
						if (this.type == WorldType.AMPLIFIED && f3 > 0.0F) {
							f3 = 1.0F + f3 * 2.0F;
							f4 = 1.0F + f4 * 4.0F;
						}
						float f5 = this.parabolicField[l1 + 2 + (i2 + 2) * 5] / (f3 + 2.0F);
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
				double d12 = this.gen4[i1] / 8000.0D;
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
					double d7 = this.gen2[l] / 512.0D;
					double d8 = this.gen3[l] / 512.0D;
					double d9 = (this.gen1[l] / 10.0D + 1.0D) / 2.0D;
					double d10 = MathHelper.denormalizeClamp(d7, d8, d9) - d6;
					if (j2 > 29) {
						double d11 = (double)((float)(j2 - 29) / 3.0F);
						d10 = d10 * (1.0D - d11) + -10.0D * d11;
					}
					this.da[l] = d10;
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
	public void populate(IChunkProvider par1IChunkProvider, int par2, int par3) {
		int var4 = par2 * 16;
		int var5 = par3 * 16;
		BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(var4 + 16, var5 + 16);
		long p1 = this.rand.nextLong() / 2L * 2L + 1L;
		long j1 = this.rand.nextLong() / 2L * 2L + 1L;
		this.rand.setSeed(this.worldObj.getSeed());
		this.rand.setSeed((long)par2 * p1 + (long)par3 * j1 ^ this.worldObj.getSeed());
		int j, var12, var13, var14, x, i, y, z;

		if (biomegenbase == DimensionOrganizer.Abyss) {
			for (i = 0; i < 4; i++) {
				x = var4 + this.rand.nextInt(16);
				z = var5 + this.rand.nextInt(16);
				y = this.worldObj.getHeightValue(x, z);
				if (this.rand.nextInt(2) == 1)
					(new BloodPine()).generate(this.worldObj, this.rand, x, y, z);
				else
					(new EyeShrub()).generate(this.worldObj, this.rand, x, y, z);
			}
		}
		else if (biomegenbase == DimensionOrganizer.AbyssShadow) {
			for (i = 0; i < 3; i++) {
				x = var4 + this.rand.nextInt(16);
				z = var5 + this.rand.nextInt(16);
				y = this.worldObj.getHeightValue(x, z);
				(new Shadicles()).generate(this.worldObj, this.rand, x, y, z);

			}
		}
		else {
			for (i = 0; i < 3; i++) {
				x = var4 + this.rand.nextInt(16);
				z = var5 + this.rand.nextInt(16);
				y = this.worldObj.getHeightValue(x, z);
				(new BulbStock()).generate(this.worldObj, this.rand, x, y, z);

			}
		}

		if (biomegenbase == DimensionOrganizer.Abyss) {
			for (i = 0; i < 2; i++) {
				x = var4 + this.rand.nextInt(16);
				z = var5 + this.rand.nextInt(16);
				y = this.worldObj.getHeightValue(x + 1, z + 1);
				if (this.worldObj.getBlock(x + 1, y - 1, z + 1) == Blockizer.AbyssGrass) {
					int pick = rand.nextInt(11);
					if (pick == 1)
						(new Tentacle1()).generate(this.worldObj, this.rand, x, y - 1, z);
					else if (pick == 2)
						(new Tentacle2()).generate(this.worldObj, this.rand, x, y - 1, z);
					else if (pick == 3)
						(new Tentacle3()).generate(this.worldObj, this.rand, x, y - 1, z);
					else if (pick == 4)
						(new Tentacle4()).generate(this.worldObj, this.rand, x, y - 1, z);
					else if (pick == 5)
						(new Tentacle5()).generate(this.worldObj, this.rand, x, y - 1, z);
					else if (pick == 6)
						(new Tentacle6()).generate(this.worldObj, this.rand, x, y - 1, z);
					else if (pick == 7)
						(new Tentacle7()).generate(this.worldObj, this.rand, x, y - 1, z);
					else if (pick == 8)
						(new AbyssTree1()).generate(this.worldObj, this.rand, x - 2, y - 1, z - 2);
					else if (pick == 9)
						(new AbyssTree2()).generate(this.worldObj, this.rand, x - 2, y - 1, z - 2);
					else if (pick == 10)
						(new AbyssTree3()).generate(this.worldObj, this.rand, x - 2, y - 1, z - 2);
					else
						(new AbyssTree4()).generate(this.worldObj, this.rand, x - 2, y - 1, z - 2);
				}
			}
		}
		else if (biomegenbase == DimensionOrganizer.AbyssEye) {
			for (i = 0; i < 2; i++) {
				x = var4 + this.rand.nextInt(16);
				z = var5 + this.rand.nextInt(16);
				y = this.worldObj.getHeightValue(x + 1, z + 1);
				if (this.worldObj.getBlock(x + 1, y - 1, z + 1) == Blockizer.AbyssGrass) {
					int pick = rand.nextInt(7);
					if (pick == 1)
						(new EyeCane1()).generate(this.worldObj, this.rand, x, y - 1, z);
					else if (pick == 2)
						(new EyeCane2()).generate(this.worldObj, this.rand, x, y - 1, z);
					else if (pick == 3)
						(new EyeCane3()).generate(this.worldObj, this.rand, x, y - 1, z);
					else if (pick == 4)
						(new EyeCane4()).generate(this.worldObj, this.rand, x, y - 1, z);
					else if (pick == 5)
						(new EyeHang1()).generate(this.worldObj, this.rand, x, y - 1, z);
					else if (pick == 6)
						(new EyeHang2()).generate(this.worldObj, this.rand, x, y - 1, z);
					else
						(new EyeHang3()).generate(this.worldObj, this.rand, x, y - 1, z);
				}
			}
		}
		else {
			for (i = 0; i < 4; i++) {
				x = var4 + this.rand.nextInt(16);
				z = var5 + this.rand.nextInt(16);
				y = this.worldObj.getHeightValue(x + 2, z + 2);
				if (this.worldObj.getBlock(x + 2, y - 1, z + 2) == Blockizer.AbyssGrass) {
					int pick = rand.nextInt(5);
					if (pick == 1)
						(new ShadeTree1()).generate(this.worldObj, this.rand, x, y - 1, z);
					else if (pick == 2)
						(new ShadeTree2()).generate(this.worldObj, this.rand, x, y - 1, z);
					else if (pick == 3)
						(new ShadeTree3()).generate(this.worldObj, this.rand, x, y - 1, z);
					else if (pick == 4)
						(new ShadeTree4()).generate(this.worldObj, this.rand, x, y - 1, z);
					else
						(new ShadeTree5()).generate(this.worldObj, this.rand, x, y - 1, z);
				}
			}
			for (i = 0; i < 6; i++) {
				x = var4 + this.rand.nextInt(16);
				z = var5 + this.rand.nextInt(16);
				y = this.worldObj.getHeightValue(x, z);
				if (this.worldObj.getBlock(x, y - 1, z) == Blockizer.AbyssGrass) {
					int pick = rand.nextInt(4);
					if (pick == 1)
						(new ShadeBush1()).generate(this.worldObj, this.rand, x, y, z);
					else if (pick == 2)
						(new ShadeBush2()).generate(this.worldObj, this.rand, x, y, z);
					else if (pick == 3)
						(new ShadeBush3()).generate(this.worldObj, this.rand, x, y, z);
					else
						(new ShadeBush4()).generate(this.worldObj, this.rand, x, y, z);
				}
			}
		}
		if (biomegenbase == DimensionOrganizer.Abyss)
			if (rand.nextInt(35) == 16) {
				x = var4 + this.rand.nextInt(16);
				z = var5 + this.rand.nextInt(16);
				y = 63;

				if (rand.nextInt(2) == 1)
					(new Arch1()).generate(this.worldObj, this.rand, x, y - 1, z);
				else
					(new Arch2()).generate(this.worldObj, this.rand, x, y - 1, z);
			}

		if (rand.nextInt(650) == 127) {
			x = var4 + this.rand.nextInt(16);
			z = var5 + this.rand.nextInt(16);
			y = this.worldObj.getHeightValue(x, z) + 12;
			(new RuneShrinePlatform(12)).generate(this.worldObj, this.rand, x, y, z);
		}

		if (biomegenbase == DimensionOrganizer.AbyssShadow)
			if (rand.nextInt(300) == 127) {
				x = var4 + this.rand.nextInt(16);
				z = var5 + this.rand.nextInt(16);
				y = this.worldObj.getHeightValue(x + 2, z + 1);
				(new ShadowlordSpawn()).generate(this.worldObj, this.rand, x, y - 2, z);
			}

		if (biomegenbase != DimensionOrganizer.AbyssShadow)
			if (rand.nextInt(750) == 650) {
				x = var4 + this.rand.nextInt(16);
				z = var5 + this.rand.nextInt(16);
				y = this.worldObj.getHeightValue(x + 15, z + 15);
				(new FragmentTempleAbyss()).generate(this.worldObj, this.rand, x, y - 1, z);
			}

		if (rand.nextInt(440) == 127) {
			x = var4 + this.rand.nextInt(16);
			z = var5 + this.rand.nextInt(16);
			y = this.worldObj.getHeightValue(x + 2, z + 2);
			(new LottoAbyssStructure()).generate(this.worldObj, this.rand, x, y - 1, z);
		}

		if (biomegenbase != DimensionOrganizer.AbyssShadow)
			if (rand.nextInt(520) == 127) {
				x = var4 + this.rand.nextInt(16);
				z = var5 + this.rand.nextInt(16);
				y = this.worldObj.getHeightValue(x + 2, z + 2);
				(new JaweHut()).generate(this.worldObj, this.rand, x, y - 1, z);
			}

		if (biomegenbase != DimensionOrganizer.AbyssShadow) {
			for (i = 0; i < 20; i++) {
				x = var4 + this.rand.nextInt(16);
				z = var5 + this.rand.nextInt(16);
				y = this.worldObj.getHeightValue(x, z);
				if (this.worldObj.getBlock(x, y, z) == Blocks.air)
					this.worldObj.setBlock(x, y, z, Blockizer.Tubeicles);
			}
		}
		else {
			for (i = 0; i < 20; i++) {
				x = var4 + this.rand.nextInt(16);
				z = var5 + this.rand.nextInt(16);
				y = this.worldObj.getHeightValue(x, z);
				if (this.worldObj.getBlock(x, y, z) == Blocks.air)
					this.worldObj.setBlock(x, y, z, Blockizer.ShadowShrub);
			}
		}
		// Ore//
		for (i = 0; i < 4; i++) {
			x = var4 + this.rand.nextInt(16);
			y = rand.nextInt(5) + 45;
			z = var5 + this.rand.nextInt(16);
			new WorldGenMinable(Blockizer.oreBloodstone, 10, Blockizer.AbyssStone).generate(this.worldObj, rand, x, y, z);
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
		return "Abyss";
	}

	@Override
	public List getPossibleCreatures(EnumCreatureType par1EnumCreatureType, int par2, int par3, int par4) {
		BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(par2, par4);
		return biomegenbase.getSpawnableList(par1EnumCreatureType);
	}

	@Override
	public ChunkPosition func_147416_a(World p_147416_1_, String p_147416_2_, int p_147416_3_, int p_147416_4_, int p_147416_5_) {
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