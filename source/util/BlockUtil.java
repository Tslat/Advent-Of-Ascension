package net.tslat.aoa3.util;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.WeightedSpawnerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.library.misc.MutableSupplier;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public abstract class BlockUtil {
	public static final float UNBREAKABLE_HARDNESS = -1f;
	public static final float UNBREAKABLE_RESISTANCE = 999999999f;

	public static AbstractBlock.Properties generateBlockProperties(Material material, MaterialColor mapColour, float hardness, float resistance) {
		return generateBlockProperties(material, mapColour, hardness, resistance, null, null, 0, -1);
	}

	public static AbstractBlock.Properties generateBlockProperties(Material material, MaterialColor mapColour, float hardness, float resistance, int lightLevel) {
		return generateBlockProperties(material, mapColour, hardness, resistance, null, null, lightLevel, -1);
	}

	public static AbstractBlock.Properties generateBlockProperties(Material material, MaterialColor mapColour, float hardness, float resistance, ToolType harvestTool, int harvestLevel) {
		return generateBlockProperties(material, mapColour, hardness, resistance, harvestTool, null, 0, harvestLevel);
	}

	public static AbstractBlock.Properties generateBlockProperties(Material material, MaterialColor mapColour, float hardness, float resistance, @Nullable SoundType soundType) {
		return generateBlockProperties(material, mapColour, hardness, resistance, null, soundType, 0, -1);
	}

	public static AbstractBlock.Properties generateBlockProperties(Material material, MaterialColor mapColour, float hardness, float resistance, @Nullable ToolType harvestTool, @Nullable SoundType soundType, int lightLevel, int harvestLevel) {
		AbstractBlock.Properties properties = AbstractBlock.Properties.of(material, mapColour);

		properties.strength(hardness, resistance);
		properties.harvestLevel(harvestLevel);
		properties.lightLevel(state -> lightLevel);

		if (hardness == UNBREAKABLE_HARDNESS && resistance == UNBREAKABLE_RESISTANCE)
			properties.noDrops();

		if (harvestTool != null)
			properties.harvestTool(harvestTool);

		if (soundType != null) {
			properties.sound(soundType);
		}
		else {
			if (material == null || material == Material.STONE) {
				properties.sound(SoundType.STONE);
			}
			else if (material == Material.WOOD) {
				properties.sound(SoundType.WOOD);
			}
			else if (material == Material.GLASS) {
				properties.sound(SoundType.GLASS);
			}
			else if (material == Material.DIRT) {
				properties.sound(SoundType.GRAVEL);
			}
			else if (material == Material.PLANT || material == Material.GRASS) {
				properties.sound(SoundType.GRASS);
			}
			else if (material == Material.TOP_SNOW || material == Material.SNOW) {
				properties.sound(SoundType.SNOW);
			}
			else if (material == Material.SAND) {
				properties.sound(SoundType.SAND);
			}
			else if (material == Material.WOOL) {
				properties.sound(SoundType.WOOL);
			}
		}

		return properties;
	}

	public static RegistryObject<FlowingFluidBlock> createFluidBlock(String id, Material material, int colour, int viscosity, int density) {
		return createFluidBlock(id, material, colour, viscosity, density, new ResourceLocation("block/water_still"), new ResourceLocation("block/water_flow"), new ResourceLocation("block/water_overlay"), (flowingFluidSupplier, blockProperties) -> () -> new FlowingFluidBlock(flowingFluidSupplier, blockProperties));
	}

	public static RegistryObject<FlowingFluidBlock> createFluidBlock(String id, Material material, int colour, int viscosity, int density, BiFunction<MutableSupplier<ForgeFlowingFluid.Flowing>, AbstractBlock.Properties, Supplier<FlowingFluidBlock>> blockCreationFunction) {
		return createFluidBlock(id, material, colour, viscosity, density, new ResourceLocation("block/water_still"), new ResourceLocation("block/water_flow"), new ResourceLocation("block/water_overlay"), blockCreationFunction);
	}

	public static RegistryObject<FlowingFluidBlock> createFluidBlock(String id, Material material, int colour, int viscosity, int density, ResourceLocation stillTexture, ResourceLocation flowingTexture, ResourceLocation overlay, BiFunction<MutableSupplier<ForgeFlowingFluid.Flowing>, AbstractBlock.Properties, Supplier<FlowingFluidBlock>> blockCreationFunction) {
		MutableSupplier<ForgeFlowingFluid.Source> sourceFluid = new MutableSupplier<ForgeFlowingFluid.Source>(null);
		MutableSupplier<ForgeFlowingFluid.Flowing> flowingFluid = new MutableSupplier<ForgeFlowingFluid.Flowing>(null);
		RegistryObject<FlowingFluidBlock> block = AoABlocks.BLOCKS.register(id, blockCreationFunction.apply(flowingFluid, AbstractBlock.Properties.of(material).noCollission().strength(100.0F).noDrops()));
		ForgeFlowingFluid.Properties fluidProperties = new ForgeFlowingFluid.Properties(sourceFluid, flowingFluid,
				FluidAttributes.builder(stillTexture, flowingTexture)
						.overlay(overlay)
						.translationKey("block." + AdventOfAscension.MOD_ID + "." + id)
						.color(colour)
						.viscosity(viscosity)
						.density(density)
		)
		.bucket(AoAItems.ITEMS.register(id + "_bucket", () -> new BucketItem(sourceFluid, new Item.Properties().tab(AoAItemGroups.MISC_ITEMS).stacksTo(16))))
		.block(block);

		sourceFluid.update(AoABlocks.FLUIDS.register(id, () -> new ForgeFlowingFluid.Source(fluidProperties)));
		flowingFluid.update(AoABlocks.FLUIDS.register(id + "_flowing", () -> new ForgeFlowingFluid.Flowing(fluidProperties)));

		return block;
	}

	public static Vector3d posToVec(BlockPos pos) {
		return new Vector3d(pos.getX(), pos.getY(), pos.getZ());
	}

	public static class SpawnerBuilder {
		private short initialSpawnDelay = 20;
		private short minSpawnDelay = 200;
		private short maxSpawnDelay = 800;

		private short spawnsPerGroup = 4;
		private short maxNearbyEntities = 6;
		private short requiredPlayerRange = 16;
		private short spawnRange = 4;

		private WeightedSpawnerEntity nextSpawn = null;
		private final ArrayList<WeightedSpawnerEntity> mobs = new ArrayList<WeightedSpawnerEntity>(1);

		public SpawnerBuilder initialSpawnDelay(short ticks) {
			this.initialSpawnDelay = ticks;

			return this;
		}

		public SpawnerBuilder minSpawnDelay(short ticks) {
			this.minSpawnDelay = ticks;

			return this;
		}

		public SpawnerBuilder maxSpawnDelay(short ticks) {
			this.maxNearbyEntities = ticks;

			return this;
		}

		public SpawnerBuilder spawnsPerGroup(short amount) {
			this.spawnsPerGroup = amount;

			return this;
		}

		public SpawnerBuilder maxNearbyEntities(short amount) {
			this.maxNearbyEntities = 6;

			return this;
		}

		public SpawnerBuilder whenPlayerWithin(short distance) {
			this.requiredPlayerRange = distance;

			return this;
		}

		public SpawnerBuilder spawnWithinXBlocks(short distance) {
			this.spawnRange = distance;

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
			CompoundNBT tag = new CompoundNBT();

			tag.putString("id", entity.getRegistryName().toString());

			return withSpawn(weight, tag);
		}

		public SpawnerBuilder withSpawn(int weight, CompoundNBT nbt) {
			if (this.nextSpawn == null) {
				this.nextSpawn = new WeightedSpawnerEntity(weight, nbt);

				return this;
			}

			this.mobs.add(new WeightedSpawnerEntity(weight, nbt));

			return this;
		}

		public CompoundNBT build() {
			CompoundNBT nbt = new CompoundNBT();

			if (nextSpawn == null)
				throw new IllegalStateException("Attempted to create spawner data with no mobs listed.");

			nbt.putShort("Delay", this.initialSpawnDelay);
			nbt.putShort("MinSpawnDelay", this.minSpawnDelay);
			nbt.putShort("MaxSpawnDelay", this.maxSpawnDelay);
			nbt.putShort("SpawnCount", this.spawnsPerGroup);
			nbt.putShort("MaxNearbyEntities", this.maxNearbyEntities);
			nbt.putShort("RequiredPlayerRange", this.requiredPlayerRange);
			nbt.putShort("SpawnRange", this.spawnRange);
			nbt.put("SpawnData", this.nextSpawn.getTag().copy());

			ListNBT mobs = new ListNBT();

			if (this.mobs.isEmpty()) {
				mobs.add(this.nextSpawn.save());
			}
			else {
				for (WeightedSpawnerEntity entry : this.mobs) {
					mobs.add(entry.save());
				}
			}
			nbt.put("SpawnPotentials", mobs);

			return nbt;
		}
	}
}
