package net.tslat.aoa3.content.entity.monster.nether;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.particleoption.EntityTrackingParticleOptions;
import net.tslat.aoa3.common.registration.AoAAttributes;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.entity.AoADamageTypes;
import net.tslat.aoa3.common.registration.entity.AoAEntityStats;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.content.entity.base.AoARangedAttacker;
import net.tslat.aoa3.content.entity.projectile.mob.BaseMobProjectile;
import net.tslat.aoa3.library.builder.EntitySpawnConditions;
import net.tslat.aoa3.library.builder.MultipartBuilder;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.behaviour.FirstApplicableBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.custom.attack.AnimatableMeleeAttack;
import net.tslat.smartbrainlib.api.core.behaviour.custom.attack.ConditionlessHeldAttack;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetWalkTargetToAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.InvalidateAttackTarget;
import net.tslat.smartbrainlib.util.BrainUtils;
import net.tslat.tme.api.particle.ParticleBuilder;
import net.tslat.tme.api.util.RandomUtil;
import net.tslat.tme.internal.networking.packet.TMEParticlePacket;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.constant.DefaultAnimations;

import static net.tslat.aoa3.library.builder.MultipartBuilder.Part;

public class EmbrakeEntity extends AoAMeleeMob<EmbrakeEntity> implements AoARangedAttacker {
	private static final RawAnimation BREATH_ATTACK = RawAnimation.begin().thenPlay("attack.breath.start").thenPlay("attack.breath.hold");
	private static final RawAnimation BREATH_ATTACK_END = RawAnimation.begin().thenPlay("attack.breath.end");

	public EmbrakeEntity(EntityType<? extends EmbrakeEntity> entityType, Level world) {
		super(entityType, world);
	}

	@Nullable
	@Override
	public MultipartBuilder<? extends EmbrakeEntity> definePartEntities() {
		return MultipartBuilder.of(this,
										  Part.sized(getBbWidth(), getBbHeight()).adjacentForward().damageMod(1.25f),
										  Part.sized(getBbWidth(), getBbHeight()).adjacentBehind().then(
												  Part.sized(getBbWidth() * 0.75f, getBbHeight() * 0.75f).adjacentBehind().damageMod(0.75f)));
	}

	@Override
	public BrainActivityGroup<EmbrakeEntity> getFightTasks() {
		return BrainActivityGroup.fightTasks(
				new InvalidateAttackTarget<>().invalidateIf((entity, target) -> !DamageUtil.isAttackable(target) || distanceToSqr(target.position()) > Mth.square(getAttributeValue(Attributes.FOLLOW_RANGE))),
				new FirstApplicableBehaviour<>(
						new AnimatableMeleeAttack<>(getPreAttackTime()).attackInterval(entity -> getAttackSwingDuration() + 2),
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
		DamageUtil.safelyDealDamage(DamageUtil.positionedEntityDamage(AoADamageTypes.MOB_FLAMETHROWER, this, position()), target, (float)getAttributeValue(AoAAttributes.RANGED_ATTACK_DAMAGE));

		if (RandomUtil.oneInNChance(5) && target.getRemainingFireTicks() < 200)
			target.igniteForSeconds((int)Math.ceil(Math.max(0, target.getRemainingFireTicks()) / 20f) + 1);
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
				TMEParticlePacket packet = new TMEParticlePacket(11);

				packet.particle(ParticleBuilder.forPosition(ParticleTypes.SMOKE, baseX, baseY, baseZ));

				for (int i = 0; i < 5; i++) {
					Vec3 velocity = target.position().subtract(position.x + RandomUtil.scaledGaussianValue(0.5f), position.y + RandomUtil.scaledGaussianValue(0.5f) - 0.3, position.z + RandomUtil.scaledGaussianValue(0.5f)).normalize().scale(0.75f);

					packet.particle(ParticleBuilder.forPosition(EntityTrackingParticleOptions.fromEntity(AoAParticleTypes.BURNING_FLAME, entity), baseX, baseY, baseZ)
							.scaleMod(0.3f)
							.lifespan(Mth.ceil(3 / (entity.random.nextFloat() * 0.8f + 0.2f)))
							.colourTint(0f, 0f, 0f, 0f)
							.velocity(velocity));
					packet.particle(ParticleBuilder.forPosition(ParticleTypes.SMALL_FLAME, baseX, baseY, baseZ)
							.velocity(velocity));
				}

				packet.sendToAllPlayersNearby((ServerLevel)entity.level(), entity.getEyePosition(), 64);

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
				double aggroRange = entity.getAttributeValue(AoAAttributes.AGGRO_RANGE);

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
				entity.setImmobile(true);
			});
			whenStopping(entity -> {
				entity.triggerAnim("Attack", "breath_stop");
				entity.setImmobile(false);
			});
		}
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

	@Override
	protected float getStepWeight() {
		return 2f;
	}

	@Override
	protected boolean isQuadruped() {
		return true;
	}

	@Override
	protected int getPreAttackTime() {
		return 7;
	}

	@Override
	protected int getAttackSwingDuration() {
		return 10;
	}

	public static SpawnPlacements.SpawnPredicate<EmbrakeEntity> spawnRules(EntityType<EmbrakeEntity> entityType) {
		return EntitySpawnConditions.create(entityType).noPeacefulSpawn().spawnChance(1 / 2f).noSpawnOn(Blocks.NETHER_WART_BLOCK).ifValidSpawnBlock();
	}

	public static AoAEntityStats.AttributeBuilder entityStats(EntityType<EmbrakeEntity> entityType) {
		return AoAEntityStats.AttributeBuilder.createMonster(entityType)
				.health(40)
				.moveSpeed(0.25)
				.meleeStrength(7)
				.projectileDamage(2f)
				.knockbackResist(0.6)
				.followRange(16)
				.aggroRange(10);
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(DefaultAnimations.genericWalkIdleController(this),
				DefaultAnimations.genericAttackAnimation(this, DefaultAnimations.ATTACK_BITE).transitionLength(0)
						.triggerableAnim("breath_start", BREATH_ATTACK)
						.triggerableAnim("breath_stop", BREATH_ATTACK_END));
	}
}
