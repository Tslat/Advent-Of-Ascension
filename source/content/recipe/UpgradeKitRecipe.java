package net.tslat.aoa3.content.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.container.DivineStationContainer;
import net.tslat.aoa3.common.registration.AoARecipes;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.util.RecipeUtil;
import org.jetbrains.annotations.Nullable;


public class UpgradeKitRecipe implements Recipe<DivineStationContainer.DivineStationInventory> {
	public static final Codec<UpgradeKitRecipe> CODEC = RecordCodecBuilder.create(builder ->
					RecipeUtil.RecipeBookDetails.codec(builder, instance -> instance.recipeBookDetails).and(builder.group(
					Ingredient.CODEC_NONEMPTY.fieldOf("input").forGetter(instance -> instance.input),
					Ingredient.CODEC_NONEMPTY.fieldOf("upgrade_kit").forGetter(instance -> instance.upgradeKit),
					ItemStack.ITEM_WITH_COUNT_CODEC.fieldOf("result").forGetter(instance -> instance.output)))
			.apply(builder, UpgradeKitRecipe::new));

	private final RecipeUtil.RecipeBookDetails recipeBookDetails;

	private final Ingredient input;
	private final Ingredient upgradeKit;
	private final ItemStack output;

	public UpgradeKitRecipe(String group, @Nullable CraftingBookCategory category, boolean showObtainNotification, Ingredient input, Ingredient upgradeKit, ItemStack output) {
		this(new RecipeUtil.RecipeBookDetails(group, category, showObtainNotification), input, upgradeKit, output);
	}

	public UpgradeKitRecipe(RecipeUtil.RecipeBookDetails recipeBookDetails, Ingredient input, Ingredient upgradeKit, ItemStack output) {
		this.recipeBookDetails = recipeBookDetails;
		this.input = input;
		this.upgradeKit = upgradeKit;
		this.output = output;
	}

	@Override
	public String getGroup() {
		return this.recipeBookDetails.group();
	}

	@Override
	public boolean showNotification() {
		return this.recipeBookDetails.showUnlockNotification();
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
	public ItemStack getToastSymbol() {
		return new ItemStack(AoABlocks.DIVINE_STATION.get());
	}

	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return width >= 3;
	}

	@Override
	public NonNullList<Ingredient> getIngredients() {
		return NonNullList.of(null, this.input, this.upgradeKit);
	}

	@Override
	public boolean matches(DivineStationContainer.DivineStationInventory inv, Level world) {
		return this.input.test(inv.getItem(0)) && this.upgradeKit.test(inv.getItem(1));
	}

	@Override
	public ItemStack assemble(DivineStationContainer.DivineStationInventory inv, RegistryAccess registryAccess) {
		return getResultItem(registryAccess);
	}

	@Override
	public ItemStack getResultItem(RegistryAccess registryAccess) {
		return this.output.copy();
	}

	public static class Factory implements RecipeSerializer<UpgradeKitRecipe> {
		@Override
		public Codec<UpgradeKitRecipe> codec() {
			return UpgradeKitRecipe.CODEC;
		}

		@Override
		public UpgradeKitRecipe fromNetwork(FriendlyByteBuf buffer) {
			return new UpgradeKitRecipe(RecipeUtil.RecipeBookDetails.fromNetwork(buffer), Ingredient.fromNetwork(buffer), Ingredient.fromNetwork(buffer), buffer.readItem());
		}

		@Override
		public void toNetwork(FriendlyByteBuf buffer, UpgradeKitRecipe recipe) {
			recipe.recipeBookDetails.toNetwork(buffer);
			recipe.input.toNetwork(buffer);
			recipe.upgradeKit.toNetwork(buffer);
			buffer.writeItem(recipe.output);
		}
	}
}
