package net.tslat.aoa3.mixin.client.patch;

import net.minecraft.client.ClientRecipeBook;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientRecipeBook.class)
public class ClientRecipeBookMixin { // Removes the log warnings for unknown recipe types. Why is this error even a thing?
	@Inject(method = "getCategory", at = @At("HEAD"), cancellable = true)
	private static void getCategory(RecipeHolder<?> recipeHolder, CallbackInfoReturnable<RecipeBookCategories> callback) {
		if (!recipeHolder.id().getNamespace().equals("minecraft"))
			callback.setReturnValue(RecipeBookCategories.UNKNOWN);
	}
}
