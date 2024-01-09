package net.tslat.aoa3.content.entity.mob.nether;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.client.render.AoAAnimations;
import net.tslat.aoa3.common.particletype.CustomisableParticleType;
import net.tslat.aoa3.common.registration.AoAAttributes;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.entity.AoADamageTypes;
import net.tslat.aoa3.content.entity.base.AoAEntityPart;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.content.entity.base.AoARangedAttacker;
import net.tslat.aoa3.content.entity.projectile.mob.BaseMobProjectile;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.effectslib.api.particle.ParticleBuilder;
import net.tslat.effectslib.networking.packet.TELParticlePacket;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.behaviour.FirstApplicableBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.custom.attack.AnimatableMeleeAttack;
import net.tslat.smartbrainlib.api.core.behaviour.custom.attack.ConditionlessHeldAttack;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetWalkTargetToAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.InvalidateAttackTarget;
import net.tslat.smartbrainlib.util.BrainUtils;
import net.tslat.smartbrainlib.util.RandomUtil;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.RawAnimation;


public class EmbrakeEntity extends AoAMeleeMob<EmbrakeEntity> implements AoARangedAttacker {
	private static final RawAnimation BREATH_ATTACK = RawAnimation.begin().thenPlay("attack.breath.start").thenPlay("attack.breath.hold");
	private static final RawAnimation BREATH_ATTACK_END = RawAnimation.begin().thenPlay("attack.breath.end");

	public EmbrakeEntity(EntityType<? extends EmbrakeEntity> entityType, Level world) {
		super(entityType, world);

		setParts(
				new AoAEntityPart<>(this, getBbWidth(), getBbHeight(), 0, 0, getBbWidth()).setDamageMultiplier(1.25f),
				new AoAEntityPart<>(this, getBbWidth(), getBbHeight(), 0, 0, -getBbWidth()),
				new AoAEntityPart<>(this, getBbWidth() * 0.75f, getBbHeight() * 0.75f, 0, 0, -getBbWidth() * 1.875f).setDamageMultiplier(0.75f)
		);
	}

	@Override
	public BrainActivityGroup<EmbrakeEntity> getFightTasks() {
		return BrainActivityGroup.fightTasks(
				new InvalidateAttackTarget<>().invalidateIf((entity, target) -> (target instanceof Player pl && pl.getAbilities().invulnerable) || distanceToSqr(target.position()) > Math.pow(getAttributeValue(Attributes.FOLLOW_RANGE), 2)),
				new FirstApplicableBehaviour<>(
						new AnimatableMeleeAttack<>(getPreAttackTime()).attackInterval(entity -> getAttackSwingDuration()),
						new FirstApplicableBehaviour<>(
								new BreathAttack(),
								new SetWalkTargetToAttackTarget<>().closeEnoughDist((entity, target) -> 4)
						)
				));
	}

	@Override
	public void performRangedAttack(LivingEntity target, float distanceFactor) {}

	@Override
	public void doRangedAttackEntity(@org.jetbrains.annotations.Nullable BaseMobProjectile projectile, Entity target) {
		DamageUtil.safelyDealDamage(DamageUtil.positionedEntityDamage(AoADamageTypes.MOB_FLAMETHROWER, this, position()), target, (float)getAttributeValue(AoAAttributes.RANGED_ATTACK_DAMAGE.get()));

		if (RandomUtil.oneInNChance(5) && target.getRemainingFireTicks() < 200)
			target.setSecondsOnFire((int)Math.ceil(Math.max(0, target.getRemainingFireTicks()) / 20f) + 1);
	}

	@Override
	public void doRangedAttackBlock(@org.jetbrains.annotations.Nullable BaseMobProjectile projectile, BlockState blockHit, BlockPos pos, Direction sideHit) {}

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
				TELParticlePacket packet = new TELParticlePacket(ParticleBuilder.forPosition(ParticleTypes.SMOKE, baseX, baseY, baseZ));

				for (int i = 0; i < 5; i++) {
					Vec3 velocity = target.position().subtract(position.x + RandomUtil.randomScaledGaussianValue(0.5f), position.y + RandomUtil.randomScaledGaussianValue(0.5f) - 0.3, position.z + RandomUtil.randomScaledGaussianValue(0.5f)).normalize().scale(0.75f);

					packet.particle(ParticleBuilder.forPosition(new CustomisableParticleType.Data(AoAParticleTypes.BURNING_FLAME.get(), 0.3f, 3, 0, 0, 0, 0, entity.getId()), baseX, baseY, baseZ).velocity(velocity.x, velocity.y, velocity.z));
					packet.particle(ParticleBuilder.forPosition(ParticleTypes.SMALL_FLAME, baseX, baseY, baseZ).velocity(velocity.x, velocity.y, velocity.z));
				}

				packet.sendToAllNearbyPlayers((ServerLevel)entity.level(), entity.getEyePosition(), 64);

				if (getRunningTime() % 9 == 0 || getRunningTime() % 19 == 0)
					entity.playSound(AoASounds.FLAMETHROWER.get(), 2, 1);

				return true;
			});
			requiresTarget();
			startCondition(entity -> {
				LivingEntity target = BrainUtils.getMemory(entity, MemoryModuleType.ATTACK_TARGET);

				if (target == null)
					return false;

				double dist = target.distanceToSqr(entity);
				double aggroRange = entity.getAttributeValue(AoAAttributes.AGGRO_RANGE.get());

				return dist <= aggroRange * aggroRange && !entity.isWithinMeleeAttackRange(target) && BrainUtils.canSee(entity, target);
			});
			stopIf(entity -> {
				LivingEntity target = BrainUtils.getMemory(entity, MemoryModuleType.ATTACK_TARGET);

				if (target == null)
					return true;

				double dist = target.distanceToSqr(entity);
				double distanceCutoff = entity.getAttributeValue(Attributes.FOLLOW_RANGE) * 0.75f;

				return dist > distanceCutoff * distanceCutoff || entity.isWithinMeleeAttackRange(target) || !BrainUtils.canSee(entity, target);
			});
			cooldownFor(entity -> 10);
			whenStarting(entity -> {
				entity.triggerAnim("Attack", "breath_start");
				IMMOBILE.set(entity, true);
			});
			whenStopping(entity -> {
				entity.triggerAnim("Attack", "breath_stop");
				IMMOBILE.set(entity, false);
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
		return AoASounds.LARGE_LIZARD_HISS.get();
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
		return AoASounds.ENTITY_GENERIC_DINO_STEP.get();
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
				AoAAnimations.genericAttackAnimation(this, DefaultAnimations.ATTACK_BITE)
						.triggerableAnim("breath_start", BREATH_ATTACK)
						.triggerableAnim("breath_stop", BREATH_ATTACK_END));
	}
}
