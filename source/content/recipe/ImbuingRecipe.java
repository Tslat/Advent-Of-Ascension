package net.tslat.aoa3.content.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.valueproviders.FloatProvider;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.CommonHooks;
import net.neoforged.neoforge.common.util.RecipeMatcher;
import net.tslat.aoa3.common.container.InfusionTableContainer;
import net.tslat.aoa3.common.registration.AoAConfigs;
import net.tslat.aoa3.common.registration.AoARecipes;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.content.recipe.infusiontable.InfusionTableRecipe;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ImbuingRecipe extends InfusionTableRecipe {
	public static final Codec<ImbuingRecipe> CODEC = RecordCodecBuilder.create(builder -> infusionTableRecipe(builder).and(builder.group(
					AoARegistries.ENCHANTMENTS.lookupCodec().fieldOf("enchantment").forGetter(instance -> instance.getEnchant().enchantment),
					Codec.intRange(0, 255).fieldOf("enchantment_level").forGetter(instance -> instance.getEnchant().level),
					Ingredient.CODEC_NONEMPTY
							.listOf()
							.fieldOf("ingredients")
							.flatXmap(
									ingredients -> {
										final Ingredient[] ingredientArray = ingredients.toArray(Ingredient[]::new);

										if (ingredientArray.length == 0)
											return DataResult.error(() -> "No ingredients for Imbuing recipe");

										return ingredientArray.length > 3 * 3
												? DataResult.error(() -> "Too many ingredients for Imbuing recipe. The maximum is: %s".formatted(3 * 3))
												: DataResult.success(NonNullList.of(Ingredient.EMPTY, ingredientArray));
									},
									DataResult::success)
							.forGetter(instance -> instance.catalysts),
					ExtraCodecs.strictOptionalField(Codec.BOOL, "show_notification", true).forGetter(instance -> instance.showUnlockNotification)))
			.apply(builder, ImbuingRecipe::new));

	private final boolean showUnlockNotification;

	private final EnchantmentInstance enchant;
	private final NonNullList<Ingredient> catalysts;

	private final boolean isSimple;

	private boolean isSafeToEnchant = true;
	@Nullable
	private Boolean lastConfigValue = null;

	public ImbuingRecipe(int infusionLevelReq, FloatProvider xp, Enchantment enchant, int enchantLevel, NonNullList<Ingredient> catalysts, boolean showUnlockNotification) {
		super(infusionLevelReq, xp);

		this.enchant = new EnchantmentInstance(enchant, enchantLevel);
		this.catalysts = catalysts;
		this.showUnlockNotification = showUnlockNotification;
		boolean isSimple = true;

		for (Ingredient ingredient : catalysts) {
			if (!ingredient.isSimple()) {
				isSimple = false;

				break;
			}
		}

		this.isSimple = isSimple;
	}

	public EnchantmentInstance getEnchant() {
		return this.enchant;
	}

	@Override
	public boolean showNotification() {
		return this.showUnlockNotification && (AoAConfigs.SERVER.allowUnsafeInfusion.get() || getEnchant().level <= getEnchant().enchantment.getMaxLevel());
	}

	@Override
	public RecipeSerializer<ImbuingRecipe> getSerializer() {
		return AoARecipes.IMBUING.serializer().get();
	}

	@Override
	public RecipeType<ImbuingRecipe> getType() {
		return AoARecipes.IMBUING.type().get();
	}

	@Override
	public NonNullList<Ingredient> getIngredients() {
		return this.catalysts;
	}

	@Override
	public boolean matches(InfusionTableContainer.InfusionInventory inventory, Level level) {
		if (!checkEnchantSafety())
			return false;

		final ItemStack inputStack = inventory.getItem(0);

		if (inputStack.isEmpty() || !canEnchantInput(inputStack))
			return false;

		final List<Ingredient> ingredients = getIngredients();

		return this.isSimple ? checkSimpleIngredients(inventory, ingredients.size(), inputStack) : checkNonSimpleIngredients(inventory, ingredients, inputStack);
	}

	private boolean checkEnchantSafety() {
		final boolean configValue = AoAConfigs.SERVER.allowUnsafeInfusion.get();

		if (this.lastConfigValue != null && this.lastConfigValue == configValue)
			return this.isSafeToEnchant;

		this.lastConfigValue = configValue;
		this.isSafeToEnchant = configValue || getEnchant().level <= getEnchant().enchantment.getMaxLevel();

		return this.isSafeToEnchant;
	}

	public boolean canEnchantInput(ItemStack inputStack) {
		if (inputStack.is(Items.BOOK))
			return false;

		final Enchantment enchant = getEnchant().enchantment;

		if (!enchant.canEnchant(inputStack) || EnchantmentHelper.getItemEnchantmentLevel(enchant, inputStack) >= getEnchant().level)
			return false;

		for (Enchantment existingEnchant : EnchantmentHelper.getEnchantments(inputStack).keySet()) {
			if (!existingEnchant.isCompatibleWith(enchant) || !enchant.isCompatibleWith(existingEnchant))
				return false;
		}

		return true;
	}

	private boolean checkSimpleIngredients(InfusionTableContainer.InfusionInventory inventory, int ingredientsCount, ItemStack inputStack) {
		StackedContents itemHelper = new StackedContents();

		for (ItemStack ingredient : inventory.getItems()) {
			if (ingredient.isEmpty() || ingredient == inputStack)
				continue;

			if (ingredientsCount-- < 0)
				return false;

			itemHelper.accountStack(ingredient, 1);
		}

		return ingredientsCount == 0 && itemHelper.canCraft(this, null);
	}

	private boolean checkNonSimpleIngredients(InfusionTableContainer.InfusionInventory inventory, List<Ingredient> ingredients, ItemStack inputStack) {
		int ingredientsCount = ingredients.size();
		List<ItemStack> foundIngredients = new ObjectArrayList<>(ingredientsCount);

		for (ItemStack ingredient : inventory.getItems()) {
			if (ingredient.isEmpty() || ingredient == inputStack)
				continue;

			if (ingredientsCount-- < 0)
				return false;

			foundIngredients.add(ingredient);
		}

		return ingredientsCount == 0 && RecipeMatcher.findMatches(foundIngredients, ingredients) != null;
	}

	@Override
	public NonNullList<ItemStack> getRemainingItems(InfusionTableContainer.InfusionInventory inv) {
		final NonNullList<ItemStack> returns = NonNullList.withSize(inv.getContainerSize(), ItemStack.EMPTY);

		for (int i = 1; i < returns.size(); i++) {
			ItemStack stack = inv.getItem(i);

			if (stack.hasCraftingRemainingItem())
				returns.set(i, CommonHooks.getCraftingRemainingItem(stack));
		}

		return returns;
	}

	@Override
	public ItemStack assemble(InfusionTableContainer.InfusionInventory inv, RegistryAccess registryAccess) {
		final ItemStack input = inv.getItem(0).copy();

		input.enchant(getEnchant().enchantment, getEnchant().level);

		return input;
	}

	@Override
	public ItemStack getResultItem(RegistryAccess registryAccess) {
		return ItemStack.EMPTY.copy();
	}

	public ItemStack getEnchantmentAsBook() {
		if (!checkEnchantSafety())
			return ItemStack.EMPTY;

		ItemStack bookStack = new ItemStack(Items.ENCHANTED_BOOK);

		EnchantedBookItem.addEnchantment(bookStack, getEnchant());

		return bookStack;
	}

	public static class Factory implements RecipeSerializer<ImbuingRecipe> {
		@Override
		public Codec<ImbuingRecipe> codec() {
			return ImbuingRecipe.CODEC;
		}

		@Override
		public ImbuingRecipe fromNetwork(FriendlyByteBuf buffer) {
			int infusionLevelReq = buffer.readVarInt();
			FloatProvider xpProvider = buffer.readWithCodecTrusted(NbtOps.INSTANCE, FloatProvider.CODEC);
			boolean showNotification = buffer.readBoolean();
			Enchantment enchant = buffer.readById(BuiltInRegistries.ENCHANTMENT);
			int enchantLevel = buffer.readVarInt();
			NonNullList<Ingredient> ingredients = NonNullList.withSize(buffer.readVarInt(), Ingredient.EMPTY);

			ingredients.replaceAll(list -> Ingredient.fromNetwork(buffer));

			return new ImbuingRecipe(infusionLevelReq, xpProvider, enchant, enchantLevel, ingredients, showNotification);
		}

		@Override
		public void toNetwork(FriendlyByteBuf buffer, ImbuingRecipe recipe) {
			buffer.writeVarInt(recipe.getInfusionLevelReq());
			buffer.writeWithCodec(NbtOps.INSTANCE, FloatProvider.CODEC, recipe.getXpProvider());

			buffer.writeBoolean(recipe.showNotification());

			buffer.writeId(BuiltInRegistries.ENCHANTMENT, recipe.getEnchant().enchantment);
			buffer.writeVarInt(recipe.getEnchant().level);
			buffer.writeVarInt(recipe.getIngredients().size());
			recipe.getIngredients().forEach(ingredient -> ingredient.toNetwork(buffer));
		}
	}
}
