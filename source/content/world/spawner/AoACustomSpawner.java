package net.tslat.aoa3.content.world.spawner;

import com.mojang.serialization.Codec;
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
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.CustomSpawner;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.SpawnData;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.Heightmap;
import net.tslat.smartbrainlib.util.RandomUtil;

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
			Biome.LIST_CODEC.optionalFieldOf("biomes").forGetter(GenericSettings::biomeList),
			ResourceKey.codec(Registries.DIMENSION).listOf().xmap(Set::copyOf, List::copyOf).fieldOf("dimensions").forGetter(GenericSettings::dimensions),
			Codec.BOOL.fieldOf("whitelist_mode").forGetter(GenericSettings::whitelistMode),
			SpawnData.CustomSpawnRules.CODEC.optionalFieldOf("spawn_rules").forGetter(GenericSettings::spawnRules),
			Codec.BOOL.fieldOf("spawn_in_flat_world").forGetter(GenericSettings::spawnInSuperflat)
	).apply(builder, GenericSettings::new));

	boolean shouldAddToDimension(ServerLevel level);
	AoACustomSpawner copy();
	Type getType();

	default Heightmap.Types getHeightmapForSpawn(EntityType<E> entityType, ServerLevel level, RandomSource random, BlockPos pos) {
		return SpawnPlacements.getHeightmapType(entityType);
	}

	default SpawnPlacements.Type getSpawnPlacementTypeForSpawn(EntityType<E> entityType, ServerLevel level, RandomSource random, BlockPos pos) {
		return SpawnPlacements.getPlacementType(entityType);
	}

	default boolean canSpawnAt(EntityType<E> entityType, ServerLevel level, RandomSource random, BlockPos pos, SpawnPlacements.Type spawnPlacement) {
		return NaturalSpawner.isSpawnPositionOk(spawnPlacement, level, pos, entityType) && level.noCollision(entityType.getAABB(pos.getX() + 0.5d, pos.getY(), pos.getZ() + 0.5d));
	}

	default List<Pair<EntityType<E>, BlockPos>> findNearbySpawnPositions(ServerLevel level, RandomSource random, BlockPos centerPos, int minRadius, int maxRadius, int maxTries, Supplier<Optional<EntityType<E>>> entityTypeSupplier) {
		final List<Pair<EntityType<E>, BlockPos>> positions = new ObjectArrayList<>();
		final RandomUtil.EasyRandom rand = new RandomUtil.EasyRandom(random);
		final BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();
		final float radius = Math.max(maxRadius - minRadius, 0);

		for (int i = 0; i < maxTries; i++) {
			entityTypeSupplier.get().ifPresent(entityType -> {
				double xAdjust = rand.randomValueBetween(-radius, radius);
				double zAdjust = rand.randomValueBetween(-radius, radius);
				int newX = (int)Math.floor(centerPos.getX() + xAdjust + radius * Math.signum(xAdjust));
				int newZ = (int)Math.floor(centerPos.getZ() + zAdjust + radius * Math.signum(zAdjust));

				if (level.dimensionType().hasCeiling()) {
					mutablePos.set(newX, Mth.randomBetweenInclusive(random, level.getMinBuildHeight(), level.getHeight(Heightmap.Types.WORLD_SURFACE, newX, newZ)), newZ);

					while (!level.getBlockState(mutablePos.move(Direction.DOWN)).isAir()) {}
				}
				else {
					mutablePos.set(level.getHeightmapPos(getHeightmapForSpawn(entityType, level, random, mutablePos.set(newX, 0, newZ)), mutablePos));
				}

				SpawnPlacements.Type spawnPlacement = getSpawnPlacementTypeForSpawn(entityType, level, random, mutablePos);

				if (spawnPlacement == SpawnPlacements.Type.ON_GROUND)
					while (level.getBlockState(mutablePos.move(Direction.DOWN)).isAir() && mutablePos.getY() > level.getMinBuildHeight()) {}

				mutablePos.move(Direction.UP);

				if (canSpawnAt(entityType, level, random, mutablePos, spawnPlacement))
					positions.add(Pair.of(entityType, mutablePos.immutable()));
			});
		}

		return positions;
	}

	record Type(Codec<? extends AoACustomSpawner> codec) {}
	record GenericSettings(IntProvider spawnInterval, IntProvider extraDelayPerSpawn, float chancePerPlayer, IntProvider spawnAttemptsPerPlayer, Optional<HolderSet<Biome>> biomeList, Set<ResourceKey<Level>> dimensions, boolean whitelistMode, Optional<SpawnData.CustomSpawnRules> spawnRules, boolean spawnInSuperflat) {}
}
