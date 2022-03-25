package net.tslat.aoa3.content.recipe;

import com.google.gson.JsonObject;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ICraftingRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.content.block.functional.misc.TrophyBlock;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoARecipes;

import javax.annotation.Nullable;

public class TrophyRecipe implements ICraftingRecipe, net.minecraftforge.common.crafting.IShapedRecipe<CraftingInventory> {
	public static final ResourceLocation ID = new ResourceLocation(AdventOfAscension.MOD_ID, "trophy");
	private final String group;

	public TrophyRecipe(String group) {
		this.group = group;
	}

	@Override
	public boolean matches(CraftingInventory inv, World worldIn) {
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
	public ItemStack assemble(CraftingInventory inv) {
		for (int i = 0; i < inv.getContainerSize(); i++) {
			if (inv.getItem(i).getItem() == AoABlocks.TROPHY.get().asItem())
				return TrophyBlock.cloneTrophy(inv.getItem(i), AoABlocks.GOLD_TROPHY.get());
		}

		return ItemStack.EMPTY;
	}

	private boolean checkMatch(CraftingInventory inv) {
		String entityType = null;

		for(int gridX = 0; gridX < inv.getWidth(); ++gridX) {
			for(int gridY = 0; gridY < inv.getHeight(); ++gridY) {
				ItemStack slotStack = inv.getItem(gridX + gridY * inv.getWidth());

				if (slotStack.getItem() != AoABlocks.TROPHY.get().asItem() || !slotStack.hasTag())
					return false;

				CompoundNBT tag = slotStack.getTag();

				if (!tag.contains("BlockEntityTag"))
					return false;

				CompoundNBT blockEntityTag = tag.getCompound("BlockEntityTag");

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
	public IRecipeSerializer<?> getSerializer() {
		return AoARecipes.TROPHY.getB().get();
	}

	@Override
	public IRecipeType<?> getType() {
		return IRecipeType.CRAFTING;
	}

	@Override
	public int getRecipeWidth() {
		return 3;
	}

	@Override
	public int getRecipeHeight() {
		return 3;
	}

	public static class Factory extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<TrophyRecipe> {
		@Override
		public TrophyRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
			return new TrophyRecipe(json.has("group") ? JSONUtils.getAsString(json, "group") : "");
		}

		@Nullable
		@Override
		public TrophyRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) {
			return new TrophyRecipe(buffer.readUtf(32767));
		}

		@Override
		public void toNetwork(PacketBuffer buffer, TrophyRecipe recipe) {
			buffer.writeUtf(recipe.getGroup());
		}
	}
}
