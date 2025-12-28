package net.tslat.aoa3.common.registration.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.entity.animal.ShinySquidEntity;
import net.tslat.aoa3.content.entity.animal.barathos.ArkbackEntity;
import net.tslat.aoa3.content.entity.animal.barathos.EmperorBeastEntity;
import net.tslat.aoa3.content.entity.animal.barathos.MasonBeetleEntity;
import net.tslat.aoa3.content.entity.animal.fish.BasicFishEntity;
import net.tslat.aoa3.content.entity.animal.fish.BasicLavaFishEntity;
import net.tslat.aoa3.content.entity.animal.precasia.DeinotheriumEntity;
import net.tslat.aoa3.content.entity.animal.precasia.HorndronEntity;
import net.tslat.aoa3.content.entity.animal.precasia.OpteryxEntity;

public final class AoAAnimals {
	public static void init() {}

	//public static final DeferredHolder<EntityType<?>, EntityType<CorateeEntity>> CORATEE = registerAnimal("coratee", CorateeEntity::new, MobCategory.WATER_AMBIENT, 1f, 1f,  0x325156, 0x55797E);
	//public static final DeferredHolder<EntityType<?>, EntityType<Animal>> CREEP_COW = registerAnimal("creep_cow", CreepCowEntity::new, 0.9f, 1.4f, 0x0CAB1A, 0xE0E9DB);
	//public static final DeferredHolder<EntityType<?>, EntityType<Animal>> HALYCON = registerAnimal("halycon", HalyconEntity::new, 0.9f, 1.4f, 0x2B5019, 0x82A94F);

	public static final DeferredHolder<EntityType<?>, EntityType<ShinySquidEntity>> SHINY_SQUID = register("shiny_squid", EntityTypeRegistrar.ambientWaterMob(ShinySquidEntity::new).sized(0.8f, 0.8f, 0.4F).spawnEgg(0xC7A003, 0x885000).clientTrackingRange(8));
	public static final DeferredHolder<EntityType<?>, EntityType<BasicLavaFishEntity>> CANDLEFISH = register("candlefish", EntityTypeRegistrar.ambientWaterMob(BasicLavaFishEntity::new).sized(0.7f, 0.4f, 0.26f).fireImmune().spawnEgg(0xD93610, 0xF88F12));
	public static final DeferredHolder<EntityType<?>, EntityType<BasicLavaFishEntity>> CHARRED_CHAR = register("charred_char", EntityTypeRegistrar.ambientWaterMob(BasicLavaFishEntity::new).sized(0.7f, 0.4f, 0.26f).fireImmune().spawnEgg(0x1F1812, 0xD87C1D));
	public static final DeferredHolder<EntityType<?>, EntityType<BasicFishEntity>> CHOCAW = register("chocaw", EntityTypeRegistrar.ambientWaterMob(BasicFishEntity::new).sized(0.7f, 0.4f).spawnEgg(0x5E3412, 0x48260A));
	public static final DeferredHolder<EntityType<?>, EntityType<BasicLavaFishEntity>> CRIMSON_SKIPPER = register("crimson_skipper", EntityTypeRegistrar.ambientWaterMob(BasicLavaFishEntity::new).sized(0.7f, 0.4f, 0.26f).fireImmune().spawnEgg(0xA50539, 0xD7385E));
	public static final DeferredHolder<EntityType<?>, EntityType<BasicLavaFishEntity>> CRIMSON_STRIPEFISH = register("crimson_stripefish", EntityTypeRegistrar.ambientWaterMob(BasicLavaFishEntity::new).sized(0.7f, 0.4f, 0.26f).fireImmune().spawnEgg(0xA14A43, 0xBD7C7F));
	public static final DeferredHolder<EntityType<?>, EntityType<BasicFishEntity>> DARK_HATCHETFISH = register("dark_hatchetfish", EntityTypeRegistrar.ambientWaterMob(BasicFishEntity::new).sized(0.7f, 0.4f).spawnEgg(0xA14A43, 0xBD7C7F).clientTrackingRange(4));
	public static final DeferredHolder<EntityType<?>, EntityType<BasicFishEntity>> BLUE_GEMTRAP = register("blue_gemtrap", EntityTypeRegistrar.ambientWaterMob(BasicFishEntity::new).sized(0.7f, 0.4f).spawnEgg(0x094467, 0x136F9F).clientTrackingRange(4));
	public static final DeferredHolder<EntityType<?>, EntityType<BasicFishEntity>> GREEN_GEMTRAP = register("green_gemtrap", EntityTypeRegistrar.ambientWaterMob(BasicFishEntity::new).sized(0.7f, 0.4f).spawnEgg(0x0A6B0A, 0x308F17).clientTrackingRange(4));
	public static final DeferredHolder<EntityType<?>, EntityType<BasicFishEntity>> PURPLE_GEMTRAP = register("purple_gemtrap", EntityTypeRegistrar.ambientWaterMob(BasicFishEntity::new).sized(0.7f, 0.4f).spawnEgg(0x480A6B, 0x69188B).clientTrackingRange(4));
	public static final DeferredHolder<EntityType<?>, EntityType<BasicFishEntity>> RED_GEMTRAP = register("red_gemtrap", EntityTypeRegistrar.ambientWaterMob(BasicFishEntity::new).sized(0.7f, 0.4f).spawnEgg(0x83200D, 0xA53819).clientTrackingRange(4));
	public static final DeferredHolder<EntityType<?>, EntityType<BasicFishEntity>> WHITE_GEMTRAP = register("white_gemtrap", EntityTypeRegistrar.ambientWaterMob(BasicFishEntity::new).sized(0.7f, 0.4f).spawnEgg(0x696969, 0xA3A3A3).clientTrackingRange(4));
	public static final DeferredHolder<EntityType<?>, EntityType<BasicFishEntity>> YELLOW_GEMTRAP = register("yellow_gemtrap", EntityTypeRegistrar.ambientWaterMob(BasicFishEntity::new).sized(0.7f, 0.4f).spawnEgg(0x6B590A, 0x8F6C16).clientTrackingRange(4));
	public static final DeferredHolder<EntityType<?>, EntityType<BasicFishEntity>> HYDRONE = register("hydrone", EntityTypeRegistrar.ambientWaterMob(BasicFishEntity::new).sized(0.7f, 0.4f).spawnEgg(0x3E4025, 0x56554C).clientTrackingRange(4));
	public static final DeferredHolder<EntityType<?>, EntityType<BasicFishEntity>> IRONBACK = register("ironback", EntityTypeRegistrar.ambientWaterMob(BasicFishEntity::new).sized(0.7f, 0.4f).spawnEgg(0x6F8E8C, 0x435251).clientTrackingRange(4));
	public static final DeferredHolder<EntityType<?>, EntityType<BasicFishEntity>> JAMFISH = register("jamfish", EntityTypeRegistrar.ambientWaterMob(BasicFishEntity::new).sized(0.7f, 0.4f).spawnEgg(0x641D8D, 0xEC81F2).clientTrackingRange(4));
	public static final DeferredHolder<EntityType<?>, EntityType<BasicFishEntity>> PARAPIRANHA = register("parapiranha", EntityTypeRegistrar.ambientWaterMob(BasicFishEntity::new).sized(0.7f, 0.4f).spawnEgg(0x5F1C0A, 0x251D19).clientTrackingRange(4));
	public static final DeferredHolder<EntityType<?>, EntityType<BasicFishEntity>> PEARL_STRIPEFISH = register("pearl_stripefish", EntityTypeRegistrar.ambientWaterMob(BasicFishEntity::new).sized(0.7f, 0.4f).spawnEgg(0xFBF4FA, 0xD8C9D6).clientTrackingRange(4));
	public static final DeferredHolder<EntityType<?>, EntityType<BasicFishEntity>> RAINBOWFISH = register("rainbowfish", EntityTypeRegistrar.ambientWaterMob(BasicFishEntity::new).sized(0.7f, 0.4f).spawnEgg(0xC76D6D, 0x47AC94).clientTrackingRange(4));
	public static final DeferredHolder<EntityType<?>, EntityType<BasicFishEntity>> RAZORFISH = register("razorfish", EntityTypeRegistrar.ambientWaterMob(BasicFishEntity::new).sized(0.7f, 0.4f).spawnEgg(0x6C685F, 0x40382D).clientTrackingRange(4));
	public static final DeferredHolder<EntityType<?>, EntityType<BasicFishEntity>> REEFTOOTH = register("reeftooth", EntityTypeRegistrar.ambientWaterMob(BasicFishEntity::new).sized(0.7f, 0.4f).spawnEgg(0x427779, 0x3C5556).clientTrackingRange(4));
	public static final DeferredHolder<EntityType<?>, EntityType<BasicFishEntity>> ROCKETFISH = register("rocketfish", EntityTypeRegistrar.ambientWaterMob(BasicFishEntity::new).sized(0.7f, 0.4f).spawnEgg(0xAB2E0F, 0x961106).clientTrackingRange(4));
	public static final DeferredHolder<EntityType<?>, EntityType<BasicFishEntity>> SAILBACK = register("sailback", EntityTypeRegistrar.ambientWaterMob(BasicFishEntity::new).sized(0.7f, 0.4f).spawnEgg(0x5CAECB, 0xD0E7ED).clientTrackingRange(4));
	public static final DeferredHolder<EntityType<?>, EntityType<BasicFishEntity>> SAPPHIRE_STRIDER = register("sapphire_strider", EntityTypeRegistrar.ambientWaterMob(BasicFishEntity::new).sized(0.7f, 0.4f).spawnEgg(0x489EFD, 0x3C20B2).clientTrackingRange(4));
	public static final DeferredHolder<EntityType<?>, EntityType<BasicFishEntity>> SKELECANTH = register("skelecanth", EntityTypeRegistrar.ambientWaterMob(BasicFishEntity::new).sized(0.7f, 0.4f).spawnEgg(0xDADAD1, 0x7C7C7A).clientTrackingRange(4));
	public static final DeferredHolder<EntityType<?>, EntityType<BasicFishEntity>> TURQUOISE_STRIPEFISH = register("turquoise_stripefish", EntityTypeRegistrar.ambientWaterMob(BasicFishEntity::new).sized(0.7f, 0.4f).spawnEgg(0x5CA0A9, 0xA9DBE2).clientTrackingRange(4));
	public static final DeferredHolder<EntityType<?>, EntityType<BasicFishEntity>> VIOLET_SKIPPER = register("violet_skipper", EntityTypeRegistrar.ambientWaterMob(BasicFishEntity::new).sized(0.7f, 0.4f).spawnEgg(0x814FBB, 0xF5F9F6).clientTrackingRange(4));

	public static final DeferredHolder<EntityType<?>, EntityType<HorndronEntity>> HORNDRON = register("horndron", EntityTypeRegistrar.creature(HorndronEntity::new).sized(1.5f, 2f, 1.34375f).spawnEgg(0x332521, 0x5F523A));
	public static final DeferredHolder<EntityType<?>, EntityType<DeinotheriumEntity>> DEINOTHERIUM = register("deinotherium", EntityTypeRegistrar.creature(DeinotheriumEntity::new).sized(1.5f, 3.125f, 2.5f).spawnEgg(0x797168, 0x595149));
	public static final DeferredHolder<EntityType<?>, EntityType<OpteryxEntity>> OPTERYX = register("opteryx", EntityTypeRegistrar.creature(OpteryxEntity::new).sized(0.5f, 1.25f, 1.125f).spawnEgg(0xBA912C, 0x785205));

	public static final DeferredHolder<EntityType<?>, EntityType<ArkbackEntity>> ARKBACK = register("arkback", EntityTypeRegistrar.creature(ArkbackEntity::new).sized(5f, 4f, 3f).spawnEgg(0x63514B, 0x3C2923));
	public static final DeferredHolder<EntityType<?>, EntityType<EmperorBeastEntity>> EMPEROR_BEAST = register("emperor_beast", EntityTypeRegistrar.creature(EmperorBeastEntity::new).sized(5f, 8.5f, 13f).spawnEgg(0x4F4444, 0xC9B5A2));
	public static final DeferredHolder<EntityType<?>, EntityType<MasonBeetleEntity>> MASON_BEETLE = register("mason_beetle", EntityTypeRegistrar.creature(MasonBeetleEntity::new).sized(0.534375f, 0.4375f, 0.390625f).spawnEgg(0x2F2727, 0x5A4D4D));

	//public static final DeferredHolder<EntityType<?>, EntityType<Animal>> ANGELICA = registerAnimal("angelica", AngelicaEntity::new, 0.6f, 2f, 0x146262, 0xB4B4B4);
	//public static final DeferredHolder<EntityType<?>, EntityType<Animal>> DAWNLIGHT = registerAnimal("dawnlight", DawnlightEntity::new, 0.6875f, 1.375f, 0xD8DAD2, 0xEBEBEA);
	//public static final DeferredHolder<EntityType<?>, EntityType<Animal>> EEO = registerAnimal("eeo", EeoEntity::new, 0.375f, 1.25f, 0xDADDDD, 0xE4E5E5);
	//public static final DeferredHolder<EntityType<?>, EntityType<Animal>> NIGHT_WATCHER = registerAnimal("night_watcher", NightWatcherEntity::new, 0.6f, 2.7f, 0x040403, 0x393828);
	//public static final DeferredHolder<EntityType<?>, EntityType<Animal>> PEPPERMINT_SNAIL = registerAnimal("peppermint_snail", PeppermintSnailEntity::new, 0.5f, 0.8125f, 0xE9E9E9, 0xF81414);
	//public static final DeferredHolder<EntityType<?>, EntityType<Animal>> RAINICORN = registerAnimal("rainicorn", RainicornEntity::new, 1.4f, 1.3125f, 0xCAD0B6, 0xDBDFD7);
	//public static final DeferredHolder<EntityType<?>, EntityType<Animal>> SPEARMINT_SNAIL = registerAnimal("spearmint_snail", SpearmintSnailEntity::new, 0.5f, 0.8125f, 0xEAEAEA, 0x26A620);
	//public static final DeferredHolder<EntityType<?>, EntityType<Animal>> TROTTER = registerAnimal("trotter", TrotterEntity::new, 0.75f, 1.1875f, 0xC02A00, 0xAC2400);
	//public static final DeferredHolder<EntityType<?>, EntityType<Animal>> URKA = registerAnimal("urka", UrkaEntity::new, 1.2f, 1.5f, 0x545454, 0x191515);
	//public static final DeferredHolder<EntityType<?>, EntityType<Animal>> VOLIANT = registerAnimal("voliant", VoliantEntity::new, 3.5f, 4.75f, 0x195E7A, 0x1A6A84);
	//public static final DeferredHolder<EntityType<?>, EntityType<ShikEntity>> SHIK = registerAnimal("shik", ShikEntity::new, 0.375f, 0.4375f, 0x444444, 0x606060);

	private static <T extends Mob> DeferredHolder<EntityType<?>, EntityType<T>> register(String registryName, EntityTypeRegistrar<T> builder) {
		final DeferredHolder<EntityType<?>, EntityType<T>> registryObject = AoARegistries.ENTITIES.register(registryName, () -> builder.build(registryName));

		if (builder.hasSpawnEgg())
			AoAItems.registerItem(registryName + "_spawn_egg", () -> new DeferredSpawnEggItem(registryObject, 0xFFFFFFFF/*builder.getSpawnEggBackgroundColour()*/, 0xFFFFFFFF/*builder.getSpawnEggDotsColour()*/, new Item.Properties()), CreativeModeTabs.SPAWN_EGGS);

		return registryObject;
	}
}
