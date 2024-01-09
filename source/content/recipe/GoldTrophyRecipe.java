package net.tslat.aoa3.content.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoARecipes;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.content.block.functional.misc.TrophyBlock;
import net.tslat.aoa3.util.RecipeUtil;

public class GoldTrophyRecipe extends CustomRecipe {
	public static final Codec<GoldTrophyRecipe> CODEC = RecordCodecBuilder.create(builder ->
			RecipeUtil.RecipeBookDetails.codec(builder, instance -> instance.recipeBookDetails)
					.apply(builder, GoldTrophyRecipe::new));

	private final RecipeUtil.RecipeBookDetails recipeBookDetails;

	private final NonNullList<Ingredient> ingredients = NonNullList.withSize(9, Ingredient.of(AoABlocks.TROPHY.get()));

	public GoldTrophyRecipe(String group, CraftingBookCategory category, boolean showObtainNotification) {
		this(new RecipeUtil.RecipeBookDetails(group, category, showObtainNotification));
	}

	public GoldTrophyRecipe(RecipeUtil.RecipeBookDetails recipeBookDetails) {
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
		return AoARecipes.GOLD_TROPHY.serializer().get();
	}

	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return width >= 3 && height >= 3;
	}

	@Override
	public NonNullList<Ingredient> getIngredients() {
		return this.ingredients;
	}

	@Override
	public boolean matches(CraftingContainer inv, Level worldIn) {
		String entityType = null;

		for (ItemStack stack : inv.getItems()) {
			if (stack.getItem() != AoABlocks.TROPHY.get().asItem() || !TrophyBlock.isOriginal(stack))
				return false;

			CompoundTag tag = stack.getTag().getCompound("BlockEntityTag");

			if (!tag.contains("EntityID", Tag.TAG_STRING))
				return false;

			String entityId = tag.getString("EntityID");

			if (entityType == null) {
				entityType = entityId;
			}
			else if (!entityType.equals(entityId)) {
				return false;
			}
		}

		return true;
	}

	@Override
	public ItemStack assemble(CraftingContainer inv, RegistryAccess registryAccess) {
		for (int i = 0; i < inv.getContainerSize(); i++) {
			if (inv.getItem(i).getItem() == AoABlocks.TROPHY.get().asItem())
				return TrophyBlock.cloneTrophy(inv.getItem(i), AoABlocks.GOLD_TROPHY.get());
		}

		return ItemStack.EMPTY;
	}

	@Override
	public ItemStack getResultItem(RegistryAccess registryAccess) {
		return new ItemStack(AoABlocks.GOLD_TROPHY.get());
	}

	public static class Factory implements RecipeSerializer<GoldTrophyRecipe> {
		@Override
		public Codec<GoldTrophyRecipe> codec() {
			return GoldTrophyRecipe.CODEC;
		}

		@Override
		public GoldTrophyRecipe fromNetwork(FriendlyByteBuf buffer) {
			return new GoldTrophyRecipe(RecipeUtil.RecipeBookDetails.fromNetwork(buffer));
		}

		@Override
		public void toNetwork(FriendlyByteBuf buffer, GoldTrophyRecipe recipe) {
			recipe.recipeBookDetails.toNetwork(buffer);
		}
	}
}
