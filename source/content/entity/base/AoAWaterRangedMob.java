package net.tslat.aoa3.content.entity.base;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.AoAAttributes;
import net.tslat.aoa3.content.entity.ai.movehelper.RoamingSwimmingMovementController;
import net.tslat.aoa3.content.entity.projectile.mob.BaseMobProjectile;
import net.tslat.aoa3.util.DamageUtil;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;

public abstract class AoAWaterRangedMob extends WaterAnimal implements RangedAttackMob, AoARangedAttacker, Enemy, IAnimatable {
	protected static final EntityDataAccessor<Boolean> INVULNERABLE = SynchedEntityData.defineId(AoAWaterRangedMob.class, EntityDataSerializers.BOOLEAN);

	private final AnimationFactory animationFactory = new AnimationFactory(this);

	protected AoAWaterRangedMob(EntityType<? extends WaterAnimal> entityType, Level world) {
		super(entityType, world);

		this.moveControl = new RoamingSwimmingMovementController(this);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(2, new RangedAttackGoal(this, 1.0d, 20, 40, 32));
		goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6));
		goalSelector.addGoal(6, new RandomSwimmingGoal(this, 1, 30));
		targetSelector.addGoal(1, new HurtByTargetGoal(this));
		targetSelector.addGoal(2, new NearestAttackableTargetGoal<Player>(this, Player.class, true));
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		getEntityData().define(INVULNERABLE, false);
	}

	@Override
	protected PathNavigation createNavigation(Level world) {
		return new WaterBoundPathNavigation(this, world);
	}

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag dataTag) {
		xpReward = reason == MobSpawnType.MOB_SUMMONED ? 0 : (int)(5 + (getAttributeValue(Attributes.MAX_HEALTH) + getAttributeValue(Attributes.ARMOR) * 1.75f + getAttributeValue(AoAAttributes.RANGED_ATTACK_DAMAGE.get()) * 2) / 10f);

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
	protected void playStepSound(BlockPos pos, BlockState groundBlock) {}

	@Nullable
	protected abstract SoundEvent getShootSound();

	protected abstract BaseMobProjectile getNewProjectileInstance();

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
	public void doProjectileEntityImpact(BaseMobProjectile projectile, Entity target) {
		boolean success = switch (projectile.getProjectileType()) {
			case MAGIC -> DamageUtil.dealMagicDamage(projectile, this, target, (float)getAttributeValue(AoAAttributes.RANGED_ATTACK_DAMAGE.get()), false);
			case GUN -> DamageUtil.dealGunDamage(target, this, projectile, (float)getAttributeValue(AoAAttributes.RANGED_ATTACK_DAMAGE.get()));
			case PHYSICAL -> DamageUtil.dealRangedDamage(target, this, projectile, (float)getAttributeValue(AoAAttributes.RANGED_ATTACK_DAMAGE.get()));
			default -> target.hurt(DamageSource.indirectMobAttack(projectile, this), (float)getAttributeValue(AoAAttributes.RANGED_ATTACK_DAMAGE.get()));
		};

		if (success)
			doProjectileImpactEffect(projectile, target);
	}

	@Override
	public void doProjectileBlockImpact(BaseMobProjectile projectile, BlockState blockHit, BlockPos pos, Direction sideHit) {}

	@Override
	public void doProjectileImpactEffect(BaseMobProjectile projectile, Entity target) {}

	@Override
	public void performRangedAttack(LivingEntity target, float distanceFactor) {
		BaseMobProjectile projectile = getNewProjectileInstance();

		double distanceFactorX = target.getX() - getX();
		double distanceFactorY = target.getBoundingBox().minY + (double)(target.getBbHeight() / 3.0f) - projectile.getY();
		double distanceFactorZ = target.getZ() - getZ();
		double hyp = Math.sqrt((distanceFactorX * distanceFactorX + distanceFactorZ * distanceFactorZ)) + 0.2D;

		if (getShootSound() != null)
			level.playSound(null, getX(), getY(), getZ(), getShootSound(), SoundSource.HOSTILE, 1.0f, 1.0f);

		projectile.shoot(distanceFactorX, distanceFactorY + hyp * 0.20000000298023224D, distanceFactorZ, 1.6f, (float)(4 - level.getDifficulty().getId()));
		level.addFreshEntity(projectile);
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
	public void registerControllers(AnimationData data) {}

	@Override
	public AnimationFactory getFactory() {
		return this.animationFactory;
	}
}
