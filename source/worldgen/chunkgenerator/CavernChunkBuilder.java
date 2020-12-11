package net.tslat.aoa3.worldgen.chunkgenerator;

import it.unimi.dsi.fastutil.longs.LongIterator;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import it.unimi.dsi.fastutil.objects.ObjectListIterator;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeManager;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.ChunkSection;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.*;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.jigsaw.JigsawJunction;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.structure.AbstractVillagePiece;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.spawner.WorldEntitySpawner;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.AoABiome;
import net.tslat.aoa3.worldgen.chunkgenerator.config.CavernGenerationSettings;

import java.util.Random;

public class CavernChunkBuilder extends NoiseChunkGenerator<CavernGenerationSettings> {
	private static final float[] weightMap = Util.make(new float[25], (weights) -> {
		for(int xOffset = -2; xOffset <= 2; ++xOffset) {
			for(int zOffset = -2; zOffset <= 2; ++zOffset) {
				float surroundingAverage = 10.0F / MathHelper.sqrt((float)(xOffset * xOffset + zOffset * zOffset) + 0.2F);
				weights[xOffset + 2 + (zOffset + 2) * 5] = surroundingAverage;
			}
		}
	});

	private final OctavesNoiseGenerator noiseGenerator;

	public CavernChunkBuilder(IWorld world, BiomeProvider provider, CavernGenerationSettings genSettings) {
		super(world, provider, 4, 8, 256, genSettings, true);

		this.randomSeed.skip(2620);
		this.noiseGenerator = new OctavesNoiseGenerator(this.randomSeed, 15, 0);
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
	public int getHeight(int x, int z, Heightmap.Type heightmapType) {
		return 20;
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
	public void makeBase(IWorld world, IChunk chunk) {
		int seaLevel = this.getSeaLevel();
		ObjectList<AbstractVillagePiece> villagePieces = new ObjectArrayList<>(10);
		ObjectList<JigsawJunction> jigsawJunctions = new ObjectArrayList<>(32);
		ChunkPos chunkPos = chunk.getPos();
		int chunkX = chunkPos.x;
		int chunkZ = chunkPos.z;
		int baseX = chunkX << 4;
		int baseZ = chunkZ << 4;

		for (Structure<?> structure : Feature.ILLAGER_STRUCTURES) {
			String structureName = structure.getStructureName();
			LongIterator structureReferences = chunk.getStructureReferences(structureName).iterator();

			while (structureReferences.hasNext()) {
				long structurePos = structureReferences.nextLong();
				ChunkPos structureChunkPos = new ChunkPos(structurePos);
				StructureStart structureStart = world.getChunk(structureChunkPos.x, structureChunkPos.z).getStructureStart(structureName);

				if (structureStart != null && structureStart.isValid()) {
					for (StructurePiece piece : structureStart.getComponents()) {
						if (piece.func_214810_a(chunkPos, 12) && piece instanceof AbstractVillagePiece) {
							AbstractVillagePiece villagePiece = (AbstractVillagePiece)piece;

							if (villagePiece.getJigsawPiece().getPlacementBehaviour() == JigsawPattern.PlacementBehaviour.RIGID)
								villagePieces.add(villagePiece);

							for (JigsawJunction junction : villagePiece.getJunctions()) {
								int villageStartX = junction.getSourceX();
								int villageStartZ = junction.getSourceZ();

								if (villageStartX > baseX - 12 && villageStartZ > baseZ - 12 && villageStartX < baseX + 15 + 12 && villageStartZ < baseZ + 15 + 12)
									jigsawJunctions.add(junction);
							}
						}
					}
				}
			}
		}

		double[][][] noiseMap = new double[2][this.noiseSizeZ + 1][this.noiseSizeY + 1];

		for (int i = 0; i < this.noiseSizeZ + 1; ++i) {
			noiseMap[0][i] = new double[this.noiseSizeY + 1];

			this.fillNoiseColumn(noiseMap[0][i], chunkX * this.noiseSizeX, chunkZ * this.noiseSizeZ + i);

			noiseMap[1][i] = new double[this.noiseSizeY + 1];
		}

		ChunkPrimer chunkPrimer = (ChunkPrimer)chunk;
		Heightmap oceanFloorHeightmap = chunkPrimer.getHeightmap(Heightmap.Type.OCEAN_FLOOR_WG);
		Heightmap surfaceHeightmap = chunkPrimer.getHeightmap(Heightmap.Type.WORLD_SURFACE_WG);
		BlockPos.Mutable mutablePos = new BlockPos.Mutable();
		ObjectListIterator<AbstractVillagePiece> villagePieceIterator = villagePieces.iterator();
		ObjectListIterator<JigsawJunction> jigsawJunctionIterator = jigsawJunctions.iterator();

		for (int xNoiseMod = 0; xNoiseMod < this.noiseSizeX; ++xNoiseMod) {
			for (int zNoisePos = 0; zNoisePos < this.noiseSizeZ + 1; ++zNoisePos) {
				this.fillNoiseColumn(noiseMap[1][zNoisePos], chunkX * this.noiseSizeX + xNoiseMod + 1, chunkZ * this.noiseSizeZ + zNoisePos);
			}

			for (int zNoiseMod = 0; zNoiseMod < this.noiseSizeZ; ++zNoiseMod) {
				ChunkSection chunkSection = chunkPrimer.getSection(15);
				chunkSection.lock();

				for (int posY = this.noiseSizeY - 1; posY >= 0; --posY) {
					double d16 = noiseMap[0][zNoiseMod][posY];
					double d17 = noiseMap[0][zNoiseMod + 1][posY];
					double d18 = noiseMap[1][zNoiseMod][posY];
					double d0 = noiseMap[1][zNoiseMod + 1][posY];
					double d1 = noiseMap[0][zNoiseMod][posY + 1];
					double d2 = noiseMap[0][zNoiseMod + 1][posY + 1];
					double d3 = noiseMap[1][zNoiseMod][posY + 1];
					double d4 = noiseMap[1][zNoiseMod + 1][posY + 1];

					for (int posYNoiseOffset = this.verticalNoiseGranularity - 1; posYNoiseOffset >= 0; --posYNoiseOffset) {
						int finalPosY = posY * this.verticalNoiseGranularity + posYNoiseOffset;
						int chunkRelativeYPos = finalPosY & 15;
						int verticalChunkPos = finalPosY >> 4;

						if (chunkSection.getYLocation() >> 4 != verticalChunkPos) {
							chunkSection.unlock();
							chunkSection = chunkPrimer.getSection(verticalChunkPos);
							chunkSection.lock();
						}

						double yNoisePosRatio = (double)posYNoiseOffset / (double)this.verticalNoiseGranularity;
						double d6 = MathHelper.lerp(yNoisePosRatio, d16, d1);
						double d7 = MathHelper.lerp(yNoisePosRatio, d18, d3);
						double d8 = MathHelper.lerp(yNoisePosRatio, d17, d2);
						double d9 = MathHelper.lerp(yNoisePosRatio, d0, d4);

						for (int posXNoiseOffset = 0; posXNoiseOffset < this.horizontalNoiseGranularity; ++posXNoiseOffset) {
							int finalPosX = baseX + xNoiseMod * this.horizontalNoiseGranularity + posXNoiseOffset;
							int chunkRelativeXPos = finalPosX & 15;
							double xNoisePosRatio = (double)posXNoiseOffset / (double)this.horizontalNoiseGranularity;
							double d11 = MathHelper.lerp(xNoisePosRatio, d6, d7);
							double d12 = MathHelper.lerp(xNoisePosRatio, d8, d9);

							for (int posZNoiseOffset = 0; posZNoiseOffset < this.horizontalNoiseGranularity; ++posZNoiseOffset) {
								int finalPosZ = baseZ + zNoiseMod * this.horizontalNoiseGranularity + posZNoiseOffset;
								int chunkRelativeZPos = finalPosZ & 15;
								double zNoisePosRatio = (double)posZNoiseOffset / (double)this.horizontalNoiseGranularity;
								double d14 = MathHelper.lerp(zNoisePosRatio, d11, d12);
								double d15 = MathHelper.clamp(d14 / 200.0D, -1.0D, 1.0D);
								int structureXStart;
								int structureYStart;
								int structureZStart;

								for (d15 = d15 / 2.0D - d15 * d15 * d15 / 24.0D; villagePieceIterator.hasNext(); d15 += func_222556_a(structureXStart, structureYStart, structureZStart) * 0.8D) {
									AbstractVillagePiece curVillagePiece = villagePieceIterator.next();
									MutableBoundingBox villagePieceBoundingBox = curVillagePiece.getBoundingBox();
									structureXStart = Math.max(0, Math.max(villagePieceBoundingBox.minX - finalPosX, finalPosX - villagePieceBoundingBox.maxX));
									structureYStart = finalPosY - (villagePieceBoundingBox.minY + curVillagePiece.getGroundLevelDelta());
									structureZStart = Math.max(0, Math.max(villagePieceBoundingBox.minZ - finalPosZ, finalPosZ - villagePieceBoundingBox.maxZ));
								}

								villagePieceIterator.back(villagePieces.size());

								while (jigsawJunctionIterator.hasNext()) {
									JigsawJunction curJunction = jigsawJunctionIterator.next();
									int junctionXStart = finalPosX - curJunction.getSourceX();
									structureXStart = finalPosY - curJunction.getSourceGroundY();
									structureYStart = finalPosZ - curJunction.getSourceZ();
									d15 += func_222556_a(junctionXStart, structureXStart, structureYStart) * 0.4D;
								}

								jigsawJunctionIterator.back(jigsawJunctions.size());

								BlockState state = Blocks.AIR.getDefaultState();

								if (d15 > 0.0D) {
									if (finalPosY < settings.getCeilingHeight())
										state = this.defaultBlock;
								}
								else if (finalPosY < settings.getFloorHeight()) {
									state = this.defaultBlock;
								}
								else if (finalPosY < seaLevel) {
									if (finalPosY < settings.getCeilingHeight())
										state = this.defaultBlock;
								}

								if (state != Blocks.AIR.getDefaultState()) {
									mutablePos.setPos(finalPosX, finalPosY, finalPosZ);

									if (state.getLightValue(chunkPrimer, mutablePos) != 0)
										chunkPrimer.addLightPosition(mutablePos);

									chunkSection.setBlockState(chunkRelativeXPos, chunkRelativeYPos, chunkRelativeZPos, state, false);
									oceanFloorHeightmap.update(chunkRelativeXPos, finalPosY, chunkRelativeZPos, state);
									surfaceHeightmap.update(chunkRelativeXPos, finalPosY, chunkRelativeZPos, state);
								}
							}
						}
					}
				}

				chunkSection.unlock();
			}

			double[][] adouble1 = noiseMap[0];
			noiseMap[0] = noiseMap[1];
			noiseMap[1] = adouble1;
		}
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
				float heightVariation = -1 - biome.getScale();

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
					chunk.setBlockState(pos.setPos(x, 129 + y, z), dimFabric, false);
				}
			}
		}
	}

	@Override
	public int getGroundHeight() {
		return 64;
	}
}
