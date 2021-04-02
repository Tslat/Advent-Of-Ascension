package net.tslat.aoa3.integration.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.*;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.container.DivineStationContainer;
import net.tslat.aoa3.common.container.InfusionTableContainer;
import net.tslat.aoa3.common.container.recipe.FrameBenchRecipe;
import net.tslat.aoa3.common.container.recipe.InfusionRecipe;
import net.tslat.aoa3.common.container.recipe.WhitewashingRecipe;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoARecipes;
import net.tslat.aoa3.integration.IntegrationManager;
import net.tslat.aoa3.integration.jei.ingredient.subtype.TrophySubtypeInterpreter;
import net.tslat.aoa3.integration.jei.recipe.framebench.FrameBenchRecipeCategory;
import net.tslat.aoa3.integration.jei.recipe.framebench.FrameBenchRecipeTransferInfo;
import net.tslat.aoa3.integration.jei.recipe.imbuing.ImbuingRecipeCategory;
import net.tslat.aoa3.integration.jei.recipe.imbuing.ImbuingRecipeTransferInfo;
import net.tslat.aoa3.integration.jei.recipe.infusion.InfusionRecipeCategory;
import net.tslat.aoa3.integration.jei.recipe.infusion.InfusionRecipeTransferInfo;
import net.tslat.aoa3.integration.jei.recipe.upgradekit.UpgradeKitRecipeCategory;
import net.tslat.aoa3.integration.jei.recipe.upgradekit.UpgradeKitRecipeTransferInfo;
import net.tslat.aoa3.integration.jei.recipe.whitewashing.WhitewashingRecipeCategory;
import net.tslat.aoa3.integration.jei.recipe.whitewashing.WhitewashingRecipeTransferInfo;

import java.util.ArrayList;
import java.util.Collection;

@JeiPlugin
public class JEIIntegration implements IModPlugin {
	@Override
	public ResourceLocation getPluginUid() {
		return new ResourceLocation(AdventOfAscension.MOD_ID, "core");
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
		if (!IntegrationManager.isJEIActive())
			return;

		registration.addRecipeCatalyst(new ItemStack(AoABlocks.DIVINE_STATION.get()), UpgradeKitRecipeCategory.ID);
		registration.addRecipeCatalyst(new ItemStack(AoABlocks.INFUSION_TABLE.get()), InfusionRecipeCategory.ID, ImbuingRecipeCategory.ID);
		registration.addRecipeCatalyst(new ItemStack(AoABlocks.FRAME_BENCH.get()), FrameBenchRecipeCategory.ID);
		registration.addRecipeCatalyst(new ItemStack(AoABlocks.WHITEWASHING_TABLE.get()), WhitewashingRecipeCategory.ID);
	}

	@Override
	public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
		if (!IntegrationManager.isJEIActive())
			return;

		registration.addRecipeTransferHandler(new UpgradeKitRecipeTransferInfo());
		registration.addRecipeTransferHandler(new ImbuingRecipeTransferInfo());
		registration.addRecipeTransferHandler(new InfusionRecipeTransferInfo());
		registration.addRecipeTransferHandler(new FrameBenchRecipeTransferInfo());
		registration.addRecipeTransferHandler(new WhitewashingRecipeTransferInfo());
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		if (Minecraft.getInstance().getConnection() == null || !IntegrationManager.isJEIActive())
			return;

		RecipeManager recipeManager = Minecraft.getInstance().getConnection().getRecipeManager();

		registration.addRecipes(compileUpgradeKitRecipes(recipeManager), UpgradeKitRecipeCategory.ID);
		registration.addRecipes(compileImbuingRecipes(recipeManager), ImbuingRecipeCategory.ID);
		registration.addRecipes(compileInfusionRecipes(recipeManager), InfusionRecipeCategory.ID);
		registration.addRecipes(compileFrameBenchRecipes(recipeManager), FrameBenchRecipeCategory.ID);
		registration.addRecipes(compileWhitewashingRecipes(recipeManager), WhitewashingRecipeCategory.ID);
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) {
		if (!IntegrationManager.isJEIActive())
			return;

		registration.addRecipeCategories(
				new UpgradeKitRecipeCategory(registration.getJeiHelpers().getGuiHelper()),
				new ImbuingRecipeCategory(registration.getJeiHelpers().getGuiHelper()),
				new InfusionRecipeCategory(registration.getJeiHelpers().getGuiHelper()),
				new FrameBenchRecipeCategory(registration.getJeiHelpers().getGuiHelper()),
				new WhitewashingRecipeCategory(registration.getJeiHelpers().getGuiHelper())
		);
	}

	@Override
	public void registerItemSubtypes(ISubtypeRegistration registration) {
		if (!IntegrationManager.isJEIActive())
			return;

		registration.registerSubtypeInterpreter(AoABlocks.TROPHY.get().asItem(), TrophySubtypeInterpreter.INSTANCE);
		registration.registerSubtypeInterpreter(AoABlocks.GOLD_TROPHY.get().asItem(), TrophySubtypeInterpreter.INSTANCE);
		registration.registerSubtypeInterpreter(AoABlocks.ORNATE_TROPHY.get().asItem(), TrophySubtypeInterpreter.INSTANCE);
	}

	private Collection<IRecipe<DivineStationContainer.DivineStationInventory>> compileUpgradeKitRecipes(RecipeManager recipeManager) {
		return recipeManager.byType(AoARecipes.UPGRADE_KIT.getA()).values();
	}

	private ArrayList<WhitewashingRecipe> compileWhitewashingRecipes(RecipeManager recipeManager) {
		ArrayList<WhitewashingRecipe> whitewashingRecipes = new ArrayList<WhitewashingRecipe>(10);

		whitewashingRecipes.add(new WhitewashingRecipe(new ResourceLocation(AdventOfAscension.MOD_ID, "whitewashing_white"), AoAItems.WHITEWASHING_SOLUTION.get(), AoABlocks.WHITEWASH_BRICKS.get()));
		whitewashingRecipes.add(new WhitewashingRecipe(new ResourceLocation(AdventOfAscension.MOD_ID, "whitewashing_dark"), AoAItems.DARKLY_POWDER.get(), AoABlocks.DARKWASH_BRICKS.get()));

		return whitewashingRecipes;
	}

	private ArrayList<IRecipe<Inventory>> compileFrameBenchRecipes(RecipeManager recipeManager) {
		ArrayList<IRecipe<Inventory>> frameRecipes = new ArrayList<IRecipe<Inventory>>(10);

		frameRecipes.add(new FrameBenchRecipe(new ResourceLocation(AdventOfAscension.MOD_ID, "frame_bench_crossbow"), AoAItems.CROSSBOW_FRAME.get()));
		frameRecipes.add(new FrameBenchRecipe(new ResourceLocation(AdventOfAscension.MOD_ID, "frame_bench_blaster"), AoAItems.BLASTER_FRAME.get()));
		frameRecipes.add(new FrameBenchRecipe(new ResourceLocation(AdventOfAscension.MOD_ID, "frame_bench_cannon"), AoAItems.CANNON_FRAME.get()));
		frameRecipes.add(new FrameBenchRecipe(new ResourceLocation(AdventOfAscension.MOD_ID, "frame_bench_helmet"), AoAItems.HELMET_FRAME.get()));
		frameRecipes.add(new FrameBenchRecipe(new ResourceLocation(AdventOfAscension.MOD_ID, "frame_bench_chestplate"), AoAItems.CHESTPLATE_FRAME.get()));
		frameRecipes.add(new FrameBenchRecipe(new ResourceLocation(AdventOfAscension.MOD_ID, "frame_bench_leggings"), AoAItems.LEGGINGS_FRAME.get()));
		frameRecipes.add(new FrameBenchRecipe(new ResourceLocation(AdventOfAscension.MOD_ID, "frame_bench_boots"), AoAItems.BOOTS_FRAME.get()));
		frameRecipes.add(new FrameBenchRecipe(new ResourceLocation(AdventOfAscension.MOD_ID, "frame_bench_gun"), AoAItems.GUN_FRAME.get()));
		frameRecipes.add(new FrameBenchRecipe(new ResourceLocation(AdventOfAscension.MOD_ID, "frame_bench_shotgun"), AoAItems.SHOTGUN_FRAME.get()));
		frameRecipes.add(new FrameBenchRecipe(new ResourceLocation(AdventOfAscension.MOD_ID, "frame_bench_sniper"), AoAItems.SNIPER_FRAME.get()));

		return frameRecipes;
	}

	private ArrayList<InfusionRecipe> compileImbuingRecipes(RecipeManager recipeManager) {
		ArrayList<InfusionRecipe> imbuingRecipes = new ArrayList<InfusionRecipe>();

		for (IRecipe<InfusionTableContainer.InfusionInventory> recipe : recipeManager.byType(AoARecipes.INFUSION.getA()).values()) {
			if (recipe instanceof InfusionRecipe) {
				if (((InfusionRecipe)recipe).isEnchanting())
					imbuingRecipes.add((InfusionRecipe)recipe);
			}
		}

		return imbuingRecipes;
	}

	private ArrayList<InfusionRecipe> compileInfusionRecipes(RecipeManager recipeManager) {
		ArrayList<InfusionRecipe> infusionRecipes = new ArrayList<InfusionRecipe>();

		for (IRecipe<InfusionTableContainer.InfusionInventory> recipe : recipeManager.byType(AoARecipes.INFUSION.getA()).values()) {
			if (recipe instanceof InfusionRecipe) {
				if (!((InfusionRecipe)recipe).isEnchanting())
					infusionRecipes.add((InfusionRecipe)recipe);
			}
		}

		return infusionRecipes;
	}
}
