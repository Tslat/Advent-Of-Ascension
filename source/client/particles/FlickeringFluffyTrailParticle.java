package net.tslat.aoa3.client.particles;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.ParticleRegister;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class FlickeringFluffyTrailParticle extends Particle {
	private final int textureIndexOffset;

	public FlickeringFluffyTrailParticle(World world, double posX, double posY, double posZ, double velocityX, double velocityY, double velocityZ, int colour, float scale, int lifespanModifier, int textureIndexOffset) {
		super(world, posX, posY, posZ, velocityX, velocityY, velocityZ);

		this.particleTexture = ParticleRegister.getTextureAtlas();
		this.motionX = velocityX + (float)(Math.random() * 2.0 - 1.0) * 0.05f;
		this.motionY = velocityY + (float)(Math.random() * 2.0 - 1.0) * 0.05f;
		this.motionZ = velocityZ + (float)(Math.random() * 2.0 - 1.0) * 0.05f;
		this.particleScale = (rand.nextFloat() * rand.nextFloat() * 6.0f + 1.0f) * scale;
		this.particleMaxAge = (int)(lifespanModifier / (rand.nextFloat() * 0.8 + 0.2));
		float colorMod = rand.nextFloat() * 0.7f + 0.3f;
		this.particleRed = ((colour >> 16) / 255.0f) * colorMod;
		this.particleGreen = (((colour >> 8) & 0xff) / 255.0f) * colorMod;
		this.particleBlue = ((colour & 0xff) / 255.0f) * colorMod;
		this.particleAlpha = (colour >> 24) / 255.0f;

		if (particleAlpha == 0)
			particleAlpha = 1f;

		this.particleTextureIndexY = 4;
		this.textureIndexOffset = textureIndexOffset;
	}

	@Override
	public int getFXLayer() {
		return 1;
	}

	@Override
	public void onUpdate() {
		this.prevPosX = posX;
		this.prevPosY = posY;
		this.prevPosZ = posZ;

		if (particleAge++ >= particleMaxAge)
			setExpired();

		this.particleTextureIndexX = 7 - ((int)((particleAge / (float)particleMaxAge) * textureIndexOffset) % 8);

		move(motionX, motionY, motionZ);

		this.motionX *= 0.8999999761581421;
		this.motionY *= 0.8999999761581421;
		this.motionZ *= 0.8999999761581421;

		if (onGround) {
			this.motionX *= 0.699999988079071;
			this.motionZ *= 0.699999988079071;
		}
	}

	@Override
	public void renderParticle(BufferBuilder buffer, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
		double texturePixelRatio = ParticleRegister.getParticleTextureRatio() * 8;
		double minU = particleTexture.getMinU() + particleTextureIndexX * texturePixelRatio;
		double maxU = minU + texturePixelRatio;
		double minV = particleTexture.getMinV() + particleTextureIndexY * texturePixelRatio;
		double maxV = minV + texturePixelRatio;
		double x = prevPosX + (posX - prevPosX) * partialTicks - interpPosX;
		double y = prevPosY + (posY - prevPosY) * partialTicks - interpPosY;
		double z = prevPosZ + (posZ - prevPosZ) * partialTicks - interpPosZ;
		double scale = 0.1F * particleScale;
		double scaledUDDirX = rotationXY * scale;
		double scaledUDDirY = rotationZ * scale;
		double scaledUDDirZ = rotationXZ * scale;
		double scaledLRDirX = rotationX * scale;
		double scaledLRDirZ = rotationYZ * scale;
		int combinedBrightness = getBrightnessForRender(partialTicks);
		int skyLight = combinedBrightness >> 16 & 65535;
		int blockLight = combinedBrightness & 65535;

		buffer.pos(x - scaledLRDirX - scaledUDDirX, y - scaledUDDirY, z - scaledLRDirZ - scaledUDDirZ)
				.tex(maxU, maxV)
				.color(particleRed, particleGreen, particleBlue, particleAlpha)
				.lightmap(skyLight, blockLight)
				.endVertex();
		buffer.pos(x - scaledLRDirX + scaledUDDirX,y + scaledUDDirY,z - scaledLRDirZ + scaledUDDirZ)
				.tex(maxU, minV)
				.color(particleRed, particleGreen, particleBlue, particleAlpha)
				.lightmap(skyLight, blockLight)
				.endVertex();
		buffer.pos(x + scaledLRDirX + scaledUDDirX,y + scaledUDDirY,z + scaledLRDirZ + scaledUDDirZ)
				.tex(minU, minV)
				.color(particleRed, particleGreen, particleBlue, particleAlpha)
				.lightmap(skyLight, blockLight)
				.endVertex();
		buffer.pos(x + scaledLRDirX - scaledUDDirX,y - scaledUDDirY,z + scaledLRDirZ - scaledUDDirZ)
				.tex(minU, maxV)
				.color(particleRed, particleGreen, particleBlue, particleAlpha)
				.lightmap(skyLight, blockLight)
				.endVertex();
	}

	public static class Factory implements IParticleFactory {
		@Nullable
		@Override
		public Particle createParticle(int id, World world, double posX, double posY, double posZ, double velocityX, double velocityY, double velocityZ, int... args) {
			return new FlickeringFluffyTrailParticle(Minecraft.getMinecraft().world, posX, posY, posZ, velocityX, velocityY, velocityZ, args.length > 0 ? args[0] : Enums.RGBIntegers.WHITE, args.length > 1 ? args[1] / 100f : 1f, args.length > 2 ? args[2] : 3, args.length > 3 ? args[3] : 7);
		}
	}
}
