package net.tslat.aoa3.util;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.SpawnData;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.util.Optional;

public final class BlockUtil {
	public static final float UNBREAKABLE_HARDNESS = -1f;
	public static final float UNBREAKABLE_RESISTANCE = 999999999f;

	public static Vec3 posToVec(BlockPos pos) {
		return new Vec3(pos.getX(), pos.getY(), pos.getZ());
	}

	public static class SpawnerBuilder {
		private short initialSpawnDelay = 20;
		private short minSpawnDelay = 200;
		private short maxSpawnDelay = 800;

		private short spawnsPerGroup = 4;
		private short maxNearbyEntities = 6;
		private short requiredPlayerRange = 16;
		private short spawnRange = 4;

		private SpawnData nextSpawn = null;
		private final SimpleWeightedRandomList.Builder<SpawnData> mobs = SimpleWeightedRandomList.builder();

		public SpawnerBuilder initialSpawnDelay(int ticks) {
			this.initialSpawnDelay = (short)ticks;

			return this;
		}

		public SpawnerBuilder minSpawnDelay(int ticks) {
			this.minSpawnDelay = (short)ticks;

			return this;
		}

		public SpawnerBuilder maxSpawnDelay(int ticks) {
			this.maxSpawnDelay = (short)ticks;

			return this;
		}

		public SpawnerBuilder spawnsPerGroup(int amount) {
			this.spawnsPerGroup = (short)amount;

			return this;
		}

		public SpawnerBuilder maxNearbyEntities(int amount) {
			this.maxNearbyEntities = (short)amount;

			return this;
		}

		public SpawnerBuilder whenPlayerWithin(int distance) {
			this.requiredPlayerRange = (short)distance;

			return this;
		}

		public SpawnerBuilder spawnWithinXBlocks(int distance) {
			this.spawnRange = (short)distance;

			return this;
		}

		public SpawnerBuilder withSpawns(EntityType<?>... entities) {
			for (EntityType<?> entity : entities) {
				withSpawn(1, entity);
			}

			return this;
		}

		public SpawnerBuilder withSpawns(RegistryObject<EntityType<?>>... entities) {
			for (RegistryObject<EntityType<?>> registryEntry : entities) {
				withSpawn(1, registryEntry.get());
			}

			return this;
		}

		public SpawnerBuilder withSpawn(int weight, EntityType<?> entity) {
			return withSpawn(weight, entity, null);
		}

		public SpawnerBuilder withSpawn(int weight, EntityType<?> entity, SpawnData.CustomSpawnRules spawnRules) {
			CompoundTag tag = new CompoundTag();

			tag.putString("id", ForgeRegistries.ENTITY_TYPES.getKey(entity).toString());

			return withSpawn(weight, tag, spawnRules);
		}

		public SpawnerBuilder withSpawn(int weight, CompoundTag nbt, @Nullable SpawnData.CustomSpawnRules spawnRules) {
			if (this.nextSpawn == null) {
				this.nextSpawn = new SpawnData(nbt, (spawnRules == null ? Optional.empty() : Optional.of(spawnRules)));

				return this;
			}

			this.mobs.add(new SpawnData(nbt, (spawnRules == null ? Optional.empty() : Optional.of(spawnRules))), weight);

			return this;
		}

		public CompoundTag build() {
			CompoundTag nbt = new CompoundTag();

			if (nextSpawn == null)
				throw new IllegalStateException("Attempted to create spawner data with no mobs listed.");

			nbt.putShort("Delay", this.initialSpawnDelay);
			nbt.putShort("MinSpawnDelay", this.minSpawnDelay);
			nbt.putShort("MaxSpawnDelay", this.maxSpawnDelay);
			nbt.putShort("SpawnCount", this.spawnsPerGroup);
			nbt.putShort("MaxNearbyEntities", this.maxNearbyEntities);
			nbt.putShort("RequiredPlayerRange", this.requiredPlayerRange);
			nbt.putShort("SpawnRange", this.spawnRange);
			nbt.put("SpawnData", SpawnData.CODEC.encodeStart(NbtOps.INSTANCE, this.nextSpawn).result().orElseThrow(() -> new IllegalStateException("Invalid SpawnData")));
			nbt.put("SpawnPotentials", SpawnData.LIST_CODEC.encodeStart(NbtOps.INSTANCE, this.mobs.build()).result().orElseThrow());

			return nbt;
		}
	}

	public static VoxelShape pixelBasedCube(int minPixelX, int minPixelY, int minPixelZ, int maxPixelX, int maxPixelY, int maxPixelZ) {
		return Shapes.create(new AABB(minPixelX / 16d, minPixelY / 16d, minPixelZ / 16d, maxPixelX / 16d, maxPixelY / 16d, maxPixelZ / 16d));
	}

	public static boolean canPlayerHarvest(BlockState state, Player player, LevelAccessor world, BlockPos pos) {
		if (!state.requiresCorrectToolForDrops())
			return true;

		return player.getMainHandItem().isCorrectToolForDrops(state);
	}
}
