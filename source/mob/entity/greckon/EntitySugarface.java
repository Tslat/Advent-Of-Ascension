package net.nevermine.mob.entity.greckon;

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
import net.nevermine.izer.equipment.Weaponizer;

public class EntitySugarface extends EntityMob {
	public EntitySugarface(final World par1World) {
		super(par1World);
		setSize(1.2f, 1.9f);
	}

	protected String getLivingSound() {
		return "nevermine:SugarfaceLiving";
	}

	protected String getDeathSound() {
		return "nevermine:SugarfaceDeath";
	}

	protected String getHurtSound() {
		return "nevermine:SugarfaceHit";
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
		if (rand.nextInt(45) == 30) {
			dropItem(Weaponizer.GhoulCannon, 1);
		}
		if (rand.nextInt(20) == 3) {
			dropItem(Itemizer.Ghoulasm, 1);
		}
		dropItem(Itemizer.CopperCoin, 5 + rand.nextInt(10));
		if (rand.nextInt(7) == 2) {
			dropItem(dropBanner(), 1);
		}
	}

	private Item dropBanner() {
		return Item.getItemFromBlock(SpecialBlockizer.GhoulBanner);
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return (entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null;
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.7);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(50.0);
	}

	public boolean attackEntityAsMob(final Entity par1Entity) {
		super.attackEntityAsMob(par1Entity);
		if (entityToAttack != null) {
			if (entityToAttack instanceof EntityPlayer) {
				final float playHP = ((EntityPlayer)entityToAttack).getHealth();
				final float mobHP = getHealth();
				if (playHP / 20.0f < mobHP / 50.0f) {
					par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), 10.0f);
				}
			}
			return true;
		}
		return false;
	}
}
