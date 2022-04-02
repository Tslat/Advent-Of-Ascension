package net.tslat.aoa3.common.registration.entity;

import net.minecraft.SharedConstants;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.RegistryObject;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.content.entity.animal.*;
import net.tslat.aoa3.content.entity.animal.fish.BasicFishEntity;
import net.tslat.aoa3.content.entity.animal.fish.BasicLavaFishEntity;
import net.tslat.aoa3.content.entity.misc.pixon.*;
import net.tslat.aoa3.content.entity.mob.haven.AngelicaEntity;
import net.tslat.aoa3.content.entity.mob.haven.DawnlightEntity;
import net.tslat.aoa3.content.entity.mob.haven.RainicornEntity;

import java.util.function.Consumer;

public final class AoAAnimals {
	public static void init() {}

	public static final RegistryObject<EntityType<Animal>> CORATEE = registerAnimal("coratee", CorateeEntity::new, MobCategory.WATER_AMBIENT, 1f, 1f,  40623, 7236);
	public static final RegistryObject<EntityType<Animal>> CREEP_COW = registerAnimal("creep_cow", CreepCowEntity::new, 0.9f, 1.4f, 408837, 15135462);
	public static final RegistryObject<EntityType<Animal>> ELKANYNE = registerAnimal("elkanyne", ElkanyneEntity::new, 0.85f, 1.1875f, 4471327, 7695709);
	public static final RegistryObject<EntityType<Animal>> HALYCON = registerAnimal("halycon", HalyconEntity::new, 0.9f, 1.4f, 2683401, 734469);

	public static final RegistryObject<EntityType<ShinySquidEntity>> SHINY_SQUID = registerAnimal("shiny_squid", ShinySquidEntity::new, MobCategory.WATER_CREATURE, 0.8f, 0.8f, 24217, 978934, builder -> builder.clientTrackingRange(8));
	public static final RegistryObject<EntityType<BasicLavaFishEntity>> CANDLEFISH = registerAnimal("candlefish", BasicLavaFishEntity::new, MobCategory.WATER_AMBIENT, 0.7f, 0.4f, 739037, 199385, builder -> builder.clientTrackingRange(4).fireImmune());
	public static final RegistryObject<EntityType<BasicLavaFishEntity>> CHARRED_CHAR = registerAnimal("charred_char", BasicLavaFishEntity::new, MobCategory.WATER_AMBIENT, 0.7f, 0.4f, 1119770, 3384054, builder -> builder.clientTrackingRange(4).fireImmune());
	public static final RegistryObject<EntityType<BasicFishEntity>> CHOCAW = registerAnimal("chocaw", BasicFishEntity::new, MobCategory.WATER_AMBIENT, 0.7f, 0.4f, 1258848, 1258848, builder -> builder.clientTrackingRange(4));
	public static final RegistryObject<EntityType<BasicLavaFishEntity>> CRIMSON_SKIPPER = registerAnimal("crimson_skipper", BasicLavaFishEntity::new, MobCategory.WATER_AMBIENT, 0.7f, 0.4f, 6174935, 4131984, builder -> builder.clientTrackingRange(4).fireImmune());
	public static final RegistryObject<EntityType<BasicLavaFishEntity>> CRIMSON_STRIPEFISH = registerAnimal("crimson_stripefish", BasicLavaFishEntity::new, MobCategory.WATER_AMBIENT, 0.7f, 0.4f, 5856681, 13682943, builder -> builder.clientTrackingRange(4).fireImmune());
	public static final RegistryObject<EntityType<BasicFishEntity>> DARK_HATCHETFISH = registerAnimal("dark_hatchetfish", BasicFishEntity::new, MobCategory.WATER_AMBIENT, 0.7f, 0.4f, 5323811, 4892782, builder -> builder.clientTrackingRange(4));
	public static final RegistryObject<EntityType<BasicFishEntity>> BLUE_GEMTRAP = registerAnimal("blue_gemtrap", BasicFishEntity::new, MobCategory.WATER_AMBIENT, 0.7f, 0.4f, 6570505, 15658578, builder -> builder.clientTrackingRange(4));
	public static final RegistryObject<EntityType<BasicFishEntity>> GREEN_GEMTRAP = registerAnimal("green_gemtrap", BasicFishEntity::new, MobCategory.WATER_AMBIENT, 0.7f, 0.4f, 687370, 1370063, builder -> builder.clientTrackingRange(4));
	public static final RegistryObject<EntityType<BasicFishEntity>> PURPLE_GEMTRAP = registerAnimal("purple_gemtrap", BasicFishEntity::new, MobCategory.WATER_AMBIENT, 0.7f, 0.4f, 9513058, 15027428, builder -> builder.clientTrackingRange(4));
	public static final RegistryObject<EntityType<BasicFishEntity>> RED_GEMTRAP = registerAnimal("red_gemtrap", BasicFishEntity::new, MobCategory.WATER_AMBIENT, 0.7f, 0.4f, 1256895, 6285047, builder -> builder.clientTrackingRange(4));
	public static final RegistryObject<EntityType<BasicFishEntity>> WHITE_GEMTRAP = registerAnimal("white_gemtrap", BasicFishEntity::new, MobCategory.WATER_AMBIENT, 0.7f, 0.4f, 9803157, 16250871, builder -> builder.clientTrackingRange(4));
	public static final RegistryObject<EntityType<BasicFishEntity>> YELLOW_GEMTRAP = registerAnimal("yellow_gemtrap", BasicFishEntity::new, MobCategory.WATER_AMBIENT, 0.7f, 0.4f, 610148, 6277111, builder -> builder.clientTrackingRange(4));
	public static final RegistryObject<EntityType<BasicFishEntity>> HYDRONE = registerAnimal("hydrone", BasicFishEntity::new, MobCategory.WATER_AMBIENT, 0.7f, 0.4f, 4869452, 1261627, builder -> builder.clientTrackingRange(4));
	public static final RegistryObject<EntityType<BasicFishEntity>> IRONBACK = registerAnimal("ironback", BasicFishEntity::new, MobCategory.WATER_AMBIENT, 0.7f, 0.4f, 8816741, 7303023, builder -> builder.clientTrackingRange(4));
	public static final RegistryObject<EntityType<BasicFishEntity>> JAMFISH = registerAnimal("jamfish", BasicFishEntity::new, MobCategory.WATER_AMBIENT, 0.7f, 0.4f, 16749559, 9706088, builder -> builder.clientTrackingRange(4));
	public static final RegistryObject<EntityType<BasicFishEntity>> PARAPIRANHA = registerAnimal("parapiranha", BasicFishEntity::new, MobCategory.WATER_AMBIENT, 0.7f, 0.4f, 728166, 1514273, builder -> builder.clientTrackingRange(4));
	public static final RegistryObject<EntityType<BasicFishEntity>> PEARL_STRIPEFISH = registerAnimal("pearl_stripefish", BasicFishEntity::new, MobCategory.WATER_AMBIENT, 0.7f, 0.4f, 16709631, 9205136, builder -> builder.clientTrackingRange(4));
	public static final RegistryObject<EntityType<BasicFishEntity>> RAINBOWFISH = registerAnimal("rainbowfish", BasicFishEntity::new, MobCategory.WATER_AMBIENT, 0.7f, 0.4f, 6284953, 10071794, builder -> builder.clientTrackingRange(4));
	public static final RegistryObject<EntityType<BasicFishEntity>> RAZORFISH = registerAnimal("razorfish", BasicFishEntity::new, MobCategory.WATER_AMBIENT, 0.7f, 0.4f, 4480882, 6052956, builder -> builder.clientTrackingRange(4));
	public static final RegistryObject<EntityType<BasicFishEntity>> REEFTOOTH = registerAnimal("reeftooth", BasicFishEntity::new, MobCategory.WATER_AMBIENT, 0.7f, 0.4f, 7960386, 6312555, builder -> builder.clientTrackingRange(4));
	public static final RegistryObject<EntityType<BasicFishEntity>> ROCKETFISH = registerAnimal("rocketfish", BasicFishEntity::new, MobCategory.WATER_AMBIENT, 0.7f, 0.4f, 994987, 10796527, builder -> builder.clientTrackingRange(4));
	public static final RegistryObject<EntityType<BasicFishEntity>> SAILBACK = registerAnimal("sailback", BasicFishEntity::new, MobCategory.WATER_AMBIENT, 0.7f, 0.4f, 14339700, 16250097, builder -> builder.clientTrackingRange(4));
	public static final RegistryObject<EntityType<BasicFishEntity>> SAPPHIRE_STRIDER = registerAnimal("sapphire_strider", BasicFishEntity::new, MobCategory.WATER_AMBIENT, 0.7f, 0.4f, 16621128, 16771764, builder -> builder.clientTrackingRange(4));
	public static final RegistryObject<EntityType<BasicFishEntity>> SKELECANTH = registerAnimal("skelecanth", BasicFishEntity::new, MobCategory.WATER_AMBIENT, 0.7f, 0.4f, 14016993, 10133150, builder -> builder.clientTrackingRange(4));
	public static final RegistryObject<EntityType<BasicFishEntity>> TURQUOISE_STRIPEFISH = registerAnimal("turquoise_stripefish", BasicFishEntity::new, MobCategory.WATER_AMBIENT, 0.7f, 0.4f, 11446887, 16773832, builder -> builder.clientTrackingRange(4));
	public static final RegistryObject<EntityType<BasicFishEntity>> VIOLET_SKIPPER = registerAnimal("violet_skipper", BasicFishEntity::new, MobCategory.WATER_AMBIENT, 0.7f, 0.4f, 11557499, 14776495, builder -> builder.clientTrackingRange(4));

	public static final RegistryObject<EntityType<Animal>> ANGELICA = registerAnimal("angelica", AngelicaEntity::new, 0.6f, 2f, 3579029, 13290663);
	public static final RegistryObject<EntityType<Animal>> DAWNLIGHT = registerAnimal("dawnlight", DawnlightEntity::new, 0.6875f, 1.375f, 10796201, 1948221);
	public static final RegistryObject<EntityType<Animal>> EEO = registerAnimal("eeo", EeoEntity::new, 0.375f, 1.25f, 15921906, 5155053);
	public static final RegistryObject<EntityType<Animal>> NIGHT_WATCHER = registerAnimal("night_watcher", NightWatcherEntity::new, 0.6f, 2.7f, 723977, 592898);
	public static final RegistryObject<EntityType<Animal>> PEPPERMINT_SNAIL = registerAnimal("peppermint_snail", PeppermintSnailEntity::new, 0.5f, 0.8125f, 14162711, 14849941);
	public static final RegistryObject<EntityType<Animal>> RAINICORN = registerAnimal("rainicorn", RainicornEntity::new, 1.4f, 1.3125f, 15066083, 7836541);
	public static final RegistryObject<EntityType<Animal>> SPEARMINT_SNAIL = registerAnimal("spearmint_snail", SpearmintSnailEntity::new, 0.5f, 0.8125f, 521247, 14938853);
	public static final RegistryObject<EntityType<Animal>> TROTTER = registerAnimal("trotter", TrotterEntity::new, 0.75f, 1.1875f, 16213252, 16051204);
	public static final RegistryObject<EntityType<Animal>> URKA = registerAnimal("urka", UrkaEntity::new, 1.2f, 1.5f, 1644569, 15987444);
	public static final RegistryObject<EntityType<Animal>> VOLIANT = registerAnimal("voliant", VoliantEntity::new, 3.5f, 4.75f, 811936, 4096951);
	public static final RegistryObject<EntityType<MeganeuropsisEntity>> MEGANEUROPSIS = registerAnimal("meganeuropsis", MeganeuropsisEntity::new, 0.5f, 0.4375f, 16763904, 5986352);
	public static final RegistryObject<EntityType<ShikEntity>> SHIK = registerAnimal("shik", ShikEntity::new, 0.375f, 0.4375f, 7500402, 3947580);

	public static final RegistryObject<EntityType<PixonEntity>> AMBIENT_PIXON = registerAnimal("ambient_pixon", AmbientPixonEntity::new, MobCategory.AMBIENT, 0.9f, 1.3f, 12235956, 12039354);
	public static final RegistryObject<EntityType<PixonEntity>> BLOOMING_PIXON = registerAnimal("blooming_pixon", BloomingPixonEntity::new, MobCategory.AMBIENT, 0.9f, 1.3f, 10292975, 13540842);
	public static final RegistryObject<EntityType<PixonEntity>> GLARING_PIXON = registerAnimal("glaring_pixon", GlaringPixonEntity::new, MobCategory.AMBIENT, 0.9f, 1.3f, 2628099, 2827805);
	public static final RegistryObject<EntityType<PixonEntity>> GLEAMING_PIXON = registerAnimal("gleaming_pixon", GleamingPixonEntity::new, MobCategory.AMBIENT, 0.9f, 1.3f, 1549800, 2830387);
	public static final RegistryObject<EntityType<PixonEntity>> GLISTENING_PIXON = registerAnimal("glistening_pixon", GlisteningPixonEntity::new, MobCategory.AMBIENT, 0.9f, 1.3f, 977425, 2370852);
	public static final RegistryObject<EntityType<PixonEntity>> GLOWING_PIXON = registerAnimal("glowing_pixon", GlowingPixonEntity::new, MobCategory.AMBIENT, 0.9f, 1.3f, 16190476, 2170141);
	public static final RegistryObject<EntityType<PixonEntity>> RADIANT_PIXON = registerAnimal("radiant_pixon", RadiantPixonEntity::new, MobCategory.AMBIENT, 0.9f, 1.3f, 2689714, 1641556);
	public static final RegistryObject<EntityType<PixonEntity>> SHINING_PIXON = registerAnimal("shining_pixon", ShiningPixonEntity::new, MobCategory.AMBIENT, 0.9f, 1.3f, 13868556, 9341827);

	private static <T extends Mob> RegistryObject<EntityType<T>> registerAnimal(String registryName, EntityType.EntityFactory<T> factory, float width, float height, int primaryEggColour, int secondaryEggColour) {
		return registerAnimal(registryName, factory, MobCategory.CREATURE, width, height, primaryEggColour, secondaryEggColour, builder -> {});
	}

	private static <T extends Mob> RegistryObject<EntityType<T>> registerAnimal(String registryName, EntityType.EntityFactory<T> factory, MobCategory classification, float width, float height, int primaryEggColour, int secondaryEggColour) {
		return registerAnimal(registryName, factory, classification, width, height, primaryEggColour, secondaryEggColour, builder -> {});
	}

	private static <T extends Mob> RegistryObject<EntityType<T>> registerAnimal(String registryName, EntityType.EntityFactory<T> factory, MobCategory classification, float width, float height, int primaryEggColour, int secondaryEggColour, Consumer<EntityType.Builder<T>> typeBuilderConsumer) {
		EntityType.Builder<T> typeBuilder = EntityType.Builder.of(factory, classification).sized(width, height).clientTrackingRange(10);

		typeBuilderConsumer.accept(typeBuilder);

		RegistryObject<EntityType<T>> registryObject = AoARegistries.ENTITIES.register(registryName, () -> {
			boolean dataFixers = SharedConstants.CHECK_DATA_FIXER_SCHEMA;
			SharedConstants.CHECK_DATA_FIXER_SCHEMA = false;
			EntityType<T> entityType = typeBuilder.build(registryName);
			SharedConstants.CHECK_DATA_FIXER_SCHEMA = dataFixers;

			return entityType;
		});

		if (primaryEggColour != -1)
			AoARegistries.ITEMS.register(registryName + "_spawn_egg", () -> new ForgeSpawnEggItem(registryObject, primaryEggColour, secondaryEggColour, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

		return registryObject;
	}
}
