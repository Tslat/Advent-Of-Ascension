package net.tslat.aoa3.mixin.common.invoker;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.Map;

@Mixin(RecipeManager.class)
public interface AccessibleRecipeManager {
	@Invoker(value = "byType") // func_215366_a
	<C extends IInventory, T extends IRecipe<C>> Map<ResourceLocation, IRecipe<C>> getRecipesForType(IRecipeType<T> recipeType);
}
