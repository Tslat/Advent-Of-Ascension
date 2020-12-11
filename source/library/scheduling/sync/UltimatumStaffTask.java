package net.tslat.aoa3.library.scheduling.sync;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.tslat.aoa3.event.GlobalEvents;
import net.tslat.aoa3.library.scheduling.AoAScheduler;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.PotionUtil;

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
		this.shooterPos = shooter.getPosition();
		this.targetPos = target.getPosition();
		this.shooterStartHealth = shooter.getHealth();
		this.targetStartHealth = target.getHealth();

		target.setMotion(0, 0, 0);
		shooter.setMotion(0, 0, 0);

		EntityUtil.removePotions(target, Effects.REGENERATION);
		EntityUtil.removePotions(shooter, Effects.REGENERATION);
		EntityUtil.applyPotions(Arrays.asList(target, shooter),
				new PotionUtil.EffectBuilder(Effects.SLOWNESS, 210).level(100).hideParticles(),
				new PotionUtil.EffectBuilder(Effects.WEAKNESS, 210).level(50).hideParticles(),
				new PotionUtil.EffectBuilder(Effects.RESISTANCE, 210).level(5).hideParticles(),
				new PotionUtil.EffectBuilder(Effects.NIGHT_VISION, 510).hideParticles(),
				new PotionUtil.EffectBuilder(Effects.BLINDNESS, 210).hideParticles(),
				new PotionUtil.EffectBuilder(Effects.LEVITATION, 210).level(-1).hideParticles());
	}

	@Override
	public void run() {
		if (shooter == null || target == null || shooter.world.isRemote || !shooter.getPosition().equals(shooterPos) || !target.getPosition().equals(targetPos)) {
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

				target.setMotion(0, 0, 0);
			}
			else {
				target.setHealth(targetPostHealth);
			}

			((ServerWorld)shooter.world).spawnParticle(ParticleTypes.DAMAGE_INDICATOR, target.getPosX(), target.getPosY() + target.getHeight(), target.getPosZ(), (int)Math.ceil(10 * healthPercent), 0, 0, 0, 0);
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

				((ServerWorld)shooter.world).spawnParticle(ParticleTypes.DAMAGE_INDICATOR, shooter.getPosX(), shooter.getPosY() + shooter.getHeight(), shooter.getPosZ(), (int)Math.ceil(10 * healthPercent), 0, 0, 0, 0);
			}
		}

		targetTurn = !targetTurn;

		AoAScheduler.scheduleSyncronisedTask(this, 1);
	}

	private void resetStates() {
		if (target.getHealth() > 0)
			EntityUtil.removePotions(target, Effects.BLINDNESS, Effects.RESISTANCE, Effects.WEAKNESS, Effects.SLOWNESS, Effects.LEVITATION, Effects.NIGHT_VISION);

		if (shooter.getHealth() > 0)
			EntityUtil.removePotions(shooter, Effects.BLINDNESS, Effects.RESISTANCE, Effects.WEAKNESS, Effects.SLOWNESS, Effects.LEVITATION, Effects.NIGHT_VISION);
	}
}
