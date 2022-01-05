package net.tslat.aoa3.object.entity.animal;

import net.minecraft.entity.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.object.entity.ai.mob.CompletePanicGoal;
import net.tslat.aoa3.object.entity.base.AoAAnimal;
import net.tslat.aoa3.object.entity.mob.haven.SpiritGuardianEntity;
import net.tslat.aoa3.object.entity.mob.haven.SpiritProtectorEntity;

import javax.annotation.Nullable;

public class EeoEntity extends AoAAnimal {
	public EeoEntity(EntityType<? extends AnimalEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();

		goalSelector.addGoal(1, new CompletePanicGoal(this, 200, 1.1d));
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 1.125f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.RABBIT_AMBIENT;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.RABBIT_DEATH;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.RABBIT_HURT;
	}

	@Override
	protected boolean isBreedable() {
		return false;
	}

	@Override
	public boolean skipAttackInteraction(Entity entity) {
		if (!level.isClientSide()) {
			if (!level.getEntitiesOfClass(LivingEntity.class, getBoundingBox().inflate(20), nearbyEntity -> nearbyEntity instanceof SpiritGuardianEntity || nearbyEntity instanceof SpiritProtectorEntity).isEmpty())
				return false;

			if (random.nextBoolean()) {
				SpiritGuardianEntity guardian = new SpiritGuardianEntity(AoAEntities.Mobs.SPIRIT_GUARDIAN.get(), level);

				guardian.absMoveTo(getX(), getY(), getZ(), yRot, xRot);
				level.addFreshEntity(guardian);
			}
			else {
				SpiritProtectorEntity protector = new SpiritProtectorEntity(AoAEntities.Mobs.SPIRIT_PROTECTOR.get(), level);

				protector.absMoveTo(getX(), getY(), getZ(), yRot, xRot);
				level.addFreshEntity(protector);
			}
		}

		return false;
	}
}
