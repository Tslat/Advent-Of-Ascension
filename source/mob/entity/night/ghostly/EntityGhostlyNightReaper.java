package net.nevermine.mob.entity.night.ghostly;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.event.dimensional.overworld.SoulScurryEvent;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.SpecialBlockizer;

public class EntityGhostlyNightReaper extends EntityMob {
	public EntityGhostlyNightReaper(final World par1World) {
		super(par1World);
		setSize(1.2f, 2.5f);
	}

	protected String getLivingSound() {
		return "nevermine:ReaperLiving";
	}

	protected String getDeathSound() {
		return "nevermine:ReaperDeath";
	}

	protected String getHurtSound() {
		return "nevermine:ReaperHit";
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		if (rand.nextInt(3) == 1) {
			dropItem(Itemizer.GhostStone, 4);
		}
		if (rand.nextInt(5) == 3) {
			dropItem(Item.getItemFromBlock(SpecialBlockizer.GhostlyBanner), 1);
		}
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && SoulScurryEvent.isScurry() && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox) && !worldObj.isDaytime();
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
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(11.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.7);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(50.0);
	}

	public boolean attackEntityAsMob(final Entity par1) {
		if (super.attackEntityAsMob(par1)) {
			if (par1 instanceof EntityLivingBase) {
				((EntityLivingBase)par1).addPotionEffect(new PotionEffect(Potion.blindness.id, 30, 7));
			}
			return true;
		}
		return false;
	}
}
