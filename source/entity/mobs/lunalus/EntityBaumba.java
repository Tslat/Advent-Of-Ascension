package net.tslat.aoa3.entity.mobs.lunalus;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ArmourRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoARangedMob;
import net.tslat.aoa3.entity.projectiles.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectiles.mob.EntityBloodball;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

public class EntityBaumba extends AoARangedMob {
	public static final float entityWidth = 0.5f;

	public EntityBaumba(World world) {
		super(world, entityWidth, 2f);
	}

	@Override
	public float getEyeHeight() {
		return  1.78125f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.1;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 200;
	}

	@Override
	public double getBaseProjectileDamage() {
		return 7.5;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0;
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
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextInt(100 - lootingMod) == 0)
			dropItem(ItemRegister.moltenUpgrader, 1);

		if (rand.nextInt(150 - lootingMod) == 0)
			dropItem(WeaponRegister.blasterRevolution, 1);

		if (rand.nextInt(30 - lootingMod) == 0) {
			switch (rand.nextInt(4)) {
				case 0:
					dropItem(ArmourRegister.SpacekingBody, 1);
					break;
				case 1:
					dropItem(ArmourRegister.SpacekingLegs, 1);
					break;
				case 2:
					dropItem(ArmourRegister.SpacekingHelmet, 1);
					break;
				case 3:
					dropItem(ArmourRegister.SpacekingBoots, 1);
			}
		}
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		if (rand.nextBoolean())
			dropItem(ItemRegister.coinSilver, 1 + rand.nextInt(2 + lootingMod));
	}

	@Override
	public void doProjectileImpactEffect(BaseMobProjectile projectile, Entity target) {
		world.createExplosion(this, projectile.posX, projectile.posY, projectile.posZ, 2f, false);
	}

	@Override
	public void doProjectileBlockImpact(BaseMobProjectile projectile, IBlockState blockHit, BlockPos pos, EnumFacing sideHit) {
		world.createExplosion(this, projectile.posX, projectile.posY, projectile.posZ, 2f, false);
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (!isDead && ticksExisted % 50 == 0) {
			world.playSound(null, posX, posY, posZ, SoundsRegister.mobBaumbaJump, SoundCategory.HOSTILE, 1.0f, 1.0f);

			motionY = 0.5;

			if (getAttackTarget() != null) {
				motionX = (getAttackTarget().posX - posX) * 0.0649;
				motionZ = (getAttackTarget().posZ - posZ) * 0.0649;
			}
		}
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return SoundsRegister.shotBaumbaFire;
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return new EntityBloodball(this, Enums.MobProjectileType.ENERGY);
	}
}
