/*
package net.tslat.aoa3.content.world.gen.chunkgenerator;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import it.unimi.dsi.fastutil.objects.ObjectListIterator;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.entity.MobCategory;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.Util;
import net.minecraft.util.math.*;
import net.minecraft.world.Blockreader;
import net.minecraft.world.BlockGetter;
import net.minecraft.world.LevelAccessor;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnSettings;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.biome.provider.EndBiomeProvider;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.ChunkSection;
import net.minecraft.world.chunk.ChunkAccess;
import net.minecraft.world.gen.*;
import net.minecraft.world.gen.feature.jigsaw.JigsawJunction;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.structure.AbstractVillagePiece;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.settings.NoiseSettings;
import net.minecraft.world.spawner.WorldEntitySpawner;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class CavernsChunkGenerator extends ChunkGenerator {
	public static final Codec<CavernsChunkGenerator> CODEC = RecordCodecBuilder.create((record) -> record.group(
					BiomeProvider.CODEC.fieldOf("biome_source").forGetter((generator) -> generator.biomeSource),
					Codec.LONG.fieldOf("seed").stable().forGetter((generator) -> generator.seed),
					DimensionSettings.CODEC.fieldOf("settings").forGetter((generator) -> generator.dimensionSettings))
			.apply(record, record.stable(CavernsChunkGenerator::new)));
	private static final float[] BEARD_KERNEL = Util.make(new float[13824], (p_236094_0_) -> {
		for(int z = 0; z < 24; ++z) {
			for(int x = 0; x < 24; ++x) {
				for(int y = 0; y < 24; ++y) {
					p_236094_0_[z * 24 * 24 + x * 24 + y] = (float)computeContribution(x - 12, y - 12, z - 12);
				}
			}
		}
	});
	private static final float[] biomeWeightModifiers = Util.make(new float[25], (weights) -> {
		for(int x = -2; x <= 2; ++x) {
			for(int z = -2; z <= 2; ++z) {
				float weight = 10.0F / Math.sqrt((x * x + z * z) + 0.2F);
				weights[x + 2 + (z + 2) * 5] = weight;
			}
		}
	});
	private static final BlockState AIR = Blocks.AIR.defaultBlockState();
	public final int verticalNoiseGranularity;
	public final int horizontalNoiseGranularity;
	public final int noiseSizeX;
	public final int noiseSizeY;
	public final int noiseSizeZ;
	protected final SharedSeedRandom randomSeed;
	private final OctavesNoiseGenerator lowerNoiseGen;
	private final OctavesNoiseGenerator upperNoiseGen;
	private final OctavesNoiseGenerator lerpNoiseGen;
	private final INoiseGenerator surfaceNoise;
	private final OctavesNoiseGenerator depthNoise;
	@Nullable
	private final SimplexNoiseGenerator islandNoiseGenerator;
	protected final BlockState defaultBlock;
	protected final BlockState defaultFluid;
	private final long seed;
	protected final Supplier<DimensionSettings> dimensionSettings;
	private final int logicalWorldHeight;

	public CavernsChunkGenerator(BiomeProvider biomeProvider, long seed, Supplier<DimensionSettings> dimensionSettingsSupplier) {
		this(biomeProvider, biomeProvider, seed, dimensionSettingsSupplier);
	}

	private CavernsChunkGenerator(BiomeProvider biomeProvider, BiomeProvider biomeProvider2, long seed, Supplier<DimensionSettings> dimSettingsSupplier) {
		super(biomeProvider, biomeProvider2, dimSettingsSupplier.get().structureSettings(), seed);
		this.seed = seed;
		DimensionSettings dimensionSettings = dimSettingsSupplier.get();
		this.dimensionSettings = dimSettingsSupplier;
		NoiseSettings noiseSettings = dimensionSettings.noiseSettings();
		this.logicalWorldHeight = noiseSettings.height();
		this.verticalNoiseGranularity = noiseSettings.noiseSizeVertical() * 4;
		this.horizontalNoiseGranularity = noiseSettings.noiseSizeHorizontal() * 4;
		this.defaultBlock = dimensionSettings.getDefaultBlock();
		this.defaultFluid = dimensionSettings.getDefaultFluid();
		this.noiseSizeX = 16 / this.horizontalNoiseGranularity;
		this.noiseSizeY = noiseSettings.height() / this.verticalNoiseGranularity;
		this.noiseSizeZ = 16 / this.horizontalNoiseGranularity;
		this.randomSeed = new SharedSeedRandom(seed);
		this.lowerNoiseGen = new OctavesNoiseGenerator(this.randomSeed, IntStream.rangeClosed(-15, 0));
		this.upperNoiseGen = new OctavesNoiseGenerator(this.randomSeed, IntStream.rangeClosed(-15, 0));
		this.lerpNoiseGen = new OctavesNoiseGenerator(this.randomSeed, IntStream.rangeClosed(-7, 0));
		this.surfaceNoise = noiseSettings.useSimplexSurfaceNoise() ? new PerlinNoiseGenerator(this.randomSeed, IntStream.rangeClosed(-3, 0)) : new OctavesNoiseGenerator(this.randomSeed, IntStream.rangeClosed(-3, 0));
		this.randomSeed.consumeCount(2620);
		this.depthNoise = new OctavesNoiseGenerator(this.randomSeed, IntStream.rangeClosed(-15, 0));

		if (noiseSettings.islandNoiseOverride()) {
			SharedSeedRandom sharedseedrandom = new SharedSeedRandom(seed);
			sharedseedrandom.consumeCount(17292);
			this.islandNoiseGenerator = new SimplexNoiseGenerator(sharedseedrandom);
		}
		else {
			this.islandNoiseGenerator = null;
		}
	}

	@Override
	protected Codec<? extends ChunkGenerator> codec() {
		return CODEC;
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public ChunkGenerator withSeed(long seed) {
		return new CavernsChunkGenerator(biomeSource.withSeed(seed), seed, dimensionSettings);
	}

	private double getLerpedNoiseAt(int posX, int posY, int posZ, double horizontalNoiseScale, double verticalNoiseScale, double horizontalNoiseScaleModifier, double verticalNoiseScaleModifier) {
		double lowerNoise = 0;
		double upperNoise = 0;
		double lerpNoise = 0;
		double noiseStageMultiplier = 1;

		for(int i = 0; i < 16; ++i) {
			double x = OctavesNoiseGenerator.wrap((double)posX * horizontalNoiseScale * noiseStageMultiplier);
			double y = OctavesNoiseGenerator.wrap((double)posY * verticalNoiseScale * noiseStageMultiplier);
			double z = OctavesNoiseGenerator.wrap((double)posZ * horizontalNoiseScale * noiseStageMultiplier);
			double verticalNoiseModifier = verticalNoiseScale * noiseStageMultiplier;
			ImprovedNoiseGenerator lowerNoiseGenerator = this.lowerNoiseGen.getOctaveNoise(i);

			if (lowerNoiseGenerator != null)
				lowerNoise += lowerNoiseGenerator.noise(x, y, z, verticalNoiseModifier, (double)posY * verticalNoiseModifier) / noiseStageMultiplier;

			ImprovedNoiseGenerator upperNoiseGenerator = upperNoiseGen.getOctaveNoise(i);

			if (upperNoiseGenerator != null)
				upperNoise += upperNoiseGenerator.noise(x, y, z, verticalNoiseModifier, (double)posY * verticalNoiseModifier) / noiseStageMultiplier;

			if (i < 8) {
				ImprovedNoiseGenerator lerpNoiseGenerator = lerpNoiseGen.getOctaveNoise(i);

				if (lerpNoiseGenerator != null)
					lerpNoise += lerpNoiseGenerator.noise(OctavesNoiseGenerator.wrap((double)posX * horizontalNoiseScaleModifier * noiseStageMultiplier), OctavesNoiseGenerator.wrap((double)posY * verticalNoiseScaleModifier * noiseStageMultiplier), OctavesNoiseGenerator.wrap((double)posZ * horizontalNoiseScaleModifier * noiseStageMultiplier), verticalNoiseScaleModifier * noiseStageMultiplier, (double)posY * verticalNoiseScaleModifier * noiseStageMultiplier) / noiseStageMultiplier;
			}

			noiseStageMultiplier /= 2.0D;
		}

		return Mth.clampedLerp(lowerNoise / 512.0D, upperNoise / 512.0D, (lerpNoise / 10.0D + 1.0D) / 2.0D);
	}

	private double[] getNoiseColumn(int noiseX, int noiseZ, boolean isCeiling) {
		double[] noiseColumn = new double[noiseSizeY + 1];

		fillNoiseColumn(noiseColumn, noiseX, noiseZ, isCeiling);

		return noiseColumn;
	}

	private void fillNoiseColumn(double[] noiseColumn, int noiseX, int noiseZ, boolean isCeiling) {
		NoiseSettings noiseSettings = dimensionSettings.get().noiseSettings();
		double biomeDensityOffset;
		double biomeDensityFactor;

		if (islandNoiseGenerator != null) {
			biomeDensityOffset = EndBiomeProvider.getHeightValue(islandNoiseGenerator, noiseX, noiseZ) - 8.0F;

			if (biomeDensityOffset > 0) {
				biomeDensityFactor = 0.25d;
			}
			else {
				biomeDensityFactor = 1;
			}
		}
		else {
			float totalDepth = 0;
			float totalScale = 0;
			float totalWeight = 0;
			int seaLevel = getSeaLevel();
			float relativeBiomeDepth = biomeSource.getNoiseBiome(noiseX, seaLevel, noiseZ).getDepth();

			for(int biomeX = -2; biomeX <= 2; ++biomeX) {
				for(int biomeZ = -2; biomeZ <= 2; ++biomeZ) {
					Biome biome = biomeSource.getNoiseBiome(noiseX + biomeX, seaLevel, noiseZ + biomeZ);
					float rawDepth = biome.getDepth();
					float rawScale = biome.getScale();

					if (isCeiling)
						rawScale = -1 + rawScale;

					if (noiseSettings.isAmplified() && rawDepth > 0) {
						rawDepth = 1 + rawDepth * 2f;
						rawScale = 1 + rawScale * 4f;
					}

					float biomeDepthModifier = biome.getDepth() > relativeBiomeDepth ? 0.5F : 1.0F;
					float biomeWeight = biomeDepthModifier * biomeWeightModifiers[biomeX + 2 + (biomeZ + 2) * 5] / (rawDepth + 2.0F);
					totalDepth += rawScale * biomeWeight;
					totalScale += rawDepth * biomeWeight;
					totalWeight += biomeWeight;
				}
			}

			float rawDepth = totalScale / totalWeight;
			float rawScale = totalDepth / totalWeight;
			double biomeDepth = rawDepth * 0.5F - 0.125F;
			double biomeScale = rawScale * 0.9F + 0.1F;
			biomeDensityOffset = biomeDepth * 0.265625D;
			biomeDensityFactor = 96.0D / biomeScale;
		}

		if (isCeiling) {
			biomeDensityOffset /= 5d;
			biomeDensityFactor /= 10d;
		}

		double horizontalNoiseScale = 684.412D * noiseSettings.noiseSamplingSettings().xzScale();
		double verticalNoiseScale = 684.412D * noiseSettings.noiseSamplingSettings().yScale();
		double horizontalNoiseScaleModifier = horizontalNoiseScale / noiseSettings.noiseSamplingSettings().xzFactor();
		double verticalNoiseScaleModifier = verticalNoiseScale / noiseSettings.noiseSamplingSettings().yFactor();
		double upperSlideTarget = noiseSettings.topSlideSettings().target();
		double upperSlideSize = noiseSettings.topSlideSettings().size();
		double upperSlideOffset = noiseSettings.topSlideSettings().offset();
		double lowerSlideTarget = noiseSettings.bottomSlideSettings().target();
		double lowerSlideSize = noiseSettings.bottomSlideSettings().size();
		double lowerSlideOffset = noiseSettings.bottomSlideSettings().offset();
		double randomNoiseDensityOffset = noiseSettings.randomDensityOffset() ? getNoiseDensityOffset(noiseX, noiseZ) : 0;
		double noiseDensityFactor = noiseSettings.densityFactor();
		double noiseDensityOffset = noiseSettings.densityOffset();

		for(int noiseY = 0; noiseY <= noiseSizeY; ++noiseY) {
			double height = getLerpedNoiseAt(noiseX, noiseY, noiseZ, horizontalNoiseScale, verticalNoiseScale, horizontalNoiseScaleModifier, verticalNoiseScaleModifier);
			double baseHeightGradient = 1.0D - (double)noiseY * 2.0D / (double)noiseSizeY + randomNoiseDensityOffset;
			double configuredHeightGradient = baseHeightGradient * noiseDensityFactor + noiseDensityOffset;
			double biomeHeightGradient = (configuredHeightGradient + biomeDensityOffset) * biomeDensityFactor;

			if (biomeHeightGradient > 0.0D) {
				height = height + biomeHeightGradient * 4.0D;
			}
			else {
				height = height + biomeHeightGradient;
			}

			if (upperSlideSize > 0.0D) {
				double upperSlideNoiseFraction = ((double)(noiseSizeY - noiseY) - upperSlideOffset) / upperSlideSize;
				height = Mth.clampedLerp(upperSlideTarget, height, upperSlideNoiseFraction);
			}

			if (lowerSlideSize > 0.0D) {
				double lowerSlideNoiseFraction = ((double)noiseY - lowerSlideOffset) / lowerSlideSize;
				height = Mth.clampedLerp(lowerSlideTarget, height, lowerSlideNoiseFraction);
			}

			noiseColumn[noiseY] = height;
		}
	}

	private double getNoiseDensityOffset(int x, int z) {
		double depthNoise = this.depthNoise.getValue(x * 200, 10, z * 200, 1, 0, true);

		if (depthNoise < 0.0D) {
			depthNoise = -depthNoise * 0.3D;
		}

		double offset = depthNoise * 24.575625D - 2.0D;

		return offset < 0 ? offset * 0.009486607142857142D : Math.min(offset, 1) * 0.006640625D;
	}

	@Override
	public int getBaseHeight(int x, int z, Heightmap.Type heightmapType) {
		return createAndGetGroundPos(x, z, null, heightmapType.isOpaque());
	}

	@Override
	public BlockGetter getBaseColumn(int posX, int posZ) {
		BlockState[] noiseColumnStates = new BlockState[this.noiseSizeY * this.verticalNoiseGranularity];

		createAndGetGroundPos(posX, posZ, noiseColumnStates, null);

		return new Blockreader(noiseColumnStates);
	}

	private int createAndGetGroundPos(int posX, int posZ, @Nullable BlockState[] noiseColumnStates, @Nullable Predicate<BlockState> heightmapPredicate) {
		int xPosNoiseGranularityOvershoot = Math.floorDiv(posX, horizontalNoiseGranularity);
		int zPosNoiseGranularityOvershoot = Math.floorDiv(posZ, horizontalNoiseGranularity);
		int xNoiseStep = Math.floorMod(posX, horizontalNoiseGranularity);
		int zNoiseStep = Math.floorMod(posZ, horizontalNoiseGranularity);
		double xNoiseStepProgress = (double)xNoiseStep / (double)horizontalNoiseGranularity;
		double zNoiseStepProgress = (double)zNoiseStep / (double)horizontalNoiseGranularity;
		double[][] fuzzyNoiseColumns = new double[][] {getNoiseColumn(xPosNoiseGranularityOvershoot, zPosNoiseGranularityOvershoot, false), getNoiseColumn(xPosNoiseGranularityOvershoot, zPosNoiseGranularityOvershoot + 1, false), getNoiseColumn(xPosNoiseGranularityOvershoot + 1, zPosNoiseGranularityOvershoot, false), getNoiseColumn(xPosNoiseGranularityOvershoot + 1, zPosNoiseGranularityOvershoot + 1, false)};

		for(int noisePosY = noiseSizeY - 1; noisePosY >= 0; --noisePosY) {
			double posYNoise = fuzzyNoiseColumns[0][noisePosY];
			double posYNoisePlusZ = fuzzyNoiseColumns[1][noisePosY];
			double posYNoisePlusX = fuzzyNoiseColumns[2][noisePosY];
			double posYNoisePlusXZ = fuzzyNoiseColumns[3][noisePosY];
			double posYNoiseUp = fuzzyNoiseColumns[0][noisePosY + 1];
			double posYNoisePlusZUp = fuzzyNoiseColumns[1][noisePosY + 1];
			double posYNoisePlusXUp = fuzzyNoiseColumns[2][noisePosY + 1];
			double posYNoisePlusXZUp = fuzzyNoiseColumns[3][noisePosY + 1];

			for(int noiseColumnStep = verticalNoiseGranularity - 1; noiseColumnStep >= 0; --noiseColumnStep) {
				double noiseColumnProgressPercent = (double)noiseColumnStep / (double)verticalNoiseGranularity;
				double lerpedNoisePos = Mth.lerp3(noiseColumnProgressPercent, xNoiseStepProgress, zNoiseStepProgress, posYNoise, posYNoiseUp, posYNoisePlusX, posYNoisePlusXUp, posYNoisePlusZ, posYNoisePlusZUp, posYNoisePlusXZ, posYNoisePlusXZUp);
				int yPos = noisePosY * verticalNoiseGranularity + noiseColumnStep;
				BlockState state = getStateForPosition(lerpedNoisePos, yPos);

				if (noiseColumnStates != null)
					noiseColumnStates[yPos] = state;

				if (heightmapPredicate != null && heightmapPredicate.test(state))
					return yPos + 1;
			}
		}

		return 0;
	}

	protected BlockState getStateForPosition(double lerpedNoisePos, int yPos) {
		BlockState state;

		if (lerpedNoisePos > 0.0D) {
			state = defaultBlock;
		}
		else if (yPos < getSeaLevel()) {
			state = defaultFluid;
		}
		else {
			state = AIR;
		}

		return state;
	}

	@Override
	public void buildSurfaceAndBedrock(WorldGenRegion worldGenRegion, ChunkAccess chunk) {
		ChunkPos chunkpos = chunk.getPos();
		int chunkX = chunkpos.x;
		int chunkZ = chunkpos.z;
		SharedSeedRandom seedRandom = new SharedSeedRandom();
		seedRandom.setBaseChunkSeed(chunkX, chunkZ);
		int chunkStartPosX = chunkpos.getMinBlockX();
		int chunkStartPosZ = chunkpos.getMinBlockZ();
		BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

		for(int x = 0; x < 16; ++x) {
			for(int z = 0; z < 16; ++z) {
				int posX = chunkStartPosX + x;
				int posZ = chunkStartPosZ + z;
				int posY = chunk.getHeight(Heightmap.Type.WORLD_SURFACE_WG, x, z) + 1;
				double surfaceNoise = this.surfaceNoise.getSurfaceNoiseValue((double)posX * 0.0625D, (double)posZ * 0.0625D, 0.0625D, (double)x * 0.0625D) * 15.0D;

				worldGenRegion.getBiome(mutablePos.set(chunkStartPosX + x, posY, chunkStartPosZ + z)).buildSurfaceAt(seedRandom, chunk, posX, posZ, posY, surfaceNoise, defaultBlock, defaultFluid, getSeaLevel(), worldGenRegion.getSeed());
			}
		}

		makeBedrock(chunk, seedRandom);
	}

	private void makeBedrock(ChunkAccess chunk, Random rand) {
		BlockPos.MutableBlockPos blockpos$mutable = new BlockPos.MutableBlockPos();
		int i = chunk.getPos().getMinBlockX();
		int j = chunk.getPos().getMinBlockZ();
		DimensionSettings dimensionsettings = this.dimensionSettings.get();
		int k = dimensionsettings.getBedrockFloorPosition();
		int l = this.logicalWorldHeight - 1 - dimensionsettings.getBedrockRoofPosition();
		boolean flag = l + 4 >= 0 && l < this.logicalWorldHeight;
		boolean flag1 = k + 4 >= 0 && k < this.logicalWorldHeight;

		if (flag || flag1) {
			for(BlockPos blockpos : BlockPos.betweenClosed(i, 0, j, i + 15, 0, j + 15)) {
				if (flag) {
					for(int j1 = 0; j1 < 5; ++j1) {
						if (j1 <= rand.nextInt(5))
							chunk.setBlockState(blockpos$mutable.set(blockpos.getX(), l - j1, blockpos.getZ()), Blocks.BEDROCK.defaultBlockState(), false);
					}
				}

				if (flag1) {
					for(int k1 = 4; k1 >= 0; --k1) {
						if (k1 <= rand.nextInt(5))
							chunk.setBlockState(blockpos$mutable.set(blockpos.getX(), k + k1, blockpos.getZ()), Blocks.BEDROCK.defaultBlockState(), false);
					}
				}
			}

		}
	}

	private void generateCeiling(ChunkAccess chunk) {
		ChunkPos chunkPos = chunk.getPos();
		int chunkX = chunkPos.x;
		int chunkZ = chunkPos.z;
		int chunkStartPosX = chunkX << 4;
		int chunkStartPosZ = chunkZ << 4;

		double[][][] noisePosMap = new double[2][noiseSizeZ + 1][noiseSizeY + 1];

		for(int noisePosZ = 0; noisePosZ < noiseSizeZ + 1; ++noisePosZ) {
			noisePosMap[0][noisePosZ] = new double[noiseSizeY + 1];
			noisePosMap[1][noisePosZ] = new double[noiseSizeY + 1];

			fillNoiseColumn(noisePosMap[0][noisePosZ], chunkX * noiseSizeX, chunkZ * noiseSizeZ + noisePosZ, true);
		}

		ChunkPrimer primer = (ChunkPrimer)chunk;
		Heightmap oceanFloorHeightmap = primer.getOrCreateHeightmapUnprimed(Heightmap.Type.OCEAN_FLOOR_WG);
		Heightmap surfaceHeightmap = primer.getOrCreateHeightmapUnprimed(Heightmap.Type.WORLD_SURFACE_WG);
		BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

		for(int noisePosX = 0; noisePosX < noiseSizeX; ++noisePosX) {
			for(int i = 0; i < noiseSizeZ + 1; ++i) {
				fillNoiseColumn(noisePosMap[1][i], chunkX * noiseSizeX + noisePosX + 1, chunkZ * noiseSizeZ + i, true);
			}

			for(int noisePosZ = 0; noisePosZ < noiseSizeZ; ++noisePosZ) {
				ChunkSection chunkSection = primer.getOrCreateSection(15);
				chunkSection.acquire();

				for(int noisePosY = noiseSizeY - 1; noisePosY >= 0; --noisePosY) {
					double yNoiseMin = noisePosMap[0][noisePosZ][noisePosY];
					double zAdjacentYNoiseMin = noisePosMap[0][noisePosZ + 1][noisePosY];
					double xAdjacentYNoiseMin = noisePosMap[1][noisePosZ][noisePosY];
					double xzAdjacentYNoiseMin = noisePosMap[1][noisePosZ + 1][noisePosY];
					double yNoiseMax = noisePosMap[0][noisePosZ][noisePosY + 1];
					double zAdjacentYNoiseMax = noisePosMap[0][noisePosZ + 1][noisePosY + 1];
					double xAdjacentYNoiseMax = noisePosMap[1][noisePosZ][noisePosY + 1];
					double xzAdjacentYNoiseMax = noisePosMap[1][noisePosZ + 1][noisePosY + 1];

					for(int yNoiseStepPos = verticalNoiseGranularity - 1; yNoiseStepPos >= 0; --yNoiseStepPos) {
						int posY = noisePosY * verticalNoiseGranularity + yNoiseStepPos;
						int chunkSectionRelativePosY = posY & 15;
						int verticalChunkSectionId = posY >> 4;

						if (chunkSection.bottomBlockY() >> 4 != verticalChunkSectionId) {
							chunkSection.release();
							chunkSection = primer.getOrCreateSection(verticalChunkSectionId);
							chunkSection.acquire();
						}

						double yNoiseStepProgress = (double)yNoiseStepPos / (double)verticalNoiseGranularity;
						double lerpYNoise = Mth.lerp(yNoiseStepProgress, yNoiseMin, yNoiseMax);
						double lerpXYNoise = Mth.lerp(yNoiseStepProgress, xAdjacentYNoiseMin, xAdjacentYNoiseMax);
						double lerpZYNoise = Mth.lerp(yNoiseStepProgress, zAdjacentYNoiseMin, zAdjacentYNoiseMax);
						double lerpXYZNoise = Mth.lerp(yNoiseStepProgress, xzAdjacentYNoiseMin, xzAdjacentYNoiseMax);

						for(int xNoiseStepPos = 0; xNoiseStepPos < horizontalNoiseGranularity; ++xNoiseStepPos) {
							int posX = chunkStartPosX + noisePosX * horizontalNoiseGranularity + xNoiseStepPos;
							int chunkRelativePosX = posX & 15;
							double xNoiseStepProgress = (double)xNoiseStepPos / (double)horizontalNoiseGranularity;
							double xLerpYNoise = Mth.lerp(xNoiseStepProgress, lerpYNoise, lerpXYNoise);
							double zLerpYNoise = Mth.lerp(xNoiseStepProgress, lerpZYNoise, lerpXYZNoise);

							for(int zNoiseStepPos = 0; zNoiseStepPos < horizontalNoiseGranularity; ++zNoiseStepPos) {
								int posZ = chunkStartPosZ + noisePosZ * horizontalNoiseGranularity + zNoiseStepPos;
								int chunkRelativePosZ = posZ & 15;
								double zNoiseStepProgress = (double)zNoiseStepPos / (double)horizontalNoiseGranularity;
								double finalLerpNoise = Mth.lerp(zNoiseStepProgress, xLerpYNoise, zLerpYNoise);
								double lerpedNoisePos = Mth.clamp(finalLerpNoise / 200d, -1, 1);
								BlockState state = getStateForPosition(lerpedNoisePos, posY);

								if (state != AIR) {
									mutablePos.set(posX, posY, posZ);

									if (state.getLightEmission(primer, mutablePos) != 0)
										primer.addLight(mutablePos);

									chunkSection.setBlockState(chunkRelativePosX, chunkSectionRelativePosY, chunkRelativePosZ, state, false);
									oceanFloorHeightmap.update(chunkRelativePosX, posY, chunkRelativePosZ, state);
									surfaceHeightmap.update(chunkRelativePosX, posY, chunkRelativePosZ, state);
								}
							}
						}
					}
				}

				chunkSection.release();
			}

			double[][] tempNoiseMap = noisePosMap[0];
			noisePosMap[0] = noisePosMap[1];
			noisePosMap[1] = tempNoiseMap;
		}
	}

	@Override
	public void fillFromNoise(LevelAccessor world, StructureManager structureManager, ChunkAccess chunkPrimer) {
		ObjectList<StructurePiece> structurePieces = new ObjectArrayList<>(10);
		ObjectList<JigsawJunction> jigsawJunctions = new ObjectArrayList<>(32);
		ChunkPos chunkPos = chunkPrimer.getPos();
		int chunkX = chunkPos.x;
		int chunkZ = chunkPos.z;
		int chunkStartPosX = chunkX << 4;
		int chunkStartPosZ = chunkZ << 4;

		for(Structure<?> structure : Structure.NOISE_AFFECTING_FEATURES) {
			structureManager.startsForFeature(SectionPos.of(chunkPos, 0), structure).forEach((structureStart) -> {
				for(StructurePiece structurePiece : structureStart.getPieces()) {
					if (structurePiece.isCloseToChunk(chunkPos, 12)) {
						if (structurePiece instanceof AbstractVillagePiece) {
							AbstractVillagePiece villagePiece = (AbstractVillagePiece)structurePiece;
							JigsawPattern.PlacementBehaviour placementBehaviour = villagePiece.getElement().getProjection();

							if (placementBehaviour == JigsawPattern.PlacementBehaviour.RIGID)
								structurePieces.add(villagePiece);

							for (JigsawJunction junction : villagePiece.getJunctions()) {
								int junctionX = junction.getSourceX();
								int junctionZ = junction.getSourceZ();

								if (junctionX > chunkStartPosX - 12 && junctionZ > chunkStartPosZ - 12 && junctionX < chunkStartPosX + 15 + 12 && junctionZ < chunkStartPosZ + 15 + 12)
									jigsawJunctions.add(junction);
							}
						}
						else {
							structurePieces.add(structurePiece);
						}
					}
				}
			});
		}

		double[][][] noisePosMap = new double[2][noiseSizeZ + 1][noiseSizeY + 1];

		for(int noisePosZ = 0; noisePosZ < noiseSizeZ + 1; ++noisePosZ) {
			noisePosMap[0][noisePosZ] = new double[noiseSizeY + 1];
			noisePosMap[1][noisePosZ] = new double[noiseSizeY + 1];

			fillNoiseColumn(noisePosMap[0][noisePosZ], chunkX * noiseSizeX, chunkZ * noiseSizeZ + noisePosZ, false);
		}

		ChunkPrimer primer = (ChunkPrimer)chunkPrimer;
		Heightmap oceanFloorHeightmap = primer.getOrCreateHeightmapUnprimed(Heightmap.Type.OCEAN_FLOOR_WG);
		Heightmap surfaceHeightmap = primer.getOrCreateHeightmapUnprimed(Heightmap.Type.WORLD_SURFACE_WG);
		BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();
		ObjectListIterator<StructurePiece> structurePiecesIterator = structurePieces.iterator();
		ObjectListIterator<JigsawJunction> jigsawJunctionsIterator = jigsawJunctions.iterator();

		for(int noisePosX = 0; noisePosX < noiseSizeX; ++noisePosX) {
			for(int j1 = 0; j1 < noiseSizeZ + 1; ++j1) {
				fillNoiseColumn(noisePosMap[1][j1], chunkX * noiseSizeX + noisePosX + 1, chunkZ * noiseSizeZ + j1, false);
			}

			for(int noisePosZ = 0; noisePosZ < noiseSizeZ; ++noisePosZ) {
				ChunkSection chunkSection = primer.getOrCreateSection(15);
				chunkSection.acquire();

				for(int noisePosY = noiseSizeY - 1; noisePosY >= 0; --noisePosY) {
					double yNoiseMin = noisePosMap[0][noisePosZ][noisePosY];
					double zAdjacentYNoiseMin = noisePosMap[0][noisePosZ + 1][noisePosY];
					double xAdjacentYNoiseMin = noisePosMap[1][noisePosZ][noisePosY];
					double xzAdjacentYNoiseMin = noisePosMap[1][noisePosZ + 1][noisePosY];
					double yNoiseMax = noisePosMap[0][noisePosZ][noisePosY + 1];
					double zAdjacentYNoiseMax = noisePosMap[0][noisePosZ + 1][noisePosY + 1];
					double xAdjacentYNoiseMax = noisePosMap[1][noisePosZ][noisePosY + 1];
					double xzAdjacentYNoiseMax = noisePosMap[1][noisePosZ + 1][noisePosY + 1];

					for(int yNoiseStepPos = verticalNoiseGranularity - 1; yNoiseStepPos >= 0; --yNoiseStepPos) {
						int posY = noisePosY * verticalNoiseGranularity + yNoiseStepPos;
						int chunkSectionRelativePosY = posY & 15;
						int verticalChunkSectionId = posY >> 4;

						if (chunkSection.bottomBlockY() >> 4 != verticalChunkSectionId) {
							chunkSection.release();
							chunkSection = primer.getOrCreateSection(verticalChunkSectionId);
							chunkSection.acquire();
						}

						double yNoiseStepProgress = (double)yNoiseStepPos / (double)verticalNoiseGranularity;
						double lerpYNoise = Mth.lerp(yNoiseStepProgress, yNoiseMin, yNoiseMax);
						double lerpXYNoise = Mth.lerp(yNoiseStepProgress, xAdjacentYNoiseMin, xAdjacentYNoiseMax);
						double lerpZYNoise = Mth.lerp(yNoiseStepProgress, zAdjacentYNoiseMin, zAdjacentYNoiseMax);
						double lerpXYZNoise = Mth.lerp(yNoiseStepProgress, xzAdjacentYNoiseMin, xzAdjacentYNoiseMax);

						for(int xNoiseStepPos = 0; xNoiseStepPos < horizontalNoiseGranularity; ++xNoiseStepPos) {
							int posX = chunkStartPosX + noisePosX * horizontalNoiseGranularity + xNoiseStepPos;
							int chunkRelativePosX = posX & 15;
							double xNoiseStepProgress = (double)xNoiseStepPos / (double)horizontalNoiseGranularity;
							double xLerpYNoise = Mth.lerp(xNoiseStepProgress, lerpYNoise, lerpXYNoise);
							double zLerpYNoise = Mth.lerp(xNoiseStepProgress, lerpZYNoise, lerpXYZNoise);

							for(int zNoiseStepPos = 0; zNoiseStepPos < horizontalNoiseGranularity; ++zNoiseStepPos) {
								int posZ = chunkStartPosZ + noisePosZ * horizontalNoiseGranularity + zNoiseStepPos;
								int chunkRelativePosZ = posZ & 15;
								double zNoiseStepProgress = (double)zNoiseStepPos / (double)horizontalNoiseGranularity;
								double finalLerpNoise = Mth.lerp(zNoiseStepProgress, xLerpYNoise, zLerpYNoise);
								double lerpedNoisePos = Mth.clamp(finalLerpNoise / 200d, -1, 1);

								int structureYOffset;
								int structureZOffset;
								int zRelativeStructureZOffset;

								for(lerpedNoisePos = lerpedNoisePos / 2.0D - lerpedNoisePos * lerpedNoisePos * lerpedNoisePos / 24.0D; structurePiecesIterator.hasNext(); lerpedNoisePos += getContribution(structureYOffset, structureZOffset, zRelativeStructureZOffset) * 0.8D) {
									StructurePiece structurepiece = structurePiecesIterator.next();
									MutableBoundingBox structureBoundingBox = structurepiece.getBoundingBox();
									structureYOffset = Math.max(0, Math.max(structureBoundingBox.x0 - posX, posX - structureBoundingBox.x1));
									structureZOffset = posY - (structureBoundingBox.y0 + (structurepiece instanceof AbstractVillagePiece ? ((AbstractVillagePiece)structurepiece).getGroundLevelDelta() : 0));
									zRelativeStructureZOffset = Math.max(0, Math.max(structureBoundingBox.z0 - posZ, posZ - structureBoundingBox.z1));
								}

								structurePiecesIterator.back(structurePieces.size());

								while(jigsawJunctionsIterator.hasNext()) {
									JigsawJunction jigsawjunction = jigsawJunctionsIterator.next();
									int structureXOffset = posX - jigsawjunction.getSourceX();
									structureYOffset = posY - jigsawjunction.getSourceGroundY();
									structureZOffset = posZ - jigsawjunction.getSourceZ();
									lerpedNoisePos += getContribution(structureXOffset, structureYOffset, structureZOffset) * 0.4D;
								}

								jigsawJunctionsIterator.back(jigsawJunctions.size());
								BlockState state = getStateForPosition(lerpedNoisePos, posY);

								if (state != AIR) {
									mutablePos.set(posX, posY, posZ);

									if (state.getLightEmission(primer, mutablePos) != 0)
										primer.addLight(mutablePos);

									chunkSection.setBlockState(chunkRelativePosX, chunkSectionRelativePosY, chunkRelativePosZ, state, false);
									oceanFloorHeightmap.update(chunkRelativePosX, posY, chunkRelativePosZ, state);
									surfaceHeightmap.update(chunkRelativePosX, posY, chunkRelativePosZ, state);
								}
							}
						}
					}
				}

				chunkSection.release();
			}

			double[][] tempNoiseMap = noisePosMap[0];
			noisePosMap[0] = noisePosMap[1];
			noisePosMap[1] = tempNoiseMap;
		}

		generateCeiling(chunkPrimer);
	}

	public static double getContribution(int structureXOffset, int structureYOffset, int structureZOffset) {
		int x = structureXOffset + 12;
		int y = structureYOffset + 12;
		int z = structureZOffset + 12;

		if (x >= 0 && x < 24 && y >= 0 && y < 24 && z >= 0 && z < 24)
			return BEARD_KERNEL[z * 24 * 24 + x * 24 + y];

		return 0;
	}

	private static double computeContribution(int x, int y, int z) {
		double xzSquared = x * x + z * z;
		double yCenter = (double)y + 0.5D;
		double ySquared = yCenter * yCenter;
		double d3 = Math.pow(Math.E, -(ySquared / 16.0D + xzSquared / 16.0D));
		double d4 = -yCenter * Mth.fastInvSqrt(ySquared / 2.0D + xzSquared / 2.0D) / 2.0D;

		return d4 * d3;
	}

	@Override
	public int getGenDepth() {
		return logicalWorldHeight;
	}

	@Override
	public int getSeaLevel() {
		return dimensionSettings.get().seaLevel();
	}

	@Override
	public List<MobSpawnSettings.SpawnerData> getMobsAt(Biome biome, StructureManager structureManager, MobCategory classification, BlockPos pos) {
		List<MobSpawnSettings.SpawnerData> spawns = net.minecraftforge.common.world.StructureSpawnManager.getStructureSpawns(structureManager, classification, pos);

		if (spawns != null)
			return spawns;

		return super.getMobsAt(biome, structureManager, classification, pos);
	}

	@Override
	public void spawnOriginalMobs(WorldGenRegion worldGenRegion) {
		if (!dimensionSettings.get().disableMobGeneration()) {
			int chunkX = worldGenRegion.getCenterX();
			int chunkZ = worldGenRegion.getCenterZ();
			Biome biome = worldGenRegion.getBiome((new ChunkPos(chunkX, chunkZ)).getWorldPosition());
			SharedSeedRandom seedRandom = new SharedSeedRandom();

			seedRandom.setDecorationSeed(worldGenRegion.getSeed(), chunkX << 4, chunkZ << 4);
			WorldEntitySpawner.spawnMobsForChunkGeneration(worldGenRegion, biome, chunkX, chunkZ, seedRandom);
		}
	}
}
*/
