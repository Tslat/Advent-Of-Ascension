package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.Effects;
import net.minecraft.util.Hand;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.tslat.aoa3.client.AoAKeybinds;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.aoa3.util.*;

public class StunStrike extends AoAAbility.Instance {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.KEY_INPUT, ListenerType.OUTGOING_ATTACK_AFTER};

	private final float energyCost;
	private final int stunDuration;

	private boolean primedAttack = false;

	public StunStrike(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.STUN_STRIKE.get(), skill, data);

		this.energyCost = JSONUtils.getAsFloat(data, "energy_cost");
		this.stunDuration = JSONUtils.getAsInt(data, "stun_duration", 20);
	}

	public StunStrike(AoASkill.Instance skill, CompoundNBT data) {
		super(AoAAbilities.STUN_STRIKE.get(), skill, data);

		this.energyCost = data.getFloat("energy_cost");
		this.stunDuration = data.getInt("stun_duration");
	}

	@Override
	protected void updateDescription(TranslationTextComponent defaultDescription) {
		super.updateDescription(new TranslationTextComponent(defaultDescription.getKey(), NumberUtil.roundToNthDecimalPlace(this.stunDuration / 20f, 2), NumberUtil.roundToNthDecimalPlace(this.energyCost, 2)));
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public KeyBinding getKeybind() {
		return AoAKeybinds.ABILITY_ACTION;
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public boolean shouldSendKeyPress() {
		ClientPlayerEntity pl = Minecraft.getInstance().player;

		return !pl.input.hasForwardImpulse() && pl.input.leftImpulse == 0 && !pl.getItemInHand(Hand.OFF_HAND).isEmpty() && PlayerUtil.getResourceValue(pl, AoAResources.ENERGY.get()) >= this.energyCost;
	}

	@Override
	public void handleKeyInput() {
		ServerPlayerEntity player = getPlayer();

		if (!primedAttack && skill.getPlayerDataManager().getResource(AoAResources.ENERGY.get()).hasAmount(this.energyCost)) {
			this.primedAttack = true;

			activatedActionKey(player);
		}
	}

	@Override
	public void handlePostOutgoingAttack(LivingDamageEvent ev) {
		if (ev.getAmount() > 0 && primedAttack && DamageUtil.isMeleeDamage(ev.getSource()) && !getPlayer().getItemInHand(Hand.OFF_HAND).isEmpty()) {
			this.primedAttack = false;

			if (skill.getPlayerDataManager().getResource(AoAResources.ENERGY.get()).consume(this.energyCost, true)) {
				ServerPlayerEntity player = getPlayer();
				LivingEntity target = ev.getEntityLiving();

				AoAScheduler.scheduleSyncronisedTask(() -> {
					player.swing(Hand.OFF_HAND, true);

					if (target != null && target.isAlive()) {
						if (player.distanceToSqr(target) < 36 && player.canSee(target)) {
							DamageUtil.doScaledKnockback(target, player, 0.5f, player.getX() - target.getX(), player.getZ() - target.getZ());
							EntityUtil.applyPotions(target, new PotionUtil.EffectBuilder(Effects.MOVEMENT_SLOWDOWN, this.stunDuration).level(127), new PotionUtil.EffectBuilder(Effects.DIG_SLOWDOWN, this.stunDuration).level(127));
							activatedActionKey(player);

							if (skill.canGainXp(true))
								skill.adjustXp(PlayerUtil.getTimeBasedXpForLevel(skill.getLevel(true), 30), false, false);
						}
					}
				}, player.getCurrentSwingDuration() - 1);
			}
		}
	}

	@Override
	public CompoundNBT getSyncData(boolean forClientSetup) {
		CompoundNBT data = super.getSyncData(forClientSetup);

		if (forClientSetup) {
			data.putFloat("energy_cost", this.energyCost);
			data.putFloat("stun_duration", this.stunDuration);
		}

		return data;
	}
}
