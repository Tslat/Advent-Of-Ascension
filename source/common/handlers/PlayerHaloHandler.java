package net.tslat.aoa3.common.handlers;

import net.minecraft.entity.player.EntityPlayerMP;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.packet.PacketPlayerHaloInfo;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.PacketUtil;

import javax.annotation.Nullable;
import java.util.*;

public class PlayerHaloHandler {
	private static final HashMap<UUID, PlayerHaloContainer> playerHalos = new HashMap<UUID, PlayerHaloContainer>();
	private static final HashSet<UUID> renderCheckedPlayers = new HashSet<UUID>();

	public static void syncWithNewClient(EntityPlayerMP player) {
		AdventOfAscension.logOptionalMessage("Syncing player halos with new player: " + player.getUniqueID().toString());
		PacketUtil.network.sendTo(new PacketPlayerHaloInfo(playerHalos), player);
	}

	public static boolean testForNewRenderer(UUID uuid) {
		return playerHalos.containsKey(uuid) && renderCheckedPlayers.add(uuid);
	}

	public static void syncNewHaloChoice(UUID uuid, Enums.PlayerHaloTypes halo) {
		if (playerHalos.containsKey(uuid) && halo != playerHalos.get(uuid).getPreferredHalo()) {
			halo = testAndCorrectHaloChoice(uuid, halo);

			if (halo != null) {
				playerHalos.get(uuid).setPreferredHalo(halo);
				PacketUtil.network.sendToAll(new PacketPlayerHaloInfo(uuid, halo));
			}
		}
	}

	public static boolean isDonator(UUID uuid) {
		return checkPlayerHalo(uuid, Enums.PlayerHaloTypes.Donator);
	}

	public static boolean isSuperDonator(UUID uuid) {
		return checkPlayerHalo(uuid, Enums.PlayerHaloTypes.Super_Donator);
	}

	public static boolean isCrazyDonator(UUID uuid) {
		return checkPlayerHalo(uuid, Enums.PlayerHaloTypes.Crazy_Donator);
	}

	public static boolean isWikiEditor(UUID uuid) {
		return checkPlayerHalo(uuid, Enums.PlayerHaloTypes.Wiki_Editor);
	}

	public static boolean checkPlayerHalo(UUID uuid, Enums.PlayerHaloTypes halo) {
		PlayerHaloContainer container = playerHalos.get(uuid);

		return container != null && container.hasHalo(halo);
	}

	@Nullable
	public static HashMap<UUID, PlayerHaloContainer> getHaloMapForPrefill() {
		if (playerHalos.isEmpty())
			return playerHalos;

		return null;
	}

	public static void updateHalosMap(HashMap<UUID, PlayerHaloContainer> newMap) {
		Iterator<Map.Entry<UUID, PlayerHaloContainer>> existingMapIterator = playerHalos.entrySet().iterator();

		while (existingMapIterator.hasNext()) {
			Map.Entry<UUID, PlayerHaloContainer> entry = existingMapIterator.next();

			if (newMap.containsKey(entry.getKey())) {
				entry.setValue(newMap.get(entry.getKey()).setPreferredHalo(entry.getValue().getPreferredHalo()));
				testAndCorrectHaloChoice(entry.getKey(), entry.getValue().preferredHalo);
			}
			else {
				existingMapIterator.remove();
			}
		}
	}

	public static void setHaloChoice(UUID uuid, Enums.PlayerHaloTypes halo) {
		playerHalos.put(uuid, playerHalos.getOrDefault(uuid, new PlayerHaloContainer(halo)).setPreferredHalo(halo));
	}

	@Nullable
	public static Enums.PlayerHaloTypes getHalo(UUID uuid) {
		PlayerHaloContainer cont = playerHalos.get(uuid);

		if (cont != null)
			return cont.getPreferredHalo();

		return null;
	}

	@Nullable
	private static Enums.PlayerHaloTypes testAndCorrectHaloChoice(UUID uuid, Enums.PlayerHaloTypes preferredHalo) {
		if (checkPlayerHalo(uuid, preferredHalo))
			return preferredHalo;

		switch (preferredHalo) {
			case Tslat:
			case Staff:
				return null;
			case Wiki_Editor:
				if (isDonator(uuid)) {
					return Enums.PlayerHaloTypes.Donator;
				}
				else if (isSuperDonator(uuid)) {
					return Enums.PlayerHaloTypes.Super_Donator;
				}
				else if (isCrazyDonator(uuid)) {
					return Enums.PlayerHaloTypes.Crazy_Donator;
				}
				break;
			case Donator:
				if (isWikiEditor(uuid)) {
					return Enums.PlayerHaloTypes.Wiki_Editor;
				}
				else if (isSuperDonator(uuid)) {
					return Enums.PlayerHaloTypes.Donator;
				}
				else if (isCrazyDonator(uuid)) {
					return Enums.PlayerHaloTypes.Crazy_Donator;
				}
				break;
			case Super_Donator:
				if (isWikiEditor(uuid)) {
					return Enums.PlayerHaloTypes.Wiki_Editor;
				}
				else if (isDonator(uuid)) {
					return Enums.PlayerHaloTypes.Donator;
				}
				else if (isCrazyDonator(uuid)) {
					return Enums.PlayerHaloTypes.Crazy_Donator;
				}
				break;
			case Crazy_Donator:
				if (isWikiEditor(uuid)) {
					return Enums.PlayerHaloTypes.Wiki_Editor;
				}
				else if (isDonator(uuid)) {
					return Enums.PlayerHaloTypes.Donator;
				}
				else if (isSuperDonator(uuid)) {
					return Enums.PlayerHaloTypes.Super_Donator;
				}
				break;
		}

		return null;
	}

	public static class PlayerHaloContainer {
		private Enums.PlayerHaloTypes preferredHalo;
		private final HashSet<Enums.PlayerHaloTypes> unlockedHalos;

		public PlayerHaloContainer(HashSet<Enums.PlayerHaloTypes> availableHalos) {
			this.unlockedHalos = availableHalos;

			this.preferredHalo = availableHalos.iterator().next();
		}

		public PlayerHaloContainer(Enums.PlayerHaloTypes preferredHalo) {
			this.preferredHalo = preferredHalo;
			this.unlockedHalos = null;
		}

		PlayerHaloContainer setPreferredHalo(Enums.PlayerHaloTypes halo) {
			if (unlockedHalos == null || unlockedHalos.contains(preferredHalo))
				this.preferredHalo = halo;

			return this;
		}

		public Enums.PlayerHaloTypes getPreferredHalo() {
			return this.preferredHalo;
		}

		public boolean hasHalo(Enums.PlayerHaloTypes halo) {
			return unlockedHalos.contains(halo);
		}
	}
}
