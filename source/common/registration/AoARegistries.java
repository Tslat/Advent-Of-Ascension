package net.tslat.aoa3.common.registration;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.Keyable;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.commands.synchronization.ArgumentTypeInfo;
import net.minecraft.core.Holder;
import net.minecraft.core.IdMap;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.schedule.Activity;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacementType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryType;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.*;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.block.AoABlockEntities;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.common.registration.block.AoAFluidTypes;
import net.tslat.aoa3.common.registration.custom.*;
import net.tslat.aoa3.common.registration.entity.*;
import net.tslat.aoa3.common.registration.entity.variant.*;
import net.tslat.aoa3.common.registration.item.*;
import net.tslat.aoa3.common.registration.loot.AoALootConditions;
import net.tslat.aoa3.common.registration.loot.AoALootEntryTypes;
import net.tslat.aoa3.common.registration.loot.AoALootFunctions;
import net.tslat.aoa3.common.registration.loot.AoALootModifiers;
import net.tslat.aoa3.common.registration.worldgen.*;
import net.tslat.aoa3.common.toast.CustomToastData;
import net.tslat.aoa3.content.world.spawner.AoACustomSpawner;
import net.tslat.aoa3.player.ability.AoAAbility;
import net.tslat.aoa3.player.resource.AoAResource;
import net.tslat.aoa3.player.skill.AoASkill;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.Stream;

public final class AoARegistries {
	public static final ResourceKey<Registry<AoASkill>> SKILLS_REGISTRY_KEY = ResourceKey.createRegistryKey(AdventOfAscension.id("skills"));
	public static final ResourceKey<Registry<AoAResource>> RESOURCES_REGISTRY_KEY = ResourceKey.createRegistryKey(AdventOfAscension.id("resources"));
	public static final ResourceKey<Registry<AoAAbility>> ABILITIES_REGISTRY_KEY = ResourceKey.createRegistryKey(AdventOfAscension.id("abilities"));
	public static final ResourceKey<Registry<AoAAspectFocus>> ASPECT_FOCI_REGISTRY_KEY = ResourceKey.createRegistryKey(AdventOfAscension.id("aspect_focus"));
	public static final ResourceKey<Registry<CustomToastData.Type<?>>> CUSTOM_TOAST_DATA_REGISTRY_KEY = ResourceKey.createRegistryKey(AdventOfAscension.id("custom_toasts"));
	public static final ResourceKey<Registry<AoACustomSpawner.Type>> CUSTOM_SPAWNER_TYPE_REGISTRY_KEY = ResourceKey.createRegistryKey(AdventOfAscension.id("custom_spawner_type"));
	public static final ResourceKey<Registry<AoACustomSpawner>> CUSTOM_SPAWNERS_REGISTRY_KEY = ResourceKey.createRegistryKey(AdventOfAscension.id("custom_spawners"));

	public static final ResourceKey<Registry<ChargerVariant>> CHARGER_VARIANTS_REGISTRY_KEY = ResourceKey.createRegistryKey(AdventOfAscension.id("charger_variant"));
	public static final ResourceKey<Registry<VeloraptorVariant>> VELORAPTOR_VARIANTS_REGISTRY_KEY = ResourceKey.createRegistryKey(AdventOfAscension.id("veloraptor_variant"));
	public static final ResourceKey<Registry<PixonVariant>> PIXON_VARIANTS_REGISTRY_KEY = ResourceKey.createRegistryKey(AdventOfAscension.id("pixon_variant"));
	public static final ResourceKey<Registry<DryadSpriteVariant>> DRYAD_SPRITE_VARIANTS_REGISTRY_KEY = ResourceKey.createRegistryKey(AdventOfAscension.id("dryad_sprite_variant"));
	public static final ResourceKey<Registry<UndeadHeraldTrade>> UNDEAD_HERALD_TRADES_REGISTRY_KEY = ResourceKey.createRegistryKey(AdventOfAscension.id("undead_herald_trade"));

	public static final RegistryHelper<CreativeModeTab> CREATIVE_MODE_TABS = new RegistryHelper<>(Registries.CREATIVE_MODE_TAB, AoACreativeModeTabs::init);
	public static final RegistryHelper<EntityDataSerializer<?>> ENTITY_DATA_SERIALIZERS = new RegistryHelper<>(NeoForgeRegistries.Keys.ENTITY_DATA_SERIALIZERS, AoAEntityDataSerializers::init);
	public static final RegistryHelper<Block> BLOCKS = new RegistryHelper<>(Registries.BLOCK, (registryKey, modId) -> DeferredRegister.createBlocks(modId), AoABlocks::init);
	public static final RegistryHelper<Item> ITEMS = new RegistryHelper<>(Registries.ITEM, (registryKey, modId) -> DeferredRegister.createItems(modId), AoAItems::init, AoAWeapons::init, AoATools::init, AoAArmour::init);
	public static final RegistryHelper<Fluid> FLUIDS = new RegistryHelper<>(Registries.FLUID);
	public static final RegistryHelper<EntityType<?>> ENTITIES = new RegistryHelper<>(Registries.ENTITY_TYPE, AoAMobs::init, AoAAnimals::init, AoANpcs::init, AoAMiscEntities::init, AoAProjectiles::init);
	public static final RegistryHelper<BlockEntityType<?>> BLOCK_ENTITIES = new RegistryHelper<>(Registries.BLOCK_ENTITY_TYPE, AoABlockEntities::init);
	public static final RegistryHelper<SoundEvent> SOUNDS = new RegistryHelper<>(Registries.SOUND_EVENT, AoASounds::init);
	public static final RegistryHelper<Enchantment> ENCHANTMENTS = new RegistryHelper<>(Registries.ENCHANTMENT, AoAEnchantments::init);
	public static final RegistryHelper<ParticleType<?>> PARTICLES = new RegistryHelper<>(Registries.PARTICLE_TYPE, AoAParticleTypes::init);
	public static final RegistryHelper<MenuType<?>> MENUS = new RegistryHelper<>(Registries.MENU, AoAMenus::init);
	public static final RegistryHelper<RecipeSerializer<?>> RECIPE_SERIALIZERS = new RegistryHelper<>(Registries.RECIPE_SERIALIZER, AoARecipes::init);
	public static final RegistryHelper<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = new RegistryHelper<>(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, AoALootModifiers::init);
	public static final RegistryHelper<Attribute> ENTITY_ATTRIBUTES = new RegistryHelper<>(Registries.ATTRIBUTE, AoAAttributes::init);
	public static final RegistryHelper<PoiType> POI_TYPES = new RegistryHelper<>(Registries.POINT_OF_INTEREST_TYPE, AoAPoiTypes::init);
	public static final RegistryHelper<VillagerProfession> VILLAGER_PROFESSIONS = new RegistryHelper<>(Registries.VILLAGER_PROFESSION, AoAProfessions::init);
	public static final RegistryHelper<Activity> BRAIN_ACTIVITIES = new RegistryHelper<>(Registries.ACTIVITY, AoABrainActivities::init);
	public static final RegistryHelper<SensorType<?>> BRAIN_SENSORS = new RegistryHelper<>(Registries.SENSOR_TYPE, AoABrainSensors::init);
	public static final RegistryHelper<MemoryModuleType<?>> BRAIN_MEMORIES = new RegistryHelper<>(Registries.MEMORY_MODULE_TYPE, AoABrainMemories::init);
	public static final RegistryHelper<BannerPattern> BANNER_PATTERNS = new RegistryHelper<>(Registries.BANNER_PATTERN, AoABannerPatterns::init);
	public static final RegistryHelper<FluidType> FLUID_TYPES = new RegistryHelper<>(NeoForgeRegistries.Keys.FLUID_TYPES, AoAFluidTypes::init);
	public static final RegistryHelper<MobEffect> MOB_EFFECTS = new RegistryHelper<>(Registries.MOB_EFFECT, AoAMobEffects::init);

	public static final RegistryHelper<AttachmentType<?>> DATA_ATTACHMENTS = new RegistryHelper<>(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, AoADataAttachments::init);

	public static final RegistryHelper<LootItemFunctionType> LOOT_FUNCTIONS = new RegistryHelper<>(Registries.LOOT_FUNCTION_TYPE, AoALootFunctions::init);
	public static final RegistryHelper<LootItemConditionType> LOOT_CONDITIONS = new RegistryHelper<>(Registries.LOOT_CONDITION_TYPE, AoALootConditions::init);
	public static final RegistryHelper<LootPoolEntryType> LOOT_ENTRY_TYPES = new RegistryHelper<>(Registries.LOOT_POOL_ENTRY_TYPE, AoALootEntryTypes::init);
	public static final RegistryHelper<RecipeType<?>> RECIPE_TYPES = new RegistryHelper<>(Registries.RECIPE_TYPE, AoARecipes::init);
	public static final RegistryHelper<ArgumentTypeInfo<?, ?>> ARGUMENT_TYPES = new RegistryHelper<>(Registries.COMMAND_ARGUMENT_TYPE, AoACommands::init);
	public static final RegistryHelper<CriterionTrigger<?>> ADVANCEMENT_CRITERIA = new RegistryHelper<>(Registries.TRIGGER_TYPE, AoAAdvancementTriggers::init);

	public static final RegistryHelper<Feature<?>> FEATURES = new RegistryHelper<>(Registries.FEATURE, AoAFeatures::init);
	public static final RegistryHelper<StructureType<?>> STRUCTURE_TYPES = new RegistryHelper<>(Registries.STRUCTURE_TYPE, AoAStructureTypes::init);
	public static final RegistryHelper<PlacementModifierType<?>> PLACEMENT_MODIFIERS = new RegistryHelper<>(Registries.PLACEMENT_MODIFIER_TYPE, AoAPlacementModifiers::init);
	public static final RegistryHelper<Codec<? extends BiomeModifier>> BIOME_MODIFIERS = new RegistryHelper<>(NeoForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, AoABiomeModifiers::init);
	public static final RegistryHelper<StructurePlacementType<?>> STRUCTURE_PLACEMENTS = new RegistryHelper<>(Registries.STRUCTURE_PLACEMENT, AoAStructurePlacements::init);
	public static final RegistryHelper<StructureProcessorType<?>> STRUCTURE_PROCESSORS = new RegistryHelper<>(Registries.STRUCTURE_PROCESSOR, AoAStructureProcessors::init);
	public static final RegistryHelper<Codec<? extends ChunkGenerator>> CHUNK_GENERATORS = new RegistryHelper<>(Registries.CHUNK_GENERATOR, AoAChunkGenerators::init);
	public static final RegistryHelper<TrunkPlacerType<?>> TRUNK_PLACERS = new RegistryHelper<>(Registries.TRUNK_PLACER_TYPE, AoATrees::init);
	public static final RegistryHelper<FoliagePlacerType<?>> FOLIAGE_PLACERS = new RegistryHelper<>(Registries.FOLIAGE_PLACER_TYPE, AoATrees::init);
	public static final RegistryHelper<TreeDecoratorType<?>> TREE_DECORATORS = new RegistryHelper<>(Registries.TREE_DECORATOR_TYPE, AoATrees::init);

	public static final RegistryHelper<ChargerVariant> CHARGER_VARIANTS = new RegistryHelper<>(CHARGER_VARIANTS_REGISTRY_KEY, ChargerVariant::init);
	public static final RegistryHelper<VeloraptorVariant> VELORAPTOR_VARIANTS = new RegistryHelper<>(VELORAPTOR_VARIANTS_REGISTRY_KEY, VeloraptorVariant::init);
	public static final RegistryHelper<PixonVariant> PIXON_VARIANTS = new RegistryHelper<>(PIXON_VARIANTS_REGISTRY_KEY, PixonVariant::init);
	public static final RegistryHelper<DryadSpriteVariant> DRYAD_SPRITE_VARIANTS = new RegistryHelper<>(DRYAD_SPRITE_VARIANTS_REGISTRY_KEY, DryadSpriteVariant::init);
	public static final RegistryHelper<UndeadHeraldTrade> UNDEAD_HERALD_TRADES = new RegistryHelper<>(UNDEAD_HERALD_TRADES_REGISTRY_KEY, UndeadHeraldTrade::init);

	public static final RegistryHelper<AoASkill> AOA_SKILLS = new RegistryHelper<AoASkill>(SKILLS_REGISTRY_KEY, AoASkills::init);
	public static final RegistryHelper<AoAResource> AOA_RESOURCES = new RegistryHelper<AoAResource>(RESOURCES_REGISTRY_KEY, AoAResources::init);
	public static final RegistryHelper<AoAAbility> AOA_ABILITIES = new RegistryHelper<AoAAbility>(ABILITIES_REGISTRY_KEY, AoAAbilities::init);
	public static final RegistryHelper<AoAAspectFocus> AOA_ASPECT_FOCI = new RegistryHelper<AoAAspectFocus>(ASPECT_FOCI_REGISTRY_KEY, AoAAspectFocus::init);

	public static final RegistryHelper<CustomToastData.Type<?>> CUSTOM_TOAST_TYPES = new RegistryHelper<>(CUSTOM_TOAST_DATA_REGISTRY_KEY, AoAToastTypes::init);
	public static final RegistryHelper<AoACustomSpawner.Type> CUSTOM_SPAWNER_TYPES = new RegistryHelper<>(CUSTOM_SPAWNER_TYPE_REGISTRY_KEY, AoACustomSpawners::init);

	public static void init() {
		RegistryHelper.REGISTRY_INIT_TASKS.forEach(Runnable::run);
		RegistryHelper.REGISTRY_INIT_TASKS.clear();

		AdventOfAscension.getModEventBus().addListener(EventPriority.NORMAL, false, NewRegistryEvent.class, AoARegistries::createCustomRegistries);
		AdventOfAscension.getModEventBus().addListener(EventPriority.NORMAL, false, DataPackRegistryEvent.NewRegistry.class, AoARegistries::createCustomDatapackRegistries);
	}

	private static void createCustomRegistries(final NewRegistryEvent ev) {
		ev.create(new RegistryBuilder<>(ABILITIES_REGISTRY_KEY).maxId(Integer.MAX_VALUE - 1));
		ev.create(new RegistryBuilder<>(RESOURCES_REGISTRY_KEY).maxId(Integer.MAX_VALUE - 1));
		ev.create(new RegistryBuilder<>(SKILLS_REGISTRY_KEY).maxId(Integer.MAX_VALUE - 1));
		ev.create(new RegistryBuilder<>(ASPECT_FOCI_REGISTRY_KEY).maxId(Integer.MAX_VALUE - 1));
		ev.create(new RegistryBuilder<>(CUSTOM_TOAST_DATA_REGISTRY_KEY).maxId(Integer.MAX_VALUE - 1));
		ev.create(new RegistryBuilder<>(CHARGER_VARIANTS_REGISTRY_KEY).maxId(Integer.MAX_VALUE - 1));
		ev.create(new RegistryBuilder<>(VELORAPTOR_VARIANTS_REGISTRY_KEY).maxId(Integer.MAX_VALUE - 1));
		ev.create(new RegistryBuilder<>(PIXON_VARIANTS_REGISTRY_KEY).maxId(Integer.MAX_VALUE - 1));
		ev.create(new RegistryBuilder<>(DRYAD_SPRITE_VARIANTS_REGISTRY_KEY).maxId(Integer.MAX_VALUE - 1));
		ev.create(new RegistryBuilder<>(UNDEAD_HERALD_TRADES_REGISTRY_KEY).sync(false).maxId(Integer.MAX_VALUE - 1));
		ev.create(new RegistryBuilder<>(CUSTOM_SPAWNER_TYPE_REGISTRY_KEY).sync(false).maxId(Integer.MAX_VALUE - 1));
	}

	private static void createCustomDatapackRegistries(final DataPackRegistryEvent.NewRegistry ev) {
		ev.dataPackRegistry(CUSTOM_SPAWNERS_REGISTRY_KEY, AoACustomSpawners.CODEC);
	}

	public record RegistryHelper<T>(Supplier<Registry<T>> registry, DeferredRegister<T> deferredRegister, Runnable registrationTasks) implements IdMap<T>, Keyable {
		private static final List<Runnable> REGISTRY_INIT_TASKS = new ObjectArrayList<>();

		private RegistryHelper(ResourceKey<Registry<T>> registryKey, Runnable... registrations) {
			this(registryKey, DeferredRegister::create, registrations);
		}

		private RegistryHelper(ResourceKey<Registry<T>> registryKey, BiFunction<ResourceKey<Registry<T>>, String, DeferredRegister<T>> defRegFactory, Runnable... registrations) {
			this(Suppliers.memoize(() -> (Registry<T>)BuiltInRegistries.REGISTRY.get(registryKey.location())), defRegFactory.apply(registryKey, AdventOfAscension.MOD_ID), () -> Arrays.asList(registrations).forEach(Runnable::run));

			deferredRegister().register(AdventOfAscension.getModEventBus());
			REGISTRY_INIT_TASKS.add(this.registrationTasks);
		}

		public <I extends T, H extends DeferredHolder<T, I>> H register(String id, Supplier<? extends I> object) {
			return (H)deferredRegister().register(id, object);
		}

		public Set<ResourceLocation> getAllIds() {
			return registry().get().keySet();
		}

		public Stream<T> getAllRegisteredObjects() {
			return registry().get().stream();
		}

		public List<DeferredHolder<T, ? extends T>> getAllAoARegisteredObjects() {
			return deferredRegister().getEntries().stream().toList();
		}

		public boolean hasRegisteredId(ResourceLocation id) {
			return registry().get().containsKey(id);
		}

		public T getEntry(ResourceLocation id) {
			return registry().get().get(id);
		}

		public ResourceLocation getKey(T object) {
			return registry().get().getKey(object);
		}

		public Holder<T> wrapAsHolder(T object) {
			return registry().get().wrapAsHolder(object);
		}

		public Codec<T> lookupCodec() {
			return registry().get().byNameCodec();
		}

		@Override
		public int getId(T value) {
			return registry().get().getId(value);
		}

		@NotNull
		@Override
		public Iterator<T> iterator() {
			return registry().get().iterator();
		}

		@Nullable
		@Override
		public T byId(int id) {
			return registry().get().byId(id);
		}

		@Override
		public int size() {
			return registry().get().size();
		}

		@Override
		public <T> Stream<T> keys(DynamicOps<T> ops) {
			return registry().get().keys(ops);
		}

		public Optional<Holder.Reference<T>> getRandomElement(RandomSource random) {
			return registry().get().getRandom(random);
		}

		public ResourceKey<? extends Registry<T>> key() {
			return deferredRegister().getRegistryKey();
		}
	}
}
