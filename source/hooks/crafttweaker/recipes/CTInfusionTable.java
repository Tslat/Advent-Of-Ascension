package net.tslat.aoa3.hooks.crafttweaker.recipes;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.mc1120.recipes.MCRecipeManager;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.crafting.recipes.InfusionTableRecipe;
import net.tslat.aoa3.utils.ItemUtil;
import org.apache.logging.log4j.Level;
import scala.actors.threadpool.Arrays;
import stanhebben.zenscript.annotations.Optional;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@ZenClass("mods.aoa3.InfusionTable")
public class CTInfusionTable {
	@ZenMethod
	public static void addInfusionRecipe(String id, IItemStack input, IItemStack output, IIngredient[] infusionIngredients, @Optional(valueLong = 1) long infusionLevelReq, @Optional int minXp, @Optional int maxXp) {
		ItemStack targetStack = CraftTweakerMC.getItemStack(input);
		ItemStack outputStack = CraftTweakerMC.getItemStack(output);
		NonNullList<Ingredient> ingredients = NonNullList.create();

		for (IIngredient ing : infusionIngredients) {
			if (ingredients.size() >= 9) {
				AdventOfAscension.logOptionalMessage("Too many ingredients entered for CraftTweaker infusion recipe: " + id + ". Truncating ingredients.");

				break;
			}

			ingredients.add(CraftTweakerMC.getIngredient(ing));
		}

		minXp = Math.max(0, minXp);
		maxXp = Math.max(minXp, maxXp);
		InfusionTableRecipe recipe = new InfusionTableRecipe("", targetStack, outputStack, ingredients, (int)infusionLevelReq, minXp, maxXp);

		CraftTweakerAPI.apply(new AddInfusionRecipe(recipe, id));
	}

	@ZenMethod
	public static void addImbuingRecipe(String id, IItemStack input, IIngredient[] imbuingIngredients, String enchantmentId, @Optional(valueLong = 1) long enchantmentLevel, @Optional(valueLong = 1) long infusionLevelReq, @Optional int minXp, @Optional int maxXp) {
		ItemStack targetStack = CraftTweakerMC.getItemStack(input);
		NonNullList<Ingredient> ingredients = NonNullList.create();
		Enchantment enchantment = ForgeRegistries.ENCHANTMENTS.getValue(new ResourceLocation(enchantmentId));

		if (enchantment == null) {
			AdventOfAscension.logMessage(Level.WARN, "Invalid enchantment ID: " + enchantmentId + " from CraftTweaker recipe " + id);

			return;
		}

		for (IIngredient ing : imbuingIngredients) {
			if (ingredients.size() >= 9) {
				AdventOfAscension.logOptionalMessage("Too many ingredients entered for CraftTweaker infusion recipe: " + id + ". Truncating ingredients.");

				break;
			}

			ingredients.add(CraftTweakerMC.getIngredient(ing));
		}

		minXp = Math.max(0, minXp);
		maxXp = Math.max(minXp, maxXp);
		InfusionTableRecipe recipe = new InfusionTableRecipe("", enchantment, (int)enchantmentLevel, ingredients, (int)infusionLevelReq, minXp, maxXp);

		CraftTweakerAPI.apply(new AddInfusionRecipe(recipe, id));
	}

	@ZenMethod
	public static void removeInfusionRecipe(String resourcePath) {
		if (!resourcePath.contains(":"))
			resourcePath = "aoa3:" + resourcePath;

		CraftTweakerAPI.apply(new RemoveInfusionRecipe(new ResourceLocation(resourcePath)));
	}

	@ZenMethod
	public static void removeImbuingRecipe(String enchantmentId, int enchantmentLevel) {
		Enchantment enchant = Enchantment.getEnchantmentByLocation(enchantmentId);

		if (enchant == null) {
			AdventOfAscension.logMessage(Level.WARN, "Invalid enchantment ID provided by CraftTweaker integration: " + enchantmentId);

			return;
		}

		CraftTweakerAPI.apply(new RemoveInfusionRecipe(new EnchantmentData(enchant, enchantmentLevel)));
	}

	@ZenMethod
	public static void removeInfusionRecipe(IItemStack outputStack) {
		CraftTweakerAPI.apply(new RemoveInfusionRecipe(CraftTweakerMC.getItemStack(outputStack)));
	}

	private static class RemoveInfusionRecipe extends MCRecipeManager.ActionBaseRemoveRecipes {
		@Nullable
		private final ResourceLocation[] recipeIds;
		@Nullable
		private final EnchantmentData enchant;
		@Nullable
		private final ItemStack outputStack;

		private RemoveInfusionRecipe(ResourceLocation... recipeIds) {
			this.recipeIds = recipeIds;
			this.enchant = null;
			this.outputStack = null;
		}

		private RemoveInfusionRecipe(EnchantmentData enchData) {
			this.enchant = enchData;
			this.recipeIds = null;
			this.outputStack = null;
		}

		private RemoveInfusionRecipe(ItemStack outputStack) {
			this.enchant = null;
			this.recipeIds = null;
			this.outputStack = outputStack;
		}

		@Override
		public void apply() {
			if (recipeIds != null) {
				removeRecipes(Arrays.asList(recipeIds));
			}
			else if (enchant != null) {
				List<ResourceLocation> matchedRecipes = new ArrayList<ResourceLocation>();

				for (IRecipe recipe : ForgeRegistries.RECIPES.getValuesCollection()) {
					if (recipe instanceof InfusionTableRecipe) {
						InfusionTableRecipe infusion = (InfusionTableRecipe)recipe;

						if (infusion.isEnchanting()) {
							EnchantmentData data = infusion.getEnchantment();

							if (data != null && data.enchantment == enchant.enchantment && data.enchantmentLevel == enchant.enchantmentLevel)
								matchedRecipes.add(recipe.getRegistryName());
						}
					}
				}

				removeRecipes(matchedRecipes);
			}
			else {
				List<ResourceLocation> matchedRecipes = new ArrayList<ResourceLocation>();

				for (IRecipe recipe : ForgeRegistries.RECIPES.getValuesCollection()) {
					if (recipe instanceof InfusionTableRecipe && ItemUtil.areStacksFunctionallyEqual(recipe.getRecipeOutput(), outputStack))
						matchedRecipes.add(recipe.getRegistryName());
				}

				removeRecipes(matchedRecipes);
			}
		}

		@Override
		public String describe() {
			if (recipeIds != null) {
				return "Removing " + recipeIds.length + " recipes from the Infusion Table registry.";
			}
			else if (outputStack != null) {
				return "Removing all Infusion Table recipes resulting in " + outputStack.getDisplayName() + ".";
			}
			else {
				return "Removing all Infusion Table recipes for " + enchant.enchantment.getTranslatedName(enchant.enchantmentLevel);
			}
		}
	}

	private static class AddInfusionRecipe implements IAction {
		private final InfusionTableRecipe recipe;
		private final String recipeId;

		private AddInfusionRecipe(@Nonnull InfusionTableRecipe recipe, String id) {
			this.recipe = recipe;
			this.recipeId = id;
		}

		@Override
		public void apply() {
			ForgeRegistries.RECIPES.register(recipe.setRegistryName(new ResourceLocation("aoa3_ct", recipeId)));
		}

		@Override
		public String describe() {
			if (recipe.isEnchanting()) {
				return "Registering Imbuing recipe for " + recipe.getEnchantmentAsBook().getDisplayName() + ". Recipe ID: " + recipeId;
			}
			else {
				return "Registering Infusion recipe for " + recipe.getRecipeOutput().getDisplayName() + ". Recipe ID: " + recipeId;
			}
		}
	}
}
