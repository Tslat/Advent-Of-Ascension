package net.tslat.aoa3.entity.ai.mob;

import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.item.BowItem;
import net.minecraft.item.Items;

import java.util.EnumSet;

public class AdventBowAttackGoal<T extends MobEntity & IRangedAttackMob> extends Goal {
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

		setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	}

	public void setAttackCooldown(int attackCooldown) {
		this.attackCooldown = attackCooldown;
	}

	public boolean shouldExecute() {
		return shooter.getAttackTarget() != null && isBowInMainhand();
	}

	protected boolean isBowInMainhand() {
		return shooter.getHeldItemMainhand().getItem() instanceof BowItem || shooter.getHeldItemOffhand().getItem() instanceof BowItem;
	}

	public boolean shouldContinueExecuting() {
		return (shouldExecute() || !shooter.getNavigator().noPath()) && isBowInMainhand();
	}

	public void startExecuting() {
		super.startExecuting();

		shooter.setAggroed(true);
	}

	public void resetTask() {
		super.resetTask();

		shooter.setAggroed(false);

		seeTime = 0;
		attackTime = -1;

		shooter.resetActiveHand();
	}

	public void tick() {
		LivingEntity target = shooter.getAttackTarget();

		if (target != null) {
			double distanceToTarget = shooter.getDistanceSq(target.getPosX(), target.getPosY(), target.getPosZ());
			boolean canSeeTarget = shooter.getEntitySenses().canSee(target);


			if (canSeeTarget != seeTime > 0)
				seeTime = 0;

			if (canSeeTarget) {
				++seeTime;
			}
			else {
				--seeTime;
			}

			if (!(distanceToTarget > maxAttackDistance) && seeTime >= 20) {
				shooter.getNavigator().clearPath();
				++strafingTime;
			}
			else {
				shooter.getNavigator().tryMoveToEntityLiving(target, moveSpeedAmp);
				strafingTime = -1;
			}

			if (strafingTime >= 20) {
				if (shooter.getRNG().nextFloat() < 0.3D)
					strafingClockwise = !strafingClockwise;

				if (shooter.getRNG().nextFloat() < 0.3D)
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

				shooter.getMoveHelper().strafe(strafingBackwards ? -0.5F : 0.5F, strafingClockwise ? 0.5F : -0.5F);
				shooter.faceEntity(target, 30.0F, 30.0F);
			}
			else {
				shooter.getLookController().setLookPositionWithEntity(target, 30.0F, 30.0F);
			}

			if (shooter.isHandActive()) {
				if (!canSeeTarget && seeTime < -60) {
					shooter.resetActiveHand();
				}
				else if (canSeeTarget) {
					int maxItemUseTime = shooter.getItemInUseMaxCount();

					if (maxItemUseTime >= 20) {
						shooter.resetActiveHand();
						shooter.attackEntityWithRangedAttack(target, BowItem.getArrowVelocity(maxItemUseTime));
						attackTime = attackCooldown;
					}
				}
			}
			else if (--attackTime <= 0 && seeTime >= -60) {
				shooter.setActiveHand(ProjectileHelper.getHandWith(shooter, Items.BOW));
			}
		}
	}
}