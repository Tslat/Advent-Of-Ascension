package net.tslat.aoa3.integration.jei.recipe.imbuing;

import net.tslat.aoa3.common.menu.ImbuingChamberMenu;
import net.tslat.aoa3.common.registration.AoAMenus;
import net.tslat.aoa3.content.recipe.ImbuingRecipe;
import net.tslat.aoa3.integration.jei.recipe.ExtensibleMenuRecipeTransferInfo;

public class ImbuingRecipeTransferInfo extends ExtensibleMenuRecipeTransferInfo<ImbuingChamberMenu, ImbuingRecipe> {
	public ImbuingRecipeTransferInfo() {
		super(ImbuingChamberMenu.class, AoAMenus.IMBUING_CHAMBER.get(), ImbuingRecipeCategory.RECIPE_TYPE);
	}
}
