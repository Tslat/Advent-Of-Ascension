package net.tslat.aoa3.entity.mobs.candyland;

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
import net.tslat.aoa3.entity.base.AoARangedMob;
import net.tslat.aoa3.entity.projectiles.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectiles.mob.EntityCherryShot;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

public class EntityCherryBlaster extends AoARangedMob {
	public static final float entityWidth = 0.875f;

	public EntityCherryBlaster(World world) {
		super(world, entityWidth, 1.0625f);
	}

	@Override
	public float getEyeHeight() {
		return 0.65625f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 35;
	}

	@Override
	protected double getBaseProjectileDamage() {
		return 5;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.207;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextInt(15 - lootingMod) == 0)
			dropItem(ItemRegister.sourPop, 1);

		if (rand.nextInt(3) == 0)
			dropItem(ItemRegister.tokensCandyland, 1 + rand.nextInt(2 + lootingMod));

		if (rand.nextInt(70 - lootingMod) == 0)
			dropItem(WeaponRegister.swordCaramelCarver, 1);
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		dropItem(ItemRegister.coinCopper, 1 + rand.nextInt(2 + lootingMod));
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.plantThump;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.plantThump;
	}

	@Override
	public void doProjectileImpactEffect(BaseMobProjectile projectile, Entity target) {
		world.createExplosion(this, projectile.posX, projectile.posY, projectile.posZ, 3, false);
	}

	@Override
	public void doProjectileBlockImpact(BaseMobProjectile projectile, IBlockState blockHit, BlockPos pos, EnumFacing sideHit) {
		world.createExplosion(this, projectile.posX, projectile.posY, projectile.posZ, 3, false);
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (isInWater() && getHealth() > 0)
			heal(0.4f);
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return SoundsRegister.shotCherryBlasterFire;
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return new EntityCherryShot(this, Enums.MobProjectileType.ENERGY);
	}
}
