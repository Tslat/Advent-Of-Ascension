package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.GsonHelper;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.player.skill.AoASkill;

public class FishingXpBoost extends ScalableModAbility {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.GAIN_VANILLA_XP};

	private final boolean useAddition;

	public FishingXpBoost(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.FISHING_XP_BOOST.get(), skill, data);

		this.useAddition = GsonHelper.getAsBoolean(data, "use_addition", false);
	}

	public FishingXpBoost(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.FISHING_XP_BOOST.get(), skill, data);

		this.useAddition = data.getBoolean("use_addition");
	}

	@Override
	protected boolean isPercent() {
		return !useAddition;
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handleVanillaXpGain(PlayerXpEvent.XpChange ev) {
		if (getPlayer().fishing != null) {
			float xp = ev.getAmount();

			if (useAddition) {
				xp += getScaledValue();
			}
			else {
				xp *= 1 + getScaledValue();
			}

			ev.setAmount((int)xp);
		}
	}

	@Override
	public CompoundTag getSyncData(boolean forClientSetup) {
		CompoundTag data = super.getSyncData(forClientSetup);

		if (forClientSetup)
			data.putBoolean("use_addition", this.useAddition);

		return data;
	}
}
