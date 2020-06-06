package net.tslat.aoa3.hooks.crafttweaker;

import crafttweaker.CraftTweakerAPI;
import net.tslat.aoa3.hooks.crafttweaker.recipes.CTInfusionTable;
import net.tslat.aoa3.hooks.crafttweaker.recipes.CTUpgradeKit;
import net.tslat.aoa3.hooks.crafttweaker.util.CTPlayer;
import net.tslat.aoa3.hooks.crafttweaker.util.CTPlayerData;

public class CraftTweakerCompat {
	public static void preInit() {
		CraftTweakerAPI.registerClass(CTInfusionTable.class);
		CraftTweakerAPI.registerClass(CTUpgradeKit.class);
		CraftTweakerAPI.registerClass(CTPlayer.class);
		CraftTweakerAPI.registerClass(CTPlayerData.class);
	}
}
