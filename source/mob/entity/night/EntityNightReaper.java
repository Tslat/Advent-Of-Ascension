package net.nevermine.mob.entity.night;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.SpecialBlockizer;

public class EntityNightReaper extends EntityMob {
	public EntityNightReaper(final World par1World) {
		super(par1World);
		setSize(0.9f, 2.4f);
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
		if (rand.nextInt(2) == 0) {
			dropItem(Itemizer.CopperCoin, 2 + rand.nextInt(3));
		}
		if (rand.nextInt(7) == 0) {
			dropItem(dropBanner(), 1);
		}
	}

	private Item dropBanner() {
		return Item.getItemFromBlock(SpecialBlockizer.EnergyBanner);
	}

	public boolean getCanSpawnHere() {
		final Block b = worldObj.getBlock((int)posX, (int)posY - 1, (int)posZ);
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && isValidLightLevel() && (b == Blocks.grass || b == Blocks.dirt || b == Blocks.stained_hardened_clay || b == Blocks.stone || b == Blocks.sand || b == Blocks.snow || b == Blocks.snow_layer || b == Blocks.hardened_clay || b == Blocks.clay) && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox) && !worldObj.isDaytime();
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
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(5.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.7);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(50.0);
	}

	public boolean attackEntityAsMob(final Entity par1) {
		if (super.attackEntityAsMob(par1)) {
			if (par1 instanceof EntityLivingBase) {
				((EntityLivingBase)par1).addPotionEffect(new PotionEffect(Potion.blindness.id, 40, 7));
			}
			return true;
		}
		return false;
	}
}
