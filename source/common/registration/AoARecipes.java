package net.tslat.aoa3.common.registration;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.Tuple;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.content.recipe.ToolInteractionRecipe;
import net.tslat.aoa3.content.recipe.InfusionRecipe;
import net.tslat.aoa3.content.recipe.TrophyRecipe;
import net.tslat.aoa3.content.recipe.UpgradeKitRecipe;

import java.util.function.Supplier;

public final class AoARecipes {
	public static final DeferredRegister<IRecipeSerializer<?>> RECIPES = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, AdventOfAscension.MOD_ID);

	public static final Tuple<IRecipeType<UpgradeKitRecipe>, RegistryObject<IRecipeSerializer<UpgradeKitRecipe>>> UPGRADE_KIT = registerRecipeType("upgrade_kit", UpgradeKitRecipe.Factory::new);
	public static final Tuple<IRecipeType<InfusionRecipe>, RegistryObject<IRecipeSerializer<InfusionRecipe>>> INFUSION = registerRecipeType("infusion", InfusionRecipe.Factory::new);
	public static final Tuple<IRecipeType<TrophyRecipe>, RegistryObject<IRecipeSerializer<TrophyRecipe>>> TROPHY = registerRecipeType("trophy", TrophyRecipe.Factory::new);
	public static final Tuple<IRecipeType<ToolInteractionRecipe>, RegistryObject<IRecipeSerializer<ToolInteractionRecipe>>> TOOL_INTERACTION = registerRecipeType("tool_interaction", ToolInteractionRecipe.Factory::new);

	private static <T extends IRecipe<I>, I extends IInventory> Tuple<IRecipeType<T>, RegistryObject<IRecipeSerializer<T>>> registerRecipeType(String id, Supplier<IRecipeSerializer<T>> serializer) {
		return new Tuple<IRecipeType<T>, RegistryObject<IRecipeSerializer<T>>>(IRecipeType.register(AdventOfAscension.MOD_ID + ":" + id), RECIPES.register(id, serializer));
	}
}
