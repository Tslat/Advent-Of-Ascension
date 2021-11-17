package net.tslat.aoa3.player.resource;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.TickEvent;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.player.PlayerDataManager;

import javax.annotation.Nonnull;

public class SpiritResource extends AoAResource.Instance {
	private final float maxValue;
	private final int rechargeDelay;
	private final float regenAmount;

	private long lastUseTick = 0;
	private float value = 0;

	public SpiritResource(PlayerDataManager plData, JsonObject jsonData) {
		super(AoAResources.SPIRIT.get(), plData);

		this.maxValue = Math.max(0, JSONUtils.getAsFloat(jsonData, "max_value"));
		this.rechargeDelay = JSONUtils.getAsInt(jsonData, "recharge_delay");
		this.regenAmount = JSONUtils.getAsFloat(jsonData, "regen_per_tick");
	}

	public SpiritResource(CompoundNBT nbtData) {
		super(AoAResources.SPIRIT.get(), null);

		this.maxValue = nbtData.getFloat("max_value");
		this.rechargeDelay = nbtData.getInt("recharge_delay");
		this.regenAmount = nbtData.getFloat("regen_per_tick");
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return new ListenerType[] {
				ListenerType.PLAYER_TICK
		};
	}

	@Override
	public float getCurrentValue() {
		return this.value;
	}

	@Override
	public boolean consume(float amount, boolean consumeIfInsufficient) {
		boolean success = super.consume(amount, consumeIfInsufficient);

		if (success)
			this.lastUseTick = playerDataManager.player().level.getGameTime();

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
		if (this.value < getMaxValue() && playerDataManager.player().level.getGameTime() - rechargeDelay > lastUseTick)
			addValue(regenAmount);
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
			data.putInt("recharge_delay", rechargeDelay);
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
