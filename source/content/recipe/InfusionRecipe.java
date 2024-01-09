package net.tslat.aoa3.content.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.valueproviders.FloatProvider;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.CommonHooks;
import net.neoforged.neoforge.common.util.RecipeMatcher;
import net.tslat.aoa3.common.container.InfusionTableContainer;
import net.tslat.aoa3.common.registration.AoARecipes;
import net.tslat.aoa3.content.recipe.infusiontable.InfusionTableRecipe;
import net.tslat.aoa3.util.RecipeUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class InfusionRecipe extends InfusionTableRecipe {
	public static final Codec<InfusionRecipe> CODEC = RecordCodecBuilder.create(builder ->
					infusionTableRecipe(builder).and(
					RecipeUtil.RecipeBookDetails.codec(builder, instance -> instance.recipeBookDetails)).and(builder.group(
					Ingredient.CODEC_NONEMPTY
							.listOf()
							.fieldOf("ingredients")
							.flatXmap(
									ingredients -> {
										final Ingredient[] ingredientArray = ingredients.toArray(Ingredient[]::new);

										if (ingredientArray.length == 0)
											return DataResult.error(() -> "No ingredients for Infusion recipe");

										return ingredientArray.length > 3 * 3
												? DataResult.error(() -> "Too many ingredients for Infusion recipe. The maximum is: %s".formatted(3 * 3))
												: DataResult.success(NonNullList.of(Ingredient.EMPTY, ingredientArray));
									},
									DataResult::success)
							.forGetter(instance -> instance.ingredients),
					Ingredient.CODEC_NONEMPTY.fieldOf("tool").forGetter(instance -> instance.input),
					ItemStack.ITEM_WITH_COUNT_CODEC.fieldOf("result").forGetter(instance -> instance.output)))
			.apply(builder, InfusionRecipe::new));

	private final RecipeUtil.RecipeBookDetails recipeBookDetails;

	private final NonNullList<Ingredient> ingredients;
	private final Ingredient input;
	private final ItemStack output;

	private final boolean isSimple;

	public InfusionRecipe(int infusionLevelReq, FloatProvider xp, String group, @Nullable CraftingBookCategory category, boolean showObtainNotification, NonNullList<Ingredient> ingredients, Ingredient input, ItemStack output) {
		this(infusionLevelReq, xp, new RecipeUtil.RecipeBookDetails(group, category, showObtainNotification), ingredients, input, output);
	}

	public InfusionRecipe(int infusionLevelReq, FloatProvider xp, RecipeUtil.RecipeBookDetails recipeBookDetails, NonNullList<Ingredient> ingredients, Ingredient input, ItemStack output) {
		super(infusionLevelReq, xp);

		this.recipeBookDetails = recipeBookDetails;
		this.ingredients = ingredients;
		this.input = input;
		this.output = output;
		boolean isSimple = true;

		for (Ingredient ingredient : ingredients) {
			if (!ingredient.isSimple()) {
				isSimple = false;

				break;
			}
		}

		this.isSimple = isSimple;
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
	public RecipeSerializer<InfusionRecipe> getSerializer() {
		return AoARecipes.INFUSION.serializer().get();
	}

	@Override
	public RecipeType<InfusionRecipe> getType() {
		return AoARecipes.INFUSION.type().get();
	}

	@Override
	public NonNullList<Ingredient> getIngredients() {
		return this.ingredients;
	}

	public Ingredient getInput() {
		return this.input;
	}

	@Override
	public boolean matches(InfusionTableContainer.InfusionInventory inventory, Level level) {
		final ItemStack inputStack = inventory.getItem(0);

		if (inputStack.isEmpty() || !this.input.test(inputStack))
			return false;

		final List<Ingredient> ingredients = getIngredients();

		return this.isSimple ? checkSimpleIngredients(inventory, ingredients.size(), inputStack) : checkNonSimpleIngredients(inventory, ingredients, inputStack);
	}

	private boolean checkSimpleIngredients(InfusionTableContainer.InfusionInventory inventory, int ingredientsCount, ItemStack inputStack) {
		StackedContents itemHelper = new StackedContents();

		for (ItemStack ingredient : inventory.getItems()) {
			if (ingredient.isEmpty() || ingredient == inputStack)
				continue;

			if (ingredientsCount-- < 0)
				return false;

			itemHelper.accountStack(ingredient, 1);
		}

		return ingredientsCount == 0 && itemHelper.canCraft(this, null);
	}

	private boolean checkNonSimpleIngredients(InfusionTableContainer.InfusionInventory inventory, List<Ingredient> ingredients, ItemStack inputStack) {
		int ingredientsCount = ingredients.size();
		List<ItemStack> foundIngredients = new ObjectArrayList<>(ingredientsCount);

		for (ItemStack ingredient : inventory.getItems()) {
			if (ingredient.isEmpty() || ingredient == inputStack)
				continue;

			if (ingredientsCount-- < 0)
				return false;

			foundIngredients.add(ingredient);
		}

		return ingredientsCount == 0 && RecipeMatcher.findMatches(foundIngredients, ingredients) != null;
	}

	@Override
	public NonNullList<ItemStack> getRemainingItems(InfusionTableContainer.InfusionInventory inv) {
		final NonNullList<ItemStack> returns = NonNullList.withSize(inv.getContainerSize(), ItemStack.EMPTY);

		for (int i = 0; i < returns.size(); i++) {
			ItemStack stack = inv.getItem(i);

			if (stack.hasCraftingRemainingItem())
				returns.set(i, CommonHooks.getCraftingRemainingItem(stack));
		}

		return returns;
	}

	@Override
	public ItemStack assemble(InfusionTableContainer.InfusionInventory inv, RegistryAccess registryAccess) {
		return getResultItem(registryAccess);
	}

	@Override
	public ItemStack getResultItem(RegistryAccess registryAccess) {
		return this.output.copy();
	}

	public static class Factory implements RecipeSerializer<InfusionRecipe> {
		@Override
		public Codec<InfusionRecipe> codec() {
			return InfusionRecipe.CODEC;
		}

		@Override
		public InfusionRecipe fromNetwork(FriendlyByteBuf buffer) {
			RecipeUtil.RecipeBookDetails recipeBookDetails = RecipeUtil.RecipeBookDetails.fromNetwork(buffer);
			int infusionLevelReq = buffer.readVarInt();
			FloatProvider xpProvider = buffer.readWithCodecTrusted(NbtOps.INSTANCE, FloatProvider.CODEC);
			Ingredient input = Ingredient.fromNetwork(buffer);
			NonNullList<Ingredient> ingredients = NonNullList.withSize(buffer.readVarInt(), Ingredient.EMPTY);

			ingredients.replaceAll(list -> Ingredient.fromNetwork(buffer));

			ItemStack result = buffer.readItem();

			return new InfusionRecipe(infusionLevelReq, xpProvider, recipeBookDetails, ingredients, input, result);
		}

		@Override
		public void toNetwork(FriendlyByteBuf buffer, InfusionRecipe recipe) {
			recipe.recipeBookDetails.toNetwork(buffer);

			buffer.writeVarInt(recipe.getInfusionLevelReq());
			buffer.writeWithCodec(NbtOps.INSTANCE, FloatProvider.CODEC, recipe.getXpProvider());

			recipe.input.toNetwork(buffer);
			buffer.writeVarInt(recipe.getIngredients().size());
			recipe.getIngredients().forEach(ingredient -> ingredient.toNetwork(buffer));
			buffer.writeItem(recipe.output);
		}
	}
}