package net.tslat.aoa3.util;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
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
import net.tslat.aoa3.util.misc.MutableSupplier;

import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public abstract class BlockUtil {
	public static final float UNBREAKABLE_HARDNESS = -1f;
	public static final float UNBREAKABLE_RESISTANCE = 999999999f;

	public static class CompactProperties {
		private final AbstractBlock.Properties properties;

		public CompactProperties(Material material, Function<BlockState, MaterialColor> mapColours) {
			this.properties = AbstractBlock.Properties.of(material, mapColours).sound(approximateSound(material));
		}

		public CompactProperties(Material material, MaterialColor mapColour) {
			this(material, state -> mapColour);
		}

		public CompactProperties harvestTool(ToolType tool, int harvestLevel) {
			harvestTool(tool);
			this.properties.harvestLevel(harvestLevel);

			return this;
		}

		public CompactProperties harvestTool(ToolType tool) {
			tool(tool);
			this.properties.requiresCorrectToolForDrops();

			return this;
		}

		public CompactProperties tool(ToolType tool) {
			this.properties.harvestTool(tool);

			return this;
		}

		public CompactProperties harvestLevel(int level) {
			this.properties.harvestLevel(level);

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

		public CompactProperties emissive(AbstractBlock.IPositionPredicate when) {
			this.properties.emissiveRendering(when);

			return this;
		}

		public CompactProperties renderAdjust() {
			return renderAdjust((state, world, pos) -> true);
		}

		public CompactProperties renderAdjust(AbstractBlock.IPositionPredicate when) {
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

		public CompactProperties coversScreen(AbstractBlock.IPositionPredicate when) {
			this.properties.isViewBlocking(when);

			return this;
		}

		public CompactProperties noRedstone() {
			return conductRedstone((state, world, pos) -> false);
		}

		public CompactProperties conductRedstone(AbstractBlock.IPositionPredicate when) {
			this.properties.isRedstoneConductor(when);

			return this;
		}

		public CompactProperties noSpawns() {
			return specialSpawns((state, world, pos, entityType) -> false);
		}

		public CompactProperties specialSpawns(AbstractBlock.IExtendedPositionPredicate<EntityType<?>> when) {
			this.properties.isValidSpawn(when);

			return this;
		}

		public CompactProperties breathable() {
			return suffocate((state, world, pos) -> false);
		}

		public CompactProperties suffocate(AbstractBlock.IPositionPredicate when) {
			this.properties.isSuffocating(when);

			return this;
		}

		public AbstractBlock.Properties get() {
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
			else {
				return SoundType.STONE;
			}
		}
	}

	public static RegistryObject<FlowingFluidBlock> createFluidBlock(String id, Material material, int colour, int viscosity, int density, int temperature) {
		return createFluidBlock(id, material, colour, viscosity, density, temperature, new ResourceLocation("block/water_still"), new ResourceLocation("block/water_flow"), new ResourceLocation("block/water_overlay"), (flowingFluidSupplier, blockProperties) -> () -> new FlowingFluidBlock(flowingFluidSupplier, blockProperties));
	}

	public static RegistryObject<FlowingFluidBlock> createFluidBlock(String id, Material material, int colour, int viscosity, int density, int temperature, BiFunction<MutableSupplier<ForgeFlowingFluid.Flowing>, AbstractBlock.Properties, Supplier<FlowingFluidBlock>> blockCreationFunction) {
		return createFluidBlock(id, material, colour, viscosity, density, temperature, new ResourceLocation("block/water_still"), new ResourceLocation("block/water_flow"), new ResourceLocation("block/water_overlay"), blockCreationFunction);
	}

	public static RegistryObject<FlowingFluidBlock> createFluidBlock(String id, Material material, int colour, int viscosity, int density, int temperature, ResourceLocation stillTexture, ResourceLocation flowingTexture, ResourceLocation overlay, BiFunction<MutableSupplier<ForgeFlowingFluid.Flowing>, AbstractBlock.Properties, Supplier<FlowingFluidBlock>> blockCreationFunction) {
		MutableSupplier<ForgeFlowingFluid.Source> sourceFluid = new MutableSupplier<ForgeFlowingFluid.Source>(null);
		MutableSupplier<ForgeFlowingFluid.Flowing> flowingFluid = new MutableSupplier<ForgeFlowingFluid.Flowing>(null);
		RegistryObject<FlowingFluidBlock> block = AoABlocks.BLOCKS.register(id, blockCreationFunction.apply(flowingFluid, AbstractBlock.Properties.of(material).noCollission().strength(100.0F).noDrops()));
		ForgeFlowingFluid.Properties fluidProperties = new ForgeFlowingFluid.Properties(sourceFluid, flowingFluid,
				FluidAttributes.builder(stillTexture, flowingTexture)
						.overlay(overlay)
						.translationKey("block." + AdventOfAscension.MOD_ID + "." + id)
						.color(colour)
						.temperature(temperature)
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

	public static boolean isBlockTaggedAs(Block block, ITag<Block>... tags) {
		for (ITag<Block> tag : tags) {
			if (block.is(tag))
				return true;
		}

		return false;
	}

	public static class FluidBuilder {
		private final ResourceLocation id;

		private Material material = Material.WATER;
		private boolean isGas = false;
		private boolean noBucket = false;

		private int colour = 0xFFFFFFFF;
		private int viscosity = 1000;
		private int density = 1000;
		private int temperature = 300;
		private int lightLevel = 0;

		private ResourceLocation stillTexture = new ResourceLocation("block/water_still");
		private ResourceLocation flowingTexture = new ResourceLocation("block/water_flow");
		private ResourceLocation submergedOverlayTexture = new ResourceLocation("block/water_overlay");

		private String localeKey;
		private SoundEvent bucketFillSound = null;
		private SoundEvent bucketEmptySound = null;

		private Rarity rarity = Rarity.COMMON;

		private BiFunction<MutableSupplier<ForgeFlowingFluid.Flowing>, AbstractBlock.Properties, Supplier<FlowingFluidBlock>> blockFunction = (fluidSupplier, fluidProperties) -> () -> new FlowingFluidBlock(fluidSupplier, fluidProperties);
		private BiFunction<MutableSupplier<? extends ForgeFlowingFluid>, Item.Properties, Supplier<Item>> bucketFunction = (fluid, properties) -> () -> new BucketItem(fluid, properties);
		private Function<ForgeFlowingFluid.Properties, Supplier<ForgeFlowingFluid.Flowing>> flowingFluid = properties -> () -> new ForgeFlowingFluid.Flowing(properties);
		private Function<ForgeFlowingFluid.Properties, Supplier<ForgeFlowingFluid.Source>> sourceFluid = properties -> () -> new ForgeFlowingFluid.Source(properties);

		public FluidBuilder(String id) {
			this(new ResourceLocation(AdventOfAscension.MOD_ID, id));
		}

		public FluidBuilder(ResourceLocation id) {
			this.id	= id;
			this.localeKey = "block." + id.getNamespace() + "." + id.getPath();
		}

		public FluidBuilder stillTexture(ResourceLocation texture) {
			this.stillTexture = texture;

			return this;
		}

		public FluidBuilder flowingTexture(ResourceLocation texture) {
			this.flowingTexture = texture;

			return this;
		}

		public FluidBuilder submergedOverlay(ResourceLocation texture) {
			this.submergedOverlayTexture = texture;

			return this;
		}

		public FluidBuilder material(Material material) {
			this.material = material;

			return this;
		}

		public FluidBuilder isGas() {
			this.isGas = true;

			return this;
		}

		public FluidBuilder colour(int red, int green, int blue, int alpha) {
			return colour(NumberUtil.alpha(NumberUtil.RGB(red, green, blue), alpha));
		}

		public FluidBuilder colour(int colour) {
			this.colour = colour;

			return this;
		}

		public FluidBuilder viscosity(int viscosity) {
			this.viscosity = viscosity;

			return this;
		}

		public FluidBuilder density(int density) {
			this.density = density;

			return this;
		}

		public FluidBuilder temperature(int temperature) {
			this.temperature = temperature;

			return this;
		}

		public FluidBuilder light(int lightLevel) {
			this.lightLevel = lightLevel;

			return this;
		}

		public FluidBuilder localeKey(String langKey) {
			this.localeKey = langKey;

			return this;
		}

		public FluidBuilder fillSound(SoundEvent sound) {
			this.bucketFillSound = sound;

			return this;
		}

		public FluidBuilder emptySound(SoundEvent sound) {
			this.bucketEmptySound = sound;

			return this;
		}

		public FluidBuilder sound(SoundEvent sound) {
			fillSound(sound);
			emptySound(sound);

			return this;
		}

		public FluidBuilder rarity(Rarity rarity) {
			this.rarity = rarity;

			return this;
		}

		public FluidBuilder noBucket() {
			this.noBucket = true;

			return this;
		}

		public FluidBuilder blockFunction(BiFunction<MutableSupplier<ForgeFlowingFluid.Flowing>, AbstractBlock.Properties, Supplier<FlowingFluidBlock>> function) {
			this.blockFunction = function;

			return this;
		}

		public FluidBuilder bucketFunction(BiFunction<MutableSupplier<? extends ForgeFlowingFluid>, Item.Properties, Supplier<Item>> function) {
			this.bucketFunction = function;

			return this;
		}

		public FluidBuilder sourceFluid(Function<ForgeFlowingFluid.Properties, Supplier<ForgeFlowingFluid.Source>> function) {
			this.sourceFluid = function;

			return this;
		}

		public FluidBuilder flowingFluid(Function<ForgeFlowingFluid.Properties, Supplier<ForgeFlowingFluid.Flowing>> function) {
			this.flowingFluid = function;

			return this;
		}

		public FluidAttributes.Builder toAttributes() {
			FluidAttributes.Builder attributes = FluidAttributes.builder(stillTexture, flowingTexture);

			attributes.overlay(submergedOverlayTexture);

			if (isGas)
				attributes.gaseous();

			attributes.color(colour);
			attributes.viscosity(viscosity);
			attributes.density(density);
			attributes.temperature(temperature);
			attributes.luminosity(lightLevel);
			attributes.translationKey(localeKey);
			attributes.sound(bucketFillSound, bucketEmptySound);
			attributes.rarity(rarity);

			return attributes;
		}

		public RegistryObject<FlowingFluidBlock> register() {
			MutableSupplier<ForgeFlowingFluid.Source> sourceFluid = new MutableSupplier<ForgeFlowingFluid.Source>(null);
			MutableSupplier<ForgeFlowingFluid.Flowing> flowingFluid = new MutableSupplier<ForgeFlowingFluid.Flowing>(null);
			ForgeFlowingFluid.Properties fluidProperties = new ForgeFlowingFluid.Properties(sourceFluid, flowingFluid, toAttributes());
			RegistryObject<FlowingFluidBlock> block = AoABlocks.BLOCKS.register(id.getPath(), blockFunction.apply(flowingFluid, AbstractBlock.Properties.of(material).noCollission().strength(100.0F).noDrops()));

			if (!noBucket)
				fluidProperties.bucket(AoAItems.ITEMS.register(id.getPath() + "_bucket", bucketFunction.apply(sourceFluid, new Item.Properties().tab(AoAItemGroups.MISC_ITEMS).stacksTo(16))));

			fluidProperties.block(block);
			sourceFluid.update(AoABlocks.FLUIDS.register(id.getPath(), this.sourceFluid.apply(fluidProperties)));
			flowingFluid.update(AoABlocks.FLUIDS.register(id.getPath() + "_flowing", this.flowingFluid.apply(fluidProperties)));

			return block;
		}
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
