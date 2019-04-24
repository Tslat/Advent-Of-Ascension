package net.nevermine.mob.entity.lunalus;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Weaponizer;
import net.nevermine.mob.ai.EntityAIFlying;
import net.nevermine.mob.placement.EntityNoFire;

public class EntityLunarcher extends EntityAIFlying implements IRangedAttackMob, EntityNoFire {
	private ChunkCoordinates currentFlightTarget;
	private int flyTimer;
	private static final ItemStack defaultHeldItem;
	private EntityAIArrowAttack aiArrowAttack;

	public EntityLunarcher(final World var1) {
		super(var1);
		aiArrowAttack = new EntityAIArrowAttack(this, getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue(), 20, 60, 15.0f);
		final double moveSpeed = 0.3499999940395355;
		isImmuneToFire = true;
		tasks.addTask(7, new EntityAIArrowAttack(this, 0.25, 20, 64.0f));
		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(2, new EntityAIRestrictSun(this));
		tasks.addTask(3, new EntityAIFleeSun(this, moveSpeed));
		tasks.addTask(5, new EntityAIWander(this, moveSpeed));
		tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));
		tasks.addTask(6, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		flyTimer = 0;
		setSize(0.6f, 2.0f);
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		dropItem(Itemizer.CopperCoin, 5 + rand.nextInt(10));
		if (rand.nextInt(2) == 1) {
			dropItem(Itemizer.CoinsLunalus, 2);
		}
		if (rand.nextInt(100) == 35) {
			dropItem(Itemizer.UpgradeKitLunar, 1);
		}
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3499999940395355);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(3.0);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40.0);
	}

	protected boolean isAIEnabled() {
		return true;
	}

	protected float getSoundPitch() {
		return super.getSoundPitch() * 0.95f;
	}

	public ItemStack getHeldItem() {
		return EntityLunarcher.defaultHeldItem;
	}

	public void attackEntityWithRangedAttack(final EntityLivingBase var1, final float f) {
		final EntityArrow var2 = new EntityArrow(worldObj, this, var1, 1.6f, 12.0f);
		var2.setDamage(2.0);
		playSound("random.bow", 1.0f, 1.0f / (getRNG().nextFloat() * 0.4f + 0.8f));
		worldObj.spawnEntityInWorld(var2);
	}

	protected String getLivingSound() {
		return "nevermine:LunarcherLiving";
	}

	protected String getHurtSound() {
		return "nevermine:LunarcherHit";
	}

	protected String getDeathSound() {
		return "nevermine:LunarcherDeath";
	}

	public void onUpdate() {
		super.onUpdate();
		motionY *= 0.6000000238418579;
	}

	protected void updateAITasks() {
		super.updateAITasks();
		if (getAttackTarget() != null) {
			final int var1 = (int)getAttackTarget().posX;
			final int var2 = (int)getAttackTarget().posY;
			final int var3 = (int)getAttackTarget().posZ;
			currentFlightTarget = new ChunkCoordinates(var1, var2, var3);
		}
		else if (flyTimer != 0) {
			flyTimer = 120;
			currentFlightTarget = new ChunkCoordinates((int)(posX + rand.nextInt(16)) - 8, (int)(posY + rand.nextInt(32)) - 16, (int)(posZ + rand.nextInt(16)) - 8);
		}
		if (currentFlightTarget != null) {
			final double var4 = currentFlightTarget.posX - posX;
			final double var5 = currentFlightTarget.posY + rand.nextFloat() + 1.0 - posY;
			final double var6 = currentFlightTarget.posZ - posZ;
			if (Math.signum(var4) != 0.0 || Math.signum(var5) != 0.0 || Math.signum(var6) != 0.0) {
				motionX += (Math.signum(var4) * 0.15 - motionX) * 0.10000000149011612;
				motionY += (Math.signum(var5) * 1.699999988079071 - motionY) * 0.10000000149011612;
				motionZ += (Math.signum(var6) * 0.15 - motionZ) * 0.10000000149011612;
				final float var7 = (float)(Math.atan2(motionZ, motionX) * 180.0 / 3.141592653589793) - 90.0f;
				final float var8 = MathHelper.wrapAngleTo180_float(var7 - rotationYaw);
				moveForward = 0.5f;
				rotationYaw += var8;
			}
			--flyTimer;
		}
	}

	protected boolean canTriggerWalking() {
		return false;
	}

	@Override
	protected void fall(final float par1) {
	}

	@Override
	protected void updateFallState(final double par1, final boolean par3) {
	}

	public boolean doesEntityNotTriggerPressurePlate() {
		return true;
	}

	public void onCollideWithPlayer(final EntityPlayer par1EntityPlayer) {
		if (!isDead)
			attackEntityAsMob(par1EntityPlayer);
	}

	static {
		defaultHeldItem = new ItemStack(Weaponizer.LunarBow, 1);
	}
}
