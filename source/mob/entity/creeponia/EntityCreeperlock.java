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

public class EntityCreeperlock extends EntityCreeper {
	public EntityCreeperlock(final World par1World) {
		super(par1World);
		setSize(0.6f, 2.37f);
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
		if (rand.nextInt(6) == 2) {
			dropItem(Item.getItemFromBlock(SpecialBlockizer.CreepyBanner), 1);
		}
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("mob.pig.step", 1.0f, 1.0f);
	}

	public boolean getCanSpawnHere() {
		final int counter = worldObj.getEntitiesWithinAABB(EntityMob.class, boundingBox.expand(20.0, 10.0, 20.0)).size();
		return counter <= 5 && worldObj.difficultySetting != EnumDifficulty.PEACEFUL && rand.nextInt(nevermine.isMultiplayer ? 4 : 40) == 0 && posY < 19.0 && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		final EntityPlayer var0 = worldObj.getClosestVulnerablePlayerToEntity(this, 20.0);
		if (var0 == null || var0.capabilities.isCreativeMode || !canEntityBeSeen(var0)) {
			return;
		}
		if (rand.nextInt(120) == 34) {
			if (!worldObj.isRemote) {
				setPosition(var0.posX, var0.posY, var0.posZ);
			}
			playSound("nevermine:CreeperlockTeleport", 0.85f, 1.0f);
		}
		if (rand.nextInt(70) == 37 && entityToAttack != null) {
			final Entity var2 = entityToAttack;
			final EntityCreeperShot var3 = new EntityCreeperShot(worldObj, this, 7.0f);
			final double var4 = var2.posX - posX;
			final double var5 = var2.posY + var2.getEyeHeight() - 1.100000023841858 - var3.posY;
			final double var6 = var2.posZ - posZ;
			final float var7 = MathHelper.sqrt_double(var4 * var4 + var6 * var6) * 0.2f;
			var3.setThrowableHeading(var4, var5 + var7, var6, 1.6f, 12.0f);
			playSound("nevermine:MagicalCreeper", 1.0f, 1.0f / (getRNG().nextFloat() * 0.4f + 0.8f));
			worldObj.spawnEntityInWorld(var3);
		}
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(10.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.95);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(80.0);
	}
}
