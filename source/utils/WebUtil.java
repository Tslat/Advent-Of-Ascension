package net.tslat.aoa3.utils;

import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.common.Loader;
import net.tslat.aoa3.advent.AdventOfAscension;
import org.apache.logging.log4j.Level;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;

public class WebUtil {
	private static boolean isUpdateAvailable = false;
	private static String latestVersion = AdventOfAscension.version;
	public static final HashSet<UUID> crazyDonators = new HashSet<UUID>();
	private static final ArrayList<String> donators = new ArrayList<String>();

	public static void doHTTPTasks(boolean isClient) {
		ForgeVersion.CheckResult updateCheckResult = ForgeVersion.getResult(Loader.instance().activeModContainer());

		if (updateCheckResult.status != ForgeVersion.Status.FAILED) {
			HttpURLConnection connection;

			if (updateCheckResult.status == ForgeVersion.Status.OUTDATED) {
				isUpdateAvailable = true;
				latestVersion = updateCheckResult.target.toString();
			}

			try {
				if (isClient) {
					connection = (HttpURLConnection)new URL("http://tslat.net/Hosting/Tslat-AoA/donators.txt").openConnection();

					connection.setConnectTimeout(1000);
					connection.connect();

					if (HttpURLConnection.HTTP_OK != connection.getResponseCode()) {
						AdventOfAscension.getLogger().log(Level.WARN, "Access to the donators file was rejected, skipping");
					}
					else {
						BufferedReader donatorsFile = new BufferedReader(new InputStreamReader(connection.getInputStream()));
						String line;

						while ((line = donatorsFile.readLine()) != null) {
							donators.add(line);
						}

						donatorsFile.close();
					}

					connection.disconnect();
				}

				connection = (HttpURLConnection) new URL("http://tslat.net/Hosting/Tslat-AoA/high_donators.txt").openConnection();

				connection.setConnectTimeout(1000);
				connection.connect();

				if (HttpURLConnection.HTTP_OK != connection.getResponseCode()) {
					AdventOfAscension.getLogger().log(Level.WARN, "access to the high donators file was rejected, skipping");
				}
				else {
					BufferedReader highDonatorsFile = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					String line;

					while ((line = highDonatorsFile.readLine()) != null) {
						if (!line.startsWith(" <!DOCTYPE"))
							crazyDonators.add(UUID.fromString(line));
					}

					highDonatorsFile.close();
				}

				connection.disconnect();
			}
			catch (IOException e) {
				AdventOfAscension.getLogger().log(Level.WARN, "Unable to access web server for HTTP tasks. I'm going to have to skip them.");
			}
		}
	}

	public static boolean isUpdateAvailable() {
		return isUpdateAvailable;
	}

	public static String getLatestVersion() {
		return latestVersion;
	}

	public static String getRandomDonator() {
		if (!donators.isEmpty())
			return donators.get(AdventOfAscension.rand.nextInt(donators.size()));

		return null;
	}
}
