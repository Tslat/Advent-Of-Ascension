package net.tslat.aoa3.integration;

import net.minecraftforge.fml.loading.FMLLoader;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.integration.immersiveengineering.ImmersiveEngineeringIntegration;
import net.tslat.aoa3.integration.jer.JERIntegration;

public class IntegrationManager {
	static boolean jeiActive = false;
	static boolean jerActive = false;
	static boolean immersiveEngineeringActive = false;

	public static boolean isJEIActive() {
		return jeiActive;
	}

	public static boolean isJERActive() {
		return jerActive;
	}

	public static boolean isImmersiveEngineeringActive() {
		return immersiveEngineeringActive;
	}

	public static void preInit() {
		Logging.logStatusMessage("Checking for third-party integrations");

		if (isModPresent("jei") && AoAConfig.INTEGRATIONS.jeiIntegrationEnabled.get())
			jeiPreInit();

		if (isModPresent("jeresources") && AoAConfig.INTEGRATIONS.jerIntegrationEnabled.get())
			jerPreInit();

		if (isModPresent("immersiveengineering") && AoAConfig.INTEGRATIONS.immersiveEngineeringEnabled.get())
			immersiveEngineeringPreInit();
	}

	public static void init() {
		if (isJERActive())
			JERIntegration.init();

		if (isImmersiveEngineeringActive())
			ImmersiveEngineeringIntegration.init();
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

	public static boolean isModPresent(String modId) {
		return FMLLoader.getLoadingModList().getModFileById(modId) != null;
	}
}
