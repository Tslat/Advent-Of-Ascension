package net.tslat.aoa3.integration.jei.recipe.imbuing;

import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.transfer.IRecipeTransferInfo;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.Slot;
import net.tslat.aoa3.common.container.InfusionTableContainer;
import net.tslat.aoa3.content.recipe.InfusionRecipe;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("removal")
public class ImbuingRecipeTransferInfo implements IRecipeTransferInfo<InfusionTableContainer, InfusionRecipe> {
	@Override
	public Class<InfusionTableContainer> getContainerClass() {
		return InfusionTableContainer.class;
	}

	@Deprecated
	@Override
	public Class<InfusionRecipe> getRecipeClass() {
		return InfusionRecipe.class;
	}

	@Deprecated
	@Override
	public ResourceLocation getRecipeCategoryUid() {
		return ImbuingRecipeCategory.ID;
	}

	@Override
	public RecipeType<InfusionRecipe> getRecipeType() {
		return ImbuingRecipeCategory.RECIPE_TYPE;
	}

	@Override
	public boolean canHandle(InfusionTableContainer container, InfusionRecipe recipe) {
		return recipe.isEnchanting();
	}

	@Override
	public List<Slot> getRecipeSlots(InfusionTableContainer container, InfusionRecipe recipe) {
		List<Slot> slots = new ArrayList<>(10);

		for (int i = 1; i <= 11; i++) {
			slots.add(container.getSlot(i));
		}

		return slots;
	}

	@Override
	public List<Slot> getInventorySlots(InfusionTableContainer container, InfusionRecipe recipe) {
		List<Slot> inventorySlots = new ArrayList<Slot>(container.slots.size() - 11);

		for (int i = 12; i < container.slots.size(); i++) {
			inventorySlots.add(container.getSlot(i));
		}

		return inventorySlots;
	}
}
