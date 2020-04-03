package net.tslat.aoa3.dimension.celeve;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.NoiseGeneratorSimplex;
import net.minecraftforge.event.ForgeEventFactory;
import net.tslat.aoa3.common.registration.BiomeRegister;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.dimension.FloatingDimChunk;
import net.tslat.aoa3.structure.StructuresHandler;
import net.tslat.aoa3.utils.ConfigurationUtil;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ChunkGenCeleve implements IChunkGenerator {
	private final World world;

	private final Random rand;

	private ChunkPrimer primer;
	private int curChunkX;
	private int curChunkZ;

	private final Biome biome = BiomeRegister.biomeCeleve;

	private double[] noiseArray;
	private double[] surfaceBuffer = new double[256];

	private NoiseGeneratorOctaves noiseGen1; //minLimitPerlinNoise
	private NoiseGeneratorOctaves noiseGen2; //maxLimitPerlinNoise
	private NoiseGeneratorOctaves noiseGen3; //mainPerlinNoise
	private NoiseGeneratorPerlin noiseGen4; //surfaceNoise
	private NoiseGeneratorOctaves noiseGen5; //scaleNoise
	private NoiseGeneratorOctaves noiseGen6; //depthNoise
	private NoiseGeneratorSimplex islandNoise;

	double[] noise1; //pnr
	double[] noise2; //ar
	double[] noise3; //br
	double[] noise5;
	double[] noise6;

	protected ChunkGenCeleve(World world) {
		this.world = world;
		this.rand = new Random(world.getSeed());
		this.world.setSeaLevel(0);

		noiseGen1 = new NoiseGeneratorOctaves(rand, 16);
		noiseGen2 = new NoiseGeneratorOctaves(rand, 16);
		noiseGen3 = new NoiseGeneratorOctaves(rand, 8);
		noiseGen4 = new NoiseGeneratorPerlin(rand, 4);
		noiseGen5 = new NoiseGeneratorOctaves(rand, 10);
		noiseGen6 = new NoiseGeneratorOctaves(rand, 16);
		islandNoise = new NoiseGeneratorSimplex(rand);
	}



	@Override
	public Chunk generateChunk(int chunkX, int chunkZ) {
		this.curChunkX = chunkX;
		this.curChunkZ = chunkZ;
		this.rand.setSeed(chunkX * 341873128712L + chunkZ * 132897987541L);
		this.primer = new ChunkPrimer();
		boolean populatedChunk = setBlocksInChunk();
		Chunk chunk;

		if (populatedChunk) {
			replaceBiomeBlocks();

			chunk = new FloatingDimChunk(world, primer, curChunkX, curChunkZ);
		}
		else {
			chunk = new FloatingDimChunk(world, curChunkX, curChunkZ);
		}

		Arrays.fill(chunk.getBiomeArray(), (byte)Biome.getIdForBiome(biome));
		chunk.generateSkylightMap();

		return chunk;
	}

	private double[] generateNoiseField(double[] blockNoiseArray, int chunkXRef, int chunkYRef, int chunkZRef, int p_185963_5_, int p_185963_6_, int p_185963_7_) {
		if (blockNoiseArray == null) {
			blockNoiseArray = new double[p_185963_5_ * p_185963_6_ * p_185963_7_];
		}

		double d0 = 684.412D;
		double d1 = 684.412D;
		d0 = d0 * 2.0D;
		this.noise1 = this.noiseGen3.generateNoiseOctaves(this.noise1, chunkXRef, chunkYRef, chunkZRef, p_185963_5_, p_185963_6_, p_185963_7_, d0 / 80.0D, 4.277575000000001D, d0 / 80.0D);
		this.noise2 = this.noiseGen1.generateNoiseOctaves(this.noise2, chunkXRef, chunkYRef, chunkZRef, p_185963_5_, p_185963_6_, p_185963_7_, d0, 684.412D, d0);
		this.noise3 = this.noiseGen2.generateNoiseOctaves(this.noise3, chunkXRef, chunkYRef, chunkZRef, p_185963_5_, p_185963_6_, p_185963_7_, d0, 684.412D, d0);
		int chunkX = chunkXRef / 2;
		int chunkZ = chunkZRef / 2;
		int k = 0;

		for (int posX = 0; posX < p_185963_5_; ++posX) {
			for (int posZ = 0; posZ < p_185963_7_; ++posZ) {

				for (int j1 = 0; j1 < p_185963_6_; ++j1) {
					double d2 = this.noise2[k] / 512.0D;
					double d3 = this.noise3[k] / 512.0D;
					double d5 = (this.noise1[k] / 10.0D + 1.0D) / 2.0D;
					double d4;

					if (d5 < 0.0D) {
						d4 = d2;
					}
					else if (d5 > 1.0D) {
						d4 = d3;
					}
					else {
						d4 = d2 + (d3 - d2) * d5;
					}

					d4 = d4 - 8.0D;
					int k1 = 32;

					if (j1 > p_185963_6_ - k1) {
						double d6 = (double)((float)(j1 - (p_185963_6_ - k1)) / 31);
						d4 = d4 * (1.0D - d6) + -15.0D * d6;
					}

					k1 = 8;

					if (j1 < k1) {
						double d7 = (double)((float)(k1 - j1) / ((float)k1 - 1.0F));
						d4 = d4 * (1.0D - d7) + -30.0D * d7;
					}

					blockNoiseArray[k] = d4;
					++k;
				}
			}
		}

		return blockNoiseArray;
	}

	private boolean setBlocksInChunk() {
		this.noiseArray = this.generateNoiseField(this.noiseArray, curChunkX * 2, 0, curChunkZ * 2, 3, 33, 3);
		boolean emptyChunk = true;

		for (int i1 = 0; i1 < 2; ++i1) {
			for (int j1 = 0; j1 < 2; ++j1) {
				for (int k1 = 0; k1 < 32; ++k1) {
					double d1 = this.noiseArray[(i1 * 3 + j1) * 33 + k1];
					double d2 = this.noiseArray[(i1 * 3 + j1 + 1) * 33 + k1];
					double d3 = this.noiseArray[((i1 + 1) * 3 + j1) * 33 + k1];
					double d4 = this.noiseArray[((i1 + 1) * 3 + j1 + 1) * 33 + k1];
					double d5 = (this.noiseArray[(i1 * 3 + j1) * 33 + k1 + 1] - d1) * 0.25D;
					double d6 = (this.noiseArray[(i1 * 3 + j1 + 1) * 33 + k1 + 1] - d2) * 0.25D;
					double d7 = (this.noiseArray[((i1 + 1) * 3 + j1) * 33 + k1 + 1] - d3) * 0.25D;
					double d8 = (this.noiseArray[((i1 + 1) * 3 + j1 + 1) * 33 + k1 + 1] - d4) * 0.25D;

					for (int l1 = 0; l1 < 4; ++l1) {
						double d10 = d1;
						double d11 = d2;
						double d12 = (d3 - d1) * 0.0025D;
						double d13 = (d4 - d2) * 0.0025D;

						for (int i2 = 0; i2 < 8; ++i2) {
							double d14 = 0.125D;
							double d15 = d10;
							double d16 = (d11 - d10) * 0.125D;

							for (int j2 = 0; j2 < 8; ++j2) {
								IBlockState blockState = Blocks.AIR.getDefaultState();

								if (d15 > 0.0D) {
									blockState = BlockRegister.dirtCeleve.getDefaultState();
									emptyChunk = false;
								}

								int k2 = i2 + i1 * 8;
								int l2 = l1 + k1 * 4;
								int i3 = j2 + j1 * 8;
								primer.setBlockState(k2, l2, i3, blockState);
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

		return !emptyChunk;
	}

	private void replaceBiomeBlocks() {
		if (!ForgeEventFactory.onReplaceBiomeBlocks(this, curChunkX, curChunkZ, primer, world))
			return;

		surfaceBuffer = noiseGen4.getRegion(surfaceBuffer, curChunkX * 16, curChunkZ * 16, 16, 16, 0.0625, 0.0625, 1);

		for (int x = 0; x < 16; ++x) {
			for (int z = 0; z < 16; ++z) {
				generateBiomeTerrain(world, rand, primer, curChunkX * 16 + x, curChunkZ * 16 + z, surfaceBuffer[z + x * 16]);
			}
		}
	}

	private void generateBiomeTerrain(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
		IBlockState topBlock = biome.topBlock;
		IBlockState fillerBlock = biome.fillerBlock;
		int j = -1;
		int k = (int)(noiseVal / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
		int l = x & 15;
		int i1 = z & 15;

		for (int j1 = 255; j1 >= 0; --j1) {
			IBlockState iblockstate2 = chunkPrimerIn.getBlockState(i1, j1, l);

			if (iblockstate2.getMaterial() == Material.AIR) {
				j = -1;
			}
			else if (iblockstate2.getBlock() == BlockRegister.dirtCeleve) {
				if (j == -1) {
					j = k;

					primer.setBlockState(i1, j1, l, topBlock);
				}
				else if (j > 0) {
					--j;
					chunkPrimerIn.setBlockState(i1, j1, l, fillerBlock);
				}
			}
		}
	}

	@Override
	public void populate(int chunkX, int chunkZ) {
		this.rand.setSeed(world.getSeed());
		long a = this.rand.nextLong() / 2L * 2L + 1L;
		long b = this.rand.nextLong() / 2L * 2L + 1L;
		final int baseX = chunkX * 16 + 1;
		final int baseZ = chunkZ * 16 + 1;
		int x;
		int z;
		int y;
		BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
		BlockPos basePos = new BlockPos(baseX, 0, baseZ);
		Biome biome = world.getBiome(basePos);

		this.rand.setSeed(chunkX * a + chunkZ * b ^ this.world.getSeed());

		if (ConfigurationUtil.StructureConfig.celeve.gyroPlatformSpawnChance > 0 && rand.nextInt(ConfigurationUtil.StructureConfig.celeve.gyroPlatformSpawnChance) == 0) {
			x = baseX + rand.nextInt(11);
			z = baseZ + rand.nextInt(16);
			y = 40 + rand.nextInt(20);

			if (world.getBlockState(pos.setPos(x, y, z)).getBlock() == Blocks.AIR && world.getBlockState(pos.setPos(x + 17, y + 7, z + 17)).getBlock() == Blocks.AIR)
				StructuresHandler.generateStructure("GyroPlatform", world, rand, pos.setPos(x, y, z));
		}
		else if (ConfigurationUtil.StructureConfig.celeve.celevianLottoBalloonSpawnChance > 0 && rand.nextInt(ConfigurationUtil.StructureConfig.celeve.celevianLottoBalloonSpawnChance) == 0) {
			x = baseX + rand.nextInt(16);
			z = baseZ + rand.nextInt(16);
			y = 10 + rand.nextInt(20);

			if (world.getBlockState(pos.setPos(x + 2, y, z + 2)).getBlock() == Blocks.AIR && world.getBlockState(pos.setPos(x + 8, y + 17, z + 8)).getBlock() == Blocks.AIR)
				StructuresHandler.generateStructure("CelevianLottoBalloon", world, rand, pos.setPos(x, y, z));
		}
		else if (ConfigurationUtil.StructureConfig.celeve.toyTowerSpawnChance > 0 && rand.nextInt(ConfigurationUtil.StructureConfig.celeve.toyTowerSpawnChance) == 0) {
			x = baseX + rand.nextInt(16);
			z = baseZ + rand.nextInt(16);
			y = world.getHeight(x + 5, z + 5);

			if (world.getBlockState(pos.setPos(x + 5, y - 1, z + 5)) == biome.topBlock)
				StructuresHandler.generateStructure("ToyTower", world, rand, pos.setPos(x, y, z));
		}

		if (ConfigurationUtil.StructureConfig.celeve.compassRuneShrineSpawnChance > 0 && rand.nextInt(ConfigurationUtil.StructureConfig.celeve.compassRuneShrineSpawnChance) == 0) {
			x = baseX + rand.nextInt(16);
			z = baseZ + rand.nextInt(16);
			y = world.getHeight(x + 3, z + 3) + 15;

			StructuresHandler.generateStructure("CompassRuneShrine", world, rand, pos.setPos(x, y, z ));
		}

		this.rand.setSeed(chunkX * a + chunkZ * b ^ this.world.getSeed());
		biome.decorate(world, rand, basePos);
	}

	@Override
	public boolean generateStructures(Chunk chunkIn, int x, int z) {
		return false;
	}

	@Override
	public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
		return this.world.getBiome(pos).getSpawnableList(creatureType);
	}

	@Nullable
	@Override
	public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored) {
		return null;
	}

	@Override
	public void recreateStructures(Chunk chunkIn, int x, int z) {}

	@Override
	public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
		return false;
	}
}
