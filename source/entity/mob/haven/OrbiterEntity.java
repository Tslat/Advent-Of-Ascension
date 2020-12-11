package net.tslat.aoa3.entity.mob.haven;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.PotionUtil;
import net.tslat.aoa3.util.RandomUtil;

import javax.annotation.Nullable;

public class OrbiterEntity extends AoAMeleeMob {
	public OrbiterEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 2.1875f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.1;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 83;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 7.5;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_ORBITER_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_ORBITER_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_ORBITER_HURT.get();
	}

	@Override
	protected SoundEvent getStepSound(BlockPos pos, BlockState blockState) {
		return null;
	}

	@Override
	protected void onAttack(Entity target) {
		if (!world.isRemote && target instanceof LivingEntity) {
			switch (RandomUtil.randomNumberUpTo(4)) {
				case 0:
					EntityUtil.applyPotions(target, new PotionUtil.EffectBuilder(Effects.SLOWNESS, 40));
					break;
				case 1:
					EntityUtil.applyPotions(target, new PotionUtil.EffectBuilder(Effects.POISON, 60));
					break;
				case 2:
					target.setFire(3);
					break;
				case 3:
					EntityUtil.healEntity((LivingEntity)target, 3);
					break;
			}
		}
	}
}
