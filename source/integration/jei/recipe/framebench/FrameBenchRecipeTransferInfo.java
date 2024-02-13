package net.tslat.aoa3.integration.jei.recipe.framebench;

import net.tslat.aoa3.common.menu.FrameBenchMenu;
import net.tslat.aoa3.common.registration.AoAMenus;
import net.tslat.aoa3.content.recipe.FrameBenchRecipe;
import net.tslat.aoa3.integration.jei.recipe.ExtensibleMenuRecipeTransferInfo;

public class FrameBenchRecipeTransferInfo extends ExtensibleMenuRecipeTransferInfo<FrameBenchMenu, FrameBenchRecipe> {
	public FrameBenchRecipeTransferInfo() {
		super(FrameBenchMenu.class, AoAMenus.FRAME_BENCH.get(), FrameBenchRecipeCategory.RECIPE_TYPE);
	}
}
