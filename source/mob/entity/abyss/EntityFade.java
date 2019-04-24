package net.nevermine.mob.entity.abyss;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.SpecialBlockizer;

public class EntityFade extends EntityMob {
	public EntityFade(final World par1World) {
		super(par1World);
		setSize(0.6f, 2.1f);
	}

	protected String getLivingSound() {
		return "nevermine:FadeLiving";
	}

	protected String getDeathSound() {
		return "nevermine:FadeDeath";
	}

	protected String getHurtSound() {
		return "nevermine:FadeHit";
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return ((entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null);
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		if (rand.nextInt(2) == 0) {
			dropItem(Itemizer.CopperCoin, 4);
		}
		if (rand.nextInt(30) == 25) {
			dropItem(Itemizer.RealmstoneFragment4, 1);
		}
		if (rand.nextInt(4) == 3) {
			dropItem(Item.getItemFromBlock(SpecialBlockizer.FragmentBanner), 1);
		}
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	public boolean attackEntityFrom(final DamageSource par1DamageSource, final float dmg) {
		if (!worldObj.isRemote && dmg >= 1) {
			if (rand.nextInt(Math.round((float)(getHealth() / (dmg * 1.2) + 2))) == 0) {
				if (worldObj.getEntitiesWithinAABB(EntityFade.class, boundingBox.expand(10.0, 5.0, 10.0)).size() < 5) {
					final EntityFade var2 = new EntityFade(worldObj);

					var2.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
					worldObj.spawnEntityInWorld(var2);
				}
			}
		}

		return super.attackEntityFrom(par1DamageSource, dmg);
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.3);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(50.0);
	}

	public boolean attackEntityAsMob(final Entity par1) {
		if (super.attackEntityAsMob(par1)) {
			if (par1 instanceof EntityLivingBase) {
				((EntityLivingBase)par1).addPotionEffect(new PotionEffect(Potion.wither.id, 60, 2));
			}
			return true;
		}
		return false;
	}
}
