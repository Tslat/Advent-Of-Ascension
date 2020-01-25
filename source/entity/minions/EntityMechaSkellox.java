package net.tslat.aoa3.entity.minions;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;

import javax.annotation.Nullable;

public class EntityMechaSkellox extends AoAMinion {
	public static final float entityWidth = 0.8f;

	public EntityMechaSkellox(final World world){
		super(world, -1, entityWidth, 2.375f);
		this.setAIMoveSpeed(3.2f);
	}

	@Override
	public float getEyeHeight() {
		return 2.09375f;
	}

	@Override
	protected void initEntityAI() {
		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(2, new EntityAIWanderAvoidWater(this, 1.0d));
		tasks.addTask(3, new EntityAILookIdle(this));
		tasks.addTask(4, new EntityAIFollowOwner(this, 1.0d, 30.0f, 2.0f));
		tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));

		if (isHostile()) {
			if (getBaseMeleeDamage() >= 0) {
				tasks.addTask(6, new EntityAIAttackMelee(this, 1.5f, true));
				tasks.addTask(7, new EntityAILeapAtTarget(this, 0.3f));

			}

			targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityLivingBase.class, 0, false, false, IMob.MOB_SELECTOR));
			targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
			targetTasks.addTask(3, new EntityAIOwnerHurtByTarget(this));
			targetTasks.addTask(4, new EntityAIOwnerHurtTarget(this));
		}
	}

	@Override
	protected double getBaseMoveSpeed() {
		return 0.72;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 160.0d;
	}

	@Override
	protected boolean isHostile() {
		return true;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 15.0d;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobMechyonLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobMechyonHit;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobMechyonDeath;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityMechaSkellox;
	}

	@Override
	public void travel(float strafe, float vertical, float forward) {
		if (this.isServerWorld() || this.canPassengerSteer()) {
			if (!this.isInWater()) {
				if (!this.isInLava()) {
					if (this.isElytraFlying()) {
						if (this.motionY > -0.5D)
							this.fallDistance = 1.0F;

						Vec3d vec3d = this.getLookVec();
						float f = this.rotationPitch * 0.017453292F;
						double d6 = Math.sqrt(vec3d.x * vec3d.x + vec3d.z * vec3d.z);
						double d8 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
						double d1 = vec3d.length();
						float f4 = MathHelper.cos(f);
						f4 = (float)((double)f4 * (double)f4 * Math.min(1.0D, d1 / 0.4D));
						this.motionY += -0.08D + (double)f4 * 0.06D;

						if (this.motionY < 0.0D && d6 > 0.0D) {
							double d2 = this.motionY * -0.1D * (double)f4;
							this.motionY += d2;
							this.motionX += vec3d.x * d2 / d6;
							this.motionZ += vec3d.z * d2 / d6;
						}

						if (f < 0.0F) {
							double d10 = d8 * (double)(-MathHelper.sin(f)) * 0.04D;
							this.motionY += d10 * 3.2D;
							this.motionX -= vec3d.x * d10 / d6;
							this.motionZ -= vec3d.z * d10 / d6;
						}

						if (d6 > 0.0D) {
							this.motionX += (vec3d.x / d6 * d8 - this.motionX) * 0.1D;
							this.motionZ += (vec3d.z / d6 * d8 - this.motionZ) * 0.1D;
						}

						this.motionX *= 0.9900000095367432D;
						this.motionY *= 0.9800000190734863D;
						this.motionZ *= 0.9900000095367432D;
						this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);

						if (this.collidedHorizontally && !this.world.isRemote) {
							double d11 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
							double d3 = d8 - d11;
							float f5 = (float)(d3 * 10.0D - 3.0D);

							if (f5 > 0.0F) {
								this.playSound(this.getFallSound((int)f5), 1.0F, 1.0F);
								this.attackEntityFrom(DamageSource.FLY_INTO_WALL, f5);
							}
						}

						if (this.onGround && !this.world.isRemote) {
							this.setFlag(7, false);
						}
					}
					else {
						float f6 = 0.91F;
						BlockPos.PooledMutableBlockPos checkPos = BlockPos.PooledMutableBlockPos.retain(this.posX, this.getEntityBoundingBox().minY - 1.0D, this.posZ);
						float f7 = 0.16277136F / (f6 * f6 * f6);
						float f8;

						if (this.onGround) {
							f8 = this.getAIMoveSpeed() * f7;
						}
						else {
							f8 = this.jumpMovementFactor;
						}

						this.moveRelative(strafe, vertical, forward, f8);
						f6 = 0.91F;

						if (this.isOnLadder()) {
							float f9 = 0.15F;
							this.motionX = MathHelper.clamp(this.motionX, -0.15000000596046448D, 0.15000000596046448D);
							this.motionZ = MathHelper.clamp(this.motionZ, -0.15000000596046448D, 0.15000000596046448D);
							this.fallDistance = 0.0F;

							if (this.motionY < -0.15D)
								this.motionY = -0.15D;

							if (this.isSneaking() && this.motionY < 0.0D)
								this.motionY = 0.0D;
						}

						this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);

						if (this.collidedHorizontally && this.isOnLadder())
							this.motionY = 0.2D;

						if (this.isPotionActive(MobEffects.LEVITATION)) {
							this.motionY += (0.05D * (double)(this.getActivePotionEffect(MobEffects.LEVITATION).getAmplifier() + 1) - this.motionY) * 0.2D;
						}
						else {
							checkPos.setPos(this.posX, 0.0D, this.posZ);

							if (!this.world.isRemote || this.world.isBlockLoaded(checkPos) && this.world.getChunk(checkPos).isLoaded()) {
								if (!this.hasNoGravity())
									this.motionY -= 0.08D;
							}
							else if (this.posY > 0.0D) {
								this.motionY = -0.1D;
							}
							else {
								this.motionY = 0.0D;
							}
						}

						this.motionY *= 0.9800000190734863D;
						this.motionX *= (double)f6;
						this.motionZ *= (double)f6;
						checkPos.release();
					}
				}
				else {
					double d4 = this.posY;
					this.moveRelative(strafe, vertical, forward, 0.02F);
					this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
					this.motionX *= 0.5D;
					this.motionY *= 0.5D;
					this.motionZ *= 0.5D;

					if (!this.hasNoGravity())
						this.motionY -= 0.02D;

					if (this.collidedHorizontally && this.isOffsetPositionInLiquid(this.motionX, this.motionY + 0.6000000238418579D - this.posY + d4, this.motionZ))
						this.motionY = 0.30000001192092896D;
				}
			}
			else {
				double d0 = this.posY;
				float f1 = this.getWaterSlowDown();
				float f2 = 0.02F;
				float f3 = (float)EnchantmentHelper.getDepthStriderModifier(this);

				if (f3 > 3.0F)
					f3 = 3.0F;

				if (!this.onGround)
					f3 *= 0.5F;

				if (f3 > 0.0F) {
					f1 += (0.54600006F - f1) * f3 / 3.0F;
					f2 += (this.getAIMoveSpeed() - f2) * f3 / 3.0F;
				}

				this.moveRelative(strafe, vertical, forward, f2);
				this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
				this.motionX *= (double)f1;
				this.motionY *= 0.800000011920929D;
				this.motionZ *= (double)f1;

				if (!this.hasNoGravity())
					this.motionY -= 0.02D;

				if (this.collidedHorizontally && this.isOffsetPositionInLiquid(this.motionX, this.motionY + 0.6000000238418579D - this.posY + d0, this.motionZ))
					this.motionY = 0.30000001192092896D;
			}
		}

		this.prevLimbSwingAmount = this.limbSwingAmount;
		double d5 = this.posX - this.prevPosX;
		double d7 = this.posZ - this.prevPosZ;
		double d9 = this instanceof net.minecraft.entity.passive.EntityFlying ? this.posY - this.prevPosY : 0.0D;
		float f10 = MathHelper.sqrt(d5 * d5 + d9 * d9 + d7 * d7) * 4.0F;

		if (f10 > 1.0F)
			f10 = 1.0F;

		this.limbSwingAmount += (f10 - this.limbSwingAmount) * 0.4F;
		this.limbSwing += this.limbSwingAmount;
	}
}
