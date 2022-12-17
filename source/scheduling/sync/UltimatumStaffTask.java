package net.tslat.aoa3.scheduling.sync;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.tslat.aoa3.event.GlobalEvents;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.effectslib.api.util.EffectBuilder;

import java.util.Arrays;

public class UltimatumStaffTask implements Runnable {
	private final int startingTick;
	private final LivingEntity shooter;
	private final LivingEntity target;
	private final BlockPos shooterPos;
	private final BlockPos targetPos;
	private final float shooterStartHealth;
	private final float targetStartHealth;

	private boolean targetTurn = true;

	public UltimatumStaffTask(LivingEntity shooter, LivingEntity target) {
		this.startingTick = GlobalEvents.tick;
		this.shooter = shooter;
		this.target = target;
		this.shooterPos = shooter.blockPosition();
		this.targetPos = target.blockPosition();
		this.shooterStartHealth = shooter.getHealth();
		this.targetStartHealth = target.getHealth();

		target.setDeltaMovement(0, 0, 0);
		shooter.setDeltaMovement(0, 0, 0);

		EntityUtil.removePotions(target, MobEffects.REGENERATION);
		EntityUtil.removePotions(shooter, MobEffects.REGENERATION);
		EntityUtil.applyPotions(Arrays.asList(target, shooter),
				new EffectBuilder(MobEffects.MOVEMENT_SLOWDOWN, 210).level(100).hideParticles(),
				new EffectBuilder(MobEffects.WEAKNESS, 210).level(50).hideParticles(),
				new EffectBuilder(MobEffects.DAMAGE_RESISTANCE, 210).level(5).hideParticles(),
				new EffectBuilder(MobEffects.NIGHT_VISION, 510).hideParticles(),
				new EffectBuilder(MobEffects.BLINDNESS, 210).hideParticles(),
				new EffectBuilder(MobEffects.LEVITATION, 210).level(-1).hideParticles());
	}

	@Override
	public void run() {
		if (shooter == null || target == null || shooter.level.isClientSide || !shooter.blockPosition().equals(shooterPos) || !target.blockPosition().equals(targetPos)) {
			resetStates();

			return;
		}

		float healthPercent = 1 - (GlobalEvents.tick - startingTick) / 200f;
		float targetPostHealth = targetStartHealth * healthPercent;
		float shooterPostHealth = shooterStartHealth * healthPercent;

		if (targetTurn) {
			if (target.getHealth() == 0) {
				resetStates();

				return;
			}

			if (targetPostHealth <= 0) {
				resetStates();
				DamageUtil.dealMagicDamage(null, shooter, target, target.getHealth() - targetPostHealth, true);

				target.setDeltaMovement(0, 0, 0);
			}
			else {
				target.setHealth(targetPostHealth);
			}

			((ServerLevel)shooter.level).sendParticles(ParticleTypes.DAMAGE_INDICATOR, target.getX(), target.getY() + target.getBbHeight(), target.getZ(), (int)Math.ceil(10 * healthPercent), 0, 0, 0, 0);
		}
		else {
			if (!(shooter instanceof Player) || !((Player)shooter).isCreative()) {
				if (shooterPostHealth <= 0) {
					resetStates();
					DamageUtil.dealSelfHarmDamage(shooter, shooter.getHealth() - shooterPostHealth);
				}
				else {
					shooter.setHealth(shooterPostHealth);
				}

				((ServerLevel)shooter.level).sendParticles(ParticleTypes.DAMAGE_INDICATOR, shooter.getX(), shooter.getY() + shooter.getBbHeight(), shooter.getZ(), (int)Math.ceil(10 * healthPercent), 0, 0, 0, 0);
			}
		}

		targetTurn = !targetTurn;

		AoAScheduler.scheduleSyncronisedTask(this, 1);
	}

	private void resetStates() {
		if ( target != null && target.getHealth() > 0)
			EntityUtil.removePotions(target, MobEffects.BLINDNESS, MobEffects.DAMAGE_RESISTANCE, MobEffects.WEAKNESS, MobEffects.MOVEMENT_SLOWDOWN, MobEffects.LEVITATION, MobEffects.NIGHT_VISION);

		if (shooter != null && shooter.getHealth() > 0)
			EntityUtil.removePotions(shooter, MobEffects.BLINDNESS, MobEffects.DAMAGE_RESISTANCE, MobEffects.WEAKNESS, MobEffects.MOVEMENT_SLOWDOWN, MobEffects.LEVITATION, MobEffects.NIGHT_VISION);
	}
}
