package net.tslat.aoa3.integration.jei.recipe.arcanumbatteryattach;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
import mezz.jei.api.gui.ingredient.ICraftingGridHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.category.extensions.vanilla.crafting.ICraftingCategoryExtension;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.tslat.aoa3.common.registration.AoATags;
import net.tslat.aoa3.common.registration.item.AoAArtificeDevices;
import net.tslat.aoa3.common.registration.item.AoADataComponents;
import net.tslat.aoa3.content.item.weapon.staff.AoAStaff;
import net.tslat.aoa3.content.recipe.ArcanumBatteryAttachRecipe;

import java.util.List;
import java.util.OptionalInt;

public class ArcanumBatteryAttachRecipeExtension implements ICraftingCategoryExtension<ArcanumBatteryAttachRecipe> {
	@Override
	public void setRecipe(RecipeHolder<ArcanumBatteryAttachRecipe> recipeHolder, IRecipeLayoutBuilder builder, ICraftingGridHelper craftingGridHelper, IFocusGroup focuses) {
		final List<ItemStack> staves = new ObjectArrayList<>();
		final List<ItemStack> attachedStaves = new ObjectArrayList<>();

		for (Holder<Item> tag : BuiltInRegistries.ITEM.getTagOrEmpty(AoATags.Items.STAVES)) {
			ItemStack staff = tag.value().getDefaultInstance();
			ItemStack attachedStaff = staff.copy();

			attachedStaff.set(AoADataComponents.STORED_SPELL_CASTS, new AoAStaff.StoredCasts(0, OptionalInt.empty()));
			staves.add(staff);
			attachedStaves.add(attachedStaff);
		}

		final List<IRecipeSlotBuilder> ingredientSlots = craftingGridHelper.createAndSetInputs(builder, List.of(List.of(AoAArtificeDevices.ARCANUM_BATTERY.toStack()), staves), 0, 0);
		final IRecipeSlotBuilder resultSlot = craftingGridHelper.createAndSetOutputs(builder, attachedStaves);

		builder.createFocusLink(ingredientSlots.get(1), resultSlot);
	}

	@Override
	public int getWidth(RecipeHolder<ArcanumBatteryAttachRecipe> recipeHolder) {
		return 0;
	}

	@Override
	public int getHeight(RecipeHolder<ArcanumBatteryAttachRecipe> recipeHolder) {
		return 0;
	}
}
