package net.nevermine.projectiles.energy;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.nevermine.assist.EntityUtil;
import net.nevermine.fx.circular.YellowCircular;
import net.nevermine.fx.trail.thin.ThinCyanTrail;
import net.nevermine.mob.placement.HardProjectile;

public class EntityParalyzerShot extends EntityThrowable implements HardProjectile {
	private float damage;
	private int knockbackStrength;
	private int age = 0;
	private int amplify;

	public EntityParalyzerShot(World par1World) {
		super(par1World);
	}

	public EntityParalyzerShot(World par1World, EntityPlayer par3EntityPlayer, float dmg, int amp) {
		super(par1World, par3EntityPlayer);
		damage = dmg;
		amplify = amp;
		setThrowableHeading(motionX, motionY, motionZ, 3.0F, 1.0F);
	}

	public EntityParalyzerShot(World par1World, double par2, double par4, double par6) {
		super(par1World, par2, par4, par6);
	}

	protected float getGravityVelocity() {
		return 0.0F;
	}

	protected void onImpact(MovingObjectPosition movingobjectposition) {
		if (movingobjectposition.entityHit != null) {
			EntityUtil.shootEntity(movingobjectposition.entityHit, getThrower(), this, damage);
			if ((movingobjectposition.entityHit instanceof EntityLivingBase)) {
				((EntityLivingBase)movingobjectposition.entityHit).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 40, amplify));
				((EntityLivingBase)movingobjectposition.entityHit).addPotionEffect(new PotionEffect(Potion.weakness.id, 40, amplify));
			}
		}

		if (!worldObj.isRemote) {
			setDead();
		}
	}

	public void onUpdate() {
		super.onUpdate();

		if (age == 40) {
			if (!worldObj.isRemote) {
				setDead();
			}
		}
		else {
			age += 1;
		}
	}

	@SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
	public void onEntityUpdate() {
		super.onEntityUpdate();
		for (int var3 = 0; var3 < 8; var3++) {
			YellowCircular var21 = new YellowCircular(worldObj, posX, posY, posZ, 0.0D, 0.0D, 0.0D, 8);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(var21);

			ThinCyanTrail var22 = new ThinCyanTrail(worldObj, posX, posY, posZ, 0.0D, 0.0D, 0.0D, 8);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(var22);
		}
	}
}
