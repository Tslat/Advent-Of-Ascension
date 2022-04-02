package net.tslat.aoa3.integration.jei.recipe.whitewashing;

import mezz.jei.api.recipe.transfer.IRecipeTransferInfo;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.Slot;
import net.tslat.aoa3.common.container.WhitewashingTableContainer;
import net.tslat.aoa3.content.recipe.WhitewashingRecipe;
import net.tslat.aoa3.integration.jei.recipe.framebench.FrameBenchRecipeCategory;

import java.util.ArrayList;
import java.util.List;

public class WhitewashingRecipeTransferInfo implements IRecipeTransferInfo<WhitewashingTableContainer, WhitewashingRecipe> {
	@Override
	public Class<WhitewashingTableContainer> getContainerClass() {
		return WhitewashingTableContainer.class;
	}

	@Override
	public Class<WhitewashingRecipe> getRecipeClass() {
		return WhitewashingRecipe.class;
	}

	@Override
	public ResourceLocation getRecipeCategoryUid() {
		return FrameBenchRecipeCategory.ID;
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
