package net.tslat.aoa3.content.recipe;

import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoARecipes;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.content.block.functional.misc.TrophyBlock;

import javax.annotation.Nullable;

public class TrophyRecipe implements CraftingRecipe, net.minecraftforge.common.crafting.IShapedRecipe<CraftingContainer> {
	public static final ResourceLocation ID = new ResourceLocation(AdventOfAscension.MOD_ID, "trophy");
	private final String group;

	public TrophyRecipe(String group) {
		this.group = group;
	}

	@Override
	public boolean matches(CraftingContainer inv, Level worldIn) {
		for(int x = 0; x <= inv.getWidth() - 3; ++x) {
			for(int y = 0; y <= inv.getHeight() - 3; ++y) {
				if (checkMatch(inv))
					return true;
			}
		}

		return false;
	}

	@Override
	public NonNullList<Ingredient> getIngredients() {
		NonNullList<Ingredient> ingredients = NonNullList.create();

		for (int i = 0; i < 9; i++) {
			ingredients.add(Ingredient.of(new ItemStack(AoABlocks.TROPHY.get())));
		}

		return ingredients;
	}

	@Override
	public String getGroup() {
		return group;
	}

	@Override
	public ItemStack assemble(CraftingContainer inv) {
		for (int i = 0; i < inv.getContainerSize(); i++) {
			if (inv.getItem(i).getItem() == AoABlocks.TROPHY.get().asItem())
				return TrophyBlock.cloneTrophy(inv.getItem(i), AoABlocks.GOLD_TROPHY.get());
		}

		return ItemStack.EMPTY;
	}

	private boolean checkMatch(CraftingContainer inv) {
		String entityType = null;

		for(int gridX = 0; gridX < inv.getWidth(); ++gridX) {
			for(int gridY = 0; gridY < inv.getHeight(); ++gridY) {
				ItemStack slotStack = inv.getItem(gridX + gridY * inv.getWidth());

				if (slotStack.getItem() != AoABlocks.TROPHY.get().asItem() || !slotStack.hasTag())
					return false;

				CompoundTag tag = slotStack.getTag();

				if (!tag.contains("BlockEntityTag"))
					return false;

				CompoundTag blockEntityTag = tag.getCompound("BlockEntityTag");

				if (!blockEntityTag.contains("OriginalTrophy") || !blockEntityTag.getBoolean("OriginalTrophy"))
					return false;

				if (blockEntityTag.contains("EntityID")) {
					if (entityType == null) {
						entityType = blockEntityTag.getString("EntityID");
					}
					else if (!entityType.equals(blockEntityTag.getString("EntityID"))) {
						return false;
					}
				}
			}
		}

		return true;
	}

	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return width >= 3 && height >= 3;
	}

	@Override
	public ItemStack getResultItem() {
		return new ItemStack(AoABlocks.GOLD_TROPHY.get());
	}

	@Override
	public ResourceLocation getId() {
		return ID;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return AoARecipes.TROPHY.serializer().get();
	}

	@Override
	public RecipeType<?> getType() {
		return RecipeType.CRAFTING;
	}

	@Override
	public int getRecipeWidth() {
		return 3;
	}

	@Override
	public int getRecipeHeight() {
		return 3;
	}

	public static class Factory implements RecipeSerializer<TrophyRecipe> {
		@Override
		public TrophyRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
			return new TrophyRecipe(json.has("group") ? GsonHelper.getAsString(json, "group") : "");
		}

		@Nullable
		@Override
		public TrophyRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
			return new TrophyRecipe(buffer.readUtf(32767));
		}

		@Override
		public void toNetwork(FriendlyByteBuf buffer, TrophyRecipe recipe) {
			buffer.writeUtf(recipe.getGroup());
		}
	}
}
