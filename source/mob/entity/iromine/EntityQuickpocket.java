package net.nevermine.mob.entity.iromine;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Weaponizer;

public class EntityQuickpocket extends EntityMob {
	public EntityQuickpocket(final World par1World) {
		super(par1World);
		setSize(0.6f, 1.5f);
	}

	protected String getLivingSound() {
		return "nevermine:QuickpocketLiving";
	}

	protected String getDeathSound() {
		return "nevermine:QuickpocketDeath";
	}

	protected String getHurtSound() {
		return "nevermine:QuickpocketHit";
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

	protected void dropFewItems(final boolean par1, final int par2) {
		if (rand.nextInt(20) == 13) {
			dropItem(Weaponizer.BoltRifle, 1);
		}
		if (rand.nextInt(100) == 35) {
			dropItem(Itemizer.UpgradeKitGolden, 1);
		}
		dropItem(Itemizer.CopperCoin, 5 + rand.nextInt(10));
	}

	public boolean attackEntityAsMob(final Entity par1) {
		if (super.attackEntityAsMob(par1)) {
			if (par1 instanceof EntityPlayer && ((EntityPlayer)par1).getHeldItem() != null) {
				final ItemStack var4 = ((EntityPlayer)par1).getHeldItem().copy();
				final Item var5 = ((EntityPlayer)par1).getHeldItem().getItem();
				((EntityPlayer)par1).inventory.mainInventory[((EntityPlayer)par1).inventory.currentItem] = null;
				entityDropItem(var4, 1.0f);
			}
			return true;
		}
		return false;
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("mob.pig.step", 0.55f, 1.0f);
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.3);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(60.0);
	}
}
