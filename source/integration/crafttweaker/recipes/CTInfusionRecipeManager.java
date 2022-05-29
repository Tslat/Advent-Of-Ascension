package net.tslat.aoa3.integration.crafttweaker.recipes;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.action.recipe.ActionAddRecipe;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.common.registration.AoARecipes;
import net.tslat.aoa3.content.recipe.InfusionRecipe;
import org.apache.logging.log4j.Level;
import org.openzen.zencode.java.ZenCodeType;

@Document("aoa3/api/InfusionRecipe")
@ZenCodeType.Name("mods.aoa3.InfusionRecipe")
@ZenRegister
public class CTInfusionRecipeManager implements IRecipeManager<InfusionRecipe> {
	@ZenCodeType.Method
	public void addInfusionRecipe(String id, IIngredient input, IItemStack output, IIngredient[] infusionIngredients, @ZenCodeType.OptionalLong(value = 1) long infusionLevelReq, @ZenCodeType.OptionalInt int minXp, @ZenCodeType.OptionalInt int maxXp) {
		Ingredient targetIngredient = input.asVanillaIngredient();
		ItemStack outputStack = output.getInternal();
		NonNullList<Ingredient> ingredients = NonNullList.create();

		for (IIngredient ing : infusionIngredients) {
			if (ingredients.size() >= 9) {
				Logging.logMessage(Level.WARN, "Too many ingredients entered for CraftTweaker infusion recipe: " + id + ". Truncating ingredients.");

				break;
			}

			ingredients.add(ing.asVanillaIngredient());
		}

		minXp = Math.max(0, minXp);
		maxXp = Math.max(minXp, maxXp);
		InfusionRecipe recipe = new InfusionRecipe(new ResourceLocation("crafttweaker", id), "", outputStack, targetIngredient, ingredients, (int)infusionLevelReq, minXp, maxXp);

		CraftTweakerAPI.apply(new ActionAddRecipe<>(this, recipe));
	}

	@ZenCodeType.Method
	public void addImbuingRecipe(String id, IItemStack input, IIngredient[] imbuingIngredients, String enchantmentId, @ZenCodeType.OptionalLong(value = 1) long enchantmentLevel, @ZenCodeType.OptionalLong(value = 1) long infusionLevelReq, @ZenCodeType.OptionalInt int minXp, @ZenCodeType.OptionalInt int maxXp) {
		ItemStack targetStack = input.getInternal();
		NonNullList<Ingredient> ingredients = NonNullList.create();
		Enchantment enchantment = ForgeRegistries.ENCHANTMENTS.getValue(new ResourceLocation(enchantmentId));

		if (enchantment == null) {
			Logging.logMessage(Level.WARN, "Invalid enchantment ID: " + enchantmentId + " from CraftTweaker recipe " + id);

			return;
		}

		for (IIngredient ing : imbuingIngredients) {
			if (ingredients.size() >= 9) {
				Logging.logMessage(Level.WARN, "Too many ingredients entered for CraftTweaker infusion recipe: " + id + ". Truncating ingredients.");

				break;
			}

			ingredients.add(ing.asVanillaIngredient());
		}

		minXp = Math.max(0, minXp);
		maxXp = Math.max(minXp, maxXp);
		InfusionRecipe recipe = new InfusionRecipe(new ResourceLocation("crafttweaker", id), "", enchantment, (int)enchantmentLevel, ingredients, (int)infusionLevelReq, minXp, maxXp);

		CraftTweakerAPI.apply(new ActionAddRecipe<>(this, recipe));
	}

	@Override
	public RecipeType<InfusionRecipe> getRecipeType() {
		return AoARecipes.INFUSION.type().get();
	}
}
