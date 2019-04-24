package net.tslat.aoa3.entity.projectiles.mob;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.boss.corallus.EntityCorallus;

import javax.annotation.Nullable;

public class EntityCorallusShot extends EntityFlying {
	private final EntityCorallus corallus;
	private final EntityLivingBase target;
	private final float dmg;

	public EntityCorallusShot(EntityCorallus corallus, EntityLivingBase target, int dmg) {
		super(corallus.world);
		this.corallus = corallus;
		this.target = target;
		this.dmg = dmg;
		setSize(1.1f, 1.1f);
		this.isImmuneToFire = true;
		setNoGravity(true);
	}

	public EntityCorallusShot(World world) {
		super(world);
		corallus = null;
		target = null;
		dmg = 0;
	}

	@Override
	public boolean isImmuneToExplosions() {
		return true;
	}

	@Override
	protected void initEntityAI() {
		tasks.addTask(0, new EntityCorallusShotAI(this));
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
		return SoundsRegister.staffCoral;
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
		world.createExplosion(this, posX, posY, posZ, 1.0f, false);

		if (!world.isRemote)
			setDead();

		return true;
	}

	@Override
	protected void collideWithEntity(Entity entityIn) {
		if (entityIn == target) {
			target.attackEntityFrom(DamageSource.causeMobDamage(this), dmg);
			world.createExplosion(this, posX, posY, posZ, 1.0f, false);

			if (!world.isRemote)
				setDead();
		}
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (world.getDifficulty() == EnumDifficulty.PEACEFUL)
			setDead();
	}

	private class EntityCorallusShotAI extends EntityAIBase {
		private final EntityCorallusShot shot;

		public EntityCorallusShotAI(EntityCorallusShot shot) {
			this.shot = shot;
		}

		@Override
		public boolean isInterruptible() {
			return false;
		}

		@Override
		public boolean shouldExecute() {
			return true;
		}

		@Override
		public void startExecuting() {
			if (!world.isRemote && (this.shot.target == null || !this.shot.target.isEntityAlive()))
				setDead();
		}

		@Override
		public boolean shouldContinueExecuting() {
			return true;
		}

		@Override
		public void updateTask() {
			if (this.shot.target == null || !this.shot.target.isEntityAlive()) {
				if (!world.isRemote) {
					world.createExplosion(this.shot, this.shot.posX, this.shot.posY, this.shot.posZ, 1.0f, false);
					setDead();
				}
			}
			else {
				final double distanceX = this.shot.target.posX - shot.posX;
				final double distanceY = this.shot.target.posY - shot.posY;
				final double distanceZ = this.shot.target.posZ - shot.posZ;

				if (Math.signum(distanceX) != 0 || Math.signum(distanceY) != 0 || Math.signum(distanceZ) != 0) {
					this.shot.motionX += (Math.signum(distanceX) * 0.3 - this.shot.motionX) * 0.10000000149011612;
					this.shot.motionY += (Math.signum(distanceY) * 0.3 - this.shot.motionY) * 0.10000000149011612;
					this.shot.motionZ += (Math.signum(distanceZ) * 0.3 -this.shot. motionZ) * 0.10000000149011612;
					this.shot.rotationYaw += MathHelper.wrapDegrees(((float)(Math.atan2(this.shot.motionZ, this.shot.motionX) * 180.0 / 3.141592653589793) - 90.0f) - this.shot.rotationYaw);
				}
			}
		}
	}
}

