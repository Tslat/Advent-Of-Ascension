package net.nevermine.assist;

import net.nevermine.common.nevermine;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WebRequest {
	public static String webVer = "Tslat-1.1.2";

	public static boolean isUpdateAvailable() {
		try {
			BufferedReader verFile = new BufferedReader(new InputStreamReader(new URL("http://tslat.net/Hosting/Tslat-AoA/1.7.10ver.txt").openStream()));
			webVer = verFile.readLine();
			verFile.close();

			if (webVer.equals(nevermine.version))
				return false;

			return true;
		}
		catch (Exception e) {
			System.out.println("Unable to fetch 1.7.10 version file, defaulting");
			return false;
		}
	}

	public static String getRandomDonator() {
		try {
			List<String> donators = new ArrayList<String>();
			BufferedReader donatorFile = new BufferedReader(new InputStreamReader(new URL("http://tslat.net/Hosting/Tslat-AoA/donators.txt").openStream()));
			String line;

			while ((line = donatorFile.readLine()) != null) {
				donators.add(line);
			}

			if (donators.isEmpty()) {
				return null;
			}
			else {
				return donators.get(nevermine.rand.nextInt(donators.size()));
			}
		}
		catch (Exception e) {
			System.out.println("Unable to fetch donators file, defaulting");
			return null;
		}
	}
}