package net.nevermine.mob.entity.candyland;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.izer.Blockizer;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.SpecialBlockizer;
import net.nevermine.izer.equipment.Weaponizer;

public class EntityCandyCorny extends EntityMob {
	public EntityCandyCorny(final World par1World) {
		super(par1World);
		setSize(1.2f, 1.8f);
	}

	protected String getLivingSound() {
		return null;
	}

	protected String getDeathSound() {
		return "nevermine:CandyThump";
	}

	protected String getHurtSound() {
		return "nevermine:CandyThump";
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
		if (rand.nextInt(15) == 3) {
			dropItem(Itemizer.CandyCorn, 1);
		}
		if (rand.nextInt(3) == 1) {
			dropItem(Itemizer.CoinsCandyland, 2);
		}
		if (rand.nextInt(75) == 31) {
			dropItem(Weaponizer.SweetTooth, 1);
		}
		if (rand.nextInt(4) == 3) {
			dropItem(Item.getItemFromBlock(Blockizer.ChocolateBlock), 4);
		}
		else if (rand.nextInt(4) == 1) {
			dropItem(Item.getItemFromBlock(Blockizer.ChocolateBlockDark), 4);
		}
		else if (rand.nextInt(4) == 2) {
			dropItem(Item.getItemFromBlock(Blockizer.ChocolateBlockWhite), 4);
		}
		dropItem(Itemizer.CopperCoin, 3);
		if (rand.nextInt(7) == 3) {
			dropItem(Item.getItemFromBlock(SpecialBlockizer.CandyBanner), 1);
		}
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (isInWater()) {
			heal(0.4f);
		}
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return (entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null;
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(13.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0);
	}
}
