/*
package net.tslat.aoa3.integration.jei.recipe.framebench;

import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.transfer.IRecipeTransferInfo;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.tslat.aoa3.common.container.FrameBenchContainer;
import net.tslat.aoa3.common.registration.AoAContainers;
import net.tslat.aoa3.content.recipe.FrameBenchRecipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("removal")
public class FrameBenchRecipeTransferInfo implements IRecipeTransferInfo<FrameBenchContainer, FrameBenchRecipe> {
	@Override
	public Class<FrameBenchContainer> getContainerClass() {
		return FrameBenchContainer.class;
	}

	@Override
	public Optional<MenuType<FrameBenchContainer>> getMenuType() {
		return Optional.of(AoAContainers.FRAME_BENCH.get());
	}

	@Override
	public RecipeType<FrameBenchRecipe> getRecipeType() {
		return FrameBenchRecipeCategory.RECIPE_TYPE;
	}

	@Override
	public List<Slot> getInventorySlots(FrameBenchContainer container, FrameBenchRecipe recipe) {
		List<Slot> inventorySlots = new ArrayList<Slot>(container.slots.size() - 2);

		for (int i = 2; i < container.slots.size(); i++) {
			inventorySlots.add(container.getSlot(i));
		}

		return inventorySlots;
	}

	@Override
	public List<Slot> getRecipeSlots(FrameBenchContainer container, FrameBenchRecipe recipe) {
		List<Slot> slots = new ArrayList<>(1);

		slots.add(container.getSlot(0));

		return slots;
	}

	@Override
	public boolean canHandle(FrameBenchContainer container, FrameBenchRecipe recipe) {
		return true;
	}
}
*/
