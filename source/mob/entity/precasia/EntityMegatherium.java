package net.nevermine.mob.entity.precasia;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;
import net.nevermine.mob.placement.EntityNoBows;
import net.nevermine.mob.placement.EntityNoRange;

public class EntityMegatherium extends EntityMob implements EntityNoRange, EntityNoBows {
	public EntityMegatherium(final World par1World) {
		super(par1World);
		setSize(2.5f, 3.9f);
	}

	protected String getLivingSound() {
		return "nevermine:MegatheriumLiving";
	}

	protected String getDeathSound() {
		return "nevermine:MegatheriumDeath";
	}

	protected String getHurtSound() {
		return "nevermine:MegatheriumHit";
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("nevermine:VeryHeavyStep", 1.65f, 1.0f);
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		dropItem(Itemizer.CopperCoin, 5 + rand.nextInt(10));
		if (rand.nextInt(2) == 0) {
			dropItem(Itemizer.CoinsPrecasian, 1);
		}
		dropItem(Itemizer.Ivory, 4);
		if (rand.nextInt(200) == 142) {
			dropItem(Itemizer.UpgradeKitAncient, 1);
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
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(250.0);
	}

	public boolean attackEntityFrom(final DamageSource par1DamageSource, final float par2) {
		final Entity entity = par1DamageSource.getSourceOfDamage();
		return !par1DamageSource.isProjectile() && !(entity instanceof EntityArrow) && !(entity instanceof EntityThrowable) && super.attackEntityFrom(par1DamageSource, par2);
	}
}
