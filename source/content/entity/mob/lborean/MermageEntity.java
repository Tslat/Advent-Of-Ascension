package net.tslat.aoa3.content.entity.mob.lborean;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAWaterRangedMob;
import net.tslat.aoa3.content.entity.projectile.mob.BaseMobProjectile;
import net.tslat.aoa3.content.entity.projectile.mob.CyanShotEntity;
import org.jetbrains.annotations.Nullable;


public class MermageEntity extends AoAWaterRangedMob {
	public MermageEntity(EntityType<? extends WaterAnimal> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return 1.875f;
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(2, new RangedAttackGoal(this, 1.0d, 20, 40, 32));
		goalSelector.addGoal(7, new RandomStrollGoal(this, 1));
		goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8f));
		goalSelector.addGoal(8, new RandomLookAroundGoal(this));
		targetSelector.addGoal(1, new HurtByTargetGoal(this));
		targetSelector.addGoal(2, new NearestAttackableTargetGoal<Player>(this, Player.class, true));
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_MERMAGE_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_MERMAGE_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_MERMAGE_HURT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return AoASounds.ENTITY_MERMAGE_SHOOT.get();
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return new CyanShotEntity(this, BaseMobProjectile.Type.MAGIC);
	}
}
