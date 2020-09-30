package net.tslat.aoa3.hooks.jei.recipetransfer;

import mezz.jei.api.recipe.transfer.IRecipeTransferInfo;
import net.minecraft.inventory.Slot;
import net.tslat.aoa3.common.containers.ContainerWhitewashingTable;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class WhitewashingTableRecipeTransferInfo implements IRecipeTransferInfo<ContainerWhitewashingTable> {
	@Nonnull
	@Override
	public Class<ContainerWhitewashingTable> getContainerClass() {
		return ContainerWhitewashingTable.class;
	}

	@Override
	public String getRecipeCategoryUid() {
		return "aoa3.whitewashing";
	}

	@Override
	public boolean canHandle(ContainerWhitewashingTable container) {
		return true;
	}

	@Override
	public List<Slot> getRecipeSlots(ContainerWhitewashingTable container) {
		List<Slot> slots = new ArrayList<>(2);

		for (int i = 0; i <= 1; i++) {
			slots.add(container.getSlot(i));
		}

		return slots;
	}

	@Override
	public List<Slot> getInventorySlots(ContainerWhitewashingTable container) {
		List<Slot> inventorySlots = new ArrayList<Slot>(container.inventorySlots.size() - 3);

		for (int i = 2; i < container.inventorySlots.size() - 1; i++) {
			inventorySlots.add(container.getSlot(i));
		}

		return inventorySlots;
	}
}
