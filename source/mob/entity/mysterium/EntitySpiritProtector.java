package net.nevermine.mob.entity.mysterium;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.nevermine.projectiles.enemy.EntitySkelemanShot;

public class EntitySpiritProtector extends EntityMob implements IRangedAttackMob {
	public EntitySpiritProtector(final World par1World) {
		super(par1World);
		final float moveSpeed = 0.25f;
		tasks.addTask(7, new EntityAIArrowAttack(this, 0.25, 4, 10.0f));
		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(2, new EntityAIRestrictSun(this));
		tasks.addTask(3, new EntityAIFleeSun(this, (double)moveSpeed));
		tasks.addTask(5, new EntityAIWander(this, (double)moveSpeed));
		tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));
		tasks.addTask(6, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		setSize(0.9f, 2.3f);
	}

	protected String getLivingSound() {
		return "nevermine:SpiritLiving";
	}

	protected String getDeathSound() {
		return "nevermine:SpiritDeath";
	}

	protected String getHurtSound() {
		return "nevermine:SpiritLiving";
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return (entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null;
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6.0);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(45.0);
	}

	public boolean isAIEnabled() {
		return true;
	}

	public void attackEntityWithRangedAttack(final EntityLivingBase var1, final float f) {
		final EntitySkelemanShot var2 = new EntitySkelemanShot(worldObj, this, 6.0f);
		playSound("nevermine:SpiritGuardianShot", 1.0f, 1.0f / (getRNG().nextFloat() * 0.4f + 0.8f));
		worldObj.spawnEntityInWorld(var2);
	}
}
