package net.tslat.aoa3.hooks.crafttweaker.recipes;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.mc1120.recipes.MCRecipeManager;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.tslat.aoa3.crafting.recipes.UpgradeKitRecipe;
import net.tslat.aoa3.utils.ItemUtil;
import scala.actors.threadpool.Arrays;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@ZenClass("mods.aoa3.UpgradeKit")
public class CTUpgradeKit {
	@ZenMethod
	public static void addUpgradeKitRecipe(String id, IItemStack input, IItemStack upgradeKit, IItemStack output) {
		UpgradeKitRecipe recipe = new UpgradeKitRecipe("", CraftTweakerMC.getItemStack(input), CraftTweakerMC.getItemStack(upgradeKit), CraftTweakerMC.getItemStack(output));

		CraftTweakerAPI.apply(new AddUpgradeKitRecipe(recipe, id));
	}

	@ZenMethod
	public static void removeUpgradeKitRecipe(String resourcePath) {
		if (!resourcePath.contains(":"))
			resourcePath = "aoa3:" + resourcePath;

		CraftTweakerAPI.apply(new RemoveUpgradeKitRecipe(new ResourceLocation(resourcePath)));
	}

	@ZenMethod
	public static void removeUpgradeKitRecipes(IItemStack output) {
		CraftTweakerAPI.apply(new RemoveUpgradeKitRecipe(CraftTweakerMC.getItemStack(output)));
	}

	private static class RemoveUpgradeKitRecipe extends MCRecipeManager.ActionBaseRemoveRecipes {
		@Nullable
		private final ResourceLocation[] recipeIds;
		@Nullable
		private final ItemStack outputStack;

		private RemoveUpgradeKitRecipe(ResourceLocation... recipeIds) {
			this.recipeIds = recipeIds;
			this.outputStack = null;
		}

		private RemoveUpgradeKitRecipe(ItemStack outputStack) {
			this.outputStack = outputStack;
			this.recipeIds = null;
		}

		@Override
		public void apply() {
			if (outputStack != null) {
				List<ResourceLocation> matchedRecipes = new ArrayList<ResourceLocation>();

				for (IRecipe recipe : ForgeRegistries.RECIPES.getValuesCollection()) {
					if (recipe instanceof UpgradeKitRecipe) {
						if (ItemUtil.areStacksFunctionallyEqual(outputStack, recipe.getRecipeOutput()))
							matchedRecipes.add(recipe.getRegistryName());
					}
				}

				removeRecipes(matchedRecipes);
			}
			else {
				removeRecipes(Arrays.asList(recipeIds));
			}
		}

		@Override
		public String describe() {
			if (outputStack != null) {
				return "Removing all Upgrade Kit recipes resulting in " + outputStack.getDisplayName() + " from the registry.";
			}
			else {
				return "Removing " + recipeIds.length + " Upgrade Kit recipes from the registry.";
			}
		}
	}

	private static class AddUpgradeKitRecipe implements IAction {
		private final UpgradeKitRecipe recipe;
		private final String recipeId;

		private AddUpgradeKitRecipe(UpgradeKitRecipe recipe, String id) {
			this.recipe = recipe;
			this.recipeId = id;
		}

		@Override
		public void apply() {
			ForgeRegistries.RECIPES.register(recipe.setRegistryName(new ResourceLocation("aoa3_ct", recipeId)));
		}

		@Override
		public String describe() {
			return "Registering Upgrade Kit recipe for " + recipe.getRecipeOutput().getDisplayName() + ". Recipe ID: " + recipeId;
		}
	}
}
