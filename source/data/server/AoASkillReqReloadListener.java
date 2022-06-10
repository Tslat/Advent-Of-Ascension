package net.tslat.aoa3.data.server;

import com.google.gson.*;
import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.SkillRequirementDataPacket;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.player.PlayerDataManager;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.PlayerUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class AoASkillReqReloadListener extends SimpleJsonResourceReloadListener {
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	private static final String folder = "player/skill_reqs";

	private static final HashMap<ResourceLocation, SkillReqHandler> REQUIREMENTS_MAP = new HashMap<>();
	private static Map<ResourceLocation, Map<String, List<Pair<ResourceLocation, Integer>>>> requirementsData = new HashMap<>();

	public AoASkillReqReloadListener() {
		super(GSON, folder);
	}

	public static boolean canEquip(PlayerDataManager plData, Item item, boolean notifyPlayer) {
		SkillReqHandler handler = getRequirements(ForgeRegistries.ITEMS.getKey(item));

		if (handler != null && !handler.canEquip(plData)) {
			if (notifyPlayer && !plData.player().level.isClientSide())
				handler.notifyPlayerCantEquip((ServerPlayer)plData.player());

			return false;
		}

		return true;
	}

	public static boolean canPlaceBlock(PlayerDataManager plData, Block block, boolean notifyPlayer) {
		SkillReqHandler handler = getRequirements(ForgeRegistries.BLOCKS.getKey(block));

		if (handler != null && !handler.canPlaceBlock(plData)) {
			if (notifyPlayer && !plData.player().level.isClientSide())
				handler.notifyPlayerCantPlaceBlock((ServerPlayer)plData.player());

			return false;
		}

		return true;
	}

	public static boolean canBreakBlock(PlayerDataManager plData, Block block, boolean notifyPlayer) {
		SkillReqHandler handler = getRequirements(ForgeRegistries.BLOCKS.getKey(block));

		if (handler != null && !handler.canBreakBlock(plData)) {
			if (notifyPlayer && !plData.player().level.isClientSide())
				handler.notifyPlayerCantBreakBlock((ServerPlayer)plData.player());

			return false;
		}

		return true;
	}

	public static boolean canInteractWith(PlayerDataManager plData, Block block, boolean notifyPlayer) {
		SkillReqHandler handler = getRequirements(ForgeRegistries.BLOCKS.getKey(block));

		if (handler != null && !handler.canInteractWith(plData)) {
			if (notifyPlayer && !plData.player().level.isClientSide())
				handler.notifyPlayerCantInteract((ServerPlayer)plData.player());

			return false;
		}

		return true;
	}

	@Nullable
	public static SkillReqHandler getRequirements(ResourceLocation id) {
		return REQUIREMENTS_MAP.get(id);
	}

	@Override
	protected void apply(Map<ResourceLocation, JsonElement> jsonMap, ResourceManager resourceManager, ProfilerFiller profiler) {
		REQUIREMENTS_MAP.clear();

		parseAll(prepData(jsonMap));
	}

	public static void parseAll(Map<ResourceLocation, Map<String, List<Pair<ResourceLocation, Integer>>>> restrictions) {
		requirementsData = new HashMap<>(restrictions);

		for (Map.Entry<ResourceLocation, Map<String, List<Pair<ResourceLocation, Integer>>>> entry : requirementsData.entrySet()) {
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

	private static Pair<Predicate<PlayerDataManager>, Consumer<ServerPlayer>> parseRequirements(List<Pair<ResourceLocation, Integer>> reqs) {
		Predicate<PlayerDataManager> predicate = plData -> true;
		Consumer<ServerPlayer> notificationHandler = plData -> {};

		for (Pair<ResourceLocation, Integer> pair : reqs) {
			AoASkill skill = AoASkills.getSkill(pair.getFirst());
			int level = pair.getSecond();

			if (level <= 0 || level > 1000)
				throw new IllegalArgumentException("Invalid skill level requirement: " + level + ", must be 1-1000");

			if (skill == null)
				throw new IllegalArgumentException("Unknown skill: '" + pair.getFirst() + "' for item skill entry.");

			predicate = predicate.and(plData -> plData.getSkill(skill).hasLevel(level));
			notificationHandler = notificationHandler.andThen(player -> PlayerUtil.notifyPlayerOfInsufficientLevel(player, skill, level));
		}

		return Pair.of(predicate, notificationHandler);
	}

	private Map<ResourceLocation, Map<String, List<Pair<ResourceLocation, Integer>>>> prepData(Map<ResourceLocation, JsonElement> jsonData) {
		HashMap<ResourceLocation, Map<String, List<Pair<ResourceLocation, Integer>>>> idToReqsMap = new HashMap<>(jsonData.size());

		for (Map.Entry<ResourceLocation, JsonElement> jsonFile : jsonData.entrySet()) {
			List<ResourceLocation> targetIds;
			JsonObject json = jsonFile.getValue().getAsJsonObject();
			JsonElement target = json.get("target");

			if (target.isJsonPrimitive()) {
				targetIds = List.of(new ResourceLocation(target.getAsString()));
			}
			else if (target.isJsonArray()) {
				JsonArray array = target.getAsJsonArray();
				targetIds = new ArrayList<>();

				for (JsonElement element : array) {
					targetIds.add(new ResourceLocation(element.getAsString()));
				}
			}
			else {
				throw new IllegalArgumentException("Unknown entry type for 'target' in AoA Skill Req json: " + jsonFile.getKey());
			}

			Map<String, List<Pair<ResourceLocation, Integer>>> reqMap = new HashMap<>();

			for (Map.Entry<String, JsonElement> reqEntry : json.entrySet()) {
				if (reqEntry.getKey().equals("target"))
					continue;

				List<Pair<ResourceLocation, Integer>> reqList = new ArrayList<>();
				JsonElement element = reqEntry.getValue();

				if (element.isJsonObject()) {
					JsonObject reqEntryObj = element.getAsJsonObject();

					reqList.add(Pair.of(new ResourceLocation(GsonHelper.getAsString(reqEntryObj, "skill")), GsonHelper.getAsInt(reqEntryObj, "level")));
				}
				else if (element.isJsonArray() && element.getAsJsonArray().size() > 0) {
					for (JsonElement ele2 : element.getAsJsonArray()) {
						JsonObject reqEntryObj = ele2.getAsJsonObject();

						reqList.add(Pair.of(new ResourceLocation(GsonHelper.getAsString(reqEntryObj, "skill")), GsonHelper.getAsInt(reqEntryObj, "level")));
					}
				}

				reqMap.put(reqEntry.getKey(), reqList);
			}

			for (ResourceLocation id : targetIds) {
				mergeOrAddEntry(id, reqMap, idToReqsMap);
			}
		}

		return idToReqsMap;
	}

	private static void mergeOrAddEntry(ResourceLocation id, Map<String, List<Pair<ResourceLocation, Integer>>> newReqs, Map<ResourceLocation, Map<String, List<Pair<ResourceLocation, Integer>>>> reqMap) {
		Map<String, List<Pair<ResourceLocation, Integer>>> existingReqsMap = reqMap.putIfAbsent(id, newReqs);

		if (existingReqsMap == null)
			return;

		for (Map.Entry<String, List<Pair<ResourceLocation, Integer>>> newReqEntry : newReqs.entrySet()) {
			String reqType = newReqEntry.getKey();
			List<Pair<ResourceLocation, Integer>> newReqsList = newReqEntry.getValue();
			List<Pair<ResourceLocation, Integer>> existingReqsList = existingReqsMap.putIfAbsent(reqType, newReqsList);

			if (existingReqsList != null) {
				for (Pair<ResourceLocation, Integer> newReq : newReqsList) {
					int index = -1;

					for (int i = 0; i < existingReqsList.size(); i++) {
						Pair<ResourceLocation, Integer> oldReq = existingReqsList.get(i);

						if (oldReq.getFirst().equals(newReq.getFirst())) {
							if (oldReq.getSecond() < newReq.getSecond())
								index = i;

							break;
						}
					}

					if (index >= 0)
						existingReqsList.set(index, newReq);
				}
			}
		}
	}

	public static void setRequirements(ResourceLocation id, SkillReqHandler handler) {
		REQUIREMENTS_MAP.put(id, handler);
	}

	public static void addRequirements(ResourceLocation id, Map<String, List<Pair<ResourceLocation, Integer>>> data) {
		mergeOrAddEntry(id, data, requirementsData);
		setRequirements(id, parse(getParsedReqDataFor(id)));
	}

	public static Map<ResourceLocation, Map<String, List<Pair<ResourceLocation, Integer>>>> getParsedReqData() {
		return requirementsData;
	}

	@Nonnull
	public static Map<String, List<Pair<ResourceLocation, Integer>>> getParsedReqDataFor(ResourceLocation itemId) {
		if (!requirementsData.containsKey(itemId))
			return new HashMap<>(0);

		return requirementsData.get(itemId);
	}

	public static void syncNewPlayer(ServerPlayer player) {
		AoAPackets.messagePlayer(player, new SkillRequirementDataPacket(requirementsData));
	}

	public static class SkillReqHandler {
		@Nullable
		private Pair<Predicate<PlayerDataManager>, Consumer<ServerPlayer>> equipPredicate;
		@Nullable
		private Pair<Predicate<PlayerDataManager>, Consumer<ServerPlayer>> blockPlacePredicate;
		@Nullable
		private Pair<Predicate<PlayerDataManager>, Consumer<ServerPlayer>> blockBreakPredicate;
		@Nullable
		private Pair<Predicate<PlayerDataManager>, Consumer<ServerPlayer>> interactionPredicate;

		private void forEquipping(Pair<Predicate<PlayerDataManager>, Consumer<ServerPlayer>> equipPredicate) {
			this.equipPredicate = equipPredicate;
		}

		private void forPlacingBlocks(Pair<Predicate<PlayerDataManager>, Consumer<ServerPlayer>> blockPlacePredicate) {
			this.blockPlacePredicate = blockPlacePredicate;
		}

		private void forBreakingBlocks(Pair<Predicate<PlayerDataManager>, Consumer<ServerPlayer>> blockBreakPredicate) {
			this.blockBreakPredicate = blockBreakPredicate;
		}

		private void forInteracting(Pair<Predicate<PlayerDataManager>, Consumer<ServerPlayer>> interactionPredicate) {
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

		public void notifyPlayerCantEquip(ServerPlayer player) {
			if (handlingEquip())
				equipPredicate.getSecond().accept(player);
		}

		public boolean canPlaceBlock(PlayerDataManager plData) {
			return !handlingBlockPlacement() || blockPlacePredicate.getFirst().test(plData);
		}

		public void notifyPlayerCantPlaceBlock(ServerPlayer player) {
			if (handlingBlockPlacement())
				blockPlacePredicate.getSecond().accept(player);
		}

		public boolean canBreakBlock(PlayerDataManager plData) {
			return !handlingBlockBreak() || blockBreakPredicate.getFirst().test(plData);
		}

		public void notifyPlayerCantBreakBlock(ServerPlayer player) {
			if (handlingBlockBreak())
				blockBreakPredicate.getSecond().accept(player);
		}

		public boolean canInteractWith(PlayerDataManager plData) {
			return !handlingInteraction() || interactionPredicate.getFirst().test(plData);
		}

		public void notifyPlayerCantInteract(ServerPlayer player) {
			if (handlingInteraction())
				interactionPredicate.getSecond().accept(player);
		}

		public boolean isValid() {
			return handlingEquip() || handlingBlockPlacement() || handlingBlockBreak() || handlingInteraction();
		}
	}
}
