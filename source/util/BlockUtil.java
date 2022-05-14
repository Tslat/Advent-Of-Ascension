package net.tslat.aoa3.util;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.tags.TagKey;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.SpawnData;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.ToIntFunction;

public final class BlockUtil {
	public static final float UNBREAKABLE_HARDNESS = -1f;
	public static final float UNBREAKABLE_RESISTANCE = 999999999f;

	public static class CompactProperties {
		private final Block.Properties properties;

		public CompactProperties(Material material, Function<BlockState, MaterialColor> mapColours) {
			this.properties = Block.Properties.of(material, mapColours).sound(approximateSound(material));
		}

		public CompactProperties(Material material, MaterialColor mapColour) {
			this(material, state -> mapColour);
		}

		public CompactProperties needsTool() {
			this.properties.requiresCorrectToolForDrops();

			return this;
		}

		public CompactProperties sound(SoundType sound) {
			this.properties.sound(sound);

			return this;
		}

		public CompactProperties stats(float hardness) {
			return stats(hardness, hardness);
		}

		public CompactProperties stats(float hardness, float resistance) {
			this.properties.strength(hardness, resistance);

			return this;
		}

		public CompactProperties unbreakable() {
			noDrops();

			return stats(UNBREAKABLE_HARDNESS, UNBREAKABLE_RESISTANCE);
		}

		public CompactProperties light(int light) {
			return light(state -> light);
		}

		public CompactProperties light(ToIntFunction<BlockState> light) {
			this.properties.lightLevel(light);

			return this;
		}

		public CompactProperties randomTicks() {
			this.properties.randomTicks();

			return this;
		}

		public CompactProperties isAir() {
			this.properties.air();

			return this;
		}

		public CompactProperties dynamicShape() {
			this.properties.dynamicShape();

			return this;
		}

		public CompactProperties slippery(float slipMod) {
			this.properties.friction(slipMod);

			return this;
		}

		public CompactProperties emissive() {
			return emissive((state, world, pos) -> true);
		}

		public CompactProperties emissive(BlockBehaviour.StatePredicate when) {
			this.properties.emissiveRendering(when);

			return this;
		}

		public CompactProperties renderAdjust() {
			return renderAdjust((state, world, pos) -> true);
		}

		public CompactProperties renderAdjust(BlockBehaviour.StatePredicate when) {
			this.properties.hasPostProcess(when);

			return this;
		}

		public CompactProperties moveSpeed(float speedMod) {
			this.properties.speedFactor(speedMod);

			return this;
		}

		public CompactProperties bouncy(float jumpFactor) {
			this.properties.jumpFactor(jumpFactor);

			return this;
		}

		public CompactProperties noDrops() {
			this.properties.noDrops();

			return this;
		}

		public CompactProperties noOcclusion() {
			this.properties.noOcclusion();

			return this;
		}

		public CompactProperties noClip() {
			this.properties.noCollission();
			coversScreen((state, world, pos) -> false);

			return this;
		}

		public CompactProperties noScreenCover() {
			return coversScreen((state, world, pos) -> false);
		}

		public CompactProperties coversScreen(BlockBehaviour.StatePredicate when) {
			this.properties.isViewBlocking(when);

			return this;
		}

		public CompactProperties noRedstone() {
			return conductRedstone((state, world, pos) -> false);
		}

		public CompactProperties conductRedstone(BlockBehaviour.StatePredicate when) {
			this.properties.isRedstoneConductor(when);

			return this;
		}

		public CompactProperties noSpawns() {
			return specialSpawns((state, world, pos, entityType) -> false);
		}

		public CompactProperties specialSpawns(BlockBehaviour.StateArgumentPredicate<EntityType<?>> when) {
			this.properties.isValidSpawn(when);

			return this;
		}

		public CompactProperties breathable() {
			return suffocate((state, world, pos) -> false);
		}

		public CompactProperties suffocate(BlockBehaviour.StatePredicate when) {
			this.properties.isSuffocating(when);

			return this;
		}

		public Block.Properties get() {
			return this.properties;
		}

		private static SoundType approximateSound(Material material) {
			if (material == Material.WOOD) {
				return SoundType.WOOD;
			}
			else if (material == Material.GLASS) {
				return SoundType.GLASS;
			}
			else if (material == Material.DIRT) {
				return SoundType.GRAVEL;
			}
			else if (material == Material.PLANT || material == Material.GRASS) {
				return SoundType.GRASS;
			}
			else if (material == Material.TOP_SNOW || material == Material.SNOW) {
				return SoundType.SNOW;
			}
			else if (material == Material.SAND) {
				return SoundType.SAND;
			}
			else if (material == Material.WOOL) {
				return SoundType.WOOL;
			}
			else if (material == Material.METAL || material == Material.HEAVY_METAL) {
				return SoundType.METAL;
			}
			else {
				return SoundType.STONE;
			}
		}
	}

	public static Vec3 posToVec(BlockPos pos) {
		return new Vec3(pos.getX(), pos.getY(), pos.getZ());
	}

	public static boolean isBlockTaggedAs(Block block, TagKey<Block>... tags) {
		for (TagKey<Block> tag : tags) {
			if (block.builtInRegistryHolder().is(tag))
				return true;
		}

		return false;
	}

	public static boolean isAirBlock(BlockState state) {
		return state.getMaterial() == Material.AIR || state.getMaterial() == Material.STRUCTURAL_AIR;
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

			tag.putString("id", entity.getRegistryName().toString());

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
