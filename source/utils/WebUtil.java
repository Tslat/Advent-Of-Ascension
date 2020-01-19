package net.tslat.aoa3.utils;

import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.common.Loader;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.handlers.PlayerCrownHandler;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.library.scheduling.async.UpdateCrownsMapTask;
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

public class WebUtil {
	private static boolean isUpdateAvailable = false;
	private static String latestVersion = AdventOfAscension.version;

	public static void doHTTPTasks() {
		AdventOfAscension.logOptionalMessage("Starting web tasks");
		ForgeVersion.CheckResult updateCheckResult = ForgeVersion.getResult(Loader.instance().activeModContainer());

		if (updateCheckResult.status != ForgeVersion.Status.FAILED) {
			if (updateCheckResult.status == ForgeVersion.Status.OUTDATED) {
				isUpdateAvailable = true;
				latestVersion = updateCheckResult.target.toString();
			}
		}
	}

	public static HashMap<UUID, PlayerCrownHandler.PlayerCrownContainer> fillCrownsMap(HashMap<UUID, PlayerCrownHandler.PlayerCrownContainer> crownsMap) {
		AdventOfAscension.logOptionalMessage("Updating player crowns map");

		BufferedReader fileReader = null;

		try {
			HttpURLConnection connection = (HttpURLConnection)new URL("http://tslat.net/Hosting/Tslat-AoA/player_crowns.txt").openConnection();

			connection.setConnectTimeout(1000);
			connection.connect();

			if (HttpURLConnection.HTTP_OK != connection.getResponseCode()) {
				AdventOfAscension.logOptionalMessage("Failed connection to cloud based crowns map, response code " + connection.getResponseMessage());

				return crownsMap;
			}

			fileReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;

			if (crownsMap != null) {
				while ((line = fileReader.readLine()) != null) {
					if (!line.startsWith(" <!DOCTYPE")) {
						String[] lineSplit = line.split("\\|");
						UUID uuid;

						if (lineSplit.length > 2) {
							HashSet<Enums.PlayerCrownTypes> crownsSet = new HashSet<Enums.PlayerCrownTypes>(lineSplit.length - 1);

							try {
								uuid = UUID.fromString(lineSplit[1]);
							}
							catch (IllegalArgumentException ex) {
								if (ConfigurationUtil.MainConfig.doVerboseDebugging)
									AdventOfAscension.logMessage(Level.WARN, "Invalid UUID format from web: " + lineSplit[1]);

								continue;
							}

							for (int i = 2; i < lineSplit.length; i++) {
								try {
									crownsSet.add(Enums.PlayerCrownTypes.valueOf(lineSplit[i]));
								} catch (IllegalArgumentException ex) {
									if (ConfigurationUtil.MainConfig.doVerboseDebugging)
										AdventOfAscension.logMessage(Level.WARN, "Invalid crown type from web: " + lineSplit[i]);
								}
							}

							crownsMap.put(uuid, new PlayerCrownHandler.PlayerCrownContainer(crownsSet));
							AdventOfAscension.logOptionalMessage("Found player crown for " + uuid.toString());
						}
					}
				}
			}

			connection.disconnect();
		}
		catch (Exception e) {
			AdventOfAscension.logMessage(Level.WARN, "Error while performing HTTP Tasks, dropping.");

			if (ConfigurationUtil.MainConfig.doVerboseDebugging)
				e.printStackTrace();
		}
		finally {
			IOUtils.closeQuietly(fileReader);
		}

		return crownsMap;
	}


	public static void extractPlayerCrownsFromWeb() {
		HashMap<UUID, PlayerCrownHandler.PlayerCrownContainer> newMap = PlayerCrownHandler.getCrownMapForPrefill();

		if (newMap != null) {
			fillCrownsMap(newMap);
			ModUtil.scheduleAsyncTask(new UpdateCrownsMapTask(), 30, TimeUnit.MINUTES);
		}
	}

	public static boolean isUpdateAvailable() {
		return isUpdateAvailable;
	}

	public static String getLatestVersion() {
		return latestVersion;
	}
}
