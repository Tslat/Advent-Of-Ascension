package net.nevermine.boss.elusive;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityElusiveClone extends EntityMob {
	private static float explosionRadius;

	public EntityElusiveClone(final World par1World) {
		super(par1World);
		setSize(1.1f, 1.9f);
	}

	protected String getLivingSound() {
		switch (rand.nextInt(3)) {
			case 0: {
				return "nevermine:ElusiveLiving1";
			}
			case 1: {
				return "nevermine:ElusiveLiving2";
			}
			case 2: {
				return "nevermine:ElusiveLiving3";
			}
			default: {
				return "nevermine:ElusiveLiving";
			}
		}
	}

	protected String getDeathSound() {
		return "nevermine:ElusiveDeath";
	}

	protected String getHurtSound() {
		return "nevermine:ElusiveHit";
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return (entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null;
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(8.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0);
	}

	public boolean attackEntityAsMob(final Entity par1Entity) {
		super.attackEntityAsMob(par1Entity);
		if (entityToAttack != null) {
			addPotionEffect(new PotionEffect(14, 40, 2));
			if (par1Entity instanceof EntityLivingBase) {
				((EntityLivingBase)par1Entity).addPotionEffect(new PotionEffect(15, 80, 2));
			}
			return true;
		}
		return false;
	}

	public void onDeath(final DamageSource var1) {
		super.onDeath(var1);
		worldObj.createExplosion(this, posX, posY, posZ, EntityElusiveClone.explosionRadius, false);
		if (!worldObj.isRemote) {
			setDead();
		}
	}

	static {
		EntityElusiveClone.explosionRadius = 2.0f;
	}
}
