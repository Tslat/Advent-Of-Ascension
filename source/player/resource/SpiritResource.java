package net.tslat.aoa3.player.resource;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.Mth;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.tslat.aoa3.common.registration.AoATags;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.event.dynamic.DynamicEventSubscriber;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public class SpiritResource extends AoAResource.Instance {
	public static final float DEFAULT_MAX_VALUE = 200;
	public static final float DEFAULT_REGEN_PER_DAMAGE = 0.4f;
	public static final float DEFAULT_INVERSE_HEALTH_REGEN_MOD = 3.5f;
	public static final float DEFAULT_REGEN_PER_TICK = 0.04f;
	private final List<DynamicEventSubscriber<?>> eventSubscribers = List.of(
			afterAttacking(this::handleAfterAttacking),
			listener(PlayerTickEvent.Pre.class, PlayerTickEvent.Pre::getEntity, this::handlePlayerTick));

	private final float maxValue;
	private final float regenPerTick;
	private final float regenPerDamage;
	private final float healthModMax;

	private float value = 0;

	public SpiritResource(ServerPlayerDataManager plData, JsonObject jsonData) {
		super(AoAResources.SPIRIT.get(), plData);

		this.maxValue = Math.max(0, GsonHelper.getAsFloat(jsonData, "max_value", DEFAULT_MAX_VALUE));
		this.regenPerDamage = GsonHelper.getAsFloat(jsonData, "regen_per_damage", DEFAULT_REGEN_PER_DAMAGE);
		this.healthModMax = GsonHelper.getAsFloat(jsonData, "inverse_health_regen_mod", DEFAULT_INVERSE_HEALTH_REGEN_MOD);
		this.regenPerTick = GsonHelper.getAsFloat(jsonData, "regen_per_tick", DEFAULT_REGEN_PER_TICK);
	}

	public SpiritResource(CompoundTag nbtData) {
		super(AoAResources.SPIRIT.get(), null);

		this.maxValue = nbtData.getFloat("max_value");
		this.regenPerTick = nbtData.getFloat("regen_per_tick");
		this.regenPerDamage = nbtData.getFloat("regen_per_damage");
		this.healthModMax = nbtData.getFloat("inverse_health_regen_mod");
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

	@Override
	public float getPerTickRegen() {
		return regenPerTick;
	}

	private void handleAfterAttacking(LivingDamageEvent.Post ev) {
		if (this.value < getMaxValue() && !ev.getSource().is(AoATags.DamageTypes.NO_SPIRIT_REGEN))
			addValue(getHealthScaledRegen(this.regenPerDamage * ev.getNewDamage()));
	}

	private void handlePlayerTick(final PlayerTickEvent.Pre ev) {
		if (this.value < getMaxValue())
			addValue(getHealthScaledRegen(getPerTickRegen()));
	}

	protected float getHealthScaledRegen(float regenAmount) {
		ServerPlayer player = getPlayerDataManager().getPlayer();

		return (1 + (1 - player.getHealth() / player.getMaxHealth()) * (this.healthModMax - 1)) * regenAmount;
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
			data.putFloat("inverse_health_regen_mod", this.healthModMax);
			data.putFloat("regen_per_damage", this.regenPerDamage);
			data.putFloat("regen_per_tick", getPerTickRegen());
		}
		else {
			data.putFloat("value", getCurrentValue());
		}

		if (!forClientSetup)
			this.needsSync = false;

		return data;
	}

	@Override
	public void receiveSyncData(CompoundTag data) {
		if (data.contains("value"))
			this.value = data.getFloat("value");
	}
}
