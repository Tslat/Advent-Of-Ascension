package net.tslat.aoa3.library.leaderboard;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ConfigurationUtil;

import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Leaderboard implements Serializable {
	private final ArrayList<LeaderboardEntry> leaderboardArray = new ArrayList<LeaderboardEntry>(ConfigurationUtil.leaderboardSize);
	private final HashMap<UUID, Integer> indexMap = new HashMap<UUID, Integer>(ConfigurationUtil.leaderboardSize);

	private int lowestIndex = 0;
	@Nullable
	private Enums.Skills skill;

	public Leaderboard(@Nullable Enums.Skills skill) {
		this.skill = skill;
	}

	@Nullable
	protected int getPlayerRank(EntityPlayer pl) {
		return indexMap.get(pl.getUniqueID());
	}

	protected void addOrUpdatePlayer(EntityPlayer pl, int level) {
		UUID uuid = pl.getUniqueID();

		if (indexMap.containsKey(uuid)) {
			int index = indexMap.get(uuid);
			LeaderboardEntry entry = leaderboardArray.get(index).update(pl, level);

			if (leaderboardArray.size() > 1) {
				leaderboardArray.remove(index);

				for (int i = index; i >= 0; i--) {
					if (leaderboardArray.get(i).getLvl() >= level) {
						leaderboardArray.add(i + 1, entry);
						indexMap.put(uuid, i + 1);

						break;
					}
				}
			}
		}
		else {
			int i;

			if (leaderboardArray.size() == 0) {
				leaderboardArray.add(0, new LeaderboardEntry(pl, level));
				indexMap.put(uuid, 0);

			}
			else {
				for (i = lowestIndex; i >= 0; i--) {
					LeaderboardEntry entry = leaderboardArray.get(i);

					if (entry != null && entry.getLvl() >= level) {
						if (i + 1 < ConfigurationUtil.leaderboardSize) {
							if (lowestIndex == ConfigurationUtil.leaderboardSize - 1) {
								indexMap.remove(leaderboardArray.get(lowestIndex).getUuid());
								leaderboardArray.remove(lowestIndex);
							}

							leaderboardArray.add(i + 1, entry.update(pl, level));
							indexMap.put(uuid, i + 1);
						}

						return;
					}
				}

				if (i == -1) {
					leaderboardArray.add(0, new LeaderboardEntry(pl, level));
					indexMap.put(uuid, 0);
				}
			}
		}
	}

	protected void removePlayer(EntityPlayer pl) {
		UUID uuid = pl.getUniqueID();

		if (indexMap.containsKey(uuid)) {
			LeaderboardEntry entry = leaderboardArray.get(indexMap.get(uuid));

			leaderboardArray.remove(entry);
			indexMap.remove(uuid);
		}
	}

	protected LeaderboardDataPackage getTopTen() {
		return getSurroundingRankEntries(null);
	}

	protected LeaderboardDataPackage getSurroundingRankEntries(@Nullable EntityPlayer pl) {
		int firstRank = (pl == null ? 4 : Math.max(4, indexMap.containsKey(pl.getUniqueID()) ? indexMap.get(pl.getUniqueID()) : lowestIndex - 5) - 4);
		ArrayList<TrimmedLeaderboardEntry> resultArray = new ArrayList<TrimmedLeaderboardEntry>(10);

		for (int i = firstRank; i <= firstRank + 10 && i < leaderboardArray.size(); i++) {
			LeaderboardEntry entry = leaderboardArray.get(i);

			if (entry == null)
				break;

			resultArray.add(new TrimmedLeaderboardEntry(entry));
		}

		return new LeaderboardDataPackage(skill, firstRank, resultArray);
	}

	/*protected ArrayList<NBTTagCompound> getTopTen() {
		return getSurroundingRankEntries(null);
	}

	protected ArrayList<NBTTagCompound> getSurroundingRankEntries(@Nullable EntityPlayer pl) {
		int firstRank = (pl == null ? 4 : Math.max(4, indexMap.containsKey(pl.getUniqueID()) ? indexMap.get(pl.getUniqueID()) : lowestIndex - 5) - 4);
		ArrayList<NBTTagCompound> resultArray = new ArrayList<NBTTagCompound>();

		for (int i = firstRank; i <= firstRank + 10 && i < leaderboardArray.size(); i++) {
			LeaderboardEntry entry = leaderboardArray.get(i);

			if (entry == null)
				break;

			resultArray.add(entry.asNBTTag(firstRank));
		}

		return resultArray;
	}*/

	public static class LeaderboardDataPackage {
		@Nullable
		public final Enums.Skills skill;
		public final int firstRank;
		public final ArrayList<TrimmedLeaderboardEntry> entries;

		private LeaderboardDataPackage(@Nullable Enums.Skills skill, int topRank, ArrayList<TrimmedLeaderboardEntry> entries) {
			this.skill = skill;
			this.firstRank = topRank;
			this.entries = entries;
		}
	}

	public static class TrimmedLeaderboardEntry implements Serializable {
		public final String name;
		public final int lvl;

		private TrimmedLeaderboardEntry(LeaderboardEntry entry) {
			this.name = entry.getName();
			this.lvl = entry.getLvl();
		}
	}

	public static class LeaderboardEntry implements Serializable {
		private final UUID uuid;
		private String name;
		private int lvl;

		private LeaderboardEntry(EntityPlayer pl, int level) {
			this.uuid = pl.getUniqueID();
			this.name = pl.getName();
			this.lvl = level;
		}

		public NBTTagCompound asNBTTag(int rank) {
			NBTTagCompound tag = new NBTTagCompound();

			tag.setInteger("Rank", rank);
			tag.setString("Name", name);
			tag.setInteger("Lvl", lvl);

			return tag;
		}

		public UUID getUuid() {
			return uuid;
		}

		public String getName() {
			return name;
		}

		public int getLvl() {
			return lvl;
		}

		private LeaderboardEntry update(EntityPlayer pl, int level) {
			this.lvl = level;

			if (!this.name.equals(pl.getName()))
				this.name = pl.getName();

			return this;
		}
	}
}
