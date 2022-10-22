package net.tslat.aoa3.content.entity.boss.smash;

import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.behavior.StopAttackingIfTargetInvalid;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fluids.FluidType;
import net.tslat.aoa3.client.render.AoAAnimations;
import net.tslat.aoa3.common.misc.AoAAnimationController;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.boss.AoABoss;
import net.tslat.aoa3.content.entity.brain.task.custom.ChargeAttack;
import net.tslat.aoa3.content.entity.brain.task.custom.GroundSlamAttack;
import net.tslat.aoa3.library.builder.SoundBuilder;
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
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.WalkOrRunToWalkTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetRandomWalkTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetWalkTargetToAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.TargetOrRetaliate;
import net.tslat.smartbrainlib.api.core.sensor.ExtendedSensor;
import net.tslat.smartbrainlib.api.core.sensor.custom.UnreachableTargetSensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.HurtBySensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.NearbyPlayersSensor;
import net.tslat.smartbrainlib.api.util.BrainUtils;
import net.tslat.smartbrainlib.registry.SBLMemoryTypes;
import net.tslat.effectslib.api.util.EffectBuilder;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.manager.AnimationData;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class EliteSmashEntity extends AoABoss {
	protected static final EntityDataAccessor<Boolean> ENRAGED = SynchedEntityData.defineId(EliteSmashEntity.class, EntityDataSerializers.BOOLEAN);
	private static final AttributeModifier ENRAGED_DAMAGE_MOD = new AttributeModifier(UUID.fromString("104c09f0-28cc-43dd-81c0-10de6b3083bd"), "EnragedDamageModifier", 0.95f, AttributeModifier.Operation.MULTIPLY_TOTAL);
	private static final AttributeModifier ENRAGED_ARMOUR_MOD = new AttributeModifier(UUID.fromString("bbbdf964-689b-4bcf-9a23-122a7bba682e"), "EnragedArmourModifier", 0.9f, AttributeModifier.Operation.MULTIPLY_TOTAL);
	private static final AttributeModifier ENRAGED_TOUGHNESS_MOD = new AttributeModifier(UUID.fromString("ac843c67-4731-4e77-85e9-6992bd92ae4b"), "EnragedToughnessModifier", 1.1f, AttributeModifier.Operation.MULTIPLY_TOTAL);

	private static final int AXE_SWING_STATE = 0;
	private static final int AXE_SLAM_STATE = 1;
	private static final int CHARGE_STATE = 2;

	public EliteSmashEntity(EntityType<? extends AoABoss> entityType, Level level) {
		super(entityType, level);

		this.maxUpStep = 1.25f;
	}

	@Override
	protected void addSwingData(Int2ObjectOpenHashMap<SwingData> states) {
		states.put(AXE_SWING_STATE, new SwingData(20, 13, new AnimationBuilder().addAnimation("attack.axe_swing", false)));
		states.put(AXE_SLAM_STATE, new SwingData(20, 17, new AnimationBuilder().addAnimation("attack.axe_slam", false)));
		states.put(CHARGE_STATE, new SwingData(0, 0, new AnimationBuilder().addAnimation("attack.axe_slam", false)));
	}

	@Nullable
	@Override
	protected SoundEvent getMusic() {
		return AoASounds.SMASH_MUSIC.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.LARGE_CREATURE_GROAN.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.LARGE_CREATURE_GRUNT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getStepSound(BlockPos pos, BlockState blockState) {
		return AoASounds.ENTITY_GENERIC_HEAVY_STEP.get();
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
		return 3.0625f;
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		getEntityData().define(ENRAGED, false);
	}

	@Override
	protected void customServerAiStep() {
		super.customServerAiStep();

		if (EntityUtil.getCurrentHealthPercent(this) < 0.3 && !isEnraged())
			enrage();
	}

	protected void enrage() {
		if (!isEnraged()) {
			getEntityData().set(ENRAGED, true);
			EntityUtil.applyAttributeModifierSafely(this, Attributes.ATTACK_DAMAGE, ENRAGED_DAMAGE_MOD, true);
			EntityUtil.applyAttributeModifierSafely(this, Attributes.ARMOR, ENRAGED_ARMOUR_MOD, true);
			EntityUtil.applyAttributeModifierSafely(this, Attributes.ARMOR_TOUGHNESS, ENRAGED_TOUGHNESS_MOD, true);
			triggerAnim(this, "enrage");
			new SoundBuilder(AoASounds.ENTITY_SMASH_ENRAGE).followEntity(this).category(SoundSource.HOSTILE).execute();
			BrainUtils.setForgettableMemory(this, MemoryModuleType.ATTACK_COOLING_DOWN, true, 100);
			BrainUtils.setForgettableMemory(this, SBLMemoryTypes.SPECIAL_ATTACK_COOLDOWN.get(), true, 100);
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
		return Math.min(1, super.getFluidMotionScale(type) * 4);
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
	public List<ExtendedSensor<AoABoss>> getSensors() {
		return ObjectArrayList.of(
				new NearbyPlayersSensor<>(),
				new HurtBySensor<>(),
				new UnreachableTargetSensor<AoABoss>()
		);
	}

	@Override
	public BrainActivityGroup<AoABoss> getCoreTasks() {
		return BrainActivityGroup.coreTasks(
				new LookAtTarget<>()
						.startCondition(entity -> !isAttackState(CHARGE_STATE)),
				new WalkOrRunToWalkTarget<>()
						.startCondition(entity -> !isAttackState(CHARGE_STATE)));
	}

	@Override
	public BrainActivityGroup<AoABoss> getIdleTasks() {
		return BrainActivityGroup.idleTasks(
				new FirstApplicableBehaviour<AoABoss>(
						new TargetOrRetaliate<>()
								.useMemory(MemoryModuleType.NEAREST_VISIBLE_ATTACKABLE_PLAYER)
								.startCondition(entity -> !isAttackState(CHARGE_STATE)),
						new Idle<>().runFor(entity -> entity.getRandom().nextInt(15, 45))),
				new OneRandomBehaviour<>(
						new SetRandomWalkTarget<>().speedModifier(0.9f),
						new Idle<>().runFor(entity -> entity.getRandom().nextInt(30, 60))));
	}

	@Override
	public BrainActivityGroup<AoABoss> getFightTasks() {
		return BrainActivityGroup.fightTasks(
				new StopAttackingIfTargetInvalid<>(target -> !target.isAlive() || target instanceof Player && ((Player)target).isCreative()),
				new SetWalkTargetToAttackTarget<>()
						.speedMod(1.1f)
						.startCondition(entity -> !isAttackState(CHARGE_STATE)),
				new FirstApplicableBehaviour<>(
						new OneRandomBehaviour<>(
								Pair.of(
										new GroundSlamAttack<>(getSwingWarmupTicks(AXE_SLAM_STATE))
												.requiresTarget()
												.whenActivating(entity -> this.level.playSound(null, getX(), getY(), getZ(), AoASounds.HEAVY_WOODEN_IMPACT.get(), this.getSoundSource(), 1, 1))
												.cooldownFor(entity -> 5 + (int)(getSwingDurationTicks(AXE_SLAM_STATE) * entity.getRandom().nextFloat() * 1.5f))
												.startCondition(mob -> isAttackState(AXE_SLAM_STATE))
												.whenStopping(entity -> setAttackState(AXE_SWING_STATE)),
										20),
								Pair.of(
										new FleeTarget<>().fleeDistance(40).startCondition(entity -> BrainUtils.hasMemory(entity, SBLMemoryTypes.TARGET_UNREACHABLE.get()) && isEnraged()),
										1)),
						new ReactToUnreachableTarget<>()
								.timeBeforeReacting(entity -> 40)
								.reaction((entity, isTowering) -> {
									enrage();
									setAttackState(AXE_SLAM_STATE);
								}),
						new OneRandomBehaviour<>(
								Pair.of(
										new GroundSlamAttack<>(getSwingWarmupTicks(AXE_SLAM_STATE))
												.radius(5)
												.requiresTarget()
												.whenActivating(entity -> this.level.playSound(null, getX(), getY(), getZ(), AoASounds.HEAVY_WOODEN_IMPACT.get(), this.getSoundSource(), 1, 0.5f))
												.whenStarting(entity -> setAttackState(AXE_SLAM_STATE))
												.startCondition(entity -> {
													LivingEntity target = BrainUtils.getTargetOfEntity(entity);

													return target != null && target.distanceToSqr(entity) < 25;
												})
												.whenStopping(entity -> {
													setAttackState(AXE_SWING_STATE);
													BrainUtils.setForgettableMemory(entity, MemoryModuleType.ATTACK_COOLING_DOWN, true, 5 + (int)(getSwingDurationTicks(AXE_SLAM_STATE) * entity.getRandom().nextFloat() * 5f));
												}),
										1),
								Pair.of(
										new AnimatableMeleeAttack<>(getSwingWarmupTicks(AXE_SWING_STATE))
												.attackInterval(entity -> getSwingDurationTicks(AXE_SWING_STATE) + entity.getRandom().nextInt(5, 15))
												.startCondition(mob -> isAttackState(AXE_SWING_STATE)),
										5)
						).startCondition(entity -> !BrainUtils.hasMemory(entity, MemoryModuleType.ATTACK_COOLING_DOWN)),
						new OneRandomBehaviour<>(
								new CustomBehaviour<>(entity -> {
									BrainUtils.setForgettableMemory(entity, MemoryModuleType.ATTACK_COOLING_DOWN, true, 90);
									triggerAnim(this, "belly_drum");
									new SoundBuilder(AoASounds.ENTITY_SMASH_BELLY_DRUM).isMonster().followEntity(this).execute();
									EntityUtil.applyPotions(entity,
											new EffectBuilder(MobEffects.DAMAGE_RESISTANCE, 100).level(2),
											new EffectBuilder(MobEffects.MOVEMENT_SPEED, 100),
											new EffectBuilder(MobEffects.DAMAGE_BOOST, 100).level(5));
								})
										.startCondition(entity -> BrainUtils.hasMemory(entity, MemoryModuleType.ATTACK_TARGET) && !BrainUtils.hasMemory(entity, SBLMemoryTypes.SPECIAL_ATTACK_COOLDOWN.get()))
										.cooldownFor(entity -> 150)
										.whenStopping(entity -> BrainUtils.setForgettableMemory(entity, SBLMemoryTypes.SPECIAL_ATTACK_COOLDOWN.get(), true, 150)),
								new ChargeAttack<>(38)
										.speedModifier(1.6f)
										.targetWhenCharging()
										.whenStarting(entity -> {
											triggerAnim(this, "charge_up");
											setAttackState(CHARGE_STATE);
										})
										.whenStopping(entity -> {
											setAttackState(AXE_SWING_STATE);
											BrainUtils.setForgettableMemory(entity, SBLMemoryTypes.SPECIAL_ATTACK_COOLDOWN.get(), true, 150);
										})
										.cooldownFor(entity -> 150)
						)));
	}

	private static final AnimationBuilder WALK_BOTTOM_HALF = new AnimationBuilder().addAnimation("move.walk.bottom_half", true);
	private static final AnimationBuilder WALK_TOP_HALF = new AnimationBuilder().addAnimation("move.walk.top_half", true);
	private static final AnimationBuilder RUN_BOTTOM_HALF = new AnimationBuilder().addAnimation("move.run.bottom_half", true);
	private static final AnimationBuilder RUN_TOP_HALF = new AnimationBuilder().addAnimation("move.run.top_half", true);
	private static final AnimationBuilder ENRAGE = new AnimationBuilder().addAnimation("misc.enrage", false);
	private static final AnimationBuilder ENRAGED_IDLE = new AnimationBuilder().addAnimation("misc.idle.enraged", true);
	private static final AnimationBuilder CHARGE_UP = new AnimationBuilder().addAnimation("misc.charge_up", false);
	private static final AnimationBuilder CHARGE = new AnimationBuilder().addAnimation("move.charge", true);
	private static final AnimationBuilder BELLY_DRUM = new AnimationBuilder().addAnimation("misc.belly_drum", false);

	@Override
	public void registerControllers(AnimationData animationData) {
		animationData.addAnimationController(new AnimationController<>(this, "legs_controller", 0, event -> {
			if (event.isMoving()) {
				event.getController().setAnimation(isSprinting() ? RUN_BOTTOM_HALF : WALK_BOTTOM_HALF);

				return PlayState.CONTINUE;
			}

			return PlayState.STOP;
		}));
		animationData.addAnimationController(new AoAAnimationController<>(this, "arms_controller", 3, event -> {
			AnimationController<?> controller = event.getController();

			if (isAttackState(CHARGE_STATE)) {
				controller.setAnimation(CHARGE);

				return PlayState.CONTINUE;
			}

			if (this.swinging) {
				controller.setAnimation(getSwingAnim(getAttackState()));

				return PlayState.CONTINUE;
			}
			else if (controller.getCurrentAnimation() != null && !controller.getCurrentAnimation().animationName.startsWith("move")) {
				controller.markNeedsReload();
			}

			if (event.isMoving()) {
				controller.setAnimation(isSprinting() ? RUN_TOP_HALF : WALK_TOP_HALF);
			}
			else {
				controller.setAnimation(isEnraged() ? ENRAGED_IDLE : AoAAnimations.IDLE);
			}

			return PlayState.CONTINUE;
		}).triggerable("enrage", ENRAGE).triggerable("belly_drum", BELLY_DRUM).triggerable("charge_up", CHARGE_UP));
	}
}