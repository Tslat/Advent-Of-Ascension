package net.nevermine.mob.entity.creeponia;

import net.minecraft.block.Block;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.common.nevermine;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.SpecialBlockizer;

public class EntityCaveCreepoid extends EntityCreeper {
	public EntityCaveCreepoid(final World par1World) {
		super(par1World);
		setSize(0.6f, 1.66f);
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

	protected void dropFewItems(final boolean par1, final int par2) {
		dropItem(Itemizer.CopperCoin, 2 + rand.nextInt(5));
		if (rand.nextInt(2) == 1) {
			dropItem(Itemizer.CoinsCreeponia, 3);
		}
		if (rand.nextInt(4) == 2) {
			dropItem(Item.getItemFromBlock(SpecialBlockizer.CreepoidBanner), 1);
		}
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("mob.pig.step", 1.0f, 1.0f);
	}

	public boolean attackEntityFrom(final DamageSource par1DamageSource, final float par2) {
		if (par1DamageSource.isExplosion()) {
			return false;
		}
		if (!par1DamageSource.isFireDamage() && rand.nextInt(3) == 2) {
			worldObj.createExplosion(this, posX, posY, posZ, 2.0f, false);
			return false;
		}
		return super.attackEntityFrom(par1DamageSource, par2);
	}

	public boolean getCanSpawnHere() {
		final int counter = worldObj.getEntitiesWithinAABB(EntityMob.class, boundingBox.expand(20.0, 10.0, 20.0)).size();
		return counter <= 5 && worldObj.difficultySetting != EnumDifficulty.PEACEFUL && posY < 19.0 && rand.nextInt(nevermine.isMultiplayer ? 3 : 35) == 0 && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(7.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.95);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(50.0);
	}
}
