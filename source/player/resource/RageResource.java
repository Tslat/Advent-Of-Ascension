package net.tslat.aoa3.player.resource;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.player.PlayerDataManager;

public class RageResource extends AoAResource.Instance {
	private final float maxValue;
	private final float perTickDrain;

	private float value = 0;

	public RageResource(PlayerDataManager plData, JsonObject jsonData) {
		super(AoAResources.RAGE.get(), plData);

		this.maxValue = Math.max(0, JSONUtils.getAsFloat(jsonData, "max_value"));
		this.perTickDrain = JSONUtils.getAsFloat(jsonData, "per_tick_drain");
	}

	public RageResource(CompoundNBT nbtData) {
		super(AoAResources.RAGE.get(), null);

		this.maxValue = nbtData.getFloat("max_value");
		this.perTickDrain = nbtData.getFloat("per_tick_drain");
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return new ListenerType[] {
				ListenerType.INCOMING_ATTACK_DURING,
				ListenerType.PLAYER_TICK
		};
	}

	@Override
	public float getCurrentValue() {
		return this.value;
	}

	@Override
	public void setValue(float amount) {
		this.value = MathHelper.clamp(amount, 0, getMaxValue());
	}

	@Override
	public float getMaxValue() {
		return maxValue;
	}

	@Override
	public void handleIncomingAttack(LivingHurtEvent ev) {
		if (ev.getSource().getEntity() != null)
			this.value = Math.min(getMaxValue(), this.value + ev.getAmount());
	}

	@Override
	public void handlePlayerTick(TickEvent.PlayerTickEvent ev) {
		if (this.value > 0)
			this.value -= perTickDrain;
	}

	@Override
	public CompoundNBT getSyncData(boolean forClientSetup) {
		CompoundNBT nbt = new CompoundNBT();

		if (forClientSetup) {
			nbt.putFloat("max_value", maxValue);
			nbt.putFloat("per_tick_drain", perTickDrain);
		}
		else {
			nbt.putFloat("value", getCurrentValue());
		}

		return nbt;
	}

	@Override
	public void receiveSyncData(CompoundNBT data) {
		this.value = data.getFloat("value");
	}
}
