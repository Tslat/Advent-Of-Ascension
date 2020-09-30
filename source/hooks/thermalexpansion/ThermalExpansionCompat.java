package net.tslat.aoa3.hooks.thermalexpansion;

import cofh.thermalexpansion.util.managers.device.TapperManager;
import cofh.thermalexpansion.util.managers.dynamo.EnervationManager;
import cofh.thermalexpansion.util.managers.dynamo.NumismaticManager;
import cofh.thermalexpansion.util.managers.machine.*;
import cofh.thermalfoundation.init.TFFluids;
import cofh.thermalfoundation.init.TFItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.tslat.aoa3.block.functional.crops.CropBlock;
import net.tslat.aoa3.block.generation.plants.FlowerBlock;
import net.tslat.aoa3.block.generation.plants.GenericPlantBlock;
import net.tslat.aoa3.block.generation.plants.PlantStackable;
import net.tslat.aoa3.block.generation.wood.LogBlock;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.FluidsRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.utils.ConfigurationUtil;

public class ThermalExpansionCompat {
	public static void init() {
		addPulverizerRecipes();
		addSawmillRecipes();
		addPhytogenicInsolatorRecipes();
		addFractionatingStillRecipes();
		addGlacialPrecipitatorRecipes();
		addArborealExtractorRecipes();
		addEnervationDynamoRecipes();
		addNumismaticDynamoRecipes();
	}

	private static void addPulverizerRecipes() {
		if (!ConfigurationUtil.IntegrationsConfig.thermalExpansion.pulverizerRecipes)
			return;

		PulverizerManager.addRecipe(3000, new ItemStack(ItemRegister.ARCHERGUN_FRAME), new ItemStack(ItemRegister.SCRAP_METAL), new ItemStack(Items.IRON_INGOT), 20);
		PulverizerManager.addRecipe(3000, new ItemStack(ItemRegister.BLASTER_FRAME), new ItemStack(ItemRegister.SCRAP_METAL), new ItemStack(Items.IRON_INGOT), 20);
		PulverizerManager.addRecipe(3000, new ItemStack(ItemRegister.BOOTS_FRAME), new ItemStack(ItemRegister.SCRAP_METAL), new ItemStack(Items.IRON_INGOT), 20);
		PulverizerManager.addRecipe(3000, new ItemStack(ItemRegister.LEGGINGS_FRAME), new ItemStack(ItemRegister.SCRAP_METAL), new ItemStack(Items.IRON_INGOT), 20);
		PulverizerManager.addRecipe(3000, new ItemStack(ItemRegister.CHESTPLATE_FRAME), new ItemStack(ItemRegister.SCRAP_METAL), new ItemStack(Items.IRON_INGOT), 20);
		PulverizerManager.addRecipe(3000, new ItemStack(ItemRegister.HELMET_FRAME), new ItemStack(ItemRegister.SCRAP_METAL), new ItemStack(Items.IRON_INGOT), 20);
		PulverizerManager.addRecipe(3000, new ItemStack(ItemRegister.CANNON_FRAME), new ItemStack(ItemRegister.SCRAP_METAL), new ItemStack(Items.IRON_INGOT), 20);
		PulverizerManager.addRecipe(3000, new ItemStack(ItemRegister.GUN_FRAME), new ItemStack(ItemRegister.SCRAP_METAL), new ItemStack(Items.IRON_INGOT), 20);
		PulverizerManager.addRecipe(3000, new ItemStack(ItemRegister.SHOTGUN_FRAME), new ItemStack(ItemRegister.SCRAP_METAL), new ItemStack(Items.IRON_INGOT), 20);
		PulverizerManager.addRecipe(3000, new ItemStack(ItemRegister.SNIPER_FRAME), new ItemStack(ItemRegister.SCRAP_METAL), new ItemStack(Items.IRON_INGOT), 20);
		PulverizerManager.addRecipe(2000, new ItemStack(ItemRegister.HARDENED_CONFETTI_BALL), new ItemStack(ItemRegister.CONFETTI_PILE, 4));
		PulverizerManager.addRecipe(2000, new ItemStack(BlockRegister.GINGERBREAD), new ItemStack(ItemRegister.GINGERBREAD_COOKIE, 2), new ItemStack(ItemRegister.GINGERBREAD_WING, 2), 50);
		PulverizerManager.addRecipe(3000, new ItemStack(BlockRegister.CHOCOLATE_BLOCK), new ItemStack(Items.DYE, 2, 3), new ItemStack(Items.SUGAR, 3), 40);
		PulverizerManager.addRecipe(3000, new ItemStack(BlockRegister.DARK_CHOCOLATE_BLOCK), new ItemStack(Items.DYE, 2, 3), new ItemStack(Items.SUGAR, 3), 40);
		PulverizerManager.addRecipe(6000, new ItemStack(ItemRegister.GOLD_COIN), new ItemStack(Items.GOLD_INGOT, 3));
		PulverizerManager.addRecipe(4000, new ItemStack(ItemRegister.ARMOUR_PLATING), new ItemStack(ItemRegister.CHITIN, 4));
	}

	private static void addSawmillRecipes() {
		if (!ConfigurationUtil.IntegrationsConfig.thermalExpansion.sawmillRecipes)
			return;

		for (Block block : ForgeRegistries.BLOCKS.getValuesCollection()) {
			if ((block instanceof PlantStackable || block instanceof GenericPlantBlock) && block.getDefaultState().getMaterial() == Material.PLANTS)
				SawmillManager.addRecipe(2000, new ItemStack(block, 8), new ItemStack(TFItems.itemMaterial, 1, 816));
		}

		SawmillManager.addRecipe(2000, new ItemStack(BlockRegister.CREEP_VINES, 8), new ItemStack(TFItems.itemMaterial, 1, 816));
	}

	private static void addPhytogenicInsolatorRecipes() {
		if (!ConfigurationUtil.IntegrationsConfig.thermalExpansion.phytogenicInsolatorRecipes)
			return;

		for (Block block : ForgeRegistries.BLOCKS.getValuesCollection()) {
			if (block instanceof CropBlock) {
				CropBlock crop = (CropBlock)block;

				if (!(crop.getSeeds() instanceof Item))
					continue;

				Item seeds = (Item)crop.getSeeds();

				if (seeds != null) {
					InsolatorManager.addRecipe(5000, new ItemStack(TFItems.itemFertilizer), new ItemStack(seeds), new ItemStack(crop.getCrop(), 2), new ItemStack(seeds), 110);
					InsolatorManager.addRecipe(7500, new ItemStack(TFItems.itemFertilizer, 1, 1), new ItemStack(seeds), new ItemStack(crop.getCrop(), 4), new ItemStack(seeds), 125);
					InsolatorManager.addRecipe(10000, new ItemStack(TFItems.itemFertilizer, 1, 2), new ItemStack(seeds), new ItemStack(crop.getCrop(), 6), new ItemStack(seeds), 150);
				}
			}
			else if (block instanceof FlowerBlock) {
				InsolatorManager.addRecipe(5000, new ItemStack(TFItems.itemFertilizer), new ItemStack(block), new ItemStack(block, 3));
				InsolatorManager.addRecipe(7500, new ItemStack(TFItems.itemFertilizer, 1, 1), new ItemStack(block), new ItemStack(block, 6));
				InsolatorManager.addRecipe(10000, new ItemStack(TFItems.itemFertilizer, 1, 2), new ItemStack(block), new ItemStack(block, 9));
			}
		}
	}

	private static void addFractionatingStillRecipes() {
		if (!ConfigurationUtil.IntegrationsConfig.thermalExpansion.fractionatingStillRecipes)
			return;

		RefineryManager.addRecipe(6000, new FluidStack(FluidsRegister.CANDIED_WATER, 200), new FluidStack(FluidRegistry.WATER, 100), new ItemStack(Items.SUGAR, 2));
	}

	private static void addGlacialPrecipitatorRecipes() {
		if (!ConfigurationUtil.IntegrationsConfig.thermalExpansion.glacialPrecipitatorRecipes)
			return;

		PrecipitatorManager.addRecipe(6000, new ItemStack(ItemRegister.ICE_CRYSTAL), new FluidStack(FluidRegistry.WATER, 4000));
	}

	private static void addArborealExtractorRecipes() {
		if (!ConfigurationUtil.IntegrationsConfig.thermalExpansion.arborealExtractorRecipes)
			return;

		for (Block block : ForgeRegistries.BLOCKS.getValuesCollection()) {
			if (block instanceof LogBlock)
				TapperManager.addStandardMapping(new ItemStack(block), new FluidStack(TFFluids.fluidResin, 50));
		}

		TapperManager.addStandardMapping(new ItemStack(BlockRegister.AQUA_COTTON_CANDY), new FluidStack(FluidsRegister.CANDIED_WATER, 200));
		TapperManager.addStandardMapping(new ItemStack(BlockRegister.PINK_COTTON_CANDY), new FluidStack(FluidsRegister.CANDIED_WATER, 200));
	}

	private static void addEnervationDynamoRecipes() {
		if (!ConfigurationUtil.IntegrationsConfig.thermalExpansion.enervationDynamoRecipes)
			return;

		EnervationManager.addFuel(new ItemStack(ItemRegister.POWER_CORE), 1200000);
		EnervationManager.addFuel(new ItemStack(ItemRegister.RUNIC_ENERGY), 900000);
		EnervationManager.addFuel(new ItemStack(ItemRegister.POWER_RUNE), 30000);
		EnervationManager.addFuel(new ItemStack(ItemRegister.ENERGY_RUNE), 40000);
	}

	private static void addNumismaticDynamoRecipes() {
		if (!ConfigurationUtil.IntegrationsConfig.thermalExpansion.numismaticDynamoRecipes)
			return;

		NumismaticManager.addFuel(new ItemStack(ItemRegister.COPPER_COIN), 20000);
		NumismaticManager.addFuel(new ItemStack(ItemRegister.SILVER_COIN), 200000);
		NumismaticManager.addFuel(new ItemStack(ItemRegister.GOLD_COIN), 700000);
		NumismaticManager.addFuel(new ItemStack(ItemRegister.LUNAVER_COIN), 2000000);
	}
}
