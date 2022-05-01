package net.tslat.aoa3.content.recipe;

import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.tslat.aoa3.common.registration.block.AoABlocks;

public class WhitewashingRecipe implements Recipe<Inventory> {
	private final RecipeType<WhitewashingRecipe> RECIPE_TYPE = new RecipeType<>() {
		@Override
		public String toString() {
			return "whitewashing";
		}
	};

	private final ResourceLocation id;
	private final String group;

	private final ItemStack input;
	private final ItemStack powderIngredient;
	private final ItemStack output;

	public WhitewashingRecipe(ResourceLocation id, ItemLike powderIngredient, ItemLike output) {
		this(id, "", powderIngredient, output);
	}

	public WhitewashingRecipe(ResourceLocation id, String group, ItemLike powderIngredient, ItemLike output) {
		this.id = id;
		this.group = group;
		this.input = new ItemStack(Blocks.OBSIDIAN);
		this.powderIngredient = new ItemStack(powderIngredient);
		this.output = new ItemStack(output);
	}

	@Override
	public ItemStack getToastSymbol() {
		return new ItemStack(AoABlocks.WHITEWASHING_TABLE.get());
	}

	@Override
	public boolean matches(Inventory inv, Level world) {
		return inv.getItem(0).getItem() == input.getItem() && inv.getItem(1).getItem() == powderIngredient.getItem();
	}

	@Override
	public ItemStack assemble(Inventory inv) {
		return output.copy();
	}

	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return width == 2 && height == 1;
	}

	@Override
	public ItemStack getResultItem() {
		return output.copy();
	}

	@Override
	public ResourceLocation getId() {
		return id;
	}

	@Override
	public RecipeSerializer<WhitewashingRecipe> getSerializer() {
		return null;
	}

	@Override
	public RecipeType<WhitewashingRecipe> getType() {
		return RECIPE_TYPE;
	}

	@Override
	public NonNullList<ItemStack> getRemainingItems(Inventory inv) {
		return NonNullList.create();
	}

	@Override
	public NonNullList<Ingredient> getIngredients() {
		NonNullList<Ingredient> ingredients = NonNullList.create();

		ingredients.add(Ingredient.of(input));
		ingredients.add(Ingredient.of(powderIngredient));

		return ingredients;
	}

	@Override
	public String getGroup() {
		return group;
	}
}
