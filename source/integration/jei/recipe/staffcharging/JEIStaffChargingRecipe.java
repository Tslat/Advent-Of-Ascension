package net.tslat.aoa3.integration.jei.recipe.staffcharging;

import it.unimi.dsi.fastutil.objects.Reference2IntMap;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.item.AoADataComponents;
import net.tslat.aoa3.content.item.weapon.staff.AoAStaff;
import net.tslat.aoa3.content.recipe.StaffChargingRecipe;

import java.util.OptionalInt;

public class JEIStaffChargingRecipe extends StaffChargingRecipe {
		private final NonNullList<Ingredient> ingredients;
		private final ItemStack output;

		public JEIStaffChargingRecipe(Item staff) {
			super("", CraftingBookCategory.MISC, true);

			Reference2IntMap<Item> runeCost = staff.components().get(AoADataComponents.STAFF_STATS.get()).runeCosts();
			this.ingredients = NonNullList.withSize(runeCost.size() + 1, Ingredient.EMPTY);
			int i = 1;
			ItemStack chargedStaff = staff.getDefaultInstance();
			this.ingredients.set(0, Ingredient.of(staff.getDefaultInstance()));

			chargedStaff.set(AoADataComponents.STORED_SPELL_CASTS, new AoAStaff.StoredCasts(1, OptionalInt.empty()));

			this.output = chargedStaff;

			for (Item item : runeCost.keySet()) {
				this.ingredients.set(i++, Ingredient.of(new ItemStack(item, runeCost.getInt(item))));
			}
		}

		@Override
		public boolean matches(CraftingInput input, Level level) {
			return super.matches(input, level);
		}

		@Override
		public NonNullList<Ingredient> getIngredients() {
			return this.ingredients;
		}

		@Override
		public ItemStack getResultItem(HolderLookup.Provider holderLookup) {
			return this.output;
		}

		@Override
		public ItemStack assemble(CraftingInput inventory, HolderLookup.Provider holderLookup) {
			return this.getResultItem(holderLookup).copy();
		}
	}