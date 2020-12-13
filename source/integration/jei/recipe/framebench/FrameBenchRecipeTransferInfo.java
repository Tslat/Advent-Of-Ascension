package net.tslat.aoa3.integration.jei.recipe.framebench;

import mezz.jei.api.recipe.transfer.IRecipeTransferInfo;
import net.minecraft.inventory.container.Slot;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.common.container.FrameBenchContainer;

import java.util.ArrayList;
import java.util.List;

public class FrameBenchRecipeTransferInfo implements IRecipeTransferInfo<FrameBenchContainer> {
	@Override
	public Class<FrameBenchContainer> getContainerClass() {
		return FrameBenchContainer.class;
	}

	@Override
	public ResourceLocation getRecipeCategoryUid() {
		return FrameBenchRecipeCategory.ID;
	}

	@Override
	public boolean canHandle(FrameBenchContainer container) {
		return true;
	}

	@Override
	public List<Slot> getRecipeSlots(FrameBenchContainer container) {
		List<Slot> slots = new ArrayList<>(1);

		slots.add(container.getSlot(0));

		return slots;
	}

	@Override
	public List<Slot> getInventorySlots(FrameBenchContainer container) {
		List<Slot> inventorySlots = new ArrayList<Slot>(container.inventorySlots.size() - 2);

		for (int i = 2; i < container.inventorySlots.size(); i++) {
			inventorySlots.add(container.getSlot(i));
		}

		return inventorySlots;
	}
}
