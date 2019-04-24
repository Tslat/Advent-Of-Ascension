package net.nevermine.mob.entity.greckon;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.assist.AddPackets;
import net.nevermine.gui.MobHitPacket;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.SpecialBlockizer;
import net.nevermine.mob.placement.EntityNoBows;
import net.nevermine.mob.placement.EntityNoRange;

public class EntityCenturion extends EntityMob implements EntityNoRange, EntityNoBows {
	public EntityCenturion(final World par1World) {
		super(par1World);
		setSize(1.2f, 2.5f);
	}

	protected String getLivingSound() {
		return "nevermine:CenturionLiving";
	}

	protected String getDeathSound() {
		return "nevermine:CenturionDeath";
	}

	protected String getHurtSound() {
		return "nevermine:CenturionHit";
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		if (rand.nextInt(2) == 0) {
			dropItem(Itemizer.CopperCoin, 4);
		}
		if (rand.nextInt(30) == 25) {
			dropItem(Itemizer.RealmstoneFragment5, 1);
		}
		if (rand.nextInt(4) == 3) {
			dropItem(Item.getItemFromBlock(SpecialBlockizer.FragmentBanner), 1);
		}
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
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(15.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(300.0);
	}

	public boolean attackEntityAsMob(final Entity par1) {
		if (super.attackEntityAsMob(par1)) {
			if (par1 instanceof EntityPlayer && par1 instanceof EntityPlayerMP) {
				AddPackets.network.sendTo(new MobHitPacket(300, 7), (EntityPlayerMP)par1);
			}
			return true;
		}
		return false;
	}
}
