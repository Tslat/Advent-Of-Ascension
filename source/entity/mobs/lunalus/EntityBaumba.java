package net.tslat.aoa3.entity.mobs.lunalus;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoARangedMob;
import net.tslat.aoa3.entity.projectiles.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectiles.mob.EntityBloodball;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.WorldUtil;

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
		return 0;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 134;
	}

	@Override
	public double getBaseProjectileDamage() {
		return 12;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.PLANT_THUMP;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.PLANT_THUMP;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityBaumba;
	}

	@Override
	public void doProjectileImpactEffect(BaseMobProjectile projectile, Entity target) {
		WorldUtil.createExplosion(this, world, projectile, 2f);
	}

	@Override
	public void doProjectileBlockImpact(BaseMobProjectile projectile, IBlockState blockHit, BlockPos pos, EnumFacing sideHit) {
		WorldUtil.createExplosion(this, world, projectile, 2f);
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (!isDead && ticksExisted % 50 == 0) {
			world.playSound(null, posX, posY, posZ, SoundsRegister.MOB_BAUMBA_JUMP, SoundCategory.HOSTILE, 1.0f, 1.0f);

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
		return SoundsRegister.BAUMBA_SHOOT;
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return new EntityBloodball(this, Enums.MobProjectileType.MAGIC);
	}
}
