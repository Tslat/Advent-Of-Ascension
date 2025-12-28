package net.tslat.aoa3.player;

import com.google.common.base.Suppliers;
import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.PlayerAdvancements;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.Event;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.EntityInvulnerabilityCheckEvent;
import net.neoforged.neoforge.event.entity.living.*;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.items.ItemHandlerHelper;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.common.networking.AoANetworking;
import net.tslat.aoa3.common.networking.packets.adventplayer.PlayerDataSyncPacket;
import net.tslat.aoa3.common.networking.packets.adventplayer.PlayerDataUpdatePacket;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.common.registration.item.AoAEnchantments;
import net.tslat.aoa3.common.toast.AbilityUnlockToastData;
import net.tslat.aoa3.content.item.armour.AdventArmour;
import net.tslat.aoa3.data.server.AoAResourcesReloadListener;
import net.tslat.aoa3.data.server.AoASkillReqReloadListener;
import net.tslat.aoa3.data.server.AoASkillsReloadListener;
import net.tslat.aoa3.event.custom.events.PlayerLevelChangeEvent;
import net.tslat.aoa3.event.dynamic.DynamicEventSubscriber;
import net.tslat.aoa3.integration.IntegrationManager;
import net.tslat.aoa3.leaderboard.SkillsLeaderboard;
import net.tslat.aoa3.leaderboard.task.LeaderboardActions;
import net.tslat.aoa3.library.object.interfaces.PartialNbtSerializable;
import net.tslat.aoa3.library.object.container.PositionAndRotation;
import net.tslat.aoa3.player.ability.AoAAbility;
import net.tslat.aoa3.player.resource.AoAResource;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.*;
import net.tslat.tme.api.util.RandomUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.ref.WeakReference;
import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.BooleanSupplier;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static net.tslat.aoa3.player.AoAPlayerEventListener.ListenerState.*;

public final class ServerPlayerDataManager implements AoAPlayerEventListener, PlayerDataManager, PartialNbtSerializable {
	private final WeakReference<ServerPlayer> player;
	public final Equipment equipment;
	public final Stats stats;
	public final Storage storage;

	private final ObjectArrayList<WeakReference<AoAPlayerEventListener>> eventListeners = new ObjectArrayList<>();
	private final List<DynamicEventSubscriber<?>> eventSubscribers = List.of(
			listener(LivingDeathEvent.class, LivingDeathEvent::getEntity, this::handlePlayerDeath),
			listener(PlayerLevelChangeEvent.class, this::handleLevelChange),
			listener(PlayerTickEvent.Pre.class, this::handlePlayerTick));

	public ServerPlayerDataManager(ServerPlayer player) {
		this.player = new WeakReference<>(player);
		this.equipment = new Equipment();
		this.stats = new Stats();
		this.storage = new Storage();

		gatherListeners();
	}

	@Override
	public ServerPlayer getPlayer() {
		return this.player.get();
	}

	@Override
	public boolean isStillValid() {
		return getPlayer() != null && !getPlayer().isRemoved();
	}

	@Override
	public Collection<AoASkill.Instance> getSkills() {
		return this.stats.getSkills();
	}

	@NotNull
	@Override
	public AoASkill.Instance getSkill(AoASkill skill) {
		return this.stats.getSkill(skill);
	}

	@Nullable
	@Override
	public AoAAbility.Instance getAbility(String abilityId) {
		return this.stats.getAbility(abilityId);
	}

	@Override
	public Collection<AoAResource.Instance> getResources() {
		return this.stats.getResources();
	}

	@Override
	@NotNull
	public AoAResource.Instance getResource(AoAResource resource) {
		return this.stats.getResource(resource);
	}

	@Override
	public void load(CompoundTag nbt, HolderLookup.Provider holderLookup, boolean isPartial) {
		this.stats.load(nbt, holderLookup, isPartial);
		this.storage.load(nbt, holderLookup, isPartial);

		int hash = nbt.getInt("hash");

		if (hash == 0) {
			applyLegitimacyPenalties();
		}
		else {
			nbt.remove("hash");

			if (hash != nbt.hashCode())
				applyLegitimacyPenalties();

			nbt.putInt("hash", nbt.hashCode());
		}

		checkAndUpdateLegitimacy();
	}

	@Override
	public void save(CompoundTag nbt, HolderLookup.Provider holderLookup, boolean isPartial) {
		this.stats.save(nbt, holderLookup, isPartial);
		this.storage.save(nbt, holderLookup, isPartial);

		nbt.putInt("hash", nbt.hashCode());
	}

	@Override
	public void addEventListener(AoAPlayerEventListener listener) {
		this.eventListeners.add(new WeakReference<>(listener));
		listener.registerEventSubscribers();
	}

	@Override
	public List<DynamicEventSubscriber<? extends Event>> getEventSubscribers() {
		return this.eventSubscribers;
	}

	private void gatherListeners() {
		registerEventSubscribers();
		this.equipment.registerEventSubscribers();
		this.stats.registerEventSubscribers();
	}

	public void syncNewPlayer() {
		if (SkillsLeaderboard.isEnabled())
			LeaderboardActions.addNewPlayer(this);

		final CompoundTag data = new CompoundTag();

		save(data, getPlayer().level().registryAccess(), true);
		AoANetworking.sendToPlayer(getPlayer(), new PlayerDataSyncPacket(data));
	}

	private void handlePlayerTick(final PlayerTickEvent.Pre ev) {
		if (getPlayer() == null || getPlayer().isSpectator())
			return;

		Supplier<CompoundTag> syncData = Suppliers.memoize(CompoundTag::new);
		boolean sync = this.stats.getSyncData(syncData);
		sync |= this.storage.getSyncData(syncData);

		if (sync)
			AoANetworking.sendToPlayer(getPlayer(), new PlayerDataUpdatePacket(syncData.get()));
	}

	public void storeInventoryContents() {
		this.storage.putItemsInStorage(stack -> true);
	}

	private void handlePlayerDeath(final LivingDeathEvent ev) {
		final ServerLevel level = getPlayer().serverLevel();

		this.storage.putItemsInStorage(stack -> {
			if (stack.isEmpty() || !EnchantmentUtil.hasEnchantment(level, stack, AoAEnchantments.INTERVENTION))
				return false;

			if (RandomUtil.oneInNChance(5))
				EnchantmentUtil.removeEnchantment(level, stack, AoAEnchantments.INTERVENTION);

			return true;
		});
	}

	private static void handlePlayerRespawn(final PlayerEvent.PlayerRespawnEvent ev) {
		if (ev.getEntity() instanceof ServerPlayer pl)
			PlayerUtil.getAdventPlayer(pl).storage.returnStoredItems();
	}

	private void handleLevelChange(PlayerLevelChangeEvent ev) {
		if (SkillsLeaderboard.isEnabled())
			LeaderboardActions.updatePlayer(this, ev.getSkill());

		if (!this.stats.areAbilitiesRegionLocked())
			checkAndUpdateListeners();
	}

	private void checkAndUpdateListeners() {
		for (WeakReference<AoAPlayerEventListener> ref : this.eventListeners) {
			AoAPlayerEventListener listener = ref.get();
			ListenerState state = listener.getListenerState();

			if (state == ACTIVE) {
				if (!listener.meetsRequirements())
					listener.disable(DEACTIVATED, false);
			}
			else if (state == DEACTIVATED || (state == REGION_LOCKED && !this.stats.areAbilitiesRegionLocked())) {
				if (listener.meetsRequirements()) {
					listener.reenable(false);

					if (state == DEACTIVATED && listener instanceof AoAAbility.Instance ability)
						AbilityUnlockToastData.sendToastPopupTo(getPlayer(), ability);
				}
			}
		}
	}

	public void checkAndUpdateLegitimacy() {
		if (this.player != null) {
			AdvancementUtil.getAdvancement(getPlayer().serverLevel(), AdventOfAscension.id("completionist/by_the_books")).ifPresent(adv -> {
				PlayerAdvancements plAdv = getPlayer().getAdvancements();
				boolean hasAdvancement = plAdv.getOrStartProgress(adv).isDone();

				if (hasAdvancement && !this.stats.isLegitimate())
					plAdv.revoke(adv, "legitimate");
			});
		}
	}

	public void applyLegitimacyPenalties() {
		this.stats.isLegitimate = false;

		checkAndUpdateLegitimacy();
	}

	@Override
	public boolean isLegitimate() {
		return this.stats.isLegitimate();
	}

	@Override
	public int getTotalLevel() {
		return this.stats.getTotalLevel();
	}

	static {
		NeoForge.EVENT_BUS.addListener(PlayerEvent.Clone.class, ServerPlayerDataManager::handlePlayerDataClone);
		NeoForge.EVENT_BUS.addListener(PlayerEvent.PlayerRespawnEvent.class, ServerPlayerDataManager::handlePlayerRespawn);
	}

	private static void handlePlayerDataClone(final PlayerEvent.Clone ev) {
		ServerPlayerDataManager newData = PlayerUtil.getAdventPlayer((ServerPlayer)ev.getEntity());
		ServerPlayerDataManager sourceData = PlayerUtil.getAdventPlayer((ServerPlayer)ev.getOriginal());

		newData.stats.clone(sourceData.stats);
		newData.storage.clone(sourceData.storage);
	}

	public final class Stats implements PartialNbtSerializable {
		private final Object2ObjectOpenHashMap<AoASkill, AoASkill.Instance> skills = AoASkillsReloadListener.generateSkillsMap(ServerPlayerDataManager.this);
		private final Object2ObjectOpenHashMap<AoAResource, AoAResource.Instance> resources = AoAResourcesReloadListener.generateResourcesMap(ServerPlayerDataManager.this);

		private boolean isLegitimate = true;
		private boolean abilitiesRegionLocked = false;

		private Stats() {}

		public Collection<AoASkill.Instance> getSkills() {
			return this.skills.values();
		}

		public Collection<AoAResource.Instance> getResources() {
			return this.resources.values();
		}

		public boolean isLegitimate() {
			return this.isLegitimate;
		}

		public boolean areAbilitiesRegionLocked() {
			return this.abilitiesRegionLocked;
		}

		@NotNull
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

		public int getLevel(AoASkill skill) {
			return getLevel(skill, true);
		}

		public int getLevel(AoASkill skill, boolean includeVanity) {
			return getSkill(skill).getLevel(includeVanity);
		}

		public int getTotalLevel() {
			int i = 0;

			for (AoASkill.Instance skill : getSkills()) {
				i += skill.getLevel(true);
			}

			return i;
		}

		public boolean hasLevel(AoASkill skill, int level) {
			return getLevel(skill) >= level;
		}

		@NotNull
		public AoAResource.Instance getResource(AoAResource resource) {
			return this.resources.getOrDefault(resource, AoAResources.DEFAULT);
		}

		public float getResourceAmount(AoAResource resource) {
			return getResource(resource).getCurrentValue();
		}

		public boolean hasResourceAmount(AoAResource resource, float amount) {
			return getResourceAmount(resource) >= amount;
		}

		public void setInAbilityLockRegion() {
			for (WeakReference<AoAPlayerEventListener> listener : ServerPlayerDataManager.this.eventListeners) {
				listener.get().disable(REGION_LOCKED, false);
			}

			this.abilitiesRegionLocked = true;
		}

		public void leaveAbilityLockRegion() {
			if (!areAbilitiesRegionLocked())
				return;

			this.abilitiesRegionLocked = false;

			checkAndUpdateListeners();
		}

		void registerEventSubscribers() {
			this.resources.values().forEach(ServerPlayerDataManager.this::addEventListener);

			for (AoASkill.Instance skill : this.skills.values()) {
				addEventListener(skill);
				skill.getAbilityMap().values().forEach(ServerPlayerDataManager.this::addEventListener);
			}
		}

		private void clone(Stats oldStats) {
			this.isLegitimate = oldStats.isLegitimate;

			for (Map.Entry<AoASkill, AoASkill.Instance> entry : oldStats.skills.entrySet()) {
				AoASkill.Instance newInstance = this.skills.get(entry.getKey());

				if (newInstance != null)
					newInstance.loadFromNbt(entry.getValue().saveToNbt());
			}

			for (Map.Entry<AoAResource, AoAResource.Instance> entry : oldStats.resources.entrySet()) {
				AoAResource.Instance newInstance = this.resources.get(entry.getKey());

				if (newInstance != null)
					newInstance.loadFromNbt(entry.getValue().saveToNbt());
			}
		}

		boolean getSyncData(Supplier<CompoundTag> syncData) {
			CompoundTag skillData = null;
			CompoundTag resourceData = null;

			for (AoASkill.Instance skill : getSkills()) {
				if (skill.needsSync) {
					if (skillData == null)
						skillData = new CompoundTag();

					skillData.put(RegistryUtil.getId(skill.type()).toString(), skill.getSyncData(false));
				}
			}

			for (AoAResource.Instance resource : getResources()) {
				if (resource.needsSync) {
					if (resourceData == null)
						resourceData = new CompoundTag();

					resourceData.put(RegistryUtil.getId(resource.type()).toString(), resource.getSyncData(false));
				}
			}

			if (skillData != null)
				syncData.get().put("skills", skillData);

			if (resourceData != null)
				syncData.get().put("resources", resourceData);

			if (skillData == null && resourceData == null)
				return false;

			syncData.get().putBoolean("legitimate", isLegitimate());

			return true;
		}

		@Override
		public void load(CompoundTag nbt, HolderLookup.Provider holderLookup, boolean isPartial) {
			this.isLegitimate = nbt.getBoolean("legitimate");

			if (nbt.contains("skills")) {
				CompoundTag skillsNbt = nbt.getCompound("skills");

				for (AoASkill.Instance skill : this.skills.values()) {
					String id = AoARegistries.AOA_SKILLS.getKey(skill.type()).toString();

					if (skillsNbt.contains(id))
						skill.loadFromNbt(skillsNbt.getCompound(id));
				}
			}

			if (nbt.contains("resources")) {
				CompoundTag resourcesNbt = nbt.getCompound("resources");

				for (AoAResource.Instance resource : this.resources.values()) {
					String id = AoARegistries.AOA_RESOURCES.getKey(resource.type()).toString();

					if (resourcesNbt.contains(id))
						resource.loadFromNbt(resourcesNbt.getCompound(id));
				}
			}
		}

		@Override
		public void save(CompoundTag nbt, HolderLookup.Provider holderLookup, boolean forClientSync) {
			nbt.putBoolean("legitimate", isLegitimate());

			if (!this.skills.isEmpty()) {
				final CompoundTag skillsData = new CompoundTag();

				for (AoASkill.Instance skill : this.skills.values()) {
					skillsData.put(AoARegistries.AOA_SKILLS.getKey(skill.type()).toString(), forClientSync ? skill.getSyncData(true) : skill.saveToNbt());
				}

				nbt.put("skills", skillsData);
			}

			if (!this.resources.isEmpty()) {
				CompoundTag resourcesNbt = new CompoundTag();

				for (AoAResource.Instance resource : this.resources.values()) {
					resourcesNbt.put(AoARegistries.AOA_RESOURCES.getKey(resource.type()).toString(), forClientSync ? resource.getSyncData(true) : resource.saveToNbt());
				}

				nbt.put("resources", resourcesNbt);
			}
		}
	}

	public final class Storage implements PartialNbtSerializable {
		private final Object2ObjectOpenHashMap<ResourceKey<Level>, GlobalPos> portalCoordinatesMap = new Object2ObjectOpenHashMap<>();
		private final CopyOnWriteArraySet<ResourceLocation> patchouliBooks = new CopyOnWriteArraySet<>();
		private final Int2ObjectMap<ItemStack> itemStorage = new Int2ObjectArrayMap<>();
		@Nullable
		private PositionAndRotation activeCheckpoint = null;
		@Nullable
		private CompoundTag statsStorage = null;

		private boolean needsPatchouliSync = false;

		private Storage() {
			if (IntegrationManager.isPatchouliActive())
				this.patchouliBooks.add(AdventOfAscension.id("aoa_essentia"));
		}

		public Map<ResourceKey<Level>, GlobalPos> getPortalReturnLocations() {
			return this.portalCoordinatesMap;
		}

		@Nullable
		public GlobalPos getPortalReturnFor(ResourceKey<Level> toDimension) {
			return getPortalReturnLocations().get(toDimension);
		}

		public void setPortalReturnLocation(ResourceKey<Level> toDimension, ResourceKey<Level> fromDim, BlockPos posInFromDim) {
			setPortalReturnLocation(toDimension, new GlobalPos(fromDim, posInFromDim));
		}

		public void setPortalReturnLocation(ResourceKey<Level> toDimension, GlobalPos pos) {
			getPortalReturnLocations().put(toDimension, pos);
		}

		public void removePortalReturnLocation(ResourceKey<Level> toDimension) {
			getPortalReturnLocations().remove(toDimension);
		}

		public void addPatchouliBook(ResourceLocation book) {
			if (this.patchouliBooks.add(book))
				this.needsPatchouliSync = true;
		}

		public boolean hasPatchouliBook(ResourceLocation book) {
			return this.patchouliBooks.contains(book);
		}

		@Nullable
		public PositionAndRotation getActiveCheckpoint() {
			return this.activeCheckpoint;
		}

		public void clearActiveCheckpoint() {
			setActiveCheckpoint(null);
		}

		public void setActiveCheckpoint(@Nullable PositionAndRotation checkpoint) {
			this.activeCheckpoint = checkpoint;
		}

		public void putItemsInStorage(Predicate<ItemStack> shouldStore) {
			putItemsInStorage(shouldStore, () -> true);
		}

		public void putItemsInStorage(Predicate<ItemStack> shouldStore, BooleanSupplier keepLooking) {
			this.itemStorage.clear();

			final ServerPlayer player = getPlayer();
			int slotIndex = 0;

			for (NonNullList<ItemStack> compartment : player.getInventory().compartments) {
				for (int i = 0; i < compartment.size(); i++) {
					ItemStack stack = compartment.get(i);

					if (!stack.isEmpty() && shouldStore.test(stack)) {
						int position = slotIndex + i;
						ItemStack prevStack = this.itemStorage.put(position, stack.copy());

						if (prevStack != null)
							this.itemStorage.put(-(position), prevStack);

						compartment.set(i, ItemStack.EMPTY);
					}

					if (!keepLooking.getAsBoolean())
						return;
				}

				slotIndex += compartment.size();
			}
		}

		public void returnStoredItems() {
			if (this.itemStorage.isEmpty())
				return;

			Inventory inventory = getPlayer().getInventory();

			this.itemStorage.int2ObjectEntrySet().removeIf(entry -> {
				final int slotIndex = entry.getIntKey();

				if (slotIndex > inventory.getContainerSize() || slotIndex < 0)
					return false;

				final ItemStack slotItem = inventory.getItem(slotIndex);
				final ItemStack storageItem = entry.getValue();

				if (slotItem.isEmpty()) {
					inventory.setItem(slotIndex, storageItem);

					return true;
				}

				if (ItemStack.isSameItemSameComponents(slotItem, storageItem)) {
					int growSize = Math.min(slotItem.getMaxStackSize(), slotItem.getCount() + storageItem.getCount()) - slotItem.getCount();

					if (growSize > 0) {
						slotItem.grow(growSize);
						slotItem.setPopTime(5);
					}

					if (growSize < storageItem.getCount()) {
						storageItem.setCount(storageItem.getCount() - growSize);
					}
					else {
						return true;
					}
				}

				return false;
			});

			if (!this.itemStorage.isEmpty()) {
				InventoryUtil.giveItemsTo(getPlayer(), this.itemStorage.values());
				this.itemStorage.clear();
			}
		}

		public void saveStats() {
			this.statsStorage = new CompoundTag();

			getPlayer().getFoodData().addAdditionalSaveData(this.statsStorage);
		}

		public void restoreStats() {
			if (this.statsStorage == null)
				return;

			getPlayer().getFoodData().readAdditionalSaveData(this.statsStorage);

			this.statsStorage = null;
		}

		boolean getSyncData(Supplier<CompoundTag> syncData) {
			if (!this.needsPatchouliSync)
				return false;

			this.needsPatchouliSync = false;
			ListTag booksNbt = new ListTag();

			for (ResourceLocation id : this.patchouliBooks) {
				booksNbt.add(StringTag.valueOf(id.toString()));
			}

			syncData.get().put("PatchouliBooks", booksNbt);

			return true;
		}

		private void clone(Storage oldStorage) {
			this.portalCoordinatesMap.putAll(oldStorage.portalCoordinatesMap);
			this.patchouliBooks.addAll(oldStorage.patchouliBooks);
			this.itemStorage.putAll(oldStorage.itemStorage);
			this.activeCheckpoint = oldStorage.activeCheckpoint;
			this.statsStorage = oldStorage.statsStorage;
		}

		@Override
		public void load(CompoundTag nbt, HolderLookup.Provider holderLookup, boolean isPartial) {
			if (nbt.contains("ItemStorage", Tag.TAG_COMPOUND)) {
				CompoundTag data = nbt.getCompound("ItemStorage");

				if (!data.isEmpty()) {
					this.itemStorage.clear();

					for (String key : data.getAllKeys()) {
						try {
							this.itemStorage.put(Integer.parseInt(key), ItemStack.parseOptional(holderLookup, data.getCompound(key)));
						}
						catch (Exception ex) {
							Logging.logMessage(org.apache.logging.log4j.Level.WARN, "Invalid key in ItemStorage NBT data for player. Stop messing with player NBT!", ex);
						}
					}
				}
			}

			if (nbt.contains("PortalMap", Tag.TAG_COMPOUND)) {
				CompoundTag data = nbt.getCompound("PortalMap");

				if (!data.isEmpty()) {
					this.portalCoordinatesMap.clear();

					for (String entry : data.getAllKeys()) {
						try {
							CompoundTag portalReturnTag = data.getCompound(entry);
							ResourceLocation fromDim = ResourceLocation.read(portalReturnTag.getString("FromDim")).getOrThrow();
							BlockPos portalPos = NbtUtils.readBlockPos(portalReturnTag, "PortalPos").orElseGet(() -> getPlayer().level().getSharedSpawnPos());
							ResourceKey<Level> toDimKey = ResourceKey.create(Registries.DIMENSION, ResourceLocation.read(entry).getOrThrow());
							ResourceKey<Level> fromDimKey = ResourceKey.create(Registries.DIMENSION, fromDim);

							this.portalCoordinatesMap.put(toDimKey, new GlobalPos(fromDimKey, portalPos));
						}
						catch (Exception e) {
							Logging.logMessage(org.apache.logging.log4j.Level.WARN, "Found invalid portal map data, has someone been tampering with files? Data: " + entry);
						}
					}
				}
			}

			if (nbt.contains("Checkpoint", Tag.TAG_COMPOUND)) {
				try {
					CompoundTag checkpointTag = nbt.getCompound("Checkpoint");
					double x = checkpointTag.getDouble("x");
					double y = checkpointTag.getDouble("y");
					double z = checkpointTag.getDouble("z");
					float pitch = checkpointTag.getFloat("pitch");
					float yaw = checkpointTag.getFloat("yaw");

					this.activeCheckpoint = new PositionAndRotation(x, y, z, pitch, yaw);
				}
				catch (NumberFormatException e) {
					Logging.logMessage(org.apache.logging.log4j.Level.WARN, "Found invalid checkpoint data, has someone been tampering with files?");
				}
			}

			if (nbt.contains("PatchouliBooks")) {
				ListTag data = nbt.getList("PatchouliBooks", Tag.TAG_STRING);

				this.needsPatchouliSync = true;

				if (!this.patchouliBooks.isEmpty())
					this.patchouliBooks.clear();

				for (Tag book : data) {
					ResourceLocation.read(book.getAsString()).resultOrPartial(err -> Logging.logMessage(org.apache.logging.log4j.Level.WARN, err)).ifPresent(this.patchouliBooks::add);
				}
			}
		}

		@Override
		public void save(CompoundTag nbt, HolderLookup.Provider holderLookup, boolean isPartial) {
			if (!this.patchouliBooks.isEmpty()) {
				ListTag data = new ListTag();

				for (ResourceLocation id : this.patchouliBooks) {
					data.add(StringTag.valueOf(id.toString()));
				}

				nbt.put("PatchouliBooks", data);
			}

			if (isPartial)
				return;

			if (!this.itemStorage.isEmpty()) {
				CompoundTag data = new CompoundTag();

				for (Int2ObjectMap.Entry<ItemStack> entry : this.itemStorage.int2ObjectEntrySet()) {
					data.put(String.valueOf(entry.getIntKey()), entry.getValue().save(holderLookup));
				}

				nbt.put("ItemStorage", data);
			}

			if (!this.portalCoordinatesMap.isEmpty()) {
				CompoundTag data = new CompoundTag();

				for (Map.Entry<ResourceKey<Level>, GlobalPos> entry : this.portalCoordinatesMap.entrySet()) {
					CompoundTag portalReturnTag = new CompoundTag();
					GlobalPos container = entry.getValue();

					portalReturnTag.putString("FromDim", container.dimension().location().toString());
					portalReturnTag.put("PortalPos", NbtUtils.writeBlockPos(container.pos()));

					data.put(entry.getKey().location().toString(), portalReturnTag);
				}

				nbt.put("PortalMap", data);
			}

			if (this.activeCheckpoint != null) {
				CompoundTag data = new CompoundTag();

				data.putDouble("x", this.activeCheckpoint.x());
				data.putDouble("y", this.activeCheckpoint.y());
				data.putDouble("z", this.activeCheckpoint.z());
				data.putFloat("pitch", this.activeCheckpoint.pitch());
				data.putFloat("yaw", this.activeCheckpoint.yaw());

				nbt.put("Checkpoint", data);
			}
		}
	}

	public final class Equipment implements AoAPlayerEventListener {
		private final Map<Holder<ArmorMaterial>, AdventArmourSetContainer> equippedByMaterial = new Object2ObjectOpenHashMap<>(4);
		private final EnumMap<AdventArmour.Piece, AdventArmour> equippedByPiece = new EnumMap<>(AdventArmour.Piece.class);
		private final List<DynamicEventSubscriber<?>> eventSubscribers = List.of(
				listener(LivingEquipmentChangeEvent.class, LivingEquipmentChangeEvent::getEntity, this::handleArmourChange),
				listener(PlayerTickEvent.Pre.class, this::handlePlayerTick),
				listener(EntityInvulnerabilityCheckEvent.class, EntityInvulnerabilityCheckEvent::getEntity, this::handleEntityInvulnerability),
				listener(MobEffectEvent.Applicable.class, MobEffectEvent.Applicable::getEntity, this::handleEffectApplicability),
				listener(LivingDamageEvent.Pre.class, LivingDamageEvent::getEntity, this::handlePreDamageApplication),
				whenTakingDamage(this::handleIncomingDamage),
				afterTakingDamage(this::handleAfterDamaged),
				whenAttacking(this::handleOutgoingAttack),
				afterAttacking(this::handleAfterAttacking),
				listener(LivingDeathEvent.class, LivingDeathEvent::getEntity, this::handlePlayerDeath));

		private boolean checkNewArmour = false;

		private Equipment() {}

		@Override
		public Player getPlayer() {
			return ServerPlayerDataManager.this.getPlayer();
		}

		@Override
		public boolean isStillValid() {
			return ServerPlayerDataManager.this.isStillValid();
		}

		@Override
		public List<DynamicEventSubscriber<? extends Event>> getEventSubscribers() {
			return this.eventSubscribers;
		}

		@Nullable
		public Holder<ArmorMaterial> getCurrentFullArmourSet() {
			AdventArmour setArmour = this.equippedByPiece.get(AdventArmour.Piece.FULL_SET);

			return setArmour != null ? setArmour.getMaterial() : null;
		}

		private void handleEntityInvulnerability(final EntityInvulnerabilityCheckEvent ev) {
			for (AdventArmourSetContainer wrapper : this.equippedByMaterial.values()) {
				wrapper.armour.checkDamageInvulnerability(getPlayer(), wrapper.equippedPieces(), ev);
			}
		}

		private void handleIncomingDamage(final LivingIncomingDamageEvent ev) {
			for (AdventArmourSetContainer wrapper : this.equippedByMaterial.values()) {
				wrapper.armour.handleIncomingDamage(getPlayer(), wrapper.equippedPieces(), ev);
			}
		}

		private void handleOutgoingAttack(final LivingIncomingDamageEvent ev) {
			for (AdventArmourSetContainer wrapper : this.equippedByMaterial.values()) {
				wrapper.armour.handleOutgoingAttack(getPlayer(), wrapper.equippedPieces(), ev);
			}
		}

		private void handlePreDamageApplication(LivingDamageEvent.Pre ev) {
			for (AdventArmourSetContainer wrapper : this.equippedByMaterial.values()) {
				wrapper.armour.beforeTakingDamage(getPlayer(), wrapper.equippedPieces(), ev);
			}
		}

		private void handleAfterDamaged(final LivingDamageEvent.Post ev) {
			for (AdventArmourSetContainer wrapper : this.equippedByMaterial.values()) {
				wrapper.armour.afterTakingDamage(getPlayer(), wrapper.equippedPieces(), ev);
			}
		}

		private void handleAfterAttacking(final LivingDamageEvent.Post ev) {
			for (AdventArmourSetContainer wrapper : this.equippedByMaterial.values()) {
				wrapper.armour.afterOutgoingAttack(getPlayer(), wrapper.equippedPieces(), ev);
			}
		}

		private void handlePlayerDeath(final LivingDeathEvent ev) {
			for (AdventArmourSetContainer wrapper : this.equippedByMaterial.values()) {
				wrapper.armour.onEntityDeath(getPlayer(), wrapper.equippedPieces(), ev);
			}
		}

		private void handleArmourChange(final LivingEquipmentChangeEvent ev) {
			if (ev.getSlot().isArmor())
				this.checkNewArmour = true;
		}

		private void handleEffectApplicability(MobEffectEvent.Applicable ev) {
			for (AdventArmourSetContainer wrapper : this.equippedByMaterial.values()) {
				wrapper.armour.onEffectApplication(getPlayer(), wrapper.equippedPieces(), ev);
			}
		}

		private void handlePlayerTick(final PlayerTickEvent.Pre ev) {
			if (!getPlayer().isAlive() || getPlayer().isSpectator())
				return;

			if (this.checkNewArmour)
				checkNewArmour();

			if (!checkEquippedItems())
				return;

			for (AdventArmourSetContainer container : this.equippedByMaterial.values()) {
				container.armour().onArmourTick(getPlayer(), container.equippedPieces());
			}
		}

		private void checkNewArmour() {
			boolean armourChanged = false;

			for (AdventArmour.Piece piece : AdventArmour.Piece.values()) {
				EquipmentSlot slot = piece.toVanillaSlot();

				if (slot == null)
					continue;

				AdventArmour existingArmour = this.equippedByPiece.get(piece);
				Item wornItem = getPlayer().getItemBySlot(slot).getItem();

				if (existingArmour == wornItem)
					continue;

				armourChanged = true;

				if (existingArmour != null)
					unequipAdventArmour(existingArmour, piece);

				AdventArmour newArmour = wornItem instanceof AdventArmour adventArmour ? adventArmour : null;

				this.equippedByPiece.put(piece, newArmour);

				if (newArmour != null && (getPlayer().getAbilities().instabuild || AoASkillReqReloadListener.canEquip(ServerPlayerDataManager.this, newArmour, false)))
					equipAdventArmour(newArmour, piece);
			}

			if (armourChanged) {
				AdventArmour oldSet = this.equippedByPiece.remove(AdventArmour.Piece.FULL_SET);
				AdventArmour newSet = null;

				if (ObjectUtil.allEquivalent((piece1, piece2) -> piece1 != null && piece2 != null && (piece1.getMaterial().is(piece2.getMaterial()) || piece1.isCompatibleWithAnySet() || piece2.isCompatibleWithAnySet()), this.equippedByPiece.values().toArray(new AdventArmour[0]))) {
					for (AdventArmour armour : this.equippedByPiece.values()) {
						if (!armour.isCompatibleWithAnySet()) {
							newSet = armour;

							break;
						}
					}
				}

				if (newSet != null)
					this.equippedByPiece.put(AdventArmour.Piece.FULL_SET, newSet);

				if (newSet != oldSet) {
					if (oldSet != null)
						unequipAdventArmour(oldSet, AdventArmour.Piece.FULL_SET);

					if (newSet != null)
						equipAdventArmour(newSet, AdventArmour.Piece.FULL_SET);
				}
			}
		}

		private boolean checkEquippedItems() {
			if (getPlayer().getAbilities().instabuild)
				return true;

			boolean updateInventory = false;
			boolean doArmourTick = true;

			for (InteractionHand hand : InteractionHand.values()) {
				ItemStack heldStack = getPlayer().getItemInHand(hand);

				if (!AoASkillReqReloadListener.canEquip(ServerPlayerDataManager.this, heldStack.getItem(), true)) {
					updateInventory = true;

					ItemHandlerHelper.giveItemToPlayer(getPlayer(), heldStack);
					getPlayer().setItemInHand(hand, ItemStack.EMPTY);
				}
			}

			for (AdventArmour.Piece piece : AdventArmour.Piece.values()) {
				if (piece == AdventArmour.Piece.FULL_SET)
					continue;

				AdventArmour armour = this.equippedByPiece.get(piece);

				if (armour != null && !AoASkillReqReloadListener.canEquip(ServerPlayerDataManager.this, armour, true)) {
					updateInventory = true;
					doArmourTick = false;
					EquipmentSlot slot = armour.getEquipmentSlot();

					ItemHandlerHelper.giveItemToPlayer(getPlayer(), getPlayer().getItemBySlot(slot));
					getPlayer().setItemSlot(slot, ItemStack.EMPTY);
					unequipAdventArmour(armour, piece);
				}
			}

			if (updateInventory)
				getPlayer().inventoryMenu.broadcastChanges();

			return doArmourTick;
		}

		private void equipAdventArmour(AdventArmour item, AdventArmour.Piece piece) {
			AdventArmourSetContainer pieceContainer = this.equippedByMaterial.computeIfAbsent(item.getMaterial(), material -> new AdventArmourSetContainer(item));

			item.onEquip(getPlayer(), piece, pieceContainer.equippedPieces);
			pieceContainer.equippedPieces().add(piece);
		}

		private void unequipAdventArmour(AdventArmour item, AdventArmour.Piece piece) {
			AdventArmourSetContainer pieceContainer = this.equippedByMaterial.get(item.getMaterial());

			if (pieceContainer != null) {
				item.onUnequip(getPlayer(), piece, pieceContainer.equippedPieces());
				pieceContainer.equippedPieces().remove(piece);

				if (pieceContainer.equippedPieces().isEmpty())
					this.equippedByMaterial.remove(item.getMaterial());
			}
		}

		private record AdventArmourSetContainer(AdventArmour armour, EnumSet<AdventArmour.Piece> equippedPieces) {
			private AdventArmourSetContainer(AdventArmour armour) {
				this(armour, EnumSet.noneOf(AdventArmour.Piece.class));
			}
		}
	}
}
