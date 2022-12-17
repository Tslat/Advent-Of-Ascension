package net.tslat.aoa3.content.entity.base;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
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
import net.tslat.aoa3.common.registration.AoAAttributes;
import net.tslat.aoa3.content.entity.brain.sensor.AggroBasedNearbyLivingEntitySensor;
import net.tslat.aoa3.content.entity.brain.sensor.AggroBasedNearbyPlayersSensor;
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
import net.tslat.smartbrainlib.util.RandomUtil;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.List;

public abstract class AoAMonster<T extends AoAMonster<T>> extends Monster implements GeoEntity, SmartBrainOwner<T> {
	protected static final EntityDataAccessor<Integer> ATTACK_STATE = SynchedEntityData.defineId(AoAMonster.class, EntityDataSerializers.INT);
	protected static final EntityDataAccessor<Boolean> INVULNERABLE = SynchedEntityData.defineId(AoAMonster.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Boolean> IMMOBILE = SynchedEntityData.defineId(AoAMonster.class, EntityDataSerializers.BOOLEAN);

	private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);
	protected boolean hasDrops = true;

	private RandomUtil.EasyRandom random;

	protected AoAMonster(EntityType<? extends AoAMonster> entityType, Level level) {
		super(entityType, level);

		getNavigation().setCanFloat(true);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();

		getEntityData().define(ATTACK_STATE, 0);
		getEntityData().define(INVULNERABLE, false);
		getEntityData().define(IMMOBILE, false);
	}

	@Override
	protected Brain.Provider<T> brainProvider() {
		return new SmartBrainProvider(this);
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.geoCache;
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {}

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
				new WalkOrRunToWalkTarget<>().startCondition(entity -> !getEntityData().get(IMMOBILE)),
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

	public static AttributeSupplier.Builder getDefaultAttributes() {
		return Mob.createMobAttributes().add(Attributes.ATTACK_DAMAGE).add(Attributes.ATTACK_KNOCKBACK).add(AoAAttributes.AGGRO_RANGE.get());
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
		if (!blockState.getMaterial().isLiquid()) {
			BlockState state = this.level.getBlockState(pos.above());
			SoundType blockSound = state.getBlock() == Blocks.SNOW ? state.getSoundType(this.level, pos, this) : blockState.getSoundType(this.level, pos, this);

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

	protected int getAttackSwingDuration() {
		return 6;
	}

	protected int getPreAttackTime() {
		return 0;
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
	public boolean doHurtTarget(Entity target) {
		if (super.doHurtTarget(target)) {
			onAttack(target);

			return true;
		}

		return false;
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (super.hurt(source, amount)) {
			onHurt(source, amount);

			return true;
		}

		return false;
	}

	protected void onAttack(Entity target) {}

	protected void onHurt(DamageSource source, float amount) {}

	public int getAttackState() {
		return getEntityData().get(ATTACK_STATE);
	}

	public void setAttackState(int state) {
		getEntityData().set(ATTACK_STATE, state);
	}

	protected void setImmobile(boolean immobile) {
		getEntityData().set(IMMOBILE, immobile);
	}

	@Override
	public void setInvulnerable(boolean isInvulnerable) {
		super.setInvulnerable(isInvulnerable);

		getEntityData().set(INVULNERABLE, isInvulnerable);
	}

	@Override
	public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
		super.onSyncedDataUpdated(key);

		if (key.equals(INVULNERABLE))
			setInvulnerable(getEntityData().get(INVULNERABLE));
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);

		setInvulnerable(isInvulnerable());
		compound.putBoolean("DropsLoot", this.hasDrops);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);

		this.hasDrops = compound.getBoolean("DropsLoot");
	}

	@Override
	protected void customServerAiStep() {
		super.customServerAiStep();

		tickBrain((T)this);
	}

	@Override
	protected boolean shouldDropLoot() {
		return super.shouldDropLoot() && this.hasDrops;
	}

	@Override
	public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
		return true;
	}

	protected RandomUtil.EasyRandom rand() {
		if (this.random == null)
			this.random = new RandomUtil.EasyRandom();

		return this.random;
	}
}
