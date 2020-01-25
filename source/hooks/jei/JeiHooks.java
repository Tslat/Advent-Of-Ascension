package net.tslat.aoa3.hooks.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.crafting.recipes.InfusionTableRecipe;
import net.tslat.aoa3.crafting.recipes.UpgradeKitRecipe;
import net.tslat.aoa3.hooks.ThirdPartyInteractions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

@JEIPlugin
public class JeiHooks implements IModPlugin {

	static {
		ThirdPartyInteractions.setJEIActive();
	}

	@Override
	public void register(IModRegistry registry) {
		AdventOfAscension.logOptionalMessage("Beginning JEI Integration");
		registerHiddenItems(registry);
		registry.handleRecipes(InfusionTableRecipe.class, new InfusionRecipeWrapper.Factory(), "aoa3.infusion");
		registry.handleRecipes(UpgradeKitRecipe.class, new UpgradeRecipeWrapper.Factory(), "aoa3.upgradeKits");
		registry.addRecipeCatalyst(new ItemStack(BlockRegister.infusionTable), "aoa3.infusion");
		registry.addRecipeCatalyst(new ItemStack(BlockRegister.frameBench), "aoa3.frameBench");
		registry.addRecipeCatalyst(new ItemStack(BlockRegister.whitewashingTable), "aoa3.whitewashing");
		registry.addRecipeCatalyst(new ItemStack(BlockRegister.divineStation), "aoa3.upgradeKits");
		registry.addRecipes(compileFrameBenchRecipes(), "aoa3.frameBench");
		registry.addRecipes(compileWhitewashingRecipes(), "aoa3.whitewashing");
		registry.addRecipes(compileInfusionRecipes(), "aoa3.infusion");
		registry.addRecipes(compileUpgradeKitRecipes(), "aoa3.upgradeKits");
		//registry.addRecipes(RecipeRegister.getAllInfusionRecipes().stream().sorted(Comparator.comparing(recipe -> recipe.getRegistryName().toString())).collect(Collectors.toList()), "aoa3.infusion");
		//registry.addRecipes(RecipeRegister.getAllUpgradeKitRecipes().stream().sorted(Comparator.comparing(recipe -> recipe.getRegistryName().toString())).collect(Collectors.toList()), "aoa3.upgradeKits");
	}

	private void registerHiddenItems(IModRegistry registry) {
		IIngredientBlacklist blacklist = registry.getJeiHelpers().getIngredientBlacklist();

		blacklist.addIngredientToBlacklist(new ItemStack(ItemRegister.alienOrb));
		blacklist.addIngredientToBlacklist(new ItemStack(ItemRegister.fleshyBones));
		blacklist.addIngredientToBlacklist(new ItemStack(ItemRegister.millenniumUpgrader));
		blacklist.addIngredientToBlacklist(new ItemStack(ItemRegister.moltenUpgrader));
		blacklist.addIngredientToBlacklist(new ItemStack(ItemRegister.moonstone));
		blacklist.addIngredientToBlacklist(new ItemStack(ItemRegister.oldBoot));
		blacklist.addIngredientToBlacklist(new ItemStack(ItemRegister.yetiFingernails));
		blacklist.addIngredientToBlacklist(new ItemStack(WeaponRegister.blasterColourCannon));
		blacklist.addIngredientToBlacklist(new ItemStack(WeaponRegister.blasterExperimentW801));
		blacklist.addIngredientToBlacklist(new ItemStack(WeaponRegister.gunShoeFlinger));
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		registry.addRecipeCategories(new InfusionRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
		registry.addRecipeCategories(new FrameRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
		registry.addRecipeCategories(new WhitewashingRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
		registry.addRecipeCategories(new UpgradeRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
	}

	private ArrayList<UpgradeKitRecipe> compileUpgradeKitRecipes() {
		ArrayList<UpgradeKitRecipe> upgradeKitRecipes = new ArrayList<UpgradeKitRecipe>();

		for (IRecipe recipe : ForgeRegistries.RECIPES.getValuesCollection()) {
			if (recipe instanceof UpgradeKitRecipe)
				upgradeKitRecipes.add((UpgradeKitRecipe)recipe);
		}

		return upgradeKitRecipes;
	}

	private ArrayList<InfusionTableRecipe> compileInfusionRecipes() {
		ArrayList<InfusionTableRecipe> upgradeKitRecipes = new ArrayList<InfusionTableRecipe>();

		for (IRecipe recipe : ForgeRegistries.RECIPES.getValuesCollection()) {
			if (recipe instanceof InfusionTableRecipe)
				upgradeKitRecipes.add((InfusionTableRecipe)recipe);
		}

		return upgradeKitRecipes;
	}

	private ArrayList<FrameRecipeWrapper> compileFrameBenchRecipes() {
		ArrayList<FrameRecipeWrapper> frameRecipes = new ArrayList<FrameRecipeWrapper>(10);

		frameRecipes.add(new FrameRecipeWrapper(ItemRegister.frameArchergun));
		frameRecipes.add(new FrameRecipeWrapper(ItemRegister.frameBlaster));
		frameRecipes.add(new FrameRecipeWrapper(ItemRegister.frameCannon));
		frameRecipes.add(new FrameRecipeWrapper(ItemRegister.frameHelmet));
		frameRecipes.add(new FrameRecipeWrapper(ItemRegister.frameChestplate));
		frameRecipes.add(new FrameRecipeWrapper(ItemRegister.frameLeggings));
		frameRecipes.add(new FrameRecipeWrapper(ItemRegister.frameBoots));
		frameRecipes.add(new FrameRecipeWrapper(ItemRegister.frameGun));
		frameRecipes.add(new FrameRecipeWrapper(ItemRegister.frameShotgun));
		frameRecipes.add(new FrameRecipeWrapper(ItemRegister.frameSniper));

		return frameRecipes;
	}

	private ArrayList<WhitewashingRecipeWrapper> compileWhitewashingRecipes() {
		ArrayList<WhitewashingRecipeWrapper> whitewashingRecipes = new ArrayList<WhitewashingRecipeWrapper>(2);

		whitewashingRecipes.add(new WhitewashingRecipeWrapper(BlockRegister.bricksWhitewash));
		whitewashingRecipes.add(new WhitewashingRecipeWrapper(BlockRegister.bricksDarkwash));

		return whitewashingRecipes;
	}

	private Collection<?> sorted(Collection<? extends IRecipe> collection) {
		return collection.stream().sorted(Comparator.comparing(recipe -> recipe.getRegistryName().toString())).collect(Collectors.toList());
	}
}