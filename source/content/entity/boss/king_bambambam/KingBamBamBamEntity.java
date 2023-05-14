package net.tslat.aoa3.content.entity.boss.king_bambambam;

import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.AoASoundBuilderPacket;
import net.tslat.aoa3.common.packet.packets.ServerParticlePacket;
import net.tslat.aoa3.common.registration.AoAAttributes;
import net.tslat.aoa3.common.registration.AoAExplosions;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.entity.AoAMobs;
import net.tslat.aoa3.content.entity.base.AoARangedAttacker;
import net.tslat.aoa3.content.entity.boss.AoABoss;
import net.tslat.aoa3.content.entity.brain.task.temp.LookAtAttackTarget;
import net.tslat.aoa3.content.entity.brain.task.temp.NearbyItemsSensor;
import net.tslat.aoa3.content.entity.mob.nether.EmbrakeEntity;
import net.tslat.aoa3.content.entity.mob.nether.LittleBamEntity;
import net.tslat.aoa3.content.entity.projectile.mob.BaseMobProjectile;
import net.tslat.aoa3.content.entity.projectile.mob.StickyFireballEntity;
import net.tslat.aoa3.library.builder.ParticleBuilder;
import net.tslat.aoa3.library.builder.SoundBuilder;
import net.tslat.aoa3.library.object.EntityDataHolder;
import net.tslat.aoa3.library.object.TriFunction;
import net.tslat.aoa3.library.object.explosion.StandardExplosion;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.aoa3.util.PositionAndMotionUtil;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.behaviour.DelayedBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.FirstApplicableBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.HeldBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.OneRandomBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.custom.look.LookAtTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.CustomDelayedBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.WalkOrRunToWalkTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.InvalidateAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.TargetOrRetaliate;
import net.tslat.smartbrainlib.api.core.sensor.ExtendedSensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.HurtBySensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.NearbyLivingEntitySensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.NearbyPlayersSensor;
import net.tslat.smartbrainlib.util.BrainUtils;
import net.tslat.smartbrainlib.util.EntityRetrievalUtil;
import net.tslat.smartbrainlib.util.RandomUtil;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.Animation;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;

import java.util.List;
import java.util.function.Supplier;

public class KingBamBamBamEntity extends AoABoss implements AoARangedAttacker {
	public static final EntityDataHolder<Boolean> EXHAUSTED = EntityDataHolder.register(KingBamBamBamEntity.class, EntityDataSerializers.BOOLEAN, false, entity -> entity.exhausted, (entity, value) -> entity.exhausted = value);
	public static final EntityDataHolder<Boolean> STAFF_CHARGED = EntityDataHolder.register(KingBamBamBamEntity.class, EntityDataSerializers.BOOLEAN, false, entity -> entity.staffCharged, (entity, value) -> entity.staffCharged = value);
	public static final EntityDataHolder<Integer> ENERGY_LEVEL = EntityDataHolder.register(KingBamBamBamEntity.class, EntityDataSerializers.INT, 100, entity -> entity.energy, (entity, value) -> entity.energy = value);

	private static final RawAnimation EXHAUSTED_ANIM = RawAnimation.begin().thenPlayAndHold("misc.exhaust");
	private static final RawAnimation FIREBALLS_ANIM = RawAnimation.begin().thenPlay("attack.fireballs_start").then("attack.fireballs_loop", new Animation.LoopType() {
		@Override
		public boolean shouldPlayAgain(GeoAnimatable geoAnimatable, AnimationController<? extends GeoAnimatable> animationController, Animation animation) {
			return ATTACK_STATE.is((Entity)geoAnimatable, FIREBALLS_STATE);
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
	private static final int FIREBALLS_STATE = 3;

	private boolean exhausted = false;
	private boolean staffCharged = false;
	private int energy = 100;

	public KingBamBamBamEntity(EntityType<? extends KingBamBamBamEntity> entityType, Level level) {
		super(entityType, level);
	}

	@Override
	protected void addSwingData(SwingData swings) {
		swings.put(SUMMON_1_STATE, new SwingData.Swing(20, 10, SUMMON_1_ANIM));
		swings.put(SUMMON_2_STATE, new SwingData.Swing(20, 12, SUMMON_2_ANIM));
		swings.put(SUMMON_3_STATE, new SwingData.Swing(70, 53, SUMMON_3_ANIM));
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();

		registerDataParams(EXHAUSTED);
		registerDataParams(STAFF_CHARGED);
		registerDataParams(ENERGY_LEVEL);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
		return 2.15625f;
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
	protected SoundEvent getStepSound(BlockPos pos, BlockState blockState) {
		return super.getStepSound(pos, blockState);
	}

	@Nullable
	@Override
	public SoundEvent getMusic() {
		return AoASounds.KING_BAMBAMBAM_MUSIC.get();
	}

	@Override
	public List<ExtendedSensor<AoABoss>> getSensors() {
		return ObjectArrayList.of(
				new NearbyPlayersSensor<>(),
				new NearbyLivingEntitySensor<AoABoss>().setScanRate(entity -> 40),
				new HurtBySensor<AoABoss>().setPredicate((source, mob) -> !EntityUtil.isHostileMob(mob)),
				new NearbyItemsSensor<>()
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
				new TargetOrRetaliate<>().useMemory(MemoryModuleType.NEAREST_VISIBLE_ATTACKABLE_PLAYER));
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
										.cooldownFor(entity -> entity.rand().randomNumberBetween(25, 35)), 15),
								Pair.of(new SummonMinions(SummonMinions.Variant.PIGLIN_BRUTE, this::getSwingWarmupTicks)
										.cooldownFor(entity -> entity.rand().randomNumberBetween(40, 60)), 10),
								Pair.of(new SummonMinions(SummonMinions.Variant.EMBRAKE, this::getSwingWarmupTicks)
										.cooldownFor(entity -> entity.rand().randomNumberBetween(75, 90)), 5),
								Pair.of(new CustomDelayedBehaviour<>(60)
										.whenActivating(entity -> {
											STAFF_CHARGED.set(entity, true);
											BrainUtils.setForgettableMemory(entity, MemoryModuleType.ATTACK_COOLING_DOWN, true, 5);

											if (!EntityRetrievalUtil.getPlayers(entity.level, getBoundingBox().expandTowards(getForward().multiply(3f, 1.25f, 3f))).isEmpty()) {
												new StandardExplosion(AoAExplosions.KING_BAMBAMBAM_DISCHARGE, (ServerLevel)entity.level, entity, entity.position().add(0, 0.5f, 0).add(entity.getForward().multiply(1.5f, 1.5f, 1.5f))).explode();
											}
										})
										.whenStarting(entity -> triggerAnim("Sphere", "Charge"))
										.startCondition(entity -> !BrainUtils.hasMemory(entity, MemoryModuleType.ATTACK_COOLING_DOWN))
										.cooldownFor(entity -> rand().randomNumberBetween(2000, 3000)), 50)
						).startCondition(entity -> STAFF_CHARGED.is(entity, false))));
	}

	@Override
	protected void customServerAiStep() {
		super.customServerAiStep();

		if (this.tickCount % 5 == 0)
			addEnergy(1);
	}

	@Override
	protected void onHurt(DamageSource source, float amount) {
		if (EXHAUSTED.is(this, false))
			consumeEnergy((int)Math.floor(amount));
	}

	@Override
	public boolean isInvulnerableTo(DamageSource source) {
		if (source.is(DamageTypeTags.IS_EXPLOSION) && source.getEntity() instanceof LittleBamEntity)
			return true;

		return super.isInvulnerableTo(source);
	}

	public void addEnergy(int amount) {
		ENERGY_LEVEL.set(this, Math.min(ENERGY_LEVEL.get(this) + amount, 100));
	}

	public void consumeEnergy(int amount) {
		ENERGY_LEVEL.set(this, Math.max(ENERGY_LEVEL.get(this) - amount, 0));
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
			if (STAFF_CHARGED.is(this, false))
				return state.setAndContinue(STAFF_INACTIVE_ANIM);

			return state.setAndContinue(STAFF_ACTIVE_ANIM);
		})
				.triggerableAnim("Charge", STAFF_CHARGE_ANIM));
		controllers.add(new AnimationController<>(this, "Fireballs", 0, state -> {
			if (ATTACK_STATE.is(this, FIREBALLS_STATE) || (!state.getController().hasAnimationFinished() && state.getController().getCurrentAnimation() != null && state.getController().getCurrentAnimation().animation().name().equals("attack.fireballs_end")))
				return state.setAndContinue(FIREBALLS_ANIM);

			state.getController().forceAnimationReset();

			return PlayState.STOP;
		}));
		controllers.add(new AnimationController<GeoAnimatable>(this, "Exhaustion", 5, state -> {
			if (EXHAUSTED.is(this, true))
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
		if (DamageUtil.doProjectileAttack(this, projectile, target, (float)getAttributeValue(AoAAttributes.RANGED_ATTACK_DAMAGE.get())))
			target.setSecondsOnFire((int)Math.ceil(Math.max(0, target.getRemainingFireTicks()) / 20f) + 2);
	}

	@Override
	public void doRangedAttackBlock(@Nullable BaseMobProjectile projectile, BlockState blockHit, BlockPos pos, Direction sideHit) {

	}

	public static class Fireballs extends HeldBehaviour<AoABoss> {
		private static final List<Pair<MemoryModuleType<?>, MemoryStatus>> MEMORY_REQUIREMENTS = ObjectArrayList.of(Pair.of(MemoryModuleType.ATTACK_COOLING_DOWN, MemoryStatus.VALUE_ABSENT));

		public Fireballs() {
			runFor(entity -> 200);
		}

		@Override
		protected List<Pair<MemoryModuleType<?>, MemoryStatus>> getMemoryRequirements() {
			return MEMORY_REQUIREMENTS;
		}

		@Override
		protected boolean checkExtraStartConditions(ServerLevel level, AoABoss entity) {
			return STAFF_CHARGED.is(entity, true);
		}

		@Override
		protected boolean shouldKeepRunning(AoABoss entity) {
			return !ENERGY_LEVEL.is(entity, 0);
		}

		@Override
		protected void start(AoABoss entity) {
			ATTACK_STATE.set(entity, FIREBALLS_STATE);
		}

		@Override
		protected void stop(AoABoss entity) {
			ATTACK_STATE.set(entity, SUMMON_1_STATE);
			STAFF_CHARGED.set(entity, false);
			BrainUtils.setForgettableMemory(entity, MemoryModuleType.ATTACK_COOLING_DOWN, true, 60);
		}

		@Override
		protected void tick(AoABoss entity) {
			if (this.runningTime < 20)
				return;

			Vec3 forward = entity.getForward();
			Vec3 left = Vec3.directionFromRotation(entity.getXRot(), entity.getYRot() + 90);

			for (int i = 0; i < 2; i++) {
				StickyFireballEntity fireball = new StickyFireballEntity(entity.level, (KingBamBamBamEntity)entity, BaseMobProjectile.Type.PHYSICAL);

				fireball.setPos((forward.x + left.x) * 0.25f + entity.getRandomX(0.25f), entity.getY() + 4.5f, (forward.z + left.z) * 0.25f + entity.getRandomZ(0.25f));
				fireball.setDeltaMovement(entity.getRandom().nextGaussian() * 0.4f, 1.4f, entity.getRandom().nextGaussian() * 0.4f);
				entity.level.addFreshEntity(fireball);
			}

			if (getRunningTime() % 9 == 0 || getRunningTime() % 20 == 0)
				entity.playSound(AoASounds.FLAMETHROWER.get(), 2, 0.5f);
		}
	}

	public static class Rest extends HeldBehaviour<AoABoss> {
		public Rest() {
			runFor(entity -> 200);
		}

		@Override
		protected List<Pair<MemoryModuleType<?>, MemoryStatus>> getMemoryRequirements() {
			return List.of();
		}

		@Override
		protected boolean checkExtraStartConditions(ServerLevel level, AoABoss entity) {
			return ENERGY_LEVEL.is(entity, 0);
		}

		@Override
		protected boolean shouldKeepRunning(AoABoss entity) {
			return !ENERGY_LEVEL.is(entity, 100);
		}

		@Override
		protected void start(AoABoss entity) {
 			EXHAUSTED.set(entity, true);
			entity.setDeltaMovement(0, 0, 0);
			entity.getNavigation().stop();
			BrainUtils.clearMemory(entity, MemoryModuleType.PATH);
			AoAPackets.messageAllPlayersTrackingEntity(new AoASoundBuilderPacket(new SoundBuilder(AoASounds.ENTITY_KING_BAMBAMBAM_EXHAUSTED).followEntity(entity)), entity);
		}

		@Override
		protected void stop(AoABoss entity) {
			EXHAUSTED.set(entity, false);
			((KingBamBamBamEntity)entity).addEnergy(100);
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

		private final Variant variant;

		public SummonMinions(Variant variant, Int2IntFunction delayFunction) {
			super(delayFunction.apply(variant.ordinal()));

			this.variant = variant;
		}

		@Override
		protected List<Pair<MemoryModuleType<?>, MemoryStatus>> getMemoryRequirements() {
			return MEMORY_REQUIREMENTS;
		}

		@Override
		protected void start(AoABoss entity) {
			ATTACK_STATE.set(entity, this.variant.ordinal());
			entity.swing(InteractionHand.MAIN_HAND);
			AoAPackets.messageAllPlayersTrackingEntity(new AoASoundBuilderPacket(new SoundBuilder(this.variant.summonSound).followEntity(entity).category(SoundSource.HOSTILE)), entity);
		}

		@Override
		protected void doDelayedAction(AoABoss entity) {
			Vec3 spawnPos = Vec3.atBottomCenterOf(RandomUtil.getRandomPositionWithinRange(entity.blockPosition(), 4, 2, 4, 1, 0, 1, false, entity.level, 5, (state, pos) -> Math.abs(pos.getY() - entity.getY()) <= 5));
			spawnPos = PositionAndMotionUtil.moveDownToGround(entity.level, spawnPos);
			spawnPos = PositionAndMotionUtil.moveUpToSurface(entity.level, spawnPos);
			LivingEntity minion = this.variant.spawnFunction.apply(entity, spawnPos, BrainUtils.memoryOrDefault(entity, MemoryModuleType.HURT_BY_ENTITY, () -> null));

			if (minion != null) {
				LivingEntity target = BrainUtils.getTargetOfEntity(entity);

				((KingBamBamBamEntity)entity).consumeEnergy(this.variant.energyCost);

				if (target != null)
					BrainUtils.setMemory(minion, MemoryModuleType.ATTACK_TARGET, target);

				ServerParticlePacket packet = new ServerParticlePacket();

				packet.particle(ParticleBuilder.forRandomPosInEntity(ParticleTypes.SMALL_FLAME, minion)
						.spawnNTimes(50)
						.lifespan(40)
						.ignoreDistanceAndLimits());

				AoAPackets.messageAllPlayersTrackingEntity(packet, entity);
				AoAPackets.messageAllPlayersTrackingEntity(new AoASoundBuilderPacket(new SoundBuilder(SoundEvents.BLAZE_SHOOT).followEntity(entity).category(SoundSource.HOSTILE).pitch(0.5f).varyPitch(0.1f)), entity);
			}
		}

		@Override
		protected void stop(AoABoss entity) {
			BrainUtils.setForgettableMemory(entity, MemoryModuleType.ATTACK_COOLING_DOWN, true, entity.rand().randomNumberBetween((this.variant.ordinal() + 1) * 40, (this.variant.ordinal() + 1) * 60));
		}

		@javax.annotation.Nullable
		private static LivingEntity summonLittleBam(AoABoss entity, Vec3 pos, @Nullable LivingEntity target) {
			LittleBamEntity littleBam = new LittleBamEntity(AoAMobs.LITTLE_BAM.get(), entity.level) {
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
				protected void dropAllDeathLoot(DamageSource source) {
					for (int i = 0; i < 3; i++) {
						createMagnetisedItemStack(entity, getEyePosition().add(0, 0.1f, 0), new Vec3(entity.rand().randomScaledGaussianValue(0.5f), 0.5f, entity.rand().randomScaledGaussianValue(0.5f)), entity.rand().getRandomSelection(LOOT_ITEMS));
					}
				}

				@Override
				public void setTarget(@Nullable LivingEntity target) {
					if (target != null && target.getType() != EntityType.PLAYER)
						return;

					super.setTarget(target);
				}
			};

			littleBam.setTarget(target);
			littleBam.setPos(pos);
			littleBam.finalizeSpawn((ServerLevel)littleBam.level, littleBam.level.getCurrentDifficultyAt(littleBam.blockPosition()), MobSpawnType.REINFORCEMENT, null, null);

			return entity.level.addFreshEntity(littleBam) ? littleBam : null;
		}

		@javax.annotation.Nullable
		private static LivingEntity summonPiglinBrute(AoABoss entity, Vec3 pos, @Nullable LivingEntity target) {
			PiglinBrute piglin = new PiglinBrute(EntityType.PIGLIN_BRUTE, entity.level) {
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
				protected void dropAllDeathLoot(DamageSource source) {
					for (int i = 0; i < 3; i++) {
						createMagnetisedItemStack(entity, getEyePosition().add(0, 0.1f, 0), new Vec3(entity.rand().randomScaledGaussianValue(0.5f), 0.25f, entity.rand().randomScaledGaussianValue(0.5f)), entity.rand().getRandomSelection(LOOT_ITEMS));
					}
				}

				@Override
				public void setTarget(@Nullable LivingEntity target) {
					if (target != null && target.getType() != EntityType.PLAYER)
						return;

					super.setTarget(target);
				}
			};

			piglin.setTarget(target);
			piglin.setPos(pos);
			piglin.finalizeSpawn((ServerLevel)piglin.level, piglin.level.getCurrentDifficultyAt(piglin.blockPosition()), MobSpawnType.REINFORCEMENT, null, null);

			return entity.level.addFreshEntity(piglin) ? piglin : null;
		}

		@javax.annotation.Nullable
		private static LivingEntity summonEmbrake(AoABoss entity, Vec3 pos, @Nullable LivingEntity target) {
			EmbrakeEntity embrake = new EmbrakeEntity(AoAMobs.EMBRAKE.get(), entity.level) {
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
				protected void dropAllDeathLoot(DamageSource source) {
					for (int i = 0; i < 3; i++) {
						createMagnetisedItemStack(entity, getEyePosition().add(0, 0.1f, 0), new Vec3(entity.rand().randomScaledGaussianValue(0.3f), 0.25f, entity.rand().randomScaledGaussianValue(0.3f)), entity.rand().getRandomSelection(LOOT_ITEMS));
					}
				}

				@Override
				public void setTarget(@Nullable LivingEntity target) {
					if (target != null && target.getType() != EntityType.PLAYER)
						return;

					super.setTarget(target);
				}
			};

			embrake.setTarget(target);
			embrake.setPos(pos);
			embrake.finalizeSpawn((ServerLevel)embrake.level, embrake.level.getCurrentDifficultyAt(embrake.blockPosition()), MobSpawnType.REINFORCEMENT, null, null);

			return entity.level.addFreshEntity(embrake) ? embrake : null;
		}

		private static void createMagnetisedItemStack(AoABoss boss, Vec3 pos, Vec3 velocity, Item item) {
			ItemStack stack = item.getDefaultInstance();

			stack.enchant(Enchantments.VANISHING_CURSE, 1);

			ItemEntity itemEntity = new ItemEntity(boss.level, pos.x, pos.y, pos.z, stack, velocity.x, velocity.y, velocity.z) {
				private final AoABoss magnetisedTo = boss;

				@Override
				public void tick() {
					if (this.magnetisedTo == null || !this.magnetisedTo.isAlive()) {
						discard();
					}
					else if (this.getY() < this.level.getMinBuildHeight()) {
						discard();
					}
					else if (this.tickCount > 60) {
						hurtMarked = true;
						this.noPhysics = true;
						Vec3 startPos = getEyePosition().add(0, 0.15f, 0);
						Vec3 travelVector = this.magnetisedTo.getEyePosition().subtract(0, 0.2f, 0).subtract(startPos);
						Vec3 angle = travelVector.normalize();
						double dist = travelVector.length();
						ServerParticlePacket particlePacket = new ServerParticlePacket();

						setNoGravity(true);
						setDeltaMovement(angle.scale(0.08f));

						for (float i = 0.25f; i < dist; i += 0.5f) {
							particlePacket.particle(ParticleBuilder.forPos(ParticleTypes.ELECTRIC_SPARK, startPos.add(angle.multiply(i, i, i))).colourOverride(1, 1, 1, 0.15f));
						}

						if (this.magnetisedTo.distanceToSqr(this) < 3.1f) {
							this.magnetisedTo.heal(50);
							((KingBamBamBamEntity)this.magnetisedTo).addEnergy(30);
							this.magnetisedTo.triggerAnim("Gold Consumption", "consume");
							AoAPackets.messageAllPlayersTrackingEntity(new AoASoundBuilderPacket(new SoundBuilder(SoundEvents.ARMOR_EQUIP_GOLD).followEntity(this.magnetisedTo).category(SoundSource.HOSTILE).pitch(0.3f).varyPitch(0.1f)), this.magnetisedTo);
							particlePacket.particle(ParticleBuilder.forRandomPosInEntity(ParticleTypes.HEART, this.magnetisedTo).spawnNTimes(10));

							discard();
						}
						else if (!EntityRetrievalUtil.getPlayers(this.level, getBoundingBox().inflate(0.75f, 2, 0.75f)).isEmpty()) {
							AoAPackets.messageNearbyPlayers(new AoASoundBuilderPacket(new SoundBuilder(SoundEvents.ARMOR_EQUIP_GOLD).category(SoundSource.PLAYERS).atPos(this.level, position().x, position().y, position().z)), (ServerLevel)this.level, position(), 6);
							discard();
						}

						AoAPackets.messageAllPlayersTrackingEntity(particlePacket, this.magnetisedTo);
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
			boss.level.addFreshEntity(itemEntity);
		}

		public enum Variant {
			LITTLE_BAM(AoASounds.ENTITY_KING_BAMBAMBAM_SUMMON_1, 20, SummonMinions::summonLittleBam),
			PIGLIN_BRUTE(AoASounds.ENTITY_KING_BAMBAMBAM_SUMMON_2, 30, SummonMinions::summonPiglinBrute),
			EMBRAKE(AoASounds.ENTITY_KING_BAMBAMBAM_SUMMON_3, 40, SummonMinions::summonEmbrake);

			private final Supplier<SoundEvent> summonSound;
			private final int energyCost;
			private final TriFunction<AoABoss, Vec3, LivingEntity, LivingEntity> spawnFunction;

			Variant(Supplier<SoundEvent> summonSound, int energyCost, TriFunction<AoABoss, Vec3, LivingEntity, LivingEntity> spawnFunction) {
				this.summonSound = summonSound;
				this.energyCost = energyCost;
				this.spawnFunction = spawnFunction;
			}
		}
	}
}
