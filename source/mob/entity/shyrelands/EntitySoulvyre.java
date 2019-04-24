package net.nevermine.mob.entity.shyrelands;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.SpecialBlockizer;

public class EntitySoulvyre extends EntityMob {
	private int count = 0;
	private int timer = 0;

	public EntitySoulvyre(World par1World) {
		super(par1World);
		setSize(0.6F, 2.5F);
	}

	protected String getLivingSound() {
		return "nevermine:SoulvyreLiving";
	}

	protected String getDeathSound() {
		return "nevermine:SoulvyreDeath";
	}

	protected String getHurtSound() {
		return "nevermine:SoulvyreHit";
	}

	protected void dropFewItems(boolean par1, int par2) {
		dropItem(Itemizer.CoinsShyrelands, 2 + this.rand.nextInt(2));

		if (this.rand.nextInt(7) == 2) {
			dropItem(Item.getItemFromBlock(SpecialBlockizer.ShyreBanner), 1);
		}
	}

	protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_) {
		playSound("mob.pig.step", 1.0F, 1.0F);
	}

	public boolean getCanSpawnHere() {
		return (this.worldObj.difficultySetting != EnumDifficulty.PEACEFUL) && (this.worldObj.checkNoEntityCollision(this.boundingBox)) && (this.posY < 35.0D) && (this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty()) && (!this.worldObj.isAnyLiquid(this.boundingBox));
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (this.count > 0) {
			this.timer -= 1;
		}
		if ((this.timer == 0) && (this.count > 0)) {
			this.count -= 1;
		}
	}

	public boolean attackEntityAsMob(Entity par1Entity) {
		super.attackEntityAsMob(par1Entity);

		if ((par1Entity instanceof EntityPlayer)) {
			this.count += 1;
			this.timer = 80;
			EntityPlayer pl = (EntityPlayer)par1Entity;

			if (pl.getHealth() - count > 0.0f) {
				pl.setHealth(pl.getHealth() - count);
			}
			else {
				pl.attackEntityFrom(DamageSource.generic, count);
			}

			return true;
		}
		return false;
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	protected Entity findPlayerToAttack() {
		EntityPlayer entityPlayer = this.worldObj.getClosestVulnerablePlayerToEntity(this, 16.0D);
		return (entityPlayer != null) && (canEntityBeSeen(entityPlayer)) ? entityPlayer : null;
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();

		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(5.0D);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0D);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.1D);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8D);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(95.0D);
	}
}
