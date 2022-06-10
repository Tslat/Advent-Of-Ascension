package net.tslat.aoa3.content.recipe;

import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.tslat.aoa3.common.container.DivineStationContainer;
import net.tslat.aoa3.common.registration.AoARecipes;
import net.tslat.aoa3.common.registration.block.AoABlocks;

import javax.annotation.Nullable;

public class UpgradeKitRecipe implements Recipe<DivineStationContainer.DivineStationInventory> {
	private final ResourceLocation id;
	private final String group;

	private final ItemStack input;
	private final ItemStack upgradeKit;
	private final ItemStack output;

	public UpgradeKitRecipe(ResourceLocation id, String group, ItemStack input, ItemStack upgradeKit, ItemStack output) {
		this.id = id;
		this.group = group;
		this.input = input;
		this.upgradeKit = upgradeKit;
		this.output = output;
	}

	@Override
	public ItemStack getToastSymbol() {
		return new ItemStack(AoABlocks.DIVINE_STATION.get());
	}

	@Override
	public boolean matches(DivineStationContainer.DivineStationInventory inv, Level world) {
		return ItemStack.isSame(input, inv.getItem(0)) && ItemStack.isSame(upgradeKit, inv.getItem(1));
	}

	@Override
	public ItemStack assemble(DivineStationContainer.DivineStationInventory inv) {
		return output.copy();
	}

	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return width * height <= 3;
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
	public RecipeSerializer<UpgradeKitRecipe> getSerializer() {
		return AoARecipes.UPGRADE_KIT.serializer().get();
	}

	@Override
	public RecipeType<UpgradeKitRecipe> getType() {
		return AoARecipes.UPGRADE_KIT.type().get();
	}

	@Override
	public NonNullList<ItemStack> getRemainingItems(DivineStationContainer.DivineStationInventory inv) {
		NonNullList<ItemStack> remainingItems = NonNullList.<ItemStack>withSize(inv.getContainerSize(), ItemStack.EMPTY);

		for (int i = 0; i < remainingItems.size(); i++) {
			ItemStack stack = inv.getItem(i);

			remainingItems.set(i, ForgeHooks.getContainerItem(stack));
		}

		return remainingItems;
	}

	@Override
	public NonNullList<Ingredient> getIngredients() {
		NonNullList<Ingredient> ingredients = NonNullList.<Ingredient>create();

		ingredients.add(Ingredient.of(input));
		ingredients.add(Ingredient.of(upgradeKit));

		return ingredients;
	}

	@Override
	public String getGroup() {
		return group;
	}

	public static class Factory implements RecipeSerializer<UpgradeKitRecipe> {
		@Override
		public UpgradeKitRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
			String group = GsonHelper.getAsString(json, "group", "");
			ItemStack inputItem = CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(json, "input"), false);
			ItemStack upgradeKit = CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(json, "upgrade_kit"), false);
			ItemStack output = CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(json, "result"), true);

			return new UpgradeKitRecipe(recipeId, group, inputItem, upgradeKit, output);
		}

		@Nullable
		@Override
		public UpgradeKitRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
			String group = buffer.readUtf(32767);
			ItemStack inputItem = buffer.readItem();
			ItemStack upgradeKit = buffer.readItem();
			ItemStack output = buffer.readItem();

			return new UpgradeKitRecipe(recipeId, group, inputItem, upgradeKit, output);
		}

		@Override
		public void toNetwork(FriendlyByteBuf buffer, UpgradeKitRecipe recipe) {
			buffer.writeUtf(recipe.getGroup(), 32767);
			buffer.writeItem(recipe.input);
			buffer.writeItem(recipe.upgradeKit);
			buffer.writeItem(recipe.output);
		}
	}
}
