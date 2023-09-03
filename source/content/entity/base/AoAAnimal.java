package net.tslat.aoa3.content.entity.base;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.stats.Stats;
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
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.entity.PartEntity;
import net.tslat.aoa3.content.entity.brain.task.temp.BreedWithPartner;
import net.tslat.aoa3.content.entity.brain.task.temp.FollowParent;
import net.tslat.aoa3.content.entity.brain.task.temp.FollowTemptation;
import net.tslat.aoa3.content.entity.brain.task.temp.Panic;
import net.tslat.aoa3.library.object.EntityDataHolder;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.smartbrainlib.api.SmartBrainOwner;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.SmartBrainProvider;
import net.tslat.smartbrainlib.api.core.behaviour.FirstApplicableBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.OneRandomBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.custom.look.LookAtTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.Idle;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.FloatToSurfaceOfFluid;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.WalkOrRunToWalkTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetRandomWalkTarget;
import net.tslat.smartbrainlib.api.core.sensor.ExtendedSensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.HurtBySensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.ItemTemptingSensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.NearbyLivingEntitySensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.NearbyPlayersSensor;
import net.tslat.smartbrainlib.util.BrainUtils;
import net.tslat.smartbrainlib.util.RandomUtil;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public abstract class AoAAnimal<T extends AoAAnimal<T>> extends Animal implements GeoEntity, SmartBrainOwner<T> {
	protected static final AttributeModifier BABY_HEALTH_MOD = new AttributeModifier(UUID.fromString("8d9e3f81-b520-4c1c-8c7c-e83906296599"), "Baby Health Mod", -0.5f, AttributeModifier.Operation.MULTIPLY_TOTAL);

	private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);
	protected AoAEntityPart<?>[] parts = new AoAEntityPart[0];
	private EntityDataHolder<?>[] dataParams;

	public AoAAnimal(EntityType<? extends Animal> entityType, Level world) {
		super(entityType, world);

		getNavigation().setCanFloat(true);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();

		this.dataParams = new EntityDataHolder<?>[0];

		for (EntityDataHolder<?> dataHolder : this.dataParams) {
			dataHolder.defineDefault(this);
		}
	}

	protected final void registerDataParams(EntityDataHolder<?>... params) {
		EntityDataHolder<?>[] newArray = new EntityDataHolder[this.dataParams.length + params.length];

		System.arraycopy(this.dataParams, 0, newArray, 0, this.dataParams.length);
		System.arraycopy(params, 0, newArray, this.dataParams.length, params.length);

		for (EntityDataHolder<?> param : params) {
			param.defineDefault(this);
		}

		this.dataParams = newArray;
	}

	@Override
	protected abstract float getStandingEyeHeight(Pose pose, EntityDimensions size);

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
		if (!blockState.liquid()) {
			BlockState state = level().getBlockState(pos.above());
			SoundType blockSound = state.getBlock() == Blocks.SNOW ? state.getSoundType(level(), pos, this) : blockState.getSoundType(level(), pos, this);

			return blockSound.getStepSound();
		}

		return null;
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState blockIn) {
		SoundEvent stepSound = getStepSound(pos, blockIn);

		if (stepSound != null)
			playSound(stepSound, 0.15F, 1.0F);
	}

	@Override
	public int getAmbientSoundInterval() {
		return 240;
	}

	public final RandomUtil.EasyRandom rand() {
		return new RandomUtil.EasyRandom(getRandom());
	}

	@Override
	protected Brain.Provider<? extends T> brainProvider() {
		return new SmartBrainProvider(this);
	}

	@Override
	public List<ExtendedSensor<? extends T>> getSensors() {
		return ObjectArrayList.of(
				new ItemTemptingSensor<T>().setTemptingItems(getTemptItem() == null ? Ingredient.EMPTY : Ingredient.of(getTemptItem())),
				new NearbyPlayersSensor<>(),
				new NearbyLivingEntitySensor<T>().setScanRate(entity -> 40),
				new HurtBySensor<>());
	}

	@Override
	public BrainActivityGroup<? extends T> getCoreTasks() {
		return BrainActivityGroup.coreTasks(
				new LookAtTarget<>(),
				new WalkOrRunToWalkTarget<>(),
				new FloatToSurfaceOfFluid<>());
	}

	@Override
	public BrainActivityGroup<? extends T> getIdleTasks() {
		return BrainActivityGroup.idleTasks(
				new BreedWithPartner<>().startCondition(entity -> canBreed()),
				new FirstApplicableBehaviour<>(
						new FollowParent<>(),
						new FollowTemptation<>().startCondition(entity -> getTemptItem() != null),
						new OneRandomBehaviour<>(
								new SetRandomWalkTarget<>().speedModifier(0.9f),
								new Idle<>().runFor(entity -> entity.getRandom().nextInt(30, 60)))
				).startCondition(entity -> !BrainUtils.hasMemory(entity, MemoryModuleType.IS_PANICKING)));
	}

	@Override
	public Map<Activity, BrainActivityGroup<? extends T>> getAdditionalTasks() {
		return Map.of(Activity.PANIC, new BrainActivityGroup<T>(Activity.PANIC)
				.behaviours(new Panic<>())
				.requireAndWipeMemoriesOnUse(MemoryModuleType.HURT_BY_ENTITY));
	}

	@Override
	public List<Activity> getActivityPriorities() {
		return ObjectArrayList.of(Activity.FIGHT, Activity.PANIC, Activity.IDLE);
	}

	@Override
	protected void customServerAiStep() {
		tickBrain((T)this);

		if (this.age == 0 && EntityUtil.hasAttributeModifier(this, Attributes.MAX_HEALTH, BABY_HEALTH_MOD))
			EntityUtil.removeAttributeModifier(this, Attributes.MAX_HEALTH, BABY_HEALTH_MOD);
	}

	@Override
	public void refreshDimensions() {
		super.refreshDimensions();

		if (isMultipartEntity()) {
			for (AoAEntityPart<?> part : getParts()) {
				part.refreshDimensions();
			}
		}
	}

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag dataTag) {
		this.xpReward = calculateKillXp();

		if (reason == MobSpawnType.SPAWNER)
			this.xpReward *= 0.5d;

		if (this.age < 0)
			EntityUtil.applyAttributeModifierSafely(this, Attributes.MAX_HEALTH, BABY_HEALTH_MOD, true);

		return super.finalizeSpawn(world, difficulty, reason, spawnData, dataTag);
	}

	public int calculateKillXp() {
		return (int)(getAttributeValue(Attributes.MAX_HEALTH) / 25f);
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

	@Override
	protected void actuallyHurt(DamageSource source, float amount) {
		if (!isInvulnerableTo(source)) {
			amount = ForgeHooks.onLivingHurt(this, source, amount);

			if (amount <= 0)
				return;

			amount = getDamageAfterArmorAbsorb(source, amount);
			amount = getDamageAfterMagicAbsorb(source, amount);
			float adjustedDamage = Math.max(amount - getAbsorptionAmount(), 0);
			float absorbedAmount = amount - adjustedDamage;

			setAbsorptionAmount(getAbsorptionAmount() - absorbedAmount);

			if (absorbedAmount > 0 && absorbedAmount < 3.4028235E37F && source.getEntity() instanceof ServerPlayer pl)
				pl.awardStat(Stats.DAMAGE_DEALT_ABSORBED, Math.round(absorbedAmount * 10f));

			adjustedDamage = ForgeHooks.onLivingDamage(this, source, adjustedDamage);

			if (adjustedDamage != 0) {
				getCombatTracker().recordDamage(source, adjustedDamage);
				setHealth(getHealth() - adjustedDamage);
				setAbsorptionAmount(getAbsorptionAmount() - adjustedDamage);
				gameEvent(GameEvent.ENTITY_DAMAGE);
				onHurt(source, adjustedDamage);
			}
		}
	}

	protected void onAttack(Entity target) {}

	protected void onHurt(DamageSource source, float amount) {}

	@Override
	public void die(DamageSource source) {
		if (ForgeHooks.onLivingDeath(this, source))
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
					dropAllDeathLoot(source);
					createWitherRose(killer);
				}

				serverLevel.broadcastEntityEvent(this, (byte)3);
			}

			getCombatTracker().recheckStatus();
			setPose(Pose.DYING);
		}
	}

	@Override
	public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
		super.onSyncedDataUpdated(key);

		for (EntityDataHolder<?> dataHolder : this.dataParams) {
			if (dataHolder.checkSync(this, key))
				break;
		}
	}

	@Override
	public void aiStep() {
		super.aiStep();

		for (AoAEntityPart<?> part : getParts()) {
			part.updatePosition();
		}
	}

	@Override
	public boolean isMultipartEntity() {
		return getParts().length > 0;
	}

	@Override
	public AoAEntityPart<?>[] getParts() {
		return this.parts;
	}

	protected void setParts(AoAEntityPart<?>... parts) {
		if (this.parts.length > 0)
			throw new IllegalStateException("Cannot add more parts after having already done so!");

		this.parts = parts;

		setId(ENTITY_COUNTER.getAndAdd(getParts().length + 1) + 1);
	}

	@Override
	public boolean isFood(ItemStack stack) {
		return stack.getItem() == getTemptItem();
	}

	@Nullable
	protected Item getTemptItem() {
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

		baby.finalizeSpawn(level, level.getCurrentDifficultyAt(BlockPos.containing(baby.position())), MobSpawnType.BREEDING, null, null);
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.geoCache;
	}

	@Override
	public void setId(int id) {
		super.setId(id);

		int newId = id + 1;

		for (PartEntity<?> part : getParts()) {
			part.setId(newId++);
		}
	}
}
