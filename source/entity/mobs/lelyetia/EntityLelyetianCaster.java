package net.tslat.aoa3.entity.mobs.lelyetia;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoARangedMob;
import net.tslat.aoa3.entity.projectiles.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectiles.mob.EntityLelyetianShot;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.PlayerUtil;

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
	protected double getBaseProjectileDamage() {
		return 4;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.207;
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

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextInt(6) == 0)
			dropItem(ItemRegister.seedsChilli, 1 + rand.nextInt(2 + lootingMod));

		if (rand.nextInt(20 - lootingMod) == 0)
			dropItem(ItemRegister.guardiansEye, 1);
	}

	@Override
	public void doProjectileImpactEffect(BaseMobProjectile projectile, Entity target) {
		if (target instanceof EntityPlayer)
			PlayerUtil.getAdventPlayer((EntityPlayer)target).consumeResource(Enums.Resources.ENERGY, 50, true);
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return null;
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return new EntityLelyetianShot(this, Enums.MobProjectileType.ENERGY);
	}
}
