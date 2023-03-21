package net.tslat.aoa3.integration.jei.recipe.whitewashing;

import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.transfer.IRecipeTransferInfo;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.tslat.aoa3.common.container.WhitewashingTableContainer;
import net.tslat.aoa3.common.registration.AoAContainers;
import net.tslat.aoa3.content.recipe.WhitewashingRecipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WhitewashingRecipeTransferInfo implements IRecipeTransferInfo<WhitewashingTableContainer, WhitewashingRecipe> {
	@Override
	public Class<WhitewashingTableContainer> getContainerClass() {
		return WhitewashingTableContainer.class;
	}

	@Override
	public Optional<MenuType<WhitewashingTableContainer>> getMenuType() {
		return Optional.of(AoAContainers.WHITEWASHING_TABLE.get());
	}

	@Override
	public RecipeType<WhitewashingRecipe> getRecipeType() {
		return WhitewashingRecipeCategory.RECIPE_TYPE;
	}

	@Override
	public boolean canHandle(WhitewashingTableContainer container, WhitewashingRecipe recipe) {
		return true;
	}

	@Override
	public List<Slot> getRecipeSlots(WhitewashingTableContainer container, WhitewashingRecipe recipe) {
		List<Slot> slots = new ArrayList<>(2);

		for (int i = 0; i <= 1; i++) {
			slots.add(container.getSlot(i));
		}

		return slots;
	}

	@Override
	public List<Slot> getInventorySlots(WhitewashingTableContainer container, WhitewashingRecipe recipe) {
		List<Slot> inventorySlots = new ArrayList<Slot>(container.slots.size() - 3);

		for (int i = 2; i < container.slots.size() - 1; i++) {
			inventorySlots.add(container.getSlot(i));
		}

		return inventorySlots;
	}
}
