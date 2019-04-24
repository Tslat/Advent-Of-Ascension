package net.nevermine.fx.portal;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class EntityHavenPortalFX extends EntityFX {
	private float portalParticleScale;
	private double portalPosX;
	private double portalPosY;
	private double portalPosZ;

	public EntityHavenPortalFX(final World var1, final double var2, final double var4, final double var6, final double var8, final double var10, final double var12) {
		super(var1, var2, var4, var6, var8, var10, var12);
		motionX = var8;
		motionY = var10;
		motionZ = var12;
		posX = var2;
		portalPosX = var2;
		posY = var4;
		portalPosY = var4;
		posZ = var6;
		portalPosZ = var6;
		final float var13 = rand.nextFloat() * 0.6f + 0.4f;
		final float n = rand.nextFloat() * 0.2f + 0.5f;
		particleScale = n;
		portalParticleScale = n;
		particleBlue = (float)rand.nextGaussian();
		particleGreen = (float)rand.nextGaussian();
		particleRed = (float)rand.nextGaussian();
		particleMaxAge = (int)(Math.random() * 10.0) + 40;
		noClip = true;
		setParticleTextureIndex((int)(Math.random() * 8.0));
	}

	public void renderParticle(final Tessellator var1, final float var2, final float var3, final float var4, final float var5, final float var6, final float var7) {
		float var8 = (particleAge + var2) / particleMaxAge;
		var8 = 1.0f - var8;
		var8 *= var8;
		var8 = 1.0f - var8;
		particleScale = portalParticleScale * var8;
		super.renderParticle(var1, var2, var3, var4, var5, var6, var7);
	}

	public int getBrightnessForRender(final float var1) {
		final int var2 = super.getBrightnessForRender(var1);
		float var3 = particleAge / particleMaxAge;
		var3 *= var3;
		var3 *= var3;
		final int var4 = var2 & 0xFF;
		int var5 = var2 >> 16 & 0xFF;
		var5 += (int)(var3 * 15.0f * 16.0f);
		if (var5 > 240) {
			var5 = 240;
		}
		return var4 | var5 << 16;
	}

	public float getBrightness(final float var1) {
		final float var2 = super.getBrightness(var1);
		float var3 = particleAge / particleMaxAge;
		var3 *= var3 * var3 * var3;
		return var2 * (1.0f - var3) + var3;
	}

	public void onUpdate() {
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;
		final float var2;
		float var1 = var2 = particleAge / particleMaxAge;
		var1 = -var1 + var1 * var1 * 2.0f;
		var1 = 1.0f - var1;
		posX = portalPosX + motionX * var1;
		posY = portalPosY + motionY * var1 + (1.0f - var2);
		posZ = portalPosZ + motionZ * var1;
		if (particleAge++ >= particleMaxAge) {
			setDead();
		}
	}
}
