package net.tslat.aoa3.integration.jei.recipe.upgradekit;

import net.tslat.aoa3.common.menu.DivineStationMenu;
import net.tslat.aoa3.common.registration.AoAMenus;
import net.tslat.aoa3.content.recipe.UpgradeKitRecipe;
import net.tslat.aoa3.integration.jei.recipe.ExtensibleMenuRecipeTransferInfo;

public class UpgradeKitRecipeTransferInfo extends ExtensibleMenuRecipeTransferInfo<DivineStationMenu, UpgradeKitRecipe> {
	public UpgradeKitRecipeTransferInfo() {
		super(DivineStationMenu.class, AoAMenus.DIVINE_STATION.get(), UpgradeKitRecipeCategory.RECIPE_TYPE);
	}
}
