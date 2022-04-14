/*
package net.tslat.aoa3.content.world.gen.feature.features;

import com.mojang.serialization.Codec;
import com.sun.jna.Structure;
import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraft.world.level.material.Material;
import net.tslat.aoa3.content.world.gen.feature.placement.config.BlockStatePlacementConfig;

import java.util.Random;

public class BiomeFriendlyLake extends LakeFeature {
	private BlockState dimensionFillerBlock = null;

	public BiomeFriendlyLake(Codec<LakeFeature.Configuration> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeaturePlaceContext<Configuration> p_159958_) {
		while(pos.getY() > 5 && reader.isEmptyBlock(pos)) {
			pos = pos.below();
		}

		if (pos.getY() <= 4) {
			return false;
		}
		else {
			pos = pos.below(4);

			if (reader.startsForFeature(SectionPos.of(pos), Structure.VILLAGE).findAny().isPresent()) {
				return false;
			}
			else {
				boolean[] carvingBitMask = new boolean[2048];
				int attempts = rand.nextInt(4) + 4;

				for(int j = 0; j < attempts; ++j) {
					double diamX = rand.nextDouble() * 6.0D + 3.0D;
					double diamY = rand.nextDouble() * 4.0D + 2.0D;
					double diamZ = rand.nextDouble() * 6.0D + 3.0D;
					double offsetX = rand.nextDouble() * (16.0D - diamX - 2.0D) + 1.0D + diamX / 2.0D;
					double offsetY = rand.nextDouble() * (8.0D - diamY - 4.0D) + 2.0D + diamY / 2.0D;
					double offsetZ = rand.nextDouble() * (16.0D - diamZ - 2.0D) + 1.0D + diamZ / 2.0D;

					for(int chunkRelX = 1; chunkRelX < 15; ++chunkRelX) {
						for(int chunkRelZ = 1; chunkRelZ < 15; ++chunkRelZ) {
							for(int chunkRelY = 1; chunkRelY < 7; ++chunkRelY) {
								double ratioX = ((double)chunkRelX - offsetX) / (diamX / 2.0D);
								double ratioY = ((double)chunkRelY - offsetY) / (diamY / 2.0D);
								double ratioZ = ((double)chunkRelZ - offsetZ) / (diamZ / 2.0D);
								double distSq = ratioX * ratioX + ratioY * ratioY + ratioZ * ratioZ;

								if (distSq < 1.0D)
									carvingBitMask[(chunkRelX * 16 + chunkRelZ) * 8 + chunkRelY] = true;
							}
						}
					}
				}

				for(int chunkRelX = 0; chunkRelX < 16; ++chunkRelX) {
					for(int chunkRelZ = 0; chunkRelZ < 16; ++chunkRelZ) {
						for(int chunkRelY = 0; chunkRelY < 8; ++chunkRelY) {
							boolean carvingBoundary = !carvingBitMask[(chunkRelX * 16 + chunkRelZ) * 8 + chunkRelY] && (chunkRelX < 15 && carvingBitMask[((chunkRelX + 1) * 16 + chunkRelZ) * 8 + chunkRelY] || chunkRelX > 0 && carvingBitMask[((chunkRelX - 1) * 16 + chunkRelZ) * 8 + chunkRelY] || chunkRelZ < 15 && carvingBitMask[(chunkRelX * 16 + chunkRelZ + 1) * 8 + chunkRelY] || chunkRelZ > 0 && carvingBitMask[(chunkRelX * 16 + (chunkRelZ - 1)) * 8 + chunkRelY] || chunkRelY < 7 && carvingBitMask[(chunkRelX * 16 + chunkRelZ) * 8 + chunkRelY + 1] || chunkRelY > 0 && carvingBitMask[(chunkRelX * 16 + chunkRelZ) * 8 + (chunkRelY - 1)]);

							if (carvingBoundary) {
								Material material = reader.getBlockState(pos.offset(chunkRelX, chunkRelY, chunkRelZ)).getMaterial();

								if (chunkRelY >= 4 && material.isLiquid())
									return false;

								if (chunkRelY < 4 && !material.isSolid() && reader.getBlockState(pos.offset(chunkRelX, chunkRelY, chunkRelZ)) != config.state)
									return false;
							}
						}
					}
				}

				for(int chunkRelX = 0; chunkRelX < 16; ++chunkRelX) {
					for(int chunkRelZ = 0; chunkRelZ < 16; ++chunkRelZ) {
						for(int chunkRelY = 0; chunkRelY < 8; ++chunkRelY) {
							if (carvingBitMask[(chunkRelX * 16 + chunkRelZ) * 8 + chunkRelY])
								reader.setBlock(pos.offset(chunkRelX, chunkRelY, chunkRelZ), chunkRelY >= 4 ? Blocks.CAVE_AIR.defaultBlockState() : config.state, 2);
						}
					}
				}

				for(int chunkRelX = 0; chunkRelX < 16; ++chunkRelX) {
					for(int chunkRelZ = 0; chunkRelZ < 16; ++chunkRelZ) {
						for(int chunkRelY = 4; chunkRelY < 8; ++chunkRelY) {
							if (carvingBitMask[(chunkRelX * 16 + chunkRelZ) * 8 + chunkRelY]) {
								BlockPos placementPos = pos.offset(chunkRelX, chunkRelY - 1, chunkRelZ);
								ISurfaceBuilderConfig surfaceBuilderConfig = reader.getBiome(placementPos).getGenerationSettings().getSurfaceBuilderConfig();

								if (reader.getBlockState(placementPos).getBlock() == surfaceBuilderConfig.getUnderMaterial().getBlock() && reader.getBrightness(LightLayer.SKY, pos.offset(chunkRelX, chunkRelY, chunkRelZ)) > 0)
									reader.setBlock(placementPos, surfaceBuilderConfig.getTopMaterial(), 2);
							}
						}
					}
				}

				if (config.state.getMaterial() == Material.LAVA) {
					for(int chunkRelX = 0; chunkRelX < 16; ++chunkRelX) {
						for(int chunkRelZ = 0; chunkRelZ < 16; ++chunkRelZ) {
							for(int chunkRelY = 0; chunkRelY < 8; ++chunkRelY) {
								boolean carvingBoundary = !carvingBitMask[(chunkRelX * 16 + chunkRelZ) * 8 + chunkRelY] && (chunkRelX < 15 && carvingBitMask[((chunkRelX + 1) * 16 + chunkRelZ) * 8 + chunkRelY] || chunkRelX > 0 && carvingBitMask[((chunkRelX - 1) * 16 + chunkRelZ) * 8 + chunkRelY] || chunkRelZ < 15 && carvingBitMask[(chunkRelX * 16 + chunkRelZ + 1) * 8 + chunkRelY] || chunkRelZ > 0 && carvingBitMask[(chunkRelX * 16 + (chunkRelZ - 1)) * 8 + chunkRelY] || chunkRelY < 7 && carvingBitMask[(chunkRelX * 16 + chunkRelZ) * 8 + chunkRelY + 1] || chunkRelY > 0 && carvingBitMask[(chunkRelX * 16 + chunkRelZ) * 8 + (chunkRelY - 1)]);
								BlockPos placementPos = pos.offset(chunkRelX, chunkRelY, chunkRelZ);

								if (carvingBoundary && (chunkRelY < 4 || rand.nextBoolean()) && reader.getBlockState(placementPos).getMaterial().isSolid())
									reader.setBlock(placementPos, getFillerBlockState(reader, generator, placementPos), 2);
							}
						}
					}
				}

				if (config.state.getMaterial() == Material.WATER) {
					for(int chunkRelX = 0; chunkRelX < 16; ++chunkRelX) {
						for(int chunkRelZ = 0; chunkRelZ < 16; ++chunkRelZ) {
							BlockPos placementPos = pos.offset(chunkRelX, 4, chunkRelZ);

							if (reader.getBiome(placementPos).shouldFreeze(reader, placementPos, false))
								reader.setBlock(placementPos, Blocks.ICE.defaultBlockState(), 2);
						}
					}
				}

				return true;
			}
		}
	}

	private BlockState getFillerBlockState(LevelReader reader, ChunkGenerator generator, BlockPos pos) {
		if (dimensionFillerBlock != null)
			return dimensionFillerBlock;

		if (generator instanceof NoiseBasedChunkGenerator) {
			dimensionFillerBlock = ((NoiseBasedChunkGenerator)generator).settings.get().getDefaultBlock();

			return dimensionFillerBlock;
		}

		dimensionFillerBlock = reader.getBiome(pos).value().getGenerationSettings().getSurfaceBuilderConfig().getUnderMaterial();

		return dimensionFillerBlock;
	}
}
*/
