package net.tslat.aoa3.content.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectIntPair;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.Mth;
import net.minecraft.util.valueproviders.FloatProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.CommonHooks;
import net.neoforged.neoforge.common.util.RecipeMatcher;
import net.tslat.aoa3.common.menu.ImbuingChamberMenu;
import net.tslat.aoa3.common.registration.AoARecipes;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.content.item.misc.AspectFocusItem;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.tme.api.util.StreamCodecUtil;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ImbuingRecipe implements Recipe<ImbuingRecipe.ImbuingRecipeInput> {
	private final boolean showUnlockNotification;

	private final ObjectIntPair<Holder<Enchantment>> enchant;
	private final NonNullList<Ingredient> ingredients;
	private final int imbuingLevelReq;
	private final Optional<FloatProvider> xpOverride;

	private final boolean isSimpleIngredients;

	public ImbuingRecipe(int imbuingLevelReq, Optional<FloatProvider> xpOverride, Holder<Enchantment> enchant, int enchantLevel, NonNullList<Ingredient> foci, Ingredient powerSource, boolean showUnlockNotification) {
		this.enchant = ObjectIntPair.of(enchant, enchantLevel);
		this.ingredients = NonNullList.withSize(foci.size() + 1, Ingredient.EMPTY);
		this.showUnlockNotification = showUnlockNotification;
		this.imbuingLevelReq = imbuingLevelReq;
		this.xpOverride = xpOverride;
		this.isSimpleIngredients = powerSource.isSimple();

		this.ingredients.set(0, powerSource);

		for (int i = 0; i < foci.size(); i++) {
			this.ingredients.set(i + 1, foci.get(i));
		}
	}

	@Override
	public ItemStack getToastSymbol() {
		return new ItemStack(AoABlocks.IMBUING_CHAMBER.get());
	}

	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return width * height >= getIngredients().size() + 1;
	}

	public int getImbuingLevelReq() {
		return this.imbuingLevelReq;
	}

	public Optional<FloatProvider> getXpOverrideProvider() {
		return this.xpOverride;
	}

	public float getXp(Player player) {
        return getXpOverrideProvider().map(floatProvider -> floatProvider.sample(player.getRandom()))
				.orElseGet(() -> PlayerUtil.getXpForFractionOfLevel(PlayerUtil.getLevel(player, AoASkills.IMBUING.get()), Mth.clamp(getImbuingLevelReq(), 1, 99) / 100f));

    }

	public ObjectIntPair<Holder<Enchantment>> getEnchant() {
		return this.enchant;
	}

	@Override
	public boolean showNotification() {
		return this.showUnlockNotification;
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
		return this.ingredients;
	}

	public Ingredient getPowerSource() {
		return getIngredients().get(0);
	}

	@Override
	public boolean matches(ImbuingRecipeInput input, Level level) {
		final ItemStack targetStack = input.getItem(6);

		if (!targetStack.isEmpty() && !input.inventory.imbuing && !canEnchantInput(targetStack))
			return false;

		final List<Ingredient> ingredients = getIngredients();

		return this.isSimpleIngredients ? checkSimpleIngredients(input, ingredients.size(), targetStack) : checkNonSimpleIngredients(input, ingredients, targetStack);
	}

	public boolean canEnchantInput(ItemStack inputStack) {
		if (inputStack.is(Items.BOOK))
			return false;

		final Holder<Enchantment> enchant = getEnchant().left();

		if (!enchant.value().canEnchant(inputStack) || inputStack.getEnchantmentLevel(enchant) >= getEnchant().rightInt())
			return false;

		for (Holder<Enchantment> existingEnchant : EnchantmentHelper.getEnchantmentsForCrafting(inputStack).keySet()) {
			if (!existingEnchant.is(enchant) && !Enchantment.areCompatible(existingEnchant, enchant))
				return false;
		}

		return true;
	}

	private boolean checkSimpleIngredients(ImbuingRecipeInput input, int ingredientsCount, ItemStack inputStack) {
		StackedContents itemHelper = new StackedContents();

		for (ItemStack ingredient : input.inventory.getItems()) {
			if (ingredient.isEmpty() || ingredient == inputStack)
				continue;

			if (ingredientsCount-- < 0)
				return false;

			itemHelper.accountStack(ingredient, 1);
		}

		return ingredientsCount == 0 && itemHelper.canCraft(this, null);
	}

	private boolean checkNonSimpleIngredients(ImbuingRecipeInput input, List<Ingredient> ingredients, ItemStack inputStack) {
		int ingredientsCount = ingredients.size();
		List<ItemStack> foundIngredients = new ObjectArrayList<>(ingredientsCount);

		for (ItemStack ingredient : input.inventory.getItems()) {
			if (ingredient.isEmpty() || ingredient == inputStack)
				continue;

			if (ingredientsCount-- < 0)
				return false;

			foundIngredients.add(ingredient);
		}

		return ingredientsCount == 0 && RecipeMatcher.findMatches(foundIngredients, ingredients) != null;
	}

	@Override
	public NonNullList<ItemStack> getRemainingItems(ImbuingRecipeInput input) {
		final NonNullList<ItemStack> returns = NonNullList.withSize(input.size() - 1, ItemStack.EMPTY);

		for (int i = 0; i < returns.size(); i++) {
			ItemStack stack = input.getItem(i);

			if (!stack.isEmpty()) {
				if (stack.hasCraftingRemainingItem()) {
					stack = CommonHooks.getCraftingRemainingItem(stack);
				}
				else {
					stack = stack.copy();

					if (i > 0) {
						if (stack.isDamageableItem())
							stack.setDamageValue(stack.getDamageValue() + 1);

						if (stack.getDamageValue() >= stack.getMaxDamage())
							stack = ItemStack.EMPTY;
					}
					else {
						stack = ItemStack.EMPTY;
					}
				}

				returns.set(i, stack);
			}
		}

		return returns;
	}

	@Override
	public ItemStack assemble(ImbuingRecipeInput input, HolderLookup.Provider holderLookup) {
		final ItemStack target = input.getItem(6).copy();

		if (target.isEmpty())
			return target;

		target.enchant(getEnchant().left(), getEnchant().rightInt());

		return target;
	}

	@Override
	public ItemStack getResultItem(HolderLookup.Provider holderLookup) {
		return ItemStack.EMPTY.copy();
	}

	public static class Factory implements RecipeSerializer<ImbuingRecipe> {
		public static final MapCodec<ImbuingRecipe> CODEC = RecordCodecBuilder.mapCodec(builder -> builder.group(
						Codec.intRange(1, 1000).optionalFieldOf("imbuing_level", 1).forGetter(ImbuingRecipe::getImbuingLevelReq),
						FloatProvider.CODEC.optionalFieldOf("imbuing_xp_override").forGetter(ImbuingRecipe::getXpOverrideProvider),
						Enchantment.CODEC.fieldOf("enchantment").forGetter(instance -> instance.getEnchant().left()),
						Codec.intRange(0, 255).fieldOf("enchantment_level").forGetter(instance -> instance.getEnchant().rightInt()),
						AoARegistries.AOA_ASPECT_FOCI.lookupCodec().listOf().fieldOf("aspect_foci").xmap(foci ->
								foci.stream().map(Ingredient::of).<NonNullList<Ingredient>>collect(net.minecraft.core.NonNullList::create, NonNullList::add, NonNullList::addAll), ingredients ->
								ingredients.stream()
										.skip(1)
										.map(ingredient -> ingredient.getItems()[0])
										.map(ItemStack::getItem)
										.map(AspectFocusItem.class::cast)
										.map(AspectFocusItem::getFocus).toList()).forGetter(ImbuingRecipe::getIngredients),
						Ingredient.CODEC_NONEMPTY.fieldOf("power_source").forGetter(ImbuingRecipe::getPowerSource),
						Codec.BOOL.optionalFieldOf("show_notification", true).forGetter(instance -> instance.showUnlockNotification))
				.apply(builder, ImbuingRecipe::new));
		public static final StreamCodec<RegistryFriendlyByteBuf, ImbuingRecipe> STREAM_CODEC = StreamCodec.composite(
				ByteBufCodecs.VAR_INT, ImbuingRecipe::getImbuingLevelReq,
				ByteBufCodecs.optional(ByteBufCodecs.fromCodec(FloatProvider.CODEC)), ImbuingRecipe::getXpOverrideProvider,
				ByteBufCodecs.BOOL, ImbuingRecipe::showNotification,
				ByteBufCodecs.holderRegistry(Registries.ENCHANTMENT), recipe -> recipe.enchant.left(),
				ByteBufCodecs.VAR_INT, recipe -> recipe.enchant.rightInt(),
				StreamCodecUtil.nonNullList(Ingredient.CONTENTS_STREAM_CODEC, Ingredient.EMPTY), ImbuingRecipe::getIngredients,
				(imbuingLevelReq, xpOverride, showUnlockNotification, enchant, enchantLevel, foci) -> {
					Ingredient powerSource = foci.getFirst();
					NonNullList<Ingredient> patchedIngredients = NonNullList.withSize(foci.size() - 1, Ingredient.EMPTY);

					for (int i = 1; i < foci.size(); i++) {
						patchedIngredients.set(i - 1, foci.get(i));
					}

					return new ImbuingRecipe(imbuingLevelReq, xpOverride, enchant, enchantLevel, patchedIngredients, powerSource, showUnlockNotification);
				});

		@Override
		public MapCodec<ImbuingRecipe> codec() {
			return CODEC;
		}

		@Override
		public StreamCodec<RegistryFriendlyByteBuf, ImbuingRecipe> streamCodec() {
			return STREAM_CODEC;
		}
	}

	public record ImbuingRecipeInput(ImbuingChamberMenu.ImbuingInventory inventory) implements RecipeInput {
		@Override
		public ItemStack getItem(int index) {
			return this.inventory.getItem(index);
		}

		@Override
		public int size() {
			return this.inventory.getContainerSize();
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == this)
				return true;

			if (!(obj instanceof ImbuingRecipeInput imbuingRecipeInput))
				return false;

			return Objects.equals(this, imbuingRecipeInput);
		}

		@Override
		public int hashCode() {
			return 0;
		}
	}
}
