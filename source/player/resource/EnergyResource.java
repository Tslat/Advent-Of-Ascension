package net.tslat.aoa3.player.resource;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.Mth;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.event.dynamic.DynamicEventSubscriber;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class EnergyResource extends AoAResource.Instance {
	public static final float DEFAULT_MAX_VALUE = 100;
	public static final int DEFAULT_DELAY_ON_EMPTY = 100;
	public static final int DEFAULT_DELAY_ON_HIT = 40;
	public static final float DEFAULT_REGEN_PER_TICK = 0.3f;
	private final List<DynamicEventSubscriber<?>> eventSubscribers = List.of(
			afterTakingDamage(this::handleAfterDamaged),
			listener(PlayerTickEvent.Pre.class, PlayerTickEvent.Pre::getEntity, this::handlePlayerTick));

	private final float maxValue;
	private final int dischargeDelay;
	private final int hitDelay;
	private final float regenAmount;

	private int currentDelay = 0;
	private float value = 0;

	public EnergyResource(ServerPlayerDataManager plData, JsonObject jsonData) {
		super(AoAResources.ENERGY.get(), plData);

		this.maxValue = Math.max(0, GsonHelper.getAsFloat(jsonData, "max_value", DEFAULT_MAX_VALUE));
		this.dischargeDelay = GsonHelper.getAsInt(jsonData, "delay_on_empty", DEFAULT_DELAY_ON_EMPTY);
		this.hitDelay = GsonHelper.getAsInt(jsonData, "delay_on_hit", DEFAULT_DELAY_ON_HIT);
		this.regenAmount = GsonHelper.getAsFloat(jsonData, "regen_per_tick", DEFAULT_REGEN_PER_TICK);
	}

	public EnergyResource(CompoundTag nbtData) {
		super(AoAResources.ENERGY.get(), null);

		this.maxValue = nbtData.getFloat("max_value");
		this.dischargeDelay = nbtData.getInt("delay_on_empty");
		this.hitDelay = nbtData.getInt("delay_on_hit");
		this.regenAmount = nbtData.getFloat("regen_per_tick");
	}

	@Override
	public List<DynamicEventSubscriber<?>> getEventSubscribers() {
		return this.eventSubscribers;
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
		this.needsSync = true;
	}

	@Override
	public float getMaxValue() {
		return this.maxValue;
	}

	@Override
	public float getPerTickRegen() {
		return this.regenAmount;
	}

	private void handlePlayerTick(final PlayerTickEvent.Pre ev) {
		if (this.currentDelay > 0) {
			this.currentDelay--;
		}
		else if (this.value < getMaxValue()) {
			addValue(getPerTickRegen());
		}
	}

	private void handleAfterDamaged(LivingDamageEvent.Post ev) {
		if (ev.getNewDamage() > 0) {
			this.currentDelay += this.hitDelay;

			if (this.currentDelay > 1200)
				this.currentDelay = 1200;
		}
	}

	@NotNull
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

		if (!forClientSetup)
			this.needsSync = false;

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
