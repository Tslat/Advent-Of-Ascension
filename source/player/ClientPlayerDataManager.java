package net.tslat.aoa3.player;

import com.google.common.collect.ArrayListMultimap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.tslat.aoa3.client.ClientOperations;
import net.tslat.aoa3.client.gui.adventgui.AdventGuiTabLore;
import net.tslat.aoa3.common.packet.AoANetworking;
import net.tslat.aoa3.common.packet.packets.PlayerAbilityKeybindPacket;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.player.ability.AoAAbility;
import net.tslat.aoa3.player.resource.AoAResource;
import net.tslat.aoa3.player.skill.AoASkill;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

public final class ClientPlayerDataManager implements PlayerDataManager {
	private LocalPlayer player;

	private final ConcurrentSkipListMap<AoASkill, AoASkill.Instance> skills = new ConcurrentSkipListMap<>(Comparator.comparing(AoARegistries.AOA_SKILLS::getId));
	private final ConcurrentSkipListMap<AoAResource, AoAResource.Instance> resources = new ConcurrentSkipListMap<>(Comparator.comparing(AoARegistries.AOA_RESOURCES::getId));
	private final ArrayListMultimap<AoAPlayerEventListener.ListenerType, AoAPlayerEventListener> eventListeners = ArrayListMultimap.create();

	private final ConcurrentHashMap<Integer, ArrayList<AoAPlayerEventListener>> keyListeners = new ConcurrentHashMap<>(1);

	private boolean isLegitimate = true;
	private int totalLevel = 0;

	public static ClientPlayerDataManager get() {
		return ClientOperations.CLIENT_PLAYER_DATA;
	}

	@Override
	public void updatePlayerInstance(Player pl) {
		player = (LocalPlayer)pl;
	}

	public void reset() {
		player = null;
		isLegitimate = true;
		totalLevel = 0;

		skills.clear();
		resources.clear();
		keyListeners.clear();
	}

	@Override
	public LocalPlayer player() {
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
		return skills.values();
	}

	@Override
	@Nonnull
	public AoASkill.Instance getSkill(AoASkill skill) {
		return skills.getOrDefault(skill, AoASkills.DEFAULT);
	}

	@Override
	public Collection<AoAResource.Instance> getResources() {
		return resources.values();
	}

	@Override
	@Nonnull
	public AoAResource.Instance getResource(AoAResource resource) {
		return resources.getOrDefault(resource, AoAResources.DEFAULT);
	}

	public void handleKeyInput(int keycode) {
		if (Minecraft.getInstance().player == null || keyListeners.isEmpty() || !keyListeners.containsKey(keycode))
			return;

		ArrayList<AoAPlayerEventListener> listeners = keyListeners.get(keycode);
		ArrayList<String> abilities = new ArrayList<>(listeners.size());

		for (AoAPlayerEventListener listener : listeners) {
			if (listener.getListenerState() == AoAPlayerEventListener.ListenerState.ACTIVE && listener.shouldSendKeyPress())
				abilities.add(((AoAAbility.Instance)listener).getUniqueIdentifier());
		}

		if (!abilities.isEmpty())
			AoANetworking.sendToServer(new PlayerAbilityKeybindPacket(abilities));
	}

	private void updateTotalLevel() {
		totalLevel = 0;

		for (AoASkill.Instance skill : skills.values()) {
			totalLevel += 100 * skill.getCycles();
			totalLevel += skill.getLevel(true);
		}
	}

	@Override
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
				AoASkill skill = AoASkills.getSkill(new ResourceLocation(id));
				AoASkill.Instance instance = skill.buildClientInstance(skillsNbt.getCompound(id));

				skills.put(skill, instance);
				checkForListeners(instance);

				for (AoAAbility.Instance ability : instance.getAbilityMap().values()) {
					checkForListeners(ability);
				}
			}

			updateTotalLevel();
		}

		if (baseTag.contains("resources")) {
			CompoundTag resourcesNbt = baseTag.getCompound("resources");

			for (String id : resourcesNbt.getAllKeys()) {
				AoAResource resource = AoAResources.getResource(new ResourceLocation(id));
				AoAResource.Instance instance = resource.buildClientInstance(resourcesNbt.getCompound(id));

				resources.put(resource, instance);
				checkForListeners(instance);
			}
		}

		if (baseTag.contains("PatchouliBooks")) {
			ArrayList<ResourceLocation> books = new ArrayList<>();

			ListTag booksNbt = baseTag.getList("PatchouliBooks", Tag.TAG_STRING);

			for (Tag book : booksNbt) {
				books.add(new ResourceLocation(book.getAsString()));
			}

			AdventGuiTabLore.syncBooks(books);
		}
	}

	@Override
	public void addListener(AoAPlayerEventListener listener, boolean active, AoAPlayerEventListener.ListenerType... types) {
		for (AoAPlayerEventListener.ListenerType type : types) {
			eventListeners.put(type, listener);
		}
	}

	@Override
	public List<AoAPlayerEventListener> getListeners(AoAPlayerEventListener.ListenerType eventType) {
		return eventListeners.get(eventType);
	}

	public void updateData(CompoundTag syncTag) {
		if (syncTag.contains("skills")) {
			CompoundTag skillsData = syncTag.getCompound("skills");

			for (String key : skillsData.getAllKeys()) {
				getSkill(AoASkills.getSkill(new ResourceLocation(key))).receiveSyncData(skillsData.getCompound(key));
			}

			updateTotalLevel();
		}

		if (syncTag.contains("resources")) {
			CompoundTag resourcesData = syncTag.getCompound("resources");

			for (String key : resourcesData.getAllKeys()) {
				getResource(AoAResources.getResource(new ResourceLocation(key))).receiveSyncData(resourcesData.getCompound(key));
			}
		}

		if (syncTag.contains("PatchouliBooks")) {
			ArrayList<ResourceLocation> books = new ArrayList<>();

			ListTag booksNbt = syncTag.getList("PatchouliBooks", Tag.TAG_STRING);

			for (Tag book : booksNbt) {
				books.add(new ResourceLocation(book.getAsString()));
			}

			AdventGuiTabLore.syncBooks(books);
		}

		if (syncTag.contains("legitimate"))
			isLegitimate = syncTag.getBoolean("legitimate");
	}

	private void checkForListeners(AoAPlayerEventListener listener) {
		for (AoAPlayerEventListener.ListenerType type : listener.getListenerTypes()) {
			if (type == AoAPlayerEventListener.ListenerType.KEY_INPUT) {
				int keyCode = listener.getKeybind().getKey().getValue();

				if (!keyListeners.containsKey(keyCode))
					keyListeners.putIfAbsent(keyCode, new ArrayList<>(1));

				keyListeners.get(keyCode).add(listener);

				return;
			}
			else {
				addListener(listener, listener.meetsRequirements(), type);
			}
		}
	}
}
