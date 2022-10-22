package net.tslat.aoa3.content.entity.mob.candyland;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoARangedMob;
import net.tslat.aoa3.content.entity.projectile.mob.BaseMobProjectile;
import net.tslat.aoa3.content.entity.projectile.mob.CherryShotEntity;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;

public class CherryBlasterEntity extends AoARangedMob<CherryBlasterEntity> {
	public CherryBlasterEntity(EntityType<? extends CherryBlasterEntity> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return 0.65625f;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_GENERIC_PLANT_HURT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_GENERIC_PLANT_HURT.get();
	}

	@Override
	public void onProjectileAttack(BaseMobProjectile projectile, Entity target) {
		WorldUtil.createExplosion(this, level, projectile, 3f);
	}

	@Override
	public void doRangedAttackBlock(BaseMobProjectile projectile, BlockState blockHit, BlockPos pos, Direction sideHit) {
		WorldUtil.createExplosion(this, level, projectile, 3f);
	}

	@Override
	public void aiStep() {
		super.aiStep();

		if (isInWater() && getHealth() > 0)
			heal(0.4f);
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return AoASounds.ENTITY_CHERRY_BLASTER_SHOOT.get();
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return new CherryShotEntity(this, BaseMobProjectile.Type.MAGIC);
	}
}
