package net.nevermine.mob.entity.shyrelands;

import net.minecraft.block.Block;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.Item;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.SpecialBlockizer;

public class EntityShyreKnight extends EntityMob {
	public EntityShyreKnight(World par1World) {
		super(par1World);
		setSize(0.6F, 2.5F);
	}

	protected String getLivingSound() {
		return null;
	}

	protected String getDeathSound() {
		return "random.anvil_land";
	}

	protected String getHurtSound() {
		return "random.anvil_land";
	}

	protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_) {
		playSound("mob.pig.step", 1.0F, 1.0F);
	}

	protected void dropFewItems(boolean par1, int par2) {
		dropItem(Itemizer.CoinsShyrelands, 3 + this.rand.nextInt(8));

		dropItem(Itemizer.CopperCoin, 4);

		if (this.rand.nextInt(7) == 2) {
			dropItem(Item.getItemFromBlock(SpecialBlockizer.LightBanner), 1);
		}
		else if (this.rand.nextInt(7) == 2) {
			dropItem(Item.getItemFromBlock(SpecialBlockizer.ShyreBanner), 1);
		}
	}

	public boolean getCanSpawnHere() {
		return (this.worldObj.difficultySetting != EnumDifficulty.PEACEFUL) && (this.worldObj.checkNoEntityCollision(this.boundingBox)) && (this.posY < 35.0D) && (this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty()) && (!this.worldObj.isAnyLiquid(this.boundingBox));
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	protected net.minecraft.entity.Entity findPlayerToAttack() {
		net.minecraft.entity.player.EntityPlayer entityPlayer = this.worldObj.getClosestVulnerablePlayerToEntity(this, 16.0D);
		return (entityPlayer != null) && (canEntityBeSeen(entityPlayer)) ? entityPlayer : null;
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();

		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(7.0D);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0D);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.1D);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8D);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(120.0D);
	}
}
