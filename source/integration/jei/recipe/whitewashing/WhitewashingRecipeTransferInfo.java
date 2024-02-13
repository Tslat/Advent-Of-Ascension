package net.tslat.aoa3.integration.jei.recipe.whitewashing;

import net.tslat.aoa3.common.menu.WhitewashingTableMenu;
import net.tslat.aoa3.common.registration.AoAMenus;
import net.tslat.aoa3.content.recipe.WhitewashingRecipe;
import net.tslat.aoa3.integration.jei.recipe.ExtensibleMenuRecipeTransferInfo;

public class WhitewashingRecipeTransferInfo extends ExtensibleMenuRecipeTransferInfo<WhitewashingTableMenu, WhitewashingRecipe> {
	public WhitewashingRecipeTransferInfo() {
		super(WhitewashingTableMenu.class, AoAMenus.WHITEWASHING_TABLE.get(), WhitewashingRecipeCategory.RECIPE_TYPE);
	}
}
