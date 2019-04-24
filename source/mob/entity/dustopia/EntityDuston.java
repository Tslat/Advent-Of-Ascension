package net.nevermine.mob.entity.dustopia;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;
import net.nevermine.mob.ai.EntityAIFlying;

public class EntityDuston extends EntityAIFlying {
	private ChunkCoordinates currentFlightTarget;
	private int flyTimer;

	public EntityDuston(final World var1) {
		super(var1);
		final double moveSpeed = 0.3499999940395355;
		getNavigator().setAvoidsWater(true);
		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(5, new EntityAIAttackOnCollide(this, moveSpeed, true));
		tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 32.0f));
		tasks.addTask(9, new EntityAILookIdle(this));
		tasks.addTask(6, new EntityAIWander(this, moveSpeed));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
		setSize(0.6f, 1.5f);
		flyTimer = 0;
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		if (rand.nextInt(2) == 0) {
			dropItem(Itemizer.CoinsDustopia, 1);
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
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(5.0);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0);
	}

	protected boolean isAIEnabled() {
		return true;
	}

	protected float getSoundPitch() {
		return super.getSoundPitch() * 0.95f;
	}

	protected String getLivingSound() {
		return null;
	}

	protected String getHurtSound() {
		return "nevermine:DustonHit";
	}

	protected String getDeathSound() {
		return null;
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

	public void onDeath(final DamageSource var1) {
		super.onDeath(var1);
		transform();
	}

	private void transform() {
		if (!worldObj.isRemote) {
			final EntityDustStrider var2 = new EntityDustStrider(worldObj);
			var2.setLocationAndAngles(posX, posY + 3.0, posZ, rotationYaw, rotationPitch);
			worldObj.spawnEntityInWorld(var2);
			final EntityDustStrider var3 = new EntityDustStrider(worldObj);
			var3.setLocationAndAngles(posX, posY + 3.5, posZ, rotationYaw, rotationPitch);
			worldObj.spawnEntityInWorld(var3);
			final EntityDustStrider var4 = new EntityDustStrider(worldObj);
			var4.setLocationAndAngles(posX, posY + 3.2, posZ, rotationYaw, rotationPitch);
			worldObj.spawnEntityInWorld(var4);
			final EntityDustStrider var5 = new EntityDustStrider(worldObj);
			var5.setLocationAndAngles(posX, posY + 3.4, posZ, rotationYaw, rotationPitch);
			worldObj.spawnEntityInWorld(var5);
			if (!worldObj.isRemote) {
				setDead();
			}
		}
	}
}
