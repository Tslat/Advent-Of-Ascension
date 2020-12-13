package net.tslat.aoa3.worldgen.features;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.LightType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkStatus;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.LakesFeature;

import java.util.Random;
import java.util.function.Function;

public class BiomeFriendlyLakeFeature extends LakesFeature {
	public BiomeFriendlyLakeFeature(Function<Dynamic<?>, ? extends BlockStateFeatureConfig> configFunction) {
		super(configFunction);
	}

	@Override
	public boolean place(IWorld world, ChunkGenerator<? extends GenerationSettings> chunkGenerator, Random rand, BlockPos basePos, BlockStateFeatureConfig config) {
		while(basePos.getY() > 5 && world.isAirBlock(basePos)) {
			basePos = basePos.down();
		}

		if (basePos.getY() <= 4) {
			return false;
		}
		else {
			basePos = basePos.down(4);
			ChunkPos chunkPos = new ChunkPos(basePos);

			if (!world.getChunk(chunkPos.x, chunkPos.z, ChunkStatus.STRUCTURE_REFERENCES).getStructureReferences(Feature.VILLAGE.getStructureName()).isEmpty()) {
				return false;
			}
			else {
				boolean[] positions = new boolean[2048];
				int i = rand.nextInt(4) + 4;

				for(int j = 0; j < i; ++j) {
					double radiusX = rand.nextDouble() * 6.0D + 3.0D;
					double radiusY = rand.nextDouble() * 4.0D + 2.0D;
					double radiusZ = rand.nextDouble() * 6.0D + 3.0D;
					double centerX = rand.nextDouble() * (16.0D - radiusX - 2.0D) + 1.0D + radiusX / 2.0D;
					double centerY = rand.nextDouble() * (8.0D - radiusY - 4.0D) + 2.0D + radiusY / 2.0D;
					double centerZ = rand.nextDouble() * (16.0D - radiusZ - 2.0D) + 1.0D + radiusZ / 2.0D;

					for(int x = 1; x < 15; ++x) {
						for(int z = 1; z < 15; ++z) {
							for(int y = 1; y < 7; ++y) {
								double distX = ((double)x - centerX) / (radiusX / 2.0D);
								double distY = ((double)y - centerY) / (radiusY / 2.0D);
								double distZ = ((double)z - centerZ) / (radiusZ / 2.0D);
								double dist = distX * distX + distY * distY + distZ * distZ;

								if (dist < 1.0D)
									positions[(x * 16 + z) * 8 + y] = true;
							}
						}
					}
				}

				for(int x = 0; x < 16; ++x) {
					for(int z = 0; z < 16; ++z) {
						for(int y = 0; y < 8; ++y) {
							boolean shouldPlace = !positions[(x * 16 + z) * 8 + y] && (x < 15 && positions[((x + 1) * 16 + z) * 8 + y] || x > 0 && positions[((x - 1) * 16 + z) * 8 + y] || z < 15 && positions[(x * 16 + z + 1) * 8 + y] || z > 0 && positions[(x * 16 + (z - 1)) * 8 + y] || y < 7 && positions[(x * 16 + z) * 8 + y + 1] || y > 0 && positions[(x * 16 + z) * 8 + (y - 1)]);

							if (shouldPlace) {
								Material material = world.getBlockState(basePos.add(x, y, z)).getMaterial();

								if (y >= 4 && material.isLiquid())
									return false;

								if (y < 4 && !material.isSolid() && world.getBlockState(basePos.add(x, y, z)) != config.state)
									return false;
							}
						}
					}
				}

				for(int x = 0; x < 16; ++x) {
					for(int z = 0; z < 16; ++z) {
						for(int y = 0; y < 8; ++y) {
							if (positions[(x * 16 + z) * 8 + y])
								world.setBlockState(basePos.add(x, y, z), y >= 4 ? Blocks.AIR.getDefaultState() : config.state, 2);
						}
					}
				}

				for(int x = 0; x < 16; ++x) {
					for(int z = 0; z < 16; ++z) {
						for(int y = 4; y < 8; ++y) {
							if (positions[(x * 16 + z) * 8 + y]) {
								BlockPos grassPos = basePos.add(x, y - 1, z);

								if (isDirt(world.getBlockState(grassPos).getBlock()) && world.getLightFor(LightType.SKY, basePos.add(x, y, z)) > 0) {
									Biome biome = world.getBiome(grassPos);

									world.setBlockState(grassPos, biome.getSurfaceBuilderConfig().getTop(), 2);
								}
							}
						}
					}
				}

				if (config.state.getMaterial() == Material.LAVA) {
					for(int x = 0; x < 16; ++x) {
						for(int z = 0; z < 16; ++z) {
							for(int y = 0; y < 8; ++y) {
								boolean shouldPlace = !positions[(x * 16 + z) * 8 + y] && (x < 15 && positions[((x + 1) * 16 + z) * 8 + y] || x > 0 && positions[((x - 1) * 16 + z) * 8 + y] || z < 15 && positions[(x * 16 + z + 1) * 8 + y] || z > 0 && positions[(x * 16 + (z - 1)) * 8 + y] || y < 7 && positions[(x * 16 + z) * 8 + y + 1] || y > 0 && positions[(x * 16 + z) * 8 + (y - 1)]);

								if (shouldPlace && (y < 4 || rand.nextBoolean()) && world.getBlockState(basePos.add(x, y, z)).getMaterial().isSolid())
									world.setBlockState(basePos.add(x, y, z), Blocks.STONE.getDefaultState(), 2);
							}
						}
					}
				}
				else if (config.state.getMaterial() == Material.WATER) {
					for(int x = 0; x < 16; ++x) {
						for(int z = 0; z < 16; ++z) {
							BlockPos icePos = basePos.add(x, 4, z);

							if (world.getBiome(icePos).doesWaterFreeze(world, icePos, false))
								world.setBlockState(icePos, Blocks.ICE.getDefaultState(), 2);
						}
					}
				}

				return true;
			}
		}
	}
}
