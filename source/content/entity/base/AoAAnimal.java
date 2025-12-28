package net.tslat.aoa3.content.entity.base;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.schedule.Activity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.neoforge.common.CommonHooks;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoAAttributes;
import net.tslat.aoa3.content.entity.brain.task.temp.FixedFollowParent;
import net.tslat.aoa3.library.builder.MultipartBuilder;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.aoa3.util.AttributeUtil;
import net.tslat.smartbrainlib.api.SmartBrainOwner;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.SmartBrainProvider;
import net.tslat.smartbrainlib.api.core.behaviour.FirstApplicableBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.OneRandomBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.custom.look.LookAtTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.BreedWithPartner;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.Idle;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.Panic;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.FloatToSurfaceOfFluid;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.FollowTemptation;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.WalkOrRunToWalkTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetRandomWalkTarget;
import net.tslat.smartbrainlib.api.core.sensor.ExtendedSensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.HurtBySensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.ItemTemptingSensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.NearbyLivingEntitySensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.NearbyPlayersSensor;
import net.tslat.tme.api.object.EasyRandom;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;
import java.util.Map;

public abstract class AoAAnimal<T extends AoAAnimal<T>> extends Animal implements GeoEntity, SmartBrainOwner<T>, AoAMultipartEntity<T> {
	protected static final AttributeModifier BABY_HEALTH_MOD = new AttributeModifier(AdventOfAscension.id("baby_health_mod"), -0.5f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
	public static final EntityDataAccessor<Boolean> IMMOBILE = makeSynchedData(AoAAnimal.class, EntityDataSerializers.BOOLEAN);

	private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);
	private AoAEntityPart<T>[] parts = new AoAEntityPart[0];

	public AoAAnimal(EntityType<? extends Animal> entityType, Level world) {
		super(entityType, world);

		getNavigation().setCanFloat(true);
		registerParts(ENTITY_COUNTER, this::setId, parts -> this.parts = parts);
	}

	@Override
	public MultipartBuilder<? extends T> definePartEntities() {
		return null;
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);

		builder.define(IMMOBILE, false);
	}

	protected static <D> EntityDataAccessor<D> makeSynchedData(Class<? extends SyncedDataHolder> entityClass, EntityDataSerializer<D> serializer) {
		return SynchedEntityData.defineId(entityClass, serializer);
	}

	protected <D> D getSynchedData(EntityDataAccessor<D> data) {
        return getEntityData().get(data);
	}

	protected <D> void setSynchedData(EntityDataAccessor<D> data, D value) {
		getEntityData().set(data, value);
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return null;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return null;
	}

	@Nullable
	protected SoundEvent getStepSound(BlockPos pos, BlockState blockState) {
		return null;
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState blockState) {
		if (!blockState.liquid()) {
			BlockState state = level().getBlockState(pos.above());
			SoundType blockSound = state.getBlock() == Blocks.SNOW ? state.getSoundType(level(), pos, this) : blockState.getSoundType(level(), pos, this);
			SoundEvent stepSound = blockSound.getStepSound();
			SoundEvent stepSoundOverlay = getStepSound(pos, blockState);

			playStepSounds(stepSound, stepSoundOverlay);

			if (isQuadruped() && !level().isClientSide)
				AoAScheduler.schedule(6, tick -> playStepSounds(stepSound, stepSoundOverlay));
		}
	}

	protected void playStepSounds(SoundEvent stepSound, @Nullable SoundEvent stepSoundOverlay) {
		float stepWeight = getStepWeight() - 1;

		playSound(stepSound, 5 * 0.15f + stepWeight * 0.15f, 1 - stepWeight * 0.1f);

		if (stepSoundOverlay != null)
			playSound(stepSoundOverlay, 5 * 0.15f + stepWeight * 0.15f, 1 - stepWeight * 0.1f);
	}

	@Override
	protected float nextStep() {
		return this.moveDist + 1;
	}

	protected float getStepWeight() {
		return 1f;
	}

	protected boolean isQuadruped() {
		return false;
	}

	@Override
	public int getAmbientSoundInterval() {
		return 240;
	}

	public final EasyRandom rand() {
		return EasyRandom.wrap(getRandom());
	}

	@Override
	protected Brain.Provider<? extends T> brainProvider() {
		return new SmartBrainProvider(this);
	}

	@Override
	public List<ExtendedSensor<? extends T>> getSensors() {
		return ObjectArrayList.of(
				new ItemTemptingSensor<T>().temptedWith((entity, stack) -> isFood(stack)),
				new NearbyPlayersSensor<>(),
				new NearbyLivingEntitySensor<T>().setScanRate(entity -> 40),
				new HurtBySensor<>());
	}

	@Override
	public BrainActivityGroup<? extends T> getCoreTasks() {
		return BrainActivityGroup.coreTasks(
				new LookAtTarget<>(),
				new WalkOrRunToWalkTarget<>().startCondition(entity -> !isDoingStationaryActivity()),
				new FloatToSurfaceOfFluid<>());
	}

	@Override
	public BrainActivityGroup<? extends T> getIdleTasks() {
		return BrainActivityGroup.idleTasks(
				new BreedWithPartner<>().startCondition(entity -> canBreed()),
				new FirstApplicableBehaviour<>(
						new FixedFollowParent<>(),
						new FollowTemptation<>().startCondition(entity -> getTemptationTag() != null),
						new OneRandomBehaviour<>(
								new SetRandomWalkTarget<>().speedModifier(0.9f),
								new Idle<>().runFor(entity -> entity.getRandom().nextInt(30, 60)))));
	}

	@Override
	public Map<Activity, BrainActivityGroup<? extends T>> getAdditionalTasks() {
		return Map.of(Activity.PANIC, new BrainActivityGroup<T>(Activity.PANIC)
				.behaviours(new Panic<>()
						.setRadius(15, 10)
						.speedMod(entity -> 1.5f))
				.requireAndWipeMemoriesOnUse(MemoryModuleType.HURT_BY_ENTITY));
	}

	@Override
	public List<Activity> getActivityPriorities() {
		return ObjectArrayList.of(Activity.FIGHT, Activity.PANIC, Activity.IDLE);
	}

	@Override
	protected void customServerAiStep() {
		tickBrain((T)this);

		if (!isBaby()) {
			AttributeUtil.removeModifier(this, Attributes.MAX_HEALTH, BABY_HEALTH_MOD);
		}
		else {
			AttributeUtil.applyPermanentModifier(this, Attributes.MAX_HEALTH, BABY_HEALTH_MOD, true);
		}
	}

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData spawnData) {
		this.xpReward = calculateKillXp();

		if (reason == MobSpawnType.SPAWNER)
			this.xpReward *= 0.5d;

		if (isBaby())
			AttributeUtil.applyPermanentModifier(this, Attributes.MAX_HEALTH, BABY_HEALTH_MOD);

		AoAAttributes.addSpawnVarianceHealthMod(this, 1f);
		AoAAttributes.addSpawnVarianceSpeedMod(this, 1f);

		return super.finalizeSpawn(world, difficulty, reason, spawnData);
	}

	public int calculateKillXp() {
		return Mth.floor(getAttributeValue(Attributes.MAX_HEALTH) / 25f);
	}

	public void setImmobile(boolean immobile) {
		setSynchedData(IMMOBILE, immobile);

		if (getSynchedData(IMMOBILE))
			getNavigation().stop();
	}

	public boolean isDoingStationaryActivity() {
		return getSynchedData(IMMOBILE);
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		onMultipartParentHurt(source);

		return super.hurt(source, amount);
	}

	@Override
	public boolean doHurtTarget(Entity target) {
		if (super.doHurtTarget(target)) {
			onAttack(target);

			return true;
		}

		return false;
	}

	@Override
	public boolean canBreed() {
		return true;
	}

	protected void onAttack(Entity target) {}

	@Override
	public void die(DamageSource source) {
		if (CommonHooks.onLivingDeath(this, source))
			return;

		if (!isRemoved() && !this.dead) {
			Entity lastAttacker = source.getEntity();
			LivingEntity killer = getKillCredit();

			if (this.deathScore >= 0 && killer != null)
				killer.awardKillScore(this, this.deathScore, source);

			if (isSleeping())
				stopSleeping();

			this.dead = true;

			if (level() instanceof ServerLevel serverLevel) {
				if (lastAttacker == null || lastAttacker.killedEntity(serverLevel, this)) {
					gameEvent(GameEvent.ENTITY_DIE);
					dropAllDeathLoot(serverLevel, source);
					createWitherRose(killer);
				}

				serverLevel.broadcastEntityEvent(this, (byte)3);
			}

			getCombatTracker().recheckStatus();
			setPose(Pose.DYING);
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		saveMultiparts(compound);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		loadMultiparts(compound);
	}

	@Override
	public void tick() {
		super.tick();
		updateMultipartPositions();
	}

	@Override
	public boolean isFood(ItemStack stack) {
		if (getFoodTag() != null)
			return stack.is(getFoodTag());

		return getTemptationTag() != null && stack.is(getTemptationTag());
	}

	@Nullable
	protected TagKey<Item> getTemptationTag() {
		return getFoodTag();
	}

	@Nullable
	protected TagKey<Item> getFoodTag() {
		return null;
	}

	@Nullable
	@Override
	public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob partner) {
		return null;
	}

	@Override
	public void finalizeSpawnChildFromBreeding(ServerLevel level, Animal animal, @Nullable AgeableMob baby) {
		super.finalizeSpawnChildFromBreeding(level, animal, baby);

		baby.finalizeSpawn(level, level.getCurrentDifficultyAt(BlockPos.containing(baby.position())), MobSpawnType.BREEDING, null);
	}

	@Override
	public AoAEntityPart<T>[] getParts() {
		return this.parts;
	}

	@Override
	public boolean isMultipartEntity() {
		return isMultipartActive();
	}

	@Override
	public void refreshDimensions() {
		super.refreshDimensions();
		refreshMultipartDimensions();
	}

	@Override
	public void setId(int id) {
		super.setId(id);
		setMultipartIds(id);
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.geoCache;
	}
}
