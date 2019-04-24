package net.nevermine.projectiles.enemy;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.nevermine.fx.circular.CyanCircular;
import net.nevermine.fx.circular.YellowCircular;

public class EntityCraexxeusNuke extends EntityThrowable {
	private float damage;
	private float grav;
	private int age = 0;

	public EntityCraexxeusNuke(World par1World) {
		super(par1World);
	}

	public EntityCraexxeusNuke(World par1World, EntityMob entityGoblin, float dmg, float gravity) {
		super(par1World, entityGoblin);
		this.grav = gravity;
		this.damage = dmg;
	}

	public EntityCraexxeusNuke(World par1World, double par2, double par4, double par6) {
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
			CyanCircular var27 = new CyanCircular(this.worldObj, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D, 5);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(var27);

			YellowCircular var29 = new YellowCircular(this.worldObj, this.posX, this.posY + 0.25D, this.posZ, 0.0D, 0.0D, 0.0D, 5);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(var29);

			YellowCircular var28 = new YellowCircular(this.worldObj, this.posX, this.posY - 0.25D, this.posZ, 0.0D, 0.0D, 0.0D, 5);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(var28);
		}
	}
}
