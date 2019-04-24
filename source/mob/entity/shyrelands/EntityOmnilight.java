package net.nevermine.mob.entity.shyrelands;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;
import net.nevermine.mob.ai.EntityAIFlying;
import net.nevermine.projectiles.enemy.EntityCraexxeusShot;

public class EntityOmnilight extends EntityAIFlying {
	private ChunkCoordinates currentFlightTarget;
	private int flyTimer;

	public EntityOmnilight(World var1) {
		super(var1);
		double moveSpeed = 0.3499999940395355D;

		getNavigator().setAvoidsWater(true);
		setSize(0.9f, 0.9f);
		this.tasks.addTask(1, new net.minecraft.entity.ai.EntityAISwimming(this));
		this.tasks.addTask(5, new EntityAIAttackOnCollide(this, moveSpeed, true));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 32.0F));
		this.tasks.addTask(9, new EntityAILookIdle(this));
		this.tasks.addTask(6, new EntityAIWander(this, moveSpeed));
		this.targetTasks.addTask(2, new net.minecraft.entity.ai.EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		this.targetTasks.addTask(3, new net.minecraft.entity.ai.EntityAIHurtByTarget(this, true));
		this.flyTimer = 0;
	}

	protected void dropFewItems(boolean par1, int par2) {
		dropItem(Itemizer.CoinsShyrelands, 2 + this.rand.nextInt(4));

		if (this.rand.nextInt(7) == 2) {
			dropItem(Item.getItemFromBlock(net.nevermine.izer.SpecialBlockizer.LightBanner), 1);
		}
	}

	public boolean getCanSpawnHere() {
		return (this.worldObj.difficultySetting != net.minecraft.world.EnumDifficulty.PEACEFUL) && (this.posY < 35.0D) && (this.worldObj.checkNoEntityCollision(this.boundingBox)) && (this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty()) && (!this.worldObj.isAnyLiquid(this.boundingBox));
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();

		if ((this.rand.nextInt(15) == 8) && (this.entityToAttack != null)) {
			if (this.entityToAttack instanceof EntityPlayer && ((EntityPlayer)entityToAttack).capabilities.isCreativeMode)
				return;

			Entity var1 = this.entityToAttack;
			EntityCraexxeusShot var2 = new EntityCraexxeusShot(this.worldObj, this, 8.0F, 1.0F);
			double var3 = var1.posX - this.posX;
			double var5 = var1.posY + var1.getEyeHeight() - 1.100000023841858D - var2.posY;
			double var7 = var1.posZ - this.posZ;
			float var9 = MathHelper.sqrt_double(var3 * var3 + var7 * var7) * 0.2F;
			var2.setThrowableHeading(var3, var5 + var9, var7, 1.6F, 12.0F);

			playSound("nevermine:OmnilightFire", 1.0F, 1.0F / (getRNG().nextFloat() * 0.4F + 0.8F));
			this.worldObj.spawnEntityInWorld(var2);
		}
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3499999940395355D);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0D);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0D);
	}

	protected boolean isAIEnabled() {
		return true;
	}

	protected float getSoundPitch() {
		return super.getSoundPitch() * 0.95F;
	}

	protected String getLivingSound() {
		return "nevermine:OmnilightLiving";
	}

	protected String getHurtSound() {
		return "nevermine:OmnilightHit";
	}

	protected String getDeathSound() {
		return "nevermine:OmnilightDeath";
	}

	public void onUpdate() {
		super.onUpdate();
		this.motionY *= 0.6000000238418579D;
	}

	protected void updateAITasks() {
		super.updateAITasks();

		if (getAttackTarget() != null) {
			int var1 = (int)getAttackTarget().posX;
			int var2 = (int)getAttackTarget().posY;
			int var3 = (int)getAttackTarget().posZ;
			this.currentFlightTarget = new ChunkCoordinates(var1, var2, var3);
		}
		else if (this.flyTimer != 0) {
			this.flyTimer = 120;
			this.currentFlightTarget = new ChunkCoordinates((int)(this.posX + this.rand.nextInt(16)) - 8, (int)(this.posY + this.rand.nextInt(32)) - 16, (int)(this.posZ + this.rand.nextInt(16)) - 8);
		}

		if (this.currentFlightTarget != null) {
			double var1 = this.currentFlightTarget.posX - this.posX;
			double var3 = this.currentFlightTarget.posY + this.rand.nextFloat() + 1.0D - this.posY;
			double var5 = this.currentFlightTarget.posZ - this.posZ;

			if ((Math.signum(var1) != 0.0D) || (Math.signum(var3) != 0.0D) || (Math.signum(var5) != 0.0D)) {
				this.motionX += (Math.signum(var1) * 0.15D - this.motionX) * 0.10000000149011612D;
				this.motionY += (Math.signum(var3) * 1.699999988079071D - this.motionY) * 0.10000000149011612D;
				this.motionZ += (Math.signum(var5) * 0.15D - this.motionZ) * 0.10000000149011612D;
				float var7 = (float)(Math.atan2(this.motionZ, this.motionX) * 180.0D / 3.141592653589793D) - 90.0F;
				float var8 = MathHelper.wrapAngleTo180_float(var7 - this.rotationYaw);
				this.moveForward = 0.5F;
				this.rotationYaw += var8;
			}
			this.flyTimer -= 1;
		}
	}

	protected boolean canTriggerWalking() {
		return false;
	}

	protected void fall(float par1) {
	}

	protected void updateFallState(double par1, boolean par3) {
	}

	public boolean doesEntityNotTriggerPressurePlate() {
		return true;
	}

	public void onCollideWithPlayer(EntityPlayer par1EntityPlayer) {
		if (!isDead)
			attackEntityAsMob(par1EntityPlayer);
	}
}
