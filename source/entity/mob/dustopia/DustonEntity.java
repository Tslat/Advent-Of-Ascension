package net.tslat.aoa3.entity.mob.dustopia;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAFlyingMeleeMob;
import net.tslat.aoa3.util.player.PlayerUtil;

import javax.annotation.Nullable;

public class DustonEntity extends AoAFlyingMeleeMob {
	private int cooldown = 600;

	public DustonEntity(EntityType<? extends FlyingEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 0.875f;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_DUSTON_HURT.get();
	}

	@Override
	public void tick() {
		super.tick();

		if (!level.isClientSide && isAlive() && tickCount % 20 == 0 && level.getEntitiesOfClass(PlayerEntity.class, getBoundingBox().inflate(15), PlayerUtil::shouldPlayerBeAffected).size() > 0) {
			cooldown -= 20;

			if (cooldown <= 0) {
				level.addFreshEntity(new DustStriderEntity(this));

				cooldown = 600;
			}
		}
	}
}
