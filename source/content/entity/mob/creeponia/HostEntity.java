package net.tslat.aoa3.content.entity.mob.creeponia;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.RandomUtil;

import javax.annotation.Nullable;

public class HostEntity extends AoAMeleeMob {
	public HostEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(1, new WaterAvoidingRandomStrollGoal(this, 1));
		goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 8f));
		goalSelector.addGoal(3, new RandomLookAroundGoal(this));
		targetSelector.addGoal(1, new HurtByTargetGoal(this));
		targetSelector.addGoal(2, new NearestAttackableTargetGoal<Player>(this, Player.class, true));
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return sizeIn.height * 0.85f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_HOST_AMBIENT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_HOST_DEATH.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_HOST_AMBIENT.get();
	}

	@Override
	public void aiStep() {
		super.aiStep();

		Vec3 motion = getDeltaMovement();
		double motionY = motion.y();

		if (hasImpulse && motionY < 0)
			motionY *= 0.8;

		if ((getTarget() == null ? RandomUtil.oneInNChance(100) : RandomUtil.oneInNChance(10)))
			motionY += 0.3;

		setDeltaMovement(motion.x(), motionY, motion.z());

		if (!level.isClientSide && getTarget() != null && RandomUtil.oneInNChance(80)) {
			Creeper creeper = new Creeper(EntityType.CREEPER, level);

			creeper.moveTo(getX(), getY(), getZ(), random.nextFloat() * 360f, 0.0f);
			level.addFreshEntity(creeper);
			playSound(AoASounds.ENTITY_HOST_SUMMON.get(), 1.0f, 1.0f);
		}
	}

}
