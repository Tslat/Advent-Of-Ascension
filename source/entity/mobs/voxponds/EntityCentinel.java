package net.tslat.aoa3.entity.mobs.voxponds;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoARangedMob;
import net.tslat.aoa3.entity.projectiles.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectiles.mob.EntityBloodball;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

public class EntityCentinel extends AoARangedMob {
	public static final float entityWidth = 0.6875f;

	public EntityCentinel(World world) {
		super(world, entityWidth, 1.625f);
	}

	@Override
	public float getEyeHeight() {
		return 1f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 40;
	}

	@Override
	protected double getBaseProjectileDamage() {
		return 3;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.207;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_COW_HURT;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.ENTITY_COW_HURT;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextInt(7) == 0)
			dropItem(Item.getItemFromBlock(BlockRegister.bannerVox), 1);
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		dropItem(ItemRegister.coinCopper, 5 + rand.nextInt(9 + lootingMod));
	}

	@Override
	public void doProjectileImpactEffect(BaseMobProjectile projectile, Entity target) {
		world.createExplosion(this, projectile.posX, projectile.posY, projectile.posZ, 2, false);
	}

	@Override
	public void doProjectileBlockImpact(BaseMobProjectile projectile, IBlockState blockHit, BlockPos pos, EnumFacing sideHit) {
		world.createExplosion(this, projectile.posX, projectile.posY, projectile.posZ, 2, false);
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return SoundsRegister.gunWitherCannon;
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return new EntityBloodball(this, Enums.MobProjectileType.OTHER);
	}
}
