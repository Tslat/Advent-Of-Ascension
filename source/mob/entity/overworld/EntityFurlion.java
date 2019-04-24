package net.nevermine.mob.entity.overworld;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;

public class EntityFurlion extends EntityMob {
	public EntityFurlion(final World par1World) {
		super(par1World);
		setSize(1.0f, 1.2f);
	}

	protected String getLivingSound() {
		return "nevermine:FurlionLiving";
	}

	protected String getDeathSound() {
		return "nevermine:FurlionHit";
	}

	protected String getHurtSound() {
		return "nevermine:FurlionHit";
	}

	protected void fall(final float var1) {
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("mob.pig.step", 1.0f, 1.0f);
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	public void onDeath(final DamageSource var1) {
		if (!worldObj.isRemote) {
			if (var1.isFireDamage() || isBurning()) {
				dropItem(Itemizer.FurlionChop, 2);
			}
			else {
				dropItem(Itemizer.FurlionChopRaw, 2);
			}
		}
		super.onDeath(var1);
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return (entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null;
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.1);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0);
	}

	public boolean attackEntityAsMob(final Entity par1) {
		if (super.attackEntityAsMob(par1)) {
			if (par1 instanceof EntityPlayer) {
				setPositionAndUpdate(posX - 10.0 + rand.nextInt(5), posY + 15.0, posZ - 10.0 + rand.nextInt(5));
			}
			return true;
		}
		return false;
	}
}
