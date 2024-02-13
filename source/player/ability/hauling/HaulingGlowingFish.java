package net.tslat.aoa3.player.ability.hauling;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.player.ability.AoAAbility;
import net.tslat.aoa3.player.skill.AoASkill;

public class HaulingGlowingFish extends AoAAbility.Instance {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.CUSTOM};

	public HaulingGlowingFish(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.HAULING_GLOWING_FISH.get(), skill, data);
	}

	public HaulingGlowingFish(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.HAULING_GLOWING_FISH.get(), skill, data);
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handleCustomInteraction(String interactionType, Object data) {
		if (interactionType.equals("hauling_spawn_fish"))
			((Entity)data).setGlowingTag(true);
	}
}
