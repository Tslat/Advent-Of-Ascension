package net.tslat.aoa3.integration;

import net.minecraftforge.fml.ModList;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.common.registration.AoAConfigs;
import net.tslat.aoa3.integration.patchouli.PatchouliIntegration;
import net.tslat.aoa3.integration.tes.TESIntegration;

public class IntegrationManager {
	static boolean jeiActive = false;
	static boolean tesActive = false;
	static boolean patchouliActive = false;

	public static boolean isJEIActive() {
		return jeiActive && AoAConfigs.INTEGRATIONS.jeiIntegrationEnabled.get();
	}

	public static boolean isPatchouliActive() {
		return patchouliActive && AoAConfigs.INTEGRATIONS.patchouliEnabled.get();
	}

	public static boolean isTESActive() {
		return tesActive && AoAConfigs.INTEGRATIONS.tesEnabled.get();
	}

	public static void init() {
		Logging.logStatusMessage("Checking for third-party integrations");

		if (isModPresent("jei"))
			jeiPreInit();

		if (isModPresent("patchouli"))
			patchouliPreInit();

		if (isModPresent("tslatentitystatus"))
			tesPreInit();
	}

	public static void lateInit() {}

	public static void clientInit() {
		if (isTESActive())
			TESIntegration.clientInit();
	}

	private static void jeiPreInit() {
		Logging.logStatusMessage("Found JEI, integrating");

		jeiActive = true;
	}

	private static void tesPreInit() {
		Logging.logStatusMessage("Found TES, integrating");

		tesActive = true;
	}

	private static void patchouliPreInit() {
		Logging.logStatusMessage("Found Patchouli, Integrating");

		patchouliActive = true;
		PatchouliIntegration.preInit();
	}

	public static boolean isModPresent(String modId) {
		return ModList.get().isLoaded(modId);
	}
}
