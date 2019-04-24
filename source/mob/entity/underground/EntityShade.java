package net.nevermine.mob.entity.underground;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;

public class EntityShade extends EntityMob {
	public EntityShade(final World par1World) {
		super(par1World);
		setSize(0.6f, 1.5f);
	}

	protected String getLivingSound() {
		return "nevermine:ShadeLiving";
	}

	protected String getDeathSound() {
		return "nevermine:ShadeDeath";
	}

	protected String getHurtSound() {
		return "nevermine:ShadeHit";
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && posY < 30.0 && isValidLightLevel() && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty();
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("mob.pig.step", 0.15f, 1.0f);
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return (entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null;
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		if (rand.nextInt(40) == 0) {
			dropItem(Itemizer.BookOfShadows, 1);
		}
		if (rand.nextInt(15) == 4) {
			dropItem(Itemizer.RealmstoneDeeplands, 2);
		}
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.1);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(2.1);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(25.0);
	}
}
