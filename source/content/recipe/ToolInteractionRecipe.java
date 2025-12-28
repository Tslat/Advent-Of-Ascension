package net.tslat.aoa3.content.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoARecipes;
import net.tslat.aoa3.util.RecipeUtil;
import net.tslat.tme.api.util.StreamCodecUtil;
import org.jetbrains.annotations.Nullable;

public class ToolInteractionRecipe extends CustomRecipe implements RecipeBookRecipe<CraftingInput> {
	private final RecipeUtil.RecipeBookDetails recipeBookDetails;

	private final NonNullList<Ingredient> ingredients;
	private final Ingredient toolItem;
	private final ItemStack output;

	public ToolInteractionRecipe(String group, @Nullable CraftingBookCategory category, boolean showObtainNotification, NonNullList<Ingredient> ingredients, Ingredient toolItem, ItemStack output) {
		this(new RecipeUtil.RecipeBookDetails(group, category, showObtainNotification), ingredients, toolItem, output);
	}

	public ToolInteractionRecipe(RecipeUtil.RecipeBookDetails recipeBookDetails, NonNullList<Ingredient> ingredients, Ingredient toolItem, ItemStack output) {
		super(recipeBookDetails.category());

		this.recipeBookDetails = recipeBookDetails;
		this.ingredients = ingredients;
		this.toolItem = toolItem;
		this.output = output;
	}

	@Override
	public RecipeUtil.RecipeBookDetails recipeBookDetails() {
		return this.recipeBookDetails;
	}

	@Override
	public RecipeSerializer<ToolInteractionRecipe> getSerializer() {
		return AoARecipes.TOOL_INTERACTION.serializer().get();
	}

	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return width * height >= this.ingredients.size() + 1;
	}

	@Override
	public NonNullList<Ingredient> getIngredients() {
		return this.ingredients;
	}

	@Override
	public boolean matches(CraftingInput input, Level level) {
		if (this.ingredients.size() != input.ingredientCount())
			return false;

		boolean hasTool = false;

		for (ItemStack stack : input.items()) {
			if (this.toolItem.test(stack)) {
				hasTool = true;

				break;
			}
		}

		return hasTool && input.stackedContents().canCraft(this, null);
	}

	@Override
	public NonNullList<ItemStack> getRemainingItems(CraftingInput input) {
		final NonNullList<ItemStack> returns = NonNullList.withSize(input.size(), ItemStack.EMPTY);
		boolean hasTool = false;

		for(int i = 0; i < returns.size(); ++i) {
			ItemStack stack = input.getItem(i);

			if (!hasTool && this.toolItem.test(stack)) {
				ItemStack toolCopy = stack.copy();

				if (toolCopy.isDamageableItem())
					toolCopy.setDamageValue(toolCopy.getDamageValue() + 1);

				if (!toolCopy.isDamageableItem() || toolCopy.getDamageValue() < toolCopy.getMaxDamage())
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
	public ItemStack assemble(CraftingInput inventory, HolderLookup.Provider holderLookup) {
		return getResultItem(holderLookup);
	}

	@Override
	public ItemStack getResultItem(HolderLookup.Provider holderLookup) {
		return this.output.copy();
	}

	public static class Factory implements RecipeSerializer<ToolInteractionRecipe> {
		public static final MapCodec<ToolInteractionRecipe> CODEC = RecordCodecBuilder.mapCodec(builder ->
				RecipeUtil.RecipeBookDetails.codec(builder, instance -> instance.recipeBookDetails).and(builder.group(
								RecipeUtil.shapelessIngredientCodec("ToolInteractionRecipe", "ingredients", ShapedRecipePattern.getMaxWidth() * ShapedRecipePattern.getMaxHeight() - 1, ToolInteractionRecipe::getIngredients),
								Ingredient.CODEC_NONEMPTY.fieldOf("tool").forGetter(instance -> instance.toolItem),
								ItemStack.STRICT_CODEC.fieldOf("result").forGetter(instance -> instance.output)))
						.apply(builder, ToolInteractionRecipe::new));
		public static final StreamCodec<RegistryFriendlyByteBuf, ToolInteractionRecipe> STREAM_CODEC = StreamCodec.composite(
				RecipeUtil.RecipeBookDetails.STREAM_CODEC, recipe -> recipe.recipeBookDetails,
				StreamCodecUtil.nonNullList(Ingredient.CONTENTS_STREAM_CODEC, Ingredient.EMPTY), recipe -> recipe.ingredients,
				Ingredient.CONTENTS_STREAM_CODEC, recipe -> recipe.toolItem,
				ItemStack.STREAM_CODEC, recipe -> recipe.output,
				ToolInteractionRecipe::new);

		@Override
		public MapCodec<ToolInteractionRecipe> codec() {
			return CODEC;
		}

		@Override
		public StreamCodec<RegistryFriendlyByteBuf, ToolInteractionRecipe> streamCodec() {
			return STREAM_CODEC;
		}
	}
}
