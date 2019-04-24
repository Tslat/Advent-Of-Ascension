package net.nevermine.mob.entity.greckon;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;

public class EntitySkullCreature extends EntityMob {
	private Boolean invuln;

	public EntitySkullCreature(final World par1World) {
		super(par1World);
		invuln = false;
		setSize(1.2f, 1.9f);
	}

	protected String getLivingSound() {
		return "nevermine:SkullCreatureLiving";
	}

	protected String getDeathSound() {
		return "nevermine:SkullCreatureDeath";
	}

	protected String getHurtSound() {
		return "nevermine:SkullCreatureHit";
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("mob.pig.step", 0.55f, 1.0f);
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		dropItem(Itemizer.CopperCoin, 5 + rand.nextInt(10));
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return (entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null;
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.4);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40.0);
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		if ((getHealth() < 5.0f && getHealth() > 0.0f) || (getHealth() < 15.0f && getHealth() > 10.0f)) {
			heal(0.2f);
			invuln = true;
		}
		else {
			invuln = false;
		}
	}

	public boolean isEntityInvulnerable() {
		return invuln;
	}
}
