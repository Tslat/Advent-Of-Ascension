package net.tslat.aoa3.integration.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.*;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoARecipes;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.recipe.*;
import net.tslat.aoa3.integration.IntegrationManager;
import net.tslat.aoa3.integration.jei.ingredient.subtype.TrophySubtypeInterpreter;
import net.tslat.aoa3.integration.jei.recipe.framebench.FrameBenchRecipeCategory;
import net.tslat.aoa3.integration.jei.recipe.framebench.FrameBenchRecipeTransferInfo;
import net.tslat.aoa3.integration.jei.recipe.imbuing.ImbuingRecipeCategory;
import net.tslat.aoa3.integration.jei.recipe.imbuing.ImbuingRecipeTransferInfo;
import net.tslat.aoa3.integration.jei.recipe.infusion.InfusionRecipeCategory;
import net.tslat.aoa3.integration.jei.recipe.infusion.InfusionRecipeTransferInfo;
import net.tslat.aoa3.integration.jei.recipe.toolinteraction.ToolInteractionRecipeExtension;
import net.tslat.aoa3.integration.jei.recipe.upgradekit.UpgradeKitRecipeCategory;
import net.tslat.aoa3.integration.jei.recipe.upgradekit.UpgradeKitRecipeTransferInfo;
import net.tslat.aoa3.integration.jei.recipe.whitewashing.WhitewashingRecipeCategory;
import net.tslat.aoa3.integration.jei.recipe.whitewashing.WhitewashingRecipeTransferInfo;

import java.util.ArrayList;
import java.util.List;

@JeiPlugin
public class JEIIntegration implements IModPlugin {
	@Override
	public ResourceLocation getPluginUid() {
		return new ResourceLocation(AdventOfAscension.MOD_ID, "core");
	}

	@Override
	public void registerVanillaCategoryExtensions(IVanillaCategoryExtensionRegistration registration) {
		registration.getCraftingCategory().addCategoryExtension(ToolInteractionRecipe.class, ToolInteractionRecipeExtension::new);
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
		if (!IntegrationManager.isJEIActive())
			return;

		registration.addRecipeCatalyst(VanillaTypes.ITEM_STACK, new ItemStack(AoABlocks.DIVINE_STATION.get()), UpgradeKitRecipeCategory.RECIPE_TYPE);
		registration.addRecipeCatalyst(VanillaTypes.ITEM_STACK, new ItemStack(AoABlocks.INFUSION_TABLE.get()), InfusionRecipeCategory.RECIPE_TYPE, ImbuingRecipeCategory.RECIPE_TYPE);
		registration.addRecipeCatalyst(VanillaTypes.ITEM_STACK, new ItemStack(AoABlocks.FRAME_BENCH.get()), FrameBenchRecipeCategory.RECIPE_TYPE);
		registration.addRecipeCatalyst(VanillaTypes.ITEM_STACK, new ItemStack(AoABlocks.WHITEWASHING_TABLE.get()), WhitewashingRecipeCategory.RECIPE_TYPE);
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

		registration.addRecipes(UpgradeKitRecipeCategory.RECIPE_TYPE, compileUpgradeKitRecipes(recipeManager));
		registration.addRecipes(ImbuingRecipeCategory.RECIPE_TYPE, compileImbuingRecipes(recipeManager));
		registration.addRecipes(InfusionRecipeCategory.RECIPE_TYPE, compileInfusionRecipes(recipeManager));
		registration.addRecipes(FrameBenchRecipeCategory.RECIPE_TYPE, compileFrameBenchRecipes(recipeManager));
		registration.addRecipes(WhitewashingRecipeCategory.RECIPE_TYPE, compileWhitewashingRecipes(recipeManager));
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

		registration.registerSubtypeInterpreter(VanillaTypes.ITEM_STACK, AoABlocks.TROPHY.get().asItem(), TrophySubtypeInterpreter.INSTANCE);
		registration.registerSubtypeInterpreter(VanillaTypes.ITEM_STACK, AoABlocks.GOLD_TROPHY.get().asItem(), TrophySubtypeInterpreter.INSTANCE);
		registration.registerSubtypeInterpreter(VanillaTypes.ITEM_STACK, AoABlocks.ORNATE_TROPHY.get().asItem(), TrophySubtypeInterpreter.INSTANCE);
	}

	private List<UpgradeKitRecipe> compileUpgradeKitRecipes(RecipeManager recipeManager) {
		return recipeManager.getAllRecipesFor(AoARecipes.UPGRADE_KIT.type().get());
	}

	private List<WhitewashingRecipe> compileWhitewashingRecipes(RecipeManager recipeManager) {
		return recipeManager.getAllRecipesFor(AoARecipes.WHITEWASHING.type().get());
	}

	private ArrayList<FrameBenchRecipe> compileFrameBenchRecipes(RecipeManager recipeManager) {
		ArrayList<FrameBenchRecipe> frameRecipes = new ArrayList<>(10);

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
		ArrayList<InfusionRecipe> imbuingRecipes = new ArrayList<>();

		for (InfusionRecipe recipe : recipeManager.getAllRecipesFor(AoARecipes.INFUSION.type().get())) {
			if (recipe.isEnchanting())
				imbuingRecipes.add(recipe);
		}

		return imbuingRecipes;
	}

	private ArrayList<InfusionRecipe> compileInfusionRecipes(RecipeManager recipeManager) {
		ArrayList<InfusionRecipe> infusionRecipes = new ArrayList<>();

		for (InfusionRecipe recipe : recipeManager.getAllRecipesFor(AoARecipes.INFUSION.type().get())) {
			if (!recipe.isEnchanting())
				infusionRecipes.add(recipe);
		}

		return infusionRecipes;
	}

	@Override
	public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
		if (!IntegrationManager.isJEIActive())
			return;

		/*if (!IntegrationManager.isTinkersConstructActive()) {
			Function<FluidUtil.RegisteredFluidHolder, FluidStack> fluidStackGen = holder -> new FluidStack(holder.fluid().get(), FluidAttributes.BUCKET_VOLUME);

			jeiRuntime.getIngredientManager().removeIngredientsAtRuntime(VanillaTypes.FLUID, Arrays.asList(
					fluidStackGen.apply(TinkersFluids.MOLTEN_BARONYTE),
					fluidStackGen.apply(TinkersFluids.MOLTEN_BLAZIUM),
					fluidStackGen.apply(TinkersFluids.MOLTEN_ELECANIUM),
					fluidStackGen.apply(TinkersFluids.MOLTEN_EMBERSTONE),
					fluidStackGen.apply(TinkersFluids.MOLTEN_GHASTLY),
					fluidStackGen.apply(TinkersFluids.MOLTEN_GHOULISH),
					fluidStackGen.apply(TinkersFluids.MOLTEN_LIMONITE),
					fluidStackGen.apply(TinkersFluids.MOLTEN_LUNAR),
					fluidStackGen.apply(TinkersFluids.MOLTEN_LYON),
					fluidStackGen.apply(TinkersFluids.MOLTEN_MYSTITE),
					fluidStackGen.apply(TinkersFluids.MOLTEN_ROSITE),
					fluidStackGen.apply(TinkersFluids.MOLTEN_SHYRESTONE),
					fluidStackGen.apply(TinkersFluids.MOLTEN_SKELETAL),
					fluidStackGen.apply(TinkersFluids.MOLTEN_VARSIUM),
					fluidStackGen.apply(TinkersFluids.MOLTEN_CHARGER)));
		}*/

	}
}