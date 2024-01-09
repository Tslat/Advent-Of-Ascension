package net.tslat.aoa3.content.entity.boss.nethengeic_wither;

import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.schedule.Activity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.fluids.FluidType;
import net.tslat.aoa3.common.particletype.CustomisableParticleType;
import net.tslat.aoa3.common.registration.AoAAttributes;
import net.tslat.aoa3.common.registration.AoAExplosions;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.entity.AoABrainActivities;
import net.tslat.aoa3.common.registration.entity.AoABrainMemories;
import net.tslat.aoa3.common.registration.entity.AoADamageTypes;
import net.tslat.aoa3.common.registration.entity.AoAMobEffects;
import net.tslat.aoa3.content.entity.ai.movehelper.AirborneMoveControl;
import net.tslat.aoa3.content.entity.base.AoARangedAttacker;
import net.tslat.aoa3.content.entity.boss.AoABoss;
import net.tslat.aoa3.content.entity.brain.sensor.AggroBasedNearbyPlayersSensor;
import net.tslat.aoa3.content.entity.projectile.mob.BaseMobProjectile;
import net.tslat.aoa3.content.entity.projectile.mob.FireballEntity;
import net.tslat.aoa3.library.object.EntityDataHolder;
import net.tslat.aoa3.library.object.explosion.StandardExplosion;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.PositionAndMotionUtil;
import net.tslat.effectslib.api.particle.ParticleBuilder;
import net.tslat.effectslib.networking.packet.TELParticlePacket;
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
import net.tslat.smartbrainlib.util.RandomUtil;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class NethengeicWitherEntity extends AoABoss implements AoARangedAttacker {
	public static final EntityDataHolder<Boolean> FLAME_AURA = EntityDataHolder.register(NethengeicWitherEntity.class, EntityDataSerializers.BOOLEAN, false, entity -> entity.flameAura, (entity, value) -> entity.flameAura = value);
	public static final EntityDataHolder<Integer> SECOND_HEAD_TARGET = EntityDataHolder.register(NethengeicWitherEntity.class, EntityDataSerializers.INT, -1, entity -> entity.secondHeadTarget, (entity, value) -> entity.secondHeadTarget = value);
	public static final EntityDataHolder<Integer> THIRD_HEAD_TARGET = EntityDataHolder.register(NethengeicWitherEntity.class, EntityDataSerializers.INT, -1, entity -> entity.thirdHeadTarget, (entity, value) -> entity.thirdHeadTarget = value);

	private static final RawAnimation FLAMETHROWER_ANIM = RawAnimation.begin().thenLoop("attack.flamethrower.center");
	private static final RawAnimation CORE_SPIN_ANIM = RawAnimation.begin().thenLoop("attack.core.spin");
	private static final RawAnimation FIRE_BOMB_ANIM = RawAnimation.begin().thenLoop("attack.fire_bomb.idle");

	private static final int FIREBALL_STATE = 0;
	private static final int FLAMETHROWER_STATE = 1;
	private static final int FIRE_BOMB_STATE = 2;

	private boolean flameAura = false;
	private boolean doneFireBomb = false;
	private int secondHeadTarget = -1;
	private int thirdHeadTarget = -1;

	public NethengeicWitherEntity(EntityType<? extends NethengeicWitherEntity> entityType, Level level) {
		super(entityType, level);

		this.moveControl = new AirborneMoveControl(this, 30, true);
		setNoGravity(true);
	}

	@Override
	protected void addSwingData(SwingData swings) {
		swings.put(FIREBALL_STATE, new SwingData.Swing(10, 6, RawAnimation.begin()));
		swings.put(FLAMETHROWER_STATE, new SwingData.Swing(0, 0, RawAnimation.begin()));
		swings.put(FIRE_BOMB_STATE, new SwingData.Swing(0, 0, RawAnimation.begin()));
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();

		registerDataParams(FLAME_AURA);
		registerDataParams(SECOND_HEAD_TARGET);
		registerDataParams(THIRD_HEAD_TARGET);
	}

	@Override
	protected PathNavigation createNavigation(Level level) {
		FlyingPathNavigation navigation = new FlyingPathNavigation(this, level);

		navigation.setCanOpenDoors(false);
		navigation.setCanFloat(true);
		navigation.setCanPassDoors(true);

		return navigation;
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
		return 3;
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
	protected SoundEvent getStepSound(BlockPos pos, BlockState blockState) {
		return null;
	}

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
						.attackablePredicate(target -> target.isAlive() && (!(target instanceof Player player) || !player.getAbilities().invulnerable) && !isAlliedTo(target)),
				new SetRandomHoverTarget<>().speedModifier(0.9f));
	}

	@Override
	public BrainActivityGroup<AoABoss> getFightTasks() {
		return BrainActivityGroup.fightTasks(
				new InvalidateAttackTarget<AoABoss>()
						.invalidateIf((entity, target) -> !target.isAlive() || (target instanceof Player pl && pl.getAbilities().invulnerable) || distanceToSqr(target.position()) > Math.pow(getAttributeValue(Attributes.FOLLOW_RANGE), 2)),
				new SetAdditionalAttackTargets<>()
						.withMemories(AoABrainMemories.SECOND_ATTACK_TARGET.get(), AoABrainMemories.THIRD_ATTACK_TARGET.get())
						.allowDuplicateTargeting()
						.whenTargeting((owner, memory, target) -> {
							if (memory == AoABrainMemories.SECOND_ATTACK_TARGET.get()) {
								SECOND_HEAD_TARGET.set(owner, target.getId());
							}
							else {
								THIRD_HEAD_TARGET.set(owner, target.getId());
							}
						}),
				new SetWalkTargetToAttackTarget<>()
						.speedMod((entity, target) -> 1.25f)
						.startCondition(entity -> hasAura() && !ATTACK_STATE.is(entity, FIRE_BOMB_STATE))
						.stopIf(entity -> !hasAura()),
				new StayWithinDistanceOfAttackTarget<>()
						.startCondition(entity -> !hasAura())
						.stopIf(entity -> hasAura()),
				new StrafeTarget<>()
						.startCondition(entity -> !hasAura())
						.stopIf(entity -> hasAura()),
				new FirstApplicableBehaviour(
						new FireBombAttack(),
						new FlamethrowerAttack(),
						new OneRandomBehaviour<AoABoss>(
								Pair.of(new AnimatableRangedAttack(getSwingWarmupTicks(FIREBALL_STATE))
												.attackInterval(entity -> rand().randomNumberBetween(getSwingDurationTicks(FIREBALL_STATE), getSwingDurationTicks(FIREBALL_STATE) * 2))
												.attackRadius((int)getAttributeValue(Attributes.FOLLOW_RANGE))
												.whenStarting(entity -> triggerAnim("Middle Head", "fireball"))
												.whenStopping(entity -> BrainUtils.setForgettableMemory((NethengeicWitherEntity)entity, SBLMemoryTypes.SPECIAL_ATTACK_COOLDOWN.get(), true, 21)),
										10),
								Pair.of(new CustomDelayedBehaviour<>(20)
												.whenActivating(entity -> toggleAura())
												.cooldownFor(entity -> entity.getRandom().nextIntBetweenInclusive(400, 800))
												.whenStarting(entity -> {
													triggerAnim("Middle Head", "fire_aura");

													entity.playSound(AoASounds.ENTITY_NETHENGEIC_BEAST_FLAME_AURA_ACTIVATE.get(), 2, 1);
												}),
										1
								)
						).startCondition(entity -> !BrainUtils.hasMemory(entity, SBLMemoryTypes.SPECIAL_ATTACK_COOLDOWN.get()))
				));
	}

	@Override
	public Map<Activity, BrainActivityGroup<? extends AoABoss>> getAdditionalTasks() {
		return Map.of(AoABrainActivities.FIGHT_2.get(),
				new BrainActivityGroup<AoABoss>(AoABrainActivities.FIGHT_2.get())
						.priority(11)
						.behaviours(
								new InvalidateMemory<>(AoABrainMemories.SECOND_ATTACK_TARGET.get())
										.invalidateIf((entity, memory) -> {
											if (!memory.isAlive() || (memory instanceof Player pl && pl.getAbilities().invulnerable) || memory.distanceToSqr(entity) > Math.pow((int)getAttributeValue(Attributes.FOLLOW_RANGE), 2)) {
												SECOND_HEAD_TARGET.set(entity, -1);

												return true;
											}

											return false;
										}),
								new CustomDelayedBehaviour<>(getSwingWarmupTicks(FIREBALL_STATE))
										.whenActivating(entity -> {
											LivingEntity target = BrainUtils.getMemory(entity, AoABrainMemories.THIRD_ATTACK_TARGET.get());

											if (target != null)
												doFireball(PositionAndMotionUtil.moveRelativeToFacing(entity.getEyePosition(), entity.getYRot(), -1.25f, 0, -0.25f), target);
										})
										.cooldownFor(entity -> rand().randomNumberBetween(getSwingDurationTicks(FIREBALL_STATE) * (ATTACK_STATE.is(entity, FIRE_BOMB_STATE) ? 6 : 3), getSwingDurationTicks(FIREBALL_STATE) * (ATTACK_STATE.is(entity, FIRE_BOMB_STATE) ? 10 : 5)))
										.whenStarting(entity -> triggerAnim("Left Head", "fireball"))
										.startCondition(entity -> BrainUtils.hasMemory(entity, AoABrainMemories.SECOND_ATTACK_TARGET.get()))
						)
						.requireAndWipeMemoriesOnUse(AoABrainMemories.SECOND_ATTACK_TARGET.get()),
				AoABrainActivities.FIGHT_3.get(),
				new BrainActivityGroup<AoABoss>(AoABrainActivities.FIGHT_3.get())
						.priority(12)
						.behaviours(
								new InvalidateMemory<>(AoABrainMemories.THIRD_ATTACK_TARGET.get())
										.invalidateIf((entity, memory) -> {
											if (!memory.isAlive() || (memory instanceof Player pl && pl.getAbilities().invulnerable) || memory.distanceToSqr(entity) > Math.pow((int)getAttributeValue(Attributes.FOLLOW_RANGE), 2)) {
												THIRD_HEAD_TARGET.set(entity, -1);

												return true;
											}

											return false;
										}),
								new CustomDelayedBehaviour<>(getSwingWarmupTicks(FIREBALL_STATE))
										.whenActivating(entity -> {
											LivingEntity target = BrainUtils.getMemory(entity, AoABrainMemories.THIRD_ATTACK_TARGET.get());

											if (target != null)
												doFireball(PositionAndMotionUtil.moveRelativeToFacing(entity.getEyePosition(), entity.getYRot(), 1.25f, -0.5f, -0.25f), target);
										})
										.cooldownFor(entity -> rand().randomNumberBetween(getSwingDurationTicks(FIREBALL_STATE) * (ATTACK_STATE.is(entity, FIRE_BOMB_STATE) ? 6 : 3), getSwingDurationTicks(FIREBALL_STATE) * (ATTACK_STATE.is(entity, FIRE_BOMB_STATE) ? 10 : 5)))
										.whenStarting(entity -> triggerAnim("Right Head", "fireball"))
										.startCondition(entity -> BrainUtils.hasMemory(entity, AoABrainMemories.THIRD_ATTACK_TARGET.get()))
						)
						.requireAndWipeMemoriesOnUse(AoABrainMemories.THIRD_ATTACK_TARGET.get()));
	}

	@Override
	public Set<Activity> getAlwaysRunningActivities() {
		return ImmutableSet.of(Activity.CORE, AoABrainActivities.FIGHT_2.get(), AoABrainActivities.FIGHT_3.get());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);

		if (getHealth() / getMaxHealth() <= 0.5f)
			this.doneFireBomb = true;
	}

	private void doFireball(@Nullable Vec3 startPos, LivingEntity target) {
		BaseMobProjectile projectile = new FireballEntity(this.level(), this, BaseMobProjectile.Type.PHYSICAL);

		if (startPos != null)
			projectile.setPos(startPos);

		projectile.setYRot(getYHeadRot());
		PositionAndMotionUtil.moveRelativeToFacing(projectile, 0, 1, 0);
		PositionAndMotionUtil.moveTowards(projectile, target.getEyePosition(), 1.6d, 4 - level().getDifficulty().getId());
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

			if (RandomUtil.oneInNChance(4) && target.getRemainingFireTicks() < 300)
				target.setSecondsOnFire((int)Math.ceil(Math.max(0, target.getRemainingFireTicks()) / 20f) + 1);

			if (RandomUtil.oneInNChance(4) && target instanceof LivingEntity livingEntity)
				livingEntity.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 0));
		}
		else {
			float dmg = (float)getAttributeValue(AoAAttributes.RANGED_ATTACK_DAMAGE.get());

			if (ATTACK_STATE.is(this, FIRE_BOMB_STATE))
				dmg *= 1.5f;

			if (DamageUtil.doProjectileAttack(this, projectile, target, dmg)) {
				target.setSecondsOnFire((int)Math.ceil(Math.max(0, target.getRemainingFireTicks()) / 20f) + 3);

				if (target instanceof LivingEntity livingEntity)
					livingEntity.addEffect(new MobEffectInstance(AoAMobEffects.NETHENGEIC_CURSE.get(), 200, this.level().getDifficulty().getId() - 1));
			}
		}
	}

	@Override
	public void doRangedAttackBlock(@Nullable BaseMobProjectile projectile, BlockState blockHit, BlockPos pos, Direction sideHit) {
		if (projectile == null)
			return;

		if (level() instanceof ServerLevel serverLevel)
			new StandardExplosion(AoAExplosions.NETHENGEIC_WITHER_FIREBALL, serverLevel, projectile, this).explode();
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (hasAura()) {
			if (DamageUtil.isMeleeDamage(source)) {
				if (source.getDirectEntity() instanceof LivingEntity attacker) {
					DamageUtil.safelyDealDamage(DamageUtil.entityDamage(AoADamageTypes.MOB_FIRE_RECOIL, this), attacker, 5);
					attacker.setSecondsOnFire((int)Math.ceil(Math.max(0, attacker.getRemainingFireTicks()) / 20f) + 2);
					attacker.addEffect(new MobEffectInstance(AoAMobEffects.NETHENGEIC_CURSE.get(), 200, 2));
				}
			}
			else if (DamageUtil.isEnergyDamage(source)) {
				heal(amount);

				return false;
			}
		}

		return super.hurt(source, amount);
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
		return FLAME_AURA.is(this, true);
	}

	public void toggleAura() {
		FLAME_AURA.set(this, !FLAME_AURA.get(this));
	}

	@Override
	public void aiStep() {
		super.aiStep();

		if (level().isClientSide()) {
			if (hasAura()) {
				for (int i = 0; i < 3; i++) {
					double cos = Math.cos(getX() * RandomUtil.randomValueBetween(-1, 1));
					double sin = Math.sin(getZ() * RandomUtil.randomValueBetween(-1, 1));
					double startX = cos * getBbWidth() + getX();
					double startZ = sin * getBbWidth() + getZ();
					double startY = getRandomY();

					ParticleBuilder.forPosition(new CustomisableParticleType.Data(AoAParticleTypes.FIRE_AURA.get(), 0.25f, 5, 1, 1, 1, 0.75f, getId()), startX, startY, startZ)
							.velocity(RandomUtil.fiftyFifty() ? -1 : 1, RandomUtil.fiftyFifty() ? -1 : 1, RandomUtil.fiftyFifty() ? -1 : 1).spawnParticles(level());
				}
			}

			ParticleBuilder.forPosition(ParticleTypes.FLAME, getX() + RandomUtil.randomValueBetween(-0.2f, 0.2f), getEyeY() - 1 + RandomUtil.randomValueBetween(-0.2f, 0.2f), getZ() + RandomUtil.randomValueBetween(-0.2f, 0.2f)).spawnParticles(level());

			if (getRandom().nextInt(10) == 0) {
				ParticleBuilder.forPosition(ParticleTypes.SMOKE, getX(), getEyeY() - 1, getZ()).spawnParticles(level());

				if (getDeltaMovement().horizontalDistanceSqr() == 0)
					ParticleBuilder.forPosition(ParticleTypes.DRIPPING_LAVA, getX(), getEyeY() - 1, getZ()).spawnParticles(level());
			}
		}
		else if (hasAura() && BrainUtils.getTargetOfEntity(this) == null) {
			toggleAura();
		}
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(new AnimationController<>(this, "living", 0, state -> {
			if (ATTACK_STATE.is(this, FIRE_BOMB_STATE)) {
				state.getController().setAnimationSpeed(1);

				return state.setAndContinue(CORE_SPIN_ANIM);
			}

			state.getController().setAnimationSpeed(1 + (1 - (getHealth() / getMaxHealth())) * 5);
			state.getController().setAnimation(DefaultAnimations.LIVING);

			return PlayState.CONTINUE;
		}));
		controllers.add(new AnimationController<>(this, "Walk/Idle", 0, state -> {
			if (ATTACK_STATE.is(this, FIRE_BOMB_STATE))
				return state.setAndContinue(FIRE_BOMB_ANIM);

			return state.setAndContinue(state.isMoving() ? DefaultAnimations.WALK : DefaultAnimations.IDLE);
		}));
		controllers.add(new AnimationController<>(this, "Middle Head", 0, state -> {
			if (ATTACK_STATE.is(this, FLAMETHROWER_STATE))
				state.setAndContinue(FLAMETHROWER_ANIM);

			return PlayState.STOP;
		}).triggerableAnim("fireball", RawAnimation.begin().thenPlay("attack.fireball.center"))
				.triggerableAnim("fire_aura", RawAnimation.begin().thenPlay("misc.fire_aura")));
		controllers.add(new AnimationController<>(this, "Left Head", 0, state -> PlayState.STOP)
				.triggerableAnim("fireball", RawAnimation.begin().thenPlay("attack.fireball.left")));
		controllers.add(new AnimationController<>(this, "Right Head", 0, state -> PlayState.STOP)
				.triggerableAnim("fireball", RawAnimation.begin().thenPlay("attack.fireball.right")));
	}

	private static class FireBombAttack extends ConditionlessHeldAttack<NethengeicWitherEntity> {
		private final List<FireballEntity> fireballs = new ObjectArrayList<>();

		public FireBombAttack() {
			runFor(entity -> 280);
			onTick(entity -> {
				if (this.runningTime >= 20 && !entity.hasAura())
					entity.toggleAura();

				entity.setDeltaMovement(this.target.position().subtract(entity.position()).normalize().multiply(0, 0.5f, 0).add(0, Math.cos(entity.tickCount) * 0.05f, 0));

				if (this.runningTime % 20 == 0) {
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

					for (double angle = RandomUtil.randomValueBetween(-45, 45) * Mth.DEG_TO_RAD; angle <= 360 * Mth.DEG_TO_RAD; angle += 45 * Mth.DEG_TO_RAD) {
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
				if (entity.doneFireBomb || entity.getHealth() > entity.getMaxHealth() / 2f || entity.hasAura())
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
				ATTACK_STATE.set(entity, FIRE_BOMB_STATE);
			});
			whenStopping(entity -> {
				entity.doneFireBomb = true;

				ATTACK_STATE.set(entity, FIREBALL_STATE);
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

	private static class FlamethrowerAttack extends ConditionlessHeldAttack<NethengeicWitherEntity> {
		public FlamethrowerAttack() {
			runFor(entity -> RandomUtil.randomNumberBetween(160, 240));
			requiresTarget();
			onTick(entity -> {
				Vec3 position = PositionAndMotionUtil.moveRelativeToFacing(entity.getEyePosition(), entity.getYRot(), 0, 1, 0);
				double baseX = position.x;
				double baseY = position.y;
				double baseZ = position.z;
				TELParticlePacket packet = new TELParticlePacket(ParticleBuilder.forPosition(ParticleTypes.LARGE_SMOKE, baseX, baseY, baseZ));

				for (int i = 0; i < 5; i++) {
					Vec3 velocity = this.target.getEyePosition().subtract(baseX + RandomUtil.randomScaledGaussianValue(0.5f), baseY + RandomUtil.randomScaledGaussianValue(0.5f), baseZ + RandomUtil.randomScaledGaussianValue(0.5f)).normalize().scale(0.75f);

					packet.particle(ParticleBuilder.forPosition(new CustomisableParticleType.Data(AoAParticleTypes.BURNING_FLAME.get(), 0.35f, 5, 0, 0, 0, 0, entity.getId()), baseX, baseY, baseZ).velocity(velocity.x, velocity.y, velocity.z));
					packet.particle(ParticleBuilder.forPosition(RandomUtil.fiftyFifty() ? ParticleTypes.SMALL_FLAME : ParticleTypes.SQUID_INK, baseX, baseY, baseZ).velocity(velocity.x, velocity.y, velocity.z));
				}

				packet.sendToAllNearbyPlayers((ServerLevel)entity.level(), EntityUtil.getEntityCenter(entity), 64);

				if (getRunningTime() % 9 == 0 || getRunningTime() % 19 == 0)
					entity.playSound(AoASounds.FLAMETHROWER.get(), 2, 1);

				return true;
			});
			startCondition(entity -> {
				if (!entity.hasAura())
					return false;

				LivingEntity target = BrainUtils.getMemory(entity, MemoryModuleType.ATTACK_TARGET);

				return target != null && target.isAlive();
			});
			stopIf(entity -> {
				LivingEntity target = BrainUtils.getMemory(entity, MemoryModuleType.ATTACK_TARGET);

				return target == null || !target.isAlive();
			});
			whenStarting(entity -> ATTACK_STATE.set(entity, FLAMETHROWER_STATE));
			whenStopping(entity -> {
				ATTACK_STATE.set(entity, FIREBALL_STATE);

				if (entity.hasAura())
					entity.toggleAura();

				BrainUtils.setForgettableMemory(entity, SBLMemoryTypes.SPECIAL_ATTACK_COOLDOWN.get(), true, 30);
			});
		}
	}
}