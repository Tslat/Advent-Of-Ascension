package net.tslat.aoa3.common.handlers;

import net.minecraft.entity.player.EntityPlayerMP;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.packet.PacketPlayerCrownInfo;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.PacketUtil;

import javax.annotation.Nullable;
import java.util.*;

public class PlayerCrownHandler {
	private static final HashMap<UUID, PlayerCrownContainer> playerCrowns = new HashMap<UUID, PlayerCrownContainer>();
	private static final HashSet<UUID> renderCheckedPlayers = new HashSet<UUID>();

	public static void syncWithNewClient(EntityPlayerMP player) {
		AdventOfAscension.logOptionalMessage("Syncing player crowns with new player: " + player.getUniqueID().toString());
		PacketUtil.network.sendTo(new PacketPlayerCrownInfo(playerCrowns), player);
	}

	public static boolean testForNewRenderer(UUID uuid) {
		return playerCrowns.containsKey(uuid) && renderCheckedPlayers.add(uuid);
	}

	public static void syncNewCrownChoice(UUID uuid, Enums.PlayerCrownTypes crown) {
		if (playerCrowns.containsKey(uuid) && crown != playerCrowns.get(uuid).getPreferredCrown()) {
			crown = testAndCorrectCrownChoice(uuid, crown);

			if (crown != null) {
				playerCrowns.get(uuid).setPreferredCrown(crown);
				PacketUtil.network.sendToAll(new PacketPlayerCrownInfo(uuid, crown));
			}
		}
	}

	public static boolean isDonator(UUID uuid) {
		return checkPlayerCrown(uuid, Enums.PlayerCrownTypes.Donator);
	}

	public static boolean isSuperDonator(UUID uuid) {
		return checkPlayerCrown(uuid, Enums.PlayerCrownTypes.Super_Donator);
	}

	public static boolean isCrazyDonator(UUID uuid) {
		return checkPlayerCrown(uuid, Enums.PlayerCrownTypes.Crazy_Donator);
	}

	public static boolean isWikiEditor(UUID uuid) {
		return checkPlayerCrown(uuid, Enums.PlayerCrownTypes.Wiki_Editor);
	}

	public static boolean checkPlayerCrown(UUID uuid, Enums.PlayerCrownTypes crown) {
		PlayerCrownContainer container = playerCrowns.get(uuid);

		return container != null && container.hasCrown(crown);
	}

	@Nullable
	public static HashMap<UUID, PlayerCrownContainer> getCrownMapForPrefill() {
		if (playerCrowns.isEmpty())
			return playerCrowns;

		return null;
	}

	public static void updateCrownsMap(HashMap<UUID, PlayerCrownContainer> newMap) {
		Iterator<Map.Entry<UUID, PlayerCrownContainer>> existingMapIterator = playerCrowns.entrySet().iterator();

		while (existingMapIterator.hasNext()) {
			Map.Entry<UUID, PlayerCrownContainer> entry = existingMapIterator.next();

			if (newMap.containsKey(entry.getKey())) {
				entry.setValue(newMap.get(entry.getKey()).setPreferredCrown(entry.getValue().getPreferredCrown()));
				testAndCorrectCrownChoice(entry.getKey(), entry.getValue().preferredCrown);
			}
			else {
				existingMapIterator.remove();
			}
		}
	}

	public static void setCrownChoice(UUID uuid, Enums.PlayerCrownTypes crown) {
		playerCrowns.put(uuid, playerCrowns.getOrDefault(uuid, new PlayerCrownContainer(crown)).setPreferredCrown(crown));
	}

	@Nullable
	public static Enums.PlayerCrownTypes getCrown(UUID uuid) {
		PlayerCrownContainer cont = playerCrowns.get(uuid);

		if (cont != null)
			return cont.getPreferredCrown();

		return null;
	}

	@Nullable
	private static Enums.PlayerCrownTypes testAndCorrectCrownChoice(UUID uuid, Enums.PlayerCrownTypes preferredCrown) {
		if (checkPlayerCrown(uuid, preferredCrown))
			return preferredCrown;

		switch (preferredCrown) {
			case Tslat:
			case Ursun:
				return null;
			case Wiki_Editor:
				if (isDonator(uuid)) {
					return Enums.PlayerCrownTypes.Donator;
				}
				else if (isSuperDonator(uuid)) {
					return Enums.PlayerCrownTypes.Super_Donator;
				}
				else if (isCrazyDonator(uuid)) {
					return Enums.PlayerCrownTypes.Crazy_Donator;
				}
				break;
			case Donator:
				if (isWikiEditor(uuid)) {
					return Enums.PlayerCrownTypes.Wiki_Editor;
				}
				else if (isSuperDonator(uuid)) {
					return Enums.PlayerCrownTypes.Donator;
				}
				else if (isCrazyDonator(uuid)) {
					return Enums.PlayerCrownTypes.Crazy_Donator;
				}
				break;
			case Super_Donator:
				if (isWikiEditor(uuid)) {
					return Enums.PlayerCrownTypes.Wiki_Editor;
				}
				else if (isDonator(uuid)) {
					return Enums.PlayerCrownTypes.Donator;
				}
				else if (isCrazyDonator(uuid)) {
					return Enums.PlayerCrownTypes.Crazy_Donator;
				}
				break;
			case Crazy_Donator:
				if (isWikiEditor(uuid)) {
					return Enums.PlayerCrownTypes.Wiki_Editor;
				}
				else if (isDonator(uuid)) {
					return Enums.PlayerCrownTypes.Donator;
				}
				else if (isSuperDonator(uuid)) {
					return Enums.PlayerCrownTypes.Super_Donator;
				}
				break;
		}

		return null;
	}

	public static class PlayerCrownContainer {
		private Enums.PlayerCrownTypes preferredCrown;
		private final HashSet<Enums.PlayerCrownTypes> unlockedCrowns;

		public PlayerCrownContainer(HashSet<Enums.PlayerCrownTypes> availableCrowns) {
			this.unlockedCrowns = availableCrowns;

			this.preferredCrown = availableCrowns.iterator().next();
		}

		public PlayerCrownContainer(Enums.PlayerCrownTypes preferredCrown) {
			this.preferredCrown = preferredCrown;
			this.unlockedCrowns = null;
		}

		PlayerCrownContainer setPreferredCrown(Enums.PlayerCrownTypes crown) {
			if (unlockedCrowns == null || unlockedCrowns.contains(preferredCrown))
				this.preferredCrown = crown;

			return this;
		}

		public Enums.PlayerCrownTypes getPreferredCrown() {
			return this.preferredCrown;
		}

		public boolean hasCrown(Enums.PlayerCrownTypes crown) {
			return unlockedCrowns.contains(crown);
		}
	}
}
