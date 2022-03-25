package net.tslat.aoa3.content.entity.mob.lunalus;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class FakeZorpEntity extends AoAMeleeMob {
	public FakeZorpEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);

		setInvulnerable(true);
	}

	public FakeZorpEntity(Entity target) {
		this(AoAEntities.Mobs.FAKE_ZORP.get(), target.level);

		this.moveTo(target.getX(), target.getY(), target.getZ(), target.yRot, target.xRot);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
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
			remove();
	}
}
