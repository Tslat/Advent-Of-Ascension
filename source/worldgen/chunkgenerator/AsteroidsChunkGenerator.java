package net.tslat.aoa3.worldgen.chunkgenerator;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import it.unimi.dsi.fastutil.objects.ObjectListIterator;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.Util;
import net.minecraft.util.math.*;
import net.minecraft.world.Blockreader;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.ChunkSection;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.*;
import net.minecraft.world.gen.feature.jigsaw.JigsawJunction;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.structure.AbstractVillagePiece;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.settings.NoiseSettings;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.IntStream;

@SuppressWarnings("NullableProblems")
public class AsteroidsChunkGenerator extends ChunkGenerator {
	public static final Codec<AsteroidsChunkGenerator> CODEC = RecordCodecBuilder.create((record) -> record.group(
			BiomeProvider.CODEC.fieldOf("biome_source").forGetter((generator) -> generator.biomeSource),
			Codec.LONG.fieldOf("seed").stable().forGetter((generator) -> generator.seed),
			DimensionSettings.CODEC.fieldOf("settings").forGetter((generator) -> generator.dimensionSettings))
			.apply(record, record.stable(AsteroidsChunkGenerator::new)));

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
				float weight = 10.0F / MathHelper.sqrt((float)(x * x + z * z) + 0.2F);
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
	private final OctavesNoiseGenerator lateralNoise;
	private final OctavesNoiseGenerator verticalNoise;
	private final INoiseGenerator surfaceNoise;
	protected final BlockState defaultBlock;
	protected final BlockState defaultFluid;
	private final long seed;
	protected final Supplier<DimensionSettings> dimensionSettings;

	private AsteroidsChunkGenerator(BiomeProvider biomeProvider, long seed, Supplier<DimensionSettings> dimSettingsSupplier) {
		super(biomeProvider, biomeProvider, dimSettingsSupplier.get().structureSettings(), seed);

		this.seed = seed;
		this.randomSeed = new SharedSeedRandom(seed);

		DimensionSettings dimensionSettings = dimSettingsSupplier.get();
		this.dimensionSettings = dimSettingsSupplier;
		this.defaultBlock = dimensionSettings.getDefaultBlock();
		this.defaultFluid = dimensionSettings.getDefaultFluid();

		NoiseSettings noiseSettings = dimensionSettings.noiseSettings();
		this.verticalNoiseGranularity = noiseSettings.noiseSizeVertical() * 4;
		this.horizontalNoiseGranularity = noiseSettings.noiseSizeHorizontal() * 8;
		this.noiseSizeX = 16 / this.horizontalNoiseGranularity;
		this.noiseSizeY = noiseSettings.height() / this.verticalNoiseGranularity;
		this.noiseSizeZ = 16 / this.horizontalNoiseGranularity;

		this.surfaceNoise = noiseSettings.useSimplexSurfaceNoise() ? new PerlinNoiseGenerator(this.randomSeed, IntStream.rangeClosed(-3, 0)) : new OctavesNoiseGenerator(this.randomSeed, IntStream.rangeClosed(-3, 0));
		this.lateralNoise = new OctavesNoiseGenerator(this.randomSeed, IntStream.rangeClosed(-3, 0));
		this.verticalNoise = new OctavesNoiseGenerator(this.randomSeed, IntStream.rangeClosed(-9, 0));

		this.randomSeed.consumeCount(2620);
	}

	@Override
	protected Codec<? extends ChunkGenerator> codec() {
		return CODEC;
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public ChunkGenerator withSeed(long seed) {
		return new AsteroidsChunkGenerator(biomeSource.withSeed(seed), seed, dimensionSettings);
	}

	@Override
	public void buildSurfaceAndBedrock(WorldGenRegion worldGenRegion, IChunk chunk) {
		ChunkPos chunkpos = chunk.getPos();
		int chunkX = chunkpos.x;
		int chunkZ = chunkpos.z;
		SharedSeedRandom seedRandom = new SharedSeedRandom();
		seedRandom.setBaseChunkSeed(chunkX, chunkZ);
		int chunkStartPosX = chunkpos.getMinBlockX();
		int chunkStartPosZ = chunkpos.getMinBlockZ();
		BlockPos.Mutable mutablePos = new BlockPos.Mutable();

		for(int x = 0; x < 16; ++x) {
			for(int z = 0; z < 16; ++z) {
				int posX = chunkStartPosX + x;
				int posZ = chunkStartPosZ + z;
				int posY = chunk.getHeight(Heightmap.Type.WORLD_SURFACE_WG, x, z) + 1;
				double surfaceNoise = this.surfaceNoise.getSurfaceNoiseValue((double)posX * 0.0625D, (double)posZ * 0.0625D, 0.0625D, (double)x * 0.0625D) * 15.0D;

				worldGenRegion.getBiome(mutablePos.set(chunkStartPosX + x, posY, chunkStartPosZ + z)).buildSurfaceAt(seedRandom, chunk, posX, posZ, posY, surfaceNoise, defaultBlock, defaultFluid, getSeaLevel(), worldGenRegion.getSeed());
			}
		}
	}

	@Override
	public void fillFromNoise(IWorld world, StructureManager structureManager, IChunk chunkPrimer) {
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

			fillNoiseColumn(noisePosMap[0][noisePosZ], chunkX * noiseSizeX, chunkZ * noiseSizeZ + noisePosZ);
		}

		ChunkPrimer primer = (ChunkPrimer)chunkPrimer;
		Heightmap oceanFloorHeightmap = primer.getOrCreateHeightmapUnprimed(Heightmap.Type.OCEAN_FLOOR_WG);
		Heightmap surfaceHeightmap = primer.getOrCreateHeightmapUnprimed(Heightmap.Type.WORLD_SURFACE_WG);
		BlockPos.Mutable mutablePos = new BlockPos.Mutable();
		ObjectListIterator<StructurePiece> structurePiecesIterator = structurePieces.iterator();
		ObjectListIterator<JigsawJunction> jigsawJunctionsIterator = jigsawJunctions.iterator();

		for(int noisePosX = 0; noisePosX < noiseSizeX; ++noisePosX) {
			for(int j1 = 0; j1 < noiseSizeZ + 1; ++j1) {
				fillNoiseColumn(noisePosMap[1][j1], chunkX * noiseSizeX + noisePosX + 1, chunkZ * noiseSizeZ + j1);
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
						double lerpYNoise = MathHelper.lerp(yNoiseStepProgress, yNoiseMin, yNoiseMax);
						double lerpXYNoise = MathHelper.lerp(yNoiseStepProgress, xAdjacentYNoiseMin, xAdjacentYNoiseMax);
						double lerpZYNoise = MathHelper.lerp(yNoiseStepProgress, zAdjacentYNoiseMin, zAdjacentYNoiseMax);
						double lerpXYZNoise = MathHelper.lerp(yNoiseStepProgress, xzAdjacentYNoiseMin, xzAdjacentYNoiseMax);

						for(int xNoiseStepPos = 0; xNoiseStepPos < horizontalNoiseGranularity; ++xNoiseStepPos) {
							int posX = chunkStartPosX + noisePosX * horizontalNoiseGranularity + xNoiseStepPos;
							int chunkRelativePosX = posX & 15;
							double xNoiseStepProgress = (double)xNoiseStepPos / (double)horizontalNoiseGranularity;
							double xLerpYNoise = MathHelper.lerp(xNoiseStepProgress, lerpYNoise, lerpXYNoise);
							double zLerpYNoise = MathHelper.lerp(xNoiseStepProgress, lerpZYNoise, lerpXYZNoise);

							for(int zNoiseStepPos = 0; zNoiseStepPos < horizontalNoiseGranularity; ++zNoiseStepPos) {
								int posZ = chunkStartPosZ + noisePosZ * horizontalNoiseGranularity + zNoiseStepPos;
								int chunkRelativePosZ = posZ & 15;
								double zNoiseStepProgress = (double)zNoiseStepPos / (double)horizontalNoiseGranularity;
								double finalLerpNoise = MathHelper.lerp(zNoiseStepProgress, xLerpYNoise, zLerpYNoise);
								double lerpedNoisePos = MathHelper.clamp(finalLerpNoise / 200d, -1, 1);

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

									if (state.getLightValue(primer, mutablePos) != 0)
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
	public int getBaseHeight(int x, int z, Heightmap.Type heightmapType) {
		return createAndGetGroundPos(x, z, null, heightmapType.isOpaque());
	}

	@Override
	public IBlockReader getBaseColumn(int posX, int posZ) {
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
		double[][] fuzzyNoiseColumns = new double[][] {getNoiseColumn(xPosNoiseGranularityOvershoot, zPosNoiseGranularityOvershoot), getNoiseColumn(xPosNoiseGranularityOvershoot, zPosNoiseGranularityOvershoot + 1), getNoiseColumn(xPosNoiseGranularityOvershoot + 1, zPosNoiseGranularityOvershoot), getNoiseColumn(xPosNoiseGranularityOvershoot + 1, zPosNoiseGranularityOvershoot + 1)};

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
				double lerpedNoisePos = MathHelper.lerp3(noiseColumnProgressPercent, xNoiseStepProgress, zNoiseStepProgress, posYNoise, posYNoiseUp, posYNoisePlusX, posYNoisePlusXUp, posYNoisePlusZ, posYNoisePlusZUp, posYNoisePlusXZ, posYNoisePlusXZUp);
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

	private double[] getNoiseColumn(int noiseX, int noiseZ) {
		double[] noiseColumn = new double[noiseSizeY + 1];

		fillNoiseColumn(noiseColumn, noiseX, noiseZ);

		return noiseColumn;
	}

	private void fillNoiseColumn(double[] noiseColumn, int noiseX, int noiseZ) {
		NoiseSettings noiseSettings = dimensionSettings.get().noiseSettings();
		double biomeDensityOffset;
		double biomeDensityFactor;

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

		double horizontalNoiseScale = 684.412D * noiseSettings.noiseSamplingSettings().xzScale() / 10d;
		double verticalNoiseScale = 684.412D * noiseSettings.noiseSamplingSettings().yScale() * 10;
		double horizontalNoiseScaleModifier = horizontalNoiseScale / noiseSettings.noiseSamplingSettings().xzFactor() * 6d;
		double verticalNoiseScaleModifier = verticalNoiseScale / noiseSettings.noiseSamplingSettings().yFactor();
		double upperSlideTarget = noiseSettings.topSlideSettings().target();
		double upperSlideSize = noiseSettings.topSlideSettings().size();
		double upperSlideOffset = noiseSettings.topSlideSettings().offset();
		double lowerSlideTarget = noiseSettings.bottomSlideSettings().target();
		double lowerSlideSize = 10;
		double lowerSlideOffset = 1;
		double noiseDensityOffset = noiseSettings.densityOffset();

		for(int noiseY = 0; noiseY <= noiseSizeY; ++noiseY) {
			double height = getCombinedNoiseAt(noiseX, noiseY, noiseZ, horizontalNoiseScale, verticalNoiseScale, horizontalNoiseScaleModifier, verticalNoiseScaleModifier);
			double biomeHeightGradient = (noiseDensityOffset + biomeDensityOffset) * biomeDensityFactor;

			if (biomeHeightGradient > 0.0D) {
				height = height + biomeHeightGradient * 4.0D;
			}
			else {
				height = height + biomeHeightGradient;
			}

			if (upperSlideSize > 0.0D) {
				double upperSlideNoiseFraction = ((double)(noiseSizeY - noiseY) - upperSlideOffset) / upperSlideSize;
				height = MathHelper.clampedLerp(upperSlideTarget, height, upperSlideNoiseFraction);
			}

			double lowerSlideNoiseFraction = ((double)noiseY - lowerSlideOffset) / lowerSlideSize;
			height = MathHelper.clampedLerp(lowerSlideTarget, height, lowerSlideNoiseFraction);

			noiseColumn[noiseY] = height;
		}
	}

	private double getCombinedNoiseAt(int posX, int posY, int posZ, double horizontalNoiseScale, double verticalNoiseScale, double horizontalNoiseScaleModifier, double verticalNoiseScaleModifier) {
		double verticalNoise = 0;
		double lateralNoise = 0;
		double noiseStageMultiplier = 0.2d;

		for(int i = 0; i < 9; ++i) {
			double x = OctavesNoiseGenerator.wrap((double)posX * horizontalNoiseScaleModifier * noiseStageMultiplier);
			double y = OctavesNoiseGenerator.wrap((double)posY * verticalNoiseScaleModifier * noiseStageMultiplier);
			double z = OctavesNoiseGenerator.wrap((double)posZ * horizontalNoiseScaleModifier * noiseStageMultiplier);
			double verticalNoiseModifier = verticalNoiseScale * noiseStageMultiplier;
			double lateralNoiseModifier = horizontalNoiseScale * noiseStageMultiplier;
			ImprovedNoiseGenerator verticalNoiseOctave = this.verticalNoise.getOctaveNoise(i);

			if (verticalNoiseOctave != null)
				verticalNoise += verticalNoiseOctave.noise(x, y, z, verticalNoiseModifier, (double)posY * verticalNoiseModifier) / noiseStageMultiplier;

			ImprovedNoiseGenerator lateralNoiseOctave = this.lateralNoise.getOctaveNoise(i / 3);

			if (lateralNoiseOctave != null)
				lateralNoise += lateralNoiseOctave.noise(y, x, z, lateralNoiseModifier, (double)posY * lateralNoiseModifier * 2) / noiseStageMultiplier;

			noiseStageMultiplier /= 1.4D;
		}

		double min = Math.min(lateralNoise, verticalNoise);
		double max = Math.max(lateralNoise, verticalNoise);

		return (verticalNoise / 2.5 * lateralNoise * 2.5 - min) / (max - min);
	}

	private static double computeContribution(int x, int y, int z) {
		double xzSquared = x * x + z * z;
		double yCenter = (double)y + 0.5D;
		double ySquared = yCenter * yCenter;
		double d3 = Math.pow(Math.E, -(ySquared / 16.0D + xzSquared / 16.0D));
		double d4 = -yCenter * MathHelper.fastInvSqrt(ySquared / 2.0D + xzSquared / 2.0D) / 2.0D;

		return d4 * d3;
	}

	public static double getContribution(int structureXOffset, int structureYOffset, int structureZOffset) {
		int x = structureXOffset + 12;
		int y = structureYOffset + 12;
		int z = structureZOffset + 12;

		if (x >= 0 && x < 24 && y >= 0 && y < 24 && z >= 0 && z < 24)
			return BEARD_KERNEL[z * 24 * 24 + x * 24 + y];

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
}
