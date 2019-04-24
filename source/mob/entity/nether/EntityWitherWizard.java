package net.nevermine.mob.entity.nether;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.SpecialBlockizer;
import net.nevermine.izer.equipment.Weaponizer;
import net.nevermine.mob.placement.EntityNoFire;
import net.nevermine.projectiles.enemy.EntityMagicBallWither;

public class EntityWitherWizard extends EntityMob implements IRangedAttackMob, EntityNoFire {
	private EntityAIArrowAttack aiArrowAttack;

	public EntityWitherWizard(final World par1World) {
		super(par1World);
		aiArrowAttack = new EntityAIArrowAttack(this, getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue(), 20, 60, 15.0f);
		final float moveSpeed = 0.45f;
		isImmuneToFire = true;
		tasks.addTask(7, new EntityAIArrowAttack(this, getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue(), 20, 64.0f));
		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(2, new EntityAIRestrictSun(this));
		tasks.addTask(5, new EntityAIWander(this, (double)moveSpeed));
		tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));
		tasks.addTask(6, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		setSize(0.6f, 2.0f);
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	private Item dropBanner() {
		return Item.getItemFromBlock(SpecialBlockizer.NetherBanner);
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	protected String getLivingSound() {
		return "nevermine:WitherWizardLiving";
	}

	protected String getDeathSound() {
		return "nevermine:WitherWizardHit";
	}

	protected String getHurtSound() {
		return "nevermine:WitherWizardHit";
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		if (rand.nextInt(15) == 4) {
			dropItem(Weaponizer.EmberStaff, 1);
		}
		dropItem(Itemizer.WitherRune, 2 + rand.nextInt(3));
		if (rand.nextInt(7) == 0) {
			dropItem(dropBanner(), 1);
		}
		dropItem(Itemizer.CopperCoin, 5 + rand.nextInt(3));
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
	}

	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(16, new Integer(5));
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40.0);
	}

	public boolean isAIEnabled() {
		return true;
	}

	public void attackEntityWithRangedAttack(final EntityLivingBase var1, final float f) {
		final EntityMagicBallWither var2 = new EntityMagicBallWither(worldObj, this, 6.0f, 3);
		final double var3 = var1.posX - posX;
		final double var4 = var1.posY + var1.getEyeHeight() - 1.100000023841858 - var2.posY;
		final double var5 = var1.posZ - posZ;
		final float var6 = MathHelper.sqrt_double(var3 * var3 + var5 * var5) * 0.2f;
		var2.setThrowableHeading(var3, var4 + var6, var5, 1.6f, 12.0f);
		playSound("nevermine:Wizard", 1.0f, 1.0f / (getRNG().nextFloat() * 0.4f + 0.8f));
		worldObj.spawnEntityInWorld(var2);
	}

	public boolean attackEntityFrom(final DamageSource par1DamageSource, final float par2) {
		playSound("nevermine:WitherWizardHit", 6.55f, 1.0f);
		return super.attackEntityFrom(par1DamageSource, par2);
	}
}
