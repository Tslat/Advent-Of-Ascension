package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.text.TranslationTextComponent;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.event.custom.events.PlayerChangeXpEvent;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.NumberUtil;

public class FlatXpBoost extends AoAAbility.Instance {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.GAIN_SKILL_XP};

	private final float modifier;

	public FlatXpBoost(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.FLAT_XP_BOOST.get(), skill, data);

		this.modifier = JSONUtils.getAsFloat(data, "modifier");
	}

	public FlatXpBoost(AoASkill.Instance skill, CompoundNBT data) {
		super(AoAAbilities.FLAT_XP_BOOST.get(), skill, data);

		this.modifier = data.getFloat("modifier");
	}

	@Override
	public TranslationTextComponent getDescription() {
		return new TranslationTextComponent(super.getDescription().getKey(), skill.getName(), NumberUtil.roundToNthDecimalPlace(modifier - 1f, 2));
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handleSkillXpGain(PlayerChangeXpEvent ev) {
		ev.setXpGain(ev.getNewXpGain() * modifier);
	}

	@Override
	public CompoundNBT getSyncData(boolean forClientSetup) {
		CompoundNBT data = super.getSyncData(forClientSetup);

		if (forClientSetup)
			data.putFloat("modifier", this.modifier);

		return data;
	}
}
