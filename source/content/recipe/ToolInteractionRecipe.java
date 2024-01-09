package net.tslat.aoa3.content.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoARecipes;
import net.tslat.aoa3.util.RecipeUtil;
import org.jetbrains.annotations.Nullable;


public class ToolInteractionRecipe extends CustomRecipe {
	public static final Codec<ToolInteractionRecipe> CODEC = RecordCodecBuilder.create(builder ->
							RecipeUtil.RecipeBookDetails.codec(builder, instance -> instance.recipeBookDetails).and(builder.group(
							Ingredient.CODEC_NONEMPTY
									.listOf()
									.fieldOf("ingredients")
									.flatXmap(
											ingredients -> {
												final Ingredient[] ingredientArray = ingredients.toArray(Ingredient[]::new);

												if (ingredientArray.length == 0)
													return DataResult.error(() -> "No ingredients for ToolInteractionRecipe");

												return ingredientArray.length > 3 * 3 - 1
														? DataResult.error(() -> "Too many ingredients for ToolInteractionRecipe recipe. The maximum is: %s".formatted(3 * 3 - 1))
														: DataResult.success(NonNullList.of(Ingredient.EMPTY, ingredientArray));
											},
											DataResult::success)
									.forGetter(instance -> instance.ingredients),
							Ingredient.CODEC_NONEMPTY.fieldOf("tool").forGetter(instance -> instance.toolItem),
							ItemStack.ITEM_WITH_COUNT_CODEC.fieldOf("result").forGetter(instance -> instance.output)))
					.apply(builder, ToolInteractionRecipe::new));

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
	public String getGroup() {
		return this.recipeBookDetails.group();
	}

	@Override
	public boolean showNotification() {
		return this.recipeBookDetails.showUnlockNotification();
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
	public boolean matches(CraftingContainer inventory, Level level) {
		StackedContents itemHelper = new StackedContents();
		boolean hasTool = false;
		int ingredientCount = 0;

		for (int i = 0; i < inventory.getContainerSize(); i++) {
			ItemStack stack = inventory.getItem(i);

			if (!stack.isEmpty()) {
				if (!hasTool && this.toolItem.test(stack))
					hasTool = true;

				ingredientCount++;

				itemHelper.accountStack(stack, 1);
			}
		}

		return hasTool && ingredientCount == this.ingredients.size() && itemHelper.canCraft(this, null);
	}

	@Override
	public NonNullList<ItemStack> getRemainingItems(CraftingContainer inventory) {
		final NonNullList<ItemStack> returns = NonNullList.withSize(inventory.getContainerSize(), ItemStack.EMPTY);
		boolean hasTool = false;

		for(int i = 0; i < returns.size(); ++i) {
			ItemStack stack = inventory.getItem(i);

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
	public ItemStack assemble(CraftingContainer inventory, RegistryAccess registryAccess) {
		return getResultItem(registryAccess);
	}

	@Override
	public ItemStack getResultItem(RegistryAccess registryAccess) {
		return this.output.copy();
	}

	public static class Factory implements RecipeSerializer<ToolInteractionRecipe> {
		@Override
		public Codec<ToolInteractionRecipe> codec() {
			return ToolInteractionRecipe.CODEC;
		}

		@Override
		public ToolInteractionRecipe fromNetwork(FriendlyByteBuf buffer) {
			RecipeUtil.RecipeBookDetails recipeBookDetails = RecipeUtil.RecipeBookDetails.fromNetwork(buffer);
			NonNullList<Ingredient> ingredients = NonNullList.withSize(buffer.readVarInt(), Ingredient.EMPTY);

            ingredients.replaceAll(list -> Ingredient.fromNetwork(buffer));

			Ingredient tool = Ingredient.fromNetwork(buffer);
			ItemStack result = buffer.readItem();

			return new ToolInteractionRecipe(recipeBookDetails, ingredients, tool, result);
		}

		@Override
		public void toNetwork(FriendlyByteBuf buffer, ToolInteractionRecipe recipe) {
			recipe.recipeBookDetails.toNetwork(buffer);
			buffer.writeVarInt(recipe.ingredients.size());
			recipe.ingredients.forEach(ingredient -> ingredient.toNetwork(buffer));
			recipe.toolItem.toNetwork(buffer);
			buffer.writeItem(recipe.output);
		}
	}
}
