package net.tslat.aoa3.common.registration;

import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.registries.*;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.block.AoABlockEntities;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.common.registration.entity.*;
import net.tslat.aoa3.common.registration.item.AoAArmour;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.common.registration.item.AoATools;
import net.tslat.aoa3.common.registration.item.AoAWeapons;
import net.tslat.aoa3.common.registration.worldgen.AoAFeatures;
import net.tslat.aoa3.common.registration.worldgen.AoAPlacementModifiers;
import net.tslat.aoa3.common.registration.worldgen.AoAStructures;
import net.tslat.aoa3.player.ability.AoAAbility;
import net.tslat.aoa3.player.resource.AoAResource;
import net.tslat.aoa3.player.skill.AoASkill;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public final class AoARegistries {
	public static final RegistryHelper<Block> BLOCKS = new ForgeRegistryHelper<>(ForgeRegistries.Keys.BLOCKS, AoABlocks::init);
	public static final RegistryHelper<Item> ITEMS = new ForgeRegistryHelper<>(ForgeRegistries.Keys.ITEMS, AoAItems::init, AoAWeapons::init, AoATools::init, AoAArmour::init);
	public static final RegistryHelper<Fluid> FLUIDS = new ForgeRegistryHelper<>(ForgeRegistries.Keys.FLUIDS);
	public static final RegistryHelper<EntityType<?>> ENTITIES = new ForgeRegistryHelper<>(ForgeRegistries.Keys.ENTITY_TYPES, AoAMobs::init, AoAAnimals::init, AoANpcs::init, AoAMiscEntities::init, AoAProjectiles::init);
	public static final RegistryHelper<BlockEntityType<?>> BLOCK_ENTITIES = new ForgeRegistryHelper<>(ForgeRegistries.Keys.BLOCK_ENTITY_TYPES, AoABlockEntities::init);
	public static final RegistryHelper<SoundEvent> SOUNDS = new ForgeRegistryHelper<>(ForgeRegistries.Keys.SOUND_EVENTS, AoASounds::init);
	public static final RegistryHelper<Enchantment> ENCHANTMENTS = new ForgeRegistryHelper<>(ForgeRegistries.Keys.ENCHANTMENTS, AoAEnchantments::init);
	public static final RegistryHelper<ParticleType<?>> PARTICLES = new ForgeRegistryHelper<>(ForgeRegistries.Keys.PARTICLE_TYPES, AoAParticleTypes::init);
	public static final RegistryHelper<MenuType<?>> MENUS = new ForgeRegistryHelper<>(ForgeRegistries.Keys.CONTAINER_TYPES, AoAContainers::init);
	public static final RegistryHelper<RecipeSerializer<?>> RECIPE_SERIALIZERS = new ForgeRegistryHelper<>(ForgeRegistries.Keys.RECIPE_SERIALIZERS, AoARecipes::init);
	public static final RegistryHelper<GlobalLootModifierSerializer<?>> LOOT_MODIFIERS = new ForgeRegistryHelper<>(ForgeRegistries.Keys.LOOT_MODIFIER_SERIALIZERS, AoALootModifiers::init);
	public static final RegistryHelper<Attribute> ENTITY_ATTRIBUTES = new ForgeRegistryHelper<>(ForgeRegistries.Keys.ATTRIBUTES, AoAAttributes::init);
	public static final RegistryHelper<VillagerProfession> VILLAGER_PROFESSIONS = new ForgeRegistryHelper<>(ForgeRegistries.Keys.VILLAGER_PROFESSIONS, AoAProfessions::init);
	public static final RegistryHelper<SensorType<?>> BRAIN_SENSORS = new ForgeRegistryHelper<>(ForgeRegistries.Keys.SENSOR_TYPES, AoABrainSensors::init);
	public static final RegistryHelper<MemoryModuleType<?>> BRAIN_MEMORIES = new ForgeRegistryHelper<>(ForgeRegistries.Keys.MEMORY_MODULE_TYPES, AoABrainMemories::init);

	public static final RegistryHelper<LootItemFunctionType> LOOT_FUNCTIONS = new VanillaRegistryHelper<>(Registry.LOOT_FUNCTION_REGISTRY, AoALootOperations.LootFunctions::init);
	public static final RegistryHelper<LootItemConditionType> LOOT_CONDITIONS = new VanillaRegistryHelper<>(Registry.LOOT_ITEM_REGISTRY, AoALootOperations.LootConditions::init);
	public static final RegistryHelper<RecipeType<?>> RECIPE_TYPES = new VanillaRegistryHelper<>(Registry.RECIPE_TYPE_REGISTRY, AoARecipes::init);

	public static final RegistryHelper<Feature<?>> FEATURES = new ForgeRegistryHelper<>(ForgeRegistries.Keys.FEATURES, AoAFeatures::init);
	public static final RegistryHelper<StructureFeature<?>> STRUCTURES = new ForgeRegistryHelper<>(ForgeRegistries.Keys.STRUCTURE_FEATURES, AoAStructures::init);
	public static final RegistryHelper<PlacementModifierType<?>> PLACEMENT_MODIFIERS = new VanillaRegistryHelper<>(Registry.PLACEMENT_MODIFIER_REGISTRY, AoAPlacementModifiers::init);

	public static final RegistryHelper<AoASkill> AOA_SKILLS = new ForgeRegistryHelper<AoASkill>(ResourceKey.createRegistryKey(AdventOfAscension.id("skills")), AoASkills::init);
	public static final RegistryHelper<AoAResource> AOA_RESOURCES = new ForgeRegistryHelper<AoAResource>(ResourceKey.createRegistryKey(AdventOfAscension.id("resources")), AoAResources::init);
	public static final RegistryHelper<AoAAbility> AOA_ABILITIES = new ForgeRegistryHelper<AoAAbility>(ResourceKey.createRegistryKey(AdventOfAscension.id("abilities")), AoAAbilities::init);

	public static void init() {
		BLOCKS.doRegistrations();
		ITEMS.doRegistrations();
		FLUIDS.doRegistrations();
		ENTITIES.doRegistrations();
		BLOCK_ENTITIES.doRegistrations();
		SOUNDS.doRegistrations();
		ENCHANTMENTS.doRegistrations();
		PARTICLES.doRegistrations();
		MENUS.doRegistrations();
		RECIPE_SERIALIZERS.doRegistrations();
		LOOT_MODIFIERS.doRegistrations();
		ENTITY_ATTRIBUTES.doRegistrations();
		VILLAGER_PROFESSIONS.doRegistrations();
		BRAIN_SENSORS.doRegistrations();
		BRAIN_MEMORIES.doRegistrations();
		AOA_SKILLS.doRegistrations();
		AOA_RESOURCES.doRegistrations();
		AOA_ABILITIES.doRegistrations();

		LOOT_FUNCTIONS.doRegistrations();
		LOOT_CONDITIONS.doRegistrations();
		RECIPE_TYPES.doRegistrations();

		FEATURES.doRegistrations();
		STRUCTURES.doRegistrations();
		PLACEMENT_MODIFIERS.doRegistrations();
	}

	public interface RegistryHelper<T> {
		DeferredRegister<T> deferredRegister();
		<I extends T> RegistryObject<I> register(String id, Supplier<? extends I> object);
		Set<ResourceLocation> getAllIds();
		Collection<T> getAllRegisteredObjects();
		List<RegistryObject<T>> getAllAoARegisteredObjects();
		T getEntry(ResourceLocation id);
		void doRegistrations();
	}

	public record ForgeRegistryHelper<T extends IForgeRegistryEntry<T>>(Lazy<ForgeRegistry<T>> forgeRegistry, DeferredRegister<T> deferredRegister, Runnable registrationTasks) implements RegistryHelper<T> {
		private ForgeRegistryHelper(ResourceKey<Registry<T>> registryKey, Runnable... registrations) {
			this(Lazy.of(() -> RegistryManager.ACTIVE.getRegistry(registryKey)), DeferredRegister.<T>create(registryKey, AdventOfAscension.MOD_ID), () -> Arrays.asList(registrations).forEach(Runnable::run));

			deferredRegister().register(AdventOfAscension.modEventBus);
		}

		private ForgeRegistryHelper(ResourceKey<Registry<T>> registryKey, Supplier<List<Class<?>>> staticInitClasses) {
			this(Lazy.of(() -> RegistryManager.ACTIVE.getRegistry(registryKey)), DeferredRegister.<T>create(registryKey, AdventOfAscension.MOD_ID), staticInitClasses::get);

			deferredRegister().register(AdventOfAscension.modEventBus);
		}

		@Override
		public void doRegistrations() {
			registrationTasks().run();
		}

		public <I extends T> RegistryObject<I> register(String id, Supplier<? extends I> object) {
			return deferredRegister().register(id, object);
		}

		@Override
		public Set<ResourceLocation> getAllIds() {
			return forgeRegistry().get().getKeys();
		}

		@Override
		public Collection<T> getAllRegisteredObjects() {
			return forgeRegistry().get().getValues();
		}

		@Override
		public List<RegistryObject<T>> getAllAoARegisteredObjects() {
			return deferredRegister().getEntries().stream().filter(entry -> entry.getId().getNamespace().equals(AdventOfAscension.MOD_ID)).toList();
		}

		@Override
		public T getEntry(ResourceLocation id) {
			return forgeRegistry().get().getValue(id);
		}
	}

	public record VanillaRegistryHelper<T>(Lazy<Registry<T>> registry, DeferredRegister<T> deferredRegister, Runnable registrationTasks) implements RegistryHelper<T> {
		private VanillaRegistryHelper(ResourceKey<Registry<T>> registryKey, Runnable... registrations) {
			this(Lazy.of(() -> (Registry<T>)Registry.REGISTRY.get(registryKey.location())), DeferredRegister.<T>create(registryKey, AdventOfAscension.MOD_ID), () -> Arrays.asList(registrations).forEach(Runnable::run));

			deferredRegister().register(AdventOfAscension.modEventBus);
		}

		private VanillaRegistryHelper(ResourceKey<Registry<T>> registryKey, Supplier<List<Class<?>>> staticInitClasses) {
			this(Lazy.of(() -> (Registry<T>)Registry.REGISTRY.get(registryKey.location())), DeferredRegister.<T>create(registryKey, AdventOfAscension.MOD_ID), staticInitClasses::get);

			deferredRegister().register(AdventOfAscension.modEventBus);
		}

		@Override
		public void doRegistrations() {
			registrationTasks().run();
		}

		public <I extends T> RegistryObject<I> register(String id, Supplier<? extends I> object) {
			return deferredRegister().register(id, object);
		}

		@Override
		public Set<ResourceLocation> getAllIds() {
			return registry().get().keySet();
		}

		@Override
		public Collection<T> getAllRegisteredObjects() {
			return registry().get().stream().toList();
		}

		@Override
		public List<RegistryObject<T>> getAllAoARegisteredObjects() {
			return deferredRegister().getEntries().stream().filter(entry -> entry.getId().getNamespace().equals(AdventOfAscension.MOD_ID)).toList();
		}

		@Override
		public T getEntry(ResourceLocation id) {
			return registry().get().get(id);
		}
	}
}
