package net.tslat.aoa3.dimension.haven;

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
import net.minecraftforge.event.ForgeEventFactory;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.DimensionRegister;
import net.tslat.aoa3.structure.StructuresHandler;
import net.tslat.aoa3.utils.ConfigurationUtil;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class ChunkGenHaven implements IChunkGenerator {
	private final World world;

	private final Random rand;

	private ChunkPrimer primer;
	private int curChunkX;
	private int curChunkZ;

	private final Biome biome = DimensionRegister.biomeHaven;

	private double[] noiseArray;
	private double[] surfaceBuffer = new double[256];

	private NoiseGeneratorOctaves minLimitPerlinNoise;
	private NoiseGeneratorOctaves maxLimitPerlinNoise;
	private NoiseGeneratorOctaves mainPerlinNoise;
	private NoiseGeneratorPerlin surfaceNoise;

	private double[] mainNoiseRegion;
	private double[] minNoiseRegion;
	private double[] maxNoiseRegion;

	protected ChunkGenHaven(World world) {
		this.world = world;
		this.rand = new Random(world.getSeed());
		this.world.setSeaLevel(0);

		minLimitPerlinNoise = new NoiseGeneratorOctaves(rand, 16);
		maxLimitPerlinNoise = new NoiseGeneratorOctaves(rand, 16);
		mainPerlinNoise = new NoiseGeneratorOctaves(rand, 8);
		surfaceNoise = new NoiseGeneratorPerlin(rand, 4);
	}

	@Override
	public Chunk generateChunk(int chunkX, int chunkZ) {
		this.curChunkX = chunkX;
		this.curChunkZ = chunkZ;
		this.rand.setSeed(chunkX * 341873128712L + chunkZ * 132897987541L);
		this.primer = new ChunkPrimer();

		setBlocksInChunk();
		replaceBiomeBlocks();

		Chunk chunk = new Chunk(world, primer, curChunkX, curChunkZ);
		byte[] biomeArray = chunk.getBiomeArray();

		for (int i = 0; i < biomeArray.length; ++i) {
			biomeArray[i] = (byte)Biome.getIdForBiome(biome);
		}

		chunk.generateSkylightMap();
		return chunk;
	}

	private double[] generateNoiseField(double[] blockNoiseArray, int chunkXRef, int chunkYRef, int chunkZRef, int p_185963_5_, int p_185963_6_, int p_185963_7_) {
		if (blockNoiseArray == null)
			blockNoiseArray = new double[p_185963_5_ * p_185963_6_ * p_185963_7_];

		double d0 = 684.412D;
		d0 = d0 * 2.0D;
		this.mainNoiseRegion = this.mainPerlinNoise.generateNoiseOctaves(this.mainNoiseRegion, chunkXRef, chunkYRef, chunkZRef, p_185963_5_, p_185963_6_, p_185963_7_, d0 / 80.0D, 4.277575000000001D, d0 / 80.0D);
		this.minNoiseRegion = this.minLimitPerlinNoise.generateNoiseOctaves(this.minNoiseRegion, chunkXRef, chunkYRef, chunkZRef, p_185963_5_, p_185963_6_, p_185963_7_, d0, 684.412D, d0);
		this.maxNoiseRegion = this.maxLimitPerlinNoise.generateNoiseOctaves(this.maxNoiseRegion, chunkXRef, chunkYRef, chunkZRef, p_185963_5_, p_185963_6_, p_185963_7_, d0, 684.412D, d0);
		int k = 0;

		for (int posX = 0; posX < p_185963_5_; ++posX) {
			for (int posZ = 0; posZ < p_185963_7_; ++posZ) {
				for (int j1 = 0; j1 < p_185963_6_; ++j1) {
					double d2 = this.minNoiseRegion[k] / 512.0D;
					double d3 = this.maxNoiseRegion[k] / 512.0D;
					double d5 = (this.mainNoiseRegion[k] / 10.0D + 1.0D) / 2.0D;
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

	private void setBlocksInChunk() {
		this.noiseArray = this.generateNoiseField(this.noiseArray, curChunkX * 2, 0, curChunkZ * 2, 3, 33, 3);

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
						double d12 = (d3 - d1) * 0.125D;
						double d13 = (d4 - d2) * 0.125D;

						for (int i2 = 0; i2 < 8; ++i2) {
							double d15 = d10;
							double d16 = (d11 - d10) * 0.125D;

							for (int j2 = 0; j2 < 8; ++j2) {
								IBlockState iblockstate = Blocks.AIR.getDefaultState();

								if (d15 > 0.0D)
									iblockstate = BlockRegister.stoneHaven.getDefaultState();

								int k2 = i2 + i1 * 8;
								int l2 = l1 + k1 * 4;
								int i3 = j2 + j1 * 8;

								primer.setBlockState(k2, l2, i3, iblockstate);

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

	private void replaceBiomeBlocks() {
		if (!ForgeEventFactory.onReplaceBiomeBlocks(this, curChunkX, curChunkZ, primer, world))
			return;

		surfaceBuffer = surfaceNoise.getRegion(surfaceBuffer, curChunkX * 16, curChunkZ * 16, 16, 16, 0.0625, 0.0625, 1);

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
			else if (iblockstate2.getBlock() == BlockRegister.stoneHaven) {
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

		if (ConfigurationUtil.StructureConfig.haven.dawnlightDungeonSpawnChance > 0 && rand.nextInt(ConfigurationUtil.StructureConfig.haven.dawnlightDungeonSpawnChance) == 0) {
			x = baseX + rand.nextInt(10);
			z = baseZ + rand.nextInt(16);
			y = rand.nextInt(25) + 10;

			if (world.getBlockState(pos.setPos(x + 10, y + 7, z + 5)).getBlock() == Blocks.AIR)
				StructuresHandler.generateStructure("DawnlightDungeon", world, rand, pos.setPos(x, y, z));
		}
		else if (ConfigurationUtil.StructureConfig.haven.floatingLottoFountainSpawnChance > 0 && rand.nextInt(ConfigurationUtil.StructureConfig.haven.floatingLottoFountainSpawnChance) == 0) {
			x = baseX + rand.nextInt(16);
			z = baseZ + rand.nextInt(16);
			y = rand.nextInt(40) + 10;

			if (world.getBlockState(pos.setPos(x + 3, y + 8, z + 3)).getBlock() == Blocks.AIR)
				StructuresHandler.generateStructure("FloatingLottoFountain", world, rand, pos.setPos(x, y, z));
		}
		else if (ConfigurationUtil.StructureConfig.haven.guardianTowerSpawnChance > 0 && rand.nextInt(ConfigurationUtil.StructureConfig.haven.guardianTowerSpawnChance) == 0) {
			x = baseX;
			z = baseZ;
			y = world.getHeight(x, z);

			if (world.getBlockState(pos.setPos(x + 14, y - 1, x + 14)) == biome.topBlock)
				StructuresHandler.generateStructure("GuardianTower", world, rand, pos.setPos(x, y - 1, z));
		}
		else if (ConfigurationUtil.StructureConfig.haven.rockriderBoulderSpawnChance > 0 && rand.nextInt(ConfigurationUtil.StructureConfig.haven.rockriderBoulderSpawnChance) == 0) {
			x = baseX + rand.nextInt(16);
			z = baseZ + rand.nextInt(16);
			y = rand.nextInt(45) + 30;

			if (world.getBlockState(pos.setPos(x + 5, y - 1, z + 5)) == biome.topBlock)
				StructuresHandler.generateStructure("RockriderBoulder", world, rand, pos.setPos(x, y - 1, z));
		}

		if (ConfigurationUtil.StructureConfig.haven.strikeRuneShrineSpawnChance > 0 && rand.nextInt(ConfigurationUtil.StructureConfig.haven.strikeRuneShrineSpawnChance) == 0) {
			x = baseX + rand.nextInt(16);
			z = baseZ + rand.nextInt(16);
			y = world.getHeight(x + 3, z + 3) + 20;

			if (world.getBlockState(pos.setPos(x + 3, y, z + 3)).getBlock() == Blocks.AIR)
				StructuresHandler.generateStructure("StrikeRuneShrine", world, rand, pos.setPos(x, y, z));
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
