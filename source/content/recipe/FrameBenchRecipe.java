package net.tslat.aoa3.content.recipe;

import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.common.registration.item.AoAItems;

/**
 * Just a dummy recipe for JEI
 */
public class FrameBenchRecipe implements Recipe<Inventory> {
	private final RecipeType<FrameBenchRecipe> RECIPE_TYPE = new RecipeType<FrameBenchRecipe>() {
		@Override
		public String toString() {
			return "frame_bench";
		}
	};

	private final String group;

	private final ItemStack input;
	private final ItemStack output;

	public FrameBenchRecipe(ItemLike output) {
		this("", output);
	}

	public FrameBenchRecipe(String group, ItemLike output) {
		this.group = group;
		this.input = new ItemStack(AoAItems.SCRAP_METAL.get());
		this.output = new ItemStack(output);
	}

	@Override
	public ItemStack getToastSymbol() {
		return new ItemStack(AoABlocks.FRAME_BENCH.get());
	}

	@Override
	public boolean matches(Inventory inv, Level world) {
		return inv.getItem(0).getItem() == input.getItem();
	}

	@Override
	public ItemStack assemble(Inventory inv, RegistryAccess registryAccess) {
		return output.copy();
	}

	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return width * height == 1;
	}

	@Override
	public ItemStack getResultItem(RegistryAccess registryAccess) {
		return output.copy();
	}

	@Override
	public RecipeSerializer<FrameBenchRecipe> getSerializer() {
		return null;
	}

	@Override
	public RecipeType<FrameBenchRecipe> getType() {
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
