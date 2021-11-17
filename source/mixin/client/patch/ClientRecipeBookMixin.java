package net.tslat.aoa3.mixin.client.patch;

import net.minecraft.client.util.ClientRecipeBook;
import net.minecraft.client.util.RecipeBookCategories;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.registry.Registry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientRecipeBook.class)
public class ClientRecipeBookMixin {
	@Inject(method = "getCategory", at = @At("HEAD"), cancellable = true)
	private static void getCategory(IRecipe<?> recipe, CallbackInfoReturnable<RecipeBookCategories> callback) {
		if (!Registry.RECIPE_TYPE.getKey(recipe.getType()).getNamespace().equals("minecraft"))
			callback.setReturnValue(RecipeBookCategories.UNKNOWN);
	}
}
