package net.tslat.aoa3.entity.mobs.haven;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoAFlyingRangedMob;
import net.tslat.aoa3.entity.projectiles.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectiles.mob.EntityVolarShot;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

public class EntityVoliant extends AoAFlyingRangedMob {
	public static final float entityWidth = 3.5f;

	public EntityVoliant(World world) {
		super(world, entityWidth, 4.75f);
	}

	@Override
	public float getEyeHeight() {
		return 3.875f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 140;
	}

	@Override
	public double getBaseProjectileDamage() {
		return 20;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.1;
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return null;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobVoliantLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobVoliantDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobVoliantHit;
	}

	@Override
	protected int getSpawnChanceFactor() {
		return 15;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		dropItem(ItemRegister.realmstoneHaven, 1);
		dropItem(ItemRegister.tokensHaven, 3 + rand.nextInt(2 + lootingMod));
		dropItem(ItemRegister.voliantHeart, 1);

		if (rand.nextBoolean())
			dropItem(WeaponRegister.cannonRPG, 1);
	}

	@Override
	public void doProjectileImpactEffect(BaseMobProjectile projectile, Entity target) {
		world.createExplosion(this, projectile.posX, projectile.posY, projectile.posZ, 4.0f, false);
	}

	@Override
	public void doProjectileBlockImpact(BaseMobProjectile projectile, IBlockState blockHit, BlockPos pos, EnumFacing sideHit) {
		world.createExplosion(this, projectile.posX, projectile.posY, projectile.posZ, 4.0f, false);
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return new EntityVolarShot(this, Enums.MobProjectileType.PHYSICAL);
	}
}
