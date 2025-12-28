package net.tslat.aoa3.content.entity.boss.smash;

import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.fluids.FluidType;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.entity.AoAEntityStats;
import net.tslat.aoa3.content.entity.boss.AoABoss;
import net.tslat.aoa3.content.entity.brain.sensor.AggroBasedNearbyLivingEntitySensor;
import net.tslat.aoa3.content.entity.brain.sensor.AggroBasedNearbyPlayersSensor;
import net.tslat.aoa3.content.entity.brain.task.custom.ChargeAttack;
import net.tslat.aoa3.content.entity.brain.task.custom.GroundSlamAttack;
import net.tslat.aoa3.util.AttributeUtil;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.behaviour.FirstApplicableBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.OneRandomBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.custom.attack.AnimatableMeleeAttack;
import net.tslat.smartbrainlib.api.core.behaviour.custom.look.LookAtTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.CustomBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.Idle;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.ReactToUnreachableTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.FleeTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.FloatToSurfaceOfFluid;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.WalkOrRunToWalkTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetRandomWalkTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetWalkTargetToAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.InvalidateAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.TargetOrRetaliate;
import net.tslat.smartbrainlib.api.core.sensor.ExtendedSensor;
import net.tslat.smartbrainlib.api.core.sensor.custom.UnreachableTargetSensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.HurtBySensor;
import net.tslat.smartbrainlib.registry.SBLMemoryTypes;
import net.tslat.smartbrainlib.util.BrainUtils;
import net.tslat.tme.api.object.builder.EffectBuilder;
import net.tslat.tme.api.sound.SoundBuilder;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.constant.DefaultAnimations;

import java.util.List;

public class SmashEntity extends AoABoss {
	protected static final EntityDataAccessor<Boolean> ENRAGED = SynchedEntityData.defineId(SmashEntity.class, EntityDataSerializers.BOOLEAN);
	private static final AttributeModifier ENRAGED_DAMAGE_MOD = new AttributeModifier(AdventOfAscension.id("enraged"), 0.95f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
	private static final AttributeModifier ENRAGED_ARMOUR_MOD = new AttributeModifier(AdventOfAscension.id("enraged"), 1.1f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
	private static final AttributeModifier ENRAGED_TOUGHNESS_MOD = new AttributeModifier(AdventOfAscension.id("enraged"), 1.25f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);

	private static final int AXE_SWING_STATE = 0;
	private static final int AXE_SLAM_STATE = 1;
	private static final int CHARGE_STATE = 2;

	public SmashEntity(EntityType<? extends AoABoss> entityType, Level level) {
		super(entityType, level);
	}

	@Override
	protected void addSwingData(SwingData swings) {
		swings.put(AXE_SWING_STATE, new SwingData.Swing(20, 13, RawAnimation.begin().thenPlay("attack.axe_swing")));
		swings.put(AXE_SLAM_STATE, new SwingData.Swing(20, 17, RawAnimation.begin().thenPlay("attack.axe_slam")));
		swings.put(CHARGE_STATE, new SwingData.Swing(0, 0, RawAnimation.begin().thenPlay("attack.axe_slam")));
	}

	@Nullable
	@Override
	public SoundEvent getMusic() {
		return AoASounds.SMASH_MUSIC.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_SMASH_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_SMASH_HURT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getStepSound(BlockPos pos, BlockState blockState) {
		return AoASounds.ENTITY_GENERIC_HEAVY_STEP.get();
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(ENRAGED, false);
	}

	@Override
	protected void customServerAiStep() {
		super.customServerAiStep();

		if (EntityUtil.getHealthPercent(this) < 0.25 && !isEnraged())
			enrage();
	}

	protected void enrage() {
		if (!isEnraged()) {
			getEntityData().set(ENRAGED, true);
			AttributeUtil.applyPermanentModifier(this, Attributes.ATTACK_DAMAGE, ENRAGED_DAMAGE_MOD);
			AttributeUtil.applyPermanentModifier(this, Attributes.ARMOR, ENRAGED_ARMOUR_MOD);
			AttributeUtil.applyPermanentModifier(this, Attributes.ARMOR_TOUGHNESS, ENRAGED_TOUGHNESS_MOD);
			triggerAnim("arms_controller", "enrage");
			SoundBuilder.following(AoASounds.ENTITY_SMASH_ENRAGE, this).play();
			BrainUtils.setForgettableMemory(this, MemoryModuleType.ATTACK_COOLING_DOWN, true, 100);
		}
	}

	public boolean isEnraged() {
		return getEntityData().get(ENRAGED);
	}

	@Override
	public boolean canDisableShield() {
		return !isAttackState(AXE_SLAM_STATE);
	}

	@Override
	public double getFluidMotionScale(FluidType type) {
		return Math.min(1, super.getFluidMotionScale(type) * 2);
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);

		compound.putBoolean("Enraged", isEnraged());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);

		if (compound.contains("Enraged"))
			getEntityData().set(ENRAGED, compound.getBoolean("Enraged"));
	}

	@Override
	public List<ExtendedSensor<? extends AoABoss>> getSensors() {
		return ObjectArrayList.of(
				new AggroBasedNearbyPlayersSensor<>(),
				new AggroBasedNearbyLivingEntitySensor<AoABoss>().setPredicate((target, entity) -> target instanceof OwnableEntity tamedEntity && tamedEntity.getOwnerUUID() != null).setScanRate(entity -> 40),
				new HurtBySensor<>(),
				new UnreachableTargetSensor<AoABoss>().afterScanning(entity -> {
					if (!BrainUtils.hasMemory(entity, SBLMemoryTypes.TARGET_UNREACHABLE.get()) && !isAttackState(CHARGE_STATE))
						setAttackState(AXE_SWING_STATE);
				})
		);
	}

	@Override
	public BrainActivityGroup<AoABoss> getCoreTasks() {
		return BrainActivityGroup.coreTasks(
				new LookAtTarget<>()
						.startCondition(entity -> !isAttackState(CHARGE_STATE)),
				new WalkOrRunToWalkTarget<>()
						.startCondition(entity -> !isAttackState(CHARGE_STATE)),
				new FloatToSurfaceOfFluid<>());
	}

	@Override
	public BrainActivityGroup<AoABoss> getIdleTasks() {
		return BrainActivityGroup.idleTasks(
				new FirstApplicableBehaviour<AoABoss>(
						new TargetOrRetaliate<>()
								.useMemory(MemoryModuleType.NEAREST_VISIBLE_ATTACKABLE_PLAYER)
								.attackablePredicate(target -> DamageUtil.isAttackable(target) && !isAlliedTo(target))
								.startCondition(entity -> !isAttackState(CHARGE_STATE)),
						new Idle<>().runFor(entity -> entity.getRandom().nextInt(15, 45))),
				new OneRandomBehaviour<>(
						new SetRandomWalkTarget<>().speedModifier(0.9f),
						new Idle<>().runFor(entity -> entity.getRandom().nextInt(30, 60))));
	}

	@Override
	public BrainActivityGroup<AoABoss> getFightTasks() {
		return BrainActivityGroup.fightTasks(
				new InvalidateAttackTarget<>(),
				new SetWalkTargetToAttackTarget<>()
						.speedMod((entity, target) -> 1.1f)
						.startCondition(entity -> !isAttackState(CHARGE_STATE)),
				new FirstApplicableBehaviour<>(
						new OneRandomBehaviour<>(
								Pair.of(
										new GroundSlamAttack<>(getSwingWarmupTicks(AXE_SLAM_STATE))
												.slamAtTarget()
												.radius(4)
												.requiresTarget()
												.whenActivating(entity -> this.level().playSound(null, getX(), getY(), getZ(), AoASounds.HEAVY_WOODEN_IMPACT.get(), this.getSoundSource(), 1, 1))
												.cooldownFor(entity -> (int)(getSwingDurationTicks(AXE_SLAM_STATE) * entity.getRandom().nextFloat() * 2))
												.startCondition(mob -> isAttackState(AXE_SLAM_STATE))
												.whenStopping(entity -> setAttackState(AXE_SWING_STATE)),
										20),
								Pair.of(
										new FleeTarget<>().fleeDistance(40).startCondition(entity -> BrainUtils.hasMemory(entity, SBLMemoryTypes.TARGET_UNREACHABLE.get()) && isEnraged()),
										1)),
						new ReactToUnreachableTarget<>()
								.timeBeforeReacting(entity -> 60)
								.reaction((entity, isTowering) -> {
									enrage();
									setAttackState(AXE_SLAM_STATE);
								}),
						new AnimatableMeleeAttack<>(getSwingWarmupTicks(AXE_SWING_STATE))
								.attackInterval(entity -> getSwingDurationTicks(AXE_SWING_STATE) + entity.getRandom().nextInt(15, 25))
								.startCondition(mob -> isAttackState(AXE_SWING_STATE)),
						new OneRandomBehaviour<>(
								new CustomBehaviour<>(entity -> {
									BrainUtils.setForgettableMemory(entity, MemoryModuleType.ATTACK_COOLING_DOWN, true, 90);
									triggerAnim("arms_controller", "belly_drum");
									SoundBuilder.following(AoASounds.ENTITY_SMASH_BELLY_DRUM, this).play();
									EntityUtil.applyPotions(entity, entity,
											new EffectBuilder(MobEffects.DAMAGE_RESISTANCE, 100).level(2),
											new EffectBuilder(MobEffects.MOVEMENT_SPEED, 100),
											new EffectBuilder(MobEffects.DAMAGE_BOOST, 100).level(2));
								})
										.startCondition(entity -> BrainUtils.hasMemory(entity, MemoryModuleType.ATTACK_TARGET) && !BrainUtils.hasMemory(entity, SBLMemoryTypes.SPECIAL_ATTACK_COOLDOWN.get()))
										.cooldownFor(entity -> entity.getRandom().nextInt(200, 400))
										.whenStopping(entity -> BrainUtils.setForgettableMemory(entity, SBLMemoryTypes.SPECIAL_ATTACK_COOLDOWN.get(), true, 150)),
								new ChargeAttack<>(38)
										.speedModifier(1.5f)
										.whenStarting(entity -> {
											triggerAnim("arms_controller", "charge_up");
											setAttackState(CHARGE_STATE);
										})
										.whenStopping(entity -> {
											setAttackState(AXE_SWING_STATE);
											BrainUtils.setForgettableMemory(entity, SBLMemoryTypes.SPECIAL_ATTACK_COOLDOWN.get(), true, 150);
										})
										.cooldownFor(entity -> entity.getRandom().nextInt(200, 400))
						)));
	}

	public static AoAEntityStats.AttributeBuilder entityStats(EntityType<SmashEntity> entityType) {
		return AoAEntityStats.AttributeBuilder.createMonster(entityType)
				.health(275)
				.moveSpeed(0.31)
				.meleeStrength(15)
				.knockbackResist(0.9)
				.followRange(128)
				.aggroRange(128)
				.armour(10, 10)
				.knockback(1f)
				.stepHeight(1.25f);
	}

	private static final RawAnimation WALK_BOTTOM_HALF = RawAnimation.begin().thenPlay("move.walk.bottom_half");
	private static final RawAnimation WALK_TOP_HALF = RawAnimation.begin().thenPlay("move.walk.top_half");
	private static final RawAnimation RUN_BOTTOM_HALF = RawAnimation.begin().thenPlay("move.run.bottom_half");
	private static final RawAnimation RUN_TOP_HALF = RawAnimation.begin().thenPlay("move.run.top_half");
	private static final RawAnimation ENRAGE = RawAnimation.begin().thenPlay("misc.enrage");
	private static final RawAnimation ENRAGED_IDLE = RawAnimation.begin().thenPlay("misc.idle.enraged");
	private static final RawAnimation CHARGE_UP = RawAnimation.begin().thenPlay("misc.charge_up");
	private static final RawAnimation CHARGE = RawAnimation.begin().thenPlay("move.charge");
	private static final RawAnimation BELLY_DRUM = RawAnimation.begin().thenPlay("misc.belly_drum");

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(new AnimationController<>(this, "legs_controller", 0, state -> {
			if (state.isMoving())
				return state.setAndContinue(isSprinting() ? RUN_BOTTOM_HALF : WALK_BOTTOM_HALF);

			return PlayState.STOP;
		}));
		controllers.add(new AnimationController<>(this, "arms_controller", 3, state -> {
			if (isAttackState(CHARGE_STATE))
				return state.setAndContinue(CHARGE);

			if (this.swinging)
				return state.setAndContinue(getSwingAnimation(getAttackState()));

			if (state.isMoving()) {
				return state.setAndContinue(isSprinting() ? RUN_TOP_HALF : WALK_TOP_HALF);
			}
			else {
				return state.setAndContinue(isEnraged() ? ENRAGED_IDLE : DefaultAnimations.IDLE);
			}
		}).triggerableAnim("enrage", ENRAGE).triggerableAnim("belly_drum", BELLY_DRUM).triggerableAnim("charge_up", CHARGE_UP));
	}
}