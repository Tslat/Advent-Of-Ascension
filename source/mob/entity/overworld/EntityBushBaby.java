package net.nevermine.mob.entity.overworld;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Weaponizer;
import net.nevermine.mob.ai.EntityAIHighHop;

public class EntityBushBaby extends EntityMob {
	public EntityBushBaby(final World par1World) {
		super(par1World);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(3, new EntityAIHighHop(this, 1.0f));
		tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0, false));
		tasks.addTask(4, new EntityAIAttackOnCollide(this, EntityVillager.class, 1.0, true));
		tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0));
		tasks.addTask(6, new EntityAIMoveThroughVillage(this, 1.0, false));
		tasks.addTask(7, new EntityAIWander(this, 1.0));
		tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));
		tasks.addTask(8, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, 0, false));
		setSize(1.0f, 1.0f);
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		if (rand.nextInt(30) == 13) {
			dropItem(Weaponizer.GravityBlaster, 1);
		}
		if (rand.nextInt(2) == 1) {
			dropItem(Itemizer.RealmstonePrecasia, 2);
		}
		dropItem(Itemizer.CopperCoin, 5 + rand.nextInt(10));
	}

	protected String getLivingSound() {
		return "nevermine:BushBabyLiving";
	}

	protected String getDeathSound() {
		return "nevermine:BushBabyDeath";
	}

	protected String getHurtSound() {
		return "nevermine:BushBabyHit";
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("mob.pig.step", 0.55f, 1.0f);
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 32.0);
		return (entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null;
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(5.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.0);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.44);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0);
	}

	protected boolean isAIEnabled() {
		return true;
	}
}
