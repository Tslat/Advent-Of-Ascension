package net.nevermine.boss.skeletalarmy;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.nevermine.boss.EntityAILeap;
import net.nevermine.izer.SpecialBlockizer;

public class EntitySkeleHopperElite extends EntityMob {
	public EntitySkeleHopperElite(final World par1World) {
		super(par1World);
		setSize(0.7f, 1.2f);
		final double moveSpeed = 0.44999998807907104;
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(3, new EntityAILeap(this, 1.0f));
		tasks.addTask(4, new EntityAIAttackOnCollide(this, moveSpeed, true));
		tasks.addTask(6, new EntityAIWander(this, moveSpeed));
		tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 16.0f));
		tasks.addTask(7, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(64.0);
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
	}

	public boolean isAIEnabled() {
		return true;
	}

	protected String getLivingSound() {
		return "mob.skeleton.say";
	}

	protected String getDeathSound() {
		return "mob.skeleton.death";
	}

	protected String getHurtSound() {
		return "mob.skeleton.hurt";
	}

	private Item dropBanner() {
		return Item.getItemFromBlock(SpecialBlockizer.SkeletalBanner);
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		if (rand.nextInt(7) == 0) {
			dropItem(dropBanner(), 1);
		}
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return (entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null;
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(12.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.1);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(64.0);
	}
}
