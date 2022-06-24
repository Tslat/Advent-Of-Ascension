package net.tslat.aoa3.integration;

import net.minecraftforge.fml.loading.FMLLoader;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.integration.patchouli.PatchouliIntegration;

public class IntegrationManager {
	static boolean jeiActive = false;
	static boolean jerActive = false;
	static boolean immersiveEngineeringActive = false;
	static boolean patchouliActive = false;
	static boolean tinkersConstructActive = false;

	public static boolean isJEIActive() {
		return jeiActive && AoAConfig.INTEGRATIONS.jeiIntegrationEnabled.get();
	}

	public static boolean isJERActive() {
		return jerActive && AoAConfig.INTEGRATIONS.jerIntegrationEnabled.get();
	}

	public static boolean isImmersiveEngineeringActive() {
		return immersiveEngineeringActive && AoAConfig.INTEGRATIONS.immersiveEngineeringEnabled.get();
	}

	public static boolean isPatchouliActive() {
		return patchouliActive && AoAConfig.INTEGRATIONS.patchouliEnabled.get();
	}

	public static boolean isTinkersConstructActive() {
		return tinkersConstructActive && AoAConfig.INTEGRATIONS.tinkersConstructEnabled.get();
	}

	public static void preInit() {
		Logging.logStatusMessage("Checking for third-party integrations");

		if (isModPresent("jei"))
			jeiPreInit();

		if (isModPresent("jeresources"))
			jerPreInit();

		if (isModPresent("immersiveengineering"))
			immersiveEngineeringPreInit();

		if (isModPresent("patchouli"))
			patchouliPreInit();

		/*if (isModPresent("tconstruct"))
			tinkersConstructPreInit();*/

		//TinkersFluids.preInit();
	}

	public static void postInit() {
		/*if (isJERActive())
			JERIntegration.postInit();

		if (isImmersiveEngineeringActive())
			ImmersiveEngineeringIntegration.postInit();*/
	}

	private static void jeiPreInit() {
		Logging.logStatusMessage("Found JEI, integrating");

		jeiActive = true;
	}

	private static void jerPreInit() {
		Logging.logStatusMessage("Found JustEnoughResources, integrating");

		jerActive = true;
	}

	private static void immersiveEngineeringPreInit() {
		Logging.logStatusMessage("Found Immersive Engineering, integrating");

		immersiveEngineeringActive = true;
	}

	private static void patchouliPreInit() {
		Logging.logStatusMessage("Found Patchouli, Integrating");

		patchouliActive = true;
		PatchouliIntegration.preInit();
	}

	private static void tinkersConstructPreInit() {
		Logging.logStatusMessage("Found Tinkers Construct, Integrating");

		tinkersConstructActive = true;
		//TinkersConstructIntegration.preInit();
	}

	public static boolean isModPresent(String modId) {
		return FMLLoader.getLoadingModList().getModFileById(modId) != null;
	}
}
