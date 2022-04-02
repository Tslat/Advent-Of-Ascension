package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.util.GsonHelper;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.event.custom.events.PlayerChangeXpEvent;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.NumberUtil;

public class FlatXpBoost extends AoAAbility.Instance {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.GAIN_SKILL_XP};

	private final float modifier;

	public FlatXpBoost(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.FLAT_XP_BOOST.get(), skill, data);

		this.modifier = GsonHelper.getAsFloat(data, "modifier");
	}

	public FlatXpBoost(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.FLAT_XP_BOOST.get(), skill, data);

		this.modifier = data.getFloat("modifier");
	}

	@Override
	protected void updateDescription(TranslatableComponent defaultDescription) {
		super.updateDescription(new TranslatableComponent(defaultDescription.getKey(),
				skill.getName(),
				NumberUtil.roundToNthDecimalPlace(modifier - 1f, 2)));
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
	public CompoundTag getSyncData(boolean forClientSetup) {
		CompoundTag data = super.getSyncData(forClientSetup);

		if (forClientSetup)
			data.putFloat("modifier", this.modifier);

		return data;
	}
}
