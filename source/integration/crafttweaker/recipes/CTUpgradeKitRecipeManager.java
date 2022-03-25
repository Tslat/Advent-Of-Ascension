package net.tslat.aoa3.integration.crafttweaker.recipes;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.managers.IRecipeManager;
import com.blamejared.crafttweaker.impl.actions.recipes.ActionAddRecipe;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.content.recipe.UpgradeKitRecipe;
import net.tslat.aoa3.common.registration.AoARecipes;
import org.openzen.zencode.java.ZenCodeType;

@Document("aoa3/api/UpgradeKitRecipe")
@ZenCodeType.Name("mods.aoa3.UpgradeKitRecipe")
@ZenRegister
public class CTUpgradeKitRecipeManager implements IRecipeManager {
	@ZenCodeType.Method
	public void addUpgradeKitRecipe(String id, IItemStack input, IItemStack upgradeKit, IItemStack output) {
		UpgradeKitRecipe recipe = new UpgradeKitRecipe(new ResourceLocation("crafttweaker", id), "", input.getInternal(), upgradeKit.getInternal(), output.getInternal());

		CraftTweakerAPI.apply(new ActionAddRecipe(this, recipe));
	}

	@Override
	public IRecipeType<UpgradeKitRecipe> getRecipeType() {
		return AoARecipes.UPGRADE_KIT.getA();
	}
}
