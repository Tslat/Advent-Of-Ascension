package net.tslat.aoa3.player.resource;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.Mth;
import net.neoforged.neoforge.event.TickEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.player.ServerPlayerDataManager;

import javax.annotation.Nonnull;

public class EnergyResource extends AoAResource.Instance {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.PLAYER_TICK, ListenerType.INCOMING_ATTACK_AFTER};

	private final float maxValue;
	private final int dischargeDelay;
	private final int hitDelay;
	private final float regenAmount;

	private int currentDelay = 0;
	private float value = 0;

	public EnergyResource(ServerPlayerDataManager plData, JsonObject jsonData) {
		super(AoAResources.ENERGY.get(), plData);

		this.maxValue = Math.max(0, GsonHelper.getAsFloat(jsonData, "max_value"));
		this.dischargeDelay = GsonHelper.getAsInt(jsonData, "delay_on_empty");
		this.hitDelay = GsonHelper.getAsInt(jsonData, "delay_on_hit");
		this.regenAmount = GsonHelper.getAsFloat(jsonData, "regen_per_tick");
	}

	public EnergyResource(CompoundTag nbtData) {
		super(AoAResources.ENERGY.get(), null);

		this.maxValue = nbtData.getFloat("max_value");
		this.dischargeDelay = nbtData.getInt("delay_on_empty");
		this.hitDelay = nbtData.getInt("delay_on_hit");
		this.regenAmount = nbtData.getFloat("regen_per_tick");
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public float getCurrentValue() {
		return this.value;
	}

	public int getCurrentDelay() {
		return this.currentDelay;
	}

	@Override
	public boolean consume(float amount, boolean consumeIfInsufficient) {
		boolean success = super.consume(amount, true);

		if (getCurrentValue() <= 0) {
			this.currentDelay += this.dischargeDelay;

			if (this.currentDelay > 1200)
				this.currentDelay = 1200;
		}

		return success;
	}

	@Override
	public void setValue(float amount) {
		this.value = Mth.clamp(amount, 0, getMaxValue());
	}

	@Override
	public float getMaxValue() {
		return maxValue;
	}

	@Override
	public float getPerTickRegen() {
		return regenAmount;
	}

	@Override
	public void handlePlayerTick(TickEvent.PlayerTickEvent ev) {
		if (currentDelay > 0) {
			currentDelay--;
		}
		else if (this.value < getMaxValue()) {
			addValue(getPerTickRegen());
		}
	}

	@Override
	public void handlePostIncomingAttack(LivingDamageEvent ev) {
		if (ev.getAmount() > 0) {
			currentDelay += hitDelay;

			if (this.currentDelay > 1200)
				this.currentDelay = 1200;
		}
	}

	@Nonnull
	@Override
	public CompoundTag saveToNbt() {
		return new CompoundTag();
	}

	@Override
	public CompoundTag getSyncData(boolean forClientSetup) {
		CompoundTag data = new CompoundTag();

		if (forClientSetup) {
			data.putFloat("max_value", getMaxValue());
			data.putInt("delay_on_empty", dischargeDelay);
			data.putInt("delay_on_hit", hitDelay);
			data.putFloat("regen_per_tick", getPerTickRegen());
		}
		else {
			data.putFloat("value", getCurrentValue());
			data.putInt("current_delay", getCurrentDelay());
		}

		return data;
	}

	@Override
	public void receiveSyncData(CompoundTag data) {
		if (data.contains("value"))
			this.value = data.getFloat("value");

		if (data.contains("current_delay"))
			this.currentDelay = data.getInt("current_delay");
	}
}
