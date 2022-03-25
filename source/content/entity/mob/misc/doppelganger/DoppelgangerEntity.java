package net.tslat.aoa3.content.entity.mob.misc.doppelganger;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Dynamic;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.memory.MemoryModuleType;
import net.minecraft.entity.ai.brain.schedule.Activity;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.entity.ai.brain.task.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShootableItem;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.tslat.aoa3.client.ClientOperations;
import net.tslat.aoa3.common.registration.AoAArmour;
import net.tslat.aoa3.common.registration.AoABrainSensors;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.content.entity.brain.SmartBrainHandler;
import net.tslat.aoa3.content.entity.brain.SmartBrainOwner;
import net.tslat.aoa3.content.entity.brain.task.*;
import net.tslat.aoa3.util.BrainUtils;
import org.apache.commons.lang3.tuple.Triple;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DoppelgangerEntity extends MonsterEntity implements SmartBrainOwner<DoppelgangerEntity>, ICrossbowUser {
	private static final ImmutableList<SensorType<? extends Sensor<? super DoppelgangerEntity>>> SENSORS = ImmutableList.of(SensorType.NEAREST_PLAYERS, SensorType.HURT_BY, SensorType.NEAREST_LIVING_ENTITIES, AoABrainSensors.INCOMING_PROJECTILES.get());
	private static final DataParameter<Boolean> LOADING_CROSSBOW = EntityDataManager.defineId(DoppelgangerEntity.class, DataSerializers.BOOLEAN);

	private SmartBrainHandler<DoppelgangerEntity> handler;

	public DoppelgangerEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}

	public DoppelgangerEntity(World world) {
		super(AoAEntities.Mobs.DOPPELGANGER.get(), world);
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
	public Brain.BrainCodec<?> brainProvider() {
		return getBrainHandler().getBrainCodec();
	}

	@Override
	public Brain<?> makeBrain(Dynamic<?> codecLoader) {
		if (getBrainHandler() == null)
			setBrainHandler(new SmartBrainHandler<DoppelgangerEntity>(this));

		return getBrainHandler().makeBrain(codecLoader);
	}

	@Override
	public Brain<?> getBrain() {
		return getBrainHandler().getBrain();
	}

	@Override
	public Triple<Integer, ImmutableList<? extends Task<? super DoppelgangerEntity>>, MemoryModuleType<?>> getCoreTasks() {
		return Triple.of(0,
				ImmutableList.of(
						new LookTask(60, 240),
						new WalkToTargetTask()),
				null);
	}

	@Override
	public Triple<Integer, ImmutableList<? extends Task<? super DoppelgangerEntity>>, MemoryModuleType<?>> getIdleTasks() {
		return Triple.of(10,
				ImmutableList.of(
						new FirstSuccessfulTask<DoppelgangerEntity>(ImmutableList.of(
								Pair.of(new RetaliateOrTargetTask<DoppelgangerEntity>(this), 1),
								Pair.of(new LookAtEntityTask(EntityType.PLAYER, 8), 1),
								Pair.of(new LookAtEntityTask(8), 1),
								Pair.of(new DummyTask(30, 60), 1))),
						new FirstShuffledTask<DoppelgangerEntity>(ImmutableList.of(
								Pair.of(new WalkRandomlyTask(1f), 1),
								Pair.of(new DummyTask(30, 60), 1)))),
				null);
	}

	@Override
	public Triple<Integer, ImmutableList<? extends Task<? super DoppelgangerEntity>>, MemoryModuleType<?>> getFightTasks() {
		return Triple.of(10,
				ImmutableList.of(
						new FindNewAttackTargetTask<>(target -> !target.isAlive() || target instanceof PlayerEntity && ((PlayerEntity)target).isCreative()),
						new CounterTargetWeaponTask(),
						new AttackTargetTask(6),
						new MoveToTargetTask(1.3f)),
				MemoryModuleType.ATTACK_TARGET);
	}

	@Override
	public Map<Activity, Triple<Integer, ImmutableList<? extends Task<? super DoppelgangerEntity>>, MemoryModuleType<?>>> getAdditionalTasks() {
		return ImmutableMap.of(
				Activity.AVOID,
				Triple.of(0, ImmutableList.of(
						new BlockIncomingProjectileTask(true),
						new ParryStunlockTask<>()
				), null));
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
	public ILivingEntityData finalizeSpawn(IServerWorld pLevel, DifficultyInstance difficulty, SpawnReason pReason, @Nullable ILivingEntityData pSpawnData, @Nullable CompoundNBT pDataTag) {
		ILivingEntityData data = super.finalizeSpawn(pLevel, difficulty, pReason, pSpawnData, pDataTag);

		populateDefaultEquipmentSlots(difficulty);
		populateDefaultEquipmentEnchantments(difficulty);

		return data;
	}

	@Override
	protected void populateDefaultEquipmentSlots(DifficultyInstance difficulty) {
		AoAArmour.ArmourSet armourSet = AoAArmour.ALACRITY_ARMOUR;

		if (difficulty.getDifficulty() == Difficulty.NORMAL || difficulty.getDifficulty() == Difficulty.HARD)
			armourSet = AoAArmour.KNIGHT_ARMOUR;

		setItemSlot(EquipmentSlotType.HEAD, new ItemStack(armourSet.helmet.get()));
		setItemSlot(EquipmentSlotType.CHEST, new ItemStack(armourSet.chestplate.get()));
		setItemSlot(EquipmentSlotType.LEGS, new ItemStack(armourSet.leggings.get()));
		setItemSlot(EquipmentSlotType.FEET, new ItemStack(armourSet.boots.get()));
	}

	@Override
	protected boolean shouldDespawnInPeaceful() {
		return false;
	}

	@Override
	public ITextComponent getDisplayName() {
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
		return this.isUsingItem() && !this.useItem.isEmpty() && useItem.getItem().getUseAnimation(this.useItem) == UseAction.BLOCK;
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
	public void shootCrossbowProjectile(LivingEntity target, ItemStack crossbow, ProjectileEntity bolt, float angle) {
		shootCrossbowProjectile(this, target, bolt, angle, 1.6f);
	}

	@Nullable
	@Override
	public LivingEntity getTarget() {
		return BrainUtils.getTargetOfEntity(this, super.getTarget());
	}

	@Override
	public boolean canFireProjectileWeapon(ShootableItem weapon) {
		return weapon instanceof CrossbowItem;
	}

	@Override
	public void onCrossbowAttackPerformed() {}

	@Override
	public void performRangedAttack(LivingEntity target, float distanceFactor) {
		performCrossbowAttack(this, 1.6f);
	}
}
