package net.tslat.aoa3.content.world.gen.feature.ore;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.OreFeature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.tslat.aoa3.library.object.PositionTableSet;

public class BrushableBlockOreFeature extends OreFeature {
	public BrushableBlockOreFeature(Codec<BrushableBlockOreFeature.Configuration> codec) {
		super((Codec)codec);
	}

	@Override
	protected boolean doPlace(WorldGenLevel level, RandomSource rand, OreConfiguration config, double minX, double maxX, double minZ, double maxZ, double minY, double maxY, int x, int y, int z, int width, int height) {
		final PositionTableSet placements = PositionTableSet.of(width, height, width);
		final BlockPos.MutableBlockPos testPos = new BlockPos.MutableBlockPos();
		final int size = config.size;
		final double[] blobPoints = new double[size * 4];
		int placeCount = 0;

		for(int i = 0; i < size; ++i) {
			final float step = (float)i / (float)size;
			final double blobSize = rand.nextDouble() * (double)size / 16;

			blobPoints[i * 4 + 0] = Mth.lerp(step, minX, maxX);
			blobPoints[i * 4 + 1] = Mth.lerp(step, minY, maxY);
			blobPoints[i * 4 + 2] = Mth.lerp(step, minZ, maxZ);
			blobPoints[i * 4 + 3] = ((double)(Mth.sin((float)Math.PI * step) + 1) * blobSize + 1) / 2d;
		}

		for(int i = 0; i < size - 1; ++i) {
			if (blobPoints[i * 4 + 3] <= 0)
				continue;

			for(int j = i + 1; j < size; ++j) {
				if (blobPoints[j * 4 + 3] <= 0)
					continue;

				double xSize = blobPoints[i * 4 + 0] - blobPoints[j * 4 + 0];
				double ySize = blobPoints[i * 4 + 1] - blobPoints[j * 4 + 1];
				double zSize = blobPoints[i * 4 + 2] - blobPoints[j * 4 + 2];
				double radius = blobPoints[i * 4 + 3] - blobPoints[j * 4 + 3];

				if (radius * radius > xSize * xSize + ySize * ySize + zSize * zSize)
					blobPoints[(radius > 0 ? j : i) * 4 + 3] = -1;
			}
		}

		for(int i = 0; i < size; ++i) {
			final double blobPointRadius = blobPoints[i * 4 + 3];

			if (blobPointRadius < 0)
				continue;

			final double blobPointX = blobPoints[i * 4 + 0];
			final double blobPointY = blobPoints[i * 4 + 1];
			final double blobPointZ = blobPoints[i * 4 + 2];
			final int blobMinX = Math.max(Mth.floor(blobPointX - blobPointRadius), x);
			final int blobMinY = Math.max(Mth.floor(blobPointY - blobPointRadius), y);
			final int blobMinZ = Math.max(Mth.floor(blobPointZ - blobPointRadius), z);
			final int blobMaxX = Math.max(Mth.floor(blobPointX + blobPointRadius), blobMinX);
			final int blobMaxY = Math.max(Mth.floor(blobPointY + blobPointRadius), blobMinY);
			final int blobMaxZ = Math.max(Mth.floor(blobPointZ + blobPointRadius), blobMinZ);

			for(int oreX = blobMinX; oreX <= blobMaxX; ++oreX) {
				final double oreXRadius = ((double)oreX + 0.5D - blobPointX) / blobPointRadius;

				if (oreXRadius * oreXRadius >= 1)
					continue;

				for(int oreY = blobMinY; oreY <= blobMaxY; ++oreY) {
					final double oreYRadius = ((double)oreY + 0.5D - blobPointY) / blobPointRadius;

					if (oreXRadius * oreXRadius + oreYRadius * oreYRadius >= 1)
						continue;

					for(int oreZ = blobMinZ; oreZ <= blobMaxZ; ++oreZ) {
						final double oreZRadius = ((double)oreZ + 0.5D - blobPointZ) / blobPointRadius;

						if (oreXRadius * oreXRadius + oreYRadius * oreYRadius + oreZRadius * oreZRadius >= 1 || level.isOutsideBuildHeight(oreY))
							continue;

						if (!placements.isFilledAt(oreX - x, oreY - y, oreZ - z)) {
							placements.add(oreX - x, oreY - y, oreZ - z);
							testPos.set(oreX, oreY, oreZ);

							if (level.ensureCanWrite(testPos)) {
								final BlockState preOreState = level.getBlockState(testPos);

								for(OreConfiguration.TargetBlockState targetState : config.targetStates) {
									if (canPlaceOre(preOreState, level::getBlockState, rand, config, targetState, testPos)) {
										level.setBlock(testPos, targetState.state, Block.UPDATE_CLIENTS);
										level.getBlockEntity(testPos, BlockEntityType.BRUSHABLE_BLOCK).ifPresent(brushable ->
												brushable.setLootTable(((BrushableBlockOreFeature.Configuration)config).lootTable(), testPos.asLong()));

										++placeCount;

										break;
									}
								}
							}
						}
					}
				}
			}
		}

		return placeCount > 0;
	}

	public static class Configuration extends OreConfiguration {
		public static final Codec<Configuration> CODEC = RecordCodecBuilder.create(builder ->
				builder.group(OreConfiguration.CODEC.fieldOf("ore_config").forGetter(config -> config))
						.and(ResourceLocation.CODEC.fieldOf("loot_table").forGetter(Configuration::lootTable))
						.apply(builder, Configuration::new));

		public final ResourceLocation lootTable;

		public Configuration(OreConfiguration oreConfig, ResourceLocation lootTable) {
			super(oreConfig.targetStates, oreConfig.size, oreConfig.discardChanceOnAirExposure);

			this.lootTable = lootTable;
		}

		public ResourceLocation lootTable() {
			return this.lootTable;
		}
	}
}
