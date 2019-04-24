package net.nevermine.mob.entity.immortallis;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityUrioh extends EntityMob {
	public EntityUrioh(final World par1World) {
		super(par1World);
		setSize(2.0f, 2.0f);
	}

	protected String getLivingSound() {
		return "nevermine:ApparitionLiving";
	}

	protected String getDeathSound() {
		return "nevermine:ApparitionDeath";
	}

	protected String getHurtSound() {
		return "nevermine:ApparitionHit";
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
		return (entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null;
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (getHealth() < 10.0f) {
			setSize(0.2f, 0.2f);
		}
		else if (getHealth() < 25.0f) {
			setSize(0.4f, 0.4f);
		}
		else if (getHealth() < 40.0f) {
			setSize(0.8f, 0.8f);
		}
		else if (getHealth() < 60.0f) {
			setSize(1.2f, 1.2f);
		}
		else {
			setSize(2.0f, 2.0f);
		}
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(5.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.1);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(75.0);
	}
}
