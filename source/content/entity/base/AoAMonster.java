package net.tslat.aoa3.content.entity.base;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.neoforge.common.CommonHooks;
import net.tslat.aoa3.common.registration.AoAAttributes;
import net.tslat.aoa3.content.entity.brain.sensor.AggroBasedNearbyLivingEntitySensor;
import net.tslat.aoa3.content.entity.brain.sensor.AggroBasedNearbyPlayersSensor;
import net.tslat.aoa3.library.builder.MultipartBuilder;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.smartbrainlib.api.SmartBrainOwner;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.SmartBrainProvider;
import net.tslat.smartbrainlib.api.core.behaviour.OneRandomBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.custom.look.LookAtTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.Idle;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.FloatToSurfaceOfFluid;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.WalkOrRunToWalkTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetRandomWalkTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.TargetOrRetaliate;
import net.tslat.smartbrainlib.api.core.navigation.MultiFluidSmoothGroundNavigation;
import net.tslat.smartbrainlib.api.core.sensor.ExtendedSensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.HurtBySensor;
import net.tslat.smartbrainlib.util.BrainUtils;
import net.tslat.tme.api.object.EasyRandom;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;

public abstract class AoAMonster<T extends AoAMonster<T>> extends Monster implements GeoEntity, SmartBrainOwner<T>, AoAMultipartEntity<T> {
	public static final EntityDataAccessor<Integer> ATTACK_STATE = makeSynchedData(AoAMonster.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Boolean> INVULNERABLE = makeSynchedData(AoAMonster.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> IMMOBILE = makeSynchedData(AoAMonster.class, EntityDataSerializers.BOOLEAN);

	private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);
	private AoAEntityPart<T>[] parts = new AoAEntityPart[0];

	protected boolean hasDrops = true;

	protected AoAMonster(EntityType<? extends AoAMonster> entityType, Level level) {
		super(entityType, level);

        this.moveControl = createMoveControl();
		getNavigation().setCanFloat(true);
		registerParts(ENTITY_COUNTER, this::setId, parts -> this.parts = parts);
	}

	@Nullable
	@Override
	public MultipartBuilder<? extends T> definePartEntities() {
		return null;
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);

		builder.define(ATTACK_STATE, 0);
		builder.define(INVULNERABLE, false);
		builder.define(IMMOBILE, false);
	}

    protected MoveControl createMoveControl() {
        return this.moveControl;
    }

	@Override
	protected PathNavigation createNavigation(Level level) {
		return new MultiFluidSmoothGroundNavigation(this, level);
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
	protected Brain.Provider<T> brainProvider() {
		return new SmartBrainProvider(this);
	}

	@Override
	public List<ExtendedSensor<? extends T>> getSensors() {
		return ObjectArrayList.of(
				new AggroBasedNearbyPlayersSensor<T>(),
				new AggroBasedNearbyLivingEntitySensor<T>().setPredicate((target, entity) -> target instanceof OwnableEntity tamedEntity && tamedEntity.getOwnerUUID() != null).setScanRate(entity -> 40),
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
				new TargetOrRetaliate<>()
						.useMemory(MemoryModuleType.NEAREST_VISIBLE_ATTACKABLE_PLAYER)
						.attackablePredicate(target -> DamageUtil.isAttackable(target) && EntityUtil.areProbablyEnemies(target, this, true)),
				new OneRandomBehaviour<>(
						new SetRandomWalkTarget<>().speedModifier(0.9f),
						new Idle<>().runFor(entity -> entity.getRandom().nextInt(30, 60))));
	}

	protected int getAttackSwingDuration() {
		return 6;
	}

	protected int getPreAttackTime() {
		return 0;
	}

	@Override
	public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
		return true;
	}

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
		this.hasDrops = spawnType != MobSpawnType.MOB_SUMMONED;
		this.xpReward = calculateKillXp();

		if (spawnType == MobSpawnType.SPAWNER)
			this.xpReward /= 2;

		AoAAttributes.addSpawnVarianceHealthMod(this, difficulty.getSpecialMultiplier());
		AoAAttributes.addSpawnVarianceSpeedMod(this, difficulty.getSpecialMultiplier());

		return super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
	}

	public int calculateKillXp() {
		if (!this.hasDrops)
			return 0;

		return Mth.floor(5 + (getAttributeValue(Attributes.MAX_HEALTH) + getAttributeValue(Attributes.ARMOR) * 1.75f + getAttributeValue(Attributes.ARMOR_TOUGHNESS) * 1.5f) / 10f);
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

	private void playStepSounds(SoundEvent stepSound, @Nullable SoundEvent stepSoundOverlay) {
		float stepWeight = getStepWeight() - 1;

		playSound(stepSound, 5 * 0.15f + stepWeight * 0.15f, 1 - stepWeight * 0.1f);

		if (stepSoundOverlay != null)
			playSound(stepSoundOverlay, 5 * 0.15f + stepWeight * 0.15f, 1 - stepWeight * 0.1f);
	}

	@Override
	public int getCurrentSwingDuration() {
		int time = getAttackSwingDuration();

		if (MobEffectUtil.hasDigSpeed(this))
			time -= 1 + MobEffectUtil.getDigSpeedAmplification(this);

		if (hasEffect(MobEffects.DIG_SLOWDOWN))
			time += (1 + getEffect(MobEffects.DIG_SLOWDOWN).getAmplifier()) * 2;

		return time;
	}

	public int getAttackState() {
		return getSynchedData(ATTACK_STATE);
	}

	public void setAttackState(int state) {
		setSynchedData(ATTACK_STATE, state);
	}

	public boolean isAttackState(int state) {
		return getAttackState() == state;
	}

	@Override
	protected void customServerAiStep() {
		tickBrain((T)this);
	}

	@Nullable
	@Override
	public LivingEntity getTarget() {
		return BrainUtils.getTargetOfEntity(this, super.getTarget());
	}

	public void setImmobile(boolean immobile) {
		setSynchedData(IMMOBILE, immobile);

		if (immobile)
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
		float damage = (float)getAttributeValue(Attributes.ATTACK_DAMAGE);
		DamageSource damageSource = damageSources().mobAttack(this);

		if (level() instanceof ServerLevel serverLevel)
			damage = EnchantmentHelper.modifyDamage(serverLevel, getWeaponItem(), target, damageSource, damage);

		if (target.hurt(damageSource, damage)) {
			if (target instanceof LivingEntity livingTarget) {
				float knockback = getKnockback(target, damageSource);

				if (knockback > 0) {
					livingTarget.knockback(knockback * 0.5f, Mth.sin(getYRot() * Mth.DEG_TO_RAD), -Mth.cos(getYRot() * Mth.DEG_TO_RAD));
					setDeltaMovement(getDeltaMovement().multiply(0.6, 1.0, 0.6));
				}
			}

			if (level() instanceof ServerLevel serverLevel)
				EnchantmentHelper.doPostAttackEffects(serverLevel, target, damageSource);

			setLastHurtMob(target);
			playAttackSound();
			onAttack(target);

			return true;
		}

		return false;
	}

	protected DamageSource getAttackDamageSource(Entity target) {
		return damageSources().mobAttack(this);
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

		compound.putBoolean("DropsLoot", this.hasDrops);
		saveMultiparts(compound);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);

		this.hasDrops = compound.getBoolean("DropsLoot");

		loadMultiparts(compound);
		setSynchedData(INVULNERABLE, isInvulnerable());
	}

	@Override
	public void setInvulnerable(boolean isInvulnerable) {
		super.setInvulnerable(isInvulnerable);
		setSynchedData(INVULNERABLE, isInvulnerable);
	}

	@Override
	public void tick() {
		super.tick();
		updateMultipartPositions();
	}

	@Override
	protected boolean shouldDropLoot() {
		return super.shouldDropLoot() && this.hasDrops;
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
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.geoCache;
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {}
}
