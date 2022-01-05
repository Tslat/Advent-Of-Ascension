package net.tslat.aoa3.player;

import com.google.common.collect.ArrayListMultimap;
import io.netty.util.internal.ConcurrentSet;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.util.Hand;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.items.ItemHandlerHelper;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.PlayerDataSyncPacket;
import net.tslat.aoa3.common.packet.packets.PlayerDataUpdatePacket;
import net.tslat.aoa3.common.packet.packets.ToastPopupPacket;
import net.tslat.aoa3.common.registration.AoAEnchantments;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.data.server.AoAResourcesReloadListener;
import net.tslat.aoa3.data.server.AoASkillReqReloadListener;
import net.tslat.aoa3.data.server.AoASkillsReloadListener;
import net.tslat.aoa3.event.custom.events.PlayerLevelChangeEvent;
import net.tslat.aoa3.integration.IntegrationManager;
import net.tslat.aoa3.object.item.armour.AdventArmour;
import net.tslat.aoa3.player.ability.AoAAbility;
import net.tslat.aoa3.player.resource.AoAResource;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.aoa3.util.AdvancementUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.world.teleporter.PortalCoordinatesContainer;
import org.apache.logging.log4j.Level;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.Stream;

import static net.tslat.aoa3.player.AoAPlayerEventListener.ListenerState.ACTIVE;
import static net.tslat.aoa3.player.AoAPlayerEventListener.ListenerState.DEACTIVATED;

public final class ServerPlayerDataManager implements AoAPlayerEventListener, PlayerDataManager {
	private ServerPlayerEntity player;

	private PlayerEquipment equipment;

	private final HashMap<AoASkill, AoASkill.Instance> skills = new HashMap<AoASkill, AoASkill.Instance>(10);
	private final HashMap<AoAResource, AoAResource.Instance> resources = new HashMap<AoAResource, AoAResource.Instance>(1);

	private final ArrayListMultimap<ListenerType, AoAPlayerEventListener> activeEventListeners = ArrayListMultimap.create();
	private final ArrayListMultimap<ListenerType, AoAPlayerEventListener> disabledEventListeners = ArrayListMultimap.create();
	private final HashSet<AoAPlayerEventListener> dirtyListeners = new HashSet<AoAPlayerEventListener>();

	private HashMap<RegistryKey<World>, PortalCoordinatesContainer> portalCoordinatesMap = new HashMap<RegistryKey<World>, PortalCoordinatesContainer>();
	private HashSet<ItemStack> itemStorage = null;

	private ConcurrentSet<ResourceLocation> patchouliBooks = null;
	private boolean syncBooks = false;

	private boolean isLegitimate = true;

	public ServerPlayerDataManager(ServerPlayerEntity player) {
		this.player = player;
		this.equipment = new PlayerEquipment(this);

		populateSkillsAndResources();
		gatherListeners();
	}

	@Override
	public ServerPlayerEntity player() {
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
	public void loadFromNbt(CompoundNBT baseTag) {
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
			CompoundNBT skillsNbt = baseTag.getCompound("skills");

			for (AoASkill.Instance skill : skills.values()) {
				String id = skill.type().getRegistryName().toString();

				if (skillsNbt.contains(id))
					skill.loadFromNbt(skillsNbt.getCompound(id));
			}
		}

		if (baseTag.contains("resources")) {
			CompoundNBT resourcesNbt = baseTag.getCompound("resources");

			for (AoAResource.Instance resource : resources.values()) {
				String id = resource.type().getRegistryName().toString();

				if (resourcesNbt.contains(id))
					resource.loadFromNbt(resourcesNbt.getCompound(id));
			}
		}

		if (baseTag.contains("ItemStorage")) {
			itemStorage = new HashSet<ItemStack>();
			CompoundNBT itemStorage = baseTag.getCompound("ItemStorage");
			int i = 0;

			while (itemStorage.contains(String.valueOf(i))) {
				this.itemStorage.add(ItemStack.of(itemStorage.getCompound(String.valueOf(i))));

				i++;
			}
		}

		if (baseTag.contains("PortalMap")) {
			CompoundNBT portalMapTag = baseTag.getCompound("PortalMap");

			for (String s : portalMapTag.getAllKeys()) {
				try {
					CompoundNBT portalReturnTag = portalMapTag.getCompound(s);
					ResourceLocation fromDim = new ResourceLocation(portalReturnTag.getString("FromDim"));
					double x = portalReturnTag.getDouble("PosX");
					double y = portalReturnTag.getDouble("PosY");
					double z = portalReturnTag.getDouble("PosZ");
					RegistryKey<World> toDimKey = RegistryKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(s));
					RegistryKey<World> fromDimKey = RegistryKey.create(Registry.DIMENSION_REGISTRY, fromDim);

					portalCoordinatesMap.put(toDimKey, new PortalCoordinatesContainer(fromDimKey, x, y, z));
				} catch (NumberFormatException e) {
					Logging.logMessage(Level.WARN, "Found invalid portal map data, has someone been tampering with files? Data: " + s);
				}
			}
		}

		if (baseTag.contains("PatchouliBooks")) {
			ListNBT booksNbt = baseTag.getList("PatchouliBooks", Constants.NBT.TAG_STRING);

			if (patchouliBooks == null) {
				patchouliBooks = new ConcurrentSet<ResourceLocation>();
			}
			else if (!patchouliBooks.isEmpty()) {
				patchouliBooks.clear();
			}

			for (INBT book : booksNbt) {
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

	public void addListener(AoAPlayerEventListener listener, boolean active, ListenerType... types) {
		ArrayListMultimap<ListenerType, AoAPlayerEventListener> holder = active ? this.activeEventListeners : this.disabledEventListeners;

		for (ListenerType type : types) {
			holder.put(type, listener);
		}
	}

	public List<AoAPlayerEventListener> getListeners(ListenerType eventType) {
		if (!activeEventListeners.containsKey(eventType))
			return Collections.emptyList();

		return activeEventListeners.get(eventType);
	}

	public void markListenerDirty(AoAPlayerEventListener listener) {
		dirtyListeners.add(listener);
	}

	@Override
	public void updatePlayerInstance(PlayerEntity player) {
		this.player = (ServerPlayerEntity)player;
	}

	private void selfDestruct() {
		player = null;
		equipment = null;
		patchouliBooks = null;

		skills.clear();
		resources.clear();
		portalCoordinatesMap.clear();
		activeEventListeners.clear();

		if (itemStorage != null)
			itemStorage.clear();
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

	public static void syncNewPlayer(ServerPlayerEntity pl) {
		ServerPlayerDataManager plData = PlayerUtil.getAdventPlayer(pl);

		if (plData.patchouliBooks == null && IntegrationManager.isPatchouliActive()) {
			plData.patchouliBooks = new ConcurrentSet<ResourceLocation>();

			plData.patchouliBooks.add(new ResourceLocation(AdventOfAscension.MOD_ID, "aoa_essentia"));
		}

		AoAPackets.messagePlayer(pl, new PlayerDataSyncPacket(plData.savetoNbt(true)));
	}

	public void doPlayerTick() {
		if (player == null || player.isSpectator() || player.level.isClientSide)
			return;

		if (!dirtyListeners.isEmpty()) {
			Iterator<AoAPlayerEventListener> listeners = dirtyListeners.iterator();

			while (listeners.hasNext()) {
				AoAPlayerEventListener listener = listeners.next();

				if (listener.getListenerState() == ACTIVE) {
					for (ListenerType type : listener.getListenerTypes()) {
						disabledEventListeners.remove(type, listener);
						activeEventListeners.put(type, listener);
					}
				}
				else {
					for (ListenerType type : listener.getListenerTypes()) {
						activeEventListeners.remove(type, listener);
						disabledEventListeners.put(type, listener);
					}
				}

				listeners.remove();
			}
		}

		equipment.handleEquipmentCheck(this);

		CompoundNBT syncData = null;
		CompoundNBT skillData = null;
		CompoundNBT resourceData = null;

		for (AoASkill.Instance skill : getSkills()) {
			if (skill.needsSync) {
				if (skillData == null)
					skillData = new CompoundNBT();

				skillData.put(skill.type().getRegistryName().toString(), skill.getSyncData(false));
			}
		}

		for (AoAResource.Instance resource : getResources()) {
			if (resource.needsSync) {
				if (resourceData == null)
					resourceData = new CompoundNBT();

				resourceData.put(resource.type().getRegistryName().toString(), resource.getSyncData(false));
			}
		}

		if (skillData != null) {
			if (syncData == null)
				syncData = new CompoundNBT();

			syncData.put("skills", skillData);
		}

		if (resourceData != null) {
			if (syncData == null)
				syncData = new CompoundNBT();

			syncData.put("resources", resourceData);
		}

		if (syncBooks) {
			if (syncData == null)
				syncData = new CompoundNBT();

			ListNBT booksNbt = new ListNBT();

			for (ResourceLocation id : patchouliBooks) {
				booksNbt.add(StringNBT.valueOf(id.toString()));
			}

			syncData.put("patchouliBooks", booksNbt);
		}

		if (syncData != null) {
			syncData.putBoolean("legitimate", this.isLegitimate);

			AoAPackets.messagePlayer(player, new PlayerDataUpdatePacket(syncData));
		}
	}

	private void storeInterventionItems() {
		if (itemStorage == null)
			itemStorage = new HashSet<ItemStack>();

		for (int i = 0; i < player.inventory.getContainerSize(); i++) {
			ItemStack stack = player.inventory.getItem(i);

			if (EnchantmentHelper.getItemEnchantmentLevel(AoAEnchantments.INTERVENTION.get(), stack) > 0) {
				if (RandomUtil.oneInNChance(5))
					stack = ItemUtil.removeEnchantment(stack, AoAEnchantments.INTERVENTION.get());

				itemStorage.add(stack);
				player.inventory.setItem(i, ItemStack.EMPTY);
			}
		}

		for (int i = 0; i < 4; i++) {
			ItemStack stack = player.inventory.armor.get(i);

			if (EnchantmentHelper.getItemEnchantmentLevel(AoAEnchantments.INTERVENTION.get(), stack) > 0) {
				if (RandomUtil.oneInNChance(5))
					stack = ItemUtil.removeEnchantment(stack, AoAEnchantments.INTERVENTION.get());

				itemStorage.add(stack);
				player.inventory.armor.set(i, ItemStack.EMPTY);
			}
		}
	}

	public void returnItemStorage() {
		if (itemStorage != null) {
			for (ItemStack stack : itemStorage) {
				ItemUtil.givePlayerItemOrDrop(player, stack);
			}

			itemStorage = null;
		}
	}

	public void storeItems(@Nonnull Collection<ItemStack> stacks) {
		if (itemStorage == null)
			itemStorage = new HashSet<ItemStack>();

		itemStorage.addAll(stacks);
	}

	public void storeItems(@Nonnull ItemStack... stacks) {
		storeItems(Arrays.asList(stacks));
	}

	public void setPortalReturnLocation(RegistryKey<World> toDim, PortalCoordinatesContainer coords) {
		portalCoordinatesMap.put(toDim, coords);
	}

	public void removePortalReturnLocation(RegistryKey<World> toDim) {
		portalCoordinatesMap.remove(toDim);
	}

	public void flushPortalReturnLocations() {
		portalCoordinatesMap.clear();
	}

	public PortalCoordinatesContainer getPortalReturnLocation(RegistryKey<World> toDim) {
		return portalCoordinatesMap.get(toDim);
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
		Stream.of(activeEventListeners.values(), disabledEventListeners.values()).forEach(collection -> collection.forEach(listener -> {
			if (listener.getListenerState() == ACTIVE) {
				if (!listener.meetsRequirements())
					listener.disable(ListenerState.DEACTIVATED, false);
			}
			else if (listener.getListenerState() == DEACTIVATED) {
				if (listener.meetsRequirements()) {
					listener.reenable(false);

					if (listener instanceof AoAAbility.Instance) {
						AoAAbility.Instance ability = (AoAAbility.Instance)listener;

						AoAPackets.messagePlayer(player, new ToastPopupPacket(ability.getSkill().type(), ability.type()));
					}
				}
			}
		}));
	}

	@Override
	public void handleArmourChange(final LivingEquipmentChangeEvent ev) {
		equipment().markDirty();
	}

	public void checkAndUpdateLegitimacy() {
		if (player != null) {
			Advancement adv = AdvancementUtil.getAdvancement(new ResourceLocation(AdventOfAscension.MOD_ID, "overworld/by_the_books"));

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
	public boolean isLegitimate() {
		return this.isLegitimate;
	}

	@Override
	public int getTotalLevel() {
		return 0;
	}

	public void addPatchouliBook(ResourceLocation book) {
		if (patchouliBooks == null)
			patchouliBooks = new ConcurrentSet<ResourceLocation>();

		patchouliBooks.add(book);
		syncBooks = true;
	}

	@Override
	public void handlePlayerDataClone(final PlayerEvent.Clone ev) {
		ServerPlayerDataManager sourceData = PlayerUtil.getAdventPlayer((ServerPlayerEntity)ev.getOriginal());

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
		this.itemStorage = sourceData.itemStorage;
		this.patchouliBooks = sourceData.patchouliBooks;
		this.isLegitimate = sourceData.isLegitimate;

		AoAScheduler.scheduleSyncronisedTask(sourceData::selfDestruct, 1);
	}

	public CompoundNBT savetoNbt(boolean forClientSync) {
		CompoundNBT baseTag = new CompoundNBT();

		if (!skills.isEmpty()) {
			CompoundNBT skillsNbt = new CompoundNBT();

			for (AoASkill.Instance skill : skills.values()) {
				skillsNbt.put(skill.type().getRegistryName().toString(), forClientSync ? skill.getSyncData(true) : skill.saveToNbt());
			}

			if (!skillsNbt.isEmpty())
				baseTag.put("skills", skillsNbt);
		}

		if (!resources.isEmpty()) {
			CompoundNBT resourcesNbt = new CompoundNBT();

			for (AoAResource.Instance resource : resources.values()) {
				resourcesNbt.put(resource.type().getRegistryName().toString(), forClientSync ? resource.getSyncData(true) : resource.saveToNbt());
			}

			if (!resourcesNbt.isEmpty())
				baseTag.put("resources", resourcesNbt);
		}

		if (patchouliBooks != null) {
			ListNBT booksNbt = new ListNBT();

			for (ResourceLocation id : patchouliBooks) {
				booksNbt.add(StringNBT.valueOf(id.toString()));
			}

			baseTag.put("PatchouliBooks", booksNbt);
		}

		if (!forClientSync) {
			if (itemStorage != null) {
				CompoundNBT itemStorage = new CompoundNBT();
				int i = 0;

				for (ItemStack stack : this.itemStorage) {
					itemStorage.put(String.valueOf(i), stack.serializeNBT());
					i++;
				}

				baseTag.put("ItemStorage", itemStorage);
			}

			if (!portalCoordinatesMap.isEmpty()) {
				CompoundNBT portalCoordinatesNBT = new CompoundNBT();

				for (Map.Entry<RegistryKey<World>, PortalCoordinatesContainer> entry : portalCoordinatesMap.entrySet()) {
					CompoundNBT portalReturnTag = new CompoundNBT();
					PortalCoordinatesContainer container = entry.getValue();

					portalReturnTag.putString("FromDim", container.fromDim.location().toString());
					portalReturnTag.putDouble("PosX", container.x);
					portalReturnTag.putDouble("PosY", container.y);
					portalReturnTag.putDouble("PosZ", container.z);

					portalCoordinatesNBT.put(entry.getKey().location().toString(), portalReturnTag);
				}

				baseTag.put("PortalMap", portalCoordinatesNBT);
			}
		}

		baseTag.putBoolean("legitimate", isLegitimate);
		baseTag.putInt("hash", baseTag.hashCode());

		return baseTag;
	}

	public final class PlayerEquipment implements AoAPlayerEventListener {
		private final ServerPlayerDataManager playerDataManager;

		private HashMap<String, Integer> cooldowns = new HashMap<String, Integer>(1);
		private HashMap<AdventArmour.Type, ArmourEffectWrapper> armourMap = new HashMap<AdventArmour.Type, ArmourEffectWrapper>(4);

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
			return currentFullSet != null ? currentFullSet.setType() : AdventArmour.Type.NONE;
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

			armourChanged = checkAndHandleArmourSwap(boots, player.inventory.armor.get(0).getItem(), EquipmentSlotType.FEET);
			armourChanged |= checkAndHandleArmourSwap(legs, player.inventory.armor.get(1).getItem(), EquipmentSlotType.LEGS);
			armourChanged |= checkAndHandleArmourSwap(body, player.inventory.armor.get(2).getItem(), EquipmentSlotType.CHEST);
			armourChanged |= checkAndHandleArmourSwap(helmet, player.inventory.armor.get(3).getItem(), EquipmentSlotType.HEAD);

			AdventArmour oldSet = currentFullSet;

			if (armourChanged) {
				if (boots != null && legs != null && body != null && helmet != null && boots.setType() == legs.setType() && legs.setType() == body.setType() && body.isSetHelmet(helmet)) {
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

		private boolean checkAndHandleArmourSwap(@Nullable AdventArmour currentPiece, @Nonnull Item newPiece, @Nonnull EquipmentSlotType slot) {
			boolean changed = false;

			if (newPiece != currentPiece) {
				changed = true;

				if (currentPiece != null)
					unequipAdventArmour(playerDataManager, currentPiece, slot);

				switch (slot) {
					case FEET:
						boots = newPiece instanceof AdventArmour ? (AdventArmour)newPiece : null;

						if (boots != null && AoASkillReqReloadListener.canEquip(playerDataManager, boots))
							equipAdventArmour(playerDataManager, boots, slot);
						break;
					case LEGS:
						legs = newPiece instanceof AdventArmour ? (AdventArmour)newPiece : null;

						if (legs != null && AoASkillReqReloadListener.canEquip(playerDataManager, legs))
							equipAdventArmour(playerDataManager, legs, slot);
						break;
					case CHEST:
						body = newPiece instanceof AdventArmour ? (AdventArmour)newPiece : null;

						if (body != null && AoASkillReqReloadListener.canEquip(playerDataManager, body))
							equipAdventArmour(playerDataManager, body, slot);
						break;
					case HEAD:
						helmet = newPiece instanceof AdventArmour ? (AdventArmour)newPiece : null;

						if (helmet != null && AoASkillReqReloadListener.canEquip(playerDataManager, helmet))
							equipAdventArmour(playerDataManager, helmet, slot);
						break;
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
				for (Hand hand : Hand.values()) {
					ItemStack heldStack = player.getItemInHand(hand);

					if (!AoASkillReqReloadListener.canEquip(playerDataManager, heldStack.getItem())) {
						ItemHandlerHelper.giveItemToPlayer(player, heldStack);
						player.setItemInHand(hand, ItemStack.EMPTY);
					}
				}

				if (boots != null && !AoASkillReqReloadListener.canEquip(playerDataManager, boots)) {
					doArmourTick = false;

					ItemHandlerHelper.giveItemToPlayer(player, player.inventory.armor.get(0));
					player.inventory.armor.set(0, ItemStack.EMPTY);
					unequipAdventArmour(playerDataManager, boots, EquipmentSlotType.FEET);
				}

				if (legs != null && !AoASkillReqReloadListener.canEquip(playerDataManager, legs)) {
					doArmourTick = false;

					ItemHandlerHelper.giveItemToPlayer(player, player.inventory.armor.get(1));
					player.inventory.armor.set(1, ItemStack.EMPTY);
					unequipAdventArmour(playerDataManager, legs, EquipmentSlotType.LEGS);
				}

				if (body != null && !AoASkillReqReloadListener.canEquip(playerDataManager, body)) {
					doArmourTick = false;

					ItemHandlerHelper.giveItemToPlayer(player, player.inventory.armor.get(2));
					player.inventory.armor.set(2, ItemStack.EMPTY);
					unequipAdventArmour(playerDataManager, body, EquipmentSlotType.CHEST);
				}

				if (helmet != null && !AoASkillReqReloadListener.canEquip(playerDataManager, helmet)) {
					doArmourTick = false;

					ItemHandlerHelper.giveItemToPlayer(player, player.inventory.armor.get(3));
					player.inventory.armor.set(3, ItemStack.EMPTY);
					unequipAdventArmour(playerDataManager, helmet, EquipmentSlotType.HEAD);
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

		private void equipAdventArmour(ServerPlayerDataManager plData, AdventArmour item, @Nullable EquipmentSlotType slot) {
			item.onEquip(plData, slot);

			ArmourEffectWrapper armourEffectWrapper = armourMap.get(item.setType());

			if (slot != null) {
				if (armourEffectWrapper == null) {
					armourMap.put(item.setType(), new ArmourEffectWrapper(item, slot));
				}
				else {
					armourEffectWrapper.currentSlots.add(slot);
				}
			}
		}

		private void unequipAdventArmour(ServerPlayerDataManager plData, AdventArmour item, @Nullable EquipmentSlotType slot) {
			item.onUnequip(plData, slot);

			ArmourEffectWrapper armourEffectWrapper = armourMap.get(item.setType());

			if (armourEffectWrapper != null && slot != null) {
				if (armourEffectWrapper.currentSlots.size() <= 1) {
					armourMap.remove(item.setType());
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
			private final HashSet<EquipmentSlotType> currentSlots = new HashSet<EquipmentSlotType>(4);

			private ArmourEffectWrapper(AdventArmour armour, EquipmentSlotType firstSlotEquipped) {
				this.armour = armour;

				currentSlots.add(firstSlotEquipped);
			}
		}
	}
}
