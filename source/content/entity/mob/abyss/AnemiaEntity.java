package net.tslat.aoa3.content.entity.mob.abyss;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.content.entity.base.AoAFlyingRangedMob;
import net.tslat.aoa3.content.entity.projectile.mob.AnemiaBombEntity;
import net.tslat.aoa3.content.entity.projectile.mob.BaseMobProjectile;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;

public class AnemiaEntity extends AoAFlyingRangedMob {
	public AnemiaEntity(EntityType<? extends FlyingMob> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return 2.0625f;
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return null;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_ANEMIA_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_ANEMIA_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_ANEMIA_HURT.get();
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return new AnemiaBombEntity(this, BaseMobProjectile.Type.MAGIC);
	}

	@Override
	public void onProjectileAttack(BaseMobProjectile projectile, Entity target) {
		if (target instanceof ServerPlayer)
			PlayerUtil.consumeResource((ServerPlayer)target, AoAResources.SPIRIT.get(), 50f, true);

		WorldUtil.createExplosion(this, level, projectile, 2.0f);
	}

	@Override
	public void doRangedAttackBlock(BaseMobProjectile projectile, BlockState blockHit, BlockPos pos, Direction sideHit) {
		WorldUtil.createExplosion(this, level, projectile, 2.0f);
	}

}
