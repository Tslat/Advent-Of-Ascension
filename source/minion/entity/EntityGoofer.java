package net.nevermine.minion.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityGoofer extends EntityTameable {
	public EntityGoofer(final World par1World) {
		super(par1World);
		setSize(1.5f, 1.2f);
		getNavigator().setAvoidsWater(true);
		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(3, new EntityAILeapAtTarget(this, 0.5f));
		tasks.addTask(4, new EntityAIAttackOnCollide(this, getMoveSpeed(), true));
		tasks.addTask(5, new EntityAIFollowOwner(this, getMoveSpeed(), 10.0f, 2.0f));
		tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));
		targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
		targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
		targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
		tasks.addTask(5, new EntityAIAttackOnCollide(this, EntityPlayer.class, getMoveSpeed(), false));
		targetTasks.addTask(6, new EntityAINearestAttackableTarget(this, EntityLiving.class, 0, false, true, IMob.mobSelector));
	}

	public boolean attackEntityAsMob(final Entity par1Entity) {
		if (rand.nextInt(6) == 4) {
			return par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), 0.0f);
		}
		return par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), 30.0f);
	}

	public double getMoveSpeed() {
		return getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue();
	}

	public boolean isAIEnabled() {
		return true;
	}

	public EntityGoofer(final World par1World, final EntityPlayer p) {
		this(par1World);
		func_152115_b(p.getUniqueID().toString());
		setTamed(true);
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5199999809265137);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(270.0);
	}

	public boolean interact(final EntityPlayer par1EntityPlayer) {
		final ItemStack var2 = par1EntityPlayer.inventory.getCurrentItem();
		if (var2 != null && var2.getItem() == Items.cookie) {
			heal(20.0f);
			playLivingSound();
			final ItemStack itemStack = var2;
			--itemStack.stackSize;
		}
		if (isTamed()) {
			if (var2 != null) {
			}
		}
		else {
			setTamed(true);
			func_152115_b(par1EntityPlayer.getUniqueID().toString());
		}
		return super.interact(par1EntityPlayer);
	}

	public void onUpdate() {
		super.onUpdate();
	}

	public EntityAgeable createChild(final EntityAgeable var1) {
		return null;
	}

	protected String getLivingSound() {
		final int pick = rand.nextInt(5);
		if (pick == 1) {
			return "nevermine:CeleveClownLiving1";
		}
		if (pick == 2) {
			return "nevermine:CeleveClownLiving2";
		}
		if (pick == 3) {
			return "nevermine:CeleveClownLiving3";
		}
		if (pick == 4) {
			return "nevermine:CeleveClownLiving4";
		}
		return "nevermine:CeleveClownLiving5";
	}

	protected String getDeathSound() {
		return "nevermine:CeleveClownHit";
	}

	protected String getHurtSound() {
		return "nevermine:CeleveClownHit";
	}
}
