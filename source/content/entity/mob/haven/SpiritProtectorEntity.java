package net.tslat.aoa3.content.entity.mob.haven;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoARangedMob;
import net.tslat.aoa3.content.entity.projectile.mob.BaseMobProjectile;
import net.tslat.aoa3.content.entity.projectile.mob.SpiritualShotEntity;

import javax.annotation.Nullable;

public class SpiritProtectorEntity extends AoARangedMob<SpiritProtectorEntity> {
	public SpiritProtectorEntity(EntityType<? extends SpiritProtectorEntity> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return 1.6875f;
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return AoASounds.ENTITY_SPIRIT_PROTECTOR_SHOOT.get();
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return new SpiritualShotEntity(this, BaseMobProjectile.Type.MAGIC);
	}
}
