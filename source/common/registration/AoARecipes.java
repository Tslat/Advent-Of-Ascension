package net.tslat.aoa3.common.registration;

import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.minecraftforge.registries.RegistryObject;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.content.recipe.*;

import java.util.function.Supplier;

public final class AoARecipes {
	public static void init() {}

	public static final RecipeTypeContainer<UpgradeKitRecipe> UPGRADE_KIT = registerRecipeType("upgrade_kit", UpgradeKitRecipe.Factory::new);
	public static final RecipeTypeContainer<InfusionRecipe> INFUSION = registerRecipeType("infusion", InfusionRecipe.Factory::new);
	public static final RecipeTypeContainer<TrophyRecipe> TROPHY = registerRecipeType("trophy", TrophyRecipe.Factory::new);
	public static final RecipeTypeContainer<ToolInteractionRecipe> TOOL_INTERACTION = registerRecipeType("tool_interaction", ToolInteractionRecipe.Factory::new);
	public static final RecipeTypeContainer<AshfernCookingRecipe> ASHFERN_COOKING = registerRecipeType("ashfern_cooking", () -> new SimpleCraftingRecipeSerializer<>(AshfernCookingRecipe::new));
	public static final RecipeTypeContainer<WhitewashingRecipe> WHITEWASHING = registerRecipeType("whitewashing", WhitewashingRecipe.Factory::new);

	private static <T extends Recipe<I>, I extends Container> RecipeTypeContainer<T> registerRecipeType(String id, Supplier<RecipeSerializer<T>> serializer) {
		return new RecipeTypeContainer<T>(AoARegistries.RECIPE_TYPES.register(id, () -> new RecipeType<T>() {
			@Override
			public String toString() {
				return AdventOfAscension.id(id).toString();
			}
		}), AoARegistries.RECIPE_SERIALIZERS.register(id, serializer));
	}

	public record RecipeTypeContainer<T extends Recipe<?>>(RegistryObject<RecipeType<T>> type, RegistryObject<RecipeSerializer<T>> serializer) {}
}
