package net.tslat.aoa3.content.entity.mob.nether;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.ai.behavior.EntityTracker;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.WalkTarget;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.ServerParticlePacket;
import net.tslat.aoa3.common.particletype.CustomisableParticleType;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.content.entity.base.AoARangedAttacker;
import net.tslat.aoa3.content.entity.brain.task.temp.ConditionlessHeldAttack;
import net.tslat.aoa3.content.entity.projectile.mob.BaseMobProjectile;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.behaviour.FirstApplicableBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.custom.attack.AnimatableMeleeAttack;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetWalkTargetToAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.InvalidateAttackTarget;
import net.tslat.smartbrainlib.util.BrainUtils;
import net.tslat.smartbrainlib.util.RandomUtil;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.RawAnimation;

import javax.annotation.Nullable;

public class EmbrakeEntity extends AoAMeleeMob<EmbrakeEntity> implements AoARangedAttacker {
	private static final RawAnimation BREATH_ATTACK = RawAnimation.begin().thenPlay("attack.breath.start").thenPlay("attack.breath.hold");
	private static final RawAnimation BREATH_ATTACK_END = RawAnimation.begin().thenPlay("attack.breath.end");

	public EmbrakeEntity(EntityType<? extends EmbrakeEntity> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	public BrainActivityGroup<EmbrakeEntity> getFightTasks() {
		return BrainActivityGroup.fightTasks(
				new InvalidateAttackTarget<>(),
				new FirstApplicableBehaviour<>(
						new AnimatableMeleeAttack<>(getPreAttackTime()).attackInterval(entity -> getAttackSwingDuration()),
						new FirstApplicableBehaviour<>(
								new BreathAttack(),
								new WalkUpToTarget()
						)
				));
	}

	@Override
	public void performRangedAttack(LivingEntity target, float distanceFactor) {}

	@Override
	public void doRangedAttackEntity(@org.jetbrains.annotations.Nullable BaseMobProjectile projectile, Entity target) {
		target.hurt(DamageSource.IN_FIRE, 2);
		target.setSecondsOnFire((int)Math.ceil(target.getRemainingFireTicks() / 20f) + 1);
	}

	@Override
	public void doRangedAttackBlock(@org.jetbrains.annotations.Nullable BaseMobProjectile projectile, BlockState blockHit, BlockPos pos, Direction sideHit) {}

	private static class WalkUpToTarget extends SetWalkTargetToAttackTarget<EmbrakeEntity> {
		@Override
		protected void start(EmbrakeEntity entity) {
			Brain<?> brain = entity.getBrain();
			LivingEntity target = BrainUtils.getTargetOfEntity(entity);

			if (entity.getSensing().hasLineOfSight(target) && BehaviorUtils.isWithinAttackRange(entity, target, 1)) {
				BrainUtils.clearMemory(brain, MemoryModuleType.WALK_TARGET);
			}
			else {
				BrainUtils.setMemory(brain, MemoryModuleType.LOOK_TARGET, new EntityTracker(target, true));
				BrainUtils.setMemory(brain, MemoryModuleType.WALK_TARGET, new WalkTarget(new EntityTracker(target, false), this.speedModifier, 4));
			}
		}
	}

	private static class BreathAttack extends ConditionlessHeldAttack<EmbrakeEntity> {
		public BreathAttack() {
			onTick(entity -> {
				if (getRunningTime() <= 15)
					return true;

				Vec3 position = entity.position();
				Vec3 direction = position.add(Vec3.directionFromRotation(0, entity.yBodyRot));

				double baseX = direction.x;
				double baseY = entity.getEyeY() - 0.3f;
				double baseZ = direction.z;
				ServerParticlePacket packet = new ServerParticlePacket();

				for (int i = 0; i < 5; i++) {
					Vec3 velocity = target.position().subtract(position.x + RandomUtil.randomScaledGaussianValue(0.5f), position.y + RandomUtil.randomScaledGaussianValue(0.5f) - 0.3, position.z + RandomUtil.randomScaledGaussianValue(0.5f)).normalize().scale(0.75f);

					packet.particle(new CustomisableParticleType.Data(AoAParticleTypes.BURNING_FLAME.get(), 0.3f, 3, 0, 0, 0, 0, entity.getId()), baseX, baseY, baseZ, velocity.x, velocity.y, velocity.z);
					packet.particle(ParticleTypes.SMALL_FLAME, baseX, baseY, baseZ, velocity.x, velocity.y, velocity.z);
				}

				packet.particle(ParticleTypes.SMOKE, baseX, baseY, baseZ, 0, 0, 0);

				AoAPackets.messageNearbyPlayers(packet, (ServerLevel)entity.level, entity.getEyePosition(), 64);

				return true;
			});
			requiresTarget();
			startCondition(entity -> {
				LivingEntity target = BrainUtils.getMemory(entity, MemoryModuleType.ATTACK_TARGET);

				if (target == null)
					return false;

				double dist = target.distanceToSqr(entity);

				return dist <= 64 && !entity.isWithinMeleeAttackRange(target) && BrainUtils.canSee(entity, target);
			});
			stopIf(entity -> {
				LivingEntity target = BrainUtils.getMemory(entity, MemoryModuleType.ATTACK_TARGET);

				if (target == null)
					return false;

				double dist = target.distanceToSqr(entity);

				return dist <= 64 && !entity.isWithinMeleeAttackRange(target) && BrainUtils.canSee(entity, target);
			});
			cooldownFor(entity -> 10);
			whenStarting(entity -> {
				entity.triggerAnim("Attack", "breath_start");
				entity.setImmobile(true);
			});
			whenStopping(entity -> {
				entity.triggerAnim("Attack", "breath_stop");
				entity.setImmobile(false);
			});
		}
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
		return 0.8125f;
	}

	@Override
	public int getMaxHeadYRot() {
		return 30;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_EMBRAKE_AMBIENT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_EMBRAKE_DEATH.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_EMBRAKE_HURT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getStepSound(BlockPos pos, BlockState blockState) {
		return AoASounds.ENTITY_GENERIC_HEAVY_STEP.get();
	}

	@Override
	protected int getPreAttackTime() {
		return 7;
	}

	@Override
	protected int getAttackSwingDuration() {
		return 10;
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(DefaultAnimations.genericWalkIdleController(this),
				DefaultAnimations.genericAttackAnimation(this, DefaultAnimations.ATTACK_BITE)
						.triggerableAnim("breath_start", BREATH_ATTACK)
						.triggerableAnim("breath_stop", BREATH_ATTACK_END));
	}
}
