package net.tslat.aoa3.hooks;

import net.minecraftforge.fml.common.Loader;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.hooks.tconstruct.TinkersFluidRegistry;
import net.tslat.aoa3.utils.ConfigurationUtil;

public final class ThirdPartyInteractions {
	static boolean tinkersConstructActive = false;
	static boolean jeiActive = false;

	public static boolean isTinkersConstructActive() {
		return tinkersConstructActive;
	}

	public static boolean isJEIActive() {
		return jeiActive;
	}

	public static void preInit() {
		AdventOfAscension.logOptionalMessage("Checking for third-party integrations");

		if (Loader.isModLoaded("tconstruct") && ConfigurationUtil.MainConfig.thirdPartyMods.tinkersConstructActive)
			tinkersInit();
	}

	private static void tinkersInit() {
		AdventOfAscension.logOptionalMessage("Found Tinkers Construct, integrating");
		TinkersFluidRegistry.preInit();

		tinkersConstructActive = true;
	}

	public static void setJEIActive() {
		if (Loader.isModLoaded("jei"))
			jeiActive = true;
	}
}
