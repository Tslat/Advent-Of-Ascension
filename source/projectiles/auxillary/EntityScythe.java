package net.nevermine.projectiles.auxillary;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.nevermine.assist.ConfigurationHelper;
import net.nevermine.resource.energy.energyHelper;
import net.nevermine.resource.soulpower.soulPowerHelper;

public class EntityScythe extends EntityThrowable {
	private float damage;
	private int age;
	private int effect;

	public EntityScythe(final World par1World) {
		super(par1World);
		age = 0;
	}

	public EntityScythe(final World par1World, final EntityPlayer par3EntityPlayer, final float dmg, final int ability) {
		super(par1World, par3EntityPlayer);
		age = 0;
		damage = dmg;
		effect = ability;
		setThrowableHeading(motionX, motionY, motionZ, 3.0f, 1.0f);
	}

	public EntityScythe(final World par1World, final double par2, final double par4, final double par6) {
		super(par1World, par2, par4, par6);
		age = 0;
	}

	protected float getGravityVelocity() {
		return 0.0f;
	}

	protected void onImpact(final MovingObjectPosition movingobjectposition) {
		if (movingobjectposition.entityHit instanceof EntityLiving && !((EntityLiving)movingobjectposition.entityHit).isDead) {
			final EntityPlayer play = (EntityPlayer)getThrower();

			if (play != null && play.getHeldItem() != null) {
				if (EnchantmentHelper.getEnchantmentLevel(ConfigurationHelper.eslice, play.getHeldItem()) == 1) {
					movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), (float)(damage * 1.2));
				}
				else if (EnchantmentHelper.getEnchantmentLevel(ConfigurationHelper.eslice, play.getHeldItem()) == 2) {
					movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), (float)(damage * 1.4));
				}
				else if (EnchantmentHelper.getEnchantmentLevel(ConfigurationHelper.eslice, play.getHeldItem()) == 3) {
					movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), (float)(damage * 1.6));
				}
				else {
					movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), damage);
				}
			}
			if (play != null) {
				if (!play.capabilities.isCreativeMode)
					energyHelper.getProperties(play).removeBarValue(15.0f);

				if (play.getHeldItem() != null) {
					play.getHeldItem().damageItem(1, play);
				}

				if (effect == 1) {
					play.heal(1.0f);
				}

				if (effect == 2) {
					movingobjectposition.entityHit.setFire(10);
				}

				if (effect == 3) {
					soulPowerHelper.getProperties(play).regen(200.0f);
				}
			}
		}
		if (!worldObj.isRemote) {
			setDead();
		}
	}

	public void onUpdate() {
		super.onUpdate();
		if (age == 1 && !worldObj.isRemote) {
			setDead();
		}
		++age;
	}
}
