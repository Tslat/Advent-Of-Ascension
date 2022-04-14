package net.tslat.aoa3.common.registration;

import net.minecraft.util.Tuple;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.registries.RegistryObject;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.content.recipe.InfusionRecipe;
import net.tslat.aoa3.content.recipe.ToolInteractionRecipe;
import net.tslat.aoa3.content.recipe.TrophyRecipe;
import net.tslat.aoa3.content.recipe.UpgradeKitRecipe;

import java.util.function.Supplier;

public final class AoARecipes {
	public static void init() {};

	public static void doVanillaRegistryRegistrations() {
		UPGRADE_KIT.getA().get();
		INFUSION.getA().get();
		TROPHY.getA().get();
		TOOL_INTERACTION.getA().get();
	}

	public static final Tuple<Lazy<RecipeType<UpgradeKitRecipe>>, RegistryObject<RecipeSerializer<UpgradeKitRecipe>>> UPGRADE_KIT = registerRecipeType("upgrade_kit", UpgradeKitRecipe.Factory::new);
	public static final Tuple<Lazy<RecipeType<InfusionRecipe>>, RegistryObject<RecipeSerializer<InfusionRecipe>>> INFUSION = registerRecipeType("infusion", InfusionRecipe.Factory::new);
	public static final Tuple<Lazy<RecipeType<TrophyRecipe>>, RegistryObject<RecipeSerializer<TrophyRecipe>>> TROPHY = registerRecipeType("trophy", TrophyRecipe.Factory::new);
	public static final Tuple<Lazy<RecipeType<ToolInteractionRecipe>>, RegistryObject<RecipeSerializer<ToolInteractionRecipe>>> TOOL_INTERACTION = registerRecipeType("tool_interaction", ToolInteractionRecipe.Factory::new);

	private static <T extends Recipe<I>, I extends Container> Tuple<Lazy<RecipeType<T>>, RegistryObject<RecipeSerializer<T>>> registerRecipeType(String id, Supplier<RecipeSerializer<T>> serializer) {
		return new Tuple<>(Lazy.concurrentOf(() -> RecipeType.register(AdventOfAscension.MOD_ID + ":" + id)), AoARegistries.RECIPES.register(id, serializer));
	}
}
