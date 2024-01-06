package net.tslat.aoa3.content.recipe;

import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.tslat.aoa3.common.registration.AoARecipes;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import org.jetbrains.annotations.Nullable;


public class WhitewashingRecipe implements Recipe<Inventory> {
	private final ResourceLocation id;
	private final String group;

	private final ItemStack input;
	private final ItemStack washingMaterial;
	private final ItemStack output;

	public WhitewashingRecipe(ResourceLocation id, String group, ItemStack input, ItemStack washingMaterial, ItemStack output) {
		this.id = id;
		this.group = group;
		this.input = input;
		this.washingMaterial = washingMaterial;
		this.output = output;
	}

	@Override
	public ItemStack getToastSymbol() {
		return new ItemStack(AoABlocks.WHITEWASHING_TABLE.get());
	}

	@Override
	public boolean matches(Inventory inv, Level world) {
		return ItemStack.isSameItem(input, inv.getItem(0)) && ItemStack.isSameItem(washingMaterial, inv.getItem(1));
	}

	@Override
	public ItemStack assemble(Inventory inventory, RegistryAccess registryAccess) {
		return output.copy();
	}

	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return width >= 3;
	}

	@Override
	public ItemStack getResultItem(RegistryAccess registryAccess) {
		return output.copy();
	}

	@Override
	public ResourceLocation getId() {
		return id;
	}

	@Override
	public RecipeSerializer<WhitewashingRecipe> getSerializer() {
		return AoARecipes.WHITEWASHING.serializer().get();
	}

	@Override
	public RecipeType<WhitewashingRecipe> getType() {
		return AoARecipes.WHITEWASHING.type().get();
	}

	@Override
	public NonNullList<Ingredient> getIngredients() {
		NonNullList<Ingredient> ingredients = NonNullList.create();

		ingredients.add(Ingredient.of(input));
		ingredients.add(Ingredient.of(washingMaterial));

		return ingredients;
	}

	@Override
	public String getGroup() {
		return group;
	}

	public static class Factory implements RecipeSerializer<WhitewashingRecipe> {
		@Override
		public WhitewashingRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
			String group = GsonHelper.getAsString(json, "group", "");
			ItemStack inputItem = CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(json, "input"), false);
			ItemStack washingMaterial = CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(json, "washing_material"), false);
			ItemStack output = CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(json, "result"), true);

			return new WhitewashingRecipe(recipeId, group, inputItem, washingMaterial, output);
		}

		@Nullable
		@Override
		public WhitewashingRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
			String group = buffer.readUtf(32767);
			ItemStack inputItem = buffer.readItem();
			ItemStack washingMaterial = buffer.readItem();
			ItemStack output = buffer.readItem();

			return new WhitewashingRecipe(recipeId, group, inputItem, washingMaterial, output);
		}

		@Override
		public void toNetwork(FriendlyByteBuf buffer, WhitewashingRecipe recipe) {
			buffer.writeUtf(recipe.getGroup(), 32767);
			buffer.writeItem(recipe.input);
			buffer.writeItem(recipe.washingMaterial);
			buffer.writeItem(recipe.output);
		}
	}
}
