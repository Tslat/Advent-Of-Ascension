package net.tslat.aoa3.util;

import net.neoforged.fml.ModList;
import net.neoforged.fml.VersionChecker;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.advent.Logging;
import org.apache.logging.log4j.Level;

public final class WebUtil {
	private static boolean isUpdateAvailable = false;
	private static String latestVersion = AdventOfAscension.VERSION;

	public static void doHTTPTasks() {
		Logging.logMessage(Level.DEBUG, "Starting web tasks");
		VersionChecker.CheckResult updateCheckResult = VersionChecker.getResult(ModList.get().getModFileById(AdventOfAscension.MOD_ID).getMods().get(0));

		if (updateCheckResult.status() == VersionChecker.Status.OUTDATED) {
			isUpdateAvailable = true;
			latestVersion = updateCheckResult.target().toString();
		}
	}

	public static boolean isUpdateAvailable() {
		return isUpdateAvailable;
	}

	public static String getLatestVersion() {
		return latestVersion;
	}
}
