package net.tslat.aoa3.entity.boss.craexxeus;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAFlyingRangedMob;
import net.tslat.aoa3.entity.projectiles.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectiles.mob.EntityCraexxeusNuke;
import net.tslat.aoa3.entity.projectiles.mob.EntityCraexxeusShot;
import net.tslat.aoa3.entity.properties.BossEntity;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;

import javax.annotation.Nullable;

public class EntityCraexxeus extends AoAFlyingRangedMob implements BossEntity {
	private static final ResourceLocation bossBarTexture = new ResourceLocation("aoa3", "textures/gui/bossbars/craexxeus.png");
	public static final float entityWidth = 3.5f;
	private int chargeCooldown = 300;

	public EntityCraexxeus(World world) {
		super(world, entityWidth, 4.4375f);
	}

	@Override
	public float getEyeHeight() {
		return 3.9f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 1;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 3000;
	}

	@Override
	public double getBaseProjectileDamage() {
		return 10;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.1;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobCraexxeusLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobCraexxeusDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobCraexxeusHit;
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return SoundsRegister.shotCraexxeusFire;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityCraexxeus;
	}

	@Override
	public boolean isNonBoss() {
		return false;
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return new EntityCraexxeusShot(this, Enums.MobProjectileType.ENERGY);
	}

	@Override
	public ResourceLocation getBossBarTexture() {
		return bossBarTexture;
	}

	@Nullable
	@Override
	public SoundEvent getBossMusic() {
		return SoundsRegister.musicCraexxeus;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (world.isRemote && ticksExisted == 1)
			playMusic(this);
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		motionY *= 0.15;

		if (!world.isRemote) {
			if (chargeCooldown > 0) {
				chargeCooldown--;

				if (chargeCooldown == 40)
					world.playSound(null, posX, posY, posZ, SoundsRegister.mobCraexxeusCharge, SoundCategory.HOSTILE, 1.0f, 1.0f);
			}
			else {
				chargeCooldown = 300;

				if (getAttackTarget() != null) {
					EntityLivingBase target = getAttackTarget();
					BaseMobProjectile projectile = new EntityCraexxeusNuke(this, Enums.MobProjectileType.ENERGY);

					double distanceFactorX = target.posX - projectile.posX;
					double distanceFactorY = target.getEntityBoundingBox().minY + (target.height / 3) - projectile.posY;
					double distanceFactorZ = target.posZ - projectile.posZ;
					double hyp = MathHelper.sqrt(distanceFactorX * distanceFactorX + distanceFactorZ * distanceFactorZ) * 0.05d;

					world.playSound(null, posX, posY, posZ, SoundsRegister.shotCraexxeusNukeFire, SoundCategory.HOSTILE, 1.0f, 1.0f);
					projectile.shoot(distanceFactorX, distanceFactorY + hyp, distanceFactorZ, 1.6f, (float)(4 - this.world.getDifficulty().getId()));
					world.spawnEntity(projectile);
				}
			}
		}
	}

	@Override
	public void doProjectileEntityImpact(BaseMobProjectile projectile, Entity target) {
		if (projectile instanceof EntityCraexxeusNuke) {
			EntityUtil.dealBlasterDamage(this, target, projectile, (float)getBaseProjectileDamage() * 3, false);
		}
		else {
			super.doProjectileEntityImpact(projectile, target);
		}
	}

	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);

		if (!world.isRemote) {
			EntityXxeus xxeus = new EntityXxeus(this);

			world.spawnEntity(xxeus);
			setDead();
		}
	}

	@Override
	public void setAttackTarget(@Nullable EntityLivingBase target) {
		if (target instanceof BossEntity)
			return;

		super.setAttackTarget(target);
	}
}
