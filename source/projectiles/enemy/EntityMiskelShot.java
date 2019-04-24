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
import net.nevermine.fx.trail.WhiteTrail;

public class EntityMiskelShot extends EntityThrowable {
	private float damage;
	private int ability;
	private int age;

	public EntityMiskelShot(final World par1World) {
		super(par1World);
		age = 0;
	}

	public EntityMiskelShot(final World par1World, final EntityMob entityGoblin, final float dmg, final int color) {
		super(par1World, entityGoblin);
		age = 0;
		ability = color;
		damage = dmg;
	}

	public EntityMiskelShot(final World par1World, final double par2, final double par4, final double par6) {
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
					((EntityLivingBase)movingobjectposition.entityHit).addPotionEffect(new PotionEffect(Potion.blindness.id, 120, 15));
				}
				if (ability == 1) {
					((EntityLivingBase)movingobjectposition.entityHit).addPotionEffect(new PotionEffect(Potion.wither.id, 80, 6));
				}
				getThrower().heal(40.0f);
			}
			if (!worldObj.isRemote) {
				setDead();
			}
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
			final WhiteTrail var4 = new WhiteTrail(worldObj, posX, posY, posZ, 0.0, 0.0, 0.0, 5);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(var4);
		}
	}
}
