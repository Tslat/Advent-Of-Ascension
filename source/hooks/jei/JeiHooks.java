package net.tslat.aoa3.hooks.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;

@JEIPlugin
public class JeiHooks implements IModPlugin {

	@Override
	public void register(IModRegistry registry) {
		IIngredientBlacklist blacklist = registry.getJeiHelpers().getIngredientBlacklist();

		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.lampIro.getOffLamp()));
		blacklist.addIngredientToBlacklist(new ItemStack(ItemRegister.animaStone));
		blacklist.addIngredientToBlacklist(new ItemStack(ItemRegister.heartStone));
		blacklist.addIngredientToBlacklist(new ItemStack(WeaponRegister.gunShoeFlinger));
		blacklist.addIngredientToBlacklist(new ItemStack(ItemRegister.oldBoot));
	}
}