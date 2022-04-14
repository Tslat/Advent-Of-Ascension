package net.tslat.aoa3.common.registration;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.tslat.aoa3.advent.AdventOfAscension;

public final class AoATags {
	public static class Blocks {
		public static final TagKey<Block> GRASS = tag("grass");
		public static final TagKey<Block> MUSHROOMS = tag("mushrooms");

		public static final TagKey<Block> BARONYTE_ORE = tag("ores/baronyte");
		public static final TagKey<Block> BLAZIUM_ORE = tag("ores/blazium");
		public static final TagKey<Block> BLOODSTONE_ORE = tag("ores/bloodstone");
		public static final TagKey<Block> BLUE_GEMSTONE_ORE = tag("ores/blue_gemstone");
		public static final TagKey<Block> CHARGED_RUNIUM_ORE = tag("ores/charged_runium");
		public static final TagKey<Block> CHESTBONE_FRAGMENTS_ORE = tag("ores/chestbone_fragments");
		public static final TagKey<Block> CRYSTALLITE_ORE = tag("ores/crystallite");
		public static final TagKey<Block> ELECANIUM_ORE = tag("ores/elecanium");
		public static final TagKey<Block> EMBERSTONE_ORE = tag("ores/emberstone");
		public static final TagKey<Block> FOOTBONE_FRAGMENTS_ORE = tag("ores/footbone_fragments");
		public static final TagKey<Block> GEMENYTE_ORE = tag("ores/gemenyte");
		public static final TagKey<Block> GHASTLY_ORE = tag("ores/ghastly");
		public static final TagKey<Block> GHOULISH_ORE = tag("ores/ghoulish");
		public static final TagKey<Block> GREEN_GEMSTONE_ORE = tag("ores/green_gemstone");
		public static final TagKey<Block> JADE_ORE = tag("ores/jade");
		public static final TagKey<Block> JEWELYTE_ORE = tag("ores/jewelyte");
		public static final TagKey<Block> LEGBONE_FRAGMENTS_ORE = tag("ores/legbone_fragments");
		public static final TagKey<Block> LIMONITE_ORE = tag("ores/limonite");
		public static final TagKey<Block> LYON_ORE = tag("ores/lyon");
		public static final TagKey<Block> MYSTITE_ORE = tag("ores/mystite");
		public static final TagKey<Block> ORNAMYTE_ORE = tag("ores/ornamyte");
		public static final TagKey<Block> PURPLE_GEMSTONE_ORE = tag("ores/purple_gemstone");
		public static final TagKey<Block> RED_GEMSTONE_ORE = tag("ores/red_gemstone");
		public static final TagKey<Block> RUNIUM_ORE = tag("ores/runium");
		public static final TagKey<Block> SHYREGEM_ORE = tag("ores/shyregem");
		public static final TagKey<Block> SHYRESTONE_ORE = tag("ores/shyrestone");
		public static final TagKey<Block> SKULLBONE_FRAGMENTS_ORE = tag("ores/skullbone_fragments");
		public static final TagKey<Block> VARSIUM_ORE = tag("ores/varsium");
		public static final TagKey<Block> WHITE_GEMSTONE_ORE = tag("ores/white_gemstone");
		public static final TagKey<Block> YELLOW_GEMSTONE_ORE = tag("ores/yellow_gemstone");
		public static final TagKey<Block> STORAGE_BLOCKS_BARONYTE = tag("storage_blocks/baronyte");
		public static final TagKey<Block> STORAGE_BLOCKS_BLAZIUM = tag("storage_blocks/blazium");
		public static final TagKey<Block> STORAGE_BLOCKS_BLOODSTONE = tag("storage_blocks/bloodstone");
		public static final TagKey<Block> STORAGE_BLOCKS_CRYSTALLITE = tag("storage_blocks/crystallite");
		public static final TagKey<Block> STORAGE_BLOCKS_ELECANIUM = tag("storage_blocks/elecanium");
		public static final TagKey<Block> STORAGE_BLOCKS_EMBERSTONE = tag("storage_blocks/emberstone");
		public static final TagKey<Block> STORAGE_BLOCKS_GEMENYTE = tag("storage_blocks/gemenyte");
		public static final TagKey<Block> STORAGE_BLOCKS_GHASTLY = tag("storage_blocks/ghastly");
		public static final TagKey<Block> STORAGE_BLOCKS_GHOULISH = tag("storage_blocks/ghoulish");
		public static final TagKey<Block> STORAGE_BLOCKS_JADE = tag("storage_blocks/jade");
		public static final TagKey<Block> STORAGE_BLOCKS_JEWELYTE = tag("storage_blocks/jewelyte");
		public static final TagKey<Block> STORAGE_BLOCKS_LIMONITE = tag("storage_blocks/limonite");
		public static final TagKey<Block> STORAGE_BLOCKS_LUNAR = tag("storage_blocks/lunar");
		public static final TagKey<Block> STORAGE_BLOCKS_LYON = tag("storage_blocks/lyon");
		public static final TagKey<Block> STORAGE_BLOCKS_MYSTITE = tag("storage_blocks/mystite");
		public static final TagKey<Block> STORAGE_BLOCKS_ORNAMYTE = tag("storage_blocks/ornamyte");
		public static final TagKey<Block> STORAGE_BLOCKS_SHYREGEM = tag("storage_blocks/shyregem");
		public static final TagKey<Block> STORAGE_BLOCKS_SHYRESTONE = tag("storage_blocks/shyrestone");
		public static final TagKey<Block> STORAGE_BLOCKS_SKELETAL = tag("storage_blocks/skeletal");
		public static final TagKey<Block> STORAGE_BLOCKS_VARSIUM = tag("storage_blocks/varsium");

		public static final TagKey<Block> CARVED_RUNE = aoaTag("carved_rune");
		public static final TagKey<Block> LUNAR_ORB = aoaTag("lunar_orb");
		public static final TagKey<Block> BASE_STONE_CREEPONIA = aoaTag("base_stone_creeponia");

		public static final TagKey<Block> NEEDS_NETHERITE_TOOL = tag("needs_netherite_tool");

		private static TagKey<Block> aoaTag(String id) {
			return BlockTags.create(new ResourceLocation(AdventOfAscension.MOD_ID, id));
		}

		private static TagKey<Block> tag(String id) {
			return BlockTags.create(new ResourceLocation("forge", id));
		}
	}

	public static class Items {
		public static final TagKey<Item> DIRT = tag("dirt");
		public static final TagKey<Item> GRASS = tag("grass");

		public static final TagKey<Item> BARONYTE_ORE = tag("ores/baronyte");
		public static final TagKey<Item> BLAZIUM_ORE = tag("ores/blazium");
		public static final TagKey<Item> BLOODSTONE_ORE = tag("ores/bloodstone");
		public static final TagKey<Item> BLUE_GEMSTONE_ORE = tag("ores/blue_gemstone");
		public static final TagKey<Item> CHARGED_RUNIUM_ORE = tag("ores/charged_runium");
		public static final TagKey<Item> CHESTBONE_FRAGMENTS_ORE = tag("ores/chestbone_fragments");
		public static final TagKey<Item> CRYSTALLITE_ORE = tag("ores/crystallite");
		public static final TagKey<Item> ELECANIUM_ORE = tag("ores/elecanium");
		public static final TagKey<Item> EMBERSTONE_ORE = tag("ores/emberstone");
		public static final TagKey<Item> FOOTBONE_FRAGMENTS_ORE = tag("ores/footbone_fragments");
		public static final TagKey<Item> GEMENYTE_ORE = tag("ores/gemenyte");
		public static final TagKey<Item> GHASTLY_ORE = tag("ores/ghastly");
		public static final TagKey<Item> GHOULISH_ORE = tag("ores/ghoulish");
		public static final TagKey<Item> GREEN_GEMSTONE_ORE = tag("ores/green_gemstone");
		public static final TagKey<Item> JADE_ORE = tag("ores/jade");
		public static final TagKey<Item> JEWELYTE_ORE = tag("ores/jewelyte");
		public static final TagKey<Item> LEGBONE_FRAGMENTS_ORE = tag("ores/legbone_fragments");
		public static final TagKey<Item> LIMONITE_ORE = tag("ores/limonite");
		public static final TagKey<Item> LYON_ORE = tag("ores/lyon");
		public static final TagKey<Item> MYSTITE_ORE = tag("ores/mystite");
		public static final TagKey<Item> ORNAMYTE_ORE = tag("ores/ornamyte");
		public static final TagKey<Item> PURPLE_GEMSTONE_ORE = tag("ores/purple_gemstone");
		public static final TagKey<Item> RED_GEMSTONE_ORE = tag("ores/red_gemstone");
		public static final TagKey<Item> RUNIUM_ORE = tag("ores/runium");
		public static final TagKey<Item> SHYREGEM_ORE = tag("ores/shyregem");
		public static final TagKey<Item> SHYRESTONE_ORE = tag("ores/shyrestone");
		public static final TagKey<Item> SKULLBONE_FRAGMENTS_ORE = tag("ores/skullbone_fragments");
		public static final TagKey<Item> VARSIUM_ORE = tag("ores/varsium");
		public static final TagKey<Item> WHITE_GEMSTONE_ORE = tag("ores/white_gemstone");
		public static final TagKey<Item> YELLOW_GEMSTONE_ORE = tag("ores/yellow_gemstone");

		public static final TagKey<Item> STORAGE_BLOCKS_BARONYTE = tag("storage_blocks/baronyte");
		public static final TagKey<Item> STORAGE_BLOCKS_BLAZIUM = tag("storage_blocks/blazium");
		public static final TagKey<Item> STORAGE_BLOCKS_BLOODSTONE = tag("storage_blocks/bloodstone");
		public static final TagKey<Item> STORAGE_BLOCKS_CRYSTALLITE = tag("storage_blocks/crystallite");
		public static final TagKey<Item> STORAGE_BLOCKS_ELECANIUM = tag("storage_blocks/elecanium");
		public static final TagKey<Item> STORAGE_BLOCKS_EMBERSTONE = tag("storage_blocks/emberstone");
		public static final TagKey<Item> STORAGE_BLOCKS_GEMENYTE = tag("storage_blocks/gemenyte");
		public static final TagKey<Item> STORAGE_BLOCKS_GHASTLY = tag("storage_blocks/ghastly");
		public static final TagKey<Item> STORAGE_BLOCKS_GHOULISH = tag("storage_blocks/ghoulish");
		public static final TagKey<Item> STORAGE_BLOCKS_JADE = tag("storage_blocks/jade");
		public static final TagKey<Item> STORAGE_BLOCKS_JEWELYTE = tag("storage_blocks/jewelyte");
		public static final TagKey<Item> STORAGE_BLOCKS_LIMONITE = tag("storage_blocks/limonite");
		public static final TagKey<Item> STORAGE_BLOCKS_LUNAR = tag("storage_blocks/lunar");
		public static final TagKey<Item> STORAGE_BLOCKS_LYON = tag("storage_blocks/lyon");
		public static final TagKey<Item> STORAGE_BLOCKS_MYSTITE = tag("storage_blocks/mystite");
		public static final TagKey<Item> STORAGE_BLOCKS_ORNAMYTE = tag("storage_blocks/ornamyte");
		public static final TagKey<Item> STORAGE_BLOCKS_SHYREGEM = tag("storage_blocks/shyregem");
		public static final TagKey<Item> STORAGE_BLOCKS_SHYRESTONE = tag("storage_blocks/shyrestone");
		public static final TagKey<Item> STORAGE_BLOCKS_SKELETAL = tag("storage_blocks/skeletal");
		public static final TagKey<Item> STORAGE_BLOCKS_VARSIUM = tag("storage_blocks/varsium");

		public static final TagKey<Item> GEMS_BLOODSTONE = tag("gems/bloodstone");
		public static final TagKey<Item> GEMS_CRYSTALLITE = tag("gems/crystallite");
		public static final TagKey<Item> GEMS_GEMENYTE = tag("gems/gemenyte");
		public static final TagKey<Item> GEMS_JADE = tag("gems/jade");
		public static final TagKey<Item> GEMS_JEWELYTE = tag("gems/jewelyte");
		public static final TagKey<Item> GEMS_ORNAMYTE = tag("gems/ornamyte");
		public static final TagKey<Item> GEMS_SHYREGEM = tag("gems/shyregem");
		
		public static final TagKey<Item> INGOTS_BARONYTE = tag("ingots/baronyte");
		public static final TagKey<Item> INGOTS_BLAZIUM = tag("ingots/blazium");
		public static final TagKey<Item> INGOTS_ELECANIUM = tag("ingots/elecanium");
		public static final TagKey<Item> INGOTS_EMBERSTONE = tag("ingots/emberstone");
		public static final TagKey<Item> INGOTS_GHASTLY = tag("ingots/ghastly");
		public static final TagKey<Item> INGOTS_GHOULISH = tag("ingots/ghoulish");
		public static final TagKey<Item> INGOTS_LIMONITE = tag("ingots/limonite");
		public static final TagKey<Item> INGOTS_LUNAR = tag("ingots/lunar");
		public static final TagKey<Item> INGOTS_LYON = tag("ingots/lyon");
		public static final TagKey<Item> INGOTS_MYSTITE = tag("ingots/mystite");
		public static final TagKey<Item> INGOTS_SHYRESTONE = tag("ingots/shyrestone");
		public static final TagKey<Item> INGOTS_SKELETAL = tag("ingots/skeletal");
		public static final TagKey<Item> INGOTS_VARSIUM = tag("ingots/varsium");

		public static final TagKey<Item> NUGGETS_BARONYTE = tag("nuggets/baronyte");
		public static final TagKey<Item> NUGGETS_BLAZIUM = tag("nuggets/blazium");
		public static final TagKey<Item> NUGGETS_ELECANIUM = tag("nuggets/elecanium");
		public static final TagKey<Item> NUGGETS_EMBERSTONE = tag("nuggets/emberstone");
		public static final TagKey<Item> NUGGETS_GHASTLY = tag("nuggets/ghastly");
		public static final TagKey<Item> NUGGETS_GHOULISH = tag("nuggets/ghoulish");
		public static final TagKey<Item> NUGGETS_LIMONITE = tag("nuggets/limonite");
		public static final TagKey<Item> NUGGETS_LUNAR = tag("nuggets/lunar");
		public static final TagKey<Item> NUGGETS_LYON = tag("nuggets/lyon");
		public static final TagKey<Item> NUGGETS_MYSTITE = tag("nuggets/mystite");
		public static final TagKey<Item> NUGGETS_SHYRESTONE = tag("nuggets/shyrestone");
		public static final TagKey<Item> NUGGETS_SKELETAL = tag("nuggets/skeletal");
		public static final TagKey<Item> NUGGETS_VARSIUM = tag("nuggets/varsium");
		
		public static final TagKey<Item> PRECASIAN_BONE = aoaTag("precasian_bone");
		public static final TagKey<Item> ADVENT_RUNE = aoaTag("rune");
		public static final TagKey<Item> FRAME_BENCH_FRAME = aoaTag("frame_bench_frame");
		public static final TagKey<Item> INFUSION_STONE = aoaTag("infusion_stone");
		public static final TagKey<Item> SKILL_CRYSTAL = aoaTag("skill_crystal");
		public static final TagKey<Item> HAULING_FISH = aoaTag("hauling_fish");
		public static final TagKey<Item> FAUNAMANCER_TOOL = aoaTag("faunamancer_tool");

		public static final TagKey<Item> SWORDS = tag("swords");
		public static final TagKey<Item> CURRENCY = tag("currency");
		public static final TagKey<Item> CANDY = tag("candy");
		public static final TagKey<Item> FRUIT = tag("fruit");
		public static final TagKey<Item> MILK = tag("milk");
		public static final TagKey<Item> GINGERBREAD = tag("gingerbread");
		public static final TagKey<Item> MINTS = tag("mints");
		public static final TagKey<Item> FOOD = tag("food");
		public static final TagKey<Item> SHULKER_BOXES = tag("shulker_boxes");
		public static final TagKey<Item> IVORY = tag("ivory");
		public static final TagKey<Item> RODS_METAL = tag("rods/metal");

		public static final TagKey<Item> COOKIES = tag("cookies");
		public static final TagKey<Item> RAWMEATS = tag("rawmeats");
		public static final TagKey<Item> RAWBEEF = tag("rawbeef");
		public static final TagKey<Item> RAWPORK = tag("rawpork");
		public static final TagKey<Item> RAWCHICKEN = tag("rawchicken");
		public static final TagKey<Item> RAWFISH = tag("rawfish");
		public static final TagKey<Item> COOKEDFISH = tag("cookedfish");
		public static final TagKey<Item> COOKEDCHICKEN = tag("cookedchicken");
		public static final TagKey<Item> COOKEDPORK = tag("cookedpork");
		public static final TagKey<Item> COOKEDBEEF = tag("cookedbeef");

		private static TagKey<Item> aoaTag(String id) {
			return ItemTags.create(new ResourceLocation(AdventOfAscension.MOD_ID, id));
		}

		private static TagKey<Item> tag(String id) {
			return ItemTags.create(new ResourceLocation("forge", id));
		}
	}

	public static class Fluids {
		public static final TagKey<Fluid> CANDIED_WATER = tag("candied_water");
		public static final TagKey<Fluid> TOXIC_WASTE = tag("toxic_waste");

		private static TagKey<Fluid> aoaTag(String id) {
			return FluidTags.create(new ResourceLocation(AdventOfAscension.MOD_ID, id));
		}

		private static TagKey<Fluid> tag(String id) {
			return FluidTags.create(new ResourceLocation("forge", id));
		}
	}

	public static class Entities {
		public static final TagKey<EntityType<?>> WEAK_TO_MELEE = tag("weak_to_melee");
		public static final TagKey<EntityType<?>> WEAK_TO_MAGIC = tag("weak_to_magic");
		public static final TagKey<EntityType<?>> WEAK_TO_RANGED = tag("weak_to_ranged");
		public static final TagKey<EntityType<?>> WEAK_TO_EXPLOSIONS = tag("weak_to_explosions");

		private static TagKey<EntityType<?>> aoaTag(String id) {
			return create(AdventOfAscension.id(id));
		}

		private static TagKey<EntityType<?>> tag(String id) {
			return create(new ResourceLocation("forge", id));
		}

		public static TagKey<EntityType<?>> create(ResourceLocation id) {
			return TagKey.create(Registry.ENTITY_TYPE_REGISTRY, id);
		}
	}
}
