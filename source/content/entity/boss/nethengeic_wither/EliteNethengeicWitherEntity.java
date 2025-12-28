package net.tslat.aoa3.content.entity.boss.nethengeic_wither;

import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.schedule.Activity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.fluids.FluidType;
import net.tslat.aoa3.common.particleoption.EntityTrackingParticleOptions;
import net.tslat.aoa3.common.registration.AoAAttributes;
import net.tslat.aoa3.common.registration.AoAExplosions;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.entity.*;
import net.tslat.aoa3.content.entity.ai.movehelper.AirborneMoveControl;
import net.tslat.aoa3.content.entity.base.AoARangedAttacker;
import net.tslat.aoa3.content.entity.boss.AoABoss;
import net.tslat.aoa3.content.entity.brain.sensor.AggroBasedNearbyPlayersSensor;
import net.tslat.aoa3.content.entity.projectile.mob.BaseMobProjectile;
import net.tslat.aoa3.content.entity.projectile.mob.FireballEntity;
import net.tslat.aoa3.library.builder.AoAExplosionBuilder;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.PositionAndMotionUtil;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.behaviour.FirstApplicableBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.OneRandomBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.custom.attack.AnimatableRangedAttack;
import net.tslat.smartbrainlib.api.core.behaviour.custom.attack.ConditionlessHeldAttack;
import net.tslat.smartbrainlib.api.core.behaviour.custom.look.LookAtTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.CustomDelayedBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.InvalidateMemory;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.StayWithinDistanceOfAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.StrafeTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.WalkOrRunToWalkTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetRandomHoverTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetWalkTargetToAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.InvalidateAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.SetAdditionalAttackTargets;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.TargetOrRetaliate;
import net.tslat.smartbrainlib.api.core.sensor.ExtendedSensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.HurtBySensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.NearbyLivingEntitySensor;
import net.tslat.smartbrainlib.registry.SBLMemoryTypes;
import net.tslat.smartbrainlib.util.BrainUtils;
import net.tslat.tme.api.explosion.StandardExplosion;
import net.tslat.tme.api.object.builder.EffectBuilder;
import net.tslat.tme.api.particle.ParticleBuilder;
import net.tslat.tme.api.util.RandomUtil;
import net.tslat.tme.internal.networking.packet.TMEParticlePacket;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.constant.DefaultAnimations;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class EliteNethengeicWitherEntity extends AoABoss implements AoARangedAttacker {
	public static final EntityDataAccessor<Boolean> FLAME_AURA = makeSynchedData(EliteNethengeicWitherEntity.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Integer> SECOND_HEAD_TARGET = makeSynchedData(EliteNethengeicWitherEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> THIRD_HEAD_TARGET = makeSynchedData(EliteNethengeicWitherEntity.class, EntityDataSerializers.INT);

	private static final RawAnimation FLAMETHROWER_ANIM = RawAnimation.begin().thenLoop("attack.flamethrower.center");
	private static final RawAnimation CORE_SPIN_ANIM = RawAnimation.begin().thenLoop("attack.core.spin");
	private static final RawAnimation FIRE_BOMB_ANIM = RawAnimation.begin().thenLoop("attack.fire_bomb.idle");

	private static final int FIREBALL_STATE = 0;
	private static final int FLAMETHROWER_STATE = 1;
	private static final int FIRE_BOMB_STATE = 2;

	private boolean crossedThreshold = false;

	public EliteNethengeicWitherEntity(EntityType<? extends EliteNethengeicWitherEntity> entityType, Level level) {
		super(entityType, level);
	}

    @Override
    protected MoveControl createMoveControl() {
        return new AirborneMoveControl(this).canHover().withMaxTurn(30);
    }

    @Override
	protected void addSwingData(SwingData swings) {
		swings.put(FIREBALL_STATE, new SwingData.Swing(10, 6, RawAnimation.begin()));
		swings.put(FLAMETHROWER_STATE, new SwingData.Swing(0, 0, RawAnimation.begin()));
		swings.put(FIRE_BOMB_STATE, new SwingData.Swing(0, 0, RawAnimation.begin()));
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);

		builder.define(FLAME_AURA, false);
		builder.define(SECOND_HEAD_TARGET, -1);
		builder.define(THIRD_HEAD_TARGET, -1);
	}

	@Override
	protected PathNavigation createNavigation(Level level) {
		FlyingPathNavigation navigation = new FlyingPathNavigation(this, level);

		navigation.setCanOpenDoors(false);
		navigation.setCanFloat(true);
		navigation.setCanPassDoors(true);

		return navigation;
	}

	@Nullable
	@Override
	public SoundEvent getMusic() {
		return AoASounds.NETHENGEIC_WITHER_MUSIC.get();
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.CAMPFIRE_CRACKLE;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_NETHENGEIC_BEAST_HURT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_NETHENGEIC_BEAST_DEATH.get();
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState blockState) {}

	@Override
	public boolean canSwimInFluidType(FluidType type) {
		return type != NeoForgeMod.EMPTY_TYPE.value();
	}

	@Override
	public List<ExtendedSensor<? extends AoABoss>> getSensors() {
		return ObjectArrayList.of(
				new AggroBasedNearbyPlayersSensor<>(),
				new NearbyLivingEntitySensor<AoABoss>().setScanRate(entity -> 40),
				new HurtBySensor<>()
		);
	}

	@Override
	public BrainActivityGroup<AoABoss> getCoreTasks() {
		return BrainActivityGroup.coreTasks(
				new LookAtTarget<>(),
				new WalkOrRunToWalkTarget<>());
	}

	@Override
	public BrainActivityGroup<AoABoss> getIdleTasks() {
		return BrainActivityGroup.idleTasks(
				new TargetOrRetaliate<>()
						.useMemory(MemoryModuleType.NEAREST_VISIBLE_ATTACKABLE_PLAYER)
						.attackablePredicate(target -> DamageUtil.isAttackable(target) && EntityUtil.areProbablyEnemies(target, this)),
				new SetRandomHoverTarget<>().speedModifier(0.9f));
	}

	@Override
	public BrainActivityGroup<AoABoss> getFightTasks() {
		return BrainActivityGroup.fightTasks(
				new InvalidateAttackTarget<AoABoss>()
						.invalidateIf((entity, target) -> !DamageUtil.isAttackable(target) || distanceToSqr(target.position()) > Mth.square(getAttributeValue(Attributes.FOLLOW_RANGE))),
				new SetAdditionalAttackTargets<>()
						.withMemories(AoABrainMemories.SECOND_ATTACK_TARGET.get(), AoABrainMemories.THIRD_ATTACK_TARGET.get())
						.attackablePredicate((mob, memoryModuleType, livingEntity) -> DamageUtil.isAttackable(livingEntity) && EntityUtil.areProbablyEnemies(livingEntity, mob))
						.allowDuplicateTargeting()
						.whenTargeting((owner, memory, target) -> {
							if (memory == AoABrainMemories.SECOND_ATTACK_TARGET.get()) {
								setSynchedData(SECOND_HEAD_TARGET, target.getId());
							}
							else {
								setSynchedData(THIRD_HEAD_TARGET, target.getId());
							}
						}),
				new SetWalkTargetToAttackTarget<>()
						.speedMod((entity, target) -> 1.5f)
						.startCondition(entity -> hasAura() && !isAttackState(FIRE_BOMB_STATE))
						.stopIf(entity -> !hasAura()),
				new StayWithinDistanceOfAttackTarget<>()
						.startCondition(entity -> !hasAura())
						.stopIf(entity -> hasAura()),
				new StrafeTarget<>()
						.startCondition(entity -> !hasAura())
						.stopIf(entity -> hasAura()),
				new FirstApplicableBehaviour(
						new FlamethrowerAttack(MemoryModuleType.ATTACK_TARGET, entity -> PositionAndMotionUtil.moveRelativeToFacing(entity.getEyePosition(), entity.getYRot(), 0, 1, 0)),
						new FireBombAttack(),
						new OneRandomBehaviour<AoABoss>(
								Pair.of(new AnimatableRangedAttack(getSwingWarmupTicks(FIREBALL_STATE))
												.attackInterval(entity -> rand().numberBetween(getSwingDurationTicks(FIREBALL_STATE), (int)Math.floor(getSwingDurationTicks(FIREBALL_STATE) * 1.5f)))
												.attackRadius((int)getAttributeValue(Attributes.FOLLOW_RANGE))
												.whenStarting(entity -> triggerAnim("Middle Head", "fireball"))
												.whenStopping(entity -> BrainUtils.setForgettableMemory((EliteNethengeicWitherEntity)entity, SBLMemoryTypes.SPECIAL_ATTACK_COOLDOWN.get(), true, 21)),
										10),
								Pair.of(new CustomDelayedBehaviour<>(20)
												.whenActivating(entity -> toggleAura())
												.cooldownFor(entity -> entity.getRandom().nextIntBetweenInclusive(400, 800))
												.whenStarting(entity -> {
													triggerAnim("Middle Head", "fire_aura");

													entity.playSound(AoASounds.ENTITY_NETHENGEIC_BEAST_FLAME_AURA_ACTIVATE.get(), 2, 1);
												})
												.startCondition(entity -> !BrainUtils.hasMemory(entity, SBLMemoryTypes.SPECIAL_ATTACK_COOLDOWN.get())),
										1
								)
						)
				));
	}

	@Override
	public Map<Activity, BrainActivityGroup<? extends AoABoss>> getAdditionalTasks() {
		return Map.of(AoABrainActivities.FIGHT_2.get(),
				new BrainActivityGroup(AoABrainActivities.FIGHT_2.get())
						.priority(11)
						.behaviours(
								new InvalidateMemory<>(AoABrainMemories.SECOND_ATTACK_TARGET.get())
										.invalidateIf((entity, target) -> {
											if (!DamageUtil.isAttackable(target) || target.distanceToSqr(entity) > Mth.square((int)getAttributeValue(Attributes.FOLLOW_RANGE))) {
												setSynchedData(SECOND_HEAD_TARGET, -1);

												return true;
											}

											return false;
										}),
								new OneRandomBehaviour<>(
										Pair.of(
												new CustomDelayedBehaviour<>(getSwingWarmupTicks(FIREBALL_STATE))
														.whenActivating(entity -> {
															LivingEntity target = BrainUtils.getMemory(entity, AoABrainMemories.THIRD_ATTACK_TARGET.get());

															if (target != null)
																doFireball(PositionAndMotionUtil.moveRelativeToFacing(entity.getEyePosition(), entity.getYRot(), -1.25f, 0, -0.25f), target);
														})
														.whenStarting(entity -> triggerAnim("Left Head", "fireball"))
														.startCondition(entity -> BrainUtils.hasMemory(entity, AoABrainMemories.SECOND_ATTACK_TARGET.get())),
												20),
										Pair.of(new FlamethrowerAttack(AoABrainMemories.SECOND_ATTACK_TARGET.get(), entity -> PositionAndMotionUtil.moveRelativeToFacing(entity.getEyePosition(), entity.getYRot(), -1.25f, 0, -0.25f)), 1)
								).cooldownFor(entity -> rand().numberBetween(getSwingDurationTicks(FIREBALL_STATE) * (isAttackState(FIRE_BOMB_STATE) ? 4 : 2), getSwingDurationTicks(FIREBALL_STATE) * (isAttackState(FIRE_BOMB_STATE) ? 6 : 3)))

						)
						.requireAndWipeMemoriesOnUse(AoABrainMemories.SECOND_ATTACK_TARGET.get()),
				AoABrainActivities.FIGHT_3.get(),
				new BrainActivityGroup(AoABrainActivities.FIGHT_3.get())
						.priority(12)
						.behaviours(
								new InvalidateMemory<>(AoABrainMemories.THIRD_ATTACK_TARGET.get())
										.invalidateIf((entity, target) -> {
											if (!DamageUtil.isAttackable(target) || target.distanceToSqr(entity) > Mth.square((int)getAttributeValue(Attributes.FOLLOW_RANGE))) {
												setSynchedData(THIRD_HEAD_TARGET, -1);

												return true;
											}

											return false;
										}),
								new OneRandomBehaviour<>(
										Pair.of(
												new CustomDelayedBehaviour<>(getSwingWarmupTicks(FIREBALL_STATE))
														.whenActivating(entity -> {
															LivingEntity target = BrainUtils.getMemory(entity, AoABrainMemories.THIRD_ATTACK_TARGET.get());

															if (target != null)
																doFireball(PositionAndMotionUtil.moveRelativeToFacing(entity.getEyePosition(), entity.getYRot(), 1.25f, -0.5f, -0.25f), target);
														})
														.whenStarting(entity -> triggerAnim("Right Head", "fireball"))
														.startCondition(entity -> BrainUtils.hasMemory(entity, AoABrainMemories.THIRD_ATTACK_TARGET.get())),
												20),
										Pair.of(new FlamethrowerAttack(AoABrainMemories.THIRD_ATTACK_TARGET.get(), entity -> PositionAndMotionUtil.moveRelativeToFacing(entity.getEyePosition(), entity.getYRot(), 1.25f, -0.5f, -0.25f)), 1)
								).cooldownFor(entity -> rand().numberBetween(getSwingDurationTicks(FIREBALL_STATE) * (isAttackState(FIRE_BOMB_STATE) ? 4 : 2), getSwingDurationTicks(FIREBALL_STATE) * (isAttackState(FIRE_BOMB_STATE) ? 6 : 3)))
						)
						.requireAndWipeMemoriesOnUse(AoABrainMemories.THIRD_ATTACK_TARGET.get()));
	}

	@Override
	public Set<Activity> getAlwaysRunningActivities() {
		return ImmutableSet.of(Activity.CORE, AoABrainActivities.FIGHT_2.get(), AoABrainActivities.FIGHT_3.get());
	}

	private void doFireball(@Nullable Vec3 startPos, LivingEntity target) {
		BaseMobProjectile projectile = new FireballEntity(this.level(), this, BaseMobProjectile.Type.PHYSICAL);

		if (startPos != null)
			projectile.setPos(startPos);

		projectile.setYRot(getYHeadRot());
		PositionAndMotionUtil.moveRelativeToFacing(projectile, 0, 1, 0);

		Vec3 targetPos = target.getEyePosition();

		if (getHealth() < getMaxHealth() * 0.5f) {
			float timeToTarget = (float)targetPos.subtract(projectile.position()).length() / 1.6f;

			targetPos = targetPos.add(new Vec3(target.getX() - target.xOld, 0, target.getZ() - target.zOld)
					.multiply(timeToTarget, timeToTarget, timeToTarget));
		}

		PositionAndMotionUtil.moveTowards(projectile, targetPos, 1.6d, 4 - level().getDifficulty().getId());
		projectile.setDeltaMovement(PositionAndMotionUtil.accountForGravity(projectile.position(), projectile.getDeltaMovement(), target.position(), projectile.getGravity()));
		PositionAndMotionUtil.faceTowardsMotion(projectile);

		playSound(SoundEvents.BLAZE_SHOOT, 1, 1);

		level().addFreshEntity(projectile);
	}

	@Override
	public void performRangedAttack(LivingEntity target, float velocity) {
		doFireball(null, target);
	}

	@Override
	public void doRangedAttackEntity(@Nullable BaseMobProjectile projectile, Entity target) {
		if (projectile == null) {
			DamageUtil.safelyDealDamage(DamageUtil.positionedEntityDamage(AoADamageTypes.MOB_FLAMETHROWER, this, position()), target, 1);

			if (RandomUtil.oneInNChance(3) && target.getRemainingFireTicks() < 300)
				target.igniteForSeconds((int)Math.ceil(Math.max(0, target.getRemainingFireTicks()) / 20f) + 1);

			if (RandomUtil.oneInNChance(3) && target instanceof LivingEntity livingEntity)
				EntityUtil.applyPotions(livingEntity, this, new EffectBuilder(MobEffects.WITHER, 80).level(2));
		}
		else {
			float dmg = (float)getAttributeValue(AoAAttributes.RANGED_ATTACK_DAMAGE);

			if (isAttackState(FIRE_BOMB_STATE))
				dmg *= 1.5f;

			if (DamageUtil.doProjectileAttack(this, projectile, target, dmg)) {
				target.igniteForSeconds((int)Math.ceil(Math.max(0, target.getRemainingFireTicks()) / 20f) + 3);

				if (target instanceof LivingEntity livingEntity)
					EntityUtil.applyPotions(livingEntity, this, new EffectBuilder(AoAMobEffects.NETHENGEIC_CURSE, 200).level(level().getDifficulty().getId() + 1));
			}
		}
	}

	@Override
	public void doRangedAttackBlock(@Nullable BaseMobProjectile projectile, BlockState blockHit, BlockPos pos, Direction sideHit) {
		if (projectile == null)
			return;

		if (level() instanceof ServerLevel serverLevel)
			AoAExplosionBuilder.at(projectile, AoAExplosions.NETHENGEIC_WITHER_FIREBALL, StandardExplosion::new).explode();
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (hasAura()) {
			if (DamageUtil.isMeleeDamage(source)) {
				if (source.getDirectEntity() instanceof LivingEntity attacker) {
					DamageUtil.safelyDealDamage(DamageUtil.entityDamage(AoADamageTypes.MOB_FIRE_RECOIL, this), attacker, 5);
					attacker.igniteForSeconds((int)Math.ceil(Math.max(0, attacker.getRemainingFireTicks()) / 20f) + 2);
					EntityUtil.applyPotions(attacker, this, new EffectBuilder(AoAMobEffects.NETHENGEIC_CURSE, 200).level(3));
				}
			}
			else if (DamageUtil.isEnergyDamage(source)) {
				heal(amount);

				return false;
			}
		}

		float priorHealth = getHealth();

		if (super.hurt(source, amount)) {
			float maxHealth = getMaxHealth();
			float quarterHealth = maxHealth / 4f;
			float currentHealth = getHealth();

			for (float checkHealth = maxHealth - quarterHealth; checkHealth > 0; checkHealth -= quarterHealth) {
				if (priorHealth > checkHealth && currentHealth < checkHealth) {
					this.crossedThreshold = true;

					break;
				}
			}

			return true;
		}

		return false;
	}

	@Override
	public boolean canBeAffected(MobEffectInstance effect) {
		return effect.getEffect() != MobEffects.WITHER && effect.getEffect() != AoAMobEffects.NETHENGEIC_CURSE.get() && super.canBeAffected(effect);
	}

	@Override
	protected void checkFallDamage(double pY, boolean pOnGround, BlockState pState, BlockPos pPos) {}

	@Override
	protected void spawnSprintParticle() {}

	public boolean hasAura() {
		return getSynchedData(FLAME_AURA);
	}

	public void toggleAura() {
		setSynchedData(FLAME_AURA, !getSynchedData(FLAME_AURA));
	}

	@Override
	public void aiStep() {
		super.aiStep();

		if (level().isClientSide()) {
			if (hasAura()) {
				for (int i = 0; i < 3; i++) {
					double cos = Math.cos(getX() * RandomUtil.valueBetween(-1, 1));
					double sin = Math.sin(getZ() * RandomUtil.valueBetween(-1, 1));
					double startX = cos * getBbWidth() + getX();
					double startZ = sin * getBbWidth() + getZ();
					double startY = getRandomY();

					ParticleBuilder.forPosition(EntityTrackingParticleOptions.fromEntity(AoAParticleTypes.FIRE_AURA, this), startX, startY, startZ)
							.scaleMod(0.25f)
							.colourTint(1f, 1f, 1f, 0.75f)
							.velocity(RandomUtil.fiftyFifty() ? -1 : 1, RandomUtil.fiftyFifty() ? -1 : 1, RandomUtil.fiftyFifty() ? -1 : 1)
							.spawnClientParticles(level());
				}
			}

			ParticleBuilder.forPosition(ParticleTypes.FLAME, getX() + RandomUtil.valueBetween(-0.2f, 0.2f), getEyeY() - 1 + RandomUtil.valueBetween(-0.2f, 0.2f), getZ() + RandomUtil.valueBetween(-0.2f, 0.2f)).spawnClientParticles(level());

			if (getRandom().nextInt(10) == 0) {
				ParticleBuilder.forPosition(ParticleTypes.SMOKE, getX(), getEyeY() - 1, getZ()).spawnClientParticles(level());

				if (getDeltaMovement().horizontalDistanceSqr() == 0)
					ParticleBuilder.forPosition(ParticleTypes.DRIPPING_LAVA, getX(), getEyeY() - 1, getZ()).spawnClientParticles(level());
			}
		}
		else if (hasAura() && BrainUtils.getTargetOfEntity(this) == null) {
			toggleAura();
		}
	}

	public static AoAEntityStats.AttributeBuilder entityStats(EntityType<EliteNethengeicWitherEntity> entityType) {
		return AoAEntityStats.AttributeBuilder.createMonster(entityType)
				.health(950)
				.moveSpeed(0.33)
				.flyingSpeed(1.5f)
				.projectileDamage(30)
				.knockbackResist(1)
				.followRange(128)
				.aggroRange(128)
				.knockback(1f);
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(new AnimationController<>(this, "living", 0, state -> {
			if (isAttackState(FIRE_BOMB_STATE)) {
				state.getController().setAnimationSpeed(1);

				return state.setAndContinue(CORE_SPIN_ANIM);
			}

			state.getController().setAnimationSpeed(1 + (1 - (getHealth() / getMaxHealth())) * 5);
			state.getController().setAnimation(DefaultAnimations.LIVING);

			return PlayState.CONTINUE;
		}));
		controllers.add(new AnimationController<>(this, "Walk/Idle", 0, state -> {
			if (isAttackState(FIRE_BOMB_STATE))
				return state.setAndContinue(FIRE_BOMB_ANIM);

			return state.setAndContinue(state.isMoving() ? DefaultAnimations.WALK : DefaultAnimations.IDLE);
		}));
		controllers.add(new AnimationController<>(this, "Middle Head", 0, state -> {
			if (isAttackState(FLAMETHROWER_STATE))
				state.setAndContinue(FLAMETHROWER_ANIM);

			return PlayState.STOP;
		}).triggerableAnim("fireball", RawAnimation.begin().thenPlay("attack.fireball.center"))
				.triggerableAnim("fire_aura", RawAnimation.begin().thenPlay("misc.fire_aura")));
		controllers.add(new AnimationController<>(this, "Left Head", 0, state -> PlayState.STOP)
				.triggerableAnim("fireball", RawAnimation.begin().thenPlay("attack.fireball.left")));
		controllers.add(new AnimationController<>(this, "Right Head", 0, state -> PlayState.STOP)
				.triggerableAnim("fireball", RawAnimation.begin().thenPlay("attack.fireball.right")));
	}

	private static class FireBombAttack extends ConditionlessHeldAttack<EliteNethengeicWitherEntity> {
		private final List<FireballEntity> fireballs = new ObjectArrayList<>();

		public FireBombAttack() {
			runFor(entity -> 280);
			onTick(entity -> {
				if (this.runningTime >= 20 && !entity.hasAura())
					entity.toggleAura();

				entity.setDeltaMovement(this.target.position().subtract(entity.position()).normalize().multiply(0, 0.5f, 0).add(0, Math.cos(entity.tickCount) * 0.05f, 0));

				if (this.runningTime % 14 == 0) {
					if (!this.fireballs.isEmpty()) {
						for (FireballEntity fireball : this.fireballs) {
							Vec3 angle = fireball.position().subtract(entity.position()).multiply(1, 0, 1).normalize();

							fireball.setYRot((float)Math.atan2(angle.z, angle.y));
							PositionAndMotionUtil.moveTowards(fireball, fireball.position().add(angle.multiply(10, 10, 10)), 1.4d, 4 - entity.level().getDifficulty().getId());
							PositionAndMotionUtil.faceTowardsMotion(fireball);
						}

						entity.playSound(SoundEvents.BLAZE_SHOOT, 1, 1);
						this.fireballs.clear();
					}

					for (double angle = RandomUtil.valueBetween(-45, 45) * Mth.DEG_TO_RAD; angle <= 360 * Mth.DEG_TO_RAD; angle += 45 * Mth.DEG_TO_RAD) {
						for (double offsetY = -2f; offsetY <= 2; offsetY += 1f) {
							FireballEntity projectile = new FireballEntity(entity.level(), entity, BaseMobProjectile.Type.PHYSICAL);
							double xAngle = Math.cos(angle);
							double zAngle = Math.sin(angle);

							projectile.setPos(entity.position().add(2 * xAngle, entity.getBbHeight() / 2f + offsetY, 2 * zAngle));
							projectile.setDeltaMovement(0, -0.01f, 0);
							entity.level().addFreshEntity(projectile);
							this.fireballs.add(projectile);
						}
					}
				}

				return true;
			});
			startCondition(entity -> {
				if (!entity.crossedThreshold || entity.hasAura())
					return false;

				LivingEntity target = BrainUtils.getMemory(entity, MemoryModuleType.ATTACK_TARGET);

				return target != null && target.isAlive();
			});
			stopIf(entity -> (this.target = BrainUtils.getMemory(entity, MemoryModuleType.ATTACK_TARGET)) == null);
			whenStarting(entity -> {
				entity.triggerAnim("Middle Head", "fire_aura");

				entity.getNavigation().stop();
				entity.setXxa(0);
				entity.setYya(0);
				entity.setZza(0);
				entity.setDeltaMovement(Vec3.ZERO);
				BrainUtils.clearMemory(entity, MemoryModuleType.PATH);
				entity.setAttackState(FIRE_BOMB_STATE);

				entity.crossedThreshold = false;
			});
			whenStopping(entity -> {
				entity.setAttackState(FIREBALL_STATE);
				entity.triggerAnim("Middle Head", "fire_aura");
				BrainUtils.setForgettableMemory(entity, SBLMemoryTypes.SPECIAL_ATTACK_COOLDOWN.get(), true, 25);

				if (!this.fireballs.isEmpty()) {
					for (FireballEntity fireball : this.fireballs) {
						Vec3 angle = fireball.position().subtract(entity.position()).normalize();

						fireball.setYRot((float)Math.atan2(angle.z, angle.y));
						PositionAndMotionUtil.moveTowards(fireball, fireball.position().add(angle.multiply(10, 10, 10)), 1.2d, 4 - entity.level().getDifficulty().getId());
						PositionAndMotionUtil.faceTowardsMotion(fireball);
					}

					entity.playSound(SoundEvents.BLAZE_SHOOT, 1, 1);
					this.fireballs.clear();
				}

				if (entity.hasAura())
					entity.toggleAura();
			});
		}
	}

	private static class FlamethrowerAttack extends ConditionlessHeldAttack<EliteNethengeicWitherEntity> {
		private final Function<EliteNethengeicWitherEntity, Vec3> startPosFunction;
		private final MemoryModuleType<LivingEntity> targetMemory;

		public FlamethrowerAttack(MemoryModuleType<LivingEntity> targetMemory, Function<EliteNethengeicWitherEntity, Vec3> startPosFunction) {
			this.startPosFunction = startPosFunction;
			this.targetMemory = targetMemory;

			runFor(entity -> RandomUtil.numberBetween(160, 240));
			requiresTarget();
			onTick(entity -> {
				Vec3 position = this.startPosFunction.apply(entity);
				double baseX = position.x;
				double baseY = position.y;
				double baseZ = position.z;
				TMEParticlePacket packet = new TMEParticlePacket(ParticleBuilder.forPosition(ParticleTypes.LARGE_SMOKE, baseX, baseY, baseZ));

				for (int i = 0; i < 5; i++) {
					Vec3 velocity = this.target.getEyePosition().subtract(baseX + RandomUtil.scaledGaussianValue(0.5f), baseY + RandomUtil.scaledGaussianValue(0.5f), baseZ + RandomUtil.scaledGaussianValue(0.5f)).normalize().scale(0.9f);

					packet.particle(ParticleBuilder.forPosition(EntityTrackingParticleOptions.fromEntity(AoAParticleTypes.BURNING_FLAME, entity), baseX, baseY, baseZ)
							.colourTint(0f, 0f, 0f, 0f)
							.scaleMod(0.35f)
							.velocity(velocity));
					packet.particle(ParticleBuilder.forPosition(RandomUtil.fiftyFifty() ? ParticleTypes.SMALL_FLAME : ParticleTypes.SQUID_INK, baseX, baseY, baseZ)
							.velocity(velocity));
				}

				packet.sendToAllPlayersNearby((ServerLevel)entity.level(), EntityUtil.getEntityCenter(entity), 64);

				if (getRunningTime() % 9 == 0 || getRunningTime() % 19 == 0)
					entity.playSound(AoASounds.FLAMETHROWER.get(), 2, 1);

				return true;
			});
			startCondition(entity -> {
				if (this.targetMemory == MemoryModuleType.ATTACK_TARGET && !entity.hasAura())
					return false;

				LivingEntity target = BrainUtils.getMemory(entity, this.targetMemory);

				return target != null && target.isAlive();
			});
			stopIf(entity -> {
				LivingEntity target = BrainUtils.getMemory(entity, this.targetMemory);

				return target == null || !target.isAlive();
			});
			whenStarting(entity -> entity.setAttackState(FLAMETHROWER_STATE));
			whenStopping(entity -> {
				if (this.targetMemory != MemoryModuleType.ATTACK_TARGET)
					return;

				entity.setAttackState(FIREBALL_STATE);

				if (entity.hasAura())
					entity.toggleAura();

				BrainUtils.setForgettableMemory(entity, SBLMemoryTypes.SPECIAL_ATTACK_COOLDOWN.get(), true, 30);
			});
		}
	}
}