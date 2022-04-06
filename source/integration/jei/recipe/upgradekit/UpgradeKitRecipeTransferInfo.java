package net.tslat.aoa3.integration.jei.recipe.upgradekit;

import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.transfer.IRecipeTransferInfo;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.Slot;
import net.tslat.aoa3.common.container.DivineStationContainer;
import net.tslat.aoa3.content.recipe.UpgradeKitRecipe;

import java.util.ArrayList;
import java.util.List;

public class UpgradeKitRecipeTransferInfo implements IRecipeTransferInfo<DivineStationContainer, UpgradeKitRecipe> {
	@Override
	public Class<DivineStationContainer> getContainerClass() {
		return DivineStationContainer.class;
	}

	@Deprecated
	@Override
	public Class<UpgradeKitRecipe> getRecipeClass() {
		return UpgradeKitRecipe.class;
	}

	@Deprecated
	@Override
	public ResourceLocation getRecipeCategoryUid() {
		return UpgradeKitRecipeCategory.ID;
	}

	@Override
	public RecipeType<UpgradeKitRecipe> getRecipeType() {
		return UpgradeKitRecipeCategory.RECIPE_TYPE;
	}

	@Override
	public boolean canHandle(DivineStationContainer container, UpgradeKitRecipe recipe) {
		return true;
	}

	@Override
	public List<Slot> getRecipeSlots(DivineStationContainer container, UpgradeKitRecipe recipe) {
		List<Slot> slots = new ArrayList<>(2);

		for (int i = 1; i <= 2; i++) {
			slots.add(container.getSlot(i));
		}

		return slots;
	}

	@Override
	public List<Slot> getInventorySlots(DivineStationContainer container, UpgradeKitRecipe recipe) {
		List<Slot> inventorySlots = new ArrayList<Slot>(container.slots.size() - 3);

		for (int i = 3; i < container.slots.size(); i++) {
			inventorySlots.add(container.getSlot(i));
		}

		return inventorySlots;
	}
}
