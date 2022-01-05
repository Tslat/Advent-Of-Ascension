package net.tslat.aoa3.object.recipe;

import net.minecraft.block.Blocks;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoABlocks;

public class WhitewashingRecipe implements IRecipe<Inventory> {
	private final IRecipeType<WhitewashingRecipe> RECIPE_TYPE = new IRecipeType<WhitewashingRecipe>() {
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

	public WhitewashingRecipe(ResourceLocation id, IItemProvider powderIngredient, IItemProvider output) {
		this(id, "", powderIngredient, output);
	}

	public WhitewashingRecipe(ResourceLocation id, String group, IItemProvider powderIngredient, IItemProvider output) {
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
	public boolean matches(Inventory inv, World world) {
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
	public IRecipeSerializer<WhitewashingRecipe> getSerializer() {
		return null;
	}

	@Override
	public IRecipeType<WhitewashingRecipe> getType() {
		return RECIPE_TYPE;
	}

	@Override
	public NonNullList<ItemStack> getRemainingItems(Inventory inv) {
		return NonNullList.create();
	}

	@Override
	public NonNullList<Ingredient> getIngredients() {
		NonNullList<Ingredient> ingredients = NonNullList.<Ingredient>create();

		ingredients.add(Ingredient.of(input));
		ingredients.add(Ingredient.of(powderIngredient));

		return ingredients;
	}

	@Override
	public String getGroup() {
		return group;
	}
}
