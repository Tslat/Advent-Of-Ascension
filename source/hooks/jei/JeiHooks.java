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
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.lampAmethyst));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.lampAquatic));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.lampBaronyte));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.lampBlazium));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.lampBloodstone));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.lampCrystallite));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.lampElecanium));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.lampEmberstone));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.lampFire));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.lampGhastly));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.lampGhoulish));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.lampIro));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.lampIvory));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.lampIvoryAmethyst));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.lampIvoryJade));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.lampIvoryLimonite));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.lampIvoryRosite));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.lampIvorySapphire));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.lampJade));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.lampLifeAqua));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.lampLifeBlack));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.lampLifeBlue));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.lampLifeBrown));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.lampLifeCyan));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.lampLifeDarkGrey));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.lampLifeGreen));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.lampLifeGrey));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.lampLifeLime));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.lampLifeMagenta));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.lampLifeOrange));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.lampLifePink));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.lampLifePurple));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.lampLifeRed));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.lampLifeWhite));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.lampLifeYellow));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.lampLimonite));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.lampLunar));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.lampLyon));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.lampMystic));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.lampNeon));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.lampNeonCircling));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.lampNeonLapis));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.lampNeonLapisCircling));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.lampNeonLapisTriangles));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.lampNeonRunic));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.lampNeonTriangles));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.lampRosite));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.lampSapphire));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.lampSkeletal));
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
}