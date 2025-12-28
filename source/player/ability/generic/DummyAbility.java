package net.tslat.aoa3.player.ability.generic;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.util.GsonHelper;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.event.custom.events.PlayerLevelChangeEvent;
import net.tslat.aoa3.event.dynamic.DynamicEventSubscriber;
import net.tslat.aoa3.player.ability.AoAAbility;
import net.tslat.aoa3.player.skill.AoASkill;

import java.util.List;

public class DummyAbility extends AoAAbility.Instance {
	private final List<DynamicEventSubscriber<?>> eventSubscribers = List.of(
			listener(PlayerLevelChangeEvent.class, serverOnly(this::handleLevelChange)));

	private final MutableComponent displayName;

	public DummyAbility(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.DUMMY_ABILITY.get(), skill, data);

		this.displayName = Component.translatable(GsonHelper.getAsString(data, "display_name", ""));
	}

	public DummyAbility(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.DUMMY_ABILITY.get(), skill, data);

		this.displayName = Component.translatable(data.getString("display_name"));
	}

	@Override
	public List<DynamicEventSubscriber<?>> getEventSubscribers() {
		return this.eventSubscribers;
	}

	private void handleLevelChange(PlayerLevelChangeEvent ev) {
		if (getListenerState() == ListenerState.ACTIVE && !meetsRequirements())
			disable(ListenerState.DEACTIVATED, false);
	}

	@Override
	public MutableComponent getName() {
		return displayName;
	}

	@Override
	public CompoundTag getSyncData(boolean forClientSetup) {
		CompoundTag data = super.getSyncData(forClientSetup);

		if (forClientSetup)
			data.putString("display_name", ((TranslatableContents)this.displayName.getContents()).getKey());

		return data;
	}

	// Client Only
	@Override
	public boolean onGuiHover(int mouseX, int mouseY) {
		return false;
	}

	// Client Only
	@Override
	public boolean onGuiClick(int mouseX, int mouseY) {
		return false;
	}
}
