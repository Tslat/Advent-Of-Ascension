package net.nevermine.mob.entity.underground;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;
import net.nevermine.mob.placement.EntityNoMelee;

public class EntityEverbeast extends EntityMob implements EntityNoMelee {
	public EntityEverbeast(final World par1World) {
		super(par1World);
		setSize(1.5f, 2.6f);
	}

	protected String getLivingSound() {
		return "nevermine:EverbeastLiving";
	}

	protected String getDeathSound() {
		return "nevermine:EverbeastHit";
	}

	protected String getHurtSound() {
		return "nevermine:EverbeastHit";
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("mob.pig.step", 0.85f, 1.0f);
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return ((entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null);
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && posY < 25.0 && isValidLightLevel() && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		dropItem(Itemizer.AmethystIngot, 6);
		if (rand.nextInt(4) > 1) {
			dropItem(Itemizer.IngotLimonite, 8);
		}
		if (rand.nextInt(1) == 0) {
			dropItem(Itemizer.IngotRosite, 6);
		}
		if (rand.nextInt(2) == 0) {
			dropItem(Itemizer.IngotJade, 5);
		}
		if (rand.nextInt(3) == 0) {
			dropItem(Itemizer.IngotSapphire, 3);
		}
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(24.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(150.0);
	}

	public boolean attackEntityAsMob(final Entity par1) {
		if (super.attackEntityAsMob(par1)) {
			if (par1 instanceof EntityLivingBase) {
				((EntityLivingBase)par1).addPotionEffect(new PotionEffect(Potion.confusion.id, 140, 14));
			}
			return true;
		}
		return false;
	}

	public boolean attackEntityFrom(final DamageSource par1DamageSource, final float par2) {
		final Entity entity = par1DamageSource.getSourceOfDamage();
		return (par1DamageSource.isProjectile() || entity instanceof EntityArrow || entity instanceof EntityThrowable || par1DamageSource.isMagicDamage()) && super.attackEntityFrom(par1DamageSource, par2);
	}
}
