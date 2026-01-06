package net.tslat.aoa3.common.registration;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.material.Fluid;
import net.tslat.aoa3.advent.AdventOfAscension;

public final class AoATags {
	public static class Blocks {
		public static final TagKey<Block> GRASS = commonTag("grass");
		public static final TagKey<Block> MUSHROOMS = commonTag("mushrooms");

		public static final TagKey<Block> INCORRECT_FOR_EMBERSTONE_TOOL = aoaTag("incorrect_for_emberstone_tool");
		public static final TagKey<Block> INCORRECT_FOR_JADE_TOOL = aoaTag("incorrect_for_jade_tool");
		public static final TagKey<Block> INCORRECT_FOR_LIMONITE_TOOL = aoaTag("incorrect_for_limonite_tool");
		public static final TagKey<Block> INCORRECT_FOR_ORNAMYTE_TOOL = aoaTag("incorrect_for_ornamyte_tool");
		public static final TagKey<Block> INCORRECT_FOR_SKELETAL_TOOL = aoaTag("incorrect_for_skeletal_tool");
		public static final TagKey<Block> INCORRECT_FOR_ENERGISTIC_TOOL = aoaTag("incorrect_for_energistic_tool");
		public static final TagKey<Block> INCORRECT_FOR_GEMCRACKER_TOOL = aoaTag("incorrect_for_gemcracker_tool");
		public static final TagKey<Block> INCORRECT_FOR_GOOFY_TOOL = aoaTag("incorrect_for_goofy_tool");
		public static final TagKey<Block> INCORRECT_FOR_OCCULT_TOOL = aoaTag("incorrect_for_occult_tool");
		public static final TagKey<Block> INCORRECT_FOR_PICKMAX_TOOL = aoaTag("incorrect_for_pickmax_tool");
		public static final TagKey<Block> INCORRECT_FOR_SOULSTONE_TOOL = aoaTag("incorrect_for_soulstone_tool");
		public static final TagKey<Block> INCORRECT_FOR_CHAINSAW_TOOL = aoaTag("incorrect_for_chainsaw_tool");
		public static final TagKey<Block> INCORRECT_FOR_DRYADS_BLESSING_TOOL = aoaTag("incorrect_for_dryads_blessing_tool");
		public static final TagKey<Block> INCORRECT_FOR_ROCK_PICK_TOOL = aoaTag("incorrect_for_rock_pick_tool");
		public static final TagKey<Block> INCORRECT_FOR_TROLL_BASHER_TOOL = aoaTag("incorrect_for_troll_basher_tool");
		public static final TagKey<Block> INCORRECT_FOR_MAUL = aoaTag("incorrect_for_maul");

		public static final TagKey<Block> BARONYTE_ORE = commonTag("ores/baronyte");
		public static final TagKey<Block> BLAZIUM_ORE = commonTag("ores/blazium");
		public static final TagKey<Block> BLOODSTONE_ORE = commonTag("ores/bloodstone");
		public static final TagKey<Block> BLUE_GEMSTONE_ORE = commonTag("ores/blue_gemstone");
		public static final TagKey<Block> CHARGED_RUNIUM_ORE = commonTag("ores/charged_runium");
		public static final TagKey<Block> BONE_FRAGMENTS_ORE = commonTag("ores/chestbone_fragments");
		public static final TagKey<Block> CRYSTALLITE_ORE = commonTag("ores/crystallite");
		public static final TagKey<Block> ELECANIUM_ORE = commonTag("ores/elecanium");
		public static final TagKey<Block> EMBERSTONE_ORE = commonTag("ores/emberstone");
		public static final TagKey<Block> GEMENYTE_ORE = commonTag("ores/gemenyte");
		public static final TagKey<Block> GHASTLY_ORE = commonTag("ores/ghastly");
		public static final TagKey<Block> GHOULISH_ORE = commonTag("ores/ghoulish");
		public static final TagKey<Block> GREEN_GEMSTONE_ORE = commonTag("ores/green_gemstone");
		public static final TagKey<Block> JADE_ORE = commonTag("ores/jade");
		public static final TagKey<Block> JEWELYTE_ORE = commonTag("ores/jewelyte");
		public static final TagKey<Block> LIMONITE_ORE = commonTag("ores/limonite");
		public static final TagKey<Block> LYON_ORE = commonTag("ores/lyon");
		public static final TagKey<Block> MYSTITE_ORE = commonTag("ores/mystite");
		public static final TagKey<Block> ORNAMYTE_ORE = commonTag("ores/ornamyte");
		public static final TagKey<Block> PURPLE_GEMSTONE_ORE = commonTag("ores/purple_gemstone");
		public static final TagKey<Block> RED_GEMSTONE_ORE = commonTag("ores/red_gemstone");
		public static final TagKey<Block> RUNIUM_ORE = commonTag("ores/runium");
		public static final TagKey<Block> SHYREGEM_ORE = commonTag("ores/shyregem");
		public static final TagKey<Block> SHYRESTONE_ORE = commonTag("ores/shyrestone");
		public static final TagKey<Block> VARSIUM_ORE = commonTag("ores/varsium");
		public static final TagKey<Block> WHITE_GEMSTONE_ORE = commonTag("ores/white_gemstone");
		public static final TagKey<Block> YELLOW_GEMSTONE_ORE = commonTag("ores/yellow_gemstone");
		public static final TagKey<Block> STORAGE_BLOCKS_BARONYTE = commonTag("storage_blocks/baronyte");
		public static final TagKey<Block> STORAGE_BLOCKS_BLAZIUM = commonTag("storage_blocks/blazium");
		public static final TagKey<Block> STORAGE_BLOCKS_BLOODSTONE = commonTag("storage_blocks/bloodstone");
		public static final TagKey<Block> STORAGE_BLOCKS_CRYSTALLITE = commonTag("storage_blocks/crystallite");
		public static final TagKey<Block> STORAGE_BLOCKS_ELECANIUM = commonTag("storage_blocks/elecanium");
		public static final TagKey<Block> STORAGE_BLOCKS_EMBERSTONE = commonTag("storage_blocks/emberstone");
		public static final TagKey<Block> STORAGE_BLOCKS_GEMENYTE = commonTag("storage_blocks/gemenyte");
		public static final TagKey<Block> STORAGE_BLOCKS_GHASTLY = commonTag("storage_blocks/ghastly");
		public static final TagKey<Block> STORAGE_BLOCKS_GHOULISH = commonTag("storage_blocks/ghoulish");
		public static final TagKey<Block> STORAGE_BLOCKS_JADE = commonTag("storage_blocks/jade");
		public static final TagKey<Block> STORAGE_BLOCKS_JEWELYTE = commonTag("storage_blocks/jewelyte");
		public static final TagKey<Block> STORAGE_BLOCKS_RAW_LIMONITE = commonTag("storage_blocks/raw_limonite");
		public static final TagKey<Block> STORAGE_BLOCKS_RAW_EMBERSTONE = commonTag("storage_blocks/raw_emberstone");
		public static final TagKey<Block> STORAGE_BLOCKS_LIMONITE = commonTag("storage_blocks/limonite");
		public static final TagKey<Block> STORAGE_BLOCKS_LUNAR = commonTag("storage_blocks/lunar");
		public static final TagKey<Block> STORAGE_BLOCKS_LYON = commonTag("storage_blocks/lyon");
		public static final TagKey<Block> STORAGE_BLOCKS_MYSTITE = commonTag("storage_blocks/mystite");
		public static final TagKey<Block> STORAGE_BLOCKS_ORNAMYTE = commonTag("storage_blocks/ornamyte");
		public static final TagKey<Block> STORAGE_BLOCKS_SHYREGEM = commonTag("storage_blocks/shyregem");
		public static final TagKey<Block> STORAGE_BLOCKS_SHYRESTONE = commonTag("storage_blocks/shyrestone");
		public static final TagKey<Block> STORAGE_BLOCKS_SKELETAL = commonTag("storage_blocks/skeletal");
		public static final TagKey<Block> STORAGE_BLOCKS_VARSIUM = commonTag("storage_blocks/varsium");

		public static final TagKey<Block> CARVED_RUNE = aoaTag("carved_rune");
		public static final TagKey<Block> LUNAR_ORB = aoaTag("lunar_orb");
		public static final TagKey<Block> NOWHERE_SAFE_GUI_BLOCK = aoaTag("nowhere_safe_gui_block");

		public static final TagKey<Block> BASE_STONE_CREEPONIA = aoaTag("base_stone_creeponia");
		public static final TagKey<Block> BASE_STONE_PRECASIA = aoaTag("base_stone_precasia");
		public static final TagKey<Block> BASE_STONE_BARATHOS = aoaTag("base_stone_barathos");

		public static final TagKey<Block> EXTRACTION_TRAINABLE = aoaTag("extraction_trainable");
		public static final TagKey<Block> NO_BONUS_MINING_RESULT = aoaTag("no_bonus_mining_result");
		public static final TagKey<Block> NO_HARVEST_REPLANT = aoaTag("no_harvest_replant");

		public static final TagKey<Block> INFINIBURN_BARATHOS = aoaTag("infiniburn_barathos");

		public static final TagKey<Block> FAST_CLIMBABLE = aoaTag("fast_climbable");

		public static final TagKey<Block> INFERNAL_CAN_CONVERT = aoaTag("infernal_can_convert");

		private static TagKey<Block> aoaTag(String id) {
			return BlockTags.create(AdventOfAscension.id(id));
		}

		private static TagKey<Block> commonTag(String id) {
			return BlockTags.create(ResourceLocation.fromNamespaceAndPath("c", id));
		}
	}

	public static class Items {
		public static final TagKey<Item> GRASS = commonTag("grass");
		public static final TagKey<Item> BANNER_PATTERNS = commonTag("banner_patterns");

		public static final TagKey<Item> BARONYTE_ORE = commonTag("ores/baronyte");
		public static final TagKey<Item> BLAZIUM_ORE = commonTag("ores/blazium");
		public static final TagKey<Item> BLOODSTONE_ORE = commonTag("ores/bloodstone");
		public static final TagKey<Item> BLUE_GEMSTONE_ORE = commonTag("ores/blue_gemstone");
		public static final TagKey<Item> CHARGED_RUNIUM_ORE = commonTag("ores/charged_runium");
		public static final TagKey<Item> BONE_FRAGMENTS_ORE = commonTag("ores/chestbone_fragments");
		public static final TagKey<Item> CRYSTALLITE_ORE = commonTag("ores/crystallite");
		public static final TagKey<Item> ELECANIUM_ORE = commonTag("ores/elecanium");
		public static final TagKey<Item> EMBERSTONE_ORE = commonTag("ores/emberstone");
		public static final TagKey<Item> GEMENYTE_ORE = commonTag("ores/gemenyte");
		public static final TagKey<Item> GHASTLY_ORE = commonTag("ores/ghastly");
		public static final TagKey<Item> GHOULISH_ORE = commonTag("ores/ghoulish");
		public static final TagKey<Item> GREEN_GEMSTONE_ORE = commonTag("ores/green_gemstone");
		public static final TagKey<Item> JADE_ORE = commonTag("ores/jade");
		public static final TagKey<Item> JEWELYTE_ORE = commonTag("ores/jewelyte");
		public static final TagKey<Item> LIMONITE_ORE = commonTag("ores/limonite");
		public static final TagKey<Item> LYON_ORE = commonTag("ores/lyon");
		public static final TagKey<Item> MYSTITE_ORE = commonTag("ores/mystite");
		public static final TagKey<Item> ORNAMYTE_ORE = commonTag("ores/ornamyte");
		public static final TagKey<Item> PURPLE_GEMSTONE_ORE = commonTag("ores/purple_gemstone");
		public static final TagKey<Item> RED_GEMSTONE_ORE = commonTag("ores/red_gemstone");
		public static final TagKey<Item> RUNIUM_ORE = commonTag("ores/runium");
		public static final TagKey<Item> SHYREGEM_ORE = commonTag("ores/shyregem");
		public static final TagKey<Item> SHYRESTONE_ORE = commonTag("ores/shyrestone");
		public static final TagKey<Item> VARSIUM_ORE = commonTag("ores/varsium");
		public static final TagKey<Item> WHITE_GEMSTONE_ORE = commonTag("ores/white_gemstone");
		public static final TagKey<Item> YELLOW_GEMSTONE_ORE = commonTag("ores/yellow_gemstone");

		public static final TagKey<Item> STORAGE_BLOCKS_BARONYTE = commonTag("storage_blocks/baronyte");
		public static final TagKey<Item> STORAGE_BLOCKS_BLAZIUM = commonTag("storage_blocks/blazium");
		public static final TagKey<Item> STORAGE_BLOCKS_BLOODSTONE = commonTag("storage_blocks/bloodstone");
		public static final TagKey<Item> STORAGE_BLOCKS_CRYSTALLITE = commonTag("storage_blocks/crystallite");
		public static final TagKey<Item> STORAGE_BLOCKS_ELECANIUM = commonTag("storage_blocks/elecanium");
		public static final TagKey<Item> STORAGE_BLOCKS_EMBERSTONE = commonTag("storage_blocks/emberstone");
		public static final TagKey<Item> STORAGE_BLOCKS_GEMENYTE = commonTag("storage_blocks/gemenyte");
		public static final TagKey<Item> STORAGE_BLOCKS_GHASTLY = commonTag("storage_blocks/ghastly");
		public static final TagKey<Item> STORAGE_BLOCKS_GHOULISH = commonTag("storage_blocks/ghoulish");
		public static final TagKey<Item> STORAGE_BLOCKS_JADE = commonTag("storage_blocks/jade");
		public static final TagKey<Item> STORAGE_BLOCKS_JEWELYTE = commonTag("storage_blocks/jewelyte");
		public static final TagKey<Item> STORAGE_BLOCKS_RAW_LIMONITE = commonTag("storage_blocks/raw_limonite");
		public static final TagKey<Item> STORAGE_BLOCKS_RAW_EMBERSTONE = commonTag("storage_blocks/raw_emberstone");
		public static final TagKey<Item> STORAGE_BLOCKS_LIMONITE = commonTag("storage_blocks/limonite");
		public static final TagKey<Item> STORAGE_BLOCKS_LUNAR = commonTag("storage_blocks/lunar");
		public static final TagKey<Item> STORAGE_BLOCKS_LYON = commonTag("storage_blocks/lyon");
		public static final TagKey<Item> STORAGE_BLOCKS_MYSTITE = commonTag("storage_blocks/mystite");
		public static final TagKey<Item> STORAGE_BLOCKS_ORNAMYTE = commonTag("storage_blocks/ornamyte");
		public static final TagKey<Item> STORAGE_BLOCKS_SHYREGEM = commonTag("storage_blocks/shyregem");
		public static final TagKey<Item> STORAGE_BLOCKS_SHYRESTONE = commonTag("storage_blocks/shyrestone");
		public static final TagKey<Item> STORAGE_BLOCKS_SKELETAL = commonTag("storage_blocks/skeletal");
		public static final TagKey<Item> STORAGE_BLOCKS_VARSIUM = commonTag("storage_blocks/varsium");

		public static final TagKey<Item> GEMS_BLOODSTONE = commonTag("gems/bloodstone");
		public static final TagKey<Item> GEMS_CRYSTALLITE = commonTag("gems/crystallite");
		public static final TagKey<Item> GEMS_GEMENYTE = commonTag("gems/gemenyte");
		public static final TagKey<Item> GEMS_JADE = commonTag("gems/jade");
		public static final TagKey<Item> GEMS_JEWELYTE = commonTag("gems/jewelyte");
		public static final TagKey<Item> GEMS_ORNAMYTE = commonTag("gems/ornamyte");
		public static final TagKey<Item> GEMS_SHYREGEM = commonTag("gems/shyregem");
		
		public static final TagKey<Item> INGOTS_BARONYTE = commonTag("ingots/baronyte");
		public static final TagKey<Item> INGOTS_BLAZIUM = commonTag("ingots/blazium");
		public static final TagKey<Item> INGOTS_ELECANIUM = commonTag("ingots/elecanium");
		public static final TagKey<Item> INGOTS_EMBERSTONE = commonTag("ingots/emberstone");
		public static final TagKey<Item> INGOTS_GHASTLY = commonTag("ingots/ghastly");
		public static final TagKey<Item> INGOTS_GHOULISH = commonTag("ingots/ghoulish");
		public static final TagKey<Item> INGOTS_LIMONITE = commonTag("ingots/limonite");
		public static final TagKey<Item> INGOTS_LUNAR = commonTag("ingots/lunar");
		public static final TagKey<Item> INGOTS_LYON = commonTag("ingots/lyon");
		public static final TagKey<Item> INGOTS_MYSTITE = commonTag("ingots/mystite");
		public static final TagKey<Item> INGOTS_SHYRESTONE = commonTag("ingots/shyrestone");
		public static final TagKey<Item> INGOTS_SKELETAL = commonTag("ingots/skeletal");
		public static final TagKey<Item> INGOTS_VARSIUM = commonTag("ingots/varsium");

		public static final TagKey<Item> RAW_MATERIALS_LIMONITE = commonTag("raw_materials/limonite");
		public static final TagKey<Item> RAW_MATERIALS_EMBERSTONE = commonTag("raw_materials/emberstone");

		public static final TagKey<Item> NUGGETS_BARONYTE = commonTag("nuggets/baronyte");
		public static final TagKey<Item> NUGGETS_BLAZIUM = commonTag("nuggets/blazium");
		public static final TagKey<Item> NUGGETS_ELECANIUM = commonTag("nuggets/elecanium");
		public static final TagKey<Item> NUGGETS_EMBERSTONE = commonTag("nuggets/emberstone");
		public static final TagKey<Item> NUGGETS_GHASTLY = commonTag("nuggets/ghastly");
		public static final TagKey<Item> NUGGETS_GHOULISH = commonTag("nuggets/ghoulish");
		public static final TagKey<Item> NUGGETS_LIMONITE = commonTag("nuggets/limonite");
		public static final TagKey<Item> NUGGETS_LUNAR = commonTag("nuggets/lunar");
		public static final TagKey<Item> NUGGETS_LYON = commonTag("nuggets/lyon");
		public static final TagKey<Item> NUGGETS_MYSTITE = commonTag("nuggets/mystite");
		public static final TagKey<Item> NUGGETS_SHYRESTONE = commonTag("nuggets/shyrestone");
		public static final TagKey<Item> NUGGETS_SKELETAL = commonTag("nuggets/skeletal");
		public static final TagKey<Item> NUGGETS_VARSIUM = commonTag("nuggets/varsium");
		
		public static final TagKey<Item> PRECASIAN_BONE = aoaTag("precasian_bone");
		public static final TagKey<Item> ADVENT_RUNE = aoaTag("runes");
		public static final TagKey<Item> FRAME_BENCH_FRAME = aoaTag("frame_bench_frame");
		public static final TagKey<Item> ENERGY_STONE = aoaTag("energy_stone");
		public static final TagKey<Item> SKILL_CRYSTAL = aoaTag("skill_crystal");
		public static final TagKey<Item> HAULING_FISH = aoaTag("hauling_fish");
		public static final TagKey<Item> FAUNAMANCER_TOOL = aoaTag("faunamancer_tool");
		public static final TagKey<Item> POWER_STONE = aoaTag("power_stones");

		public static final TagKey<Item> AOA_COINS = aoaTag("aoa_coins");
		public static final TagKey<Item> FRUIT = commonTag("fruit");
		public static final TagKey<Item> MILK = commonTag("milk");
		public static final TagKey<Item> GINGERBREAD = commonTag("gingerbread");
		public static final TagKey<Item> MINTS = commonTag("mints");
		public static final TagKey<Item> SHULKER_BOXES = commonTag("shulker_boxes");
		public static final TagKey<Item> IVORY = commonTag("ivory");
		public static final TagKey<Item> RODS_METAL = commonTag("rods/metal");
		public static final TagKey<Item> RODS_LIMONITE = commonTag("rods/limonite");
		public static final TagKey<Item> AIRTIGHT = commonTag("armor/airtight");

		public static final TagKey<Item> COMPASS_RUNE_CATALYST = aoaTag("compass_rune_catalyst");
		public static final TagKey<Item> DISTORTION_RUNE_CATALYST = aoaTag("distortion_rune_catalyst");
		public static final TagKey<Item> ENERGY_RUNE_CATALYST = aoaTag("energy_rune_catalyst");
		public static final TagKey<Item> FIRE_RUNE_CATALYST = aoaTag("fire_rune_catalyst");
		public static final TagKey<Item> KINETIC_RUNE_CATALYST = aoaTag("kinetic_rune_catalyst");
		public static final TagKey<Item> LIFE_RUNE_CATALYST = aoaTag("life_rune_catalyst");
		public static final TagKey<Item> LUNAR_RUNE_CATALYST = aoaTag("lunar_rune_catalyst");
		public static final TagKey<Item> POISON_RUNE_CATALYST = aoaTag("poison_rune_catalyst");
		public static final TagKey<Item> POWER_RUNE_CATALYST = aoaTag("power_rune_catalyst");
		public static final TagKey<Item> STORM_RUNE_CATALYST = aoaTag("storm_rune_catalyst");
		public static final TagKey<Item> STRIKE_RUNE_CATALYST = aoaTag("strike_rune_catalyst");
		public static final TagKey<Item> WATER_RUNE_CATALYST = aoaTag("water_rune_catalyst");
		public static final TagKey<Item> WITHER_RUNE_CATALYST = aoaTag("wither_rune_catalyst");
		public static final TagKey<Item> WIND_RUNE_CATALYST = aoaTag("wind_rune_catalyst");

		public static final TagKey<Item> STAVES = aoaTag("staves");
		public static final TagKey<Item> GUNS = aoaTag("guns");
		public static final TagKey<Item> MAULS = aoaTag("mauls");
		public static final TagKey<Item> ONE_HANDED_GUNS = aoaTag("one_handed_guns");
		public static final TagKey<Item> SHOTGUNS = aoaTag("shotguns");
		public static final TagKey<Item> SNIPERS = aoaTag("snipers");
		public static final TagKey<Item> CANNONS = aoaTag("cannons");
		public static final TagKey<Item> BLASTERS = aoaTag("blasters");
		public static final TagKey<Item> GREATBLADES = aoaTag("greatblades");
		public static final TagKey<Item> BULLET_FIRING_GUNS = aoaTag("bullet_firing_guns");

		public static final TagKey<Item> STAVES_ENCHANTABLE = aoaTag("staves_enchantable");
		public static final TagKey<Item> GUNS_ENCHANTABLE = aoaTag("guns_enchantable");
		public static final TagKey<Item> CANNONS_ENCHANTABLE = aoaTag("cannons_enchantable");
		public static final TagKey<Item> SNIPERS_ENCHANTABLE = aoaTag("snipers_enchantable");
		public static final TagKey<Item> SMALL_GUNS_ENCHANTABLE = aoaTag("small_guns_enchantable");
		public static final TagKey<Item> SHOTGUNS_ENCHANTABLE = aoaTag("shotguns_enchantable");
		public static final TagKey<Item> BLASTERS_ENCHANTABLE = aoaTag("blasters_enchantable");
		public static final TagKey<Item> GREATBLADES_ENCHANTABLE = aoaTag("greatblades_enchantable");
		public static final TagKey<Item> BULLET_FIRING_ENCHANTABLE = aoaTag("bullet_firing_enchantable");
		public static final TagKey<Item> GREED_ENCHANTABLE = aoaTag("greed_enchantable");
		public static final TagKey<Item> INTERVENTION_ENCHANTMENT_COMPATIBLE = aoaTag("intervention_enchantment_compatible");

		public static final TagKey<Item> DEINOTHERIUM_FOOD = aoaTag("deinotherium_food");
		public static final TagKey<Item> HORNDRON_FOOD = aoaTag("horndron_food");
		public static final TagKey<Item> OPTERYX_FOOD = aoaTag("opteryx_food");

		public static final TagKey<Item> NO_BONUS_SMELT_RESULT = aoaTag("no_bonus_smelt_result");

		public static final TagKey<Item> AMMO = aoaTag("ammo");
		public static final TagKey<Item> AMMO_VOID_POUCH_COMPATIBLE = aoaTag("ammo_void_pouch_compatible");

		public static final TagKey<Item> ASHFERN_SEEDS = commonTag("seeds/ashfern");
		public static final TagKey<Item> BUBBLE_BERRY_SEEDS = commonTag("seeds/bubble_berry");
		public static final TagKey<Item> CHILLI_SEEDS = commonTag("seeds/chilli");
		public static final TagKey<Item> FLORACLE_SEEDS = commonTag("seeds/floracle");
		public static final TagKey<Item> GOLDICAP_SEEDS = commonTag("seeds/goldicap");
		public static final TagKey<Item> GREEN_MANURE_SEEDS = commonTag("seeds/green_manure");
		public static final TagKey<Item> HEART_FRUIT_SEEDS = commonTag("seeds/heart_fruit");
		public static final TagKey<Item> LUNACRIKE_SEEDS = commonTag("seeds/lunacrike");
		public static final TagKey<Item> LUNALON_SEEDS = commonTag("seeds/lunalon");
		public static final TagKey<Item> LUNA_GLOBE_SEEDS = commonTag("seeds/luna_globe");
		public static final TagKey<Item> ROSIDON_SEEDS = commonTag("seeds/rosidon");
		public static final TagKey<Item> TEA_SEEDS = commonTag("seeds/tea");
		public static final TagKey<Item> THORNY_PLANT_SEEDS = commonTag("seeds/thorny_plant");
		public static final TagKey<Item> TRILLIAD_SEEDS = commonTag("seeds/trilliad");

		private static TagKey<Item> aoaTag(String id) {
			return ItemTags.create(AdventOfAscension.id(id));
		}

		private static TagKey<Item> commonTag(String id) {
			return ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", id));
		}
	}

	public static class Fluids {
		public static final TagKey<Fluid> CANDIED_WATER = commonTag("candied_water");
		public static final TagKey<Fluid> TOXIC_WASTE = commonTag("toxic_waste");
		public static final TagKey<Fluid> TAR = commonTag("tar");

		private static TagKey<Fluid> aoaTag(String id) {
			return FluidTags.create(AdventOfAscension.id(id));
		}

		private static TagKey<Fluid> commonTag(String id) {
			return FluidTags.create(ResourceLocation.fromNamespaceAndPath("c", id));
		}
	}

	public static class Entities {
		public static final TagKey<EntityType<?>> FLYING = commonTag("flying");
		public static final TagKey<EntityType<?>> IMMUNE_TO_SANDSTORM = aoaTag("immune_to_sandstorm");
		public static final TagKey<EntityType<?>> LOOSE_SAND_WALKABLE_MOBS = aoaTag("loose_sand_walkable_mobs");

		private static TagKey<EntityType<?>> aoaTag(String id) {
			return create(AdventOfAscension.id(id));
		}

		private static TagKey<EntityType<?>> commonTag(String id) {
			return create(ResourceLocation.fromNamespaceAndPath("c", id));
		}

		public static TagKey<EntityType<?>> create(ResourceLocation id) {
			return TagKey.create(Registries.ENTITY_TYPE, id);
		}
	}

	public static class Biomes {
		public static final TagKey<Biome> NO_MOB_SPAWNS = commonTag("no_mob_spawns");

		public static final TagKey<Biome> IS_ABYSS = aoaTag("is_abyss");
		public static final TagKey<Biome> IS_BARATHOS = aoaTag("is_barathos");
		public static final TagKey<Biome> IS_CELEVE = aoaTag("is_celeve");
		public static final TagKey<Biome> IS_CRYSTEVIA = aoaTag("is_crystevia");
		public static final TagKey<Biome> IS_DEEPLANDS = aoaTag("is_deeplands");
		public static final TagKey<Biome> IS_DUSTOPIA = aoaTag("is_dustopia");
		public static final TagKey<Biome> IS_LBOREAN = aoaTag("is_lborean");
		public static final TagKey<Biome> IS_LELYETIA = aoaTag("is_lelyetia");
		public static final TagKey<Biome> IS_NOWHERE = aoaTag("is_nowhere");
		public static final TagKey<Biome> IS_PRECASIA = aoaTag("is_precasia");

		public static final TagKey<Biome> WATER_FISHING_BENEFICIAL = aoaTag("water_fishing_beneficial");
		public static final TagKey<Biome> WATER_FISHING_DETRIMENTAL = aoaTag("water_fishing_detrimental");
		public static final TagKey<Biome> LAVA_FISHING_BENEFICIAL = aoaTag("lava_fishing_beneficial");
		public static final TagKey<Biome> LAVA_FISHING_DETRIMENTAL = aoaTag("lava_fishing_detrimental");

		public static final TagKey<Biome> HAS_RUINED_TELEPORTER = aoaTag("has_structure/ruined_teleporter");
		public static final TagKey<Biome> HAS_PRECASIAN_LOTTO_HOVEL = aoaTag("has_structure/precasian_lotto_hovel");
		public static final TagKey<Biome> HAS_ATTERCOPUS_NEST = aoaTag("has_structure/attercopus_nest");
		public static final TagKey<Biome> HAS_BARON_RESOURCE_PIT = aoaTag("has_structure/baron_resource_pit");

		private static TagKey<Biome> aoaTag(String id) {
			return create(AdventOfAscension.id(id));
		}

		private static TagKey<Biome> commonTag(String id) {
			return create(ResourceLocation.fromNamespaceAndPath("c", id));
		}

		public static TagKey<Biome> create(ResourceLocation id) {
			return TagKey.create(Registries.BIOME, id);
		}
	}

	public static class Structures {
		public static final TagKey<Structure> ON_RUINED_TELEPORTER_FRAME_MAPS = aoaTag("on_ruined_teleporter_frame_maps");

		private static TagKey<Structure> aoaTag(String id) {
			return create(AdventOfAscension.id(id));
		}

		private static TagKey<Structure> commonTag(String id) {
			return create(ResourceLocation.fromNamespaceAndPath("c", id));
		}

		public static TagKey<Structure> create(ResourceLocation id) {
			return TagKey.create(Registries.STRUCTURE, id);
		}
	}

	public static class DamageTypes {
		public static final TagKey<DamageType> GUN = aoaTag("gun");
		public static final TagKey<DamageType> ENERGY = aoaTag("energy");
		public static final TagKey<DamageType> NO_SPIRIT_REGEN = aoaTag("no_spirit_regen");
		public static final TagKey<DamageType> REDUCED_FLINCH = commonTag("reduced_flinch");
		public static final TagKey<DamageType> NO_ARMOUR_DAMAGE = commonTag("no_armour_damage");

		private static TagKey<DamageType> aoaTag(String id) {
			return create(AdventOfAscension.id(id));
		}

		private static TagKey<DamageType> commonTag(String id) {
			return create(ResourceLocation.fromNamespaceAndPath("c", id));
		}

		public static TagKey<DamageType> create(ResourceLocation id) {
			return TagKey.create(Registries.DAMAGE_TYPE, id);
		}
	}

	public static class BannerPatterns {
		public static final TagKey<BannerPattern> COMPASS_RUNE = aoaTag("pattern_item/compass_rune");
		public static final TagKey<BannerPattern> DISTORTION_RUNE = aoaTag("pattern_item/distortion_rune");
		public static final TagKey<BannerPattern> ENERGY_RUNE = aoaTag("pattern_item/energy_rune");
		public static final TagKey<BannerPattern> FIRE_RUNE = aoaTag("pattern_item/fire_rune");
		public static final TagKey<BannerPattern> KINETIC_RUNE = aoaTag("pattern_item/kinetic_rune");
		public static final TagKey<BannerPattern> LIFE_RUNE = aoaTag("pattern_item/life_rune");
		public static final TagKey<BannerPattern> LUNAR_RUNE = aoaTag("pattern_item/lunar_rune");
		public static final TagKey<BannerPattern> POISON_RUNE = aoaTag("pattern_item/poison_rune");
		public static final TagKey<BannerPattern> POWER_RUNE = aoaTag("pattern_item/power_rune");
		public static final TagKey<BannerPattern> STORM_RUNE = aoaTag("pattern_item/storm_rune");
		public static final TagKey<BannerPattern> STRIKE_RUNE = aoaTag("pattern_item/strike_rune");
		public static final TagKey<BannerPattern> WATER_RUNE = aoaTag("pattern_item/water_rune");
		public static final TagKey<BannerPattern> WIND_RUNE = aoaTag("pattern_item/wind_rune");
		public static final TagKey<BannerPattern> WITHER_RUNE = aoaTag("pattern_item/wither_rune");

		private static TagKey<BannerPattern> aoaTag(String id) {
			return create(AdventOfAscension.id(id));
		}

		private static TagKey<BannerPattern> commonTag(String id) {
			return create(ResourceLocation.fromNamespaceAndPath("c", id));
		}

		public static TagKey<BannerPattern> create(ResourceLocation id) {
			return TagKey.create(Registries.BANNER_PATTERN, id);
		}
	}

	public static class Enchantments {
		public static final TagKey<Enchantment> ARTIFICE_TOOL_INCOMPATIBLE = aoaTag("artifice_tool_incompatible");

		private static TagKey<Enchantment> aoaTag(String id) {
			return create(AdventOfAscension.id(id));
		}

		public static TagKey<Enchantment> create(ResourceLocation id) {
			return TagKey.create(Registries.ENCHANTMENT, id);
		}
	}
}
