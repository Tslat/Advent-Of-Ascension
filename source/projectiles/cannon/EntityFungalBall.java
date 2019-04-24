package net.nevermine.projectiles.cannon;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.nevermine.assist.EntityUtil;
import net.nevermine.fx.trail.BlueTrail;
import net.nevermine.fx.trail.GreenTrail;
import net.nevermine.fx.trail.PurpleTrail;
import net.nevermine.fx.trail.YellowTrail;
import net.nevermine.projectiles.HardProjectile;

public class EntityFungalBall extends EntityThrowable implements HardProjectile {
	private float damage;
	float f4;
	private int age;
	private int knockbackStrength;

	public EntityFungalBall(final World par1World) {
		super(par1World);
		age = 0;
	}

	public EntityFungalBall(final World par1World, final EntityPlayer par3EntityPlayer, final float dmg, final int knock) {
		super(par1World, par3EntityPlayer);
		age = 0;
		damage = dmg;
		knockbackStrength = knock;
		setThrowableHeading(motionX, motionY, motionZ, 3.0f, 1.0f);
	}

	public EntityFungalBall(final World par1World, final double par2, final double par4, final double par6) {
		super(par1World, par2, par4, par6);
		age = 0;
	}

	protected float getGravityVelocity() {
		return 0.1f;
	}

	protected void onImpact(final MovingObjectPosition movingobjectposition) {
		if (movingobjectposition.entityHit != null) {
			EntityUtil.shootEntity(movingobjectposition.entityHit, getThrower(), this, damage);

			f4 = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);

			if (movingobjectposition.entityHit instanceof EntityLivingBase) {
				((EntityLivingBase)movingobjectposition.entityHit).addPotionEffect(new PotionEffect(Potion.poison.id, 100, 2));
			}

			movingobjectposition.entityHit.addVelocity(motionX * knockbackStrength * 0.6000000238418579 / f4, 0.1, motionZ * knockbackStrength * 0.6000000238418579 / f4);
		}

		if (!worldObj.isRemote) {
			setDead();
		}
	}

	public void onUpdate() {
		super.onUpdate();
		if (age == 30) {
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
			final int clr = rand.nextInt(4);
			if (clr == 1) {
				final GreenTrail var4 = new GreenTrail(worldObj, posX, posY, posZ, 0.0, 0.0, 0.0, 5);
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(var4);
			}
			else if (clr == 2) {
				final YellowTrail var5 = new YellowTrail(worldObj, posX, posY, posZ, 0.0, 0.0, 0.0, 5);
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(var5);
			}
			else if (clr == 3) {
				final BlueTrail var6 = new BlueTrail(worldObj, posX, posY, posZ, 0.0, 0.0, 0.0, 5);
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(var6);
			}
			else {
				final PurpleTrail var7 = new PurpleTrail(worldObj, posX, posY, posZ, 0.0, 0.0, 0.0, 5);
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(var7);
			}
		}
	}
}
