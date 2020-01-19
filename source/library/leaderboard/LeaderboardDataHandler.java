package net.tslat.aoa3.library.leaderboard;

import net.minecraftforge.fml.common.FMLCommonHandler;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ConfigurationUtil;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.Level;

import javax.annotation.Nullable;
import java.io.*;
import java.util.HashMap;

public class LeaderboardDataHandler {
	private static File dataDir;
	private static final HashMap<Enums.Skills, Leaderboard> leaderboards = new HashMap<Enums.Skills, Leaderboard>(Enums.Skills.values().length + 1);

	protected static void init() {
		if (!ConfigurationUtil.MainConfig.leaderboardEnabled)
			return;

		File worldDir = new File(FMLCommonHandler.instance().getSavesDirectory(), FMLCommonHandler.instance().getMinecraftServerInstance().getFolderName());
		File dataDir = new File(worldDir, "data");
		LeaderboardDataHandler.dataDir = new File(dataDir, "aoaleaderboards");

		if (!LeaderboardDataHandler.dataDir.exists())
			LeaderboardDataHandler.dataDir.mkdirs();

		loadLeaderboard(null);

		for (Enums.Skills skill : Enums.Skills.values()) {
			loadLeaderboard(skill);
		}
	}

	public static Leaderboard getLeaderboard(@Nullable Enums.Skills skill) {
		return leaderboards.get(skill);
	}

	private static void loadLeaderboard(@Nullable Enums.Skills skill) {
		File skillFile = new File(dataDir, (skill == null ? "totals" : skill.toString().toLowerCase()) + ".aoa3");
		Leaderboard leaderboard;

		if (!skillFile.exists()) {
			try {
				skillFile.createNewFile();
				leaderboards.put(skill, new Leaderboard(skill));
			}
			catch (IOException e) {
				AdventOfAscension.logMessage(Level.ERROR, "No file access to leaderboard files in " + dataDir.getAbsolutePath() + ", disabling leaderboards");
				e.printStackTrace();
				AoALeaderboard.kill();
			}

			return;
		}

		FileInputStream inputStream = null;
		ObjectInputStream objectStream = null;

		try {
			inputStream = new FileInputStream(skillFile);
			objectStream = new ObjectInputStream(inputStream);

			Object obj = objectStream.readObject();

			if (obj instanceof Leaderboard) {
				leaderboard = (Leaderboard)obj;
			}
			else {
				throw new ClassNotFoundException();
			}
		}
		catch (FileNotFoundException e) {
			AdventOfAscension.logMessage(Level.ERROR,"Unable to load leaderboard from file: I can't find the file!");
			e.printStackTrace();

			return;
		}
		catch (IOException e) {
			AdventOfAscension.logMessage(Level.ERROR, "Unable to load leaderboard from file: I can't access the file!");
			e.printStackTrace();

			return;
		}
		catch (ClassNotFoundException e) {
			AdventOfAscension.logMessage(Level.ERROR, "Unable to load leaderboard from file: The file appears to be tampered with. Please don't do that.");
			e.printStackTrace();

			return;
		}
		finally {
			if (objectStream != null)
				IOUtils.closeQuietly(objectStream);

			if (inputStream != null)
				IOUtils.closeQuietly(inputStream);
		}

		leaderboards.put(skill, leaderboard);
	}

	protected static void saveAllLeaderboards() {
		for (Enums.Skills skill : leaderboards.keySet()) {
			saveLeaderboard(skill);
		}
	}

	protected static void saveLeaderboard(@Nullable Enums.Skills skill) {
		File skillFile = new File(dataDir, (skill == null ? "totals" : skill.toString()) + ".aoa3");
		Leaderboard leaderboard = leaderboards.get(skill);

		if (leaderboard == null)
			return;

		if (!skillFile.exists()) {
			try {
				skillFile.createNewFile();
			}
			catch (IOException e) {
				AdventOfAscension.logMessage(Level.ERROR, "No file access to leaderboard files in " + dataDir.getAbsolutePath() + ", disabling leaderboards");
				e.printStackTrace();
			}
		}

		FileOutputStream fos = null;
		ObjectOutputStream oos = null;

		try {
			fos = new FileOutputStream(skillFile);
			oos = new ObjectOutputStream(fos);

			oos.writeObject(leaderboard);
			oos.close();
		}
		catch (FileNotFoundException e) {
			AdventOfAscension.logMessage(Level.ERROR, "Unable to save leaderboard to file: I can't create the file!");
			e.printStackTrace();

			return;
		}
		catch (IOException e) {
			AdventOfAscension.logMessage(Level.ERROR,"Unable to save leaderboard to file: Failure to write to file");
			e.printStackTrace();

			return;
		}
		finally {
			if (oos != null)
				IOUtils.closeQuietly(oos);

			if (fos != null)
				IOUtils.closeQuietly(fos);
		}
	}
}
