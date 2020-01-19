package net.tslat.aoa3.entity.boss.gyro;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIFindEntityNearest;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAFlyingRangedMob;
import net.tslat.aoa3.entity.base.ai.mob.EntityAIFlyingLookAround;
import net.tslat.aoa3.entity.base.ai.mob.EntityAIFlyingRangedAttack;
import net.tslat.aoa3.entity.base.ai.mob.EntityAIRandomFly;
import net.tslat.aoa3.entity.minions.AoAMinion;
import net.tslat.aoa3.entity.projectiles.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectiles.mob.EntityBulletShot;
import net.tslat.aoa3.entity.properties.BossEntity;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nullable;

public class EntityGyro extends AoAFlyingRangedMob implements BossEntity {
	private static final ResourceLocation bossBarTexture = new ResourceLocation("aoa3", "textures/gui/bossbars/gyro.png");
	public static final float entityWidth = 1.375f;

	public EntityGyro(EntityGyrocopter copter) {
		this(copter.world);

		setLocationAndAngles(copter.posX, copter.posY, copter.posZ, copter.rotationYaw, copter.rotationPitch);
	}

	public EntityGyro(World world) {
		super(world, entityWidth, 1.625f);
	}

	@Override
	public float getEyeHeight() {
		return 1.40625f;
	}

	@Override
	protected void initEntityAI() {
		this.tasks.addTask(1, new EntityAIRandomFly(this, true));
		this.tasks.addTask(2, new EntityAIFlyingLookAround(this));
		this.tasks.addTask(3, new EntityAILookIdle(this));
		this.tasks.addTask(4, new EntityAIFlyingRangedAttack(this, 10, 20));
		this.targetTasks.addTask(1, new EntityAIFindEntityNearest(this, AoAMinion.class));
		this.targetTasks.addTask(2, new EntityAIFindEntityNearestPlayer(this));
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 2500;
	}

	@Override
	public double getBaseProjectileDamage() {
		return 5;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.1;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobGyroLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobGyroDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobGyroHit;
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return SoundsRegister.gunMinigun;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityGyro;
	}

	@Override
	public boolean isNonBoss() {
		return false;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (world.isRemote && ticksExisted == 1)
			playMusic(this);
	}

	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);

		if (!world.isRemote && !isAIDisabled()) {
			Entity source = cause.getTrueSource();
			EntityPlayer killer = null;

			if (source != null) {
				if (source instanceof EntityPlayer) {
					killer = (EntityPlayer)source;
				}
				else if (source instanceof EntityTameable && ((EntityTameable)source).getOwner() instanceof EntityPlayer) {
					killer = (EntityPlayer)((EntityTameable)source).getOwner();
				}
			}

			if (killer != null)
				StringUtil.sendMessageWithinRadius(StringUtil.getLocaleWithArguments("message.mob.gyro.kill", killer.getDisplayNameString()), this, 50);
		}
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return new EntityBulletShot(this, Enums.MobProjectileType.PHYSICAL);
	}

	@Override
	public ResourceLocation getBossBarTexture() {
		return bossBarTexture;
	}

	@Nullable
	@Override
	public SoundEvent getBossMusic() {
		return SoundsRegister.musicGyro;
	}

	@Override
	public void setAttackTarget(@Nullable EntityLivingBase target) {
		if (target instanceof BossEntity)
			return;

		super.setAttackTarget(target);
	}
}
