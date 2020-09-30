package net.tslat.aoa3.utils;

import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.common.Loader;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.handlers.PlayerHaloHandler;
import net.tslat.aoa3.library.Enums;
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

public class WebUtil {
	private static boolean isUpdateAvailable = false;
	private static String latestVersion = AdventOfAscension.version;

	public static void doHTTPTasks() {
		AdventOfAscension.logOptionalMessage("Starting web tasks");
		ForgeVersion.CheckResult updateCheckResult = ForgeVersion.getResult(Loader.instance().activeModContainer());

		if (updateCheckResult.status == ForgeVersion.Status.OUTDATED) {
			isUpdateAvailable = true;
			latestVersion = updateCheckResult.target.toString();
		}
	}

	public static HashMap<UUID, PlayerHaloHandler.PlayerHaloContainer> fillHalosMap(HashMap<UUID, PlayerHaloHandler.PlayerHaloContainer> halosMap) {
		AdventOfAscension.logOptionalMessage("Updating player halos map");

		BufferedReader fileReader = null;

		try {
			HttpURLConnection connection = (HttpURLConnection)new URL("https://gist.githubusercontent.com/Tslat/2c2eb98dceeff18f05ed068982fb71a7/raw/").openConnection();

			connection.setConnectTimeout(1000);
			connection.connect();

			if (HttpURLConnection.HTTP_OK != connection.getResponseCode()) {
				AdventOfAscension.logOptionalMessage("Failed connection to cloud based halos map, response code " + connection.getResponseMessage());

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
							HashSet<Enums.PlayerHaloTypes> halosSet = new HashSet<Enums.PlayerHaloTypes>(lineSplit.length - 1);

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
									halosSet.add(Enums.PlayerHaloTypes.valueOf(lineSplit[i]));
								} catch (IllegalArgumentException ex) {
									if (ConfigurationUtil.MainConfig.doVerboseDebugging)
										AdventOfAscension.logMessage(Level.WARN, "Invalid halo type from web: " + lineSplit[i]);
								}
							}

							halosMap.put(uuid, new PlayerHaloHandler.PlayerHaloContainer(halosSet));
							AdventOfAscension.logOptionalMessage("Found player halo for " + uuid.toString());
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

		return halosMap;
	}


	public static void extraPlayerHalosFromWeb() {
		HashMap<UUID, PlayerHaloHandler.PlayerHaloContainer> newMap = PlayerHaloHandler.getHaloMapForPrefill();

		if (newMap != null) {
			fillHalosMap(newMap);
			ModUtil.scheduleAsyncTask(new UpdateHalosMapTask(), 60, TimeUnit.MINUTES);
		}
	}

	public static boolean isUpdateAvailable() {
		return isUpdateAvailable;
	}

	public static String getLatestVersion() {
		return latestVersion;
	}
}
