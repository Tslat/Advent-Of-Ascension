package net.tslat.aoa3.integration.jei.recipe.infusion;

import net.tslat.aoa3.common.menu.InfusionTableMenu;
import net.tslat.aoa3.common.registration.AoAMenus;
import net.tslat.aoa3.content.recipe.InfusionRecipe;
import net.tslat.aoa3.integration.jei.recipe.ExtensibleMenuRecipeTransferInfo;

public class InfusionRecipeTransferInfo extends ExtensibleMenuRecipeTransferInfo<InfusionTableMenu, InfusionRecipe> {
	public InfusionRecipeTransferInfo() {
		super(InfusionTableMenu.class, AoAMenus.INFUSION_TABLE.get(), InfusionRecipeCategory.RECIPE_TYPE);
	}
}
