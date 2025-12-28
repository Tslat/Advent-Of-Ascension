package net.tslat.aoa3.content.world.spawner;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.Pair;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.CustomSpawner;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.SpawnData;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.Heightmap;
import net.tslat.tme.api.object.EasyRandom;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;

public interface AoACustomSpawner<E extends Entity> extends CustomSpawner {
	Codec<GenericSettings> GENERIC_SETTINGS_CODEC = RecordCodecBuilder.create(builder -> builder.group(
			IntProvider.CODEC.fieldOf("spawn_interval").forGetter(GenericSettings::spawnInterval),
			IntProvider.CODEC.fieldOf("extra_delay_per_spawn").forGetter(GenericSettings::extraDelayPerSpawn),
			Codec.FLOAT.fieldOf("chance_per_player").forGetter(GenericSettings::chancePerPlayer),
			IntProvider.CODEC.fieldOf("spawn_attempts_per_player").forGetter(GenericSettings::spawnAttemptsPerPlayer),
			Biome.LIST_CODEC.optionalFieldOf("in_biomes").forGetter(GenericSettings::inBiomes),
			Biome.LIST_CODEC.optionalFieldOf("not_in_biomes").forGetter(GenericSettings::notInBiomes),
			ResourceKey.codec(Registries.DIMENSION).listOf().xmap(Set::copyOf, List::copyOf).optionalFieldOf("in_dimensions").forGetter(GenericSettings::inDimensions),
			ResourceKey.codec(Registries.DIMENSION).listOf().xmap(Set::copyOf, List::copyOf).optionalFieldOf("not_in_dimensions").forGetter(GenericSettings::notInDimensions),
			SpawnData.CustomSpawnRules.CODEC.optionalFieldOf("spawn_rules").forGetter(GenericSettings::spawnRules),
			Codec.BOOL.fieldOf("spawn_in_flat_world").forGetter(GenericSettings::spawnInSuperflat)
	).apply(builder, GenericSettings::new));

	boolean shouldAddToDimension(ServerLevel level);
	AoACustomSpawner<E> copy();
	Type<E> getType();

	default Heightmap.Types getHeightmapForSpawn(EntityType<E> entityType, ServerLevel level, RandomSource random, BlockPos pos) {
		return SpawnPlacements.getHeightmapType(entityType);
	}

	default SpawnPlacementType getSpawnPlacementTypeForSpawn(EntityType<E> entityType, ServerLevel level, RandomSource random, BlockPos pos) {
		return SpawnPlacements.getPlacementType(entityType);
	}

	default boolean canSpawnAt(EntityType<E> entityType, ServerLevel level, RandomSource random, BlockPos pos, SpawnPlacementType spawnPlacement) {
		return canSpawnInBiome(level, pos) && NaturalSpawner.isValidEmptySpawnBlock(level, pos, level.getBlockState(pos), level.getFluidState(pos), entityType) && level.noCollision(entityType.getSpawnAABB(pos.getX() + 0.5d, pos.getY(), pos.getZ() + 0.5d));
	}

	default boolean canSpawnInBiome(ServerLevel level, BlockPos pos) {
		return true;
	}

	default List<Pair<EntityType<E>, BlockPos>> findNearbySpawnPositions(ServerLevel level, RandomSource random, BlockPos centerPos, int minRadius, int maxRadius, int maxTries, Supplier<Optional<EntityType<E>>> entityTypeSupplier) {
		final List<Pair<EntityType<E>, BlockPos>> positions = new ObjectArrayList<>();
		final EasyRandom rand = EasyRandom.wrap(random);
		final BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();
		final float radius = Math.max(maxRadius - minRadius, 0);

		for (int i = 0; i < maxTries; i++) {
			entityTypeSupplier.get().ifPresent(entityType -> {
				double xAdjust = rand.valueBetween(-radius, radius);
				double zAdjust = rand.valueBetween(-radius, radius);
				int newX = (int)Math.floor(centerPos.getX() + xAdjust + radius * Math.signum(xAdjust));
				int newZ = (int)Math.floor(centerPos.getZ() + zAdjust + radius * Math.signum(zAdjust));

				if (level.dimensionType().hasCeiling()) {
					mutablePos.set(newX, Mth.randomBetweenInclusive(random, level.getMinBuildHeight(), level.getHeight(Heightmap.Types.WORLD_SURFACE, newX, newZ)), newZ);

					while (!level.getBlockState(mutablePos.move(Direction.DOWN)).isAir());
				}
				else {
					mutablePos.set(level.getHeightmapPos(getHeightmapForSpawn(entityType, level, random, mutablePos.set(newX, 0, newZ)), mutablePos));
				}

				SpawnPlacementType spawnPlacement = getSpawnPlacementTypeForSpawn(entityType, level, random, mutablePos);

				if (spawnPlacement == SpawnPlacementTypes.ON_GROUND)
					while (level.getBlockState(mutablePos.move(Direction.DOWN)).isAir() && mutablePos.getY() > level.getMinBuildHeight());

				mutablePos.move(Direction.UP);

				if (canSpawnAt(entityType, level, random, mutablePos, spawnPlacement))
					positions.add(Pair.of(entityType, mutablePos.immutable()));
			});
		}

		return positions;
	}

	record Type<E extends Entity>(MapCodec<? extends AoACustomSpawner<E>> codec) {}
	record GenericSettings(IntProvider spawnInterval, IntProvider extraDelayPerSpawn, float chancePerPlayer, IntProvider spawnAttemptsPerPlayer, Optional<HolderSet<Biome>> inBiomes, Optional<HolderSet<Biome>> notInBiomes, Optional<Set<ResourceKey<Level>>> inDimensions, Optional<Set<ResourceKey<Level>>> notInDimensions, Optional<SpawnData.CustomSpawnRules> spawnRules, boolean spawnInSuperflat) {}
}
