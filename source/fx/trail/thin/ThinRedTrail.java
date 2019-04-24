package net.nevermine.fx.trail.thin;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class ThinRedTrail extends EntityFX {
	public float portalParticleScale;
	public int last;

	public ThinRedTrail(World par1World, double par2, double par4, double par6, double par8, double par10, double par12, int age) {
		super(par1World, par2, par4, par6, par8, par10, par12);
		last = age;
		motionX = par8 + (float)(Math.random() * 2.0D - 1.0D) * 0.05F;
		motionY = par10 + (float)(Math.random() * 2.0D - 1.0D) * 0.05F;
		motionZ = par12 + (float)(Math.random() * 2.0D - 1.0D) * 0.05F;
		float var14 = rand.nextFloat() * 0.6F + 0.4F;
		portalParticleScale = particleScale = rand.nextFloat() * 0.2F + 0.5F;
		particleRed *= 5.4F;
		particleGreen *= 0.0F;
		particleBlue *= 0.0F;
		particleScale = 1.0F;
		particleMaxAge = (int)(3.0D / (rand.nextFloat() * 0.8D + 0.2D));
	}

	@Override
	public void onUpdate() {
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;

		if (particleAge++ >= particleMaxAge) {
			setDead();
		}

		setParticleTextureIndex(7 - particleAge * last / particleMaxAge);
		motionY += 0.004D;
		motionX *= 0.8999999761581421D;
		motionY *= 0.8999999761581421D;
		motionZ *= 0.8999999761581421D;

		if (onGround) {
			motionX *= 0.699999988079071D;
			motionZ *= 0.699999988079071D;
		}
	}
}