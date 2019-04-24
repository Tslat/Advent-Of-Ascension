package net.nevermine.mob.entity.deeplands;

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

public class EntityRockbiter extends EntityMob {
	public EntityRockbiter(final World par1World) {
		super(par1World);
		setSize(1.2f, 1.1f);
	}

	protected String getLivingSound() {
		return "nevermine:RockbiterLiving";
	}

	protected String getDeathSound() {
		return "nevermine:RockbiterDeath";
	}

	protected String getHurtSound() {
		return "nevermine:RockbiterHit";
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("mob.pig.step", 0.55f, 1.0f);
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && posY < 120.0 && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		if (rand.nextInt(4) == 3) {
			dropItem(Itemizer.RockBones, 1);
		}
		if (rand.nextInt(100) == 35) {
			dropItem(Itemizer.UpgradeKitPredator, 1);
		}
		dropItem(Itemizer.CopperCoin, 5 + rand.nextInt(10));
		if (rand.nextInt(2) == 1) {
			dropItem(Itemizer.CoinsDeeplands, 2);
		}
		if (rand.nextInt(7) == 2) {
			dropItem(dropBanner(), 1);
		}
	}

	private Item dropBanner() {
		return Item.getItemFromBlock(SpecialBlockizer.DeepBanner);
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return (entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null;
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(7.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(60.0);
	}
}
