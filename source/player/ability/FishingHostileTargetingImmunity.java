package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.CompoundNBT;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.player.skill.AoASkill;

public class FishingHostileTargetingImmunity extends AoAAbility.Instance {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.CUSTOM};

	public FishingHostileTargetingImmunity(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.HAULING_GLOWING_FISH.get(), skill, data);
	}

	public FishingHostileTargetingImmunity(AoASkill.Instance skill, CompoundNBT data) {
		super(AoAAbilities.HAULING_GLOWING_FISH.get(), skill, data);
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handleCustomInteraction(String interactionType, Object data) {
		if (interactionType.equals("hauling_spawn_fish"))
			((Entity)data).setGlowing(true);
	}
}
