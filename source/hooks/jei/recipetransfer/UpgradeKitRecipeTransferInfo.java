package net.tslat.aoa3.hooks.jei.recipetransfer;

import mezz.jei.api.recipe.transfer.IRecipeTransferInfo;
import net.minecraft.inventory.Slot;
import net.tslat.aoa3.common.containers.ContainerDivineStation;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class UpgradeKitRecipeTransferInfo implements IRecipeTransferInfo<ContainerDivineStation> {
	@Nonnull
	@Override
	public Class<ContainerDivineStation> getContainerClass() {
		return ContainerDivineStation.class;
	}

	@Override
	public String getRecipeCategoryUid() {
		return "aoa3.upgradeKits";
	}

	@Override
	public boolean canHandle(ContainerDivineStation container) {
		return true;
	}

	@Override
	public List<Slot> getRecipeSlots(ContainerDivineStation container) {
		List<Slot> slots = new ArrayList<>(2);

		for (int i = 1; i <= 2; i++) {
			slots.add(container.getSlot(i));
		}

		return slots;
	}

	@Override
	public List<Slot> getInventorySlots(ContainerDivineStation container) {
		List<Slot> inventorySlots = new ArrayList<Slot>(container.inventorySlots.size() - 3);

		for (int i = 3; i < container.inventorySlots.size(); i++) {
			inventorySlots.add(container.getSlot(i));
		}

		return inventorySlots;
	}
}
