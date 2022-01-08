package net.tslat.aoa3.data.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import net.minecraft.block.Block;
import net.minecraft.client.resources.JsonReloadListener;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.profiler.IProfiler;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.SkillRequirementDataPacket;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.player.PlayerDataManager;
import net.tslat.aoa3.player.skill.AoASkill;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class AoASkillReqReloadListener extends JsonReloadListener {
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	private static final String folder = "player/skill_reqs";

	private static final HashMap<ResourceLocation, SkillReqHandler> REQUIREMENTS_MAP = new HashMap<ResourceLocation, SkillReqHandler>();
	private static Map<ResourceLocation, Map<String, List<Pair<ResourceLocation, Integer>>>> requirementsData = null;

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

		parseAll(requirementsData = prepData(jsonMap));
	}

	public static void parseAll(Map<ResourceLocation, Map<String, List<Pair<ResourceLocation, Integer>>>> restrictions) {
		for (Map.Entry<ResourceLocation, Map<String, List<Pair<ResourceLocation, Integer>>>> entry : restrictions.entrySet()) {
			SkillReqHandler handler = parse(entry.getValue());

			if (handler.isValid()) {
				REQUIREMENTS_MAP.put(entry.getKey(), handler);
			}
			else {
				REQUIREMENTS_MAP.remove(entry.getKey());
			}
		}
	}

	public static SkillReqHandler parse(Map<String, List<Pair<ResourceLocation, Integer>>> reqData) {
		SkillReqHandler handler = new SkillReqHandler();

		if (reqData.containsKey("equip"))
			handler.forEquipping(parseRequirements(reqData.get("equip")));

		if (reqData.containsKey("place_block"))
			handler.forPlacingBlocks(parseRequirements(reqData.get("place_block")));

		if (reqData.containsKey("break_block"))
			handler.forBreakingBlocks(parseRequirements(reqData.get("break_block")));

		if (reqData.containsKey("interact_with"))
			handler.forInteracting(parseRequirements(reqData.get("interact_with")));

		return handler;
	}

	private static Predicate<PlayerDataManager> parseRequirements(List<Pair<ResourceLocation, Integer>> reqs) {
		Predicate<PlayerDataManager> predicate = plData -> true;

		for (Pair<ResourceLocation, Integer> pair : reqs) {
			AoASkill skill = AoASkills.getSkill(pair.getFirst());

			if (skill == null)
				throw new IllegalArgumentException("Unknown skill: '" + pair.getFirst() + "' for item skill entry.");

			predicate = predicate.and(plData -> plData.getSkill(skill).hasLevel(pair.getSecond()));
		}

		return predicate;
	}

	private Map<ResourceLocation, Map<String, List<Pair<ResourceLocation, Integer>>>> prepData(Map<ResourceLocation, JsonElement> jsonData) {
		HashMap<ResourceLocation, Map<String, List<Pair<ResourceLocation, Integer>>>> bufferDataMap = new HashMap<ResourceLocation, Map<String, List<Pair<ResourceLocation, Integer>>>>(jsonData.size());

		for (Map.Entry<ResourceLocation, JsonElement> entry : jsonData.entrySet()) {
			Map<String, List<Pair<ResourceLocation, Integer>>> entryMap = bufferDataMap.computeIfAbsent(entry.getKey(), key -> new HashMap<String, List<Pair<ResourceLocation, Integer>>>());

			for (Map.Entry<String, JsonElement> reqEntry : entry.getValue().getAsJsonObject().entrySet()) {
				List<Pair<ResourceLocation, Integer>> reqList = entryMap.computeIfAbsent(reqEntry.getKey(), key -> new ArrayList<Pair<ResourceLocation, Integer>>());
				JsonElement element = reqEntry.getValue();

				if (element.isJsonObject()) {
					JsonObject reqEntryObj = element.getAsJsonObject();

					reqList.add(Pair.of(new ResourceLocation(JSONUtils.getAsString(reqEntryObj, "skill")), JSONUtils.getAsInt(reqEntryObj, "level")));
				}
				else if (element.isJsonArray() && element.getAsJsonArray().size() > 0) {
					for (JsonElement ele2 : element.getAsJsonArray()) {
						JsonObject reqEntryObj = ele2.getAsJsonObject();

						reqList.add(Pair.of(new ResourceLocation(JSONUtils.getAsString(reqEntryObj, "skill")), JSONUtils.getAsInt(reqEntryObj, "level")));
					}
				}
			}
		}

		return bufferDataMap;
	}

	public static void addRequirement(ResourceLocation id, SkillReqHandler handler) {
		REQUIREMENTS_MAP.put(id, handler);
	}

	public static void addParsedData(ResourceLocation id, Map<String, List<Pair<ResourceLocation, Integer>>> data) {
		requirementsData.put(id, data);
	}

	public static Map<ResourceLocation, Map<String, List<Pair<ResourceLocation, Integer>>>> getParsedReqData() {
		return requirementsData;
	}

	@Nullable
	public static Map<String, List<Pair<ResourceLocation, Integer>>> getParsedReqDataFor(ResourceLocation itemId) {
		return requirementsData.get(itemId);
	}

	public static void syncNewPlayer(ServerPlayerEntity player) {
		AoAPackets.messagePlayer(player, new SkillRequirementDataPacket(requirementsData));
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
