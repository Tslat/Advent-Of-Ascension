package net.tslat.aoa3.content.entity.ai.mob;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Items;

import java.util.EnumSet;

public class AdventBowAttackGoal<T extends Mob & RangedAttackMob> extends Goal {
	private final T shooter;
	private final double moveSpeedAmp;
	private int attackCooldown;
	private final float maxAttackDistance;
	private int attackTime = -1;
	private int seeTime;
	private boolean strafingClockwise;
	private boolean strafingBackwards;
	private int strafingTime = -1;

	public AdventBowAttackGoal(T shooter, double moveSpeedAmplifier, int attackCooldown, float maxAttackDistance) {
		this.shooter = shooter;
		this.moveSpeedAmp = moveSpeedAmplifier;
		this.attackCooldown = attackCooldown;
		this.maxAttackDistance = maxAttackDistance * maxAttackDistance;

		setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	}

	public void setAttackCooldown(int attackCooldown) {
		this.attackCooldown = attackCooldown;
	}

	public boolean canUse() {
		return shooter.getTarget() != null && isBowInMainhand();
	}

	protected boolean isBowInMainhand() {
		return shooter.getMainHandItem().getItem() instanceof BowItem || shooter.getOffhandItem().getItem() instanceof BowItem;
	}

	public boolean canContinueToUse() {
		return (canUse() || !shooter.getNavigation().isDone()) && isBowInMainhand();
	}

	public void start() {
		super.start();

		shooter.setAggressive(true);
	}

	public void stop() {
		super.stop();

		shooter.setAggressive(false);

		seeTime = 0;
		attackTime = -1;

		shooter.stopUsingItem();
	}

	public void tick() {
		LivingEntity target = shooter.getTarget();

		if (target != null) {
			double distanceToTarget = shooter.distanceToSqr(target.getX(), target.getY(), target.getZ());
			boolean canSeeTarget = shooter.getSensing().hasLineOfSight(target);


			if (canSeeTarget != seeTime > 0)
				seeTime = 0;

			if (canSeeTarget) {
				++seeTime;
			}
			else {
				--seeTime;
			}

			if (!(distanceToTarget > maxAttackDistance) && seeTime >= 20) {
				shooter.getNavigation().stop();
				++strafingTime;
			}
			else {
				shooter.getNavigation().moveTo(target, moveSpeedAmp);
				strafingTime = -1;
			}

			if (strafingTime >= 20) {
				if (shooter.getRandom().nextFloat() < 0.3D)
					strafingClockwise = !strafingClockwise;

				if (shooter.getRandom().nextFloat() < 0.3D)
					strafingBackwards = !strafingBackwards;

				strafingTime = 0;
			}

			if (strafingTime > -1) {
				if (distanceToTarget > maxAttackDistance * 0.75F) {
					strafingBackwards = false;
				}
				else if (distanceToTarget < maxAttackDistance * 0.25F) {
					strafingBackwards = true;
				}

				shooter.getMoveControl().strafe(strafingBackwards ? -0.5F : 0.5F, strafingClockwise ? 0.5F : -0.5F);
				shooter.lookAt(target, 30.0F, 30.0F);
			}
			else {
				shooter.getLookControl().setLookAt(target, 30.0F, 30.0F);
			}

			if (shooter.isUsingItem()) {
				if (!canSeeTarget && seeTime < -60) {
					shooter.stopUsingItem();
				}
				else if (canSeeTarget) {
					int maxItemUseTime = shooter.getTicksUsingItem();

					if (maxItemUseTime >= 20) {
						shooter.stopUsingItem();
						shooter.performRangedAttack(target, BowItem.getPowerForTime(maxItemUseTime));
						attackTime = attackCooldown;
					}
				}
			}
			else if (--attackTime <= 0 && seeTime >= -60) {
				shooter.startUsingItem(ProjectileUtil.getWeaponHoldingHand(shooter, Items.BOW));
			}
		}
	}
}