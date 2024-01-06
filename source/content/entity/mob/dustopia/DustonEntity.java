package net.tslat.aoa3.content.entity.mob.dustopia;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAFlyingMeleeMob;
import net.tslat.aoa3.util.PlayerUtil;
import org.jetbrains.annotations.Nullable;


public class DustonEntity extends AoAFlyingMeleeMob {
	private int cooldown = 600;

	public DustonEntity(EntityType<? extends FlyingMob> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
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

		if (!level().isClientSide && isAlive() && tickCount % 20 == 0 && level().getEntitiesOfClass(Player.class, getBoundingBox().inflate(15), PlayerUtil::shouldPlayerBeAffected).size() > 0) {
			cooldown -= 20;

			if (cooldown <= 0) {
				level().addFreshEntity(new DustStriderEntity(this));

				cooldown = 600;
			}
		}
	}
}
