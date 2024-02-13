package net.tslat.aoa3.content.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoARecipes;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.util.RecipeUtil;
import net.tslat.aoa3.util.WorldUtil;

import java.util.Optional;

public class AshfernCookingRecipe extends CustomRecipe {
	public static final Codec<AshfernCookingRecipe> CODEC = RecordCodecBuilder.create(builder ->
			RecipeUtil.RecipeBookDetails.codec(builder, instance -> instance.recipeBookDetails)
					.apply(builder, AshfernCookingRecipe::new));

	private final RecipeUtil.RecipeBookDetails recipeBookDetails;

	public AshfernCookingRecipe(String group, CraftingBookCategory category, boolean showObtainNotification) {
		this(new RecipeUtil.RecipeBookDetails(group, category, showObtainNotification));
	}

	public AshfernCookingRecipe(RecipeUtil.RecipeBookDetails recipeBookDetails) {
		super(recipeBookDetails.category());

		this.recipeBookDetails = recipeBookDetails;
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
	public RecipeSerializer<?> getSerializer() {
		return AoARecipes.ASHFERN_COOKING.serializer().get();
	}

	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return width * height >= 2;
	}

	@Override
	public boolean matches(CraftingContainer container, Level level) {
		boolean hasFern = false;
		boolean hasFood = false;

		for (ItemStack stack : container.getItems()) {
			if (stack.isEmpty())
				continue;

			if (stack.is(AoAItems.ASHFERN.get())) {
				if (hasFern)
					return false;

				hasFern = true;

				continue;
			}

			if (hasFood || !stack.isEdible())
				return false;

			Optional<RecipeHolder<SmeltingRecipe>> smeltingRecipe = level.getRecipeManager().getRecipeFor(RecipeType.SMELTING, new SimpleContainer(stack), level);

			if (smeltingRecipe.isPresent() && !smeltingRecipe.get().value().getResultItem(level.registryAccess()).isEmpty()) {
				hasFood = true;
			}
			else {
				return false;
			}
		}

		return hasFern && hasFood;
	}

	@Override
	public ItemStack assemble(CraftingContainer container, RegistryAccess registryAccess) {
		final Level level = WorldUtil.getServer().getLevel(AoADimensions.OVERWORLD);

		if (level == null)
			return ItemStack.EMPTY;

		boolean foundFern = false;
		ItemStack output = ItemStack.EMPTY;

		for (int i = 0; i < container.getContainerSize(); i++) {
			final ItemStack stack = container.getItem(i);

			if (stack.isEmpty())
				continue;

			if (stack.is(AoAItems.ASHFERN.get())) {
				foundFern = true;

				if (!output.isEmpty())
					break;

				continue;
			}

			Optional<RecipeHolder<SmeltingRecipe>> smeltingRecipe = level.getRecipeManager().getRecipeFor(RecipeType.SMELTING, new SimpleContainer(stack), level);

			if (smeltingRecipe.isPresent())
				output = smeltingRecipe.get().value().getResultItem(registryAccess);

			if (foundFern)
				break;
		}

		if (!foundFern)
			return ItemStack.EMPTY;

		output = output.copy();

		return output;
	}

	public static class Factory implements RecipeSerializer<AshfernCookingRecipe> {
		@Override
		public Codec<AshfernCookingRecipe> codec() {
			return AshfernCookingRecipe.CODEC;
		}

		@Override
		public AshfernCookingRecipe fromNetwork(FriendlyByteBuf buffer) {
			return new AshfernCookingRecipe(RecipeUtil.RecipeBookDetails.fromNetwork(buffer));
		}

		@Override
		public void toNetwork(FriendlyByteBuf buffer, AshfernCookingRecipe recipe) {
			recipe.recipeBookDetails.toNetwork(buffer);
		}
	}
}
