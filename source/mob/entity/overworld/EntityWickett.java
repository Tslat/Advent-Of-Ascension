package net.nevermine.mob.entity.overworld;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.mob.placement.EntityNoExplosions;
import net.nevermine.mob.placement.EntityNoFire;
import net.nevermine.mob.placement.EntityNoMagic;

public class EntityWickett extends EntityMob implements EntityNoFire, EntityNoMagic, EntityNoExplosions {
	public EntityWickett(final World par1World) {
		super(par1World);
		setSize(1.0f, 1.0f);
		isImmuneToFire = true;
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

	public boolean attackEntityFrom(final DamageSource par1DamageSource, final float par2) {
		final Entity entity = par1DamageSource.getSourceOfDamage();
		return !par1DamageSource.isExplosion() && !par1DamageSource.isMagicDamage() && super.attackEntityFrom(par1DamageSource, par2);
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("mob.pig.step", 1.0f, 1.0f);
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return ((entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null);
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.1);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(25.0);
	}
}
