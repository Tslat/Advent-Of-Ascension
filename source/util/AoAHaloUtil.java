package net.tslat.aoa3.util;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.PlayerHaloDataPacket;
import org.apache.logging.log4j.Level;

import javax.annotation.Nullable;
import java.util.*;

public final class AoAHaloUtil {
	private static final HashMap<UUID, PlayerHaloContainer> playerHalos = new HashMap<UUID, PlayerHaloContainer>();
	private static final HashSet<UUID> renderCheckedPlayers = new HashSet<UUID>();

	public static void syncWithNewClient(ServerPlayerEntity player) {
		Logging.logMessage(Level.DEBUG, "Syncing player halos with new player: " + player.getUUID().toString());
		AoAPackets.messagePlayer(player, new PlayerHaloDataPacket(playerHalos));
	}

	public static boolean testForNewRenderer(UUID uuid) {
		return playerHalos.containsKey(uuid) && renderCheckedPlayers.add(uuid);
	}

	public static void syncNewHaloChoice(UUID uuid, Type halo) {
		if (playerHalos.containsKey(uuid) && halo != playerHalos.get(uuid).getPreferredHalo()) {
			halo = testAndCorrectHaloChoice(uuid, halo);

			if (halo != null) {
				playerHalos.get(uuid).setPreferredHalo(halo);
				AoAPackets.messageAllPlayers(new PlayerHaloDataPacket(uuid, halo));
			}
		}
	}

	public static boolean isDonator(UUID uuid) {
		return checkPlayerHalo(uuid, Type.Donator);
	}

	public static boolean isSuperDonator(UUID uuid) {
		return checkPlayerHalo(uuid, Type.Super_Donator);
	}

	public static boolean isCrazyDonator(UUID uuid) {
		return checkPlayerHalo(uuid, Type.Crazy_Donator);
	}

	public static boolean isWikiEditor(UUID uuid) {
		return checkPlayerHalo(uuid, Type.Wiki_Editor);
	}

	public static boolean checkPlayerHalo(UUID uuid, Type halo) {
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

	public static void setHaloChoice(UUID uuid, Type halo) {
		playerHalos.put(uuid, playerHalos.getOrDefault(uuid, new PlayerHaloContainer(halo)).setPreferredHalo(halo));
	}

	@Nullable
	public static Type getHalo(UUID uuid) {
		PlayerHaloContainer cont = playerHalos.get(uuid);

		if (cont != null)
			return cont.getPreferredHalo();

		return null;
	}

	@Nullable
	private static Type testAndCorrectHaloChoice(UUID uuid, Type preferredHalo) {
		if (checkPlayerHalo(uuid, preferredHalo))
			return preferredHalo;

		switch (preferredHalo) {
			case Tslat:
			case Staff:
				return null;
			case Wiki_Editor:
				if (isDonator(uuid)) {
					return Type.Donator;
				}
				else if (isSuperDonator(uuid)) {
					return Type.Super_Donator;
				}
				else if (isCrazyDonator(uuid)) {
					return Type.Crazy_Donator;
				}
				break;
			case Donator:
				if (isWikiEditor(uuid)) {
					return Type.Wiki_Editor;
				}
				else if (isSuperDonator(uuid)) {
					return Type.Donator;
				}
				else if (isCrazyDonator(uuid)) {
					return Type.Crazy_Donator;
				}
				break;
			case Super_Donator:
				if (isWikiEditor(uuid)) {
					return Type.Wiki_Editor;
				}
				else if (isDonator(uuid)) {
					return Type.Donator;
				}
				else if (isCrazyDonator(uuid)) {
					return Type.Crazy_Donator;
				}
				break;
			case Crazy_Donator:
				if (isWikiEditor(uuid)) {
					return Type.Wiki_Editor;
				}
				else if (isDonator(uuid)) {
					return Type.Donator;
				}
				else if (isSuperDonator(uuid)) {
					return Type.Super_Donator;
				}
				break;
		}

		return null;
	}

	public static class PlayerHaloContainer {
		private Type preferredHalo;
		private final HashSet<Type> unlockedHalos;

		public PlayerHaloContainer(HashSet<Type> availableHalos) {
			this.unlockedHalos = availableHalos;

			this.preferredHalo = availableHalos.iterator().next();
		}

		public PlayerHaloContainer(Type preferredHalo) {
			this.preferredHalo = preferredHalo;
			this.unlockedHalos = null;
		}

		PlayerHaloContainer setPreferredHalo(Type halo) {
			if (unlockedHalos == null || unlockedHalos.contains(preferredHalo))
				this.preferredHalo = halo;

			return this;
		}

		public Type getPreferredHalo() {
			return this.preferredHalo;
		}

		public boolean hasHalo(Type halo) {
			return unlockedHalos.contains(halo);
		}
	}

	public enum Type {
		Donator,
		Super_Donator,
		Crazy_Donator,
		Wiki_Editor,
		Tslat,
		Staff;

		public enum Choosable {
			Donator,
			Super_Donator,
			Wiki_Editor;

			public Type toBaseType() {
				switch (this) {
					case Donator:
						return Type.Donator;
					case Super_Donator:
						return Type.Super_Donator;
					case Wiki_Editor:
						return Type.Wiki_Editor;
					default:
						return null;
				}
			}
		}
	}
}
