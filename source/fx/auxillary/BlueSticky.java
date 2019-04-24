package net.nevermine.fx.auxillary;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class BlueSticky extends EntityFX {
	public float portalParticleScale;
	public int last;

	public BlueSticky(final World par1World, final double par2, final double par4, final double par6, final double par8, final double par10, final double par12, final int age) {
		super(par1World, par2, par4, par6, par8, par10, par12);
		last = age;
		motionX = par8 + (float)(Math.random() * 2.0 - 1.0) * 0.05f;
		motionY = par10 + (float)(Math.random() * 2.0 - 1.0) * 0.05f;
		motionZ = par12 + (float)(Math.random() * 2.0 - 1.0) * 0.05f;
		final float var14 = rand.nextFloat() * 0.6f + 0.4f;
		final float n = rand.nextFloat() * 0.2f + 0.5f;
		particleScale = n;
		portalParticleScale = n;
		particleRed *= 0.0f;
		particleGreen *= 0.0f;
		particleBlue *= 0.9f;
		particleScale = rand.nextFloat() * rand.nextFloat() * 6.0f + 1.0f;
		particleMaxAge = (int)(16.0 / (rand.nextFloat() * 0.8 + 0.2)) + 2;
	}

	public void onUpdate() {
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;
		if (particleAge++ >= particleMaxAge) {
			setDead();
		}
		setParticleTextureIndex(128 + particleAge * last / particleMaxAge);
		motionY += 0.004;
		motionX *= 0.8999999761581421;
		motionY *= 0.8999999761581421;
		motionZ *= 0.8999999761581421;
		if (onGround) {
			motionX *= 0.699999988079071;
			motionZ *= 0.699999988079071;
		}
	}
}
