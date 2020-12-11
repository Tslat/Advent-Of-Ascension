package net.tslat.aoa3.entity.projectile.mob;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.boss.CorallusEntity;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;

public class CorallusShotEntity extends FlyingEntity {
	private final CorallusEntity corallus;
	private final LivingEntity target;
	private final float dmg;

	public CorallusShotEntity(CorallusEntity corallus, LivingEntity target, int dmg) {
		super(AoAEntities.Projectiles.CORALLUS_SHOT.get(), corallus.world);
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
	public boolean isImmuneToExplosions() {
		return true;
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(0, new CorallusShotTargetGoal(this));
	}

	@Override
	protected boolean canTriggerWalking() {
		return false;
	}

	@Override
	public boolean canBePushed() {
		return false;
	}

	@Override
	protected boolean canBeRidden(Entity entityIn) {
		return false;
	}

	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}

	@Override
	public boolean doesEntityNotTriggerPressurePlate() {
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
	public boolean hasNoGravity() {
		return true;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (!world.isRemote)
			remove();

		return true;
	}

	@Override
	protected void collideWithEntity(Entity entityIn) {
		if (!world.isRemote && entityIn == target) {
			target.attackEntityFrom(DamageSource.causeMobDamage(this), dmg);
			WorldUtil.createExplosion(corallus, world, this, 1.0f);
			remove();
		}
	}

	@Override
	public void tick() {
		super.tick();

		if (world.getDifficulty() == Difficulty.PEACEFUL)
			remove();
	}

	private class CorallusShotTargetGoal extends Goal {
		private final CorallusShotEntity shot;

		public CorallusShotTargetGoal(CorallusShotEntity shot) {
			this.shot = shot;
		}

		@Override
		public boolean isPreemptible() {
			return false;
		}

		@Override
		public boolean shouldExecute() {
			return true;
		}

		@Override
		public void startExecuting() {
			if (!world.isRemote && (this.shot.target == null || !this.shot.target.isAlive()))
				remove();
		}

		@Override
		public boolean shouldContinueExecuting() {
			return true;
		}

		@Override
		public void tick() {
			if (this.shot.target == null || !this.shot.target.isAlive()) {
				if (!world.isRemote) {
					WorldUtil.createExplosion(corallus, world, shot, 1.0f);
					remove();
				}
			}
			else {
				final double distanceX = this.shot.target.getPosX() - shot.getPosX();
				final double distanceY = this.shot.target.getPosY() - shot.getPosY();
				final double distanceZ = this.shot.target.getPosZ() - shot.getPosZ();

				if (Math.signum(distanceX) != 0 || Math.signum(distanceY) != 0 || Math.signum(distanceZ) != 0) {
					Vec3d motion = shot.getMotion();

					shot.setMotion(motion.add((Math.signum(distanceX) * 0.3 - motion.getX()) * 0.10000000149011612, (Math.signum(distanceY) * 0.3 - motion.getY()) * 0.10000000149011612, (Math.signum(distanceZ) * 0.3 -motion.getZ()) * 0.10000000149011612));
					shot.rotationYaw += MathHelper.wrapDegrees(((float)(Math.atan2(shot.getMotion().getZ(), shot.getMotion().getX()) * 180.0 / 3.141592653589793) - 90.0f) - this.shot.rotationYaw);
				}
			}
		}
	}
}