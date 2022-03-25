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
import net.tslat.aoa3.util.PlayerUtil;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class AoASkillReqReloadListener extends JsonReloadListener {
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	private static final String folder = "player/skill_reqs";

	private static final HashMap<ResourceLocation, SkillReqHandler> REQUIREMENTS_MAP = new HashMap<ResourceLocation, SkillReqHandler>();
	private static Map<ResourceLocation, Map<String, List<Pair<ResourceLocation, Integer>>>> requirementsData = null;

	public AoASkillReqReloadListener() {
		super(GSON, folder);
	}

	public static boolean canEquip(PlayerDataManager plData, Item item, boolean notifyPlayer) {
		SkillReqHandler handler = getRequirements(item.getRegistryName());

		if (handler != null && !handler.canEquip(plData)) {
			if (notifyPlayer && !plData.player().level.isClientSide())
				handler.notifyPlayerCantEquip((ServerPlayerEntity)plData.player());

			return false;
		}

		return true;
	}

	public static boolean canPlaceBlock(PlayerDataManager plData, Block block, boolean notifyPlayer) {
		SkillReqHandler handler = getRequirements(block.getRegistryName());

		if (handler != null && !handler.canPlaceBlock(plData)) {
			if (notifyPlayer && !plData.player().level.isClientSide())
				handler.notifyPlayerCantPlaceBlock((ServerPlayerEntity)plData.player());

			return false;
		}

		return true;
	}

	public static boolean canBreakBlock(PlayerDataManager plData, Block block, boolean notifyPlayer) {
		SkillReqHandler handler = getRequirements(block.getRegistryName());

		if (handler != null && !handler.canBreakBlock(plData)) {
			if (notifyPlayer && !plData.player().level.isClientSide())
				handler.notifyPlayerCantBreakBlock((ServerPlayerEntity)plData.player());

			return false;
		}

		return true;
	}

	public static boolean canInteractWith(PlayerDataManager plData, Block block, boolean notifyPlayer) {
		SkillReqHandler handler = getRequirements(block.getRegistryName());

		if (handler != null && !handler.canInteractWith(plData)) {
			if (notifyPlayer && !plData.player().level.isClientSide())
				handler.notifyPlayerCantInteract((ServerPlayerEntity)plData.player());

			return false;
		}

		return true;
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

	private static Pair<Predicate<PlayerDataManager>, Consumer<ServerPlayerEntity>> parseRequirements(List<Pair<ResourceLocation, Integer>> reqs) {
		Predicate<PlayerDataManager> predicate = plData -> true;
		Consumer<ServerPlayerEntity> notificationHandler = plData -> {};

		for (Pair<ResourceLocation, Integer> pair : reqs) {
			AoASkill skill = AoASkills.getSkill(pair.getFirst());

			if (skill == null)
				throw new IllegalArgumentException("Unknown skill: '" + pair.getFirst() + "' for item skill entry.");

			predicate = predicate.and(plData -> plData.getSkill(skill).hasLevel(pair.getSecond()));
			notificationHandler = notificationHandler.andThen(player -> PlayerUtil.notifyPlayerOfInsufficientLevel(player, skill, pair.getSecond()));
		}

		return Pair.of(predicate, notificationHandler);
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
		if (requirementsData == null)
			return null;

		return requirementsData.get(itemId);
	}

	public static void syncNewPlayer(ServerPlayerEntity player) {
		AoAPackets.messagePlayer(player, new SkillRequirementDataPacket(requirementsData));
	}

	public static class SkillReqHandler {
		@Nullable
		private Pair<Predicate<PlayerDataManager>, Consumer<ServerPlayerEntity>> equipPredicate;
		@Nullable
		private Pair<Predicate<PlayerDataManager>, Consumer<ServerPlayerEntity>> blockPlacePredicate;
		@Nullable
		private Pair<Predicate<PlayerDataManager>, Consumer<ServerPlayerEntity>> blockBreakPredicate;
		@Nullable
		private Pair<Predicate<PlayerDataManager>, Consumer<ServerPlayerEntity>> interactionPredicate;

		private void forEquipping(Pair<Predicate<PlayerDataManager>, Consumer<ServerPlayerEntity>> equipPredicate) {
			this.equipPredicate = equipPredicate;
		}

		private void forPlacingBlocks(Pair<Predicate<PlayerDataManager>, Consumer<ServerPlayerEntity>> blockPlacePredicate) {
			this.blockPlacePredicate = blockPlacePredicate;
		}

		private void forBreakingBlocks(Pair<Predicate<PlayerDataManager>, Consumer<ServerPlayerEntity>> blockBreakPredicate) {
			this.blockBreakPredicate = blockBreakPredicate;
		}

		private void forInteracting(Pair<Predicate<PlayerDataManager>, Consumer<ServerPlayerEntity>> interactionPredicate) {
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
			return !handlingEquip() || equipPredicate.getFirst().test(plData);
		}

		public void notifyPlayerCantEquip(ServerPlayerEntity player) {
			if (handlingEquip())
				equipPredicate.getSecond().accept(player);
		}

		public boolean canPlaceBlock(PlayerDataManager plData) {
			return !handlingBlockPlacement() || blockPlacePredicate.getFirst().test(plData);
		}

		public void notifyPlayerCantPlaceBlock(ServerPlayerEntity player) {
			if (handlingBlockPlacement())
				blockPlacePredicate.getSecond().accept(player);
		}

		public boolean canBreakBlock(PlayerDataManager plData) {
			return !handlingBlockBreak() || blockBreakPredicate.getFirst().test(plData);
		}

		public void notifyPlayerCantBreakBlock(ServerPlayerEntity player) {
			if (handlingBlockBreak())
				blockBreakPredicate.getSecond().accept(player);
		}

		public boolean canInteractWith(PlayerDataManager plData) {
			return !handlingInteraction() || interactionPredicate.getFirst().test(plData);
		}

		public void notifyPlayerCantInteract(ServerPlayerEntity player) {
			if (handlingInteraction())
				interactionPredicate.getSecond().accept(player);
		}

		public boolean isValid() {
			return handlingEquip() || handlingBlockPlacement() || handlingBlockBreak() || handlingInteraction();
		}
	}
}
