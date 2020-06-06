package net.tslat.aoa3.entity.mobs.mysterium;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoARangedMob;
import net.tslat.aoa3.entity.projectiles.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectiles.mob.EntityWitherBall;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

public class EntityFungik extends AoARangedMob {
	public static final float entityWidth = 0.875f;

	public EntityFungik(World world) {
		super(world, entityWidth, 2.5f);
	}

	@Override
	public float getEyeHeight() {
		return 1.96875f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.7d;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 110;
	}

	@Override
	public double getBaseProjectileDamage() {
		return 10;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.207;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.MOB_FUNGI_LIVING;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.MOB_FUNGI_DEATH;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.MOB_FUNGI_HIT;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityFungik;
	}

	@Override
	public void doProjectileImpactEffect(BaseMobProjectile projectile, Entity target) {
		if (target instanceof EntityLivingBase)
			((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 90, 1, true, true));
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return SoundsRegister.FUNGIK_SHOOT;
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return new EntityWitherBall(this, Enums.MobProjectileType.MAGIC);
	}
}
