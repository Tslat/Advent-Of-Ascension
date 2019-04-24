package net.nevermine.boss.craexxeus;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.boss.EntityBoss;
import net.nevermine.container.PlayerContainer;
import net.nevermine.mob.ai.EntityAIFlying;
import net.nevermine.projectiles.enemy.EntityCraexxeusBomb;
import net.nevermine.projectiles.enemy.EntityCraexxeusNuke;
import net.nevermine.projectiles.enemy.EntityCraexxeusShot;

import java.util.List;

import static net.nevermine.container.PlayerContainer.Skills.Hunter;

public class EntityCraexxeus extends EntityAIFlying implements EntityBoss {
	private ChunkCoordinates currentFlightTarget;
	private int flyTimer;
	private int musicTick = 1;

	private boolean isDash = false;
	private int dropTimer = 60;
	private int dashCD = 200;

	private boolean charge = false;
	private int chargeTimer = 40;
	private int chargeCD = 300;

	private boolean spawnedWithBlock;

	public EntityCraexxeus(World var1) {
		super(var1);
		double moveSpeed = 1.25D;

		getNavigator().setAvoidsWater(true);
		tasks.addTask(1, new net.minecraft.entity.ai.EntityAISwimming(this));
		tasks.addTask(5, new EntityAIAttackOnCollide(this, moveSpeed, true));
		tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 32.0F));
		tasks.addTask(9, new EntityAILookIdle(this));
		tasks.addTask(6, new EntityAIWander(this, moveSpeed));
		targetTasks.addTask(2, new net.minecraft.entity.ai.EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
		flyTimer = 0;
		setSize(4.0F, 4.5F);
		spawnedWithBlock = false;
	}

	public EntityCraexxeus(World var1, int x, int y, int z) {
		this(var1);

		setEntityCoord(x, y, z);
		spawnedWithBlock = true;
	}

	public float getAIMoveSpeed() {
		return 3.7F;
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	protected void entityInit() {
		super.entityInit();
		getDataWatcher().addObject(25, Integer.valueOf(0));
		getDataWatcher().updateObject(25, Integer.valueOf(0));

		getDataWatcher().addObject(26, Integer.valueOf(0));
		getDataWatcher().updateObject(26, Integer.valueOf(0));

		getDataWatcher().addObject(27, Integer.valueOf(0));
		getDataWatcher().updateObject(27, Integer.valueOf(0));
	}

	private int getEntityCoordX() {
		return getDataWatcher().getWatchableObjectInt(25);
	}

	private int getEntityCoordY() {
		return getDataWatcher().getWatchableObjectInt(26);
	}

	private int getEntityCoordZ() {
		return getDataWatcher().getWatchableObjectInt(27);
	}

	private void setEntityCoord(int i, int j, int k) {
		getDataWatcher().updateObject(25, Integer.valueOf(i));
		getDataWatcher().updateObject(26, Integer.valueOf(j));
		getDataWatcher().updateObject(27, Integer.valueOf(k));
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.6499999761581421D);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(8.0D);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0D);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(7000.0D);
	}

	protected boolean isAIEnabled() {
		return true;
	}

	protected float getSoundPitch() {
		return super.getSoundPitch() * 0.95F;
	}

	protected String getLivingSound() {
		switch (rand.nextInt(3)) {
			case 0:
				return "nevermine:CraexxeusLiving1";
			case 1:
				return "nevermine:CraexxeusLiving2";
			case 2:
				return "nevermine:CraexxeusLiving3";
		}
		return "nevermine:CraexxeusLiving1";
	}

	protected String getHurtSound() {
		return "nevermine:CraexxeusHit";
	}

	protected String getDeathSound() {
		return "nevermine:CraexxeusDeath";
	}

	public void onDeath(DamageSource d) {
		super.onDeath(d);

		if (!worldObj.isRemote && d.getEntity() instanceof EntityPlayer) {
			EntityPlayer p = (EntityPlayer)d.getEntity();

			IChatComponent msg = StringUtil.getLocaleWithArguments("message.mob.craexxeus.kill", p.getDisplayName());

			for (final EntityPlayer e : (List<EntityPlayer>)p.worldObj.getEntitiesWithinAABB(EntityPlayer.class, p.boundingBox.expand(50.0, 50.0, 50.0))) {
				e.addChatMessage(msg);
			}

			PlayerContainer cont = PlayerContainer.getProperties((EntityPlayer)d.getEntity());

			if (cont.getLevel(Hunter) >= 30)
				cont.addExperience(10000, Hunter);
		}

		transform();
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();

		if ((rand.nextInt(70) == 37) && (entityToAttack != null) && !(entityToAttack instanceof EntityPlayer && ((EntityPlayer)entityToAttack).capabilities.isCreativeMode)) {
			Entity var1 = entityToAttack;
			EntityCraexxeusShot var2 = new EntityCraexxeusShot(worldObj, this, 10.0F, 1.0F);
			double var3 = var1.posX - posX;
			double var5 = var1.posY + var1.getEyeHeight() - 1.100000023841858D - var2.posY;
			double var7 = var1.posZ - posZ;
			float var9 = MathHelper.sqrt_double(var3 * var3 + var7 * var7) * 0.2F;
			var2.setThrowableHeading(var3, var5 + var9, var7, 1.6F, 12.0F);

			playSound("nevermine:CraexxeusFire", 2.8F, 1.0F / (getRNG().nextFloat() * 0.4F + 0.8F));
			worldObj.spawnEntityInWorld(var2);
		}

		musicTick -= 1;
		if (musicTick == 0) {
			musicTick = 480;
			playSound("nevermine:MusicCrae", 5.8F, 1.0F);
		}

		chargeCD -= 1;
		if (chargeCD == 0) {
			chargeCD = 300;
			charge = true;
			chargeTimer = 40;
			playSound("nevermine:CraexxeusChargeUp", 2.8F, 1.0F);
		}
		Entity var1;

		charge:
		if (charge) {
			chargeTimer -= 1;
			if (chargeTimer == 0) {
				charge = false;
				if (entityToAttack != null) {
					if (!(entityToAttack instanceof EntityPlayer && ((EntityPlayer)entityToAttack).capabilities.isCreativeMode)) {
						var1 = entityToAttack;
						EntityCraexxeusNuke var2 = new EntityCraexxeusNuke(worldObj, this, 30.0F, 1.0F);
						double var3 = var1.posX - posX;
						double var5 = var1.posY + var1.getEyeHeight() - 1.100000023841858D - var2.posY;
						double var7 = var1.posZ - posZ;
						float var9 = MathHelper.sqrt_double(var3 * var3 + var7 * var7) * 0.2F;
						var2.setThrowableHeading(var3, var5 + var9, var7, 1.6F, 12.0F);

						playSound("nevermine:CraexxeusNuke", 2.8F, 1.0F / (getRNG().nextFloat() * 0.4F + 0.8F));
						worldObj.spawnEntityInWorld(var2);
					}
				}
			}
		}

		dashCD -= 1;
		if (dashCD == 0) {
			double positionX = getEntityCoordX();
			double positionY = getEntityCoordY();
			double positionZ = getEntityCoordZ();

			if (!spawnedWithBlock) {
				positionX = posX;
				positionY = posY;
				positionZ = posZ;
			}

			for (EntityPlayer e : (List<EntityPlayer>)worldObj.getEntitiesWithinAABB(EntityPlayer.class, boundingBox.expand(30.0D, 30.0D, 30.0D))) {
				if (e.capabilities.isCreativeMode)
					continue;

				e.setPositionAndUpdate(positionX, positionY + 1, positionZ);
			}

			setPositionAndUpdate(positionX, positionY + 3, positionZ);
			playSound("nevermine:CraexxeusTeleport", 2.8F, 1.0F);

			dashCD = 200;
			isDash = true;
			dropTimer = 60;
		}

		if (isDash) {
			dropTimer -= 1;

			if (rand.nextInt(10) == 4) {
				worldObj.spawnEntityInWorld(new EntityCraexxeusBomb(worldObj, posX, posY - 0.25D, posZ, this));
			}
			if (dropTimer == 0) {
				isDash = false;
			}
		}
	}

	public void onUpdate() {
		super.onUpdate();
		motionY *= 0.1500000023841858D;
	}

	protected void updateAITasks() {
		super.updateAITasks();

		if (getAttackTarget() != null) {
			int var1 = (int)getAttackTarget().posX;
			int var2 = (int)getAttackTarget().posY;
			int var3 = (int)getAttackTarget().posZ;
			currentFlightTarget = new ChunkCoordinates(var1, var2, var3);
		}
		else if (flyTimer != 0) {
			flyTimer = 120;

			currentFlightTarget = new ChunkCoordinates((int)(posX + rand.nextInt(16)) - 8, (int)(posY + rand.nextInt(32)) - 16, (int)(posZ + rand.nextInt(16)) - 8);
		}

		if (currentFlightTarget != null) {
			double var1 = currentFlightTarget.posX - posX;
			double var3 = currentFlightTarget.posY + rand.nextFloat() + 1.0D - posY;
			double var5 = currentFlightTarget.posZ - posZ;

			if ((Math.signum(var1) != 0.0D) || (Math.signum(var3) != 0.0D) || (Math.signum(var5) != 0.0D)) {
				motionX += (Math.signum(var1) * 0.15D - motionX) * 0.10000000149011612D;
				motionY += (Math.signum(var3) * 1.699999988079071D - motionY) * 0.10000000149011612D;
				motionZ += (Math.signum(var5) * 0.15D - motionZ) * 0.10000000149011612D;
				float var7 = (float)(Math.atan2(motionZ, motionX) * 180.0D / 3.141592653589793D) - 90.0F;
				float var8 = MathHelper.wrapAngleTo180_float(var7 - rotationYaw);
				moveForward = 0.5F;
				rotationYaw += var8;
			}
			flyTimer -= 1;
		}
	}

	private void transform() {
		if (!worldObj.isRemote) {
			EntityXxeus var2 = new EntityXxeus(worldObj, musicTick);
			var2.setLocationAndAngles(posX, posY, posZ, rotationYaw, rotationPitch);
			worldObj.spawnEntityInWorld(var2);
			if (!worldObj.isRemote) {
				setDead();
			}
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
