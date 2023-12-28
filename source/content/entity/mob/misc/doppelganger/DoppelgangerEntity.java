/*
package net.tslat.aoa3.content.entity.mob.misc.doppelganger;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Dynamic;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.*;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.monster.CrossbowAttackMob;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.schedule.Activity;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.tslat.aoa3.client.ClientOperations;
import net.tslat.aoa3.common.registration.entity.AoABrainSensors;
import net.tslat.aoa3.common.registration.entity.AoAMobs;
import net.tslat.aoa3.common.registration.item.AoAArmour;
import net.tslat.aoa3.content.entity.brain.BrainActivityGroup;
import net.tslat.aoa3.content.entity.brain.SmartBrainHandler;
import net.tslat.aoa3.content.entity.brain.task.BlockIncomingProjectileTask;
import net.tslat.aoa3.content.entity.brain.task.CounterTargetWeaponTask;
import net.tslat.aoa3.content.entity.brain.task.ParryStunlockTask;
import net.tslat.aoa3.content.entity.brain.task.RetaliateOrTargetTask;
import net.tslat.aoa3.content.entity.brain.task.wrapper.FirstSuccessfulTask;
import net.tslat.aoa3.util.BrainUtils;
import net.tslat.smartbrainlib.api.SmartBrainOwner;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class DoppelgangerEntity extends Monster implements SmartBrainOwner<DoppelgangerEntity>, CrossbowAttackMob {
	private static final ImmutableList<SensorType<? extends Sensor<? super DoppelgangerEntity>>> SENSORS = ImmutableList.of(SensorType.NEAREST_PLAYERS, SensorType.HURT_BY, SensorType.NEAREST_LIVING_ENTITIES, AoABrainSensors.INCOMING_PROJECTILES.get());
	private static final EntityDataAccessor<Boolean> LOADING_CROSSBOW = SynchedEntityData.defineId(DoppelgangerEntity.class, EntityDataSerializers.BOOLEAN);

	private SmartBrainHandler<DoppelgangerEntity> handler;

	public DoppelgangerEntity(EntityType<? extends DoppelgangerEntity> entityType, Level world) {
		super(entityType, world);
	}

	public DoppelgangerEntity(Level world) {
		super(AoAMobs.DOPPELGANGER.get(), world);
	}

	@Nullable
	@Override
	public SmartBrainHandler<DoppelgangerEntity> getBrainHandler() {
		return this.handler;
	}

	@Override
	public void setBrainHandler(SmartBrainHandler<DoppelgangerEntity> handler) {
		this.handler = handler;
	}

	@Override
	public ImmutableList<SensorType<? extends Sensor<? super DoppelgangerEntity>>> getSensors() {
		return SENSORS;
	}

	@Override
	public Brain.Provider<DoppelgangerEntity> brainProvider() {
		return getBrainHandler().getBrainCodec();
	}

	@Override
	public Brain<?> makeBrain(Dynamic<?> codecLoader) {
		if (getBrainHandler() == null)
			setBrainHandler(new SmartBrainHandler<>(this));

		return getBrainHandler().makeBrain(codecLoader);
	}

	@Override
	public Brain<?> getBrain() {
		return getBrainHandler().getBrain();
	}

	@Override
	public BrainActivityGroup<DoppelgangerEntity> getCoreTasks() {
		return BrainActivityGroup.coreTasks(
				new LookAtTargetSink(60, 240),
				new MoveToTargetSink());
	}

	@Override
	public BrainActivityGroup<DoppelgangerEntity> getIdleTasks() {
		return BrainActivityGroup.idleTasks(
				new FirstSuccessfulTask<>(ImmutableList.of(
						Pair.of(new RetaliateOrTargetTask<>(this), 1),
						Pair.of(new SetEntityLookTarget(EntityType.PLAYER, 8), 1),
						Pair.of(new SetEntityLookTarget(8), 1),
						Pair.of(new DoNothing(30, 60), 1))),
				new RunOne<>(ImmutableList.of(
						Pair.of(new RandomStroll(1f), 1),
						Pair.of(new DoNothing(30, 60), 1))));
	}

	@Override
	public BrainActivityGroup<DoppelgangerEntity> getFightTasks() {
		return BrainActivityGroup.fightTasks(
				new StopAttackingIfTargetInvalid<>(target -> !target.isAlive() || target instanceof Player && ((Player)target).isCreative()),
				new CounterTargetWeaponTask(),
				new MeleeAttack(6),
				new SetWalkTargetFromAttackTargetIfTargetOutOfReach(1.3f));
	}

	@Override
	public Map<Activity, BrainActivityGroup<DoppelgangerEntity>> getAdditionalTasks() {
		return ImmutableMap.of(
				Activity.AVOID,
				new BrainActivityGroup<DoppelgangerEntity>(Activity.AVOID)
						.behaviours(
								new BlockIncomingProjectileTask(true),
								new ParryStunlockTask<>()));
	}

	@Override
	public Set<Activity> getAlwaysRunningActivities() {
		return ImmutableSet.of(Activity.CORE, Activity.AVOID);
	}

	@Override
	public List<Activity> getActivityPriorities() {
		return ImmutableList.of(Activity.FIGHT, Activity.IDLE);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(LOADING_CROSSBOW, false);
	}

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance difficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
		SpawnGroupData data = super.finalizeSpawn(pLevel, difficulty, pReason, pSpawnData, pDataTag);

		populateDefaultEquipmentSlots(random, difficulty);
		populateDefaultEquipmentEnchantments(random, difficulty);

		return data;
	}

	@Override
	protected void populateDefaultEquipmentSlots(RandomSource rand, DifficultyInstance difficulty) {
		AoAArmour.ArmourSet armourSet = AoAArmour.ALACRITY_ARMOUR;

		if (difficulty.getDifficulty() == Difficulty.NORMAL || difficulty.getDifficulty() == Difficulty.HARD)
			armourSet = AoAArmour.KNIGHT_ARMOUR;

		setItemSlot(EquipmentSlot.HEAD, new ItemStack(armourSet.helmet.get()));
		setItemSlot(EquipmentSlot.CHEST, new ItemStack(armourSet.chestplate.get()));
		setItemSlot(EquipmentSlot.LEGS, new ItemStack(armourSet.leggings.get()));
		setItemSlot(EquipmentSlot.FEET, new ItemStack(armourSet.boots.get()));
	}

	@Override
	protected boolean shouldDespawnInPeaceful() {
		return false;
	}

	@Override
	public Component getDisplayName() {
		if (this.level.isClientSide())
			return ClientOperations.getPlayerName();

		return super.getDisplayName();
	}

	@Override
	protected void customServerAiStep() {
		handler.tick();
		super.customServerAiStep();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSource) {
		if (damageSource == DamageSource.ON_FIRE) {
			return SoundEvents.PLAYER_HURT_ON_FIRE;
		}
		else if (damageSource == DamageSource.DROWN) {
			return SoundEvents.PLAYER_HURT_DROWN;
		}
		else {
			return damageSource == DamageSource.SWEET_BERRY_BUSH ? SoundEvents.PLAYER_HURT_SWEET_BERRY_BUSH : SoundEvents.PLAYER_HURT;
		}
	}

	@Override
	public boolean isBlocking() {
		return this.isUsingItem() && !this.useItem.isEmpty() && useItem.getItem().getUseAnimation(this.useItem) == UseAnim.BLOCK;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.PLAYER_DEATH;
	}

	@Override
	public void setChargingCrossbow(boolean pIsCharging) {
		this.entityData.get(LOADING_CROSSBOW);
	}

	@Override
	public void shootCrossbowProjectile(LivingEntity target, ItemStack crossbow, Projectile bolt, float angle) {
		shootCrossbowProjectile(this, target, bolt, angle, 1.6f);
	}

	@Nullable
	@Override
	public LivingEntity getTarget() {
		return BrainUtils.getTargetOfEntity(this, super.getTarget());
	}

	@Override
	public boolean canFireProjectileWeapon(ProjectileWeaponItem weapon) {
		return weapon instanceof CrossbowItem;
	}

	@Override
	public void onCrossbowAttackPerformed() {}

	@Override
	public void performRangedAttack(LivingEntity target, float distanceFactor) {
		performCrossbowAttack(this, 1.6f);
	}
}
*/
