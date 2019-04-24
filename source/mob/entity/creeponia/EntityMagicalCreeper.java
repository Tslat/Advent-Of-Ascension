package net.nevermine.mob.entity.creeponia;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.common.nevermine;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.SpecialBlockizer;
import net.nevermine.projectiles.enemy.EntityCreeperShot;

public class EntityMagicalCreeper extends EntityCreeper {
	public EntityMagicalCreeper(final World par1World) {
		super(par1World);
		setSize(0.6f, 2.3f);
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

	public boolean getCanSpawnHere() {
		final int counter = worldObj.getEntitiesWithinAABB(EntityMob.class, boundingBox.expand(20.0, 10.0, 20.0)).size();
		return counter <= 5 && worldObj.difficultySetting != EnumDifficulty.PEACEFUL && rand.nextInt(nevermine.isMultiplayer ? 4 : 45) == 0 && posY < 50.0 && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	protected boolean isValidLightLevel() {
		return true;
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

	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (rand.nextInt(70) == 37 && entityToAttack != null) {
			if (entityToAttack instanceof EntityPlayer && ((EntityPlayer)entityToAttack).capabilities.isCreativeMode) {
				entityToAttack = null;
				return;
			}
			final Entity var1 = entityToAttack;
			final EntityCreeperShot var2 = new EntityCreeperShot(worldObj, this, 5.0f);
			final double var3 = var1.posX - posX;
			final double var4 = var1.posY + var1.getEyeHeight() - 1.100000023841858 - var2.posY;
			final double var5 = var1.posZ - posZ;
			final float var6 = MathHelper.sqrt_double(var3 * var3 + var5 * var5) * 0.2f;
			var2.setThrowableHeading(var3, var4 + var6, var5, 1.6f, 12.0f);
			playSound("nevermine:MagicalCreeper", 1.0f, 1.0f / (getRNG().nextFloat() * 0.4f + 0.8f));
			worldObj.spawnEntityInWorld(var2);
		}
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.95);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(50.0);
	}
}
