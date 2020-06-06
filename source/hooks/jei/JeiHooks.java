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
import net.tslat.aoa3.hooks.jei.recipecategory.*;
import net.tslat.aoa3.hooks.jei.recipetransfer.InfusionTableRecipeTransferInfo;
import net.tslat.aoa3.hooks.jei.recipetransfer.UpgradeKitRecipeTransferInfo;
import net.tslat.aoa3.hooks.jei.recipetransfer.WhitewashingTableRecipeTransferInfo;
import net.tslat.aoa3.hooks.jei.recipewrapper.FrameRecipeWrapper;
import net.tslat.aoa3.hooks.jei.recipewrapper.InfusionRecipeWrapper;
import net.tslat.aoa3.hooks.jei.recipewrapper.UpgradeRecipeWrapper;
import net.tslat.aoa3.hooks.jei.recipewrapper.WhitewashingRecipeWrapper;

import java.util.ArrayList;

@JEIPlugin
public class JeiHooks implements IModPlugin {
	@Override
	public void register(IModRegistry registry) {
		if (!ThirdPartyInteractions.isJEIActive())
			return;

		AdventOfAscension.logOptionalMessage("Beginning JEI Integration");
		registerHiddenItems(registry);
		registry.handleRecipes(InfusionTableRecipe.class, new InfusionRecipeWrapper.Factory(), "aoa3.infusion");
		registry.handleRecipes(InfusionTableRecipe.class, new InfusionRecipeWrapper.Factory(), "aoa3.imbuing");
		registry.handleRecipes(UpgradeKitRecipe.class, new UpgradeRecipeWrapper.Factory(), "aoa3.upgradeKits");
		registry.addRecipeCatalyst(new ItemStack(BlockRegister.INFUSION_TABLE), "aoa3.infusion");
		registry.addRecipeCatalyst(new ItemStack(BlockRegister.INFUSION_TABLE), "aoa3.imbuing");
		registry.addRecipeCatalyst(new ItemStack(BlockRegister.FRAME_BENCH), "aoa3.frameBench");
		registry.addRecipeCatalyst(new ItemStack(BlockRegister.WHITEWASHING_TABLE), "aoa3.whitewashing");
		registry.addRecipeCatalyst(new ItemStack(BlockRegister.DIVINE_STATION), "aoa3.upgradeKits");
		registry.addRecipes(compileFrameBenchRecipes(), "aoa3.frameBench");
		registry.addRecipes(compileWhitewashingRecipes(), "aoa3.whitewashing");
		registry.addRecipes(compileUpgradeKitRecipes(), "aoa3.upgradeKits");

		registry.getRecipeTransferRegistry().addRecipeTransferHandler(new InfusionTableRecipeTransferInfo("infusion"));
		registry.getRecipeTransferRegistry().addRecipeTransferHandler(new InfusionTableRecipeTransferInfo("imbuing"));
		registry.getRecipeTransferRegistry().addRecipeTransferHandler(new UpgradeKitRecipeTransferInfo());
		registry.getRecipeTransferRegistry().addRecipeTransferHandler(new WhitewashingTableRecipeTransferInfo());

		compileInfusionRecipes(registry);
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		if (!ThirdPartyInteractions.isJEIActive())
			return;

		registry.addRecipeCategories(new InfusionRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
		registry.addRecipeCategories(new ImbuingRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
		registry.addRecipeCategories(new FrameRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
		registry.addRecipeCategories(new WhitewashingRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
		registry.addRecipeCategories(new UpgradeRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
	}

	private void registerHiddenItems(IModRegistry registry) {
		IIngredientBlacklist blacklist = registry.getJeiHelpers().getIngredientBlacklist();

		blacklist.addIngredientToBlacklist(new ItemStack(ItemRegister.ALIEN_ORB));
		blacklist.addIngredientToBlacklist(new ItemStack(ItemRegister.FLESHY_BONES));
		blacklist.addIngredientToBlacklist(new ItemStack(ItemRegister.MILLENNIUM_UPGRADER));
		blacklist.addIngredientToBlacklist(new ItemStack(ItemRegister.MOLTEN_UPGRADER));
		blacklist.addIngredientToBlacklist(new ItemStack(ItemRegister.MOONSTONE));
		blacklist.addIngredientToBlacklist(new ItemStack(ItemRegister.OLD_BOOT));
		blacklist.addIngredientToBlacklist(new ItemStack(ItemRegister.YETI_FINGERNAILS));
		blacklist.addIngredientToBlacklist(new ItemStack(WeaponRegister.COLOUR_CANNON));
		blacklist.addIngredientToBlacklist(new ItemStack(WeaponRegister.EXPERIMENT_W801));
		blacklist.addIngredientToBlacklist(new ItemStack(WeaponRegister.SHOE_FLINGER));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.AMETHYST_LAMP));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.AQUATIC_LAMP));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.BARONYTE_LAMP));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.BLAZIUM_LAMP));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.BLOODSTONE_LAMP));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.CRYSTALLITE_LAMP));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.ELECANIUM_LAMP));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.EMBERSTONE_LAMP));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.FIRE_LAMP));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.GHASTLY_LAMP));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.GHOULISH_LAMP));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.IRO_LAMP));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.IVORY_LAMP));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.IVORY_AMETHYST_LAMP));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.IVORY_JADE_LAMP));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.IVORY_LIMONITE_LAMP));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.IVORY_ROSITE_LAMP));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.IVORY_SAPPHIRE_LAMP));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.JADE_LAMP));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.AQUA_LIFE_LAMP));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.BLACK_LIFE_LAMP));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.BLUE_LIFE_LAMP));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.BROWN_LIFE_LAMP));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.CYAN_LIFE_LAMP));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.DARK_GREY_LIFE_LAMP));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.GREEN_LIFE_LAMP));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.GREY_LIFE_LAMP));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.LIME_LIFE_LAMP));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.MAGENTA_LIFE_LAMP));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.ORANGE_LIFE_LAMP));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.PINK_LIFE_LAMP));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.PURPLE_LIFE_LAMP));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.RED_LIFE_LAMP));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.WHITE_LIFE_LAMP));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.YELLOW_LIFE_LAMP));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.LIMONITE_LAMP));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.LUNAR_LAMP));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.LYON_LAMP));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.MYSTIC_LAMP));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.NEON_LAMP));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.NEON_CIRCLING_LAMP));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.NEON_LAPIS_LAMP));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.NEON_LAPIS_CIRCLING_LAMP));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.NEON_LAPIS_TRIANGLES_LAMP));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.NEON_RUNIC_LAMP));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.NEON_TRIANGLES_LAMP));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.ROSITE_LAMP));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.SAPPHIRE_LAMP));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.SKELETAL_LAMP));
	}

	private ArrayList<UpgradeKitRecipe> compileUpgradeKitRecipes() {
		ArrayList<UpgradeKitRecipe> upgradeKitRecipes = new ArrayList<UpgradeKitRecipe>();

		for (IRecipe recipe : ForgeRegistries.RECIPES.getValuesCollection()) {
			if (recipe instanceof UpgradeKitRecipe)
				upgradeKitRecipes.add((UpgradeKitRecipe)recipe);
		}

		return upgradeKitRecipes;
	}

	private void compileInfusionRecipes(IModRegistry registry) {
		ArrayList<InfusionTableRecipe> infusionRecipes = new ArrayList<InfusionTableRecipe>();
		ArrayList<InfusionTableRecipe> imbuingRecipes = new ArrayList<InfusionTableRecipe>();

		for (IRecipe recipe : ForgeRegistries.RECIPES.getValuesCollection()) {
			if (recipe instanceof InfusionTableRecipe) {
				if (((InfusionTableRecipe)recipe).isEnchanting()) {
					imbuingRecipes.add((InfusionTableRecipe)recipe);
				}
				else {
					infusionRecipes.add((InfusionTableRecipe)recipe);
				}
			}
		}

		registry.addRecipes(infusionRecipes, "aoa3.infusion");
		registry.addRecipes(imbuingRecipes, "aoa3.imbuing");
	}

	private ArrayList<FrameRecipeWrapper> compileFrameBenchRecipes() {
		ArrayList<FrameRecipeWrapper> frameRecipes = new ArrayList<FrameRecipeWrapper>(10);

		frameRecipes.add(new FrameRecipeWrapper(ItemRegister.ARCHERGUN_FRAME));
		frameRecipes.add(new FrameRecipeWrapper(ItemRegister.BLASTER_FRAME));
		frameRecipes.add(new FrameRecipeWrapper(ItemRegister.CANNON_FRAME));
		frameRecipes.add(new FrameRecipeWrapper(ItemRegister.HELMET_FRAME));
		frameRecipes.add(new FrameRecipeWrapper(ItemRegister.CHESTPLATE_FRAME));
		frameRecipes.add(new FrameRecipeWrapper(ItemRegister.LEGGINGS_FRAME));
		frameRecipes.add(new FrameRecipeWrapper(ItemRegister.BOOTS_FRAME));
		frameRecipes.add(new FrameRecipeWrapper(ItemRegister.GUN_FRAME));
		frameRecipes.add(new FrameRecipeWrapper(ItemRegister.SHOTGUN_FRAME));
		frameRecipes.add(new FrameRecipeWrapper(ItemRegister.SNIPER_FRAME));

		return frameRecipes;
	}

	private ArrayList<WhitewashingRecipeWrapper> compileWhitewashingRecipes() {
		ArrayList<WhitewashingRecipeWrapper> whitewashingRecipes = new ArrayList<WhitewashingRecipeWrapper>(2);

		whitewashingRecipes.add(new WhitewashingRecipeWrapper(BlockRegister.WHITEWASH_BRICKS));
		whitewashingRecipes.add(new WhitewashingRecipeWrapper(BlockRegister.DARKWASH_BRICKS));

		return whitewashingRecipes;
	}
}