package net.nevermine.projectiles.enemy;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.nevermine.fx.trail.CyanTrail;
import net.nevermine.fx.trail.WhiteTrail;
import net.nevermine.fx.trail.YellowTrail;

public class EntityCraexxeusShot extends EntityThrowable {
	private float damage;
	private float grav;
	private int age = 0;

	public EntityCraexxeusShot(World par1World) {
		super(par1World);
	}

	public EntityCraexxeusShot(World par1World, EntityMob entityGoblin, float dmg, float gravity) {
		super(par1World, entityGoblin);
		this.grav = gravity;
		this.damage = dmg;
	}

	public EntityCraexxeusShot(World par1World, double par2, double par4, double par6) {
		super(par1World, par2, par4, par6);
	}

	protected float getGravityVelocity() {
		return 0.075F;
	}

	protected void onImpact(MovingObjectPosition movingobjectposition) {
		if (movingobjectposition.entityHit != null) {
			movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), this.damage);
		}

		if (!this.worldObj.isRemote) {
			setDead();
		}
	}

	public void onUpdate() {
		super.onUpdate();
		if (this.age == 40) {
			if (!this.worldObj.isRemote) {
				setDead();
			}
		}
		else {
			this.age += 1;
		}
	}

	@SideOnly(Side.CLIENT)
	public void onEntityUpdate() {
		super.onEntityUpdate();
		for (int var3 = 0; var3 < 8; var3++) {
			CyanTrail var27 = new CyanTrail(this.worldObj, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D, 5);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(var27);

			YellowTrail var29 = new YellowTrail(this.worldObj, this.posX, this.posY + 0.25D, this.posZ, 0.0D, 0.0D, 0.0D, 5);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(var29);

			WhiteTrail var28 = new WhiteTrail(this.worldObj, this.posX, this.posY - 0.25D, this.posZ, 0.0D, 0.0D, 0.0D, 5);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(var28);
		}
	}
}
