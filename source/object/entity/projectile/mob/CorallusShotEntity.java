package net.tslat.aoa3.object.entity.projectile.mob;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.object.entity.boss.CorallusEntity;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;

public class CorallusShotEntity extends FlyingEntity {
	private final CorallusEntity corallus;
	private final LivingEntity target;
	private final float dmg;

	public CorallusShotEntity(CorallusEntity corallus, LivingEntity target, int dmg) {
		super(AoAEntities.Projectiles.CORALLUS_SHOT.get(), corallus.level);
		this.corallus = corallus;
		this.target = target;
		this.dmg = dmg;

		setNoGravity(true);
	}

	public CorallusShotEntity(EntityType<? extends FlyingEntity> entityType, World world) {
		super(entityType, world);
		corallus = null;
		target = null;
		dmg = 0;
	}

	@Override
	public boolean ignoreExplosion() {
		return true;
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(0, new CorallusShotTargetGoal(this));
	}

	@Override
	protected boolean isMovementNoisy() {
		return false;
	}

	@Override
	public boolean isPushable() {
		return false;
	}

	@Override
	protected boolean canRide(Entity entityIn) {
		return false;
	}

	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}

	@Override
	public boolean isIgnoringBlockTriggers() {
		return true;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ITEM_CORAL_STAFF_CAST.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return null;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return null;
	}

	@Override
	public boolean isNoGravity() {
		return true;
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (!level.isClientSide)
			remove();

		return true;
	}

	@Override
	protected void doPush(Entity entityIn) {
		if (!level.isClientSide && entityIn == target) {
			target.hurt(DamageSource.mobAttack(this), dmg);
			WorldUtil.createExplosion(corallus, level, this, 1.0f);
			remove();
		}
	}

	@Override
	public void tick() {
		super.tick();

		if (level.getDifficulty() == Difficulty.PEACEFUL)
			remove();
	}

	private class CorallusShotTargetGoal extends Goal {
		private final CorallusShotEntity shot;

		public CorallusShotTargetGoal(CorallusShotEntity shot) {
			this.shot = shot;
		}

		@Override
		public boolean isInterruptable() {
			return false;
		}

		@Override
		public boolean canUse() {
			return true;
		}

		@Override
		public void start() {
			if (!level.isClientSide && (this.shot.target == null || !this.shot.target.isAlive()))
				remove();
		}

		@Override
		public boolean canContinueToUse() {
			return true;
		}

		@Override
		public void tick() {
			if (this.shot.target == null || !this.shot.target.isAlive()) {
				if (!level.isClientSide) {
					WorldUtil.createExplosion(corallus, level, shot, 1.0f);
					remove();
				}
			}
			else {
				final double distanceX = this.shot.target.getX() - shot.getX();
				final double distanceY = this.shot.target.getY() - shot.getY();
				final double distanceZ = this.shot.target.getZ() - shot.getZ();

				if (Math.signum(distanceX) != 0 || Math.signum(distanceY) != 0 || Math.signum(distanceZ) != 0) {
					Vector3d motion = shot.getDeltaMovement();

					shot.setDeltaMovement(motion.add((Math.signum(distanceX) * 0.3 - motion.x()) * 0.10000000149011612, (Math.signum(distanceY) * 0.3 - motion.y()) * 0.10000000149011612, (Math.signum(distanceZ) * 0.3 -motion.z()) * 0.10000000149011612));
					shot.yRot += MathHelper.wrapDegrees(((float)(Math.atan2(shot.getDeltaMovement().z(), shot.getDeltaMovement().x()) * 180.0 / 3.141592653589793) - 90.0f) - this.shot.yRot);
				}
			}
		}
	}
}