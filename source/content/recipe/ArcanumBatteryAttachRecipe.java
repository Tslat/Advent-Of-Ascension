package net.tslat.aoa3.content.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoARecipes;
import net.tslat.aoa3.common.registration.AoATags;
import net.tslat.aoa3.common.registration.item.AoAArtificeDevices;
import net.tslat.aoa3.common.registration.item.AoADataComponents;
import net.tslat.aoa3.content.item.weapon.staff.AoAStaff;
import net.tslat.aoa3.util.RecipeUtil;
import org.jetbrains.annotations.Nullable;

import java.util.OptionalInt;

public class ArcanumBatteryAttachRecipe extends CustomRecipe implements RecipeBookRecipe<CraftingInput> {
	private final RecipeUtil.RecipeBookDetails recipeBookDetails;

	private final NonNullList<Ingredient> ingredients;

	public ArcanumBatteryAttachRecipe(String group, @Nullable CraftingBookCategory category, boolean showObtainNotification) {
		this(new RecipeUtil.RecipeBookDetails(group, category, showObtainNotification));
	}

	public ArcanumBatteryAttachRecipe(RecipeUtil.RecipeBookDetails recipeBookDetails) {
		super(recipeBookDetails.category());

		this.recipeBookDetails = recipeBookDetails;
		this.ingredients = NonNullList.of(Ingredient.EMPTY, Ingredient.of(AoATags.Items.STAVES), Ingredient.of(AoAArtificeDevices.ARCANUM_BATTERY));
	}

	@Override
	public RecipeUtil.RecipeBookDetails recipeBookDetails() {
		return this.recipeBookDetails;
	}

	@Override
	public RecipeSerializer<ArcanumBatteryAttachRecipe> getSerializer() {
		return AoARecipes.ARCANUM_BATTERY_ATTACH.serializer().get();
	}

	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return width * height >= this.ingredients.size();
	}

	@Override
	public NonNullList<Ingredient> getIngredients() {
		return this.ingredients;
	}

	@Override
	public boolean matches(CraftingInput input, Level level) {
		if (this.ingredients.size() != input.ingredientCount())
			return false;

		for (ItemStack stack : input.items()) {
			if (stack.is(AoATags.Items.STAVES)) {
				if (AoAStaff.StoredCasts.getIfPresent(stack).map(storedCasts -> storedCasts.stored() >= 0).orElse(false))
					return false;
			}
		}

		return input.stackedContents().canCraft(this, null);
	}

	@Override
	public ItemStack assemble(CraftingInput input, HolderLookup.Provider holderLookup) {
		ItemStack staff = ItemStack.EMPTY;

		for (ItemStack stack : input.items()) {
			if (stack.is(AoATags.Items.STAVES)) {
				staff = stack.copy();

				staff.set(AoADataComponents.STORED_SPELL_CASTS, new AoAStaff.StoredCasts(0, OptionalInt.empty()));

				break;
			}
		}

		return staff;
	}

	public static class Factory implements RecipeSerializer<ArcanumBatteryAttachRecipe> {
		public static final MapCodec<ArcanumBatteryAttachRecipe> CODEC = RecordCodecBuilder.mapCodec(builder ->
				RecipeUtil.RecipeBookDetails.codec(builder, instance -> instance.recipeBookDetails)
						.apply(builder, ArcanumBatteryAttachRecipe::new));
		public static final StreamCodec<RegistryFriendlyByteBuf, ArcanumBatteryAttachRecipe> STREAM_CODEC = StreamCodec.composite(
				RecipeUtil.RecipeBookDetails.STREAM_CODEC, recipe -> recipe.recipeBookDetails,
				ArcanumBatteryAttachRecipe::new);

		@Override
		public MapCodec<ArcanumBatteryAttachRecipe> codec() {
			return CODEC;
		}

		@Override
		public StreamCodec<RegistryFriendlyByteBuf, ArcanumBatteryAttachRecipe> streamCodec() {
			return STREAM_CODEC;
		}
	}
}
