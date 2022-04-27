package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.text.TranslationTextComponent;
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

		this.restoreHealthTo = JSONUtils.getAsFloat(data, "health_restore_amount", 1);
		this.minActivationHealth = JSONUtils.getAsFloat(data, "min_activation_health", 0);
	}

	public OneShotDamageLimiter(AoASkill.Instance skill, CompoundNBT data) {
		super(AoAAbilities.ONE_SHOT_DAMAGE_LIMITER.get(), skill, data);

		this.restoreHealthTo = data.getFloat("health_restore_amount");
		this.minActivationHealth = data.getFloat("min_activation_health");
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	protected void updateDescription(TranslationTextComponent defaultDescription) {
		if (this.minActivationHealth <= 0) {
			super.updateDescription(new TranslationTextComponent(defaultDescription.getKey() + ".full", NumberUtil.roundToNthDecimalPlace(this.restoreHealthTo, 2)));
		}
		else {
			super.updateDescription(new TranslationTextComponent(defaultDescription.getKey(), NumberUtil.roundToNthDecimalPlace(this.minActivationHealth, 2), NumberUtil.roundToNthDecimalPlace(this.restoreHealthTo, 2)));
		}
	}

	@Override
	public void handlePostIncomingAttack(LivingDamageEvent ev) {
		if (ev.getSource() == DamageSource.OUT_OF_WORLD)
			return;

		LivingEntity player = ev.getEntityLiving();

		if (player instanceof ServerPlayerEntity && player.getHealth() - ev.getAmount() <= 0 && player.getHealth() >= (minActivationHealth == 0 ? player.getMaxHealth() : minActivationHealth)) {
			ev.setCanceled(true);
			player.setHealth(restoreHealthTo);
			skill.getPlayerDataManager().getResource(AoAResources.ENERGY.get()).setValue(0);

			if (skill.canGainXp(true))
				skill.adjustXp(PlayerUtil.getTimeBasedXpForLevel(skill.getLevel(true), 500), false, false);
		}
	}

	@Override
	public CompoundNBT getSyncData(boolean forClientSetup) {
		CompoundNBT data = super.getSyncData(forClientSetup);

		if (forClientSetup) {
			data.putFloat("health_restore_amount", this.restoreHealthTo);
			data.putFloat("min_activation_health", this.minActivationHealth);
		}

		return data;
	}
}
