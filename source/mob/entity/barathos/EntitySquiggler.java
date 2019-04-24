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

public class EntitySquiggler extends EntityMob {
	public EntitySquiggler(final World par1World) {
		super(par1World);
		setSize(2.8f, 1.5f);
	}

	protected String getLivingSound() {
		return "nevermine:SquigglerLiving";
	}

	protected String getDeathSound() {
		return "nevermine:SquigglerDeath";
	}

	protected String getHurtSound() {
		return "nevermine:SquigglerHit";
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("mob.pig.step", 0.55f, 1.0f);
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && posY < 20.0 && rand.nextInt(5) == 2 && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		if (rand.nextInt(2) == 1) {
			dropItem(Weaponizer.UnderworldGreatblade, 1);
		}
		dropItem(Itemizer.SilverCoin, 5 + rand.nextInt(10));
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		final EntityPlayer var1 = worldObj.getClosestVulnerablePlayerToEntity(this, 20.0);
		if (var1 == null || var1.capabilities.isCreativeMode || var1.getDistanceToEntity(this) > 20.0f) {
			return;
		}

		var1.setFire(8);
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return ((entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null);
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.3);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(60.0);
	}
}
