package net.tslat.aoa3.integration.jei.recipe.upgradekit;

import mezz.jei.api.recipe.transfer.IRecipeTransferInfo;
import net.minecraft.inventory.container.Slot;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.common.container.DivineStationContainer;

import java.util.ArrayList;
import java.util.List;

public class UpgradeKitRecipeTransferInfo implements IRecipeTransferInfo<DivineStationContainer> {
	@Override
	public Class<DivineStationContainer> getContainerClass() {
		return DivineStationContainer.class;
	}

	@Override
	public ResourceLocation getRecipeCategoryUid() {
		return UpgradeKitRecipeCategory.ID;
	}

	@Override
	public boolean canHandle(DivineStationContainer container) {
		return true;
	}

	@Override
	public List<Slot> getRecipeSlots(DivineStationContainer container) {
		List<Slot> slots = new ArrayList<>(2);

		for (int i = 1; i <= 2; i++) {
			slots.add(container.getSlot(i));
		}

		return slots;
	}

	@Override
	public List<Slot> getInventorySlots(DivineStationContainer container) {
		List<Slot> inventorySlots = new ArrayList<Slot>(container.inventorySlots.size() - 3);

		for (int i = 3; i < container.inventorySlots.size(); i++) {
			inventorySlots.add(container.getSlot(i));
		}

		return inventorySlots;
	}
}
