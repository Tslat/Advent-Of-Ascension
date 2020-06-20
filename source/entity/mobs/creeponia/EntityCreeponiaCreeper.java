package net.tslat.aoa3.entity.mobs.creeponia;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityAreaEffectCloud;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.entity.minions.AoAMinion;
import net.tslat.aoa3.entity.properties.SpecialPropertyEntity;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ConfigurationUtil;
import net.tslat.aoa3.utils.WorldUtil;

import java.util.Collection;
import java.util.TreeSet;

public abstract class EntityCreeponiaCreeper extends EntityCreeper {
	protected final TreeSet<Enums.MobProperties> mobProperties;

	protected int fuseTime = 30;
	protected int lastActiveTime;
	protected int timeSinceIgnited;

	public EntityCreeponiaCreeper(World world, float entityWidth, float entityHeight) {
		super(world);

		mobProperties = this instanceof SpecialPropertyEntity ? new TreeSet<Enums.MobProperties>() : null;
		experienceValue = (int)(getBaseMaxHealth() / 10d);

		setSize(entityWidth, entityHeight);
	}

	@Override
	protected void initEntityAI() {
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAICreeperSwell(this));
		this.tasks.addTask(3, new EntityAIAvoidEntity<>(this, EntityOcelot.class, 6.0F, 1.0D, 1.2D));
		this.tasks.addTask(4, new EntityAIAttackMelee(this, 1.0D, false));
		this.tasks.addTask(5, new EntityAIWanderAvoidWater(this, 0.8D));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(6, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget<>(this, AoAMinion.class, 10, true, false, EntityTameable::isTamed));
		targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(3, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();

		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(getBaseMaxHealth() * (ConfigurationUtil.MainConfig.funOptions.hardcoreMode ? 2f : 1f));
		getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(getBaseKnockbackResistance());
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(getBaseMovementSpeed());
	}

	protected abstract double getBaseKnockbackResistance();

	protected abstract double getBaseMaxHealth();

	protected abstract double getBaseMovementSpeed();

	public abstract float getExplosionStrength();

	@Override
	protected void dropLoot(boolean wasRecentlyHit, int lootingModifier, DamageSource source) {
		if (getLootTable() != null) {
			LootTable lootTable = world.getLootTableManager().getLootTableFromLocation(getLootTable());

			LootContext.Builder lootBuilder = (new LootContext.Builder((WorldServer)world)).withLootedEntity(this).withDamageSource(source);

			if (wasRecentlyHit && attackingPlayer != null)
				lootBuilder.withPlayer(attackingPlayer).withLuck(attackingPlayer.getLuck() + lootingModifier);

			for (ItemStack stack : lootTable.generateLootForPools(rand, lootBuilder.build())) {
				entityDropItem(stack, 0);
			}

			dropEquipment(wasRecentlyHit, lootingModifier);
		}
		else {
			super.dropLoot(wasRecentlyHit, lootingModifier, source);
		}
	}

	@Override
	public void onUpdate() {
		if (isEntityAlive()) {
			lastActiveTime = timeSinceIgnited;

			if (hasIgnited())
				setCreeperState(1);

			int stateValue = getCreeperState();

			if (stateValue > 0 && timeSinceIgnited == 0)
				playSound(SoundEvents.ENTITY_CREEPER_PRIMED, 1.0F, 0.5F);

			timeSinceIgnited += stateValue;

			if (timeSinceIgnited < 0)
				timeSinceIgnited = 0;

			if (timeSinceIgnited >= fuseTime) {
				timeSinceIgnited = fuseTime;

				explode();
			}
		}

		if (ForgeHooks.onLivingUpdate(this))
			return;

		if (!this.world.isRemote) {
			this.setFlag(6, this.isGlowing());
		}

		onEntityUpdate();

		if (!world.isRemote) {
			int arrowCount = getArrowCountInEntity();

			if (arrowCount > 0) {
				if (arrowHitTimer <= 0)
					arrowHitTimer = 20 * (30 - arrowCount);

				--arrowHitTimer;

				if (arrowHitTimer <= 0)
					setArrowCountInEntity(arrowCount - 1);
			}

			if (ticksExisted % 20 == 0)
				getCombatTracker().reset();

			if (!glowing) {
				boolean flag = isPotionActive(MobEffects.GLOWING);

				if (getFlag(6) != flag)
					setFlag(6, flag);
			}
		}

		onLivingUpdate();

		double interpolateX = posX - prevPosX;
		double interpolateZ = posZ - prevPosZ;
		float interpolateLatSq = (float)(interpolateX * interpolateX + interpolateZ * interpolateZ);
		float interpolateRenderYaw = renderYawOffset;
		float moveDistance = 0.0F;
		prevOnGroundSpeedFactor = onGroundSpeedFactor;
		float frictionFactor = 0.0F;

		if (interpolateLatSq > 0.0025000002F) {
			frictionFactor = 1.0F;
			moveDistance = (float)Math.sqrt(interpolateLatSq) * 3.0F;
			float updatedInterpolYaw = (float)MathHelper.atan2(interpolateZ, interpolateX) * (180F / (float)Math.PI) - 90.0F;
			float absoluteWrappedYaw = MathHelper.abs(MathHelper.wrapDegrees(rotationYaw) - updatedInterpolYaw);

			if (95.0F < absoluteWrappedYaw && absoluteWrappedYaw < 265.0F) {
				interpolateRenderYaw = updatedInterpolYaw - 180.0F;
			}
			else {
				interpolateRenderYaw = updatedInterpolYaw;
			}
		}

		if (swingProgress > 0.0F)
			interpolateRenderYaw = this.rotationYaw;

		if (!onGround)
			frictionFactor = 0.0F;

		onGroundSpeedFactor += (frictionFactor - onGroundSpeedFactor) * 0.3F;

		world.profiler.startSection("headTurn");

		moveDistance = updateDistance(interpolateRenderYaw, moveDistance);

		world.profiler.endSection();
		world.profiler.startSection("rangeChecks");

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

		world.profiler.endSection();

		movedDistance += moveDistance;

		if (!world.isRemote) {
			updateLeashedState();

			if (ticksExisted % 5 == 0) {
				boolean inControl = !(getControllingPassenger() instanceof EntityLiving);

				tasks.setControlFlag(1, inControl);
				tasks.setControlFlag(4, inControl && !(getRidingEntity() instanceof EntityBoat));
				tasks.setControlFlag(2, inControl);
			}

			if (world.getDifficulty() == EnumDifficulty.PEACEFUL)
				setDead();
		}
	}

	@Override
	public boolean isEntityInvulnerable(DamageSource source) {
		if (source == DamageSource.OUT_OF_WORLD)
			return false;

		if (getIsInvulnerable())
			return true;

		return isSpecialImmuneTo(source, 1);
	}

	protected boolean isSpecialImmuneTo(DamageSource source, int damage) {
		return false;
	}

	@Override
	protected boolean isValidLightLevel() {
		return WorldUtil.getLightLevel(world, getPosition(), true, false) <= rand.nextInt(8);
	}

	@Override
	public boolean getCanSpawnHere() {
		return world.getDifficulty() != EnumDifficulty.PEACEFUL && rand.nextFloat() < 0.1 && isValidLightLevel() && canSpawnOnBlock(world.getBlockState(getPosition().down()));
	}

	protected boolean canSpawnOnBlock(IBlockState block) {
		return block.canEntitySpawn(this);
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn) {}

	@SideOnly(Side.CLIENT)
	@Override
	public float getCreeperFlashIntensity(float partialTicks) {
		return ((float)lastActiveTime + (float)(timeSinceIgnited - lastActiveTime) * partialTicks) / (float)(fuseTime - 2);
	}

	protected void explode() {
		if (!world.isRemote) {
			WorldUtil.createExplosion(this, world, getExplosionStrength() * (getPowered() ? 2f : 1f));
			setDead();
			spawnLingeringCloud();
		}
	}

	protected void spawnLingeringCloud() {
		Collection<PotionEffect> activeEffects = getActivePotionEffects();

		if (!activeEffects.isEmpty()) {
			EntityAreaEffectCloud effectCloud = new EntityAreaEffectCloud(world, posX, posY, posZ);

			effectCloud.setRadius(2.5F);
			effectCloud.setRadiusOnUse(-0.5F);
			effectCloud.setWaitTime(10);
			effectCloud.setDuration(effectCloud.getDuration() / 2);
			effectCloud.setRadiusPerTick(-effectCloud.getRadius() / (float)effectCloud.getDuration());

			for (PotionEffect effect : activeEffects) {
				effectCloud.addEffect(new PotionEffect(effect));
			}

			world.spawnEntity(effectCloud);
		}
	}
}
