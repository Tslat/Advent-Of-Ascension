package net.tslat.aoa3.common.registration;

import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.content.recipe.*;

import java.util.function.Supplier;

public final class AoARecipes {
	public static void init() {}

	public static final RecipeTypeContainer<UpgradeKitRecipe> UPGRADE_KIT = registerRecipeType("upgrade_kit", UpgradeKitRecipe.Factory::new);
	public static final RecipeTypeContainer<InfusionRecipe> INFUSION = registerRecipeType("infusion", InfusionRecipe.Factory::new);
	public static final RecipeTypeContainer<ImbuingRecipe> IMBUING = registerRecipeType("imbuing", ImbuingRecipe.Factory::new);
	public static final RecipeTypeContainer<GoldTrophyRecipe> GOLD_TROPHY = registerRecipeType("gold_trophy", GoldTrophyRecipe.Factory::new);
	public static final RecipeTypeContainer<ToolInteractionRecipe> TOOL_INTERACTION = registerRecipeType("tool_interaction", ToolInteractionRecipe.Factory::new);
	public static final RecipeTypeContainer<AshfernCookingRecipe> ASHFERN_COOKING = registerRecipeType("ashfern_cooking", AshfernCookingRecipe.Factory::new);
	public static final RecipeTypeContainer<WhitewashingRecipe> WHITEWASHING = registerRecipeType("whitewashing", WhitewashingRecipe.Factory::new);

	private static <T extends Recipe<I>, I extends Container> RecipeTypeContainer<T> registerRecipeType(String id, Supplier<RecipeSerializer<T>> serializer) {
		return new RecipeTypeContainer<>(AoARegistries.RECIPE_TYPES.register(id, () -> new RecipeType<>() {
			@Override
			public String toString() {
				return AdventOfAscension.id(id).toString();
			}
		}), AoARegistries.RECIPE_SERIALIZERS.register(id, serializer));
	}

	public record RecipeTypeContainer<T extends Recipe<?>>(DeferredHolder<RecipeType<?>, RecipeType<T>> type, DeferredHolder<RecipeSerializer<?>, RecipeSerializer<T>> serializer) {}
}
