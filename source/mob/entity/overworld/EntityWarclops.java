package net.nevermine.mob.entity.overworld;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityWarclops extends EntityMob {
	public EntityWarclops(final World par1World) {
		super(par1World);
		setSize(0.6f, 2.5f);
	}

	protected String getLivingSound() {
		return "nevermine:CyclopsLiving";
	}

	protected String getDeathSound() {
		return "nevermine:CyclopsDeath";
	}

	protected String getHurtSound() {
		return "nevermine:CyclopsHit";
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("mob.pig.step", 1.0f, 1.0f);
	}

	public boolean getCanSpawnHere() {
		final Block b = worldObj.getBlock((int)posX, (int)posY - 1, (int)posZ);
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && (b == Blocks.grass || b == Blocks.dirt || b == Blocks.stone) && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
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
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(5.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.1);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0);
	}

	public boolean attackEntityAsMob(final Entity par1Entity) {
		super.attackEntityAsMob(par1Entity);
		if (entityToAttack != null) {
			if (entityToAttack instanceof EntityPlayer && ((EntityPlayer)entityToAttack).getEntityAttribute(SharedMonsterAttributes.knockbackResistance).getAttributeValue() != 1.0) {
				final float f4 = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
				par1Entity.addVelocity(motionX * 1.5 * 0.6000000238418579 / f4, 0.1, motionZ * 1.5 * 0.6000000238418579 / f4);
			}
			if (entityToAttack instanceof EntityLiving) {
				((EntityLiving)entityToAttack).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 50, 0));
			}
			return true;
		}
		return false;
	}
}
