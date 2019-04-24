package net.nevermine.mob.entity.gardencia;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Weaponizer;
import net.nevermine.projectiles.staff.EntityTangleFall;

public class EntityVineWizard extends EntityMob implements IRangedAttackMob {
	private EntityAIArrowAttack aiArrowAttack;

	public EntityVineWizard(final World par1World) {
		super(par1World);
		aiArrowAttack = new EntityAIArrowAttack(this, getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue(), 20, 60, 15.0f);
		final float moveSpeed = 0.45f;
		tasks.addTask(7, new EntityAIArrowAttack(this, getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue(), 20, 64.0f));
		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(2, new EntityAIRestrictSun(this));
		tasks.addTask(5, new EntityAIWander(this, (double)moveSpeed));
		tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));
		tasks.addTask(6, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		setSize(0.6f, 2.3f);
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		if (rand.nextInt(50) == 3) {
			dropItem(Weaponizer.FlowerCannon, 1);
		}
		if (rand.nextInt(40) == 17) {
			dropItem(Itemizer.SmallPedalGreen, 1);
		}
		dropItem(Itemizer.CopperCoin, 5 + rand.nextInt(10));
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	protected String getLivingSound() {
		return null;
	}

	protected String getDeathSound() {
		return null;
	}

	protected String getHurtSound() {
		return null;
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (isInWater()) {
			heal(0.6f);
		}
	}

	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(16, new Integer(5));
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6.0);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(60.0);
	}

	public boolean isAIEnabled() {
		return true;
	}

	public void attackEntityWithRangedAttack(final EntityLivingBase var1, final float f) {
		final float rotationPitch = var1.rotationPitch;
		final float rotationYaw = var1.rotationYaw;
		final double x = var1.posX;
		final double y = var1.posY + 1.62 - var1.yOffset;
		final double z = var1.posZ;
		final Vec3 worldVector = Vec3.createVectorHelper(x, y, z);
		final float yawAngleCos = MathHelper.cos(-rotationYaw * 0.01745329f - 3.1415927f);
		final float yawAngleSin = MathHelper.sin(-rotationYaw * 0.01745329f - 3.1415927f);
		final float pitchAngle = -MathHelper.cos(-rotationPitch * 0.01745329f);
		final float yVec = MathHelper.sin(-rotationPitch * 0.01745329f);
		final float xVec = yawAngleSin * pitchAngle;
		final float zVec = yawAngleCos * pitchAngle;
		final double multiplyer = 30.0;
		final Vec3 worldVector2 = worldVector.addVector(xVec * multiplyer, yVec * multiplyer, zVec * multiplyer);
		final MovingObjectPosition objPos = worldObj.rayTraceBlocks(worldVector, worldVector2);
		if (objPos != null && objPos.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
			int blockX = objPos.blockX;
			int blockY = objPos.blockY;
			int blockZ = objPos.blockZ;
			final int side = objPos.sideHit;
			if (side == 0) {
				--blockY;
			}
			if (side == 1) {
				++blockY;
			}
			if (side == 2) {
				--blockZ;
			}
			if (side == 3) {
				++blockZ;
			}
			if (side == 4) {
				--blockX;
			}
			if (side == 5) {
				++blockX;
			}
			if (!worldObj.isRemote) {
				for (int i = 0; i < 8; ++i) {
					worldObj.spawnEntityInWorld(new EntityTangleFall(worldObj, blockX + 0.5, blockY + 25.0, blockZ + 0.5, this));
				}
				var1.worldObj.playSoundAtEntity(var1, "nevermine:VineWizardFire", 1.0f, 1.0f);
			}
			var1.getLook(1.0f);
		}
	}
}
