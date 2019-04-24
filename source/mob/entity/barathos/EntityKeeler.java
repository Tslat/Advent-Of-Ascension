package net.nevermine.mob.entity.barathos;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Weaponizer;

public class EntityKeeler extends EntityMob {
	public EntityKeeler(final World par1World) {
		super(par1World);
		setSize(0.7f, 1.4f);
	}

	protected String getLivingSound() {
		return "nevermine:KeelerLiving";
	}

	protected String getDeathSound() {
		return "nevermine:KeelerDeath";
	}

	protected String getHurtSound() {
		return "nevermine:KeelerHit";
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("nevermine:VeryHeavyStep", 1.25f, 1.0f);
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && posY < 50.0 && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		if (rand.nextInt(60) == 13) {
			dropItem(Weaponizer.BaronBow, 1);
		}
		if (rand.nextInt(200) == 135) {
			dropItem(Itemizer.UpgradeKitRocky, 1);
		}
		dropItem(Itemizer.CopperCoin, 5 + rand.nextInt(10));
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		final float curHp = getHealth();
		if (curHp > 0.0f && curHp < 8.0f) {
			setHealth(35.0f);
			playSound("nevermine:KeelerRevive", 1.25f, 1.0f);
		}
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
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(35.0);
	}
}
