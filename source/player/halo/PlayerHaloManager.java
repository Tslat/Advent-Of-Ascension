package net.tslat.aoa3.player.halo;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.common.networking.AoANetworking;
import net.tslat.aoa3.common.networking.packets.SyncHaloDataPacket;
import org.apache.logging.log4j.Level;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public final class PlayerHaloManager {
    private static final Map<UUID, PlayerHaloContainer> PLAYER_HALOS = new Object2ObjectOpenHashMap<>();

    public static Map<UUID, PlayerHaloContainer> getHaloMap() {
        return ImmutableMap.copyOf(PLAYER_HALOS);
    }

    public static void updateSelectedHalo(UUID player, HaloTypes selected, boolean sync) {
        PLAYER_HALOS.computeIfAbsent(player, key -> PlayerHaloContainer.defaulted(selected)).updateSelectedHalo(player, selected, sync);
    }

    public static Optional<HaloTypes> getHaloForRender(UUID player) {
        return Optional.ofNullable(PLAYER_HALOS.get(player)).map(PlayerHaloManager::testAndCorrectHaloChoice);
    }

    @Nullable
    private static HaloTypes testAndCorrectHaloChoice(PlayerHaloContainer container) {
        final HaloTypes selected = container.getSelected();

        if (container.isEmpty())
            return null;

        if (container.hasHalo(selected))
            return selected;

        for (int i = HaloTypes.values().length - 1; i >= 0; i--) {
            final HaloTypes halo = HaloTypes.values()[i];

            if (container.hasHalo(halo))
                return halo;
        }

        return null;
    }

    public static void syncFromServer(Map<UUID, PlayerHaloContainer> containers) {
        PLAYER_HALOS.clear();
        PLAYER_HALOS.putAll(containers);
    }

    public static void updateHalosFromWeb() {
        Logging.logMessage(Level.DEBUG, "Updating player halos map");

        final Map<UUID, PlayerHaloContainer> updatedHalos = new Object2ObjectOpenHashMap<>();

        try {
            HttpURLConnection connection = (HttpURLConnection)new URL("https://gist.githubusercontent.com/Tslat/2c2eb98dceeff18f05ed068982fb71a7/raw/").openConnection();

            connection.setConnectTimeout(1000);
            connection.connect();

            if (HttpURLConnection.HTTP_OK != connection.getResponseCode()) {
                Logging.logMessage(Level.DEBUG, "Connection to cloud based halos map unavailable, response code " + connection.getResponseMessage());
            }
            else {
                try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String line;

                    while ((line = fileReader.readLine()) != null) {
                        if (!line.startsWith(" <!DOCTYPE")) {
                            final String[] lineSplit = line.split("\\|");

                            if (lineSplit.length <= 2)
                                continue;

                            try {
                                UUID uuid = UUID.fromString(lineSplit[1]);
                                EnumSet<HaloTypes> unlockedHalos = EnumSet.noneOf(HaloTypes.class);

                                for (int i = 2; i < lineSplit.length; i++) {
                                    final String type = lineSplit[i];

                                    try {
                                        unlockedHalos.add(HaloTypes.valueOf(type.toUpperCase(Locale.ROOT)));
                                    }
                                    catch (IllegalArgumentException ex) {
                                        Logging.logMessage(Level.WARN, "Invalid halo type from web: " + type);
                                    }
                                }

                                updatedHalos.put(uuid, PlayerHaloContainer.forUnlocked(unlockedHalos));
                                Logging.logMessage(Level.DEBUG, "Found player halo for " + uuid);
                            }
                            catch (IllegalArgumentException ex) {
                                Logging.logMessage(Level.WARN, "Invalid UUID format from web: " + lineSplit[1]);
                            }
                        }
                    }
                }
            }
        }
        catch (IOException ex) {
            Logging.logMessage(Level.DEBUG, "Connection to player halos map errored out, skipping", ex);
        }

        long preUpdateHash = PLAYER_HALOS.hashCode();

        for (Map.Entry<UUID, PlayerHaloContainer> existing : PLAYER_HALOS.entrySet()) {
            existing.getValue().mergeUpdatedMap(updatedHalos.getOrDefault(existing.getKey(), PlayerHaloContainer.defaulted(existing.getValue().getSelected())));
        }

        if (PLAYER_HALOS.size() < updatedHalos.size()) {
            for (Map.Entry<UUID, PlayerHaloContainer> newHalo : updatedHalos.entrySet()) {
                if (!PLAYER_HALOS.containsKey(newHalo.getKey()))
                    PLAYER_HALOS.put(newHalo.getKey(), newHalo.getValue());
            }
        }

        if (preUpdateHash != PLAYER_HALOS.hashCode())
            AoANetworking.sendToAllPlayers(new SyncHaloDataPacket(updatedHalos));
    }
}
