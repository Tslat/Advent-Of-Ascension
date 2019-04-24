package net.nevermine.projectiles.enemy;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.nevermine.fx.trail.BlackTrail;

public class EntityFungiShot extends EntityThrowable {
	private float damage;
	private int ability;
	private int age;

	public EntityFungiShot(final World par1World) {
		super(par1World);
		age = 0;
	}

	public EntityFungiShot(final World par1World, final EntityMob entityGoblin, final float dmg, final int color) {
		super(par1World, entityGoblin);
		age = 0;
		ability = color;
		damage = dmg;
	}

	public EntityFungiShot(final World par1World, final double par2, final double par4, final double par6) {
		super(par1World, par2, par4, par6);
		age = 0;
	}

	protected float getGravityVelocity() {
		return 0.075f;
	}

	protected void onImpact(final MovingObjectPosition movingobjectposition) {
		if (movingobjectposition.entityHit instanceof EntityLivingBase) {
			movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), damage);
			if (movingobjectposition.entityHit instanceof EntityLivingBase) {
				if (ability == 0) {
					((EntityLivingBase)movingobjectposition.entityHit).addPotionEffect(new PotionEffect(19, 90, 1));
				}
				if (ability == 1) {
					((EntityLivingBase)movingobjectposition.entityHit).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 80, 2));
				}
				if (ability == 2) {
					((EntityLivingBase)movingobjectposition.entityHit).addPotionEffect(new PotionEffect(Potion.poison.id, 80, 4));
				}
				if (ability == 3) {
					((EntityLivingBase)movingobjectposition.entityHit).addPotionEffect(new PotionEffect(Potion.confusion.id, 140, 100));
				}
				if (ability == 4) {
					((EntityLivingBase)movingobjectposition.entityHit).addPotionEffect(new PotionEffect(Potion.weakness.id, 100, 2));
				}
			}
		}
		if (!worldObj.isRemote) {
			setDead();
		}
	}

	public void onUpdate() {
		super.onUpdate();
		if (age == 200) {
			if (!worldObj.isRemote) {
				setDead();
			}
		}
		else {
			++age;
		}
	}

	@SideOnly(Side.CLIENT)
	public void onEntityUpdate() {
		super.onEntityUpdate();
		for (int var3 = 0; var3 < 8; ++var3) {
			final BlackTrail var4 = new BlackTrail(worldObj, posX, posY, posZ, 0.0, 0.0, 0.0, 5);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(var4);
		}
	}
}
