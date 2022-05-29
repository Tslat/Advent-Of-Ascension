package net.tslat.aoa3.content.entity.mob.overworld.bigday;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.client.render.AoAAnimations;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.entity.AoAMobs;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.content.entity.mob.overworld.BushBabyEntity;
import net.tslat.aoa3.library.builder.EntityPredicate;
import net.tslat.aoa3.util.EntityRetrievalUtil;
import net.tslat.aoa3.util.RandomUtil;
import software.bernie.geckolib3.core.manager.AnimationData;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class LeafyGiantEntity extends AoAMeleeMob {
	private int nextBushBaby;

	public LeafyGiantEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);

		nextBushBaby = RandomUtil.randomNumberBetween(150, 500);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return 3f;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_GIANT_DEATH.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_GIANT_HURT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getStepSound(BlockPos pos, BlockState blockState) {
		return AoASounds.ENTITY_GENERIC_VERY_HEAVY_STEP.get();
	}

	@Override
	public boolean isPushable() {
		return false;
	}

	@Override
	protected int getAttackSwingDuration() {
		return 11;
	}

	@Override
	protected int getPreAttackTime() {
		return 6;
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		double preAttackHealth = getHealth();
		double halfHealth = getMaxHealth() * 0.5d;
		boolean success = super.hurt(source, amount);

		if (success && getHealth() <= halfHealth && preAttackHealth > halfHealth) {
			setInvulnerable(true);

			LivingEntity target = getTarget() != null ? getTarget() : this;

			for (int i = 0; i < 10; i++) {
				spawnBushBaby(target);
			}
		}

		return success;
	}

	@Override
	public boolean isInvulnerable() {
		return super.isInvulnerable();
	}

	@Override
	protected void customServerAiStep() {
		if (nextBushBaby <= tickCount) {
			LivingEntity target = getTarget();

			if (target == null)
				return;

			nextBushBaby = tickCount + RandomUtil.randomNumberBetween(150, 500);

			if (EntityRetrievalUtil.getEntities(this, 10, new EntityPredicate<>(this).isAlive().is(AoAMobs.BUSH_BABY.get())).size() < 5)
				spawnBushBaby(target);
		}

		if (isInvulnerable() && tickCount % 30 == 0) {
			if (EntityRetrievalUtil.getEntities(this, 40, new EntityPredicate<>(this).isAlive().is(AoAMobs.BUSH_BABY.get())).isEmpty())
				setInvulnerable(false);
		}
	}

	protected void spawnBushBaby(@Nonnull LivingEntity target) {
		BushBabyEntity bushBaby = new BushBabyEntity(AoAMobs.BUSH_BABY.get(), level);

		bushBaby.setPos(getX(), getBoundingBox().maxY, getZ());
		bushBaby.setDeltaMovement(Mth.clamp((target.getX() - getX()) * 0.2f, -0.85, 0.5f), 0.7, Mth.clamp((target.getZ() - getZ()) * 0.2f, -0.85, 0.85));
		bushBaby.setTarget(target);

		if (isOnFire())
			bushBaby.setSecondsOnFire(getRemainingFireTicks() / 20);

		if (getHealth() < 0.5f * getMaxHealth())
			setInvulnerable(true);

		level.addFreshEntity(bushBaby);
	}

	@Override
	protected void onAttack(Entity target) {
		if (isOnFire())
			target.setSecondsOnFire(getRemainingFireTicks() / 20);
	}

	@Override
	public void registerControllers(AnimationData animationData) {
		animationData.addAnimationController(AoAAnimations.genericWalkController(this));
		animationData.addAnimationController(AoAAnimations.genericAttackController(this, AoAAnimations.ATTACK_SLAM));
	}
}
