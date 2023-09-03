package net.tslat.aoa3.content.entity.animal;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.content.entity.brain.task.temp.SetRandomFlyingTarget;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.effectslib.api.util.EffectBuilder;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.TargetOrRetaliate;
import net.tslat.smartbrainlib.util.BrainUtils;

import javax.annotation.Nullable;

public class MeganeuropsisEntity extends AoAMeleeMob<MeganeuropsisEntity> {
	public MeganeuropsisEntity(EntityType<? extends MeganeuropsisEntity> entityType, Level world) {
		super(entityType, world);

		this.moveControl = new MeganeuropsisMoveControl(this);
	}

	@Override
	protected PathNavigation createNavigation(Level level) {
		final FlyingPathNavigation navigation = new FlyingPathNavigation(this, level);

		navigation.setCanFloat(true);

		return navigation;
	}

	@Override
	public BrainActivityGroup<? extends MeganeuropsisEntity> getIdleTasks() {
		return BrainActivityGroup.idleTasks(
				new TargetOrRetaliate<>()
						.useMemory(MemoryModuleType.NEAREST_VISIBLE_ATTACKABLE_PLAYER)
						.attackablePredicate(target -> target.isAlive() && (!(target instanceof Player player) || !player.getAbilities().invulnerable) && !isAlliedTo(target)),
				new SetRandomFlyingTarget<>()
						.verticalWeight(entity -> -(entity.getRandom().nextInt(10) == 0 ? 1 : 0))
						.setRadius(4, 4)
						.startCondition(entity -> !BrainUtils.hasMemory(entity, MemoryModuleType.IS_PANICKING)));
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
		return 0.3125f;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return AoASounds.ENTITY_MEGANEUROPSIS_HURT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_MEGANEUROPSIS_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_MEGANEUROPSIS_AMBIENT.get();
	}

	@Override
	protected void spawnSprintParticle() {}

	@Override
	protected void playStepSound(BlockPos pos, BlockState block) {}

	@Override
	public boolean causeFallDamage(float distance, float damageMultiplier, DamageSource source) {
		return false;
	}

	@Override
	protected void checkFallDamage(double y, boolean onGroundIn, BlockState state, BlockPos pos) {}

	@Override
	public boolean onClimbable() {
		return false;
	}

	@Override
	protected void onAttack(Entity target) {
		if (target instanceof LivingEntity livingTarget && rand().oneInNChance(10))
			EntityUtil.applyPotions(livingTarget, new EffectBuilder(MobEffects.CONFUSION, 120).hideParticles());
	}

	@Override
	public MobType getMobType() {
		return MobType.ARTHROPOD;
	}

	private static class MeganeuropsisMoveControl extends MoveControl {
		private int cooldown = 0;

		public MeganeuropsisMoveControl(Mob mob) {
			super(mob);
		}

		@Override
		public void strafe(float pForward, float pStrafe) {
			this.operation = MoveControl.Operation.STRAFE;
			this.strafeForwards = pForward;
			this.strafeRight = pStrafe;
			this.speedModifier = 0.5d;
		}

		@Override
		public void tick() {
			if (this.mob.tickCount > 0) {
				if (this.cooldown < this.mob.tickCount) {
					this.cooldown = 0;
				}
				else {
					this.mob.setSpeed(0);
					this.mob.setDeltaMovement(this.mob.getDeltaMovement().scale(0.5f));
					this.mob.setYya(0);
					this.mob.setZza(0);

					return;
				}
			}

			if (this.operation == MoveControl.Operation.STRAFE) {
				this.mob.setNoGravity(true);

				this.operation = MoveControl.Operation.WAIT;
				float moveSpeed = (float)(this.speedModifier * this.mob.getAttributeValue((this.mob.onGround() ? Attributes.MOVEMENT_SPEED : Attributes.FLYING_SPEED)));

				this.mob.setSpeed(moveSpeed);
				this.mob.setZza(this.strafeForwards);
				this.mob.setXxa(this.strafeRight);
			}
			else if (this.operation == MoveControl.Operation.MOVE_TO) {
				this.mob.setNoGravity(true);

				this.operation = MoveControl.Operation.WAIT;
				double distX = this.wantedX - this.mob.getX();
				double distY = this.wantedY - this.mob.getY();
				double distZ = this.wantedZ - this.mob.getZ();
				double distSq = distX * distX + distY * distY + distZ * distZ;

				if (distSq < 0.9) {
					this.mob.setYya(0);
					this.mob.setZza(0);
					this.cooldown = this.mob.tickCount + 20;

					return;
				}

				this.mob.setYRot(rotlerp(this.mob.getYRot(), (float)(Mth.atan2(distZ, distX) * Mth.RAD_TO_DEG) - 90, 180));

				float moveSpeed = (float)(this.speedModifier * this.mob.getAttributeValue((this.mob.onGround() ? Attributes.MOVEMENT_SPEED : Attributes.FLYING_SPEED)));
				double lateralDist = Math.sqrt(distX * distX + distZ * distZ);

				this.mob.setSpeed(moveSpeed);
				this.mob.setZza(2);

				if (Math.abs(distY) > (double)0.75f || Math.abs(lateralDist) > (double)0.75f) {
					float angle = (float)(-(Mth.atan2(distY, lateralDist) * Mth.RAD_TO_DEG));

					this.mob.setXRot(rotlerp(this.mob.getXRot(), angle, 1f));
					this.mob.setYya(distY > 0 ? moveSpeed : -moveSpeed);
				}
				else {
					this.mob.setSpeed(0);
				}
			}
			else {
				this.mob.setYya(0);
				this.mob.setZza(0);
			}
		}
	}
}
