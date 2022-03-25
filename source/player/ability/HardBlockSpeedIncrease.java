package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.player.skill.AoASkill;

public class HardBlockSpeedIncrease extends ScalableModAbility {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.BLOCK_BREAK_SPEED};

	public HardBlockSpeedIncrease(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.HARD_BLOCK_SPEED_INCREASE.get(), skill, data);
	}

	public HardBlockSpeedIncrease(AoASkill.Instance skill, CompoundNBT data) {
		super(AoAAbilities.HARD_BLOCK_SPEED_INCREASE.get(), skill, data);
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handleHarvestSpeedCheck(PlayerEvent.BreakSpeed ev) {
		float hardness = ev.getState().getDestroySpeed(ev.getPlayer().level, ev.getPos());

		if (hardness > 2)
			ev.setNewSpeed(ev.getNewSpeed() * (1 + (getScaledValue() * ((hardness - 2) / 48f))));
	}
}
