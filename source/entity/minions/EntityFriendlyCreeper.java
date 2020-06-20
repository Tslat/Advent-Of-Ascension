package net.tslat.aoa3.entity.minions;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAreaEffectCloud;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemFlintAndSteel;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.utils.WorldUtil;

import javax.annotation.Nullable;
import java.util.Collection;

public class EntityFriendlyCreeper extends AoAMinion {
	public static final float entityWidth = 0.6f;

	private static final DataParameter<Integer> STATE = EntityDataManager.<Integer>createKey(EntityFriendlyCreeper.class, DataSerializers.VARINT);
	private static final DataParameter<Boolean> POWERED = EntityDataManager.<Boolean>createKey(EntityFriendlyCreeper.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> IGNITED = EntityDataManager.<Boolean>createKey(EntityFriendlyCreeper.class, DataSerializers.BOOLEAN);

	private int lastActiveTime;
	private int timeSinceIgnited;
	private int fuseTime = 20;
	private float explosionRadius = 2.2f;

	public EntityFriendlyCreeper(World world) {
		super(world, -1, entityWidth, 1.7f);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(STATE, -1);
		dataManager.register(POWERED, false);
		dataManager.register(IGNITED, false);
	}

	@Override
	protected void initEntityAI() {
		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(2, new EntityAIFriendlyCreeperSwell(this));
		tasks.addTask(4, new EntityAIAttackMelee(this, 1.0D, false));
		tasks.addTask(5, new EntityAIWanderAvoidWater(this, 0.8D));
		tasks.addTask(6, new EntityAIWatchClosest(this, EntityMob.class, 8.0F));
		tasks.addTask(6, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityMob.class, true));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();

		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25d);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(0d);
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(8d);
	}

	@Override
	protected double getBaseMaxHealth() {
		return 20;
	}

	@Override
	protected boolean isHostile() {
		return true;
	}

	@Override
	public int getMaxFallHeight() {
		return getAttackTarget() == null ? 3 : 3 + (int)getHealth() - 1;
	}

	@Override
	public void fall(float distance, float damageMultiplier) {
		super.fall(distance, damageMultiplier);

		timeSinceIgnited = (int)((float)timeSinceIgnited + distance * 1.5f);

		if (timeSinceIgnited > fuseTime - 5)
			timeSinceIgnited = fuseTime	- 5;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);

		if (dataManager.get(POWERED))
			compound.setBoolean("powered", true);

		compound.setShort("Fuse", (short)fuseTime);
		compound.setByte("ExplosionRadius", (byte)explosionRadius);
		compound.setBoolean("ignited", hasIgnited());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);

		dataManager.set(POWERED, compound.getBoolean("powered"));

		if (compound.hasKey("Fuse", 99))
			fuseTime = compound.getShort("Fuse");

		if (compound.hasKey("ExplosionRadius", 99))
			explosionRadius = compound.getByte("ExplosionRadius");

		if (compound.getBoolean("ignited"))
			ignite();
	}

	@Override
	public void onUpdate() {
		if (isEntityAlive()) {
			lastActiveTime = timeSinceIgnited;

			if (hasIgnited())
				setCreeperState(1);

			int state = getCreeperState();

			if (state > 0 && timeSinceIgnited == 0)
				playSound(SoundEvents.ENTITY_CREEPER_PRIMED, 1.0f, 0.5f);

			timeSinceIgnited += state;

			if (timeSinceIgnited < 0)
				timeSinceIgnited = 0;

			if (timeSinceIgnited >= fuseTime) {
				timeSinceIgnited = fuseTime;
				explode();
			}
		}

		super.onUpdate();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.ENTITY_CREEPER_HURT;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_CREEPER_DEATH;
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		return true;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (source.isExplosion() && source.getTrueSource() != null && source.getTrueSource().getUniqueID().equals(getOwnerId()))
			return false;

		return super.attackEntityFrom(source, amount);
	}

	public boolean getPowered() {
		return dataManager.get(POWERED);
	}

	@SideOnly(Side.CLIENT)
	public float getCreeperFlashIntensity(float partialTicks) {
		return (lastActiveTime + (timeSinceIgnited - lastActiveTime) * partialTicks) / (float)(fuseTime - 2);
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return null;
	}

	public int getCreeperState() {
		return dataManager.get(STATE);
	}

	public void setCreeperState(int state) {
		dataManager.set(STATE, state);
	}

	@Override
	public void onStruckByLightning(EntityLightningBolt lightningBolt) {
		super.onStruckByLightning(lightningBolt);

		dataManager.set(POWERED, true);
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand) {
		ItemStack heldStack = player.getHeldItem(hand);

		if (heldStack.getItem() instanceof ItemFlintAndSteel) {
			world.playSound(player, posX, posY, posZ, SoundEvents.ITEM_FLINTANDSTEEL_USE, getSoundCategory(), 1.0f, rand.nextFloat() * 0.4F + 0.8F);
			player.swingArm(hand);

			if (!world.isRemote) {
				ignite();
				heldStack.damageItem(1, player);
			}
		}

		return super.processInteract(player, hand);
	}

	public void explode() {
		if (!world.isRemote) {
			EntityLivingBase owner = getOwner();

			setDead();
			world.createExplosion(owner == null ? this : owner, posX, posY, posZ, explosionRadius, WorldUtil.checkGameRule(world, "doStrongerMobGriefing"));
			spawnLingeringCloud();
		}
	}

	private void spawnLingeringCloud() {
		Collection<PotionEffect> activeEffects = this.getActivePotionEffects();

		if (!activeEffects.isEmpty()) {
			EntityAreaEffectCloud entityEffectCloud = new EntityAreaEffectCloud(world, posX, posY, posZ);
			entityEffectCloud.setRadius(2.5F);
			entityEffectCloud.setRadiusOnUse(-0.5F);
			entityEffectCloud.setWaitTime(10);
			entityEffectCloud.setDuration(entityEffectCloud.getDuration() / 2);
			entityEffectCloud.setRadiusPerTick(-entityEffectCloud.getRadius() / (float)entityEffectCloud.getDuration());

			for (PotionEffect effect : activeEffects) {
				entityEffectCloud.addEffect(new PotionEffect(effect));
			}

			world.spawnEntity(entityEffectCloud);
		}
	}

	public boolean hasIgnited() {
		return dataManager.get(IGNITED);
	}

	public void ignite() {
		dataManager.set(IGNITED, true);
	}

	private class EntityAIFriendlyCreeperSwell extends EntityAIBase {
		EntityFriendlyCreeper swellingCreeper;
		EntityLivingBase target;

		public EntityAIFriendlyCreeperSwell(EntityFriendlyCreeper creeper) {
			this.swellingCreeper = creeper;
			setMutexBits(1);
		}

		@Override
		public boolean shouldExecute() {
			EntityLivingBase target = swellingCreeper.getAttackTarget();

			return swellingCreeper.getCreeperState() > 0 || target != null && swellingCreeper.getDistanceSq(target) < 9;
		}

		@Override
		public void startExecuting() {
			swellingCreeper.getNavigator().clearPath();

			target = swellingCreeper.getAttackTarget();
		}

		@Override
		public void resetTask() {
			target = null;
		}

		@Override
		public void updateTask() {
			if (target == null || swellingCreeper.getDistanceSq(target) > 49 || !swellingCreeper.getEntitySenses().canSee(target)) {
				swellingCreeper.setCreeperState(-1);
			}
			else {
				swellingCreeper.setCreeperState(1);
			}
		}
	}
}
