package net.tslat.aoa3.content.entity.boss.king_bambambam;

import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.monster.hoglin.Hoglin;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.damagesource.DamageContainer;
import net.neoforged.neoforge.event.EventHooks;
import net.tslat.aoa3.common.registration.AoAAttributes;
import net.tslat.aoa3.common.registration.AoAExplosions;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.entity.AoAEntityStats;
import net.tslat.aoa3.common.registration.entity.AoAMonsters;
import net.tslat.aoa3.content.entity.base.AoARangedAttacker;
import net.tslat.aoa3.content.entity.boss.AoABoss;
import net.tslat.aoa3.content.entity.brain.sensor.AggroBasedNearbyPlayersSensor;
import net.tslat.aoa3.content.entity.monster.nether.EmbrakeEntity;
import net.tslat.aoa3.content.entity.monster.nether.LittleBamEntity;
import net.tslat.aoa3.content.entity.projectile.mob.BaseMobProjectile;
import net.tslat.aoa3.content.entity.projectile.mob.StickyFireballEntity;
import net.tslat.aoa3.library.builder.AoAExplosionBuilder;
import net.tslat.aoa3.library.object.interfaces.TriFunction;
import net.tslat.aoa3.util.*;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.behaviour.DelayedBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.FirstApplicableBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.HeldBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.OneRandomBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.custom.look.LookAtAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.look.LookAtTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.CustomDelayedBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.FloatToSurfaceOfFluid;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.WalkOrRunToWalkTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.InvalidateAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.TargetOrRetaliate;
import net.tslat.smartbrainlib.api.core.sensor.ExtendedSensor;
import net.tslat.smartbrainlib.api.core.sensor.custom.NearbyItemsSensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.HurtBySensor;
import net.tslat.smartbrainlib.util.BrainUtils;
import net.tslat.tme.api.explosion.StandardExplosion;
import net.tslat.tme.api.object.builder.EffectBuilder;
import net.tslat.tme.api.particle.ParticleBuilder;
import net.tslat.tme.api.sound.SoundBuilder;
import net.tslat.tme.api.util.EntityRetrievalUtil;
import net.tslat.tme.api.util.RandomUtil;
import net.tslat.tme.internal.networking.packet.TMEParticlePacket;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.constant.DefaultAnimations;

import java.util.List;

public class EliteKingBamBamBamEntity extends AoABoss implements AoARangedAttacker {
	public static final EntityDataAccessor<Boolean> EXHAUSTED = makeSynchedData(EliteKingBamBamBamEntity.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> STAFF_CHARGED = makeSynchedData(EliteKingBamBamBamEntity.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Integer> ENERGY_LEVEL = makeSynchedData(EliteKingBamBamBamEntity.class, EntityDataSerializers.INT);

	private static final RawAnimation EXHAUSTED_ANIM = RawAnimation.begin().thenPlayAndHold("misc.exhaust");
	private static final RawAnimation FIREBALLS_ANIM = RawAnimation.begin().thenPlay("attack.fireballs_start").then("attack.fireballs_loop", new Animation.LoopType() {
		@Override
		public boolean shouldPlayAgain(GeoAnimatable geoAnimatable, AnimationController<? extends GeoAnimatable> animationController, Animation animation) {
			return ((AoABoss)geoAnimatable).isAttackState(FIREBALLS_STATE);
		}
	}).thenPlay("attack.fireballs_end");
	private static final RawAnimation GET_UP_ANIM = RawAnimation.begin().thenPlay("misc.get_up");
	private static final RawAnimation GOLD_CONSUME_ANIM = RawAnimation.begin().thenPlay("misc.gold_consume");
	private static final RawAnimation STAFF_CHARGE_ANIM = RawAnimation.begin().thenPlay("misc.explosive_charge").thenPlay("misc.explosive_charge_end");
	private static final RawAnimation STAFF_ACTIVE_ANIM = RawAnimation.begin().thenLoop("misc.sphere_spin");
	private static final RawAnimation STAFF_INACTIVE_ANIM = RawAnimation.begin().thenLoop("misc.hide_sphere");
	private static final RawAnimation SUMMON_1_ANIM = RawAnimation.begin().thenLoop("misc.summon");
	private static final RawAnimation SUMMON_2_ANIM = RawAnimation.begin().thenLoop("misc.summon2");
	private static final RawAnimation SUMMON_3_ANIM = RawAnimation.begin().thenLoop("misc.big_summon");

	private static final int SUMMON_1_STATE = 0;
	private static final int SUMMON_2_STATE = 1;
	private static final int SUMMON_3_STATE = 2;
	private static final int SUMMON_4_STATE = 3;
	private static final int FIREBALLS_STATE = 4;

	public EliteKingBamBamBamEntity(EntityType<? extends EliteKingBamBamBamEntity> entityType, Level level) {
		super(entityType, level);
	}

	@Override
	protected void addSwingData(SwingData swings) {
		swings.put(SUMMON_1_STATE, new SwingData.Swing(20, 10, SUMMON_1_ANIM));
		swings.put(SUMMON_2_STATE, new SwingData.Swing(20, 10, SUMMON_1_ANIM));
		swings.put(SUMMON_3_STATE, new SwingData.Swing(20, 12, SUMMON_2_ANIM));
		swings.put(SUMMON_4_STATE, new SwingData.Swing(70, 53, SUMMON_3_ANIM));
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);

		builder.define(EXHAUSTED, false);
		builder.define(STAFF_CHARGED, false);
		builder.define(ENERGY_LEVEL, 150);
	}

	@Override
	public float getVoicePitch() {
		return 0.75f;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.PIGLIN_BRUTE_DEATH;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.PIGLIN_BRUTE_HURT;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.PIGLIN_BRUTE_AMBIENT;
	}

	@Nullable
	@Override
	public SoundEvent getMusic() {
		return AoASounds.KING_BAMBAMBAM_MUSIC.get();
	}

	@Override
	public List<ExtendedSensor<? extends AoABoss>> getSensors() {
		return ObjectArrayList.of(
				new AggroBasedNearbyPlayersSensor<AoABoss>()
						.onlyAttacking(TargetingConditions.forCombat().ignoreLineOfSight()::test)
						.onlyTargeting(TargetingConditions.forNonCombat().ignoreLineOfSight()::test),
				new HurtBySensor<AoABoss>().setPredicate((source, mob) -> source.getEntity() == null || source.getEntity().shouldBeSaved() || source.getEntity() instanceof Player),
				new NearbyItemsSensor<>());
	}

	@Override
	public BrainActivityGroup<AoABoss> getCoreTasks() {
		return BrainActivityGroup.coreTasks(
				new LookAtTarget<>(),
				new WalkOrRunToWalkTarget<>(),
				new FloatToSurfaceOfFluid<>());
	}

	@Override
	public BrainActivityGroup<AoABoss> getIdleTasks() {
		return BrainActivityGroup.idleTasks(
				new TargetOrRetaliate<>()
						.useMemory(MemoryModuleType.NEAREST_VISIBLE_ATTACKABLE_PLAYER)
						.attackablePredicate(target -> DamageUtil.isAttackable(target) && EntityUtil.areProbablyEnemies(this, target, true)));
	}

	@Override
	public BrainActivityGroup<AoABoss> getFightTasks() {
		return BrainActivityGroup.fightTasks(
				new InvalidateAttackTarget<>(),
				new LookAtAttackTarget<>(),
				new FirstApplicableBehaviour<>(
						new Rest(),
						new Fireballs(),
						new OneRandomBehaviour<>(
								Pair.of(new SummonMinions(SummonMinions.Variant.LITTLE_BAM, this::getSwingWarmupTicks)
										.cooldownFor(entity -> entity.rand().numberBetween(25, 35)), 15),
								Pair.of(new SummonMinions(SummonMinions.Variant.ZOMBIFIED_PIGLIN, this::getSwingWarmupTicks)
										.cooldownFor(entity -> entity.rand().numberBetween(21, 25)), 13),
								Pair.of(new SummonMinions(SummonMinions.Variant.PIGLIN_BRUTE, this::getSwingWarmupTicks)
										.cooldownFor(entity -> entity.rand().numberBetween(40, 60)), 10),
								Pair.of(new SummonMinions(SummonMinions.Variant.EMBRAKE, this::getSwingWarmupTicks)
										.cooldownFor(entity -> entity.rand().numberBetween(75, 90)), 5),
								Pair.of(new CustomDelayedBehaviour<>(60)
										.whenActivating(entity -> {
											setSynchedData(STAFF_CHARGED, true);
											BrainUtils.setForgettableMemory(entity, MemoryModuleType.ATTACK_COOLING_DOWN, true, 5);

											if (!EntityRetrievalUtil.getPlayers(entity.level(), getBoundingBox().expandTowards(getForward().multiply(3f, 1.25f, 3f))).isEmpty()) {
												AoAExplosionBuilder.at((ServerLevel)entity.level(), entity.position().add(0, 0.5f, 0).add(entity.getForward().multiply(1.5f, 1.5f, 1.5f)),
																	   AoAExplosions.KING_BAMBAMBAM_DISCHARGE, StandardExplosion::new).explodingEntity(entity).explode();
											}
										})
										.whenStarting(entity -> triggerAnim("Sphere", "Charge"))
										.startCondition(entity -> !BrainUtils.hasMemory(entity, MemoryModuleType.ATTACK_COOLING_DOWN))
										.cooldownFor(entity -> rand().numberBetween(2000, 3000)), 50)
						).startCondition(entity -> !getSynchedData(STAFF_CHARGED))));
	}

	@Override
	protected void customServerAiStep() {
		super.customServerAiStep();

		if (this.tickCount % 5 == 0)
			addEnergy(1);
	}

	protected void collapse() {
		setInvulnerable(true);
		consumeEnergy(999999);

		for (int i = 0; i < 5; i++) {
			Vec3 spawnPos = Vec3.atBottomCenterOf(RandomUtil.positionWithinRange(blockPosition(), 8, 2, 8, 3, 0, 3, false, level(), 5, (state, pos) -> Math.abs(pos.getY() - getY()) <= 5));

			PositionAndMotionUtil.getNearestOnGroundPosition(level(), spawnPos).ifPresent(pos -> {
				LivingEntity minion = SummonMinions.summonHoglin(this, pos, BrainUtils.memoryOrDefault(this, MemoryModuleType.HURT_BY_ENTITY, () -> null));

				if (minion != null) {
					EntityUtil.applyPotions(minion, this,
							new EffectBuilder(MobEffects.MOVEMENT_SPEED).hideParticles(),
							new EffectBuilder(MobEffects.DAMAGE_BOOST).level(4));

					TMEParticlePacket packet = new TMEParticlePacket();

					packet.particle(ParticleBuilder.forRandomPosInEntity(ParticleTypes.SOUL_FIRE_FLAME, minion)
							.spawnNTimes(50)
							.lifespan(40)
							.velocity(0, 0.25f, 0)
							.ignoreDistanceAndLimits()
							.lifespan(40));

					packet.sendToAllPlayersTrackingEntity(this);
					SoundBuilder.at(SoundEvents.BLAZE_SHOOT, level(), minion.position()).category(getSoundSource()).pitch(0.5f).varyPitch(0.1f).play();
				}
			});
		}

		for (int i = 0; i < 15; i++) {
			createMagnetisedItemStack(this, getEyePosition().add(0, 0.1f, 0), new Vec3(rand().scaledGaussianValue(0.3f), 0.25f, rand().scaledGaussianValue(0.3f)), Items.GOLDEN_APPLE, 200);
		}
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);

		if (isInvulnerable()) {
			setHealth(getMaxHealth() / 2f);
			setInvulnerable(true);
		}
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		float prevHealth = getHealth() / getMaxHealth();
		boolean success = super.hurt(source, amount);

		if (!source.is(Tags.DamageTypes.IS_TECHNICAL) && prevHealth > 0.25f && getHealth() / getMaxHealth() <= 0.25f)
			collapse();

		return success;
	}

	@Override
	public void onDamageTaken(DamageContainer damageContainer) {
		if (!getSynchedData(EXHAUSTED))
			consumeEnergy((int)Math.floor(damageContainer.getNewDamage()));
	}

	@Override
	public boolean isInvulnerableTo(DamageSource source) {
		if (source.is(DamageTypeTags.IS_EXPLOSION) && source.getEntity() instanceof LittleBamEntity)
			return true;

		return super.isInvulnerableTo(source);
	}

	public void addEnergy(int amount) {
		setSynchedData(ENERGY_LEVEL, Math.min(getSynchedData(ENERGY_LEVEL) + amount, 150));
	}

	public void consumeEnergy(int amount) {
		setSynchedData(ENERGY_LEVEL, Math.max(getSynchedData(ENERGY_LEVEL) - amount, 0));
	}

	public static AoAEntityStats.AttributeBuilder entityStats(EntityType<EliteKingBamBamBamEntity> entityType) {
		return AoAEntityStats.AttributeBuilder.createMonster(entityType)
				.health(1510)
				.moveSpeed(0.2875f)
				.projectileDamage(25)
				.knockbackResist(1)
				.followRange(128)
				.aggroRange(128)
				.armour(15, 30);
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(DefaultAnimations.genericWalkIdleController(this).setAnimationSpeed(1.2f));
		controllers.add(new AnimationController<>(this, "Summoning", 0, state -> {
			if (this.swinging)
				return state.setAndContinue(getSwingAnimation());

			state.getController().forceAnimationReset();

			return PlayState.STOP;
		}));
		controllers.add(new AnimationController<>(this, "Sphere", 0, state -> {
			if (!getSynchedData(STAFF_CHARGED))
				return state.setAndContinue(STAFF_INACTIVE_ANIM);

			return state.setAndContinue(STAFF_ACTIVE_ANIM);
		})
				.triggerableAnim("Charge", STAFF_CHARGE_ANIM));
		controllers.add(new AnimationController<>(this, "Fireballs", 0, state -> {
			if (isAttackState(FIREBALLS_STATE) || (!state.getController().hasAnimationFinished() && state.getController().getCurrentAnimation() != null && state.getController().getCurrentAnimation().animation().name().equals("attack.fireballs_end")))
				return state.setAndContinue(FIREBALLS_ANIM);

			state.getController().forceAnimationReset();

			return PlayState.STOP;
		}));
		controllers.add(new AnimationController<GeoAnimatable>(this, "Exhaustion", 5, state -> {
			if (getSynchedData(EXHAUSTED))
				return state.setAndContinue(EXHAUSTED_ANIM);

			if (state.isCurrentAnimation(EXHAUSTED_ANIM) || (state.isCurrentAnimation(GET_UP_ANIM) && !state.getController().hasAnimationFinished()))
				return state.setAndContinue(GET_UP_ANIM);

			state.getController().forceAnimationReset();

			return PlayState.STOP;
		}));
		controllers.add(new AnimationController<>(this, "Gold Consumption", 0, state -> PlayState.STOP)
				.triggerableAnim("consume", GOLD_CONSUME_ANIM));
	}

	@Override
	public void performRangedAttack(LivingEntity pTarget, float pVelocity) {}

	@Override
	public void doRangedAttackEntity(@Nullable BaseMobProjectile projectile, Entity target) {
		if (DamageUtil.doProjectileAttack(this, projectile, target, (float)getAttributeValue(AoAAttributes.RANGED_ATTACK_DAMAGE)))
			target.igniteForSeconds((int)Math.ceil(Math.max(0, target.getRemainingFireTicks()) / 20f) + 2);
	}

	@Override
	public void doRangedAttackBlock(@Nullable BaseMobProjectile projectile, BlockState blockHit, BlockPos pos, Direction sideHit) {}

	public static class Fireballs extends HeldBehaviour<EliteKingBamBamBamEntity> {
		private static final List<Pair<MemoryModuleType<?>, MemoryStatus>> MEMORY_REQUIREMENTS = ObjectArrayList.of(Pair.of(MemoryModuleType.ATTACK_COOLING_DOWN, MemoryStatus.VALUE_ABSENT));

		public Fireballs() {
			runFor(entity -> 200);
		}

		@Override
		protected List<Pair<MemoryModuleType<?>, MemoryStatus>> getMemoryRequirements() {
			return MEMORY_REQUIREMENTS;
		}

		@Override
		protected boolean checkExtraStartConditions(ServerLevel level, EliteKingBamBamBamEntity entity) {
			return entity.getSynchedData(STAFF_CHARGED);
		}

		@Override
		protected boolean shouldKeepRunning(EliteKingBamBamBamEntity entity) {
			return entity.getSynchedData(ENERGY_LEVEL) != 0;
		}

		@Override
		protected void start(EliteKingBamBamBamEntity entity) {
			entity.setSynchedData(ATTACK_STATE, FIREBALLS_STATE);
		}

		@Override
		protected void stop(EliteKingBamBamBamEntity entity) {
			entity.setSynchedData(ATTACK_STATE, SUMMON_1_STATE);
			entity.setSynchedData(STAFF_CHARGED, false);
			BrainUtils.setForgettableMemory(entity, MemoryModuleType.ATTACK_COOLING_DOWN, true, 60);
		}

		@Override
		protected void tick(EliteKingBamBamBamEntity entity) {
			if (this.runningTime < 20)
				return;

			Vec3 forward = entity.getForward();
			Vec3 left = Vec3.directionFromRotation(entity.getXRot(), entity.getYRot() + 90);

			for (int i = 0; i < 2; i++) {
				StickyFireballEntity fireball = new StickyFireballEntity(entity.level(), entity, BaseMobProjectile.Type.PHYSICAL);

				fireball.setPos((forward.x + left.x) * 0.25f + entity.getRandomX(0.25f), entity.getY() + 4.5f, (forward.z + left.z) * 0.25f + entity.getRandomZ(0.25f));
				fireball.setDeltaMovement(entity.getRandom().nextGaussian() * 0.15f, 1.2f, entity.getRandom().nextGaussian() * 0.15f);
				entity.level().addFreshEntity(fireball);
			}

			if (getRunningTime() % 9 == 0 || getRunningTime() % 20 == 0)
				entity.playSound(AoASounds.FLAMETHROWER.get(), 2, 0.5f);
		}
	}

	public static class Rest extends HeldBehaviour<EliteKingBamBamBamEntity> {
		public Rest() {
			runFor(entity -> entity.isInvulnerable() ? 480 : 160);
		}

		@Override
		protected List<Pair<MemoryModuleType<?>, MemoryStatus>> getMemoryRequirements() {
			return List.of();
		}

		@Override
		protected boolean checkExtraStartConditions(ServerLevel level, EliteKingBamBamBamEntity entity) {
			return entity.getSynchedData(ENERGY_LEVEL) == 0;
		}

		@Override
		protected boolean shouldKeepRunning(EliteKingBamBamBamEntity entity) {
			if (entity.isInvulnerable())
				return true;

			return entity.getSynchedData(ENERGY_LEVEL) != 150;
		}

		@Override
		protected void start(EliteKingBamBamBamEntity entity) {
			entity.setSynchedData(EXHAUSTED, true);
			entity.setDeltaMovement(0, 0, 0);
			entity.getNavigation().stop();
			BrainUtils.clearMemory(entity, MemoryModuleType.PATH);
			SoundBuilder.following(AoASounds.ENTITY_KING_BAMBAMBAM_EXHAUSTED, entity).play();
		}

		@Override
		protected void stop(EliteKingBamBamBamEntity entity) {
			entity.setSynchedData(EXHAUSTED, false);
			entity.setInvulnerable(false);
			entity.setInvulnerable(false);
			entity.addEnergy(150);
		}
	}

	public static class SummonMinions extends DelayedBehaviour<AoABoss> {
		private static final List<Pair<MemoryModuleType<?>, MemoryStatus>> MEMORY_REQUIREMENTS = ObjectArrayList.of(Pair.of(MemoryModuleType.ATTACK_COOLING_DOWN, MemoryStatus.VALUE_ABSENT));
		private static final Item[] LOOT_ITEMS = new Item[] {
				Items.GOLDEN_PICKAXE,
				Items.GOLDEN_HOE,
				Items.GOLDEN_SHOVEL,
				Items.GOLDEN_SWORD,
				Items.GOLDEN_AXE,
				Items.GOLDEN_HORSE_ARMOR,
				Items.GOLDEN_HELMET,
				Items.GOLDEN_CHESTPLATE,
				Items.GOLDEN_LEGGINGS,
				Items.GOLDEN_BOOTS,
				Items.GOLD_INGOT,
				Items.GOLD_NUGGET
		};

		private final SummonMinions.Variant variant;

		public SummonMinions(SummonMinions.Variant variant, Int2IntFunction delayFunction) {
			super(delayFunction.apply(variant.ordinal()));

			this.variant = variant;
		}

		@Override
		protected List<Pair<MemoryModuleType<?>, MemoryStatus>> getMemoryRequirements() {
			return MEMORY_REQUIREMENTS;
		}

		@Override
		protected void start(AoABoss entity) {
			entity.setAttackState(this.variant.ordinal());
			entity.swing(InteractionHand.MAIN_HAND);
			SoundBuilder.following(this.variant.summonSound, entity).category(entity.getSoundSource()).play();
		}

		@Override
		protected void doDelayedAction(AoABoss entity) {
			Vec3 spawnPos = Vec3.atBottomCenterOf(RandomUtil.positionWithinRange(entity.blockPosition(), 4, 2, 4, 1, 0, 1, false, entity.level(), 5, (state, pos) -> Math.abs(pos.getY() - entity.getY()) <= 5));

			PositionAndMotionUtil.getNearestOnGroundPosition(entity.level(), spawnPos).ifPresent(pos -> {
				LivingEntity minion = this.variant.spawnFunction.apply(entity, pos, BrainUtils.memoryOrDefault(entity, MemoryModuleType.HURT_BY_ENTITY, () -> BrainUtils.getTargetOfEntity(entity)));

				if (minion != null) {
					((EliteKingBamBamBamEntity)entity).consumeEnergy(this.variant.energyCost);
					EntityUtil.applyPotions(minion, entity,
							new EffectBuilder(MobEffects.DAMAGE_BOOST).level(3).hideParticles(),
							new EffectBuilder(MobEffects.MOVEMENT_SPEED).hideParticles());

					TMEParticlePacket packet = new TMEParticlePacket();

					packet.particle(ParticleBuilder.forRandomPosInEntity(ParticleTypes.SMALL_FLAME, minion)
							.spawnNTimes(50)
							.lifespan(40)
							.ignoreDistanceAndLimits());

					packet.sendToAllPlayersTrackingEntity(entity);
					SoundBuilder.following(SoundEvents.BLAZE_SHOOT, entity).category(entity.getSoundSource()).pitch(0.5f).varyPitch(0.1f).play();
				}
			});
		}

		@Override
		protected void stop(AoABoss entity) {
			BrainUtils.setForgettableMemory(entity, MemoryModuleType.ATTACK_COOLING_DOWN, true, entity.rand().numberBetween((this.variant.ordinal() + 1) * 25, (this.variant.ordinal() + 1) * 45));
		}

		@Nullable
		private static LivingEntity summonLittleBam(AoABoss entity, Vec3 pos, @Nullable LivingEntity target) {
			LittleBamEntity littleBam = new LittleBamEntity(AoAMonsters.LITTLE_BAM.get(), entity.level()) {
				private final AoABoss kingBamBamBam = entity;

				@Override
				public boolean isInvulnerableTo(DamageSource source) {
					return source.is(DamageTypeTags.IS_EXPLOSION) || super.isInvulnerableTo(source) || (source.getEntity() != null && PlayerUtil.getPlayerOrOwnerIfApplicable(source.getEntity()) == null);
				}

				@Override
				public void tick() {
					super.tick();

					if (this.kingBamBamBam == null || !this.kingBamBamBam.isAlive() || this.tickCount > 12000)
						discard();
				}

				@Override
				public boolean shouldBeSaved() {
					return false;
				}

				@Override
				protected void dropAllDeathLoot(ServerLevel level, DamageSource damageSource) {
					for (int i = 0; i < 3; i++) {
						createMagnetisedItemStack(entity, getEyePosition().add(0, 0.1f, 0), new Vec3(entity.rand().scaledGaussianValue(0.5f), 0.5f, entity.rand().scaledGaussianValue(0.5f)), entity.rand().selection(LOOT_ITEMS), 60);
					}
				}

				@Override
				public void setTarget(@Nullable LivingEntity target) {
					if (target != null && target.getType() != EntityType.PLAYER)
						return;

					super.setTarget(target);
				}
			};

			BrainUtils.setTargetOfEntity(littleBam, target);
			littleBam.setPos(pos);
			EventHooks.finalizeMobSpawn(littleBam, (ServerLevel)littleBam.level(), littleBam.level().getCurrentDifficultyAt(littleBam.blockPosition()), MobSpawnType.REINFORCEMENT, null);

			return entity.level().addFreshEntity(littleBam) ? littleBam : null;
		}

		@Nullable
		private static LivingEntity summonZombifiedPiglin(AoABoss entity, Vec3 pos, @Nullable LivingEntity target) {
			ZombifiedPiglin zombifiedPiglin = new ZombifiedPiglin(EntityType.ZOMBIFIED_PIGLIN, entity.level()) {
				private final AoABoss kingBamBamBam = entity;

				@Override
				public boolean isInvulnerableTo(DamageSource source) {
					return source.is(DamageTypeTags.IS_EXPLOSION) || super.isInvulnerableTo(source) || (source.getEntity() != null && PlayerUtil.getPlayerOrOwnerIfApplicable(source.getEntity()) == null);
				}

				@Override
				public void tick() {
					super.tick();

					if (this.kingBamBamBam == null || !this.kingBamBamBam.isAlive() || this.tickCount > 12000)
						discard();
				}

				@Override
				public boolean shouldBeSaved() {
					return false;
				}

				@Override
				protected void dropAllDeathLoot(ServerLevel level, DamageSource damageSource) {
					for (int i = 0; i < 3; i++) {
						createMagnetisedItemStack(entity, getEyePosition().add(0, 0.1f, 0), new Vec3(entity.rand().scaledGaussianValue(0.5f), 0.5f, entity.rand().scaledGaussianValue(0.5f)), entity.rand().selection(LOOT_ITEMS), 60);
					}
				}

				@Override
				public void setTarget(@Nullable LivingEntity target) {
					if (target != null && target.getType() != EntityType.PLAYER)
						return;

					super.setTarget(target);
				}
			};
			
			zombifiedPiglin.setPos(pos);
			EventHooks.finalizeMobSpawn(zombifiedPiglin, (ServerLevel)zombifiedPiglin.level(), zombifiedPiglin.level().getCurrentDifficultyAt(zombifiedPiglin.blockPosition()), MobSpawnType.REINFORCEMENT, null);

			return entity.level().addFreshEntity(zombifiedPiglin) ? zombifiedPiglin : null;
		}

		@Nullable
		private static LivingEntity summonPiglinBrute(AoABoss entity, Vec3 pos, @Nullable LivingEntity target) {
			PiglinBrute piglin = new PiglinBrute(EntityType.PIGLIN_BRUTE, entity.level()) {
				private final AoABoss kingBamBamBam = entity;

				@Override
				public boolean isInvulnerableTo(DamageSource source) {
					return source.is(DamageTypeTags.IS_EXPLOSION) || super.isInvulnerableTo(source) || (source.getEntity() != null && PlayerUtil.getPlayerOrOwnerIfApplicable(source.getEntity()) == null);
				}

				@Override
				public void tick() {
					super.tick();

					if (this.kingBamBamBam == null || !this.kingBamBamBam.isAlive() || this.tickCount > 12000)
						discard();
				}

				@Override
				public boolean shouldBeSaved() {
					return false;
				}

				@Override
				protected void dropAllDeathLoot(ServerLevel level, DamageSource damageSource) {
					for (int i = 0; i < 3; i++) {
						createMagnetisedItemStack(entity, getEyePosition().add(0, 0.1f, 0), new Vec3(entity.rand().scaledGaussianValue(0.5f), 0.25f, entity.rand().scaledGaussianValue(0.5f)), entity.rand().selection(LOOT_ITEMS), 60);
					}
				}

				@Override
				public void setTarget(@Nullable LivingEntity target) {
					if (target != null && target.getType() != EntityType.PLAYER)
						return;

					super.setTarget(target);
				}
			};

			BrainUtils.setTargetOfEntity(piglin, target);
			piglin.setPos(pos);
			EventHooks.finalizeMobSpawn(piglin, (ServerLevel)piglin.level(), piglin.level().getCurrentDifficultyAt(piglin.blockPosition()), MobSpawnType.REINFORCEMENT, null);

			return entity.level().addFreshEntity(piglin) ? piglin : null;
		}

		@Nullable
		private static LivingEntity summonEmbrake(AoABoss entity, Vec3 pos, @Nullable LivingEntity target) {
			EmbrakeEntity embrake = new EmbrakeEntity(AoAMonsters.EMBRAKE.get(), entity.level()) {
				private final AoABoss kingBamBamBam = entity;

				@Override
				public boolean isInvulnerableTo(DamageSource source) {
					return source.is(DamageTypeTags.IS_EXPLOSION) || super.isInvulnerableTo(source) || (source.getEntity() != null && PlayerUtil.getPlayerOrOwnerIfApplicable(source.getEntity()) == null);
				}

				@Override
				public void tick() {
					super.tick();

					if (this.kingBamBamBam == null || !this.kingBamBamBam.isAlive() || this.tickCount > 12000)
						discard();
				}

				@Override
				public boolean shouldBeSaved() {
					return false;
				}

				@Override
				protected void dropAllDeathLoot(ServerLevel level, DamageSource damageSource) {
					for (int i = 0; i < 3; i++) {
						createMagnetisedItemStack(entity, getEyePosition().add(0, 0.1f, 0), new Vec3(entity.rand().scaledGaussianValue(0.3f), 0.25f, entity.rand().scaledGaussianValue(0.3f)), entity.rand().selection(LOOT_ITEMS), 60);
					}
				}

				@Override
				public void setTarget(@Nullable LivingEntity target) {
					if (target != null && target.getType() != EntityType.PLAYER)
						return;

					super.setTarget(target);
				}
			};

			BrainUtils.setTargetOfEntity(embrake, target);
			embrake.setPos(pos);
			EventHooks.finalizeMobSpawn(embrake, (ServerLevel)embrake.level(), embrake.level().getCurrentDifficultyAt(embrake.blockPosition()), MobSpawnType.REINFORCEMENT, null);

			return entity.level().addFreshEntity(embrake) ? embrake : null;
		}

		@Nullable
		private static LivingEntity summonHoglin(AoABoss entity, Vec3 pos, @Nullable LivingEntity target) {
			Hoglin hoglin = new Hoglin(EntityType.HOGLIN, entity.level()) {
				private final AoABoss kingBamBamBam = entity;

				@Override
				public boolean isInvulnerableTo(DamageSource source) {
					return source.is(DamageTypeTags.IS_EXPLOSION) || super.isInvulnerableTo(source) || (source.getEntity() != null && PlayerUtil.getPlayerOrOwnerIfApplicable(source.getEntity()) == null);
				}

				@Override
				public void tick() {
					super.tick();

					if (this.kingBamBamBam == null || !this.kingBamBamBam.isAlive() || this.tickCount > 12000)
						discard();
				}

				@Override
				public boolean shouldBeSaved() {
					return false;
				}

				@Override
				protected void dropAllDeathLoot(ServerLevel level, DamageSource damageSource) {
					for (int i = 0; i < 3; i++) {
						createMagnetisedItemStack(entity, getEyePosition().add(0, 0.1f, 0), new Vec3(entity.rand().scaledGaussianValue(0.3f), 0.25f, entity.rand().scaledGaussianValue(0.3f)), entity.rand().selection(LOOT_ITEMS), 60);
					}
				}

				@Override
				public void setTarget(@Nullable LivingEntity target) {
					if (target != null && target.getType() != EntityType.PLAYER)
						return;

					super.setTarget(target);
				}
			};

			BrainUtils.setTargetOfEntity(hoglin, target);
			hoglin.setPos(pos);
			EventHooks.finalizeMobSpawn(hoglin, (ServerLevel)hoglin.level(), hoglin.level().getCurrentDifficultyAt(hoglin.blockPosition()), MobSpawnType.REINFORCEMENT, null);

			return entity.level().addFreshEntity(hoglin) ? hoglin : null;
		}

		public enum Variant {
			ZOMBIFIED_PIGLIN(AoASounds.ENTITY_KING_BAMBAMBAM_SUMMON_1, 25, SummonMinions::summonZombifiedPiglin),
			LITTLE_BAM(AoASounds.ENTITY_KING_BAMBAMBAM_SUMMON_1, 20, SummonMinions::summonLittleBam),
			PIGLIN_BRUTE(AoASounds.ENTITY_KING_BAMBAMBAM_SUMMON_2, 30, SummonMinions::summonPiglinBrute),
			EMBRAKE(AoASounds.ENTITY_KING_BAMBAMBAM_SUMMON_3, 40, SummonMinions::summonEmbrake);

			private final Holder<SoundEvent> summonSound;
			private final int energyCost;
			private final TriFunction<AoABoss, Vec3, LivingEntity, LivingEntity> spawnFunction;

			Variant(Holder<SoundEvent> summonSound, int energyCost, TriFunction<AoABoss, Vec3, LivingEntity, LivingEntity> spawnFunction) {
				this.summonSound = summonSound;
				this.energyCost = energyCost;
				this.spawnFunction = spawnFunction;
			}
		}
	}

	private static void createMagnetisedItemStack(AoABoss boss, Vec3 pos, Vec3 velocity, Item item, int delay) {
		ItemStack stack = item.getDefaultInstance();

		EnchantmentUtil.addEnchantment(boss.level(), stack, Enchantments.VANISHING_CURSE);

		ItemEntity itemEntity = new ItemEntity(boss.level(), pos.x, pos.y, pos.z, stack, velocity.x, velocity.y, velocity.z) {
			private final AoABoss magnetisedTo = boss;

			@Override
			public void tick() {
				if (this.magnetisedTo == null || !this.magnetisedTo.isAlive()) {
					discard();
				}
				else if (getY() < level().getMinBuildHeight()) {
					discard();
				}
				else if (this.tickCount > 40) {
					if (!EntityRetrievalUtil.getPlayers(level(), getBoundingBox().inflate(0.75f, 2, 0.75f)).isEmpty()) {
						SoundBuilder.at(SoundEvents.ARMOR_EQUIP_GOLD, level(), position()).category(boss.getSoundSource()).radius(6).play();
						discard();

						return;
					}

					if (this.tickCount > delay) {
						this.hurtMarked = true;
						this.noPhysics = true;
						Vec3 startPos = getEyePosition().add(0, 0.15f, 0);
						Vec3 travelVector = this.magnetisedTo.getEyePosition().subtract(0, 0.2f, 0).subtract(startPos);
						Vec3 angle = travelVector.normalize();
						double dist = travelVector.length();
						TMEParticlePacket particlePacket = new TMEParticlePacket();

						setNoGravity(true);
						setDeltaMovement(angle.scale(0.08f));

						for (float i = 0.25f; i < dist; i += 0.5f) {
							particlePacket.particle(ParticleBuilder.forPositions(ParticleTypes.ELECTRIC_SPARK, startPos.add(angle.multiply(i, i, i))).colourTint(1, 1, 1, 0.15f));
						}

						if (this.magnetisedTo.distanceToSqr(this) < 3.1f) {
							this.magnetisedTo.heal(50);
							((EliteKingBamBamBamEntity)this.magnetisedTo).addEnergy(30);
							this.magnetisedTo.triggerAnim("Gold Consumption", "consume");
							SoundBuilder.following(SoundEvents.ARMOR_EQUIP_GOLD, this.magnetisedTo).category(boss.getSoundSource()).pitch(0.3f).varyPitch(0.1f).play();
							particlePacket.particle(ParticleBuilder.forRandomPosInEntity(ParticleTypes.HEART, this.magnetisedTo).spawnNTimes(10));

							discard();
						}

						particlePacket.sendToAllPlayersTrackingEntity(this.magnetisedTo);
					}
				}

				super.tick();
			}

			@Override
			public boolean shouldBeSaved() {
				return false;
			}

			@Override
			public boolean hurt(DamageSource pSource, float pAmount) {
				discard();

				return true;
			}

			@Override
			public boolean hasPickUpDelay() {
				if (tickCount > 5)
					setItem(ItemStack.EMPTY);

				return true;
			}
		};

		itemEntity.setNeverPickUp();
		boss.level().addFreshEntity(itemEntity);
	}
}
