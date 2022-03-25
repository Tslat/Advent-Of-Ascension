package net.tslat.aoa3.scheduling.sync;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.tslat.aoa3.event.GlobalEvents;
import net.tslat.aoa3.library.builder.EffectBuilder;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.EntityUtil;

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

		EntityUtil.removePotions(target, Effects.REGENERATION);
		EntityUtil.removePotions(shooter, Effects.REGENERATION);
		EntityUtil.applyPotions(Arrays.asList(target, shooter),
				new EffectBuilder(Effects.MOVEMENT_SLOWDOWN, 210).level(100).hideParticles(),
				new EffectBuilder(Effects.WEAKNESS, 210).level(50).hideParticles(),
				new EffectBuilder(Effects.DAMAGE_RESISTANCE, 210).level(5).hideParticles(),
				new EffectBuilder(Effects.NIGHT_VISION, 510).hideParticles(),
				new EffectBuilder(Effects.BLINDNESS, 210).hideParticles(),
				new EffectBuilder(Effects.LEVITATION, 210).level(-1).hideParticles());
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

			((ServerWorld)shooter.level).sendParticles(ParticleTypes.DAMAGE_INDICATOR, target.getX(), target.getY() + target.getBbHeight(), target.getZ(), (int)Math.ceil(10 * healthPercent), 0, 0, 0, 0);
		}
		else {
			if (!(shooter instanceof PlayerEntity) || !((PlayerEntity)shooter).isCreative()) {
				if (shooterPostHealth <= 0) {
					resetStates();
					DamageUtil.dealSelfHarmDamage(shooter, shooter.getHealth() - shooterPostHealth);
				}
				else {
					shooter.setHealth(shooterPostHealth);
				}

				((ServerWorld)shooter.level).sendParticles(ParticleTypes.DAMAGE_INDICATOR, shooter.getX(), shooter.getY() + shooter.getBbHeight(), shooter.getZ(), (int)Math.ceil(10 * healthPercent), 0, 0, 0, 0);
			}
		}

		targetTurn = !targetTurn;

		AoAScheduler.scheduleSyncronisedTask(this, 1);
	}

	private void resetStates() {
		if ( target != null && target.getHealth() > 0)
			EntityUtil.removePotions(target, Effects.BLINDNESS, Effects.DAMAGE_RESISTANCE, Effects.WEAKNESS, Effects.MOVEMENT_SLOWDOWN, Effects.LEVITATION, Effects.NIGHT_VISION);

		if (shooter != null && shooter.getHealth() > 0)
			EntityUtil.removePotions(shooter, Effects.BLINDNESS, Effects.DAMAGE_RESISTANCE, Effects.WEAKNESS, Effects.MOVEMENT_SLOWDOWN, Effects.LEVITATION, Effects.NIGHT_VISION);
	}
}
