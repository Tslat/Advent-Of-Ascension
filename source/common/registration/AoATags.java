package net.tslat.aoa3.common.registration;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.advent.AdventOfAscension;

public final class AoATags {
	public static class Blocks {
		public static final Tag<Block> GRASS = tag("grass");

		public static final Tag<Block> AMETHYST_ORE = tag("ores/amethyst");
		public static final Tag<Block> BARONYTE_ORE = tag("ores/baronyte");
		public static final Tag<Block> BLAZIUM_ORE = tag("ores/blazium");
		public static final Tag<Block> BLOODSTONE_ORE = tag("ores/bloodstone");
		public static final Tag<Block> BLUE_GEMSTONE_ORE = tag("ores/blue_gemstone");
		public static final Tag<Block> CHARGED_RUNIUM_ORE = tag("ores/charged_runium");
		public static final Tag<Block> CHESTBONE_FRAGMENTS_ORE = tag("ores/chestbone_fragments");
		public static final Tag<Block> CRYSTALLITE_ORE = tag("ores/crystallite");
		public static final Tag<Block> ELECANIUM_ORE = tag("ores/elecanium");
		public static final Tag<Block> EMBERSTONE_ORE = tag("ores/emberstone");
		public static final Tag<Block> FOOTBONE_FRAGMENTS_ORE = tag("ores/footbone_fragments");
		public static final Tag<Block> GEMENYTE_ORE = tag("ores/gemenyte");
		public static final Tag<Block> GHASTLY_ORE = tag("ores/ghastly");
		public static final Tag<Block> GHOULISH_ORE = tag("ores/ghoulish");
		public static final Tag<Block> GREEN_GEMSTONE_ORE = tag("ores/green_gemstone");
		public static final Tag<Block> JADE_ORE = tag("ores/jade");
		public static final Tag<Block> JEWELYTE_ORE = tag("ores/jewelyte");
		public static final Tag<Block> LEGBONE_FRAGMENTS_ORE = tag("ores/legbone_fragments");
		public static final Tag<Block> LIMONITE_ORE = tag("ores/limonite");
		public static final Tag<Block> LYON_ORE = tag("ores/lyon");
		public static final Tag<Block> MYSTITE_ORE = tag("ores/mystite");
		public static final Tag<Block> ORNAMYTE_ORE = tag("ores/ornamyte");
		public static final Tag<Block> PURPLE_GEMSTONE_ORE = tag("ores/purple_gemstone");
		public static final Tag<Block> RED_GEMSTONE_ORE = tag("ores/red_gemstone");
		public static final Tag<Block> ROSITE_ORE = tag("ores/rosite");
		public static final Tag<Block> RUNIUM_ORE = tag("ores/runium");
		public static final Tag<Block> SAPPHIRE_ORE = tag("ores/sapphire");
		public static final Tag<Block> SHYREGEM_ORE = tag("ores/shyregem");
		public static final Tag<Block> SHYRESTONE_ORE = tag("ores/shyrestone");
		public static final Tag<Block> SKULLBONE_FRAGMENTS_ORE = tag("ores/skullbone_fragments");
		public static final Tag<Block> VARSIUM_ORE = tag("ores/varsium");
		public static final Tag<Block> WHITE_GEMSTONE_ORE = tag("ores/white_gemstone");
		public static final Tag<Block> YELLOW_GEMSTONE_ORE = tag("ores/yellow_gemstone");
		public static final Tag<Block> STORAGE_BLOCKS_AMETHYST = tag("storage_blocks/amethyst");
		public static final Tag<Block> STORAGE_BLOCKS_BARONYTE = tag("storage_blocks/baronyte");
		public static final Tag<Block> STORAGE_BLOCKS_BLAZIUM = tag("storage_blocks/blazium");
		public static final Tag<Block> STORAGE_BLOCKS_BLOODSTONE = tag("storage_blocks/bloodstone");
		public static final Tag<Block> STORAGE_BLOCKS_CRYSTALLITE = tag("storage_blocks/crystallite");
		public static final Tag<Block> STORAGE_BLOCKS_ELECANIUM = tag("storage_blocks/elecanium");
		public static final Tag<Block> STORAGE_BLOCKS_EMBERSTONE = tag("storage_blocks/emberstone");
		public static final Tag<Block> STORAGE_BLOCKS_GEMENYTE = tag("storage_blocks/gemenyte");
		public static final Tag<Block> STORAGE_BLOCKS_GHASTLY = tag("storage_blocks/ghastly");
		public static final Tag<Block> STORAGE_BLOCKS_GHOULISH = tag("storage_blocks/ghoulish");
		public static final Tag<Block> STORAGE_BLOCKS_JADE = tag("storage_blocks/jade");
		public static final Tag<Block> STORAGE_BLOCKS_JEWELYTE = tag("storage_blocks/jewelyte");
		public static final Tag<Block> STORAGE_BLOCKS_LIMONITE = tag("storage_blocks/limonite");
		public static final Tag<Block> STORAGE_BLOCKS_LUNAR = tag("storage_blocks/lunar");
		public static final Tag<Block> STORAGE_BLOCKS_LYON = tag("storage_blocks/lyon");
		public static final Tag<Block> STORAGE_BLOCKS_MYSTITE = tag("storage_blocks/mystite");
		public static final Tag<Block> STORAGE_BLOCKS_ORNAMYTE = tag("storage_blocks/ornamyte");
		public static final Tag<Block> STORAGE_BLOCKS_ROSITE = tag("storage_blocks/rosite");
		public static final Tag<Block> STORAGE_BLOCKS_SAPPHIRE = tag("storage_blocks/sapphire");
		public static final Tag<Block> STORAGE_BLOCKS_SHYREGEM = tag("storage_blocks/shyregem");
		public static final Tag<Block> STORAGE_BLOCKS_SHYRESTONE = tag("storage_blocks/shyrestone");
		public static final Tag<Block> STORAGE_BLOCKS_SKELETAL_INGOT = tag("storage_blocks/skeletal_ingot");
		public static final Tag<Block> STORAGE_BLOCKS_VARSIUM = tag("storage_blocks/varsium");

		public static final Tag<Block> CARVED_RUNE = aoaTag("carved_rune");
		public static final Tag<Block> LUNAR_ORB = aoaTag("lunar_orb");

		private static Tag<Block> aoaTag(String id) {
			return new BlockTags.Wrapper(new ResourceLocation(AdventOfAscension.MOD_ID, id));
		}

		private static Tag<Block> tag(String id) {
			return new BlockTags.Wrapper(new ResourceLocation("forge", id));
		}
	}

	public static class Items {
		public static final Tag<Item> DIRT = tag("dirt");
		public static final Tag<Item> GRASS = tag("grass");

		public static final Tag<Item> AMETHYST_ORE = tag("ores/amethyst");
		public static final Tag<Item> BARONYTE_ORE = tag("ores/baronyte");
		public static final Tag<Item> BLAZIUM_ORE = tag("ores/blazium");
		public static final Tag<Item> BLOODSTONE_ORE = tag("ores/bloodstone");
		public static final Tag<Item> BLUE_GEMSTONE_ORE = tag("ores/blue_gemstone");
		public static final Tag<Item> CHARGED_RUNIUM_ORE = tag("ores/charged_runium");
		public static final Tag<Item> CHESTBONE_FRAGMENTS_ORE = tag("ores/chestbone_fragments");
		public static final Tag<Item> CRYSTALLITE_ORE = tag("ores/crystallite");
		public static final Tag<Item> ELECANIUM_ORE = tag("ores/elecanium");
		public static final Tag<Item> EMBERSTONE_ORE = tag("ores/emberstone");
		public static final Tag<Item> FOOTBONE_FRAGMENTS_ORE = tag("ores/footbone_fragments");
		public static final Tag<Item> GEMENYTE_ORE = tag("ores/gemenyte");
		public static final Tag<Item> GHASTLY_ORE = tag("ores/ghastly");
		public static final Tag<Item> GHOULISH_ORE = tag("ores/ghoulish");
		public static final Tag<Item> GREEN_GEMSTONE_ORE = tag("ores/green_gemstone");
		public static final Tag<Item> JADE_ORE = tag("ores/jade");
		public static final Tag<Item> JEWELYTE_ORE = tag("ores/jewelyte");
		public static final Tag<Item> LEGBONE_FRAGMENTS_ORE = tag("ores/legbone_fragments");
		public static final Tag<Item> LIMONITE_ORE = tag("ores/limonite");
		public static final Tag<Item> LYON_ORE = tag("ores/lyon");
		public static final Tag<Item> MYSTITE_ORE = tag("ores/mystite");
		public static final Tag<Item> ORNAMYTE_ORE = tag("ores/ornamyte");
		public static final Tag<Item> PURPLE_GEMSTONE_ORE = tag("ores/purple_gemstone");
		public static final Tag<Item> RED_GEMSTONE_ORE = tag("ores/red_gemstone");
		public static final Tag<Item> ROSITE_ORE = tag("ores/rosite");
		public static final Tag<Item> RUNIUM_ORE = tag("ores/runium");
		public static final Tag<Item> SAPPHIRE_ORE = tag("ores/sapphire");
		public static final Tag<Item> SHYREGEM_ORE = tag("ores/shyregem");
		public static final Tag<Item> SHYRESTONE_ORE = tag("ores/shyrestone");
		public static final Tag<Item> SKULLBONE_FRAGMENTS_ORE = tag("ores/skullbone_fragments");
		public static final Tag<Item> VARSIUM_ORE = tag("ores/varsium");
		public static final Tag<Item> WHITE_GEMSTONE_ORE = tag("ores/white_gemstone");
		public static final Tag<Item> YELLOW_GEMSTONE_ORE = tag("ores/yellow_gemstone");
		
		public static final Tag<Item> STORAGE_BLOCKS_AMETHYST = tag("storage_blocks/amethyst");
		public static final Tag<Item> STORAGE_BLOCKS_BARONYTE = tag("storage_blocks/baronyte");
		public static final Tag<Item> STORAGE_BLOCKS_BLAZIUM = tag("storage_blocks/blazium");
		public static final Tag<Item> STORAGE_BLOCKS_BLOODSTONE = tag("storage_blocks/bloodstone");
		public static final Tag<Item> STORAGE_BLOCKS_CRYSTALLITE = tag("storage_blocks/crystallite");
		public static final Tag<Item> STORAGE_BLOCKS_ELECANIUM = tag("storage_blocks/elecanium");
		public static final Tag<Item> STORAGE_BLOCKS_EMBERSTONE = tag("storage_blocks/emberstone");
		public static final Tag<Item> STORAGE_BLOCKS_GEMENYTE = tag("storage_blocks/gemenyte");
		public static final Tag<Item> STORAGE_BLOCKS_GHASTLY = tag("storage_blocks/ghastly");
		public static final Tag<Item> STORAGE_BLOCKS_GHOULISH = tag("storage_blocks/ghoulish");
		public static final Tag<Item> STORAGE_BLOCKS_JADE = tag("storage_blocks/jade");
		public static final Tag<Item> STORAGE_BLOCKS_JEWELYTE = tag("storage_blocks/jewelyte");
		public static final Tag<Item> STORAGE_BLOCKS_LIMONITE = tag("storage_blocks/limonite");
		public static final Tag<Item> STORAGE_BLOCKS_LUNAR = tag("storage_blocks/lunar");
		public static final Tag<Item> STORAGE_BLOCKS_LYON = tag("storage_blocks/lyon");
		public static final Tag<Item> STORAGE_BLOCKS_MYSTITE = tag("storage_blocks/mystite");
		public static final Tag<Item> STORAGE_BLOCKS_ORNAMYTE = tag("storage_blocks/ornamyte");
		public static final Tag<Item> STORAGE_BLOCKS_ROSITE = tag("storage_blocks/rosite");
		public static final Tag<Item> STORAGE_BLOCKS_SAPPHIRE = tag("storage_blocks/sapphire");
		public static final Tag<Item> STORAGE_BLOCKS_SHYREGEM = tag("storage_blocks/shyregem");
		public static final Tag<Item> STORAGE_BLOCKS_SHYRESTONE = tag("storage_blocks/shyrestone");
		public static final Tag<Item> STORAGE_BLOCKS_SKELETAL_INGOT = tag("storage_blocks/skeletal_ingot");
		public static final Tag<Item> STORAGE_BLOCKS_VARSIUM = tag("storage_blocks/varsium");

		public static final Tag<Item> GEMS_AMETHYST = tag("gems/amethyst");
		public static final Tag<Item> GEMS_BLOODSTONE = tag("gems/bloodstone");
		public static final Tag<Item> GEMS_CRYSTALLITE = tag("gems/crystallite");
		public static final Tag<Item> GEMS_GEMENYTE = tag("gems/gemenyte");
		public static final Tag<Item> GEMS_JADE = tag("gems/jade");
		public static final Tag<Item> GEMS_JEWELYTE = tag("gems/jewelyte");
		public static final Tag<Item> GEMS_ORNAMYTE = tag("gems/ornamyte");
		public static final Tag<Item> GEMS_SAPPHIRE = tag("gems/sapphire");
		public static final Tag<Item> GEMS_SHYREGEM = tag("gems/shyregem");
		
		public static final Tag<Item> INGOTS_BARONYTE = tag("ingots/baronyte");
		public static final Tag<Item> INGOTS_BLAZIUM = tag("ingots/blazium");
		public static final Tag<Item> INGOTS_ELECANIUM = tag("ingots/elecanium");
		public static final Tag<Item> INGOTS_EMBERSTONE = tag("ingots/emberstone");
		public static final Tag<Item> INGOTS_GHASTLY = tag("ingots/ghastly");
		public static final Tag<Item> INGOTS_GHOULISH = tag("ingots/ghoulish");
		public static final Tag<Item> INGOTS_LIMONITE = tag("ingots/limonite");
		public static final Tag<Item> INGOTS_LUNAR = tag("ingots/lunar");
		public static final Tag<Item> INGOTS_LYON = tag("ingots/lyon");
		public static final Tag<Item> INGOTS_MYSTITE = tag("ingots/mystite");
		public static final Tag<Item> INGOTS_ROSITE = tag("ingots/rosite");
		public static final Tag<Item> INGOTS_SHYRESTONE = tag("ingots/shyrestone");
		public static final Tag<Item> INGOTS_SKELETAL = tag("ingots/skeletal");
		public static final Tag<Item> INGOTS_VARSIUM = tag("ingots/varsium");

		public static final Tag<Item> NUGGETS_BARONYTE = tag("nuggets/baronyte");
		public static final Tag<Item> NUGGETS_BLAZIUM = tag("nuggets/blazium");
		public static final Tag<Item> NUGGETS_ELECANIUM = tag("nuggets/elecanium");
		public static final Tag<Item> NUGGETS_EMBERSTONE = tag("nuggets/emberstone");
		public static final Tag<Item> NUGGETS_GHASTLY = tag("nuggets/ghastly");
		public static final Tag<Item> NUGGETS_GHOULISH = tag("nuggets/ghoulish");
		public static final Tag<Item> NUGGETS_LIMONITE = tag("nuggets/limonite");
		public static final Tag<Item> NUGGETS_LUNAR = tag("nuggets/lunar");
		public static final Tag<Item> NUGGETS_LYON = tag("nuggets/lyon");
		public static final Tag<Item> NUGGETS_MYSTITE = tag("nuggets/mystite");
		public static final Tag<Item> NUGGETS_ROSITE = tag("nuggets/rosite");
		public static final Tag<Item> NUGGETS_SHYRESTONE = tag("nuggets/shyrestone");
		public static final Tag<Item> NUGGETS_VARSIUM = tag("nuggets/varsium");
		
		public static final Tag<Item> PRECASIAN_BONE = aoaTag("precasian_bone");
		public static final Tag<Item> ADVENT_RUNE = aoaTag("rune");
		public static final Tag<Item> FRAME_BENCH_FRAME = aoaTag("frame_bench_frame");
		public static final Tag<Item> ADVENT_TOKENS = aoaTag("tokens");
		public static final Tag<Item> INFUSION_STONE = aoaTag("infusion_stone");
		public static final Tag<Item> SKILL_CRYSTAL = aoaTag("skill_crystal");

		public static final Tag<Item> CURRENCY = tag("currency");
		public static final Tag<Item> COOKED_MEAT = tag("cooked_meat");
		public static final Tag<Item> RAW_MEAT = tag("raw_meat");
		public static final Tag<Item> FRESH_FISH = tag("fresh_fish");
		public static final Tag<Item> CANDY = tag("candy");
		public static final Tag<Item> COOKIE = tag("cookie");
		public static final Tag<Item> FRUIT = tag("fruit");
		public static final Tag<Item> MILK = tag("milk");
		public static final Tag<Item> GINGERBREAD = tag("gingerbread");
		public static final Tag<Item> MINTS = tag("mints");
		public static final Tag<Item> FOOD = tag("food");

		private static Tag<Item> aoaTag(String id) {
			return new ItemTags.Wrapper(new ResourceLocation(AdventOfAscension.MOD_ID, id));
		}

		private static Tag<Item> tag(String id) {
			return new ItemTags.Wrapper(new ResourceLocation("forge", id));
		}
	}
}
