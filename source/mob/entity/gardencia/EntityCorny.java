package net.nevermine.mob.entity.gardencia;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;

public class EntityCorny extends EntityMob {
	public EntityCorny(final World par1World) {
		super(par1World);
		setSize(1.2f, 1.8f);
	}

	protected String getLivingSound() {
		return null;
	}

	protected String getDeathSound() {
		return "nevermine:PlantThump";
	}

	protected String getHurtSound() {
		return "nevermine:PlantThump";
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("mob.pig.step", 0.55f, 1.0f);
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && posY > 66.0 && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		if (rand.nextInt(40) == 17) {
			dropItem(Itemizer.SmallPedalPurple, 1);
		}
		if (rand.nextInt(3) == 1) {
			dropItem(Itemizer.GoldicapPedals, 1);
		}
		dropItem(Itemizer.CopperCoin, 5 + rand.nextInt(10));
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (worldObj.isRaining()) {
			heal(0.2f);
		}
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return (entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null;
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(11.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0);
	}
}
