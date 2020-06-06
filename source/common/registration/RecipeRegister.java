package net.tslat.aoa3.common.registration;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.tslat.aoa3.advent.AdventOfAscension;

@SuppressWarnings("ConstantConditions")
public class RecipeRegister {
	public static void registerRecipes() {
		AdventOfAscension.logOptionalMessage("Adding smelting recipes");

		GameRegistry.addSmelting(BlockRegister.AMETHYST_ORE, new ItemStack(ItemRegister.AMETHYST), 3.0f);
		GameRegistry.addSmelting(BlockRegister.BARONYTE_ORE, new ItemStack(ItemRegister.BARONYTE_INGOT), 3.0f);
		GameRegistry.addSmelting(BlockRegister.BLAZIUM_ORE, new ItemStack(ItemRegister.BLAZIUM_INGOT), 3.0f);
		GameRegistry.addSmelting(BlockRegister.BLOODSTONE_ORE, new ItemStack(ItemRegister.BLOODSTONE), 3.0f);
		GameRegistry.addSmelting(BlockRegister.CHARGED_RUNIUM_ORE, new ItemStack(ItemRegister.CHARGED_RUNIUM_CHUNK), 2.0f);
		GameRegistry.addSmelting(BlockRegister.CRYSTALLITE_ORE, new ItemStack(ItemRegister.CRYSTALLITE), 3.0f);
		GameRegistry.addSmelting(BlockRegister.ELECANIUM_ORE, new ItemStack(ItemRegister.ELECANIUM_INGOT), 3.0f);
		GameRegistry.addSmelting(BlockRegister.EMBERSTONE_ORE, new ItemStack(ItemRegister.EMBERSTONE_INGOT), 3.0f);
		GameRegistry.addSmelting(BlockRegister.GEMENYTE_ORE, new ItemStack(ItemRegister.GEMENYTE), 2.0f);
		GameRegistry.addSmelting(BlockRegister.GHASTLY_ORE, new ItemStack(ItemRegister.GHASTLY_INGOT), 3.0f);
		GameRegistry.addSmelting(BlockRegister.GHOULISH_ORE, new ItemStack(ItemRegister.GHOULISH_INGOT), 3.0f);
		GameRegistry.addSmelting(BlockRegister.JADE_ORE, new ItemStack(ItemRegister.JADE), 3.0f);
		GameRegistry.addSmelting(BlockRegister.JEWELYTE_ORE, new ItemStack(ItemRegister.JEWELYTE), 2.0f);
		GameRegistry.addSmelting(BlockRegister.LIMONITE_ORE, new ItemStack(ItemRegister.LIMONITE_INGOT), 3.0f);
		GameRegistry.addSmelting(BlockRegister.LYON_ORE, new ItemStack(ItemRegister.LYON_INGOT), 3.0f);
		GameRegistry.addSmelting(BlockRegister.MYSTITE_ORE, new ItemStack(ItemRegister.MYSTITE_INGOT), 3.0f);
		GameRegistry.addSmelting(BlockRegister.ORNAMYTE_ORE, new ItemStack(ItemRegister.ORNAMYTE), 3.0f);
		GameRegistry.addSmelting(BlockRegister.ROSITE_ORE, new ItemStack(ItemRegister.ROSITE_INGOT), 3.0f);
		GameRegistry.addSmelting(BlockRegister.RUNIUM_ORE, new ItemStack(ItemRegister.RUNIUM_CHUNK), 2.0f);
		GameRegistry.addSmelting(BlockRegister.SAPPHIRE_ORE, new ItemStack(ItemRegister.SAPPHIRE), 3.0f);
		GameRegistry.addSmelting(BlockRegister.BLUE_CRYSTAL_ORE, new ItemStack(ItemRegister.BLUE_GEMSTONES), 3.0f);
		GameRegistry.addSmelting(BlockRegister.GREEN_CRYSTAL_ORE, new ItemStack(ItemRegister.GREEN_GEMSTONES), 3.0f);
		GameRegistry.addSmelting(BlockRegister.PURPLE_CRYSTAL_ORE, new ItemStack(ItemRegister.PURPLE_GEMSTONES), 3.0f);
		GameRegistry.addSmelting(BlockRegister.RED_CRYSTAL_ORE, new ItemStack(ItemRegister.RED_GEMSTONES), 3.0f);
		GameRegistry.addSmelting(BlockRegister.WHITE_CRYSTAL_ORE, new ItemStack(ItemRegister.WHITE_GEMSTONES), 3.0f);
		GameRegistry.addSmelting(BlockRegister.YELLOW_CRYSTAL_ORE, new ItemStack(ItemRegister.YELLOW_GEMSTONES), 3.0f);
		GameRegistry.addSmelting(BlockRegister.SHYREGEM_ORE, new ItemStack(ItemRegister.SHYREGEM), 5.0f);
		GameRegistry.addSmelting(BlockRegister.SHYRESTONE_ORE, new ItemStack(ItemRegister.SHYRESTONE_INGOT), 2.0f);
		GameRegistry.addSmelting(BlockRegister.VARSIUM_ORE, new ItemStack(ItemRegister.VARSIUM_INGOT), 3.0f);
		GameRegistry.addSmelting(ItemRegister.CHESTBONE_FRAGMENT, new ItemStack(ItemRegister.SKELETAL_INGOT), 3.0f);
		GameRegistry.addSmelting(ItemRegister.FOOTBONE_FRAGMENT, new ItemStack(ItemRegister.SKELETAL_INGOT), 3.0f);
		GameRegistry.addSmelting(ItemRegister.LEGBONE_FRAGMENT, new ItemStack(ItemRegister.SKELETAL_INGOT), 3.0f);
		GameRegistry.addSmelting(ItemRegister.SKULLBONE_FRAGMENT, new ItemStack(ItemRegister.SKELETAL_INGOT), 3.0f);
		GameRegistry.addSmelting(ItemRegister.RAW_CANDLEFISH, new ItemStack(ItemRegister.CANDLEFISH), 3.0f);
		GameRegistry.addSmelting(ItemRegister.RAW_CHARGER_SHANK, new ItemStack(ItemRegister.CHARGER_SHANK), 3.0f);
		GameRegistry.addSmelting(ItemRegister.RAW_CHIMERA_CHOP, new ItemStack(ItemRegister.CHIMERA_CHOP), 3.0f);
		GameRegistry.addSmelting(ItemRegister.RAW_CRIMSON_SKIPPER, new ItemStack(ItemRegister.CRIMSON_SKIPPER), 3.0f);
		GameRegistry.addSmelting(ItemRegister.RAW_CRIMSON_STRIPEFISH, new ItemStack(ItemRegister.CRIMSON_STRIPEFISH), 3.0f);
		GameRegistry.addSmelting(ItemRegister.RAW_DARK_HATCHETFISH, new ItemStack(ItemRegister.DARK_HATCHETFISH), 3.0f);
		GameRegistry.addSmelting(ItemRegister.RAW_FINGERFISH, new ItemStack(ItemRegister.FINGERFISH), 3.0f);
		GameRegistry.addSmelting(ItemRegister.RAW_FURLION_CHOP, new ItemStack(ItemRegister.FURLION_CHOP), 3.0f);
		GameRegistry.addSmelting(ItemRegister.RAW_GOLDEN_GULLFISH, new ItemStack(ItemRegister.GOLDEN_GULLFISH), 3.0f);
		GameRegistry.addSmelting(ItemRegister.RAW_HALYCON_BEEF, new ItemStack(ItemRegister.HALYCON_BEEF), 3.0f);
		GameRegistry.addSmelting(ItemRegister.RUSTED_IRON_INGOT, new ItemStack(ItemRegister.SCRAP_METAL), 1.0f);
		GameRegistry.addSmelting(ItemRegister.RAW_IRONBACK, new ItemStack(ItemRegister.IRONBACK), 3.0f);
		GameRegistry.addSmelting(ItemRegister.RAW_LIMEFISH, new ItemStack(ItemRegister.LIMEFISH), 3.0f);
		GameRegistry.addSmelting(ItemRegister.RAW_PEARL_STRIPEFISH, new ItemStack(ItemRegister.PEARL_STRIPEFISH), 3.0f);
		GameRegistry.addSmelting(ItemRegister.RAW_RAINBOWFISH, new ItemStack(ItemRegister.RAINBOWFISH), 3.0f);
		GameRegistry.addSmelting(ItemRegister.RAW_RAZORFISH, new ItemStack(ItemRegister.RAZORFISH), 3.0f);
		GameRegistry.addSmelting(ItemRegister.RAW_ROCKETFISH, new ItemStack(ItemRegister.ROCKETFISH), 3.0f);
		GameRegistry.addSmelting(ItemRegister.RAW_SAILBACK, new ItemStack(ItemRegister.SAILBACK), 3.0f);
		GameRegistry.addSmelting(ItemRegister.RAW_SAPPHIRE_STRIDER, new ItemStack(ItemRegister.SAPPHIRE_STRIDER), 3.0f);
		GameRegistry.addSmelting(ItemRegister.SCRAP_METAL, new ItemStack(Items.IRON_NUGGET), 1.0f);
		GameRegistry.addSmelting(ItemRegister.RAW_TURQUOISE_STRIPEFISH, new ItemStack(ItemRegister.TURQUOISE_STRIPEFISH), 3.0f);
		GameRegistry.addSmelting(ItemRegister.RAW_URSA_MEAT, new ItemStack(ItemRegister.URSA_MEAT), 3.0f);
		GameRegistry.addSmelting(ItemRegister.RAW_VIOLET_SKIPPER, new ItemStack(ItemRegister.VIOLET_SKIPPER), 3.0f);
	}
}
