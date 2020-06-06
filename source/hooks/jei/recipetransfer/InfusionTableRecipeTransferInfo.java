package net.tslat.aoa3.hooks.jei.recipetransfer;

import mezz.jei.api.recipe.transfer.IRecipeTransferInfo;
import net.minecraft.inventory.Slot;
import net.tslat.aoa3.common.containers.ContainerInfusionTable;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class InfusionTableRecipeTransferInfo implements IRecipeTransferInfo<ContainerInfusionTable> {
	private final String type;

	public InfusionTableRecipeTransferInfo(String type) {
		this.type = type;
	}

	@Nonnull
	@Override
	public Class<ContainerInfusionTable> getContainerClass() {
		return ContainerInfusionTable.class;
	}

	@Override
	public String getRecipeCategoryUid() {
		return "aoa3." + type;
	}

	@Override
	public boolean canHandle(ContainerInfusionTable containerInfusionTable) {
		return true;
	}

	@Override
	public List<Slot> getRecipeSlots(ContainerInfusionTable containerInfusionTable) {
		List<Slot> slots = new ArrayList<>(10);

		for (int i = 1; i <= 11; i++) {
			slots.add(containerInfusionTable.getSlot(i));
		}

		return slots;
	}

	@Override
	public List<Slot> getInventorySlots(ContainerInfusionTable containerInfusionTable) {
		List<Slot> inventorySlots = new ArrayList<Slot>(containerInfusionTable.inventorySlots.size() - 11);

		for (int i = 12; i < containerInfusionTable.inventorySlots.size(); i++) {
			inventorySlots.add(containerInfusionTable.getSlot(i));
		}

		return inventorySlots;
	}
}
