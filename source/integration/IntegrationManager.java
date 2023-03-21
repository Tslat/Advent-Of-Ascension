package net.tslat.aoa3.integration;

import net.minecraftforge.fml.ModList;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.common.registration.AoAConfigs;

public class IntegrationManager {
	static boolean jeiActive = false;
	static boolean reiActive = false;
	static boolean jerActive = false;
	static boolean immersiveEngineeringActive = false;
	static boolean patchouliActive = false;
	static boolean tinkersConstructActive = false;

	public static boolean isJEIActive() {
		return jeiActive && AoAConfigs.INTEGRATIONS.jeiIntegrationEnabled.get();
	}

	public static boolean isREIActive() {
		return reiActive && AoAConfigs.INTEGRATIONS.reiIntegrationEnabled.get();
	}

	public static boolean isJERActive() {
		return jerActive && AoAConfigs.INTEGRATIONS.jerIntegrationEnabled.get();
	}

	public static boolean isImmersiveEngineeringActive() {
		return immersiveEngineeringActive && AoAConfigs.INTEGRATIONS.immersiveEngineeringEnabled.get();
	}

	public static boolean isPatchouliActive() {
		return patchouliActive && AoAConfigs.INTEGRATIONS.patchouliEnabled.get();
	}

	public static boolean isTinkersConstructActive() {
		return tinkersConstructActive && AoAConfigs.INTEGRATIONS.tinkersConstructEnabled.get();
	}

	public static void init() {
		Logging.logStatusMessage("Checking for third-party integrations");

		if (isModPresent("jei"))
			jeiPreInit();

		if (isModPresent("rei"))
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

	public static void lateInit() {
		/*if (isJERActive())
			JERIntegration.postInit();

		if (isImmersiveEngineeringActive())
			ImmersiveEngineeringIntegration.postInit();*/
	}

	private static void jeiPreInit() {
		Logging.logStatusMessage("Found JEI, integrating");

		jeiActive = true;
	}

	private static void reiPreInit() {
		Logging.logStatusMessage("Found REI, integrating");

		reiActive = true;
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
		//PatchouliIntegration.preInit();
	}

	private static void tinkersConstructPreInit() {
		Logging.logStatusMessage("Found Tinkers Construct, Integrating");

		tinkersConstructActive = true;
		//TinkersConstructIntegration.preInit();
	}

	public static boolean isModPresent(String modId) {
		return ModList.get().isLoaded(modId);
	}
}
