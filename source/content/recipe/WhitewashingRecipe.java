package net.tslat.aoa3.content.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoARecipes;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.util.RecipeUtil;
import org.jetbrains.annotations.Nullable;


public class WhitewashingRecipe implements Recipe<Inventory> {
	public static final Codec<WhitewashingRecipe> CODEC = RecordCodecBuilder.create(builder ->
					RecipeUtil.RecipeBookDetails.codec(builder, instance -> instance.recipeBookDetails).and(builder.group(
					Ingredient.CODEC_NONEMPTY.fieldOf("input").forGetter(instance -> instance.input),
					Ingredient.CODEC_NONEMPTY.fieldOf("washing_material").forGetter(instance -> instance.washingMaterial),
					ItemStack.ITEM_WITH_COUNT_CODEC.fieldOf("result").forGetter(instance -> instance.output)))
			.apply(builder, WhitewashingRecipe::new));

	private final RecipeUtil.RecipeBookDetails recipeBookDetails;

	private final Ingredient input;
	private final Ingredient washingMaterial;
	private final ItemStack output;

	public WhitewashingRecipe(String group, @Nullable CraftingBookCategory category, boolean showObtainNotification, Ingredient input, Ingredient washingMaterial, ItemStack output) {
		this(new RecipeUtil.RecipeBookDetails(group, category, showObtainNotification), input, washingMaterial, output);
	}

	public WhitewashingRecipe(RecipeUtil.RecipeBookDetails recipeBookDetails, Ingredient input, Ingredient washingMaterial, ItemStack output) {
		this.recipeBookDetails = recipeBookDetails;
		this.input = input;
		this.washingMaterial = washingMaterial;
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
	public RecipeSerializer<WhitewashingRecipe> getSerializer() {
		return AoARecipes.WHITEWASHING.serializer().get();
	}

	@Override
	public RecipeType<WhitewashingRecipe> getType() {
		return AoARecipes.WHITEWASHING.type().get();
	}

	@Override
	public ItemStack getToastSymbol() {
		return new ItemStack(AoABlocks.WHITEWASHING_TABLE.get());
	}

	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return width >= 3;
	}

	@Override
	public NonNullList<Ingredient> getIngredients() {
		return NonNullList.of(null, this.input, this.washingMaterial);
	}

	@Override
	public boolean matches(Inventory inv, Level world) {
		return this.input.test(inv.getItem(0)) && this.washingMaterial.test(inv.getItem(1));
	}

	@Override
	public ItemStack assemble(Inventory inventory, RegistryAccess registryAccess) {
		return getResultItem(registryAccess);
	}

	@Override
	public ItemStack getResultItem(RegistryAccess registryAccess) {
		return this.output.copy();
	}

	public static class Factory implements RecipeSerializer<WhitewashingRecipe> {
		@Override
		public Codec<WhitewashingRecipe> codec() {
			return WhitewashingRecipe.CODEC;
		}

		@Override
		public WhitewashingRecipe fromNetwork(FriendlyByteBuf buffer) {
			return new WhitewashingRecipe(RecipeUtil.RecipeBookDetails.fromNetwork(buffer), Ingredient.fromNetwork(buffer), Ingredient.fromNetwork(buffer), buffer.readItem());
		}

		@Override
		public void toNetwork(FriendlyByteBuf buffer, WhitewashingRecipe recipe) {
			recipe.recipeBookDetails.toNetwork(buffer);
			recipe.input.toNetwork(buffer);
			recipe.washingMaterial.toNetwork(buffer);
			buffer.writeItem(recipe.output);
		}
	}
}
