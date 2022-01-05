package net.tslat.aoa3.data.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.client.resources.JsonReloadListener;
import net.minecraft.profiler.IProfiler;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.player.skill.AoASkill;
import org.apache.logging.log4j.Level;

import java.util.HashMap;
import java.util.Map;

public class AoASkillsReloadListener extends JsonReloadListener {
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	private static final String folder = "player/skills";

	private static final HashMap<AoASkill, JsonObject> SKILLS_DATA = new HashMap<AoASkill, JsonObject>();

	public AoASkillsReloadListener() {
		super(GSON, folder);
	}

	public static void populateSkillMap(ServerPlayerDataManager plData, HashMap<AoASkill, AoASkill.Instance> skillMap) {
		skillMap.clear();

		for (Map.Entry<AoASkill, JsonObject> skill : SKILLS_DATA.entrySet()) {
			skillMap.put(skill.getKey(), skill.getKey().buildDefaultInstance(plData, skill.getValue()));
		}
	}

	@Override
	protected void apply(Map<ResourceLocation, JsonElement> jsonMap, IResourceManager resourceManager, IProfiler profiler) {
		SKILLS_DATA.clear();

		for (Map.Entry<ResourceLocation, JsonElement> entry : jsonMap.entrySet()) {
			ResourceLocation skillId = entry.getKey();
			JsonElement json = entry.getValue();
			AoASkill skill = AoASkills.getSkill(skillId);

			if (skill == null) {
				Logging.logMessage(Level.WARN, "Unable to find registered skill: '" + skillId.toString() + "' from datapack entry.");

				continue;
			}

			if (!json.isJsonObject()) {
				Logging.logMessage(Level.ERROR, "Invalidly formatted skill json '" + skillId.toString() + "' from datapack entry.");

				continue;
			}

			SKILLS_DATA.put(skill, json.getAsJsonObject());
		}
	}
}
