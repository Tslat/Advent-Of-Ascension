package net.tslat.aoa3.entity.mob.mysterium;

import net.minecraft.entity.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.ai.mob.CompletePanicGoal;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.PotionUtil;

import javax.annotation.Nullable;

public class EeoEntity extends AoAMeleeMob {
	public EeoEntity(EntityType<? extends MonsterEntity> entityType, World world) {
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

	@Override
	protected double getBaseKnockbackResistance() {
		return 0;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 45;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 7.5;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.3;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_HUNCH_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_HUNCH_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_HUNCH_HURT.get();
	}

	@Override
	protected void onAttack(Entity target) {
		EntityUtil.applyPotions(target, new PotionUtil.EffectBuilder(Effects.BLINDNESS, 80));
	}

	@Override
	protected void onHit(DamageSource source, float amount) {
		if (!world.isRemote && source != DamageSource.OUT_OF_WORLD) {
			if (!world.getEntitiesWithinAABB(LivingEntity.class, getBoundingBox().grow(20), entity -> entity instanceof SpiritGuardianEntity || entity instanceof SpiritProtectorEntity).isEmpty())
				return;

			if (rand.nextBoolean()) {
				SpiritGuardianEntity guardian = new SpiritGuardianEntity(AoAEntities.Mobs.SPIRIT_GUARDIAN.get(), world);

				guardian.setPositionAndRotation(getPosX(), getPosY(), getPosZ(), rotationYaw, rotationPitch);
				world.addEntity(guardian);
			}
			else {
				SpiritProtectorEntity protector = new SpiritProtectorEntity(AoAEntities.Mobs.SPIRIT_PROTECTOR.get(), world);

				protector.setPositionAndRotation(getPosX(), getPosY(), getPosZ(), rotationYaw, rotationPitch);
				world.addEntity(protector);
			}
		}
	}
}
