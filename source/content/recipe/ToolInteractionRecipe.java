package net.tslat.aoa3.content.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoARecipes;

import javax.annotation.Nullable;

public class ToolInteractionRecipe extends CustomRecipe {
	private final NonNullList<Ingredient> ingredients;
	private final Ingredient toolItem;
	private final ItemStack result;

	public ToolInteractionRecipe(ResourceLocation id, Ingredient toolItem, ItemStack result, NonNullList<Ingredient> ingredients) {
		super(id);

		this.ingredients = ingredients;
		this.toolItem = toolItem;
		this.result = result;
	}

	@Override
	public boolean matches(CraftingContainer inventory, Level level) {
		StackedContents itemHelper = new StackedContents();
		boolean hasTool = false;
		int ingredientCount = 0;

		for (int i = 0; i < inventory.getContainerSize(); i++) {
			ItemStack stack = inventory.getItem(i);

			if (!stack.isEmpty()) {
				if (!hasTool && toolItem.test(stack))
					hasTool = true;

				ingredientCount++;

				itemHelper.accountStack(stack, 1);
			}
		}

		return hasTool && ingredientCount == this.ingredients.size() && itemHelper.canCraft(this, null);
	}

	@Override
	public ItemStack assemble(CraftingContainer inventory) {
		return result.copy();
	}

	@Override
	public NonNullList<ItemStack> getRemainingItems(CraftingContainer inventory) {
		NonNullList<ItemStack> returns = NonNullList.withSize(inventory.getContainerSize(), ItemStack.EMPTY);
		boolean hasTool = false;

		for(int i = 0; i < returns.size(); ++i) {
			ItemStack stack = inventory.getItem(i);

			if (!hasTool && toolItem.test(stack)) {
				ItemStack toolCopy = stack.copy();

				toolCopy.setDamageValue(toolCopy.getDamageValue() + 1);

				if (toolCopy.getDamageValue() < toolCopy.getMaxDamage())
					returns.set(i, toolCopy);

				hasTool = true;
			}
			else if (stack.hasCraftingRemainingItem()) {
				returns.set(i, stack.getCraftingRemainingItem());
			}
		}

		return returns;
	}

	@Override
	public NonNullList<Ingredient> getIngredients() {
		return this.ingredients;
	}

	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return width * height >= ingredients.size() + 1;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return AoARecipes.TOOL_INTERACTION.serializer().get();
	}

	@Override
	public ItemStack getResultItem() {
		return this.result.copy();
	}

	public static class Factory implements RecipeSerializer<ToolInteractionRecipe> {
		@Override
		public ToolInteractionRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
			NonNullList<Ingredient> ingredients = NonNullList.create();
			Ingredient toolItem = Ingredient.fromJson(json.get("tool"));
			ItemStack result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));
			JsonArray ingredientsArray = json.getAsJsonArray("ingredients");

			for (JsonElement ingredientElement : ingredientsArray) {
				Ingredient ingredient = Ingredient.fromJson(ingredientElement);

				if (!ingredient.isEmpty())
					ingredients.add(ingredient);
			}

			if (ingredients.isEmpty())
				throw new JsonParseException("No valid ingredients for tool interaction recipe: " + recipeId.toString());

			return new ToolInteractionRecipe(recipeId, toolItem, result, ingredients);
		}

		@Nullable
		@Override
		public ToolInteractionRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
			int ingredientsCount = buffer.readVarInt();
			NonNullList<Ingredient> ingredients = NonNullList.withSize(ingredientsCount, Ingredient.EMPTY);

			for (int i = 0; i < ingredients.size(); i++) {
				ingredients.set(i, Ingredient.fromNetwork(buffer));
			}

			Ingredient toolItem = Ingredient.fromNetwork(buffer);
			ItemStack result = buffer.readItem();

			return new ToolInteractionRecipe(recipeId, toolItem, result, ingredients);
		}

		@Override
		public void toNetwork(FriendlyByteBuf buffer, ToolInteractionRecipe recipe) {
			buffer.writeVarInt(recipe.ingredients.size());

			for (Ingredient ingredient : recipe.ingredients) {
				ingredient.toNetwork(buffer);
			}

			recipe.toolItem.toNetwork(buffer);
			buffer.writeItem(recipe.result);
		}
	}
}
