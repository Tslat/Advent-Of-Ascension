package net.nevermine.mob.entity.shyrelands;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.SpecialBlockizer;
import net.nevermine.izer.equipment.Weaponizer;
import net.nevermine.projectiles.enemy.EntityCraexxeusShot;

public class EntityShyreTroll extends EntityMob implements IRangedAttackMob {
	private EntityAIArrowAttack aiArrowAttack = new EntityAIArrowAttack(this, getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue(), 20, 60, 15.0F);

	public EntityShyreTroll(World par1World) {
		super(par1World);
		float moveSpeed = 0.45F;
		this.tasks.addTask(7, new EntityAIArrowAttack(this, getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue(), 20, 64.0F));
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIRestrictSun(this));
		this.tasks.addTask(5, new EntityAIWander(this, moveSpeed));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(6, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		setSize(0.6F, 1.80F);
	}

	public boolean getCanSpawnHere() {
		return (this.worldObj.difficultySetting != EnumDifficulty.PEACEFUL) && (this.worldObj.checkNoEntityCollision(this.boundingBox)) && (this.posY < 35.0D) && (this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty()) && (!this.worldObj.isAnyLiquid(this.boundingBox));
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		heal(0.2F);
	}

	protected String getLivingSound() {
		return "nevermine:GoblinLiving";
	}

	protected String getDeathSound() {
		return "nevermine:GoblinDeath";
	}

	protected String getHurtSound() {
		return "nevermine:GoblinHit";
	}

	protected void dropFewItems(boolean par1, int par2) {
		dropItem(Itemizer.WindRune, 4 + this.rand.nextInt(3));

		if (this.rand.nextInt(7) == 2) {
			dropItem(Item.getItemFromBlock(SpecialBlockizer.ShinyBanner), 1);
		}
		if (this.rand.nextInt(50) == 25) {
			dropItem(Weaponizer.ShyreStaff, 1);
		}
	}

	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(16, new Integer(5));
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();

		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6.0D);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5D);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
	}

	public boolean isAIEnabled() {
		return true;
	}

	protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_) {
		playSound("mob.pig.step", 0.55F, 1.0F);
	}

	public void attackEntityWithRangedAttack(EntityLivingBase var1, float f) {
		EntityCraexxeusShot var2 = new EntityCraexxeusShot(this.worldObj, this, 5.5F, 1.0F);
		double var3 = var1.posX - this.posX;
		double var5 = var1.posY + var1.getEyeHeight() - 1.100000023841858D - var2.posY;
		double var7 = var1.posZ - this.posZ;
		float var9 = MathHelper.sqrt_double(var3 * var3 + var7 * var7) * 0.2F;
		var2.setThrowableHeading(var3, var5 + var9, var7, 1.6F, 12.0F);

		playSound("nevermine:ShyreTrollFire", 1.0F, 1.0F / (getRNG().nextFloat() * 0.4F + 0.8F));
		this.worldObj.spawnEntityInWorld(var2);
	}
}
