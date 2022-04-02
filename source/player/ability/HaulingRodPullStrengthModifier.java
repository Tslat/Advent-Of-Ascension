package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.event.custom.events.HaulingRodPullEntityEvent;
import net.tslat.aoa3.player.skill.AoASkill;

import static net.tslat.aoa3.player.AoAPlayerEventListener.ListenerType.HAULING_ROD_PULL_ENTITY;

public class HaulingRodPullStrengthModifier extends ScalableModAbility {
	private static final ListenerType[] LISTENERS = new ListenerType[] {HAULING_ROD_PULL_ENTITY};

	public HaulingRodPullStrengthModifier(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.HAULING_ROD_PULL_STRENGTH.get(), skill, data);
	}

	public HaulingRodPullStrengthModifier(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.HAULING_ROD_PULL_STRENGTH.get(), skill, data);
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handleHaulingRodPullEntity(HaulingRodPullEntityEvent ev) {
		ev.setPullStrength(ev.getPullStrength() * (1 + getScaledValue()));
	}
}
