package net.tslat.aoa3.integration;

import net.minecraftforge.fml.loading.FMLLoader;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.integration.immersiveengineering.ImmersiveEngineeringIntegration;
import net.tslat.aoa3.integration.jer.JERIntegration;
import net.tslat.aoa3.integration.patchouli.PatchouliIntegration;
import net.tslat.aoa3.integration.tinkersconstruct.TinkersConstructIntegration;
import net.tslat.aoa3.integration.tinkersconstruct.TinkersFluids;

public class IntegrationManager {
	static boolean jeiActive = false;
	static boolean jerActive = false;
	static boolean immersiveEngineeringActive = false;
	static boolean patchouliActive = false;
	static boolean tinkersConstructActive = false;

	public static boolean isJEIActive() {
		return jeiActive;
	}

	public static boolean isJERActive() {
		return jerActive;
	}

	public static boolean isImmersiveEngineeringActive() {
		return immersiveEngineeringActive;
	}

	public static boolean isPatchouliActive() {
		return patchouliActive;
	}

	public static boolean isTinkersConstructActive() {
		return tinkersConstructActive;
	}

	public static void preInit() {
		Logging.logStatusMessage("Checking for third-party integrations");

		if (isModPresent("jei") && AoAConfig.INTEGRATIONS.jeiIntegrationEnabled.get())
			jeiPreInit();

		if (isModPresent("jeresources") && AoAConfig.INTEGRATIONS.jerIntegrationEnabled.get())
			jerPreInit();

		if (isModPresent("immersiveengineering") && AoAConfig.INTEGRATIONS.immersiveEngineeringEnabled.get())
			immersiveEngineeringPreInit();

		if (isModPresent("patchouli") && AoAConfig.INTEGRATIONS.patchouliEnabled.get())
			patchouliPreInit();

		/*if (isModPresent("tconstruct") && AoAConfig.INTEGRATIONS.tinkersConstructEnabled.get())
			tinkersConstructPreInit();*/

		TinkersFluids.preInit();
	}

	public static void postInit() {
		if (isJERActive())
			JERIntegration.postInit();

		if (isImmersiveEngineeringActive())
			ImmersiveEngineeringIntegration.postInit();
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
		TinkersConstructIntegration.preInit();
	}

	public static boolean isModPresent(String modId) {
		return FMLLoader.getLoadingModList().getModFileById(modId) != null;
	}
}
