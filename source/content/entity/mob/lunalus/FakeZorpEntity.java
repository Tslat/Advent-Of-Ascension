package net.tslat.aoa3.content.entity.mob.lunalus;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.entity.AoAMobs;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class FakeZorpEntity extends AoAMeleeMob {
	public FakeZorpEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);

		setInvulnerable(true);
	}

	public FakeZorpEntity(Entity target) {
		this(AoAMobs.FAKE_ZORP.get(), target.level);

		this.moveTo(target.getX(), target.getY(), target.getZ(), target.getYRot(), target.getXRot());
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
		return 1.6875f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_ZORP_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_ZORP_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_ZORP_HURT.get();
	}

	@Override
	public void aiStep() {
		super.aiStep();

		if (tickCount >= 200)
			discard();
	}
}
