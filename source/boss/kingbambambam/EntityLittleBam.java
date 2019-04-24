package net.nevermine.boss.kingbambambam;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityLittleBam extends EntityMob {
	private static float explosionRadius;

	public EntityLittleBam(final World par1World) {
		super(par1World);
		setSize(0.7f, 0.9f);
	}

	protected String getLivingSound() {
		return "mob.bat.hurt";
	}

	protected String getDeathSound() {
		return "mob.bat.death";
	}

	protected String getHurtSound() {
		return "mob.bat.hurt";
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("mob.pig.step", 0.85f, 1.0f);
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return (entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null;
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.7);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0);
	}

	public boolean attackEntityAsMob(final Entity par1) {
		if (super.attackEntityAsMob(par1)) {
			if (par1 instanceof EntityLivingBase) {
				worldObj.createExplosion(this, posX, posY, posZ, EntityLittleBam.explosionRadius, false);
				setDead();
			}
			return true;
		}
		return false;
	}

	static {
		EntityLittleBam.explosionRadius = 2.0f;
	}
}
