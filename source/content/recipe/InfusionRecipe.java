package net.tslat.aoa3.content.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.util.RecipeMatcher;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.container.InfusionTableContainer;
import net.tslat.aoa3.common.registration.AoARecipes;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Map;

public class InfusionRecipe implements Recipe<InfusionTableContainer.InfusionInventory> {
	private static final InfusionRecipe EMPTY_RECIPE = new InfusionRecipe(new ResourceLocation(AdventOfAscension.MOD_ID, "infusion_empty"), "", ItemStack.EMPTY, Ingredient.EMPTY, NonNullList.<Ingredient>create(), 1, 0, 0);

	private final ResourceLocation id;
	private final String group;

	private final ItemStack output;
	private final Ingredient input;
	protected final NonNullList<Ingredient> ingredients;
	private final boolean isSimple;
	private final int minXp;
	private final int maxXp;

	private final boolean isEnchanting;
	private final Enchantment enchantment;
	private final int enchantmentLevel;

	private final int infusionReq;

	public InfusionRecipe(ResourceLocation id, String group, ItemStack output, Ingredient input, NonNullList<Ingredient> ingredients, int infusionLevelReq, int minXp, int maxXp) {
		this.id = id;
		this.isEnchanting = false;
		this.output = output;
		this.input = input;
		this.ingredients = ingredients;
		this.group = group;
		this.infusionReq = Mth.clamp(infusionLevelReq, 1, 1000);
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

	public InfusionRecipe(ResourceLocation id, String group, Enchantment enchantment, int level, NonNullList<Ingredient> ingredients, int infusionLevelReq, int minXp, int maxXp) {
		this.id = id;
		this.isEnchanting = true;
		this.group = group;
		this.enchantment = enchantment;
		this.ingredients = ingredients;
		this.enchantmentLevel = level;
		this.infusionReq = Mth.clamp(infusionLevelReq, 1, 1000);
		boolean simple = true;

		for (Ingredient ingredient : ingredients) {
			if (!ingredient.isSimple()) {
				simple = false;

				break;
			}
		}

		this.isSimple = simple;
		this.output = ItemStack.EMPTY;
		this.input = Ingredient.EMPTY;
		this.minXp = minXp;
		this.maxXp = maxXp;
	}

	@Override
	public boolean matches(InfusionTableContainer.InfusionInventory inv, Level world) {
		if (this != EMPTY_RECIPE) {
			int ingredientCount = 0;
			StackedContents recipeItemHelper = new StackedContents();
			ArrayList<ItemStack> inputIngredients = new ArrayList<ItemStack>();
			ItemStack inputStack = inv.getItem(0);

			if (inputStack.isEmpty() || (!isEnchanting && !input.test(inputStack)))
				return false;

			for (int i = 1; i < 10; i++) {
				ItemStack stack = inv.getItem(i);

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

			if (isEnchanting && EnchantmentHelper.getItemEnchantmentLevel(enchantment, inputStack) >= enchantmentLevel)
				return false;

			if (isSimple)
				return recipeItemHelper.canCraft(this, null);

			return RecipeMatcher.findMatches(inputIngredients, ingredients) != null;
		}

		return false;
	}

	@Override
	public ItemStack assemble(InfusionTableContainer.InfusionInventory inv) {
		if (isEnchanting) {
			return provideEmptyOrCompatibleStackForEnchanting(inv.getItem(0).copy());
		}
		else {
			return output.copy();
		}
	}

	@Override
	public ItemStack getToastSymbol() {
		return new ItemStack(AoABlocks.INFUSION_TABLE.get());
	}

	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return width * height >= ingredients.size();
	}

	@Override
	public ItemStack getResultItem() {
		return isEnchanting ? ItemStack.EMPTY : output;
	}

	public Ingredient getRecipeInput() {
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

	@Nullable
	public EnchantmentInstance getEnchantment() {
		if (enchantment == null)
			return null;

		return new EnchantmentInstance(enchantment, enchantmentLevel);
	}

	public ItemStack getEnchantmentAsBook() {
		if (!isEnchanting || this == EMPTY_RECIPE)
			return ItemStack.EMPTY;

		ItemStack bookStack = new ItemStack(Items.ENCHANTED_BOOK);

		EnchantedBookItem.addEnchantment(bookStack, new EnchantmentInstance(enchantment, enchantmentLevel));

		return bookStack;
	}

	@Override
	public NonNullList<ItemStack> getRemainingItems(InfusionTableContainer.InfusionInventory inv) {
		NonNullList<ItemStack> remainingItems = NonNullList.<ItemStack>withSize(inv.getContainerSize(), ItemStack.EMPTY);

		remainingItems.set(0, ItemStack.EMPTY);

		for (int i = 1; i < remainingItems.size(); i++) {
			ItemStack stack = inv.getItem(i);

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

	@Override
	public ResourceLocation getId() {
		return id;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return AoARecipes.INFUSION.serializer().get();
	}

	@Override
	public RecipeType<?> getType() {
		return AoARecipes.INFUSION.type().get();
	}

	public ItemStack provideEmptyOrCompatibleStackForEnchanting(ItemStack inputStack) {
		if (this == EMPTY_RECIPE || !enchantment.canEnchant(inputStack) || (!AoAConfig.SERVER.allowUnsafeInfusion.get() && enchantment.getMaxLevel() < enchantmentLevel))
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

	public static class Factory implements RecipeSerializer<InfusionRecipe> {
		@Override
		public InfusionRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
			if (GsonHelper.isValidNode(json, "infusion")) {
				String group = GsonHelper.getAsString(json, "group", "");
				Enchantment enchantment;
				int level = 1;
				int infusionReq = 1;
				int minXp = 0;
				int maxXp = 0;
				NonNullList<Ingredient> ingredients = NonNullList.create();
				JsonObject enchantmentJson = GsonHelper.getAsJsonObject(json, "infusion");

				if (!enchantmentJson.has("enchantment"))
					throw new JsonParseException("No valid enchantment for Infusion recipe");

				String enchantmentString = GsonHelper.getAsString(enchantmentJson, "enchantment");
				enchantment = ForgeRegistries.ENCHANTMENTS.getValue(new ResourceLocation(enchantmentString));

				if (json.has("infusion_level"))
					infusionReq = GsonHelper.getAsInt(json, "infusion_level");

				if (json.has("infusion_xp")) {
					JsonObject xpJson = GsonHelper.getAsJsonObject(json, "infusion_xp");

					if (xpJson.isJsonPrimitive()) {
						minXp = maxXp = xpJson.getAsInt();
					}
					else if (xpJson.has("min")) {
						if (!xpJson.has("max"))
							throw new JsonParseException("No max set for min/max xp amount for infusion recipe");

						minXp = Math.max(0, GsonHelper.getAsInt(xpJson, "min"));
						maxXp = Math.max(minXp, GsonHelper.getAsInt(xpJson, "max"));
					}
				}

				if (enchantment == null)
					throw new JsonParseException("Invalid enchantment for infusion recipe: " + enchantmentString);

				if (enchantmentJson.has("level"))
					level = GsonHelper.getAsInt(enchantmentJson, "level");

				if (!AoAConfig.SERVER.allowUnsafeInfusion.get() && enchantment.getMaxLevel() < level)
					throw new JsonParseException("Unsafe enchantment level for recipe, Enchantment: " + LocaleUtil.getLocaleString(enchantment.getDescriptionId()) + ", Lvl: " + level + ", and Allow Unsafe Infusion not enabled in config");

				for (JsonElement element : GsonHelper.getAsJsonArray(json, "ingredients")) {
					try {
						ingredients.add(CraftingHelper.getIngredient(element));
					}
					catch (JsonSyntaxException ex) {
						if (ex.getMessage().startsWith("Unknown item") && !ModList.get().isLoaded(ex.getMessage().split("'")[1].split(":")[0]))
							return EMPTY_RECIPE;

						throw ex;
					}
				}

				if (ingredients.isEmpty())
					throw new JsonParseException("No ingredients for infusion table recipe");

				if (ingredients.size() > 9)
					throw new JsonParseException("Too many ingredients for infusion table recipe");

				return new InfusionRecipe(recipeId, group, enchantment, level, ingredients, infusionReq, minXp, maxXp);
			}
			else {
				String group = GsonHelper.getAsString(json, "group", "");
				Ingredient input = CraftingHelper.getIngredient(GsonHelper.getAsJsonObject(json, "input"));
				ItemStack output = CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(json, "result"), true);
				NonNullList<Ingredient> ingredients = NonNullList.create();
				int infusionReq = 1;
				int minXp = 0;
				int maxXp = 0;

				if (json.has("infusion_level"))
					infusionReq = GsonHelper.getAsInt(json, "infusion_level");

				if (json.has("infusion_xp")) {
					JsonObject xpJson = GsonHelper.getAsJsonObject(json, "infusion_xp");

					if (xpJson.isJsonPrimitive()) {
						minXp = maxXp = xpJson.getAsInt();
					}
					else if (xpJson.has("min")) {
						if (!xpJson.has("max"))
							throw new JsonParseException("No max set for min/max xp amount for infusion recipe");

						minXp = Math.max(0, GsonHelper.getAsInt(xpJson, "min"));
						maxXp = Math.max(minXp, GsonHelper.getAsInt(xpJson, "max"));
					}
				}

				for (JsonElement element : GsonHelper.getAsJsonArray(json, "ingredients")) {
					ingredients.add(CraftingHelper.getIngredient(element));
				}

				if (ingredients.isEmpty())
					throw new JsonParseException("No ingredients for infusion table recipe");

				if (ingredients.size() > 9)
					throw new JsonParseException("Too many ingredients for infusion table recipe");

				return new InfusionRecipe(recipeId, group, output, input, ingredients, infusionReq, minXp, maxXp);
			}
		}

		@Nullable
		@Override
		public InfusionRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
			String group = buffer.readUtf(32767);

			if (buffer.readBoolean()) {
				Enchantment ench = ForgeRegistries.ENCHANTMENTS.getValue(buffer.readResourceLocation());
				int lvl = buffer.readInt();
				NonNullList<Ingredient> ingredients = NonNullList.withSize(buffer.readInt(), Ingredient.EMPTY);

				for (int i = 0; i < ingredients.size(); i++) {
					ingredients.set(i, Ingredient.fromNetwork(buffer));
				}

				int infusionReq = buffer.readInt();
				int minXp = buffer.readInt();
				int maxXp = buffer.readInt();

				return new InfusionRecipe(recipeId, group, ench, lvl, ingredients, infusionReq, minXp, maxXp);
			}
			else {
				Ingredient input = Ingredient.fromNetwork(buffer);
				ItemStack output = buffer.readItem();
				NonNullList<Ingredient> ingredients = NonNullList.withSize(buffer.readInt(), Ingredient.EMPTY);

				for (int i = 0; i < ingredients.size(); i++) {
					ingredients.set(i, Ingredient.fromNetwork(buffer));
				}

				int infusionReq = buffer.readInt();
				int minXp = buffer.readInt();
				int maxXp = buffer.readInt();

				return new InfusionRecipe(recipeId, group, output, input, ingredients, infusionReq, minXp, maxXp);
			}
		}

		@Override
		public void toNetwork(FriendlyByteBuf buffer, InfusionRecipe recipe) {
			buffer.writeUtf(recipe.getGroup());

			if (recipe.isEnchanting()) {
				buffer.writeBoolean(true);
				buffer.writeResourceLocation(ForgeRegistries.ENCHANTMENTS.getKey(recipe.getEnchantment().enchantment));
				buffer.writeInt(recipe.getEnchantment().level);
				buffer.writeInt(recipe.getIngredients().size());

				for (Ingredient ing : recipe.getIngredients()) {
					ing.toNetwork(buffer);
				}
			}
			else {
				buffer.writeBoolean(false);
				recipe.getRecipeInput().toNetwork(buffer);
				buffer.writeItemStack(recipe.getResultItem(), false);
				buffer.writeInt(recipe.getIngredients().size());

				for (Ingredient ing : recipe.getIngredients()) {
					ing.toNetwork(buffer);
				}
			}

			buffer.writeInt(recipe.getInfusionReq());
			buffer.writeInt(recipe.getMinXp());
			buffer.writeInt(recipe.getMaxXp());
		}
	}
}
