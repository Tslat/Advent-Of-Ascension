package net.tslat.aoa3.common.registration.item;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredItem;
import net.tslat.aoa3.advent.AoAStartupCache;
import net.tslat.aoa3.common.registration.AoABannerPatterns;
import net.tslat.aoa3.common.registration.AoACreativeModeTabs;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.content.item.food.*;
import net.tslat.aoa3.content.item.lootbox.*;
import net.tslat.aoa3.content.item.misc.*;
import net.tslat.aoa3.content.item.misc.summoning.*;
import net.tslat.effectslib.api.util.EffectBuilder;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public final class AoAItems {
	public static void init() {}

	public static final DeferredItem<Item> BLOODSTONE = registerItem("bloodstone", miscItem());
	public static final DeferredItem<Item> CRYSTALLITE = registerItem("crystallite", miscItem());
	public static final DeferredItem<Item> GEMENYTE = registerItem("gemenyte", miscItem());
	public static final DeferredItem<Item> JADE = registerItem("jade", miscItem());
	public static final DeferredItem<Item> JEWELYTE = registerItem("jewelyte", miscItem());
	public static final DeferredItem<Item> ORNAMYTE = registerItem("ornamyte", miscItem());
	public static final DeferredItem<Item> SHYREGEM = registerItem("shyregem", miscItem());
	public static final DeferredItem<Item> RUNIUM_CHUNK = registerItem("runium_chunk", miscItem());
	public static final DeferredItem<Item> CHESTBONE_FRAGMENT = registerItem("chestbone_fragment", miscItem());
	public static final DeferredItem<Item> FOOTBONE_FRAGMENT = registerItem("footbone_fragment", miscItem());
	public static final DeferredItem<Item> LEGBONE_FRAGMENT = registerItem("legbone_fragment", miscItem());
	public static final DeferredItem<Item> SKULLBONE_FRAGMENT = registerItem("skullbone_fragment", miscItem());
	public static final DeferredItem<Item> CHARGED_RUNIUM_CHUNK = registerItem("charged_runium_chunk", miscItem());
	public static final DeferredItem<Item> BLUE_CRYSTAL = registerItem("blue_crystal", miscItem());
	public static final DeferredItem<Item> GREEN_CRYSTAL = registerItem("green_crystal", miscItem());
	public static final DeferredItem<Item> PURPLE_CRYSTAL = registerItem("purple_crystal", miscItem());
	public static final DeferredItem<Item> RED_CRYSTAL = registerItem("red_crystal", miscItem());
	public static final DeferredItem<Item> WHITE_CRYSTAL = registerItem("white_crystal", miscItem());
	public static final DeferredItem<Item> YELLOW_CRYSTAL = registerItem("yellow_crystal", miscItem());
	public static final DeferredItem<Item> BARONYTE_INGOT = registerItem("baronyte_ingot", miscItem());
	public static final DeferredItem<Item> BLAZIUM_INGOT = registerItem("blazium_ingot", miscItem());
	public static final DeferredItem<Item> ELECANIUM_INGOT = registerItem("elecanium_ingot", miscItem());
	public static final DeferredItem<Item> EMBERSTONE_INGOT = registerItem("emberstone_ingot", miscItem());
	public static final DeferredItem<Item> GHASTLY_INGOT = registerItem("ghastly_ingot", miscItem());
	public static final DeferredItem<Item> GHOULISH_INGOT = registerItem("ghoulish_ingot", miscItem());
	public static final DeferredItem<Item> LIMONITE_INGOT = registerItem("limonite_ingot", miscItem());
	public static final DeferredItem<Item> LUNAR_INGOT = registerItem("lunar_ingot", miscItem());
	public static final DeferredItem<Item> LYON_INGOT = registerItem("lyon_ingot", miscItem());
	public static final DeferredItem<Item> MYSTITE_INGOT = registerItem("mystite_ingot", miscItem());
	public static final DeferredItem<Item> SHYRESTONE_INGOT = registerItem("shyrestone_ingot", miscItem());
	public static final DeferredItem<Item> SKELETAL_INGOT = registerItem("skeletal_ingot", miscItem());
	public static final DeferredItem<Item> VARSIUM_INGOT = registerItem("varsium_ingot", miscItem());

	public static final DeferredItem<Item> RAW_LIMONITE = registerItem("raw_limonite", miscItem());
	public static final DeferredItem<Item> RAW_EMBERSTONE = registerItem("raw_emberstone", miscItem());

	public static final DeferredItem<Item> BARONYTE_NUGGET = registerItem("baronyte_nugget", miscItem());
	public static final DeferredItem<Item> BLAZIUM_NUGGET = registerItem("blazium_nugget", miscItem());
	public static final DeferredItem<Item> ELECANIUM_NUGGET = registerItem("elecanium_nugget", miscItem());
	public static final DeferredItem<Item> EMBERSTONE_NUGGET = registerItem("emberstone_nugget", miscItem());
	public static final DeferredItem<Item> GHASTLY_NUGGET = registerItem("ghastly_nugget", miscItem());
	public static final DeferredItem<Item> GHOULISH_NUGGET = registerItem("ghoulish_nugget", miscItem());
	public static final DeferredItem<Item> LIMONITE_NUGGET = registerItem("limonite_nugget", miscItem());
	public static final DeferredItem<Item> LUNAR_NUGGET = registerItem("lunar_nugget", miscItem());
	public static final DeferredItem<Item> LYON_NUGGET = registerItem("lyon_nugget", miscItem());
	public static final DeferredItem<Item> MYSTITE_NUGGET = registerItem("mystite_nugget", miscItem());
	public static final DeferredItem<Item> SHYRESTONE_NUGGET = registerItem("shyrestone_nugget", miscItem());
	public static final DeferredItem<Item> SKELETAL_NUGGET = registerItem("skeletal_nugget", miscItem());
	public static final DeferredItem<Item> VARSIUM_NUGGET = registerItem("varsium_nugget", miscItem());

	public static final DeferredItem<Item> UNPOWERED_RUNE = registerItem("unpowered_rune", () -> new RuneSource(1));
	public static final DeferredItem<Item> CHARGED_RUNE = registerItem("charged_rune", () -> new RuneSource(2));
	public static final DeferredItem<Item> COMPASS_RUNE = registerItem("compass_rune", miscItem());
	public static final DeferredItem<Item> DISTORTION_RUNE = registerItem("distortion_rune", miscItem());
	public static final DeferredItem<Item> ENERGY_RUNE = registerItem("energy_rune", miscItem());
	public static final DeferredItem<Item> FIRE_RUNE = registerItem("fire_rune", miscItem());
	public static final DeferredItem<Item> KINETIC_RUNE = registerItem("kinetic_rune", miscItem());
	public static final DeferredItem<Item> LIFE_RUNE = registerItem("life_rune", miscItem());
	public static final DeferredItem<Item> LUNAR_RUNE = registerItem("lunar_rune", miscItem());
	public static final DeferredItem<Item> POISON_RUNE = registerItem("poison_rune", miscItem());
	public static final DeferredItem<Item> POWER_RUNE = registerItem("power_rune", miscItem());
	public static final DeferredItem<Item> STORM_RUNE = registerItem("storm_rune", miscItem());
	public static final DeferredItem<Item> STRIKE_RUNE = registerItem("strike_rune", miscItem());
	public static final DeferredItem<Item> WATER_RUNE = registerItem("water_rune", miscItem());
	public static final DeferredItem<Item> WIND_RUNE = registerItem("wind_rune", miscItem());
	public static final DeferredItem<Item> WITHER_RUNE = registerItem("wither_rune", miscItem());

	public static final DeferredItem<Item> ASHFERN = registerItem("ashfern", miscFuelItem(800));
	public static final DeferredItem<Item> ACTIVE_RUNE_STONE = registerItem("active_rune_stone", miscItem());
	public static final DeferredItem<Item> ALIEN_ORB = registerItem("alien_orb", () -> new ReservedItem("alien_orb"));
	public static final DeferredItem<Item> ARMOUR_PLATING = registerItem("armour_plating", miscItem());
	public static final DeferredItem<Item> BLANK_SLAB = registerItem("blank_slab", miscItem());
	public static final DeferredItem<Item> CHITIN = registerItem("chitin", miscItem());
	public static final DeferredItem<Item> CIRCUS_COIN = registerItem("circus_coin", miscItem());
	public static final DeferredItem<Item> COPPER_COIN = registerItem("copper_coin", miscItem());
	public static final DeferredItem<Item> SILVER_COIN = registerItem("silver_coin", miscItem());
	public static final DeferredItem<Item> GOLD_COIN = registerItem("gold_coin", miscItem());
	public static final DeferredItem<Item> LUNAVER_COIN = registerItem("lunaver_coin", miscItem());
	public static final DeferredItem<Item> CONFETTI_PILE = registerItem("confetti_pile", miscFuelItem(10));
	public static final DeferredItem<Item> CORAL_STONE = registerItem("coral_stone", miscItem());
	public static final DeferredItem<Item> COSMIC_DUST = registerItem("cosmic_dust", miscItem());
	public static final DeferredItem<Item> CUP = registerItem("cup", miscItem());
	public static final DeferredItem<Item> DARK_BONES = registerItem("dark_bones", () -> new ReservedItem("alien_orb"), (ResourceKey<CreativeModeTab>[])null);
	public static final DeferredItem<Item> DARKLY_POWDER = registerItem("darkly_powder", miscItem());
	public static final DeferredItem<Item> DENSE_ROCK = registerItem("dense_rock", miscItem());
	public static final DeferredItem<Item> BLUE_DRUSE = registerItem("blue_druse", miscItem());
	public static final DeferredItem<Item> GREEN_DRUSE = registerItem("green_druse", miscItem());
	public static final DeferredItem<Item> PURPLE_DRUSE = registerItem("purple_druse", miscItem());
	public static final DeferredItem<Item> RAINBOW_DRUSE = registerItem("rainbow_druse", miscItem());
	public static final DeferredItem<Item> RED_DRUSE = registerItem("red_druse", miscItem());
	public static final DeferredItem<Item> WHITE_DRUSE = registerItem("white_druse", miscItem());
	public static final DeferredItem<Item> YELLOW_DRUSE = registerItem("yellow_druse", miscItem());
	public static final DeferredItem<Item> ENCHANTED_GUNPOWDER = registerItem("enchanted_gunpowder", miscItem());
	public static final DeferredItem<Item> EYE_BULB = registerItem("eye_bulb", () -> new ItemNameBlockItem(AoABlocks.EYE_BULB_CROP.get(), new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(0.4f).build())));
	public static final DeferredItem<Item> FLAMMABLE_DUST = registerItem("flammable_dust", miscFuelItem(100));
	public static final DeferredItem<Item> FLESHY_BONES = registerItem("fleshy_bones", () -> new ReservedItem("alien_orb"), (ResourceKey<CreativeModeTab>[])null);
	public static final DeferredItem<Item> FLOATING_STONE = registerItem("floating_stone", FloatingStone::new);
	public static final DeferredItem<Item> BLUE_GEMSTONES = registerItem("blue_gemstones", miscItem());
	public static final DeferredItem<Item> GREEN_GEMSTONES = registerItem("green_gemstones", miscItem());
	public static final DeferredItem<Item> PURPLE_GEMSTONES = registerItem("purple_gemstones", miscItem());
	public static final DeferredItem<Item> RED_GEMSTONES = registerItem("red_gemstones", miscItem());
	public static final DeferredItem<Item> WHITE_GEMSTONES = registerItem("white_gemstones", miscItem());
	public static final DeferredItem<Item> YELLOW_GEMSTONES = registerItem("yellow_gemstones", miscItem());
	public static final DeferredItem<Item> GHOSTLY_POWDER = registerItem("ghostly_powder", miscItem());
	public static final DeferredItem<Item> GHOULASM = registerItem("ghoulasm", miscItem());
	public static final DeferredItem<Item> HARDENED_CONFETTI_BALL = registerItem("hardened_confetti_ball", miscFuelItem(120));
	public static final DeferredItem<Item> HIVE_CHUNK = registerItem("hive_chunk", HiveChunk::new);
	public static final DeferredItem<Item> ICE_CRYSTAL = registerItem("ice_crystal", miscItem());
	public static final DeferredItem<Item> IVORY = registerItem("ivory", miscItem());
	public static final DeferredItem<Item> JUNGLE_THORNS = registerItem("jungle_thorns", miscItem());
	public static final DeferredItem<Item> LIMONITE_ROD = registerItem("limonite_rod", miscItem());
	public static final DeferredItem<Item> LOTTO_TOTEM = registerItem("lotto_totem", LottoTotem::new);
	public static final DeferredItem<Item> LUNARADE_MUG = registerItem("lunarade_mug", miscItem());
	public static final DeferredItem<Item> METAL_TUB = registerItem("metal_tub", miscItem());
	public static final DeferredItem<Item> MAGIC_MENDING_COMPOUND = registerItem("magic_mending_compound", () -> new TooltipItem(1, new Item.Properties()));
	public static final DeferredItem<Item> MAGIC_MENDING_SOLUTION = registerItem("magic_mending_solution", MagicMendingSolution::new);
	public static final DeferredItem<Item> MAGIC_REPAIR_DUST = registerItem("magic_repair_dust", () -> new TooltipItem(1, new Item.Properties()));
	public static final DeferredItem<Item> MAGNET_SHARD = registerItem("magnet_shard", miscItem());
	public static final DeferredItem<Item> MECHA_GEAR = registerItem("mecha_gear", miscItem());
	public static final DeferredItem<Item> BLUE_MEGA_RUNE_FRAGMENT = registerItem("blue_mega_rune_fragment", miscItem());
	public static final DeferredItem<Item> GREEN_MEGA_RUNE_FRAGMENT = registerItem("green_mega_rune_fragment", miscItem());
	public static final DeferredItem<Item> RED_MEGA_RUNE_FRAGMENT = registerItem("red_mega_rune_fragment", miscItem());
	public static final DeferredItem<Item> YELLOW_MEGA_RUNE_FRAGMENT = registerItem("yellow_mega_rune_fragment", miscItem());
	public static final DeferredItem<Item> MILLENNIUM_UPGRADER = registerItem("millennium_upgrader", () -> new ReservedItem("alien_orb"));
	public static final DeferredItem<Item> MOLTEN_UPGRADER = registerItem("molten_upgrader", () -> new ReservedItem("alien_orb"));
	public static final DeferredItem<Item> MOONSTONE = registerItem("moonstone", () -> new ReservedItem("alien_orb"));
	public static final DeferredItem<Item> MUSHROOM_SPORES = registerItem("mushroom_spores", miscItem());
	public static final DeferredItem<Item> NIGHTMARE_FLAKES = registerItem("nightmare_flakes", miscItem());
	public static final DeferredItem<Item> OLD_BOOT = registerItem("old_boot", OldBoot::new);
	public static final DeferredItem<Item> ORANGE_SPORES = registerItem("orange_spores", miscItem());
	public static final DeferredItem<Item> ORBULON = registerItem("orbulon", miscItem());
	public static final DeferredItem<Item> PADDED_CLOTH = registerItem("padded_cloth", miscFuelItem(110));
	public static final DeferredItem<Item> PHANTASM = registerItem("phantasm", miscItem());
	public static final DeferredItem<Item> POWER_CORE = registerItem("power_core", miscItem());
	public static final DeferredItem<Item> PRIMED_GHOULASM = registerItem("primed_ghoulasm", miscItem());
	public static final DeferredItem<Item> PRIMORDIAL_SKULL = registerItem("primordial_skull", miscItem());
	public static final DeferredItem<Item> REINFORCED_CLOTH = registerItem("reinforced_cloth", miscFuelItem(90));
	public static final DeferredItem<Item> RETURN_CRYSTAL = registerItem("return_crystal", ReturnCrystal::new);
	public static final DeferredItem<Item> ROCK_BONES = registerItem("rock_bones", () -> new ReservedItem("alien_orb"));
	public static final DeferredItem<Item> ROSID_ROOT = registerItem("rosid_root", miscItem());
	public static final DeferredItem<Item> RUNIC_ENERGY = registerItem("runic_energy", miscItem());
	public static final DeferredItem<Item> SCRAP_METAL = registerItem("scrap_metal", miscItem());
	public static final DeferredItem<Item> SCREAM_SHIELD = registerItem("scream_shield", miscItem());
	public static final DeferredItem<Item> SHARP_CLAW = registerItem("sharp_claw", miscItem());
	public static final DeferredItem<Item> SMALL_BLUE_PETAL = registerItem("small_blue_petal", miscItem());
	public static final DeferredItem<Item> SMALL_GREEN_PETAL = registerItem("small_green_petal", miscItem());
	public static final DeferredItem<Item> SMALL_ORANGE_PETAL = registerItem("small_orange_petal", miscItem());
	public static final DeferredItem<Item> SMALL_PURPLE_PETAL = registerItem("small_purple_petal", miscItem());
	public static final DeferredItem<Item> SMALL_RED_PETAL = registerItem("small_red_petal", miscItem());
	public static final DeferredItem<Item> STICKY_SLIME = registerItem("sticky_slime", miscItem());
	public static final DeferredItem<Item> TEA_SHREDDINGS = registerItem("tea_shreddings", miscItem());
	public static final DeferredItem<Item> THORNY_PETALS = registerItem("thorny_petals", miscItem());
	public static final DeferredItem<Item> TORN_CLOTH = registerItem("torn_cloth", miscItem());
	public static final DeferredItem<Item> TOXIC_LUMP = registerItem("toxic_lump", miscItem());
	public static final DeferredItem<Item> UNCHARGED_STONE = registerItem("uncharged_stone", miscItem());
	public static final DeferredItem<Item> UNSTABLE_GUNPOWDER = registerItem("unstable_gunpowder", miscItem());
	public static final DeferredItem<Item> URKA_HIDE = registerItem("urka_hide", miscItem());
	public static final DeferredItem<Item> VOID_SCALES = registerItem("void_scales", miscItem());
	public static final DeferredItem<Item> BATTLE_VULCANE_AUGMENT = registerItem("battle_vulcane_augment", miscItem());
	public static final DeferredItem<Item> EQUALITY_VULCANE_AUGMENT = registerItem("equality_vulcane_augment", miscItem());
	public static final DeferredItem<Item> FIRE_VULCANE_AUGMENT = registerItem("fire_vulcane_augment", miscItem());
	public static final DeferredItem<Item> IMPAIRMENT_VULCANE_AUGMENT = registerItem("impairment_vulcane_augment", miscItem());
	public static final DeferredItem<Item> POISON_VULCANE_AUGMENT = registerItem("poison_vulcane_augment", miscItem());
	public static final DeferredItem<Item> POWER_VULCANE_AUGMENT = registerItem("power_vulcane_augment", miscItem());
	public static final DeferredItem<Item> WITHER_VULCANE_AUGMENT = registerItem("wither_vulcane_augment", miscItem());
	public static final DeferredItem<Item> WEAPON_PARTS = registerItem("weapon_parts", miscItem());
	public static final DeferredItem<Item> WHITEWASHING_SOLUTION = registerItem("whitewashing_solution", miscItem());
	public static final DeferredItem<Item> WORN_BOOK = registerItem("worn_book", WornBook::new);
	public static final DeferredItem<Item> TORN_PAGES = registerItem("torn_pages", TornPages::new, (ResourceKey<CreativeModeTab>[])null);
	public static final DeferredItem<Item> YELLOW_SPORES = registerItem("yellow_spores", miscItem());
	public static final DeferredItem<Item> ZHINX_DUST = registerItem("zhinx_dust", miscItem());
	public static final DeferredItem<Item> CREATURE_ESSENCE = registerItem("creature_essence", CreatureEssence::new, (ResourceKey<CreativeModeTab>[])null);

	public static final DeferredItem<Item> BALLOON = registerItem("balloon", () -> new Item(new Item.Properties()), AoACreativeModeTabs.AMMUNITION.getKey());
	public static final DeferredItem<Item> CANNONBALL = registerItem("cannonball", () -> new Item(new Item.Properties()), AoACreativeModeTabs.AMMUNITION.getKey());
	public static final DeferredItem<Item> DISCHARGE_CAPSULE = registerItem("discharge_capsule", () -> new Item(new Item.Properties()), AoACreativeModeTabs.AMMUNITION.getKey());
	public static final DeferredItem<Item> LIMONITE_BULLET = registerItem("limonite_bullet", () -> new Item(new Item.Properties()), AoACreativeModeTabs.AMMUNITION.getKey());
	public static final DeferredItem<Item> METAL_SLUG = registerItem("metal_slug", () -> new Item(new Item.Properties()), AoACreativeModeTabs.AMMUNITION.getKey());
	public static final DeferredItem<Item> POP_SHOT = registerItem("pop_shot", () -> new ArrowItem(new Item.Properties()), AoACreativeModeTabs.AMMUNITION.getKey());
	public static final DeferredItem<Item> SPREADSHOT = registerItem("spreadshot", () -> new Item(new Item.Properties()), AoACreativeModeTabs.AMMUNITION.getKey());

	public static final DeferredItem<Item> BLASTER_FRAME = registerItem("blaster_frame", miscItem());
	public static final DeferredItem<Item> CROSSBOW_FRAME = registerItem("crossbow_frame", miscItem());
	public static final DeferredItem<Item> BOOTS_FRAME = registerItem("boots_frame", miscItem());
	public static final DeferredItem<Item> LEGGINGS_FRAME = registerItem("leggings_frame", miscItem());
	public static final DeferredItem<Item> CHESTPLATE_FRAME = registerItem("chestplate_frame", miscItem());
	public static final DeferredItem<Item> HELMET_FRAME = registerItem("helmet_frame", miscItem());
	public static final DeferredItem<Item> CANNON_FRAME = registerItem("cannon_frame", miscItem());
	public static final DeferredItem<Item> GUN_FRAME = registerItem("gun_frame", miscItem());
	public static final DeferredItem<Item> SHOTGUN_FRAME = registerItem("shotgun_frame", miscItem());
	public static final DeferredItem<Item> SNIPER_FRAME = registerItem("sniper_frame", miscItem());

	public static final DeferredItem<Item> EXPLOSIVE_IDOL = registerItem("explosive_idol", ExplosiveIdol::new);
	public static final DeferredItem<Item> NETHENGEIC_CALLSTONE = registerItem("nethengeic_callstone", NethengeicCallstone::new);
	public static final DeferredItem<Item> TROLL_IDOL = registerItem("troll_idol", TrollIdol::new);
	public static final DeferredItem<Item> BONE_HORN = registerItem("bone_horn", BoneHorn::new);
	public static final DeferredItem<Item> WARPED_HORN = registerItem("warped_horn", WarpedHorn::new);

	public static final DeferredItem<Item> AMPHIBIYTE_LUNG = registerItem("amphibiyte_lung", miscItem()); // TODO
	public static final DeferredItem<Item> ANCIENT_RING = registerItem("ancient_ring", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<Item> BOOK_OF_SHADOWS = registerItem("book_of_shadows", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<Item> BOULDER_DASH = registerItem("boulder_dash", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<Item> CALL_OF_THE_DRAKE = registerItem("call_of_the_drake", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<Item> EXPLOSIVE_GEMS = registerItem("explosive_gems", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<Item> GIANT_CRYSTAL = registerItem("giant_crystal", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<Item> GOLD_SPRING = registerItem("gold_spring", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<Item> GUARDIANS_EYE = registerItem("guardians_eye", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<Item> HAUNTED_IDOL = registerItem("haunted_idol", miscItem()); // TODO
	public static final DeferredItem<Item> HEAVY_BOULDER = registerItem("heavy_boulder", miscItem()); // TODO
	public static final DeferredItem<Item> HIVE_EGG = registerItem("hive_egg", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<Item> MEGA_RUNE_STONE = registerItem("mega_rune_stone", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON).durability(5).setNoRepair()));
	public static final DeferredItem<Item> OBSERVING_EYE = registerItem("observing_eye", miscItem()); // TODO
	public static final DeferredItem<Item> PETALS = registerItem("petals", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<Item> SHROOM_STONE = registerItem("shroom_stone", miscItem()); // TODO
	public static final DeferredItem<Item> STARING_EYE = registerItem("staring_eye", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<Item> TOY_GYROCOPTER = registerItem("toy_gyrocopter", miscItem()); // TODO
	public static final DeferredItem<Item> TREAT_BAG = registerItem("treat_bag", miscItem()); // TODO
	public static final DeferredItem<Item> VILE_STONE = registerItem("vile_stone", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<Item> VOLIANT_HEART = registerItem("voliant_heart", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<Item> WARLOCK_GEM = registerItem("warlock_gem", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<Item> ARCHAIC_TOKEN = registerItem("archaic_token", miscItem());

	public static final DeferredItem<Item> ABYSSAL_UPGRADE_KIT = registerItem("abyssal_upgrade_kit", () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
	public static final DeferredItem<Item> ANCIENT_UPGRADE_KIT = registerItem("ancient_upgrade_kit", () -> new Item(new Item.Properties().rarity(Rarity.RARE))); // TODO Obtain Method
	public static final DeferredItem<Item> APOCO_UPGRADE_KIT = registerItem("apoco_upgrade_kit", () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
	public static final DeferredItem<Item> CLOWN_UPGRADE_KIT = registerItem("clown_upgrade_kit", () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
	public static final DeferredItem<Item> DARKLY_UPGRADE_KIT = registerItem("darkly_upgrade_kit", () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
	public static final DeferredItem<Item> FLORO_UPGRADE_KIT = registerItem("floro_upgrade_kit", () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
	public static final DeferredItem<Item> FUNGAL_UPGRADE_KIT = registerItem("fungal_upgrade_kit", () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
	public static final DeferredItem<Item> GOLDEN_UPGRADE_KIT = registerItem("golden_upgrade_kit", () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
	public static final DeferredItem<Item> HAUNTED_UPGRADE_KIT = registerItem("haunted_upgrade_kit", () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
	public static final DeferredItem<Item> LELYETIAN_UPGRADE_KIT = registerItem("lelyetian_upgrade_kit", () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
	public static final DeferredItem<Item> LIGHT_UPGRADE_KIT = registerItem("light_upgrade_kit", () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
	public static final DeferredItem<Item> LUNAR_UPGRADE_KIT = registerItem("lunar_upgrade_kit", () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
	public static final DeferredItem<Item> NETHER_UPGRADE_KIT = registerItem("nether_upgrade_kit", () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
	public static final DeferredItem<Item> PRECASIAN_UPGRADE_KIT = registerItem("precasian_upgrade_kit", () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
	public static final DeferredItem<Item> PREDATOR_UPGRADE_KIT = registerItem("predator_upgrade_kit", () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
	public static final DeferredItem<Item> ROCKY_UPGRADE_KIT = registerItem("rocky_upgrade_kit", () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
	public static final DeferredItem<Item> SEASIDE_UPGRADE_KIT = registerItem("seaside_upgrade_kit", () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
	public static final DeferredItem<Item> SMILEY_UPGRADE_KIT = registerItem("smiley_upgrade_kit", () -> new Item(new Item.Properties().rarity(Rarity.RARE)));

	public static final DeferredItem<Item> WATERLOGGED_AQUA_CANNON = registerItem("waterlogged_aqua_cannon", () -> new WaterloggedItem(AoAWeapons.AQUA_CANNON, new Item.Properties()));
	public static final DeferredItem<Item> WATERLOGGED_CORAL_CANNON = registerItem("waterlogged_coral_cannon", () -> new WaterloggedItem(AoAWeapons.CORAL_CANNON, new Item.Properties()));
	public static final DeferredItem<Item> WATERLOGGED_CORAL_CLOGGER = registerItem("waterlogged_coral_clogger", () -> new WaterloggedItem(AoAWeapons.CORAL_CLOGGER, new Item.Properties()));
	public static final DeferredItem<Item> WATERLOGGED_CORAL_CROSSBOW = registerItem("waterlogged_coral_crossbow", () -> new WaterloggedItem(AoAWeapons.CORAL_CROSSBOW, new Item.Properties()));
	public static final DeferredItem<Item> WATERLOGGED_REEFER = registerItem("waterlogged_reefer", () -> new WaterloggedItem(AoAWeapons.REEFER, new Item.Properties()));

	public static final DeferredItem<Item> INCOMPLETE_MECHA_BOW = registerItem("incomplete_mecha_bow", miscItem());
	public static final DeferredItem<Item> INCOMPLETE_MECHA_CANNON = registerItem("incomplete_mecha_cannon", miscItem());
	public static final DeferredItem<Item> INCOMPLETE_MECHA_CROSSBOW = registerItem("incomplete_mecha_crossbow", miscItem());
	public static final DeferredItem<Item> INCOMPLETE_MECHANICAL_ASSAULT_RIFLE = registerItem("incomplete_mechanical_assault_rifle", miscItem());
	public static final DeferredItem<Item> INCOMPLETE_MECHA_STAFF = registerItem("incomplete_mecha_staff", miscItem());
	public static final DeferredItem<Item> INCOMPLETE_MECHYRO = registerItem("incomplete_mechyro", miscItem());

	public static final DeferredItem<Item> BLANK_REALMSTONE = registerItem("blank_realmstone", BlankRealmstone::new);
	public static final DeferredItem<Item> ABYSS_REALMSTONE = registerItem("abyss_realmstone", () -> new Realmstone(null/*AoABlocks.ABYSS_PORTAL*/, "abyss"));
	public static final DeferredItem<Item> NETHER_REALMSTONE = registerItem("nether_realmstone", () -> new Realmstone(AoABlocks.NETHER_PORTAL, "nether"));
	public static final DeferredItem<Item> BARATHOS_REALMSTONE = registerItem("barathos_realmstone", () -> new Realmstone(null/*AoABlocks.BARATHOS_PORTAL*/, "barathos"));
	public static final DeferredItem<Item> LBOREAN_REALMSTONE = registerItem("lborean_realmstone", () -> new Realmstone(null/*AoABlocks.LBOREAN_PORTAL*/, "lborean"));
	public static final DeferredItem<Item> CANDYLAND_REALMSTONE = registerItem("candyland_realmstone", () -> new Realmstone(null/*AoABlocks.CANDYLAND_PORTAL*/, "candyland"));
	public static final DeferredItem<Item> CELEVE_REALMSTONE = registerItem("celeve_realmstone", () -> new Realmstone(null/*AoABlocks.CELEVE_PORTAL*/, "celeve"));
	public static final DeferredItem<Item> CREEPONIA_REALMSTONE = registerItem("creeponia_realmstone", () -> new Realmstone(null/*AoABlocks.CREEPONIA_PORTAL*/, "creeponia"));
	public static final DeferredItem<Item> CRYSTEVIA_REALMSTONE = registerItem("crystevia_realmstone", () -> new Realmstone(null/*AoABlocks.CRYSTEVIA_PORTAL*/, "crystevia"));
	public static final DeferredItem<Item> DEEPLANDS_REALMSTONE = registerItem("deeplands_realmstone", () -> new Realmstone(null/*AoABlocks.DEEPLANDS_PORTAL*/, "deeplands"));
	public static final DeferredItem<Item> DUSTOPIA_REALMSTONE = registerItem("dustopia_realmstone", () -> new Realmstone(null/*AoABlocks.DUSTOPIA_PORTAL*/, "dustopia"));
	public static final DeferredItem<Item> GARDENCIA_REALMSTONE = registerItem("gardencia_realmstone", () -> new Realmstone(null/*AoABlocks.GARDENCIA_PORTAL*/, "gardencia"));
	public static final DeferredItem<Item> GRECKON_REALMSTONE = registerItem("greckon_realmstone", () -> new Realmstone(null/*AoABlocks.GRECKON_PORTAL*/, "greckon"));
	public static final DeferredItem<Item> HAVEN_REALMSTONE = registerItem("haven_realmstone", () -> new Realmstone(null/*AoABlocks.HAVEN_PORTAL*/, "haven"));
	public static final DeferredItem<Item> IROMINE_REALMSTONE = registerItem("iromine_realmstone", () -> new Realmstone(null/*AoABlocks.IROMINE_PORTAL*/, "iromine"));
	public static final DeferredItem<Item> LELYETIA_REALMSTONE = registerItem("lelyetia_realmstone", () -> new Realmstone(null/*AoABlocks.LELYETIA_PORTAL*/, "lelyetia"));
	public static final DeferredItem<Item> LUNALUS_REALMSTONE = registerItem("lunalus_realmstone", () -> new Realmstone(null/*AoABlocks.LUNALUS_PORTAL*/, "lunalus"));
	public static final DeferredItem<Item> MYSTERIUM_REALMSTONE = registerItem("mysterium_realmstone", () -> new Realmstone(null/*AoABlocks.MYSTERIUM_PORTAL*/, "mysterium"));
	public static final DeferredItem<Item> NOWHERE_REALMSTONE = registerItem("nowhere_realmstone", () -> new Realmstone(AoABlocks.NOWHERE_PORTAL, "nowhere"));
	public static final DeferredItem<Item> PRECASIA_REALMSTONE = registerItem("precasia_realmstone", () -> new Realmstone(AoABlocks.PRECASIA_PORTAL, "precasia"));
	public static final DeferredItem<Item> RUNANDOR_REALMSTONE = registerItem("runandor_realmstone", () -> new Realmstone(null/*AoABlocks.RUNANDOR_PORTAL*/, "runandor"));
	public static final DeferredItem<Item> SHYRELANDS_REALMSTONE = registerItem("shyrelands_realmstone", () -> new Realmstone(null/*AoABlocks.SHYRELANDS_PORTAL*/, "shyrelands"));
	public static final DeferredItem<Item> VOX_PONDS_REALMSTONE = registerItem("vox_ponds_realmstone", () -> new Realmstone(null/*AoABlocks.VOX_PONDS_PORTAL*/, "vox_ponds"));

	public static final DeferredItem<Item> AMBIENT_POWER_STONE = registerItem("ambient_power_stone", miscItem());
	public static final DeferredItem<Item> BLOOMING_POWER_STONE = registerItem("blooming_power_stone", miscItem());
	public static final DeferredItem<Item> GLARING_POWER_STONE = registerItem("glaring_power_stone", miscItem());
	public static final DeferredItem<Item> GLEAMING_POWER_STONE = registerItem("gleaming_power_stone", miscItem());
	public static final DeferredItem<Item> GLISTENING_POWER_STONE = registerItem("glistening_power_stone", miscItem());
	public static final DeferredItem<Item> GLOWING_POWER_STONE = registerItem("glowing_power_stone", miscItem());
	public static final DeferredItem<Item> RADIANT_POWER_STONE = registerItem("radiant_power_stone", miscItem());
	public static final DeferredItem<Item> SHINING_POWER_STONE = registerItem("shining_power_stone", miscItem());
	
	public static final DeferredItem<Item> AMBIENT_INFUSION_STONE = registerItem("ambient_infusion_stone", () -> new InfusionStone(20, 20.0f, AMBIENT_POWER_STONE));
	public static final DeferredItem<Item> BLOOMING_INFUSION_STONE = registerItem("blooming_infusion_stone", () -> new InfusionStone(80, 300.0f, BLOOMING_POWER_STONE));
	public static final DeferredItem<Item> GLARING_INFUSION_STONE = registerItem("glaring_infusion_stone", () -> new InfusionStone(30, 40.0f, GLARING_POWER_STONE));
	public static final DeferredItem<Item> GLEAMING_INFUSION_STONE = registerItem("gleaming_infusion_stone", () -> new InfusionStone(15, 16.0f, GLEAMING_POWER_STONE));
	public static final DeferredItem<Item> GLISTENING_INFUSION_STONE = registerItem("glistening_infusion_stone", () -> new InfusionStone(5, 8.0f, GLISTENING_POWER_STONE));
	public static final DeferredItem<Item> GLOWING_INFUSION_STONE = registerItem("glowing_infusion_stone", () -> new InfusionStone(45, 85.0f, GLOWING_POWER_STONE));
	public static final DeferredItem<Item> RADIANT_INFUSION_STONE = registerItem("radiant_infusion_stone", () -> new InfusionStone(70, 220.0f, RADIANT_POWER_STONE));
	public static final DeferredItem<Item> SHINING_INFUSION_STONE = registerItem("shining_infusion_stone", () -> new InfusionStone(60, 150.0f, SHINING_POWER_STONE));
	
	public static final DeferredItem<Item> GIANT_SKILL_CRYSTAL = registerItem("giant_skill_crystal", () -> new SkillCrystal(4, Rarity.EPIC));
	public static final DeferredItem<Item> LARGE_SKILL_CRYSTAL = registerItem("large_skill_crystal", () -> new SkillCrystal(8, Rarity.RARE));
	public static final DeferredItem<Item> MEDIUM_SKILL_CRYSTAL = registerItem("medium_skill_crystal", () -> new SkillCrystal(12, Rarity.UNCOMMON));
	public static final DeferredItem<Item> SMALL_SKILL_CRYSTAL = registerItem("small_skill_crystal", () -> new SkillCrystal(15));

	public static final DeferredItem<Item> CRYSTAL_BOX = registerItem("crystal_box", CrystalBox::new);
	public static final DeferredItem<Item> GEM_BAG = registerItem("gem_bag", GemBag::new);
	public static final DeferredItem<Item> RUNE_BOX = registerItem("rune_box", RuneBox::new);
	public static final DeferredItem<Item> SHINY_BOX = registerItem("shiny_box", ShinyBox::new);
	public static final DeferredItem<Item> TREASURE_BOX = registerItem("treasure_box", TreasureBox::new);
	public static final DeferredItem<Item> WEAPONS_CASE = registerItem("weapons_case", WeaponsCase::new);

	public static final DeferredItem<Item> BLUE_GEMTRAP = registerItem("blue_gemtrap", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(0.2f).build())), AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> CANDLEFISH = registerItem("candlefish", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(0.2f).build())), AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> CHARRED_CHAR = registerItem("charred_char", CharredChar::new);
	public static final DeferredItem<Item> CHOCAW = registerItem("chocaw", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(6).saturationMod(0.65f).build())), AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> CRIMSON_SKIPPER = registerItem("crimson_skipper", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(0.2f).build())), AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> CRIMSON_STRIPEFISH = registerItem("crimson_stripefish", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(0.2f).build())), AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> DARK_HATCHETFISH = registerItem("dark_hatchetfish", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(0.2f).build())), AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> GREEN_GEMTRAP = registerItem("green_gemtrap", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(0.2f).build())), AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> HYDRONE = registerItem("hydrone", miscItem());
	public static final DeferredItem<Item> IRONBACK = registerItem("ironback", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(0.2f).build())), AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> JAMFISH = registerItem("jamfish", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(5).saturationMod(0.5f).effect(() -> new EffectBuilder(MobEffects.MOVEMENT_SPEED, 600).build(), 1f).build())), AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> PARAPIRANHA = registerItem("parapiranha", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(0.2f).build())), AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> PEARL_STRIPEFISH = registerItem("pearl_stripefish", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(0.2f).build())), AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> PURPLE_GEMTRAP = registerItem("purple_gemtrap", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(0.2f).build())), AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> RAZORFISH = registerItem("razorfish", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(0.2f).build())), AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> RED_GEMTRAP = registerItem("red_gemtrap", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(0.2f).build())), AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> REEFTOOTH = registerItem("reeftooth", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(0.2f).build())), AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> ROCKETFISH = registerItem("rocketfish", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(0.2f).build())), AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> SAILBACK = registerItem("sailback", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(0.2f).build())), AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> SAPPHIRE_STRIDER = registerItem("sapphire_strider", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(0.2f).build())), AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> SKELECANTH = registerItem("skelecanth", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.1f).build())), AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> WHITE_GEMTRAP = registerItem("white_gemtrap", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(0.2f).build())), AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> YELLOW_GEMTRAP = registerItem("yellow_gemtrap", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(0.2f).build())), AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> TURQUOISE_STRIPEFISH = registerItem("turquoise_stripefish", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(0.2f).build())), AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> VIOLET_SKIPPER = registerItem("violet_skipper", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(0.2f).build())), AoACreativeModeTabs.FOOD.getKey());

	public static final DeferredItem<Item> RAW_RAINBOWFISH = registerItem("raw_rainbowfish", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationMod(0.85f).build())), AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> COOKED_RAINBOWFISH = registerItem("cooked_rainbowfish", () -> new HealingFood(5.0f, new Item.Properties().food(new FoodProperties.Builder().nutrition(6).saturationMod(0.85f).effect(() -> new EffectBuilder(MobEffects.LUCK, 600).build(), 1f).build())), AoACreativeModeTabs.FOOD.getKey());

	public static final DeferredItem<Item> BUBBLE_BERRIES = registerItem("bubble_berries", BubbleBerries::new, AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> CANDY_CANE = registerItem("candy_cane", () -> new Item(new Item.Properties().food(AoAFood.CANDY_CANE)), AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> CANDY_CORN = registerItem("candy_corn", () -> new Item(new Item.Properties().food(AoAFood.CANDY_CORN)), AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> CHILLI = registerItem("chilli", Chilli::new, AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> RAW_CHARGER_SHANK = registerItem("raw_charger_shank", () -> new Item(new Item.Properties().food(AoAFood.RAW_CHARGER_SHANK)), AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> COOKED_CHARGER_SHANK = registerItem("cooked_charger_shank", () -> new Item(new Item.Properties().food(AoAFood.COOKED_CHARGER_SHANK)), AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> RAW_HALYCON_BEEF = registerItem("raw_halycon_beef", () -> new Item(new Item.Properties().food(AoAFood.RAW_HALYCON_BEEF)), AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> COOKED_HALYCON_BEEF = registerItem("cooked_halycon_beef", () -> new TooltipItem(1, new Item.Properties().food(AoAFood.COOKED_HALYCON_BEEF)), AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> RAW_GIANT_LIZARD_MEAT = registerItem("raw_giant_lizard_meat", () -> new Item(new Item.Properties().food(AoAFood.RAW_GIANT_LIZARD_MEAT)), AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> COOKED_GIANT_LIZARD_MEAT = registerItem("cooked_giant_lizard_meat", () -> new Item(new Item.Properties().food(AoAFood.COOKED_GIANT_LIZARD_MEAT)), AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> EYE_CANDY = registerItem("eye_candy", EyeCandy::new, AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> FIERY_CHOPS = registerItem("fiery_chops", FieryChops::new, AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> FLORACLE_STICKS = registerItem("floracle_sticks", FloracleSticks::new, AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> GINGERBREAD_COOKIE = registerItem("gingerbread_cookie", () -> new Item(new Item.Properties().food(AoAFood.GINGERBREAD_COOKIE)), AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> GINGERBREAD_WING = registerItem("gingerbread_wing", () -> new Item(new Item.Properties().food(AoAFood.GINGERBREAD_WING)), AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> GOLDICAP_PETALS = registerItem("goldicap_petals", GoldicapPetals::new, AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> HEART_FRUIT = registerItem("heart_fruit", HeartFruit::new, AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> HOT_ROD = registerItem("hot_rod", HotRod::new, AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> LUNACRIKE = registerItem("lunacrike", Lunacrike::new, AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> LUNA_GLOBE = registerItem("luna_globe", () -> new TooltipItem(1, new Item.Properties().food(AoAFood.LUNA_GLOBE)), AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> LUNALONS = registerItem("lunalons", () -> new TooltipItem(1, new Item.Properties().food(AoAFood.LUNALONS)), AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> MAGIC_MARANG = registerItem("magic_marang", MagicMarang::new, AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> MYSTIC_SHROOMS = registerItem("mystic_shrooms", () -> new ItemNameBlockItem(AoABlocks.MYSTIC_SHROOM_CROP.get(), new Item.Properties().food(AoAFood.MYSTIC_SHROOMS)), AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> NATURE_MELON_SLICE = registerItem("nature_melon_slice", () -> new Item(new Item.Properties().food(AoAFood.NATURE_MELON_SLICE)), AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> PEPPERMINT_CANDY = registerItem("peppermint_candy", () -> new Item(new Item.Properties().food(AoAFood.PEPPERMINT_CANDY)), AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> ROSIDONS = registerItem("rosidons", Rosidons::new, AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> SOUR_CANDY = registerItem("sour_candy", () -> new Item(new Item.Properties().food(AoAFood.SOUR_CANDY)), AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> SOUR_GUMMY = registerItem("sour_gummy", () -> new Item(new Item.Properties().food(AoAFood.SOUR_GUMMY)), AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> SOUR_POP = registerItem("sour_pop", () -> new Item(new Item.Properties().food(AoAFood.SOUR_POP)), AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> SPEARMINT_CANDY = registerItem("spearmint_candy", () -> new Item(new Item.Properties().food(AoAFood.SPEARMINT_CANDY)), AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> TRILLIAD_LEAVES = registerItem("trilliad_leaves", TrilliadLeaves::new, AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> YETI_FINGERNAILS = registerItem("yeti_fingernails", YetiFingernails::new, (ResourceKey<CreativeModeTab>[])null);
	public static final DeferredItem<Item> HALYCON_MILK = registerItem("halycon_milk", HalyconMilk::new, AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> LUNARADE = registerItem("lunarade", Lunarade::new, AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> TEA = registerItem("tea", Tea::new, AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> FUNGAL_TEA = registerItem("fungal_tea", FungalTea::new, AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> NATURAL_TEA = registerItem("natural_tea", NaturalTea::new, AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> COOKED_FISH = registerItem("cooked_fish", () -> new Item(new Item.Properties().food(AoAFood.COOKED_FISH)), AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> CHUM = registerItem("chum", ChumItem::new, AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> CHUM_BURGER = registerItem("chum_burger", () -> new Item(new Item.Properties().food(AoAFood.CHUM_BURGER)), AoACreativeModeTabs.FOOD.getKey());
	public static final DeferredItem<Item> CHUM_AND_SALAD_BURGER = registerItem("chum_and_salad_burger", () -> new Item(new Item.Properties().food(AoAFood.CHUM_AND_SALAD_BURGER)), AoACreativeModeTabs.FOOD.getKey());

	public static final DeferredItem<Item> ASHFERN_SEEDS = registerItem("ashfern_seeds", () -> new ItemNameBlockItem(AoABlocks.ASHFERN_CROP.get(), new Item.Properties()));
	public static final DeferredItem<Item> BUBBLE_BERRY_SEEDS = registerItem("bubble_berry_seeds", () -> new ItemNameBlockItem(AoABlocks.BUBBLE_BERRY_CROP.get(), new Item.Properties()));
	public static final DeferredItem<Item> CHILLI_SEEDS = registerItem("chilli_seeds", () -> new ItemNameBlockItem(AoABlocks.CHILLI_CROP.get(), new Item.Properties()));
	public static final DeferredItem<Item> FLORACLE_SEEDS = registerItem("floracle_seeds", () -> new ItemNameBlockItem(AoABlocks.FLORACLES_CROP.get(), new Item.Properties()));
	public static final DeferredItem<Item> GOLDICAP_SEEDS = registerItem("goldicap_seeds", () -> new ItemNameBlockItem(AoABlocks.GOLDICAPS_CROP.get(), new Item.Properties()));
	public static final DeferredItem<Item> HEART_FRUIT_SEEDS = registerItem("heart_fruit_seeds", () -> new ItemNameBlockItem(AoABlocks.HEART_FRUIT_CROP.get(), new Item.Properties()));
	public static final DeferredItem<Item> LUNACRIKE_SEEDS = registerItem("lunacrike_seeds", () -> new ItemNameBlockItem(AoABlocks.LUNACRIKE_CROP.get(), new Item.Properties()));
	public static final DeferredItem<Item> LUNALON_SEEDS = registerItem("lunalon_seeds", () -> new ItemNameBlockItem(AoABlocks.LUNALON_CROP.get(), new Item.Properties()));
	public static final DeferredItem<Item> LUNA_GLOBE_SEEDS = registerItem("luna_globe_seeds", () -> new ItemNameBlockItem(AoABlocks.LUNA_GLOBE_CROP.get(), new Item.Properties()));
	public static final DeferredItem<Item> ROSIDON_SEEDS = registerItem("rosidon_seeds", () -> new ItemNameBlockItem(AoABlocks.ROSIDON_CROP.get(), new Item.Properties()));
	public static final DeferredItem<Item> TEA_SEEDS = registerItem("tea_seeds", () -> new ItemNameBlockItem(AoABlocks.TEA_CROP.get(), new Item.Properties()));
	public static final DeferredItem<Item> THORNY_PLANT_SEEDS = registerItem("thorny_plant_seeds", () -> new ItemNameBlockItem(AoABlocks.THORNY_PLANT_CROP.get(), new Item.Properties()));
	public static final DeferredItem<Item> TRILLIAD_SEEDS = registerItem("trilliad_seeds", () -> new ItemNameBlockItem(AoABlocks.TRILLIAD_CROP.get(), new Item.Properties()));
	public static final DeferredItem<Item> GREEN_MANURE_SEEDS = registerItem("green_manure_seeds", () -> new ItemNameBlockItem(AoABlocks.GREEN_MANURE.get(), new Item.Properties()));

	public static final DeferredItem<Item> MUSIC_DISC_OUTLAW = registerItem("music_disc_outlaw", () -> new RecordItem(15, AoASounds.OUTLAW_MUSIC_DISC, new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 3080));
	public static final DeferredItem<Item> MUSIC_DISC_CAVERNS = registerItem("music_disc_caverns", () -> new RecordItem(15, AoASounds.CAVERNS_MUSIC_DISC, new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 3300));

	public static final DeferredItem<Item> COMPASS_RUNE_BANNER_PATTERN = registerItem("compass_rune_banner_pattern", () -> new BannerPatternItem(AoABannerPatterns.COMPASS_RUNE.tag(), new Item.Properties().stacksTo(1)));
	public static final DeferredItem<Item> DISTORTION_RUNE_BANNER_PATTERN = registerItem("distortion_rune_banner_pattern", () -> new BannerPatternItem(AoABannerPatterns.DISTORTION_RUNE.tag(), new Item.Properties().stacksTo(1)));
	public static final DeferredItem<Item> ENERGY_RUNE_BANNER_PATTERN = registerItem("energy_rune_banner_pattern", () -> new BannerPatternItem(AoABannerPatterns.ENERGY_RUNE.tag(), new Item.Properties().stacksTo(1)));
	public static final DeferredItem<Item> FIRE_RUNE_BANNER_PATTERN = registerItem("fire_rune_banner_pattern", () -> new BannerPatternItem(AoABannerPatterns.FIRE_RUNE.tag(), new Item.Properties().stacksTo(1)));
	public static final DeferredItem<Item> KINETIC_RUNE_BANNER_PATTERN = registerItem("kinetic_rune_banner_pattern", () -> new BannerPatternItem(AoABannerPatterns.KINETIC_RUNE.tag(), new Item.Properties().stacksTo(1)));
	public static final DeferredItem<Item> LIFE_RUNE_BANNER_PATTERN = registerItem("life_rune_banner_pattern", () -> new BannerPatternItem(AoABannerPatterns.LIFE_RUNE.tag(), new Item.Properties().stacksTo(1)));
	public static final DeferredItem<Item> LUNAR_RUNE_BANNER_PATTERN = registerItem("lunar_rune_banner_pattern", () -> new BannerPatternItem(AoABannerPatterns.LUNAR_RUNE.tag(), new Item.Properties().stacksTo(1)));
	public static final DeferredItem<Item> POISON_RUNE_BANNER_PATTERN = registerItem("poison_rune_banner_pattern", () -> new BannerPatternItem(AoABannerPatterns.POISON_RUNE.tag(), new Item.Properties().stacksTo(1)));
	public static final DeferredItem<Item> POWER_RUNE_BANNER_PATTERN = registerItem("power_rune_banner_pattern", () -> new BannerPatternItem(AoABannerPatterns.POWER_RUNE.tag(), new Item.Properties().stacksTo(1)));
	public static final DeferredItem<Item> STORM_RUNE_BANNER_PATTERN = registerItem("storm_rune_banner_pattern", () -> new BannerPatternItem(AoABannerPatterns.STORM_RUNE.tag(), new Item.Properties().stacksTo(1)));
	public static final DeferredItem<Item> STRIKE_RUNE_BANNER_PATTERN = registerItem("strike_rune_banner_pattern", () -> new BannerPatternItem(AoABannerPatterns.STRIKE_RUNE.tag(), new Item.Properties().stacksTo(1)));
	public static final DeferredItem<Item> WATER_RUNE_BANNER_PATTERN = registerItem("water_rune_banner_pattern", () -> new BannerPatternItem(AoABannerPatterns.WATER_RUNE.tag(), new Item.Properties().stacksTo(1)));
	public static final DeferredItem<Item> WIND_RUNE_BANNER_PATTERN = registerItem("wind_rune_banner_pattern", () -> new BannerPatternItem(AoABannerPatterns.WIND_RUNE.tag(), new Item.Properties().stacksTo(1)));
	public static final DeferredItem<Item> WITHER_RUNE_BANNER_PATTERN = registerItem("wither_rune_banner_pattern", () -> new BannerPatternItem(AoABannerPatterns.WITHER_RUNE.tag(), new Item.Properties().stacksTo(1)));

	// TODO - add boat extensibility into NeoForge then come back to this
	//public static final DeferredItem<Item> BAOBAB_BOAT = registerItem("baobab_boat", () -> new BoatItem(true, null, new Item.Properties().stacksTo(1)));
	//public static final DeferredItem<Item> LUCALUS_BOAT = registerItem("lucalus_boat", () -> new BoatItem(true, null, new Item.Properties().stacksTo(1)));
	//public static final DeferredItem<Item> STRANGLEWOOD_BOAT = registerItem("stranglewood_boat", () -> new BoatItem(true, null, new Item.Properties().stacksTo(1)));

	@SafeVarargs
	public static <T extends Item> DeferredItem<T> registerItem(String registryId, Supplier<T> item, @Nullable ResourceKey<CreativeModeTab>... tabs) {
		DeferredItem<T> registeredObject = AoARegistries.ITEMS.register(registryId, item);

		if (tabs != null)
			AoAStartupCache.setItemCreativeTabs(registeredObject, tabs.length == 0 ? ObjectArrayList.of(AoACreativeModeTabs.MISC_ITEMS.getKey()) : ObjectArrayList.of(tabs));

		return registeredObject;
	}

	private static Supplier<Item> miscItem() {
		return () -> new Item(new Item.Properties());
	}

	private static Supplier<Item> miscFuelItem(int burnTime) {
		return () -> new Item(new Item.Properties()) {
			@Override
			public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
				return burnTime;
			}
		};
	}
}
