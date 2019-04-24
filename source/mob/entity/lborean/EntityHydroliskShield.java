package net.nevermine.mob.entity.lborean;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;
import net.nevermine.mob.placement.EntityNoMelee;
import net.nevermine.mob.placement.EntityNoRange;

public class EntityHydroliskShield extends EntityMob implements EntityNoRange, EntityNoMelee {
	public EntityHydroliskShield(final World par1World) {
		super(par1World);
		setSize(1.7f, 3.6f);
	}

	protected String getLivingSound() {
		return "nevermine:HydroliskLiving";
	}

	protected String getDeathSound() {
		return "nevermine:HydroliskDeath";
	}

	protected String getHurtSound() {
		return "nevermine:HydroliskHit";
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("nevermine:EmperorBeastStep", 0.55f, 1.0f);
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return ((entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null);
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (getHealth() < 10.0f && !worldObj.isRemote) {
			final EntityHydrolisk var3 = new EntityHydrolisk(worldObj);
			var3.setLocationAndAngles(posX, posY, posZ, rotationYaw, rotationPitch);
			worldObj.spawnEntityInWorld(var3);
			if (!worldObj.isRemote) {
				setDead();
			}
		}
		if (rand.nextInt(80) == 25 && !worldObj.isRemote) {
			final EntityHydrolon var4 = new EntityHydrolon(worldObj);
			var4.setLocationAndAngles(posX, posY, posZ, rotationYaw, rotationPitch);
			worldObj.spawnEntityInWorld(var4);
		}
		if (rand.nextInt(200) == 43) {
			final EntityPlayer var5 = worldObj.getClosestVulnerablePlayerToEntity(this, 20.0);
			if (var5 == null || var5.getDistanceToEntity(this) > 20.0f) {
				return;
			}
			if (!worldObj.isRemote) {
				setPosition(var5.posX, var5.posY, var5.posZ);
			}
		}
	}

	public boolean interact(final EntityPlayer par1EntityPlayer) {
		final ItemStack var2 = par1EntityPlayer.inventory.getCurrentItem();
		if (par1EntityPlayer.inventory.consumeInventoryItem(Itemizer.HydroStone)) {
			setHealth(getHealth() - 50.0f);
		}
		return super.interact(par1EntityPlayer);
	}

	public boolean isEntityInvulnerable() {
		return true;
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(12.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(609.0);
	}
}
