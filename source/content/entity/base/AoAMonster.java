package net.tslat.aoa3.content.entity.base;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.stats.Stats;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.entity.PartEntity;
import net.tslat.aoa3.common.registration.AoAAttributes;
import net.tslat.aoa3.content.entity.brain.sensor.AggroBasedNearbyLivingEntitySensor;
import net.tslat.aoa3.content.entity.brain.sensor.AggroBasedNearbyPlayersSensor;
import net.tslat.aoa3.library.object.EntityDataHolder;
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
import net.tslat.smartbrainlib.api.core.sensor.ExtendedSensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.HurtBySensor;
import net.tslat.smartbrainlib.util.BrainUtils;
import net.tslat.smartbrainlib.util.RandomUtil;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.List;

public abstract class AoAMonster<T extends AoAMonster<T>> extends Monster implements GeoEntity, SmartBrainOwner<T> {
	public static final EntityDataHolder<Integer> ATTACK_STATE = EntityDataHolder.register(AoAMonster.class, EntityDataSerializers.INT, 0, monster -> monster.attackState, (monster, value) -> monster.attackState = value);
	public static final EntityDataHolder<Boolean> INVULNERABLE = EntityDataHolder.register(AoAMonster.class, EntityDataSerializers.BOOLEAN, false, Entity::isInvulnerable, AoAMonster::setInvulnerable);
	public static final EntityDataHolder<Boolean> IMMOBILE = EntityDataHolder.register(AoAMonster.class, EntityDataSerializers.BOOLEAN, false, monster -> monster.immobile, (monster, value) -> monster.immobile = value);

	private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);
	protected AoAEntityPart<?>[] parts = new AoAEntityPart[0];
	private EntityDataHolder<?>[] dataParams;

	protected boolean hasDrops = true;
	private int attackState = 0;
	private boolean immobile = false;

	protected AoAMonster(EntityType<? extends AoAMonster> entityType, Level level) {
		super(entityType, level);

		getNavigation().setCanFloat(true);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();

		this.dataParams = new EntityDataHolder<?>[] {ATTACK_STATE, INVULNERABLE, IMMOBILE};

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
	public int getAmbientSoundInterval() {
		return 240;
	}

	public final RandomUtil.EasyRandom rand() {
		return new RandomUtil.EasyRandom(getRandom());
	}

	@Override
	protected Brain.Provider<T> brainProvider() {
		return new SmartBrainProvider(this);
	}

	@Override
	public List<ExtendedSensor<T>> getSensors() {
		return ObjectArrayList.of(
				new AggroBasedNearbyPlayersSensor<T>(),
				new AggroBasedNearbyLivingEntitySensor<T>().setPredicate((target, entity) -> target instanceof OwnableEntity tamedEntity && tamedEntity.getOwnerUUID() != null).setScanRate(entity -> 40),
				new HurtBySensor<>());
	}

	@Override
	public BrainActivityGroup<T> getCoreTasks() {
		return BrainActivityGroup.coreTasks(
				new LookAtTarget<>(),
				new WalkOrRunToWalkTarget<>().startCondition(entity -> !IMMOBILE.get(this)),
				new FloatToSurfaceOfFluid<>());
	}

	@Override
	public BrainActivityGroup<T> getIdleTasks() {
		return BrainActivityGroup.idleTasks(
				new TargetOrRetaliate<>().useMemory(MemoryModuleType.NEAREST_VISIBLE_ATTACKABLE_PLAYER),
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

	public static AttributeSupplier.Builder getDefaultAttributes() {
		return Mob.createMobAttributes().add(Attributes.ATTACK_DAMAGE).add(Attributes.ATTACK_KNOCKBACK).add(AoAAttributes.AGGRO_RANGE.get());
	}

	@Override
	public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
		return true;
	}

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag dataTag) {
		this.hasDrops = reason != MobSpawnType.MOB_SUMMONED;
		this.xpReward = calculateKillXp();

		if (reason == MobSpawnType.SPAWNER)
			this.xpReward *= 0.5d;

		return super.finalizeSpawn(world, difficulty, reason, spawnData, dataTag);
	}

	public int calculateKillXp() {
		return !this.hasDrops ? 0 : (int)(5 + (getAttributeValue(Attributes.MAX_HEALTH) + getAttributeValue(Attributes.ARMOR) * 1.75f + getAttributeValue(Attributes.ARMOR_TOUGHNESS) * 1.5f) / 10f);
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState blockIn) {
		SoundEvent stepSound = getStepSound(pos, blockIn);

		if (stepSound != null)
			playSound(stepSound, 0.15F, 1.0F);
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

	@Override
	protected void customServerAiStep() {
		tickBrain((T)this);
	}

	@Nullable
	@Override
	public LivingEntity getTarget() {
		return BrainUtils.getTargetOfEntity(this, super.getTarget());
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
	public void setInvulnerable(boolean isInvulnerable) {
		if (!INVULNERABLE.is(this, isInvulnerable))
			INVULNERABLE.setRaw(this, isInvulnerable);

		super.setInvulnerable(isInvulnerable);
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
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);

		compound.putBoolean("DropsLoot", this.hasDrops);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);

		this.hasDrops = compound.getBoolean("DropsLoot");

		INVULNERABLE.set(this, isInvulnerable());
	}

	@Override
	public void aiStep() {
		super.aiStep();

		for (AoAEntityPart<?> part : getParts()) {
			part.updatePosition();
		}
	}

	@Override
	protected boolean shouldDropLoot() {
		return super.shouldDropLoot() && this.hasDrops;
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
	public void setId(int id) {
		super.setId(id);

		int newId = id + 1;

		for (PartEntity<?> part : getParts()) {
			part.setId(newId++);
		}
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.geoCache;
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {}
}
