package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.text.TranslationTextComponent;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.event.custom.events.PlayerLevelChangeEvent;
import net.tslat.aoa3.player.skill.AoASkill;

public class DummyAbility extends AoAAbility.Instance {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.LEVEL_CHANGE};

	private final TranslationTextComponent displayName;

	public DummyAbility(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.DUMMY_ABILITY.get(), skill, data);

		this.displayName = new TranslationTextComponent(JSONUtils.getAsString(data, "display_name", ""));
	}

	public DummyAbility(AoASkill.Instance skill, CompoundNBT data) {
		super(AoAAbilities.DUMMY_ABILITY.get(), skill, data);

		this.displayName = new TranslationTextComponent(data.getString("display_name"));
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handleLevelChange(PlayerLevelChangeEvent ev) {
		if (getListenerState() == ListenerState.ACTIVE && !meetsRequirements())
			disable(ListenerState.DEACTIVATED, false);
	}

	@Override
	public TranslationTextComponent getName() {
		return displayName;
	}

	@Override
	public CompoundNBT getSyncData(boolean forClientSetup) {
		CompoundNBT data = super.getSyncData(forClientSetup);

		if (forClientSetup)
			data.putString("display_name", this.displayName.getKey());

		return data;
	}
}
