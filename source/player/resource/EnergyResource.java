package net.tslat.aoa3.player.resource;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.player.PlayerDataManager;

import javax.annotation.Nonnull;

public class EnergyResource extends AoAResource.Instance {
	private final float maxValue;
	private final int dischargeDelay;
	private final int hitDelay;
	private final float regenAmount;

	private long currentDelay = 0;
	private float value = 0;

	public EnergyResource(PlayerDataManager plData, JsonObject jsonData) {
		super(AoAResources.ENERGY.get(), plData);

		this.maxValue = Math.max(0, JSONUtils.getAsFloat(jsonData, "max_value"));
		this.dischargeDelay = JSONUtils.getAsInt(jsonData, "delay_on_empty");
		this.hitDelay = JSONUtils.getAsInt(jsonData, "delay_on_hit");
		this.regenAmount = JSONUtils.getAsFloat(jsonData, "regen_per_tick");
	}

	public EnergyResource(CompoundNBT nbtData) {
		super(AoAResources.ENERGY.get(), null);

		this.maxValue = nbtData.getFloat("max_value");
		this.dischargeDelay = nbtData.getInt("delay_on_empty");
		this.hitDelay = nbtData.getInt("delay_on_hit");
		this.regenAmount = nbtData.getFloat("regen_per_tick");
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return new ListenerType[] {
				ListenerType.PLAYER_TICK,
				ListenerType.INCOMING_ATTACK_AFTER
		};
	}

	@Override
	public float getCurrentValue() {
		return this.value;
	}

	@Override
	public boolean consume(float amount, boolean consumeIfInsufficient) {
		boolean success = super.consume(amount, consumeIfInsufficient);

		if (success && getCurrentValue() <= 0)
			this.currentDelay += this.dischargeDelay;

		return success;
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
	public void handlePlayerTick(TickEvent.PlayerTickEvent ev) {
		if (currentDelay > 0) {
			currentDelay--;
		}
		else if (this.value < getMaxValue()) {
			addValue(regenAmount);
		}
	}

	@Override
	public void handlePostIncomingAttack(LivingDamageEvent ev) {
		if (ev.getAmount() > 0 && currentDelay < hitDelay)
			currentDelay = hitDelay;
	}

	@Nonnull
	@Override
	public CompoundNBT saveToNbt() {
		return new CompoundNBT();
	}

	@Override
	public CompoundNBT getSyncData(boolean forClientSetup) {
		CompoundNBT data = new CompoundNBT();

		if (forClientSetup) {
			data.putFloat("max_value", maxValue);
			data.putInt("delay_on_empty", dischargeDelay);
			data.putInt("delay_on_hit", hitDelay);
			data.putFloat("regen_per_tick", regenAmount);
		}
		else {
			data.putFloat("value", getCurrentValue());
		}

		return data;
	}

	@Override
	public void receiveSyncData(CompoundNBT data) {
		if (data.contains("value"))
			this.value = data.getFloat("value");
	}
}
