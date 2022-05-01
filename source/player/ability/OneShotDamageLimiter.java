package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.aoa3.util.PlayerUtil;

public class OneShotDamageLimiter extends AoAAbility.Instance {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.INCOMING_ATTACK_AFTER};

	private final float restoreHealthTo;
	private final float minActivationHealth;

	public OneShotDamageLimiter(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.ONE_SHOT_DAMAGE_LIMITER.get(), skill, data);

		this.restoreHealthTo = GsonHelper.getAsFloat(data, "health_restore_amount", 1);
		this.minActivationHealth = GsonHelper.getAsFloat(data, "min_activation_health", 0);
	}

	public OneShotDamageLimiter(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.ONE_SHOT_DAMAGE_LIMITER.get(), skill, data);

		this.restoreHealthTo = data.getFloat("health_restore_amount");
		this.minActivationHealth = data.getFloat("min_activation_health");
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	protected void updateDescription(TranslatableComponent defaultDescription) {
		if (this.minActivationHealth <= 0) {
			super.updateDescription(new TranslatableComponent(defaultDescription.getKey() + ".full", NumberUtil.roundToNthDecimalPlace(this.restoreHealthTo, 2)));
		}
		else {
			super.updateDescription(new TranslatableComponent(defaultDescription.getKey(), NumberUtil.roundToNthDecimalPlace(this.minActivationHealth, 2), NumberUtil.roundToNthDecimalPlace(this.restoreHealthTo, 2)));
		}
	}

	@Override
	public void handlePostIncomingAttack(LivingDamageEvent ev) {
		if (ev.getSource() == DamageSource.OUT_OF_WORLD)
			return;

		LivingEntity player = ev.getEntityLiving();

		if (player instanceof ServerPlayer && player.getHealth() - ev.getAmount() <= 0 && player.getHealth() >= (minActivationHealth == 0 ? player.getMaxHealth() : minActivationHealth)) {
			ev.setCanceled(true);
			player.setHealth(restoreHealthTo);
			skill.getPlayerDataManager().getResource(AoAResources.ENERGY.get()).setValue(0);

			if (skill.canGainXp(true))
				skill.adjustXp(PlayerUtil.getTimeBasedXpForLevel(skill.getLevel(true), 500), false, false);
		}
	}

	@Override
	public CompoundTag getSyncData(boolean forClientSetup) {
		CompoundTag data = super.getSyncData(forClientSetup);

		if (forClientSetup) {
			data.putFloat("health_restore_amount", this.restoreHealthTo);
			data.putFloat("min_activation_health", this.minActivationHealth);
		}

		return data;
	}
}
