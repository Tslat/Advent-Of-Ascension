package net.tslat.aoa3.crafting.recipes;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.client.util.RecipeItemHelper;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.common.util.RecipeMatcher;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.tslat.aoa3.common.containers.ContainerInfusionTable;
import net.tslat.aoa3.utils.ConfigurationUtil;
import net.tslat.aoa3.utils.StringUtil;

import java.util.ArrayList;
import java.util.Map;

public class InfusionTableRecipe extends IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {
	private final ItemStack output;
	private final ItemStack input;
	protected final NonNullList<Ingredient> ingredients;
	private final String group;
	private final boolean isSimple;
	private final int minXp;
	private final int maxXp;

	private final boolean isEnchanting;
	private final Enchantment enchantment;
	private final int enchantmentLevel;

	private final int infusionReq;

	public InfusionTableRecipe(String group, ItemStack output, ItemStack input, NonNullList<Ingredient> ingredients, int infusionLevelReq, int minXp, int maxXp) {
		this.isEnchanting = false;
		this.output = output;
		this.input = input;
		this.ingredients = ingredients;
		this.group = group;
		this.infusionReq = infusionLevelReq;
		boolean simple = true;

		for (Ingredient ingredient : ingredients) {
			if (!ingredient.isSimple()) {
				simple = false;

				break;
			}
		}

		this.isSimple = simple;
		this.enchantment = null;
		this.enchantmentLevel = -1;
		this.minXp = minXp;
		this.maxXp = maxXp;
	}

	public InfusionTableRecipe(String group, Enchantment enchantment, int level, NonNullList<Ingredient> ingredients, int infusionLevelReq, int minXp, int maxXp) {
		this.isEnchanting = true;
		this.group = group;
		this.enchantment = enchantment;
		this.ingredients = ingredients;
		this.enchantmentLevel = level;
		this.infusionReq = infusionLevelReq;
		boolean simple = true;

		for (Ingredient ingredient : ingredients) {
			if (!ingredient.isSimple()) {
				simple = false;

				break;
			}
		}

		this.isSimple = simple;
		this.output = ItemStack.EMPTY;
		this.input = ItemStack.EMPTY;
		this.minXp = minXp;
		this.maxXp = maxXp;
	}

	@Override
	public boolean matches(InventoryCrafting inv, World world) {
		if (inv instanceof ContainerInfusionTable.InventoryInfusion) {
			int ingredientCount = 0;
			RecipeItemHelper recipeItemHelper = new RecipeItemHelper();
			ArrayList<ItemStack> inputIngredients = new ArrayList<ItemStack>();
			ItemStack inputStack = inv.getStackInSlot(0);

			if (inputStack.isEmpty() || (!isEnchanting && (input.getItem() != inputStack.getItem() || (input.getMetadata() != 32767 && input.getMetadata() != inputStack.getMetadata()))))
				return false;

			for (int i = 1; i < 10; i++) {
				ItemStack stack = inv.getStackInSlot(i);

				if (!stack.isEmpty()) {
					ingredientCount++;

					if (isSimple) {
						recipeItemHelper.accountStack(stack, 1);
					}
					else {
						inputIngredients.add(stack);
					}
				}
			}

			if (ingredientCount != ingredients.size())
				return false;

			if (isEnchanting && EnchantmentHelper.getEnchantmentLevel(enchantment, inputStack) >= enchantmentLevel)
				return false;

			if (isSimple)
				return recipeItemHelper.canCraft(this, null);

			return RecipeMatcher.findMatches(inputIngredients, ingredients) != null;
		}

		return false;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv) {
		if (isEnchanting) {
			return provideEmptyOrCompatibleStackForEnchanting(inv.getStackInSlot(0).copy());
		}
		else {
			return output.copy();
		}
	}

	@Override
	public boolean canFit(int width, int height) {
		return width * height >= ingredients.size();
	}

	@Override
	public ItemStack getRecipeOutput() {
		return isEnchanting ? ItemStack.EMPTY : output;
	}

	public ItemStack getRecipeInput() {
		return input;
	}

	public boolean isEnchanting() {
		return isEnchanting;
	}

	public int getInfusionReq() {
		return infusionReq;
	}

	public int getMinXp() {
		return minXp;
	}

	public int getMaxXp() {
		return maxXp;
	}

	public ItemStack getEnchantmentAsBook() {
		if (!isEnchanting)
			return ItemStack.EMPTY;

		ItemStack bookStack = new ItemStack(Items.ENCHANTED_BOOK);

		ItemEnchantedBook.addEnchantment(bookStack, new EnchantmentData(enchantment, enchantmentLevel));

		return bookStack;
	}

	@Override
	public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv) {
		NonNullList<ItemStack> remainingItems = NonNullList.<ItemStack>withSize(inv.getSizeInventory(), ItemStack.EMPTY);

		for (int i = 0; i < remainingItems.size(); i++) {
			ItemStack stack = inv.getStackInSlot(i);

			remainingItems.set(i, ForgeHooks.getContainerItem(stack));
		}

		return remainingItems;
	}

	@Override
	public NonNullList<Ingredient> getIngredients() {
		return ingredients;
	}

	@Override
	public String getGroup() {
		return group;
	}

	public boolean containsIngredient(ItemStack item) {
		for (Ingredient ing : ingredients) {
			if (ing.apply(item))
				return true;
		}

		return false;
	}

	public ItemStack provideEmptyOrCompatibleStackForEnchanting(ItemStack inputStack) {
		if (!enchantment.canApply(inputStack) || (!ConfigurationUtil.MainConfig.allowUnsafeInfusion && enchantment.getMaxLevel() < enchantmentLevel))
			return ItemStack.EMPTY;

		Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(inputStack);

		for (Enchantment enchantment : enchantments.keySet()) {
			if (this.enchantment != enchantment && (!enchantment.isCompatibleWith(this.enchantment) || !this.enchantment.isCompatibleWith(enchantment)))
				return ItemStack.EMPTY;
		}

		enchantments.put(enchantment, enchantmentLevel);
		EnchantmentHelper.setEnchantments(enchantments, inputStack);

		return inputStack;
	}

	public static class Factory implements IRecipeFactory {
		@Override
		public IRecipe parse(JsonContext context, JsonObject json) {
			if (JsonUtils.hasField(json, "infusion")) {
				String group = JsonUtils.getString(json, "group", "");
				Enchantment enchantment;
				int level = 0;
				int infusionReq = 1;
				int minXp = 0;
				int maxXp = 0;
				NonNullList<Ingredient> ingredients = NonNullList.create();
				JsonObject enchantmentJson = JsonUtils.getJsonObject(json, "infusion");

				if (!enchantmentJson.has("enchantment"))
					throw new JsonParseException("No valid enchantment for Infusion recipe");

				String enchantmentString = JsonUtils.getString(enchantmentJson, "enchantment");
				enchantment = ForgeRegistries.ENCHANTMENTS.getValue(new ResourceLocation(enchantmentString));

				if (json.has("infusion_level"))
					infusionReq = JsonUtils.getInt(json, "infusion_level");

				if (json.has("infusion_xp")) {
					JsonObject xpJson = JsonUtils.getJsonObject(json, "infusion_xp");

					if (xpJson.isJsonPrimitive()) {
						minXp = maxXp = xpJson.getAsInt();
					}
					else if (xpJson.has("min")) {
						if (!xpJson.has("max"))
							throw new JsonParseException("No max set for min/max xp amount for infusion recipe");

						minXp = Math.max(0, JsonUtils.getInt(xpJson, "min"));
						maxXp = Math.max(minXp, JsonUtils.getInt(xpJson, "max"));
					}
				}

				if (enchantment == null)
					throw new JsonParseException("Invalid enchantment for infusion recipe: " + enchantmentString);

				if (enchantmentJson.has("level"))
					level = JsonUtils.getInt(enchantmentJson, "level");

				if (!ConfigurationUtil.MainConfig.allowUnsafeInfusion && enchantment.getMaxLevel() < level)
					throw new JsonParseException("Unsafe enchantment level for recipe, Enchantment: " + StringUtil.getLocaleString(enchantment.getName()) + ", Lvl: " + level + ", and Allow Unsafe Infusion not enabled in config");

				for (JsonElement element : JsonUtils.getJsonArray(json, "ingredients")) {
					ingredients.add(CraftingHelper.getIngredient(element, context));
				}

				if (ingredients.isEmpty())
					throw new JsonParseException("No ingredients for infusion table recipe");

				if (ingredients.size() > 9)
					throw new JsonParseException("Too many ingredients for infusion table recipe");

				return new InfusionTableRecipe(group, enchantment, level, ingredients, infusionReq, minXp, maxXp);
			}
			else {
				String group = JsonUtils.getString(json, "group", "");
				ItemStack input = CraftingHelper.getItemStack(JsonUtils.getJsonObject(json, "input"), context);
				ItemStack output = CraftingHelper.getItemStack(JsonUtils.getJsonObject(json, "result"), context);
				NonNullList<Ingredient> ingredients = NonNullList.create();
				int infusionReq = 1;
				int minXp = 0;
				int maxXp = 0;

				if (json.has("infusion_level"))
					infusionReq = JsonUtils.getInt(json, "infusion_level");

				if (json.has("infusion_xp")) {
					JsonObject xpJson = JsonUtils.getJsonObject(json, "infusion_xp");

					if (xpJson.isJsonPrimitive()) {
						minXp = maxXp = xpJson.getAsInt();
					}
					else if (xpJson.has("min")) {
						if (!xpJson.has("max"))
							throw new JsonParseException("No max set for min/max xp amount for infusion recipe");

						minXp = Math.max(0, JsonUtils.getInt(xpJson, "min"));
						maxXp = Math.max(minXp, JsonUtils.getInt(xpJson, "max"));
					}
				}

				for (JsonElement element : JsonUtils.getJsonArray(json, "ingredients")) {
					ingredients.add(CraftingHelper.getIngredient(element, context));
				}

				if (ingredients.isEmpty())
					throw new JsonParseException("No ingredients for infusion table recipe");

				if (ingredients.size() > 9)
					throw new JsonParseException("Too many ingredients for infusion table recipe");

				return new InfusionTableRecipe(group, output, input, ingredients, infusionReq, minXp, maxXp);
			}
		}
	}
}
