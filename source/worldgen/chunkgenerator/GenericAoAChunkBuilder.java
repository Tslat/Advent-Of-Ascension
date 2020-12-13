package net.tslat.aoa3.worldgen.chunkgenerator;

import net.minecraft.block.BlockState;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeManager;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.*;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.spawner.WorldEntitySpawner;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.AoABiome;

import java.util.Random;

public class GenericAoAChunkBuilder extends NoiseChunkGenerator<GenerationSettings> {
	private static final float[] weightMap = Util.make(new float[25], (weights) -> {
		for(int xOffset = -2; xOffset <= 2; ++xOffset) {
			for(int zOffset = -2; zOffset <= 2; ++zOffset) {
				float surroundingAverage = 10.0F / MathHelper.sqrt((float)(xOffset * xOffset + zOffset * zOffset) + 0.2F);
				weights[xOffset + 2 + (zOffset + 2) * 5] = surroundingAverage;
			}
		}
	});

	private final OctavesNoiseGenerator noiseGenerator;
	private final boolean isAmplified;

	public GenericAoAChunkBuilder(IWorld world, BiomeProvider provider, GenerationSettings genSettings) {
		super(world, provider, 4, 8, 256, genSettings, true);

		this.randomSeed.skip(2620);
		this.noiseGenerator = new OctavesNoiseGenerator(this.randomSeed, 15, 0);
		this.isAmplified = world.getWorldInfo().getGenerator() == WorldType.AMPLIFIED;
	}

	@Override
	public void spawnMobs(WorldGenRegion region) {
		int chunkX = region.getMainChunkX();
		int chunkZ = region.getMainChunkZ();
		Biome biome = region.getBiome((new ChunkPos(chunkX, chunkZ)).asBlockPos());
		SharedSeedRandom seededRandom = new SharedSeedRandom();

		seededRandom.setDecorationSeed(region.getSeed(), chunkX << 4, chunkZ << 4);
		WorldEntitySpawner.performWorldGenSpawning(region, biome, chunkX, chunkZ, seededRandom);
	}

	@Override
	protected void fillNoiseColumn(double[] noiseColumn, int noiseX, int noiseZ) {
		double lateralScale = 684.412F;
		double verticalScale = 684.412F;
		double lateralScaleModifier = lateralScale / 80d;
		double heightScaleModifier = verticalScale / 160d;
		int additionalVerticalNoiseLerpCap = -10;
		int lerpDivisor = 3;

		calcNoiseColumn(noiseColumn, noiseX, noiseZ, lateralScale, verticalScale, lateralScaleModifier, heightScaleModifier, lerpDivisor, additionalVerticalNoiseLerpCap);
	}

	@Override
	protected double func_222545_a(double heightAverage, double accumulatedHeightVariation, int noisePos) {
		double baseSize = 8.5d;
		double heightStretch = 12d;
		double noiseHeight = ((double)noisePos - (baseSize + heightAverage * baseSize / 8.0D * 4.0D)) * heightStretch * 128.0D / 256.0D / accumulatedHeightVariation;

		if (noiseHeight < 0.0D)
			noiseHeight *= 4;

		return noiseHeight;
	}

	@Override
	protected double[] getBiomeNoiseColumn(int noiseX, int noiseZ) {
		double[] noisePair = new double[2];
		float accumulatedHeightVariation = 0.0F;
		float accumulatedHeight = 0;
		float accumulatedHeightFactor = 0.0F;
		int seaLevel = this.getSeaLevel();

		for(int xOffset = -2; xOffset <= 2; ++xOffset) {
			for(int zOffset = -2; zOffset <= 2; ++zOffset) {
				Biome biome = biomeProvider.getNoiseBiome(noiseX + xOffset, 64, noiseZ + zOffset);
				float offsetBaseHeight = biome.getDepth();
				float heightVariation = biome.getScale();

				if (isAmplified && offsetBaseHeight > 0.0F) {
					offsetBaseHeight = 1.0F + offsetBaseHeight * 2.0F;
					heightVariation = 1.0F + heightVariation * 4.0F;
				}

				float heightFactor = weightMap[xOffset + 2 + (zOffset + 2) * 5] / (offsetBaseHeight + 2.0F);

				if (biome.getDepth() > biomeProvider.getNoiseBiome(noiseX, seaLevel, noiseZ).getDepth())
					heightFactor /= 2.0F;

				accumulatedHeightVariation += heightVariation * heightFactor;
				accumulatedHeight += offsetBaseHeight * heightFactor;
				accumulatedHeightFactor += heightFactor;
			}
		}

		accumulatedHeightVariation = accumulatedHeightVariation / accumulatedHeightFactor;
		accumulatedHeight = accumulatedHeight / accumulatedHeightFactor;
		accumulatedHeightVariation = accumulatedHeightVariation * 0.9F + 0.1F;
		accumulatedHeight = (accumulatedHeight * 4.0F - 1.0F) / 8.0F;
		noisePair[0] = (double)accumulatedHeight + getNoiseDepthAt(noiseX, noiseZ);
		noisePair[1] = accumulatedHeightVariation;

		return noisePair;
	}

	private double getNoiseDepthAt(int noiseX, int noiseZ) {
		double noiseDepth = noiseGenerator.getValue(noiseX * 200, 10.0D, noiseZ * 200, 1.0D, 0.0D, true) * 65535.0D / 8000.0D;

		if (noiseDepth < 0.0D)
			noiseDepth *= -0.3D;

		noiseDepth = noiseDepth * 3.0D - 2.0D;

		if (noiseDepth < 0.0D) {
			noiseDepth /= 28.0D;
		}
		else {
			if (noiseDepth > 1)
				noiseDepth = 1;

			noiseDepth /= 40.0D;
		}

		return noiseDepth;
	}

	@Override
	public void generateStructures(BiomeManager biomeManager, IChunk chunk, ChunkGenerator<?> chunkGenerator, TemplateManager templateManager) {
		super.generateStructures(biomeManager, chunk, chunkGenerator, templateManager);

		Biome centerBiome = biomeManager.getBiome(new BlockPos(chunk.getPos().getXStart() + 8, 0, chunk.getPos().getZStart() + 8));

		if (centerBiome instanceof AoABiome)
			((AoABiome)centerBiome).generateStructures(world, biomeManager, chunk, chunkGenerator, templateManager);
	}

	@Override
	protected void makeBedrock(IChunk chunk, Random rand) {
		ChunkPos chunkPos = chunk.getPos();
		BlockPos.Mutable pos = new BlockPos.Mutable();
		BlockState dimFabric = AoABlocks.DIMENSIONAL_FABRIC.get().getDefaultState();

		for (int x = chunkPos.getXStart(); x <= chunkPos.getXEnd(); x++) {
			for (int z = chunkPos.getZStart(); z <= chunkPos.getZEnd(); z++) {
				for (int y = 0; y <= 1; y++) {
					chunk.setBlockState(pos.setPos(x, y, z), dimFabric, false);
				}
			}
		}
	}

	@Override
	public int getGroundHeight() {
		return 64;
	}
}
