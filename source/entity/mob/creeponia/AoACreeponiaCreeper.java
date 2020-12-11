package net.tslat.aoa3.entity.mob.creeponia;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.OcelotEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.util.WorldUtil;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Random;

public abstract class AoACreeponiaCreeper extends AoAMeleeMob {
	private static final DataParameter<Integer> STATE = EntityDataManager.createKey(AoACreeponiaCreeper.class, DataSerializers.VARINT);
	private static final DataParameter<Boolean> POWERED = EntityDataManager.createKey(AoACreeponiaCreeper.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> IGNITED = EntityDataManager.createKey(AoACreeponiaCreeper.class, DataSerializers.BOOLEAN);
	protected int fuseTime = 30;
	protected int lastActiveTime;
	protected int timeSinceIgnited;
	private float swimAnimation;
	private float lastSwimAnimation;
	private int explosionRadius = 3;

	public AoACreeponiaCreeper(EntityType<? extends AoACreeponiaCreeper> entityType, World world) {
		super(entityType, world);

		experienceValue = (int)(getBaseMaxHealth() / 10d);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(1, new SwimGoal(this));
		goalSelector.addGoal(2, new CustomCreeperSwellGoal(this));
		goalSelector.addGoal(3, new AvoidEntityGoal<>(this, OcelotEntity.class, 6.0F, 1.0D, 1.2D));
		goalSelector.addGoal(3, new AvoidEntityGoal<>(this, CatEntity.class, 6.0F, 1.0D, 1.2D));
		goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
		goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.8D));
		goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		goalSelector.addGoal(6, new LookRandomlyGoal(this));
		targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, TameableEntity.class, 10, true, false, entity -> entity instanceof TameableEntity && ((TameableEntity)entity).isTamed()));
		targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
		targetSelector.addGoal(3, new HurtByTargetGoal(this));
	}

	@Override
	protected void registerAttributes() {
		super.registerAttributes();

		getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(getBaseMaxHealth() * (AoAConfig.COMMON.hardcoreMode.get() ? 2f : 1f));
		getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(getBaseKnockbackResistance());
		getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(getBaseMovementSpeed());
	}

	protected void registerData() {
		super.registerData();

		dataManager.register(STATE, -1);
		dataManager.register(POWERED, false);
		dataManager.register(IGNITED, false);
	}

	protected abstract double getBaseKnockbackResistance();

	protected abstract double getBaseMaxHealth();

	protected abstract double getBaseMovementSpeed();

	public abstract float getExplosionStrength();

	protected int getMaxSpawnHeight() {
		return -1;
	}

	protected void onAttack(Entity target) {}

	protected void onHit(DamageSource source, float amount) {}

	public static boolean meetsSpawnConditions(EntityType<? extends MonsterEntity> type, IWorld world, SpawnReason reason, BlockPos pos, Random random) {
		return world.getDifficulty() != Difficulty.PEACEFUL;
	}

	@Override
	public boolean canSpawn(IWorld world, SpawnReason reason) {
		return isValidLightLevel(reason) && canSpawnAt(reason, world.getBlockState(getPosition().down()));
	}

	protected boolean canSpawnAt(SpawnReason reason, BlockState blockState) {
		if (getMaxSpawnHeight() >= 0 && getPosY() > getMaxSpawnHeight())
			return false;

		return blockState.canEntitySpawn(world, getPosition(), getType());
	}

	private boolean isValidLightLevel(SpawnReason reason) {
		return WorldUtil.getLightLevel(world, getPosition(), true, false) <= RandomUtil.randomNumberUpTo(8);
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.ENTITY_CREEPER_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_CREEPER_DEATH;
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState block) {}

	public int getCreeperState() {
		return this.dataManager.get(STATE);
	}

	public void setCreeperState(int state) {
		this.dataManager.set(STATE, state);
	}

	@Override
	public void onStruckByLightning(LightningBoltEntity lightningBolt) {
		super.onStruckByLightning(lightningBolt);

		dataManager.set(POWERED, true);
	}

	@Override
	protected boolean processInteract(PlayerEntity player, Hand hand) {
		ItemStack itemstack = player.getHeldItem(hand);

		if (itemstack.getItem() == Items.FLINT_AND_STEEL) {
			world.playSound(player, getPosX(), getPosY(), getPosZ(), SoundEvents.ITEM_FLINTANDSTEEL_USE, getSoundCategory(), 1.0F, rand.nextFloat() * 0.4F + 0.8F);

			if (!world.isRemote) {
				ignite();
				itemstack.damageItem(1, player, (p_213625_1_) -> {
					p_213625_1_.sendBreakAnimation(hand);
				});
			}

			return true;
		}
		else {
			return super.processInteract(player, hand);
		}
	}

	@Override
	public void tick() {
		if (isAlive()) {
			lastActiveTime = timeSinceIgnited;

			if (hasIgnited())
				setCreeperState(1);

			int creeperState = getCreeperState();

			if (creeperState > 0 && timeSinceIgnited == 0)
				playSound(SoundEvents.ENTITY_CREEPER_PRIMED, 1.0F, 0.5F);

			timeSinceIgnited += creeperState;

			if (timeSinceIgnited < 0)
				timeSinceIgnited = 0;

			if (timeSinceIgnited >= fuseTime) {
				timeSinceIgnited = fuseTime;

				explode();
			}
		}

		if (!net.minecraftforge.common.ForgeHooks.onLivingUpdate(this)) {
			if (!world.isRemote)
				setFlag(6, isGlowing());

			baseTick();

			lastSwimAnimation = swimAnimation;

			if (isActualySwimming()) {
				swimAnimation = Math.min(1.0F, swimAnimation + 0.09F);
			}
			else {
				swimAnimation = Math.max(0.0F, swimAnimation - 0.09F);
			}

			if (!world.isRemote) {
				int arrowCount = getArrowCountInEntity();

				if (arrowCount > 0) {
					if (arrowHitTimer <= 0)
						arrowHitTimer = 20 * (30 - arrowCount);

					--arrowHitTimer;

					if (arrowHitTimer <= 0)
						setArrowCountInEntity(arrowCount - 1);
				}

				int beeStings = getBeeStingCount();

				if (beeStings > 0) {
					if (beeStingRemovalCooldown <= 0)
						beeStingRemovalCooldown = 20 * (30 - beeStings);

					--beeStingRemovalCooldown;

					if (beeStingRemovalCooldown <= 0)
						setBeeStingCount(beeStings - 1);
				}

				if (ticksExisted % 20 == 0)
					getCombatTracker().reset();

				if (!glowing) {
					boolean glowing = isPotionActive(Effects.GLOWING);

					if (getFlag(6) != glowing)
						setFlag(6, glowing);
				}
			}

			livingTick();

			double diffX = getPosX() - prevPosX;
			double diffZ = getPosZ() - prevPosZ;
			float movementDiff = (float)(diffX * diffX + diffZ * diffZ);
			float direction = renderYawOffset;
			float distanceMoved = 0.0F;
			prevOnGroundSpeedFactor = onGroundSpeedFactor;
			float moveSpeedBase = 0.0F;

			if (movementDiff > 0.0025000002F) {
				moveSpeedBase = 1.0F;
				distanceMoved = (float)Math.sqrt(movementDiff) * 3.0F;
				float f = (float)MathHelper.atan2(diffZ, diffX) * (180F / (float)Math.PI) - 90.0F;
				float f1 = MathHelper.abs(MathHelper.wrapDegrees(rotationYaw) - f);

				if (95.0F < f1 && f1 < 265.0F) {
					direction = f - 180.0F;
				}
				else {
					direction = f;
				}
			}

			if (swingProgress > 0.0F)
				direction = rotationYaw;

			if (!onGround)
				moveSpeedBase = 0.0F;

			onGroundSpeedFactor += (moveSpeedBase - onGroundSpeedFactor) * 0.3F;

			world.getProfiler().startSection("headTurn");

			distanceMoved = updateDistance(direction, distanceMoved);

			world.getProfiler().endSection();
			world.getProfiler().startSection("rangeChecks");

			while (rotationYaw - prevRotationYaw < -180.0F) {
				prevRotationYaw -= 360.0F;
			}

			while (rotationYaw - prevRotationYaw >= 180.0F) {
				prevRotationYaw += 360.0F;
			}

			while (renderYawOffset - prevRenderYawOffset < -180.0F) {
				prevRenderYawOffset -= 360.0F;
			}

			while (renderYawOffset - prevRenderYawOffset >= 180.0F) {
				prevRenderYawOffset += 360.0F;
			}

			while (rotationPitch - prevRotationPitch < -180.0F) {
				prevRotationPitch -= 360.0F;
			}

			while (rotationPitch - prevRotationPitch >= 180.0F) {
				prevRotationPitch += 360.0F;
			}

			while (rotationYawHead - prevRotationYawHead < -180.0F) {
				prevRotationYawHead -= 360.0F;
			}

			while (rotationYawHead - prevRotationYawHead >= 180.0F) {
				prevRotationYawHead += 360.0F;
			}

			world.getProfiler().endSection();

			movedDistance += distanceMoved;

			if (isElytraFlying()) {
				++ticksElytraFlying;
			}
			else {
				ticksElytraFlying = 0;
			}

			if (isSleeping())
				rotationPitch = 0.0F;
		}

		if (!world.isRemote) {
			updateLeashedState();

			if (ticksExisted % 5 == 0)
				updateMovementGoalFlags();
		}
	}

	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);

		if (this.dataManager.get(POWERED))
			compound.putBoolean("powered", true);

		compound.putShort("Fuse", (short)this.fuseTime);
		compound.putByte("ExplosionRadius", (byte)explosionRadius);
		compound.putBoolean("ignited", hasIgnited());
	}

	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);

		dataManager.set(POWERED, compound.getBoolean("powered"));

		if (compound.contains("Fuse", 99))
			fuseTime = compound.getShort("Fuse");

		if (compound.contains("ExplosionRadius", 99))
			explosionRadius = compound.getByte("ExplosionRadius");

		if (compound.getBoolean("ignited"))
			ignite();
	}

	@Override
	public boolean onLivingFall(float distance, float damageMultiplier) {
		boolean success = super.onLivingFall(distance, damageMultiplier);

		timeSinceIgnited = (int)((float)timeSinceIgnited + distance * 1.5F);

		if (timeSinceIgnited > fuseTime - 5)
			timeSinceIgnited = fuseTime - 5;

		return success;
	}

	@OnlyIn(Dist.CLIENT)
	public float getCreeperFlashIntensity(float partialTicks) {
		return MathHelper.lerp(partialTicks, (float)lastActiveTime, (float)timeSinceIgnited) / (float)(fuseTime - 2);
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public float getSwimAnimation(float partialTicks) {
		return MathHelper.lerp(partialTicks, this.lastSwimAnimation, this.swimAnimation);
	}

	public boolean hasIgnited() {
		return dataManager.get(IGNITED);
	}

	public void ignite() {
		dataManager.set(IGNITED, true);
	}

	public boolean isCharged() {
		return dataManager.get(POWERED);
	}

	protected void explode() {
		if (!world.isRemote) {
			WorldUtil.createExplosion(this, world, getExplosionStrength() * (isCharged() ? 2f : 1f));
			remove();
			spawnLingeringCloud();
		}
	}

	protected void spawnLingeringCloud() {
		Collection<EffectInstance> activeEffects = getActivePotionEffects();

		if (!activeEffects.isEmpty()) {
			AreaEffectCloudEntity cloud = new AreaEffectCloudEntity(world, getPosX(), getPosY(), getPosZ());
			cloud.setRadius(2.5F);
			cloud.setRadiusOnUse(-0.5F);
			cloud.setWaitTime(10);
			cloud.setDuration(cloud.getDuration() / 2);
			cloud.setRadiusPerTick(-cloud.getRadius() / (float)cloud.getDuration());

			for (EffectInstance effect : activeEffects) {
				cloud.addEffect(new EffectInstance(effect));
			}

			world.addEntity(cloud);
		}
	}

	public class CustomCreeperSwellGoal extends Goal {
		private final AoACreeponiaCreeper swellingCreeper;
		private LivingEntity target;

		public CustomCreeperSwellGoal(AoACreeponiaCreeper entity) {
			this.swellingCreeper = entity;
			this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
		}

		public boolean shouldExecute() {
			LivingEntity target = this.swellingCreeper.getAttackTarget();
			return this.swellingCreeper.getCreeperState() > 0 || target != null && this.swellingCreeper.getDistanceSq(target) < 9.0D;
		}

		@Override
		public void startExecuting() {
			this.swellingCreeper.getNavigator().clearPath();
			this.target = this.swellingCreeper.getAttackTarget();
		}


		@Override
		public void resetTask() {
			this.target = null;
		}

		@Override
		public void tick() {
			if (this.target == null) {
				this.swellingCreeper.setCreeperState(-1);
			}
			else if (this.swellingCreeper.getDistanceSq(this.target) > 49.0D) {
				this.swellingCreeper.setCreeperState(-1);
			}
			else if (!this.swellingCreeper.getEntitySenses().canSee(this.target)) {
				this.swellingCreeper.setCreeperState(-1);
			}
			else {
				this.swellingCreeper.setCreeperState(1);
			}
		}
	}
}
