package net.tslat.aoa3.player;

import com.google.common.collect.ArrayListMultimap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.PlayerAdvancements;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.items.ItemHandlerHelper;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.common.packet.AoANetworking;
import net.tslat.aoa3.common.packet.packets.PlayerDataSyncPacket;
import net.tslat.aoa3.common.packet.packets.PlayerDataUpdatePacket;
import net.tslat.aoa3.common.packet.packets.ToastPopupPacket;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.common.registration.item.AoAEnchantments;
import net.tslat.aoa3.content.item.armour.AdventArmour;
import net.tslat.aoa3.content.world.teleporter.PortalCoordinatesContainer;
import net.tslat.aoa3.data.server.AoAResourcesReloadListener;
import net.tslat.aoa3.data.server.AoASkillReqReloadListener;
import net.tslat.aoa3.data.server.AoASkillsReloadListener;
import net.tslat.aoa3.event.custom.events.PlayerLevelChangeEvent;
import net.tslat.aoa3.integration.IntegrationManager;
import net.tslat.aoa3.leaderboard.SkillsLeaderboard;
import net.tslat.aoa3.leaderboard.task.LeaderboardActions;
import net.tslat.aoa3.library.object.PositionAndRotation;
import net.tslat.aoa3.player.ability.AoAAbility;
import net.tslat.aoa3.player.resource.AoAResource;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.aoa3.util.AdvancementUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.smartbrainlib.util.RandomUtil;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.Consumer;

import static net.tslat.aoa3.player.AoAPlayerEventListener.ListenerState.*;

public final class ServerPlayerDataManager implements AoAPlayerEventListener, PlayerDataManager {
	private ServerPlayer player;

	private PlayerEquipment equipment;

	private final Object2ObjectOpenHashMap<AoASkill, AoASkill.Instance> skills = new Object2ObjectOpenHashMap<>(10);
	private final Object2ObjectOpenHashMap<AoAResource, AoAResource.Instance> resources = new Object2ObjectOpenHashMap<>(1);

	private final ArrayListMultimap<ListenerType, AoAPlayerEventListener> activeEventListeners = ArrayListMultimap.create();
	private final ArrayListMultimap<ListenerType, AoAPlayerEventListener> disabledEventListeners = ArrayListMultimap.create();
	private final ObjectOpenHashSet<AoAPlayerEventListener> dirtyListeners = new ObjectOpenHashSet<>();

	private Object2ObjectOpenHashMap<ResourceKey<Level>, PortalCoordinatesContainer> portalCoordinatesMap = new Object2ObjectOpenHashMap<>();
	private ItemStack[] itemStorage = null;
	private PositionAndRotation checkpoint = null;

	private CopyOnWriteArraySet<ResourceLocation> patchouliBooks = null;
	private boolean syncBooks = false;

	private boolean isLegitimate = true;

	private boolean abilitiesRegionLocked = false;

	public ServerPlayerDataManager(ServerPlayer player) {
		this.player = player;
		this.equipment = new PlayerEquipment(this);

		populateSkillsAndResources();
		gatherListeners();
	}

	@Override
	public ServerPlayer player() {
		return player;
	}

	public PlayerEquipment equipment() {
		return equipment;
	}

	@Override
	public Collection<AoASkill.Instance> getSkills() {
		return this.skills.values();
	}

	@Override
	@Nonnull
	public AoASkill.Instance getSkill(AoASkill skill) {
		return this.skills.getOrDefault(skill, AoASkills.DEFAULT);
	}

	@Nullable
	public AoAAbility.Instance getAbility(String abilityId) {
		for (AoASkill.Instance skill : getSkills()) {
			if (skill.getAbilityMap().containsKey(abilityId))
				return skill.getAbilityMap().get(abilityId);
		}

		return null;
	}

	@Override
	public Collection<AoAResource.Instance> getResources() {
		return this.resources.values();
	}

	@Override
	@Nonnull
	public AoAResource.Instance getResource(AoAResource resource) {
		return this.resources.getOrDefault(resource, AoAResources.DEFAULT);
	}

	@Override
	public void loadFromNbt(CompoundTag baseTag) {
		this.isLegitimate = baseTag.getBoolean("legitimate");
		int hash = baseTag.getInt("hash");

		if (hash == 0) {
			applyLegitimacyPenalties();
		}
		else {
			baseTag.remove("hash");

			if (hash != baseTag.hashCode())
				applyLegitimacyPenalties();
		}

		if (baseTag.contains("skills")) {
			CompoundTag skillsNbt = baseTag.getCompound("skills");

			for (AoASkill.Instance skill : skills.values()) {
				String id = AoARegistries.AOA_SKILLS.getId(skill.type()).toString();

				if (skillsNbt.contains(id))
					skill.loadFromNbt(skillsNbt.getCompound(id));
			}
		}

		if (baseTag.contains("resources")) {
			CompoundTag resourcesNbt = baseTag.getCompound("resources");

			for (AoAResource.Instance resource : resources.values()) {
				String id = AoARegistries.AOA_RESOURCES.getId(resource.type()).toString();

				if (resourcesNbt.contains(id))
					resource.loadFromNbt(resourcesNbt.getCompound(id));
			}
		}

		if (baseTag.contains("ItemStorage")) {
			CompoundTag itemStorage = baseTag.getCompound("ItemStorage");

			if (!itemStorage.isEmpty()) {
				this.itemStorage = new ItemStack[player.getInventory().getContainerSize()];

				for (String key : itemStorage.getAllKeys()) {
					try {
						int index = Integer.parseInt(key);

						if (index >= this.itemStorage.length)
							this.itemStorage = Arrays.copyOf(this.itemStorage, index + 1);

						this.itemStorage[index] = ItemStack.of(itemStorage.getCompound(key));
					}
					catch (Exception ex) {
						Logging.logMessage(org.apache.logging.log4j.Level.WARN, "Invalid key in ItemStorage NBT data for player. Stop messing with player NBT!", ex);
					}
				}
			}
		}

		if (baseTag.contains("PortalMap")) {
			CompoundTag portalMapTag = baseTag.getCompound("PortalMap");

			for (String s : portalMapTag.getAllKeys()) {
				try {
					CompoundTag portalReturnTag = portalMapTag.getCompound(s);
					ResourceLocation fromDim = new ResourceLocation(portalReturnTag.getString("FromDim"));
					double x = portalReturnTag.getDouble("PosX");
					double y = portalReturnTag.getDouble("PosY");
					double z = portalReturnTag.getDouble("PosZ");
					ResourceKey<Level> toDimKey = ResourceKey.create(Registries.DIMENSION, new ResourceLocation(s));
					ResourceKey<Level> fromDimKey = ResourceKey.create(Registries.DIMENSION, fromDim);

					portalCoordinatesMap.put(toDimKey, new PortalCoordinatesContainer(fromDimKey, x, y, z));
				} catch (NumberFormatException e) {
					Logging.logMessage(org.apache.logging.log4j.Level.WARN, "Found invalid portal map data, has someone been tampering with files? Data: " + s);
				}
			}
		}

		if (baseTag.contains("Checkpoint")) {
			try {
				CompoundTag checkpointTag = baseTag.getCompound("Checkpoint");
				double x = checkpointTag.getDouble("x");
				double y = checkpointTag.getDouble("y");
				double z = checkpointTag.getDouble("z");
				float pitch = checkpointTag.getFloat("pitch");
				float yaw = checkpointTag.getFloat("yaw");

				checkpoint = new PositionAndRotation(x, y, z, pitch, yaw);
			} catch (NumberFormatException e) {
				Logging.logMessage(org.apache.logging.log4j.Level.WARN, "Found invalid checkpoint data, has someone been tampering with files?");
			}
		}

		if (baseTag.contains("PatchouliBooks")) {
			ListTag booksNbt = baseTag.getList("PatchouliBooks", Tag.TAG_STRING);

			if (patchouliBooks == null) {
				patchouliBooks = new CopyOnWriteArraySet<>();
			}
			else if (!patchouliBooks.isEmpty()) {
				patchouliBooks.clear();
			}

			for (Tag book : booksNbt) {
				patchouliBooks.add(new ResourceLocation(book.getAsString()));
			}
		}

		checkAndUpdateLegitimacy();

		baseTag.putInt("hash", baseTag.hashCode());
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return new ListenerType[] {
				ListenerType.PLAYER_DEATH,
				ListenerType.PLAYER_RESPAWN,
				ListenerType.PLAYER_CLONE,
				ListenerType.EQUIPMENT_CHANGE,
				ListenerType.LEVEL_CHANGE
		};
	}

	@Override
	public void addListener(AoAPlayerEventListener listener, boolean active, ListenerType... types) {
		ArrayListMultimap<ListenerType, AoAPlayerEventListener> holder = active ? this.activeEventListeners : this.disabledEventListeners;

		if (types.length > 0) {
			for (ListenerType type : types) {
				holder.put(type, listener);
			}
		}
		else {
			holder.put(null, listener);
		}
	}

	public void removeListener(AoAPlayerEventListener listener, boolean active, ListenerType... types) {
		ArrayListMultimap<ListenerType, AoAPlayerEventListener> holder = active ? this.activeEventListeners : this.disabledEventListeners;

		if (types.length > 0) {
			for (ListenerType type : types) {
				holder.remove(type, listener);
			}
		}
		else {
			holder.remove(null, listener);
		}
	}

	@Override
	public List<AoAPlayerEventListener> getListeners(ListenerType eventType) {
		return activeEventListeners.get(eventType);
	}

	public void markListenerDirty(AoAPlayerEventListener listener) {
		dirtyListeners.add(listener);
	}

	@Override
	public void updatePlayerInstance(Player player) {
		this.player = (ServerPlayer)player;
	}

	private void selfDestruct() {
		player = null;
		equipment = null;
		patchouliBooks = null;
		checkpoint = null;

		skills.clear();
		resources.clear();
		portalCoordinatesMap.clear();
		activeEventListeners.clear();

		if (itemStorage != null)
			Arrays.fill(this.itemStorage, null);
	}

	private void populateSkillsAndResources() {
		AoASkillsReloadListener.populateSkillMap(this, skills);
		AoAResourcesReloadListener.populateResourceMap(this, resources);
	}

	private void gatherListeners() {
		addListener(this, true, getListenerTypes());
		addListener(equipment, true, equipment.getListenerTypes());

		for (AoASkill.Instance skill : skills.values()) {
			addListener(skill, true, skill.getListenerTypes());

			for (AoAAbility.Instance ability : skill.getAbilityMap().values()) {
				addListener(ability, ability.meetsRequirements(), ability.getListenerTypes());
			}
		}

		for (AoAResource.Instance resource : resources.values()) {
			addListener(resource, resource.meetsRequirements(), resource.getListenerTypes());
		}
	}

	public static void syncNewPlayer(ServerPlayer pl) {
		ServerPlayerDataManager plData = PlayerUtil.getAdventPlayer(pl);

		if (plData.patchouliBooks == null && IntegrationManager.isPatchouliActive()) {
			plData.patchouliBooks = new CopyOnWriteArraySet<>();

			plData.patchouliBooks.add(new ResourceLocation(AdventOfAscension.MOD_ID, "aoa_essentia"));
		}

		if (SkillsLeaderboard.isEnabled())
			LeaderboardActions.addNewPlayer(plData);

		AoANetworking.sendToPlayer(pl, new PlayerDataSyncPacket(plData.savetoNbt(true)));
	}

	public void doPlayerTick() {
		if (player == null || player.isSpectator() || player.level().isClientSide)
			return;

		if (!dirtyListeners.isEmpty()) {
			Iterator<AoAPlayerEventListener> listeners = dirtyListeners.iterator();

			while (listeners.hasNext()) {
				AoAPlayerEventListener listener = listeners.next();

				if (listener.getListenerState() == ACTIVE) {
					addListener(listener, true, listener.getListenerTypes());
					removeListener(listener, false, listener.getListenerTypes());
				}
				else {
					addListener(listener, false, listener.getListenerTypes());
					removeListener(listener, true, listener.getListenerTypes());
				}

				listeners.remove();
			}
		}

		equipment.handleEquipmentCheck(this);

		CompoundTag syncData = null;
		CompoundTag skillData = null;
		CompoundTag resourceData = null;

		for (AoASkill.Instance skill : getSkills()) {
			if (skill.needsSync) {
				if (skillData == null)
					skillData = new CompoundTag();

				skillData.put(AoARegistries.AOA_SKILLS.getId(skill.type()).toString(), skill.getSyncData(false));
			}
		}

		for (AoAResource.Instance resource : getResources()) {
			if (resource.needsSync) {
				if (resourceData == null)
					resourceData = new CompoundTag();

				resourceData.put(AoARegistries.AOA_RESOURCES.getId(resource.type()).toString(), resource.getSyncData(false));
			}
		}

		if (skillData != null) {
			if (syncData == null)
				syncData = new CompoundTag();

			syncData.put("skills", skillData);
		}

		if (resourceData != null) {
			if (syncData == null)
				syncData = new CompoundTag();

			syncData.put("resources", resourceData);
		}

		if (syncBooks) {
			if (syncData == null)
				syncData = new CompoundTag();

			ListTag booksNbt = new ListTag();

			for (ResourceLocation id : patchouliBooks) {
				booksNbt.add(StringTag.valueOf(id.toString()));
			}

			syncData.put("PatchouliBooks", booksNbt);
		}

		if (syncData != null) {
			syncData.putBoolean("legitimate", this.isLegitimate);

			AoANetworking.sendToPlayer(player, new PlayerDataUpdatePacket(syncData));
		}
	}

	private void storeInterventionItems() {
		if (itemStorage == null)
			itemStorage = new ItemStack[player.getInventory().getContainerSize()];

		for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
			ItemStack stack = player.getInventory().getItem(i);

			if (stack.getEnchantmentLevel(AoAEnchantments.INTERVENTION.get()) > 0) {
				if (RandomUtil.oneInNChance(5))
					stack = ItemUtil.removeEnchantment(stack, AoAEnchantments.INTERVENTION.get());

				itemStorage[i] = stack;
				player.getInventory().setItem(i, ItemStack.EMPTY);
			}
		}
	}

	public void returnItemStorage() {
		if (this.itemStorage != null) {
			Inventory inventory = this.player.getInventory();

			for (int i = 0; i < this.itemStorage.length; i++) {
				ItemStack slotItem = inventory.getItem(i);
				ItemStack storageItem = this.itemStorage[i];

				if (storageItem == null) {
					this.itemStorage[i] = ItemStack.EMPTY;

					continue;
				}

				if (slotItem.isEmpty()) {
					inventory.setItem(i, storageItem);

					this.itemStorage[i] = ItemStack.EMPTY;
				}
				else if (ItemUtil.areStacksFunctionallyEqual(slotItem, storageItem) && Objects.equals(slotItem.getTag(), storageItem.getTag())) {
					int growSize = Math.min(slotItem.getMaxStackSize(), slotItem.getCount() + storageItem.getCount()) - slotItem.getCount();

					if (growSize > 0) {
						slotItem.grow(growSize);
						slotItem.setPopTime(5);
					}

					if (growSize < storageItem.getCount()) {
						storageItem.setCount(storageItem.getCount() - growSize);
					}
					else {
						this.itemStorage[i] = ItemStack.EMPTY;
					}
				}
			}

			ItemUtil.givePlayerMultipleItems(this.player, this.itemStorage);

			this.itemStorage = null;
		}
	}

	public void storeInventoryContents() {
		if (itemStorage == null)
			itemStorage = new ItemStack[player.getInventory().getContainerSize()];

		int slotIndex = 0;

		for (NonNullList<ItemStack> compartment : player.getInventory().compartments) {
			int compartmentIndex = 0;

			for (ItemStack stack : compartment) {
				if (!stack.isEmpty())
					storeItem(slotIndex + compartmentIndex++, stack);
			}

			slotIndex += compartment.size();
		}

		player.getInventory().clearContent();
	}

	public void storeItem(int slotIndex, ItemStack stack) {
		if (this.itemStorage == null)
			this.itemStorage = new ItemStack[Math.max(slotIndex, player.getInventory().getContainerSize())];

		if (this.itemStorage[slotIndex] != null) {
			slotIndex = 0;

			while (true) {
				if (this.itemStorage.length <= slotIndex++) {
					if (slotIndex >= this.itemStorage.length)
						this.itemStorage = Arrays.copyOf(this.itemStorage, slotIndex + 1);

					break;
				}
				else if (this.itemStorage[slotIndex] == null) {
					break;
				}
			}
		}

		this.itemStorage[slotIndex] = stack;
	}

	public void setPortalReturnLocation(ResourceKey<Level> toDim, PortalCoordinatesContainer coords) {
		portalCoordinatesMap.put(toDim, coords);
	}

	public void removePortalReturnLocation(ResourceKey<Level> toDim) {
		portalCoordinatesMap.remove(toDim);
	}

	public void flushPortalReturnLocations() {
		portalCoordinatesMap.clear();
	}

	@Nullable
	public PortalCoordinatesContainer getPortalReturnLocation(ResourceKey<Level> toDim) {
		return portalCoordinatesMap.get(toDim);
	}

	public void setCheckpoint(PositionAndRotation pos) {
		this.checkpoint = pos;
	}

	public void clearCheckpoint() {
		this.checkpoint = null;
	}

	@Nullable
	public PositionAndRotation getCheckpoint() {
		return this.checkpoint;
	}

	@Override
	public void handlePlayerDeath(final LivingDeathEvent ev) {
		storeInterventionItems();
	}

	@Override
	public void handlePlayerRespawn(final PlayerEvent.PlayerRespawnEvent ev) {
		returnItemStorage();
	}

	@Override
	public void handleLevelChange(PlayerLevelChangeEvent ev) {
		if (SkillsLeaderboard.isEnabled())
			LeaderboardActions.updatePlayer(this, ev.getSkill());

		if (!abilitiesRegionLocked)
			recheckSkills();
	}

	private void recheckSkills() {
		Consumer<Collection<AoAPlayerEventListener>> updateHandler = collection -> collection.forEach(listener -> {
			ListenerState state = listener.getListenerState();

			if (state == ACTIVE) {
				if (!listener.meetsRequirements())
					listener.disable(DEACTIVATED, false);
			}
			else if (state == DEACTIVATED || (state == REGION_LOCKED && !abilitiesRegionLocked)) {
				if (listener.meetsRequirements()) {
					listener.reenable(false);

					if (state == DEACTIVATED && listener instanceof AoAAbility.Instance ability)
						AoANetworking.sendToPlayer(player, new ToastPopupPacket(ability.getSkill().type(), ability.type()));
				}
			}
		});

		updateHandler.accept(activeEventListeners.values());
		updateHandler.accept(disabledEventListeners.values());
	}

	public void setInAbilityLockRegion() {
		if (activeEventListeners.isEmpty())
			return;

		for (AoAPlayerEventListener listener : activeEventListeners.values()) {
			listener.disable(REGION_LOCKED, false);
		}

		abilitiesRegionLocked = true;
	}

	public void leaveAbilityLockRegion() {
		if (!abilitiesRegionLocked)
			return;

		abilitiesRegionLocked = false;

		recheckSkills();
	}

	public boolean areAbilitiesRegionLocked() {
		return this.abilitiesRegionLocked;
	}

	public void checkAndUpdateLegitimacy() {
		if (player != null) {
			Advancement adv = AdvancementUtil.getAdvancement(new ResourceLocation(AdventOfAscension.MOD_ID, "completionist/by_the_books"));

			if (adv == null)
				return;

			PlayerAdvancements plAdv = player.getAdvancements();
			boolean hasAdvancement = plAdv.getOrStartProgress(adv).isDone();

			if (hasAdvancement && !this.isLegitimate)
				plAdv.revoke(adv, "legitimate");
		}
	}

	public void applyLegitimacyPenalties() {
		this.isLegitimate = false;

		checkAndUpdateLegitimacy();
	}

	@Override
	public void handleArmourChange(final LivingEquipmentChangeEvent ev) {
		equipment().markDirty();
	}

	@Override
	public boolean isLegitimate() {
		return this.isLegitimate;
	}

	@Override
	public int getTotalLevel() {
		int i = 0;

		for (AoASkill.Instance skill : this.getSkills()) {
			i += skill.getLevel(true);
		}

		return i;
	}

	public void addPatchouliBook(ResourceLocation book) {
		if (patchouliBooks == null)
			patchouliBooks = new CopyOnWriteArraySet<>();

		patchouliBooks.add(book);
		syncBooks = true;
	}

	@Override
	public void handlePlayerDataClone(final PlayerEvent.Clone ev) {
		ev.getOriginal().reviveCaps();
		ServerPlayerDataManager sourceData = PlayerUtil.getAdventPlayer((ServerPlayer)ev.getOriginal());

		for (Map.Entry<AoASkill, AoASkill.Instance> entry : sourceData.skills.entrySet()) {
			AoASkill.Instance newInstance = skills.get(entry.getKey());

			if (newInstance != null)
				newInstance.loadFromNbt(entry.getValue().saveToNbt());
		}

		for (Map.Entry<AoAResource, AoAResource.Instance> entry : sourceData.resources.entrySet()) {
			AoAResource.Instance newInstance = resources.get(entry.getKey());

			if (newInstance != null)
				newInstance.loadFromNbt(entry.getValue().saveToNbt());
		}

		this.equipment.cooldowns = sourceData.equipment.cooldowns;
		this.portalCoordinatesMap = sourceData.portalCoordinatesMap;
		this.checkpoint = sourceData.checkpoint;
		this.itemStorage = sourceData.itemStorage;
		this.patchouliBooks = sourceData.patchouliBooks;
		this.isLegitimate = sourceData.isLegitimate;

		AoAScheduler.scheduleSyncronisedTask(sourceData::selfDestruct, 1);
		ev.getOriginal().invalidateCaps();
	}

	public CompoundTag savetoNbt(boolean forClientSync) {
		CompoundTag baseTag = new CompoundTag();

		if (!skills.isEmpty()) {
			CompoundTag skillsNbt = new CompoundTag();

			for (AoASkill.Instance skill : skills.values()) {
				skillsNbt.put(AoARegistries.AOA_SKILLS.getId(skill.type()).toString(), forClientSync ? skill.getSyncData(true) : skill.saveToNbt());
			}

			if (!skillsNbt.isEmpty())
				baseTag.put("skills", skillsNbt);
		}

		if (!resources.isEmpty()) {
			CompoundTag resourcesNbt = new CompoundTag();

			for (AoAResource.Instance resource : resources.values()) {
				resourcesNbt.put(AoARegistries.AOA_RESOURCES.getId(resource.type()).toString(), forClientSync ? resource.getSyncData(true) : resource.saveToNbt());
			}

			if (!resourcesNbt.isEmpty())
				baseTag.put("resources", resourcesNbt);
		}

		if (patchouliBooks != null) {
			ListTag booksNbt = new ListTag();

			for (ResourceLocation id : patchouliBooks) {
				booksNbt.add(StringTag.valueOf(id.toString()));
			}

			baseTag.put("PatchouliBooks", booksNbt);
		}

		if (!forClientSync) {
			if (this.itemStorage != null) {
				CompoundTag itemStorage = new CompoundTag();

				for (int i = 0; i < this.itemStorage.length; i++) {
					if (this.itemStorage[i] != null)
						itemStorage.put(String.valueOf(i), this.itemStorage[i].serializeNBT());
				}

				baseTag.put("ItemStorage", itemStorage);
			}

			if (!portalCoordinatesMap.isEmpty()) {
				CompoundTag portalCoordinatesNBT = new CompoundTag();

				for (Map.Entry<ResourceKey<Level>, PortalCoordinatesContainer> entry : portalCoordinatesMap.entrySet()) {
					CompoundTag portalReturnTag = new CompoundTag();
					PortalCoordinatesContainer container = entry.getValue();

					portalReturnTag.putString("FromDim", container.fromDim().location().toString());
					portalReturnTag.putDouble("PosX", container.x());
					portalReturnTag.putDouble("PosY", container.y());
					portalReturnTag.putDouble("PosZ", container.z());

					portalCoordinatesNBT.put(entry.getKey().location().toString(), portalReturnTag);
				}

				baseTag.put("PortalMap", portalCoordinatesNBT);
			}

			if (checkpoint != null) {
				CompoundTag checkpointTag = new CompoundTag();

				checkpointTag.putDouble("x", this.checkpoint.x());
				checkpointTag.putDouble("y", this.checkpoint.y());
				checkpointTag.putDouble("z", this.checkpoint.z());
				checkpointTag.putFloat("pitch", this.checkpoint.pitch());
				checkpointTag.putFloat("yaw", this.checkpoint.yaw());

				baseTag.put("Checkpoint", checkpointTag);
			}
		}

		baseTag.putBoolean("legitimate", isLegitimate);
		baseTag.putInt("hash", baseTag.hashCode());

		return baseTag;
	}

	public final class PlayerEquipment implements AoAPlayerEventListener {
		private final ServerPlayerDataManager playerDataManager;

		private HashMap<String, Integer> cooldowns = new HashMap<>(1);
		private HashMap<AdventArmour.Type, ArmourEffectWrapper> armourMap = new HashMap<>(4);

		private boolean checkEquipment = true;

		private AdventArmour currentFullSet = null;

		private AdventArmour helmet = null;
		private AdventArmour body = null;
		private AdventArmour legs = null;
		private AdventArmour boots = null;

		private PlayerEquipment(ServerPlayerDataManager playerData) {
			this.playerDataManager = playerData;
		}

		public void markDirty() {
			this.checkEquipment = true;
		}

		@Override
		public ListenerType[] getListenerTypes() {
			return new ListenerType[] {
					ListenerType.INCOMING_ATTACK_BEFORE,
					ListenerType.INCOMING_ATTACK_DURING,
					ListenerType.INCOMING_ATTACK_AFTER,
					ListenerType.OUTGOING_ATTACK_DURING,
					ListenerType.PLAYER_FALL,
					ListenerType.PLAYER_DEATH,
					ListenerType.PLAYER_TICK
			};
		}

		public boolean isCooledDown(String counter) {
			return !cooldowns.containsKey(counter);
		}

		public int getCooldown(String counter) {
			return cooldowns.getOrDefault(counter, 0);
		}

		public void setCooldown(String counter, int cooldownTicks) {
			cooldowns.put(counter, cooldownTicks);
		}

		public AdventArmour.Type getCurrentFullArmourSet() {
			return currentFullSet != null ? currentFullSet.getSetType() : AdventArmour.Type.NONE;
		}

		@Override
		public void handlePreIncomingAttack(final LivingAttackEvent ev) {
			if (currentFullSet != null)
				currentFullSet.onPreAttackReceived(playerDataManager, null, ev);

			for (ArmourEffectWrapper wrapper : armourMap.values()) {
				wrapper.armour.onPreAttackReceived(playerDataManager, wrapper.currentSlots, ev);
			}
		}

		@Override
		public void handleIncomingAttack(final LivingHurtEvent ev) {
			if (currentFullSet != null)
				currentFullSet.onAttackReceived(playerDataManager, null, ev);

			for (ArmourEffectWrapper wrapper : armourMap.values()) {
				wrapper.armour.onAttackReceived(playerDataManager, wrapper.currentSlots, ev);
			}
		}

		@Override
		public void handleOutgoingAttack(final LivingHurtEvent ev) {
			if (currentFullSet != null)
				currentFullSet.onDamageDealt(playerDataManager, null, ev);

			for (ArmourEffectWrapper wrapper : armourMap.values()) {
				wrapper.armour.onDamageDealt(playerDataManager, wrapper.currentSlots, ev);
			}
		}

		@Override
		public void handlePostIncomingAttack(final LivingDamageEvent ev) {
			if (currentFullSet != null)
				currentFullSet.onPostAttackReceived(playerDataManager, null, ev);

			for (ArmourEffectWrapper wrapper : armourMap.values()) {
				wrapper.armour.onPostAttackReceived(playerDataManager, wrapper.currentSlots, ev);
			}
		}

		@Override
		public void handlePlayerFall(final LivingFallEvent ev) {
			if (currentFullSet != null)
				currentFullSet.onPlayerLandingFall(playerDataManager, null, ev);

			for (ArmourEffectWrapper wrapper : armourMap.values()) {
				wrapper.armour.onPlayerLandingFall(playerDataManager, wrapper.currentSlots, ev);
			}
		}

		@Override
		public void handlePlayerDeath(final LivingDeathEvent ev) {
			if (currentFullSet != null)
				currentFullSet.onPlayerDeath(playerDataManager, null, ev);

			for (ArmourEffectWrapper wrapper : armourMap.values()) {
				wrapper.armour.onPlayerDeath(playerDataManager, wrapper.currentSlots, ev);
			}
		}

		void handleEquipmentCheck(ServerPlayerDataManager playerDataManager) {
			if (!checkEquipment)
				return;

			boolean armourChanged;

			armourChanged = checkAndHandleArmourSwap(boots, player.getInventory().armor.get(0).getItem(), EquipmentSlot.FEET);
			armourChanged |= checkAndHandleArmourSwap(legs, player.getInventory().armor.get(1).getItem(), EquipmentSlot.LEGS);
			armourChanged |= checkAndHandleArmourSwap(body, player.getInventory().armor.get(2).getItem(), EquipmentSlot.CHEST);
			armourChanged |= checkAndHandleArmourSwap(helmet, player.getInventory().armor.get(3).getItem(), EquipmentSlot.HEAD);

			AdventArmour oldSet = currentFullSet;

			if (armourChanged) {
				if (boots != null && legs != null && body != null && helmet != null && boots.getSetType() == legs.getSetType() && legs.getSetType() == body.getSetType() && body.isSetHelmet(helmet)) {
					currentFullSet = boots;

					if (currentFullSet != oldSet) {
						if (oldSet != null)
							unequipAdventArmour(playerDataManager, oldSet, null);

						equipAdventArmour(playerDataManager, currentFullSet, null);
					}
				}
				else {
					currentFullSet = null;

					if (oldSet != null)
						unequipAdventArmour(playerDataManager, oldSet, null);
				}
			}

			checkEquipment = false;
		}

		private boolean checkAndHandleArmourSwap(@Nullable AdventArmour currentPiece, @Nonnull Item newPiece, @Nonnull EquipmentSlot slot) {
			boolean changed = false;

			if (newPiece != currentPiece) {
				changed = true;

				if (currentPiece != null)
					unequipAdventArmour(playerDataManager, currentPiece, slot);

				switch (slot) {
					case FEET -> {
						boots = newPiece instanceof AdventArmour ? (AdventArmour)newPiece : null;
						if (boots != null && AoASkillReqReloadListener.canEquip(playerDataManager, boots, false))
							equipAdventArmour(playerDataManager, boots, slot);
					}
					case LEGS -> {
						legs = newPiece instanceof AdventArmour ? (AdventArmour)newPiece : null;
						if (legs != null && AoASkillReqReloadListener.canEquip(playerDataManager, legs, false))
							equipAdventArmour(playerDataManager, legs, slot);
					}
					case CHEST -> {
						body = newPiece instanceof AdventArmour ? (AdventArmour)newPiece : null;
						if (body != null && AoASkillReqReloadListener.canEquip(playerDataManager, body, false))
							equipAdventArmour(playerDataManager, body, slot);
					}
					case HEAD -> {
						helmet = newPiece instanceof AdventArmour ? (AdventArmour)newPiece : null;
						if (helmet != null && AoASkillReqReloadListener.canEquip(playerDataManager, helmet, false))
							equipAdventArmour(playerDataManager, helmet, slot);
					}
				}
			}

			return changed;
		}

		@Override
		public void handlePlayerTick(final TickEvent.PlayerTickEvent ev) {
			if (!player.isAlive() || player.isSpectator())
				return;

			boolean doArmourTick = true;

			if (PlayerUtil.shouldPlayerBeAffected(player)) {
				for (InteractionHand hand : InteractionHand.values()) {
					ItemStack heldStack = player.getItemInHand(hand);

					if (!AoASkillReqReloadListener.canEquip(playerDataManager, heldStack.getItem(), true)) {
						ItemHandlerHelper.giveItemToPlayer(player, heldStack);
						player.setItemInHand(hand, ItemStack.EMPTY);
					}
				}

				if (boots != null && !AoASkillReqReloadListener.canEquip(playerDataManager, boots, true)) {
					doArmourTick = false;

					ItemHandlerHelper.giveItemToPlayer(player, player.getInventory().armor.get(0));
					player.getInventory().armor.set(0, ItemStack.EMPTY);
					unequipAdventArmour(playerDataManager, boots, EquipmentSlot.FEET);
				}

				if (legs != null && !AoASkillReqReloadListener.canEquip(playerDataManager, legs, true)) {
					doArmourTick = false;

					ItemHandlerHelper.giveItemToPlayer(player, player.getInventory().armor.get(1));
					player.getInventory().armor.set(1, ItemStack.EMPTY);
					unequipAdventArmour(playerDataManager, legs, EquipmentSlot.LEGS);
				}

				if (body != null && !AoASkillReqReloadListener.canEquip(playerDataManager, body, true)) {
					doArmourTick = false;

					ItemHandlerHelper.giveItemToPlayer(player, player.getInventory().armor.get(2));
					player.getInventory().armor.set(2, ItemStack.EMPTY);
					unequipAdventArmour(playerDataManager, body, EquipmentSlot.CHEST);
				}

				if (helmet != null && !AoASkillReqReloadListener.canEquip(playerDataManager, helmet, true)) {
					doArmourTick = false;

					ItemHandlerHelper.giveItemToPlayer(player, player.getInventory().armor.get(3));
					player.getInventory().armor.set(3, ItemStack.EMPTY);
					unequipAdventArmour(playerDataManager, helmet, EquipmentSlot.HEAD);
				}
			}

			if (doArmourTick) {
				if (currentFullSet != null)
					currentFullSet.onEffectTick(playerDataManager, null);

				for (ArmourEffectWrapper wrapper : armourMap.values()) {
					wrapper.armour.onEffectTick(playerDataManager, wrapper.currentSlots);
				}
			}

			handleCooldowns();
			player.inventoryMenu.broadcastChanges();
		}

		private void equipAdventArmour(ServerPlayerDataManager plData, AdventArmour item, @Nullable EquipmentSlot slot) {
			item.onEquip(plData, slot);

			ArmourEffectWrapper armourEffectWrapper = armourMap.get(item.getSetType());

			if (slot != null) {
				if (armourEffectWrapper == null) {
					armourMap.put(item.getSetType(), new ArmourEffectWrapper(item, slot));
				}
				else {
					armourEffectWrapper.currentSlots.add(slot);
				}
			}
		}

		private void unequipAdventArmour(ServerPlayerDataManager plData, AdventArmour item, @Nullable EquipmentSlot slot) {
			item.onUnequip(plData, slot);

			ArmourEffectWrapper armourEffectWrapper = armourMap.get(item.getSetType());

			if (armourEffectWrapper != null && slot != null) {
				if (armourEffectWrapper.currentSlots.size() <= 1) {
					armourMap.remove(item.getSetType());
				}
				else {
					armourEffectWrapper.currentSlots.remove(slot);
				}
			}
		}

		private void handleCooldowns() {
			Iterator<Map.Entry<String, Integer>> cooldownsIterator = cooldowns.entrySet().iterator();

			while (cooldownsIterator.hasNext()) {
				Map.Entry<String, Integer> entry = cooldownsIterator.next();

				if (entry.getValue() <= 1) {
					cooldownsIterator.remove();
				}
				else {
					entry.setValue(entry.getValue() - 1);
				}
			}
		}

		private class ArmourEffectWrapper {
			private final AdventArmour armour;
			private final HashSet<EquipmentSlot> currentSlots = new HashSet<>(4);

			private ArmourEffectWrapper(AdventArmour armour, EquipmentSlot firstSlotEquipped) {
				this.armour = armour;

				currentSlots.add(firstSlotEquipped);
			}
		}
	}
}
