package net.tslat.aoa3.entity.mobs.lelyetia;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoARangedMob;
import net.tslat.aoa3.entity.projectiles.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectiles.mob.EntityLelyetianShot;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.player.PlayerUtil;

import javax.annotation.Nullable;

public class EntityLelyetianCaster extends AoARangedMob {
	public static final float entityWidth = 0.6f;

	public EntityLelyetianCaster(World world) {
		super(world, entityWidth, 2.375f);
	}

	@Override
	public float getEyeHeight() {
		return 1.75f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 60;
	}

	@Override
	public double getBaseProjectileDamage() {
		return 7;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.23;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobLelyetianLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobLelyetianDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobLelyetianHit;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityLelyetianCaster;
	}

	@Override
	public void doProjectileImpactEffect(BaseMobProjectile projectile, Entity target) {
		if (target instanceof EntityPlayer)
			PlayerUtil.getAdventPlayer((EntityPlayer)target).stats().consumeResource(Enums.Resources.ENERGY, 50, true);
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return null;
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return new EntityLelyetianShot(this, Enums.MobProjectileType.MAGIC);
	}
}
