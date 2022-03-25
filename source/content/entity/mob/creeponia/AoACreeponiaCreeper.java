package net.tslat.aoa3.content.entity.mob.creeponia;

import net.minecraft.block.BlockState;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.effect.LightningBoltEntity;
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
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.Constants;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.WorldUtil;

import java.util.Collection;
import java.util.EnumSet;

public abstract class AoACreeponiaCreeper extends AoAMeleeMob {
	private static final DataParameter<Integer> STATE = EntityDataManager.defineId(AoACreeponiaCreeper.class, DataSerializers.INT);
	private static final DataParameter<Boolean> POWERED = EntityDataManager.defineId(AoACreeponiaCreeper.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> IGNITED = EntityDataManager.defineId(AoACreeponiaCreeper.class, DataSerializers.BOOLEAN);
	protected int fuseTime = 30;
	protected int lastActiveTime;
	protected int timeSinceIgnited;
	private float swimAnimation;
	private float lastSwimAnimation;
	private int explosionRadius = 3;

	public AoACreeponiaCreeper(EntityType<? extends AoACreeponiaCreeper> entityType, World world) {
		super(entityType, world);
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
		targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, TameableEntity.class, 10, true, false, entity -> entity instanceof TameableEntity && ((TameableEntity)entity).isTame()));
		targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
		targetSelector.addGoal(3, new HurtByTargetGoal(this));
	}

	protected void defineSynchedData() {
		super.defineSynchedData();

		entityData.define(STATE, -1);
		entityData.define(POWERED, false);
		entityData.define(IGNITED, false);
	}

	public abstract float getExplosionStrength();

	protected void onAttack(Entity target) {}

	protected void onHit(DamageSource source, float amount) {}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.CREEPER_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.CREEPER_DEATH;
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState block) {}

	public int getCreeperState() {
		return this.entityData.get(STATE);
	}

	public void setCreeperState(int state) {
		this.entityData.set(STATE, state);
	}

	@Override
	public void thunderHit(ServerWorld world, LightningBoltEntity lightningBolt) {
		super.thunderHit(world, lightningBolt);

		entityData.set(POWERED, true);
	}

	@Override
	protected ActionResultType mobInteract(PlayerEntity player, Hand hand) {
		ItemStack itemstack = player.getItemInHand(hand);

		if (itemstack.getItem() == Items.FLINT_AND_STEEL) {
			level.playSound(player, getX(), getY(), getZ(), SoundEvents.FLINTANDSTEEL_USE, getSoundSource(), 1.0F, random.nextFloat() * 0.4F + 0.8F);

			if (!level.isClientSide) {
				ignite();
				itemstack.hurtAndBreak(1, player, (p_213625_1_) -> {
					p_213625_1_.broadcastBreakEvent(hand);
				});
			}

			return ActionResultType.sidedSuccess(level.isClientSide);
		}
		else {
			return super.mobInteract(player, hand);
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
				playSound(SoundEvents.CREEPER_PRIMED, 1.0F, 0.5F);

			timeSinceIgnited += creeperState;

			if (timeSinceIgnited < 0)
				timeSinceIgnited = 0;

			if (timeSinceIgnited >= fuseTime) {
				timeSinceIgnited = fuseTime;

				explode();
			}
		}

		if (!net.minecraftforge.common.ForgeHooks.onLivingUpdate(this)) {
			if (!level.isClientSide)
				setSharedFlag(6, isGlowing());

			baseTick();

			lastSwimAnimation = swimAnimation;

			if (isVisuallySwimming()) {
				swimAnimation = Math.min(1.0F, swimAnimation + 0.09F);
			}
			else {
				swimAnimation = Math.max(0.0F, swimAnimation - 0.09F);
			}

			if (!level.isClientSide) {
				int arrowCount = getArrowCount();

				if (arrowCount > 0) {
					if (removeArrowTime <= 0)
						removeArrowTime = 20 * (30 - arrowCount);

					--removeArrowTime;

					if (removeArrowTime <= 0)
						setArrowCount(arrowCount - 1);
				}

				int beeStings = getStingerCount();

				if (beeStings > 0) {
					if (removeStingerTime <= 0)
						removeStingerTime = 20 * (30 - beeStings);

					--removeStingerTime;

					if (removeStingerTime <= 0)
						setStingerCount(beeStings - 1);
				}

				if (tickCount % 20 == 0)
					getCombatTracker().recheckStatus();

				if (!glowing) {
					boolean glowing = hasEffect(Effects.GLOWING);

					if (getSharedFlag(6) != glowing)
						setSharedFlag(6, glowing);
				}
			}

			aiStep();

			double diffX = getX() - xo;
			double diffZ = getZ() - zo;
			float movementDiff = (float)(diffX * diffX + diffZ * diffZ);
			float direction = yBodyRot;
			float distanceMoved = 0.0F;
			oRun = run;
			float moveSpeedBase = 0.0F;

			if (movementDiff > 0.0025000002F) {
				moveSpeedBase = 1.0F;
				distanceMoved = (float)Math.sqrt(movementDiff) * 3.0F;
				float f = (float)MathHelper.atan2(diffZ, diffX) * (180F / (float)Math.PI) - 90.0F;
				float f1 = MathHelper.abs(MathHelper.wrapDegrees(yRot) - f);

				if (95.0F < f1 && f1 < 265.0F) {
					direction = f - 180.0F;
				}
				else {
					direction = f;
				}
			}

			if (attackAnim > 0.0F)
				direction = yRot;

			if (!onGround)
				moveSpeedBase = 0.0F;

			run += (moveSpeedBase - run) * 0.3F;

			level.getProfiler().push("headTurn");

			distanceMoved = tickHeadTurn(direction, distanceMoved);

			level.getProfiler().pop();
			level.getProfiler().push("rangeChecks");

			while (yRot - yRotO < -180.0F) {
				yRotO -= 360.0F;
			}

			while (yRot - yRotO >= 180.0F) {
				yRotO += 360.0F;
			}

			while (yBodyRot - yBodyRotO < -180.0F) {
				yBodyRotO -= 360.0F;
			}

			while (yBodyRot - yBodyRotO >= 180.0F) {
				yBodyRotO += 360.0F;
			}

			while (xRot - xRotO < -180.0F) {
				xRotO -= 360.0F;
			}

			while (xRot - xRotO >= 180.0F) {
				xRotO += 360.0F;
			}

			while (yHeadRot - yHeadRotO < -180.0F) {
				yHeadRotO -= 360.0F;
			}

			while (yHeadRot - yHeadRotO >= 180.0F) {
				yHeadRotO += 360.0F;
			}

			level.getProfiler().pop();

			animStep += distanceMoved;

			if (isFallFlying()) {
				++fallFlyTicks;
			}
			else {
				fallFlyTicks = 0;
			}

			if (isSleeping())
				xRot = 0.0F;
		}

		if (!level.isClientSide) {
			tickLeash();

			if (tickCount % 5 == 0)
				updateControlFlags();
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);

		if (this.entityData.get(POWERED))
			compound.putBoolean("powered", true);

		compound.putShort("Fuse", (short)this.fuseTime);
		compound.putByte("ExplosionRadius", (byte)explosionRadius);
		compound.putBoolean("ignited", hasIgnited());
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);

		entityData.set(POWERED, compound.getBoolean("powered"));

		if (compound.contains("Fuse", Constants.NBT.TAG_ANY_NUMERIC))
			fuseTime = compound.getShort("Fuse");

		if (compound.contains("ExplosionRadius", Constants.NBT.TAG_ANY_NUMERIC))
			explosionRadius = compound.getByte("ExplosionRadius");

		if (compound.getBoolean("ignited"))
			ignite();
	}

	@Override
	public boolean causeFallDamage(float distance, float damageMultiplier) {
		boolean success = super.causeFallDamage(distance, damageMultiplier);

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
	public float getSwimAmount(float partialTicks) {
		return MathHelper.lerp(partialTicks, this.lastSwimAnimation, this.swimAnimation);
	}

	public boolean hasIgnited() {
		return entityData.get(IGNITED);
	}

	public void ignite() {
		entityData.set(IGNITED, true);
	}

	public boolean isCharged() {
		return entityData.get(POWERED);
	}

	protected void explode() {
		if (!level.isClientSide) {
			WorldUtil.createExplosion(this, level, getExplosionStrength() * (isCharged() ? 2f : 1f));
			remove();
			spawnLingeringCloud();
		}
	}

	protected void spawnLingeringCloud() {
		Collection<EffectInstance> activeEffects = getActiveEffects();

		if (!activeEffects.isEmpty()) {
			AreaEffectCloudEntity cloud = new AreaEffectCloudEntity(level, getX(), getY(), getZ());
			cloud.setRadius(2.5F);
			cloud.setRadiusOnUse(-0.5F);
			cloud.setWaitTime(10);
			cloud.setDuration(cloud.getDuration() / 2);
			cloud.setRadiusPerTick(-cloud.getRadius() / (float)cloud.getDuration());

			for (EffectInstance effect : activeEffects) {
				cloud.addEffect(new EffectInstance(effect));
			}

			level.addFreshEntity(cloud);
		}
	}

	public static class CustomCreeperSwellGoal extends Goal {
		private final AoACreeponiaCreeper swellingCreeper;
		private LivingEntity target;

		public CustomCreeperSwellGoal(AoACreeponiaCreeper entity) {
			this.swellingCreeper = entity;
			this.setFlags(EnumSet.of(Goal.Flag.MOVE));
		}

		public boolean canUse() {
			LivingEntity target = this.swellingCreeper.getTarget();
			return this.swellingCreeper.getCreeperState() > 0 || target != null && this.swellingCreeper.distanceToSqr(target) < 9.0D;
		}

		@Override
		public void start() {
			this.swellingCreeper.getNavigation().stop();
			this.target = this.swellingCreeper.getTarget();
		}


		@Override
		public void stop() {
			this.target = null;
		}

		@Override
		public void tick() {
			if (this.target == null) {
				this.swellingCreeper.setCreeperState(-1);
			}
			else if (this.swellingCreeper.distanceToSqr(this.target) > 49.0D) {
				this.swellingCreeper.setCreeperState(-1);
			}
			else if (!this.swellingCreeper.getSensing().canSee(this.target)) {
				this.swellingCreeper.setCreeperState(-1);
			}
			else {
				this.swellingCreeper.setCreeperState(1);
			}
		}
	}
}
