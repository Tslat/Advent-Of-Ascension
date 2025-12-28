package net.tslat.aoa3.client.player;

import com.mojang.blaze3d.platform.InputConstants;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.Util;
import net.minecraft.client.KeyMapping;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.tslat.aoa3.advent.AoAResourceCaching;
import net.tslat.aoa3.client.ClientOperations;
import net.tslat.aoa3.client.gui.adventgui.AdventGuiTabLore;
import net.tslat.aoa3.common.networking.AoANetworking;
import net.tslat.aoa3.common.networking.packets.adventplayer.PlayerAbilityKeybindTriggerPacket;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.player.AoAPlayerEventListener;
import net.tslat.aoa3.player.PlayerDataManager;
import net.tslat.aoa3.player.ability.AoAAbility;
import net.tslat.aoa3.player.resource.AoAResource;
import net.tslat.aoa3.player.skill.AoASkill;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnknownNullability;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;

import static net.tslat.aoa3.player.AoAPlayerEventListener.ListenerState.REMOVED;

public final class ClientPlayerDataManager implements PlayerDataManager {
	public static final ClientPlayerDataManager INSTANCE = Util.make(new ClientPlayerDataManager(), manager -> AoAResourceCaching.onClientLogout(manager::reset));

	private Player player;

	private final ConcurrentSkipListMap<AoASkill, AoASkill.Instance> skills = new ConcurrentSkipListMap<>(Comparator.comparing(AoARegistries.AOA_SKILLS::getKey));
	private final ConcurrentSkipListMap<AoAResource, AoAResource.Instance> resources = new ConcurrentSkipListMap<>(Comparator.comparing(AoARegistries.AOA_RESOURCES::getKey));
	private final ObjectArrayList<AoAPlayerEventListener> eventListeners = new ObjectArrayList<>();

	private final ConcurrentMap<KeyMapping, List<AoAPlayerKeybindListener>> keyListeners = new ConcurrentHashMap<>(2);

	private boolean isLegitimate = true;
	private int totalLevel = 0;

	public static ClientPlayerDataManager get() {
		return INSTANCE;
	}

	public void updatePlayerInstance(Player pl) {
		this.player = pl;
	}

	public void reset() {
		this.player = null;
		this.isLegitimate = true;
		this.totalLevel = 0;

		this.skills.clear();
		this.resources.clear();
		this.keyListeners.clear();

		for (AoAPlayerEventListener listener : this.eventListeners) {
			listener.disable(REMOVED, false);
		}

		this.eventListeners.clear();
	}

	@Override
	public Player getPlayer() {
		return player;
	}

	@Override
	public boolean isLegitimate() {
		return isLegitimate;
	}

	@Override
	public int getTotalLevel() {
		return totalLevel;
	}

	@Override
	public Collection<AoASkill.Instance> getSkills() {
		return this.skills.values();
	}

	@Override
	@NotNull
	public AoASkill.Instance getSkill(AoASkill skill) {
		return this.skills.getOrDefault(skill, AoASkills.DEFAULT);
	}

	@Override
	public AoAAbility.Instance getAbility(String abilityId) {
		for (AoASkill.Instance skill : getSkills()) {
			if (skill.getAbilityMap().containsKey(abilityId))
				return skill.getAbilityMap().get(abilityId);
		}

		return null;
	}

	@Override
	public Collection<AoAResource.Instance> getResources() {
		return resources.values();
	}

	@Override
	@NotNull
	public AoAResource.Instance getResource(AoAResource resource) {
		return resources.getOrDefault(resource, AoAResources.DEFAULT);
	}

	public void handleKeyInput(InputConstants.Key key) {
		if (ClientOperations.getPlayer() == null || this.keyListeners.isEmpty())
			return;

		List<String> abilities = null;

		for (KeyMapping keyMap : KeyMapping.MAP.getAll(key)) {
			for (AoAPlayerKeybindListener listener : this.keyListeners.getOrDefault(keyMap, List.of())) {
				if (listener.isListenerActive() && listener.getEventListener() instanceof AoAAbility.Instance abilityInstance && listener.shouldSendKeyPress()) {
					if (abilities == null)
						abilities = new ObjectArrayList<>(1);

					abilities.add(abilityInstance.getUniqueIdentifier());
				}
			}
		}

		if (abilities != null && !abilities.isEmpty())
			AoANetworking.sendToServer(new PlayerAbilityKeybindTriggerPacket(abilities));
	}

	private void updateTotalLevel() {
		totalLevel = 0;

		for (AoASkill.Instance skill : skills.values()) {
			totalLevel += 100 * skill.getCycles();
			totalLevel += skill.getLevel(true);
		}
	}

	public void loadFromNbt(CompoundTag baseTag) {
		isLegitimate = baseTag.getBoolean("legitimate");
		int hash = baseTag.getInt("hash");

		if (hash == 0)
			isLegitimate = false;

		skills.clear();
		resources.clear();

		if (baseTag.contains("skills")) {
			CompoundTag skillsNbt = baseTag.getCompound("skills");

			for (String id : skillsNbt.getAllKeys()) {
				AoASkill skill = AoASkills.getSkill(ResourceLocation.read(id).getOrThrow());
				AoASkill.Instance instance = skill.buildClientInstance(skillsNbt.getCompound(id));

				skills.put(skill, instance);
				addEventListener(instance);

				for (AoAAbility.Instance ability : instance.getAbilityMap().values()) {
					addEventListener(ability);
				}
			}

			updateTotalLevel();
		}

		if (baseTag.contains("resources")) {
			CompoundTag resourcesNbt = baseTag.getCompound("resources");

			for (String id : resourcesNbt.getAllKeys()) {
				AoAResource resource = AoAResources.getResource(ResourceLocation.read(id).getOrThrow());
				AoAResource.Instance instance = resource.buildClientInstance(resourcesNbt.getCompound(id));

				resources.put(resource, instance);
				addEventListener(instance);
			}
		}

		if (baseTag.contains("PatchouliBooks")) {
			ArrayList<ResourceLocation> books = new ArrayList<>();

			ListTag booksNbt = baseTag.getList("PatchouliBooks", Tag.TAG_STRING);

			for (Tag book : booksNbt) {
				books.add(ResourceLocation.read(book.getAsString()).getOrThrow());
			}

			AdventGuiTabLore.syncBooks(books);
		}
	}

	public void updateData(CompoundTag syncTag) {
		if (syncTag.contains("skills")) {
			CompoundTag skillsData = syncTag.getCompound("skills");

			for (String key : skillsData.getAllKeys()) {
				getSkill(AoASkills.getSkill(ResourceLocation.read(key).getOrThrow())).receiveSyncData(skillsData.getCompound(key));
			}

			updateTotalLevel();
		}

		if (syncTag.contains("resources")) {
			CompoundTag resourcesData = syncTag.getCompound("resources");

			for (String key : resourcesData.getAllKeys()) {
				getResource(AoAResources.getResource(ResourceLocation.read(key).getOrThrow())).receiveSyncData(resourcesData.getCompound(key));
			}
		}

		if (syncTag.contains("PatchouliBooks")) {
			ArrayList<ResourceLocation> books = new ArrayList<>();

			ListTag booksNbt = syncTag.getList("PatchouliBooks", Tag.TAG_STRING);

			for (Tag book : booksNbt) {
				books.add(ResourceLocation.read(book.getAsString()).getOrThrow());
			}

			AdventGuiTabLore.syncBooks(books);
		}

		if (syncTag.contains("legitimate"))
			isLegitimate = syncTag.getBoolean("legitimate");
	}

	@Override
	public void addEventListener(AoAPlayerEventListener listener) {
		if (this.player != ClientOperations.getPlayer())
			return;

		listener.createKeybindListener(keyListener -> this.keyListeners.computeIfAbsent(keyListener.getKeybind(), keyCode -> new ObjectArrayList<>(1)).add(keyListener));
		this.eventListeners.add(listener);

		if (!ClientOperations.isLocalServer())
			listener.registerEventSubscribers();
	}

	@Override
	public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider registryLookup) {
		return new CompoundTag();
	}

	@Override
	public void deserializeNBT(HolderLookup.Provider registryLookup, CompoundTag nbt) {}
}
