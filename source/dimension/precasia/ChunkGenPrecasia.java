package net.tslat.aoa3.dimension.precasia;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.terraingen.InitNoiseGensEvent;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.DimensionRegister;
import net.tslat.aoa3.structure.AoAStructure;
import net.tslat.aoa3.structure.StructuresHandler;
import net.tslat.aoa3.utils.ConfigurationUtil;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class ChunkGenPrecasia implements IChunkGenerator {
	private final World world;

	private final Random rand;

	private ChunkPrimer primer;
	private int curChunkX;
	private int curChunkZ;

	private final Biome biome = DimensionRegister.biomePrecasia;

	private double[] heightMap = new double[825];
	private float[] biomeWeights = new float[25];
	private double[] depthBuffer = new double[256];

	private NoiseGeneratorOctaves minLimitPerlinNoise;
	private NoiseGeneratorOctaves maxLimitPerlinNoise;
	private NoiseGeneratorOctaves mainPerlinNoise;
	private NoiseGeneratorPerlin surfaceNoise;
	private NoiseGeneratorOctaves scaleNoise;
	private NoiseGeneratorOctaves depthNoise;

	private double[] mainNoiseRegion;
	private double[] minLimitRegion;
	private double[] maxLimitRegion;
	private double[] depthRegion;

	private static final double depthNoiseScaleX = 200;
	private static final double depthNoiseScaleZ = 200;
	private static final double depthNoiseScaleExponent = 0.5;
	private static final double coordScale = 684.412;
	private static final int mainNoiseScaleX = 80;
	private static final int mainNoiseScaleY = 160;
	private static final int mainNoiseScaleZ = 80;
	private static final double heightScale = 684.412;
	private static final int biomeDepthOffset = 0;
	private static final int biomeScaleOffset = 0;
	private static final double heightStretch = 12;
	private static final double baseSize = 8.5;
	private static final double lowerLimitScale = 512;
	private static final double upperLimitScale = 512;
	private static final float biomeDepthWeight = 1;
	private static final float biomeScaleWeight = 1;

	private CaveGenPrecasia caveGen = new CaveGenPrecasia();

	protected ChunkGenPrecasia(World world) {
		this.world = world;
		this.rand = new Random(world.getSeed());
		this.world.setSeaLevel(0);

		minLimitPerlinNoise = new NoiseGeneratorOctaves(rand, 16);
		maxLimitPerlinNoise = new NoiseGeneratorOctaves(rand, 16);
		mainPerlinNoise = new NoiseGeneratorOctaves(rand, 8);
		surfaceNoise = new NoiseGeneratorPerlin(rand, 4);
		scaleNoise = new NoiseGeneratorOctaves(rand, 10);
		depthNoise = new NoiseGeneratorOctaves(rand, 16);

		for (int i = -2; i <= 2; ++i) {
			for (int j = -2; j <= 2; ++j) {
				biomeWeights[i + 2 + (j + 2) * 5] = 10F / MathHelper.sqrt(i * i + j * j + 0.2f);
			}
		}

		InitNoiseGensEvent.ContextOverworld context = new InitNoiseGensEvent.ContextOverworld(minLimitPerlinNoise, maxLimitPerlinNoise, mainPerlinNoise, surfaceNoise, scaleNoise, depthNoise, null);
		context = TerrainGen.getModdedNoiseGenerators(world, rand, context);
		minLimitPerlinNoise = context.getLPerlin1();
		maxLimitPerlinNoise = context.getLPerlin2();
		mainPerlinNoise = context.getPerlin();
		surfaceNoise = context.getHeight();
		scaleNoise = context.getScale();
		depthNoise = context.getDepth();
	}

	@Override
	public Chunk generateChunk(int chunkX, int chunkZ) {
		this.curChunkX = chunkX;
		this.curChunkZ = chunkZ;
		this.rand.setSeed(chunkX * 341873128712L + chunkZ * 132897987541L);
		this.primer = new ChunkPrimer();

		generateHeightMap();
		setBlocksInChunk();
		replaceBiomeBlocks();

		this.caveGen.generate(this.world, chunkX, chunkZ, primer);

		Chunk chunk = new Chunk(world, primer, curChunkX, curChunkZ);
		byte[] biomeArray = chunk.getBiomeArray();

		for (int i = 0; i < biomeArray.length; ++i) {
			biomeArray[i] = (byte)Biome.getIdForBiome(biome);
		}

		chunk.generateSkylightMap();
		return chunk;
	}

	private void generateHeightMap() {
		int offsetX = curChunkX * 4;
		int offsetZ = curChunkZ * 4;
		this.depthRegion = depthNoise.generateNoiseOctaves(depthRegion, offsetX, offsetZ, 5, 5, depthNoiseScaleX, depthNoiseScaleZ, depthNoiseScaleExponent);
		this.mainNoiseRegion = mainPerlinNoise.generateNoiseOctaves(mainNoiseRegion, offsetX, 0, offsetZ, 5, 33, 5, coordScale / mainNoiseScaleX, heightScale / mainNoiseScaleY, coordScale / mainNoiseScaleZ);
		this.minLimitRegion = minLimitPerlinNoise.generateNoiseOctaves(minLimitRegion, offsetX, 0, offsetZ, 5, 33, 5, coordScale, heightScale, coordScale);
		this.maxLimitRegion = maxLimitPerlinNoise.generateNoiseOctaves(maxLimitRegion, offsetX, 0, offsetZ, 5, 33, 5, coordScale, heightScale, coordScale);

		int i = 0;
		int j = 0;

		for (int k = 0; k < 5; ++k) {
			for (int l = 0; l < 5; ++l) {
				float accumulatedHeightVariation = 0.0F;
				float accumulatedHeight = 0.0F;
				float accumulatedWeightedHeightFactor = 0.0F;

				for (int m = -2; m <= 2; ++m) {
					for (int n = -2; n <= 2; ++n) {
						float baseHeight = biomeDepthOffset + biome.getBaseHeight() * biomeDepthWeight;
						float heightVariation = biomeScaleOffset + biome.getHeightVariation() * biomeScaleWeight;

						if (this.world.getWorldType() == WorldType.AMPLIFIED && baseHeight > 0.0F) {
							baseHeight = 1 + baseHeight * 2;
							heightVariation = 1 + heightVariation * 4;
						}


						float weightedHeightFactor = biomeWeights[m + 2 + (n + 2) * 5] / (baseHeight + 2.0F);

						accumulatedHeightVariation += heightVariation * weightedHeightFactor;
						accumulatedHeight += baseHeight * weightedHeightFactor;
						accumulatedWeightedHeightFactor += weightedHeightFactor;
					}
				}

				accumulatedHeightVariation = accumulatedHeightVariation / accumulatedWeightedHeightFactor * 0.9F + 0.1F;
				accumulatedHeight = (accumulatedHeight / accumulatedWeightedHeightFactor * 4.0F - 1.0F) / 8.0F;
				double depthBy8k = depthRegion[j] / 8000.0D;

				if (depthBy8k < 0)
					depthBy8k = -depthBy8k * 0.3;

				depthBy8k = depthBy8k * 3 - 2;

				if (depthBy8k < 0) {
					depthBy8k /= 2;

					if (depthBy8k < -1)
						depthBy8k = -1;

					depthBy8k /= 2.8;
				}
				else {
					if (depthBy8k > 1)
						depthBy8k = 1;

					depthBy8k /= 8;
				}

				++j;

				double heightAvg = baseSize + ((accumulatedHeight + depthBy8k * 0.2) * baseSize / 8) * 4;

				for (int o = 0; o < 33; ++o) {
					double d1 = (o - heightAvg) * heightStretch * 128 / 256 / accumulatedHeightVariation;

					if (d1 < 0)
						d1 *= 4;

					double minLimitScaled = minLimitRegion[i] / lowerLimitScale;
					double maxLimitScaled = maxLimitRegion[i] / upperLimitScale;
					double noiseValue = (mainNoiseRegion[i] / 10 + 1) / 2;
					double linearInterpHeight = MathHelper.clampedLerp(minLimitScaled, maxLimitScaled, noiseValue) - d1;

					if (o > 29) {
						double d11 = (o - 29) / 3f;
						linearInterpHeight = linearInterpHeight * (1 - d11) + -10 * d11;
					}

					heightMap[i] = linearInterpHeight;
					++i;
				}
			}
		}
	}

	private void setBlocksInChunk() {
		for (int i = 0; i < 4; ++i) {
			int j = i * 5;
			int k = (i + 1) * 5;

			for (int l = 0; l < 4; ++l) {
				int i1 = (j + l) * 33;
				int j1 = (j + l + 1) * 33;
				int k1 = (k + l) * 33;
				int l1 = (k + l + 1) * 33;

				for (int i2 = 0; i2 < 32; ++i2) {
					double d1 = this.heightMap[i1 + i2];
					double d2 = this.heightMap[j1 + i2];
					double d3 = this.heightMap[k1 + i2];
					double d4 = this.heightMap[l1 + i2];
					double d5 = (this.heightMap[i1 + i2 + 1] - d1) * 0.125D;
					double d6 = (this.heightMap[j1 + i2 + 1] - d2) * 0.125D;
					double d7 = (this.heightMap[k1 + i2 + 1] - d3) * 0.125D;
					double d8 = (this.heightMap[l1 + i2 + 1] - d4) * 0.125D;

					for (int j2 = 0; j2 < 8; ++j2) {
						double d10 = d1;
						double d11 = d2;
						double d12 = (d3 - d1) * 0.25D;
						double d13 = (d4 - d2) * 0.25D;

						for (int k2 = 0; k2 < 4; ++k2) {
							double d16 = (d11 - d10) * 0.25D;
							double lvt_45_1_ = d10 - d16;

							for (int l2 = 0; l2 < 4; ++l2) {
								if ((lvt_45_1_ += d16) > 0.0D) {
									primer.setBlockState(i * 4 + k2, i2 * 8 + j2, l * 4 + l2, BlockRegister.stonePrecasiaLow.getDefaultState());
								}
								else if (i2 * 8 + j2 < this.world.getSeaLevel()) {
									primer.setBlockState(i * 4 + k2, i2 * 8 + j2, l * 4 + l2, BlockRegister.grassPrecasia.getDefaultState());
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

	private void replaceBiomeBlocks() {
		if (!ForgeEventFactory.onReplaceBiomeBlocks(this, curChunkX, curChunkZ, primer, world))
			return;

		depthBuffer = surfaceNoise.getRegion(depthBuffer, curChunkX * 16, curChunkZ * 16, 16, 16, 0.0625, 0.0625, 1);

		for (int x = 0; x < 16; ++x) {
			for (int z = 0; z < 16; ++z) {
				generateBiomeTerrain(world, rand, primer, curChunkX * 16 + x, curChunkZ * 16 + z, depthBuffer[z + x * 16]);
			}
		}
	}

	private void generateBiomeTerrain(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
		int seaLevel = worldIn.getSeaLevel();
		IBlockState topBlock = biome.topBlock;
		IBlockState fillerBlock = biome.fillerBlock;
		BlockPos.MutableBlockPos tempCheckPos = new BlockPos.MutableBlockPos();
		int j = -1;
		int k = (int)(noiseVal / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
		int l = x & 15;
		int i1 = z & 15;

		for (int j1 = 255; j1 >= 0; --j1) {
			if (j1 <= 2) {
				chunkPrimerIn.setBlockState(i1, j1, l, BlockRegister.dimensionalFabric.getDefaultState());
			}
			else {
				IBlockState iblockstate2 = chunkPrimerIn.getBlockState(i1, j1, l);

				if (iblockstate2.getMaterial() == Material.AIR) {
					j = -1;
				}
				else if (iblockstate2.getBlock() == BlockRegister.stonePrecasiaLow) {
					if (j == -1) {
						if (k <= 0) {
							topBlock = Blocks.AIR.getDefaultState();
							fillerBlock = biome.fillerBlock;
						}
						else if (j1 >= seaLevel - 4 && j1 <= seaLevel + 1) {
							topBlock = biome.topBlock;
							fillerBlock = biome.fillerBlock;
						}

						if (j1 < seaLevel && (topBlock == null || topBlock.getMaterial() == Material.AIR)) {
							if (biome.getTemperature(tempCheckPos.setPos(x, j1, z)) < 0.15F) {
								topBlock = Blocks.LAVA.getDefaultState();
							}
							else {
								topBlock = BlockRegister.stonePrecasiaLow.getDefaultState();
							}
						}

						j = k;

						if (j1 >= seaLevel - 1) {
							chunkPrimerIn.setBlockState(i1, j1, l, biome.topBlock);
						}
						else if (j1 < seaLevel - 7 - k) {
							topBlock = Blocks.AIR.getDefaultState();
							fillerBlock = biome.fillerBlock;
							chunkPrimerIn.setBlockState(i1, j1, l, biome.fillerBlock);
						}
						else {
							chunkPrimerIn.setBlockState(i1, j1, l, fillerBlock);
						}
					}
					else if (j > 0) {
						--j;
						chunkPrimerIn.setBlockState(i1, j1, l, fillerBlock);
					}
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

		if (ConfigurationUtil.spawnChanceKaiyuTemple > 0 && rand.nextInt(ConfigurationUtil.spawnChanceKaiyuTemple) == 0) {
			x = baseX;
			z = baseZ;
			y = world.getHeight(x + 22, z + 22);

			StructuresHandler.generateStructure("KaiyuTemple", world, rand, pos.setPos(x, y - 30, z));
		}
		else if (ConfigurationUtil.spawnChanceSkeletalArmyArena > 0 && rand.nextInt(ConfigurationUtil.spawnChanceSkeletalArmyArena) == 0) {
			x = baseX;
			z = baseZ + rand.nextInt(7);
			y = world.getHeight(x + 22, z + 13);

			Block block1 = world.getBlockState(pos.setPos(x + 1, y - 1, z + 8)).getBlock();
			Block block2 = world.getBlockState(pos.setPos(x + 28, y - 1, z + 11)).getBlock();

			if ((block1 == biome.topBlock.getBlock() || block1 == BlockRegister.stonePrecasiaHigh) && (block2 == biome.topBlock.getBlock() || block2 == BlockRegister.stonePrecasiaHigh))
				StructuresHandler.generateStructure("SkeletalArmyArena", world, rand, pos.setPos(x, y, z));
		}
		else if (ConfigurationUtil.spawnChanceTyrosaurStompingGround > 0 && rand.nextInt(ConfigurationUtil.spawnChanceTyrosaurStompingGround) == 0) {
			x = baseX + rand.nextInt(15);
			z = baseZ + rand.nextInt(15);
			y = world.getHeight(x + 7, z + 7);

			if (world.getBlockState(pos.setPos(x + 7, y - 1, z + 7)) == biome.topBlock)
				StructuresHandler.generateStructure("TyrosaurStompingGround", world, rand, pos.setPos(x, y, z));
		}
		else if (ConfigurationUtil.spawnChanceJungleLottoHut > 0 && rand.nextInt(ConfigurationUtil.spawnChanceJungleLottoHut) == 0) {
			x = baseX + rand.nextInt(16);
			z = baseZ + rand.nextInt(16);
			y = world.getHeight(x + 4, z + 4);

			if (world.getBlockState(pos.setPos(x + 4, y - 1, z + 4)) == biome.topBlock)
				StructuresHandler.generateStructure("JungleLottoHut", world, rand, pos.setPos(x, y, z));
		}
		else if (ConfigurationUtil.spawnChanceOpteryxNest > 0 && rand.nextInt(ConfigurationUtil.spawnChanceOpteryxNest) == 0) {
			x = baseX + rand.nextInt(16);
			z = baseZ + rand.nextInt(16);
			y = world.getHeight(x + 4, z + 4);

			if (world.getBlockState(pos.setPos(x + 4, y - 1, z + 4)).getBlock() instanceof BlockLeaves)
				StructuresHandler.generateStructure("OpteryxNest", world, rand, pos.setPos(x, y, z));
		}

		if (ConfigurationUtil.spawnChancePrecasianDen > 0 && rand.nextInt(ConfigurationUtil.spawnChancePrecasianDen) == 0) {
			x = baseX + rand.nextInt(2);
			z = baseZ;
			y = rand.nextInt(5) + 2;

			AoAStructure structure = StructuresHandler.EMPTY_STRUCTURE;

			switch (rand.nextInt(3)) {
				case 0:
					structure = StructuresHandler.getStructure("DiocusDen");
					break;
				case 1:
					structure = StructuresHandler.getStructure("IosaurDen");
					break;
				case 2:
					structure = StructuresHandler.getStructure("SpinoledonDen");
					break;
			}

			StructuresHandler.generateStructure(structure, world, rand, pos.setPos(x, y, z));
		}

		if (ConfigurationUtil.spawnChanceLifeRuneShrine > 0 && rand.nextInt(ConfigurationUtil.spawnChanceLifeRuneShrine) == 0) {
			x = baseX + rand.nextInt(16);
			z = baseZ + rand.nextInt(16);
			y = world.getHeight(x, z) + 20;

			StructuresHandler.generateStructure("LifeRuneShrine", world, rand, pos.setPos(x, y, z));
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
