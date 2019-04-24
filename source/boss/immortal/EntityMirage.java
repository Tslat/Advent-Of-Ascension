package net.nevermine.boss.immortal;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.nevermine.assist.ConfigurationHelper;
import net.nevermine.boss.EntityBoss;
import net.nevermine.izer.Itemizer;
import net.nevermine.projectiles.enemy.EntitySkelemanShot;

public class EntityMirage extends EntityMob implements IRangedAttackMob, EntityBoss {
	private int pick;
	private EntityAIArrowAttack aiArrowAttack;

	public EntityMirage(final World par1World) {
		super(par1World);
		aiArrowAttack = new EntityAIArrowAttack(this, getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue(), 20, 60, 15.0f);
		final float moveSpeed = 0.0f;
		tasks.addTask(7, new EntityAIArrowAttack(this, getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue(), 20, 64.0f));
		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(2, new EntityAIRestrictSun(this));
		tasks.addTask(5, new EntityAIWander(this, (double)moveSpeed));
		tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));
		tasks.addTask(6, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		setSize(0.7f, 2.3f);
	}

	protected boolean canDespawn() {
		return true;
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	protected String getLivingSound() {
		final int pick = rand.nextInt(3);
		if (pick == 1) {
			return "nevermine:ImmortalLiving1";
		}
		if (pick == 2) {
			return "nevermine:ImmortalLiving2";
		}
		return "nevermine:ImmortalLiving3";
	}

	protected String getDeathSound() {
		return "nevermine:ImmortalDeath";
	}

	protected String getHurtSound() {
		return "random.anvil_land";
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		dropItem(Itemizer.ProgressCoin3, 1);
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (rand.nextInt(80) == 62) {
			playSound("nevermine:MirageTeleport", 1.0f, 1.0f / (getRNG().nextFloat() * 0.4f + 0.8f));
			if (!worldObj.isRemote) {
				if (worldObj.provider.dimensionId == ConfigurationHelper.immortallis) {
					pick = rand.nextInt(4);

					if (pick == 1) {
						setLocationAndAngles(167.0, 24.0, 8.0, rand.nextFloat() * 360.0f, 0.0f);
					}
					else if (pick == 2) {
						setLocationAndAngles(168.0, 24.0, -2.0, rand.nextFloat() * 360.0f, 0.0f);
					}
					else if (pick == 3) {
						setLocationAndAngles(177.0, 24.0, 8.0, rand.nextFloat() * 360.0f, 0.0f);
					}
					else {
						setLocationAndAngles(177.0, 24.0, -2.0, rand.nextFloat() * 360.0f, 0.0f);
					}
				}
				else {
					pick = rand.nextInt(4);

					if (pick == 1) {
						setLocationAndAngles(posX + 5, worldObj.getHeightValue((int)posX + 5, (int)posZ + 5), posZ + 5, rand.nextFloat() * 360.0f, 0.0f);
					}
					else if (pick == 2) {
						setLocationAndAngles(posX - 5, worldObj.getHeightValue((int)posX - 5, (int)posZ + 5), posZ + 5, rand.nextFloat() * 360.0f, 0.0f);
					}
					else if (pick == 3) {
						setLocationAndAngles(posX + 5, worldObj.getHeightValue((int)posX + 5, (int)posZ - 5), posZ - 5, rand.nextFloat() * 360.0f, 0.0f);
					}
					else {
						setLocationAndAngles(posX - 5, worldObj.getHeightValue((int)posX - 5, (int)posZ - 5), posZ - 5, rand.nextFloat() * 360.0f, 0.0f);
					}
				}
			}
		}
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(3.0);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.0);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(750.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0);
	}

	public boolean isAIEnabled() {
		return true;
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("mob.pig.step", 0.55f, 1.0f);
	}

	public void attackEntityWithRangedAttack(final EntityLivingBase var1, final float f) {
		final EntitySkelemanShot var2 = new EntitySkelemanShot(worldObj, this, 8.0f);
		final double var3 = var1.posX - posX;
		final double var4 = var1.posY + var1.getEyeHeight() - 1.100000023841858 - var2.posY;
		final double var5 = var1.posZ - posZ;
		final float var6 = MathHelper.sqrt_double(var3 * var3 + var5 * var5) * 0.2f;
		var2.setThrowableHeading(var3, var4 + var6, var5, 1.6f, 12.0f);
		playSound("nevermine:MirageFire", 1.0f, 1.0f / (getRNG().nextFloat() * 0.4f + 0.8f));
		worldObj.spawnEntityInWorld(var2);
	}
}
