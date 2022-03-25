package net.tslat.aoa3.content.recipe;

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
import net.tslat.aoa3.common.registration.AoAItems;

public class FrameBenchRecipe implements IRecipe<Inventory> {
	private final IRecipeType<FrameBenchRecipe> RECIPE_TYPE = new IRecipeType<FrameBenchRecipe>() {
		@Override
		public String toString() {
			return "frame_bench";
		}
	};

	private final ResourceLocation id;
	private final String group;

	private final ItemStack input;
	private final ItemStack output;

	public FrameBenchRecipe(ResourceLocation id, IItemProvider output) {
		this(id, "", output);
	}

	public FrameBenchRecipe(ResourceLocation id, String group, IItemProvider output) {
		this.id = id;
		this.group = group;
		this.input = new ItemStack(AoAItems.SCRAP_METAL.get());
		this.output = new ItemStack(output);
	}

	@Override
	public ItemStack getToastSymbol() {
		return new ItemStack(AoABlocks.FRAME_BENCH.get());
	}

	@Override
	public boolean matches(Inventory inv, World world) {
		return inv.getItem(0).getItem() == input.getItem();
	}

	@Override
	public ItemStack assemble(Inventory inv) {
		return output.copy();
	}

	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return width * height == 1;
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
	public IRecipeSerializer<FrameBenchRecipe> getSerializer() {
		return null;
	}

	@Override
	public IRecipeType<FrameBenchRecipe> getType() {
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

		return ingredients;
	}

	@Override
	public String getGroup() {
		return group;
	}
}
