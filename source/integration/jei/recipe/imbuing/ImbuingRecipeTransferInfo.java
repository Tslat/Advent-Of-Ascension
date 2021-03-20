package net.tslat.aoa3.integration.jei.recipe.imbuing;

import mezz.jei.api.recipe.transfer.IRecipeTransferInfo;
import net.minecraft.inventory.container.Slot;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.common.container.InfusionTableContainer;

import java.util.ArrayList;
import java.util.List;

public class ImbuingRecipeTransferInfo implements IRecipeTransferInfo<InfusionTableContainer> {
	@Override
	public Class<InfusionTableContainer> getContainerClass() {
		return InfusionTableContainer.class;
	}

	@Override
	public ResourceLocation getRecipeCategoryUid() {
		return ImbuingRecipeCategory.ID;
	}

	@Override
	public boolean canHandle(InfusionTableContainer container) {
		return true;
	}

	@Override
	public List<Slot> getRecipeSlots(InfusionTableContainer container) {
		List<Slot> slots = new ArrayList<>(10);

		for (int i = 1; i <= 11; i++) {
			slots.add(container.getSlot(i));
		}

		return slots;
	}

	@Override
	public List<Slot> getInventorySlots(InfusionTableContainer container) {
		List<Slot> inventorySlots = new ArrayList<Slot>(container.slots.size() - 11);

		for (int i = 12; i < container.slots.size(); i++) {
			inventorySlots.add(container.getSlot(i));
		}

		return inventorySlots;
	}
}
