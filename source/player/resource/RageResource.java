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

import java.util.List;

public class RageResource extends AoAResource.Instance {
	public static final float DEFAULT_MAX_VALUE = 100;
	public static final float DEFAULT_PER_TICK_DRAIN = 0.01f;
	private final List<DynamicEventSubscriber<?>> eventSubscribers = List.of(
			afterTakingDamage(this::handleAfterDamaged),
			listener(PlayerTickEvent.Pre.class, PlayerTickEvent.Pre::getEntity, this::handlePlayerTick));

	private final float maxValue;
	private final float perTickDrain;

	private float value = 0;

	public RageResource(ServerPlayerDataManager plData, JsonObject jsonData) {
		super(AoAResources.RAGE.get(), plData);

		this.maxValue = Math.max(0, GsonHelper.getAsFloat(jsonData, "max_value", DEFAULT_MAX_VALUE));
		this.perTickDrain = GsonHelper.getAsFloat(jsonData, "per_tick_drain", DEFAULT_PER_TICK_DRAIN);
	}

	public RageResource(CompoundTag nbtData) {
		super(AoAResources.RAGE.get(), null);

		this.maxValue = nbtData.getFloat("max_value");
		this.perTickDrain = nbtData.getFloat("per_tick_drain");
	}

	@Override
	public List<DynamicEventSubscriber<?>> getEventSubscribers() {
		return this.eventSubscribers;
	}

	@Override
	public float getCurrentValue() {
		return this.value;
	}

	@Override
	public void setValue(float amount) {
		this.value = Mth.clamp(amount, 0, getMaxValue());
		this.needsSync = true;
	}

	@Override
	public float getMaxValue() {
		return maxValue;
	}

	private void handleAfterDamaged(LivingDamageEvent.Post ev) {
		if (ev.getSource().getEntity() != null)
			this.value = Math.min(getMaxValue(), this.value + ev.getOriginalDamage());
	}

	private void handlePlayerTick(final PlayerTickEvent.Pre ev) {
		if (this.value > 0)
			this.value -= perTickDrain;
	}

	@Override
	public CompoundTag getSyncData(boolean forClientSetup) {
		CompoundTag nbt = new CompoundTag();

		if (forClientSetup) {
			nbt.putFloat("max_value", maxValue);
			nbt.putFloat("per_tick_drain", perTickDrain);
		}
		else {
			nbt.putFloat("value", getCurrentValue());
		}

		if (!forClientSetup)
			this.needsSync = false;

		return nbt;
	}

	@Override
	public void receiveSyncData(CompoundTag data) {
		this.value = data.getFloat("value");
	}
}
