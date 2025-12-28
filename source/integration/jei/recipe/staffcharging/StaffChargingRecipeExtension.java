package net.tslat.aoa3.integration.jei.recipe.staffcharging;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.Reference2IntMap;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
import mezz.jei.api.gui.ingredient.ICraftingGridHelper;
import mezz.jei.api.gui.ingredient.IRecipeSlotDrawable;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.category.extensions.vanilla.crafting.ICraftingCategoryExtension;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.tslat.aoa3.common.registration.item.AoADataComponents;
import net.tslat.aoa3.content.item.weapon.staff.AoAStaff;
import net.tslat.aoa3.content.recipe.StaffChargingRecipe;

import java.util.List;
import java.util.OptionalInt;

public class StaffChargingRecipeExtension implements ICraftingCategoryExtension<StaffChargingRecipe> {
	@Override
	public void setRecipe(RecipeHolder<StaffChargingRecipe> recipeHolder, IRecipeLayoutBuilder builder, ICraftingGridHelper craftingGridHelper, IFocusGroup focuses) {
		List<ItemStack> staves = new ObjectArrayList<>();
		List<ItemStack> chargedStaves = new ObjectArrayList<>();
		List<List<ItemStack>> inputs = new ObjectArrayList<>();

		inputs.add(staves);

		BuiltInRegistries.ITEM.stream().forEach(item -> {
			if (item.components().has(AoADataComponents.STORED_SPELL_CASTS.get()) && item.components().has(AoADataComponents.STAFF_STATS.get())) {
				final ItemStack staff = item.getDefaultInstance();
				final ItemStack chargedStaff = staff.copy();

				chargedStaff.set(AoADataComponents.STORED_SPELL_CASTS, new AoAStaff.StoredCasts(1, OptionalInt.empty()));

				staves.add(staff);
				chargedStaves.add(chargedStaff);

				int i = 1;

				for (Reference2IntMap.Entry<Item> rune : staff.get(AoADataComponents.STAFF_STATS).runeCosts().reference2IntEntrySet()) {
					if (inputs.size() <= i)
						inputs.add(new ObjectArrayList<>());

					inputs.get(i).add(new ItemStack(rune.getKey(), rune.getIntValue()));
					i++;
				}
			}
		});

		final List<IRecipeSlotBuilder> ingredientSlots = craftingGridHelper.createAndSetInputs(builder, inputs, 0, 0);
		final IRecipeSlotBuilder resultSlot = craftingGridHelper.createAndSetOutputs(builder, chargedStaves);

		builder.setShapeless();
		builder.createFocusLink(ingredientSlots.getFirst(), resultSlot);
	}

	@Override
	public void onDisplayedIngredientsUpdate(RecipeHolder<StaffChargingRecipe> recipeHolder, List<IRecipeSlotDrawable> recipeSlots, IFocusGroup focuses) {
		for (IRecipeSlotDrawable slot : recipeSlots) {
			if (slot.getDisplayedItemStack().filter(item -> item.has(AoADataComponents.STORED_SPELL_CASTS.get()) && item.has(AoADataComponents.STAFF_STATS.get())).isPresent()) {
				final ItemStack staff = slot.getDisplayedItemStack().get();
				int i = 1;

				for (Reference2IntMap.Entry<Item> rune : staff.get(AoADataComponents.STAFF_STATS).runeCosts().reference2IntEntrySet()) {
					recipeSlots.get(i++).createDisplayOverrides().addItemStack(new ItemStack(rune.getKey(), rune.getIntValue()));
				}

				for (; i < recipeSlots.size(); i++) {
					recipeSlots.get(i).createDisplayOverrides().addItemLike(Items.AIR);
				}

				return;
			}
		}
	}

	@Override
	public int getWidth(RecipeHolder<StaffChargingRecipe> recipeHolder) {
		return 0;
	}

	@Override
	public int getHeight(RecipeHolder<StaffChargingRecipe> recipeHolder) {
		return 0;
	}
}
