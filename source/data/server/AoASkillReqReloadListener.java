package net.tslat.aoa3.data.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.block.Block;
import net.minecraft.client.resources.JsonReloadListener;
import net.minecraft.item.Item;
import net.minecraft.profiler.IProfiler;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.ResourceLocationException;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.player.PlayerDataManager;
import net.tslat.aoa3.player.skill.AoASkill;
import org.apache.logging.log4j.Level;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class AoASkillReqReloadListener extends JsonReloadListener {
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	private static final String folder = "player/skill_reqs";

	private static final HashMap<ResourceLocation, SkillReqHandler> REQUIREMENTS_MAP = new HashMap<ResourceLocation, SkillReqHandler>();

	public AoASkillReqReloadListener() {
		super(GSON, folder);
	}

	public static boolean canEquip(PlayerDataManager plData, Item item) {
		SkillReqHandler handler = getRequirements(item.getRegistryName());

		return handler == null || handler.canEquip(plData);
	}

	public static boolean canPlaceBlock(PlayerDataManager plData, Block block) {
		SkillReqHandler handler = getRequirements(block.getRegistryName());

		return handler == null || handler.canPlaceBlock(plData);
	}

	public static boolean canBreakBlock(PlayerDataManager plData, Block block) {
		SkillReqHandler handler = getRequirements(block.getRegistryName());

		return handler == null || handler.canBreakBlock(plData);
	}

	public static boolean canInteractWith(PlayerDataManager plData, Block block) {
		SkillReqHandler handler = getRequirements(block.getRegistryName());

		return handler == null || handler.canInteractWith(plData);
	}

	@Nullable
	public static SkillReqHandler getRequirements(ResourceLocation id) {
		return REQUIREMENTS_MAP.get(id);
	}

	@Override
	protected void apply(Map<ResourceLocation, JsonElement> jsonMap, IResourceManager resourceManager, IProfiler profiler) {
		REQUIREMENTS_MAP.clear();

		for (Map.Entry<ResourceLocation, JsonElement> entry : jsonMap.entrySet()) {
			ResourceLocation itemId = entry.getKey();
			SkillReqHandler handler = parse(entry.getValue().getAsJsonObject());

			if (handler.isValid()) {
				REQUIREMENTS_MAP.put(itemId, handler);
			}
			else {
				REQUIREMENTS_MAP.remove(itemId);
			}
		}
	}

	public static void addRequirement(ResourceLocation id, SkillReqHandler handler) {
		REQUIREMENTS_MAP.put(id, handler);
	}

	public static SkillReqHandler parse(JsonObject data) {
		SkillReqHandler handler = new SkillReqHandler();

		if (data.has("equip"))
			handler.forEquipping(parseRequirements(data.get("equip")));

		if (data.has("place_block"))
			handler.forPlacingBlocks(parseRequirements(data.get("place_block")));

		if (data.has("break_block"))
			handler.forBreakingBlocks(parseRequirements(data.get("break_block")));

		if (data.has("interact_with"))
			handler.forInteracting(parseRequirements(data.get("interact_with")));

		return handler;
	}

	private static Predicate<PlayerDataManager> parseRequirements(JsonElement data) throws IllegalArgumentException, IllegalStateException, ResourceLocationException  {
		if (data.isJsonObject()) {
			JsonObject skillObject = data.getAsJsonObject();

			if (!skillObject.has("skill"))
				throw new IllegalStateException("Missing associated skill for item skill entry.");

			if (!skillObject.has("level"))
				throw new IllegalStateException("Missing associated level for item skill entry.");

			ResourceLocation skillId = new ResourceLocation(skillObject.get("skill").getAsString());
			AoASkill skill = AoASkills.getSkill(skillId);
			int levelReq = skillObject.get("level").getAsInt();

			if (skill == null)
				throw new IllegalArgumentException("Unknown skill: '" + skillId + "' for item skill entry.");

			return plData -> plData.getSkill(skill).hasLevel(levelReq);
		}
		else if (data.isJsonArray() && data.getAsJsonArray().size() > 0) {
			Predicate<PlayerDataManager> predicate = plData -> true;

			for (JsonElement element : data.getAsJsonArray()) {
				try {
					predicate = predicate.and(parseRequirements(element.getAsJsonObject()));
				}
				catch (Exception ex) {
					Logging.logMessage(Level.ERROR, "Unable to parse skill requirement section for skill requirement handler.", ex);
				}
			}

			return predicate;
		}
		else {
			throw new IllegalArgumentException("Invalid JSON object type provided for skill requirement handler.");
		}
	}

	public static class SkillReqHandler {
		@Nullable
		private Predicate<PlayerDataManager> equipPredicate;
		@Nullable
		private Predicate<PlayerDataManager> blockPlacePredicate;
		@Nullable
		private Predicate<PlayerDataManager> blockBreakPredicate;
		@Nullable
		private Predicate<PlayerDataManager> interactionPredicate;

		private void forEquipping(Predicate<PlayerDataManager> equipPredicate) {
			this.equipPredicate = equipPredicate;
		}

		private void forPlacingBlocks(Predicate<PlayerDataManager> blockPlacePredicate) {
			this.blockPlacePredicate = blockPlacePredicate;
		}

		private void forBreakingBlocks(Predicate<PlayerDataManager> blockBreakPredicate) {
			this.blockBreakPredicate = blockBreakPredicate;
		}

		private void forInteracting(Predicate<PlayerDataManager> interactionPredicate) {
			this.interactionPredicate = interactionPredicate;
		}

		public boolean handlingEquip() {
			return equipPredicate != null;
		}

		public boolean handlingBlockPlacement() {
			return blockPlacePredicate != null;
		}

		public boolean handlingBlockBreak() {
			return blockBreakPredicate != null;
		}

		public boolean handlingInteraction() {
			return interactionPredicate != null;
		}

		public boolean canEquip(PlayerDataManager plData) {
			return handlingEquip() && equipPredicate.test(plData);
		}

		public boolean canPlaceBlock(PlayerDataManager plData) {
			return handlingBlockPlacement() && blockPlacePredicate.test(plData);
		}

		public boolean canBreakBlock(PlayerDataManager plData) {
			return handlingBlockBreak() && blockBreakPredicate.test(plData);
		}

		public boolean canInteractWith(PlayerDataManager plData) {
			return handlingInteraction() && interactionPredicate.test(plData);
		}

		public boolean isValid() {
			return handlingEquip() || handlingBlockPlacement() || handlingBlockBreak() || handlingInteraction();
		}
	}
}
