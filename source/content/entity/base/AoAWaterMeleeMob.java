package net.tslat.aoa3.content.entity.base;

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
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.content.entity.ai.mob.TelegraphedMeleeAttackGoal;
import net.tslat.aoa3.content.entity.ai.movehelper.RoamingSwimmingMovementController;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;
import java.util.HashMap;

public abstract class AoAWaterMeleeMob extends WaterAnimal implements Enemy, IAnimatable {
	private static final EntityDataAccessor<Integer> ATTACK_STATE = SynchedEntityData.defineId(AoAWaterMeleeMob.class, EntityDataSerializers.INT);
	protected static final EntityDataAccessor<Boolean> INVULNERABLE = SynchedEntityData.defineId(AoAWaterMeleeMob.class, EntityDataSerializers.BOOLEAN);

	private final AnimationFactory animationFactory = new AnimationFactory(this);
	private final HashMap<String, Integer> animationStates = new HashMap<>(1);

	protected AoAWaterMeleeMob(EntityType<? extends WaterAnimal> entityType, Level world) {
		super(entityType, world);

		this.moveControl = new RoamingSwimmingMovementController(this);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(2, new TelegraphedMeleeAttackGoal<AoAWaterMeleeMob>(this).preAttackTime(getPreAttackTime()).attackInterval(getCurrentSwingDuration()));
		goalSelector.addGoal(5, new RandomSwimmingGoal(this, 1, 30));
		goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6));
		goalSelector.addGoal(7, new RandomLookAroundGoal(this));
		targetSelector.addGoal(1, new HurtByTargetGoal(this));
		targetSelector.addGoal(2, new NearestAttackableTargetGoal<Player>(this, Player.class, true));
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		getEntityData().define(ATTACK_STATE, 0);
		getEntityData().define(INVULNERABLE, false);
	}

	@Override
	protected PathNavigation createNavigation(Level world) {
		return new WaterBoundPathNavigation(this, world);
	}

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag dataTag) {
		xpReward = reason == MobSpawnType.MOB_SUMMONED ? 0 : (int)(5 + (getAttributeValue(Attributes.MAX_HEALTH) + getAttributeValue(Attributes.ARMOR) * 1.75f + getAttributeValue(Attributes.ATTACK_DAMAGE) * 2) / 10f);

		return super.finalizeSpawn(world, difficulty, reason, spawnData, dataTag);
	}

	@Override
	protected abstract float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn);

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

	@Override
	protected void playStepSound(BlockPos pos, BlockState blockIn) {}

	protected int getAttackSwingDuration() {
		return 6;
	}

	protected int getPreAttackTime() {
		return 0;
	}

	protected void onAttack(Entity target) {}

	protected void onHit(DamageSource source, float amount) {}

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
	public void load(CompoundTag compound) {
		super.load(compound);

		setInvulnerable(isInvulnerable());
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
			onHit(source, amount);

			return true;
		}

		return false;
	}

	@Override
	protected boolean shouldDropLoot() {
		return super.shouldDropLoot() && xpReward > 0;
	}

	@Override
	public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
		return true;
	}

	@Override
	public void aiStep() {
		updateSwingTime();

		super.aiStep();
	}

	@Override
	public void travel(Vec3 motion) {
		if (isEffectiveAi() && isInWater()) {
			move(MoverType.SELF, getDeltaMovement());
			setDeltaMovement(getDeltaMovement().scale(0.9D));

			if (getTarget() == null)
				setDeltaMovement(getDeltaMovement().add(0.0D, -0.001D, 0.0D));
		}
		else {
			super.travel(motion);
		}
	}

	@Override
	protected boolean shouldDespawnInPeaceful() {
		return true;
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
	public AnimationFactory getFactory() {
		return this.animationFactory;
	}

	@Override
	public void registerControllers(AnimationData animationData) {}
}
