package net.tslat.aoa3.integration.crafttweaker.recipes;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.managers.IRecipeManager;
import com.blamejared.crafttweaker.impl.actions.recipes.ActionAddRecipe;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.common.container.recipe.InfusionRecipe;
import net.tslat.aoa3.common.registration.AoARecipes;
import org.apache.logging.log4j.Level;
import org.openzen.zencode.java.ZenCodeType;

@Document("aoa3/api/InfusionRecipe")
@ZenCodeType.Name("mods.aoa3.InfusionRecipe")
@ZenRegister
public class CTInfusionRecipeManager implements IRecipeManager {
	@ZenCodeType.Method
	public void addInfusionRecipe(String id, IItemStack input, IItemStack output, IIngredient[] infusionIngredients, @ZenCodeType.OptionalLong(value = 1) long infusionLevelReq, @ZenCodeType.OptionalInt int minXp, @ZenCodeType.OptionalInt int maxXp) {
		ItemStack targetStack = input.getInternal();
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
		InfusionRecipe recipe = new InfusionRecipe(new ResourceLocation("crafttweaker", id), "", targetStack, outputStack, ingredients, (int)infusionLevelReq, minXp, maxXp);

		CraftTweakerAPI.apply(new ActionAddRecipe(this, recipe));
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

		CraftTweakerAPI.apply(new ActionAddRecipe(this, recipe));
	}

	@Override
	public IRecipeType<InfusionRecipe> getRecipeType() {
		return AoARecipes.INFUSION.getA();
	}
}
