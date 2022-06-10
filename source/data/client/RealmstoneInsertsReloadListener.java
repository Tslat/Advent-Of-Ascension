package net.tslat.aoa3.data.client;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraft.util.GsonHelper;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.client.gui.realmstone.BlankRealmstoneScreen;
import org.apache.logging.log4j.Level;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class RealmstoneInsertsReloadListener implements ResourceManagerReloadListener {
	public static final HashMap<String, BlankRealmstoneScreen.RealmstoneWorldInsert> INSERTS = new HashMap<String, BlankRealmstoneScreen.RealmstoneWorldInsert>();
	private static final Gson GSON = new Gson();

	public RealmstoneInsertsReloadListener() {}
	
	@Override
	public void onResourceManagerReload(ResourceManager resourceManager) {
		INSERTS.clear();

		for (Map.Entry<ResourceLocation, Resource> entry : resourceManager.listResources("realmstonegui", path -> path.getPath().endsWith(".json")).entrySet()) {
			try (Reader reader = new BufferedReader(new InputStreamReader(entry.getValue().open(), StandardCharsets.UTF_8))) {
				JsonObject json = GsonHelper.fromJson(GSON, reader, JsonObject.class);

				if (json != null) {
					BlankRealmstoneScreen.RealmstoneWorldInsert insert = BlankRealmstoneScreen.RealmstoneWorldInsert.fromJson(json);

					INSERTS.put(insert.getId(), insert);
				}
				else {
					Logging.logMessage(Level.ERROR, "Realmstone insert json " + entry.getKey().toString() + " appears to be empty or null.");
				}
			}
			catch (RuntimeException | IOException ex) {
				Logging.logMessage(Level.ERROR, "Unable to deserialize realmstone insert json " + entry + ", skipping.", ex);
			}
		}

		for (BlankRealmstoneScreen.RealmstoneWorldInsert insert : INSERTS.values()) {
			for (String id : insert.getParents()) {
				if (!INSERTS.containsKey(id))
					throw new IllegalArgumentException("Invalid parent node: " + id + " for realmstone insert " + insert.getId());
			}
		}
	}
}
