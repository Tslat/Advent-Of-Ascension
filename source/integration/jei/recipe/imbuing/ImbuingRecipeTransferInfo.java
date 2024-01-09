package net.tslat.aoa3.integration.jei.recipe.imbuing;

import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.transfer.IRecipeTransferInfo;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.tslat.aoa3.common.container.InfusionTableContainer;
import net.tslat.aoa3.common.registration.AoAContainers;
import net.tslat.aoa3.content.recipe.ImbuingRecipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ImbuingRecipeTransferInfo implements IRecipeTransferInfo<InfusionTableContainer, ImbuingRecipe> {
	@Override
	public Class<InfusionTableContainer> getContainerClass() {
		return InfusionTableContainer.class;
	}

	@Override
	public Optional<MenuType<InfusionTableContainer>> getMenuType() {
		return Optional.of(AoAContainers.INFUSION_TABLE.get());
	}

	@Override
	public RecipeType<ImbuingRecipe> getRecipeType() {
		return ImbuingRecipeCategory.RECIPE_TYPE;
	}

	@Override
	public boolean canHandle(InfusionTableContainer container, ImbuingRecipe recipe) {
		return true;
	}

	@Override
	public List<Slot> getRecipeSlots(InfusionTableContainer container, ImbuingRecipe recipe) {
		List<Slot> slots = new ArrayList<>(10);

		for (int i = 1; i <= 11; i++) {
			slots.add(container.getSlot(i));
		}

		return slots;
	}

	@Override
	public List<Slot> getInventorySlots(InfusionTableContainer container, ImbuingRecipe recipe) {
		List<Slot> inventorySlots = new ArrayList<Slot>(container.slots.size() - 11);

		for (int i = 12; i < container.slots.size(); i++) {
			inventorySlots.add(container.getSlot(i));
		}

		return inventorySlots;
	}
}
