package net.nevermine.mob.entity.deeplands;

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

public class EntityCaseConstruct extends EntityMob {
	public EntityCaseConstruct(final World par1World) {
		super(par1World);
		setSize(1.2f, 1.9f);
	}

	protected String getLivingSound() {
		final int pick = rand.nextInt(6);
		if (pick == 1) {
			return "nevermine:CrystalConstructLiving1";
		}
		if (pick == 2) {
			return "nevermine:CrystalConstructLiving2";
		}
		if (pick == 3) {
			return "nevermine:CrystalConstructLiving3";
		}
		if (pick == 4) {
			return "nevermine:CrystalConstructLiving4";
		}
		if (pick == 5) {
			return "nevermine:CrystalConstructLiving6";
		}
		return "nevermine:CrystalConstructLiving5";
	}

	protected String getDeathSound() {
		return "nevermine:ConstructDeath";
	}

	protected String getHurtSound() {
		return "nevermine:ConstructHit";
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("mob.pig.step", 0.55f, 1.0f);
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && rand.nextInt(3) == 2 && posY < 120.0 && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		dropItem(Itemizer.CopperCoin, 5 + rand.nextInt(10));
		dropItem(Itemizer.RealmstoneCrystevia, 2);
		dropItem(Item.getItemFromBlock(Blockizer.Deepcase), 1);
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return (entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null;
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(20.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(200.0);
	}
}
