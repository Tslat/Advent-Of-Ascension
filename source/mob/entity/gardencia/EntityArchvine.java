package net.nevermine.mob.entity.gardencia;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;

public class EntityArchvine extends EntityMob {
	public EntityArchvine(final World par1World) {
		super(par1World);
		setSize(2.3f, 2.3f);
	}

	protected String getLivingSound() {
		return "nevermine:ArchvineLiving";
	}

	protected String getDeathSound() {
		return "nevermine:ArchvineDeath";
	}

	protected String getHurtSound() {
		return "nevermine:ArchvineHit";
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("mob.pig.step", 0.55f, 1.0f);
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (worldObj.isRaining()) {
			heal(0.2f);
		}
		heal((140.0f - getHealth()) / 100.0f);
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && posY > 66.0 && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		if (rand.nextInt(100) == 91) {
			dropItem(Itemizer.UpgradeKitFloro, 1);
		}
		if (rand.nextInt(40) == 17) {
			dropItem(Itemizer.SmallPedalBlue, 1);
		}
		if (rand.nextInt(3) == 1) {
			dropItem(Itemizer.FloracleSticks, 1);
		}
		dropItem(Itemizer.CopperCoin, 5 + rand.nextInt(10));
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return (entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null;
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(140.0);
	}
}
