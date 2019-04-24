package net.nevermine.mob.entity.runandor;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.SpecialBlockizer;
import net.nevermine.izer.equipment.Weaponizer;

public class EntityBouncer extends EntityMob {
	private int jumpcount;

	public EntityBouncer(final World par1World) {
		super(par1World);
		jumpcount = 0;
		setSize(0.8f, 1.3f);
	}

	protected String getLivingSound() {
		return "nevermine:BouncerLiving";
	}

	protected String getDeathSound() {
		return "nevermine:BouncerDeath";
	}

	protected String getHurtSound() {
		return "nevermine:BouncerHit";
	}

	protected void fall(final float par1) {
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
		if (rand.nextInt(45) == 37) {
			dropItem(Weaponizer.PurityRifle, 1);
		}
		if (rand.nextInt(40) == 3) {
			dropItem(Itemizer.RealmstoneBarathos, 2);
		}
		dropItem(Itemizer.CopperCoin, 5 + rand.nextInt(10));
		if (rand.nextInt(7) == 2) {
			dropItem(dropBanner(), 1);
		}
	}

	private Item dropBanner() {
		return Item.getItemFromBlock(SpecialBlockizer.RunicBanner);
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		++jumpcount;
		if (jumpcount == 70) {
			motionY = 1.2000000476837158;
			jumpcount = 0;
		}
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return (entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null;
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(5.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(50.0);
	}
}
