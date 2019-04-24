package net.nevermine.mob.entity.precasia;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Weaponizer;

public class EntitySabretooth extends EntityMob {
	public EntitySabretooth(final World par1World) {
		super(par1World);
		final float moveSpeed = 0.65f;
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, (double)moveSpeed, false));
		tasks.addTask(6, new EntityAIWander(this, (double)moveSpeed));
		tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 32.0f));
		tasks.addTask(7, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(64.0);
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		tasks.addTask(7, new EntityAILeapAtTarget(this, 0.3f));
		setSize(1.2f, 1.8f);
	}

	protected boolean isAIEnabled() {
		return true;
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		dropItem(Itemizer.CopperCoin, 5 + rand.nextInt(10));
		if (rand.nextInt(5) == 0) {
			dropItem(Itemizer.CoinsPrecasian, 2);
		}
		if (rand.nextInt(200) == 192) {
			dropItem(Itemizer.UpgradeKitAncient, 1);
		}
		if (rand.nextInt(500) == 363) {
			dropItem(Weaponizer.Tommy, 1);
		}
	}

	public void onUpdate() {
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((entityToAttack != null) ? 0.949999988079071 : 0.5);
		super.onUpdate();
	}

	protected String getLivingSound() {
		return "nevermine:SabretoothLiving";
	}

	protected String getDeathSound() {
		return "nevermine:SabretoothDeath";
	}

	protected String getHurtSound() {
		return "nevermine:SabretoothHit";
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("nevermine:HeavyStep", 0.85f, 1.0f);
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return (entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null;
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.3);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.46);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(60.0);
	}

	public boolean attackEntityAsMob(final Entity par1) {
		if (super.attackEntityAsMob(par1)) {
			if (par1 instanceof EntityLivingBase) {
				((EntityLivingBase)par1).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 20, 2));
			}
			return true;
		}
		return false;
	}
}
