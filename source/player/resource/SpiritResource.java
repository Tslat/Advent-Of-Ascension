package net.tslat.aoa3.player.resource;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.Mth;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.player.ServerPlayerDataManager;

import javax.annotation.Nonnull;

public class SpiritResource extends AoAResource.Instance {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.PLAYER_TICK, ListenerType.OUTGOING_ATTACK_AFTER};

	private final float maxValue;
	private final float regenPerTick;
	private final float regenPerDamage;
	private final float healthModMax;

	private float value = 0;

	public SpiritResource(ServerPlayerDataManager plData, JsonObject jsonData) {
		super(AoAResources.SPIRIT.get(), plData);

		this.maxValue = Math.max(0, GsonHelper.getAsFloat(jsonData, "max_value"));
		this.regenPerTick = GsonHelper.getAsFloat(jsonData, "regen_per_tick", 0.04f);
		this.regenPerDamage = GsonHelper.getAsFloat(jsonData, "regen_per_damage", 0.75f);
		this.healthModMax = GsonHelper.getAsFloat(jsonData, "inverse_health_regen_mod", 2f);
	}

	public SpiritResource(CompoundTag nbtData) {
		super(AoAResources.SPIRIT.get(), null);

		this.maxValue = nbtData.getFloat("max_value");
		this.regenPerTick = nbtData.getFloat("regen_per_tick");
		this.regenPerDamage = nbtData.getFloat("regen_per_damage");
		this.healthModMax = nbtData.getFloat("inverse_health_regen_mod");
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public float getCurrentValue() {
		return this.value;
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
		return regenPerTick;
	}

	@Override
	public void handlePostOutgoingAttack(LivingDamageEvent ev) {
		if (this.value < getMaxValue() && !ev.getSource().getMsgId().equals("blaster") && !ev.getSource().is(DamageTypeTags.IS_EXPLOSION))
			addValue(getHealthScaledRegen(this.regenPerDamage * ev.getAmount()));
	}

	@Override
	public void handlePlayerTick(TickEvent.PlayerTickEvent ev) {
		if (this.value < getMaxValue())
			addValue(getHealthScaledRegen(getPerTickRegen()));
	}

	protected float getHealthScaledRegen(float regenAmount) {
		ServerPlayer player = getPlayerDataManager().player();

		return (1 + (1 - player.getHealth() / player.getMaxHealth()) * (this.healthModMax - 1)) * regenAmount;
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
			data.putFloat("inverse_health_regen_mod", this.healthModMax);
			data.putFloat("regen_per_damage", this.regenPerDamage);
			data.putFloat("regen_per_tick", getPerTickRegen());
		}
		else {
			data.putFloat("value", getCurrentValue());
		}

		return data;
	}

	@Override
	public void receiveSyncData(CompoundTag data) {
		if (data.contains("value"))
			this.value = data.getFloat("value");
	}
}
