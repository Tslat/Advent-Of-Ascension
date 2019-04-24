package net.nevermine.mob.entity.night;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.SpecialBlockizer;
import net.nevermine.resource.soulpower.soulPowerHelper;

public class EntityGhostineAncient extends EntityMob {
	public EntityGhostineAncient(final World par1World) {
		super(par1World);
		setSize(0.9f, 2.2f);
	}

	protected String getLivingSound() {
		return "nevermine:GhostineLiving";
	}

	protected String getDeathSound() {
		return "nevermine:GhostineDeath";
	}

	protected String getHurtSound() {
		return "nevermine:GhostineHit";
	}

	private Item dropBanner() {
		return Item.getItemFromBlock(SpecialBlockizer.EnergyBanner);
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		if (rand.nextInt(2) == 0) {
			dropItem(Itemizer.CopperCoin, 2 + rand.nextInt(3));
		}
		if (rand.nextInt(7) == 0) {
			dropItem(dropBanner(), 1);
		}
	}

	public boolean attackEntityAsMob(final Entity par1) {
		if (super.attackEntityAsMob(par1)) {
			if (par1 instanceof EntityPlayer) {
				soulPowerHelper.getProperties((EntityPlayer)par1).setBarValue(soulPowerHelper.getProperties((EntityPlayer)par1).getBarValue() / 1.5f);
				((EntityPlayer)par1).addChatMessage(StringUtil.getLocale("message.mob.ghostineAncient.attack"));
			}
			return true;
		}
		return false;
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
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.1);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40.0);
	}
}
