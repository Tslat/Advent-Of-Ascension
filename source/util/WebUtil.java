package net.tslat.aoa3.util;

import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.VersionChecker;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.library.misc.AoAHalos;
import net.tslat.aoa3.library.scheduling.AoAScheduler;
import net.tslat.aoa3.library.scheduling.async.UpdateHalosMapTask;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.Level;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public abstract class WebUtil {
	private static boolean isUpdateAvailable = false;
	private static String latestVersion = AdventOfAscension.VERSION;

	public static void doHTTPTasks() {
		Logging.logMessage(Level.DEBUG, "Starting web tasks");
		VersionChecker.CheckResult updateCheckResult = VersionChecker.getResult(ModList.get().getModFileById(AdventOfAscension.MOD_ID).getMods().get(0));

		if (updateCheckResult.status == VersionChecker.Status.OUTDATED) {
			isUpdateAvailable = true;
			latestVersion = updateCheckResult.target.toString();
		}
	}

	public static HashMap<UUID, AoAHalos.PlayerHaloContainer> fillHalosMap(HashMap<UUID, AoAHalos.PlayerHaloContainer> halosMap) {
		Logging.logMessage(Level.DEBUG, "Updating player halos map");

		BufferedReader fileReader = null;

		try {
			HttpURLConnection connection = (HttpURLConnection)new URL("https://gist.githubusercontent.com/Tslat/2c2eb98dceeff18f05ed068982fb71a7/raw/").openConnection();

			connection.setConnectTimeout(1000);
			connection.connect();

			if (HttpURLConnection.HTTP_OK != connection.getResponseCode()) {
				Logging.logMessage(Level.DEBUG, "Failed connection to cloud based halos map, response code " + connection.getResponseMessage());

				return halosMap;
			}

			fileReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;

			if (halosMap != null) {
				while ((line = fileReader.readLine()) != null) {
					if (!line.startsWith(" <!DOCTYPE")) {
						String[] lineSplit = line.split("\\|");
						UUID uuid;

						if (lineSplit.length > 2) {
							HashSet<AoAHalos.Type> halosSet = new HashSet<AoAHalos.Type>(lineSplit.length - 1);

							try {
								uuid = UUID.fromString(lineSplit[1]);
							}
							catch (IllegalArgumentException ex) {
								if (AoAConfig.CLIENT.doVerboseDebugging.get())
									Logging.logMessage(Level.WARN, "Invalid UUID format from web: " + lineSplit[1]);

								continue;
							}

							for (int i = 2; i < lineSplit.length; i++) {
								try {
									halosSet.add(AoAHalos.Type.valueOf(lineSplit[i]));
								} catch (IllegalArgumentException ex) {
									if (AoAConfig.CLIENT.doVerboseDebugging.get())
										Logging.logMessage(Level.WARN, "Invalid halo type from web: " + lineSplit[i]);
								}
							}

							halosMap.put(uuid, new AoAHalos.PlayerHaloContainer(halosSet));
							Logging.logMessage(Level.DEBUG, "Found player halo for " + uuid.toString());
						}
					}
				}

				HashSet<AoAHalos.Type> haloSet = new HashSet<AoAHalos.Type>(1);

				haloSet.add(AoAHalos.Type.Tslat);
				halosMap.put(UUID.fromString("2459b511-ca45-43d8-808d-f0eb30a63be4"), new AoAHalos.PlayerHaloContainer(haloSet));
			}

			connection.disconnect();
		}
		catch (Exception e) {
			Logging.logMessage(Level.WARN, "Error while performing HTTP Tasks, dropping.");

			if (AoAConfig.CLIENT.doVerboseDebugging.get())
				e.printStackTrace();
		}
		finally {
			IOUtils.closeQuietly(fileReader);
		}

		return halosMap;
	}


	public static void extraPlayerHalosFromWeb() {
		HashMap<UUID, AoAHalos.PlayerHaloContainer> newMap = AoAHalos.getHaloMapForPrefill();

		if (newMap != null) {
			fillHalosMap(newMap);
			AoAScheduler.scheduleAsyncTask(new UpdateHalosMapTask(), 60, TimeUnit.MINUTES);
		}
	}

	public static boolean isUpdateAvailable() {
		return isUpdateAvailable;
	}

	public static String getLatestVersion() {
		return latestVersion;
	}
}
