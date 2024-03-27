package net.tslat.aoa3.content.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectIntPair;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.Mth;
import net.minecraft.util.valueproviders.FloatProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.CommonHooks;
import net.neoforged.neoforge.common.util.RecipeMatcher;
import net.tslat.aoa3.common.menu.ImbuingChamberMenu;
import net.tslat.aoa3.common.registration.AoAConfigs;
import net.tslat.aoa3.common.registration.AoARecipes;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.content.item.misc.AspectFocusItem;
import net.tslat.aoa3.util.PlayerUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class ImbuingRecipe implements Recipe<ImbuingChamberMenu.ImbuingInventory> {
	public static final Codec<ImbuingRecipe> CODEC = RecordCodecBuilder.create(builder -> builder.group(
					ExtraCodecs.strictOptionalField(Codec.intRange(1, 1000), "imbuing_level", 1).forGetter(ImbuingRecipe::getImbuingLevelReq),
					FloatProvider.CODEC.optionalFieldOf("imbuing_xp_override").forGetter(ImbuingRecipe::getXpOverrideProvider),
					AoARegistries.ENCHANTMENTS.lookupCodec().fieldOf("enchantment").forGetter(instance -> instance.getEnchant().left()),
					Codec.intRange(0, 255).fieldOf("enchantment_level").forGetter(instance -> instance.getEnchant().rightInt()),
					AoARegistries.AOA_ASPECT_FOCI.lookupCodec().listOf().fieldOf("aspect_foci").xmap(foci ->
							foci.stream().map(Ingredient::of).<NonNullList<Ingredient>>collect(NonNullList::create, NonNullList::add, NonNullList::addAll), ingredients ->
							ingredients.stream()
									.skip(1)
									.map(ingredient -> ingredient.getItems()[0])
                                    .map(ItemStack::getItem)
                                    .map(AspectFocusItem.class::cast)
                                    .map(AspectFocusItem::getFocus).toList()).forGetter(ImbuingRecipe::getIngredients),
					Ingredient.CODEC_NONEMPTY.fieldOf("power_source").forGetter(ImbuingRecipe::getPowerSource),
					ExtraCodecs.strictOptionalField(Codec.BOOL, "show_notification", true).forGetter(instance -> instance.showUnlockNotification))
			.apply(builder, ImbuingRecipe::new));

	private final boolean showUnlockNotification;

	private final ObjectIntPair<Enchantment> enchant;
	private final NonNullList<Ingredient> ingredients;
	private final int imbuingLevelReq;
	private final Optional<FloatProvider> xpOverride;

	private final boolean isSimpleIngredients;

	private boolean isSafeToEnchant = true;
	@Nullable
	private Boolean lastConfigValue = null;

	public ImbuingRecipe(int imbuingLevelReq, Optional<FloatProvider> xpOverride, Enchantment enchant, int enchantLevel, NonNullList<Ingredient> foci, Ingredient powerSource, boolean showUnlockNotification) {
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

	public ObjectIntPair<Enchantment> getEnchant() {
		return this.enchant;
	}

	@Override
	public boolean showNotification() {
		return this.showUnlockNotification && (AoAConfigs.SERVER.allowUnsafeInfusion.get() || getEnchant().rightInt() <= getEnchant().left().getMaxLevel());
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
	public boolean matches(ImbuingChamberMenu.ImbuingInventory inventory, Level level) {
		if (!checkEnchantSafety())
			return false;

		final ItemStack targetStack = inventory.getItem(6);

		if (!targetStack.isEmpty() && !inventory.imbuing && !canEnchantInput(targetStack))
			return false;

		final List<Ingredient> ingredients = getIngredients();

		return this.isSimpleIngredients ? checkSimpleIngredients(inventory, ingredients.size(), targetStack) : checkNonSimpleIngredients(inventory, ingredients, targetStack);
	}

	private boolean checkEnchantSafety() {
		final boolean configValue = AoAConfigs.SERVER.allowUnsafeInfusion.get();

		if (this.lastConfigValue != null && this.lastConfigValue == configValue)
			return this.isSafeToEnchant;

		this.lastConfigValue = configValue;
		this.isSafeToEnchant = configValue || getEnchant().rightInt() <= getEnchant().left().getMaxLevel();

		return this.isSafeToEnchant;
	}

	public boolean canEnchantInput(ItemStack inputStack) {
		if (inputStack.is(Items.BOOK))
			return false;

		final Enchantment enchant = getEnchant().left();

		if (!enchant.canEnchant(inputStack) || inputStack.getEnchantmentLevel(enchant) >= getEnchant().rightInt())
			return false;

		for (Enchantment existingEnchant : EnchantmentHelper.getEnchantments(inputStack).keySet()) {
			if (existingEnchant != enchant && (!existingEnchant.isCompatibleWith(enchant) || !enchant.isCompatibleWith(existingEnchant)))
				return false;
		}

		return true;
	}

	private boolean checkSimpleIngredients(ImbuingChamberMenu.ImbuingInventory inventory, int ingredientsCount, ItemStack inputStack) {
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

	private boolean checkNonSimpleIngredients(ImbuingChamberMenu.ImbuingInventory inventory, List<Ingredient> ingredients, ItemStack inputStack) {
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
	public NonNullList<ItemStack> getRemainingItems(ImbuingChamberMenu.ImbuingInventory inv) {
		final NonNullList<ItemStack> returns = NonNullList.withSize(inv.getContainerSize() - 1, ItemStack.EMPTY);

		for (int i = 0; i < returns.size() - 1; i++) {
			ItemStack stack = inv.getItem(i);

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
	public ItemStack assemble(ImbuingChamberMenu.ImbuingInventory inv, RegistryAccess registryAccess) {
		final ItemStack input = inv.getItem(6).copy();

		if (input.isEmpty())
			return input;

		input.enchant(getEnchant().left(), getEnchant().rightInt());

		return input;
	}

	@Override
	public ItemStack getResultItem(RegistryAccess registryAccess) {
		return ItemStack.EMPTY.copy();
	}

	public static class Factory implements RecipeSerializer<ImbuingRecipe> {
		@Override
		public Codec<ImbuingRecipe> codec() {
			return ImbuingRecipe.CODEC;
		}

		@Override
		public ImbuingRecipe fromNetwork(FriendlyByteBuf buffer) {
			int infusionLevelReq = buffer.readVarInt();
			Optional<FloatProvider> xpProvider = buffer.readOptional(friendlyByteBuf -> friendlyByteBuf.readWithCodecTrusted(NbtOps.INSTANCE, FloatProvider.CODEC));
			boolean showNotification = buffer.readBoolean();
			Enchantment enchant = buffer.readById(BuiltInRegistries.ENCHANTMENT);
			int enchantLevel = buffer.readVarInt();
			NonNullList<Ingredient> ingredients = NonNullList.withSize(buffer.readVarInt(), Ingredient.EMPTY);

			ingredients.replaceAll(list -> Ingredient.fromNetwork(buffer));
			Ingredient powerSource = ingredients.get(0);
			ingredients.set(0, Ingredient.EMPTY);

			return new ImbuingRecipe(infusionLevelReq, xpProvider, enchant, enchantLevel, ingredients, powerSource, showNotification);
		}

		@Override
		public void toNetwork(FriendlyByteBuf buffer, ImbuingRecipe recipe) {
			buffer.writeVarInt(recipe.getImbuingLevelReq());
			buffer.writeOptional(recipe.getXpOverrideProvider(), (friendlyByteBuf, floatProvider) -> friendlyByteBuf.writeWithCodec(NbtOps.INSTANCE, FloatProvider.CODEC, floatProvider));

			buffer.writeBoolean(recipe.showNotification());

			buffer.writeId(BuiltInRegistries.ENCHANTMENT, recipe.getEnchant().left());
			buffer.writeVarInt(recipe.getEnchant().rightInt());
			buffer.writeVarInt(recipe.getIngredients().size());
			recipe.getIngredients().forEach(ingredient -> ingredient.toNetwork(buffer));
		}
	}
}
