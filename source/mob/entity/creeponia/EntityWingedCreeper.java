package net.nevermine.mob.entity.creeponia;

import net.minecraft.block.Block;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.common.nevermine;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.SpecialBlockizer;

public class EntityWingedCreeper extends EntityCreeper {
	public EntityWingedCreeper(final World par1World) {
		super(par1World);
		setSize(0.6f, 1.625f);
	}

	protected String getLivingSound() {
		return "nevermine:CreepoidLiving";
	}

	protected String getDeathSound() {
		return "nevermine:CreepoidDeath";
	}

	protected String getHurtSound() {
		return "nevermine:CreepoidHit";
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("mob.pig.step", 1.0f, 1.0f);
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		dropItem(Itemizer.CopperCoin, 2 + rand.nextInt(5));

		if (rand.nextInt(2) == 1) {
			dropItem(Itemizer.CoinsCreeponia, 3);
		}

		if (rand.nextInt(6) == 2) {
			dropItem(Item.getItemFromBlock(SpecialBlockizer.CreepyBanner), 1);
		}
	}

	public boolean getCanSpawnHere() {
		final int counter = worldObj.getEntitiesWithinAABB(EntityMob.class, boundingBox.expand(20.0, 10.0, 20.0)).size();
		return counter <= 5 && worldObj.difficultySetting != EnumDifficulty.PEACEFUL && rand.nextInt(nevermine.isMultiplayer ? 2 : 25) == 0 && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	public boolean doesEntityNotTriggerPressurePlate() {
		return true;
	}

	protected void updateFallState(final double par1, final boolean par3) {
	}

	protected boolean canTriggerWalking() {
		return false;
	}

	protected void fall(final float par1) {
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		final EntityPlayer var1 = worldObj.getClosestVulnerablePlayerToEntity(this, 15.0);
		if (var1 == null || var1.getDistanceToEntity(this) > 15.0f) {
			return;
		}
		if (var1.posY > posY && ticksExisted % 3 == 0) {
			motionY += 0.30000001192092896;
			if (var1.posZ > posZ && ticksExisted % 3 == 0) {
				motionZ += 0.05000000074505806;
			}
			if (var1.posZ < posZ && ticksExisted % 3 == 0) {
				motionZ -= 0.05000000074505806;
			}
			if (var1.posX > posX && ticksExisted % 3 == 0) {
				motionX += 0.05000000074505806;
			}
			if (var1.posX > posX && ticksExisted % 3 == 0) {
				motionX += 0.05000000074505806;
			}
		}
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.1);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(45.0);
	}
}
