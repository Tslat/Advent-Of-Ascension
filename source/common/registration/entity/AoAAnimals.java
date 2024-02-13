package net.tslat.aoa3.common.registration.entity;

import net.minecraft.SharedConstants;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.entity.animal.*;
import net.tslat.aoa3.content.entity.animal.fish.BasicFishEntity;
import net.tslat.aoa3.content.entity.animal.fish.BasicLavaFishEntity;
import net.tslat.aoa3.content.entity.animal.precasia.DeinotheriumEntity;
import net.tslat.aoa3.content.entity.animal.precasia.HorndronEntity;
import net.tslat.aoa3.content.entity.animal.precasia.OpteryxEntity;
import net.tslat.aoa3.content.entity.mob.haven.AngelicaEntity;
import net.tslat.aoa3.content.entity.mob.haven.DawnlightEntity;
import net.tslat.aoa3.content.entity.mob.haven.RainicornEntity;

import java.util.function.Consumer;

public final class AoAAnimals {
	public static void init() {}

	public static final DeferredHolder<EntityType<?>, EntityType<CorateeEntity>> CORATEE = registerAnimal("coratee", CorateeEntity::new, MobCategory.WATER_AMBIENT, 1f, 1f,  0x325156, 0x55797E);
	public static final DeferredHolder<EntityType<?>, EntityType<Animal>> CREEP_COW = registerAnimal("creep_cow", CreepCowEntity::new, 0.9f, 1.4f, 0x0CAB1A, 0xE0E9DB);
	public static final DeferredHolder<EntityType<?>, EntityType<Animal>> HALYCON = registerAnimal("halycon", HalyconEntity::new, 0.9f, 1.4f, 0x2B5019, 0x82A94F);

	public static final DeferredHolder<EntityType<?>, EntityType<ShinySquidEntity>> SHINY_SQUID = registerAnimal("shiny_squid", ShinySquidEntity::new, MobCategory.WATER_CREATURE, 0.8f, 0.8f, 0xC7A003, 0x885000, builder -> builder.clientTrackingRange(8));
	public static final DeferredHolder<EntityType<?>, EntityType<BasicLavaFishEntity>> CANDLEFISH = registerAnimal("candlefish", BasicLavaFishEntity::new, MobCategory.WATER_AMBIENT, 0.7f, 0.4f, 0xD93610, 0xF88F12, builder -> builder.clientTrackingRange(4).fireImmune());
	public static final DeferredHolder<EntityType<?>, EntityType<BasicLavaFishEntity>> CHARRED_CHAR = registerAnimal("charred_char", BasicLavaFishEntity::new, MobCategory.WATER_AMBIENT, 0.7f, 0.4f, 0x1F1812, 0xD87C1D, builder -> builder.clientTrackingRange(4).fireImmune());
	public static final DeferredHolder<EntityType<?>, EntityType<BasicFishEntity>> CHOCAW = registerAnimal("chocaw", BasicFishEntity::new, MobCategory.WATER_AMBIENT, 0.7f, 0.4f, 0x5E3412, 0x48260A, builder -> builder.clientTrackingRange(4));
	public static final DeferredHolder<EntityType<?>, EntityType<BasicLavaFishEntity>> CRIMSON_SKIPPER = registerAnimal("crimson_skipper", BasicLavaFishEntity::new, MobCategory.WATER_AMBIENT, 0.7f, 0.4f, 0xA50539, 0xD7385E, builder -> builder.clientTrackingRange(4).fireImmune());
	public static final DeferredHolder<EntityType<?>, EntityType<BasicLavaFishEntity>> CRIMSON_STRIPEFISH = registerAnimal("crimson_stripefish", BasicLavaFishEntity::new, MobCategory.WATER_AMBIENT, 0.7f, 0.4f, 0xA14A43, 0xBD7C7F, builder -> builder.clientTrackingRange(4).fireImmune());
	public static final DeferredHolder<EntityType<?>, EntityType<BasicFishEntity>> DARK_HATCHETFISH = registerAnimal("dark_hatchetfish", BasicFishEntity::new, MobCategory.WATER_AMBIENT, 0.7f, 0.4f, 0xA14A43, 0xBD7C7F, builder -> builder.clientTrackingRange(4));
	public static final DeferredHolder<EntityType<?>, EntityType<BasicFishEntity>> BLUE_GEMTRAP = registerAnimal("blue_gemtrap", BasicFishEntity::new, MobCategory.WATER_AMBIENT, 0.7f, 0.4f, 0x094467, 0x136F9F, builder -> builder.clientTrackingRange(4));
	public static final DeferredHolder<EntityType<?>, EntityType<BasicFishEntity>> GREEN_GEMTRAP = registerAnimal("green_gemtrap", BasicFishEntity::new, MobCategory.WATER_AMBIENT, 0.7f, 0.4f, 0x0A6B0A, 0x308F17, builder -> builder.clientTrackingRange(4));
	public static final DeferredHolder<EntityType<?>, EntityType<BasicFishEntity>> PURPLE_GEMTRAP = registerAnimal("purple_gemtrap", BasicFishEntity::new, MobCategory.WATER_AMBIENT, 0.7f, 0.4f, 0x480A6B, 0x69188B, builder -> builder.clientTrackingRange(4));
	public static final DeferredHolder<EntityType<?>, EntityType<BasicFishEntity>> RED_GEMTRAP = registerAnimal("red_gemtrap", BasicFishEntity::new, MobCategory.WATER_AMBIENT, 0.7f, 0.4f, 0x83200D, 0xA53819, builder -> builder.clientTrackingRange(4));
	public static final DeferredHolder<EntityType<?>, EntityType<BasicFishEntity>> WHITE_GEMTRAP = registerAnimal("white_gemtrap", BasicFishEntity::new, MobCategory.WATER_AMBIENT, 0.7f, 0.4f, 0x696969, 0xA3A3A3, builder -> builder.clientTrackingRange(4));
	public static final DeferredHolder<EntityType<?>, EntityType<BasicFishEntity>> YELLOW_GEMTRAP = registerAnimal("yellow_gemtrap", BasicFishEntity::new, MobCategory.WATER_AMBIENT, 0.7f, 0.4f, 0x6B590A, 0x8F6C16, builder -> builder.clientTrackingRange(4));
	public static final DeferredHolder<EntityType<?>, EntityType<BasicFishEntity>> HYDRONE = registerAnimal("hydrone", BasicFishEntity::new, MobCategory.WATER_AMBIENT, 0.7f, 0.4f, 0x3E4025, 0x56554C, builder -> builder.clientTrackingRange(4));
	public static final DeferredHolder<EntityType<?>, EntityType<BasicFishEntity>> IRONBACK = registerAnimal("ironback", BasicFishEntity::new, MobCategory.WATER_AMBIENT, 0.7f, 0.4f, 0x6F8E8C, 0x435251, builder -> builder.clientTrackingRange(4));
	public static final DeferredHolder<EntityType<?>, EntityType<BasicFishEntity>> JAMFISH = registerAnimal("jamfish", BasicFishEntity::new, MobCategory.WATER_AMBIENT, 0.7f, 0.4f, 0x641D8D, 0xEC81F2, builder -> builder.clientTrackingRange(4));
	public static final DeferredHolder<EntityType<?>, EntityType<BasicFishEntity>> PARAPIRANHA = registerAnimal("parapiranha", BasicFishEntity::new, MobCategory.WATER_AMBIENT, 0.7f, 0.4f, 0x5F1C0A, 0x251D19, builder -> builder.clientTrackingRange(4));
	public static final DeferredHolder<EntityType<?>, EntityType<BasicFishEntity>> PEARL_STRIPEFISH = registerAnimal("pearl_stripefish", BasicFishEntity::new, MobCategory.WATER_AMBIENT, 0.7f, 0.4f, 0xFBF4FA, 0xD8C9D6, builder -> builder.clientTrackingRange(4));
	public static final DeferredHolder<EntityType<?>, EntityType<BasicFishEntity>> RAINBOWFISH = registerAnimal("rainbowfish", BasicFishEntity::new, MobCategory.WATER_AMBIENT, 0.7f, 0.4f, 0xC76D6D, 0x47AC94, builder -> builder.clientTrackingRange(4));
	public static final DeferredHolder<EntityType<?>, EntityType<BasicFishEntity>> RAZORFISH = registerAnimal("razorfish", BasicFishEntity::new, MobCategory.WATER_AMBIENT, 0.7f, 0.4f, 0x6C685F, 0x40382D, builder -> builder.clientTrackingRange(4));
	public static final DeferredHolder<EntityType<?>, EntityType<BasicFishEntity>> REEFTOOTH = registerAnimal("reeftooth", BasicFishEntity::new, MobCategory.WATER_AMBIENT, 0.7f, 0.4f, 0x427779, 0x3C5556, builder -> builder.clientTrackingRange(4));
	public static final DeferredHolder<EntityType<?>, EntityType<BasicFishEntity>> ROCKETFISH = registerAnimal("rocketfish", BasicFishEntity::new, MobCategory.WATER_AMBIENT, 0.7f, 0.4f, 0xAB2E0F, 0x961106, builder -> builder.clientTrackingRange(4));
	public static final DeferredHolder<EntityType<?>, EntityType<BasicFishEntity>> SAILBACK = registerAnimal("sailback", BasicFishEntity::new, MobCategory.WATER_AMBIENT, 0.7f, 0.4f, 0x5CAECB, 0xD0E7ED, builder -> builder.clientTrackingRange(4));
	public static final DeferredHolder<EntityType<?>, EntityType<BasicFishEntity>> SAPPHIRE_STRIDER = registerAnimal("sapphire_strider", BasicFishEntity::new, MobCategory.WATER_AMBIENT, 0.7f, 0.4f, 0x489EFD, 0x3C20B2, builder -> builder.clientTrackingRange(4));
	public static final DeferredHolder<EntityType<?>, EntityType<BasicFishEntity>> SKELECANTH = registerAnimal("skelecanth", BasicFishEntity::new, MobCategory.WATER_AMBIENT, 0.7f, 0.4f, 0xDADAD1, 0x7C7C7A, builder -> builder.clientTrackingRange(4));
	public static final DeferredHolder<EntityType<?>, EntityType<BasicFishEntity>> TURQUOISE_STRIPEFISH = registerAnimal("turquoise_stripefish", BasicFishEntity::new, MobCategory.WATER_AMBIENT, 0.7f, 0.4f, 0x5CA0A9, 0xA9DBE2, builder -> builder.clientTrackingRange(4));
	public static final DeferredHolder<EntityType<?>, EntityType<BasicFishEntity>> VIOLET_SKIPPER = registerAnimal("violet_skipper", BasicFishEntity::new, MobCategory.WATER_AMBIENT, 0.7f, 0.4f, 0x814FBB, 0xF5F9F6, builder -> builder.clientTrackingRange(4));

	public static final DeferredHolder<EntityType<?>, EntityType<HorndronEntity>> HORNDRON = registerAnimal("horndron", HorndronEntity::new, 1.5f, 2f, 0x332521, 0x5F523A);
	public static final DeferredHolder<EntityType<?>, EntityType<DeinotheriumEntity>> DEINOTHERIUM = registerAnimal("deinotherium", DeinotheriumEntity::new, 1.5f, 3.125f, 0x797168, 0x595149);
	public static final DeferredHolder<EntityType<?>, EntityType<OpteryxEntity>> OPTERYX = registerAnimal("opteryx", OpteryxEntity::new, 0.5f, 1.25f, 0xBA912C, 0x785205);

	public static final DeferredHolder<EntityType<?>, EntityType<Animal>> ANGELICA = registerAnimal("angelica", AngelicaEntity::new, 0.6f, 2f, 0x146262, 0xB4B4B4);
	public static final DeferredHolder<EntityType<?>, EntityType<Animal>> DAWNLIGHT = registerAnimal("dawnlight", DawnlightEntity::new, 0.6875f, 1.375f, 0xD8DAD2, 0xEBEBEA);
	public static final DeferredHolder<EntityType<?>, EntityType<Animal>> EEO = registerAnimal("eeo", EeoEntity::new, 0.375f, 1.25f, 0xDADDDD, 0xE4E5E5);
	public static final DeferredHolder<EntityType<?>, EntityType<Animal>> NIGHT_WATCHER = registerAnimal("night_watcher", NightWatcherEntity::new, 0.6f, 2.7f, 0x040403, 0x393828);
	public static final DeferredHolder<EntityType<?>, EntityType<Animal>> PEPPERMINT_SNAIL = registerAnimal("peppermint_snail", PeppermintSnailEntity::new, 0.5f, 0.8125f, 0xE9E9E9, 0xF81414);
	public static final DeferredHolder<EntityType<?>, EntityType<Animal>> RAINICORN = registerAnimal("rainicorn", RainicornEntity::new, 1.4f, 1.3125f, 0xCAD0B6, 0xDBDFD7);
	public static final DeferredHolder<EntityType<?>, EntityType<Animal>> SPEARMINT_SNAIL = registerAnimal("spearmint_snail", SpearmintSnailEntity::new, 0.5f, 0.8125f, 0xEAEAEA, 0x26A620);
	public static final DeferredHolder<EntityType<?>, EntityType<Animal>> TROTTER = registerAnimal("trotter", TrotterEntity::new, 0.75f, 1.1875f, 0xC02A00, 0xAC2400);
	public static final DeferredHolder<EntityType<?>, EntityType<Animal>> URKA = registerAnimal("urka", UrkaEntity::new, 1.2f, 1.5f, 0x545454, 0x191515);
	public static final DeferredHolder<EntityType<?>, EntityType<Animal>> VOLIANT = registerAnimal("voliant", VoliantEntity::new, 3.5f, 4.75f, 0x195E7A, 0x1A6A84);
	public static final DeferredHolder<EntityType<?>, EntityType<ShikEntity>> SHIK = registerAnimal("shik", ShikEntity::new, 0.375f, 0.4375f, 0x444444, 0x606060);

	private static <T extends Mob> DeferredHolder<EntityType<?>, EntityType<T>> registerAnimal(String registryName, EntityType.EntityFactory<T> factory, float width, float height, int primaryEggColour, int secondaryEggColour) {
		return registerAnimal(registryName, factory, MobCategory.CREATURE, width, height, primaryEggColour, secondaryEggColour, builder -> {});
	}

	private static <T extends Mob> DeferredHolder<EntityType<?>, EntityType<T>> registerAnimal(String registryName, EntityType.EntityFactory<T> factory, MobCategory classification, float width, float height, int primaryEggColour, int secondaryEggColour) {
		return registerAnimal(registryName, factory, classification, width, height, primaryEggColour, secondaryEggColour, builder -> {});
	}

	private static <T extends Mob> DeferredHolder<EntityType<?>, EntityType<T>> registerAnimal(String registryName, EntityType.EntityFactory<T> factory, MobCategory classification, float width, float height, int primaryEggColour, int secondaryEggColour, Consumer<EntityType.Builder<T>> typeBuilderConsumer) {
		EntityType.Builder<T> typeBuilder = EntityType.Builder.of(factory, classification).sized(width, height).clientTrackingRange(10);

		typeBuilderConsumer.accept(typeBuilder);

		DeferredHolder<EntityType<?>, EntityType<T>> registryObject = AoARegistries.ENTITIES.register(registryName, () -> {
			boolean dataFixers = SharedConstants.CHECK_DATA_FIXER_SCHEMA;
			SharedConstants.CHECK_DATA_FIXER_SCHEMA = false;
			EntityType<T> entityType = typeBuilder.build(registryName);
			SharedConstants.CHECK_DATA_FIXER_SCHEMA = dataFixers;

			return entityType;
		});

		if (primaryEggColour != -1)
			AoAItems.registerItem(registryName + "_spawn_egg", () -> new DeferredSpawnEggItem(registryObject, primaryEggColour, secondaryEggColour, new Item.Properties()), CreativeModeTabs.SPAWN_EGGS);


		return registryObject;
	}
}
