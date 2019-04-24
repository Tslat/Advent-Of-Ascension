package net.nevermine.mob.entity.overworld;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.nevermine.izer.equipment.Weaponizer;

public class EntityMegaGuardian extends EntityMob {
	public EntityMegaGuardian(final World par1World) {
		super(par1World);
		setSize(0.9f, 2.3f);
	}

	protected String getLivingSound() {
		return "nevermine:SpiritLiving";
	}

	protected String getDeathSound() {
		return "nevermine:SpiritDeath";
	}

	protected String getHurtSound() {
		return "nevermine:SpiritLiving";
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		if (rand.nextInt(150) == 35) {
			dropItem(Weaponizer.KrasaunsDawn, 1);
		}
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return (entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null;
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(12.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(150.0);
	}
}
