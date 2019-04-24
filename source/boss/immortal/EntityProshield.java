package net.nevermine.boss.immortal;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.nevermine.boss.EntityBoss;
import net.nevermine.izer.Itemizer;

public class EntityProshield extends EntityMob implements EntityBoss {
	public EntityProshield(final World par1World) {
		super(par1World);
		setSize(0.8f, 2.4f);
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

	protected boolean canDespawn() {
		return true;
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("mob.pig.step", 1.0f, 1.0f);
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		dropItem(Itemizer.ProgressCoin2, 1);
	}

	public boolean attackEntityFrom(final DamageSource par1DamageSource, final float par2) {
		final Entity entity = par1DamageSource.getSourceOfDamage();
		if (entity instanceof EntityPlayer) {
			entity.attackEntityFrom(DamageSource.causeMobDamage(this), 10.0f);
		}
		if (entity instanceof EntityThrowable && ((EntityThrowable)entity).getThrower() != null) {
			((EntityThrowable)entity).getThrower().attackEntityFrom(DamageSource.causeMobDamage(this), 10.0f);
		}
		if (entity instanceof EntityArrow) {
			par1DamageSource.getEntity().attackEntityFrom(DamageSource.causeMobDamage(this), 10.0f);
		}
		return super.attackEntityFrom(par1DamageSource, par2);
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return (entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null;
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(5.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.1);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(500.0);
	}
}
