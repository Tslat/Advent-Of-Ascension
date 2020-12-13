package net.tslat.aoa3.entity.mob.overworld.creepday;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.minion.AoAMinion;
import net.tslat.aoa3.event.dimension.OverworldEvents;
import net.tslat.aoa3.util.RandomUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class HostEntity extends AoAMeleeMob {
	public HostEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(1, new WaterAvoidingRandomWalkingGoal(this, 1));
		goalSelector.addGoal(2, new LookAtGoal(this, PlayerEntity.class, 8f));
		goalSelector.addGoal(3, new LookRandomlyGoal(this));
		targetSelector.addGoal(1, new NearestAttackableTargetGoal<AoAMinion>(this, AoAMinion.class, true));
		targetSelector.addGoal(2, new HurtByTargetGoal(this));
		targetSelector.addGoal(3, new NearestAttackableTargetGoal<PlayerEntity>(this, PlayerEntity.class, true));
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return sizeIn.height * 0.85f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0d;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 120;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 0;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.23;
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
	protected boolean isDaylightMob() {
		return true;
	}

	@Override
	public void livingTick() {
		super.livingTick();

		Vec3d motion = getMotion();
		double motionY = motion.getY();

		if (isAirBorne && motionY < 0)
			motionY *= 0.8;

		if ((getAttackTarget() == null ? RandomUtil.oneInNChance(100) : RandomUtil.oneInNChance(10)))
			motionY += 0.3;

		setMotion(motion.getX(), motionY, motion.getZ());

		if (!world.isRemote && getAttackTarget() != null && RandomUtil.oneInNChance(80)) {
			CreeperEntity creeper = new CreeperEntity(EntityType.CREEPER, world);

			creeper.setLocationAndAngles(getPosX(), getPosY(), getPosZ(), rand.nextFloat() * 360f, 0.0f);
			world.addEntity(creeper);
			playSound(AoASounds.ENTITY_HOST_SUMMON.get(), 1.0f, 1.0f);
		}
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}

	@Nonnull
	@Override
	protected OverworldEvents.Event getEventRequirement() {
		return OverworldEvents.Event.CREEP_DAY;
	}
}
